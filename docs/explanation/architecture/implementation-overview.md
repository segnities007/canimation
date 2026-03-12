# 実装概要

この文書は、現時点のリポジトリ実装を説明する。
実装実態を優先した文書として、現在のコードベースがどのように構成され、
ランタイムが実際にどう動いているかを整理することを目的とする。

## 対象範囲

- 公開ランタイムの流れ
- リポジトリの module 境界
- showcase app の構成
- platform integration point
- 現在使っている validation コマンド

## ランタイムフロー

コアのランタイム経路は次のとおり。

1. `CanimationProvider` が次の入力から `CanimationContext` を作る。
   - `CanimationTokens`
   - `CanimationPolicy`
   - `PresetRegistry`
   - `systemReducedMotion`
2. `CanimationPolicyResolver` がその入力を `CanimationLevel` に解決する。
3. `PresetRegistry` が `CanimationPresetSpec` から preset ベースの enter / exit spec を解決する。
4. `CanimationEffect` ベースの API は `CanimationSpec` を直接構築する。
5. `Modifier.canimation*` 系 API は `graphicsLayer` ベースの transform を通して alpha / offset / scale / rotation をアニメーションさせる。

この構成により、ランタイムは次の責務に分離されている。

- policy 解決
- preset 解決
- effect 合成
- UI 側 modifier 適用

## Module Map

| Module | 現在の責務 |
|--------|-----------|
| `canimation-core` | provider, context, modifier, visibility/transition, namespace entry を持つ公開ランタイムの基本要素 |
| `canimation-tokens` | duration/easing/distance/spring token の物理的 SSoT |
| `canimation-primitives` | effect/spec/policy/direction など primitive motion 型の物理的 SSoT |
| `canimation-semantics` | descriptor/registry/taxonomy など semantic contract の物理的 SSoT |
| `canimation-recipes` | target-state recipe catalog surface。現在は built-in semantic recipe catalog を再公開する wrapper module |
| `canimation-runtime` | target-state runtime API surface。現在は provider/modifier/visibility/transition を再公開する wrapper module |
| `canimation-presets` | built-in preset 定義と preset registry の SSoT |
| `canimation-a11y` | motion preference の契約と policy bridge helper |
| `canimation-diagnostics` | diagnostics overlay, metrics computation, overlay state |
| `canimation-test` | shared test clock と test host helper |
| `canimation-test-kit` | target-state test-facing wrapper surface |
| `canimation-compat` | compatibility migration surface |
| `canimation-experimental` | experimental surface isolation point |
| `canimation-platform-android` | Android の motion preference / frame metrics adapter |
| `canimation-platform-desktop` | Desktop の motion preference / frame metrics adapter |
| `canimation-platform-ios` | iOS の motion preference adapter と diagnostics fallback |
| `canimation-platform-web` | Web の motion preference adapter と diagnostics fallback |
| `composeApp` | 利用者と同じ runtime API を使う interactive showcase app |
| `androidApp` | `composeApp` を包む薄い Android wrapper |
| `iosApp` | Compose iOS controller を包む薄い SwiftUI host |

## Repository Layout Note

target-state の architecture 文書では consumer app 群の最終到達点として `samples/` を使う。
一方、current repository では `composeApp/`、`androidApp/`、`iosApp/` が root に残っている。
app 側の現状構成は `docs/reference/showcase/consumer-app-structure.md` を current SSoT とする。

shared build convention は `settings.gradle.kts` から include される `build-logic/` にある。

## Public API と repository 内部実装

利用者向け API の中心は `canimation-core` にある。
一方で repository 内部では、preset・accessibility・diagnostics・test support・platform adapter を独立して進化させられるよう、より細かい構成を取っている。

現在は semantics-first の第一段階として、runtime entry を維持しつつ owner module を段階的に分離している。

- semantic contract 型 (`CanimationRecipeDescriptor`, `CanimationRecipeRegistry` など) -> `canimation-semantics`
- built-in semantic recipe catalog と `DefaultCanimationRecipeRegistry` -> `canimation-recipes`
- recipe-backed provider / modifier / visibility / transition entry -> `canimation-runtime`

このうち次は `canimation-core` から target-state module へ物理移動済みである。

- token 型 (`CanimationTokens`, `DurationTokens`, `EasingTokens`, `DistanceTokens`, `SpringTokens`, `SpringSpec`) -> `canimation-tokens`
- primitive 型 (`CanimationEffect`, `CanimationSpec`, `CanimationRange`, `CanimationDpRange`, `AnimationDirection`, `CanimationLevel`, `CanimationPolicy`) -> `canimation-primitives`
- semantic contract 型 (`CanimationRecipeDescriptor`, `CanimationRecipeRegistry` など) -> `canimation-semantics`
- semantic recipe catalog (`DefaultCanimationRecipeRegistry`, `Canimation.Content`, `Canimation.Feedback`, など) -> `canimation-recipes`

現時点では次の見方が基本になる。

- 利用者は `canimation-core` を中心に理解する
- コントリビューターは multi-module 全体構成で理解する

## Showcase App の構成

`composeApp` は repository の `Screen + StateHolder + State/Reducer` 規約に従う。

例:

- `AppState.kt`
- `screen/docs/DocsStateHolder.kt`
- `screen/presets/PresetGalleryState.kt`
- `screen/showcase/gallery/ShowcaseGalleryState.kt`
- `screen/showcase/detail/ShowcaseDetailState.kt`

これにより showcase app では、予測しやすい UDF 形が保たれる。

`State -> Screen -> Event -> Reducer -> State`

また、`StagedProgressionEffect.kt` のような小さな staged-entry helper を使い、screen composable に業務判断を混ぜずに demo UI の段階的表示を実現している。

## Preset と Effect のアーキテクチャ

この repository では、意図的に 2 つの拡張経路を共存させている。

- **Preset path**
  - `CanimationPreset`
  - `CanimationPresetSpec`
  - `PresetRegistry`
  - `PresetsExtensionRegistry`
- **Effect path**
  - `CanimationEffect`
  - `Canimation.*` namespace
  - `Modifier.canimation(...)`

preset path はより SSoT 駆動で registry 中心、effect path はより簡潔で composition 指向という役割分担になっている。

## Accessibility と motion handling

ランタイムは 3 つの motion level を解決対象として持つ。

- `Full`
- `Reduced`
- `Off`

共通挙動は次のとおり。

- `SystemAware` は取得可能なら platform preference を尊重する
- preference が不明な場合は `Reduced` に fallback する
- custom spec は `CanimationSpec.toReduced()` で reduced motion を導出できる
- preset spec は registry の SSoT 上で reduced enter / exit variant を明示的に定義する

参照:

- `docs/quality/accessibility/tier-1-validation.md`
- `docs/quality/accessibility/tier-2-platform-compatibility.md`

## Platform Integration

platform ごとの motion preference 取得は source set ごとに分かれている。

- Android: `Settings.Global.ANIMATOR_DURATION_SCALE`
- iOS: `UIAccessibilityIsReduceMotionEnabled()`
- Desktop: `Toolkit.getDesktopProperty(...)`
- Web (JS): `matchMedia("(prefers-reduced-motion: reduce)")`
- WasmJs showcase shell: 現状は `null` を返し、安全な fallback 挙動に依存する

diagnostics 対応は現状 Android と Desktop が最も強い。
Web と iOS は empty metrics を返す fallback collector を使っている。

## Validation Commands

現在の repository baseline は次のコマンドで検証している。

```bash
./gradlew allTests --max-workers=2 --no-daemon
./gradlew :koverHtmlReport :koverXmlReport --max-workers=2 --no-daemon
./gradlew compileLibraryAndroid compileLibraryJvm :androidApp:assembleDebug :composeApp:compileKotlinJvm --max-workers=2 --no-daemon
./gradlew compileLibraryApple :composeApp:packageDistributionForCurrentOS :composeApp:linkDebugFrameworkIosSimulatorArm64 --max-workers=2 --no-daemon
./gradlew compileLibraryWeb :composeApp:compileKotlinWasmJs --max-workers=2 --no-daemon
./gradlew releaseReadiness --max-workers=2 --no-daemon
bash scripts/security-audit.sh
bash scripts/validate-workflows.sh
bash scripts/validate-governance-docs.sh
```

`allTests` と Kover report は CI と同様に別 invocation で実行する。

browser-based JS / Wasm tests は Kotlin Multiplatform の Karma 既定に加え、
`composeApp/karma.config.d/chrome-headless-ci.js` で CI 向け headless Chrome launcher を上書きする。
これにより Linux CI や constrained local environment でも `--no-sandbox` と timeout 調整を一貫適用する。

CI の形は `.github/workflows/ci.yml` にある。

## Related Documents

- `docs/reference/architecture/ideal-architecture-blueprint.md`
- `guideline/README.md`
- `docs/reference/release/versioning-policy.md`
- `CHANGELOG.md`

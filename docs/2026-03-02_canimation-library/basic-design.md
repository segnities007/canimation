# canimation 基本設計書

- 作成日: 2026-03-02
- バージョン: 0.1 (Draft)
- 対象: Compose Multiplatform (CMP) でアニメーション実装を簡単化したい開発者

## 1. 目的

CMP でのアニメーション実装において、以下の課題を解消する。

- 画面ごとに `animate*` を都度手書きするため、実装量が多い
- Duration/Easing が分散し、動きの統一感が崩れやすい
- Reduce Motion 対応が各画面実装に依存し、漏れやすい
- パフォーマンス劣化や jank を開発時に検知しにくい
- テストが目視中心で、回帰検知が弱い

本ライブラリは「設計 -> 実装 -> 品質担保」を一貫して支援する。

## 2. スコープ

## 2.1 対象 (In Scope)

- モーショントークン管理 (`CanimationTokens`)
- 意図ベースの高レベル API (`Modifier.canimationEnter(...)` など)
- アクセシビリティポリシー (`Reduce Motion`) の共通適用
- 開発時診断 (FPS / frame time / jank の可視化)
- アニメーションの決定論的テスト支援 (仮想クロック)
- Android/iOS/Desktop/Web への段階的対応

## 2.2 対象外 (Out of Scope)

- GUI タイムラインエディタ
- 2D/3D ゲームエンジン機能
- 物理演算、パーティクル、シェーダーオーサリング
- ノーコード制作環境

## 3. 非機能要件

- API 一貫性: 同じ意図に対して同じ API 形状で書けること
- 低オーバーヘッド: ライブラリ導入で体感ジャンクが増えないこと
- 安全デフォルト: 何も設定しなくても過剰演出になりにくいこと
- A11y 対応: System の Reduce Motion を標準で尊重すること
- テスト容易性: 時刻指定で挙動を再現可能であること
- 移植性: `commonMain` 優先、最小限の `expect/actual` で吸収すること

## 4. ユースケース

1. 画面遷移時の「フェード + スライド」の Enter/Exit を 1 行で適用したい
2. アプリ全体で同一モーションルールを強制したい
3. OS の Reduce Motion 設定を反映したい
4. 開発中に重いアニメーションを即検知したい
5. CI でアニメーション回帰を自動検出したい
6. プリセットにない独自のアニメーションを Token 体系に沿って定義したい

## 5. 成果物 (モジュール構成)

## 5.1 モジュール一覧

1. `canimation-core`
- 高レベル API（Enter / Exit / Transition / Emphasize）
- Token 解決
- Policy 適用
- Transition 実行
- `CanimationSpec`（カスタムアニメーション定義型）
- `PresetRegistry`（組み込み 5 プリセットの spec 保持 + カスタマイズ機構）

2. `canimation-presets`
- 追加プリセット (`SharedAxisX`, `SharedAxisY`, `ContainerTransform` など)
- カスタムプリセット構築 DSL
- Reduced Motion 向け代替プリセット

3. `canimation-a11y`
- OS 設定取得アダプタ
- Policy 判定ロジック

4. `canimation-diagnostics`
- FPS/frame time/jank 可視化オーバーレイ
- 開発時警告フック

5. `canimation-test`
- `CanimationTestClock`
- 時刻固定アサーション
- テスト用 Host

6. `canimation-platform-*`
- `android`, `ios`, `desktop`, `web` の `actual` 実装群

## 5.2 依存方向

`presets`, `a11y`, `diagnostics`, `test` -> `core`  
`platform-*` -> `core` / `a11y`  
`core` は上位モジュールに依存しない

## 6. API 設計草案

## 6.1 基本型

```kotlin
@Immutable
data class DurationTokens(val short: Duration, val medium: Duration, val long: Duration)

@Immutable
data class EasingTokens(
    val standard: Easing,
    val emphasizedDecelerate: Easing,
    val emphasizedAccelerate: Easing,
    val decelerate: Easing,
    val accelerate: Easing
)

@Immutable
data class DistanceTokens(val small: Dp, val medium: Dp, val large: Dp)

@Immutable
data class SpringTokens(val gentle: SpringSpec, val snappy: SpringSpec)

@Immutable
data class CanimationTokens(
    val duration: DurationTokens,
    val easing: EasingTokens,
    val distance: DistanceTokens,
    val spring: SpringTokens
)

enum class CanimationLevel {
    Full, Reduced, Off
}

sealed interface CanimationPolicy {
    data object SystemAware : CanimationPolicy
    data object AlwaysFull : CanimationPolicy
    data object AlwaysReduced : CanimationPolicy
    data object AlwaysOff : CanimationPolicy
}
```

## 6.2 プロバイダ

```kotlin
@Composable
fun CanimationProvider(
    tokens: CanimationTokens = CanimationTokens.Default,
    policy: CanimationPolicy = CanimationPolicy.SystemAware,
    presetRegistry: PresetRegistry = PresetRegistry.Default,
    content: @Composable () -> Unit
)
```

## 6.3 Modifier API

```kotlin
enum class CanimationPreset { FadeUp, Fade, ScaleIn, SlideLeft, SlideRight }

enum class AnimationDirection { Enter, Exit }

fun Modifier.canimationEnter(
    visible: Boolean,
    preset: CanimationPreset = CanimationPreset.FadeUp
): Modifier

fun Modifier.canimationExit(
    visible: Boolean,
    preset: CanimationPreset = CanimationPreset.FadeUp
): Modifier

fun Modifier.canimationTransition(
    visible: Boolean,
    enterPreset: CanimationPreset = CanimationPreset.FadeUp,
    exitPreset: CanimationPreset = enterPreset
): Modifier

fun Modifier.canimationEmphasize(
    active: Boolean,
    preset: CanimationPreset = CanimationPreset.ScaleIn
): Modifier
```

## 6.3.1 カスタムアニメーション API

```kotlin
@Immutable
data class CanimationRange(val from: Float, val to: Float)

@Immutable
data class CanimationDpRange(val from: Dp, val to: Dp)

@Immutable
data class CanimationSpec(
    val durationMs: Int,
    val easing: Easing,
    val alpha: CanimationRange? = null,
    val offsetX: CanimationDpRange? = null,
    val offsetY: CanimationDpRange? = null,
    val scale: CanimationRange? = null
)

@Immutable
data class CanimationPresetSpec(
    val fullEnter: CanimationSpec,
    val fullExit: CanimationSpec,
    val reducedEnter: CanimationSpec,
    val reducedExit: CanimationSpec
)

fun Modifier.canimationEnter(
    visible: Boolean,
    fullSpec: CanimationSpec,
    reducedSpec: CanimationSpec = fullSpec.toReduced()
): Modifier

fun Modifier.canimationExit(
    visible: Boolean,
    fullSpec: CanimationSpec,
    reducedSpec: CanimationSpec = fullSpec.toReduced()
): Modifier

fun Modifier.canimationTransition(
    visible: Boolean,
    enterFullSpec: CanimationSpec,
    enterReducedSpec: CanimationSpec = enterFullSpec.toReduced(),
    exitFullSpec: CanimationSpec = enterFullSpec.reversed(),
    exitReducedSpec: CanimationSpec = enterReducedSpec.reversed()
): Modifier
```

プリセットは `CanimationSpec` の定義済みインスタンスとして内部的に実装する。
`CanimationSpec.toReduced()` は移動量を 1/4、duration を `short` に変換し、easing を方向に応じて置換するデフォルト縮退関数（詳細は `spec-values.md` 8.1 章）。Enter spec に対して使用する前提で設計されており、Exit の Reduced spec は `enterReducedSpec.reversed()` で導出する。
`CanimationSpec.reversed()` は alpha/offset/scale の `from` と `to` を入れ替え、easing を `accelerate` に置換する。

## 6.4 Composable API

```kotlin
@Composable
fun CanimationVisibility(
    visible: Boolean,
    enterPreset: CanimationPreset = CanimationPreset.FadeUp,
    exitPreset: CanimationPreset = enterPreset,
    content: @Composable () -> Unit
)

@Composable
fun CanimationVisibility(
    visible: Boolean,
    enterFullSpec: CanimationSpec,
    enterReducedSpec: CanimationSpec = enterFullSpec.toReduced(),
    exitFullSpec: CanimationSpec = enterFullSpec.reversed(),
    exitReducedSpec: CanimationSpec = enterReducedSpec.reversed(),
    content: @Composable () -> Unit
)
```

## 6.5 診断 API

```kotlin
@Composable
fun CanimationDiagnosticsOverlay(
    enabled: Boolean = true,
    showFps: Boolean = true,
    showFrameTime: Boolean = true,
    jankThresholdMs: Int = 16,
    content: @Composable () -> Unit
)
```

## 6.6 テスト API

```kotlin
class CanimationTestClock {
    fun advanceBy(millis: Long)
    fun advanceTo(millis: Long)
}

@Composable
fun CanimationTestHost(clock: CanimationTestClock, content: @Composable () -> Unit)
```

## 7. 実行モデル

1. `CanimationProvider` が `LocalCanimationContext` を提供する
2. `LocalCanimationContext` は次を保持する
   - 解決済み `CanimationTokens`
   - 現在の `CanimationLevel` (`Full/Reduced/Off`)
   - 時刻ソース (本番/テスト)
   - `PresetRegistry`（プリセット → spec 解決テーブル）

3. `canimationEnter` / `canimationExit` / `canimationTransition` などの高レベル API は
   - `CanimationPreset` または `CanimationSpec` を取得
   - `CanimationLevel` を参照
   - `Full` の場合は Full spec、`Reduced` の場合は Reduced spec で実行する
   - プリセット使用時は `PresetRegistry` から spec を解決する

4. `CanimationLevel.Off` の場合は `snap`（duration=0ms）を使用する

5. Exit アニメーション方針:
   - プリセット指定時: Enter spec を反転（alpha/offset/scale の始点と終点を入れ替え）して Exit に使用
   - `exitPreset` が明示された場合: 指定 Exit プリセットの spec を使用
   - `CanimationSpec` 直接指定時: ユーザー指定の Exit spec をそのまま使用

## 7.1 プリセット解決アーキテクチャ

- `canimation-core` に 5 つの組み込みプリセット spec を内蔵する
- `PresetRegistry` は core に定義し、デフォルトで組み込み 5 プリセットを含む
- `canimation-presets` モジュールは追加プリセットとカスタムプリセット構築 DSL を提供する
- ユーザーは `CanimationProvider(presetRegistry = ...)` で追加プリセットを登録可能

```text
ユーザーコード
  │
  ▼
CanimationProvider(tokens, policy, presetRegistry)
  │
  ├── LocalCanimationContext に context を注入
  │
  ▼
Modifier.canimationTransition(visible, enterPreset, exitPreset)
  │
  ├── context.presetRegistry.resolve(preset, level, direction) → CanimationSpec
  ├── level == Off → snap（即時遷移）
  └── Compose animate*AsState で実行
```

## 7.2 エラーハンドリング方針

1. **`CanimationProvider` 未設定時**: `LocalCanimationContext` は安全デフォルトを返す
   - `CanimationTokens.Default`
   - `CanimationLevel.Full`
   - 本番時刻ソース
   - 組み込み `PresetRegistry`
   - アニメーションは正常動作する。Provider は「上書きしたい場合」に使う

2. **プラットフォーム motion preference 取得失敗時**: `Reduced` を返す（安全側に倒す）
   - 全プラットフォームの fallback 実装で統一する

3. **`CanimationTestClock` のテスト外使用時**: 本番クロックにフォールバックする
   - debug ビルドのみ warning ログを出力する

4. **未登録 `CanimationPreset` 参照時**: `Fade` プリセットにフォールバックする
   - debug ビルドのみ warning ログを出力する

5. **`CanimationSpec` の不正値**: コンストラクタで検証し `IllegalArgumentException` を投げる
   - `durationMs < 0`、`alpha` の `from`/`to` が `0f..1f` 外、`scale` の `from`/`to` が `0f` 未満の場合

## 8. Reduce Motion 方針

- デフォルトは `SystemAware`
- `Full` 用プリセットには必ず `Reduced` 代替を定義する
- `Reduced` では以下を制限する
  - 大きい移動量
  - 3D 的な奥行き表現
  - 強いバウンス
- `Off` ではアニメーション時間を 0ms 扱いにする

## 9. パフォーマンス設計

- 実装方針
  - 不要な再コンポジションを避ける
  - 可能な限り `draw` フェーズ寄せ
  - 安定オブジェクト (`@Immutable`) を利用

- 開発時チェック
  - FPS
  - frame time p95
  - jank count

- 目標値 (MVP)
  - ライブラリ導入前後で p95 frame time の悪化を 10% 以内に抑える
  - `Reduced/Off` 切替で即時反映されること

## 10. テスト戦略

## 10.1 Unit Test

- Token 解決
- Policy -> CanimationLevel 判定
- Preset 変換 (`Full -> Reduced`)
- `CanimationSpec` バリデーション（不正値で例外）
- `CanimationSpec.toReduced()` 変換規則
- `CanimationSpec.reversed()` 変換規則

## 10.2 Integration Test (Compose)

- `visible=false -> true` で alpha/offset が時刻どおり遷移する（Enter）
- `visible=true -> false` で alpha/offset が逆順に遷移する（Exit）
- Enter/Exit 両方向の Transition が連続動作する
- `CanimationLevel` 切替で spec が変わる
- `Off` で即時反映される
- `CanimationSpec` カスタム値でアニメーションが動作する

## 10.3 Snapshot / Golden Test

- 主要プリセットの中間フレーム比較
- マルチプラットフォーム差分の監視

## 11. MVP 実装範囲 (v0.1)

- `CanimationProvider`（`presetRegistry` パラメータ含む）
- `CanimationTokens.Default`
- `CanimationPreset` 5種 (`FadeUp`, `Fade`, `ScaleIn`, `SlideLeft`, `SlideRight`)
- `CanimationSpec`（カスタムアニメーション定義型）
- `CanimationSpec.toReduced()` / `CanimationSpec.reversed()`
- `Modifier.canimationEnter`, `Modifier.canimationExit`, `Modifier.canimationTransition`
- `Modifier.canimationEmphasize`
- `CanimationVisibility`（`enterPreset` + `exitPreset` パラメータ）
- `PresetRegistry`（組み込み 5 プリセット + ユーザー拡張）
- `SystemAware` ポリシー
- `CanimationDiagnosticsOverlay` (Desktop/Android優先)
- `CanimationTestClock` / `CanimationTestHost`

## 12. リリース段階

1. Phase 1
- Android + Desktop 対応
- API 安定化前 (`0.x`)

2. Phase 2
- iOS 対応
- A11y ポリシーの検証強化

3. Phase 3
- Web 対応
- パフォーマンス診断の統一

## 13. ディレクトリ案

```text
canimation/
  modules/
    canimation-core/
    canimation-presets/
    canimation-a11y/
    canimation-diagnostics/
    canimation-test/
    canimation-platform-android/
    canimation-platform-ios/
    canimation-platform-desktop/
    canimation-platform-web/
  samples/
    sample-chat/
    sample-commerce/
  docs/
    basic-design.md
```

## 14. リスクと対策

1. API が増えすぎて学習コストが上がる
- 対策: MVP はプリセット中心に限定する

2. 各プラットフォームで見え方がズレる
- 対策: Golden Test と許容誤差ルールを定義する

3. 診断機能が本番に混入する
- 対策: build type でデフォルト無効化し、明示有効化のみ許可する

4. A11y 対応漏れ
- 対策: `Full` プリセット登録時に `Reduced` 代替必須の lint/チェックを用意する

## 15. 完了条件 (Definition of Done)

- MVP API でサンプル 2 本が実装できる
- Enter / Exit 両方のアニメーションが全プリセットで動作する
- `CanimationSpec` によるカスタムアニメーションが動作する
- Reduce Motion の `Full/Reduced/Off` 切替が動作する
- 診断オーバーレイで jank を検知できる
- 時刻指定テストが CI で再現可能
- README で 10 分以内に導入できる手順を提供する
- エラーハンドリング方針（7.2章）の全ケースが実装・テスト済み

## 16. 次アクション

1. リポジトリ雛形作成 (マルチモジュール)
2. `canimation-core` の最小 API を先行実装
3. `samples/sample-chat` で API 妥当性検証
4. `canimation-test` を先に入れて回帰防止を確立

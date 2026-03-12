# Target-State Architecture and Package Topology Guideline

## Scope

- 理想 OSS library の repository structure
- module boundary と dependency direction
- source set と package topology
- file topology と pattern selection
- macro から micro までの CoC 既定形

## Goal

この文書は、`Canimation` を長寿命の Kotlin Multiplatform / Compose Multiplatform OSS library として再構成する場合の target-state SSoT である。

重要なのは「今ある構造の延長」ではなく、
「長期保守、拡張性、互換性、 contributor onboarding、release 安定性を同時に最大化する構造」を固定することにある。

## Core Principles

- repository は「build」「published library」「sample」「benchmark」「docs」「governance」で責務を分離する。
- module は capability boundary でだけ分割する。探索性のためだけに micro module を増やさない。
- `commonMain` first を徹底し、platform 差分は target source set に押し込める。
- public API は stable / experimental / compatibility / internal の tier を物理的に分離する。
- package と file 名だけで責務が推測できる状態を既定にする。
- pattern は目的のために使う。pattern 導入自体を目的にしない。

## Canonical Repository Layout

```text
repository
├── build-logic/
├── gradle/
│   ├── libs.versions.toml
│   └── verification-metadata.xml
├── modules/
│   ├── canimation-tokens/
│   ├── canimation-primitives/
│   ├── canimation-semantics/
│   ├── canimation-recipes/
│   ├── canimation-runtime/
│   ├── canimation-presets/
│   ├── canimation-a11y/
│   ├── canimation-diagnostics/
│   ├── canimation-test/
│   ├── canimation-compat/
│   ├── canimation-experimental/
│   ├── canimation-test-kit/
│   ├── canimation-platform-android/
│   ├── canimation-platform-desktop/
│   ├── canimation-platform-ios/
│   └── canimation-platform-web/
├── samples/
│   ├── showcase-app/
│   ├── docs-site/
│   └── consumer-smoke/
├── benchmarks/
├── docs/
├── guideline/
├── scripts/
└── .github/
```

この layout は end-state の repository baseline を表す。
current repository では consumer app / host として `composeApp/`、`androidApp/`、`iosApp/` が root に残っており、
`samples/` への集約は migration backlog として扱う。

## Root Directory Rules

- `build-logic/`
  - convention plugin と shared build logic だけを置く。
- `gradle/`
  - version catalog、dependency verification、wrapper 周辺の SSoT に限定する。
- `modules/`
  - publishable artifact または publish-ready boundary だけを置く。
- `samples/`
  - target-state では consumer app、showcase、docs host を集約する。library 本体を置かない。
  - current repository では `composeApp/`、`androidApp/`、`iosApp/` が migration 前の host boundary として root に残る。
- `benchmarks/`
  - performance / regression 計測専用。production code と混在させない。
- `docs/`
  - explanation、reference、ADR、release note、quality evidence を置く。
- `guideline/`
  - 強制規約の SSoT。

禁止事項:

- sample app 都合で `modules/` の責務を歪めること
- internal helper を root 直下へ散らすこと
- generated artifact と hand-written docs を同じ場所で管理すること

## Canonical Module Set

### Stable Core Line

- `canimation-tokens`
  - duration、easing、distance、spring、policy scalar の SSoT
- `canimation-primitives`
  - semantic を持たない pure motion primitive
- `canimation-semantics`
  - taxonomy、descriptor contract、registry contract、validation
- `canimation-recipes`
  - built-in descriptor pack と built-in registry
- `canimation-runtime`
  - Compose-facing provider、modifier、visibility、transition、resolver
- `canimation-presets`
  - built-in preset 定義と preset registry の SSoT。migration 中は core-backed preset path も担う。
- `canimation-a11y`
  - reduced-motion / motion-off policy、platform preference bridge contract

### Optional / Advanced Line

- `canimation-diagnostics`
  - metrics、overlay、benchmark support

### Test Support Line

- `canimation-test`
  - low-level deterministic test clock と test host helper
- `canimation-test-kit`
  - target-state test-facing wrapper surface。consumer/docs の first path はこちらを使う。

### Lifecycle-Isolated Line

- `canimation-compat`
  - 旧 API 互換層。新規 stable entry と混在させない。
- `canimation-experimental`
  - opt-in 必須の preview family と unstable SPI

### Platform Line

- `canimation-platform-*`
  - platform adapter 実装だけを持つ。

## Module Creation Rule

新 module を作るのは次のいずれかを満たす場合だけに限定する。

- publishability が変わる
- stability tier が変わる
- dependency direction が変わる
- platform boundary を隔離する必要がある
- separate release / support policy が必要である

次だけでは module を増やさない。

- package 名をきれいにしたい
- contributor の心理的整理
- 一時的な file size 問題

## Canonical Dependency Direction

```text
runtime
  -> a11y
  -> recipes
  -> semantics
  -> primitives
  -> tokens

recipes
  -> semantics
  -> primitives
  -> tokens

semantics
  -> primitives
  -> tokens

primitives
  -> tokens

diagnostics
  -> runtime
  -> semantics

compat
  -> runtime
  -> recipes
  -> semantics

experimental
  -> recipes
  -> semantics
  -> primitives
  -> tokens
```

この diagram は `canimation-core` を取り除いた end-state 依存だけを表す。
current migration では `canimation-runtime`、`canimation-presets`、`canimation-a11y`、
`canimation-diagnostics`、`canimation-test`、`canimation-test-kit`、`canimation-compat`、
`canimation-platform-*` がまだ `canimation-core` backed contract を経由する。

禁止依存:

- `semantics -> runtime`
- `recipes -> samples`
- `tokens -> upper layer`
- `platform adapter -> samples`

## Source Set Policy

- default hierarchy template を第一選択にする。
- まず `commonMain` に置けるかを検討する。
- 次に intermediate source set に置けるかを検討する。
- 最後に target-specific source set に置く。
- `expect/actual` は thin bridge、annotation、constant、platform fact に限定する。
- test source set も production source set を mirror する。

### Canonical Source Set Ownership

- `commonMain`
  - model、descriptor、resolver、policy、registry contract、shared UI logic
- `commonTest`
  - invariant test、contract test、reduced-motion test、descriptor integrity test
- `iosMain`
  - Apple target 共有の thin adapter
- `androidMain`
  - Android system integration
- `jvmMain`
  - desktop/JVM adapter
- `jsMain` / `wasmJsMain`
  - web environment bridge

## Package Topology

### Tokens

```text
io/github/canimation/tokens/
├── duration/
├── easing/
├── distance/
├── spring/
└── policy/
```

### Semantics

```text
io/github/canimation/semantics/
├── taxonomy/
├── descriptor/
├── registry/
└── validation/
```

### Recipes

```text
io/github/canimation/recipes/
├── content/
├── feedback/
├── navigation/
├── surface/
├── emphasis/
├── transition/
├── ambient/
├── recovery/
└── experimental/
```

### Runtime

```text
io/github/canimation/runtime/
├── provider/
├── modifier/
├── visibility/
├── transition/
├── resolver/
└── state/
```

### Platform

```text
io/github/canimation/platform/<target>/
├── motion/
├── metrics/
└── environment/
```

### Samples

```text
samples/showcase-app/.../
├── app/
├── navigation/
├── component/
├── screen/
├── theme/
└── platform/
```

current repository の showcase source layout は `composeApp/src/commonMain/kotlin/com/segnities007/canimation/` にあり、
`docs/reference/showcase/consumer-app-structure.md` を current SSoT とする。

## File Topology

### Public Contract File

- 1 file 1 main concept を徹底する。
- file 名は type または responsibility と一致させる。
- `Utils`, `Helper`, `Shared`, `Part2` を既定形にしない。

### Stateful Screen / Feature Shape

```text
FooState.kt
  - FooUiState
  - FooEvent
  - reduceFooState

FooStateHolder.kt
  - FooStateHolder
  - rememberFooStateHolder

FooScreen.kt
  - screen composition
```

### Recipe Shape

```text
XxxRecipe.kt
  - id
  - descriptor
  - full / reduced / off spec
  - private helper
```

### Adapter Shape

```text
FeaturePort.kt
FeatureAdapter.<target>.kt
FeatureAdapterTest.kt
```

### Test Mirror Shape

- production package と test package を mirror する。
- reducer / resolver / registry test は `commonTest` を既定にする。
- platform adapter behavior は target test へ置く。
- benchmark artifact は `docs/quality/` へ出力できる形にする。

## Public API Tiering

- stable
  - default docs path、SemVer 契約対象、ABI check 対象
- experimental
  - opt-in 必須、separate package/module、compatibility 非保証
- compatibility
  - deprecation path 専用、first-class entry にしない
- internal
  - consumer contract 外、public by default 禁止

artifact 名と package 名で tier を推測できる状態を既定にする。
current repository では tier の物理分離は partial であり、
`canimation-experimental` / `canimation-compat` / owner module split は存在する一方、
default consumer entry はまだ `canimation-core` を経由する。

## Pattern Selection Matrix

- Reducer
  - screen state や deterministic state transition があるときに必須
- State Holder
  - Compose screen-level mutable owner が必要なときに必須
- Strategy / Policy
  - reduced-motion、easing selection、platform fallback の切替に適用
- Adapter
  - platform API、external service、target 差分を隔離するときに必須
- Factory / Builder
  - spec/descriptor の組み立て引数が多いときに適用
- Registry
  - descriptor pack や extension pack の lookup を deterministic に扱うときに適用
- Facade
  - advanced subsystem を stable entrypoint で包むときに適用
- Repository
  - 複数 data source があり、将来差し替えが発生する箇所だけに適用

禁止事項:

- service locator
- hidden global mutable registry
- god manager / mega controller
- platform implementation を domain rule と同居させること

## Naming Rules

- type 名は名詞
- function 名は動詞
- `*Descriptor` は metadata と contract の SSoT
- `*Registry` は deterministic lookup/merge を担う
- `*StateHolder` は mutable owner
- `*Policy` は strategy
- `*Adapter` は boundary bridge
- `*Catalog` は sample/demo/reference 向け表示 SSoT

## Anti-Drift Rules

- new pattern を増やす前に既定形で解けないことを証明する。
- root 直下に例外 directory を増やさない。
- sample 側 convenience を stable API へ逆流させない。
- target-state を変える場合は `guideline/` を先に更新し、必要なら ADR を追加する。

## Sources

- Kotlin Docs, Introduction to library authors' guidelines
  - https://kotlinlang.org/docs/api-guidelines-introduction.html
- Kotlin Docs, Build for Kotlin Multiplatform
  - https://kotlinlang.org/docs/api-guidelines-build-for-multiplatform.html
- Kotlin Multiplatform Docs, Expected and actual declarations
  - https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-expect-actual.html
- Kotlin Multiplatform Docs, Hierarchical project structure
  - https://kotlinlang.org/docs/multiplatform/multiplatform-hierarchy.html
- Android Developers, Compose UI Architecture
  - https://developer.android.com/develop/ui/compose/architecture
- Android Developers, Where to hoist state
  - https://developer.android.com/develop/ui/compose/state-hoisting
- Gradle Docs, Best Practices for Structuring Builds
  - https://docs.gradle.org/current/userguide/best_practices_structuring_builds.html
- Gradle Docs, Best Practices for Dependencies
  - https://docs.gradle.org/current/userguide/best_practices_dependencies.html

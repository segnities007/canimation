# Kotlin Multiplatform Guideline

## Scope

- source set 設計
- `commonMain` / targetMain 分離
- `expect/actual`
- target-specific testing

## Core Rules

- 共有できるものは `commonMain` に寄せる。
- `expect/actual` は最終手段であり、まず interface + adapter + DI/factory を検討する。
- target 差分は capability 差だけを表し、意味論差にしない。
- intermediate source set を活用し、iOS などの重複 actual を避ける。

## `expect/actual`

- `expect` は package と signature を安定化させる必要がある箇所だけに使う。
- `expect class` より interface / function / factory を優先する。
- test double が必要な責務では `expect class` より interface を優先する。
- actual は common 契約より広い意味を持ってはいけない。

## Source Set Design

- `commonMain` は business rule、state、mapping、shared UI logic を置く。
- target source set には platform API access と tiny glue code だけを置く。
- source set が build に参加していない warning を放置しない。
- common code と target code の責務境界を README または build logic から辿れるようにする。

## Multiplatform API

- broadest relevant type を選ぶ。platform-specific type を public API に漏らさない。
- binary/source compatibility は target 全体で考える。
- target 特有の制約は docs に書く。暗黙差分にしない。

## Testing

- shared logic は `commonTest` に置く。
- platform-specific behavior は各 target test source set に置く。
- common contract と actual behavior の両方を検証する。

## Sources

- Kotlin Docs, Build for Kotlin Multiplatform
  - https://kotlinlang.org/docs/api-guidelines-build-for-multiplatform.html
- Kotlin Multiplatform Docs, Expected and actual declarations
  - https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-expect-actual.html
- Kotlin Docs, Test your multiplatform app
  - https://kotlinlang.org/docs/multiplatform/multiplatform-run-tests.html
- Kotlin Docs, Configure compilations
  - https://kotlinlang.org/docs/multiplatform/multiplatform-configure-compilations.html

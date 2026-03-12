# Platform Adapters Guideline

## Scope

- platform bridge
- adapter / port
- clipboard
- motion preference
- diagnostics collector

## Core Rules

- platform API は common/domain/UI に直接漏らさない。
- common code は port/interface/expect declaration を通して platform capability を参照する。
- adapter は変換と隔離に集中し、business rule を持たない。
- target 間で同じ port の意味を揃える。

## Design

- common 側には capability contract を置く。
- actual 実装は thin adapter を基本にする。
- platform fallback が必要な場合、fallback policy を明示する。
- deprecation 対応は adapter 内で完結させる。

## Testing

- adapter contract は common test で確認し、actual behavior は target test で確認する。
- clipboard、motion preference、frame metrics など user-visible capability は回帰 test を優先する。

## Sources

- Kotlin Multiplatform Docs, Expected and actual declarations
  - https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-expect-actual.html
- Kotlin Docs, Build for Kotlin Multiplatform
  - https://kotlinlang.org/docs/api-guidelines-build-for-multiplatform.html

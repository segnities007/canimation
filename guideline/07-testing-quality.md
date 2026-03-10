# Testing and Quality Guideline

## Scope

- unit test
- reducer/state test
- platform test
- UI test
- regression prevention

## Core Rules

- 変更が behavior を変えるなら test を同時更新する。
- bug fix には再発防止 test を付ける。
- common logic は `commonTest` を優先し、platform behavior は target test に分離する。
- flaky source を排除し、`sleep` に依存しない。

## Test Pyramid for This Repo

- `canimation-core`
  - model/spec/policy resolver の unit test を厚くする。
- `composeApp`
  - reducer/state holder test を基本にし、UI branching は必要箇所だけ UI test する。
- `canimation-platform-*`
  - actual implementation 差分を target test で持つ。
- `canimation-diagnostics`
  - metrics 計算と overlay state を分けて test する。

## Quality Gates

- `allTests` を必須 gate とする。
- warning を build 成功の言い訳に使わない。
- edge case と invalid input を test しない code は仕様未確定とみなす。

## Sources

- Kotlin Docs, Test your multiplatform app
  - https://kotlinlang.org/docs/multiplatform/multiplatform-run-tests.html
- Kotlin Docs, Consistency
  - https://kotlinlang.org/docs/api-guidelines-consistency.html
- Android Developers, Accessibility in Jetpack Compose
  - https://developer.android.com/develop/ui/compose/accessibility

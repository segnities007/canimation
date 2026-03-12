# Test and Workflow Micro Convention Guideline

## Goal

test と workflow の細部を固定して、書き方の揺れを減らす。

## Test Micro Convention

- 1 test は 1 behavior を検証する。
- test 名は `what_happens_when_condition` ではなく、読める自然文で behavior を書く。
- arrange/act/assert が長い場合だけ空行で区切る。コメントは既定で不要。
- reducer test は「base state」「event」「updated state」を明確に置く。
- assertion は最小必要数に絞るが、behavior を十分に拘束する。

## Test Data Convention

- repeated fixture は helper 化する。
- ただし fixture helper が behavior を隠すなら inline で書く。
- random を使わない。時刻・乱数は注入する。

## Workflow Step Convention

- `uses:` step でも、意味が不明なら `name:` を付ける。
- `run:` step は必ず動詞から始まる step name を持つ。
  - `Run lint`
  - `Run all tests`
  - `Upload test results`
- 無名 step を増やさない。

## Artifact Convention

- artifact 名は singular/plural を固定する。
  - `test-results`
  - `flake-report`
- retention は短期 CI 7 日、定期点検 90 日を既定とする。

## Sources

- GitHub Docs, Workflow syntax for GitHub Actions
  - https://docs.github.com/actions/learn-github-actions/workflow-syntax-for-github-actions
- Kotlin Docs, Consistency
  - https://kotlinlang.org/docs/api-guidelines-consistency.html

# Repository Protection Baseline

この文書は、公式 `canimation` repository に期待する branch protection / ruleset baseline の stable reference である。

## Purpose

- owner review を GitHub 上でも enforce できる前提を明文化する
- required status checks の公式名を 1 箇所に固定する
- branch protection と ruleset の監査観点を contributor から見える形にする

## Scope

- `main`
- `dev`
- release tags (`v*`)

この文書は repository-hosted GitHub settings の baseline を定義する。
fork や mirror に対して同一 enforcement を約束するものではない。

## Branch Ruleset Baseline

`main` と `dev` には、少なくとも次を要求する。

- pull request 経由での変更
- required status checks
- code owner review
- conversation resolution
- force push 禁止
- branch deletion 禁止
- linear history

maintainer capacity と hosting plan が許す場合は、次も推奨する。

- require approval of the most recent reviewable push
- dismiss stale approvals when the diff meaningfully changes
- signed commits
- merge queue

## Required Status Checks

required status checks 名は workflow/job の表示名を含めて次で固定する。

- `CI / lint`
- `CI / test`
- `CI / build`
- `CI / build-apple`
- `CI / wasm-build`
- `CI / check`
- `CI / audit`
- `Dependency Review / dependency-review`
- `CodeQL / analyze`

job 名の衝突は ambiguous status check を生むため禁止する。
workflow file 上では各 job に explicit `name:` を持たせ、repository 全体で一意にする。

## Tag Protection Baseline

`v*` tag は immutable release artifact の参照点として扱う。

- force update 禁止
- delete 禁止
- release workflow 以外の書き換えを許可しない

## Review Ownership Baseline

review ownership の path baseline は `.github/CODEOWNERS` を SSoT にする。

特に次は owner review 必須とする。

- `.github/workflows/`
- `build-logic/`
- `guideline/`
- `modules/canimation-runtime/`
- `modules/canimation-semantics/`
- `modules/canimation-recipes/`
- `modules/canimation-a11y/`
- `SECURITY.md`
- `SUPPORT.md`

## Operational Notes

- local script から GitHub-hosted ruleset/branch protection の live state は完全には検証できない
- repository 内では `docs/quality/workflow/workflow-validation.md` と `docs/quality/security/security-audit-summary.md` が file-based evidence を持つ
- GitHub settings の実際の enforcement 状態は maintainer が UI または API で確認する

## Related Documents

- `docs/reference/release/versioning-policy.md`
- `docs/quality/workflow/workflow-validation.md`
- `docs/quality/security/security-audit-summary.md`
- `guideline/09-github-actions-ci.md`
- `guideline/13-workflow-and-script-conventions.md`
- `guideline/19-oss-governance-and-maintainer-experience.md`

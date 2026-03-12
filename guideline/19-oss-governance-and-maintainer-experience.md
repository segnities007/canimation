# OSS Governance and Maintainer Experience Guideline

## Scope

- maintainer governance
- contributor experience
- repository policy documents
- issue / PR / review / triage operations
- support matrix、security、release ownership

## Goal

理想的な OSS は code だけで成立しない。
利用者、 contributor、 maintainer が同じ期待値で動けるように、governance と運用導線を先に固定する。

## Required Root Documents

- `README.md`
- `CONTRIBUTING.md`
- `SUPPORT.md`
- `SECURITY.md`
- `MAINTAINERS.md`
- `CODE_OF_CONDUCT.md`
- `CODEOWNERS`
- `CHANGELOG.md`
- `LICENSE`
- `.github/ISSUE_TEMPLATE/`
- `.github/PULL_REQUEST_TEMPLATE.md`

これらが欠けている状態を理想 OSS の完成形とはみなさない。

## README Contract

`README.md` は最低限次を含む。

- product message
- why / problem definition
- quick start
- supported platforms と support tier
- accessibility contract
- stability tier
- extension model の概要
- docs map
- contribution / support / security への導線

禁止事項:

- marketing catalog 化して contract 情報を隠すこと
- quick start が実際に動かないこと
- support policy が README から辿れないこと

## CONTRIBUTING Contract

`CONTRIBUTING.md` は最低限次を含む。

- setup 手順
- required checks
- branch / PR flow
- test expectation
- docs update expectation
- architecture-significant change の提案方法
- label と issue type の使い分け
- response expectation と follow-up rule

追加ルール:

- contribution acceptance criteria を文章で先に固定する。
- scope 外の提案をどう扱うかを明記する。
- maintainer が断れることを正当なルールとして明示する。

## Support and Security Contract

### `SUPPORT.md`

- supported versions
- supported Kotlin / Compose / Gradle line
- supported targets
- support status (`active`, `security-fixes-only`, `eol`)
- backport policy
- bug report と question の案内先

### `SECURITY.md`

- private disclosure path
- supported versions
- response target
- fix / advisory publication flow
- dependency vulnerability response rule

## CODEOWNERS Policy

次の path は owner review を必須にする。

- `modules/canimation-runtime/`
- `modules/canimation-semantics/`
- `modules/canimation-recipes/`
- `modules/canimation-a11y/`
- `guideline/`
- `.github/workflows/`
- `build-logic/`
- `SECURITY.md`
- `SUPPORT.md`

owner review が必要な変更は self-merge を既定にしない。

## Maintainer Roles

最低限次の role を定義する。

- architecture maintainer
- release maintainer
- accessibility/performance reviewer
- platform adapter reviewer
- security contact

同一人物が兼務してもよいが、role と approval responsibility は分離して記述する。

`MAINTAINERS.md` を role / review ownership / public follow-up expectation の root SSoT とする。

## Approval Rules

- stable public API change
  - architecture maintainer review required
- release workflow / publish policy change
  - release maintainer review required
- reduced-motion / accessibility contract change
  - accessibility/performance reviewer review required
- platform adapter contract change
  - relevant platform reviewer review required
- security-sensitive workflow or secret handling change
  - security contact review required

## Triage Model

issue と PR は次の label class を既定にする。

- `type:bug`
- `type:feature`
- `type:docs`
- `type:question`
- `type:security`
- `status:needs-info`
- `status:blocked`
- `status:accepted`
- `priority:high`
- `good first issue`

運用ルール:

- stale を放置せず、close する条件を明記する。
- support request と bug report を混在させない。
- issue template で再現情報を先に集める。
- architecture discussion は issue/discussion から始め、PR だけで完結させない。
- label taxonomy の SSoT を明示し、issue form / labeler / docs で別定義しない。

## Review and Merge Policy

- required status checks を branch protection で enforce する。
- PR template に test / docs / compatibility / a11y 影響を明示させる。
- bug fix は regression test を必須にする。
- behavior change は changelog / migration note を同時更新する。
- maintainer は merge より review quality を優先する。

## Automation Policy

- dependency review を PR gate に入れる。
- Dependabot を actions / Gradle / GitHub Actions に有効化する。
- workflow 変更には CODEOWNERS + branch protection を掛ける。
- third-party action は full SHA pin を既定にする。
- reusable workflow と composite action を使い、CI の重複を減らす。
- label / stale / issue form の automation は contributor の時間を節約する方向で使う。
- required status checks 名は安定化し、workflow 間で衝突させない。
- official repository の branch protection / ruleset baseline は stable doc から辿れるようにする。

## Public Communication Rule

- feature request、support、design discussion は公開チャネルを既定にする。
- private decision をした場合も、結果と rationale は公開記録へ残す。
- maintainer meeting note は短くてもよいので公開する。

## Scope Control Rule

- README と CONTRIBUTING に product scope と non-goals を明示する。
- scope 外提案は丁寧に断るが、未回答で放置しない。
- fork / plugin / extension で解決できる要求はその道を案内する。
- maintainer の可処分時間を前提に運用ルールを書く。

## Release Ownership Rule

- release manager 不在で stable release を出さない。
- release 前に compatibility、docs、support matrix、security note を確認する。
- publish host と publish credentials の ownership を固定する。
- artifact provenance と verification path を release note から辿れるようにする。

## Anti-Patterns

- 暗黙 maintainer
- root doc 不足のまま contributor を募ること
- issue で設計合意なく大規模 PR を受け入れること
- support policy 不明のまま長期 release line を増やすこと
- workflow や security 文書を owner review なしで更新すること

## Sources

- Open Source Guides, Best Practices for Maintainers
  - https://opensource.guide/best-practices/
- GitHub Docs, About code owners
  - https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-code-owners
- GitHub Docs, Security hardening for GitHub Actions
  - https://docs.github.com/en/actions/security-for-github-actions/security-guides/security-hardening-for-github-actions
- GitHub Docs, Use GITHUB_TOKEN for authentication in workflows
  - https://docs.github.com/en/actions/security-for-github-actions/security-guides/automatic-token-authentication
- GitHub Docs, Reuse workflows
  - https://docs.github.com/en/actions/sharing-automations/reusing-workflows

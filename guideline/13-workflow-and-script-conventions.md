# Workflow and Script Convention Guideline

## Why This Exists

workflow と shell script は放置すると最も散らかりやすい。ここは実装以上に CoC を強く掛ける。

## Workflow Convention

- workflow file 名は目的で固定する。
  - `ci.yml`
  - `release.yml`
  - `pages.yml`
  - `schedule.yml`
- job 名は責務で固定する。
  - `lint`
  - `test`
  - `build`
  - `publish`
  - `deploy`
  - `audit`
  - `check`
- workflow job には explicit `name:` を付け、required status check 名が repository 全体で一意になるようにする。
- branch protection / ruleset で使う check 名は `docs/reference/release/repository-protection-baseline.md` を SSoT にする。
- setup は `.github/actions/setup-build` に寄せる。
- `actions/checkout@v4` は `persist-credentials: false` を既定にする。
- artifact upload は `actions/upload-artifact@v4` 系を使う。

## Script Convention

- shell script は `#!/usr/bin/env bash` と `set -euo pipefail` を必須にする。
- root 起点で動作する script は `ROOT_DIR` を計算して `cd` する。
- `rg` があれば `rg` を使い、なければ fallback を持つ。
- 成果物 path は固定し、workflow から回収しやすくする。
- script 名は動詞起点にする。
  - `validate-*`
  - `measure-*`
  - `audit-*`

## Output Convention

- 成否は最後に 1 行で明示する。
- machine-readable artifact が必要な script は `docs/quality/` など固定出力先へ JSON を出す。
- human output と artifact output を分離する。

## Sources

- GitHub Docs, Workflow syntax for GitHub Actions
  - https://docs.github.com/actions/learn-github-actions/workflow-syntax-for-github-actions
- GitHub Docs, Dependency caching reference
  - https://docs.github.com/actions/writing-workflows/choosing-what-your-workflow-does/caching-dependencies-to-speed-up-workflows

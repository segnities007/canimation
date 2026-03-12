# GitHub Actions and CI Guideline

## Scope

- GitHub Actions workflow
- cache
- reusable workflow
- concurrency
- artifact / attestation
- token permission

## Core Rules

- `GITHUB_TOKEN` permissions は最小権限にする。
- workflow と job の両方で `permissions` を明示する。
- branch ごとの二重実行を避けるため `concurrency` を設定する。
- reuse できる workflow は reusable workflow に切り出す。
- dependency cache と artifact を混同しない。

## Security

- third-party action は commit SHA pin を基本にする。
- cloud 認証は長期 secret より OIDC を優先する。
- build artifact は provenance を取れるものは attestation を付ける。
- artifact upload/download は v4 系を使う。
- static analysis workflow は default branch だけでなく active integration branch も対象にする。

## Performance and Reuse

- reusable workflow と shared composite action を活用して重複を減らす。
- cache key は lockfile と OS を含める。
- cache retention と churn を監視し、thrashing を避ける。
- artifact は成果物共有、cache は依存再利用に限定する。
- library-first repository の CI では sample host だけでなく publishable module compile task を明示的に走らせる。
  - Android/JVM: `compileLibraryAndroid`, `compileLibraryJvm`
  - Apple: `compileLibraryApple`
  - Web: `compileLibraryWeb`

## Operations

- `main` や release workflow は `cancel-in-progress` を適切に設定する。
- PR workflow は fork security を考慮し、write 権限を前提にしない。
- release artifact には provenance verification の導線を用意する。

## Sources

- GitHub Docs, Workflow syntax for GitHub Actions
  - https://docs.github.com/actions/learn-github-actions/workflow-syntax-for-github-actions
- GitHub Docs, Control the concurrency of workflows and jobs
  - https://docs.github.com/en/actions/using-jobs/using-concurrency
- GitHub Docs, Dependency caching reference
  - https://docs.github.com/actions/writing-workflows/choosing-what-your-workflow-does/caching-dependencies-to-speed-up-workflows
- GitHub Docs, Dependency caching
  - https://docs.github.com/en/actions/concepts/workflows-and-actions/dependency-caching
- GitHub Docs, Reusing automations
  - https://docs.github.com/en/actions/creating-actions
- GitHub Docs, Security for GitHub Actions
  - https://docs.github.com/en/actions/security-for-github-actions/security-guides
- GitHub Docs, Using artifact attestations to establish provenance for builds
  - https://docs.github.com/actions/security-for-github-actions/using-artifact-attestations/using-artifact-attestations-to-establish-provenance-for-builds
- GitHub Changelog, Deprecation notice: v3 of the artifact actions
  - https://github.blog/changelog/2024-04-16-deprecation-notice-v3-of-the-artifact-actions

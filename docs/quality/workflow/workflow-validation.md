# Workflow Validation Evidence

## Purpose

workflow と release automation の validation 結果を stable path に残す。

## Scope

- workflow lint/validation
- reusable workflow integrity
- release workflow readiness

## Source Inputs Or Commands

```bash
bash scripts/validate-workflows.sh
bash scripts/validate-governance-docs.sh
```

## Required Inputs

- validation script or command
- audited workflow scope
- failure summary if any

## Result Summary

- this document describes the maintained workflow audit surface and expected validation path; it is not a live pass/fail dashboard
- audited workflows include:
  - `ci.yml`
  - `release.yml`
  - `schedule.yml`
  - `pages.yml`
  - `dependency-review.yml`
  - `codeql.yml`
  - `labeler.yml`
  - `stale.yml`
  - `.github/actions/setup-build/action.yml`
- audited governance-doc surface includes:
  - `README.md`
  - `CONTRIBUTING.md`
  - `SUPPORT.md`
  - `SECURITY.md`
  - `.github/CODEOWNERS`
  - `.github/PULL_REQUEST_TEMPLATE.md`
  - `docs/explanation/architecture/implementation-overview.md`
- expected validation checks:
  - top-level permissions block
  - top-level concurrency block
  - expected job presence
  - job-level permissions block
  - explicit unique workflow job names
  - expected runner family
  - `actions/checkout` uses `persist-credentials: false`
  - third-party actions are pinned to full SHAs
  - governance docs retain aligned validation commands and review-ownership links
  - `SUPPORT.md` tooling baseline stays aligned with repository source-of-truth files
- CI build jobs additionally verify publishable library compile aggregators before sample-host compiles:
  - `compileLibraryAndroid`
  - `compileLibraryJvm`
  - `compileLibraryApple`
  - `compileLibraryWeb`
- CI test validation also generates Kover JVM coverage artifacts via:
  - `:koverHtmlReport`
  - `:koverXmlReport`
  - current scope: `:canimation-platform-desktop`
  - uploaded as the `coverage-report` artifact from `build/reports/kover/`
- browser-based Kotlin/JS and Wasm tests rely on module-local Karma overrides in `composeApp/karma.config.d/`
- machine-readable artifacts:
  - `docs/quality/workflow/workflow-validation-report.json`
  - `docs/quality/workflow/governance-docs-validation-report.json`
  - `docs/quality/workflow/flake-rate.json`

## Known Limitations

- validation is repository-shape based and does not prove GitHub-side branch protection or ruleset enforcement
- the pinned Kover `0.9.1` flow currently covers the JVM-only desktop adapter module; Android KMP modules using `com.android.kotlin.multiplatform.library` remain validated by `allTests` until upstream Kover support is available
- intended GitHub-side baseline is documented in `docs/reference/release/repository-protection-baseline.md`

## Last Updated Trigger

- update when workflow files, `.github/actions/`, or workflow validation rules change

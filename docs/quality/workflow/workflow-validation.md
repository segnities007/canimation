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
- expected validation checks:
  - top-level permissions block
  - top-level concurrency block
  - expected job presence
  - job-level permissions block
  - expected runner family
  - `actions/checkout` uses `persist-credentials: false`
  - third-party actions are pinned to full SHAs
- machine-readable artifacts:
  - `docs/quality/workflow/workflow-validation-report.json`
  - `docs/quality/workflow/flake-rate.json`

## Known Limitations

- validation is repository-shape based and does not prove GitHub-side branch protection or ruleset enforcement

## Last Updated Trigger

- update when workflow files, `.github/actions/`, or workflow validation rules change

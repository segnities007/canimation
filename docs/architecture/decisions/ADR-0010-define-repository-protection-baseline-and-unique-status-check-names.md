# ADR-0010: Define Repository Protection Baseline and Unique Status Check Names

## Status

Accepted

## Context

- `guideline/19-oss-governance-and-maintainer-experience.md` already requires branch protection, CODEOWNERS enforcement, and required status checks.
- GitHub documents that duplicate job names across workflows can create ambiguous required status checks.
- the repository had duplicate workflow job ids such as `build`, `check`, and `audit` across separate workflows, but no stable reference for the expected protected-branch baseline.
- local validation scripts can verify repository files, but they need a documented baseline so maintainers and auditors know what GitHub-side settings should exist.

## Decision

- add `docs/reference/release/repository-protection-baseline.md` as the stable SSoT for expected branch protection / ruleset posture.
- require explicit job `name:` values for workflow jobs that participate in status checks.
- require effective job names to be unique across repository workflows.
- extend workflow validation to fail when explicit job names are missing or duplicated.
- treat required status check names as a documented contract so branch protection and rulesets can point at stable check names.

## Consequences

- positive:
  - required status checks become auditable and less error-prone
  - contributor-facing docs can point to a stable repository protection contract
  - workflow changes that would silently break branch protection become easier to detect in review
- negative:
  - workflow edits now need to maintain explicit job names in addition to job ids
  - the documented protection baseline still requires manual GitHub-side enforcement because local scripts cannot fully verify hosted settings
- migration impact:
  - existing workflows gain explicit unique job names
  - validation scripts and docs must be updated to reflect the new status-check naming rule

## Alternatives Considered

- rely on implicit job ids and accept duplicated names across workflows
- keep repository protection expectations undocumented and maintain them only in GitHub UI settings
- use branch protection without a stable required-status-check naming contract

## Supersedes / Superseded by

- supersedes ad hoc workflow job naming with no repository-wide uniqueness guarantee
- superseded by: none

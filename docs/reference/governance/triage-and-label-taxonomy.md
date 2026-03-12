# Triage And Label Taxonomy

この文書は、`canimation` repository の issue / PR intake と label taxonomy の stable reference である。

## Purpose

- intake path と triage vocabulary を 1 箇所に固定する
- issue forms、labeler、contributor docs の drift を減らす
- maintainer と contributor が同じ label semantics を共有できるようにする

## Intake Paths

- bug report: `.github/ISSUE_TEMPLATE/bug-report.yml`
- feature request: `.github/ISSUE_TEMPLATE/feature-request.yml`
- architecture or governance proposal: `.github/ISSUE_TEMPLATE/architecture-proposal.yml`
- documentation request: `.github/ISSUE_TEMPLATE/docs-request.yml`
- usage or support question: `.github/ISSUE_TEMPLATE/question.yml`
- security-sensitive report: `SECURITY.md`

architecture-significant work は、原則として issue で scope と compatibility impact を先に共有してから PR に進む。

## Label Source Of Truth

label definitions の SSoT は `.github/labels.yml` とする。

issue forms と PR labeler は、この taxonomy に含まれる label のみを参照する。

## Label Classes

### Type labels

- `type:bug`
- `type:feature`
- `type:docs`
- `type:question`
- `type:security`

### Status labels

- `status:needs-info`
- `status:blocked`
- `status:accepted`

### Priority labels

- `priority:high`

### Contribution and automation labels

- `good first issue`
- `documentation`
- `ci`
- `architecture`

## Triage Baseline

- new issues should enter through the correct issue form
- maintainers may redirect issues that use the wrong intake path
- `status:needs-info` is used when a report lacks enough evidence to act on
- `status:accepted` is used after the issue is confirmed and accepted into scope
- `status:blocked` is used when progress is waiting on another decision, upstream fix, or external constraint
- `priority:high` is reserved for accessibility, compatibility, release, or security-critical work
- `good first issue` should only be applied when the scope is bounded and docs/tests expectations are clear

## Pull Request Labeling

path-based pull-request labels are defined in `.github/labeler.yml`.

- `documentation`
- `ci`
- `architecture`

These labels are supplemental routing signals and do not replace CODEOWNERS or required status checks.

## Related Documents

- `CONTRIBUTING.md`
- `SUPPORT.md`
- `MAINTAINERS.md`
- `.github/labels.yml`
- `.github/labeler.yml`
- `guideline/19-oss-governance-and-maintainer-experience.md`

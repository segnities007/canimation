# Maintainers

`canimation` is currently maintained by a small maintainer set.
This document is the root SSoT for maintainer roles, review ownership, and public follow-up expectations.

## Current Maintainer Model

The repository is currently operated by `@segnities007`.
Even while a single person fulfills the work today, responsibilities remain split by role so review expectations can scale without rewriting governance.

## Maintainer Roles

### Architecture maintainer

- approves stable public API boundary changes
- approves module-boundary and build-logic changes
- approves ADRs for architecture and migration policy

### Release maintainer

- approves release workflow and publish-policy changes
- validates release-readiness evidence before stable tags
- owns versioning, changelog, and release-note quality

### Accessibility and performance reviewer

- reviews reduced-motion and accessibility-contract changes
- reviews performance-sensitive animation or diagnostics changes
- requests updated quality evidence when behavior changes

### Platform adapter reviewer

- reviews adapter boundary changes for Android, iOS, Desktop, and Web surfaces
- checks platform APIs stay isolated behind documented contracts

### Security contact

- reviews security-sensitive workflow and secret-handling changes
- owns coordinated response for vulnerability disclosures

## Review Ownership

- path-level owner review is enforced through `.github/CODEOWNERS`
- repository protection expectations are documented in `docs/reference/release/repository-protection-baseline.md`
- workflows, build logic, governance docs, and architecture-significant modules should not be self-merged without the expected owner review path

## Public Communication Rules

- architecture proposals belong in public issues or pull requests unless a private security path is required
- support questions belong in the public issue tracker and should follow the documented issue forms
- private maintainer decisions should be reflected back into stable docs or ADRs

## Response Expectations

- maintainer response is best effort and capacity-limited
- a single polite follow-up after 14 days without maintainer feedback is acceptable
- repeated pings without new information may be closed as noise

## Becoming A Maintainer

There is no automatic promotion path.
Contributors who repeatedly improve architecture, docs, quality evidence, accessibility behavior, and review quality may be invited into broader review responsibility over time.

## Related Documents

- `README.md`
- `CONTRIBUTING.md`
- `SUPPORT.md`
- `SECURITY.md`
- `.github/CODEOWNERS`
- `docs/reference/release/repository-protection-baseline.md`
- `guideline/19-oss-governance-and-maintainer-experience.md`

# Contributing to canimation

Thanks for helping improve `canimation`.

## Read This First

Before changing implementation, architecture, or contributor workflows, read:

1. `README.md`
2. `docs/README.md`
3. `guideline/README.md`

For architecture-significant work, also read:

4. `docs/reference/architecture/ideal-architecture-blueprint.md`
5. `docs/explanation/architecture/implementation-overview.md`
6. `docs/architecture/decisions/README.md`

Current repository reality matters:

- the target-state semantics-first module split is documented in `docs/reference/`
- the current implementation still centers many consumer-facing behaviors on `canimation-core`
- wrapper modules such as `canimation-runtime`, `canimation-semantics`, and `canimation-recipes` already exist and should stay aligned with the documented migration path

## Choose The Right Path

- security-sensitive report: use `SECURITY.md`, not a public issue
- bug report: use `.github/ISSUE_TEMPLATE/bug-report.yml`
- feature request: use `.github/ISSUE_TEMPLATE/feature-request.yml`
- architecture or governance proposal: use `.github/ISSUE_TEMPLATE/architecture-proposal.yml`
- documentation gap or drift: use `.github/ISSUE_TEMPLATE/docs-request.yml`
- usage or support question: use `.github/ISSUE_TEMPLATE/question.yml`

Before opening a large architecture, migration, or stable-public-API pull request, start with an issue so scope, acceptance criteria, and compatibility impact are visible before implementation starts.

## Scope and Decision Rules

- prefer changes that strengthen the documented semantics-first migration path
- avoid speculative rewrites that create a second architecture beside the documented one
- keep support, release, and workflow promises aligned with current maintainer capacity
- if a request is better solved by an extension, fork, or showcase-only experiment, maintainers may redirect it there

For architecture-significant changes, open an issue first, describe the target module boundary and compatibility story, and expect an ADR if the change affects stable architecture, workflow security posture, or public API tiering.

## Development Workflow

- keep each change scoped to one clear intent
- update tests when behavior changes
- update docs when public behavior, architecture, governance, or contributor workflow changes
- preserve SSoT, UDF, dependency direction, and accessibility-first motion behavior
- add or update an ADR when you change stable architecture, semantic taxonomy, registry policy, public API tiering, support expectations, or docs IA

## Local Setup

- use the Gradle wrapper shipped in the repository
- use the versions defined in `gradle/libs.versions.toml`
- use the module map in `settings.gradle.kts` as the current implementation source of truth
- prefer small, reviewable changes over broad speculative rewrites

## Validation

Run the repository checks that match your change before opening a pull request:

```bash
./gradlew allTests compileLibraryAndroid compileLibraryJvm --max-workers=2 --no-daemon
./gradlew compileLibraryApple compileLibraryWeb --max-workers=2 --no-daemon
./gradlew :composeApp:compileKotlinJvm :composeApp:compileKotlinWasmJs :androidApp:assembleDebug --max-workers=2 --no-daemon
./gradlew releaseReadiness --max-workers=2 --no-daemon
bash scripts/security-audit.sh
bash scripts/validate-workflows.sh
bash scripts/validate-governance-docs.sh
```

Kotlin Gradle Plugin `2.3.0` currently emits JS/Wasm npm aggregation warnings during configuration.
Treat those as known upstream tooling noise unless a normal validation command starts failing.

If your change intentionally modifies the public library API, update the committed ABI dumps:

```bash
./gradlew updateLibraryAbi --max-workers=2 --no-daemon
```

If you add or update external dependencies, refresh the committed Gradle verification metadata:

```bash
./gradlew --write-verification-metadata sha256,pgp help
```

If you touch workflow or script behavior, run the relevant script locally as part of your validation.

If you touch CI coverage reporting, also verify the Kover JVM coverage report flow:

```bash
./gradlew :koverHtmlReport :koverXmlReport --max-workers=2 --no-daemon
```

## Labels and Issue Types

The repository uses these label families as the default triage vocabulary:

- type: `type:bug`, `type:feature`, `type:docs`, `type:question`, `type:security`
- status: `status:needs-info`, `status:blocked`, `status:accepted`
- priority: `priority:high`
- onboarding: `good first issue`

Issue templates map the main intake paths. If a report does not follow the right path, maintainers may redirect or close it after linking the correct template or policy document.
Label definitions and triage semantics are documented in `docs/reference/governance/triage-and-label-taxonomy.md` and `.github/labels.yml`.

## Response Expectations

- maintainer replies are best effort, not SLA-backed
- for normal issues and pull requests, a polite follow-up after 14 days without maintainer feedback is acceptable
- repeated pings without new information may be closed as noise
- architecture proposals should stay in public issue or PR discussion unless a private security path is required

## Maintainer Review Ownership

The repository currently routes owner review through `.github/CODEOWNERS`.
Role ownership details live in `MAINTAINERS.md`.

- stable public API and architecture changes: architecture maintainer review
- release workflow or publish policy changes: release maintainer review
- reduced-motion or accessibility contract changes: accessibility/performance review
- platform adapter boundary changes: relevant platform review
- security-sensitive workflow or secret-handling changes: security contact review

At the moment these roles are fulfilled by the current repository owner, but the responsibility split is still documented so it can scale beyond a single maintainer.

## Pull Requests

Pull requests should:

- explain the problem and the chosen fix
- call out behavior changes, compatibility impact, and migration impact
- include updated tests and docs where applicable
- avoid unrelated cleanup
- update `CHANGELOG.md` when user-visible behavior, governance, or release expectations change

## Contribution Acceptance Criteria

Changes are easier to accept when they:

- follow the repository CoC and guideline structure
- keep the smallest viable scope for one clear intent
- preserve backward compatibility or document the migration path
- improve or at least preserve accessibility, testability, and documentation quality
- do not introduce duplicate SSoT or hidden mutable behavior

Maintainers may decline or defer changes that:

- introduce speculative architecture not supported by the current roadmap
- expand support or release promises without matching automation or maintainer capacity
- mix large refactors with behavior changes and no focused review story

## Accessibility

Reduced-motion behavior is a core product requirement, not an optional enhancement.
Changes that affect animation behavior should keep `Full`, `Reduced`, and `Off` semantics aligned.

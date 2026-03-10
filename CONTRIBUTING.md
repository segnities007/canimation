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
- documentation gap or drift: use `.github/ISSUE_TEMPLATE/docs-request.yml`
- usage or support question: use `.github/ISSUE_TEMPLATE/question.yml`

Before opening a large architecture, migration, or stable-public-API pull request, start with an issue so scope, acceptance criteria, and compatibility impact are visible before implementation starts.

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
./gradlew allTests :composeApp:compileKotlinJvm --max-workers=2 --no-daemon
./gradlew :composeApp:compileKotlinWasmJs --max-workers=2 --no-daemon
./gradlew :androidApp:assembleDebug --max-workers=2 --no-daemon
./gradlew releaseReadiness --max-workers=2 --no-daemon
bash scripts/security-audit.sh
bash scripts/validate-workflows.sh
```

Kotlin Gradle Plugin `2.3.0` currently emits JS/Wasm npm aggregation warnings during configuration.
Treat those as known upstream tooling noise unless a normal validation command starts failing.

If your change intentionally modifies the public library API, update the committed ABI dumps:

```bash
./gradlew updateLibraryAbi --max-workers=2 --no-daemon
```

If you add or update external dependencies, refresh the committed Gradle verification metadata:

```bash
./gradlew --write-verification-metadata sha256 allTests :composeApp:compileKotlinJvm --max-workers=2 --no-daemon
```

If you touch workflow or script behavior, run the relevant script locally as part of your validation.

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

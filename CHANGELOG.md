# Changelog

All notable changes to the **canimation** library will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Changed

- Documentation and governance navigation now use a consistent docs index across `docs/tutorials`, `docs/how-to`, `docs/reference`, `docs/explanation`, `docs/architecture/decisions`, and `docs/quality`.
- Contributor, support, security, and issue-intake documents now align more closely with the repository's current module map, release workflow, and best-effort maintainer support model.
- Root governance now defines explicit review ownership through `.github/CODEOWNERS`, clearer scope/non-goal rules, and maintainer follow-up expectations.
- README and contributor validation commands now include library-first compile aggregators so published modules are verified independently of sample hosts.
- Governance drift validation now runs from `scripts/validate-governance-docs.sh` and CI/release audit flows.
- Security audit coverage now checks release OIDC/provenance hardening and CodeQL coverage for the active `dev` branch.
- Workflow jobs now use explicit unique check names, and the repository documents a stable protection/ruleset baseline for required status checks.
- Root governance now includes `MAINTAINERS.md`, a dedicated architecture proposal issue form, and stronger issue-form intake requirements.
- Label definitions now have an explicit SSoT in `.github/labels.yml`, with triage/reference docs and governance validation aligned to it.

### Deprecated

- Effect-first and preset-first APIs remain part of the current implementation but are no longer the long-term first documentation path.

### Added

- **Canimation.* Effect Namespace** with 30 categories and 190+ named effects
  - Fade, Scale, Slide, Rotate, Bounce, Spring, Flip, Zoom
  - Attention (Pulse, HeartBeat, Tada, Wobble, Swing, RubberBand, etc.)
  - Entrance (Elevate, Drop, JackInTheBox, RollIn, LightSpeed, etc.)
  - Material (FadeThrough, SharedAxisX, SharedAxisY, Emphasized, ContainerTransform)
  - Morph, Blur, Swipe, Reveal, Micro, Page
  - Wave, Glitch, Elastic, Cinematic, Playful, Stagger
  - Diagonal, Shrink, Tilt, Float, Drop, Rise, Stretch
- **Semantics-first recipe surface** with `DefaultCanimationRecipeRegistry` and first-party built-in recipe namespaces
  - `Canimation.Content.EnterSubtle`, `EnterStandard`
  - `Canimation.Feedback.Press`, `SelectionPulse`
  - `Canimation.Navigation.Forward`, `Backward`
  - `Canimation.Surface.DialogReveal`, `SheetReveal`
  - `Canimation.Emphasis.CallToAction`
  - `Canimation.Transition.ContentSwap`
  - `Canimation.Ambient.Highlight`
  - `Canimation.Recovery.ErrorShake`
- **CanimationEffect DSL** with composable `+` operator for effect combination
- **Modifier.canimation(visible, effect)** — new primary animation API
- **Modifier.canimationTransition(visible, enter, exit)** — asymmetric transitions with effects
- **Recipe-backed runtime APIs** — `Modifier.canimation(recipe = ...)`, `CanimationVisibility(visible, recipe = ...)`, and `CanimationTransition(visible, recipe = ...)`
- **379 gallery examples** in the showcase app
- **Rich component demos** grouped across dedicated showcase registries
- **Atomic Design organization** — Atoms, Molecules, Organisms hierarchy
- **Interactive showcase website** with Docs, API Reference, and Gallery pages
- **Repository support docs** — `LICENSE`, `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md`, `SECURITY.md`
- **Stable documentation IA** under `docs/reference`, `docs/explanation`, `docs/architecture/decisions`, and `docs/quality`
- **Architecture ADR set** for semantic-first product direction, descriptor SSoT, immutable registry, tiered API migration, and structured docs/evidence IA
- **Governance support files** — `SUPPORT.md`, `CODEOWNERS`, issue templates, and pull request template

- CI, release, pages, and schedule workflows now use explicit job-level permissions and repository audit validation.
- Implementation-facing documentation now points to repository audit scripts alongside build commands.
- Library modules now expose publishable Maven metadata, Dokka output, ABI validation, and a dedicated `releaseReadiness` / `updateLibraryAbi` task flow for release validation.
- CI now includes release-readiness validation plus macOS-backed iOS/Desktop smoke coverage.
- Repository documentation now treats stable reference docs and ADRs as the canonical architecture knowledge path.

### Removed

- Superseded dated architecture and planning documents that duplicated the new stable SSoT documentation tree.

### Fixed

- `scripts/lib/common.sh` now resolves the repository root correctly for all shared shell scripts.
- `scripts/security-audit.sh` now reports missing files cleanly instead of emitting noisy search-tool IO errors.
- `scripts/audit-animation-implementation.sh` now tracks the current README preset summary format.
- `canimation-platform-android` now enables `androidHostTest`, allowing `commonTest` to participate without hierarchy warnings.
- Effect-based animation defaults now resolve duration/easing/reduced-motion behavior from `CanimationContext.tokens`.
- Platform motion preference adapters now avoid broad exception catches in their runtime preference reads.

## [0.1.0] - 2026-03-02

### Added

- **Core animation system**
  - `CanimationProvider` composable for policy-based animation control.
  - `Modifier.canimationEnter()` for enter/appear animations.
  - `Modifier.canimationExit()` for exit/disappear animations.
  - `Modifier.canimationTransition()` for layout-transition animations.

- **Animation presets**
  - `Fade` — opacity transition.
  - `SlideUp` — vertical translate + fade.
  - `ScaleIn` — scale from center + fade.
  - `EmphasizeBounce` — attention-grabbing bounce effect.
  - `SkeletonShimmer` — loading placeholder shimmer.

- **Animation levels**
  - `Full` — all animations play at designed duration and effect.
  - `Reduced` — alpha-only transitions with shortened duration.
  - `Off` — instant state changes, zero animation.

- **Policy system**
  - `CanimationPolicy` sealed interface with four built-in policies:
    `SystemAware`, `AlwaysFull`, `AlwaysReduced`, `AlwaysOff`.

- **Accessibility integration**
  - `CanimationPolicyResolver` that reads the platform reduce-motion setting
    and maps it to the appropriate animation level automatically.

- **Diagnostics**
  - `CanimationDiagnosticsOverlay` composable for real-time frame-timing
    visualization during development.
  - `CanimationDiagnosticsConfig` for customizing thresholds and display.

- **Test utilities**
  - `CanimationTestClock` for deterministic time control in tests.
  - `CanimationTestHost` for hosting animated composables in test harnesses.

- **Platform modules**
  - Android (`canimation-android`)
  - JVM / Desktop (`canimation-jvm`)
  - iOS (`canimation-ios`)
  - JS / WasmJs (`canimation-js`)

### Changed

- Nothing — initial release.

### Fixed

- Nothing — initial release.

### Breaking

- Nothing — initial release.

[Unreleased]: https://github.com/segnities007/canimation/compare/v0.1.0...HEAD
[0.1.0]: https://github.com/segnities007/canimation/releases/tag/v0.1.0

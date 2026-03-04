# Changelog

All notable changes to the **canimation** library will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- **Canimation.* Effect Namespace** with 23 categories and 160+ named effects
  - Fade, Scale, Slide, Rotate, Bounce, Spring, Flip, Zoom
  - Attention (Pulse, HeartBeat, Tada, Wobble, Swing, RubberBand, etc.)
  - Entrance (Elevate, Drop, JackInTheBox, RollIn, LightSpeed, etc.)
  - Material (FadeThrough, SharedAxisX, SharedAxisY, Emphasized, ContainerTransform)
  - Morph, Blur, Swipe, Reveal, Micro, Page
  - Wave, Glitch, Elastic, Cinematic, Playful, Stagger
- **CanimationEffect DSL** with composable `+` operator for effect combination
- **Modifier.canimation(visible, effect)** — new primary animation API
- **Modifier.canimationTransition(visible, enter, exit)** — asymmetric transitions with effects
- **290+ gallery examples** in the showcase app
- **123 rich component demos** with customizable parameters
- **Atomic Design organization** — Atoms, Molecules, Organisms hierarchy
- **Interactive showcase website** with Docs, API Reference, and Gallery pages

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

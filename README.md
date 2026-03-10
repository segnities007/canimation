<h1 align="center">canimation</h1>

<p align="center">
  <strong>Accessibility-first semantic motion system for Compose Multiplatform</strong>
</p>

<p align="center">
  <a href="https://kotlinlang.org"><img src="https://img.shields.io/badge/Kotlin-2.3.0-7F52FF?logo=kotlin&logoColor=white" alt="Kotlin" /></a>
  <a href="https://github.com/JetBrains/compose-multiplatform"><img src="https://img.shields.io/badge/Compose_Multiplatform-1.10.0-4285F4?logo=jetpackcompose&logoColor=white" alt="Compose Multiplatform" /></a>
  <a href="LICENSE"><img src="https://img.shields.io/badge/License-Apache_2.0-blue.svg" alt="License" /></a>
</p>

<p align="center">
  <a href="#setup">Setup</a> •
  <a href="#quick-start">Quick Start</a> •
  <a href="#architecture">Architecture</a> •
  <a href="#semantic-api">Semantic API</a> •
  <a href="#compatibility-api">Compatibility API</a> •
  <a href="#accessibility">Accessibility</a> •
  <a href="#modules">Modules</a> •
  <a href="#validation">Validation</a> •
  <a href="#documentation">Documentation</a>
</p>

---

## What is canimation?

**canimation** is an accessibility-first semantic motion system for Compose Multiplatform.
Use semantic recipes to choose motion by UI meaning, then apply them through a small stable runtime API.
The library adapts between **Full**, **Reduced**, and **Off** motion levels
through `CanimationProvider` and its accessibility policy contract.

```
Android • iOS • Desktop (JVM) • Web (JS / WasmJs)
```

## Product Direction

- semantic motion library first
- accessibility contract first
- effect catalog second
- compatibility migration instead of abrupt rewrite

The long-term stable path is `semantics-first`:

- choose by intent
- preserve meaning in reduced/off modes
- keep recipe metadata, docs metadata, and runtime spec in one SSoT

## Current Repository Status

This README describes the implementation that exists in the repository today.

- The current consumer-facing implementation is still centered on `canimation-core`.
- The repository itself is intentionally multi-module: presets, accessibility bridges,
  diagnostics, test helpers, and platform adapters are split so the implementation stays
  maintainable.
- The target-state owner modules now physically exist for tokens, primitives, semantics,
  recipes, runtime, compat, experimental, and test-kit surfaces.
- The target architecture is stronger than the current implementation and is documented in
  `docs/reference/architecture/ideal-architecture-blueprint.md` and `guideline/18-target-state-architecture-and-package-topology.md`.

## Setup

### Local repository development

```kotlin
// build.gradle.kts
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":canimation-core"))
        }
    }
}
```

### External dependency shape

```toml
# gradle/libs.versions.toml
[versions]
canimation = "<latest-version>"

[libraries]
canimation-core = { module = "io.github.canimation:canimation-core", version.ref = "canimation" }
```

### Gradle

```kotlin
// build.gradle.kts
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.canimation.core)
        }
    }
}
```

> **Note:** The current implementation surface is centered on `canimation-core`.
> Inside this repository, the showcase app also uses internal modules such as
> `canimation-presets`, `canimation-a11y`, and `canimation-diagnostics`.
> The target-state surfaces `canimation-tokens`, `canimation-primitives`, `canimation-semantics`,
> `canimation-recipes`, and `canimation-runtime` are now real modules and can be adopted incrementally.

## Quick Start

These examples show the semantic-first direction that is now available in the runtime.
The repository is still in migration, but the semantics-first recipe surface exists and is
meant to become the long-term first docs path.

### 1. Provide animation context

```kotlin
@Composable
fun App() {
    CanimationProvider(
        policy = CanimationPolicy.SystemAware,
        recipeRegistry = DefaultCanimationRecipeRegistry,
    ) {
        MyContent()
    }
}
```

### 2. Animate with semantic recipes (target stable path)

```kotlin
Box(
    modifier = Modifier.canimation(
        visible = true,
        recipe = Canimation.Content.EnterSubtle,
    )
) {
    Text("Hello, canimation!")
}
```

```kotlin
CanimationVisibility(
    visible = isExpanded,
    recipe = Canimation.Surface.DialogReveal,
) {
    DetailContent()
}
```

```kotlin
CanimationProvider(
    recipeRegistry = DefaultCanimationRecipeRegistry,
) {
    ScreenContent()
}
```

## Architecture

The ideal architecture is top-down and semantics-first.

- product identity: semantic motion system
- stable core: tokens -> primitives -> semantics -> recipes -> runtime -> a11y
- isolated tiers: diagnostics / compat / experimental / test-kit / platform adapters
- docs and rules are also structured as SSoT

Read:

- `docs/reference/architecture/ideal-architecture-blueprint.md`
- `guideline/18-target-state-architecture-and-package-topology.md`
- `guideline/21-semantic-taxonomy-and-stability-tiering.md`
- `guideline/22-recipe-descriptor-registry-and-extension-model.md`

## Semantic API

The stable semantic direction is:

- `Canimation.Content`
- `Canimation.Feedback`
- `Canimation.Navigation`
- `Canimation.Surface`
- `Canimation.Emphasis`
- `Canimation.Transition`
- `Canimation.Ambient`
- `Canimation.Recovery`

Current built-in semantic recipes include:

- `Canimation.Content.EnterSubtle`
- `Canimation.Content.EnterStandard`
- `Canimation.Feedback.Press`
- `Canimation.Feedback.SelectionPulse`
- `Canimation.Navigation.Forward`
- `Canimation.Navigation.Backward`
- `Canimation.Surface.DialogReveal`
- `Canimation.Surface.SheetReveal`
- `Canimation.Emphasis.CallToAction`
- `Canimation.Transition.ContentSwap`
- `Canimation.Ambient.Highlight`
- `Canimation.Recovery.ErrorShake`

Read:

- `docs/reference/semantics/taxonomy.md`
- `docs/reference/recipes/descriptor-schema.md`
- `docs/reference/api/migration-policy.md`

## Compatibility API

The repository still contains effect-first and preset-first APIs during migration.
They remain useful for the current implementation, but they are not the long-term first docs path.

### Current effect-based examples

```kotlin
// Use named effects from the Canimation.* namespace
Box(
    modifier = Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Fade.Up,
    )
) {
    Text("Hello, canimation!")
}
```

```kotlin
// Combine effects with the + operator
Card(
    modifier = Modifier.canimation(
        visible = isShown,
        effect = Canimation.Fade.In + Canimation.Scale.Pop,
    )
)
```

```kotlin
// Asymmetric transitions with separate enter/exit effects
Card(
    modifier = Modifier.canimationTransition(
        visible = isShown,
        enter = Canimation.Slide.Left,
        exit = Canimation.Fade.Out,
    )
)
```

### Other current APIs

```kotlin
// Preset-based enter animation
Box(
    modifier = Modifier.canimationEnter(
        visible = isVisible,
        preset = CanimationPreset.FadeUp,
    )
)
```

```kotlin
// Attention / emphasis effects
Box(
    modifier = Modifier.canimationEmphasize(
        active = isHighlighted,
        preset = CanimationPreset.Pulse,
    )
)
```

```kotlin
// AnimatedVisibility wrapper
CanimationVisibility(
    visible = isExpanded,
    enterPreset = CanimationPreset.ScaleIn,
) {
    DetailContent()
}
```

### 4. Custom effects

```kotlin
// Build from scratch with CanimationEffect
val custom = CanimationEffect(
    alpha = CanimationRange(0f, 1f),
    offsetY = CanimationDpRange(24.dp, 0.dp),
    scale = CanimationRange(0.9f, 1f),
    durationMs = 300,
)

Box(
    modifier = Modifier.canimation(visible = isVisible, effect = custom)
)
```

## Implementation Overview

### Runtime flow

1. `CanimationProvider` resolves a `CanimationContext` from tokens, policy,
   preset registry, and the platform's motion preference.
2. `CanimationPolicyResolver` maps the system preference to `CanimationLevel`
   (`SystemAware` falls back to `Reduced` when the preference cannot be determined).
3. `PresetRegistry` resolves enter/exit specs from the preset SSoT
   (`CanimationPresetSpec` -> full / reduced / off behavior).
4. `Modifier.canimation*` APIs convert effects or presets into `CanimationSpec`
   and apply them with draw-phase-friendly `graphicsLayer` transforms.
5. The showcase app keeps screen state in `*StateHolder` + reducer pairs so the
   same public runtime APIs are exercised from real UI flows.

For the current implementation walkthrough, see `docs/explanation/architecture/implementation-overview.md`.

For the target architecture, see `docs/reference/architecture/ideal-architecture-blueprint.md`.

## Effect Namespace

The `Canimation` object provides **30 categories** of named effects. Each effect is a `CanimationEffect`
that can be used directly or combined with `+`.

| Category | Examples |
|----------|----------|
| `Canimation.Fade` | `In`, `Out`, `Up`, `Down`, `Left`, `Right`, `Gentle`, `Quick` |
| `Canimation.Scale` | `In`, `Up`, `Pop`, `Expand`, `Shrink`, `FadeIn` |
| `Canimation.Slide` | `Up`, `Down`, `Left`, `Right`, `UpBig`, `DownBig` |
| `Canimation.Rotate` | `In`, `Clockwise`, `Spin`, `Half`, `Quarter` |
| `Canimation.Bounce` | `In`, `Down`, `Up`, `Left`, `Right` |
| `Canimation.Spring` | `In`, `Up`, `Down`, `Pop`, `Bounce` |
| `Canimation.Flip` | `In`, `Up`, `Down`, `X`, `Y` |
| `Canimation.Zoom` | `In`, `Out`, `InUp`, `InDown`, `Dramatic` |
| `Canimation.Attention` | `Pulse`, `HeartBeat`, `Tada`, `Wobble`, `Swing`, `RubberBand`, `Jello` |
| `Canimation.Entrance` | `Elevate`, `Drop`, `JackInTheBox`, `RollIn`, `Snap`, `Rise` |
| `Canimation.Material` | `FadeThrough`, `SharedAxisX`, `SharedAxisY`, `Emphasized` |
| `Canimation.Morph` | `ScaleUp`, `Expand`, `Collapse` |
| `Canimation.Blur` | `In`, `Out`, `Soft` |
| `Canimation.Swipe` | `Left`, `Right`, `Up`, `Down`, `FlingRight` |
| `Canimation.Reveal` | `Center`, `Top`, `Bottom`, `Dramatic` |
| `Canimation.Micro` | `NudgeUp`, `NudgeDown`, `Pulse`, `Wiggle`, `Press` |
| `Canimation.Page` | `PushRight`, `PushLeft`, `BottomSheet`, `CoverUp` |
| `Canimation.Wave` | `Gentle`, `Strong`, `Ripple`, `Float`, `Drift` |
| `Canimation.Glitch` | `In`, `Shake`, `Flicker`, `Distort` |
| `Canimation.Elastic` | `In`, `Stretch`, `Squash`, `Snap`, `Wobble` |
| `Canimation.Cinematic` | `Curtain`, `ZoomPan`, `Dolly`, `Reveal`, `Dramatic` |
| `Canimation.Playful` | `Wiggle`, `Hop`, `Spin`, `Pop`, `Twirl`, `Boing` |
| `Canimation.Diagonal` | `TopLeft`, `TopRight`, `BottomLeft`, `BottomRight`, `Subtle` |
| `Canimation.Shrink` | `Out`, `Subtle`, `Rotate`, `FadeDown` |
| `Canimation.Tilt` | `Left`, `Right`, `Up`, `Down`, `Swing` |
| `Canimation.Float` | `Up`, `Down`, `Gentle`, `ScaleUp` |
| `Canimation.Drop` | `In`, `Heavy`, `Light`, `Rotate` |
| `Canimation.Rise` | `In`, `Slow`, `Scale`, `Rotate` |
| `Canimation.Stretch` | `Horizontal`, `Vertical`, `Both`, `Snap` |
| `Canimation.Stagger` | `Quick=40ms`, `Normal=70ms`, `Slow=120ms`, `Relaxed=200ms` |

## Presets

83 built-in presets (via `CanimationPreset`) inspired by leading animation libraries and design systems.
Each preset defines **Full** and **Reduced** enter/exit specs in the preset registry SSoT.

> **Tip:** For new code, prefer the `Canimation.*` effect namespace with `Modifier.canimation()`.
> Presets remain fully supported and work with `Modifier.canimationEnter()` and `CanimationVisibility`.

<details>
<summary>All 83 Built-in Presets (CanimationPreset)</summary>

| Preset | Source | Description |
|--------|--------|-------------|
| `FadeUp` | Core | Fade + slide up |
| `Fade` | Core | Alpha crossfade |
| `ScaleIn` | Core | Scale from 92% |
| `SlideLeft` | Core | Slide from right |
| `SlideRight` | Core | Slide from left |
| `FadeDown` | Core | Fade + slide down |
| `ScaleUp` | Core | Scale from 108% |
| `ZoomIn` | Core | Zoom from 50% |
| `ZoomOut` | Core | Zoom from 150% |
| `Pop` | Core | Overshoot pop |
| `Expand` | Core | Scale from 0% |
| `SlideUp` | Core | Long slide up |
| `SlideDown` | Core | Long slide down |
| `ElevateIn` | Core | Subtle rise + scale |
| `DropIn` | Core | Drop with bounce |
| `RotateIn` | Motion.dev | Counter-clockwise rotate |
| `RotateClockwise` | Motion.dev | Clockwise rotate |
| `SpinIn` | Motion.dev | 360° spin + scale |
| `FlipIn` | Motion.dev | 180° flip entry |
| `SwingIn` | Motion.dev | Swing + slide |
| `ZoomInUp` | Motion.dev | Zoom + upward |
| `ZoomInDown` | Motion.dev | Zoom + downward |
| `ZoomInLeft` | Motion.dev | Zoom + from left |
| `ZoomInRight` | Motion.dev | Zoom + from right |
| `BackInUp` | Motion.dev | Back easing up |
| `BackInDown` | Motion.dev | Back easing down |
| `ShrinkIn` | Motion.dev | Shrink from 200% |
| `GentleFade` | Motion.dev | 600ms gentle fade |
| `Snap` | Core | Instant 10ms cut |
| `BounceIn` | Animate.css | Bouncy scale entry |
| `BounceInDown` | Animate.css | Bounce from top |
| `BounceInLeft` | Animate.css | Bounce from left |
| `BounceInRight` | Animate.css | Bounce from right |
| `FadeInLeftBig` | Animate.css | Big 200dp slide from left |
| `FadeInRightBig` | Animate.css | Big 200dp slide from right |
| `LightSpeedInRight` | Animate.css | Fast slide + rotation |
| `LightSpeedInLeft` | Animate.css | Fast slide left + rotation |
| `JackInTheBox` | Animate.css | Scale + rotation combo |
| `RollIn` | Animate.css | Roll + slide from left |
| `BackInLeft` | Animate.css | Back ease from left |
| `BackInRight` | Animate.css | Back ease from right |
| `BounceInUp` | Animate.css | Bounce from bottom |
| `FadeInDownBig` | Animate.css | Big fade from top |
| `FadeInUpBig` | Animate.css | Big fade from bottom |
| `FadeInLeft` | Animate.css | Fade from left |
| `FadeInRight` | Animate.css | Fade from right |
| `FadeInTopLeft` | Animate.css | Diagonal top-left |
| `FadeInTopRight` | Animate.css | Diagonal top-right |
| `FadeInBottomLeft` | Animate.css | Diagonal bottom-left |
| `FadeInBottomRight` | Animate.css | Diagonal bottom-right |
| `RotateInDownLeft` | Animate.css | Rotate down-left |
| `RotateInDownRight` | Animate.css | Rotate down-right |
| `RotateInUpLeft` | Animate.css | Rotate up-left |
| `RotateInUpRight` | Animate.css | Rotate up-right |
| `FlipInY` | Animate.css | Vertical flip |
| `Pulse` | Animate.css | Scale pulse |
| `HeartBeat` | Animate.css | Heartbeat pulse |
| `Tada` | Animate.css | Tada emphasis |
| `Wobble` | Animate.css | Side wobble |
| `Swing` | Animate.css | Swing rotation |
| `RubberBand` | Animate.css | Rubber band stretch |
| `Bounce` | Animate.css | Bounce emphasis |
| `Flash` | Animate.css | Quick flash |
| `ShakeX` | Animate.css | Horizontal shake |
| `ShakeY` | Animate.css | Vertical shake |
| `HeadShake` | Animate.css | Head shake |
| `Jello` | Animate.css | Jello wobble |
| `SpringIn` | Framer Motion | Spring overshoot scale |
| `SpringSlideUp` | Framer Motion | Spring slide from below |
| `SpringFadeIn` | Framer Motion | Spring fade + scale |
| `FlipUp` | AnimXYZ | Flip + upward slide |
| `FlipDown` | AnimXYZ | Flip + downward slide |
| `TiltIn` | AnimXYZ | Tilt + scale entry |
| `FadeSmall` | AnimXYZ | Fade + shrink |
| `FadeBig` | AnimXYZ | Fade + grow |
| `FadeUpLeft` | AnimXYZ | Fade up-left |
| `FadeDownRight` | AnimXYZ | Fade down-right |
| `RotateScale` | AnimXYZ | Rotate + scale |
| `UpBig` | AnimXYZ | Big upward slide |
| `FadeThrough` | Material Motion | Material fade-through |
| `SharedAxisX` | Material Motion | Shared axis horizontal |
| `SharedAxisY` | Material Motion | Shared axis vertical |
| `EmphasizedEntry` | Material Motion | Emphasized decelerate |

</details>

## Accessibility

canimation respects the OS reduce-motion setting automatically with `CanimationPolicy.SystemAware`.
Override with explicit policies when needed:

```kotlin
// Respect system settings (default)
CanimationProvider(policy = CanimationPolicy.SystemAware) { ... }

// Force specific levels
CanimationProvider(policy = CanimationPolicy.AlwaysFull) { ... }
CanimationProvider(policy = CanimationPolicy.AlwaysReduced) { ... }
CanimationProvider(policy = CanimationPolicy.AlwaysOff) { ... }
```

| Level | Behavior |
|-------|----------|
| **Full** | All animation properties play with full spec values |
| **Reduced** | ≤120ms duration, alpha preserved, and motion channels compressed instead of removed |
| **Off** | Instant snap — zero duration on all animations |

Implementation notes:

- `CanimationPolicyResolver` treats an unknown system preference as `Reduced`.
- `CanimationSpec.toReduced()` compresses duration to the short token (`120ms`) and
  scales offset / scale / rotation channels down instead of dropping semantics entirely.
- `PresetIntegrityAuditTest` verifies that reduced specs do not amplify motion.

## Modules

| Module | Description |
|--------|-------------|
| `canimation-core` | Public runtime primitives: provider, context, effect DSL, modifiers, spec resolution, tokens |
| `canimation-tokens` | Target-state token surface for duration, easing, distance, and spring tokens |
| `canimation-primitives` | Target-state primitive motion surface for effects and specs |
| `canimation-semantics` | Target-state semantic descriptor and registry surface |
| `canimation-recipes` | Target-state first-party semantic recipe catalog |
| `canimation-runtime` | Target-state provider / modifier / visibility / transition surface |
| `canimation-presets` | Built-in preset SSoT and registry installation |
| `canimation-a11y` | Motion preference contracts and policy bridge helpers |
| `canimation-diagnostics` | Debug overlay and frame-metrics support |
| `canimation-test` | Shared test clock and test host utilities |
| `canimation-test-kit` | Target-state test-facing package for deterministic runtime tests |
| `canimation-compat` | Compatibility layer for effect-first and preset-first migration |
| `canimation-experimental` | Opt-in experimental surface isolated from stable semantic APIs |
| `canimation-platform-android` | Android motion-preference and diagnostics adapters |
| `canimation-platform-desktop` | Desktop motion-preference and diagnostics adapters |
| `canimation-platform-ios` | iOS motion-preference and diagnostics adapters |
| `canimation-platform-web` | Web motion-preference adapters and web diagnostics fallback |
| `composeApp` | Interactive showcase / docs / gallery app for Desktop, Web, Android, and iOS hosts |
| `androidApp` | Thin Android application wrapper around `composeApp` |
| `iosApp` | Thin SwiftUI host around the Compose iOS entry point |

## Showcase App

The `composeApp` module is an interactive showcase with **379 gallery examples**
currently defined in `ExampleData*.kt`, a **36-entry API reference catalog**, and
component demos organized in an Atomic Design hierarchy (Atoms, Molecules, Organisms).

```bash
./gradlew :composeApp:run                              # Desktop
./gradlew :composeApp:wasmJsBrowserDevelopmentRun      # Web (WasmJs)
./gradlew :androidApp:assembleDebug                    # Android
```

## Validation

The repository's current baseline validation is centered on the same commands used in CI:

```bash
./gradlew allTests :composeApp:compileKotlinJvm --max-workers=2 --no-daemon
./gradlew :composeApp:compileKotlinWasmJs --max-workers=2 --no-daemon
./gradlew :androidApp:assembleDebug --max-workers=2 --no-daemon
./gradlew releaseReadiness --max-workers=2 --no-daemon
bash scripts/security-audit.sh
bash scripts/validate-workflows.sh
```

> **Note:** Kotlin Gradle Plugin 2.3.0 currently emits JS/Wasm npm aggregation warnings during
> configuration. These warnings come from upstream Kotlin/Gradle integration rather than this
> repository's custom build logic, so treat them as known tooling noise unless a normal validation
> command starts failing.

When an intentional public library API change is accepted, refresh the checked ABI dumps:

```bash
./gradlew updateLibraryAbi --max-workers=2 --no-daemon
```

See `.github/workflows/ci.yml` for the current validation shape.

## Documentation

| Document | Description |
|----------|-------------|
| [Documentation Index](docs/README.md) | Stable documentation entry |
| [Ideal Architecture Blueprint](docs/reference/architecture/ideal-architecture-blueprint.md) | Top-down target-state architecture |
| [Implementation Overview](docs/explanation/architecture/implementation-overview.md) | Current repository architecture and runtime flow |
| [Changelog](CHANGELOG.md) | Version history |
| [Semantic Taxonomy](docs/reference/semantics/taxonomy.md) | Stable motion classification |
| [Descriptor Schema](docs/reference/recipes/descriptor-schema.md) | Recipe SSoT schema |
| [API Migration Policy](docs/reference/api/migration-policy.md) | Stable path and compatibility migration |
| [Consumer App Structure](docs/reference/showcase/consumer-app-structure.md) | Showcase and consumer-app conventions |
| [A11y Tier 1 Validation](docs/quality/accessibility/tier-1-validation.md) | Policy level validation matrix |
| [A11y Tier 2 Compatibility](docs/quality/accessibility/tier-2-platform-compatibility.md) | Platform-specific a11y integration |
| [Guideline Index](guideline/README.md) | Repository-wide implementation and review rules |
| [Release Versioning Policy](docs/reference/release/versioning-policy.md) | SemVer & release tag conventions |
| [Contributing Guide](CONTRIBUTING.md) | Development flow, validation commands, and PR expectations |
| [Code of Conduct](CODE_OF_CONDUCT.md) | Community participation expectations |
| [Security Policy](SECURITY.md) | Vulnerability reporting and support policy |
| [Support Policy](SUPPORT.md) | Supported lines, expectations, and support boundaries |
| [Issue Templates](.github/ISSUE_TEMPLATE/) | Bug, feature, docs, and question intake |
| [License](LICENSE) | Apache License 2.0 text |

## Contributing

Contributions are welcome. Start with [CONTRIBUTING.md](CONTRIBUTING.md), then read
`docs/README.md`, `docs/reference/architecture/ideal-architecture-blueprint.md`, and `guideline/README.md` before making structural changes.
Use [SECURITY.md](SECURITY.md) for sensitive reports and follow [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) in all project spaces.

Operational support boundaries are documented in [SUPPORT.md](SUPPORT.md).

## Acknowledgements

### Animation Libraries (Implementation Reference)

- **[Motion.dev](https://motion.dev)** — Animation patterns, easing curves (backIn/backOut), and preset design inspiration.
- **[Framer Motion](https://www.framer.com/motion/)** — Spring animation patterns, overshoot easing, and enter/exit transition concepts.
- **[Animate.css](https://animate.style/)** — Bounce, lightSpeed, rollIn, jackInTheBox, and directional entry animation patterns.
- **[AnimXYZ](https://animxyz.com/)** — Composable animation utilities, flip/tilt patterns, and transform composition concepts.

### Design Principles

- **[Material Motion](https://m3.material.io/styles/motion/overview)** — Fade-through, shared axis, emphasized easing, and motion design principles.

### UI Inspiration

- **[UI Movement](https://uimovement.com/)** — UI animation inspiration and interaction patterns.
- **[Mobbin](https://mobbin.com/)** — Mobile UI design patterns and animation references.

### Assets & Learning

- **[LottieFiles](https://lottiefiles.com/)** — Animation asset reference and complex animation patterns.
- **[Dribbble](https://dribbble.com/)** / **[Behance](https://www.behance.net/)** — Professional animation and motion design inspiration.

### Foundation

- **[Jetpack Compose](https://developer.android.com/jetpack/compose)** — UI toolkit foundation.
- **[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform)** — Cross-platform runtime.

## License

```
Copyright 2026 canimation contributors

Licensed under the Apache License, Version 2.0
```

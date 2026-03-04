<p align="center">
  <img src="docs/logo.png" alt="canimation" width="120" />
</p>

<h1 align="center">canimation</h1>

<p align="center">
  <strong>Accessibility-first animation library for Compose Multiplatform</strong>
</p>

<p align="center">
  <a href="https://kotlinlang.org"><img src="https://img.shields.io/badge/Kotlin-2.3.0-7F52FF?logo=kotlin&logoColor=white" alt="Kotlin" /></a>
  <a href="https://github.com/JetBrains/compose-multiplatform"><img src="https://img.shields.io/badge/Compose_Multiplatform-1.10.0-4285F4?logo=jetpackcompose&logoColor=white" alt="Compose Multiplatform" /></a>
  <a href="LICENSE"><img src="https://img.shields.io/badge/License-Apache_2.0-blue.svg" alt="License" /></a>
</p>

<p align="center">
  <a href="#setup">Setup</a> •
  <a href="#quick-start">Quick Start</a> •
  <a href="#canimation-namespace">Effects</a> •
  <a href="#presets">Presets</a> •
  <a href="#accessibility">Accessibility</a> •
  <a href="#modules">Modules</a> •
  <a href="#documentation">Documentation</a>
</p>

---

## What is canimation?

**canimation** is an effect-driven animation library for Compose Multiplatform.
Use the `Canimation.*` namespace to pick from **190+ named effects** across 31 categories,
combine them with the `+` operator, and apply them with `Modifier.canimation(visible, effect)`.
The library automatically adapts between **Full**, **Reduced**, and **Off** motion levels
based on system accessibility settings via `CanimationProvider`.

```
Android • iOS • Desktop (JVM) • Web (JS / WasmJs)
```

## Setup

### Version Catalog

```toml
# gradle/libs.versions.toml
[versions]
canimation = "0.1.0"

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

> **Note:** All APIs — effects, presets, accessibility, modifiers — live in `canimation-core`.

## Quick Start

### 1. Provide animation context

```kotlin
@Composable
fun App() {
    CanimationProvider(policy = CanimationPolicy.SystemAware) {
        MyContent()
    }
}
```

### 2. Animate with effects (recommended)

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

### 3. Other APIs

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

## Canimation.* Namespace

The `Canimation` object provides **31 categories** of named effects. Each effect is a `CanimationEffect`
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

112 built-in presets (via `CanimationPreset`) inspired by leading animation libraries and design systems.
Each preset provides **Full** and **Reduced** motion variants derived automatically.

> **Tip:** For new code, prefer the `Canimation.*` effect namespace with `Modifier.canimation()`.
> Presets remain fully supported and work with `Modifier.canimationEnter()` and `CanimationVisibility`.

<details>
<summary>All 83 Original Presets (CanimationPreset)</summary>

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
| **Reduced** | Alpha-only emphasis, ≤120ms duration, minimal offset |
| **Off** | Instant snap — zero duration on all animations |

## Modules

| Module | Description |
|--------|-------------|
| `canimation-core` | All library APIs — Provider, Modifiers, Effects, Presets, Accessibility, Token system |
| `composeApp` | Interactive showcase / demo website (Desktop + Web targets) |

> **Note:** All functionality (effects, presets, accessibility, diagnostics) is shipped in
> `canimation-core`. The other module directories in the repository are scaffolding for
> planned future extraction and are not published separately.

## Showcase App

The `composeApp` module is an interactive showcase with **290+ gallery examples** and
**123 rich component demos**, organized in an Atomic Design hierarchy (Atoms, Molecules, Organisms).

```bash
./gradlew :composeApp:run                              # Desktop
./gradlew :composeApp:wasmJsBrowserDevelopmentRun      # Web (WasmJs)
./gradlew :composeApp:assembleDebug                    # Android
```

## Documentation

| Document | Description |
|----------|-------------|
| [Changelog](CHANGELOG.md) | Version history |
| [A11y Tier 1 Validation](docs/a11y-tier1-validation.md) | Policy level validation matrix |
| [A11y Tier 2 Compatibility](docs/a11y-tier2-compatibility.md) | Platform-specific a11y integration |
| [Release Versioning Policy](docs/release-versioning-policy.md) | SemVer & release tag conventions |
| [Release Note Draft](docs/release-note-draft.md) | v0.1.0 release notes |

## Contributing

Contributions are welcome! Please read the documentation in `docs/` before submitting a PR.

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

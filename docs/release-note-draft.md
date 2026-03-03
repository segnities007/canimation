# Release Notes — v0.1.0

**Date:** 2026-03-02

## Overview

We are excited to announce the first release of **canimation** — a
Compose Multiplatform animation library that provides policy-based animation
control with built-in accessibility support.

canimation lets you declare animations once and have them automatically
adapt to the user's motion preferences across Android, iOS, JVM Desktop,
and Web (JS/WasmJs).

## Key Features

### Policy-Based Animation Control

`CanimationProvider` manages animation behavior for your entire composable
tree. Choose from four built-in policies:

- **SystemAware** — respects the device reduce-motion setting (recommended).
- **AlwaysFull** — forces full animations regardless of system settings.
- **AlwaysReduced** — alpha-only, short-duration transitions.
- **AlwaysOff** — instant state changes, zero animation.

### Animation Modifiers

Apply animations declaratively with Compose modifiers:

- `Modifier.canimationEnter()` — enter/appear animations.
- `Modifier.canimationExit()` — exit/disappear animations.
- `Modifier.canimationTransition()` — layout-transition animations.

### Five Built-In Presets

| Preset           | Effect                              |
|------------------|-------------------------------------|
| `Fade`           | Simple opacity transition.          |
| `SlideUp`        | Vertical translate with fade.       |
| `ScaleIn`        | Scale from center with fade.        |
| `EmphasizeBounce`| Attention-grabbing bounce.          |
| `SkeletonShimmer`| Loading placeholder shimmer effect. |

### Accessibility Integration

`CanimationPolicyResolver` automatically detects the platform's
reduce-motion preference and adjusts animations accordingly — no extra
code required when using `SystemAware` policy.

### Diagnostics Overlay

`CanimationDiagnosticsOverlay` renders real-time frame-timing metrics
during development to help identify performance issues.

### Test Utilities

- `CanimationTestClock` — deterministic time control for animation tests.
- `CanimationTestHost` — test harness for rendering animated composables.

## Getting Started

Add the dependency to your `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("io.github.segnities007:canimation:0.1.0")
        }
    }
}
```

Wrap your app content with `CanimationProvider`:

```kotlin
@Composable
fun App() {
    CanimationProvider(policy = CanimationPolicy.SystemAware) {
        Column {
            Text(
                text = "Hello, canimation!",
                modifier = Modifier.canimationEnter(preset = Fade),
            )
        }
    }
}
```

## Supported Platforms

| Platform    | Artifact                 | Status |
|-------------|--------------------------|--------|
| Android     | `canimation-android`     | ✅      |
| JVM Desktop | `canimation-jvm`         | ✅      |
| iOS         | `canimation-ios`         | ✅      |
| JS / WasmJs | `canimation-js`          | ✅      |

## Known Limitations

- **JVM/Desktop:** Automatic reduce-motion detection is not available.
  Use the system property `-Dcanimation.reduceMotion=true` or provide an
  in-app toggle.
- **WasmJs:** Requires `kotlinx-browser` on the classpath for media query
  support.
- **Android 8.x (API 26–27):** `AccessibilityManager` callbacks may be
  delayed; a workaround using `ContentObserver` is in place.
- **iOS Simulator:** `UIAccessibility.isReduceMotionEnabled` defaults to
  `false` unless explicitly configured in simulator settings.

## Feedback

Please file issues and feature requests on the
[GitHub issue tracker](https://github.com/segnities007/canimation/issues).

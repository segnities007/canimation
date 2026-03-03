# Post-Release Smoke Test Procedure

## Purpose

This procedure verifies that a newly published canimation release can be
consumed by a fresh Kotlin Multiplatform project and works correctly on
every supported platform.

**Run this procedure after every stable release is published to the
repository.**

## Prerequisites

- Android Studio with KMP plugin installed.
- Xcode (for iOS target).
- JDK 21.
- A browser with DevTools (for JS/WasmJs target).

## Procedure

### Step 1 — Create a New KMP Project

1. Open Android Studio → **New Project** → **Kotlin Multiplatform App**.
2. Select targets: **Android**, **iOS**, **Desktop (JVM)**, **Web (WasmJs)**.
3. Use default project name (e.g., `canimation-smoke`).
4. Finish the wizard and wait for the initial Gradle sync to complete.

### Step 2 — Add the canimation Dependency

Edit `composeApp/build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("io.github.segnities007:canimation:0.1.0")
        }
    }
}
```

Run Gradle sync and confirm the dependency resolves without errors.

### Step 3 — Implement a Minimal Test Screen

Replace the default `App()` composable in `commonMain`:

```kotlin
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    CanimationProvider(policy = CanimationPolicy.SystemAware) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Fade preset",
                modifier = Modifier.canimationEnter(preset = Fade),
            )
            Text(
                text = "SlideUp preset",
                modifier = Modifier.canimationEnter(preset = SlideUp),
            )
            Text(
                text = "ScaleIn preset",
                modifier = Modifier.canimationEnter(preset = ScaleIn),
            )
        }
    }
}
```

### Step 4 — Verify on Each Platform

#### Android

1. Select an Android emulator or physical device.
2. Run the app (`./gradlew :composeApp:installDebug` or via Android Studio).
3. **Verify:**
   - [ ] App launches without crash.
   - [ ] Text elements animate in with their respective presets.
   - [ ] Enabling "Remove animations" in Developer Options causes instant
         appearance (level = `Off`).

#### JVM / Desktop

1. Run `./gradlew :composeApp:run`.
2. **Verify:**
   - [ ] Desktop window opens without crash.
   - [ ] Animations play at full fidelity.
   - [ ] Passing `-Dcanimation.reduceMotion=true` results in alpha-only
         transitions.

#### iOS

1. Open `iosApp/iosApp.xcodeproj` in Xcode.
2. Build and run on an iOS simulator.
3. **Verify:**
   - [ ] App launches without crash.
   - [ ] Animations play correctly.
   - [ ] Enabling Reduce Motion in simulator Settings → Accessibility →
         Motion results in reduced animations.

#### JS / WasmJs (Browser)

1. Run `./gradlew :composeApp:wasmJsBrowserDevelopmentRun`.
2. Open the browser tab.
3. **Verify:**
   - [ ] Page loads without console errors.
   - [ ] Animations play in the browser.
   - [ ] Enabling `prefers-reduced-motion: reduce` in browser DevTools →
         Rendering triggers reduced animations.

### Step 5 — Diagnostics Overlay Check

Add `CanimationDiagnosticsOverlay` around the `Column` and re-run on at
least one platform:

```kotlin
CanimationDiagnosticsOverlay {
    Column { /* ... */ }
}
```

- [ ] Overlay renders FPS counter and frame-time graph.
- [ ] No crashes or layout issues.

### Step 6 — Test Utilities Check

Create a simple test in `commonTest`:

```kotlin
@Test
fun enterAnimationCompletesAtEndOfClock() = runCanimationTest {
    val clock = CanimationTestClock()
    CanimationTestHost(clock = clock) {
        Box(modifier = Modifier.canimationEnter(preset = Fade))
    }
    clock.advanceToEnd()
    // Assert composable is fully visible
}
```

Run `./gradlew allTests` and confirm the test passes.

## Result Recording

| Platform    | Pass / Fail | Notes          |
|-------------|-------------|----------------|
| Android     |             |                |
| JVM Desktop |             |                |
| iOS         |             |                |
| JS / WasmJs |             |                |
| Diagnostics |             |                |
| Test Utils  |             |                |

**Smoke test performed by:** _______________
**Date:** _______________
**Release version:** _______________

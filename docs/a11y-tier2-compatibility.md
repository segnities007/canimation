# Accessibility — Tier 2 Compatibility

> **Note:** All accessibility functionality (policy resolution, platform adapters)
> is included in `canimation-core`. The `canimation-a11y` module directory
> exists as scaffolding for planned future extraction.

## Purpose

This document covers **Tier 2** accessibility: platform-specific integration
details, edge cases, and known limitations for each target.

## Platform Integration

### Android

**API surface:**

- `AccessibilityManager.isEnabled` — general a11y services active.
- `Settings.Global.ANIMATOR_DURATION_SCALE` — system animator scale
  (`0f` = animations disabled by developer options).

**Integration details:**

```kotlin
// Internally used by CanimationPolicyResolver on Android
val resolver = AndroidPolicyResolver(context)
val scale = Settings.Global.getFloat(
    context.contentResolver,
    Settings.Global.ANIMATOR_DURATION_SCALE,
    1f,
)
val reduceMotion = scale == 0f ||
    accessibilityManager.isEnabled &&
    accessibilityManager.isTouchExplorationEnabled
```

- When `ANIMATOR_DURATION_SCALE` is `0f`, the resolved level is `Off`.
- When TalkBack is active but the duration scale is non-zero, the resolved
  level is `Reduced`.
- A `ContentObserver` listens for runtime changes to `ANIMATOR_DURATION_SCALE`.

**Known limitations:**

- Some OEMs override `ANIMATOR_DURATION_SCALE` behavior. On affected devices
  the resolver may not detect the correct setting until an app restart.
- `AccessibilityManager` callbacks may be delayed on Android 8.x (API 26–27).

---

### iOS

**API surface:**

- `UIAccessibility.isReduceMotionEnabled` — user preference from
  Settings → Accessibility → Motion → Reduce Motion.
- `UIAccessibility.reduceMotionStatusDidChangeNotification` — runtime change
  notification.

**Integration details:**

```kotlin
// Internally used by CanimationPolicyResolver on iOS
actual fun resolveSystemReduceMotion(): Boolean {
    return UIAccessibility.isReduceMotionEnabled
}
```

- The resolver subscribes to `reduceMotionStatusDidChangeNotification` via
  `NSNotificationCenter` so policy updates propagate immediately.

**Known limitations:**

- On iOS simulators, `isReduceMotionEnabled` always returns `false` unless
  explicitly set in the simulator's Accessibility settings.
- iPadOS multi-window: each window shares the same global setting; per-window
  override is not possible.

---

### JVM / Desktop

**API surface:**

- System property: `canimation.reduceMotion` (`"true"` / `"false"`).
- GTK / macOS desktop a11y APIs are **not** read automatically.

**Integration details:**

```kotlin
// Internally used by CanimationPolicyResolver on JVM
actual fun resolveSystemReduceMotion(): Boolean {
    return System.getProperty("canimation.reduceMotion", "false").toBoolean()
}
```

- On macOS, if running via JVM, the native `NSWorkspace` reduce-motion
  setting is not bridged automatically. Users must pass
  `-Dcanimation.reduceMotion=true` or set the property programmatically.

**Known limitations:**

- No automatic detection of OS-level reduce-motion on Linux or Windows.
- Application developers should provide an in-app toggle and set the system
  property accordingly.

---

### JS / WasmJs (Browser)

**API surface:**

- `window.matchMedia("(prefers-reduced-motion: reduce)")` — CSS media query.

**Integration details:**

```kotlin
// Internally used by CanimationPolicyResolver on JS/WasmJs
actual fun resolveSystemReduceMotion(): Boolean {
    return window.matchMedia("(prefers-reduced-motion: reduce)").matches
}
```

- A `MediaQueryList` change listener is registered so runtime preference
  changes (e.g., toggling in OS settings) update the policy immediately.

**Known limitations:**

- Older browsers (pre-Chromium Edge, IE) do not support
  `prefers-reduced-motion`. The resolver falls back to `false`.
- WasmJs support depends on Kotlin/Wasm browser APIs; ensure
  `kotlinx-browser` is on the classpath.

---

## Cross-Platform Compatibility Matrix

| Feature                          | Android | iOS | JVM/Desktop | JS/WasmJs |
|----------------------------------|---------|-----|-------------|-----------|
| Auto-detect reduce-motion        | ✅       | ✅   | ❌ (manual)  | ✅         |
| Runtime setting change detection | ✅       | ✅   | ❌           | ✅         |
| `CanimationDiagnosticsOverlay`   | ✅       | ✅   | ✅           | ✅         |
| `CanimationTestClock`            | ✅       | ✅   | ✅           | ✅         |
| `CanimationTestHost`             | ✅       | ✅   | ✅           | ✅         |

## Recommendations

1. **Always provide an in-app animation preference** toggle, especially for
   JVM/Desktop where automatic detection is not available.
2. **Test with real devices** on Android and iOS to verify
   `CanimationPolicyResolver` behavior, as simulators and emulators may not
   reflect actual system accessibility state.
3. **Use `AlwaysOff` policy** in automated screenshot / snapshot tests to
   ensure deterministic output regardless of the host machine settings.

# Accessibility Tier 2 Platform Compatibility

## Purpose

common motion contract と各 platform adapter の接続状態をまとめる。

## Scope

- platform motion preference sources
- change propagation behavior
- diagnostics availability
- host app integration points

## Platform Summary

| Surface | Motion preference source | Change propagation today | Diagnostics today | Notes |
|---|---|---|---|---|
| Android | `Settings.Global.ANIMATOR_DURATION_SCALE` | `AndroidMotionPreferenceDataSource` exposes `refresh()`; automatic OS observation is not wired in the data source itself | `AndroidFrameMetricsCollector` collects frame timing | Strongest mobile implementation today |
| iOS | `UIAccessibilityIsReduceMotionEnabled()` | `IosMotionPreferenceDataSource` exposes `refresh()`; automatic OS observation is not wired in the data source itself | `IosFrameMetricsCollector` is a fallback no-op | SwiftUI host wraps the Compose controller |
| Desktop (JVM) | `Toolkit.getDesktopProperty("gnome.Gtk/EnableAnimations")` | Snapshot-based flow; no automatic desktop observer | `DesktopFrameMetricsCollector` collects frame timing | Practical fallback for desktop environments |
| Web (JS) | `matchMedia("(prefers-reduced-motion: reduce)")` | `WebMotionPreferenceDataSource` registers a media-query change listener | `WebFrameMetricsCollector` is a fallback no-op | Best live preference propagation among non-Android targets |
| WasmJs showcase shell | `rememberSystemReducedMotion()` returns `null` | relies on common fallback behavior | no dedicated metrics collector documented here | `SystemAware` therefore resolves to `Reduced` safely |

## ComposeApp Integration

The showcase app bridges each platform data source to the common runtime through:

- `composeApp/src/androidMain/.../SystemMotion.android.kt`
- `composeApp/src/iosMain/.../SystemMotion.ios.kt`
- `composeApp/src/jvmMain/.../SystemMotion.jvm.kt`
- `composeApp/src/jsMain/.../SystemMotion.js.kt`
- `composeApp/src/wasmJsMain/.../SystemMotion.wasmJs.kt`

Each target-specific file converts the platform data source into the Boolean `systemReducedMotion` input that `CanimationProvider` expects.

## Host Applications

Two thin host apps exist around the shared showcase:

- `androidApp` -> Android application wrapper around `composeApp`
- `iosApp` -> SwiftUI wrapper around the Compose iOS view controller

This keeps most product logic in shared code while leaving platform startup and hosting concerns at the edge.

## Diagnostics Posture

Current diagnostics support is asymmetric:

- Android: live frame metrics
- Desktop: live frame metrics
- iOS: fallback / no-op
- Web: fallback / no-op

## Current Test Signals

- `AndroidPlatformAdaptersTest`
- `DesktopPlatformAdaptersTest`
- `IosPlatformAdaptersTest`
- `WebPlatformAdaptersTest`

These tests verify fallback behavior and, where implemented, frame-metrics collection.

## Source Inputs

- current platform adapter implementation
- current adapter tests
- previous platform compatibility notes promoted into the stable quality tree

## Last Updated Trigger

- refresh when a platform motion-preference adapter, diagnostics collector, or host-app integration path changes

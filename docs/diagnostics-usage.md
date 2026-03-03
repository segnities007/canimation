# Diagnostics Usage Guide

## Overview

`CanimationDiagnosticsOverlay` is a development-time composable that renders
real-time frame-timing metrics on top of your UI. Use it to identify jank,
dropped frames, and animations that exceed their target duration.

## Enabling the Overlay

Wrap your content with `CanimationDiagnosticsOverlay` inside your
`CanimationProvider`:

```kotlin
CanimationProvider(policy = CanimationPolicy.SystemAware) {
    CanimationDiagnosticsOverlay {
        // Your app content
        MyScreen()
    }
}
```

> **Tip:** Guard the overlay behind a `BuildConfig` flag or a debug-only
> flavor so it never ships in production.

```kotlin
if (BuildConfig.DEBUG) {
    CanimationDiagnosticsOverlay { content() }
} else {
    content()
}
```

## CanimationDiagnosticsConfig

Customize the overlay behavior by passing a `CanimationDiagnosticsConfig`:

```kotlin
val config = CanimationDiagnosticsConfig(
    frameBudgetMs = 16,          // target frame time (default: 16 ms)
    warningThresholdMs = 20,     // yellow highlight threshold
    criticalThresholdMs = 32,    // red highlight threshold
    historySize = 120,           // number of frames in the graph
    showFpsCounter = true,       // display FPS number
    showFrameGraph = true,       // display frame-time bar graph
    overlayPosition = Alignment.TopEnd,
)

CanimationDiagnosticsOverlay(config = config) {
    MyScreen()
}
```

### Configuration Parameters

| Parameter              | Type        | Default      | Description                                    |
|------------------------|-------------|--------------|------------------------------------------------|
| `frameBudgetMs`        | `Int`       | `16`         | Target frame duration in milliseconds.         |
| `warningThresholdMs`   | `Int`       | `20`         | Frame time above this shows a warning (yellow).|
| `criticalThresholdMs`  | `Int`       | `32`         | Frame time above this shows critical (red).    |
| `historySize`          | `Int`       | `120`        | Number of recent frames displayed in the graph.|
| `showFpsCounter`       | `Boolean`   | `true`       | Toggle the numeric FPS counter.                |
| `showFrameGraph`       | `Boolean`   | `true`       | Toggle the frame-time bar graph.               |
| `overlayPosition`      | `Alignment` | `TopEnd`     | Screen corner for the overlay.                 |

## Reading Frame Time Metrics

The overlay displays:

- **FPS counter** — current frames-per-second averaged over the last
  `historySize` frames.
- **Frame-time graph** — a bar chart where each bar represents one frame:
  - **Green** — within `frameBudgetMs`.
  - **Yellow** — between `warningThresholdMs` and `criticalThresholdMs`.
  - **Red** — above `criticalThresholdMs`.
- **Max / Avg labels** — worst-case and average frame times over the visible
  history window.

### Interpreting Results

| Symptom                    | Likely Cause                         | Suggested Action                           |
|----------------------------|--------------------------------------|--------------------------------------------|
| Frequent yellow bars       | Animation complexity slightly high   | Simplify preset or reduce composable count.|
| Red spikes on enter/exit   | Heavy recomposition during animation | Profile with Android Studio / Instruments. |
| Sustained low FPS          | Too many simultaneous animations     | Stagger animations or switch to `Reduced`. |

## Programmatic Access

You can also observe metrics without the overlay by collecting the diagnostics
flow:

```kotlin
val diagnostics = LocalCanimationDiagnostics.current
LaunchedEffect(Unit) {
    diagnostics.frameTimings.collect { timing ->
        println("Frame: ${timing.durationMs} ms")
    }
}
```

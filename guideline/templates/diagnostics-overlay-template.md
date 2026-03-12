# Diagnostics Overlay Template

```kotlin
@Composable
fun FeatureDiagnostics(
    enabled: Boolean,
    collector: DiagnosticsCollector? = null,
    content: @Composable () -> Unit,
) {
    val stateHolder = rememberDiagnosticsOverlayStateHolder(
        enabled = enabled,
        collector = collector,
        jankThresholdMs = 16,
    )
    val metrics = stateHolder.uiState.metrics

    Box {
        content()
        if (enabled) {
            Text("FPS: ${metrics.fps.toInt()}")
        }
    }
}
```

## Rule

- collection と rendering を state holder で分ける
- disabled 時に collector を回し続けない

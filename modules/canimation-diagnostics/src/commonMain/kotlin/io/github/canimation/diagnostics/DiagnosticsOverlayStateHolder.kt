package io.github.canimation.diagnostics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive

internal data class DiagnosticsOverlayUiState(
    val metrics: FrameMetrics = FrameMetrics(),
)

internal sealed interface DiagnosticsOverlayEvent {
    data class MetricsUpdated(val metrics: FrameMetrics) : DiagnosticsOverlayEvent
}

internal fun reduceDiagnosticsOverlayState(
    current: DiagnosticsOverlayUiState,
    event: DiagnosticsOverlayEvent,
): DiagnosticsOverlayUiState = when (event) {
    is DiagnosticsOverlayEvent.MetricsUpdated -> current.copy(metrics = event.metrics)
}

@Stable
internal class DiagnosticsOverlayStateHolder(
    initialState: DiagnosticsOverlayUiState = DiagnosticsOverlayUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: DiagnosticsOverlayEvent) {
        uiState = reduceDiagnosticsOverlayState(uiState, event)
    }
}

@Composable
internal fun rememberDiagnosticsOverlayStateHolder(
    enabled: Boolean,
    collector: DiagnosticsCollector?,
    jankThresholdMs: Int,
): DiagnosticsOverlayStateHolder {
    val stateHolder = remember { DiagnosticsOverlayStateHolder() }

    LaunchedEffect(enabled, collector, jankThresholdMs) {
        if (!enabled) {
            stateHolder.onEvent(DiagnosticsOverlayEvent.MetricsUpdated(FrameMetrics()))
            return@LaunchedEffect
        }

        if (collector != null) {
            collector.start()
            try {
                collector.observeMetrics().collect { metrics ->
                    stateHolder.onEvent(DiagnosticsOverlayEvent.MetricsUpdated(metrics))
                }
            } finally {
                collector.stop()
            }
        } else {
            val frameTimes = FrameTimeHistory()
            var lastFrameNanos = 0L
            while (isActive) {
                withFrameNanos { frameNanos ->
                    if (lastFrameNanos != 0L) {
                        val frameTimeMs = (frameNanos - lastFrameNanos) / 1_000_000f
                        frameTimes.add(frameTimeMs)
                        stateHolder.onEvent(
                            DiagnosticsOverlayEvent.MetricsUpdated(
                                CanimationDiagnosticsFacade.computeMetrics(
                                    frameTimesMs = frameTimes.asList(),
                                    jankThresholdMs = jankThresholdMs,
                                ),
                            ),
                        )
                    }
                    lastFrameNanos = frameNanos
                }
            }
        }
    }

    return stateHolder
}

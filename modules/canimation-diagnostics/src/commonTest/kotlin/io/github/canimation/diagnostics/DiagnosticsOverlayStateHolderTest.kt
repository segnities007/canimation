package io.github.canimation.diagnostics

import kotlin.test.Test
import kotlin.test.assertEquals

class DiagnosticsOverlayStateHolderTest {

    @Test
    fun reducerReplacesMetricsSnapshot() {
        val base = DiagnosticsOverlayUiState()
        val nextMetrics = FrameMetrics(
            fps = 57.4f,
            frameTimeMs = 19.8f,
            jankCount = 3,
            p95FrameTimeMs = 24.1f,
        )

        val updated = reduceDiagnosticsOverlayState(
            current = base,
            event = DiagnosticsOverlayEvent.MetricsUpdated(nextMetrics),
        )

        assertEquals(nextMetrics, updated.metrics)
    }
}

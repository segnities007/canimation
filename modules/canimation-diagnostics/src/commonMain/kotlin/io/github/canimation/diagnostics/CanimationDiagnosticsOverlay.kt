package io.github.canimation.diagnostics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Debug overlay that displays real-time animation performance metrics.
 *
 * Only intended for development builds. Shows FPS, frame time, and jank count
 * as a floating overlay on top of the content.
 *
 * @param enabled Whether the overlay is visible.
 * @param showFps Whether to display FPS counter.
 * @param showFrameTime Whether to display frame time.
 * @param jankThresholdMs Frame time threshold for jank detection (default: 16ms).
 * @param collector Optional custom diagnostics collector.
 * @param content The composable content to overlay.
 */
@Composable
fun CanimationDiagnosticsOverlay(
    enabled: Boolean = true,
    showFps: Boolean = true,
    showFrameTime: Boolean = true,
    jankThresholdMs: Int = 16,
    collector: DiagnosticsCollector? = null,
    content: @Composable () -> Unit,
) {
    val stateHolder = rememberDiagnosticsOverlayStateHolder(
        enabled = enabled,
        collector = collector,
        jankThresholdMs = jankThresholdMs,
    )
    val metrics = stateHolder.uiState.metrics
    val availability = (collector as? DiagnosticsCollectorAvailabilityProvider)?.availability
        ?: DiagnosticsCollectorAvailability.Available

    Box {
        content()

        if (enabled) {
            DiagnosticsOverlayPanel(
                metrics = metrics,
                availability = availability,
                showFps = showFps,
                showFrameTime = showFrameTime,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
        }
    }
}

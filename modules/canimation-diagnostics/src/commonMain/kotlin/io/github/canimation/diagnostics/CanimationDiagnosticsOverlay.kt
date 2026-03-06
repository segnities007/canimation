package io.github.canimation.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicText
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive

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
    Box {
        content()

        if (enabled) {
            var metrics by remember { mutableStateOf(FrameMetrics()) }

            LaunchedEffect(collector, jankThresholdMs) {
                if (collector != null) {
                    collector.start()
                    try {
                        collector.observeMetrics().collect { metrics = it }
                    } finally {
                        collector.stop()
                    }
                } else {
                    // Fallback: sample Compose frame times directly.
                    val frameTimes = mutableListOf<Float>()
                    var lastFrameNanos = 0L
                    while (isActive) {
                        withFrameNanos { frameNanos ->
                            if (lastFrameNanos != 0L) {
                                val frameTimeMs = (frameNanos - lastFrameNanos) / 1_000_000f
                                frameTimes.add(frameTimeMs)
                                if (frameTimes.size > 120) frameTimes.removeAt(0)
                                metrics = CanimationDiagnosticsFacade.computeMetrics(
                                    frameTimes,
                                    jankThresholdMs,
                                )
                            }
                            lastFrameNanos = frameNanos
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.7f))
                    .padding(8.dp)
                    .wrapContentSize(),
            ) {
                val textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                )
                if (showFps) {
                    val fpsText = metrics.fps.toInt().toString()
                    BasicText(
                        text = "FPS: $fpsText",
                        style = textStyle,
                    )
                }
                if (showFrameTime) {
                    val color = when {
                        metrics.p95FrameTimeMs > 22f -> Color.Red
                        metrics.p95FrameTimeMs > 18f -> Color.Yellow
                        else -> Color.Green
                    }
                    val p95Text = ((metrics.p95FrameTimeMs * 10).toInt() / 10.0).toString()
                    BasicText(
                        text = "p95: ${p95Text}ms",
                        style = textStyle.copy(color = color),
                    )
                }
                BasicText(
                    text = "Jank: ${metrics.jankCount}",
                    style = textStyle.copy(
                        color = if (metrics.jankCount > 0) Color.Yellow else Color.Green,
                    ),
                )
            }
        }
    }
}

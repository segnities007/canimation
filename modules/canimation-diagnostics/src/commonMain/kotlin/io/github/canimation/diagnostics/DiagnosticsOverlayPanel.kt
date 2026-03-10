package io.github.canimation.diagnostics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun DiagnosticsOverlayPanel(
    metrics: FrameMetrics,
    availability: DiagnosticsCollectorAvailability,
    showFps: Boolean,
    showFrameTime: Boolean,
    modifier: Modifier = Modifier,
) {
    val textStyle = TextStyle(
        color = Color.White,
        fontSize = 10.sp,
        fontFamily = FontFamily.Monospace,
    )

    Column(
        modifier = modifier
            .background(Color.Black.copy(alpha = 0.7f))
            .padding(8.dp)
            .wrapContentSize(),
    ) {
        if (!availability.isAvailable) {
            BasicText(
                text = availability.reason ?: "Diagnostics unavailable",
                style = textStyle.copy(color = Color.Yellow),
            )
            return@Column
        }

        if (showFps) {
            BasicText(
                text = "FPS: ${metrics.fps.toInt()}",
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

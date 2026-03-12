package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

@Composable
fun HeartbeatLine(
    canvasSize: Dp = 240.dp,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing),
            RepeatMode.Restart,
        ),
    )

    val lineColor = MaterialTheme.colorScheme.error
    val bgColor = MaterialTheme.colorScheme.surface

    Canvas(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Scale.Pop)
            .size(canvasSize, 100.dp)
            .background(bgColor, RoundedCornerShape(8.dp)),
    ) {
        val w = size.width
        val h = size.height
        val midY = h / 2f
        val strokeW = 3.dp.toPx()
        val points = listOf(
            0.00f to 0f,
            0.35f to 0f,
            0.40f to -0.15f,
            0.43f to 0.5f,
            0.46f to -0.6f,
            0.50f to 0.3f,
            0.53f to -0.1f,
            0.58f to 0f,
            1.00f to 0f,
        )

        val drawEnd = w * progress
        for (index in 0 until points.size - 1) {
            val (x1Frac, y1Frac) = points[index]
            val (x2Frac, y2Frac) = points[index + 1]
            val x1 = x1Frac * w
            val x2 = x2Frac * w
            if (x1 > drawEnd) break

            val clampedX2 = if (x2 > drawEnd) drawEnd else x2
            val y1 = midY + y1Frac * h * 0.8f
            val interpFrac = if (x2 == x1) 0f else (clampedX2 - x1) / (x2 - x1)
            val clampedY2 = midY + (y1Frac + (y2Frac - y1Frac) * interpFrac) * h * 0.8f

            drawLine(
                color = lineColor,
                start = androidx.compose.ui.geometry.Offset(x1, y1),
                end = androidx.compose.ui.geometry.Offset(clampedX2, clampedY2),
                strokeWidth = strokeW,
                cap = StrokeCap.Round,
            )
        }
    }
}

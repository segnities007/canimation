package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.allowsLoopingMotion
import io.github.canimation.core.LocalCanimationContext
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RadarChart(modifier: Modifier = Modifier) {
    val loopingMotionEnabled = LocalCanimationContext.current.level.allowsLoopingMotion()
    val progress: Float

    if (loopingMotionEnabled) {
        val transition = rememberInfiniteTransition()
        val animatedProgress by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(3000), RepeatMode.Reverse))
        progress = animatedProgress
    } else {
        progress = 1f
    }
    val values = listOf(0.8f, 0.6f, 0.9f, 0.5f, 0.7f)
    val primaryColor = MaterialTheme.colorScheme.primary
    val outlineColor = MaterialTheme.colorScheme.outlineVariant

    Box(modifier.fillMaxWidth().height(90.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(80.dp)) {
            val cx = size.width / 2
            val cy = size.height / 2
            val radius = size.minDimension / 2.5f
            val pointCount = values.size

            for (ring in 1..3) {
                val ringRadius = radius * ring / 3
                val grid =
                    Path().apply {
                        for (index in 0 until pointCount) {
                            val angle = ((360f / pointCount * index - 90f) * PI / 180f).toFloat()
                            val x = cx + cos(angle) * ringRadius
                            val y = cy + sin(angle) * ringRadius
                            if (index == 0) moveTo(x, y) else lineTo(x, y)
                        }
                        close()
                    }
                drawPath(grid, outlineColor.copy(alpha = 0.3f), style = Stroke(1f))
            }

            val data =
                Path().apply {
                    for (index in 0 until pointCount) {
                        val value = values[index] * progress
                        val angle = ((360f / pointCount * index - 90f) * PI / 180f).toFloat()
                        val x = cx + cos(angle) * radius * value
                        val y = cy + sin(angle) * radius * value
                        if (index == 0) moveTo(x, y) else lineTo(x, y)
                    }
                    close()
                }
            drawPath(data, primaryColor.copy(alpha = 0.2f))
            drawPath(data, primaryColor, style = Stroke(2f))
        }
    }
}

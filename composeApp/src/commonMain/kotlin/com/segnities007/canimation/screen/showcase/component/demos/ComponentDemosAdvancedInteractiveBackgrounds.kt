package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HexGrid(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val pulse by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(2000), RepeatMode.Reverse))
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(80.dp, 60.dp)) {
            val hexRadius = 12f
            repeat(3) { row ->
                repeat(4) { col ->
                    val xOffset = if (row % 2 == 1) hexRadius * 1.1f else 0f
                    val cx = 10f + col * hexRadius * 2.2f + xOffset
                    val cy = 10f + row * hexRadius * 1.9f
                    val distance = (col + row).toFloat() / 7f
                    val alpha = (0.2f + pulse * 0.6f * (1f - distance)).coerceIn(0f, 1f)
                    val path = Path().apply {
                        repeat(6) { corner ->
                            val angle = ((60 * corner - 30) * PI / 180).toFloat()
                            val px = cx + cos(angle) * hexRadius * 0.8f
                            val py = cy + sin(angle) * hexRadius * 0.8f
                            if (corner == 0) moveTo(px, py) else lineTo(px, py)
                        }
                        close()
                    }
                    drawPath(path, primaryColor.copy(alpha = alpha))
                }
            }
        }
    }
}

@Composable
fun ParticleField(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val time by transition.animateFloat(0f, 100f, infiniteRepeatable(tween(10000, easing = LinearEasing), RepeatMode.Restart))
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(70.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxWidth(0.8f).height(50.dp)) {
            repeat(20) { index ->
                val speed = 0.3f + (index % 5) * 0.15f
                val x = (index * 37f + time * speed * 8f) % size.width
                val y = index * 19f % size.height
                val radius = 1.5f + (index % 3)
                drawCircle(primaryColor.copy(alpha = 0.15f + (index % 4) * 0.1f), radius = radius, center = Offset(x, y))
            }
        }
    }
}

@Composable
fun RotatingSquares(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val outerRotation by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(4000, easing = LinearEasing), RepeatMode.Restart))
    val innerRotation by transition.animateFloat(0f, -360f, infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart))
    val primaryColor = MaterialTheme.colorScheme.primary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(64.dp)) {
            rotate(outerRotation) {
                drawRoundRect(primaryColor.copy(alpha = 0.3f), topLeft = Offset(8f, 8f), size = Size(size.width - 16f, size.height - 16f), cornerRadius = CornerRadius(4f), style = Stroke(2f))
            }
            rotate(innerRotation) {
                drawRoundRect(tertiaryColor.copy(alpha = 0.4f), topLeft = Offset(14f, 14f), size = Size(size.width - 28f, size.height - 28f), cornerRadius = CornerRadius(4f), style = Stroke(2f))
            }
        }
    }
}

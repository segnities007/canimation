package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.demo_rotating_cube
import canimation.composeapp.generated.resources.demo_water_droplet
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import org.jetbrains.compose.resources.stringResource

@Composable
fun RotatingCube(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val angle by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing)),
    )
    val outline = MaterialTheme.colorScheme.primary

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.demo_rotating_cube), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Canvas(Modifier.size(80.dp)) {
            val cx = size.width / 2
            val cy = size.height / 2
            val half = 24f
            val rad = angle * PI.toFloat() / 180f
            val cosA = cos(rad)
            val sinA = sin(rad)
            val depth = 16f

            fun project(x: Float, y: Float, z: Float): Offset {
                val rx = x * cosA - z * sinA
                val rz = x * sinA + z * cosA
                val scale = 1f + rz / 200f
                return Offset(cx + rx * scale, cy + y * scale)
            }

            val front = listOf(
                project(-half, -half, -depth), project(half, -half, -depth),
                project(half, half, -depth), project(-half, half, -depth),
            )
            val back = listOf(
                project(-half, -half, depth), project(half, -half, depth),
                project(half, half, depth), project(-half, half, depth),
            )
            for (index in 0..3) {
                drawLine(outline, front[index], front[(index + 1) % 4], 2f)
                drawLine(outline, back[index], back[(index + 1) % 4], 2f)
                drawLine(outline.copy(alpha = 0.4f), front[index], back[index], 1.5f)
            }
        }
    }
}

@Composable
fun WaterDroplet(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val ripple1 by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(1500), RepeatMode.Restart))
    val ripple2 by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(1500, delayMillis = 500), RepeatMode.Restart))
    val primary = MaterialTheme.colorScheme.primary

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Blur.Soft),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.demo_water_droplet), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Canvas(Modifier.size(100.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            listOf(ripple1, ripple2).forEach { ripple ->
                drawCircle(
                    primary.copy(alpha = (1f - ripple) * 0.5f),
                    radius = ripple * size.minDimension / 2,
                    center = center,
                    style = Stroke(2f),
                )
            }
            drawCircle(primary, radius = 6f, center = center)
        }
    }
}

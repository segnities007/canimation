package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun FloatingParticles(
    particleCount: Int = 12,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    data class Particle(val x: Float, val size: Float, val delay: Int)

    val particles = remember(particleCount) {
        List(particleCount) { index ->
            Particle(
                x = (index * 23f) % 260f,
                size = 3f + (index * 1.3f) % 4f,
                delay = (index * 200) % 1200,
            )
        }
    }
    val transition = rememberInfiniteTransition()
    val time by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.tertiary

    Canvas(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Zoom.In)
            .width(260.dp)
            .height(160.dp),
    ) {
        particles.forEach { particle ->
            val progress = (time + particle.delay / 3000f) % 1f
            val y = size.height * (1f - progress)
            val alpha = when {
                progress < 0.2f -> progress * 5f
                progress > 0.8f -> (1f - progress) * 5f
                else -> 1f
            }
            val drift = sin(progress * PI.toFloat() * 2f) * 10f
            val color = if (particle.size > 5f) primary else secondary
            drawCircle(
                color = color.copy(alpha = alpha * 0.8f),
                radius = particle.size,
                center = Offset(particle.x + drift, y),
            )
        }
    }
}

package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun ConfettiExplosion(
    particleCount: Int = 12,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val trigger = rememberLoopingVisibility(
        visibleDurationMillis = 2000L,
        hiddenDurationMillis = 100L,
    )

    data class Particle(val angle: Float, val speed: Float, val color: Color)

    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary
    val error = MaterialTheme.colorScheme.error
    val particles = remember(primary, secondary, tertiary, error, particleCount) {
        val colors = listOf(primary, secondary, tertiary, error)
        List(particleCount) { index ->
            Particle((360f / particleCount) * index, 80f + (index % 3) * 30f, colors[index % colors.size])
        }
    }

    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).size(200.dp), contentAlignment = Alignment.Center) {
        particles.forEach { particle ->
            val radians = particle.angle * PI.toFloat() / 180f
            val targetX = cos(radians) * particle.speed
            val targetY = sin(radians) * particle.speed
            val animX by animateFloatAsState(if (trigger) targetX else 0f, tween(800, easing = FastOutSlowInEasing))
            val animY by animateFloatAsState(if (trigger) targetY else 0f, tween(800, easing = FastOutSlowInEasing))
            val animAlpha by animateFloatAsState(if (trigger) 0f else 1f, tween(800))
            val animRotation by animateFloatAsState(if (trigger) particle.angle * 2f else 0f, tween(800))
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .graphicsLayer {
                        translationX = animX
                        translationY = animY
                        rotationZ = animRotation
                        alpha = animAlpha
                    }
                    .background(particle.color),
            )
        }
    }
}

@Composable
fun WaveEffect(
    barCount: Int = 10,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = (2f * PI).toFloat(),
        animationSpec = infiniteRepeatable(tween(1500, easing = LinearEasing), RepeatMode.Restart),
    )
    val barColor = MaterialTheme.colorScheme.primary

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).size(200.dp, 100.dp).padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(barCount) { index ->
            val barPhase = phase + index * (2f * PI.toFloat() / barCount)
            val heightFraction = 0.3f + 0.7f * ((sin(barPhase) + 1f) / 2f)
            Box(
                modifier = Modifier
                    .width(10.dp)
                    .height((heightFraction * 80).dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(barColor.copy(alpha = 0.5f + heightFraction * 0.5f)),
            )
        }
    }
}

@Composable
fun LiquidFill(
    circleSize: Dp = 120.dp,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val fillFraction by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(3000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
    )

    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).size(circleSize), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(50)).background(MaterialTheme.colorScheme.surfaceVariant))
        Box(modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(50)), contentAlignment = Alignment.BottomCenter) {
            Box(modifier = Modifier.fillMaxWidth().height(circleSize * fillFraction).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)))
        }
        Text(text = "${(fillFraction * 100).roundToInt()}%", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}

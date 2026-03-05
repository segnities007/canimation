package com.segnities007.canimation.screen.examples

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
// ============================================================
// 2. GradientShift
// ============================================================


@Composable
fun ConfettiExplosion(particleCount: Int = 12,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var trigger by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            trigger = true
            delay(2000)
            trigger = false
            delay(100)
        }
    }

    data class Particle(val angle: Float, val speed: Float, val color: Color)

    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary
    val error = MaterialTheme.colorScheme.error

    val particles = remember(primary, secondary, tertiary, error) {
        val colors = listOf(primary, secondary, tertiary, error)
        List(particleCount) { i ->
            val angle = (360f / particleCount) * i
            Particle(angle, 80f + (i % 3) * 30f, colors[i % colors.size])
        }
    }

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).size(200.dp),
        contentAlignment = Alignment.Center,
    ) {
        particles.forEach { particle ->
            val radians = particle.angle * PI.toFloat() / 180f
            val targetX = cos(radians) * particle.speed
            val targetY = sin(radians) * particle.speed

            val animX by animateFloatAsState(
                targetValue = if (trigger) targetX else 0f,
                animationSpec = tween(800, easing = FastOutSlowInEasing),
            )
            val animY by animateFloatAsState(
                targetValue = if (trigger) targetY else 0f,
                animationSpec = tween(800, easing = FastOutSlowInEasing),
            )
            val animAlpha by animateFloatAsState(
                targetValue = if (trigger) 0f else 1f,
                animationSpec = tween(800),
            )
            val animRotation by animateFloatAsState(
                targetValue = if (trigger) particle.angle * 2f else 0f,
                animationSpec = tween(800),
            )

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

// ============================================================
// 12. WaveEffect
// ============================================================

@Composable
fun WaveEffect(barCount: Int = 10,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (2f * PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    val barColor = MaterialTheme.colorScheme.primary

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In)
            .size(200.dp, 100.dp)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 0 until barCount) {
            val barPhase = phase + i * (2f * PI.toFloat() / barCount)
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
// ============================================================
// 14. LiquidFill
// ============================================================

@Composable
fun LiquidFill(circleSize: Dp = 120.dp,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()
    val fillFraction by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    val containerColor = MaterialTheme.colorScheme.surfaceVariant
    val fillColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).size(circleSize),
        contentAlignment = Alignment.Center,
    ) {
        // Background circle
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(50))
                .background(containerColor),
        )
        // Fill from bottom
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(50)),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(circleSize * fillFraction)
                    .background(fillColor.copy(alpha = 0.8f)),
            )
        }
        // Percentage text
        val percent = (fillFraction * 100).roundToInt()
        Text(
            text = percent.toString() + "%",
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
    }
}

// ============================================================
// 15. SlidingReveal
// ============================================================

@Composable
fun SlidingReveal(text: String = "Hello, World!",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()
    val revealFraction by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In)
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(revealFraction)
                .graphicsLayer { clip = true },
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                ),
                maxLines = 1,
            )
        }
    }
}

// ============================================================
// 16. FocusBlurEffect
// ============================================================

@Composable
fun FocusBlurEffect(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val labels = listOf("Design", "Build", "Test", "Deploy")
    var focusIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1200)
            focusIndex = (focusIndex + 1) % labels.size
        }
    }

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        labels.forEachIndexed { index, label ->
            val targetAlpha = if (index == focusIndex) 1f else 0.2f
            val targetScale = if (index == focusIndex) 1.2f else 0.9f

            val animAlpha by animateFloatAsState(
                targetValue = targetAlpha,
                animationSpec = tween(500),
            )
            val animScale by animateFloatAsState(
                targetValue = targetScale,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
            )

            Text(
                text = label,
                modifier = Modifier.graphicsLayer {
                    alpha = animAlpha
                    scaleX = animScale
                    scaleY = animScale
                },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    }
}

// ============================================================
// 17. RollingDigits
// ============================================================

@Composable
fun RollingDigits(digitCount: Int = 4,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val digits = remember { List(digitCount) { Animatable(0f) } }
    var targetDigits by remember { mutableStateOf(List(digitCount) { 0 }) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1500)
            targetDigits = List(digitCount) { (0..9).random() }
        }
    }

    LaunchedEffect(targetDigits) {
        targetDigits.forEachIndexed { index, target ->
            launch {
                delay(index * 100L)
                digits[index].animateTo(
                    targetValue = target.toFloat(),
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow,
                    ),
                )
            }
        }
    }

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        digits.forEach { animatable ->
            val value = animatable.value
            val displayDigit = ((value.roundToInt()) % 10 + 10) % 10
            val offsetY = (value - value.roundToInt()) * 40f

            Surface(
                modifier = Modifier.size(40.dp, 56.dp),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = displayDigit.toString(),
                        modifier = Modifier.graphicsLayer { translationY = offsetY },
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                    )
                }
            }
        }
    }
}

// ============================================================
// 18. SpringChain
// ============================================================

@Composable
fun SpringChain(circleCount: Int = 5,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val offsets = remember { List(circleCount) { Animatable(0f) } }
    var toggle by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1500)
            toggle = !toggle
        }
    }

    LaunchedEffect(toggle) {
        offsets.forEachIndexed { index, anim ->
            launch {
                delay(index * 80L)
                anim.animateTo(
                    targetValue = if (toggle) 20f else -20f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow,
                    ),
                )
            }
        }
    }

    val lineColor = MaterialTheme.colorScheme.outline
    val circleColor = MaterialTheme.colorScheme.primary

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        offsets.forEachIndexed { index, anim ->
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer { translationY = anim.value }
                    .clip(RoundedCornerShape(50))
                    .background(circleColor),
            )
            if (index < circleCount - 1) {
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height(2.dp)
                        .background(lineColor),
                )
            }
        }
    }
}

// ============================================================
// 19. GlitchText
// ============================================================


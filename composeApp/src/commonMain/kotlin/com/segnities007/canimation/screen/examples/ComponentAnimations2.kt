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
import androidx.compose.ui.draw.alpha
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
fun GradientShift(boxSize: Dp = 200.dp,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary

    val colors = remember(primary, secondary, tertiary) {
        listOf(primary, secondary, tertiary, primary)
    }

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).size(boxSize),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = colors,
                        startX = offset * -500f,
                        endX = offset * -500f + 800f,
                    )
                ),
        )
    }
}

// ============================================================
// 3. SkeletonLoader
// ============================================================

@Composable
fun SkeletonLoader(
    lineCount: Int = 3,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()
    val shimmerOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    val baseColor = MaterialTheme.colorScheme.surfaceVariant
    val shimmerColor = MaterialTheme.colorScheme.surface

    val widths = listOf(0.9f, 0.7f, 0.5f)

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        widths.forEach { fraction ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(baseColor, shimmerColor, baseColor),
                            startX = shimmerOffset * 300f,
                            endX = shimmerOffset * 300f + 300f,
                        )
                    ),
            )
        }
    }
}

// ============================================================
// 4. ElasticPull
// ============================================================

@Composable
fun ElasticPull(circleSize: Dp = 80.dp,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var horizontal by remember { mutableStateOf(true) }
    val scaleX by animateFloatAsState(
        targetValue = if (horizontal) 1.4f else 0.7f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
    )
    val scaleY by animateFloatAsState(
        targetValue = if (horizontal) 0.7f else 1.4f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(800)
            horizontal = !horizontal
        }
    }

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(160.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(circleSize)
                .graphicsLayer {
                    this.scaleX = scaleX
                    this.scaleY = scaleY
                }
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.tertiary),
        )
    }
}

// ============================================================
// 5. ParallaxLayers
// ============================================================

@Composable
fun ParallaxLayers(
    layerCount: Int = 3,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    val colors = listOf(
        MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
    )
    val speeds = listOf(20f, 50f, 80f)

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .size(240.dp, 160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center,
    ) {
        colors.forEachIndexed { index, color ->
            Box(
                modifier = Modifier
                    .size(180.dp, 100.dp)
                    .graphicsLayer { translationX = phase * speeds[index] }
                    .clip(RoundedCornerShape(8.dp))
                    .background(color),
            )
        }
    }
}
// ============================================================
// 8. PathTracer
// ============================================================

@Composable
fun PathTracer(canvasSize: Dp = 160.dp,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()
    val sweep by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )
    val startAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    val arcColor = MaterialTheme.colorScheme.primary
    val trackColor = MaterialTheme.colorScheme.surfaceVariant

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).size(canvasSize)) {
        val strokeWidth = 8.dp.toPx()
        val padding = strokeWidth / 2f
        drawCircle(
            color = trackColor,
            radius = (size.minDimension - strokeWidth) / 2f,
            style = Stroke(width = strokeWidth),
        )
        drawArc(
            color = arcColor,
            startAngle = startAngle - 90f,
            sweepAngle = sweep,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            topLeft = androidx.compose.ui.geometry.Offset(padding, padding),
            size = androidx.compose.ui.geometry.Size(
                size.width - strokeWidth,
                size.height - strokeWidth,
            ),
        )
    }
}
// ============================================================
// 10. CardShuffle
// ============================================================

@Composable
fun CardShuffle(
    cardCount: Int = 4,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var step by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            step = (step + 1) % 3
        }
    }

    val cardColors = listOf(
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
    )

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).size(200.dp, 160.dp),
        contentAlignment = Alignment.Center,
    ) {
        (0 until 3).forEach { index ->
            val order = (index - step + 3) % 3
            val targetX = (order - 1) * 30f
            val targetRotation = (order - 1) * 8f

            val animX by animateFloatAsState(
                targetValue = targetX,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
            )
            val animRotation by animateFloatAsState(
                targetValue = targetRotation,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
            )

            Surface(
                modifier = Modifier
                    .size(100.dp, 130.dp)
                    .graphicsLayer {
                        translationX = animX
                        rotationZ = animRotation
                    },
                shape = RoundedCornerShape(12.dp),
                color = cardColors[index],
                shadowElevation = ((3 - order) * 2).dp,
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = (index + 1).toString(),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }
}

// ============================================================
// 11. ConfettiExplosion
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
    items: List<String> = listOf("Design", "Build", "Test", "Deploy"),
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

@Composable
fun GlitchText(text: String = "GLITCH",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var glitching by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            glitching = true
            delay(150)
            glitching = false
        }
    }

    val offsetX by animateFloatAsState(
        targetValue = if (glitching) 6f else 0f,
        animationSpec = tween(50),
    )
    val textAlpha by animateFloatAsState(
        targetValue = if (glitching) 0.7f else 1f,
        animationSpec = tween(50),
    )

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        // Shifted copy (red channel)
        if (glitching) {
            Text(
                text = text,
                modifier = Modifier
                    .graphicsLayer {
                        translationX = -4f
                        translationY = 2f
                        alpha = 0.4f
                    },
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error,
                ),
            )
        }
        // Main text
        Text(
            text = text,
            modifier = Modifier.graphicsLayer {
                translationX = offsetX
                alpha = textAlpha
            },
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            ),
        )
    }
}
// ============================================================
// 23. VerticalTicker
// ============================================================

@Composable
fun VerticalTicker(
    items: List<String> = listOf("Kotlin", "Compose", "Multiplatform", "Animation", "Design"),
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val words = listOf("Kotlin", "Compose", "Multiplatform", "Animation", "Design")
    var currentIndex by remember { mutableIntStateOf(0) }
    val offset = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            offset.animateTo(
                targetValue = -40f,
                animationSpec = tween(400, easing = FastOutSlowInEasing),
            )
            currentIndex = (currentIndex + 1) % words.size
            offset.snapTo(40f)
            offset.animateTo(
                targetValue = 0f,
                animationSpec = tween(400, easing = FastOutSlowInEasing),
            )
        }
    }

    val alphaValue = 1f - abs(offset.value) / 40f

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In)
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = words[currentIndex],
            modifier = Modifier.graphicsLayer {
                translationY = offset.value
                alpha = alphaValue
            },
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            ),
            textAlign = TextAlign.Center,
        )
    }
}

// ============================================================
// 24. HeartbeatLine
// ============================================================

@Composable
fun HeartbeatLine(canvasSize: Dp = 240.dp,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    val lineColor = MaterialTheme.colorScheme.error
    val bgColor = MaterialTheme.colorScheme.surface

    Canvas(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop)
            .size(canvasSize, 100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor),
    ) {
        val w = size.width
        val h = size.height
        val midY = h / 2f
        val strokeW = 3.dp.toPx()

        // Heartbeat pattern normalized positions and Y offsets
        // flat -> spike up -> spike down -> flat
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

        for (i in 0 until points.size - 1) {
            val (x1Frac, y1Frac) = points[i]
            val (x2Frac, y2Frac) = points[i + 1]
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

// ============================================================
// 25. ExpandingSearch
// ============================================================

@Composable
fun ExpandingSearch(
    placeholder: String = "Search...",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            expanded = !expanded
        }
    }

    val animWidth by animateFloatAsState(
        targetValue = if (expanded) 240f else 48f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
    )
    val textAlpha by animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = tween(if (expanded) 400 else 100),
    )

    val primary = MaterialTheme.colorScheme.primary
    val onPrimary = MaterialTheme.colorScheme.onPrimary

    Surface(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .size(width = animWidth.dp, height = 48.dp),
        shape = RoundedCornerShape(24.dp),
        color = primary,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Search icon drawn with Canvas
            Canvas(modifier = Modifier.size(24.dp)) {
                val iconColor = onPrimary
                val r = 7.dp.toPx()
                val cx = size.width / 2f - 2.dp.toPx()
                val cy = size.height / 2f - 2.dp.toPx()
                drawCircle(
                    color = iconColor,
                    radius = r,
                    center = androidx.compose.ui.geometry.Offset(cx, cy),
                    style = Stroke(width = 2.dp.toPx()),
                )
                val handleStart = androidx.compose.ui.geometry.Offset(
                    cx + r * cos(PI.toFloat() / 4f),
                    cy + r * sin(PI.toFloat() / 4f),
                )
                val handleEnd = androidx.compose.ui.geometry.Offset(
                    handleStart.x + 4.dp.toPx(),
                    handleStart.y + 4.dp.toPx(),
                )
                drawLine(
                    color = iconColor,
                    start = handleStart,
                    end = handleEnd,
                    strokeWidth = 2.dp.toPx(),
                    cap = StrokeCap.Round,
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Search...",
                    modifier = Modifier.graphicsLayer { alpha = textAlpha },
                    color = onPrimary,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

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

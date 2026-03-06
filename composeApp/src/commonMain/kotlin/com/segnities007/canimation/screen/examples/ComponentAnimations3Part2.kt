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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Lens
import androidx.compose.material3.Icon
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.*
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

// ===== CARD ANIMATIONS =====

/** Card with animated border that traces around the edges */

@Composable
fun CardFanStack(
    cardCount: Int = 4,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val spread by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(2000), RepeatMode.Reverse),
    )
    val colors = listOf(
        MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f),
        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
    )

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).size(160.dp, 100.dp),
        contentAlignment = Alignment.Center,
    ) {
        colors.forEachIndexed { i, color ->
            val angle = (i - 1) * 15f * spread
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = color,
                modifier = Modifier
                    .size(80.dp, 50.dp)
                    .graphicsLayer { rotationZ = angle },
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("${i + 1}", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}

/** Card with magnetic snap animation */
@Composable
fun CardMagneticSnap(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var target by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) { target = 1; delay(1500); target = 2; delay(1500); target = 0; delay(1500) }
    }

    val offsetX by animateFloatAsState(
        when (target) { 0 -> -40f; 1 -> 0f; else -> 40f },
        spring(dampingRatio = Spring.DampingRatioMediumBouncy),
    )

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(160.dp, 80.dp),
        contentAlignment = Alignment.Center,
    ) {
        // Snap targets
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                )
            }
        }
        // Snapping card
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
            modifier = Modifier
                .size(40.dp, 30.dp)
                .graphicsLayer { translationX = offsetX },
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Lens,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(10.dp),
                )
            }
        }
    }
}

// ===== MORE UNIQUE ANIMATIONS =====

/** Animated notification badge count */
/** Animated progress bar with glow */
@Composable
fun GlowProgressBar(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val progress by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(3000, easing = FastOutSlowInEasing), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).width(160.dp).height(8.dp).clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    Brush.horizontalGradient(
                        listOf(primary, primary.copy(alpha = 0.6f)),
                    )
                ),
        )
    }
}

/** Animated toggle switch with spring */
/** Pulse radar effect */
@Composable
fun PulseRadar(
    ringCount: Int = 3,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val pulse1 by inf.animateFloat(0f, 1f, infiniteRepeatable(tween(2000), RepeatMode.Restart))
    val pulse2 by inf.animateFloat(0f, 1f, infiniteRepeatable(tween(2000, delayMillis = 700), RepeatMode.Restart))
    val primary = MaterialTheme.colorScheme.primary

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).size(100.dp)) {
        val c = center
        val maxR = size.minDimension / 2
        listOf(pulse1, pulse2).forEach { p ->
            drawCircle(primary.copy(alpha = (1f - p) * 0.4f), radius = maxR * p, center = c, style = Stroke(2.dp.toPx()))
        }
        drawCircle(primary, radius = 6.dp.toPx(), center = c)
    }
}

/** Morphing progress indicator: circle → line → circle */
@Composable
fun MorphProgressIndicator(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val phase by inf.animateFloat(
        0f, 2f,
        infiniteRepeatable(tween(3000), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).size(120.dp, 40.dp)) {
        val cx = size.width / 2
        val cy = size.height / 2
        when {
            phase < 1f -> {
                val r = 15.dp.toPx() * (1f - phase)
                val w = size.width * 0.8f * phase
                drawRoundRect(primary, topLeft = Offset(cx - w / 2, cy - 3.dp.toPx()), size = androidx.compose.ui.geometry.Size(w, 6.dp.toPx()), cornerRadius = androidx.compose.ui.geometry.CornerRadius(3.dp.toPx()))
                if (r > 1f) drawCircle(primary, r, Offset(cx, cy))
            }
            else -> {
                val p = phase - 1f
                val w = size.width * 0.8f * (1f - p)
                drawRoundRect(primary, topLeft = Offset(cx - w / 2, cy - 3.dp.toPx()), size = androidx.compose.ui.geometry.Size(w.coerceAtLeast(1f), 6.dp.toPx()), cornerRadius = androidx.compose.ui.geometry.CornerRadius(3.dp.toPx()))
                val r = 15.dp.toPx() * p
                if (r > 1f) drawCircle(primary, r, Offset(cx, cy))
            }
        }
    }
}

/** Animated step indicator */
/** Text with animated underline */
@Composable
fun AnimatedUnderlineText(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val width by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(1500, easing = FastOutSlowInEasing), RepeatMode.Reverse),
    )

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = stringResource(Res.string.demo_hover_me_lower),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(width)
                .height(2.dp)
                .clip(RoundedCornerShape(1.dp))
                .background(MaterialTheme.colorScheme.primary),
        )
    }
}

/** Blinking cursor animation */
/** Animated tag / chip that scales in with spring */
@Composable
fun SpringChip(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { visible = true; delay(2000); visible = false; delay(800) }
    }

    val scale by animateFloatAsState(
        if (visible) 1f else 0f,
        spring(dampingRatio = Spring.DampingRatioMediumBouncy),
    )

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).graphicsLayer { scaleX = scale; scaleY = scale },
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(16.dp),
            )
            Text(
                text = stringResource(Res.string.examples_chip_new),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}

/** Spinning coin flip */
@Composable
fun CoinFlip(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val rotY by inf.animateFloat(
        0f, 360f,
        infiniteRepeatable(tween(1500, easing = LinearEasing), RepeatMode.Restart),
    )
    val showFront = cos(rotY * PI.toFloat() / 180f) > 0

    Surface(
        shape = CircleShape,
        color = if (showFront) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In)
            .size(60.dp)
            .graphicsLayer {
                rotationY = rotY
                cameraDistance = 12f * density
            },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = if (showFront) "H" else "T",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
    }
}

/** DNA double helix animation */
/** Animated pie chart segments */

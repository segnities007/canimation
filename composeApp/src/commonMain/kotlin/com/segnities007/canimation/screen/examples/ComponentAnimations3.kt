package com.segnities007.canimation.screen.examples

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

// ===== CARD ANIMATIONS =====

/** Card with animated border that traces around the edges */
@Composable
fun CardBorderTrace() {
    val inf = rememberInfiniteTransition()
    val progress by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary
    val outline = MaterialTheme.colorScheme.outline

    Canvas(modifier = Modifier.size(160.dp, 100.dp)) {
        val w = size.width
        val h = size.height
        val r = 12.dp.toPx()
        drawRoundRect(outline.copy(alpha = 0.2f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(r), style = Stroke(2.dp.toPx()))

        val perimeter = 2 * (w + h) - 8 * r + 2 * PI.toFloat() * r
        val headDist = progress * perimeter
        val tailDist = ((progress - 0.3f + 1f) % 1f) * perimeter

        val path = Path()
        path.addRoundRect(
            androidx.compose.ui.geometry.RoundRect(0f, 0f, w, h, androidx.compose.ui.geometry.CornerRadius(r))
        )

        drawPath(path, primary, style = Stroke(3.dp.toPx(), cap = StrokeCap.Round))
    }
}

/** Card that lifts on hover with shadow animation */
@Composable
fun CardLiftHover() {
    var lifted by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { lifted = true; delay(2000); lifted = false; delay(1000) }
    }

    val elevation by animateFloatAsState(if (lifted) 16f else 2f, spring(stiffness = Spring.StiffnessLow))
    val translateY by animateFloatAsState(if (lifted) -8f else 0f, spring(stiffness = Spring.StiffnessLow))

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        shadowElevation = elevation.dp,
        modifier = Modifier
            .size(140.dp, 80.dp)
            .graphicsLayer { translationY = translateY },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text("Lift", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}

/** Card with animated gradient border */
@Composable
fun CardGradientBorder() {
    val inf = rememberInfiniteTransition()
    val angle by inf.animateFloat(
        0f, 360f,
        infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary

    Box(
        modifier = Modifier
            .size(160.dp, 100.dp)
            .graphicsLayer { rotationZ = 0f }
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.sweepGradient(
                    0f to primary,
                    0.33f to secondary,
                    0.66f to tertiary,
                    1f to primary,
                )
            )
            .padding(2.dp)
            .clip(RoundedCornerShape(11.dp))
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center,
    ) {
        Text("Gradient", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
    }
}

/** Card expand/collapse animation */
@Composable
fun CardExpandCollapse() {
    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { expanded = true; delay(2500); expanded = false; delay(1500) }
    }

    val height by animateFloatAsState(if (expanded) 120f else 48f, spring(stiffness = Spring.StiffnessLow))

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
        modifier = Modifier.width(160.dp).height(height.dp),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text("Card Title", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
            if (expanded) {
                Text("Expanded content with more details...", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f))
            }
        }
    }
}

/** Card with parallax tilt effect */
@Composable
fun CardParallaxTilt() {
    val inf = rememberInfiniteTransition()
    val rotX by inf.animateFloat(
        -10f, 10f,
        infiniteRepeatable(tween(2000), RepeatMode.Reverse),
    )
    val rotY by inf.animateFloat(
        -10f, 10f,
        infiniteRepeatable(tween(2500), RepeatMode.Reverse),
    )

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
        modifier = Modifier
            .size(140.dp, 90.dp)
            .graphicsLayer {
                rotationX = rotX
                rotationY = rotY
                cameraDistance = 12f * density
            },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text("Parallax", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}

/** Card with glassmorphism frosted effect */
@Composable
fun CardGlassmorphism() {
    val inf = rememberInfiniteTransition()
    val offset by inf.animateFloat(
        -50f, 50f,
        infiniteRepeatable(tween(3000), RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary

    Box(
        modifier = Modifier.size(160.dp, 100.dp),
        contentAlignment = Alignment.Center,
    ) {
        // Moving blob behind glass
        Box(
            modifier = Modifier
                .size(40.dp)
                .offset { IntOffset(offset.roundToInt(), (offset * 0.6f).roundToInt()) }
                .clip(CircleShape)
                .background(primary.copy(alpha = 0.5f)),
        )
        // Glass card
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
            modifier = Modifier.size(140.dp, 80.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("Glass", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

/** Card with reveal wipe animation */
@Composable
fun CardRevealWipe() {
    val inf = rememberInfiniteTransition()
    val clip by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(2000, easing = FastOutSlowInEasing), RepeatMode.Restart),
    )

    Box(
        modifier = Modifier
            .size(160.dp, 100.dp)
            .clip(RoundedCornerShape(12.dp)),
    ) {
        // Background
        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center,
        ) {
            Text("Reveal", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f))
        }
        // Wipe overlay
        Box(
            modifier = Modifier
                .fillMaxWidth(clip)
                .height(100.dp)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center,
        ) {
            if (clip > 0.3f) {
                Text("Reveal", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }
    }
}

/** Stacked cards that rotate like a fan */
@Composable
fun CardFanStack() {
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
        modifier = Modifier.size(160.dp, 100.dp),
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
fun CardMagneticSnap() {
    var target by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) { target = 1; delay(1500); target = 2; delay(1500); target = 0; delay(1500) }
    }

    val offsetX by animateFloatAsState(
        when (target) { 0 -> -40f; 1 -> 0f; else -> 40f },
        spring(dampingRatio = Spring.DampingRatioMediumBouncy),
    )

    Box(
        modifier = Modifier.size(160.dp, 80.dp),
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
                Text("•", color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }
    }
}

// ===== MORE UNIQUE ANIMATIONS =====

/** Animated notification badge count */
@Composable
fun NotificationBadge() {
    var count by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) { count++; delay(800) }
    }
    val displayCount = count % 100

    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessHigh),
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("Notifications", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier
                .size(24.dp)
                .graphicsLayer { scaleX = scale; scaleY = scale },
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = displayCount.toString(),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onError,
                )
            }
        }
    }
}

/** Animated progress bar with glow */
@Composable
fun GlowProgressBar() {
    val inf = rememberInfiniteTransition()
    val progress by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(3000, easing = FastOutSlowInEasing), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary

    Box(
        modifier = Modifier.width(160.dp).height(8.dp).clip(RoundedCornerShape(4.dp))
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
@Composable
fun SpringToggle() {
    var on by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { on = !on; delay(1500) }
    }

    val thumbOffset by animateFloatAsState(
        if (on) 24f else 0f,
        spring(dampingRatio = Spring.DampingRatioMediumBouncy),
    )
    val trackColor by animateColorAsState(
        if (on) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        tween(300),
    )

    Box(
        modifier = Modifier
            .width(52.dp)
            .height(28.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(trackColor)
            .padding(2.dp),
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .offset { IntOffset(thumbOffset.roundToInt(), 0) }
                .clip(CircleShape)
                .background(Color.White),
        )
    }
}

/** Pulse radar effect */
@Composable
fun PulseRadar() {
    val inf = rememberInfiniteTransition()
    val pulse1 by inf.animateFloat(0f, 1f, infiniteRepeatable(tween(2000), RepeatMode.Restart))
    val pulse2 by inf.animateFloat(0f, 1f, infiniteRepeatable(tween(2000, delayMillis = 700), RepeatMode.Restart))
    val primary = MaterialTheme.colorScheme.primary

    Canvas(modifier = Modifier.size(100.dp)) {
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
fun MorphProgressIndicator() {
    val inf = rememberInfiniteTransition()
    val phase by inf.animateFloat(
        0f, 2f,
        infiniteRepeatable(tween(3000), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary

    Canvas(modifier = Modifier.size(120.dp, 40.dp)) {
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
@Composable
fun StepIndicator() {
    var step by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) { step = (step + 1) % 4; delay(1000) }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(4) { i ->
            val active = i <= step
            val color by animateColorAsState(
                if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                tween(300),
            )
            val scale by animateFloatAsState(if (i == step) 1.2f else 1f, spring())

            Box(
                modifier = Modifier
                    .size(12.dp)
                    .graphicsLayer { scaleX = scale; scaleY = scale }
                    .clip(CircleShape)
                    .background(color),
            )
            if (i < 3) {
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height(2.dp)
                        .background(if (i < step) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant),
                )
            }
        }
    }
}

/** Text with animated underline */
@Composable
fun AnimatedUnderlineText() {
    val inf = rememberInfiniteTransition()
    val width by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(1500, easing = FastOutSlowInEasing), RepeatMode.Reverse),
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = "Hover me",
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
@Composable
fun BlinkingCursor() {
    val inf = rememberInfiniteTransition()
    val alpha by inf.animateFloat(
        1f, 0f,
        infiniteRepeatable(tween(500), RepeatMode.Reverse),
    )

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Type here", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(20.dp)
                .graphicsLayer { this.alpha = alpha }
                .background(MaterialTheme.colorScheme.primary),
        )
    }
}

/** Animated tag / chip that scales in with spring */
@Composable
fun SpringChip() {
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
        modifier = Modifier.graphicsLayer { scaleX = scale; scaleY = scale },
    ) {
        Text(
            text = "New ✨",
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}

/** Spinning coin flip */
@Composable
fun CoinFlip() {
    val inf = rememberInfiniteTransition()
    val rotY by inf.animateFloat(
        0f, 360f,
        infiniteRepeatable(tween(1500, easing = LinearEasing), RepeatMode.Restart),
    )
    val showFront = cos(rotY * PI.toFloat() / 180f) > 0

    Surface(
        shape = CircleShape,
        color = if (showFront) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
        modifier = Modifier
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
@Composable
fun DnaHelix() {
    val inf = rememberInfiniteTransition()
    val phase by inf.animateFloat(
        0f, 2f * PI.toFloat(),
        infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary

    Canvas(modifier = Modifier.size(160.dp, 60.dp)) {
        val w = size.width
        val cy = size.height / 2
        val amp = size.height * 0.3f
        val dotR = 4.dp.toPx()

        for (i in 0..16) {
            val x = w * i / 16f
            val angle = phase + i * 0.5f
            val y1 = cy + sin(angle) * amp
            val y2 = cy - sin(angle) * amp

            drawCircle(primary, dotR, Offset(x, y1))
            drawCircle(secondary, dotR, Offset(x, y2))
            drawLine(primary.copy(alpha = 0.2f), Offset(x, y1), Offset(x, y2), strokeWidth = 1.dp.toPx())
        }
    }
}

/** Animated pie chart segments */
@Composable
fun AnimatedPieChart() {
    val anim = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            anim.snapTo(0f)
            anim.animateTo(1f, tween(2000, easing = FastOutSlowInEasing))
            delay(1500)
        }
    }

    val slices = listOf(
        0.35f to MaterialTheme.colorScheme.primary,
        0.25f to MaterialTheme.colorScheme.secondary,
        0.2f to MaterialTheme.colorScheme.tertiary,
        0.2f to MaterialTheme.colorScheme.error,
    )

    Canvas(modifier = Modifier.size(80.dp)) {
        var startAngle = -90f
        slices.forEach { (fraction, color) ->
            val sweep = 360f * fraction * anim.value
            drawArc(color, startAngle, sweep, useCenter = true)
            startAngle += sweep
        }
    }
}

/** Pendulum swing */
@Composable
fun PendulumSwing() {
    val inf = rememberInfiniteTransition()
    val angle by inf.animateFloat(
        -30f, 30f,
        infiniteRepeatable(tween(1500, easing = FastOutSlowInEasing), RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary

    Canvas(modifier = Modifier.size(80.dp, 100.dp)) {
        val pivotX = size.width / 2
        val pivotY = 0f
        val rodLen = size.height * 0.8f
        val rad = angle * PI.toFloat() / 180f
        val bobX = pivotX + sin(rad) * rodLen
        val bobY = pivotY + cos(rad) * rodLen

        drawCircle(primary.copy(alpha = 0.5f), 4.dp.toPx(), Offset(pivotX, pivotY))
        drawLine(primary.copy(alpha = 0.4f), Offset(pivotX, pivotY), Offset(bobX, bobY), 2.dp.toPx())
        drawCircle(primary, 10.dp.toPx(), Offset(bobX, bobY))
    }
}

/** Bouncing ball with shadow */
@Composable
fun BouncingBall() {
    val inf = rememberInfiniteTransition()
    val bounce by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(800), RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary

    val y = bounce * bounce // quadratic for gravity feel
    val shadowScale = 0.5f + (1f - y) * 0.5f

    Canvas(modifier = Modifier.size(60.dp, 80.dp)) {
        val cx = size.width / 2
        val bottom = size.height - 10.dp.toPx()
        val ballY = bottom - y * (size.height - 30.dp.toPx())

        // Shadow
        drawOval(
            Color.Black.copy(alpha = 0.15f * shadowScale),
            topLeft = Offset(cx - 12.dp.toPx() * shadowScale, bottom),
            size = androidx.compose.ui.geometry.Size(24.dp.toPx() * shadowScale, 4.dp.toPx()),
        )
        // Ball
        drawCircle(primary, 12.dp.toPx(), Offset(cx, ballY))
    }
}

/** Circular menu items that orbit in/out */
@Composable
fun CircularMenu() {
    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { expanded = true; delay(2500); expanded = false; delay(1500) }
    }

    val radius by animateFloatAsState(
        if (expanded) 35f else 0f,
        spring(dampingRatio = Spring.DampingRatioMediumBouncy),
    )

    Box(
        modifier = Modifier.size(100.dp),
        contentAlignment = Alignment.Center,
    ) {
        repeat(5) { i ->
            val angle = (i * 72f - 90f) * PI.toFloat() / 180f
            val x = cos(angle) * radius
            val y = sin(angle) * radius
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .offset { IntOffset(x.roundToInt(), y.roundToInt()) }
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)),
            )
        }
        // Center button
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center,
        ) {
            Text("+", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}

/** Animated bar chart */
@Composable
fun AnimatedBarChart() {
    val bars = remember { listOf(0.7f, 0.4f, 0.9f, 0.5f, 0.8f) }
    var animate by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { animate = true; delay(3000); animate = false; delay(1000) }
    }

    Row(
        modifier = Modifier.height(60.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        bars.forEachIndexed { i, target ->
            val height by animateFloatAsState(
                if (animate) target * 60f else 0f,
                spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow),
            )
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(height.dp)
                    .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f + i * 0.08f)),
            )
        }
    }
}

/** Slinky spring animation */
@Composable
fun SlinkySpring() {
    val inf = rememberInfiniteTransition()
    val stretch by inf.animateFloat(
        0.4f, 1f,
        infiniteRepeatable(tween(1000), RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary

    Canvas(modifier = Modifier.size(60.dp, 80.dp)) {
        val w = size.width
        val h = size.height * stretch
        val coils = 8
        val coilH = h / coils
        for (i in 0 until coils) {
            val y = i * coilH
            drawLine(
                primary.copy(alpha = 0.6f + i * 0.05f),
                Offset(10.dp.toPx(), y),
                Offset(w - 10.dp.toPx(), y + coilH * 0.5f),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Round,
            )
            drawLine(
                primary.copy(alpha = 0.6f + i * 0.05f),
                Offset(w - 10.dp.toPx(), y + coilH * 0.5f),
                Offset(10.dp.toPx(), y + coilH),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Round,
            )
        }
    }
}

/** Typewriter with delete animation */
@Composable
fun TypewriterDelete() {
    val text = "canimation"
    var count by remember { mutableIntStateOf(0) }
    var deleting by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            deleting = false
            for (i in 1..text.length) { count = i; delay(100) }
            delay(1500)
            deleting = true
            for (i in text.length downTo 0) { count = i; delay(60) }
            delay(500)
        }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = text.take(count),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )
        val inf = rememberInfiniteTransition()
        val cursorAlpha by inf.animateFloat(1f, 0f, infiniteRepeatable(tween(500), RepeatMode.Reverse))
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(20.dp)
                .graphicsLayer { alpha = cursorAlpha }
                .background(MaterialTheme.colorScheme.primary),
        )
    }
}

/** Animated gradient text */
@Composable
fun AnimatedGradientText() {
    val inf = rememberInfiniteTransition()
    val offset by inf.animateFloat(
        0f, 300f,
        infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary

    Text(
        text = "canimation",
        style = MaterialTheme.typography.headlineSmall.copy(
            brush = Brush.horizontalGradient(
                listOf(primary, secondary, tertiary, primary),
                startX = offset,
                endX = offset + 200f,
            ),
        ),
        fontWeight = FontWeight.Bold,
    )
}

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
import androidx.compose.material.icons.filled.Add
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
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import canimation.composeapp.generated.resources.*
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

// ===== CARD ANIMATIONS =====

/** Card with animated border that traces around the edges */

@Composable
fun AnimatedPieChart(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

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

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).size(80.dp)) {
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
fun PendulumSwing(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val angle by inf.animateFloat(
        -30f, 30f,
        infiniteRepeatable(tween(1500, easing = FastOutSlowInEasing), RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(80.dp, 100.dp)) {
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
fun BouncingBall(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val bounce by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(800), RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary

    val y = bounce * bounce // quadratic for gravity feel
    val shadowScale = 0.5f + (1f - y) * 0.5f

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).size(60.dp, 80.dp)) {
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
fun CircularMenu(
    itemCount: Int = 5,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { expanded = true; delay(2500); expanded = false; delay(1500) }
    }

    val radius by animateFloatAsState(
        if (expanded) 35f else 0f,
        spring(dampingRatio = Spring.DampingRatioMediumBouncy),
    )

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).size(100.dp),
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
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp),
            )
        }
    }
}

/** Animated bar chart */
@Composable
fun AnimatedBarChart(
    barCount: Int = 5,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val bars = remember { listOf(0.7f, 0.4f, 0.9f, 0.5f, 0.8f) }
    var animate by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { animate = true; delay(3000); animate = false; delay(1000) }
    }

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).height(60.dp),
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
fun SlinkySpring(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val stretch by inf.animateFloat(
        0.4f, 1f,
        infiniteRepeatable(tween(1000), RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(60.dp, 80.dp)) {
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
fun TypewriterDelete(
    text: String = "canimation",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

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

    Row(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In), verticalAlignment = Alignment.CenterVertically) {
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
fun AnimatedGradientText(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val offset by inf.animateFloat(
        0f, 300f,
        infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary

    Text(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In),
        text = stringResource(Res.string.demo_canimation),
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

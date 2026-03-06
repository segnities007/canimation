package com.segnities007.canimation.screen.examples

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// ─── 1. WaveformVisualizer ──────────────────────────────────────────


@Composable
fun RotatingCube(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

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
        Text("Rotating Cube", style = MaterialTheme.typography.titleSmall)
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
            for (i in 0..3) {
                drawLine(outline, front[i], front[(i + 1) % 4], 2f)
                drawLine(outline, back[i], back[(i + 1) % 4], 2f)
                drawLine(outline.copy(alpha = 0.4f), front[i], back[i], 1.5f)
            }
        }
    }
}

// ─── 18. WaterDroplet ───────────────────────────────────────────────

@Composable
fun WaterDroplet(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val ripple1 by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(1500), RepeatMode.Restart))
    val ripple2 by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(1500, delayMillis = 500), RepeatMode.Restart))
    val primary = MaterialTheme.colorScheme.primary

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Blur.Soft),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Water Droplet", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Canvas(Modifier.size(100.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            listOf(ripple1, ripple2).forEach { r ->
                drawCircle(
                    primary.copy(alpha = (1f - r) * 0.5f),
                    radius = r * size.minDimension / 2,
                    center = center,
                    style = Stroke(2f),
                )
            }
            drawCircle(primary, radius = 6f, center = center)
        }
    }
}

// ─── 19. SlotMachine ────────────────────────────────────────────────

@Composable
fun SlotMachine(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val symbols = listOf(
        Icons.Default.Star,
        Icons.Default.Favorite,
        Icons.Default.PlayArrow,
        Icons.Default.Settings,
        Icons.Default.Home,
    )
    var slots by remember { mutableStateOf(List(3) { symbols.random() }) }
    var spinning by remember { mutableStateOf(false) }

    LaunchedEffect(spinning) {
        if (spinning) {
            repeat(10) {
                slots = List(3) { symbols.random() }
                delay(100)
            }
            spinning = false
        }
    }

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Page.BottomSheet),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Slot Machine", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            slots.forEach { symbol: ImageVector ->
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    tonalElevation = 4.dp,
                    modifier = Modifier.size(48.dp),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = symbol,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp),
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { spinning = true }, enabled = !spinning) { Text("Spin") }
    }
}

// ─── 20. MusicEqualizer ─────────────────────────────────────────────

@Composable
fun MusicEqualizer(
    barCount: Int = 5,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val primary = MaterialTheme.colorScheme.primary

    val barHeights = (0 until barCount).map { i ->
        transition.animateFloat(
            initialValue = 0.3f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 400 + i * 100,
                    easing = FastOutSlowInEasing,
                ),
                repeatMode = RepeatMode.Reverse,
            ),
        )
    }

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Music Equalizer", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Canvas(Modifier.size(80.dp, 60.dp)) {
            val barWidth = size.width / (barCount * 2f)
            for (i in 0 until barCount) {
                val h = barHeights[i].value * size.height
                val x = i * barWidth * 2 + barWidth / 2
                drawRoundRect(
                    color = primary,
                    topLeft = Offset(x, size.height - h),
                    size = Size(barWidth, h),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(barWidth / 2),
                )
            }
        }
    }
}

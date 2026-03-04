package com.segnities007.canimation.screen.examples

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

// ─── 1. WaveformVisualizer ──────────────────────────────────────────

@Composable
fun WaveformVisualizer() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val barCount = 12

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Waveform", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.height(60.dp),
        ) {
            for (i in 0 until barCount) {
                val height by transition.animateFloat(
                    initialValue = 10f,
                    targetValue = 55f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(400 + i * 60, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse,
                    ),
                )
                Box(
                    Modifier
                        .width(6.dp)
                        .height(height.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(MaterialTheme.colorScheme.primary),
                )
            }
        }
    }
}

// ─── 2. TypewriterCursor ────────────────────────────────────────────

@Composable
fun TypewriterCursor() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val fullText = "Hello, World!"
    var displayedText by remember { mutableStateOf("") }
    val transition = rememberInfiniteTransition()
    val cursorAlpha by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(tween(500), RepeatMode.Reverse),
    )

    LaunchedEffect(Unit) {
        for (i in fullText.indices) {
            displayedText = fullText.substring(0, i + 1)
            delay(100)
        }
    }

    Box(
        modifier = Modifier
            .canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row {
            Text(
                displayedText,
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = FontFamily.Monospace,
            )
            Text(
                "▌",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary.copy(alpha = cursorAlpha),
            )
        }
    }
}

// ─── 3. RadialProgress ──────────────────────────────────────────────

@Composable
fun RadialProgress() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        progress.animateTo(0.75f, animationSpec = tween(1500, easing = FastOutSlowInEasing))
    }
    val primary = MaterialTheme.colorScheme.primary
    val track = MaterialTheme.colorScheme.surfaceVariant

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Radial Progress", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(100.dp)) {
            Canvas(Modifier.size(100.dp)) {
                drawArc(track, 0f, 360f, false, style = Stroke(10f, cap = StrokeCap.Round))
                drawArc(
                    primary, -90f, progress.value * 360f, false,
                    style = Stroke(10f, cap = StrokeCap.Round),
                )
            }
            Text("${(progress.value * 100).toInt()}%", fontWeight = FontWeight.Bold)
        }
    }
}

// ─── 4. MatrixRain ──────────────────────────────────────────────────

@Composable
fun MatrixRain() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val columns = 8
    val chars = "アイウエオカキクケコ0123456789"
    val displayChars = remember { mutableStateListOf<String>().apply { repeat(columns) { add("") } } }

    LaunchedEffect(Unit) {
        while (true) {
            for (c in 0 until columns) {
                val len = (3..6).random()
                displayChars[c] = buildString { repeat(len) { append(chars.random()) } }
            }
            delay(200)
        }
    }

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Matrix Rain", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            displayChars.forEach { col ->
                Column {
                    col.forEach { ch ->
                        Text(
                            ch.toString(),
                            color = Color(0xFF00FF41),
                            fontFamily = FontFamily.Monospace,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }
        }
    }
}

// ─── 5. PulseButton ─────────────────────────────────────────────────

@Composable
fun PulseButton() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val scale by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Pulse Button", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(contentAlignment = Alignment.Center) {
            Canvas(Modifier.size((60 * scale).dp)) {
                drawCircle(primary.copy(alpha = 0.25f))
            }
            Button(onClick = {}) { Text("Tap") }
        }
    }
}

// ─── 6. NeumorphismCard ─────────────────────────────────────────────

@Composable
fun NeumorphismCard() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val elevation by transition.animateFloat(
        initialValue = 2f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(tween(1500), RepeatMode.Reverse),
    )

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Blur.Soft),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Neumorphism Card", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = elevation.dp,
            shadowElevation = elevation.dp,
            modifier = Modifier.size(140.dp, 90.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("Depth: ${elevation.toInt()}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

// ─── 7. GradientBorderCard ──────────────────────────────────────────

@Composable
fun GradientBorderCard() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val offset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2000, easing = LinearEasing)),
    )

    val colors = listOf(Color(0xFF6200EE), Color(0xFF03DAC5), Color(0xFFFF5722), Color(0xFF6200EE))
    val shift = (offset * (colors.size - 1)).toInt().coerceAtMost(colors.size - 2)
    val borderColor = Color(
        red = colors[shift].red + (colors[shift + 1].red - colors[shift].red) * (offset * (colors.size - 1) - shift),
        green = colors[shift].green + (colors[shift + 1].green - colors[shift].green) * (offset * (colors.size - 1) - shift),
        blue = colors[shift].blue + (colors[shift + 1].blue - colors[shift].blue) * (offset * (colors.size - 1) - shift),
    )

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Reveal.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Gradient Border", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(
            Modifier
                .size(150.dp, 90.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(borderColor)
                .padding(3.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center,
        ) {
            Text("Animated", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// ─── 8. FlipCounter ─────────────────────────────────────────────────

@Composable
fun FlipCounter() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var count by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            count++
        }
    }

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Flip Counter", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            val digits = (count % 10000).toString().padStart(4, '0')
            digits.forEach { digit: Char ->
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    tonalElevation = 4.dp,
                    modifier = Modifier.size(36.dp, 48.dp),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            digit.toString(),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}

// ─── 9. ExpandableChip ──────────────────────────────────────────────

@Composable
fun ExpandableChip() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var expanded by remember { mutableStateOf(false) }
    val width by animateFloatAsState(if (expanded) 200f else 80f, tween(300))

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Micro.Pulse),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Expandable Chip", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
                .height(36.dp)
                .width(width.dp)
                .clickable { expanded = !expanded },
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    if (expanded) "Kotlin Multiplatform" else "KMP",
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                )
            }
        }
    }
}

// ─── 10. StackedNotifications ───────────────────────────────────────

@Composable
fun StackedNotifications() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val items = listOf("New message", "Build passed", "Review requested")

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Swipe.Right),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Stacked Notifications", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(modifier = Modifier.height(100.dp).width(200.dp)) {
            items.forEachIndexed { idx, text ->
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    tonalElevation = (2 + idx * 2).dp,
                    shadowElevation = (1 + idx).dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (idx * 12).dp)
                        .padding(horizontal = (idx * 6).dp),
                ) {
                    Text(text, Modifier.padding(12.dp), style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

// ─── 11. SlidingToggle ──────────────────────────────────────────────

@Composable
fun SlidingToggle() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var on by remember { mutableStateOf(false) }
    val offsetX by animateFloatAsState(if (on) 32f else 0f, tween(250))
    val trackColor by animateColorAsState(
        targetValue = if (on) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        animationSpec = tween(250),
    )

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Sliding Toggle", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(
            Modifier
                .width(64.dp)
                .height(32.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(trackColor)
                .clickable { on = !on }
                .padding(4.dp),
        ) {
            Box(
                Modifier
                    .offset(x = offsetX.dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White),
            )
        }
    }
}

// ─── 12. CircularRevealCard ─────────────────────────────────────────

@Composable
fun CircularRevealCard() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val reveal = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            reveal.animateTo(1f, tween(1200))
            delay(500)
            reveal.snapTo(0f)
        }
    }
    val primary = MaterialTheme.colorScheme.primary

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Reveal.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Circular Reveal", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(
            Modifier
                .size(140.dp, 90.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center,
        ) {
            Canvas(Modifier.fillMaxSize()) {
                drawCircle(
                    color = primary.copy(alpha = 0.5f),
                    radius = reveal.value * size.maxDimension,
                    center = Offset(size.width / 2, size.height / 2),
                )
            }
            Text("Reveal", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// ─── 13. TypingIndicator ────────────────────────────────────────────

@Composable
fun TypingIndicator() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Typing Indicator", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 2.dp,
            modifier = Modifier.padding(4.dp),
        ) {
            Row(
                Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                for (i in 0..2) {
                    val offsetY by transition.animateFloat(
                        initialValue = 0f,
                        targetValue = -8f,
                        animationSpec = infiniteRepeatable(
                            tween(400, delayMillis = i * 150),
                            RepeatMode.Reverse,
                        ),
                    )
                    Box(
                        Modifier
                            .offset(y = offsetY.dp)
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)),
                    )
                }
            }
        }
    }
}

// ─── 14. SkeletonText ───────────────────────────────────────────────

@Composable
fun SkeletonText() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val alpha by transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse),
    )

    Column(
        modifier = Modifier
            .canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text("Skeleton Loading", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(4.dp))
        listOf(1f, 0.8f, 0.6f, 0.9f).forEach { fraction ->
            Box(
                Modifier
                    .fillMaxWidth(fraction)
                    .height(14.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = alpha)),
            )
        }
    }
}

// ─── 15. SwipeCard ──────────────────────────────────────────────────

@Composable
fun SwipeCard() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var dismissed by remember { mutableStateOf(false) }
    val offsetX by animateFloatAsState(if (dismissed) 300f else 0f, tween(400))
    val cardAlpha by animateFloatAsState(if (dismissed) 0f else 1f, tween(400))

    LaunchedEffect(dismissed) {
        if (dismissed) {
            delay(600)
            dismissed = false
        }
    }

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Swipe.Left),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Swipe Card", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Surface(
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 4.dp,
            modifier = Modifier
                .offset(x = offsetX.dp)
                .size(160.dp, 80.dp)
                .clickable { dismissed = true },
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = cardAlpha),
                ),
            ) {
                Text("Tap to swipe", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

// ─── 16. AnimatedCheckmark ──────────────────────────────────────────

@Composable
fun AnimatedCheckmark() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) { progress.animateTo(1f, tween(800, easing = FastOutSlowInEasing)) }
    val primary = MaterialTheme.colorScheme.primary

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Reveal.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Animated Checkmark", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Canvas(Modifier.size(64.dp)) {
            drawCircle(primary, radius = size.minDimension / 2 * progress.value, style = Stroke(4f))
            if (progress.value > 0.4f) {
                val p = ((progress.value - 0.4f) / 0.6f).coerceIn(0f, 1f)
                val cx = size.width / 2
                val cy = size.height / 2
                val startX = cx - 12f
                val startY = cy + 2f
                val midX = cx - 2f
                val midY = cy + 12f
                val endX = cx + 14f
                val endY = cy - 10f
                if (p <= 0.5f) {
                    val t = p / 0.5f
                    drawLine(primary, Offset(startX, startY), Offset(startX + (midX - startX) * t, startY + (midY - startY) * t), 4f, StrokeCap.Round)
                } else {
                    drawLine(primary, Offset(startX, startY), Offset(midX, midY), 4f, StrokeCap.Round)
                    val t = (p - 0.5f) / 0.5f
                    drawLine(primary, Offset(midX, midY), Offset(midX + (endX - midX) * t, midY + (endY - midY) * t), 4f, StrokeCap.Round)
                }
            }
        }
    }
}

// ─── 17. RotatingCube ───────────────────────────────────────────────

@Composable
fun RotatingCube() {
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
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
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
fun WaterDroplet() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val ripple1 by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(1500), RepeatMode.Restart))
    val ripple2 by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(1500, delayMillis = 500), RepeatMode.Restart))
    val primary = MaterialTheme.colorScheme.primary

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Blur.Soft),
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
fun SlotMachine() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val symbols = listOf("7", "★", "♦", "♣", "♥")
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
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Page.BottomSheet),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Slot Machine", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            slots.forEach { symbol ->
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    tonalElevation = 4.dp,
                    modifier = Modifier.size(48.dp),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(symbol, style = MaterialTheme.typography.headlineMedium)
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
fun MusicEqualizer() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val barCount = 5
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
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
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

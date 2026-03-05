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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.roundToInt
import kotlin.math.sin
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

// ============================================================
// 1. Animated Number Counter
// ============================================================

@Composable
fun AnimatedCounter(
    targetValue: Int = 1234,
    durationMs: Int = 2000,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var currentValue by remember { mutableIntStateOf(0) }
    var running by remember { mutableStateOf(true) }

    LaunchedEffect(running) {
        if (running) {
            currentValue = 0
            val steps = 60
            val stepDelay = durationMs.toLong() / steps
            for (i in 1..steps) {
                val progress = i.toFloat() / steps
                val eased = FastOutSlowInEasing.transform(progress)
                currentValue = (targetValue * eased).roundToInt()
                delay(stepDelay)
            }
            currentValue = targetValue
            delay(1500)
            running = false
        } else {
            delay(400)
            running = true
        }
    }

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),

        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val text = currentValue.toString()
        text.forEach { ch ->
            Text(
                text = ch.toString(),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                ),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
// ============================================================
// 4. Scramble Text
// ============================================================

@Composable
fun ScrambleText(
    targetText: String = "CANIMATION",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%"
    var displayText by remember { mutableStateOf(targetText.map { chars.random() }.joinToString("")) }
    var resolvedCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            resolvedCount = 0
            delay(300)
            for (i in targetText.indices) {
                // Scramble remaining for a few frames
                repeat(3) {
                    displayText = targetText.take(resolvedCount) +
                        (resolvedCount until targetText.length).map { chars.random() }.joinToString("")
                    delay(40)
                }
                resolvedCount = i + 1
                displayText = targetText.take(resolvedCount) +
                    (resolvedCount until targetText.length).map { chars.random() }.joinToString("")
            }
            displayText = targetText
            delay(2500)
        }
    }

    Text(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In),
        text = displayText,
        style = MaterialTheme.typography.titleLarge.copy(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp,
        ),
        color = MaterialTheme.colorScheme.primary,
    )
}

// ============================================================
// 5. Wavy Text
// ============================================================

@Composable
fun WavyText(
    text: String = "Wavy Text",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In),

        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
    ) {
        text.forEachIndexed { index, ch ->
            val offset = sin(phase + index * 0.5f) * 8f
            Text(
                text = ch.toString(),
                modifier = Modifier.offset { IntOffset(0, -offset.roundToInt()) },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

// ============================================================
// 6. Pulse Loading Dots
// ============================================================

@Composable
fun PulseLoadingDots(dotCount: Int = 3,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop),

        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(dotCount) { index ->
            val scale by infiniteTransition.animateFloat(
                initialValue = 0.5f,
                targetValue = 1.2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(600, delayMillis = index * 200, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse,
                ),
            )
            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.3f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(600, delayMillis = index * 200, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse,
                ),
            )
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .scale(scale)
                    .alpha(alpha)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
            )
        }
    }
}
// ============================================================
// 8. Shimmer / Skeleton Loading
// ============================================================

@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()
    val shimmerOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    val baseColor = MaterialTheme.colorScheme.surfaceVariant
    val shimmerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).fillMaxWidth().padding(horizontal = 16.dp),
    ) {
        // Title line
        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(baseColor, shimmerColor, baseColor),
                        startX = shimmerOffset * 300f,
                        endX = (shimmerOffset + 1f) * 300f,
                    ),
                ),
        )
        // Body lines
        repeat(3) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(if (it == 2) 0.75f else 1f)
                    .height(12.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(baseColor, shimmerColor, baseColor),
                            startX = shimmerOffset * 300f,
                            endX = (shimmerOffset + 1f) * 300f,
                        ),
                    ),
            )
        }
        // Avatar + text row
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(baseColor, shimmerColor, baseColor),
                            startX = shimmerOffset * 300f,
                            endX = (shimmerOffset + 1f) * 300f,
                        ),
                    ),
            )
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(10.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(baseColor, shimmerColor, baseColor),
                                startX = shimmerOffset * 300f,
                                endX = (shimmerOffset + 1f) * 300f,
                            ),
                        ),
                )
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(10.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(baseColor, shimmerColor, baseColor),
                                startX = shimmerOffset * 300f,
                                endX = (shimmerOffset + 1f) * 300f,
                            ),
                        ),
                )
            }
        }
    }
}

// ============================================================
// 9. Smooth Animated Tabs
// ============================================================

@Composable
fun AnimatedTabs(
    tabs: List<String> = listOf("Home", "Search", "Profile"),
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var selectedIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            selectedIndex = (selectedIndex + 1) % tabs.size
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            tabs.forEachIndexed { index, label ->
                val isSelected = index == selectedIndex
                val bgAlpha by animateFloatAsState(
                    targetValue = if (isSelected) 1f else 0f,
                    animationSpec = spring(stiffness = Spring.StiffnessLow),
                )
                val textAlpha by animateFloatAsState(
                    targetValue = if (isSelected) 1f else 0.6f,
                    animationSpec = tween(200),
                )
                Surface(
                    modifier = Modifier
                        .clickable { selectedIndex = index }
                        .graphicsLayer { alpha = 1f },
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = bgAlpha * 0.15f),
                ) {
                    Text(
                        text = label,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                            .graphicsLayer { alpha = textAlpha },
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}
// ============================================================
// 11. Flip Card (3D)
// ============================================================

@Composable
fun FlipCard(
    frontText: String = "Front",
    backText: String = "Back",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var flipped by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2500)
            flipped = !flipped
        }
    }

    val rotationY by animateFloatAsState(
        targetValue = if (flipped) 180f else 0f,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
    )

    val showFront = rotationY <= 90f

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In)
            .size(width = 140.dp, height = 80.dp)
            .clickable { flipped = !flipped }
            .graphicsLayer {
                this.rotationY = rotationY
                cameraDistance = 12f * density
            },
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (showFront) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.tertiaryContainer,
            border = BorderStroke(
                1.dp,
                if (showFront) MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                else MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f),
            ),
            modifier = Modifier.fillMaxSize().graphicsLayer {
                if (!showFront) this.rotationY = 180f
            },
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = if (showFront) "Front" else "Back",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (showFront) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
        }
    }
}

// ============================================================
// 12. Color Morph
// ============================================================

@Composable
fun ColorMorph(
    label: String = "Tap",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val colors = listOf(
        Color(0xFF9B8AFF),
        Color(0xFF64D8CB),
        Color(0xFFFF6B9D),
        Color(0xFFFFA726),
        Color(0xFF42A5F5),
    )
    var colorIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1500)
            colorIndex = (colorIndex + 1) % colors.size
        }
    }

    val animatedColor by animateColorAsState(
        targetValue = colors[colorIndex],
        animationSpec = tween(800),
    )

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In)
            .size(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(animatedColor)
            .clickable { colorIndex = (colorIndex + 1) % colors.size },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Tap",
            style = MaterialTheme.typography.labelMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}

// ============================================================
// 13. Progress Ring
// ============================================================

@Composable
fun ProgressRing(
    targetProgress: Float = 0.75f,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            progress = 0f
            delay(300)
            val steps = 100
            for (i in 1..steps) {
                progress = i.toFloat() / steps
                delay(25)
            }
            delay(1500)
        }
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(100),
    )

    Box(contentAlignment = Alignment.Center) {
        androidx.compose.foundation.Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).size(64.dp)) {
            val strokeWidth = 6.dp.toPx()
            // Background ring
            drawArc(
                color = Color.Gray.copy(alpha = 0.2f),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth),
            )
            // Progress ring
            drawArc(
                color = Color(0xFF9B8AFF),
                startAngle = -90f,
                sweepAngle = 360f * animatedProgress,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = strokeWidth,
                    cap = androidx.compose.ui.graphics.StrokeCap.Round,
                ),
            )
        }
        Text(
            text = "${(animatedProgress * 100).roundToInt()}%",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

// ============================================================
// 14. Hold to Confirm (press and hold progress)
// ============================================================

@Composable
fun HoldToConfirm(
    confirmText: String = "Hold to confirm",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var holdProgress by remember { mutableFloatStateOf(0f) }
    var confirmed by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            // Simulate a hold
            confirmed = false
            holdProgress = 0f
            delay(1000)
            for (i in 1..50) {
                holdProgress = i / 50f
                delay(40)
            }
            confirmed = true
            delay(2000)
        }
    }

    val animatedProgress by animateFloatAsState(
        targetValue = holdProgress,
        animationSpec = tween(80),
    )

    val bgColor by animateColorAsState(
        targetValue = if (confirmed) Color(0xFF4CAF50) else MaterialTheme.colorScheme.primary,
        animationSpec = tween(300),
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = bgColor.copy(alpha = 0.15f),
            border = BorderStroke(2.dp, bgColor),
            modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(width = 160.dp, height = 44.dp),
        ) {
            Box(contentAlignment = Alignment.CenterStart) {
                // Progress fill
                Box(
                    modifier = Modifier
                        .fillMaxWidth(animatedProgress)
                        .height(44.dp)
                        .background(
                            bgColor.copy(alpha = 0.2f),
                            RoundedCornerShape(24.dp),
                        ),
                )
                // Label
                Text(
                    text = if (confirmed) "✓ Confirmed" else "Hold to confirm",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = bgColor,
                )
            }
        }
    }
}

// ============================================================
// 15. Split Text Reveal
// ============================================================

@Composable
fun SplitTextReveal(
    text: String = "Compose Multiplatform Animations",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val words = text.split(" ")
    var visibleCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            visibleCount = 0
            delay(500)
            for (i in 1..words.size) {
                visibleCount = i
                delay(200)
            }
            delay(2500)
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).padding(horizontal = 8.dp),
    ) {
        words.forEachIndexed { index, word ->
            val visible = index < visibleCount
            val alpha by animateFloatAsState(
                targetValue = if (visible) 1f else 0f,
                animationSpec = tween(300),
            )
            val offsetY by animateFloatAsState(
                targetValue = if (visible) 0f else 20f,
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            )
            Text(
                text = word,
                modifier = Modifier
                    .graphicsLayer {
                        this.alpha = alpha
                        translationY = offsetY
                    },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

// ============================================================
// 16. Stagger From Center
// ============================================================

@Composable
fun StaggerFromCenter(itemCount: Int = 7,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var visible by remember { mutableStateOf(false) }
    val center = itemCount / 2

    LaunchedEffect(Unit) {
        while (true) {
            visible = true
            delay(3000)
            visible = false
            delay(800)
        }
    }

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),

        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(itemCount) { index ->
            val distFromCenter = kotlin.math.abs(index - center)
            var itemVisible by remember { mutableStateOf(false) }

            LaunchedEffect(visible) {
                if (visible) {
                    delay(distFromCenter * 100L)
                    itemVisible = true
                } else {
                    itemVisible = false
                }
            }

            val scale by animateFloatAsState(
                targetValue = if (itemVisible) 1f else 0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
            )
            val alpha by animateFloatAsState(
                targetValue = if (itemVisible) 1f else 0f,
                animationSpec = tween(200),
            )

            Box(
                modifier = Modifier
                    .size(width = 20.dp, height = 36.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.primary),
            )
        }
    }
}

// ============================================================
// 17. Ticker / Marquee
// ============================================================

@Composable
fun TickerMarquee(
    text: String = "  ★ Canimation  ★ Compose Multiplatform  ★ Animations  ★ Presets  ",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -1f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    val repeatedText = text.repeat(3)

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In)
            .fillMaxWidth()
            .height(32.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = repeatedText,
            modifier = Modifier.offset {
                val totalWidth = repeatedText.length * 7
                IntOffset((offset * totalWidth).roundToInt(), 0)
            },
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
            ),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
        )
    }
}

// ============================================================
// 18. Bouncy Spring List
// ============================================================

@Composable
fun BouncySpringList(
    items: List<String> = listOf("Inbox", "Starred", "Sent", "Drafts", "Trash"),
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            visible = true
            delay(3500)
            visible = false
            delay(800)
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).fillMaxWidth().padding(horizontal = 8.dp),
    ) {
        items.forEachIndexed { index, label ->
            var itemVisible by remember { mutableStateOf(false) }

            LaunchedEffect(visible) {
                if (visible) {
                    delay(index * 80L)
                    itemVisible = true
                } else {
                    itemVisible = false
                }
            }

            val offsetX by animateFloatAsState(
                targetValue = if (itemVisible) 0f else -200f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow,
                ),
            )
            val alpha by animateFloatAsState(
                targetValue = if (itemVisible) 1f else 0f,
                animationSpec = tween(200),
            )

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        translationX = offsetX
                        this.alpha = alpha
                    },
            ) {
                Text(
                    text = "  $label",
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}
// ============================================================
// 21. Swipe Actions
// ============================================================

@Composable
fun SwipeActions(
    label: String = "Swipe me →",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var phase by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            // Simulate swipe right
            phase = 1
            delay(1500)
            phase = 2 // reveal
            delay(2000)
            phase = 0 // reset
            delay(800)
        }
    }

    val offsetX by animateFloatAsState(
        targetValue = when (phase) {
            1 -> 80f
            2 -> 80f
            else -> 0f
        },
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
    )
    val actionAlpha by animateFloatAsState(
        targetValue = if (phase > 0) 1f else 0f,
        animationSpec = tween(200),
    )

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).fillMaxWidth().height(48.dp).padding(horizontal = 8.dp),
    ) {
        // Action behind
        Box(
            modifier = Modifier
                .height(48.dp)
                .width(76.dp)
                .graphicsLayer { alpha = actionAlpha }
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF4CAF50)),
            contentAlignment = Alignment.Center,
        ) {
            Text("✓ Done", style = MaterialTheme.typography.labelSmall, color = Color.White, fontWeight = FontWeight.Bold)
        }
        // Item on top
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .graphicsLayer { translationX = offsetX },
        ) {
            Box(Modifier.fillMaxSize().padding(horizontal = 12.dp), contentAlignment = Alignment.CenterStart) {
                Text("Swipe me →", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

// ============================================================
// 22. Tilt Card (3D perspective on hover simulation)
// ============================================================

@Composable
fun TiltCard(
    title: String = "3D Tilt",
    subtitle: String = "Perspective",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val infiniteTransition = rememberInfiniteTransition()
    val rotX by infiniteTransition.animateFloat(
        initialValue = -8f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )
    val rotY by infiniteTransition.animateFloat(
        initialValue = 8f,
        targetValue = -8f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In)
            .size(width = 140.dp, height = 90.dp)
            .graphicsLayer {
                rotationX = rotX
                rotationY = rotY
                cameraDistance = 12f * density
            },
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "3D Tilt",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Text(
                    text = "Perspective",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                )
            }
        }
    }
}

// ============================================================
// 23. Price Switcher (animated value change)
// ============================================================

@Composable
fun PriceSwitcher(
    monthlyPrice: String = "$9.99",
    yearlyPrice: String = "$99.99",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var isMonthly by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2500)
            isMonthly = !isMonthly
        }
    }

    val animatable = remember { Animatable(9.99f) }
    LaunchedEffect(isMonthly) {
        animatable.animateTo(
            targetValue = if (isMonthly) 9.99f else 99.99f,
            animationSpec = spring(stiffness = Spring.StiffnessLow),
        )
    }

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In), horizontalAlignment = Alignment.CenterHorizontally) {
        // Toggle label
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "Monthly",
                style = MaterialTheme.typography.labelSmall,
                color = if (isMonthly) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = if (isMonthly) FontWeight.Bold else FontWeight.Normal,
            )
            Text(
                "Yearly",
                style = MaterialTheme.typography.labelSmall,
                color = if (!isMonthly) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = if (!isMonthly) FontWeight.Bold else FontWeight.Normal,
            )
        }
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.Top) {
            Text(
                "$",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = animatable.value.let { v ->
                    val whole = v.toInt()
                    val frac = ((v - whole) * 100).roundToInt()
                    "$whole.${frac.toString().padStart(2, '0')}"
                },
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                ),
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Text(
            text = if (isMonthly) "/month" else "/year — save 17%",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

// ============================================================
// 24. Engagement Stats (staggered counters)
// ============================================================

@Composable
fun EngagementStats(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var phase by remember { mutableIntStateOf(0) }
    val stats = listOf("Views" to 12847, "Likes" to 4231, "Shares" to 892)

    LaunchedEffect(Unit) {
        while (true) {
            phase++
            delay(4000)
        }
    }

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),

        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        stats.forEachIndexed { index, (label, target) ->
            var current by remember { mutableIntStateOf(0) }
            LaunchedEffect(phase) {
                current = 0
                delay(index * 200L)
                val steps = 40
                for (i in 1..steps) {
                    val p = FastOutSlowInEasing.transform(i.toFloat() / steps)
                    current = (target * p).roundToInt()
                    delay(30)
                }
                current = target
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (current >= 1000) {
                        val k = current / 1000f
                        "${k.toInt()}.${((k - k.toInt()) * 10).roundToInt()}K"
                    } else current.toString(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                    ),
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

// ============================================================
// 25. Multi-state Badge
// ============================================================

@Composable
fun MultiStateBadge(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val states = listOf(
        "New" to Color(0xFF42A5F5),
        "Processing" to Color(0xFFFFA726),
        "Complete" to Color(0xFF66BB6A),
        "Archived" to Color(0xFF78909C),
    )
    var stateIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1800)
            stateIndex = (stateIndex + 1) % states.size
        }
    }

    val (label, color) = states[stateIndex]
    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = tween(400),
    )
    val animatedScale = remember { Animatable(1f) }
    LaunchedEffect(stateIndex) {
        animatedScale.animateTo(1.2f, spring(stiffness = Spring.StiffnessHigh))
        animatedScale.animateTo(1f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
    }

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = animatedColor.copy(alpha = 0.15f),
        border = BorderStroke(1.5.dp, animatedColor),
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).graphicsLayer {
            scaleX = animatedScale.value
            scaleY = animatedScale.value
        },
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = animatedColor,
        )
    }
}

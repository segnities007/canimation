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


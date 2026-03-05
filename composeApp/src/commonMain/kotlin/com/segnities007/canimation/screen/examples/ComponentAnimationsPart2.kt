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


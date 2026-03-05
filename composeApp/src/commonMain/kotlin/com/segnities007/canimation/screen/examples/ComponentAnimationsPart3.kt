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

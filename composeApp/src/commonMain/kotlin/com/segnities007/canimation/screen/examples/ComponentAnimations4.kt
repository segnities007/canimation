package com.segnities007.canimation.screen.examples

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOutCubic
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

// ─── 1. MegaMenuReveal ──────────────────────────────────────────────

/** Navigation menu that reveals items with staggered slide+fade from left */

@Composable
fun MegaMenuReveal(
    items: List<String> = listOf("Home", "Products", "About", "Blog", "Contact"),
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            visible = true
            delay(2500)
            visible = false
            delay(800)
        }
    }

    val offsets = items.indices.map { i ->
        val anim = animateFloatAsState(
            targetValue = if (visible) 0f else -60f,
            animationSpec = tween(400, delayMillis = i * 80, easing = FastOutSlowInEasing),
        )
        anim
    }
    val alphas = items.indices.map { i ->
        val anim = animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = tween(400, delayMillis = i * 80),
        )
        anim
    }

    Surface(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).width(200.dp).height(180.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items.forEachIndexed { i, item ->
                Text(
                    text = item,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .graphicsLayer {
                            translationX = offsets[i].value
                            alpha = alphas[i].value
                        }
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                )
            }
        }
    }
}

// ─── 2. SmoothTabIndicator ───────────────────────────────────────────

/** Horizontal tabs with a smooth sliding underline indicator */
@Composable
fun SmoothTabIndicator(
    tabs: List<String> = listOf("All", "Recent", "Popular"),
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var selected by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1200)
            selected = (selected + 1) % tabs.size
        }
    }

    val indicatorOffset by animateFloatAsState(
        targetValue = selected.toFloat(),
        animationSpec = spring(dampingRatio = 0.7f, stiffness = Spring.StiffnessLow),
    )

    val primary = MaterialTheme.colorScheme.primary

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(260.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            tabs.forEachIndexed { i, tab ->
                Box(
                    modifier = Modifier.weight(1f).padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = tab,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = if (i == selected) FontWeight.Bold else FontWeight.Normal,
                        color = if (i == selected) primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    )
                }
            }
        }
        Canvas(modifier = Modifier.fillMaxWidth().height(3.dp)) {
            val x = indicatorOffset * (size.width / tabs.size)
            drawRoundRect(
                color = primary,
                topLeft = Offset(x, 0f),
                size = Size(size.width / tabs.size, size.height),
                cornerRadius = CornerRadius(4f, 4f),
            )
        }
    }
}

// ─── 3. NumberCounter ────────────────────────────────────────────────

/** Large number that counts up from 0 to target with easing */
// ─── 4. RevealTextEffect ────────────────────────────────────────────

/** Text that reveals character by character with a moving highlight bar */
@Composable
fun RevealTextEffect(
    text: String = "Reveal Animation",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var count by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            for (i in 0..text.length) { count = i; delay(70) }
            delay(1500)
            count = 0
            delay(400)
        }
    }

    val primary = MaterialTheme.colorScheme.primary

    Row(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In), verticalAlignment = Alignment.CenterVertically) {
        text.forEachIndexed { i, c ->
            val visible = i < count
            val isHighlight = i == count - 1
            Text(
                text = c.toString(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (!visible) Color.Transparent
                else if (isHighlight) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .then(
                        if (isHighlight) Modifier.background(primary, RoundedCornerShape(2.dp))
                        else Modifier
                    ),
            )
        }
    }
}

// ─── 5. ScatterText ─────────────────────────────────────────────────

/** Text where characters scatter/explode outward then reassemble */
@Composable
fun ScatterText(
    text: String = "SCATTER",
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var scattered by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            scattered = true; delay(1500)
            scattered = false; delay(1500)
        }
    }

    val offsets = text.indices.map { i ->
        val angle = i * (360f / text.length)
        val rad = angle * PI.toFloat() / 180f
        val tx by animateFloatAsState(
            if (scattered) cos(rad) * 40f else 0f,
            spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessMediumLow),
        )
        val ty by animateFloatAsState(
            if (scattered) sin(rad) * 40f else 0f,
            spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessMediumLow),
        )
        val a by animateFloatAsState(
            if (scattered) 0.3f else 1f,
            tween(400),
        )
        Triple(tx, ty, a)
    }

    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).size(200.dp, 60.dp), contentAlignment = Alignment.Center) {
        Row {
            text.forEachIndexed { i, c ->
                val (tx, ty, a) = offsets[i]
                Text(
                    text = c.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.graphicsLayer {
                        translationX = tx
                        translationY = ty
                        alpha = a
                    },
                )
            }
        }
    }
}

// ─── 6. InfiniteLoadingList ─────────────────────────────────────────

/** Simulated infinite scroll with items fading in from bottom */
@Composable
fun InfiniteLoadingList(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var batch by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            batch = (batch + 1) % 3
        }
    }

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).width(220.dp).height(180.dp).padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        for (i in 0 until 4) {
            val itemAlpha = remember(batch) { Animatable(0f) }
            val itemOffset = remember(batch) { Animatable(30f) }
            LaunchedEffect(batch) {
                delay(i * 120L)
                itemAlpha.animateTo(1f, tween(400))
            }
            LaunchedEffect(batch) {
                delay(i * 120L)
                itemOffset.animateTo(0f, tween(400, easing = FastOutSlowInEasing))
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp)
                    .graphicsLayer {
                        alpha = itemAlpha.value
                        translationY = itemOffset.value
                    },
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.secondaryContainer,
            ) {
                Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.padding(horizontal = 12.dp)) {
                    Text(
                        text = "Item ${batch * 4 + i + 1}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }
            }
        }
    }
}

// ─── 7. CardStackSwipe ──────────────────────────────────────────────

/** Stack of cards that auto-swipe away one by one (Tinder-style) */
@Composable
fun CardStackSwipe(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val labels = listOf("Card A", "Card B", "Card C", "Card D")
    var topIndex by remember { mutableIntStateOf(0) }
    val swipeX = remember { Animatable(0f) }
    val swipeRotation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            swipeX.animateTo(350f, tween(500, easing = FastOutSlowInEasing))
            swipeRotation.animateTo(15f, tween(500))
            topIndex = (topIndex + 1) % labels.size
            swipeX.snapTo(0f)
            swipeRotation.snapTo(0f)
        }
    }

    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(180.dp, 120.dp), contentAlignment = Alignment.Center) {
        for (i in (labels.indices).reversed()) {
            val rel = (i - topIndex + labels.size) % labels.size
            if (rel > 2) continue
            val scale = 1f - rel * 0.06f
            val yOff = rel * 8f
            Surface(
                modifier = Modifier
                    .size(160.dp, 90.dp)
                    .graphicsLayer {
                        scaleX = scale; scaleY = scale
                        translationY = yOff
                        if (rel == 0) {
                            translationX = swipeX.value
                            rotationZ = swipeRotation.value
                        }
                    },
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                shadowElevation = (4 - rel).coerceAtLeast(1).dp,
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = labels[i],
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }
}

// ─── 8. HorizontalScrollGallery ─────────────────────────────────────

/** Thumbnails that scroll horizontally with parallax offset */
@Composable
fun HorizontalScrollGallery(
    itemCount: Int = 8,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val scroll by inf.animateFloat(
        0f, 400f,
        infiniteRepeatable(tween(4000, easing = LinearEasing), RepeatMode.Restart),
    )

    val colors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
    )

    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(260.dp).height(80.dp).clip(RoundedCornerShape(12.dp))) {
        Row {
            colors.forEachIndexed { i, color ->
                val parallax = (scroll * (0.5f + i * 0.15f)) % 400f
                Box(
                    modifier = Modifier
                        .size(70.dp, 70.dp)
                        .padding(4.dp)
                        .graphicsLayer { translationX = -scroll + parallax }
                        .clip(RoundedCornerShape(8.dp))
                        .background(color),
                )
            }
        }
    }
}

// ─── 9. IOSSlider ───────────────────────────────────────────────────

/** iOS-style slider with animated track fill and haptic-style knob */

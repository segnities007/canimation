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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

// ─── 1. MegaMenuReveal ──────────────────────────────────────────────

/** Navigation menu that reveals items with staggered slide+fade from left */
@Composable
fun MegaMenuReveal() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val items = listOf("Home", "Products", "About", "Blog", "Contact")
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
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).width(200.dp).height(180.dp),
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
fun SmoothTabIndicator() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val tabs = listOf("All", "Recent", "Popular")
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

    Column(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(260.dp)) {
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
        val tabWidth = 260f / tabs.size
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
@Composable
fun NumberCounter() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val target = 8742
    val anim = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            anim.snapTo(0f)
            anim.animateTo(target.toFloat(), tween(2000, easing = FastOutSlowInEasing))
            delay(1500)
        }
    }

    Box(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).size(140.dp, 80.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "${anim.value.roundToInt()}",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

// ─── 4. RevealTextEffect ────────────────────────────────────────────

/** Text that reveals character by character with a moving highlight bar */
@Composable
fun RevealTextEffect() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val text = "Reveal Animation"
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

    Row(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In), verticalAlignment = Alignment.CenterVertically) {
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
fun ScatterText() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val text = "SCATTER"
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

    Box(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).size(200.dp, 60.dp), contentAlignment = Alignment.Center) {
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
fun InfiniteLoadingList() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var batch by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            batch = (batch + 1) % 3
        }
    }

    val items = (0 until 4).map { i ->
        val idx = batch * 4 + i
        val alpha by animateFloatAsState(1f, tween(500, delayMillis = i * 120))
        val offsetY by animateFloatAsState(0f, tween(500, delayMillis = i * 120))
        Triple(idx, alpha, offsetY)
    }

    // Re-trigger via key
    var trigger by remember { mutableStateOf(false) }
    LaunchedEffect(batch) { trigger = !trigger }

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).width(220.dp).height(180.dp).padding(8.dp),
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
fun CardStackSwipe() {
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

    Box(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(180.dp, 120.dp), contentAlignment = Alignment.Center) {
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
fun HorizontalScrollGallery() {
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

    Box(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(260.dp).height(80.dp).clip(RoundedCornerShape(12.dp))) {
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
@Composable
fun IOSSlider() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val anim = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            anim.animateTo(1f, tween(2000, easing = EaseInOutCubic))
            delay(400)
            anim.animateTo(0f, tween(2000, easing = EaseInOutCubic))
            delay(400)
        }
    }

    val primary = MaterialTheme.colorScheme.primary
    val trackColor = MaterialTheme.colorScheme.surfaceVariant

    Box(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).width(240.dp).height(40.dp), contentAlignment = Alignment.CenterStart) {
        Canvas(modifier = Modifier.fillMaxWidth().height(6.dp)) {
            drawRoundRect(trackColor, cornerRadius = CornerRadius(3f, 3f))
            drawRoundRect(
                primary,
                size = Size(size.width * anim.value, size.height),
                cornerRadius = CornerRadius(3f, 3f),
            )
        }
        Box(
            modifier = Modifier
                .offset { IntOffset((anim.value * 210).dp.roundToPx(), 0) }
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.White)
                .drawBehind {
                    drawCircle(primary.copy(alpha = 0.3f), radius = size.width / 2 + 4.dp.toPx())
                },
        )
    }
}

// ─── 10. CheckboxAnimation ──────────────────────────────────────────

/** Checkbox that draws a checkmark path when toggled */
@Composable
fun CheckboxAnimation() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var checked by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            checked = true; delay(1500)
            checked = false; delay(1000)
        }
    }

    val progress by animateFloatAsState(
        if (checked) 1f else 0f,
        tween(400, easing = FastOutSlowInEasing),
    )
    val bgColor by animateColorAsState(
        if (checked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        tween(300),
    )

    Canvas(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(48.dp)) {
        drawRoundRect(bgColor, cornerRadius = CornerRadius(8.dp.toPx()))
        if (progress > 0f) {
            val path = Path().apply {
                moveTo(size.width * 0.25f, size.height * 0.52f)
                lineTo(size.width * 0.42f, size.height * 0.68f)
                lineTo(size.width * 0.75f, size.height * 0.32f)
            }
            val totalLen = size.width * 0.7f
            drawPath(
                path,
                color = Color.White,
                style = Stroke(
                    width = 3.dp.toPx(),
                    cap = StrokeCap.Round,
                    pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(
                        floatArrayOf(totalLen * progress, totalLen),
                        0f,
                    ),
                ),
            )
        }
    }
}

// ─── 11. SwitchAnimation ────────────────────────────────────────────

/** Material switch with bouncy thumb animation */
@Composable
fun SwitchAnimation() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var on by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            on = !on; delay(1400)
        }
    }

    val thumbOffset by animateFloatAsState(
        if (on) 24f else 0f,
        spring(dampingRatio = 0.5f, stiffness = Spring.StiffnessMedium),
    )
    val trackColor by animateColorAsState(
        if (on) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        tween(300),
    )
    val thumbScale by animateFloatAsState(
        if (on) 1.1f else 1f,
        spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium),
    )

    Box(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In)
            .width(52.dp).height(28.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(trackColor)
            .padding(2.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(thumbOffset.dp.roundToPx(), 0) }
                .size(24.dp)
                .graphicsLayer { scaleX = thumbScale; scaleY = thumbScale }
                .clip(CircleShape)
                .background(Color.White),
        )
    }
}

// ─── 12. ToastNotification ──────────────────────────────────────────

/** Toast that slides in from top, stays, slides out */
@Composable
fun ToastNotification() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var show by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            show = true; delay(2500)
            show = false; delay(1000)
        }
    }

    val offsetY by animateFloatAsState(
        if (show) 0f else -80f,
        spring(dampingRatio = 0.7f, stiffness = Spring.StiffnessMediumLow),
    )
    val alpha by animateFloatAsState(
        if (show) 1f else 0f,
        tween(300),
    )

    Box(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(260.dp).height(60.dp), contentAlignment = Alignment.TopCenter) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = offsetY
                    this.alpha = alpha
                },
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.inverseSurface,
            shadowElevation = 4.dp,
        ) {
            Box(modifier = Modifier.padding(12.dp), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = "\u2713 Action completed",
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

// ─── 13. AccordionMenu ──────────────────────────────────────────────

/** Expandable menu items that open/close with spring animation */
@Composable
fun AccordionMenu() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val items = listOf("Settings", "Profile", "Help")
    var expandedIndex by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        while (true) {
            for (i in items.indices) {
                expandedIndex = i; delay(1200)
            }
            expandedIndex = -1; delay(800)
        }
    }

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).width(220.dp).padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items.forEachIndexed { i, item ->
            val expanded = i == expandedIndex
            val height by animateFloatAsState(
                if (expanded) 60f else 36f,
                spring(dampingRatio = 0.7f, stiffness = Spring.StiffnessMediumLow),
            )
            Surface(
                modifier = Modifier.fillMaxWidth().height(height.dp),
                shape = RoundedCornerShape(8.dp),
                color = if (expanded) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surfaceVariant,
            ) {
                Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = if (expanded) MaterialTheme.colorScheme.onPrimaryContainer
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    if (expanded) {
                        Text(
                            text = "Sub-content here",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                        )
                    }
                }
            }
        }
    }
}

// ─── 14. MagneticButton ─────────────────────────────────────────────

/** Button that subtly moves toward a simulated touch position */
@Composable
fun MagneticButton() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val magnetX by inf.animateFloat(
        -8f, 8f,
        infiniteRepeatable(tween(2000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
    )
    val magnetY by inf.animateFloat(
        -4f, 4f,
        infiniteRepeatable(tween(1500, easing = FastOutSlowInEasing), RepeatMode.Reverse),
    )

    Box(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(180.dp, 60.dp), contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier
                .graphicsLayer {
                    translationX = magnetX
                    translationY = magnetY
                },
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.primary,
            shadowElevation = 6.dp,
        ) {
            Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp), contentAlignment = Alignment.Center) {
                Text(
                    text = "Hover Me",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

// ─── 15. RippleButton ───────────────────────────────────────────────

/** Material ripple effect that radiates from center */
@Composable
fun RippleButton() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val rippleProgress by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(1200, easing = FastOutSlowInEasing), RepeatMode.Restart),
    )

    val primary = MaterialTheme.colorScheme.primary

    Box(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).size(160.dp, 56.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val maxRadius = size.width / 2
            drawRoundRect(
                primary,
                cornerRadius = CornerRadius(12.dp.toPx()),
            )
            drawCircle(
                Color.White.copy(alpha = (1f - rippleProgress) * 0.4f),
                radius = maxRadius * rippleProgress,
                center = center,
            )
        }
        Text(
            text = "Press",
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}

// ─── 16. FloatingParticles ──────────────────────────────────────────

/** Small particles that float upward like embers */
@Composable
fun FloatingParticles() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    data class Particle(val x: Float, val speed: Float, val size: Float, val delay: Int)

    val particles = remember {
        List(12) {
            Particle(
                x = (it * 23f) % 260f,
                speed = 1500f + (it * 300f) % 1500f,
                size = 3f + (it * 1.3f) % 4f,
                delay = (it * 200) % 1200,
            )
        }
    }

    val inf = rememberInfiniteTransition()
    val time by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart),
    )

    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.tertiary

    Canvas(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).width(260.dp).height(160.dp)) {
        particles.forEach { p ->
            val prog = ((time + p.delay / 3000f) % 1f)
            val y = size.height * (1f - prog)
            val alpha = if (prog < 0.2f) prog * 5f else if (prog > 0.8f) (1f - prog) * 5f else 1f
            val drift = sin(prog * PI.toFloat() * 2f) * 10f
            val color = if (p.size > 5f) primary else secondary
            drawCircle(
                color.copy(alpha = alpha * 0.8f),
                radius = p.size,
                center = Offset(p.x + drift, y),
            )
        }
    }
}

// ─── 17. ScrollDirectionHeader ──────────────────────────────────────

/** Header that slides up/hides when scrolling down, reveals on up */
@Composable
fun ScrollDirectionHeader() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var hidden by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            hidden = true; delay(1500)
            hidden = false; delay(1500)
        }
    }

    val offsetY by animateFloatAsState(
        if (hidden) -50f else 0f,
        spring(dampingRatio = 0.8f, stiffness = Spring.StiffnessMediumLow),
    )
    val alpha by animateFloatAsState(
        if (hidden) 0f else 1f,
        tween(300),
    )

    Column(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(260.dp).height(80.dp)) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .graphicsLayer {
                    translationY = offsetY
                    this.alpha = alpha
                },
            color = MaterialTheme.colorScheme.primary,
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "App Header",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f).background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = if (hidden) "\u2193 Scrolling down" else "\u2191 Scrolling up",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

// ─── 18. TextLineReveal ─────────────────────────────────────────────

/** Multi-line text where each line slides in from right with stagger */
@Composable
fun TextLineReveal() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val lines = listOf("Design is", "not just what", "it looks like.", "It's how it works.")
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            visible = true; delay(3000)
            visible = false; delay(800)
        }
    }

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).width(220.dp).padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        lines.forEachIndexed { i, line ->
            val offsetX by animateFloatAsState(
                if (visible) 0f else 80f,
                tween(500, delayMillis = i * 150, easing = FastOutSlowInEasing),
            )
            val alpha by animateFloatAsState(
                if (visible) 1f else 0f,
                tween(500, delayMillis = i * 150),
            )
            Text(
                text = line,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.graphicsLayer {
                    translationX = offsetX
                    this.alpha = alpha
                },
            )
        }
    }
}

// ─── 19. ZoomHeroImage ──────────────────────────────────────────────

/** Image placeholder that zooms from thumbnail to hero size */
@Composable
fun ZoomHeroImage() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            expanded = true; delay(2000)
            expanded = false; delay(1200)
        }
    }

    val width by animateFloatAsState(
        if (expanded) 260f else 80f,
        spring(dampingRatio = 0.7f, stiffness = Spring.StiffnessMediumLow),
    )
    val height by animateFloatAsState(
        if (expanded) 160f else 80f,
        spring(dampingRatio = 0.7f, stiffness = Spring.StiffnessMediumLow),
    )
    val cornerRadius by animateFloatAsState(
        if (expanded) 16f else 40f,
        tween(400),
    )

    Box(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).size(260.dp, 160.dp), contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier.size(width.dp, height.dp),
            shape = RoundedCornerShape(cornerRadius.dp),
            color = MaterialTheme.colorScheme.tertiaryContainer,
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(
                    text = if (expanded) "Hero View" else "\uD83D\uDDBC",
                    style = if (expanded) MaterialTheme.typography.titleMedium
                    else MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
        }
    }
}

// ─── 20. ProgressScrubber ───────────────────────────────────────────

/** Horizontal progress bar with draggable scrubber thumb */
@Composable
fun ProgressScrubber() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val anim = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            anim.animateTo(1f, tween(3000, easing = LinearEasing))
            delay(500)
            anim.snapTo(0f)
            delay(300)
        }
    }

    val primary = MaterialTheme.colorScheme.primary
    val track = MaterialTheme.colorScheme.surfaceVariant
    val elapsed = (anim.value * 180).roundToInt()

    Column(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).width(260.dp).padding(8.dp)) {
        Box(modifier = Modifier.fillMaxWidth().height(32.dp), contentAlignment = Alignment.CenterStart) {
            Canvas(modifier = Modifier.fillMaxWidth().height(4.dp)) {
                drawRoundRect(track, cornerRadius = CornerRadius(2f))
                drawRoundRect(primary, size = Size(size.width * anim.value, size.height), cornerRadius = CornerRadius(2f))
            }
            Box(
                modifier = Modifier
                    .offset { IntOffset((anim.value * (260 - 16)).dp.roundToPx(), 0) }
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(primary),
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("${elapsed / 60}:${(elapsed % 60).let { if (it < 10) "0$it" else "$it" }}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface)
            Text("3:00", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
        }
    }
}

// ─── 21. VerticalCarousel ───────────────────────────────────────────

/** Items that cycle vertically with 3D rotation perspective */
@Composable
fun VerticalCarousel() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val items = listOf("Slide 1", "Slide 2", "Slide 3", "Slide 4")
    var current by remember { mutableIntStateOf(0) }
    val offset = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1200)
            offset.animateTo(-1f, tween(600, easing = FastOutSlowInEasing))
            current = (current + 1) % items.size
            offset.snapTo(0f)
        }
    }

    Box(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(200.dp).height(80.dp).clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center,
    ) {
        for (i in -1..1) {
            val idx = (current + i + items.size) % items.size
            val yPos = (i + offset.value) * 70f
            val rotX = (i + offset.value) * 30f
            val alphaVal = 1f - abs(i + offset.value) * 0.5f

            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(50.dp)
                    .graphicsLayer {
                        translationY = yPos
                        rotationX = rotX
                        alpha = alphaVal.coerceIn(0f, 1f)
                    },
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.secondaryContainer,
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = items[idx],
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
    }
}

// ─── 22. WaterfallGrid ──────────────────────────────────────────────

/** Items that fall into place in a staggered waterfall pattern */
@Composable
fun WaterfallGrid() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var show by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            show = true; delay(2500)
            show = false; delay(800)
        }
    }

    val cols = 3
    val rows = 2
    val colors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
    )

    Column(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).width(200.dp).padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        for (r in 0 until rows) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                for (c in 0 until cols) {
                    val idx = r * cols + c
                    val stagger = idx * 100
                    val offsetY by animateFloatAsState(
                        if (show) 0f else -80f,
                        tween(500, delayMillis = stagger, easing = FastOutSlowInEasing),
                    )
                    val alpha by animateFloatAsState(
                        if (show) 1f else 0f,
                        tween(500, delayMillis = stagger),
                    )
                    val itemHeight = if (r == 0 && c == 1) 70 else 50
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(itemHeight.dp)
                            .graphicsLayer {
                                translationY = offsetY
                                this.alpha = alpha
                            }
                            .clip(RoundedCornerShape(8.dp))
                            .background(colors[idx % colors.size]),
                    )
                }
            }
        }
    }
}

// ─── 23. PulsingAvatar ──────────────────────────────────────────────

/** Avatar circle with pulsing ring around it (like a "live" indicator) */
@Composable
fun PulsingAvatar() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val pulse by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(1500, easing = FastOutSlowInEasing), RepeatMode.Restart),
    )
    val pulse2 by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(
            tween(1500, delayMillis = 500, easing = FastOutSlowInEasing),
            RepeatMode.Restart,
        ),
    )

    val primary = MaterialTheme.colorScheme.primary

    Box(modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).size(100.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            listOf(pulse, pulse2).forEach { p ->
                drawCircle(
                    primary.copy(alpha = (1f - p) * 0.5f),
                    radius = 24.dp.toPx() + p * 20.dp.toPx(),
                    style = Stroke(width = 2.dp.toPx()),
                )
            }
        }
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(primary),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "JD",
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

// ─── 24. SegmentedControl ───────────────────────────────────────────

/** iOS-style segmented control with sliding selection indicator */
@Composable
fun SegmentedControl() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val segments = listOf("Day", "Week", "Month")
    var selected by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1200)
            selected = (selected + 1) % segments.size
        }
    }

    val indicatorOffset by animateFloatAsState(
        selected.toFloat(),
        spring(dampingRatio = 0.65f, stiffness = Spring.StiffnessMediumLow),
    )

    Box(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .width(240.dp)
            .height(36.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(2.dp),
    ) {
        val segmentWidth = (240f - 4f) / segments.size
        Box(
            modifier = Modifier
                .offset { IntOffset((indicatorOffset * segmentWidth).dp.roundToPx(), 0) }
                .width(segmentWidth.dp)
                .height(32.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.surface),
        )
        Row(modifier = Modifier.fillMaxSize()) {
            segments.forEachIndexed { i, label ->
                Box(
                    modifier = Modifier.weight(1f).fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (i == selected) FontWeight.Bold else FontWeight.Normal,
                        color = if (i == selected) MaterialTheme.colorScheme.onSurface
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

// ─── 25. ElasticDrawer ──────────────────────────────────────────────

/** Side drawer that opens with elastic overshoot spring */
@Composable
fun ElasticDrawer() {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var open by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            open = true; delay(2000)
            open = false; delay(1200)
        }
    }

    val drawerX by animateFloatAsState(
        if (open) 0f else -170f,
        spring(dampingRatio = 0.5f, stiffness = Spring.StiffnessMediumLow),
    )
    val scrimAlpha by animateFloatAsState(
        if (open) 0.3f else 0f,
        tween(300),
    )

    Box(
        modifier = Modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).width(260.dp).height(180.dp).clip(RoundedCornerShape(12.dp)),
    ) {
        // Main content
        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Main Content",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            )
        }
        // Scrim
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = scrimAlpha)),
        )
        // Drawer
        Surface(
            modifier = Modifier
                .width(160.dp)
                .fillMaxSize()
                .graphicsLayer { translationX = drawerX },
            color = MaterialTheme.colorScheme.primaryContainer,
            shadowElevation = 8.dp,
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Menu",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                listOf("Home", "Settings", "About").forEach { item ->
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                    )
                }
            }
        }
    }
}

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
fun TextLineReveal(
    lines: List<String> = listOf("Design is", "not just what", "it looks like.", "It's how it works."),
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            visible = true; delay(3000)
            visible = false; delay(800)
        }
    }

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).width(220.dp).padding(8.dp),
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
fun ZoomHeroImage(
    modifier: Modifier = Modifier,
) {
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

    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).size(260.dp, 160.dp), contentAlignment = Alignment.Center) {
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
fun ProgressScrubber(
    modifier: Modifier = Modifier,
) {
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

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).width(260.dp).padding(8.dp)) {
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
fun VerticalCarousel(
    items: List<String> = listOf("Slide 1", "Slide 2", "Slide 3", "Slide 4"),
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

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
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(200.dp).height(80.dp).clip(RoundedCornerShape(12.dp)),
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
fun WaterfallGrid(
    itemCount: Int = 8,
    modifier: Modifier = Modifier,
) {
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
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).width(200.dp).padding(4.dp),
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
fun PulsingAvatar(
    initials: String = "JD",
    modifier: Modifier = Modifier,
) {
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

    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).size(100.dp), contentAlignment = Alignment.Center) {
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
// ─── 25. ElasticDrawer ──────────────────────────────────────────────

/** Side drawer that opens with elastic overshoot spring */
@Composable
fun ElasticDrawer(
    modifier: Modifier = Modifier,
) {
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
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).width(260.dp).height(180.dp).clip(RoundedCornerShape(12.dp)),
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

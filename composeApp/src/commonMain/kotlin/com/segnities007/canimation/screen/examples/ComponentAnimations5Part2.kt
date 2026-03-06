package com.segnities007.canimation.screen.examples

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import canimation.composeapp.generated.resources.*
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// ─── 1. WaveformVisualizer ──────────────────────────────────────────


@Composable
fun ExpandableChip(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(1800); expanded = !expanded } }
    val width by animateFloatAsState(if (expanded) 200f else 80f, tween(300))

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Micro.Pulse),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.demo_expandable_chip), style = MaterialTheme.typography.titleSmall)
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
                    stringResource(
                        if (expanded) Res.string.component_kotlin_multiplatform
                        else Res.string.component_kmp,
                    ),
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                )
            }
        }
    }
}

// ─── 10. StackedNotifications ───────────────────────────────────────

@Composable
fun StackedNotifications(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val items = listOf(
        stringResource(Res.string.component_notification_new_message),
        stringResource(Res.string.component_notification_build_passed),
        stringResource(Res.string.component_notification_review_requested),
    )

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Swipe.Right),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.component_stacked_notifications), style = MaterialTheme.typography.titleSmall)
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
// ─── 12. CircularRevealCard ─────────────────────────────────────────

@Composable
fun CircularRevealCard(
    modifier: Modifier = Modifier,
) {
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
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Reveal.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.component_circular_reveal), style = MaterialTheme.typography.titleSmall)
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
            Text(stringResource(Res.string.component_reveal), style = MaterialTheme.typography.bodyMedium)
        }
    }
}
// ─── 15. SwipeCard ──────────────────────────────────────────────────

@Composable
fun SwipeCard(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var dismissed by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(2000); dismissed = true; delay(1000); dismissed = false } }
    val offsetX by animateFloatAsState(if (dismissed) 300f else 0f, tween(400))
    val cardAlpha by animateFloatAsState(if (dismissed) 0f else 1f, tween(400))

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Swipe.Left),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.component_swipe_card), style = MaterialTheme.typography.titleSmall)
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
                Text(stringResource(Res.string.component_tap_to_swipe), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

// ─── 16. AnimatedCheckmark ──────────────────────────────────────────

@Composable
fun AnimatedCheckmark(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            progress.animateTo(1f, tween(800, easing = FastOutSlowInEasing))
            delay(1200)
            progress.animateTo(0f, tween(400))
            delay(300)
        }
    }
    val primary = MaterialTheme.colorScheme.primary

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Reveal.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.component_animated_checkmark), style = MaterialTheme.typography.titleSmall)
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

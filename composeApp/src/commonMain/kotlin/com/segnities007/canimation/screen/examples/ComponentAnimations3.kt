package com.segnities007.canimation.screen.examples

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
import androidx.compose.ui.geometry.Offset
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
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import canimation.composeapp.generated.resources.*
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

// ===== CARD ANIMATIONS =====

/** Card with animated border that traces around the edges */

@Composable
fun CardBorderTrace(
    cardWidth: Int = 160,
    cardHeight: Int = 100,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val progress by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary
    val outline = MaterialTheme.colorScheme.outline

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).size(160.dp, 100.dp)) {
        val w = size.width
        val h = size.height
        val r = 12.dp.toPx()
        drawRoundRect(outline.copy(alpha = 0.2f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(r), style = Stroke(2.dp.toPx()))

        val perimeter = 2 * (w + h) - 8 * r + 2 * PI.toFloat() * r
        val headDist = progress * perimeter
        val tailDist = ((progress - 0.3f + 1f) % 1f) * perimeter

        val path = Path()
        path.addRoundRect(
            androidx.compose.ui.geometry.RoundRect(0f, 0f, w, h, androidx.compose.ui.geometry.CornerRadius(r))
        )

        drawPath(path, primary, style = Stroke(3.dp.toPx(), cap = StrokeCap.Round))
    }
}

/** Card that lifts on hover with shadow animation */
@Composable
fun CardLiftHover(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var lifted by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { lifted = true; delay(2000); lifted = false; delay(1000) }
    }

    val elevation by animateFloatAsState(if (lifted) 16f else 2f, spring(stiffness = Spring.StiffnessLow))
    val translateY by animateFloatAsState(if (lifted) -8f else 0f, spring(stiffness = Spring.StiffnessLow))

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        shadowElevation = elevation.dp,
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .size(140.dp, 80.dp)
            .graphicsLayer { translationY = translateY },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(stringResource(Res.string.demo_lift), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}

/** Card with animated gradient border */
@Composable
fun CardGradientBorder(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val angle by inf.animateFloat(
        0f, 360f,
        infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In)
            .size(160.dp, 100.dp)
            .graphicsLayer { rotationZ = 0f }
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.sweepGradient(
                    0f to primary,
                    0.33f to secondary,
                    0.66f to tertiary,
                    1f to primary,
                )
            )
            .padding(2.dp)
            .clip(RoundedCornerShape(11.dp))
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center,
    ) {
        Text(stringResource(Res.string.demo_gradient), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
    }
}

/** Card expand/collapse animation */
@Composable
fun CardExpandCollapse(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { expanded = true; delay(2500); expanded = false; delay(1500) }
    }

    val height by animateFloatAsState(if (expanded) 120f else 48f, spring(stiffness = Spring.StiffnessLow))

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(160.dp).height(height.dp),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(stringResource(Res.string.demo_card_title), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
            if (expanded) {
                Text(stringResource(Res.string.demo_expanded_content), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f))
            }
        }
    }
}

/** Card with parallax tilt effect */
@Composable
fun CardParallaxTilt(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val rotX by inf.animateFloat(
        -10f, 10f,
        infiniteRepeatable(tween(2000), RepeatMode.Reverse),
    )
    val rotY by inf.animateFloat(
        -10f, 10f,
        infiniteRepeatable(tween(2500), RepeatMode.Reverse),
    )

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In)
            .size(140.dp, 90.dp)
            .graphicsLayer {
                rotationX = rotX
                rotationY = rotY
                cameraDistance = 12f * density
            },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(stringResource(Res.string.demo_parallax), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}

/** Card with glassmorphism frosted effect */
@Composable
fun CardGlassmorphism(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val offset by inf.animateFloat(
        -50f, 50f,
        infiniteRepeatable(tween(3000), RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).size(160.dp, 100.dp),
        contentAlignment = Alignment.Center,
    ) {
        // Moving blob behind glass
        Box(
            modifier = Modifier
                .size(40.dp)
                .offset { IntOffset(offset.roundToInt(), (offset * 0.6f).roundToInt()) }
                .clip(CircleShape)
                .background(primary.copy(alpha = 0.5f)),
        )
        // Glass card
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
            modifier = Modifier.size(140.dp, 80.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(stringResource(Res.string.demo_glass), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

/** Card with reveal wipe animation */
@Composable
fun CardRevealWipe(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val clip by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(2000, easing = FastOutSlowInEasing), RepeatMode.Restart),
    )

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In)
            .size(160.dp, 100.dp)
            .clip(RoundedCornerShape(12.dp)),
    ) {
        // Background
        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center,
        ) {
            Text(stringResource(Res.string.demo_reveal), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f))
        }
        // Wipe overlay
        Box(
            modifier = Modifier
                .fillMaxWidth(clip)
                .height(100.dp)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center,
        ) {
            if (clip > 0.3f) {
                Text(stringResource(Res.string.demo_reveal), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }
    }
}

/** Stacked cards that rotate like a fan */

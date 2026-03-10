package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

@Composable
fun GlowProgressBar(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(3000, easing = FastOutSlowInEasing), RepeatMode.Restart))
    val primary = MaterialTheme.colorScheme.primary
    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).width(160.dp).height(8.dp).background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(4.dp))) {
        Box(Modifier.fillMaxWidth(progress).height(8.dp).background(Brush.horizontalGradient(listOf(primary, primary.copy(alpha = 0.6f))), RoundedCornerShape(4.dp)))
    }
}

@Composable
fun PulseRadar(
    ringCount: Int = 3,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val pulses = List(ringCount.coerceAtLeast(2)) { index ->
        transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(tween(2000, delayMillis = index * 700), RepeatMode.Restart),
        )
    }
    val primary = MaterialTheme.colorScheme.primary
    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).size(100.dp)) {
        val centerOffset = center
        val maxRadius = size.minDimension / 2
        pulses.forEach { pulse ->
            drawCircle(primary.copy(alpha = (1f - pulse.value) * 0.4f), radius = maxRadius * pulse.value, center = centerOffset, style = Stroke(2.dp.toPx()))
        }
        drawCircle(primary, radius = 6.dp.toPx(), center = centerOffset)
    }
}

@Composable
fun MorphProgressIndicator(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val phase by transition.animateFloat(0f, 2f, infiniteRepeatable(tween(3000), RepeatMode.Restart))
    val primary = MaterialTheme.colorScheme.primary
    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).size(120.dp, 40.dp)) {
        val cx = size.width / 2
        val cy = size.height / 2
        if (phase < 1f) {
            val radius = 15.dp.toPx() * (1f - phase)
            val width = size.width * 0.8f * phase
            drawRoundRect(primary, topLeft = Offset(cx - width / 2, cy - 3.dp.toPx()), size = Size(width, 6.dp.toPx()), cornerRadius = CornerRadius(3.dp.toPx()))
            if (radius > 1f) drawCircle(primary, radius, Offset(cx, cy))
        } else {
            val p = phase - 1f
            val width = size.width * 0.8f * (1f - p)
            drawRoundRect(primary, topLeft = Offset(cx - width / 2, cy - 3.dp.toPx()), size = Size(width.coerceAtLeast(1f), 6.dp.toPx()), cornerRadius = CornerRadius(3.dp.toPx()))
            val radius = 15.dp.toPx() * p
            if (radius > 1f) drawCircle(primary, radius, Offset(cx, cy))
        }
    }
}

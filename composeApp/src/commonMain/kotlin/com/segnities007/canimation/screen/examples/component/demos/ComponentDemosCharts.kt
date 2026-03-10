package com.segnities007.canimation.screen.examples.component.demos

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
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.demo_canimation
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedPieChart(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val anim = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            anim.snapTo(0f)
            anim.animateTo(1f, tween(2000, easing = FastOutSlowInEasing))
            delay(1500)
        }
    }

    val slices = listOf(
        0.35f to MaterialTheme.colorScheme.primary,
        0.25f to MaterialTheme.colorScheme.secondary,
        0.2f to MaterialTheme.colorScheme.tertiary,
        0.2f to MaterialTheme.colorScheme.error,
    )

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).size(80.dp)) {
        var startAngle = -90f
        slices.forEach { (fraction, color) ->
            val sweep = 360f * fraction * anim.value
            drawArc(color, startAngle, sweep, useCenter = true)
            startAngle += sweep
        }
    }
}

@Composable
fun AnimatedBarChart(
    barCount: Int = 5,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val bars = remember { listOf(0.7f, 0.4f, 0.9f, 0.5f, 0.8f).take(barCount) }
    val animate = rememberLoopingVisibility(
        visibleDurationMillis = 3000L,
        hiddenDurationMillis = 1000L,
    )

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).height(60.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        bars.forEachIndexed { i, target ->
            val height by animateFloatAsState(
                if (animate) target * 60f else 0f,
                spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow),
            )
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(height.dp)
                    .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f + i * 0.08f)),
            )
        }
    }
}

@Composable
fun AnimatedGradientText(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val inf = rememberInfiniteTransition()
    val offset by inf.animateFloat(
        0f, 300f,
        infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary

    androidx.compose.material3.Text(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In),
        text = stringResource(Res.string.demo_canimation),
        style = MaterialTheme.typography.headlineSmall.copy(
            brush = Brush.horizontalGradient(
                listOf(primary, secondary, tertiary, primary),
                startX = offset,
                endX = offset + 200f,
            ),
        ),
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
    )
}

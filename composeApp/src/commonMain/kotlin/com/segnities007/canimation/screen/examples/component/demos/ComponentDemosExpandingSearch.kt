package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.demo_search_placeholder
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingToggle
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExpandingSearch(modifier: Modifier = Modifier) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val expanded = rememberLoopingToggle(
        initialValue = false,
        initialDelayMillis = 2000L,
        toggleDelayMillis = 2000L,
    )

    val animWidth by animateFloatAsState(
        targetValue = if (expanded) 240f else 48f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
    )
    val textAlpha by animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = tween(if (expanded) 400 else 100),
    )

    val primary = MaterialTheme.colorScheme.primary
    val onPrimary = MaterialTheme.colorScheme.onPrimary

    Surface(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .size(width = animWidth.dp, height = 48.dp),
        shape = RoundedCornerShape(24.dp),
        color = primary,
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Canvas(modifier = Modifier.size(24.dp)) {
                val r = 7.dp.toPx()
                val cx = size.width / 2f - 2.dp.toPx()
                val cy = size.height / 2f - 2.dp.toPx()
                drawCircle(
                    color = onPrimary,
                    radius = r,
                    center = androidx.compose.ui.geometry.Offset(cx, cy),
                    style = Stroke(width = 2.dp.toPx()),
                )
                val handleStart = androidx.compose.ui.geometry.Offset(
                    cx + r * cos(PI.toFloat() / 4f),
                    cy + r * sin(PI.toFloat() / 4f),
                )
                val handleEnd = androidx.compose.ui.geometry.Offset(
                    handleStart.x + 4.dp.toPx(),
                    handleStart.y + 4.dp.toPx(),
                )
                drawLine(
                    color = onPrimary,
                    start = handleStart,
                    end = handleEnd,
                    strokeWidth = 2.dp.toPx(),
                    cap = StrokeCap.Round,
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(Res.string.demo_search_placeholder),
                    modifier = Modifier.graphicsLayer { alpha = textAlpha },
                    color = onPrimary,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_animated_checkmark
import com.segnities007.canimation.compose.LoopingFloatStep
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingAnimatedFloat
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedCheckmark(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val progress = rememberLoopingAnimatedFloat(
        initialValue = 0f,
        steps = listOf(
            LoopingFloatStep(
                value = 1f,
                animationDurationMillis = 800,
                holdDurationMillis = 1200L,
                easing = FastOutSlowInEasing,
            ),
            LoopingFloatStep(value = 0f, animationDurationMillis = 400, holdDurationMillis = 300L),
        ),
    )
    val primary = MaterialTheme.colorScheme.primary

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Reveal.Center), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(Res.string.component_animated_checkmark), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Canvas(Modifier.size(64.dp)) {
            drawCircle(primary, radius = size.minDimension / 2 * progress, style = Stroke(4f))
            if (progress > 0.4f) {
                val p = ((progress - 0.4f) / 0.6f).coerceIn(0f, 1f)
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
                    drawLine(
                        primary,
                        Offset(startX, startY),
                        Offset(startX + (midX - startX) * t, startY + (midY - startY) * t),
                        4f,
                        StrokeCap.Round,
                    )
                } else {
                    drawLine(primary, Offset(startX, startY), Offset(midX, midY), 4f, StrokeCap.Round)
                    val t = (p - 0.5f) / 0.5f
                    drawLine(
                        primary,
                        Offset(midX, midY),
                        Offset(midX + (endX - midX) * t, midY + (endY - midY) * t),
                        4f,
                        StrokeCap.Round,
                    )
                }
            }
        }
    }
}

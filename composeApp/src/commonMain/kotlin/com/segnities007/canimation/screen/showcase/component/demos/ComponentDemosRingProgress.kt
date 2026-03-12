package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun ProgressRing(
    targetProgress: Float = 0.75f,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            progress = 0f
            delay(300)
            val steps = 100
            for (index in 1..steps) {
                progress = index.toFloat() / steps
                delay(25)
            }
            delay(1500)
        }
    }

    val animatedProgress by animateFloatAsState(targetValue = progress, animationSpec = tween(100))

    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).size(64.dp)) {
            val strokeWidth = 6.dp.toPx()
            drawArc(Color.Gray.copy(alpha = 0.2f), 0f, 360f, false, style = Stroke(width = strokeWidth))
            drawArc(Color(0xFF9B8AFF), -90f, 360f * animatedProgress, false, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))
        }
        Text(
            text = "${(animatedProgress * 100).roundToInt()}%",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

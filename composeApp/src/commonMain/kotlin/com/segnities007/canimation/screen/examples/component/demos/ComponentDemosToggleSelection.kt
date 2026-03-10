package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@Composable
fun CheckboxAnimation(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var checked by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            checked = true
            delay(1500)
            checked = false
            delay(1000)
        }
    }

    val progress by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(400, easing = FastOutSlowInEasing),
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (checked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        animationSpec = tween(300),
    )

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(48.dp)) {
        drawRoundRect(backgroundColor, cornerRadius = CornerRadius(8.dp.toPx()))
        if (progress > 0f) {
            val path = Path().apply {
                moveTo(size.width * 0.25f, size.height * 0.52f)
                lineTo(size.width * 0.42f, size.height * 0.68f)
                lineTo(size.width * 0.75f, size.height * 0.32f)
            }
            val totalLength = size.width * 0.7f
            drawPath(
                path = path,
                color = Color.White,
                style = Stroke(
                    width = 3.dp.toPx(),
                    cap = StrokeCap.Round,
                    pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(
                        floatArrayOf(totalLength * progress, totalLength),
                        0f,
                    ),
                ),
            )
        }
    }
}

@Composable
fun SwitchAnimation(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var enabled by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            enabled = !enabled
            delay(1400)
        }
    }

    val thumbOffset by animateFloatAsState(
        targetValue = if (enabled) 24f else 0f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = Spring.StiffnessMedium),
    )
    val trackColor by animateColorAsState(
        targetValue = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        animationSpec = tween(300),
    )
    val thumbScale by animateFloatAsState(
        targetValue = if (enabled) 1.1f else 1f,
        animationSpec = spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium),
    )

    Box(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Spring.In)
            .width(52.dp)
            .height(28.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(trackColor)
            .padding(2.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(thumbOffset.dp.roundToPx(), 0) }
                .size(24.dp)
                .graphicsLayer {
                    scaleX = thumbScale
                    scaleY = thumbScale
                }
                .background(Color.White, CircleShape),
        )
    }
}

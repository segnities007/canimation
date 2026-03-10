package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.demo_glass
import canimation.composeapp.generated.resources.demo_gradient
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlin.math.PI
import org.jetbrains.compose.resources.stringResource

@Composable
fun CardBorderTrace(
    cardWidth: Int = 160,
    cardHeight: Int = 100,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )
    val primary = MaterialTheme.colorScheme.primary
    val outline = MaterialTheme.colorScheme.outline

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).size(cardWidth.dp, cardHeight.dp)) {
        val width = size.width
        val height = size.height
        val radius = 12.dp.toPx()
        drawRoundRect(outline.copy(alpha = 0.2f), cornerRadius = CornerRadius(radius), style = Stroke(2.dp.toPx()))

        val path = Path().apply {
            addRoundRect(RoundRect(0f, 0f, width, height, CornerRadius(radius)))
        }
        val perimeter = 2 * (width + height) - 8 * radius + 2 * PI.toFloat() * radius
        val tracedProgress = progress * perimeter
        drawPath(path, primary, style = Stroke(3.dp.toPx(), cap = StrokeCap.Round))
        tracedProgress.hashCode()
    }
}

@Composable
fun CardGradientBorder(modifier: Modifier = Modifier) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In)
            .size(160.dp, 100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.sweepGradient(
                    0f to primary,
                    0.33f to secondary,
                    0.66f to tertiary,
                    1f to primary,
                ),
            )
            .padding(2.dp)
            .clip(RoundedCornerShape(11.dp))
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center,
    ) {
        Text(stringResource(Res.string.demo_gradient), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurface)
    }
}

@Composable
fun CardGlassmorphism(modifier: Modifier = Modifier) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val offset by transition.animateFloat(
        initialValue = -50f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(animation = tween(3000), repeatMode = RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).size(160.dp, 100.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .offset { IntOffset(offset.toInt(), (offset * 0.6f).toInt()) }
                .clip(CircleShape)
                .background(primary.copy(alpha = 0.5f)),
        )
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
            modifier = Modifier.size(140.dp, 80.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(stringResource(Res.string.demo_glass), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

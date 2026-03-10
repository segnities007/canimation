package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

@Composable
fun GradientShift(
    boxSize: Dp = 200.dp,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart),
    )

    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary
    val colors = remember(primary, secondary, tertiary) { listOf(primary, secondary, tertiary, primary) }

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).size(boxSize),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = colors,
                        startX = offset * -500f,
                        endX = offset * -500f + 800f,
                    )
                ),
        )
    }
}

@Composable
fun SkeletonLoader(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val infiniteTransition = rememberInfiniteTransition()
    val shimmerOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(tween(1200, easing = LinearEasing), RepeatMode.Restart),
    )

    val baseColor = MaterialTheme.colorScheme.surfaceVariant
    val shimmerColor = MaterialTheme.colorScheme.surface
    val widths = listOf(0.9f, 0.7f, 0.5f)

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        widths.forEach { fraction ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(baseColor, shimmerColor, baseColor),
                            startX = shimmerOffset * 300f,
                            endX = shimmerOffset * 300f + 300f,
                        )
                    ),
            )
        }
    }
}

@Composable
fun ParallaxLayers(
    layerCount: Int = 3,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val infiniteTransition = rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(3000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
    )

    val colors = listOf(
        MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
    ).take(layerCount)
    val speeds = listOf(20f, 50f, 80f)

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .size(240.dp, 160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center,
    ) {
        colors.forEachIndexed { index, color ->
            Box(
                modifier = Modifier
                    .size(180.dp, 100.dp)
                    .graphicsLayer { translationX = phase * speeds[index] }
                    .clip(RoundedCornerShape(8.dp))
                    .background(color),
            )
        }
    }
}

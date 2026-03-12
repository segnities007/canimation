package com.segnities007.canimation.screen.showcase.component.demos

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

@Composable
fun PulseLoadingDots(
    dotCount: Int = 3,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val infiniteTransition = rememberInfiniteTransition()

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        repeat(dotCount) { index ->
            val scale by infiniteTransition.animateFloat(
                initialValue = 0.5f,
                targetValue = 1.2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(600, delayMillis = index * 200, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse,
                ),
            )
            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.3f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(600, delayMillis = index * 200, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse,
                ),
            )
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .scale(scale)
                    .alpha(alpha)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
            )
        }
    }
}

@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val infiniteTransition = rememberInfiniteTransition()
    val shimmerOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    val baseColor = MaterialTheme.colorScheme.surfaceVariant
    val shimmerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Scale.Pop)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(horizontalShimmerBrush(baseColor, shimmerColor, shimmerOffset)),
        )
        repeat(3) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(if (index == 2) 0.75f else 1f)
                    .height(12.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(horizontalShimmerBrush(baseColor, shimmerColor, shimmerOffset)),
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(horizontalShimmerBrush(baseColor, shimmerColor, shimmerOffset)),
            )
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(10.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(horizontalShimmerBrush(baseColor, shimmerColor, shimmerOffset)),
                )
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(10.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(horizontalShimmerBrush(baseColor, shimmerColor, shimmerOffset)),
                )
            }
        }
    }
}

private fun horizontalShimmerBrush(
    baseColor: androidx.compose.ui.graphics.Color,
    shimmerColor: androidx.compose.ui.graphics.Color,
    shimmerOffset: Float,
) = Brush.horizontalGradient(
    colors = listOf(baseColor, shimmerColor, baseColor),
    startX = shimmerOffset * 300f,
    endX = (shimmerOffset + 1f) * 300f,
)

package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingIndex
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

@Composable
fun CardShuffle(
    cardCount: Int = 4,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val stepCount = cardCount.coerceAtMost(3).coerceAtLeast(1)
    val step = rememberLoopingIndex(
        itemCount = stepCount,
        initialDelayMillis = 1000L,
        stepDelayMillis = 1000L,
    )
    val cardColors = listOf(
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
    )

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).size(200.dp, 160.dp),
        contentAlignment = Alignment.Center,
    ) {
        (0 until 3).forEach { index ->
            val order = (index - step + 3) % 3
            val targetX = (order - 1) * 30f
            val targetRotation = (order - 1) * 8f
            val animX by animateFloatAsState(
                targetValue = targetX,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
            )
            val animRotation by animateFloatAsState(
                targetValue = targetRotation,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
            )

            Surface(
                modifier = Modifier
                    .size(100.dp, 130.dp)
                    .graphicsLayer {
                        translationX = animX
                        rotationZ = animRotation
                    },
                shape = RoundedCornerShape(12.dp),
                color = cardColors[index],
                shadowElevation = ((3 - order) * 2).dp,
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = (index + 1).toString(),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }
}

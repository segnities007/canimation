package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import kotlin.math.abs
import androidx.compose.material3.MaterialTheme

@Composable
fun StaggerFromCenter(
    itemCount: Int = 7,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var visible by remember { mutableStateOf(false) }
    val center = itemCount / 2

    LaunchedEffect(Unit) {
        while (true) {
            visible = true
            delay(3000)
            visible = false
            delay(800)
        }
    }

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(itemCount) { index ->
            val distanceFromCenter = abs(index - center)
            var itemVisible by remember { mutableStateOf(false) }

            LaunchedEffect(visible) {
                if (visible) {
                    delay(distanceFromCenter * 100L)
                    itemVisible = true
                } else {
                    itemVisible = false
                }
            }

            val scale by animateFloatAsState(
                targetValue = if (itemVisible) 1f else 0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
            )
            val alpha by animateFloatAsState(
                targetValue = if (itemVisible) 1f else 0f,
                animationSpec = tween(200),
            )

            Box(
                modifier = Modifier
                    .size(width = 20.dp, height = 36.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp)),
            )
        }
    }
}

package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@Composable
fun WaterfallGrid(
    itemCount: Int = 8,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var show by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            show = true
            delay(2500)
            show = false
            delay(800)
        }
    }

    val colors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
    )

    Column(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Zoom.In)
            .width(200.dp)
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        repeat(2) { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                repeat(3) { column ->
                    val index = row * 3 + column
                    if (index >= itemCount.coerceAtMost(6)) return@repeat

                    val stagger = index * 100
                    val offsetY by animateFloatAsState(
                        targetValue = if (show) 0f else -80f,
                        animationSpec = tween(
                            durationMillis = 500,
                            delayMillis = stagger,
                            easing = FastOutSlowInEasing,
                        ),
                    )
                    val alpha by animateFloatAsState(
                        targetValue = if (show) 1f else 0f,
                        animationSpec = tween(durationMillis = 500, delayMillis = stagger),
                    )
                    val itemHeight = if (row == 0 && column == 1) 70 else 50

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(itemHeight.dp)
                            .graphicsLayer {
                                translationY = offsetY
                                this.alpha = alpha
                            }
                            .clip(RoundedCornerShape(8.dp))
                            .background(colors[index % colors.size]),
                    )
                }
            }
        }
    }
}

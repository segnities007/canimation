package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.Animatable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@Composable
fun InfiniteLoadingList(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var batch by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            batch = (batch + 1) % 3
        }
    }

    Column(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Scale.Pop)
            .width(220.dp)
            .height(180.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        for (index in 0 until 4) {
            val itemAlpha = remember(batch) { Animatable(0f) }
            val itemOffset = remember(batch) { Animatable(30f) }
            LaunchedEffect(batch) {
                delay(index * 120L)
                itemAlpha.animateTo(1f, tween(400))
            }
            LaunchedEffect(batch) {
                delay(index * 120L)
                itemOffset.animateTo(0f, tween(400, easing = FastOutSlowInEasing))
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp)
                    .graphicsLayer {
                        alpha = itemAlpha.value
                        translationY = itemOffset.value
                    },
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.secondaryContainer,
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.padding(horizontal = 12.dp),
                ) {
                    Text(
                        text = "Item ${batch * 4 + index + 1}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }
            }
        }
    }
}

@Composable
fun HorizontalScrollGallery(
    itemCount: Int = 8,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val scroll by transition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    val colors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
    ).take(itemCount.coerceAtMost(5))

    Box(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .width(260.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(12.dp)),
    ) {
        Row {
            colors.forEachIndexed { index, color ->
                val parallax = (scroll * (0.5f + index * 0.15f)) % 400f
                Box(
                    modifier = Modifier
                        .size(70.dp, 70.dp)
                        .padding(4.dp)
                        .graphicsLayer { translationX = -scroll + parallax }
                        .clip(RoundedCornerShape(8.dp))
                        .background(color),
                )
            }
        }
    }
}

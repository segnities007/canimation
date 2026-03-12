package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@Composable
fun CardStackSwipe(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val labels = listOf("Card A", "Card B", "Card C", "Card D")
    var topIndex by remember { mutableIntStateOf(0) }
    val swipeX = remember { Animatable(0f) }
    val swipeRotation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            swipeX.animateTo(350f, tween(500, easing = FastOutSlowInEasing))
            swipeRotation.animateTo(15f, tween(500))
            topIndex = (topIndex + 1) % labels.size
            swipeX.snapTo(0f)
            swipeRotation.snapTo(0f)
        }
    }

    Box(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Spring.In)
            .size(180.dp, 120.dp),
        contentAlignment = Alignment.Center,
    ) {
        for (index in labels.indices.reversed()) {
            val relativeIndex = (index - topIndex + labels.size) % labels.size
            if (relativeIndex > 2) continue
            val scale = 1f - relativeIndex * 0.06f
            val yOffset = relativeIndex * 8f
            Surface(
                modifier = Modifier
                    .size(160.dp, 90.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationY = yOffset
                        if (relativeIndex == 0) {
                            translationX = swipeX.value
                            rotationZ = swipeRotation.value
                        }
                    },
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                shadowElevation = (4 - relativeIndex).coerceAtLeast(1).dp,
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = labels[index],
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }
}

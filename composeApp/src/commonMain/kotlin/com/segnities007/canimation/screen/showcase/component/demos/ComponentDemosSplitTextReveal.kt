package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
fun SplitTextReveal(
    text: String = "Compose Multiplatform Animations",
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val words = text.split(" ")
    var visibleCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            visibleCount = 0
            delay(500)
            for (index in 1..words.size) {
                visibleCount = index
                delay(200)
            }
            delay(2500)
        }
    }

    Row(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Fade.In)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        words.forEachIndexed { index, word ->
            val visible = index < visibleCount
            val alpha by animateFloatAsState(
                targetValue = if (visible) 1f else 0f,
                animationSpec = tween(300),
            )
            val offsetY by animateFloatAsState(
                targetValue = if (visible) 0f else 20f,
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            )
            Text(
                text = word,
                modifier = Modifier.graphicsLayer {
                    this.alpha = alpha
                    translationY = offsetY
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

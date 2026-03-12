package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@Composable
fun MegaMenuReveal(
    items: List<String> = listOf("Home", "Products", "About", "Blog", "Contact"),
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            visible = true
            delay(2500)
            visible = false
            delay(800)
        }
    }

    val offsets = items.indices.map { index ->
        animateFloatAsState(
            targetValue = if (visible) 0f else -60f,
            animationSpec = tween(400, delayMillis = index * 80, easing = FastOutSlowInEasing),
        )
    }
    val alphas = items.indices.map { index ->
        animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = tween(400, delayMillis = index * 80),
        )
    }

    Surface(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Fade.In)
            .width(200.dp)
            .height(180.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items.forEachIndexed { index, item ->
                Text(
                    text = item,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .graphicsLayer {
                            translationX = offsets[index].value
                            alpha = alphas[index].value
                        }
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                )
            }
        }
    }
}

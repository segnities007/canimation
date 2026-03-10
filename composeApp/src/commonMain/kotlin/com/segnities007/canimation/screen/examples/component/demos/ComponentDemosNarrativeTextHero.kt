package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun TextLineReveal(
    lines: List<String> = listOf("Design is", "not just what", "it looks like.", "It's how it works."),
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            visible = true
            delay(3000)
            visible = false
            delay(800)
        }
    }

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).width(220.dp).padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        lines.forEachIndexed { index, line ->
            val offsetX by animateFloatAsState(
                targetValue = if (visible) 0f else 80f,
                animationSpec = tween(500, delayMillis = index * 150, easing = FastOutSlowInEasing),
            )
            val alpha by animateFloatAsState(
                targetValue = if (visible) 1f else 0f,
                animationSpec = tween(500, delayMillis = index * 150),
            )
            Text(
                text = line,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.graphicsLayer {
                    translationX = offsetX
                    this.alpha = alpha
                },
            )
        }
    }
}

@Composable
fun ZoomHeroImage(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            expanded = true
            delay(2000)
            expanded = false
            delay(1200)
        }
    }

    val width by animateFloatAsState(
        targetValue = if (expanded) 260f else 80f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = Spring.StiffnessMediumLow),
    )
    val height by animateFloatAsState(
        targetValue = if (expanded) 160f else 80f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = Spring.StiffnessMediumLow),
    )
    val cornerRadius by animateFloatAsState(targetValue = if (expanded) 16f else 40f, animationSpec = tween(400))

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).size(260.dp, 160.dp),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier.size(width.dp, height.dp),
            shape = RoundedCornerShape(cornerRadius.dp),
            color = MaterialTheme.colorScheme.tertiaryContainer,
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(
                    text = if (expanded) "Hero View" else "\uD83D\uDDBC",
                    style = if (expanded) MaterialTheme.typography.titleMedium else MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
        }
    }
}

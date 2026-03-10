package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlin.math.abs
import kotlinx.coroutines.delay

@Composable
fun GlitchText(
    text: String = "GLITCH",
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    var glitching by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            glitching = true
            delay(150)
            glitching = false
        }
    }

    val offsetX by animateFloatAsState(targetValue = if (glitching) 6f else 0f, animationSpec = tween(50))
    val textAlpha by animateFloatAsState(targetValue = if (glitching) 0.7f else 1f, animationSpec = tween(50))

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (glitching) {
            Text(
                text = text,
                modifier = Modifier.graphicsLayer {
                    translationX = -4f
                    translationY = 2f
                    alpha = 0.4f
                },
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.error),
            )
        }
        Text(
            text = text,
            modifier = Modifier.graphicsLayer {
                translationX = offsetX
                alpha = textAlpha
            },
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface),
        )
    }
}

@Composable
fun VerticalTicker(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val words = listOf("Kotlin", "Compose", "Multiplatform", "Animation", "Design")
    var currentIndex by remember { mutableIntStateOf(0) }
    val offset = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            offset.animateTo(-40f, animationSpec = tween(400, easing = FastOutSlowInEasing))
            currentIndex = (currentIndex + 1) % words.size
            offset.snapTo(40f)
            offset.animateTo(0f, animationSpec = tween(400, easing = FastOutSlowInEasing))
        }
    }

    val alphaValue = 1f - abs(offset.value) / 40f

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).fillMaxWidth().height(60.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = words[currentIndex],
            modifier = Modifier.graphicsLayer {
                translationY = offset.value
                alpha = alphaValue
            },
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary),
            textAlign = TextAlign.Center,
        )
    }
}

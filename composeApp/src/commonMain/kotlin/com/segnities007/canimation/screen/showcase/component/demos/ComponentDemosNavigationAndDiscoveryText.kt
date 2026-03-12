package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingToggle
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RevealTextEffect(
    text: String = "Reveal Animation",
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    var count by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            for (index in 0..text.length) {
                count = index
                delay(70)
            }
            delay(1500)
            count = 0
            delay(400)
        }
    }

    val primary = MaterialTheme.colorScheme.primary
    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        text.forEachIndexed { index, char ->
            val isVisible = index < count
            val isHighlight = index == count - 1
            Text(
                text = char.toString(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (!isVisible) Color.Transparent
                else if (isHighlight) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurface,
                modifier = if (isHighlight) {
                    Modifier.background(primary, androidx.compose.foundation.shape.RoundedCornerShape(2.dp))
                } else {
                    Modifier
                },
            )
        }
    }
}

@Composable
fun ScatterText(
    text: String = "SCATTER",
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val scattered = rememberLoopingToggle(toggleDelayMillis = 1500L)

    val offsets = text.indices.map { index ->
        val angle = index * (360f / text.length)
        val radians = angle * PI.toFloat() / 180f
        val x by animateFloatAsState(
            targetValue = if (scattered) cos(radians) * 40f else 0f,
            animationSpec = spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessMediumLow),
        )
        val y by animateFloatAsState(
            targetValue = if (scattered) sin(radians) * 40f else 0f,
            animationSpec = spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessMediumLow),
        )
        val alpha by animateFloatAsState(
            targetValue = if (scattered) 0.3f else 1f,
            animationSpec = tween(400),
        )
        Triple(x, y, alpha)
    }

    Box(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Fade.In)
            .size(200.dp, 60.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row {
            text.forEachIndexed { index, char ->
                val (x, y, alpha) = offsets[index]
                Text(
                    text = char.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.graphicsLayer {
                        translationX = x
                        translationY = y
                        this.alpha = alpha
                    },
                )
            }
        }
    }
}

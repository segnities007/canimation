package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_timer_total
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import kotlin.math.abs
import kotlin.math.roundToInt
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProgressScrubber(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val animation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            animation.animateTo(1f, tween(3000, easing = LinearEasing))
            delay(500)
            animation.snapTo(0f)
            delay(300)
        }
    }

    val primary = MaterialTheme.colorScheme.primary
    val track = MaterialTheme.colorScheme.surfaceVariant
    val elapsed = (animation.value * 180).roundToInt()

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).width(260.dp).padding(8.dp)) {
        Box(modifier = Modifier.fillMaxWidth().height(32.dp), contentAlignment = Alignment.CenterStart) {
            Canvas(modifier = Modifier.fillMaxWidth().height(4.dp)) {
                drawRoundRect(track, cornerRadius = CornerRadius(2f))
                drawRoundRect(primary, size = Size(size.width * animation.value, size.height), cornerRadius = CornerRadius(2f))
            }
            Box(
                modifier = Modifier
                    .offset { IntOffset((animation.value * (260 - 16)).dp.roundToPx(), 0) }
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(primary),
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("${elapsed / 60}:${(elapsed % 60).toString().padStart(2, '0')}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface)
            Text(stringResource(Res.string.component_timer_total), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
        }
    }
}

@Composable
fun VerticalCarousel(
    items: List<String> = listOf("Slide 1", "Slide 2", "Slide 3", "Slide 4"),
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var current by remember { mutableIntStateOf(0) }
    val offset = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1200)
            offset.animateTo(-1f, tween(600, easing = FastOutSlowInEasing))
            current = (current + 1) % items.size
            offset.snapTo(0f)
        }
    }

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(200.dp).height(80.dp).clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center,
    ) {
        for (index in -1..1) {
            val itemIndex = (current + index + items.size) % items.size
            val yPos = (index + offset.value) * 70f
            val rotation = (index + offset.value) * 30f
            val alpha = 1f - abs(index + offset.value) * 0.5f
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(50.dp)
                    .graphicsLayer {
                        translationY = yPos
                        rotationX = rotation
                        this.alpha = alpha.coerceIn(0f, 1f)
                    },
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.secondaryContainer,
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(text = items[itemIndex], color = MaterialTheme.colorScheme.onSecondaryContainer, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

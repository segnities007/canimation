package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.demo_music_equalizer
import canimation.composeapp.generated.resources.demo_slot_machine
import canimation.composeapp.generated.resources.demo_spin
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

@Composable
fun SlotMachine(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val symbols = listOf(
        Icons.Default.Star,
        Icons.Default.Favorite,
        Icons.Default.PlayArrow,
        Icons.Default.Settings,
        Icons.Default.Home,
    )
    var slots by remember { mutableStateOf(List(3) { symbols.random() }) }
    var spinning by remember { mutableStateOf(false) }

    LaunchedEffect(spinning) {
        if (spinning) {
            repeat(10) {
                slots = List(3) { symbols.random() }
                delay(100)
            }
            spinning = false
        }
    }

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Page.BottomSheet),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.demo_slot_machine), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            slots.forEach { symbol: ImageVector ->
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    tonalElevation = 4.dp,
                    modifier = Modifier.size(48.dp),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = symbol,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp),
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { spinning = true }, enabled = !spinning) {
            Text(stringResource(Res.string.demo_spin))
        }
    }
}

@Composable
fun MusicEqualizer(
    barCount: Int = 5,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val primary = MaterialTheme.colorScheme.primary

    val barHeights = (0 until barCount).map { index ->
        transition.animateFloat(
            initialValue = 0.3f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 400 + index * 100, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse,
            ),
        )
    }

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.demo_music_equalizer), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Canvas(Modifier.size(80.dp, 60.dp)) {
            val barWidth = size.width / (barCount * 2f)
            for (index in 0 until barCount) {
                val height = barHeights[index].value * size.height
                val x = index * barWidth * 2 + barWidth / 2
                drawRoundRect(
                    color = primary,
                    topLeft = Offset(x, size.height - height),
                    size = Size(barWidth, height),
                    cornerRadius = CornerRadius(barWidth / 2),
                )
            }
        }
    }
}

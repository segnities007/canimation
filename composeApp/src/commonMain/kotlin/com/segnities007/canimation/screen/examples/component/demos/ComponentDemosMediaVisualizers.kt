package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_matrix_rain
import canimation.composeapp.generated.resources.component_percent_value
import canimation.composeapp.generated.resources.component_radial_progress
import canimation.composeapp.generated.resources.component_waveform
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

@Composable
fun WaveformVisualizer(modifier: Modifier = Modifier) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val transition = rememberInfiniteTransition()
    val barCount = 12

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(Res.string.component_waveform), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(3.dp), verticalAlignment = Alignment.Bottom, modifier = Modifier.height(60.dp)) {
            for (i in 0 until barCount) {
                val height by transition.animateFloat(
                    initialValue = 10f,
                    targetValue = 55f,
                    animationSpec = infiniteRepeatable(tween(400 + i * 60, easing = FastOutSlowInEasing), RepeatMode.Reverse),
                )
                Box(Modifier.width(6.dp).height(height.dp).clip(RoundedCornerShape(3.dp)).background(MaterialTheme.colorScheme.primary))
            }
        }
    }
}

@Composable
fun RadialProgress(modifier: Modifier = Modifier) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) { progress.animateTo(0.75f, animationSpec = tween(1500, easing = FastOutSlowInEasing)) }
    val primary = MaterialTheme.colorScheme.primary
    val track = MaterialTheme.colorScheme.surfaceVariant

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(Res.string.component_radial_progress), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(100.dp)) {
            Canvas(Modifier.size(100.dp)) {
                drawArc(track, 0f, 360f, false, style = Stroke(10f, cap = StrokeCap.Round))
                drawArc(primary, -90f, progress.value * 360f, false, style = Stroke(10f, cap = StrokeCap.Round))
            }
            Text(stringResource(Res.string.component_percent_value, (progress.value * 100).toInt()), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun MatrixRain(modifier: Modifier = Modifier) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val columns = 8
    val chars = "アイウエオカキクケコ0123456789"
    val displayChars = remember { mutableStateListOf<String>().apply { repeat(columns) { add("") } } }

    LaunchedEffect(Unit) {
        while (true) {
            for (c in 0 until columns) {
                val len = (3..6).random()
                displayChars[c] = buildString { repeat(len) { append(chars.random()) } }
            }
            delay(200)
        }
    }

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(Res.string.component_matrix_rain), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            displayChars.forEach { col ->
                Column {
                    col.forEach { ch ->
                        Text(ch.toString(), color = Color(0xFF00FF41), fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

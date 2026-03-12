package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingIndex
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_depth_value
import canimation.composeapp.generated.resources.component_gradient_border
import canimation.composeapp.generated.resources.component_neumorphism_card
import canimation.composeapp.generated.resources.component_pulse_button
import canimation.composeapp.generated.resources.component_tap
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun PulseButton(modifier: Modifier = Modifier) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val transition = rememberInfiniteTransition()
    val scale by transition.animateFloat(1f, 1.08f, infiniteRepeatable(tween(800), RepeatMode.Reverse))
    val primary = MaterialTheme.colorScheme.primary

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(Res.string.component_pulse_button), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(contentAlignment = Alignment.Center) {
            Canvas(Modifier.size((60 * scale).dp)) { drawCircle(primary.copy(alpha = 0.25f)) }
            Button(onClick = {}) { Text(stringResource(Res.string.component_tap)) }
        }
    }
}

@Composable
fun NeumorphismCard(modifier: Modifier = Modifier) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val transition = rememberInfiniteTransition()
    val elevation by transition.animateFloat(2f, 12f, infiniteRepeatable(tween(1500), RepeatMode.Reverse))

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Blur.Soft), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(Res.string.component_neumorphism_card), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Surface(shape = RoundedCornerShape(16.dp), tonalElevation = elevation.dp, shadowElevation = elevation.dp, modifier = Modifier.size(140.dp, 90.dp)) {
            Box(contentAlignment = Alignment.Center) {
                Text(stringResource(Res.string.component_depth_value, elevation.toInt()), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun GradientBorderCard(modifier: Modifier = Modifier) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val transition = rememberInfiniteTransition()
    val offset by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(2000, easing = LinearEasing)))

    val colors = listOf(androidx.compose.ui.graphics.Color(0xFF6200EE), androidx.compose.ui.graphics.Color(0xFF03DAC5), androidx.compose.ui.graphics.Color(0xFFFF5722), androidx.compose.ui.graphics.Color(0xFF6200EE))
    val shift = (offset * (colors.size - 1)).toInt().coerceAtMost(colors.size - 2)
    val borderColor = androidx.compose.ui.graphics.Color(
        red = colors[shift].red + (colors[shift + 1].red - colors[shift].red) * (offset * (colors.size - 1) - shift),
        green = colors[shift].green + (colors[shift + 1].green - colors[shift].green) * (offset * (colors.size - 1) - shift),
        blue = colors[shift].blue + (colors[shift + 1].blue - colors[shift].blue) * (offset * (colors.size - 1) - shift),
    )

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Reveal.Center), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(Res.string.component_gradient_border), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(
            Modifier.size(150.dp, 90.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(borderColor)
                .padding(3.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center,
        ) {
            Text("Gradient Border", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FlipCounter(modifier: Modifier = Modifier) {
    val autoValue = rememberLoopingIndex(
        itemCount = 10,
        initialDelayMillis = 1200L,
        stepDelayMillis = 1200L,
    )
    var value by remember { mutableIntStateOf(autoValue) }
    LaunchedEffect(autoValue) { value = autoValue }
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.size(80.dp).clickable { value = (value + 1) % 10 },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text("$value", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        }
    }
}

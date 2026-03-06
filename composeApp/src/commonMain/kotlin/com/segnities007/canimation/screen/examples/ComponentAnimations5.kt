package com.segnities007.canimation.screen.examples

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.*
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// ─── 1. WaveformVisualizer ──────────────────────────────────────────


@Composable
fun WaveformVisualizer(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val barCount = 12

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.component_waveform), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.height(60.dp),
        ) {
            for (i in 0 until barCount) {
                val height by transition.animateFloat(
                    initialValue = 10f,
                    targetValue = 55f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(400 + i * 60, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse,
                    ),
                )
                Box(
                    Modifier
                        .width(6.dp)
                        .height(height.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(MaterialTheme.colorScheme.primary),
                )
            }
        }
    }
}
// ─── 3. RadialProgress ──────────────────────────────────────────────

@Composable
fun RadialProgress(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        progress.animateTo(0.75f, animationSpec = tween(1500, easing = FastOutSlowInEasing))
    }
    val primary = MaterialTheme.colorScheme.primary
    val track = MaterialTheme.colorScheme.surfaceVariant

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.component_radial_progress), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(100.dp)) {
            Canvas(Modifier.size(100.dp)) {
                drawArc(track, 0f, 360f, false, style = Stroke(10f, cap = StrokeCap.Round))
                drawArc(
                    primary, -90f, progress.value * 360f, false,
                    style = Stroke(10f, cap = StrokeCap.Round),
                )
            }
            Text(stringResource(Res.string.component_percent_value, (progress.value * 100).toInt()), fontWeight = FontWeight.Bold)
        }
    }
}

// ─── 4. MatrixRain ──────────────────────────────────────────────────

@Composable
fun MatrixRain(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

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

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.component_matrix_rain), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            displayChars.forEach { col ->
                Column {
                    col.forEach { ch ->
                        Text(
                            ch.toString(),
                            color = Color(0xFF00FF41),
                            fontFamily = FontFamily.Monospace,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }
        }
    }
}

// ─── 5. PulseButton ─────────────────────────────────────────────────

@Composable
fun PulseButton(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val scale by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.component_pulse_button), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(contentAlignment = Alignment.Center) {
            Canvas(Modifier.size((60 * scale).dp)) {
                drawCircle(primary.copy(alpha = 0.25f))
            }
            Button(onClick = {}) { Text(stringResource(Res.string.component_tap)) }
        }
    }
}

// ─── 6. NeumorphismCard ─────────────────────────────────────────────

@Composable
fun NeumorphismCard(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val elevation by transition.animateFloat(
        initialValue = 2f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(tween(1500), RepeatMode.Reverse),
    )

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Blur.Soft),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.component_neumorphism_card), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = elevation.dp,
            shadowElevation = elevation.dp,
            modifier = Modifier.size(140.dp, 90.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(stringResource(Res.string.component_depth_value, elevation.toInt()), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

// ─── 7. GradientBorderCard ──────────────────────────────────────────

@Composable
fun GradientBorderCard(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val transition = rememberInfiniteTransition()
    val offset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2000, easing = LinearEasing)),
    )

    val colors = listOf(Color(0xFF6200EE), Color(0xFF03DAC5), Color(0xFFFF5722), Color(0xFF6200EE))
    val shift = (offset * (colors.size - 1)).toInt().coerceAtMost(colors.size - 2)
    val borderColor = Color(
        red = colors[shift].red + (colors[shift + 1].red - colors[shift].red) * (offset * (colors.size - 1) - shift),
        green = colors[shift].green + (colors[shift + 1].green - colors[shift].green) * (offset * (colors.size - 1) - shift),
        blue = colors[shift].blue + (colors[shift + 1].blue - colors[shift].blue) * (offset * (colors.size - 1) - shift),
    )

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Reveal.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.component_gradient_border), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(
            Modifier
                .size(150.dp, 90.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(borderColor)
                .padding(3.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center,
        ) {
            Text(stringResource(Res.string.component_animated), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// ─── 8. FlipCounter ─────────────────────────────────────────────────

@Composable
fun FlipCounter(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var count by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            count++
        }
    }

    Column(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.component_flip_counter), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            val digits = (count % 10000).toString().padStart(4, '0')
            digits.forEach { digit: Char ->
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    tonalElevation = 4.dp,
                    modifier = Modifier.size(36.dp, 48.dp),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            digit.toString(),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}

// ─── 9. ExpandableChip ──────────────────────────────────────────────

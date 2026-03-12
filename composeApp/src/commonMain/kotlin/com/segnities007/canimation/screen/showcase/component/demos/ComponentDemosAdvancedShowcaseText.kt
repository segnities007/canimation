package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_animations_are
import canimation.composeapp.generated.resources.component_glitch
import canimation.composeapp.generated.resources.component_new_price
import canimation.composeapp.generated.resources.component_old_price
import canimation.composeapp.generated.resources.component_text_beautiful
import canimation.composeapp.generated.resources.component_text_fast
import canimation.composeapp.generated.resources.component_text_powerful
import canimation.composeapp.generated.resources.component_text_simple
import canimation.composeapp.generated.resources.demo_hello_world
import com.segnities007.canimation.compose.rememberLoopingIndex
import com.segnities007.canimation.compose.rememberLoopingToggle
import com.segnities007.canimation.compose.rememberReplayVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

@Composable
fun TypewriterEffect(modifier: Modifier = Modifier) {
    val text = stringResource(Res.string.demo_hello_world)
    var displayed by remember { mutableStateOf("") }
    LaunchedEffect(text) {
        while (true) {
            displayed = ""
            for (character in text) {
                displayed += character
                delay(120)
            }
            delay(2000)
        }
    }
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Text(
            text = displayed + if (displayed.length < text.length) "▌" else "",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun TextMorph(modifier: Modifier = Modifier) {
    val words = listOf(
        stringResource(Res.string.component_text_fast),
        stringResource(Res.string.component_text_simple),
        stringResource(Res.string.component_text_beautiful),
        stringResource(Res.string.component_text_powerful),
    )
    val index = rememberLoopingIndex(itemCount = words.size, initialDelayMillis = 2000L, stepDelayMillis = 2000L)
    val visible = rememberReplayVisibility(replayKey = index, resetDurationMillis = 50L)
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(Res.string.component_animations_are), style = MaterialTheme.typography.bodyLarge)
            Box(Modifier.canimation(visible = visible, effect = Canimation.Fade.Up)) {
                Text(words[index], style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
fun TextStrikethrough(modifier: Modifier = Modifier) {
    val struck = rememberLoopingToggle(toggleDelayMillis = 2000L)
    val visible = rememberReplayVisibility(replayKey = struck, resetDurationMillis = 0L)
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                stringResource(Res.string.component_old_price),
                style = MaterialTheme.typography.bodyMedium,
                textDecoration = if (struck) TextDecoration.LineThrough else TextDecoration.None,
                color = if (struck) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface,
            )
            if (struck) {
                Text(
                    stringResource(Res.string.component_new_price),
                    modifier = Modifier.canimation(visible = visible, effect = Canimation.Scale.Pop),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF22C55E),
                )
            }
        }
    }
}

@Composable
fun TextGlitch(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val offsetX by transition.animateFloat(0f, 2f, infiniteRepeatable(tween(150), RepeatMode.Reverse))
    val offsetY by transition.animateFloat(0f, -1f, infiniteRepeatable(tween(200), RepeatMode.Reverse))
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Text(stringResource(Res.string.component_glitch), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black, color = Color(0xFF22D3EE).copy(alpha = 0.5f), modifier = Modifier.offset(x = offsetX.dp, y = offsetY.dp))
        Text(stringResource(Res.string.component_glitch), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black, color = Color(0xFFEF4444).copy(alpha = 0.5f), modifier = Modifier.offset(x = (-offsetX).dp, y = (-offsetY).dp))
        Text(stringResource(Res.string.component_glitch), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black)
    }
}

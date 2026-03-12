package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberLoopingIndex
import com.segnities007.canimation.compose.rememberLoopingVisibility
import com.segnities007.canimation.compose.rememberReplayVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_built_for
import canimation.composeapp.generated.resources.component_target_creators
import canimation.composeapp.generated.resources.component_target_designers
import canimation.composeapp.generated.resources.component_target_developers
import canimation.composeapp.generated.resources.component_target_teams
import canimation.composeapp.generated.resources.component_word_animate
import canimation.composeapp.generated.resources.component_word_build
import canimation.composeapp.generated.resources.component_word_delight
import canimation.composeapp.generated.resources.component_word_ship
import canimation.composeapp.generated.resources.demo_animations_are

@Composable
fun TextFadeReveal(modifier: Modifier = Modifier) {
    val words = listOf(
        stringResource(Res.string.component_word_build),
        stringResource(Res.string.component_word_animate),
        stringResource(Res.string.component_word_ship),
        stringResource(Res.string.component_word_delight),
    )
    val index = rememberLoopingIndex(
        itemCount = words.size,
        initialDelayMillis = 1500L,
        stepDelayMillis = 1500L,
    )
    val visible = rememberReplayVisibility(replayKey = index, resetDurationMillis = 50L)
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Text(words[index], style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.canimation(visible = visible, effect = Canimation.Fade.Up))
    }
}

@Composable
fun TextHighlighter(modifier: Modifier = Modifier) {
    val words = "Compose Multiplatform Animation Library".split(" ")
    var highlighted by remember { mutableIntStateOf(-1) }
    LaunchedEffect(Unit) {
        while (true) {
            for (index in words.indices) { highlighted = index; delay(400) }
            highlighted = -1
            delay(600)
        }
    }
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        words.forEachIndexed { index, word ->
            val background by animateColorAsState(if (index == highlighted) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
            Text(word, modifier = Modifier.background(background, RoundedCornerShape(4)).padding(horizontal = 4.dp, vertical = 2.dp), style = MaterialTheme.typography.bodyLarge, fontWeight = if (index == highlighted) FontWeight.Bold else FontWeight.Normal, color = if (index == highlighted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
            if (index < words.lastIndex) Spacer(Modifier.width(4.dp))
        }
    }
}

@Composable
fun TextShuffleWord(modifier: Modifier = Modifier) {
    val options = listOf(
        stringResource(Res.string.component_target_developers),
        stringResource(Res.string.component_target_designers),
        stringResource(Res.string.component_target_creators),
        stringResource(Res.string.component_target_teams),
    )
    val index = rememberLoopingIndex(
        itemCount = options.size,
        initialDelayMillis = 2000L,
        stepDelayMillis = 2000L,
    )
    val visible = rememberReplayVisibility(replayKey = index, resetDurationMillis = 50L)
    Column(modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(Res.string.component_built_for), style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Box(Modifier.canimation(visible = visible, effect = Canimation.Slide.UpSubtle)) {
            Text(options[index], style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun TextGradientReveal(modifier: Modifier = Modifier) {
    val chars = "canimation".toList()
    Column(modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            chars.forEachIndexed { index, char ->
                val visible = rememberLoopingVisibility(
                    initialDelayMillis = index * 80L,
                    visibleDurationMillis = 2000L,
                    hiddenDurationMillis = 300L,
                )
                Text(char.toString(), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.canimation(visible = visible, effect = Canimation.Rise.In))
            }
        }
    }
}

@Composable
fun TextRotateWords(modifier: Modifier = Modifier) {
    val words = listOf("Fast", "Simple", "Beautiful", "Powerful")
    val index = rememberLoopingIndex(
        itemCount = words.size,
        initialDelayMillis = 1800L,
        stepDelayMillis = 1800L,
    )
    val visible = rememberReplayVisibility(replayKey = index, resetDurationMillis = 50L)
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center) {
        Text(stringResource(Res.string.demo_animations_are), style = MaterialTheme.typography.bodyLarge)
        Box(Modifier.canimation(visible = visible, effect = Canimation.Flip.In)) {
            Text(words[index], style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }
    }
}

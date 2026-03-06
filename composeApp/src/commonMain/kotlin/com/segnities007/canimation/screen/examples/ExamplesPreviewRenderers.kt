package com.segnities007.canimation.screen.examples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.canimation
import io.github.canimation.core.canimationEmphasize
import kotlinx.coroutines.delay

@Composable
internal fun LivePreview(item: ExampleItem) {
    when (item.demoType) {
        DemoType.Effect, DemoType.Composition -> EffectPreview(item.effect ?: Canimation.Fade.In)
        DemoType.Transition -> TransitionPreview(item.enterEffect)
        DemoType.Stagger -> StaggerPreview(item.effect ?: Canimation.Fade.Up)
        DemoType.Emphasis -> EmphasisPreview(item.preset)
        DemoType.Component -> ComponentPreview(item.componentKey)
        DemoType.RealWorld, DemoType.EnterExit, DemoType.Visibility -> PresetPreview(item.preset)
    }
}

@Composable
private fun EffectPreview(effect: CanimationEffect) {
    val visible = rememberLoopingVisibility(initialDelayMs = 300, visibleDurationMs = 1800)
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
        modifier = Modifier
            .size(56.dp)
            .canimation(visible = visible, effect = effect),
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("\u2726", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun TransitionPreview(enter: CanimationEffect?) {
    val visible = rememberLoopingVisibility(initialDelayMs = 300, visibleDurationMs = 1800)
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)),
        modifier = Modifier.size(56.dp).let { base ->
            if (enter != null) base.canimation(visible = visible, effect = enter) else base
        },
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("\u21C4", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun StaggerPreview(effect: CanimationEffect) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(3) { index ->
            val visible = rememberLoopingVisibility(
                initialDelayMs = 300L + index * 120L,
                visibleDurationMs = 2000,
            )
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .size(width = 28.dp, height = 36.dp)
                    .canimation(visible = visible, effect = effect),
            ) {}
        }
    }
}

@Composable
private fun EmphasisPreview(preset: CanimationPreset) {
    var active by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(400)
        while (true) {
            active = true
            delay(1600)
            active = false
            delay(600)
        }
    }
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
        modifier = Modifier
            .size(56.dp)
            .canimationEmphasize(active = active, preset = preset),
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("A", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun PresetPreview(preset: CanimationPreset) {
    val visible = rememberLoopingVisibility(initialDelayMs = 300, visibleDurationMs = 1800)
    Box(modifier = Modifier.size(56.dp), contentAlignment = Alignment.Center) {
        CanimationVisibility(
            visible = visible,
            enterPreset = preset,
            exitPreset = preset,
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
            ) {
                Box(Modifier.size(48.dp))
            }
        }
    }
}

@Composable
private fun ComponentPreview(componentKey: ComponentDemoKey?) {
    if (componentKey == null) return
    val demo = componentDemos[componentKey]
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)),
            contentAlignment = Alignment.Center,
        ) {
            demo?.invoke()
        }
    }
}

@Composable
private fun rememberLoopingVisibility(
    initialDelayMs: Long,
    visibleDurationMs: Long,
    hiddenDurationMs: Long = 600,
): Boolean {
    return produceState(
        initialValue = false,
        key1 = initialDelayMs,
        key2 = visibleDurationMs,
        key3 = hiddenDurationMs,
    ) {
        delay(initialDelayMs)
        while (true) {
            value = true
            delay(visibleDurationMs)
            value = false
            delay(hiddenDurationMs)
        }
    }.value
}

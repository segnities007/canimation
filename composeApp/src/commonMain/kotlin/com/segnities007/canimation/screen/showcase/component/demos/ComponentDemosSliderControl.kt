package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.allowsLoopingMotion
import io.github.canimation.core.LocalCanimationContext

@Composable
fun SliderControl(modifier: Modifier = Modifier) {
    val progress = remember { Animatable(0f) }
    val loopingMotionEnabled = LocalCanimationContext.current.level.allowsLoopingMotion()

    LaunchedEffect(loopingMotionEnabled) {
        if (!loopingMotionEnabled) {
            progress.snapTo(0.5f)
            return@LaunchedEffect
        }
        while (true) {
            progress.animateTo(1f, tween(3000))
            progress.animateTo(0f, tween(3000))
        }
    }

    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Volume", style = MaterialTheme.typography.labelMedium)
        Slider(value = progress.value, onValueChange = {}, modifier = Modifier.fillMaxWidth())
    }
}

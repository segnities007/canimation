package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@Composable
fun AnimatedFileUpload(modifier: Modifier = Modifier) {
    val states = listOf("idle", "uploading", "done")
    var stateIdx by remember { mutableIntStateOf(0) }
    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            stateIdx = 0; delay(1000)
            stateIdx = 1; progress.animateTo(1f, tween(2000)); delay(300)
            stateIdx = 2; delay(1500)
            progress.snapTo(0f)
        }
    }
    Column(modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        val icon: ImageVector = when (stateIdx) {
            0 -> Icons.Default.Folder
            1 -> Icons.Default.Upload
            else -> Icons.Default.CheckCircle
        }
        Icon(imageVector = icon, contentDescription = null, tint = if (stateIdx == 2) Color(0xFF22C55E) else MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(30.dp))
        Text(
            when (stateIdx) { 0 -> "Drop file here"; 1 -> "Uploading ${(progress.value * 100).toInt()}%"; else -> "Upload complete!" },
            style = MaterialTheme.typography.labelMedium,
            color = if (stateIdx == 2) Color(0xFF22C55E) else MaterialTheme.colorScheme.onSurfaceVariant,
        )
        if (stateIdx == 1) {
            Box(Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)).background(MaterialTheme.colorScheme.surfaceVariant)) {
                Box(Modifier.fillMaxWidth(progress.value).fillMaxHeight().clip(RoundedCornerShape(3.dp)).background(MaterialTheme.colorScheme.primary))
            }
        }
    }
}

@Composable
fun AnimatedVote(modifier: Modifier = Modifier) {
    var upCount by remember { mutableIntStateOf(42) }
    var downCount by remember { mutableIntStateOf(8) }
    var upVis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1800)
            upCount++; upVis = false; delay(30); upVis = true; delay(1800)
            downCount++; upVis = false; delay(30); upVis = true
        }
    }
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primaryContainer, modifier = Modifier.clickable { upCount++ }) {
            Row(Modifier.padding(horizontal = 12.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Icon(Icons.Default.KeyboardArrowUp, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Box(Modifier.canimation(visible = upVis, effect = Canimation.Scale.Pop)) {
                    Text(upCount.toString(), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(Modifier.width(12.dp))
        Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.errorContainer, modifier = Modifier.clickable { downCount++ }) {
            Row(Modifier.padding(horizontal = 12.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                Text(downCount.toString(), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            }
        }
    }
}

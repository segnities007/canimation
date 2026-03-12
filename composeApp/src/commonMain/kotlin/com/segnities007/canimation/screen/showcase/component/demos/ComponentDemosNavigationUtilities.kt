package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardCommandKey
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.LoopingIntStep
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingIntSequence
import com.segnities007.canimation.compose.rememberLoopingIndex
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import canimation.composeapp.generated.resources.*

@Composable
fun SideMenuReveal(modifier: Modifier = Modifier) {
    val items = listOf(
        stringResource(Res.string.component_menu_dashboard),
        stringResource(Res.string.component_menu_analytics),
        stringResource(Res.string.component_menu_settings),
        stringResource(Res.string.component_menu_logout),
    )
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        items.forEachIndexed { index, item ->
            val visible = rememberDelayedEntryVisibility(entryDelayMillis = index * 120L)
            Row(
                modifier = Modifier.fillMaxWidth().canimation(visible = visible, effect = Canimation.Slide.LeftSubtle).background(if (index == 0) MaterialTheme.colorScheme.primaryContainer else Color.Transparent, RoundedCornerShape(8.dp)).padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primary.copy(alpha = if (index == 0) 1f else 0.15f), modifier = Modifier.width(8.dp).height(8.dp)) {}
                Text(item, style = MaterialTheme.typography.bodyMedium, fontWeight = if (index == 0) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Normal)
            }
        }
    }
}

@Composable
fun PaginationDots(modifier: Modifier = Modifier) {
    val total = 5
    val active = rememberLoopingIndex(
        itemCount = total,
        initialDelayMillis = 1200L,
        stepDelayMillis = 1200L,
    )
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        repeat(total) { index ->
            val isActive = index == active
            val width by animateFloatAsState(if (isActive) 24f else 8f, tween(300))
            val color by animateColorAsState(if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant)
            Box(Modifier.padding(horizontal = 3.dp).height(8.dp).width(width.dp).background(color, RoundedCornerShape(4.dp)))
        }
    }
}

@Composable
fun CommandPalette(modifier: Modifier = Modifier) {
    val items = listOf(
        stringResource(Res.string.component_command_open_file),
        stringResource(Res.string.component_command_go_to_line),
        stringResource(Res.string.component_command_toggle_theme),
        stringResource(Res.string.component_command_run_build),
    )
    val highlighted = rememberLoopingIntSequence(
        initialValue = -1,
        steps = buildList {
            items.indices.forEach { index ->
                add(LoopingIntStep(value = index, holdDurationMillis = 800L))
            }
            add(LoopingIntStep(value = -1, holdDurationMillis = 500L))
        },
    )
    Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.surface, border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant), modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Column {
            Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.KeyboardCommandKey, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.width(8.dp))
                Text(stringResource(Res.string.component_type_a_command), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f))
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            items.forEachIndexed { index, item ->
                val background by animateColorAsState(if (index == highlighted) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
                Text(item, modifier = Modifier.fillMaxWidth().background(background).padding(horizontal = 12.dp, vertical = 10.dp), style = MaterialTheme.typography.bodyMedium, color = if (index == highlighted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import canimation.composeapp.generated.resources.*
import com.segnities007.canimation.compose.rememberLoopingIndex
import com.segnities007.canimation.compose.rememberLoopingToggle
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

private data class BottomNavItem(
    val icon: ImageVector,
    val label: StringResource,
)

@Composable
fun MusicPlayerCard(modifier: Modifier = Modifier) {
    val playing = rememberLoopingToggle(toggleDelayMillis = 2000L)
    val progress = remember { Animatable(0f) }
    LaunchedEffect(playing) {
        if (playing) progress.animateTo(1f, tween(4000)) else progress.snapTo(0f)
    }

    Box(modifier.fillMaxWidth().padding(16.dp)) {
        Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.inverseSurface) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primary, modifier = Modifier.size(40.dp)) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.MusicNote, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(20.dp))
                        }
                    }
                    Spacer(Modifier.size(10.dp))
                    Column(Modifier.weight(1f)) {
                        Text(stringResource(Res.string.component_track_title), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.inverseOnSurface)
                        Text(stringResource(Res.string.component_track_artist), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.6f))
                    }
                    Icon(
                        imageVector = if (playing) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inverseOnSurface,
                        modifier = Modifier.clickable {}.size(20.dp),
                    )
                }
                Box(Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)).background(MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.1f))) {
                    Box(Modifier.fillMaxWidth(progress.value).fillMaxHeight().clip(RoundedCornerShape(2.dp)).background(MaterialTheme.colorScheme.inversePrimary))
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(modifier: Modifier = Modifier) {
    val items = listOf(
        BottomNavItem(Icons.Default.Home, Res.string.examples_nav_home),
        BottomNavItem(Icons.Default.Search, Res.string.examples_nav_search),
        BottomNavItem(Icons.Default.Favorite, Res.string.examples_nav_saved),
        BottomNavItem(Icons.Default.Person, Res.string.examples_nav_profile),
    )
    val selected = rememberLoopingIndex(itemCount = items.size, initialDelayMillis = 1500L, stepDelayMillis = 1500L)

    Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.surfaceVariant, modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            items.forEachIndexed { index, item ->
                val isSelected = index == selected
                val scale by animateFloatAsState(if (isSelected) 1.15f else 1f, spring())
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.graphicsLayer { scaleX = scale; scaleY = scale }) {
                    Icon(item.icon, contentDescription = null, tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(if (isSelected) 20.dp else 16.dp))
                    if (isSelected) {
                        Text(stringResource(item.label), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontSize = 9.sp)
                    }
                }
            }
        }
    }
}

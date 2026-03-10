package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.examples_action_attach
import canimation.composeapp.generated.resources.examples_action_camera
import canimation.composeapp.generated.resources.examples_action_note
import com.segnities007.canimation.compose.rememberDelayedToggleVisibility
import com.segnities007.canimation.compose.rememberLoopingToggle
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

private data class FabActionItem(
    val icon: ImageVector,
    val label: StringResource,
)

@Composable
fun AnimatedFab(modifier: Modifier = Modifier) {
    val autoExpanded = rememberLoopingToggle(
        initialValue = false,
        initialDelayMillis = 1500L,
        toggleDelayMillis = 1500L,
    )
    var expanded by remember { mutableStateOf(autoExpanded) }

    LaunchedEffect(autoExpanded) {
        expanded = autoExpanded
    }

    val rotation by androidx.compose.animation.core.animateFloatAsState(
        targetValue = if (expanded) 45f else 0f,
        animationSpec = androidx.compose.animation.core.spring(stiffness = 300f),
    )
    val actions = listOf(
        FabActionItem(Icons.Default.CameraAlt, Res.string.examples_action_camera),
        FabActionItem(Icons.Default.AttachFile, Res.string.examples_action_attach),
        FabActionItem(Icons.Default.NoteAlt, Res.string.examples_action_note),
    )

    Box(modifier = modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            if (expanded) {
                actions.forEachIndexed { index, action ->
                    val itemVisible = rememberDelayedToggleVisibility(triggerVisible = expanded, enterDelayMillis = index * 100L)
                    Row(
                        modifier = Modifier.canimation(visible = itemVisible, effect = Canimation.Scale.Pop),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = stringResource(action.label),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            modifier = Modifier.size(40.dp),
                        ) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Icon(action.icon, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondaryContainer)
                            }
                        }
                    }
                }
            }

            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(56.dp).clickable { expanded = !expanded },
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().graphicsLayer { rotationZ = rotation },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}

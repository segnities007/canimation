package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_tag_animation
import canimation.composeapp.generated.resources.component_tag_compose
import canimation.composeapp.generated.resources.component_tag_design
import canimation.composeapp.generated.resources.component_tag_effect
import canimation.composeapp.generated.resources.component_tag_kotlin
import canimation.composeapp.generated.resources.component_tag_motion
import canimation.composeapp.generated.resources.component_tag_multiplatform
import canimation.composeapp.generated.resources.component_tag_ui
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedTagCloud(modifier: Modifier = Modifier) {
    val tags = listOf(
        stringResource(Res.string.component_tag_kotlin),
        stringResource(Res.string.component_tag_compose),
        stringResource(Res.string.component_tag_animation),
        stringResource(Res.string.component_tag_multiplatform),
        stringResource(Res.string.component_tag_ui),
        stringResource(Res.string.component_tag_design),
        stringResource(Res.string.component_tag_motion),
        stringResource(Res.string.component_tag_effect),
    )
    Column(modifier.fillMaxWidth().padding(16.dp)) {
        @OptIn(ExperimentalLayoutApi::class)
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            tags.forEachIndexed { index, tag ->
                val visible = rememberDelayedEntryVisibility(entryDelayMillis = index * 80L)
                Box(Modifier.canimation(visible = visible, effect = Canimation.Float.ScaleUp)) {
                    Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.secondaryContainer) {
                        Text(
                            tag,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnimatedCodeBlock(modifier: Modifier = Modifier) {
    val lines = listOf(
        "val effect = Canimation.Fade.Up",
        "Modifier.canimation(",
        "    visible = isVisible,",
        "    effect = effect,",
        ")",
    )
    Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFF1E1E2E), modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Column(Modifier.padding(12.dp)) {
            lines.forEachIndexed { index, line ->
                val visible = rememberDelayedEntryVisibility(entryDelayMillis = index * 150L)
                Box(Modifier.canimation(visible = visible, effect = Canimation.Slide.LeftSubtle)) {
                    Text(
                        line,
                        fontFamily = FontFamily.Monospace,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFA6E3A1),
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedKpi(modifier: Modifier = Modifier) {
    val kpis = listOf("Users" to "1,240", "Orders" to "358", "Revenue" to "$8.2K")
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
        kpis.forEachIndexed { index, (label, value) ->
            val visible = rememberDelayedEntryVisibility(entryDelayMillis = index * 150L)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.canimation(visible = visible, effect = Canimation.Rise.Scale),
            ) {
                Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun AnimatedListItem(modifier: Modifier = Modifier) {
    val items = listOf("Design System", "Components", "Animations", "Tokens", "Patterns")
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items.forEachIndexed { index, item ->
            val visible = rememberDelayedEntryVisibility(entryDelayMillis = index * 100L)
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth().canimation(visible = visible, effect = Canimation.Tilt.Left),
            ) {
                Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f), modifier = Modifier.size(8.dp)) {}
                    Spacer(Modifier.width(12.dp))
                    Text(item, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

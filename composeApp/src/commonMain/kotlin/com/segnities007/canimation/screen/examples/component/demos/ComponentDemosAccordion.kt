package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_accordion_body_1
import canimation.composeapp.generated.resources.component_accordion_body_2
import canimation.composeapp.generated.resources.component_accordion_body_3
import canimation.composeapp.generated.resources.component_accordion_title_1
import canimation.composeapp.generated.resources.component_accordion_title_2
import canimation.composeapp.generated.resources.component_accordion_title_3
import com.segnities007.canimation.compose.LoopingIntStep
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingIntSequence
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedAccordion(modifier: Modifier = Modifier) {
    val autoOpenIdx = rememberLoopingIntSequence(
        initialValue = -1,
        steps = listOf(
            LoopingIntStep(value = 0, holdDurationMillis = 2000L),
            LoopingIntStep(value = 1, holdDurationMillis = 2000L),
            LoopingIntStep(value = 2, holdDurationMillis = 2000L),
            LoopingIntStep(value = -1, holdDurationMillis = 1000L),
        ),
    )
    var openIdx by remember { mutableIntStateOf(autoOpenIdx) }

    LaunchedEffect(autoOpenIdx) {
        openIdx = autoOpenIdx
    }

    val items = listOf(
        stringResource(Res.string.component_accordion_title_1) to stringResource(Res.string.component_accordion_body_1),
        stringResource(Res.string.component_accordion_title_2) to stringResource(Res.string.component_accordion_body_2),
        stringResource(Res.string.component_accordion_title_3) to stringResource(Res.string.component_accordion_body_3),
    )

    Column(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items.forEachIndexed { index, (title, content) ->
            val isOpen = index == openIdx
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (isOpen) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f) else MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(
                    1.dp,
                    if (isOpen) MaterialTheme.colorScheme.primary.copy(alpha = 0.3f) else Color.Transparent,
                ),
                modifier = Modifier.fillMaxWidth().clickable { openIdx = if (openIdx == index) -1 else index },
            ) {
                Column(Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Icon(
                            imageVector = if (isOpen) Icons.Default.Remove else Icons.Default.Add,
                            contentDescription = null,
                        )
                    }
                    if (isOpen) {
                        val visible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
                        Text(
                            text = content,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .canimation(visible = visible, effect = Canimation.Fade.Up),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

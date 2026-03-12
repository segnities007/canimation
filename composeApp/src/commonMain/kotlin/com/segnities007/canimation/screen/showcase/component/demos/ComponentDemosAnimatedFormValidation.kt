package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.demo_email
import canimation.composeapp.generated.resources.demo_valid_email
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingIndex
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedFormValidation(modifier: Modifier = Modifier) {
    val states = listOf("empty", "typing", "valid", "invalid")
    val stateIdx = rememberLoopingIndex(
        itemCount = states.size,
        initialDelayMillis = 1500L,
        stepDelayMillis = 1500L,
    )
    val borderColor by animateColorAsState(
        targetValue = when (stateIdx) {
            2 -> Color(0xFF22C55E)
            3 -> Color(0xFFEF4444)
            else -> MaterialTheme.colorScheme.outline
        },
    )

    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(stringResource(Res.string.demo_email), style = MaterialTheme.typography.labelMedium)
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(1.5.dp, borderColor),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = when (stateIdx) {
                        0 -> ""
                        1 -> "user@exa"
                        2 -> "user@example.com"
                        else -> "invalid-email"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily.Monospace,
                    color = if (stateIdx == 0) {
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                    modifier = Modifier.weight(1f),
                )
                when (stateIdx) {
                    2 -> Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF22C55E), modifier = Modifier.size(16.dp))
                    3 -> Icon(Icons.Default.Close, contentDescription = null, tint = Color(0xFFEF4444), modifier = Modifier.size(16.dp))
                    else -> Unit
                }
            }
        }
        if (stateIdx == 3) {
            val visible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
            Text(
                stringResource(Res.string.demo_valid_email),
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFEF4444),
                modifier = Modifier.canimation(visible = visible, effect = Canimation.Micro.NudgeUp),
            )
        }
    }
}

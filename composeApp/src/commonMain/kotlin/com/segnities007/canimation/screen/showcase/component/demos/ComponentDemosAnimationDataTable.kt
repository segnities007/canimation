package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_duration
import canimation.composeapp.generated.resources.component_duration_18ms
import canimation.composeapp.generated.resources.component_duration_23ms
import canimation.composeapp.generated.resources.component_duration_32ms
import canimation.composeapp.generated.resources.component_duration_45ms
import canimation.composeapp.generated.resources.component_effect
import canimation.composeapp.generated.resources.component_row_bounce
import canimation.composeapp.generated.resources.component_row_fade
import canimation.composeapp.generated.resources.component_row_scale
import canimation.composeapp.generated.resources.component_row_spring
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun DataTable(modifier: Modifier = Modifier) {
    val rows = listOf(
        stringResource(Res.string.component_row_fade) to stringResource(Res.string.component_duration_23ms),
        stringResource(Res.string.component_row_scale) to stringResource(Res.string.component_duration_18ms),
        stringResource(Res.string.component_row_bounce) to stringResource(Res.string.component_duration_45ms),
        stringResource(Res.string.component_row_spring) to stringResource(Res.string.component_duration_32ms),
    )

    Column(modifier.fillMaxWidth().padding(16.dp)) {
        Row(Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
            Text(
                text = stringResource(Res.string.component_effect),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = stringResource(Res.string.component_duration),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        rows.forEachIndexed { index, (name, duration) ->
            val visible = rememberDelayedEntryVisibility(entryDelayMillis = index * 100L)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .canimation(visible = visible, effect = Canimation.Fade.Up)
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(name, Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                Surface(shape = RoundedCornerShape(4.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                    Text(
                        text = duration,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontFamily = FontFamily.Monospace,
                    )
                }
            }
        }
    }
}

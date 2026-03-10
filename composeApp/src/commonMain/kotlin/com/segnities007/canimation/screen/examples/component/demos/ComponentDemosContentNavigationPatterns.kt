package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import canimation.composeapp.generated.resources.component_breadcrumb_electronics
import canimation.composeapp.generated.resources.component_breadcrumb_home
import canimation.composeapp.generated.resources.component_breadcrumb_phones
import canimation.composeapp.generated.resources.component_breadcrumb_products
import canimation.composeapp.generated.resources.component_timeline_created
import canimation.composeapp.generated.resources.component_timeline_in_progress
import canimation.composeapp.generated.resources.component_timeline_jan_1
import canimation.composeapp.generated.resources.component_timeline_jan_10
import canimation.composeapp.generated.resources.component_timeline_jan_12
import canimation.composeapp.generated.resources.component_timeline_jan_5
import canimation.composeapp.generated.resources.component_timeline_review
import canimation.composeapp.generated.resources.component_timeline_shipped
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedBreadcrumb(modifier: Modifier = Modifier) {
    val items = listOf(
        stringResource(Res.string.component_breadcrumb_home),
        stringResource(Res.string.component_breadcrumb_products),
        stringResource(Res.string.component_breadcrumb_electronics),
        stringResource(Res.string.component_breadcrumb_phones),
    )
    Row(modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        items.forEachIndexed { index, label ->
            val visible = rememberDelayedEntryVisibility(entryDelayMillis = index * 120L)
            Box(Modifier.canimation(visible = visible, effect = Canimation.Slide.LeftSubtle)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (index == items.lastIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = if (index == items.lastIndex) FontWeight.Bold else FontWeight.Normal,
                )
            }
            if (index < items.lastIndex) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(horizontal = 4.dp).size(16.dp),
                )
            }
        }
    }
}

@Composable
fun AnimatedTimeline(modifier: Modifier = Modifier) {
    val events = listOf(
        stringResource(Res.string.component_timeline_created) to stringResource(Res.string.component_timeline_jan_1),
        stringResource(Res.string.component_timeline_in_progress) to stringResource(Res.string.component_timeline_jan_5),
        stringResource(Res.string.component_timeline_review) to stringResource(Res.string.component_timeline_jan_10),
        stringResource(Res.string.component_timeline_shipped) to stringResource(Res.string.component_timeline_jan_12),
    )
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        events.forEachIndexed { index, (title, date) ->
            val visible = rememberDelayedEntryVisibility(entryDelayMillis = index * 200L)
            Row(
                modifier = Modifier.canimation(visible = visible, effect = Canimation.Rise.In),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Surface(
                    shape = androidx.compose.foundation.shape.CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(10.dp),
                ) {}
                Column {
                    Text(title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                    Text(date, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
fun AnimatedSearchResult(modifier: Modifier = Modifier) {
    val results = listOf(
        "Canimation.Fade.Up" to "Smooth fade-in from below",
        "Canimation.Scale.Pop" to "Pop scale entry effect",
        "Canimation.Bounce.In" to "Bouncy entrance animation",
    )
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        results.forEachIndexed { index, (title, desc) ->
            val visible = rememberDelayedEntryVisibility(entryDelayMillis = index * 120L)
            Column(Modifier.fillMaxWidth().canimation(visible = visible, effect = Canimation.Float.Gentle)) {
                Text(title, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                Text(desc, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                if (index < results.lastIndex) {
                    HorizontalDivider(Modifier.padding(top = 6.dp), color = MaterialTheme.colorScheme.outlineVariant)
                }
            }
        }
    }
}

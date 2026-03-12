package com.segnities007.canimation.screen.docs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

@Composable
internal fun AtomicLevelRow(level: String, description: String, examples: String) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            level,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.widthIn(min = 80.dp),
        )
        Column {
            Text(description, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
            Text(examples, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontFamily = FontFamily.Monospace)
        }
    }
}

@Composable
internal fun ApiCard(
    signature: String,
    badge: String,
    desc: String,
    code: String,
    icon: ImageVector? = null,
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp),
                    )
                }
                Text(
                    signature,
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                )
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                ) {
                    Text(badge, Modifier.padding(horizontal = 8.dp, vertical = 2.dp), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                }
            }
            Text(desc, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CodeBlock(code)
        }
    }
}

@Composable
internal fun NamespaceRow(namespace: String, members: List<String>) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(namespace, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.widthIn(min = 140.dp))
        Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.outlineVariant, modifier = Modifier.size(16.dp))
        Row(Modifier.horizontalScroll(androidx.compose.foundation.rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            members.forEach { member ->
                Surface(shape = RoundedCornerShape(4.dp), color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f)) {
                    Text(".${member}", Modifier.padding(horizontal = 8.dp, vertical = 3.dp), fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.tertiary)
                }
            }
        }
    }
}

@Composable
internal fun ModuleRow(name: String, desc: String, icon: ImageVector) {
    val visible = rememberDelayedEntryVisibility(entryDelayMillis = 200L)
    Row(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp)
            .canimation(visible = visible, effect = Canimation.Fade.Up),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
        Column {
            Text(name, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Text(desc, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

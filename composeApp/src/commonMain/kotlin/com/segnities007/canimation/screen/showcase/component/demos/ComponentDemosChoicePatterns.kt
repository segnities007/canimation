package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import com.segnities007.canimation.compose.rememberLoopingIndex

@Composable
fun AnimatedRating(modifier: Modifier = Modifier) {
    val rating = rememberLoopingIndex(
        itemCount = 5,
        initialIndex = 0,
        initialDelayMillis = 1000L,
        stepDelayMillis = 1000L,
    ) + 1

    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        (1..5).forEach { star ->
            val visible = star <= rating
            Box(
                modifier = Modifier.size(32.dp).canimation(visible = visible, effect = Canimation.Scale.Pop),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = if (star <= rating) Icons.Default.Star else Icons.Default.StarOutline,
                    contentDescription = null,
                    tint = if (star <= rating) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.size(22.dp),
                )
            }
        }
    }
}

@Composable
fun ColorSwatchPicker(modifier: Modifier = Modifier) {
    val swatches = listOf(
        Color(0xFFEF4444),
        Color(0xFFF97316),
        Color(0xFFEAB308),
        Color(0xFF22C55E),
        Color(0xFF3B82F6),
        Color(0xFF8B5CF6),
    )
    val selected = rememberLoopingIndex(
        itemCount = swatches.size,
        initialIndex = 0,
        initialDelayMillis = 900L,
        stepDelayMillis = 900L,
    )

    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    ) {
        swatches.forEachIndexed { index, color ->
            val isSelected = index == selected
            Surface(
                shape = CircleShape,
                color = color,
                modifier = Modifier
                    .size(if (isSelected) 40.dp else 32.dp)
                    .canimation(visible = isSelected, effect = Canimation.Elastic.Snap),
                border = if (isSelected) BorderStroke(3.dp, MaterialTheme.colorScheme.onSurface) else null,
            ) {}
        }
    }
}

@Composable
fun AnimatedChipGroup(modifier: Modifier = Modifier) {
    val chips = listOf("All", "Active", "Completed", "Archived")
    val selected = rememberLoopingIndex(
        itemCount = chips.size,
        initialIndex = 0,
        initialDelayMillis = 1200L,
        stepDelayMillis = 1200L,
    )

    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        chips.forEachIndexed { index, label ->
            val isSelected = index == selected
            val backgroundColor = animateColorAsState(
                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            )
            val textColor = animateColorAsState(
                if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = backgroundColor.value,
                modifier = Modifier.clickable {},
            ) {
                Text(
                    text = label,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = textColor.value,
                )
            }
        }
    }
}

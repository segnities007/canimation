package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

@Composable
fun StackedAvatars(modifier: Modifier = Modifier) {
    val colors = listOf(Color(0xFF6366F1), Color(0xFFEC4899), Color(0xFF14B8A6), Color(0xFFF59E0B), Color(0xFF8B5CF6))

    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center) {
        colors.forEachIndexed { index, color ->
            val visible = rememberDelayedEntryVisibility(entryDelayMillis = index * 100L)
            Box(
                Modifier
                    .offset(x = (-(index * 12)).dp)
                    .canimation(visible = visible, effect = Canimation.Diagonal.Subtle),
            ) {
                Surface(
                    shape = CircleShape,
                    color = color,
                    modifier = Modifier.size(40.dp),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.surface),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("${('A'.code + index).toChar()}", color = Color.White, style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
    }
}

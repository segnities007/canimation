package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_day_f
import canimation.composeapp.generated.resources.component_day_m
import canimation.composeapp.generated.resources.component_day_s
import canimation.composeapp.generated.resources.component_day_t
import canimation.composeapp.generated.resources.component_day_w
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import org.jetbrains.compose.resources.stringResource

@Composable
fun MiniBarChart(modifier: Modifier = Modifier) {
    val values = listOf(0.4f, 0.7f, 0.5f, 0.9f, 0.6f, 0.8f, 0.3f)
    val primaryColor = MaterialTheme.colorScheme.primary
    val labels = listOf(
        stringResource(Res.string.component_day_m),
        stringResource(Res.string.component_day_t),
        stringResource(Res.string.component_day_w),
        stringResource(Res.string.component_day_t),
        stringResource(Res.string.component_day_f),
        stringResource(Res.string.component_day_s),
        stringResource(Res.string.component_day_s),
    )

    Column(modifier.fillMaxWidth().padding(16.dp)) {
        Row(
            Modifier.fillMaxWidth().height(60.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom,
        ) {
            values.forEachIndexed { index, value ->
                val visible = rememberDelayedEntryVisibility(entryDelayMillis = index * 100L)
                val height by animateFloatAsState(
                    targetValue = if (visible) value * 60f else 0f,
                    animationSpec = tween(500),
                )
                Box(
                    Modifier
                        .width(16.dp)
                        .height(height.dp)
                        .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                        .background(primaryColor),
                )
            }
        }
        Spacer(Modifier.height(4.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            labels.forEach { label ->
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.width(16.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_off
import canimation.composeapp.generated.resources.component_on
import com.segnities007.canimation.compose.rememberLoopingIndex
import com.segnities007.canimation.compose.rememberLoopingToggle
import org.jetbrains.compose.resources.stringResource

@Composable
fun SegmentedControl(modifier: Modifier = Modifier) {
    val options = listOf("Day", "Week", "Month")
    val autoSelected = rememberLoopingIndex(
        itemCount = options.size,
        initialDelayMillis = 1500L,
        stepDelayMillis = 1500L,
    )
    var selected by remember { mutableIntStateOf(autoSelected) }

    LaunchedEffect(autoSelected) {
        selected = autoSelected
    }

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.fillMaxWidth().padding(16.dp),
    ) {
        Row(Modifier.padding(4.dp)) {
            options.forEachIndexed { index, option ->
                val isSelected = index == selected
                val background by animateColorAsState(
                    if (isSelected) MaterialTheme.colorScheme.surface else Color.Transparent,
                )
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = background,
                    modifier = Modifier.weight(1f).clickable { selected = index },
                    tonalElevation = if (isSelected) 2.dp else 0.dp,
                ) {
                    Text(
                        option,
                        Modifier.padding(vertical = 8.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedSwitch(modifier: Modifier = Modifier) {
    val autoEnabled = rememberLoopingToggle(
        initialValue = false,
        initialDelayMillis = 1800L,
        toggleDelayMillis = 1800L,
    )
    var enabled by remember { mutableStateOf(autoEnabled) }

    LaunchedEffect(autoEnabled) {
        enabled = autoEnabled
    }

    val thumbOffset by animateFloatAsState(if (enabled) 28f else 4f, spring(dampingRatio = 0.6f))
    val trackColor by animateColorAsState(
        if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
    )

    Row(
        modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            stringResource(Res.string.component_off),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.width(12.dp))
        Box(
            Modifier
                .width(52.dp)
                .height(28.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(trackColor)
                .clickable { enabled = !enabled },
        ) {
            Box(
                Modifier
                    .offset(x = thumbOffset.dp)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.White),
            )
        }
        Spacer(Modifier.width(12.dp))
        Text(
            stringResource(Res.string.component_on),
            style = MaterialTheme.typography.labelMedium,
            color = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = if (enabled) FontWeight.Bold else FontWeight.Normal,
        )
    }
}

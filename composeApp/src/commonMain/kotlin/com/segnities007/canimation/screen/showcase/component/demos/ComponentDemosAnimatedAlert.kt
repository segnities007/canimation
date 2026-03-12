package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_alert_error
import canimation.composeapp.generated.resources.component_alert_success
import canimation.composeapp.generated.resources.component_alert_warning
import canimation.composeapp.generated.resources.component_operation_completed
import com.segnities007.canimation.compose.rememberLoopingIndex
import com.segnities007.canimation.compose.rememberReplayVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedAlert(modifier: Modifier = Modifier) {
    val alerts = listOf(
        stringResource(Res.string.component_alert_success) to Color(0xFF22C55E),
        stringResource(Res.string.component_alert_warning) to Color(0xFFF59E0B),
        stringResource(Res.string.component_alert_error) to Color(0xFFEF4444),
    )
    val index = rememberLoopingIndex(
        itemCount = alerts.size,
        initialDelayMillis = 2000L,
        stepDelayMillis = 2000L,
    )
    val visible = rememberReplayVisibility(replayKey = index, resetDurationMillis = 50L)
    val (label, color) = alerts[index]

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .canimation(visible = visible, effect = Canimation.Slide.LeftSubtle),
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = color.copy(alpha = 0.1f),
            border = BorderStroke(1.dp, color.copy(alpha = 0.3f)),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(shape = CircleShape, color = color, modifier = Modifier.size(8.dp)) {}
                Spacer(Modifier.size(10.dp))
                Text(
                    text = stringResource(Res.string.component_operation_completed, label),
                    style = MaterialTheme.typography.bodySmall,
                    color = color,
                )
            }
        }
    }
}

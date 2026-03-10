package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_percent_value
import com.segnities007.canimation.compose.LoopingFloatStep
import com.segnities007.canimation.compose.rememberLoopingAnimatedFloat
import org.jetbrains.compose.resources.stringResource

@Composable
fun DonutChart(modifier: Modifier = Modifier) {
    val progress = rememberLoopingAnimatedFloat(
        initialValue = 0f,
        steps = listOf(
            LoopingFloatStep(value = 0.72f, animationDurationMillis = 1200, holdDurationMillis = 2000L),
            LoopingFloatStep(value = 0f, animationDurationMillis = 600, holdDurationMillis = 400L),
        ),
    )
    val primaryColor = MaterialTheme.colorScheme.primary
    val trackColor = MaterialTheme.colorScheme.surfaceVariant

    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(80.dp)) {
            drawArc(trackColor, 0f, 360f, false, style = Stroke(12f, cap = StrokeCap.Round))
            drawArc(primaryColor, -90f, 360f * progress, false, style = Stroke(12f, cap = StrokeCap.Round))
        }
        Text(
            text = stringResource(Res.string.component_percent_value, (progress * 100).toInt()),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_performance
import org.jetbrains.compose.resources.stringResource
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun SparkLine(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = (2 * PI).toFloat(),
        animationSpec = infiniteRepeatable(tween(4000, easing = LinearEasing), RepeatMode.Restart),
    )
    val primaryColor = MaterialTheme.colorScheme.primary

    Column(modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = stringResource(Res.string.component_performance),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(4.dp))
        Canvas(Modifier.fillMaxWidth().height(40.dp)) {
            val width = size.width
            val height = size.height
            val points = 60
            for (index in 0 until points - 1) {
                val x1 = width * index / points
                val x2 = width * (index + 1) / points
                val y1 = height / 2 + sin(phase + index * 0.15f) * height * 0.35f
                val y2 = height / 2 + sin(phase + (index + 1) * 0.15f) * height * 0.35f
                drawLine(primaryColor, Offset(x1, y1), Offset(x2, y2), 2f, StrokeCap.Round)
            }
        }
    }
}

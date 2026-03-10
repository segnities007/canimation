package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_percent_value
import com.segnities007.canimation.compose.allowsLoopingMotion
import io.github.canimation.core.LocalCanimationContext
import org.jetbrains.compose.resources.stringResource

@Composable
fun GaugeChart(modifier: Modifier = Modifier) {
    val loopingMotionEnabled = LocalCanimationContext.current.level.allowsLoopingMotion()
    val value: Float

    if (loopingMotionEnabled) {
        val transition = rememberInfiniteTransition()
        val animatedValue by transition.animateFloat(0f, 0.75f, infiniteRepeatable(tween(3000), RepeatMode.Reverse))
        value = animatedValue
    } else {
        value = 0.75f
    }
    val primaryColor = MaterialTheme.colorScheme.primary

    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(80.dp, 50.dp)) {
            val sweep = 180f
            drawArc(
                primaryColor.copy(alpha = 0.1f),
                180f,
                sweep,
                false,
                style = Stroke(8f, cap = StrokeCap.Round),
                topLeft = Offset(8f, 8f),
                size = Size(size.width - 16f, (size.height - 8f) * 2),
            )
            drawArc(
                primaryColor,
                180f,
                sweep * value,
                false,
                style = Stroke(8f, cap = StrokeCap.Round),
                topLeft = Offset(8f, 8f),
                size = Size(size.width - 16f, (size.height - 8f) * 2),
            )
        }
        Text(
            text = stringResource(Res.string.component_percent_value, (value * 100).toInt()),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}

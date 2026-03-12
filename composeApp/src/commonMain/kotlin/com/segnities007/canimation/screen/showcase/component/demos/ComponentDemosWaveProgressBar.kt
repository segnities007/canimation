package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.allowsLoopingMotion
import io.github.canimation.core.LocalCanimationContext
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun WaveProgressBar(modifier: Modifier = Modifier) {
    val loopingMotionEnabled = LocalCanimationContext.current.level.allowsLoopingMotion()
    val phase: Float
    val progress: Float

    if (loopingMotionEnabled) {
        val infiniteTransition = rememberInfiniteTransition()
        val animatedPhase by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = (2 * PI).toFloat(),
            animationSpec = infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart),
        )
        val animatedProgress by infiniteTransition.animateFloat(
            initialValue = 0.2f,
            targetValue = 0.9f,
            animationSpec = infiniteRepeatable(tween(3000), RepeatMode.Reverse),
        )
        phase = animatedPhase
        progress = animatedProgress
    } else {
        phase = 0f
        progress = 0.6f
    }
    val primaryColor = MaterialTheme.colorScheme.primary
    val trackColor = MaterialTheme.colorScheme.surfaceVariant

    Canvas(modifier.fillMaxWidth().height(32.dp).padding(16.dp)) {
        val width = size.width
        val height = size.height
        drawRoundRect(color = trackColor, size = size, cornerRadius = CornerRadius(height / 2))
        val filledWidth = width * progress
        for (x in 0..filledWidth.toInt()) {
            val waveY = sin(phase + x * 0.05f) * 2
            drawCircle(color = primaryColor, radius = 1.5f, center = Offset(x.toFloat(), height / 2 + waveY))
        }
    }
}

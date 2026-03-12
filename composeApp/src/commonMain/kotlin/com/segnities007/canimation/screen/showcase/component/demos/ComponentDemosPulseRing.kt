package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.allowsLoopingMotion
import io.github.canimation.core.LocalCanimationContext

@Composable
fun PulseRing(modifier: Modifier = Modifier) {
    val loopingMotionEnabled = LocalCanimationContext.current.level.allowsLoopingMotion()
    val scale: Float
    val alpha: Float

    if (loopingMotionEnabled) {
        val infiniteTransition = rememberInfiniteTransition()
        val animatedScale by infiniteTransition.animateFloat(
            initialValue = 0.5f,
            targetValue = 1.5f,
            animationSpec = infiniteRepeatable(tween(1500, easing = LinearEasing), RepeatMode.Restart),
        )
        val animatedAlpha by infiniteTransition.animateFloat(
            initialValue = 0.7f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(tween(1500, easing = LinearEasing), RepeatMode.Restart),
        )
        scale = animatedScale
        alpha = animatedAlpha
    } else {
        scale = 1f
        alpha = 0.2f
    }
    val primaryColor = MaterialTheme.colorScheme.primary

    Box(modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(80.dp)) {
            drawCircle(
                color = primaryColor.copy(alpha = alpha),
                radius = size.minDimension / 2 * scale,
                style = Stroke(2.dp.toPx()),
            )
        }
        Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp)) {}
    }
}

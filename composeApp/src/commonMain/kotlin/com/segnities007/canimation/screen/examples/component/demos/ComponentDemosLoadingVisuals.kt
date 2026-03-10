package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun OrbitDots(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val angle by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart))
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(64.dp)) {
            val cx = size.width / 2
            val cy = size.height / 2
            val r = size.minDimension / 2.5f
            repeat(6) { i ->
                val a = ((angle + i * 60f) * PI / 180f).toFloat()
                val x = cx + cos(a) * r
                val y = cy + sin(a) * r
                drawCircle(primaryColor.copy(alpha = 0.3f + i * 0.1f), radius = 4f, center = Offset(x, y))
            }
            drawCircle(primaryColor, radius = 6f, center = Offset(cx, cy))
        }
    }
}

@Composable
fun BouncingLoader(modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        repeat(3) { i ->
            val transition = rememberInfiniteTransition()
            val offsetY by transition.animateFloat(
                initialValue = 0f,
                targetValue = -12f,
                animationSpec = infiniteRepeatable(tween(400, delayMillis = i * 150, easing = FastOutSlowInEasing), RepeatMode.Reverse),
            )
            Box(Modifier.padding(horizontal = 4.dp).offset(y = offsetY.dp).size(12.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary))
        }
    }
}

@Composable
fun GlowPulse(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val alpha by transition.animateFloat(0.2f, 0.8f, infiniteRepeatable(tween(1500), RepeatMode.Reverse))
    val scale by transition.animateFloat(0.9f, 1.1f, infiniteRepeatable(tween(1500), RepeatMode.Reverse))
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(60.dp)) {
            drawCircle(primaryColor.copy(alpha = alpha * 0.3f), radius = size.minDimension / 2 * scale)
            drawCircle(primaryColor.copy(alpha = alpha * 0.5f), radius = size.minDimension / 3)
            drawCircle(primaryColor, radius = size.minDimension / 5)
        }
    }
}

@Composable
fun WaveformBars(modifier: Modifier = Modifier) {
    val barCount = 12
    val primaryColor = MaterialTheme.colorScheme.primary
    Row(modifier.fillMaxWidth().height(50.dp).padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.Bottom) {
        repeat(barCount) { i ->
            val transition = rememberInfiniteTransition()
            val height by transition.animateFloat(
                initialValue = 8f,
                targetValue = 36f,
                animationSpec = infiniteRepeatable(tween(300 + i * 50, easing = FastOutSlowInEasing), RepeatMode.Reverse, initialStartOffset = StartOffset(i * 80)),
            )
            Box(Modifier.width(6.dp).height(height.dp).clip(RoundedCornerShape(3.dp)).background(primaryColor.copy(alpha = 0.5f + (i % 3) * 0.15f)))
        }
    }
}

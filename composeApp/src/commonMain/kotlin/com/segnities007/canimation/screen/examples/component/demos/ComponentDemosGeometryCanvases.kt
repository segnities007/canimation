package com.segnities007.canimation.screen.examples.component.demos

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun MorphingShape(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val radius by transition.animateFloat(8f, 32f, infiniteRepeatable(tween(2000), RepeatMode.Reverse))
    val rotation by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(6000, easing = LinearEasing), RepeatMode.Restart))
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(64.dp).graphicsLayer { rotationZ = rotation }) {
            drawRoundRect(primaryColor, cornerRadius = CornerRadius(radius), size = size)
        }
    }
}

@Composable
fun SpiralDots(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val angle by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(4000, easing = LinearEasing), RepeatMode.Restart))
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(64.dp)) {
            val cx = size.width / 2
            val cy = size.height / 2
            repeat(12) { i ->
                val r = 4f + i * 2f
                val a = ((angle + i * 30f) * PI / 180f).toFloat()
                drawCircle(primaryColor.copy(alpha = 0.2f + i * 0.06f), radius = 3f, center = Offset(cx + cos(a) * r, cy + sin(a) * r))
            }
        }
    }
}

@Composable
fun DnaHelixCanvas(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val phase by transition.animateFloat(0f, (2 * PI).toFloat(), infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart))
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    Box(modifier.fillMaxWidth().height(60.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxWidth(0.7f).height(40.dp)) {
            val steps = 20
            for (i in 0 until steps) {
                val x = size.width * i / steps
                val y1 = size.height / 2 + sin(phase + i * 0.5f) * size.height * 0.35f
                val y2 = size.height / 2 - sin(phase + i * 0.5f) * size.height * 0.35f
                drawCircle(primaryColor.copy(alpha = 0.7f), radius = 3f, center = Offset(x, y1))
                drawCircle(secondaryColor.copy(alpha = 0.7f), radius = 3f, center = Offset(x, y2))
                if (i % 3 == 0) drawLine(primaryColor.copy(alpha = 0.15f), Offset(x, y1), Offset(x, y2), strokeWidth = 1f)
            }
        }
    }
}

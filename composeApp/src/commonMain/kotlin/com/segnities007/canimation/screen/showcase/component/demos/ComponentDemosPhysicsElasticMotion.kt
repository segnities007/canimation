package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PendulumSwing(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val angle by transition.animateFloat(
        initialValue = -30f,
        targetValue = 30f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )
    val primary = MaterialTheme.colorScheme.primary

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(80.dp, 100.dp)) {
        val pivotX = size.width / 2
        val rodLength = size.height * 0.8f
        val radians = angle * PI.toFloat() / 180f
        val bobX = pivotX + sin(radians) * rodLength
        val bobY = cos(radians) * rodLength

        drawCircle(primary.copy(alpha = 0.5f), 4.dp.toPx(), Offset(pivotX, 0f))
        drawLine(primary.copy(alpha = 0.4f), Offset(pivotX, 0f), Offset(bobX, bobY), 2.dp.toPx())
        drawCircle(primary, 10.dp.toPx(), Offset(bobX, bobY))
    }
}

@Composable
fun BouncingBall(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val bounce by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(800), repeatMode = RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary
    val y = bounce * bounce
    val shadowScale = 0.5f + (1f - y) * 0.5f

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).size(60.dp, 80.dp)) {
        val centerX = size.width / 2
        val bottom = size.height - 10.dp.toPx()
        val ballY = bottom - y * (size.height - 30.dp.toPx())

        drawOval(
            color = Color.Black.copy(alpha = 0.15f * shadowScale),
            topLeft = Offset(centerX - 12.dp.toPx() * shadowScale, bottom),
            size = Size(24.dp.toPx() * shadowScale, 4.dp.toPx()),
        )
        drawCircle(primary, 12.dp.toPx(), Offset(centerX, ballY))
    }
}

@Composable
fun SlinkySpring(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val stretch by transition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(1000), repeatMode = RepeatMode.Reverse),
    )
    val primary = MaterialTheme.colorScheme.primary

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(60.dp, 80.dp)) {
        val width = size.width
        val height = size.height * stretch
        val coils = 8
        val coilHeight = height / coils
        for (index in 0 until coils) {
            val y = index * coilHeight
            drawLine(
                color = primary.copy(alpha = 0.6f + index * 0.05f),
                start = Offset(10.dp.toPx(), y),
                end = Offset(width - 10.dp.toPx(), y + coilHeight * 0.5f),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Round,
            )
            drawLine(
                color = primary.copy(alpha = 0.6f + index * 0.05f),
                start = Offset(width - 10.dp.toPx(), y + coilHeight * 0.5f),
                end = Offset(10.dp.toPx(), y + coilHeight),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Round,
            )
        }
    }
}

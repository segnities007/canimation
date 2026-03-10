package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.demo_hover_me
import canimation.composeapp.generated.resources.demo_press
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun MagneticButton(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val transition = rememberInfiniteTransition()
    val magnetX by transition.animateFloat(
        initialValue = -8f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(tween(2000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
    )
    val magnetY by transition.animateFloat(
        initialValue = -4f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(tween(1500, easing = FastOutSlowInEasing), RepeatMode.Reverse),
    )

    Box(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Spring.In)
            .size(180.dp, 60.dp),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier.graphicsLayer {
                translationX = magnetX
                translationY = magnetY
            },
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.primary,
            shadowElevation = 6.dp,
        ) {
            Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(Res.string.demo_hover_me),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
fun RippleButton(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    val transition = rememberInfiniteTransition()
    val rippleProgress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1200, easing = FastOutSlowInEasing), RepeatMode.Restart),
    )
    val primary = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Scale.Pop)
            .size(160.dp, 56.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val maxRadius = size.width / 2
            drawRoundRect(primary, cornerRadius = CornerRadius(12.dp.toPx()))
            drawCircle(
                color = Color.White.copy(alpha = (1f - rippleProgress) * 0.4f),
                radius = maxRadius * rippleProgress,
                center = center,
            )
        }
        Text(text = stringResource(Res.string.demo_press), color = Color.White, fontWeight = FontWeight.Bold)
    }
}

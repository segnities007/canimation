package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lens
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingIndex
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

@Composable
fun CardFanStack(
    cardCount: Int = 4,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val spread by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(2000), RepeatMode.Reverse))
    val colors = listOf(
        MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f),
        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
    ).take(cardCount.coerceAtMost(3))

    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).size(160.dp, 100.dp), contentAlignment = Alignment.Center) {
        colors.forEachIndexed { index, color ->
            val angle = (index - 1) * 15f * spread
            Surface(shape = RoundedCornerShape(8.dp), color = color, modifier = Modifier.size(80.dp, 50.dp).graphicsLayer { rotationZ = angle }) {
                Box(contentAlignment = Alignment.Center) {
                    Text("${index + 1}", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun CardMagneticSnap(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val target = rememberLoopingIndex(
        itemCount = 3,
        initialIndex = 1,
        initialDelayMillis = 1500L,
        stepDelayMillis = 1500L,
    )
    val offsetX by animateFloatAsState(when (target) { 0 -> -40f; 1 -> 0f; else -> 40f }, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(160.dp, 80.dp), contentAlignment = Alignment.Center) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            repeat(3) { Box(Modifier.size(8.dp).clip(CircleShape).background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))) }
        }
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
            modifier = Modifier.size(40.dp, 30.dp).graphicsLayer { translationX = offsetX },
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Lens, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer, modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun CoinFlip(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val transition = rememberInfiniteTransition()
    val rotationY by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(1500), RepeatMode.Restart))
    val showFront = kotlin.math.cos(rotationY * kotlin.math.PI.toFloat() / 180f) > 0

    Surface(
        shape = CircleShape,
        color = if (showFront) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(60.dp).graphicsLayer {
            this.rotationY = rotationY
            cameraDistance = 12f * density
        },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(if (showFront) "H" else "T", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}

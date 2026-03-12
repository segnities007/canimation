package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingIndex
import com.segnities007.canimation.compose.rememberLoopingToggle
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun EngagementStats(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val phase = rememberLoopingToggle(toggleDelayMillis = 4000L)
    val stats = listOf("Views" to 12847, "Likes" to 4231, "Shares" to 892)
    Row(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up), horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.Bottom) {
        stats.forEachIndexed { index, (label, target) ->
            var current by remember { mutableIntStateOf(0) }
            LaunchedEffect(phase) {
                current = 0
                delay(index * 200L)
                repeat(40) { step ->
                    val p = FastOutSlowInEasing.transform((step + 1) / 40f)
                    current = (target * p).roundToInt()
                    delay(30)
                }
                current = target
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (current >= 1000) {
                        val k = current / 1000f
                        "${k.toInt()}.${((k - k.toInt()) * 10).roundToInt()}K"
                    } else current.toString(),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace),
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun MultiStateBadge(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val states = listOf("New" to Color(0xFF42A5F5), "Processing" to Color(0xFFFFA726), "Complete" to Color(0xFF66BB6A), "Archived" to Color(0xFF78909C))
    val stateIndex = rememberLoopingIndex(
        itemCount = states.size,
        initialDelayMillis = 1800L,
        stepDelayMillis = 1800L,
    )
    val (label, color) = states[stateIndex]
    val animatedColor by animateColorAsState(color, tween(400))
    val animatedScale = remember { Animatable(1f) }
    LaunchedEffect(stateIndex) {
        animatedScale.animateTo(1.2f, spring(stiffness = Spring.StiffnessHigh))
        animatedScale.animateTo(1f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
    }
    Surface(
        shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp),
        color = animatedColor.copy(alpha = 0.15f),
        border = BorderStroke(1.5.dp, animatedColor),
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).graphicsLayer {
            scaleX = animatedScale.value
            scaleY = animatedScale.value
        },
    ) {
        Text(label, modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = animatedColor)
    }
}

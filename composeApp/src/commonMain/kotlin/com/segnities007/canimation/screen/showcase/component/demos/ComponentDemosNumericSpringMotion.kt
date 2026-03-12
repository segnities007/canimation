package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun RollingDigits(
    digitCount: Int = 4,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val digits = remember(digitCount) { List(digitCount) { Animatable(0f) } }
    var targets by remember { mutableStateOf(List(digitCount) { 0 }) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1500)
            targets = List(digitCount) { (0..9).random() }
        }
    }
    LaunchedEffect(targets) {
        targets.forEachIndexed { index, target ->
            launch {
                delay(index * 100L)
                digits[index].animateTo(
                    targetValue = target.toFloat(),
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow,
                    ),
                )
            }
        }
    }

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In).padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        digits.forEach { animatable ->
            val value = animatable.value
            val display = ((value.roundToInt()) % 10 + 10) % 10
            val offsetY = (value - value.roundToInt()) * 40f
            Surface(
                modifier = Modifier.size(40.dp, 56.dp),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = display.toString(),
                        modifier = Modifier.graphicsLayer { translationY = offsetY },
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                    )
                }
            }
        }
    }
}

@Composable
fun SpringChain(
    circleCount: Int = 5,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val offsets = remember(circleCount) { List(circleCount) { Animatable(0f) } }
    var toggle by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1500)
            toggle = !toggle
        }
    }
    LaunchedEffect(toggle) {
        offsets.forEachIndexed { index, animatable ->
            launch {
                delay(index * 80L)
                animatable.animateTo(
                    targetValue = if (toggle) 20f else -20f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow,
                    ),
                )
            }
        }
    }

    Row(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        offsets.forEachIndexed { index, animatable ->
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer { translationY = animatable.value }
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.primary),
            )
            if (index < circleCount - 1) {
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .size(width = 20.dp, height = 2.dp)
                        .background(MaterialTheme.colorScheme.outline),
                )
            }
        }
    }
}

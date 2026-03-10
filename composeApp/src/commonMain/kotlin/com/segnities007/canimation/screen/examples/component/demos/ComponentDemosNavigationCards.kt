package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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

@Composable
fun AnimatedTabs(
    tabs: List<String> = listOf("Home", "Search", "Profile"),
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var selectedIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            selectedIndex = (selectedIndex + 1) % tabs.size
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = modifier
                .canimation(visible = entryVisible, effect = Canimation.Fade.Up)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            tabs.forEachIndexed { index, label ->
                val isSelected = index == selectedIndex
                val bgAlpha by animateFloatAsState(
                    targetValue = if (isSelected) 1f else 0f,
                    animationSpec = spring(stiffness = Spring.StiffnessLow),
                )
                val textAlpha by animateFloatAsState(
                    targetValue = if (isSelected) 1f else 0.6f,
                    animationSpec = tween(200),
                )
                Surface(
                    modifier = Modifier
                        .clickable { selectedIndex = index }
                        .graphicsLayer { alpha = 1f },
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = bgAlpha * 0.15f),
                ) {
                    Text(
                        text = label,
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                            .graphicsLayer { alpha = textAlpha },
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Composable
fun FlipCard(
    frontText: String = "Front",
    backText: String = "Back",
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var flipped by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2500)
            flipped = !flipped
        }
    }

    val rotationY by animateFloatAsState(
        targetValue = if (flipped) 180f else 0f,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
    )
    val showFront = rotationY <= 90f

    Box(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Spring.In)
            .size(width = 140.dp, height = 80.dp)
            .clickable { flipped = !flipped }
            .graphicsLayer {
                this.rotationY = rotationY
                cameraDistance = 12f * density
            },
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (showFront) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.tertiaryContainer,
            border = BorderStroke(
                1.dp,
                if (showFront) MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                else MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f),
            ),
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    if (!showFront) this.rotationY = 180f
                },
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = if (showFront) frontText else backText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (showFront) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
        }
    }
}

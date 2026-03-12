package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.demo_card_title
import canimation.composeapp.generated.resources.demo_expanded_content
import canimation.composeapp.generated.resources.demo_lift
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun CardLiftHover(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val lifted = rememberLoopingVisibility(
        visibleDurationMillis = 2000L,
        hiddenDurationMillis = 1000L,
    )
    val elevation by animateFloatAsState(
        targetValue = if (lifted) 16f else 2f,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
    )
    val translateY by animateFloatAsState(
        targetValue = if (lifted) -8f else 0f,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
    )

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        shadowElevation = elevation.dp,
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .size(140.dp, 80.dp)
            .graphicsLayer { translationY = translateY },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                stringResource(Res.string.demo_lift),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}

@Composable
fun CardExpandCollapse(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val expanded = rememberLoopingVisibility(
        visibleDurationMillis = 2500L,
        hiddenDurationMillis = 1500L,
    )
    val height by animateFloatAsState(
        targetValue = if (expanded) 120f else 48f,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
    )

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(160.dp).height(height.dp),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                stringResource(Res.string.demo_card_title),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            if (expanded) {
                Text(
                    stringResource(Res.string.demo_expanded_content),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                )
            }
        }
    }
}

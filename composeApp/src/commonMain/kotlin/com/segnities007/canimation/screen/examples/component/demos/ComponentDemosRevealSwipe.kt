package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_circular_reveal
import canimation.composeapp.generated.resources.component_reveal
import canimation.composeapp.generated.resources.component_swipe_card
import canimation.composeapp.generated.resources.component_tap_to_swipe
import com.segnities007.canimation.compose.LoopingFloatStep
import com.segnities007.canimation.compose.LoopingIntStep
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingAnimatedFloat
import com.segnities007.canimation.compose.rememberLoopingIntSequence
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun CircularRevealCard(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val reveal = rememberLoopingAnimatedFloat(
        initialValue = 0f,
        steps = listOf(
            LoopingFloatStep(value = 1f, animationDurationMillis = 1200, holdDurationMillis = 500L),
            LoopingFloatStep(value = 0f, holdDurationMillis = 0L),
        ),
    )
    val primary = MaterialTheme.colorScheme.primary

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Reveal.Center), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(Res.string.component_circular_reveal), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Box(
            Modifier.size(140.dp, 90.dp).clip(RoundedCornerShape(12.dp)).background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center,
        ) {
            Canvas(Modifier.fillMaxSize()) {
                drawCircle(color = primary.copy(alpha = 0.5f), radius = reveal * size.maxDimension, center = Offset(size.width / 2, size.height / 2))
            }
            Text(stringResource(Res.string.component_reveal), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun SwipeCard(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val autoDismissStage = rememberLoopingIntSequence(
        initialValue = 0,
        steps = listOf(
            LoopingIntStep(value = 1, holdDurationMillis = 1000L),
            LoopingIntStep(value = 0, holdDurationMillis = 2000L),
        ),
        initialDelayMillis = 2000L,
    )
    var dismissed by remember { mutableStateOf(false) }

    LaunchedEffect(autoDismissStage) {
        dismissed = autoDismissStage == 1
    }

    val offsetX by animateFloatAsState(targetValue = if (dismissed) 300f else 0f, animationSpec = tween(400))
    val cardAlpha by animateFloatAsState(targetValue = if (dismissed) 0f else 1f, animationSpec = tween(400))

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Swipe.Left), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(Res.string.component_swipe_card), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Surface(
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 4.dp,
            modifier = Modifier.offset(x = offsetX.dp).size(160.dp, 80.dp).clickable { dismissed = true },
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = cardAlpha))) {
                Text(stringResource(Res.string.component_tap_to_swipe), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

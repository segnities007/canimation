package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.demo_action_completed
import canimation.composeapp.generated.resources.demo_app_header
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

@Composable
fun ToastNotification(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) {
            visible = true
            delay(2500)
            visible = false
            delay(1000)
        }
    }

    val offsetY by animateFloatAsState(
        targetValue = if (visible) 0f else -80f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = Spring.StiffnessMediumLow),
    )
    val alpha by animateFloatAsState(targetValue = if (visible) 1f else 0f, animationSpec = tween(300))

    Box(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .width(260.dp)
            .height(60.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = offsetY
                    this.alpha = alpha
                },
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.inverseSurface,
            shadowElevation = 4.dp,
        ) {
            Box(modifier = Modifier.padding(12.dp), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = stringResource(Res.string.demo_action_completed),
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
fun ScrollDirectionHeader(
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)

    var hidden by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) {
            hidden = true
            delay(1500)
            hidden = false
            delay(1500)
        }
    }

    val offsetY by animateFloatAsState(
        targetValue = if (hidden) -50f else 0f,
        animationSpec = spring(dampingRatio = 0.8f, stiffness = Spring.StiffnessMediumLow),
    )
    val alpha by animateFloatAsState(targetValue = if (hidden) 0f else 1f, animationSpec = tween(300))

    Column(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Fade.Up)
            .width(260.dp)
            .height(80.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .graphicsLayer {
                    translationY = offsetY
                    this.alpha = alpha
                },
            color = MaterialTheme.colorScheme.primary,
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(Res.string.demo_app_header),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f).background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = if (hidden) "\u2193 Scrolling down" else "\u2191 Scrolling up",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

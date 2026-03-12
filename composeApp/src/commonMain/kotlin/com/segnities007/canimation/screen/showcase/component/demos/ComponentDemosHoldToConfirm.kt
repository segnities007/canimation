package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.examples_confirmed
import canimation.composeapp.generated.resources.examples_hold_to_confirm
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

@Composable
fun HoldToConfirm(
    confirmText: String = "Hold to confirm",
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    var holdProgress by remember { mutableFloatStateOf(0f) }
    var confirmed by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            confirmed = false
            holdProgress = 0f
            delay(1000)
            for (index in 1..50) {
                holdProgress = index / 50f
                delay(40)
            }
            confirmed = true
            delay(2000)
        }
    }

    val animatedProgress by animateFloatAsState(targetValue = holdProgress, animationSpec = tween(80))
    val backgroundColor by animateColorAsState(
        targetValue = if (confirmed) Color(0xFF4CAF50) else MaterialTheme.colorScheme.primary,
        animationSpec = tween(300),
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
            color = backgroundColor.copy(alpha = 0.15f),
            border = BorderStroke(2.dp, backgroundColor),
            modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(width = 160.dp, height = 44.dp),
        ) {
            Box(contentAlignment = Alignment.CenterStart) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(animatedProgress)
                        .height(44.dp)
                        .background(backgroundColor.copy(alpha = 0.2f), androidx.compose.foundation.shape.RoundedCornerShape(24.dp)),
                )
                if (confirmed) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null, tint = backgroundColor, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.size(4.dp))
                        Text(
                            text = stringResource(Res.string.examples_confirmed),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = backgroundColor,
                        )
                    }
                } else {
                    Text(
                        text = stringResource(Res.string.examples_hold_to_confirm),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = backgroundColor,
                    )
                }
            }
        }
    }
}

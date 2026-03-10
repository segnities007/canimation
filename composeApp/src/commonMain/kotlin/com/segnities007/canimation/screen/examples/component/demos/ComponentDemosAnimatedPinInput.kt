package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.LoopingIntStep
import com.segnities007.canimation.compose.rememberLoopingIntSequence
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

@Composable
fun AnimatedPinInput(modifier: Modifier = Modifier) {
    val filled =
        rememberLoopingIntSequence(
            initialValue = 0,
            initialDelayMillis = 600L,
            steps =
                listOf(
                    LoopingIntStep(value = 1, holdDurationMillis = 600L),
                    LoopingIntStep(value = 2, holdDurationMillis = 600L),
                    LoopingIntStep(value = 3, holdDurationMillis = 600L),
                    LoopingIntStep(value = 4, holdDurationMillis = 1600L),
                    LoopingIntStep(value = 0, holdDurationMillis = 600L),
                ),
        )

    Row(
        modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(4) { index ->
            val isFilled = index < filled
            var visible by remember { mutableStateOf(false) }

            LaunchedEffect(isFilled) {
                visible = isFilled
            }

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border =
                    BorderStroke(
                        1.5.dp,
                        if (isFilled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                    ),
                modifier = Modifier.padding(horizontal = 4.dp).size(44.dp),
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    if (isFilled) {
                        Box(Modifier.canimation(visible = visible, effect = Canimation.Scale.Pop)) {
                            Box(
                                Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary),
                            )
                        }
                    }
                }
            }
        }
    }
}

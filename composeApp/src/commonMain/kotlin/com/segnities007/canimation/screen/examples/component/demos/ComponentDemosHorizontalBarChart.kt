package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_percent_value
import com.segnities007.canimation.compose.LoopingFloatStep
import com.segnities007.canimation.compose.rememberLoopingAnimatedFloat
import org.jetbrains.compose.resources.stringResource

@Composable
fun HorizontalBarChart(modifier: Modifier = Modifier) {
    val items = listOf("Kotlin" to 0.85f, "Swift" to 0.65f, "Dart" to 0.45f, "Java" to 0.55f)
    val values = items.mapIndexed { index, (_, targetValue) ->
        rememberLoopingAnimatedFloat(
            initialValue = 0f,
            initialDelayMillis = index * 200L,
            steps = listOf(
                LoopingFloatStep(value = targetValue, animationDurationMillis = 800, holdDurationMillis = 2000L),
                LoopingFloatStep(value = 0f, animationDurationMillis = 300, holdDurationMillis = 400L),
            ),
        )
    }

    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        items.forEachIndexed { index, (label, _) ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = label,
                    modifier = Modifier.width(56.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                )
                Box(
                    Modifier
                        .weight(1f)
                        .height(16.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(4.dp)),
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth(values[index])
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f), RoundedCornerShape(4.dp)),
                    )
                }
                Spacer(Modifier.width(6.dp))
                Text(
                    text = stringResource(Res.string.component_percent_value, (values[index] * 100).toInt()),
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.width(48.dp),
                )
            }
        }
    }
}

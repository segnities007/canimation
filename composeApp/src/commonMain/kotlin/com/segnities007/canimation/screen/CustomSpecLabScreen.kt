package com.segnities007.canimation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.component.PresetSelector
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import io.github.canimation.core.EasingTokens
import io.github.canimation.core.canimationEnter

private fun Float.fmt2(): String {
    val r = kotlin.math.round(this * 100).toInt()
    val sign = if (r < 0) "-" else ""
    val abs = kotlin.math.abs(r)
    return "$sign${abs / 100}.${(abs % 100).toString().padStart(2, '0')}"
}

@Composable
fun CustomSpecLabScreen(modifier: Modifier = Modifier) {
    var durationMs by remember { mutableFloatStateOf(220f) }
    var alphaFrom by remember { mutableFloatStateOf(0f) }
    var alphaTo by remember { mutableFloatStateOf(1f) }
    var offsetY by remember { mutableFloatStateOf(16f) }
    var scaleFactor by remember { mutableFloatStateOf(1f) }
    var visible by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Custom Spec Lab",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )

        // Duration slider
        LabeledSlider(
            label = "Duration",
            value = durationMs,
            onValueChange = { durationMs = it },
            valueRange = 0f..1000f,
            displayValue = "${durationMs.toInt()}ms",
        )

        // Alpha range
        LabeledSlider(
            label = "Alpha From",
            value = alphaFrom,
            onValueChange = { alphaFrom = it },
            valueRange = 0f..1f,
            displayValue = alphaFrom.fmt2(),
        )
        LabeledSlider(
            label = "Alpha To",
            value = alphaTo,
            onValueChange = { alphaTo = it },
            valueRange = 0f..1f,
            displayValue = alphaTo.fmt2(),
        )

        // Offset Y
        LabeledSlider(
            label = "Offset Y",
            value = offsetY,
            onValueChange = { offsetY = it },
            valueRange = -48f..48f,
            displayValue = "${offsetY.toInt()}dp",
        )

        // Scale
        LabeledSlider(
            label = "Scale",
            value = scaleFactor,
            onValueChange = { scaleFactor = it },
            valueRange = 0.5f..1.5f,
            displayValue = scaleFactor.fmt2(),
        )

        // Preview
        val customSpec = CanimationSpec(
            durationMs = durationMs.toInt(),
            easing = EasingTokens.Default.standard,
            alpha = CanimationRange(alphaFrom, alphaTo),
            offsetY = if (offsetY != 0f) CanimationDpRange(offsetY.dp, 0.dp) else null,
            scale = if (scaleFactor != 1f) CanimationRange(scaleFactor, 1f) else null,
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text("Preview", style = MaterialTheme.typography.titleMedium)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Card(
                        modifier = Modifier.canimationEnter(
                            visible = visible,
                            fullSpec = customSpec,
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    ) {
                        Text(
                            text = "Custom Animation",
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }

                OutlinedButton(
                    onClick = { visible = !visible },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(if (visible) "Hide" else "Show")
                }
            }
        }

        // Generated code display
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF1E1E2E),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = buildString {
                    appendLine("CanimationSpec(")
                    appendLine("    durationMs = ${durationMs.toInt()},")
                    appendLine("    easing = EasingTokens.Default.standard,")
                    appendLine("    alpha = CanimationRange(${alphaFrom}f, ${alphaTo}f),")
                    if (offsetY != 0f) appendLine("    offsetY = CanimationDpRange(${offsetY.toInt()}.dp, 0.dp),")
                    if (scaleFactor != 1f) appendLine("    scale = CanimationRange(${scaleFactor}f, 1f),")
                    append(")")
                },
                modifier = Modifier.padding(20.dp),
                fontFamily = FontFamily.Monospace,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFCDD6F4),
            )
        }
    }
}

@Composable
private fun LabeledSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    displayValue: String,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(label, style = MaterialTheme.typography.bodyMedium)
            Text(displayValue, style = MaterialTheme.typography.bodySmall)
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
        )
    }
}

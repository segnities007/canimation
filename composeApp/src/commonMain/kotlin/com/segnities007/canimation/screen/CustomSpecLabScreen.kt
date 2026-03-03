package com.segnities007.canimation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import io.github.canimation.core.EasingTokens
import io.github.canimation.core.canimationEnter
import kotlinx.coroutines.delay

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
    var entryStage by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        for (i in 0..3) { delay(80); entryStage = i }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 960.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            // Header
            Box(Modifier.canimationEnter(visible = entryStage >= 0, preset = CanimationPreset.FadeUp)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "CUSTOM SPEC",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Build your own animation",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Adjust parameters and see the result in real-time",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            // Controls card
            Box(Modifier.fillMaxWidth().canimationEnter(visible = entryStage >= 1, preset = CanimationPreset.FadeUp)) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Text(
                            text = "Parameters",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        LabeledSlider("Duration", durationMs, { durationMs = it }, 0f..1000f, "${durationMs.toInt()}ms")
                        LabeledSlider("Alpha From", alphaFrom, { alphaFrom = it }, 0f..1f, alphaFrom.fmt2())
                        LabeledSlider("Alpha To", alphaTo, { alphaTo = it }, 0f..1f, alphaTo.fmt2())
                        LabeledSlider("Offset Y", offsetY, { offsetY = it }, -48f..48f, "${offsetY.toInt()}dp")
                        LabeledSlider("Scale", scaleFactor, { scaleFactor = it }, 0.5f..1.5f, scaleFactor.fmt2())
                    }
                }
            }

            // Preview card
            Box(Modifier.fillMaxWidth().canimationEnter(visible = entryStage >= 2, preset = CanimationPreset.FadeUp)) {
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
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Text(
                            text = "Live Preview",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )

                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
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
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
                                ) {
                                    Text(
                                        text = "Custom Animation",
                                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
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
            }

            // Generated code
            Box(Modifier.fillMaxWidth().canimationEnter(visible = entryStage >= 3, preset = CanimationPreset.FadeUp)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Generated Code",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
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
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            }
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
            Text(
                displayValue,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
        )
    }
}

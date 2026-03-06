package com.segnities007.canimation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import io.github.canimation.core.canimationTransition
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

data class PresetPreviewTuning(
    val durationScale: Float = 1f,
    val distanceScale: Float = 1f,
    val scaleIntensity: Float = 1f,
    val rotationScale: Float = 1f,
)

fun tunePresetSpec(
    base: CanimationPresetSpec,
    tuning: PresetPreviewTuning,
): CanimationPresetSpec {
    return CanimationPresetSpec(
        fullEnter = tuneSpec(base.fullEnter, tuning),
        fullExit = tuneSpec(base.fullExit, tuning),
        reducedEnter = tuneSpec(base.reducedEnter, tuning),
        reducedExit = tuneSpec(base.reducedExit, tuning),
    )
}

private fun tuneSpec(
    spec: CanimationSpec,
    tuning: PresetPreviewTuning,
): CanimationSpec {
    return spec.copy(
        durationMs = (spec.durationMs * tuning.durationScale).roundToInt().coerceAtLeast(1),
        offsetX = spec.offsetX?.scaled(tuning.distanceScale),
        offsetY = spec.offsetY?.scaled(tuning.distanceScale),
        scale = spec.scale?.scaledAroundOne(tuning.scaleIntensity),
        rotation = spec.rotation?.scaled(tuning.rotationScale),
    )
}

private fun CanimationDpRange.scaled(factor: Float): CanimationDpRange {
    return CanimationDpRange(from = from * factor, to = to * factor)
}

private fun CanimationRange.scaled(factor: Float): CanimationRange {
    return CanimationRange(from = from * factor, to = to * factor)
}

private fun CanimationRange.scaledAroundOne(factor: Float): CanimationRange {
    return CanimationRange(
        from = 1f + (from - 1f) * factor,
        to = 1f + (to - 1f) * factor,
    )
}

private operator fun Dp.times(factor: Float): Dp = (value * factor).dp

private fun channelSummary(spec: CanimationSpec): String {
    val channels = mutableListOf<String>()
    if (spec.alpha != null) channels += "alpha"
    if (spec.offsetX != null) channels += "offsetX"
    if (spec.offsetY != null) channels += "offsetY"
    if (spec.scale != null) channels += "scale"
    if (spec.rotation != null) channels += "rotation"
    return channels.joinToString(separator = " | ")
}

@Composable
fun AnimationShowcase(
    title: String,
    preset: CanimationPreset,
    baseSpec: CanimationPresetSpec,
    tuning: PresetPreviewTuning,
    autoPlayEnabled: Boolean,
    autoPlayTick: Int,
    selectedForCompare: Boolean,
    onCardClick: (CanimationPreset) -> Unit,
    modifier: Modifier = Modifier,
) {
    val previewSpec = remember(baseSpec, tuning) { tunePresetSpec(baseSpec, tuning) }
    var visible by remember(preset) { mutableStateOf(false) }

    suspend fun replay() {
        visible = false
        delay(220)
        visible = true
    }

    LaunchedEffect(preset) {
        replay()
    }

    LaunchedEffect(autoPlayEnabled, autoPlayTick) {
        if (autoPlayEnabled) {
            replay()
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCardClick(preset) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        border = if (selectedForCompare) {
            BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        } else {
            BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        },
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = preset.name,
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(
                            color = MaterialTheme.colorScheme.outline,
                            shape = CircleShape,
                        ),
                )
                Card(
                    modifier = Modifier.canimationTransition(
                        visible = visible,
                        enterFullSpec = previewSpec.fullEnter,
                        enterReducedSpec = previewSpec.reducedEnter,
                        exitFullSpec = previewSpec.fullExit,
                        exitReducedSpec = previewSpec.reducedExit,
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                ) {
                    Box(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "A",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }
            }
        }
    }
}

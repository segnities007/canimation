package com.segnities007.canimation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import io.github.canimation.core.LocalCanimationContext

@Stable
internal data class StagedProgressionSpec(
    val maxStage: Int,
    val stepDelayMillis: Long,
)

@Composable
internal fun RunStagedProgressionEffect(
    spec: StagedProgressionSpec,
    onStage: (Int) -> Unit,
) {
    val level = LocalCanimationContext.current.level

    LaunchedEffect(spec.maxStage, spec.stepDelayMillis, level) {
        for (stage in 0..spec.maxStage) {
            kotlinx.coroutines.delay(level.accessibleDelayMillis(spec.stepDelayMillis))
            onStage(stage)
        }
    }
}

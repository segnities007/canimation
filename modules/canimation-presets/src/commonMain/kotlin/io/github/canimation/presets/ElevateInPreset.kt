package io.github.canimation.presets

import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

object ElevateInPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 200,
            easing = InternalEasings.standard,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.96f, 1.0f),
            offsetY = CanimationDpRange(4.dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 200,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.96f),
            offsetY = CanimationDpRange(0.dp, 4.dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.decelerate,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.99f, 1.0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.99f),
        ),
    )
}

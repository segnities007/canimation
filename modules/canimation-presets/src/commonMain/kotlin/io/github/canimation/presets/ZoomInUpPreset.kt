package io.github.canimation.presets

import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

object ZoomInUpPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = InternalEasings.standard, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.6f, 1.0f), offsetY = CanimationDpRange(40.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.6f), offsetY = CanimationDpRange(0.dp, 40.dp)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.9f, 1.0f), offsetY = CanimationDpRange(10.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.9f), offsetY = CanimationDpRange(0.dp, 10.dp)),
    )
}

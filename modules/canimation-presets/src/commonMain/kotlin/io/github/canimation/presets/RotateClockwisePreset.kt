package io.github.canimation.presets

import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

object RotateClockwisePreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = InternalEasings.standard, alpha = CanimationRange(0f, 1f), rotation = CanimationRange(90f, 0f)),
        fullExit = CanimationSpec(durationMs = 300, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), rotation = CanimationRange(0f, 90f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), rotation = CanimationRange(22f, 0f)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), rotation = CanimationRange(0f, 22f)),
    )
}

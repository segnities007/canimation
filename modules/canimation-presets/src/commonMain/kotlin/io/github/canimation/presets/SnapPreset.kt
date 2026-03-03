package io.github.canimation.presets

import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

object SnapPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 10, easing = InternalEasings.standard, alpha = CanimationRange(0f, 1f)),
        fullExit = CanimationSpec(durationMs = 10, easing = InternalEasings.standard, alpha = CanimationRange(1f, 0f)),
        reducedEnter = CanimationSpec(durationMs = 10, easing = InternalEasings.standard, alpha = CanimationRange(0f, 1f)),
        reducedExit = CanimationSpec(durationMs = 10, easing = InternalEasings.standard, alpha = CanimationRange(1f, 0f)),
    )
}

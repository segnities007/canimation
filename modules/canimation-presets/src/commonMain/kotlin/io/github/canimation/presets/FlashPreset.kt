package io.github.canimation.presets

import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

/** Quick flash appearance. Inspired by Animate.css flash. */
object FlashPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 200, easing = InternalEasings.standard, alpha = CanimationRange(0f, 1f)),
        fullExit = CanimationSpec(durationMs = 200, easing = InternalEasings.standard, alpha = CanimationRange(1f, 0f)),
        reducedEnter = CanimationSpec(durationMs = 100, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f)),
        reducedExit = CanimationSpec(durationMs = 100, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f)),
    )
}

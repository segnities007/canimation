package io.github.canimation.presets

import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

/** Subtle scale emphasis. Inspired by Animate.css pulse. */
object PulsePreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = InternalEasings.standard, alpha = CanimationRange(0.8f, 1f), scale = CanimationRange(1.05f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 300, easing = InternalEasings.standard, alpha = CanimationRange(1f, 0.8f), scale = CanimationRange(1.0f, 1.05f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0.9f)),
    )
}

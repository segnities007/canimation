package io.github.canimation.presets

import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

/** Fade with small scale. Inspired by AnimXYZ "fade small" composition. */
object FadeSmallPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = InternalEasings.standard, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.5f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 250, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.5f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
    )
}

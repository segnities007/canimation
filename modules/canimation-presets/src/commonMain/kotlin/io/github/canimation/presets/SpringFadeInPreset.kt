package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

/** Spring fade with subtle scale overshoot. Inspired by Framer Motion spring transitions. */
object SpringFadeInPreset {
    private val springEasing = CubicBezierEasing(0.175f, 0.885f, 0.32f, 1.275f)
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = springEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 250, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f)),
    )
}

package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

/** Jello-like rotation emphasis. Inspired by Animate.css jello. */
object JelloPreset {
    private val bounceEasing = CubicBezierEasing(0.68f, -0.55f, 0.27f, 1.55f)
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), rotation = CanimationRange(-12.5f, 0f)),
        fullExit = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), rotation = CanimationRange(0f, 12.5f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0.9f)),
    )
}

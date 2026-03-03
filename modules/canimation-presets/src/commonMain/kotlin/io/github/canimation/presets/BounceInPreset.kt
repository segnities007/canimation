package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

/** Bouncy scale entry with overshoot. Inspired by Animate.css bounceIn. */
object BounceInPreset {
    private val bounceEasing = CubicBezierEasing(0.68f, -0.55f, 0.27f, 1.55f)
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.3f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 350, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.3f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
    )
}

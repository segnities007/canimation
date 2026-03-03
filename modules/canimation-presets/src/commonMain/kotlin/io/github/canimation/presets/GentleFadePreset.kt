package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

object GentleFadePreset {
    private val gentleEasing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 600, easing = gentleEasing, alpha = CanimationRange(0f, 1f)),
        fullExit = CanimationSpec(durationMs = 600, easing = gentleEasing, alpha = CanimationRange(1f, 0f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f)),
    )
}

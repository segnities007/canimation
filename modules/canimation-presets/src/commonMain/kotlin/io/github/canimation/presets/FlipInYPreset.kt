package io.github.canimation.presets

import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

/** Vertical flip entry (2D approximation). Inspired by Animate.css flipInY. */
object FlipInYPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = InternalEasings.standard, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.6f, 1.0f), rotation = CanimationRange(90f, 0f)),
        fullExit = CanimationSpec(durationMs = 350, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.6f), rotation = CanimationRange(0f, 90f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.9f, 1.0f)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.9f)),
    )
}

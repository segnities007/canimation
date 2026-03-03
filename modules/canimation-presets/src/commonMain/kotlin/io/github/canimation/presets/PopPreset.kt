package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

object PopPreset {
    private val overshootEasing = CubicBezierEasing(0.175f, 0.885f, 0.32f, 1.275f)

    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 250,
            easing = overshootEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.6f, 1.0f),
        ),
        fullExit = CanimationSpec(
            durationMs = 200,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.6f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.decelerate,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.9f, 1.0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.9f),
        ),
    )
}

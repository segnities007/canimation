package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

/** Material fade-through pattern: fade + subtle scale. Inspired by Material Motion patterns. */
object FadeThroughPreset {
    private val emphasizedDecelerate = CubicBezierEasing(0.05f, 0.7f, 0.1f, 1.0f)
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = emphasizedDecelerate, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.92f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 250, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.92f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f)),
    )
}

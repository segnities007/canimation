package io.github.canimation.presets

import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

/**
 * Fade preset: simple alpha-only animation.
 */
object FadePreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 220,
            easing = InternalEasings.standard,
            alpha = CanimationRange(0f, 1f),
        ),
        fullExit = CanimationSpec(
            durationMs = 220,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.decelerate,
            alpha = CanimationRange(0f, 1f),
        ),
        reducedExit = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
        ),
    )
}

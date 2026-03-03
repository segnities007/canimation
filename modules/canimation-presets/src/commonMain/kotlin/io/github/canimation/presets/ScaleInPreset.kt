package io.github.canimation.presets

import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

/**
 * ScaleIn preset: scale up from slightly smaller with fade.
 *
 * Full motion scales from 0.92f, reduced from 0.98f.
 */
object ScaleInPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 220,
            easing = InternalEasings.standard,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.92f, 1.0f),
        ),
        fullExit = CanimationSpec(
            durationMs = 220,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.92f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.decelerate,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.98f, 1.0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.98f),
        ),
    )
}

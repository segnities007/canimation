package io.github.canimation.presets

import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

/**
 * SlideRight preset: slide in from the left (rightward movement).
 *
 * Full motion uses -16dp offset, reduced uses -4dp.
 */
object SlideRightPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 220,
            easing = InternalEasings.standard,
            alpha = CanimationRange(0f, 1f),
            offsetX = CanimationDpRange((-16).dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 220,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
            offsetX = CanimationDpRange(0.dp, (-16).dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.decelerate,
            alpha = CanimationRange(0f, 1f),
            offsetX = CanimationDpRange((-4).dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
            offsetX = CanimationDpRange(0.dp, (-4).dp),
        ),
    )
}

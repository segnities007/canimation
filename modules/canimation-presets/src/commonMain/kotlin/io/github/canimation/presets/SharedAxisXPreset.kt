package io.github.canimation.presets

import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

/** Horizontal shared axis transition. Inspired by Material Motion shared axis X. */
object SharedAxisXPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = InternalEasings.standard, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(30.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 250, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-30).dp)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(8.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-8).dp)),
    )
}

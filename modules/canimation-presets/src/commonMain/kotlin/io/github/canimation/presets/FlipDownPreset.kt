package io.github.canimation.presets

import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

/** Rotation + downward slide entry. Inspired by AnimXYZ flip-down composable. */
object FlipDownPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = InternalEasings.standard, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-20).dp, 0.dp), rotation = CanimationRange(-45f, 0f)),
        fullExit = CanimationSpec(durationMs = 300, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-20).dp), rotation = CanimationRange(0f, -45f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-5).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-5).dp)),
    )
}

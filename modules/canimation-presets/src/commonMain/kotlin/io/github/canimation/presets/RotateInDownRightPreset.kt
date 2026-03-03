package io.github.canimation.presets

import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

/** Rotate entry from down-right. Inspired by Animate.css rotateInDownRight. */
object RotateInDownRightPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), rotation = CanimationRange(45f, 0f), offsetX = CanimationDpRange(20.dp, 0.dp), offsetY = CanimationDpRange(20.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), rotation = CanimationRange(0f, 45f), offsetX = CanimationDpRange(0.dp, 20.dp), offsetY = CanimationDpRange(0.dp, 20.dp)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(5.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 5.dp)),
    )
}

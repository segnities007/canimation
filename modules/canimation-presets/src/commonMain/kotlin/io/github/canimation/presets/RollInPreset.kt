package io.github.canimation.presets

import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

/** Rolling rotation + slide from left. Inspired by Animate.css rollIn. */
object RollInPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 500, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-120).dp, 0.dp), rotation = CanimationRange(-120f, 0f)),
        fullExit = CanimationSpec(durationMs = 400, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-120).dp), rotation = CanimationRange(0f, -120f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-30).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-30).dp)),
    )
}

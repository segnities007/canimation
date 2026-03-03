package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

object SwingInPreset {
    private val overshootEasing = CubicBezierEasing(0.175f, 0.885f, 0.32f, 1.275f)
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = overshootEasing, alpha = CanimationRange(0f, 1f), rotation = CanimationRange(-15f, 0f), offsetY = CanimationDpRange(20.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 250, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), rotation = CanimationRange(0f, 15f), offsetY = CanimationDpRange(0.dp, 20.dp)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(5.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 5.dp)),
    )
}

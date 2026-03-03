package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

/** Material emphasized decelerate entry. Inspired by Material Motion emphasized easing. */
object EmphasizedEntryPreset {
    private val emphasizedDecelerate = CubicBezierEasing(0.05f, 0.7f, 0.1f, 1.0f)
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 500, easing = emphasizedDecelerate, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f), offsetY = CanimationDpRange(20.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 400, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f), offsetY = CanimationDpRange(0.dp, 20.dp)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(5.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 5.dp)),
    )
}

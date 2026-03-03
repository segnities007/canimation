package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

/** Spring slide from below with overshoot. Inspired by Framer Motion spring type. */
object SpringSlideUpPreset {
    private val springEasing = CubicBezierEasing(0.175f, 0.885f, 0.32f, 1.275f)
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = springEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(50.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 50.dp)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(12.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 12.dp)),
    )
}

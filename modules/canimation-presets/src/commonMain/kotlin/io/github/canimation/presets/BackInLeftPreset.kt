package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

/** Back-eased entry from left. Inspired by Animate.css backInLeft. */
object BackInLeftPreset {
    private val backOutEasing = CubicBezierEasing(0.34f, 1.56f, 0.64f, 1f)
    private val backInEasing = CubicBezierEasing(0.36f, 0f, 0.66f, -0.56f)
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = backOutEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.7f, 1.0f), offsetX = CanimationDpRange((-40).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = backInEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.7f), offsetX = CanimationDpRange(0.dp, (-40).dp)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-10).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-10).dp)),
    )
}

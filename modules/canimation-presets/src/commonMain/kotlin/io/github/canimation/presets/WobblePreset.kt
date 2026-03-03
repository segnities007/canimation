package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

/** Side-to-side wobble emphasis. Inspired by Animate.css wobble. */
object WobblePreset {
    private val bounceEasing = CubicBezierEasing(0.68f, -0.55f, 0.27f, 1.55f)
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), offsetX = CanimationDpRange((-25).dp, 0.dp), rotation = CanimationRange(-5f, 0f)),
        fullExit = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), offsetX = CanimationDpRange(0.dp, 25.dp), rotation = CanimationRange(0f, 5f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0.9f)),
    )
}

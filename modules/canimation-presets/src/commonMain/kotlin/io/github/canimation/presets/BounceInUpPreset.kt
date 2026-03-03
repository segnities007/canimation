package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

/** Bounce entry from bottom. Inspired by Animate.css bounceInUp. */
object BounceInUpPreset {
    private val bounceEasing = CubicBezierEasing(0.68f, -0.55f, 0.27f, 1.55f)
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 450, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.7f, 1.0f), offsetY = CanimationDpRange(60.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.7f), offsetY = CanimationDpRange(0.dp, 60.dp)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(15.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 15.dp)),
    )
}

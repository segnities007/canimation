package io.github.canimation.presets

import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

/** Large slide from right with fade. Inspired by Animate.css fadeInRightBig. */
object FadeInRightBigPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 500, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(200.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 400, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 200.dp)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(50.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 50.dp)),
    )
}

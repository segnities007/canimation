package io.github.canimation.presets

import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

/** Diagonal fade from up-left. Inspired by AnimXYZ "fade up left" composition. */
object FadeUpLeftPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = InternalEasings.standard, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-25).dp, 0.dp), offsetY = CanimationDpRange((-25).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-25).dp), offsetY = CanimationDpRange(0.dp, (-25).dp)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-6).dp, 0.dp), offsetY = CanimationDpRange((-6).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-6).dp), offsetY = CanimationDpRange(0.dp, (-6).dp)),
    )
}

package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

object DropInPreset {
    private val bounceEasing = CubicBezierEasing(0.175f, 0.885f, 0.32f, 1.1f)

    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 350,
            easing = bounceEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.95f, 1.0f),
            offsetY = CanimationDpRange((-40).dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 250,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.95f),
            offsetY = CanimationDpRange(0.dp, (-40).dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.decelerate,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange((-10).dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
            offsetY = CanimationDpRange(0.dp, (-10).dp),
        ),
    )
}

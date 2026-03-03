package io.github.canimation.presets

import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import androidx.compose.ui.unit.dp

object FadeDownPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 220,
            easing = InternalEasings.standard,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange((-16).dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 220,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
            offsetY = CanimationDpRange(0.dp, (-16).dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.decelerate,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange((-4).dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = 120,
            easing = InternalEasings.accelerate,
            alpha = CanimationRange(1f, 0f),
            offsetY = CanimationDpRange(0.dp, (-4).dp),
        ),
    )
}

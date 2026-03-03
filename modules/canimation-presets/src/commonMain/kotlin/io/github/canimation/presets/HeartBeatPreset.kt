package io.github.canimation.presets

import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

/** Larger scale emphasis like a heartbeat. Inspired by Animate.css heartBeat. */
object HeartBeatPreset {
    val spec = CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = InternalEasings.standard, alpha = CanimationRange(0.7f, 1f), scale = CanimationRange(1.3f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 400, easing = InternalEasings.standard, alpha = CanimationRange(1f, 0.7f), scale = CanimationRange(1.0f, 1.3f)),
        reducedEnter = CanimationSpec(durationMs = 120, easing = InternalEasings.decelerate, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = 120, easing = InternalEasings.accelerate, alpha = CanimationRange(1f, 0.9f)),
    )
}

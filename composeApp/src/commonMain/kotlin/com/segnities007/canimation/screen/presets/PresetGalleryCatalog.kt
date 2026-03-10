package com.segnities007.canimation.screen.presets

import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationSpec
import io.github.canimation.presets.PresetsExtensionRegistry

internal fun matchesMotionFilter(
    spec: CanimationSpec,
    filter: MotionFilter,
): Boolean =
    when (filter) {
        MotionFilter.All -> true
        MotionFilter.Translation -> spec.offsetX != null || spec.offsetY != null
        MotionFilter.Scale -> spec.scale != null
        MotionFilter.Rotation -> spec.rotation != null
        MotionFilter.AlphaOnly -> spec.offsetX == null && spec.offsetY == null && spec.scale == null && spec.rotation == null
    }

internal fun presetDescription(preset: CanimationPreset): String = PresetsExtensionRegistry.descriptorFor(preset).docs.summary

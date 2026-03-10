package io.github.canimation.presets

import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec

internal val defaultPresetSpecs: Map<CanimationPreset, CanimationPresetSpec> =
    defaultPresetSpecsCore +
        defaultPresetSpecsMotion +
        defaultPresetSpecsExpressive

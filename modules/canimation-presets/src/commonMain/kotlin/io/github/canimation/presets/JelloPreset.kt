package io.github.canimation.presets

import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec

/**
 * Compatibility wrapper that resolves the preset from PresetRegistry SSoT.
 */
object JelloPreset {
    val spec: CanimationPresetSpec
        get() = PresetsExtensionRegistry.specFor(CanimationPreset.Jello)
}

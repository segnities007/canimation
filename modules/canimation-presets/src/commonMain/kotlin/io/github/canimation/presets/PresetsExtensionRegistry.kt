package io.github.canimation.presets

import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRecipeDescriptor
import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.PresetRegistry

/**
 * Preset registry extensions.
 *
 * SSoT: Built-in preset specs are defined in this module via [defaultPresetSpecs].
 */
object PresetsExtensionRegistry {
    init {
        PresetRegistry.installDefaults(defaultPresetSpecs)
    }

    /**
     * Resolves a preset spec from the single source of truth registry.
     */
    fun specFor(preset: CanimationPreset): CanimationPresetSpec =
        defaultPresetSpecs[preset] ?: defaultPresetSpecs.getValue(CanimationPreset.Fade)

    /**
     * All built-in preset specs from this module's SSoT.
     */
    val allPresetSpecs: Map<CanimationPreset, CanimationPresetSpec> = defaultPresetSpecs

    /**
     * Built-in semantic recipe descriptors derived from preset SSoT.
     */
    val allRecipeDescriptors: Map<CanimationPreset, CanimationRecipeDescriptor> = defaultPresetRecipeDescriptors

    /**
     * Read-only recipe registry built from built-in preset descriptors.
     */
    val recipeRegistry: CanimationRecipeRegistry = defaultRecipeRegistry

    fun descriptorFor(preset: CanimationPreset): CanimationRecipeDescriptor =
        allRecipeDescriptors[preset] ?: allRecipeDescriptors.getValue(CanimationPreset.Fade)

    /**
     * Creates an extended [PresetRegistry].
     *
     * @param additional Additional presets to register. These override defaults if keys conflict.
     */
    fun createExtendedRegistry(additional: Map<CanimationPreset, CanimationPresetSpec> = emptyMap()): PresetRegistry =
        PresetRegistry.create(specs = defaultPresetSpecs + additional)
}

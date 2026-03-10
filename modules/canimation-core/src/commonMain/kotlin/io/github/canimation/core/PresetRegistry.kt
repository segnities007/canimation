package io.github.canimation.core

import androidx.compose.runtime.Stable

/**
 * Registry mapping [CanimationPreset] to their [CanimationPresetSpec].
 *
 * The [Default] instance contains built-in presets with values from the spec.
 * Users can create custom registries to override or extend preset definitions.
 */
@Stable
class PresetRegistry private constructor(
    private val specs: Map<CanimationPreset, CanimationPresetSpec>,
    private val fallback: PresetRegistry? = null,
) {
    /**
     * Resolves a [CanimationSpec] for the given preset, level, and direction.
     *
     * Falls back to [CanimationPreset.Fade] if the preset is not registered.
     * When level is [CanimationLevel.Off], returns a snap spec (duration=0ms).
     */
    fun resolve(
        preset: CanimationPreset,
        level: CanimationLevel,
        direction: AnimationDirection,
    ): CanimationSpec {
        if (level == CanimationLevel.Off) {
            val base = resolvePresetSpec(preset)
            val spec = when (direction) {
                AnimationDirection.Enter -> base.fullEnter
                AnimationDirection.Exit -> base.fullExit
            }
            return spec.copy(durationMs = 0)
        }

        val presetSpec = resolvePresetSpec(preset)
        return when {
            level == CanimationLevel.Full && direction == AnimationDirection.Enter -> presetSpec.fullEnter
            level == CanimationLevel.Full && direction == AnimationDirection.Exit -> presetSpec.fullExit
            level == CanimationLevel.Reduced && direction == AnimationDirection.Enter -> presetSpec.reducedEnter
            level == CanimationLevel.Reduced && direction == AnimationDirection.Exit -> presetSpec.reducedExit
            else -> presetSpec.fullEnter
        }
    }

    private fun resolvePresetSpec(preset: CanimationPreset): CanimationPresetSpec {
        return specs[preset]
            ?: fallback?.specs?.get(preset)
            ?: specs[CanimationPreset.Fade]
            ?: fallback?.resolvePresetSpec(CanimationPreset.Fade)
            ?: FALLBACK_PRESET_SPEC
    }

    /**
     * Creates a new [PresetRegistry] with additional or overridden preset specs.
     */
    fun withOverrides(overrides: Map<CanimationPreset, CanimationPresetSpec>): PresetRegistry {
        return PresetRegistry(specs = specs + overrides, fallback = fallback)
    }

    /**
     * Creates a new [PresetRegistry] registering a single preset spec.
     */
    fun withPreset(preset: CanimationPreset, spec: CanimationPresetSpec): PresetRegistry {
        return PresetRegistry(specs = specs + (preset to spec), fallback = fallback)
    }

    companion object {
        private val FALLBACK_PRESET_SPEC = CanimationPresetSpec(
            fullEnter = CanimationSpec(
                durationMs = 0,
                easing = EasingTokens.Default.standard,
            ),
            fullExit = CanimationSpec(
                durationMs = 0,
                easing = EasingTokens.Default.standard,
            ),
            reducedEnter = CanimationSpec(
                durationMs = 0,
                easing = EasingTokens.Default.standard,
            ),
            reducedExit = CanimationSpec(
                durationMs = 0,
                easing = EasingTokens.Default.standard,
            ),
        )

        private var defaultRegistry: PresetRegistry = PresetRegistry(
            specs = mapOf(CanimationPreset.Fade to FALLBACK_PRESET_SPEC),
        )

        /**
         * Default registry containing built-in presets.
         */
        val Default: PresetRegistry
            get() = defaultRegistry

        /**
         * Installs built-in preset definitions from an external SSoT module.
         *
         * The provided map must include [CanimationPreset.Fade] to preserve fallback behavior.
         */
        fun installDefaults(specs: Map<CanimationPreset, CanimationPresetSpec>) {
            require(specs.containsKey(CanimationPreset.Fade)) {
                "Preset defaults must contain CanimationPreset.Fade"
            }
            defaultRegistry = PresetRegistry(specs = specs.toMap())
        }

        /**
         * Creates a new [PresetRegistry] from the given specs, falling back to [Default].
         */
        fun create(specs: Map<CanimationPreset, CanimationPresetSpec>): PresetRegistry {
            return PresetRegistry(specs = specs, fallback = Default)
        }
    }
}

package io.github.canimation.core

/**
 * Resolves a [CanimationSpec] from preset, level, and direction using a [PresetRegistry].
 */
internal object CanimationSpecResolver {

    /**
     * Resolves the appropriate animation spec.
     *
     * @param preset The animation preset to use.
     * @param level The current animation level.
     * @param direction Whether this is an Enter or Exit animation.
     * @param registry The preset registry to look up specs from.
     * @return The resolved [CanimationSpec].
     */
    fun resolve(
        preset: CanimationPreset,
        level: CanimationLevel,
        direction: AnimationDirection,
        registry: PresetRegistry,
    ): CanimationSpec {
        return registry.resolve(preset, level, direction)
    }

    /**
     * Resolves the appropriate animation spec from custom specs.
     *
     * @param level The current animation level.
     * @param direction Whether this is an Enter or Exit animation.
     * @param fullSpec The full motion spec.
     * @param reducedSpec The reduced motion spec.
     * @return The resolved [CanimationSpec].
     */
    fun resolveCustom(
        level: CanimationLevel,
        direction: AnimationDirection,
        fullSpec: CanimationSpec,
        reducedSpec: CanimationSpec,
    ): CanimationSpec {
        // fullSpec/reducedSpec are already direction-specific specs provided by the caller.
        // Do not reverse here; reversing at this layer breaks custom Exit behavior.
        return when (level) {
            CanimationLevel.Full -> fullSpec
            CanimationLevel.Reduced -> reducedSpec
            CanimationLevel.Off -> fullSpec.copy(durationMs = 0)
        }
    }
}

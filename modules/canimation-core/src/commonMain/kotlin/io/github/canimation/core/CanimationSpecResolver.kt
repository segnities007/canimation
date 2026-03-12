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
     * Resolves the appropriate animation spec for a [CanimationRecipe].
     */
    fun resolveRecipe(
        recipe: CanimationRecipe,
        level: CanimationLevel,
        registry: CanimationRecipeRegistry,
    ): CanimationSpec {
        val descriptor = registry.resolve(recipe)
        return resolveLevelSpec(
            level = level,
            fullSpec = descriptor.specs.full,
            reducedSpec = descriptor.specs.reduced,
            offSpec = descriptor.specs.off,
        )
    }

    /**
     * Resolves the appropriate animation spec for a [CanimationEffect].
     */
    fun resolveEffect(
        effect: CanimationEffect,
        level: CanimationLevel,
        tokens: CanimationTokens,
    ): CanimationSpec {
        val fullSpec = effect.toEnterSpec(tokens)
        return resolveLevelSpec(
            level = level,
            fullSpec = fullSpec,
            reducedSpec = fullSpec.toReduced(tokens),
            offSpec = fullSpec.copy(durationMs = 0),
        )
    }

    /**
     * Resolves the effect-backed transition spec used by the effect-based transition modifier.
     *
     * The returned spec preserves the existing modifier behavior where motion-off always snaps
     * using the enter effect shape, while hidden states use the provided exit effect or a reversed
     * enter effect fallback.
     */
    fun resolveTransitionEffect(
        visible: Boolean,
        level: CanimationLevel,
        enter: CanimationEffect,
        exit: CanimationEffect?,
        tokens: CanimationTokens,
    ): CanimationSpec {
        val enterSpec = enter.toEnterSpec(tokens)
        val fullSpec =
            if (visible) {
                enterSpec
            } else {
                exit?.toEnterSpec(tokens) ?: enterSpec.reversed(tokens)
            }
        return resolveLevelSpec(
            level = level,
            fullSpec = fullSpec,
            reducedSpec = fullSpec.toReduced(tokens),
            offSpec = enterSpec.copy(durationMs = 0),
        )
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
        return resolveLevelSpec(
            level = level,
            fullSpec = fullSpec,
            reducedSpec = reducedSpec,
            offSpec = fullSpec.copy(durationMs = 0),
        )
    }

    private fun resolveLevelSpec(
        level: CanimationLevel,
        fullSpec: CanimationSpec,
        reducedSpec: CanimationSpec,
        offSpec: CanimationSpec,
    ): CanimationSpec =
        when (level) {
            CanimationLevel.Full -> fullSpec
            CanimationLevel.Reduced -> reducedSpec
            CanimationLevel.Off -> offSpec
        }
}

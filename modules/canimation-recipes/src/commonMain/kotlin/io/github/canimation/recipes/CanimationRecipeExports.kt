package io.github.canimation.recipes

import io.github.canimation.primitives.Canimation
import io.github.canimation.semantics.CanimationRecipeRegistry
import io.github.canimation.semantics.CanimationRecipeRegistryMergePolicy

/**
 * Stage-1 module-owned recipe entrypoints bridging the legacy core namespace.
 */
typealias CanimationContentRecipes = io.github.canimation.core.CanimationContentRecipes
typealias CanimationFeedbackRecipes = io.github.canimation.core.CanimationFeedbackRecipes
typealias CanimationNavigationRecipes = io.github.canimation.core.CanimationNavigationRecipes
typealias CanimationSurfaceRecipes = io.github.canimation.core.CanimationSurfaceRecipes
typealias CanimationEmphasisRecipes = io.github.canimation.core.CanimationEmphasisRecipes
typealias CanimationTransitionRecipes = io.github.canimation.core.CanimationTransitionRecipes
typealias CanimationAmbientRecipes = io.github.canimation.core.CanimationAmbientRecipes
typealias CanimationRecoveryRecipes = io.github.canimation.core.CanimationRecoveryRecipes
typealias PresetsExtensionRegistry = io.github.canimation.presets.PresetsExtensionRegistry

@Suppress("UnusedReceiverParameter")
val Canimation.Content: CanimationContentRecipes
    get() = CanimationContentRecipes

@Suppress("UnusedReceiverParameter")
val Canimation.Feedback: CanimationFeedbackRecipes
    get() = CanimationFeedbackRecipes

@Suppress("UnusedReceiverParameter")
val Canimation.Navigation: CanimationNavigationRecipes
    get() = CanimationNavigationRecipes

@Suppress("UnusedReceiverParameter")
val Canimation.Surface: CanimationSurfaceRecipes
    get() = CanimationSurfaceRecipes

@Suppress("UnusedReceiverParameter")
val Canimation.Emphasis: CanimationEmphasisRecipes
    get() = CanimationEmphasisRecipes

@Suppress("UnusedReceiverParameter")
val Canimation.Transition: CanimationTransitionRecipes
    get() = CanimationTransitionRecipes

@Suppress("UnusedReceiverParameter")
val Canimation.Ambient: CanimationAmbientRecipes
    get() = CanimationAmbientRecipes

@Suppress("UnusedReceiverParameter")
val Canimation.Recovery: CanimationRecoveryRecipes
    get() = CanimationRecoveryRecipes

@Suppress("unused")
val DefaultCanimationRecipeRegistry: CanimationRecipeRegistry =
    io.github.canimation.core.DefaultCanimationRecipeRegistry.merge(
        PresetsExtensionRegistry.recipeRegistry,
        CanimationRecipeRegistryMergePolicy.Fail,
    )

fun defaultCanimationRecipeRegistry(
    vararg extensions: CanimationRecipeRegistry,
    policy: CanimationRecipeRegistryMergePolicy = CanimationRecipeRegistryMergePolicy.Fail,
): CanimationRecipeRegistry =
    defaultCanimationRecipeRegistry(
        extensions = extensions.asList(),
        policy = policy,
    )

fun defaultCanimationRecipeRegistry(
    extensions: Iterable<CanimationRecipeRegistry>,
    policy: CanimationRecipeRegistryMergePolicy = CanimationRecipeRegistryMergePolicy.Fail,
): CanimationRecipeRegistry =
    extensions.fold(DefaultCanimationRecipeRegistry) { registry, extension ->
        registry.merge(extension, policy)
    }

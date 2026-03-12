package io.github.canimation.recipes

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.CanimationRecipeRegistryMergePolicy
import io.github.canimation.core.CanimationAmbientRecipes as CoreCanimationAmbientRecipes
import io.github.canimation.core.CanimationContentRecipes as CoreCanimationContentRecipes
import io.github.canimation.core.CanimationEmphasisRecipes as CoreCanimationEmphasisRecipes
import io.github.canimation.core.CanimationFeedbackRecipes as CoreCanimationFeedbackRecipes
import io.github.canimation.core.CanimationNavigationRecipes as CoreCanimationNavigationRecipes
import io.github.canimation.core.CanimationRecoveryRecipes as CoreCanimationRecoveryRecipes
import io.github.canimation.core.CanimationSurfaceRecipes as CoreCanimationSurfaceRecipes
import io.github.canimation.core.CanimationTransitionRecipes as CoreCanimationTransitionRecipes
import io.github.canimation.core.DefaultCanimationRecipeRegistry as CoreDefaultCanimationRecipeRegistry
import io.github.canimation.presets.PresetsExtensionRegistry as CorePresetsExtensionRegistry

typealias CanimationContentRecipes = CoreCanimationContentRecipes
typealias CanimationFeedbackRecipes = CoreCanimationFeedbackRecipes
typealias CanimationNavigationRecipes = CoreCanimationNavigationRecipes
typealias CanimationSurfaceRecipes = CoreCanimationSurfaceRecipes
typealias CanimationEmphasisRecipes = CoreCanimationEmphasisRecipes
typealias CanimationTransitionRecipes = CoreCanimationTransitionRecipes
typealias CanimationAmbientRecipes = CoreCanimationAmbientRecipes
typealias CanimationRecoveryRecipes = CoreCanimationRecoveryRecipes
typealias PresetsExtensionRegistry = CorePresetsExtensionRegistry

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
    CoreDefaultCanimationRecipeRegistry.merge(
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

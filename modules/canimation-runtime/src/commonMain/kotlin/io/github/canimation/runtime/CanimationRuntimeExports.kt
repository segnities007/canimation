package io.github.canimation.runtime

import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.CanimationRecipeRegistryMergePolicy
import io.github.canimation.core.PresetRegistry
import io.github.canimation.presets.PresetsExtensionRegistry
import io.github.canimation.recipes.CanimationAmbientRecipes
import io.github.canimation.recipes.CanimationContentRecipes
import io.github.canimation.recipes.CanimationEmphasisRecipes
import io.github.canimation.recipes.CanimationFeedbackRecipes
import io.github.canimation.recipes.CanimationNavigationRecipes
import io.github.canimation.recipes.CanimationRecoveryRecipes
import io.github.canimation.recipes.CanimationSurfaceRecipes
import io.github.canimation.recipes.CanimationTransitionRecipes
import io.github.canimation.core.Canimation as CoreCanimation
import io.github.canimation.core.CanimationContext as CoreCanimationContext
import io.github.canimation.recipes.DefaultCanimationRecipeRegistry as DefaultRecipeRegistry
import io.github.canimation.recipes.defaultCanimationRecipeRegistry as createDefaultRecipeRegistry

typealias Canimation = CoreCanimation
typealias CanimationContext = CoreCanimationContext

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
val DefaultCanimationPresetRegistry: PresetRegistry = PresetsExtensionRegistry.createExtendedRegistry()

@Suppress("unused")
val DefaultCanimationRecipeRegistry: CanimationRecipeRegistry = DefaultRecipeRegistry

fun defaultCanimationRecipeRegistry(
    vararg extensions: CanimationRecipeRegistry,
    policy: CanimationRecipeRegistryMergePolicy = CanimationRecipeRegistryMergePolicy.Fail,
): CanimationRecipeRegistry =
    createDefaultRecipeRegistry(
        extensions = extensions.asList(),
        policy = policy,
    )

fun defaultCanimationRecipeRegistry(
    extensions: Iterable<CanimationRecipeRegistry>,
    policy: CanimationRecipeRegistryMergePolicy = CanimationRecipeRegistryMergePolicy.Fail,
): CanimationRecipeRegistry =
    createDefaultRecipeRegistry(
        extensions = extensions,
        policy = policy,
    )

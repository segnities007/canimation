package io.github.canimation.experimental

import io.github.canimation.core.CanimationRecipe as CoreCanimationRecipe
import io.github.canimation.core.CanimationRecipeDescriptor as CoreCanimationRecipeDescriptor
import io.github.canimation.core.CanimationRecipeRegistry as CoreCanimationRecipeRegistry

typealias CanimationRecipe = CoreCanimationRecipe
typealias CanimationRecipeDescriptor = CoreCanimationRecipeDescriptor
typealias CanimationRecipeRegistry = CoreCanimationRecipeRegistry

@RequiresOptIn(
    level = RequiresOptIn.Level.WARNING,
    message = "Experimental Canimation APIs may change without compatibility guarantees.",
)
annotation class ExperimentalCanimationApi

@ExperimentalCanimationApi
fun experimentalRecipeRegistry(vararg descriptors: CanimationRecipeDescriptor): CanimationRecipeRegistry =
    experimentalRecipeRegistry(descriptors.asIterable())

@ExperimentalCanimationApi
fun experimentalRecipeRegistry(descriptors: Iterable<CanimationRecipeDescriptor>): CanimationRecipeRegistry =
    CanimationRecipeRegistry(descriptors.associateBy { it.id })

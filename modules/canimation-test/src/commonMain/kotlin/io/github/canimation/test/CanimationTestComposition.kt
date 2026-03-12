package io.github.canimation.test

import androidx.compose.runtime.Immutable
import io.github.canimation.core.CanimationLevel
import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.CanimationTokens
import io.github.canimation.core.PresetRegistry
import io.github.canimation.presets.PresetsExtensionRegistry
import io.github.canimation.recipes.DefaultCanimationRecipeRegistry as DefaultSemanticRecipeRegistry

@Immutable
data class CanimationTestComposition(
    val presetRegistry: PresetRegistry,
    val recipeRegistry: CanimationRecipeRegistry,
    val level: CanimationLevel = CanimationLevel.Full,
    val tokens: CanimationTokens = CanimationTokens.Default,
)

val DefaultCanimationTestPresetRegistry: PresetRegistry =
    PresetsExtensionRegistry.createExtendedRegistry()

val DefaultCanimationTestRecipeRegistry: CanimationRecipeRegistry = DefaultSemanticRecipeRegistry

val DefaultCanimationTestComposition: CanimationTestComposition =
    CanimationTestComposition(
        presetRegistry = DefaultCanimationTestPresetRegistry,
        recipeRegistry = DefaultCanimationTestRecipeRegistry,
    )

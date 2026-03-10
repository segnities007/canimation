package io.github.canimation.testkit

import androidx.compose.runtime.Composable
import io.github.canimation.core.CanimationLevel
import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.CanimationTokens
import io.github.canimation.core.PresetRegistry
import io.github.canimation.runtime.DefaultCanimationRecipeRegistry
import io.github.canimation.test.CanimationTestHost as CoreCanimationTestHost

@Composable
fun CanimationTestHost(
    clock: CanimationTestClock,
    level: CanimationLevel = CanimationLevel.Full,
    tokens: CanimationTokens = CanimationTokens.Default,
    presetRegistry: PresetRegistry = PresetRegistry.Default,
    recipeRegistry: CanimationRecipeRegistry = DefaultCanimationRecipeRegistry,
    content: @Composable () -> Unit,
) {
    CoreCanimationTestHost(
        clock = clock,
        level = level,
        tokens = tokens,
        presetRegistry = presetRegistry,
        recipeRegistry = recipeRegistry,
        content = content,
    )
}

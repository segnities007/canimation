package io.github.canimation.testkit

import androidx.compose.runtime.Composable
import io.github.canimation.core.CanimationLevel
import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.CanimationTokens
import io.github.canimation.core.PresetRegistry
import io.github.canimation.test.CanimationTestComposition as CoreCanimationTestComposition
import io.github.canimation.test.CanimationTestHost as CoreCanimationTestHost

@Composable
fun CanimationTestHost(
    clock: CanimationTestClock,
    composition: CanimationTestComposition,
    content: @Composable () -> Unit,
) {
    CoreCanimationTestHost(
        clock = clock,
        composition = composition,
        content = content,
    )
}

@Deprecated(
    message = "Prefer the overload that accepts CanimationTestComposition so test inputs stay explicit.",
    replaceWith =
        ReplaceWith(
            expression =
                "CanimationTestHost(clock = clock, composition = CanimationTestComposition(presetRegistry = presetRegistry, recipeRegistry = recipeRegistry, level = level, tokens = tokens), content = content)",
            imports = ["io.github.canimation.testkit.CanimationTestComposition"],
        ),
)
@Composable
fun CanimationTestHost(
    clock: CanimationTestClock,
    level: CanimationLevel = CanimationLevel.Full,
    tokens: CanimationTokens = CanimationTokens.Default,
    presetRegistry: PresetRegistry = DefaultCanimationTestPresetRegistry,
    recipeRegistry: CanimationRecipeRegistry = DefaultCanimationTestRecipeRegistry,
    content: @Composable () -> Unit,
) {
    CoreCanimationTestHost(
        clock = clock,
        composition =
            CoreCanimationTestComposition(
                presetRegistry = presetRegistry,
                recipeRegistry = recipeRegistry,
                level = level,
                tokens = tokens,
            ),
        content = content,
    )
}

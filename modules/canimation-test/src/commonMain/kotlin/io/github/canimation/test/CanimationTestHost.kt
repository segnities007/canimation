package io.github.canimation.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import io.github.canimation.core.CanimationContext
import io.github.canimation.core.CanimationLevel
import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.CanimationTokens
import io.github.canimation.core.LocalCanimationContext
import io.github.canimation.core.PresetRegistry

/**
 * Test host composable that injects a [CanimationTestClock] into the composition.
 *
 * Wraps content with an explicit [CanimationTestComposition] and uses [clock] as the injected
 * time source for deterministic animation testing.
 *
 * @param clock The test clock to use for time control.
 * @param composition Explicit animation composition inputs used for the hosted test content.
 * @param content The composable content to test.
 */
@Composable
fun CanimationTestHost(
    clock: CanimationTestClock,
    composition: CanimationTestComposition,
    content: @Composable () -> Unit,
) {
    val context =
        CanimationContext(
            tokens = composition.tokens,
            level = composition.level,
            presetRegistry = composition.presetRegistry,
            recipeRegistry = composition.recipeRegistry,
            timeSourceMillis = { clock.currentTimeMillis },
        )
    CompositionLocalProvider(LocalCanimationContext provides context) {
        content()
    }
}

@Deprecated(
    message = "Prefer the overload that accepts CanimationTestComposition so test inputs stay explicit.",
    replaceWith =
        ReplaceWith(
            expression =
                "CanimationTestHost(clock = clock, composition = CanimationTestComposition(presetRegistry = presetRegistry, recipeRegistry = recipeRegistry, level = level, tokens = tokens), content = content)",
            imports = ["io.github.canimation.test.CanimationTestComposition"],
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
    CanimationTestHost(
        clock = clock,
        composition =
            CanimationTestComposition(
                presetRegistry = presetRegistry,
                recipeRegistry = recipeRegistry,
                level = level,
                tokens = tokens,
            ),
        content = content,
    )
}

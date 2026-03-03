package io.github.canimation.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import io.github.canimation.core.CanimationContext
import io.github.canimation.core.CanimationLevel
import io.github.canimation.core.CanimationTokens
import io.github.canimation.core.LocalCanimationContext
import io.github.canimation.core.PresetRegistry

/**
 * Test host composable that injects a [CanimationTestClock] into the composition.
 *
 * Wraps content with a [CanimationContext] that uses the test clock as its time source,
 * enabling deterministic animation testing.
 *
 * @param clock The test clock to use for time control.
 * @param level The animation level to use in tests (default: [CanimationLevel.Full]).
 * @param tokens The animation tokens to use (default: [CanimationTokens.Default]).
 * @param presetRegistry The preset registry to use (default: [PresetRegistry.Default]).
 * @param content The composable content to test.
 */
@Composable
fun CanimationTestHost(
    clock: CanimationTestClock,
    level: CanimationLevel = CanimationLevel.Full,
    tokens: CanimationTokens = CanimationTokens.Default,
    presetRegistry: PresetRegistry = PresetRegistry.Default,
    content: @Composable () -> Unit,
) {
    val context = CanimationContext(
        tokens = tokens,
        level = level,
        presetRegistry = presetRegistry,
        timeSourceMillis = { clock.currentTimeMillis },
    )
    CompositionLocalProvider(LocalCanimationContext provides context) {
        content()
    }
}

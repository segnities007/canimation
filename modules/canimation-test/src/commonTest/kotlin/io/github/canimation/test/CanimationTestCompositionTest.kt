package io.github.canimation.test

import io.github.canimation.core.AnimationDirection
import io.github.canimation.core.CanimationLevel
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationTokens
import io.github.canimation.presets.PresetsExtensionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue

class CanimationTestCompositionTest {
    @Test
    fun defaultCompositionExposesExplicitTestDefaults() {
        assertSame(DefaultCanimationTestPresetRegistry, DefaultCanimationTestComposition.presetRegistry)
        assertSame(DefaultCanimationTestRecipeRegistry, DefaultCanimationTestComposition.recipeRegistry)
        assertEquals(CanimationLevel.Full, DefaultCanimationTestComposition.level)
        assertSame(CanimationTokens.Default, DefaultCanimationTestComposition.tokens)
    }

    @Test
    fun defaultTestPresetRegistryUsesPresetSsot() {
        val expected = PresetsExtensionRegistry.allPresetSpecs.getValue(CanimationPreset.FadeUp).fullEnter
        val actual =
            DefaultCanimationTestPresetRegistry.resolve(
                CanimationPreset.FadeUp,
                CanimationLevel.Full,
                AnimationDirection.Enter,
            )

        assertEquals(expected, actual)
    }

    @Test
    fun defaultTestRecipeRegistryExposesPresetAndSemanticDescriptors() {
        val ids = DefaultCanimationTestRecipeRegistry.ids().map { it.value }.toSet()

        assertTrue("preset.FadeUp" in ids)
        assertTrue("semantic.content.enter-subtle" in ids)
    }
}

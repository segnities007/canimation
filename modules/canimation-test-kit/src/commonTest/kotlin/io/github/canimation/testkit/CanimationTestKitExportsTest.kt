package io.github.canimation.testkit

import io.github.canimation.core.AnimationDirection
import io.github.canimation.core.CanimationLevel
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.CanimationTokens
import io.github.canimation.core.PresetRegistry
import io.github.canimation.presets.PresetsExtensionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertSame
import kotlin.test.assertTrue

class CanimationTestKitExportsTest {
    @Test
    fun testClockAliasWorks() {
        val clock = CanimationTestClock()
        clock.advanceBy(42)

        assertEquals(42, clock.currentTimeMillis)
    }

    @Test
    fun testClockFactorySupportsInitialTime() {
        val clock = canimationTestClock(initialTimeMillis = 48)

        assertEquals(48, clock.currentTimeMillis)
    }

    @Test
    fun advanceByFrameUsesDefaultFrameDuration() {
        val clock = canimationTestClock()

        clock.advanceByFrame()

        assertEquals(16, clock.currentTimeMillis)
    }

    @Test
    fun advanceByFramesUsesCustomFrameDuration() {
        val clock = canimationTestClock()

        clock.advanceByFrames(frameCount = 3, frameDurationMillis = 8)

        assertEquals(24, clock.currentTimeMillis)
    }

    @Test
    fun advanceByFramesRejectsNegativeFrameCount() {
        val clock = canimationTestClock()

        assertFailsWith<IllegalArgumentException> {
            clock.advanceByFrames(frameCount = -1)
        }
    }

    @Test
    fun testKitExposesDefaultRecipeRegistry() {
        val registry: CanimationRecipeRegistry = DefaultCanimationRecipeRegistry

        assertTrue(registry.ids().any { it.value == "preset.FadeUp" })
        assertTrue(registry.ids().any { it.value == "semantic.content.enter-subtle" })
    }

    @Test
    fun testKitExposesExplicitTestCompositionDefaults() {
        val composition: CanimationTestComposition = DefaultCanimationTestComposition

        assertSame(DefaultCanimationTestPresetRegistry, composition.presetRegistry)
        assertSame(DefaultCanimationTestRecipeRegistry, composition.recipeRegistry)
        assertEquals(CanimationLevel.Full, composition.level)
        assertSame(CanimationTokens.Default, composition.tokens)
    }

    @Test
    fun testKitExposesDefaultTestPresetRegistry() {
        val registry: PresetRegistry = DefaultCanimationTestPresetRegistry
        val expected = PresetsExtensionRegistry.allPresetSpecs.getValue(CanimationPreset.FadeUp).fullEnter
        val actual =
            registry.resolve(
                CanimationPreset.FadeUp,
                CanimationLevel.Full,
                AnimationDirection.Enter,
            )

        assertEquals(expected, actual)
    }
}

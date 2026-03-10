package io.github.canimation.presets

import io.github.canimation.core.AnimationDirection
import io.github.canimation.core.CanimationLevel
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationSpec
import io.github.canimation.core.EasingTokens
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PresetsExtensionRegistryTest {
    @Test
    fun specForReturnsRegisteredSpecFromSsot() {
        val expected = PresetsExtensionRegistry.allPresetSpecs.getValue(CanimationPreset.Fade)

        val actual = PresetsExtensionRegistry.specFor(CanimationPreset.Fade)

        assertEquals(expected, actual)
    }

    @Test
    fun createExtendedRegistryOverridesDefaultPreset() {
        val override =
            CanimationPresetSpec(
                fullEnter = CanimationSpec(durationMs = 777, easing = EasingTokens.Default.standard),
                fullExit = CanimationSpec(durationMs = 666, easing = EasingTokens.Default.accelerate),
                reducedEnter = CanimationSpec(durationMs = 120, easing = EasingTokens.Default.decelerate),
                reducedExit = CanimationSpec(durationMs = 100, easing = EasingTokens.Default.accelerate),
            )

        val registry =
            PresetsExtensionRegistry.createExtendedRegistry(
                additional = mapOf(CanimationPreset.FadeUp to override),
            )

        val actual =
            registry.resolve(
                CanimationPreset.FadeUp,
                CanimationLevel.Full,
                AnimationDirection.Enter,
            )

        assertEquals(777, actual.durationMs)
    }

    @Test
    fun createExtendedRegistryFallsBackToDefaultsForUnchangedPreset() {
        val registry = PresetsExtensionRegistry.createExtendedRegistry()

        val expected = PresetsExtensionRegistry.allPresetSpecs.getValue(CanimationPreset.ZoomIn).fullEnter
        val actual =
            registry.resolve(
                CanimationPreset.ZoomIn,
                CanimationLevel.Full,
                AnimationDirection.Enter,
            )

        assertEquals(expected, actual)
    }

    @Test
    fun recipeRegistryContainsPresetDescriptorsOnly() {
        val ids =
            PresetsExtensionRegistry.recipeRegistry
                .ids()
                .map { it.value }
                .toSet()

        assertTrue("preset.FadeUp" in ids)
    }

    @Test
    fun recipeRegistryKeepsPresetDescriptorAsRegistrySourceOfTruth() {
        val descriptor = PresetsExtensionRegistry.descriptorFor(CanimationPreset.FadeUp)

        assertEquals(descriptor, PresetsExtensionRegistry.recipeRegistry.getValue(descriptor.id))
    }

    @Test
    fun createExtendedRegistryDoesNotChangeRecipeRegistryDescriptorLayer() {
        PresetsExtensionRegistry.createExtendedRegistry(
            additional = mapOf(CanimationPreset.FadeUp to PresetsExtensionRegistry.allPresetSpecs.getValue(CanimationPreset.Fade)),
        )

        assertEquals(
            PresetsExtensionRegistry.descriptorFor(CanimationPreset.FadeUp),
            PresetsExtensionRegistry.recipeRegistry.getValue(PresetsExtensionRegistry.descriptorFor(CanimationPreset.FadeUp).id),
        )
    }
}

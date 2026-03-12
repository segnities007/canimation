package io.github.canimation.presets

import io.github.canimation.core.CanimationCost
import io.github.canimation.core.CanimationIntent
import io.github.canimation.core.CanimationPreset
import io.github.canimation.recipes.DefaultCanimationRecipeRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RecipeDescriptorIntegrityTest {
    @Test
    fun allPresetsExposeSemanticDescriptors() {
        assertEquals(CanimationPreset.entries.toSet(), PresetsExtensionRegistry.allRecipeDescriptors.keys)
    }

    @Test
    fun descriptorDocsAndOffSpecAreAlwaysPresent() {
        PresetsExtensionRegistry.allRecipeDescriptors.forEach { (preset, descriptor) ->
            assertTrue(descriptor.docs.title.isNotBlank(), "$preset title must not be blank")
            assertTrue(descriptor.docs.summary.isNotBlank(), "$preset summary must not be blank")
            assertTrue(descriptor.docs.usageGuidance.isNotBlank(), "$preset usage guidance must not be blank")
            assertEquals(0, descriptor.specs.off.durationMs, "$preset off spec must snap instantly")
            assertTrue(descriptor.accessibility.supportsOff, "$preset must support motion off")
        }
    }

    @Test
    fun layoutCostDescriptorsMustRequireBenchmark() {
        PresetsExtensionRegistry.allRecipeDescriptors.forEach { (preset, descriptor) ->
            if (descriptor.performance.cost == CanimationCost.Layout) {
                assertTrue(
                    descriptor.performance.benchmarkRequired,
                    "$preset layout-cost descriptor must require benchmark validation",
                )
            }
        }
    }

    @Test
    fun descriptorLookupMatchesRegistry() {
        val descriptor = PresetsExtensionRegistry.descriptorFor(CanimationPreset.FadeUp)
        val fromRegistry = PresetsExtensionRegistry.recipeRegistry.descriptorFor(descriptor.id)

        assertNotNull(fromRegistry)
        assertEquals(descriptor, fromRegistry)
    }

    @Test
    fun builtInPresetRegistryMergesWithSemanticRegistry() {
        val merged =
            DefaultCanimationRecipeRegistry.withDescriptors(PresetsExtensionRegistry.recipeRegistry.all())

        assertTrue(merged.ids().any { it.value == "semantic.content.enter-subtle" })
        assertTrue(merged.ids().any { it.value == "preset.FadeUp" })
    }

    @Test
    fun experimentalPresetDocsCarryDoNotUseGuidance() {
        val descriptor = PresetsExtensionRegistry.descriptorFor(CanimationPreset.JackInTheBox)

        assertEquals(CanimationIntent.Experimental, descriptor.semantic.intent)
        assertNotNull(descriptor.docs.doNotUseWhen)
        assertTrue(descriptor.docs.doNotUseWhen!!.isNotBlank())
    }

    @Test
    fun feedbackPresetUsesAttentionFamilyAndControlSurfaceRole() {
        val descriptor = PresetsExtensionRegistry.descriptorFor(CanimationPreset.Pulse)

        assertEquals(CanimationIntent.Feedback, descriptor.semantic.intent)
        assertEquals("Attention", descriptor.semantic.family)
        assertEquals(io.github.canimation.core.CanimationSurfaceRole.Control, descriptor.semantic.surfaceRole)
    }
}

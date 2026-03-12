package io.github.canimation.recipes

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationCost
import io.github.canimation.core.CanimationIntensity
import io.github.canimation.core.CanimationIntent
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationRecipe
import io.github.canimation.core.CanimationRecipeAccessibility
import io.github.canimation.core.CanimationRecipeDescriptor
import io.github.canimation.core.CanimationRecipeDocs
import io.github.canimation.core.CanimationRecipeId
import io.github.canimation.core.CanimationRecipePerformance
import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.CanimationRecipeRegistryMergePolicy
import io.github.canimation.core.CanimationRecipeSemantic
import io.github.canimation.core.CanimationRecipeSpecSet
import io.github.canimation.core.CanimationReducedStrategy
import io.github.canimation.core.CanimationSpec
import io.github.canimation.core.CanimationSurfaceRole
import io.github.canimation.core.EasingTokens
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CanimationRecipeExportsTest {
    @Test
    fun defaultRegistryContainsSemanticAndPresetRecipes() {
        val ids = DefaultCanimationRecipeRegistry.ids().map { it.value }.toSet()

        assertTrue("semantic.content.enter-subtle" in ids)
        assertTrue("preset.FadeUp" in ids)
    }

    @Test
    fun recipesPackageExposesSemanticNamespaceExtensions() {
        assertEquals("semantic.content.enter-subtle", Canimation.Content.EnterSubtle.id.value)
        assertEquals("semantic.feedback.press", Canimation.Feedback.Press.id.value)
    }

    @Test
    fun defaultRegistryFactoryMergesExplicitExtensions() {
        val registry = defaultCanimationRecipeRegistry(customRegistry("custom.recipe"))

        assertTrue(registry.ids().any { it.value == "semantic.content.enter-subtle" })
        assertTrue(registry.ids().any { it.value == "preset.FadeUp" })
        assertTrue(registry.ids().any { it.value == "custom.recipe" })
    }

    @Test
    fun defaultRegistryFactoryHonorsPreferHostPolicy() {
        val registry =
            defaultCanimationRecipeRegistry(
                extensions = listOf(customRegistry("preset.FadeUp", title = "Host Override")),
                policy = CanimationRecipeRegistryMergePolicy.PreferHost,
            )

        assertEquals(
            PresetsExtensionRegistry.descriptorFor(CanimationPreset.FadeUp),
            registry.getValue(CanimationRecipeId("preset.FadeUp")),
        )
    }

    @Test
    fun defaultRegistryRejectsDuplicatePresetIdsByDefault() {
        assertFailsWith<IllegalStateException> {
            defaultCanimationRecipeRegistry(customRegistry("preset.FadeUp", title = "Duplicate"))
        }
    }

    @Test
    fun semanticRegistryRemainsResolvableWithoutPresetRegistryLayer() {
        val descriptor = DefaultCanimationRecipeRegistry.resolve(Canimation.Content.EnterSubtle)

        assertEquals(Canimation.Content.EnterSubtle.descriptor, descriptor)
    }

    private fun customRegistry(
        id: String,
        title: String = id,
    ): CanimationRecipeRegistry =
        CanimationRecipeRegistry(
            mapOf(
                CanimationRecipeId(id) to
                    CanimationRecipeDescriptor(
                        id = CanimationRecipeId(id),
                        semantic =
                            CanimationRecipeSemantic(
                                intent = CanimationIntent.Experimental,
                                surfaceRole = CanimationSurfaceRole.Container,
                                intensity = CanimationIntensity.Subtle,
                                family = "Custom",
                                tags = setOf("custom"),
                            ),
                        accessibility =
                            CanimationRecipeAccessibility(
                                reducedStrategy = CanimationReducedStrategy.Compress,
                                supportsOff = true,
                            ),
                        performance =
                            CanimationRecipePerformance(
                                cost = CanimationCost.Transform,
                                listSafe = true,
                                benchmarkRequired = false,
                            ),
                        docs =
                            CanimationRecipeDocs(
                                title = title,
                                summary = "Custom descriptor for tests.",
                                usageGuidance = "Use only in tests.",
                            ),
                        specs =
                            CanimationRecipeSpecSet(
                                full = CanimationSpec(durationMs = 180, easing = EasingTokens.Default.standard),
                                reduced = CanimationSpec(durationMs = 90, easing = EasingTokens.Default.standard),
                                off = CanimationSpec(durationMs = 0, easing = EasingTokens.Default.standard),
                            ),
                    ),
            ),
        )
}

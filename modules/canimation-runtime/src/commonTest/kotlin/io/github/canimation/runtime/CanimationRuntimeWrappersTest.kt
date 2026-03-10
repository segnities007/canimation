package io.github.canimation.runtime

import io.github.canimation.core.CanimationCost
import io.github.canimation.core.CanimationIntensity
import io.github.canimation.core.CanimationIntent
import io.github.canimation.core.CanimationPreset
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
import io.github.canimation.recipes.PresetsExtensionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CanimationRuntimeWrappersTest {
    @Test
    fun runtimeRecipeRegistryExposesBuiltInSemanticRecipes() {
        val registry: CanimationRecipeRegistry = DefaultCanimationRecipeRegistry
        val descriptor = registry.descriptorFor(Canimation.Content.EnterSubtle.id)

        assertNotNull(descriptor)
        assertEquals("semantic.content.enter-subtle", descriptor.id.value)
    }

    @Test
    fun runtimeNamespaceExposesSemanticRecipeFamilies() {
        assertEquals("semantic.navigation.forward", Canimation.Navigation.Forward.id.value)
        assertEquals("semantic.recovery.error-shake", Canimation.Recovery.ErrorShake.id.value)
    }

    @Test
    fun runtimeRegistryFactoryStartsFromSemanticAndPresetDefaults() {
        val registry = defaultCanimationRecipeRegistry(customRegistry("runtime.custom"))

        assertTrue(registry.ids().any { it.value == "semantic.content.enter-subtle" })
        assertTrue(registry.ids().any { it.value == "preset.FadeUp" })
        assertTrue(registry.ids().any { it.value == "runtime.custom" })
    }

    @Test
    fun runtimeRegistryFactoryRespectsPreferHostForPresetIds() {
        val registry =
            defaultCanimationRecipeRegistry(
                extensions = listOf(customRegistry("preset.FadeUp", title = "Runtime Override")),
                policy = CanimationRecipeRegistryMergePolicy.PreferHost,
            )

        assertEquals(
            DefaultCanimationRecipeRegistry.getValue(CanimationRecipeId("preset.FadeUp")),
            registry.getValue(CanimationRecipeId("preset.FadeUp")),
        )
    }

    @Test
    fun runtimeDefaultRegistryKeepsSemanticAndPresetCatalogsTogether() {
        assertEquals(
            Canimation.Content.EnterSubtle.descriptor,
            DefaultCanimationRecipeRegistry.getValue(Canimation.Content.EnterSubtle.id),
        )
        assertEquals(
            PresetsExtensionRegistry.descriptorFor(CanimationPreset.FadeUp),
            DefaultCanimationRecipeRegistry.getValue(CanimationRecipeId("preset.FadeUp")),
        )
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
                                family = "Runtime",
                                tags = setOf("runtime"),
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
                                summary = "Runtime test descriptor.",
                                usageGuidance = "Use only in runtime export tests.",
                            ),
                        specs =
                            CanimationRecipeSpecSet(
                                full = CanimationSpec(durationMs = 200, easing = EasingTokens.Default.standard),
                                reduced = CanimationSpec(durationMs = 100, easing = EasingTokens.Default.standard),
                                off = CanimationSpec(durationMs = 0, easing = EasingTokens.Default.standard),
                            ),
                    ),
            ),
        )
}

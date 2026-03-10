package io.github.canimation.experimental

import io.github.canimation.core.CanimationCost
import io.github.canimation.core.CanimationIntensity
import io.github.canimation.core.CanimationIntent
import io.github.canimation.core.CanimationRecipeAccessibility
import io.github.canimation.core.CanimationRecipeDocs
import io.github.canimation.core.CanimationRecipeId
import io.github.canimation.core.CanimationRecipePerformance
import io.github.canimation.core.CanimationRecipeSemantic
import io.github.canimation.core.CanimationRecipeSpecSet
import io.github.canimation.core.CanimationReducedStrategy
import io.github.canimation.core.CanimationSpec
import io.github.canimation.core.CanimationSurfaceRole
import io.github.canimation.core.EasingTokens
import kotlin.test.Test
import kotlin.test.assertEquals

class CanimationExperimentalExportsTest {
    @OptIn(ExperimentalCanimationApi::class)
    @Test
    fun experimentalRegistryBuildsFromDescriptors() {
        val descriptor = experimentalDescriptor("experimental.spark")

        val registry = experimentalRecipeRegistry(descriptor)

        assertEquals(descriptor, registry.getValue(descriptor.id))
    }

    @OptIn(ExperimentalCanimationApi::class)
    @Test
    fun lastDescriptorWinsForDuplicateIds() {
        val original = experimentalDescriptor("experimental.spark", title = "Original")
        val override = experimentalDescriptor("experimental.spark", title = "Override")

        val registry = experimentalRecipeRegistry(listOf(original, override))

        assertEquals("Override", registry.getValue(original.id).docs.title)
    }

    private fun experimentalDescriptor(
        id: String,
        title: String = id,
    ): CanimationRecipeDescriptor =
        CanimationRecipeDescriptor(
            id = CanimationRecipeId(id),
            semantic =
                CanimationRecipeSemantic(
                    intent = CanimationIntent.Experimental,
                    surfaceRole = CanimationSurfaceRole.Decoration,
                    intensity = CanimationIntensity.Subtle,
                    family = "Experimental",
                    tags = setOf("experimental"),
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
                    summary = "Experimental descriptor for tests.",
                    usageGuidance = "Use only in experimental tests.",
                ),
            specs =
                CanimationRecipeSpecSet(
                    full = CanimationSpec(durationMs = 220, easing = EasingTokens.Default.standard),
                    reduced = CanimationSpec(durationMs = 110, easing = EasingTokens.Default.standard),
                    off = CanimationSpec(durationMs = 0, easing = EasingTokens.Default.standard),
                ),
        )
}

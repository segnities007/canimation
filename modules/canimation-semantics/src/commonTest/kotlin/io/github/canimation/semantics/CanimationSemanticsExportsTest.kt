package io.github.canimation.semantics

import androidx.compose.animation.core.LinearEasing
import io.github.canimation.primitives.CanimationSpec
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CanimationSemanticsExportsTest {
    @Test
    fun descriptorRejectsNonSnapOffSpec() {
        assertFailsWith<IllegalArgumentException> {
            CanimationRecipeDescriptor(
                id = CanimationRecipeId("semantic.test.invalid"),
                semantic =
                    CanimationRecipeSemantic(
                        intent = CanimationIntent.Content,
                        surfaceRole = CanimationSurfaceRole.Container,
                        intensity = CanimationIntensity.Subtle,
                        family = "Content",
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
                        title = "Invalid",
                        summary = "Invalid",
                        usageGuidance = "Do not use.",
                    ),
                specs =
                    CanimationRecipeSpecSet(
                        full = spec(durationMs = 220),
                        reduced = spec(durationMs = 120),
                        off = spec(durationMs = 1),
                    ),
            )
        }
    }

    @Test
    fun registryPrefersExtensionWhenRequested() {
        val id = CanimationRecipeId("semantic.test.override")
        val host = CanimationRecipeRegistry(mapOf(id to descriptor(id, title = "Host")))
        val extension = CanimationRecipeRegistry(mapOf(id to descriptor(id, title = "Extension")))

        val merged = host.merge(extension, CanimationRecipeRegistryMergePolicy.PreferExtension)

        assertEquals("Extension", merged.getValue(id).docs.title)
    }

    private fun descriptor(
        id: CanimationRecipeId,
        title: String,
    ): CanimationRecipeDescriptor =
        CanimationRecipeDescriptor(
            id = id,
            semantic =
                CanimationRecipeSemantic(
                    intent = CanimationIntent.Content,
                    surfaceRole = CanimationSurfaceRole.Container,
                    intensity = CanimationIntensity.Subtle,
                    family = "Content",
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
                    summary = "Summary",
                    usageGuidance = "Guidance",
                ),
            specs =
                CanimationRecipeSpecSet(
                    full = spec(durationMs = 220),
                    reduced = spec(durationMs = 120),
                    off = spec(durationMs = 0),
                ),
        )

    private fun spec(durationMs: Int): CanimationSpec =
        CanimationSpec(
            durationMs = durationMs,
            easing = LinearEasing,
        )
}

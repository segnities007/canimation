package io.github.canimation.core

import io.github.canimation.recipes.DefaultCanimationRecipeRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CanimationSemanticRecipeDescriptorTest {
    @Test
    fun semanticRecipesProvideOffAndUsageGuidance() {
        DefaultCanimationRecipeRegistry.all().forEach { descriptor ->
            assertEquals(0, descriptor.specs.off.durationMs)
            assertTrue(descriptor.docs.usageGuidance.isNotBlank())
            assertTrue(descriptor.accessibility.supportsOff)
        }
    }

    @Test
    fun feedbackPressUsesFeedbackIntent() {
        val descriptor = Canimation.Feedback.Press.descriptor

        assertEquals(CanimationIntent.Feedback, descriptor.semantic.intent)
        assertEquals(CanimationSurfaceRole.Control, descriptor.semantic.surfaceRole)
    }

    @Test
    fun dialogRevealUsesSurfaceIntent() {
        val descriptor = Canimation.Surface.DialogReveal.descriptor

        assertEquals(CanimationIntent.Surface, descriptor.semantic.intent)
        assertEquals(CanimationSurfaceRole.Overlay, descriptor.semantic.surfaceRole)
    }
}

package io.github.canimation.core

import io.github.canimation.recipes.DefaultCanimationRecipeRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CanimationRecipeRegistryTest {
    @Test
    fun defaultSemanticRegistryContainsExpectedFamilies() {
        val ids = DefaultCanimationRecipeRegistry.ids().map { it.value }.toSet()

        assertTrue("semantic.content.enter-subtle" in ids)
        assertTrue("semantic.surface.dialog-reveal" in ids)
        assertTrue("semantic.recovery.error-shake" in ids)
    }

    @Test
    fun mergedRegistryFailsOnDuplicateIdsByDefault() {
        val duplicate =
            CanimationRecipeRegistry(
                mapOf(Canimation.Content.EnterSubtle.id to Canimation.Content.EnterSubtle.descriptor),
            )

        assertFailsWith<IllegalStateException> {
            DefaultCanimationRecipeRegistry.merge(duplicate)
        }
    }

    @Test
    fun preferHostKeepsHostDescriptorAndAddsUniqueExtensionDescriptors() {
        val duplicateId = Canimation.Content.EnterSubtle.id
        val uniqueId = CanimationRecipeId("custom.unique")
        val hostDescriptor = DefaultCanimationRecipeRegistry.getValue(duplicateId)
        val extension =
            CanimationRecipeRegistry(
                mapOf(
                    duplicateId to
                        hostDescriptor.copy(
                            docs = hostDescriptor.docs.copy(title = "Duplicate Override"),
                        ),
                    uniqueId to
                        Canimation.Surface.DialogReveal.descriptor.copy(
                            id = uniqueId,
                            docs =
                                Canimation.Surface.DialogReveal.descriptor.docs
                                    .copy(title = "Unique Extension"),
                        ),
                ),
            )

        val merged =
            DefaultCanimationRecipeRegistry.merge(
                extension = extension,
                policy = CanimationRecipeRegistryMergePolicy.PreferHost,
            )

        assertEquals(hostDescriptor, merged.getValue(duplicateId))
        assertEquals("Unique Extension", merged.getValue(uniqueId).docs.title)
    }

    @Test
    fun registryResolveFallsBackToRecipeDescriptorWhenMissing() {
        val resolved = CanimationRecipeRegistry.Empty.resolve(Canimation.Content.EnterSubtle)

        assertEquals(Canimation.Content.EnterSubtle.descriptor, resolved)
    }
}

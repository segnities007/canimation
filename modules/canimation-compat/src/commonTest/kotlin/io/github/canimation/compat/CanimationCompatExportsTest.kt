package io.github.canimation.compat

import io.github.canimation.core.CanimationRecipeRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue
import io.github.canimation.core.CanimationEffect as CoreCanimationEffect
import io.github.canimation.core.CanimationPreset as CoreCanimationPreset
import io.github.canimation.core.PresetRegistry as CorePresetRegistry

class CanimationCompatExportsTest {
    @Test
    fun compatAliasesPointToCoreTypes() {
        val preset: CanimationPreset = CanimationPreset.FadeUp
        val registry: PresetRegistry = PresetRegistry.Default

        assertEquals(CoreCanimationPreset.FadeUp, preset)
        assertSame(CorePresetRegistry.Default, registry)
    }

    @Test
    fun compatExposesRuntimeDefaultRecipeRegistry() {
        val registry: CanimationRecipeRegistry = DefaultCanimationRecipeRegistry

        assertTrue(registry.ids().any { it.value == "preset.FadeUp" })
        assertTrue(registry.ids().any { it.value == "semantic.content.enter-subtle" })
    }

    @Test
    fun compatEffectAliasStaysUsable() {
        val effect: CanimationEffect = CanimationEffect.fade()

        assertEquals(CoreCanimationEffect.fade(), effect)
    }
}

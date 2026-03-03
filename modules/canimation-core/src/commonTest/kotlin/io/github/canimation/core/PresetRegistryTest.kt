package io.github.canimation.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PresetRegistryTest {
    @Test
    fun defaultRegistryContainsAllPresets() {
        val registry = PresetRegistry.Default
        for (preset in CanimationPreset.entries) {
            val spec = registry.resolve(preset, CanimationLevel.Full, AnimationDirection.Enter)
            assertNotNull(spec)
        }
    }

    @Test
    fun fadeUpEnterHasOffsetY() {
        val spec = PresetRegistry.Default.resolve(
            CanimationPreset.FadeUp,
            CanimationLevel.Full,
            AnimationDirection.Enter,
        )
        assertNotNull(spec.offsetY)
    }

    @Test
    fun fadeEnterHasNoOffset() {
        val spec = PresetRegistry.Default.resolve(
            CanimationPreset.Fade,
            CanimationLevel.Full,
            AnimationDirection.Enter,
        )
        assertEquals(null, spec.offsetX)
        assertEquals(null, spec.offsetY)
    }

    @Test
    fun scaleInEnterHasScale() {
        val spec = PresetRegistry.Default.resolve(
            CanimationPreset.ScaleIn,
            CanimationLevel.Full,
            AnimationDirection.Enter,
        )
        assertNotNull(spec.scale)
    }

    @Test
    fun offLevelReturnsZeroDuration() {
        val spec = PresetRegistry.Default.resolve(
            CanimationPreset.FadeUp,
            CanimationLevel.Off,
            AnimationDirection.Enter,
        )
        assertEquals(0, spec.durationMs)
    }

    @Test
    fun reducedLevelReturnsReducedSpec() {
        val spec = PresetRegistry.Default.resolve(
            CanimationPreset.FadeUp,
            CanimationLevel.Reduced,
            AnimationDirection.Enter,
        )
        assertEquals(120, spec.durationMs)
    }

    @Test
    fun withPresetAddsNewPreset() {
        val customSpec = CanimationPresetSpec(
            fullEnter = CanimationSpec(durationMs = 500, easing = EasingTokens.Default.standard),
            fullExit = CanimationSpec(durationMs = 500, easing = EasingTokens.Default.standard),
            reducedEnter = CanimationSpec(durationMs = 120, easing = EasingTokens.Default.standard),
            reducedExit = CanimationSpec(durationMs = 120, easing = EasingTokens.Default.standard),
        )
        val registry = PresetRegistry.Default.withPreset(CanimationPreset.FadeUp, customSpec)
        val spec = registry.resolve(CanimationPreset.FadeUp, CanimationLevel.Full, AnimationDirection.Enter)
        assertEquals(500, spec.durationMs)
    }
}

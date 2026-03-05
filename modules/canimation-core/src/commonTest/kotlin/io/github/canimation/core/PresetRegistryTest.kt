package io.github.canimation.core

import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PresetRegistryTest {
    private val testRegistry: PresetRegistry =
        PresetRegistry.create(
            specs = mapOf(
                CanimationPreset.Fade to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 220, easing = EasingTokens.Default.standard),
                    fullExit = CanimationSpec(durationMs = 220, easing = EasingTokens.Default.accelerate),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = EasingTokens.Default.decelerate),
                    reducedExit = CanimationSpec(durationMs = 120, easing = EasingTokens.Default.accelerate),
                ),
                CanimationPreset.FadeUp to CanimationPresetSpec(
                    fullEnter = CanimationSpec(
                        durationMs = 220,
                        easing = EasingTokens.Default.standard,
                        offsetY = CanimationDpRange(16.dp, 0.dp),
                    ),
                    fullExit = CanimationSpec(
                        durationMs = 220,
                        easing = EasingTokens.Default.accelerate,
                        offsetY = CanimationDpRange(0.dp, 16.dp),
                    ),
                    reducedEnter = CanimationSpec(
                        durationMs = 120,
                        easing = EasingTokens.Default.decelerate,
                        offsetY = CanimationDpRange(4.dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
                        easing = EasingTokens.Default.accelerate,
                        offsetY = CanimationDpRange(0.dp, 4.dp),
                    ),
                ),
                CanimationPreset.ScaleIn to CanimationPresetSpec(
                    fullEnter = CanimationSpec(
                        durationMs = 220,
                        easing = EasingTokens.Default.standard,
                        scale = CanimationRange(0.92f, 1f),
                    ),
                    fullExit = CanimationSpec(
                        durationMs = 220,
                        easing = EasingTokens.Default.accelerate,
                        scale = CanimationRange(1f, 0.92f),
                    ),
                    reducedEnter = CanimationSpec(
                        durationMs = 120,
                        easing = EasingTokens.Default.decelerate,
                        scale = CanimationRange(0.98f, 1f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
                        easing = EasingTokens.Default.accelerate,
                        scale = CanimationRange(1f, 0.98f),
                    ),
                ),
            ),
        )

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
        val spec = testRegistry.resolve(
            CanimationPreset.FadeUp,
            CanimationLevel.Full,
            AnimationDirection.Enter,
        )
        assertNotNull(spec.offsetY)
    }

    @Test
    fun fadeEnterHasNoOffset() {
        val spec = testRegistry.resolve(
            CanimationPreset.Fade,
            CanimationLevel.Full,
            AnimationDirection.Enter,
        )
        assertEquals(null, spec.offsetX)
        assertEquals(null, spec.offsetY)
    }

    @Test
    fun scaleInEnterHasScale() {
        val spec = testRegistry.resolve(
            CanimationPreset.ScaleIn,
            CanimationLevel.Full,
            AnimationDirection.Enter,
        )
        assertNotNull(spec.scale)
    }

    @Test
    fun offLevelReturnsZeroDuration() {
        val spec = testRegistry.resolve(
            CanimationPreset.FadeUp,
            CanimationLevel.Off,
            AnimationDirection.Enter,
        )
        assertEquals(0, spec.durationMs)
    }

    @Test
    fun reducedLevelReturnsReducedSpec() {
        val spec = testRegistry.resolve(
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

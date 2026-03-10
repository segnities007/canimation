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

    @Test
    fun unknownPresetFallsBackToFadeSpec() {
        val registry = PresetRegistry.create(
            specs = mapOf(
                CanimationPreset.Fade to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 111, easing = EasingTokens.Default.standard),
                    fullExit = CanimationSpec(durationMs = 112, easing = EasingTokens.Default.accelerate),
                    reducedEnter = CanimationSpec(durationMs = 55, easing = EasingTokens.Default.decelerate),
                    reducedExit = CanimationSpec(durationMs = 56, easing = EasingTokens.Default.accelerate),
                ),
            ),
        )

        val spec = registry.resolve(
            CanimationPreset.BounceIn,
            CanimationLevel.Full,
            AnimationDirection.Enter,
        )

        assertEquals(111, spec.durationMs)
    }

    @Test
    fun createUsesDefaultRegistryAsFallback() {
        val registry = PresetRegistry.create(emptyMap())

        val expected = PresetRegistry.Default.resolve(
            CanimationPreset.ZoomIn,
            CanimationLevel.Full,
            AnimationDirection.Enter,
        )
        val actual = registry.resolve(
            CanimationPreset.ZoomIn,
            CanimationLevel.Full,
            AnimationDirection.Enter,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun createFallsBackToInstalledFadeWhenRequestedPresetIsMissing() {
        withInstalledDefaults(
            specs = mapOf(CanimationPreset.Fade to presetSpec(fullEnterDurationMs = 181)),
        ) {
            val registry = PresetRegistry.create(emptyMap())

            val spec = registry.resolve(
                CanimationPreset.ZoomIn,
                CanimationLevel.Full,
                AnimationDirection.Enter,
            )

            assertEquals(181, spec.durationMs)
        }
    }

    private fun presetSpec(fullEnterDurationMs: Int): CanimationPresetSpec {
        return CanimationPresetSpec(
            fullEnter = CanimationSpec(
                durationMs = fullEnterDurationMs,
                easing = EasingTokens.Default.standard,
            ),
            fullExit = CanimationSpec(
                durationMs = fullEnterDurationMs + 1,
                easing = EasingTokens.Default.accelerate,
            ),
            reducedEnter = CanimationSpec(
                durationMs = fullEnterDurationMs / 2,
                easing = EasingTokens.Default.decelerate,
            ),
            reducedExit = CanimationSpec(
                durationMs = (fullEnterDurationMs / 2) + 1,
                easing = EasingTokens.Default.accelerate,
            ),
        )
    }

    private inline fun withInstalledDefaults(
        specs: Map<CanimationPreset, CanimationPresetSpec>,
        block: () -> Unit,
    ) {
        val previousFadeDefaults = mapOf(
            CanimationPreset.Fade to CanimationPresetSpec(
                fullEnter = PresetRegistry.Default.resolve(
                    CanimationPreset.Fade,
                    CanimationLevel.Full,
                    AnimationDirection.Enter,
                ),
                fullExit = PresetRegistry.Default.resolve(
                    CanimationPreset.Fade,
                    CanimationLevel.Full,
                    AnimationDirection.Exit,
                ),
                reducedEnter = PresetRegistry.Default.resolve(
                    CanimationPreset.Fade,
                    CanimationLevel.Reduced,
                    AnimationDirection.Enter,
                ),
                reducedExit = PresetRegistry.Default.resolve(
                    CanimationPreset.Fade,
                    CanimationLevel.Reduced,
                    AnimationDirection.Exit,
                ),
            ),
        )
        PresetRegistry.installDefaults(specs)
        try {
            block()
        } finally {
            PresetRegistry.installDefaults(previousFadeDefaults)
        }
    }
}

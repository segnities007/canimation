package io.github.canimation.core

import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

class CanimationResolverIntegrationTest {

    private val fadeSpec = CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 220,
            easing = LinearEasing,
            alpha = CanimationRange(from = 0f, to = 1f),
        ),
        fullExit = CanimationSpec(
            durationMs = 210,
            easing = LinearEasing,
            alpha = CanimationRange(from = 1f, to = 0f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = 120,
            easing = LinearEasing,
            alpha = CanimationRange(from = 0f, to = 1f),
        ),
        reducedExit = CanimationSpec(
            durationMs = 110,
            easing = LinearEasing,
            alpha = CanimationRange(from = 1f, to = 0f),
        ),
    )

    private val registry = PresetRegistry.create(
        specs = mapOf(
            CanimationPreset.Fade to fadeSpec,
            CanimationPreset.FadeUp to CanimationPresetSpec(
                fullEnter = CanimationSpec(
                    durationMs = 240,
                    easing = LinearEasing,
                    alpha = CanimationRange(from = 0f, to = 1f),
                    offsetY = CanimationDpRange(from = 24.dp, to = 0.dp),
                ),
                fullExit = CanimationSpec(
                    durationMs = 230,
                    easing = LinearEasing,
                    alpha = CanimationRange(from = 1f, to = 0f),
                    offsetY = CanimationDpRange(from = 0.dp, to = 24.dp),
                ),
                reducedEnter = CanimationSpec(
                    durationMs = 140,
                    easing = LinearEasing,
                    alpha = CanimationRange(from = 0f, to = 1f),
                    offsetY = CanimationDpRange(from = 6.dp, to = 0.dp),
                ),
                reducedExit = CanimationSpec(
                    durationMs = 130,
                    easing = LinearEasing,
                    alpha = CanimationRange(from = 1f, to = 0f),
                    offsetY = CanimationDpRange(from = 0.dp, to = 6.dp),
                ),
            ),
        ),
    )

    @Test
    fun systemAwareReducedMotionUsesReducedEnterSpec() {
        val level = CanimationPolicyResolver.resolve(
            policy = CanimationPolicy.SystemAware,
            systemReducedMotion = true,
        )

        val spec = CanimationSpecResolver.resolve(
            preset = CanimationPreset.FadeUp,
            level = level,
            direction = AnimationDirection.Enter,
            registry = registry,
        )

        assertEquals(140, spec.durationMs)
        assertEquals(6.dp, spec.offsetY?.from)
    }

    @Test
    fun alwaysOffForcesZeroDurationButKeepsDirectionalShape() {
        val level = CanimationPolicyResolver.resolve(
            policy = CanimationPolicy.AlwaysOff,
            systemReducedMotion = false,
        )

        val spec = CanimationSpecResolver.resolve(
            preset = CanimationPreset.FadeUp,
            level = level,
            direction = AnimationDirection.Exit,
            registry = registry,
        )

        assertEquals(0, spec.durationMs)
        assertEquals(0.dp, spec.offsetY?.from)
        assertEquals(24.dp, spec.offsetY?.to)
    }

    @Test
    fun customResolverUsesPolicyDerivedReducedLevel() {
        val level = CanimationPolicyResolver.resolve(
            policy = CanimationPolicy.SystemAware,
            systemReducedMotion = null,
        )
        val fullSpec = CanimationSpec(
            durationMs = 300,
            easing = LinearEasing,
            offsetX = CanimationDpRange(from = (-20).dp, to = 0.dp),
        )
        val reducedSpec = CanimationSpec(
            durationMs = 120,
            easing = LinearEasing,
            offsetX = CanimationDpRange(from = (-4).dp, to = 0.dp),
        )

        val resolved = CanimationSpecResolver.resolveCustom(
            level = level,
            direction = AnimationDirection.Enter,
            fullSpec = fullSpec,
            reducedSpec = reducedSpec,
        )

        assertEquals(120, resolved.durationMs)
        assertEquals((-4).dp, resolved.offsetX?.from)
    }
}

package io.github.canimation.core

import kotlin.test.Test
import kotlin.test.assertEquals

class CanimationSpecResolverTest {
    @Test
    fun resolveFullEnterReturnsFullEnterFromRegistry() {
        val spec = CanimationSpecResolver.resolve(
            preset = CanimationPreset.FadeUp,
            level = CanimationLevel.Full,
            direction = AnimationDirection.Enter,
            registry = PresetRegistry.Default,
        )
        assertEquals(220, spec.durationMs)
    }

    @Test
    fun resolveReducedEnterReturnsReducedSpec() {
        val spec = CanimationSpecResolver.resolve(
            preset = CanimationPreset.FadeUp,
            level = CanimationLevel.Reduced,
            direction = AnimationDirection.Enter,
            registry = PresetRegistry.Default,
        )
        assertEquals(120, spec.durationMs)
    }

    @Test
    fun resolveOffReturnsZeroDuration() {
        val spec = CanimationSpecResolver.resolve(
            preset = CanimationPreset.FadeUp,
            level = CanimationLevel.Off,
            direction = AnimationDirection.Enter,
            registry = PresetRegistry.Default,
        )
        assertEquals(0, spec.durationMs)
    }

    @Test
    fun resolveCustomFullEnterReturnsFullSpec() {
        val fullSpec = CanimationSpec(
            durationMs = 300,
            easing = EasingTokens.Default.standard,
        )
        val result = CanimationSpecResolver.resolveCustom(
            level = CanimationLevel.Full,
            direction = AnimationDirection.Enter,
            fullSpec = fullSpec,
            reducedSpec = fullSpec.toReduced(),
        )
        assertEquals(300, result.durationMs)
    }

    @Test
    fun resolveCustomReducedEnterReturnsReducedSpec() {
        val fullSpec = CanimationSpec(
            durationMs = 300,
            easing = EasingTokens.Default.standard,
        )
        val result = CanimationSpecResolver.resolveCustom(
            level = CanimationLevel.Reduced,
            direction = AnimationDirection.Enter,
            fullSpec = fullSpec,
            reducedSpec = fullSpec.toReduced(),
        )
        assertEquals(120, result.durationMs)
    }
}

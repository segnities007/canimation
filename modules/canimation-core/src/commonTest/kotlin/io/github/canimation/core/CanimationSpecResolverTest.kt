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

    @Test
    fun resolveCustomFullExitReturnsProvidedExitSpecWithoutReversing() {
        val exitSpec = CanimationSpec(
            durationMs = 280,
            easing = EasingTokens.Default.accelerate,
            alpha = CanimationRange(1f, 0f),
        )
        val result = CanimationSpecResolver.resolveCustom(
            level = CanimationLevel.Full,
            direction = AnimationDirection.Exit,
            fullSpec = exitSpec,
            reducedSpec = exitSpec.toReduced(),
        )
        assertEquals(280, result.durationMs)
        assertEquals(1f, result.alpha?.from)
        assertEquals(0f, result.alpha?.to)
    }

    @Test
    fun resolveCustomReducedExitReturnsProvidedReducedExitSpecWithoutReversing() {
        val exitSpec = CanimationSpec(
            durationMs = 260,
            easing = EasingTokens.Default.accelerate,
            alpha = CanimationRange(1f, 0f),
        )
        val reducedExit = CanimationSpec(
            durationMs = 120,
            easing = EasingTokens.Default.accelerate,
            alpha = CanimationRange(1f, 0f),
        )
        val result = CanimationSpecResolver.resolveCustom(
            level = CanimationLevel.Reduced,
            direction = AnimationDirection.Exit,
            fullSpec = exitSpec,
            reducedSpec = reducedExit,
        )
        assertEquals(120, result.durationMs)
        assertEquals(1f, result.alpha?.from)
        assertEquals(0f, result.alpha?.to)
    }
}

package io.github.canimation.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CanimationTransitionResolutionTest {

    private val registry = PresetRegistry.create(emptyMap())

    @Test
    fun presetTransitionUsesEnterSpecWhenVisible() {
        val resolved = resolveTransitionPresetSpec(
            visible = true,
            level = CanimationLevel.Full,
            registry = registry,
            enterPreset = CanimationPreset.FadeUp,
            exitPreset = CanimationPreset.FadeDown,
        )

        val expected = CanimationSpecResolver.resolve(
            preset = CanimationPreset.FadeUp,
            level = CanimationLevel.Full,
            direction = AnimationDirection.Enter,
            registry = registry,
        )

        assertEquals(expected, resolved.spec)
        assertTrue(resolved.applyAsVisible)
    }

    @Test
    fun presetTransitionUsesExitSpecWhenHidden() {
        val resolved = resolveTransitionPresetSpec(
            visible = false,
            level = CanimationLevel.Full,
            registry = registry,
            enterPreset = CanimationPreset.FadeUp,
            exitPreset = CanimationPreset.FadeDown,
        )

        val expected = CanimationSpecResolver.resolve(
            preset = CanimationPreset.FadeDown,
            level = CanimationLevel.Full,
            direction = AnimationDirection.Exit,
            registry = registry,
        )

        assertEquals(expected, resolved.spec)
        assertTrue(resolved.applyAsVisible)
    }

    @Test
    fun customTransitionUsesReducedExitSpecWhenHidden() {
        val enterFull = registry.resolve(
            CanimationPreset.FadeUp,
            CanimationLevel.Full,
            AnimationDirection.Enter,
        )
        val enterReduced = enterFull.toReduced()
        val exitFull = registry.resolve(
            CanimationPreset.FadeDown,
            CanimationLevel.Full,
            AnimationDirection.Exit,
        )
        val exitReduced = exitFull.toReduced()

        val resolved = resolveTransitionCustomSpec(
            visible = false,
            level = CanimationLevel.Reduced,
            enterFullSpec = enterFull,
            enterReducedSpec = enterReduced,
            exitFullSpec = exitFull,
            exitReducedSpec = exitReduced,
        )

        val expected = CanimationSpecResolver.resolveCustom(
            level = CanimationLevel.Reduced,
            direction = AnimationDirection.Exit,
            fullSpec = exitFull,
            reducedSpec = exitReduced,
        )

        assertEquals(expected, resolved.spec)
        assertTrue(resolved.applyAsVisible)
    }

    @Test
    fun emphasizeUsesExitDirectionWhenInactive() {
        val resolved = resolveEmphasizeSpec(
            active = false,
            preset = CanimationPreset.ScaleIn,
            level = CanimationLevel.Full,
            registry = registry,
        )

        val expected = CanimationSpecResolver.resolve(
            preset = CanimationPreset.ScaleIn,
            level = CanimationLevel.Full,
            direction = AnimationDirection.Exit,
            registry = registry,
        )

        assertEquals(expected, resolved.spec)
        assertTrue(resolved.applyAsVisible)
    }
}

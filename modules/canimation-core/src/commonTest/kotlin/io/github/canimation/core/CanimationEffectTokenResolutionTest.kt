package io.github.canimation.core

import androidx.compose.animation.core.CubicBezierEasing
import kotlin.test.Test
import kotlin.test.assertEquals

class CanimationEffectTokenResolutionTest {
    private val standard = CubicBezierEasing(0.1f, 0.0f, 0.0f, 1.0f)
    private val accelerate = CubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f)
    private val tokens = CanimationTokens(
        duration = DurationTokens.Default.copy(medium = DurationTokens.Default.long),
        easing = EasingTokens(
            standard = standard,
            emphasizedDecelerate = standard,
            emphasizedAccelerate = standard,
            decelerate = standard,
            accelerate = accelerate,
        ),
        distance = DistanceTokens.Default,
        spring = SpringTokens.Default,
    )

    @Test
    fun defaultEffectDurationUsesProvidedTokens() {
        val spec = CanimationEffect.fade().toEnterSpec(tokens)

        assertEquals(tokens.duration.mediumMs, spec.durationMs)
        assertEquals(tokens.easing.standard, spec.easing)
    }

    @Test
    fun explicitEffectDurationOverridesProvidedTokens() {
        val spec = CanimationEffect.pop().toEnterSpec(tokens)

        assertEquals(300, spec.durationMs)
    }

    @Test
    fun exitSpecUsesProvidedAccelerateEasing() {
        val spec = CanimationEffect.fade().toExitSpec(tokens)

        assertEquals(tokens.easing.accelerate, spec.easing)
    }
}

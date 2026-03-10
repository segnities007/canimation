package io.github.canimation.core

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CanimationEffectTest {
    private val defaultEasing = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)
    private val customEasing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)

    @Test
    fun plusMergesDistinctProperties() {
        val effect = CanimationEffect.fade() + CanimationEffect.slideUp(24.dp)

        val alpha = assertNotNull(effect.alpha)
        val offsetY = assertNotNull(effect.offsetY)
        assertEquals(0f, alpha.from)
        assertEquals(1f, alpha.to)
        assertEquals(24.dp, offsetY.from)
        assertEquals(0.dp, offsetY.to)
    }

    @Test
    fun plusPrefersRightHandPropertyValues() {
        val effect = CanimationEffect.slideUp(12.dp) + CanimationEffect.slideUp(32.dp)

        val offsetY = assertNotNull(effect.offsetY)
        assertEquals(32.dp, offsetY.from)
        assertEquals(0.dp, offsetY.to)
    }

    @Test
    fun plusUsesLongestDuration() {
        val effect = CanimationEffect.fade() + CanimationEffect.zoom()

        assertEquals(300, effect.durationMs)
    }

    @Test
    fun plusPrefersRightHandEasingWhenPresent() {
        val left = CanimationEffect(durationMs = 120, easing = defaultEasing)
        val right = CanimationEffect(durationMs = 180, easing = customEasing)

        val effect = left + right

        assertEquals(customEasing, effect.easing)
        assertEquals(180, effect.durationMs)
    }

    @Test
    fun toEnterSpecUsesFallbackEasingWhenUnset() {
        val spec = CanimationEffect.fade().toEnterSpec(defaultEasing)

        assertEquals(defaultEasing, spec.easing)
    }

    @Test
    fun toExitSpecReversesRanges() {
        val spec = CanimationEffect.slideUp(20.dp).toExitSpec(defaultEasing)
        val offsetY = assertNotNull(spec.offsetY)

        assertEquals(0.dp, offsetY.from)
        assertEquals(20.dp, offsetY.to)
    }
}

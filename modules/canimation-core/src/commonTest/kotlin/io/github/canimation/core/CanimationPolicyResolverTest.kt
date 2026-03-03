package io.github.canimation.core

import kotlin.test.Test
import kotlin.test.assertEquals

class CanimationPolicyResolverTest {
    @Test
    fun systemAwareWithNullDefaultsToReduced() {
        val level = CanimationPolicyResolver.resolve(CanimationPolicy.SystemAware, null)
        assertEquals(CanimationLevel.Reduced, level)
    }

    @Test
    fun systemAwareWithReducedMotionReturnsReduced() {
        val level = CanimationPolicyResolver.resolve(CanimationPolicy.SystemAware, true)
        assertEquals(CanimationLevel.Reduced, level)
    }

    @Test
    fun systemAwareWithoutReducedMotionReturnsFull() {
        val level = CanimationPolicyResolver.resolve(CanimationPolicy.SystemAware, false)
        assertEquals(CanimationLevel.Full, level)
    }

    @Test
    fun alwaysFullAlwaysReturnsFull() {
        val level = CanimationPolicyResolver.resolve(CanimationPolicy.AlwaysFull, true)
        assertEquals(CanimationLevel.Full, level)
    }

    @Test
    fun alwaysReducedAlwaysReturnsReduced() {
        val level = CanimationPolicyResolver.resolve(CanimationPolicy.AlwaysReduced, false)
        assertEquals(CanimationLevel.Reduced, level)
    }

    @Test
    fun alwaysOffAlwaysReturnsOff() {
        val level = CanimationPolicyResolver.resolve(CanimationPolicy.AlwaysOff, false)
        assertEquals(CanimationLevel.Off, level)
    }
}

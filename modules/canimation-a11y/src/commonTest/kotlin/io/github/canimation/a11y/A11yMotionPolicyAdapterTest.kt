package io.github.canimation.a11y

import io.github.canimation.core.CanimationLevel
import io.github.canimation.core.CanimationPolicy
import kotlin.test.Test
import kotlin.test.assertEquals

class A11yMotionPolicyAdapterTest {
    @Test
    fun noPreferenceWithSystemAwareReturnsFull() {
        val level = A11yMotionPolicyAdapter.resolve(
            preference = SystemMotionPreference.NoPreference,
            policy = CanimationPolicy.SystemAware,
        )
        assertEquals(CanimationLevel.Full, level)
    }

    @Test
    fun reduceMotionWithSystemAwareReturnsReduced() {
        val level = A11yMotionPolicyAdapter.resolve(
            preference = SystemMotionPreference.ReduceMotion,
            policy = CanimationPolicy.SystemAware,
        )
        assertEquals(CanimationLevel.Reduced, level)
    }

    @Test
    fun alwaysFullIgnoresPreference() {
        val level = A11yMotionPolicyAdapter.resolve(
            preference = SystemMotionPreference.ReduceMotion,
            policy = CanimationPolicy.AlwaysFull,
        )
        assertEquals(CanimationLevel.Full, level)
    }

    @Test
    fun alwaysOffIgnoresPreference() {
        val level = A11yMotionPolicyAdapter.resolve(
            preference = SystemMotionPreference.NoPreference,
            policy = CanimationPolicy.AlwaysOff,
        )
        assertEquals(CanimationLevel.Off, level)
    }
}

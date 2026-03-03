package io.github.canimation.test

import kotlin.test.Test
import kotlin.test.assertEquals

class CanimationTestClockTest {
    @Test
    fun initialTimeIsZero() {
        val clock = CanimationTestClock()
        assertEquals(0L, clock.currentTimeMillis)
    }

    @Test
    fun advanceByIncrementsTime() {
        val clock = CanimationTestClock()
        clock.advanceBy(100L)
        assertEquals(100L, clock.currentTimeMillis)
    }

    @Test
    fun advanceByAccumulates() {
        val clock = CanimationTestClock()
        clock.advanceBy(50L)
        clock.advanceBy(75L)
        assertEquals(125L, clock.currentTimeMillis)
    }

    @Test
    fun advanceToSetsExactTime() {
        val clock = CanimationTestClock()
        clock.advanceTo(500L)
        assertEquals(500L, clock.currentTimeMillis)
    }

    @Test
    fun resetSetsTimeToZero() {
        val clock = CanimationTestClock()
        clock.advanceBy(200L)
        clock.reset()
        assertEquals(0L, clock.currentTimeMillis)
    }
}

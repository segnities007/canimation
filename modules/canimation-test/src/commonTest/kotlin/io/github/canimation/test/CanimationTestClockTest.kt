package io.github.canimation.test

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CanimationTestClockTest {
    @Test
    fun initialTimeCanBeProvided() {
        val clock = CanimationTestClock(initialTimeMillis = 12L)

        assertEquals(12L, clock.currentTimeMillis)
    }

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

    @Test
    fun constructorRejectsNegativeInitialTime() {
        assertFailsWith<IllegalArgumentException> {
            CanimationTestClock(initialTimeMillis = -1L)
        }
    }

    @Test
    fun advanceByRejectsNegativeDuration() {
        val clock = CanimationTestClock()

        assertFailsWith<IllegalArgumentException> {
            clock.advanceBy(-1L)
        }
    }

    @Test
    fun advanceToRejectsGoingBackInTime() {
        val clock = CanimationTestClock(initialTimeMillis = 10L)

        assertFailsWith<IllegalArgumentException> {
            clock.advanceTo(9L)
        }
    }
}

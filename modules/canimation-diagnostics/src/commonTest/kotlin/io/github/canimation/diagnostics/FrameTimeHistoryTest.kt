package io.github.canimation.diagnostics

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FrameTimeHistoryTest {

    @Test
    fun addDropsOldestSampleWhenLimitIsReached() {
        val history = FrameTimeHistory(maxSamples = 3)

        history.add(11f)
        history.add(12f)
        history.add(13f)
        history.add(14f)

        assertEquals(listOf(12f, 13f, 14f), history.asList())
    }

    @Test
    fun maxSamplesMustBePositive() {
        assertFailsWith<IllegalArgumentException> {
            FrameTimeHistory(maxSamples = 0)
        }
    }
}

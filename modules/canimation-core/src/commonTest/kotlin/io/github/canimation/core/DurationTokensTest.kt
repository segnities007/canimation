package io.github.canimation.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

class DurationTokensTest {
    @Test
    fun defaultShortIs120ms() {
        assertEquals(120.milliseconds, DurationTokens.Default.short)
    }

    @Test
    fun defaultMediumIs220ms() {
        assertEquals(220.milliseconds, DurationTokens.Default.medium)
    }

    @Test
    fun defaultLongIs360ms() {
        assertEquals(360.milliseconds, DurationTokens.Default.long)
    }
}

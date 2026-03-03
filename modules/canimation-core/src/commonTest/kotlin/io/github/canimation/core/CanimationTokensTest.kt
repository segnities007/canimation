package io.github.canimation.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.time.Duration.Companion.milliseconds

class CanimationTokensTest {
    @Test
    fun defaultTokensHaveCorrectDuration() {
        val tokens = CanimationTokens.Default
        assertEquals(120.milliseconds, tokens.duration.short)
        assertEquals(220.milliseconds, tokens.duration.medium)
        assertEquals(360.milliseconds, tokens.duration.long)
    }

    @Test
    fun defaultTokensHaveCorrectDistance() {
        val tokens = CanimationTokens.Default
        assertNotNull(tokens.distance)
    }

    @Test
    fun defaultTokensHaveCorrectSpring() {
        val tokens = CanimationTokens.Default
        assertNotNull(tokens.spring)
    }
}

package io.github.canimation.tokens

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

class CanimationTokensExportsTest {
    @Test
    fun defaultTokensExposeExpectedDurations() {
        assertEquals(120.milliseconds, CanimationTokens.Default.duration.short)
        assertEquals(220.milliseconds, CanimationTokens.Default.duration.medium)
        assertEquals(360.milliseconds, CanimationTokens.Default.duration.long)
    }

    @Test
    fun springSpecIsPartOfTokenSurface() {
        val spec = SpringSpec(dampingRatio = 0.85f, stiffness = 280f)

        assertEquals(spec, SpringTokens.Default.gentle)
    }
}

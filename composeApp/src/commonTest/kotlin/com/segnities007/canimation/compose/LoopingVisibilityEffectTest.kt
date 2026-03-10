package com.segnities007.canimation.compose

import io.github.canimation.core.CanimationLevel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LoopingVisibilityEffectTest {
    @Test
    fun `full motion keeps looping helpers enabled`() {
        assertTrue(CanimationLevel.Full.allowsLoopingMotion())
    }

    @Test
    fun `reduced and off disable looping helpers`() {
        assertFalse(CanimationLevel.Reduced.allowsLoopingMotion())
        assertFalse(CanimationLevel.Off.allowsLoopingMotion())
    }

    @Test
    fun `full motion keeps requested delays`() {
        assertEquals(240L, CanimationLevel.Full.accessibleDelayMillis(240L))
    }

    @Test
    fun `reduced and off collapse helper delays`() {
        assertEquals(0L, CanimationLevel.Reduced.accessibleDelayMillis(240L))
        assertEquals(0L, CanimationLevel.Off.accessibleDelayMillis(240L))
    }
}

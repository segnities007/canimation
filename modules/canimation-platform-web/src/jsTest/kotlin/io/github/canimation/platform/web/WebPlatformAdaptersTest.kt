package io.github.canimation.platform.web

import io.github.canimation.a11y.SystemMotionPreference
import io.github.canimation.diagnostics.FrameMetrics
import kotlin.test.Test
import kotlin.test.assertEquals

class WebPlatformAdaptersTest {
    @Test
    fun fallbackAlwaysReturnsReduceMotion() {
        assertEquals(
            expected = SystemMotionPreference.ReduceMotion,
            actual = WebMotionPreferenceFallback.currentPreference(),
        )
    }

    @Test
    fun frameCollectorReturnsDefaultMetrics() {
        assertEquals(
            expected = FrameMetrics(),
            actual = WebFrameMetricsCollector.currentMetrics(),
        )
    }
}

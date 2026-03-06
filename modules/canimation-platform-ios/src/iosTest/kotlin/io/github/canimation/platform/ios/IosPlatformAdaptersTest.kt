package io.github.canimation.platform.ios

import io.github.canimation.a11y.SystemMotionPreference
import io.github.canimation.diagnostics.FrameMetrics
import kotlin.test.Test
import kotlin.test.assertEquals

class IosPlatformAdaptersTest {
    @Test
    fun fallbackAlwaysReturnsReduceMotion() {
        assertEquals(
            expected = SystemMotionPreference.ReduceMotion,
            actual = IosMotionPreferenceFallback.currentPreference(),
        )
    }

    @Test
    fun frameCollectorReturnsDefaultMetrics() {
        assertEquals(
            expected = FrameMetrics(),
            actual = IosFrameMetricsCollector.currentMetrics(),
        )
    }
}

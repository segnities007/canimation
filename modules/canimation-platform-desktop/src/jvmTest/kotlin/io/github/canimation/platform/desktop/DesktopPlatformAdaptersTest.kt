package io.github.canimation.platform.desktop

import io.github.canimation.a11y.SystemMotionPreference
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DesktopPlatformAdaptersTest {
    @Test
    fun fallbackAlwaysReturnsReduceMotion() {
        assertEquals(
            expected = SystemMotionPreference.ReduceMotion,
            actual = DesktopMotionPreferenceFallback.currentPreference(),
        )
    }

    @Test
    fun frameCollectorUpdatesMetricsAfterFrames() {
        val collector = DesktopFrameMetricsCollector(jankThresholdMs = 16)
        collector.start()
        collector.onFrame()
        collector.onFrame()

        val metrics = collector.currentMetrics()
        assertTrue(metrics.frameTimeMs > 0f)
    }

    @Test
    fun frameCollectorIgnoresFramesAfterStop() {
        val collector = DesktopFrameMetricsCollector(jankThresholdMs = 16)
        collector.start()
        collector.onFrame()
        collector.stop()
        val stoppedMetrics = collector.currentMetrics()
        collector.onFrame()

        assertEquals(stoppedMetrics, collector.currentMetrics())
    }
}

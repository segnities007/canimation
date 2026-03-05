package io.github.canimation.platform.android

import io.github.canimation.a11y.SystemMotionPreference
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AndroidPlatformAdaptersTest {
    @Test
    fun fallbackAlwaysReturnsReduceMotion() {
        assertEquals(
            expected = SystemMotionPreference.ReduceMotion,
            actual = AndroidMotionPreferenceFallback.currentPreference(),
        )
    }

    @Test
    fun frameCollectorUpdatesMetricsAfterFrames() {
        val collector = AndroidFrameMetricsCollector(jankThresholdMs = 16)
        collector.start()
        val base = System.nanoTime()
        collector.onFrame(base + 16_000_000L)
        collector.onFrame(base + 32_000_000L)

        val metrics = collector.currentMetrics()
        assertTrue(metrics.frameTimeMs > 0f)
    }

    @Test
    fun frameCollectorIgnoresFramesAfterStop() {
        val collector = AndroidFrameMetricsCollector(jankThresholdMs = 16)
        collector.start()
        val base = System.nanoTime()
        collector.onFrame(base + 16_000_000L)
        collector.stop()
        val stoppedMetrics = collector.currentMetrics()
        collector.onFrame(base + 32_000_000L)

        assertEquals(stoppedMetrics, collector.currentMetrics())
    }
}

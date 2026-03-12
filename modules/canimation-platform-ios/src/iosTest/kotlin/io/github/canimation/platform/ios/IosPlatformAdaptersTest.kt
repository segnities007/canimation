package io.github.canimation.platform.ios

import io.github.canimation.a11y.SystemMotionPreference
import io.github.canimation.diagnostics.DiagnosticsCollectorAvailabilityProvider
import io.github.canimation.diagnostics.FrameMetrics
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

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

    @Test
    fun frameCollectorReportsUnavailableDiagnostics() {
        val availability = (IosFrameMetricsCollector as DiagnosticsCollectorAvailabilityProvider).availability

        assertFalse(availability.isAvailable)
        assertEquals(
            "Diagnostics unavailable on iOS: live frame metrics are not implemented yet.",
            availability.reason,
        )
    }
}

package io.github.canimation.platform.web

import io.github.canimation.a11y.SystemMotionPreference
import io.github.canimation.diagnostics.DiagnosticsCollectorAvailabilityProvider
import io.github.canimation.diagnostics.FrameMetrics
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

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

    @Test
    fun frameCollectorReportsUnavailableDiagnostics() {
        val availability = (WebFrameMetricsCollector as DiagnosticsCollectorAvailabilityProvider).availability

        assertFalse(availability.isAvailable)
        assertEquals(
            "Diagnostics unavailable on Web: live frame metrics are not implemented yet.",
            availability.reason,
        )
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun observePreferenceEmitsCurrentAndUpdatedValueAndCleansUpListener() = runTest {
        val mediaQuery = FakeMotionMediaQueryHandle(matches = false)
        val dataSource = WebMotionPreferenceDataSource { mediaQuery }
        val emissions = mutableListOf<SystemMotionPreference>()

        val job = launch {
            dataSource.observePreference()
                .take(2)
                .collect { emissions += it }
        }

        runCurrent()
        mediaQuery.matches = true
        mediaQuery.emitChange()
        job.join()

        assertEquals(
            listOf(
                SystemMotionPreference.NoPreference,
                SystemMotionPreference.ReduceMotion,
            ),
            emissions,
        )
        assertEquals(0, mediaQuery.listenerCount)
        assertEquals(1, mediaQuery.removedListeners)
    }

    private class FakeMotionMediaQueryHandle(
        override var matches: Boolean,
    ) : MotionMediaQueryHandle {
        private val listeners = mutableSetOf<ChangeListener>()

        var removedListeners: Int = 0
            private set

        val listenerCount: Int
            get() = listeners.size

        override fun addChangeListener(listener: ChangeListener) {
            listeners += listener
        }

        override fun removeChangeListener(listener: ChangeListener) {
            if (listeners.remove(listener)) {
                removedListeners += 1
            }
        }

        fun emitChange() {
            listeners.toList().forEach(ChangeListener::invoke)
        }
    }
}

package io.github.canimation.platform.ios

import io.github.canimation.diagnostics.DiagnosticsCollector
import io.github.canimation.diagnostics.FrameMetrics
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * No-op diagnostics collector for iOS.
 *
 * Frame metrics collection is not yet available on iOS.
 * Returns empty metrics and does nothing on start/stop.
 */
object IosFrameMetricsCollector : DiagnosticsCollector {
    override fun start() { /* no-op */ }
    override fun stop() { /* no-op */ }
    override fun currentMetrics(): FrameMetrics = FrameMetrics()
    override fun observeMetrics(): Flow<FrameMetrics> = flowOf(FrameMetrics())
}

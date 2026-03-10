package io.github.canimation.platform.web

import io.github.canimation.diagnostics.DiagnosticsCollector
import io.github.canimation.diagnostics.DiagnosticsCollectorAvailability
import io.github.canimation.diagnostics.DiagnosticsCollectorAvailabilityProvider
import io.github.canimation.diagnostics.FrameMetrics
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * No-op diagnostics collector for Web.
 *
 * Frame metrics collection is not yet available on Web.
 * Returns empty metrics and does nothing on start/stop.
 */
object WebFrameMetricsCollector : DiagnosticsCollector, DiagnosticsCollectorAvailabilityProvider {
    override val availability: DiagnosticsCollectorAvailability = DiagnosticsCollectorAvailability(
        isAvailable = false,
        reason = "Diagnostics unavailable on Web: live frame metrics are not implemented yet.",
    )

    override fun start() { /* no-op */ }
    override fun stop() { /* no-op */ }
    override fun currentMetrics(): FrameMetrics = FrameMetrics()
    override fun observeMetrics(): Flow<FrameMetrics> = flowOf(FrameMetrics())
}

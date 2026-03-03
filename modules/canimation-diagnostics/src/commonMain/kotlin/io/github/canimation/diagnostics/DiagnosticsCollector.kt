package io.github.canimation.diagnostics

import kotlinx.coroutines.flow.Flow

/**
 * Contract for collecting frame rendering metrics on each platform.
 *
 * Platform modules provide actual implementations (Android, Desktop)
 * or no-op stubs (iOS, Web) for this interface.
 */
interface DiagnosticsCollector {
    /** Start collecting frame metrics. */
    fun start()

    /** Stop collecting frame metrics. */
    fun stop()

    /** Returns the latest snapshot of frame metrics. */
    fun currentMetrics(): FrameMetrics

    /** Observe metrics updates as a Flow. */
    fun observeMetrics(): Flow<FrameMetrics>
}

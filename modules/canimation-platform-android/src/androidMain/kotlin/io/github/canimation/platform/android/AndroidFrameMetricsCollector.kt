package io.github.canimation.platform.android

import io.github.canimation.diagnostics.DiagnosticsCollector
import io.github.canimation.diagnostics.FrameMetrics
import io.github.canimation.diagnostics.JankCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Android implementation of [DiagnosticsCollector] using frame callback timing.
 */
class AndroidFrameMetricsCollector(
    private val jankThresholdMs: Int = 16,
) : DiagnosticsCollector {

    private val metricsFlow = MutableStateFlow(FrameMetrics())
    private val frameTimes = mutableListOf<Float>()
    private var isCollecting = false
    private var lastFrameNanos: Long = 0L

    override fun start() {
        isCollecting = true
        frameTimes.clear()
        lastFrameNanos = System.nanoTime()
    }

    override fun stop() {
        isCollecting = false
    }

    override fun currentMetrics(): FrameMetrics = metricsFlow.value

    override fun observeMetrics(): Flow<FrameMetrics> = metricsFlow

    /**
     * Call from a frame callback to record a frame timestamp.
     */
    fun onFrame(frameNanos: Long) {
        if (!isCollecting) return

        if (lastFrameNanos > 0) {
            val frameTimeMs = (frameNanos - lastFrameNanos) / 1_000_000f
            frameTimes.add(frameTimeMs)
            if (frameTimes.size > 120) frameTimes.removeAt(0)

            metricsFlow.value = FrameMetrics(
                fps = JankCalculator.calculateFps(frameTimes),
                frameTimeMs = frameTimeMs,
                jankCount = JankCalculator.countJanks(frameTimes, jankThresholdMs),
                p95FrameTimeMs = JankCalculator.calculateP95(frameTimes),
            )
        }
        lastFrameNanos = frameNanos
    }
}

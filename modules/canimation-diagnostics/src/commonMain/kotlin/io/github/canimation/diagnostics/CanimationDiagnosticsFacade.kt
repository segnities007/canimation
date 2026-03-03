package io.github.canimation.diagnostics

/**
 * Facade providing a simplified API for diagnostics operations.
 */
object CanimationDiagnosticsFacade {

    /**
     * Creates a [FrameMetrics] snapshot from raw frame time data.
     *
     * @param frameTimesMs Collected frame durations.
     * @param jankThresholdMs Threshold for jank detection.
     * @return Computed [FrameMetrics].
     */
    fun computeMetrics(frameTimesMs: List<Float>, jankThresholdMs: Int = 16): FrameMetrics {
        return FrameMetrics(
            fps = JankCalculator.calculateFps(frameTimesMs),
            frameTimeMs = frameTimesMs.lastOrNull() ?: 0f,
            jankCount = JankCalculator.countJanks(frameTimesMs, jankThresholdMs),
            p95FrameTimeMs = JankCalculator.calculateP95(frameTimesMs),
        )
    }
}

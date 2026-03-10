package io.github.canimation.diagnostics

import kotlin.math.ceil

/**
 * Calculates jank-related metrics from a list of frame times.
 */
object JankCalculator {

    /**
     * Counts the number of frames that exceed the jank threshold.
     *
     * @param frameTimesMs List of frame durations in milliseconds.
     * @param thresholdMs The jank threshold (default: 16ms for 60fps).
     * @return Number of janky frames.
     */
    fun countJanks(frameTimesMs: List<Float>, thresholdMs: Int = 16): Int {
        return frameTimesMs.count { it > thresholdMs }
    }

    /**
     * Calculates the p95 (95th percentile) frame time.
     *
     * @param frameTimesMs List of frame durations in milliseconds.
     * @return The p95 frame time using nearest-rank percentile semantics, or 0f if the list is empty.
     */
    fun calculateP95(frameTimesMs: List<Float>): Float {
        if (frameTimesMs.isEmpty()) return 0f
        val sorted = frameTimesMs.sorted()
        val index = ceil(sorted.size * 0.95).toInt()
            .coerceIn(1, sorted.size) - 1
        return sorted[index]
    }

    /**
     * Calculates the average FPS from frame times.
     *
     * @param frameTimesMs List of frame durations in milliseconds.
     * @return Average FPS, or 0f if the list is empty.
     */
    fun calculateFps(frameTimesMs: List<Float>): Float {
        if (frameTimesMs.isEmpty()) return 0f
        val avgFrameTime = frameTimesMs.average().toFloat()
        return if (avgFrameTime > 0f) 1000f / avgFrameTime else 0f
    }
}

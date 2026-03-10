package io.github.canimation.diagnostics

internal const val DefaultFrameHistoryLimit: Int = 120

internal class FrameTimeHistory(
    private val maxSamples: Int = DefaultFrameHistoryLimit,
) {
    private val frameTimes = ArrayDeque<Float>(maxSamples)

    init {
        require(maxSamples > 0) { "maxSamples must be > 0, was $maxSamples" }
    }

    fun add(frameTimeMs: Float) {
        if (frameTimes.size == maxSamples) {
            frameTimes.removeFirst()
        }
        frameTimes.addLast(frameTimeMs)
    }

    fun asList(): List<Float> = frameTimes
}

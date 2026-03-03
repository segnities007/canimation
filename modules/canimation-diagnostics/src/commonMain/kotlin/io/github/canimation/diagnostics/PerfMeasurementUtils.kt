package io.github.canimation.diagnostics

/**
 * Utility functions for performance measurement and threshold evaluation.
 */
object PerfMeasurementUtils {

    /**
     * Evaluates whether the measured p95 frame time is within the acceptable degradation.
     *
     * @param baselineP95Ms The baseline p95 frame time without the library.
     * @param measuredP95Ms The measured p95 frame time with the library.
     * @param maxDegradationRatio Maximum allowed degradation ratio (default: 1.10 = 10%).
     * @return `true` if the measured value is within limits.
     */
    fun isWithinDegradationLimit(
        baselineP95Ms: Float,
        measuredP95Ms: Float,
        maxDegradationRatio: Float = 1.10f,
    ): Boolean {
        return measuredP95Ms <= baselineP95Ms * maxDegradationRatio
    }

    /**
     * Calculates the degradation ratio between baseline and measured p95 frame times.
     *
     * @return Ratio >= 1.0 means degradation. Returns Float.MAX_VALUE if baseline is 0.
     */
    fun degradationRatio(baselineP95Ms: Float, measuredP95Ms: Float): Float {
        return if (baselineP95Ms > 0f) measuredP95Ms / baselineP95Ms else Float.MAX_VALUE
    }
}

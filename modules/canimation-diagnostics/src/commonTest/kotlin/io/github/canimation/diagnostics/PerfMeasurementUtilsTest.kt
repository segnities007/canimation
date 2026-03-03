package io.github.canimation.diagnostics

import kotlin.test.Test
import kotlin.test.assertTrue

class PerfMeasurementUtilsTest {
    @Test
    fun withinDegradationLimitWhenBelowThreshold() {
        assertTrue(PerfMeasurementUtils.isWithinDegradationLimit(
            baselineP95Ms = 10f,
            measuredP95Ms = 11f,
            maxDegradationRatio = 1.20f,
        ))
    }

    @Test
    fun notWithinDegradationLimitWhenAboveThreshold() {
        assertTrue(!PerfMeasurementUtils.isWithinDegradationLimit(
            baselineP95Ms = 10f,
            measuredP95Ms = 15f,
            maxDegradationRatio = 1.20f,
        ))
    }

    @Test
    fun degradationRatioCalculatedCorrectly() {
        val ratio = PerfMeasurementUtils.degradationRatio(
            baselineP95Ms = 10f,
            measuredP95Ms = 12f,
        )
        assertTrue(ratio > 1.19f && ratio < 1.21f, "Expected ~1.2, got $ratio")
    }
}

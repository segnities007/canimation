package io.github.canimation.diagnostics

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class JankCalculatorTest {
    @Test
    fun noJanksWhenAllBelowThreshold() {
        val frameTimes = listOf(14f, 15f, 16f, 15.5f)
        assertEquals(0, JankCalculator.countJanks(frameTimes, 16))
    }

    @Test
    fun countsJanksAboveThreshold() {
        val frameTimes = listOf(14f, 20f, 16f, 25f, 15f)
        assertEquals(2, JankCalculator.countJanks(frameTimes, 16))
    }

    @Test
    fun emptyFrameTimesReturnsZeroJanks() {
        assertEquals(0, JankCalculator.countJanks(emptyList(), 16))
    }

    @Test
    fun calculateFpsFromAverageFrameTime() {
        val frameTimes = listOf(16.67f, 16.67f, 16.67f)
        val fps = JankCalculator.calculateFps(frameTimes)
        assertTrue(fps > 55f && fps < 65f, "Expected ~60fps, got $fps")
    }

    @Test
    fun emptyFrameTimesReturnZeroFps() {
        assertEquals(0f, JankCalculator.calculateFps(emptyList()))
    }

    @Test
    fun calculateP95ReturnsHighPercentile() {
        val frameTimes = (1..100).map { it.toFloat() }
        val p95 = JankCalculator.calculateP95(frameTimes)
        assertTrue(p95 >= 95f, "Expected p95 >= 95, got $p95")
    }

    @Test
    fun singleFrameTimeReturnsItself() {
        assertEquals(42f, JankCalculator.calculateP95(listOf(42f)))
    }
}

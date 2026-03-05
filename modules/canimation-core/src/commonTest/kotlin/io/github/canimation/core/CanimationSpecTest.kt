package io.github.canimation.core

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class CanimationSpecTest {
    private val standardEasing = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)

    @Test
    fun validSpecDoesNotThrow() {
        val spec = CanimationSpec(
            durationMs = 220,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
        )
        assertEquals(220, spec.durationMs)
    }

    @Test
    fun negativeDurationThrows() {
        assertFailsWith<IllegalArgumentException> {
            CanimationSpec(durationMs = -1, easing = standardEasing)
        }
    }

    @Test
    fun alphaOutOfRangeThrows() {
        assertFailsWith<IllegalArgumentException> {
            CanimationSpec(
                durationMs = 100,
                easing = standardEasing,
                alpha = CanimationRange(-0.1f, 1f),
            )
        }
    }

    @Test
    fun negativeScaleThrows() {
        assertFailsWith<IllegalArgumentException> {
            CanimationSpec(
                durationMs = 100,
                easing = standardEasing,
                scale = CanimationRange(-0.1f, 1f),
            )
        }
    }

    @Test
    fun toReducedSetsShortDuration() {
        val spec = CanimationSpec(
            durationMs = 220,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange(16.dp, 0.dp),
        )
        val reduced = spec.toReduced()
        assertEquals(120, reduced.durationMs)
    }

    @Test
    fun toReducedScalesOffset() {
        val spec = CanimationSpec(
            durationMs = 220,
            easing = standardEasing,
            offsetY = CanimationDpRange(16.dp, 0.dp),
        )
        val reduced = spec.toReduced()
        val offsetY = assertNotNull(reduced.offsetY)
        assertEquals(4.dp, offsetY.from)
        assertEquals(0.dp, offsetY.to)
    }

    @Test
    fun toReducedPreservesAlpha() {
        val spec = CanimationSpec(
            durationMs = 220,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
        )
        val reduced = spec.toReduced()
        val alpha = assertNotNull(reduced.alpha)
        assertEquals(0f, alpha.from)
        assertEquals(1f, alpha.to)
    }

    @Test
    fun reversedSwapsFromTo() {
        val spec = CanimationSpec(
            durationMs = 220,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange(16.dp, 0.dp),
        )
        val reversed = spec.reversed()
        val alpha = assertNotNull(reversed.alpha)
        val offsetY = assertNotNull(reversed.offsetY)
        assertEquals(1f, alpha.from)
        assertEquals(0f, alpha.to)
        assertEquals(0.dp, offsetY.from)
        assertEquals(16.dp, offsetY.to)
    }

    @Test
    fun reversedPreservesDuration() {
        val spec = CanimationSpec(
            durationMs = 350,
            easing = standardEasing,
        )
        assertEquals(350, spec.reversed().durationMs)
    }

    @Test
    fun nullOptionalFieldsAllowed() {
        val spec = CanimationSpec(
            durationMs = 100,
            easing = standardEasing,
        )
        assertNull(spec.alpha)
        assertNull(spec.offsetX)
        assertNull(spec.offsetY)
        assertNull(spec.scale)
    }
}

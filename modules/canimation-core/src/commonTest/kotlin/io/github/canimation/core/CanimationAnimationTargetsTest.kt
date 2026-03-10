package io.github.canimation.core

import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

class CanimationAnimationTargetsTest {

    @Test
    fun visibleTargetsUseToValues() {
        val spec = CanimationSpec(
            durationMs = 240,
            easing = LinearEasing,
            alpha = CanimationRange(from = 0f, to = 0.8f),
            offsetX = CanimationDpRange(from = (-16).dp, to = 12.dp),
            offsetY = CanimationDpRange(from = 24.dp, to = 4.dp),
            scale = CanimationRange(from = 0.9f, to = 1.1f),
            rotation = CanimationRange(from = -8f, to = 6f),
        )

        val targets = resolveAnimationTargets(
            visible = true,
            spec = spec,
        )

        assertEquals(
            expected = ResolvedAnimationTargets(
                alpha = 0.8f,
                offsetX = 12f,
                offsetY = 4f,
                scale = 1.1f,
                rotation = 6f,
            ),
            actual = targets,
        )
    }

    @Test
    fun hiddenTargetsUseFromValues() {
        val spec = CanimationSpec(
            durationMs = 180,
            easing = LinearEasing,
            alpha = CanimationRange(from = 0.2f, to = 1f),
            offsetX = CanimationDpRange(from = (-20).dp, to = 0.dp),
            offsetY = CanimationDpRange(from = 16.dp, to = 0.dp),
            scale = CanimationRange(from = 0.85f, to = 1f),
            rotation = CanimationRange(from = -12f, to = 0f),
        )

        val targets = resolveAnimationTargets(
            visible = false,
            spec = spec,
        )

        assertEquals(
            expected = ResolvedAnimationTargets(
                alpha = 0.2f,
                offsetX = -20f,
                offsetY = 16f,
                scale = 0.85f,
                rotation = -12f,
            ),
            actual = targets,
        )
    }

    @Test
    fun missingRangesFallBackToStableRestValues() {
        val spec = CanimationSpec(
            durationMs = 120,
            easing = LinearEasing,
        )

        val visibleTargets = resolveAnimationTargets(
            visible = true,
            spec = spec,
        )
        val hiddenTargets = resolveAnimationTargets(
            visible = false,
            spec = spec,
        )

        assertEquals(
            expected = ResolvedAnimationTargets(
                alpha = 1f,
                offsetX = 0f,
                offsetY = 0f,
                scale = 1f,
                rotation = 0f,
            ),
            actual = visibleTargets,
        )
        assertEquals(
            expected = ResolvedAnimationTargets(
                alpha = 0f,
                offsetX = 0f,
                offsetY = 0f,
                scale = 1f,
                rotation = 0f,
            ),
            actual = hiddenTargets,
        )
    }
}

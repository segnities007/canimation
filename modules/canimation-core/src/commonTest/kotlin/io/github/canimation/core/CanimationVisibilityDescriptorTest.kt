package io.github.canimation.core

import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

class CanimationVisibilityDescriptorTest {

    @Test
    fun enterDescriptorUsesInitialValues() {
        val spec = CanimationSpec(
            durationMs = 240,
            easing = LinearEasing,
            alpha = CanimationRange(from = 0.2f, to = 1f),
            offsetX = CanimationDpRange(from = (-8).dp, to = 0.dp),
            offsetY = CanimationDpRange(from = 12.dp, to = 0.dp),
            scale = CanimationRange(from = 0.9f, to = 1f),
        )

        val descriptor = resolveEnterTransitionDescriptor(
            spec = spec,
            density = { it.value.toInt() },
        )

        assertEquals(
            VisibilityTransitionDescriptor(
                durationMs = 240,
                initialAlpha = 0.2f,
                initialOffsetXPx = -8,
                initialOffsetYPx = 12,
                initialScale = 0.9f,
            ),
            descriptor,
        )
    }

    @Test
    fun exitDescriptorUsesTargetValues() {
        val spec = CanimationSpec(
            durationMs = 180,
            easing = LinearEasing,
            alpha = CanimationRange(from = 1f, to = 0.1f),
            offsetX = CanimationDpRange(from = 0.dp, to = 14.dp),
            offsetY = CanimationDpRange(from = 0.dp, to = (-6).dp),
            scale = CanimationRange(from = 1f, to = 0.85f),
        )

        val descriptor = resolveExitTransitionDescriptor(
            spec = spec,
            density = { it.value.toInt() },
        )

        assertEquals(
            VisibilityTransitionDescriptor(
                durationMs = 180,
                targetAlpha = 0.1f,
                targetOffsetXPx = 14,
                targetOffsetYPx = -6,
                targetScale = 0.85f,
            ),
            descriptor,
        )
    }

    @Test
    fun descriptorsApplyStableFallbacksWhenRangesAreMissing() {
        val spec = CanimationSpec(
            durationMs = 120,
            easing = LinearEasing,
        )

        val enter = resolveEnterTransitionDescriptor(
            spec = spec,
            density = { it.value.toInt() },
        )
        val exit = resolveExitTransitionDescriptor(
            spec = spec,
            density = { it.value.toInt() },
        )

        assertEquals(
            VisibilityTransitionDescriptor(
                durationMs = 120,
                initialAlpha = 0f,
            ),
            enter,
        )
        assertEquals(
            VisibilityTransitionDescriptor(
                durationMs = 120,
                targetAlpha = 0f,
            ),
            exit,
        )
    }
}

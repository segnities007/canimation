package io.github.canimation.core

import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A composable animation effect that can be combined using the `+` operator.
 *
 * Build complex animations by chaining simple effects:
 * ```kotlin
 * val effect = CanimationEffect.fade() + CanimationEffect.slideUp(16.dp) + CanimationEffect.scale(0.9f)
 * Modifier.canimation(visible = true, effect = effect)
 * ```
 *
 * Or use the hierarchical namespace:
 * ```kotlin
 * Modifier.canimation(visible = true, effect = Canimation.Fade.Up)
 * Modifier.canimation(visible = true, effect = Canimation.Scale.Pop)
 * ```
 */
@Immutable
data class CanimationEffect(
    val alpha: CanimationRange? = null,
    val offsetX: CanimationDpRange? = null,
    val offsetY: CanimationDpRange? = null,
    val scale: CanimationRange? = null,
    val rotation: CanimationRange? = null,
    val durationMs: Int = UnspecifiedDurationMs,
    val easing: Easing? = null,
) {
    init {
        require(durationMs == UnspecifiedDurationMs || durationMs >= 0) {
            "durationMs must be >= 0 or UnspecifiedDurationMs, was $durationMs"
        }
    }

    /**
     * Combine two effects. Properties from [other] override this effect's null values.
     * Duration takes the longer of the two.
     */
    operator fun plus(other: CanimationEffect): CanimationEffect =
        CanimationEffect(
            alpha = other.alpha ?: alpha,
            offsetX = other.offsetX ?: offsetX,
            offsetY = other.offsetY ?: offsetY,
            scale = other.scale ?: scale,
            rotation = other.rotation ?: rotation,
            durationMs = maxOf(durationMs, other.durationMs),
            easing = other.easing ?: easing,
        )

    /** Convert to a [CanimationSpec] for the enter direction. */
    fun toEnterSpec(
        defaultEasing: Easing,
        defaultDurationMs: Int = DurationTokens.Default.mediumMs,
    ): CanimationSpec =
        CanimationSpec(
            durationMs = resolvedDurationMs(defaultDurationMs),
            easing = easing ?: defaultEasing,
            alpha = alpha,
            offsetX = offsetX,
            offsetY = offsetY,
            scale = scale,
            rotation = rotation,
        )

    /** Convert to a [CanimationSpec] for the enter direction using [tokens]. */
    fun toEnterSpec(tokens: CanimationTokens): CanimationSpec =
        toEnterSpec(
            defaultEasing = tokens.easing.standard,
            defaultDurationMs = tokens.duration.mediumMs,
        )

    /** Convert to a [CanimationSpec] for the exit direction (reversed). */
    fun toExitSpec(
        defaultEasing: Easing,
        defaultDurationMs: Int = DurationTokens.Default.mediumMs,
    ): CanimationSpec =
        toEnterSpec(
            defaultEasing = defaultEasing,
            defaultDurationMs = defaultDurationMs,
        ).reversed()

    /** Convert to a [CanimationSpec] for the exit direction (reversed) using [tokens]. */
    fun toExitSpec(tokens: CanimationTokens): CanimationSpec = toEnterSpec(tokens).reversed(tokens)

    companion object {
        internal const val UnspecifiedDurationMs = -1

        /** Fade from transparent to opaque. */
        fun fade(
            from: Float = 0f,
            to: Float = 1f,
        ) = CanimationEffect(
            alpha = CanimationRange(from, to),
        )

        /** Slide upward (element enters from below). */
        fun slideUp(offset: Dp = 16.dp) =
            CanimationEffect(
                offsetY = CanimationDpRange(offset, 0.dp),
            )

        /** Slide downward (element enters from above). */
        fun slideDown(offset: Dp = 16.dp) =
            CanimationEffect(
                offsetY = CanimationDpRange((-offset.value).dp, 0.dp),
            )

        /** Slide from the left. */
        fun slideLeft(offset: Dp = 24.dp) =
            CanimationEffect(
                offsetX = CanimationDpRange((-offset.value).dp, 0.dp),
            )

        /** Slide from the right. */
        fun slideRight(offset: Dp = 24.dp) =
            CanimationEffect(
                offsetX = CanimationDpRange(offset, 0.dp),
            )

        /** Scale from a smaller/larger value. */
        fun scale(
            from: Float = 0.92f,
            to: Float = 1f,
        ) = CanimationEffect(
            scale = CanimationRange(from, to),
        )

        /** Pop in with overshoot scale. */
        fun pop(
            from: Float = 0.8f,
            to: Float = 1f,
        ) = CanimationEffect(
            scale = CanimationRange(from, to),
            durationMs = 300,
        )

        /** Rotate entry. */
        fun rotate(
            fromDegrees: Float = -15f,
            toDegrees: Float = 0f,
        ) = CanimationEffect(
            rotation = CanimationRange(fromDegrees, toDegrees),
        )

        /** Spin (full 360deg). */
        fun spin(fromDegrees: Float = -360f) =
            CanimationEffect(
                rotation = CanimationRange(fromDegrees, 0f),
                durationMs = 400,
            )

        /** Zoom in from small. */
        fun zoom(from: Float = 0.5f) =
            CanimationEffect(
                scale = CanimationRange(from, 1f),
                alpha = CanimationRange(0f, 1f),
                durationMs = 300,
            )

        /** Bounce-like entry (scale overshoot). */
        fun bounce() =
            CanimationEffect(
                scale = CanimationRange(0.3f, 1f),
                alpha = CanimationRange(0f, 1f),
                durationMs = 400,
            )

        /** Custom duration wrapper. */
        fun duration(ms: Int) = CanimationEffect(durationMs = ms)

        /** Custom easing wrapper. */
        fun easing(easing: Easing) = CanimationEffect(easing = easing)
    }

    private fun resolvedDurationMs(defaultDurationMs: Int): Int =
        when (durationMs) {
            UnspecifiedDurationMs -> defaultDurationMs
            else -> durationMs
        }
}

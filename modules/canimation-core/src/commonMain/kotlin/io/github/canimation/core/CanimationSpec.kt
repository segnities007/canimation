package io.github.canimation.core

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A range of Float values for animatable properties (alpha, scale).
 */
@Immutable
data class CanimationRange(val from: Float, val to: Float)

/**
 * A range of Dp values for animatable offset properties (offsetX, offsetY).
 */
@Immutable
data class CanimationDpRange(val from: Dp, val to: Dp)

/**
 * Custom animation specification defining how a single animation direction is executed.
 *
 * @property durationMs Animation duration in milliseconds. Must be >= 0.
 * @property easing Easing curve to apply.
 * @property alpha Optional alpha (opacity) animation range. Values must be in 0f..1f.
 * @property offsetX Optional horizontal offset animation range.
 * @property offsetY Optional vertical offset animation range.
 * @property scale Optional scale animation range. Values must be >= 0f.
 * @property rotation Optional rotation animation range in degrees.
 *
 * @throws IllegalArgumentException if any validation constraint is violated.
 */
@Immutable
data class CanimationSpec(
    val durationMs: Int,
    val easing: Easing,
    val alpha: CanimationRange? = null,
    val offsetX: CanimationDpRange? = null,
    val offsetY: CanimationDpRange? = null,
    val scale: CanimationRange? = null,
    val rotation: CanimationRange? = null,
) {
    init {
        require(durationMs >= 0) { "durationMs must be >= 0, was $durationMs" }
        alpha?.let {
            require(it.from in 0f..1f) { "alpha.from must be in 0f..1f, was ${it.from}" }
            require(it.to in 0f..1f) { "alpha.to must be in 0f..1f, was ${it.to}" }
        }
        scale?.let {
            require(it.from >= 0f) { "scale.from must be >= 0f, was ${it.from}" }
            require(it.to >= 0f) { "scale.to must be >= 0f, was ${it.to}" }
        }
    }

    /**
     * Converts this Enter spec to its Reduced motion equivalent.
     *
     * - `durationMs` → DurationTokens.short (120ms)
     * - `offsetX/offsetY` → scaled to 25% of original
     * - `scale` → compressed toward 1f (only 25% of the scale difference)
     * - `alpha` → unchanged
     * - `easing` → decelerate
     */
    fun toReduced(): CanimationSpec {
        val reducedEasing = CubicBezierEasing(0.0f, 0.0f, 0.0f, 1.0f) // decelerate
        return CanimationSpec(
            durationMs = 120,
            easing = reducedEasing,
            alpha = alpha,
            offsetX = offsetX?.let { CanimationDpRange(it.from * 0.25f, it.to * 0.25f) },
            offsetY = offsetY?.let { CanimationDpRange(it.from * 0.25f, it.to * 0.25f) },
            scale = scale?.let {
                CanimationRange(
                    from = 1f - (1f - it.from) * 0.25f,
                    to = 1f - (1f - it.to) * 0.25f,
                )
            },
            rotation = rotation?.let {
                CanimationRange(it.from * 0.25f, it.to * 0.25f)
            },
        )
    }

    /**
     * Creates a reversed version of this spec (for Exit from an Enter spec).
     *
     * - alpha/offset/scale: `from` and `to` are swapped
     * - easing → accelerate
     * - durationMs → unchanged
     */
    fun reversed(): CanimationSpec {
        val accelerateEasing = CubicBezierEasing(0.3f, 0.0f, 1.0f, 1.0f) // accelerate
        return CanimationSpec(
            durationMs = durationMs,
            easing = accelerateEasing,
            alpha = alpha?.let { CanimationRange(it.to, it.from) },
            offsetX = offsetX?.let { CanimationDpRange(it.to, it.from) },
            offsetY = offsetY?.let { CanimationDpRange(it.to, it.from) },
            scale = scale?.let { CanimationRange(it.to, it.from) },
            rotation = rotation?.let { CanimationRange(it.to, it.from) },
        )
    }
}

private operator fun Dp.times(factor: Float): Dp = (this.value * factor).dp

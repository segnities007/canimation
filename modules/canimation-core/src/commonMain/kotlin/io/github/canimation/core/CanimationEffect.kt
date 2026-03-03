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
    val durationMs: Int = 220,
    val easing: Easing? = null,
) {
    /**
     * Combine two effects. Properties from [other] override this effect's null values.
     * Duration takes the longer of the two.
     */
    operator fun plus(other: CanimationEffect): CanimationEffect = CanimationEffect(
        alpha = other.alpha ?: alpha,
        offsetX = other.offsetX ?: offsetX,
        offsetY = other.offsetY ?: offsetY,
        scale = other.scale ?: scale,
        rotation = other.rotation ?: rotation,
        durationMs = maxOf(durationMs, other.durationMs),
        easing = other.easing ?: easing,
    )

    /** Convert to a [CanimationSpec] for the enter direction. */
    fun toEnterSpec(defaultEasing: Easing): CanimationSpec = CanimationSpec(
        durationMs = durationMs,
        easing = easing ?: defaultEasing,
        alpha = alpha,
        offsetX = offsetX,
        offsetY = offsetY,
        scale = scale,
        rotation = rotation,
    )

    /** Convert to a [CanimationSpec] for the exit direction (reversed). */
    fun toExitSpec(defaultEasing: Easing): CanimationSpec = toEnterSpec(defaultEasing).reversed()

    companion object {
        /** Fade from transparent to opaque. */
        fun fade(from: Float = 0f, to: Float = 1f) = CanimationEffect(
            alpha = CanimationRange(from, to),
        )

        /** Slide upward (element enters from below). */
        fun slideUp(offset: Dp = 16.dp) = CanimationEffect(
            offsetY = CanimationDpRange(offset, 0.dp),
        )

        /** Slide downward (element enters from above). */
        fun slideDown(offset: Dp = 16.dp) = CanimationEffect(
            offsetY = CanimationDpRange((-offset.value).dp, 0.dp),
        )

        /** Slide from the left. */
        fun slideLeft(offset: Dp = 24.dp) = CanimationEffect(
            offsetX = CanimationDpRange((-offset.value).dp, 0.dp),
        )

        /** Slide from the right. */
        fun slideRight(offset: Dp = 24.dp) = CanimationEffect(
            offsetX = CanimationDpRange(offset, 0.dp),
        )

        /** Scale from a smaller/larger value. */
        fun scale(from: Float = 0.92f, to: Float = 1f) = CanimationEffect(
            scale = CanimationRange(from, to),
        )

        /** Pop in with overshoot scale. */
        fun pop(from: Float = 0.8f, to: Float = 1f) = CanimationEffect(
            scale = CanimationRange(from, to),
            durationMs = 300,
        )

        /** Rotate entry. */
        fun rotate(fromDegrees: Float = -15f, toDegrees: Float = 0f) = CanimationEffect(
            rotation = CanimationRange(fromDegrees, toDegrees),
        )

        /** Spin (full 360°). */
        fun spin(fromDegrees: Float = -360f) = CanimationEffect(
            rotation = CanimationRange(fromDegrees, 0f),
            durationMs = 400,
        )

        /** Zoom in from small. */
        fun zoom(from: Float = 0.5f) = CanimationEffect(
            scale = CanimationRange(from, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )

        /** Bounce-like entry (scale overshoot). */
        fun bounce() = CanimationEffect(
            scale = CanimationRange(0.3f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )

        /** Custom duration wrapper. */
        fun duration(ms: Int) = CanimationEffect(durationMs = ms)

        /** Custom easing wrapper. */
        fun easing(easing: Easing) = CanimationEffect(easing = easing)
    }
}

/**
 * Hierarchical namespace for common animation effects.
 *
 * Usage:
 * ```kotlin
 * Modifier.canimation(visible, Canimation.Fade.Up)
 * Modifier.canimation(visible, Canimation.Scale.Pop + Canimation.Fade.In)
 * Modifier.canimation(visible, Canimation.Slide.Left + Canimation.Fade.In)
 * ```
 */
object Canimation {

    object Fade {
        /** Simple fade in. */
        val In = CanimationEffect.fade()
        /** Fade with upward slide. */
        val Up = CanimationEffect.fade() + CanimationEffect.slideUp()
        /** Fade with downward slide. */
        val Down = CanimationEffect.fade() + CanimationEffect.slideDown()
        /** Fade from left. */
        val Left = CanimationEffect.fade() + CanimationEffect.slideLeft()
        /** Fade from right. */
        val Right = CanimationEffect.fade() + CanimationEffect.slideRight()
        /** Gentle long fade. */
        val Gentle = CanimationEffect.fade().copy(durationMs = 600)
        /** Big upward slide with fade. */
        val UpBig = CanimationEffect.fade() + CanimationEffect.slideUp(40.dp)
        /** Big downward slide with fade. */
        val DownBig = CanimationEffect.fade() + CanimationEffect.slideDown(40.dp)
    }

    object Scale {
        /** Scale in from slightly smaller. */
        val In = CanimationEffect.scale()
        /** Scale up from larger. */
        val Up = CanimationEffect.scale(from = 1.08f)
        /** Pop with overshoot. */
        val Pop = CanimationEffect.pop()
        /** Zoom from half size. */
        val Zoom = CanimationEffect.zoom()
        /** Expand from nothing. */
        val Expand = CanimationEffect.scale(from = 0f)
        /** Shrink from large. */
        val Shrink = CanimationEffect.scale(from = 2f)
    }

    object Slide {
        /** Slide from right (leftward). */
        val Left = CanimationEffect.slideLeft()
        /** Slide from left (rightward). */
        val Right = CanimationEffect.slideRight()
        /** Slide from below. */
        val Up = CanimationEffect.slideUp()
        /** Slide from above. */
        val Down = CanimationEffect.slideDown()
        /** Big slide from right. */
        val LeftBig = CanimationEffect.slideLeft(40.dp)
        /** Big slide from left. */
        val RightBig = CanimationEffect.slideRight(40.dp)
    }

    object Rotate {
        /** Counter-clockwise rotation. */
        val In = CanimationEffect.rotate()
        /** Clockwise rotation. */
        val Clockwise = CanimationEffect.rotate(fromDegrees = 15f)
        /** Full 360° spin. */
        val Spin = CanimationEffect.spin()
    }

    object Bounce {
        /** Bouncy scale entry. */
        val In = CanimationEffect.bounce()
        /** Bounce from top. */
        val Down = CanimationEffect.bounce() + CanimationEffect.slideDown(30.dp)
        /** Bounce from bottom. */
        val Up = CanimationEffect.bounce() + CanimationEffect.slideUp(30.dp)
    }

    object Spring {
        /** Spring overshoot scale. */
        val In = CanimationEffect.scale(from = 0.8f).copy(durationMs = 350)
        /** Spring slide from below. */
        val Up = CanimationEffect.fade() + CanimationEffect.slideUp(20.dp) + CanimationEffect.duration(350)
    }

    object Flip {
        /** Half-rotation flip. */
        val In = CanimationEffect.rotate(fromDegrees = -90f) + CanimationEffect.fade()
        /** Flip upward. */
        val Up = CanimationEffect.rotate(fromDegrees = -90f) + CanimationEffect.slideUp(8.dp) + CanimationEffect.fade()
    }
}

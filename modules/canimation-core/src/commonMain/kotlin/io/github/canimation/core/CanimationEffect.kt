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
 * Hierarchical namespace for animation effects organized by Atomic Design.
 *
 * ## Atomic Design Hierarchy
 *
 * ### Atoms — single-property primitives
 * ```kotlin
 * Canimation.Fade.In           // opacity only
 * Canimation.Slide.Up          // translation only
 * Canimation.Scale.In          // scale only
 * Canimation.Rotate.In         // rotation only
 * ```
 *
 * ### Molecules — two-property combinations
 * ```kotlin
 * Canimation.Fade.Up           // fade + slideUp
 * Canimation.Scale.Pop         // scale + duration tweak
 * Canimation.Zoom.In           // scale + fade
 * ```
 *
 * ### Organisms — complex multi-property / attention seekers
 * ```kotlin
 * Canimation.Attention.Pulse   // emphasis animation
 * Canimation.Entrance.Drop     // dramatic entry
 * Canimation.Material.FadeThrough
 * ```
 *
 * All effects are composable with `+`:
 * ```kotlin
 * Modifier.canimation(visible, Canimation.Scale.Pop + Canimation.Fade.In)
 * ```
 */

object Canimation {
    val Fade = CanimationFade
    val Scale = CanimationScale
    val Slide = CanimationSlide
    val Rotate = CanimationRotate
    val Bounce = CanimationBounce
    val Spring = CanimationSpring
    val Flip = CanimationFlip
    val Zoom = CanimationZoom
    val Attention = CanimationAttention
    val Entrance = CanimationEntrance
    val Material = CanimationMaterial
    val Morph = CanimationMorph
    val Blur = CanimationBlur
    val Swipe = CanimationSwipe
    val Reveal = CanimationReveal
    val Micro = CanimationMicro
    val Page = CanimationPage
    val Wave = CanimationWave
    val Glitch = CanimationGlitch
    val Elastic = CanimationElastic
    val Cinematic = CanimationCinematic
    val Playful = CanimationPlayful
    val Stagger = CanimationStagger
    val Diagonal = CanimationDiagonal
    val Shrink = CanimationShrink
    val Tilt = CanimationTilt
    val Float = CanimationFloat
    val Drop = CanimationDrop
    val Rise = CanimationRise
    val Stretch = CanimationStretch
}

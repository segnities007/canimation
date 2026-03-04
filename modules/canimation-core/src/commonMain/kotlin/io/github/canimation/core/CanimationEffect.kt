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

    // ═══════════════════════════════════════════════════════════════
    //  ATOMS — Single-property animation primitives
    // ═══════════════════════════════════════════════════════════════

    /** Opacity-based animations. */
    object Fade {
        // ── Atoms ──
        /** Simple fade in (0 → 1). */
        val In = CanimationEffect.fade()
        /** Fade out (1 → 0). */
        val Out = CanimationEffect.fade(from = 1f, to = 0f)
        /** Gentle long fade (600ms). */
        val Gentle = CanimationEffect.fade().copy(durationMs = 600)
        /** Quick snap fade (120ms). */
        val Quick = CanimationEffect.fade().copy(durationMs = 120)

        // ── Molecules (fade + directional slide) ──
        /** Fade with upward slide. */
        val Up = CanimationEffect.fade() + CanimationEffect.slideUp()
        /** Fade with downward slide. */
        val Down = CanimationEffect.fade() + CanimationEffect.slideDown()
        /** Fade from left. */
        val Left = CanimationEffect.fade() + CanimationEffect.slideLeft()
        /** Fade from right. */
        val Right = CanimationEffect.fade() + CanimationEffect.slideRight()

        // ── Molecules (fade + big directional slide) ──
        /** Big upward slide with fade. */
        val UpBig = CanimationEffect.fade() + CanimationEffect.slideUp(40.dp)
        /** Big downward slide with fade. */
        val DownBig = CanimationEffect.fade() + CanimationEffect.slideDown(40.dp)
        /** Big slide from left with fade. */
        val LeftBig = CanimationEffect.fade() + CanimationEffect.slideLeft(40.dp)
        /** Big slide from right with fade. */
        val RightBig = CanimationEffect.fade() + CanimationEffect.slideRight(40.dp)

        // ── Molecules (fade + diagonal) ──
        /** Fade from top-left corner. */
        val TopLeft = CanimationEffect.fade() + CanimationEffect.slideDown(12.dp) + CanimationEffect.slideLeft(12.dp)
        /** Fade from top-right corner. */
        val TopRight = CanimationEffect.fade() + CanimationEffect.slideDown(12.dp) + CanimationEffect.slideRight(12.dp)
        /** Fade from bottom-left corner. */
        val BottomLeft = CanimationEffect.fade() + CanimationEffect.slideUp(12.dp) + CanimationEffect.slideLeft(12.dp)
        /** Fade from bottom-right corner. */
        val BottomRight = CanimationEffect.fade() + CanimationEffect.slideUp(12.dp) + CanimationEffect.slideRight(12.dp)

        // ── Molecules (fade + scale) ──
        /** Fade with slight scale-up. */
        val Small = CanimationEffect.fade() + CanimationEffect.scale(from = 0.95f)
        /** Fade with dramatic scale-up. */
        val Big = CanimationEffect.fade() + CanimationEffect.scale(from = 0.8f)
    }

    /** Scale-based animations. */
    object Scale {
        // ── Atoms ──
        /** Scale in from slightly smaller (0.92 → 1). */
        val In = CanimationEffect.scale()
        /** Scale up from larger (1.08 → 1). */
        val Up = CanimationEffect.scale(from = 1.08f)
        /** Scale down from current. */
        val Down = CanimationEffect.scale(from = 0.85f)
        /** Expand from nothing (0 → 1). */
        val Expand = CanimationEffect.scale(from = 0f)
        /** Shrink from large (2 → 1). */
        val Shrink = CanimationEffect.scale(from = 2f)
        /** Tiny subtle scale (0.97 → 1). */
        val Subtle = CanimationEffect.scale(from = 0.97f)

        // ── Molecules ──
        /** Pop with overshoot. */
        val Pop = CanimationEffect.pop()
        /** Scale in with fade. */
        val FadeIn = CanimationEffect.scale() + CanimationEffect.fade()
        /** Scale up from below with fade. */
        val UpFade = CanimationEffect.scale(from = 0.9f) + CanimationEffect.fade() + CanimationEffect.slideUp(8.dp)
        /** Scale up from above with fade. */
        val DownFade = CanimationEffect.scale(from = 0.9f) + CanimationEffect.fade() + CanimationEffect.slideDown(8.dp)
    }

    /** Translation-based animations. */
    object Slide {
        // ── Atoms ──
        /** Slide from right (leftward). */
        val Left = CanimationEffect.slideLeft()
        /** Slide from left (rightward). */
        val Right = CanimationEffect.slideRight()
        /** Slide from below. */
        val Up = CanimationEffect.slideUp()
        /** Slide from above. */
        val Down = CanimationEffect.slideDown()

        // ── Molecules (big slides) ──
        /** Big slide from right. */
        val LeftBig = CanimationEffect.slideLeft(40.dp)
        /** Big slide from left. */
        val RightBig = CanimationEffect.slideRight(40.dp)
        /** Big slide from below. */
        val UpBig = CanimationEffect.slideUp(40.dp)
        /** Big slide from above. */
        val DownBig = CanimationEffect.slideDown(40.dp)

        // ── Molecules (subtle slides) ──
        /** Subtle slide from below (8dp). */
        val UpSubtle = CanimationEffect.slideUp(8.dp)
        /** Subtle slide from above (8dp). */
        val DownSubtle = CanimationEffect.slideDown(8.dp)
        /** Subtle slide from right (8dp). */
        val LeftSubtle = CanimationEffect.slideLeft(8.dp)
        /** Subtle slide from left (8dp). */
        val RightSubtle = CanimationEffect.slideRight(8.dp)
    }

    /** Rotation-based animations. */
    object Rotate {
        // ── Atoms ──
        /** Counter-clockwise rotation (-15°). */
        val In = CanimationEffect.rotate()
        /** Clockwise rotation (15°). */
        val Clockwise = CanimationEffect.rotate(fromDegrees = 15f)
        /** Full 360° spin. */
        val Spin = CanimationEffect.spin()
        /** Half turn (180°). */
        val Half = CanimationEffect.rotate(fromDegrees = -180f).copy(durationMs = 400)
        /** Quarter turn (90°). */
        val Quarter = CanimationEffect.rotate(fromDegrees = -90f).copy(durationMs = 300)

        // ── Molecules (rotate + fade) ──
        /** Rotate in with fade. */
        val FadeIn = CanimationEffect.rotate() + CanimationEffect.fade()
        /** Clockwise rotate with fade. */
        val ClockwiseFade = CanimationEffect.rotate(fromDegrees = 15f) + CanimationEffect.fade()
        /** Spin with fade. */
        val SpinFade = CanimationEffect.spin() + CanimationEffect.fade()

        // ── Molecules (rotate + scale) ──
        /** Rotate with scale. */
        val ScaleIn = CanimationEffect.rotate(fromDegrees = -30f) + CanimationEffect.scale(from = 0.8f)
        /** Rotate from down-left with fade. */
        val DownLeft = CanimationEffect.rotate(fromDegrees = -45f) + CanimationEffect.fade().copy(durationMs = 300)
        /** Rotate from down-right with fade. */
        val DownRight = CanimationEffect.rotate(fromDegrees = 45f) + CanimationEffect.fade().copy(durationMs = 300)
        /** Rotate from up-left with fade. */
        val UpLeft = CanimationEffect.rotate(fromDegrees = 45f) + CanimationEffect.slideUp(8.dp) + CanimationEffect.fade()
        /** Rotate from up-right with fade. */
        val UpRight = CanimationEffect.rotate(fromDegrees = -45f) + CanimationEffect.slideUp(8.dp) + CanimationEffect.fade()
    }

    // ═══════════════════════════════════════════════════════════════
    //  MOLECULES — Multi-property combination effects
    // ═══════════════════════════════════════════════════════════════

    /** Bounce-based entry animations. */
    object Bounce {
        /** Bouncy scale entry. */
        val In = CanimationEffect.bounce()
        /** Bounce from top. */
        val Down = CanimationEffect.bounce() + CanimationEffect.slideDown(30.dp)
        /** Bounce from bottom. */
        val Up = CanimationEffect.bounce() + CanimationEffect.slideUp(30.dp)
        /** Bounce from left. */
        val Left = CanimationEffect.bounce() + CanimationEffect.slideLeft(30.dp)
        /** Bounce from right. */
        val Right = CanimationEffect.bounce() + CanimationEffect.slideRight(30.dp)
    }

    /** Spring physics-based animations. */
    object Spring {
        /** Spring overshoot scale. */
        val In = CanimationEffect.scale(from = 0.8f).copy(durationMs = 350)
        /** Spring slide from below. */
        val Up = CanimationEffect.fade() + CanimationEffect.slideUp(20.dp) + CanimationEffect.duration(350)
        /** Spring slide from above. */
        val Down = CanimationEffect.fade() + CanimationEffect.slideDown(20.dp) + CanimationEffect.duration(350)
        /** Spring slide from left. */
        val Left = CanimationEffect.fade() + CanimationEffect.slideLeft(20.dp) + CanimationEffect.duration(350)
        /** Spring slide from right. */
        val Right = CanimationEffect.fade() + CanimationEffect.slideRight(20.dp) + CanimationEffect.duration(350)
        /** Spring pop (scale overshoot with fade). */
        val Pop = CanimationEffect.scale(from = 0.6f) + CanimationEffect.fade() + CanimationEffect.duration(400)
        /** Spring bounce entry. */
        val Bounce = CanimationEffect.scale(from = 0.5f) + CanimationEffect.fade() + CanimationEffect.duration(500)
    }

    /** Flip rotation animations. */
    object Flip {
        /** Half-rotation flip with fade. */
        val In = CanimationEffect.rotate(fromDegrees = -90f) + CanimationEffect.fade()
        /** Flip upward. */
        val Up = CanimationEffect.rotate(fromDegrees = -90f) + CanimationEffect.slideUp(8.dp) + CanimationEffect.fade()
        /** Flip downward. */
        val Down = CanimationEffect.rotate(fromDegrees = 90f) + CanimationEffect.slideDown(8.dp) + CanimationEffect.fade()
        /** Horizontal flip (X-axis). */
        val X = CanimationEffect.rotate(fromDegrees = -180f) + CanimationEffect.fade().copy(durationMs = 400)
        /** Vertical flip (Y-axis). */
        val Y = CanimationEffect(
            rotation = CanimationRange(-180f, 0f),
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.8f, 1f),
            durationMs = 400,
        )
    }

    /** Zoom-based animations (scale + fade combos). */
    object Zoom {
        /** Zoom in from center. */
        val In = CanimationEffect.zoom()
        /** Zoom out (from large to normal). */
        val Out = CanimationEffect(
            scale = CanimationRange(1.5f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
        /** Zoom in from above. */
        val InUp = CanimationEffect.zoom() + CanimationEffect.slideDown(20.dp)
        /** Zoom in from below. */
        val InDown = CanimationEffect.zoom() + CanimationEffect.slideUp(20.dp)
        /** Zoom in from left. */
        val InLeft = CanimationEffect.zoom() + CanimationEffect.slideLeft(20.dp)
        /** Zoom in from right. */
        val InRight = CanimationEffect.zoom() + CanimationEffect.slideRight(20.dp)
        /** Dramatic zoom from tiny. */
        val Dramatic = CanimationEffect(
            scale = CanimationRange(0.1f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )
    }

    // ═══════════════════════════════════════════════════════════════
    //  ORGANISMS — Complex multi-step / attention-seeking animations
    // ═══════════════════════════════════════════════════════════════

    /** Attention-seeking emphasis animations. Use with `canimationEmphasize`. */
    object Attention {
        /** Pulse scale effect. */
        val Pulse = CanimationEffect(
            scale = CanimationRange(1f, 1.05f),
            durationMs = 500,
        )
        /** Heartbeat double-pulse. */
        val HeartBeat = CanimationEffect(
            scale = CanimationRange(1f, 1.15f),
            durationMs = 600,
        )
        /** Tada celebration with rotation. */
        val Tada = CanimationEffect(
            scale = CanimationRange(1f, 1.1f),
            rotation = CanimationRange(0f, -3f),
            durationMs = 500,
        )
        /** Wobble side-to-side. */
        val Wobble = CanimationEffect(
            rotation = CanimationRange(0f, -5f),
            offsetX = CanimationDpRange(0.dp, (-4).dp),
            durationMs = 500,
        )
        /** Swing pendulum. */
        val Swing = CanimationEffect(
            rotation = CanimationRange(0f, 15f),
            durationMs = 400,
        )
        /** Rubber band stretch. */
        val RubberBand = CanimationEffect(
            scale = CanimationRange(1f, 1.25f),
            durationMs = 400,
        )
        /** Jello wiggle. */
        val Jello = CanimationEffect(
            rotation = CanimationRange(0f, -8f),
            durationMs = 500,
        )
        /** Flash blink. */
        val Flash = CanimationEffect(
            alpha = CanimationRange(1f, 0f),
            durationMs = 250,
        )
        /** Horizontal shake. */
        val ShakeX = CanimationEffect(
            offsetX = CanimationDpRange(0.dp, (-10).dp),
            durationMs = 400,
        )
        /** Vertical shake. */
        val ShakeY = CanimationEffect(
            offsetY = CanimationDpRange(0.dp, (-10).dp),
            durationMs = 400,
        )
        /** Head shake (subtle horizontal). */
        val HeadShake = CanimationEffect(
            offsetX = CanimationDpRange(0.dp, (-6).dp),
            rotation = CanimationRange(0f, -3f),
            durationMs = 500,
        )
        /** Gentle glow/throb. */
        val Glow = CanimationEffect(
            alpha = CanimationRange(0.7f, 1f),
            scale = CanimationRange(0.98f, 1.02f),
            durationMs = 800,
        )
        /** Ring/bell shake. */
        val Ring = CanimationEffect(
            rotation = CanimationRange(0f, 20f),
            durationMs = 300,
        )
    }

    /** Dramatic entrance animations. */
    object Entrance {
        /** Elevate from below with scale. */
        val Elevate = CanimationEffect(
            offsetY = CanimationDpRange(24.dp, 0.dp),
            scale = CanimationRange(0.95f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
        /** Drop from above. */
        val Drop = CanimationEffect(
            offsetY = CanimationDpRange((-30).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
        /** Jack in the box spring-up. */
        val JackInTheBox = CanimationEffect(
            scale = CanimationRange(0.1f, 1f),
            rotation = CanimationRange(30f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )
        /** Roll in from the side. */
        val RollIn = CanimationEffect(
            offsetX = CanimationDpRange((-60).dp, 0.dp),
            rotation = CanimationRange(-120f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )
        /** Light speed from left. */
        val LightSpeedLeft = CanimationEffect(
            offsetX = CanimationDpRange((-80).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(-10f, 0f),
            durationMs = 400,
        )
        /** Light speed from right. */
        val LightSpeedRight = CanimationEffect(
            offsetX = CanimationDpRange(80.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(10f, 0f),
            durationMs = 400,
        )
        /** Back in from above. */
        val BackInUp = CanimationEffect(
            offsetY = CanimationDpRange((-40).dp, 0.dp),
            scale = CanimationRange(0.7f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
        /** Back in from below. */
        val BackInDown = CanimationEffect(
            offsetY = CanimationDpRange(40.dp, 0.dp),
            scale = CanimationRange(0.7f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
        /** Back in from left. */
        val BackInLeft = CanimationEffect(
            offsetX = CanimationDpRange((-40).dp, 0.dp),
            scale = CanimationRange(0.7f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
        /** Back in from right. */
        val BackInRight = CanimationEffect(
            offsetX = CanimationDpRange(40.dp, 0.dp),
            scale = CanimationRange(0.7f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
        /** Shrink from large. */
        val ShrinkIn = CanimationEffect(
            scale = CanimationRange(1.5f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
        /** Tilt entry with rotation. */
        val TiltIn = CanimationEffect(
            rotation = CanimationRange(-20f, 0f),
            scale = CanimationRange(0.9f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
        /** Snap appearance. */
        val Snap = CanimationEffect(
            scale = CanimationRange(0.5f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 150,
        )
        /** Swing from hinge. */
        val SwingIn = CanimationEffect(
            rotation = CanimationRange(-60f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )
        /** Unfold/expand appearance. */
        val Unfold = CanimationEffect(
            scale = CanimationRange(0f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
        /** Rise from below with parallax feel. */
        val Rise = CanimationEffect(
            offsetY = CanimationDpRange(60.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.98f, 1f),
            durationMs = 500,
        )
        /** Emerge from center with scale. */
        val Emerge = CanimationEffect(
            scale = CanimationRange(0.3f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
    }

    /** Material Design motion patterns. */
    object Material {
        /** Fade through (crossfade with scale). */
        val FadeThrough = CanimationEffect(
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.92f, 1f),
            durationMs = 300,
        )
        /** Shared axis X (horizontal transition). */
        val SharedAxisX = CanimationEffect(
            offsetX = CanimationDpRange(30.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
        /** Shared axis Y (vertical transition). */
        val SharedAxisY = CanimationEffect(
            offsetY = CanimationDpRange(30.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
        /** Shared axis Z (depth transition). */
        val SharedAxisZ = CanimationEffect(
            scale = CanimationRange(0.8f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
        /** Emphasized entry (larger scale). */
        val Emphasized = CanimationEffect(
            offsetY = CanimationDpRange(40.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
        /** Container transform hint. */
        val ContainerTransform = CanimationEffect(
            scale = CanimationRange(0.85f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
    }

    /** Morph/transform animations. */
    object Morph {
        /** Scale morph from small. */
        val ScaleUp = CanimationEffect(
            scale = CanimationRange(0.5f, 1f),
            alpha = CanimationRange(0.5f, 1f),
            durationMs = 400,
        )
        /** Expand morph with offset. */
        val Expand = CanimationEffect(
            scale = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange((-20).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )
        /** Collapse morph. */
        val Collapse = CanimationEffect(
            scale = CanimationRange(1.2f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
    }

    /** Blur-like fade effects using opacity + scale. */
    object Blur {
        /** Blur fade in — scale from large with fade. */
        val In = CanimationEffect(
            scale = CanimationRange(1.15f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
        /** Blur zoom out — scale from small with fade. */
        val Out = CanimationEffect(
            scale = CanimationRange(0.85f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
        /** Soft focus — gentle scale with slow fade. */
        val Soft = CanimationEffect(
            scale = CanimationRange(1.05f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 600,
        )
    }

    /** Swipe / fling style animations. */
    object Swipe {
        /** Swipe from left edge (large offset). */
        val Left = CanimationEffect(
            offsetX = CanimationDpRange((-100).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
        /** Swipe from right edge. */
        val Right = CanimationEffect(
            offsetX = CanimationDpRange(100.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
        /** Swipe up from bottom edge. */
        val Up = CanimationEffect(
            offsetY = CanimationDpRange(100.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
        /** Swipe down from top edge. */
        val Down = CanimationEffect(
            offsetY = CanimationDpRange((-100).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
        /** Fling in from right with overshoot feel. */
        val FlingRight = CanimationEffect(
            offsetX = CanimationDpRange(200.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 250,
        )
        /** Fling in from left with overshoot feel. */
        val FlingLeft = CanimationEffect(
            offsetX = CanimationDpRange((-200).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 250,
        )
    }

    /** Reveal / uncover patterns. */
    object Reveal {
        /** Scale up from center with fade. */
        val Center = CanimationEffect(
            scale = CanimationRange(0f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
        /** Reveal from top — slide down into view. */
        val Top = CanimationEffect(
            offsetY = CanimationDpRange((-50).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.9f, 1f),
            durationMs = 400,
        )
        /** Reveal from bottom — slide up into view. */
        val Bottom = CanimationEffect(
            offsetY = CanimationDpRange(50.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.9f, 1f),
            durationMs = 400,
        )
        /** Dramatic reveal — large scale and fade. */
        val Dramatic = CanimationEffect(
            scale = CanimationRange(0f, 1f),
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(15f, 0f),
            durationMs = 600,
        )
    }

    /** Subtle, micro-interaction animations. */
    object Micro {
        /** Tiny nudge up (4dp). */
        val NudgeUp = CanimationEffect(
            offsetY = CanimationDpRange(4.dp, 0.dp),
            alpha = CanimationRange(0.8f, 1f),
            durationMs = 150,
        )
        /** Tiny nudge down. */
        val NudgeDown = CanimationEffect(
            offsetY = CanimationDpRange((-4).dp, 0.dp),
            alpha = CanimationRange(0.8f, 1f),
            durationMs = 150,
        )
        /** Micro scale pulse. */
        val Pulse = CanimationEffect(
            scale = CanimationRange(0.96f, 1f),
            durationMs = 120,
        )
        /** Micro rotation wiggle. */
        val Wiggle = CanimationEffect(
            rotation = CanimationRange(3f, 0f),
            durationMs = 200,
        )
        /** Touch feedback — slight shrink. */
        val Press = CanimationEffect(
            scale = CanimationRange(0.95f, 1f),
            durationMs = 100,
        )
    }

    /** Page transition patterns. */
    object Page {
        /** iOS-style push from right. */
        val PushRight = CanimationEffect(
            offsetX = CanimationDpRange(300.dp, 0.dp),
            alpha = CanimationRange(0.5f, 1f),
            durationMs = 350,
        )
        /** iOS-style push from left. */
        val PushLeft = CanimationEffect(
            offsetX = CanimationDpRange((-300).dp, 0.dp),
            alpha = CanimationRange(0.5f, 1f),
            durationMs = 350,
        )
        /** Android-style bottom-up sheet. */
        val BottomSheet = CanimationEffect(
            offsetY = CanimationDpRange(400.dp, 0.dp),
            alpha = CanimationRange(0.5f, 1f),
            durationMs = 350,
        )
        /** Cover from bottom (modal). */
        val CoverUp = CanimationEffect(
            offsetY = CanimationDpRange(600.dp, 0.dp),
            durationMs = 400,
        )
        /** Crossfade transition. */
        val Crossfade = CanimationEffect(
            alpha = CanimationRange(0f, 1f),
            durationMs = 250,
        )
    }

    /** Wave-like motion effects. */
    object Wave {
        val Gentle = CanimationEffect(offsetY = CanimationDpRange(6.dp, 0.dp), rotation = CanimationRange(-3f, 0f), alpha = CanimationRange(0f, 1f), durationMs = 500)
        val Strong = CanimationEffect(offsetY = CanimationDpRange(20.dp, 0.dp), rotation = CanimationRange(-8f, 0f), alpha = CanimationRange(0f, 1f), durationMs = 600)
        val Ripple = CanimationEffect(scale = CanimationRange(0.9f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 400)
        val Float = CanimationEffect(offsetY = CanimationDpRange(12.dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 700)
        val Drift = CanimationEffect(offsetX = CanimationDpRange(10.dp, 0.dp), offsetY = CanimationDpRange(6.dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 500)
    }

    /** Digital glitch effects. */
    object Glitch {
        val In = CanimationEffect(offsetX = CanimationDpRange((-4).dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 150)
        val Shake = CanimationEffect(offsetX = CanimationDpRange(8.dp, 0.dp), rotation = CanimationRange(2f, 0f), alpha = CanimationRange(0f, 1f), durationMs = 200)
        val Flicker = CanimationEffect(alpha = CanimationRange(0.3f, 1f), scale = CanimationRange(1.02f, 1f), durationMs = 180)
        val Distort = CanimationEffect(offsetX = CanimationDpRange(6.dp, 0.dp), scale = CanimationRange(1.05f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 250)
    }

    /** Elastic spring-like effects. */
    object Elastic {
        val In = CanimationEffect(scale = CanimationRange(0.6f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 500)
        val Stretch = CanimationEffect(scale = CanimationRange(0.7f, 1f), offsetY = CanimationDpRange(30.dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 600)
        val Squash = CanimationEffect(scale = CanimationRange(1.3f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 400)
        val Snap = CanimationEffect(scale = CanimationRange(0.5f, 1f), rotation = CanimationRange(-5f, 0f), alpha = CanimationRange(0f, 1f), durationMs = 350)
        val Wobble = CanimationEffect(scale = CanimationRange(0.85f, 1f), rotation = CanimationRange(3f, 0f), alpha = CanimationRange(0f, 1f), durationMs = 450)
    }

    /** Cinematic film-inspired effects. */
    object Cinematic {
        val Curtain = CanimationEffect(offsetY = CanimationDpRange((-40).dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 600)
        val ZoomPan = CanimationEffect(scale = CanimationRange(1.5f, 1f), offsetX = CanimationDpRange(30.dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 700)
        val Dolly = CanimationEffect(scale = CanimationRange(0.3f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 800)
        val Reveal = CanimationEffect(alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.95f, 1f), offsetY = CanimationDpRange(10.dp, 0.dp), durationMs = 500)
        val FadeToBlack = CanimationEffect(alpha = CanimationRange(0f, 1f), durationMs = 600)
        val Dramatic = CanimationEffect(scale = CanimationRange(0.4f, 1f), rotation = CanimationRange(-10f, 0f), alpha = CanimationRange(0f, 1f), durationMs = 700)
    }

    /** Playful fun effects. */
    object Playful {
        val Wiggle = CanimationEffect(rotation = CanimationRange(-12f, 0f), scale = CanimationRange(0.9f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 400)
        val Hop = CanimationEffect(offsetY = CanimationDpRange((-20).dp, 0.dp), scale = CanimationRange(0.85f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 350)
        val Spin = CanimationEffect(rotation = CanimationRange(-180f, 0f), scale = CanimationRange(0.7f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 500)
        val Pop = CanimationEffect(scale = CanimationRange(0.3f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 300)
        val Twirl = CanimationEffect(rotation = CanimationRange(-90f, 0f), offsetY = CanimationDpRange(15.dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 450)
        val Boing = CanimationEffect(scale = CanimationRange(0.5f, 1f), offsetY = CanimationDpRange(10.dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 400)
    }

    /** Stagger helper for building delayed animation sequences. */
    object Stagger {
        /** Quick stagger delay (40ms per item). */
        const val Quick = 40
        /** Normal stagger delay (70ms per item). */
        const val Normal = 70
        /** Slow stagger delay (120ms per item). */
        const val Slow = 120
        /** Very slow stagger delay (200ms per item). */
        const val Relaxed = 200
    }
}

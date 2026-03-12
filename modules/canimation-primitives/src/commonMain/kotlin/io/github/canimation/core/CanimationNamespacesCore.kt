package io.github.canimation.core

import androidx.compose.ui.unit.dp

/** Opacity-based animations. */
object CanimationFade {
    // ── Atoms ──
    /** Simple fade in (0 → 1). */
    val In = CanimationEffect.fade()

    /** Fade out (1 → 0). */
    val Out = CanimationEffect.fade(from = 1f, to = 0f)

    /** Gentle long fade (600ms). */
    val Gentle = CanimationEffect.fade().copy(durationMs = 600)

    /** Quick snap fade (DurationTokens.short). */
    val Quick = CanimationEffect.fade().copy(durationMs = DurationTokens.Default.shortMs)

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
object CanimationScale {
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
object CanimationSlide {
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
object CanimationRotate {
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
object CanimationBounce {
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
object CanimationSpring {
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
object CanimationFlip {
    /** Half-rotation flip with fade. */
    val In = CanimationEffect.rotate(fromDegrees = -90f) + CanimationEffect.fade()

    /** Flip upward. */
    val Up = CanimationEffect.rotate(fromDegrees = -90f) + CanimationEffect.slideUp(8.dp) + CanimationEffect.fade()

    /** Flip downward. */
    val Down = CanimationEffect.rotate(fromDegrees = 90f) + CanimationEffect.slideDown(8.dp) + CanimationEffect.fade()

    /** Horizontal flip (X-axis). */
    val X = CanimationEffect.rotate(fromDegrees = -180f) + CanimationEffect.fade().copy(durationMs = 400)

    /** Vertical flip (Y-axis). */
    val Y =
        CanimationEffect(
            rotation = CanimationRange(-180f, 0f),
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.8f, 1f),
            durationMs = 400,
        )
}

/** Zoom-based animations (scale + fade combos). */
object CanimationZoom {
    /** Zoom in from center. */
    val In = CanimationEffect.zoom()

    /** Zoom out (from large to normal). */
    val Out =
        CanimationEffect(
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
    val Dramatic =
        CanimationEffect(
            scale = CanimationRange(0.1f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )
}

// ═══════════════════════════════════════════════════════════════
//  ORGANISMS — Complex multi-step / attention-seeking animations
// ═══════════════════════════════════════════════════════════════

/** Attention-seeking emphasis animations. Use with `canimationEmphasize`. */
object CanimationAttention {
    /** Pulse scale effect. */
    val Pulse =
        CanimationEffect(
            scale = CanimationRange(1f, 1.05f),
            durationMs = 500,
        )

    /** Heartbeat double-pulse. */
    val HeartBeat =
        CanimationEffect(
            scale = CanimationRange(1f, 1.15f),
            durationMs = 600,
        )

    /** Tada celebration with rotation. */
    val Tada =
        CanimationEffect(
            scale = CanimationRange(1f, 1.1f),
            rotation = CanimationRange(0f, -3f),
            durationMs = 500,
        )

    /** Wobble side-to-side. */
    val Wobble =
        CanimationEffect(
            rotation = CanimationRange(0f, -5f),
            offsetX = CanimationDpRange(0.dp, (-4).dp),
            durationMs = 500,
        )

    /** Swing pendulum. */
    val Swing =
        CanimationEffect(
            rotation = CanimationRange(0f, 15f),
            durationMs = 400,
        )

    /** Rubber band stretch. */
    val RubberBand =
        CanimationEffect(
            scale = CanimationRange(1f, 1.25f),
            durationMs = 400,
        )

    /** Jello wiggle. */
    val Jello =
        CanimationEffect(
            rotation = CanimationRange(0f, -8f),
            durationMs = 500,
        )

    /** Flash blink. */
    val Flash =
        CanimationEffect(
            alpha = CanimationRange(1f, 0f),
            durationMs = 250,
        )

    /** Horizontal shake. */
    val ShakeX =
        CanimationEffect(
            offsetX = CanimationDpRange(0.dp, (-10).dp),
            durationMs = 400,
        )

    /** Vertical shake. */
    val ShakeY =
        CanimationEffect(
            offsetY = CanimationDpRange(0.dp, (-10).dp),
            durationMs = 400,
        )

    /** Head shake (subtle horizontal). */
    val HeadShake =
        CanimationEffect(
            offsetX = CanimationDpRange(0.dp, (-6).dp),
            rotation = CanimationRange(0f, -3f),
            durationMs = 500,
        )

    /** Gentle glow/throb. */
    val Glow =
        CanimationEffect(
            alpha = CanimationRange(0.7f, 1f),
            scale = CanimationRange(0.98f, 1.02f),
            durationMs = 800,
        )

    /** Ring/bell shake. */
    val Ring =
        CanimationEffect(
            rotation = CanimationRange(0f, 20f),
            durationMs = 300,
        )
}

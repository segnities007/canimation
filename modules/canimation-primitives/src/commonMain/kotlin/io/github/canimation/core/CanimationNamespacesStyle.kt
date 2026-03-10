package io.github.canimation.core

import androidx.compose.ui.unit.dp

/** Wave-like motion effects. */
object CanimationWave {
    val Gentle =
        CanimationEffect(
            offsetY = CanimationDpRange(6.dp, 0.dp),
            rotation = CanimationRange(-3f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )
    val Strong =
        CanimationEffect(
            offsetY = CanimationDpRange(20.dp, 0.dp),
            rotation = CanimationRange(-8f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 600,
        )
    val Ripple = CanimationEffect(scale = CanimationRange(0.9f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 400)
    val Float = CanimationEffect(offsetY = CanimationDpRange(12.dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 700)
    val Drift =
        CanimationEffect(
            offsetX = CanimationDpRange(10.dp, 0.dp),
            offsetY = CanimationDpRange(6.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )
}

/** Digital glitch effects. */
object CanimationGlitch {
    val In = CanimationEffect(offsetX = CanimationDpRange((-4).dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 150)
    val Shake =
        CanimationEffect(
            offsetX = CanimationDpRange(8.dp, 0.dp),
            rotation = CanimationRange(2f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 200,
        )
    val Flicker = CanimationEffect(alpha = CanimationRange(0.3f, 1f), scale = CanimationRange(1.02f, 1f), durationMs = 180)
    val Distort =
        CanimationEffect(
            offsetX = CanimationDpRange(6.dp, 0.dp),
            scale = CanimationRange(1.05f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 250,
        )
}

/** Elastic spring-like effects. */
object CanimationElastic {
    val In = CanimationEffect(scale = CanimationRange(0.6f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 500)
    val Stretch =
        CanimationEffect(
            scale = CanimationRange(0.7f, 1f),
            offsetY = CanimationDpRange(30.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 600,
        )
    val Squash = CanimationEffect(scale = CanimationRange(1.3f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 400)
    val Snap =
        CanimationEffect(
            scale = CanimationRange(0.5f, 1f),
            rotation = CanimationRange(-5f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
    val Wobble =
        CanimationEffect(
            scale = CanimationRange(0.85f, 1f),
            rotation = CanimationRange(3f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 450,
        )
}

/** Cinematic film-inspired effects. */
object CanimationCinematic {
    val Curtain = CanimationEffect(offsetY = CanimationDpRange((-40).dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 600)
    val ZoomPan =
        CanimationEffect(
            scale = CanimationRange(1.5f, 1f),
            offsetX = CanimationDpRange(30.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 700,
        )
    val Dolly = CanimationEffect(scale = CanimationRange(0.3f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 800)
    val Reveal =
        CanimationEffect(
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.95f, 1f),
            offsetY = CanimationDpRange(10.dp, 0.dp),
            durationMs = 500,
        )
    val FadeToBlack = CanimationEffect(alpha = CanimationRange(0f, 1f), durationMs = 600)
    val Dramatic =
        CanimationEffect(
            scale = CanimationRange(0.4f, 1f),
            rotation = CanimationRange(-10f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 700,
        )
}

/** Playful fun effects. */
object CanimationPlayful {
    val Wiggle =
        CanimationEffect(
            rotation = CanimationRange(-12f, 0f),
            scale = CanimationRange(0.9f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
    val Hop =
        CanimationEffect(
            offsetY = CanimationDpRange((-20).dp, 0.dp),
            scale = CanimationRange(0.85f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
    val Spin =
        CanimationEffect(
            rotation = CanimationRange(-180f, 0f),
            scale = CanimationRange(0.7f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )
    val Pop = CanimationEffect(scale = CanimationRange(0.3f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 300)
    val Twirl =
        CanimationEffect(
            rotation = CanimationRange(-90f, 0f),
            offsetY = CanimationDpRange(15.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 450,
        )
    val Boing =
        CanimationEffect(
            scale = CanimationRange(0.5f, 1f),
            offsetY = CanimationDpRange(10.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
}

/** Stagger helper for building delayed animation sequences. */
object CanimationStagger {
    /** Quick stagger delay (40ms per item). */
    const val Quick = 40

    /** Normal stagger delay (70ms per item). */
    const val Normal = 70

    /** Slow stagger delay (120ms per item). */
    const val Slow = 120

    /** Very slow stagger delay (200ms per item). */
    const val Relaxed = 200
}

/** Diagonal movement animations combining X and Y offset. */
object CanimationDiagonal {
    val TopLeft =
        CanimationEffect(
            offsetX = CanimationDpRange((-30).dp, 0.dp),
            offsetY = CanimationDpRange((-30).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
    val TopRight =
        CanimationEffect(
            offsetX = CanimationDpRange(30.dp, 0.dp),
            offsetY = CanimationDpRange((-30).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
    val BottomLeft =
        CanimationEffect(
            offsetX = CanimationDpRange((-30).dp, 0.dp),
            offsetY = CanimationDpRange(30.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
    val BottomRight =
        CanimationEffect(
            offsetX = CanimationDpRange(30.dp, 0.dp),
            offsetY = CanimationDpRange(30.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
    val Subtle =
        CanimationEffect(
            offsetX = CanimationDpRange((-12).dp, 0.dp),
            offsetY = CanimationDpRange((-12).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 250,
        )
}

/** Shrink-based exit animations (reverse of Zoom/Scale). */
object CanimationShrink {
    val Out = CanimationEffect(scale = CanimationRange(1.3f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 300)
    val Subtle = CanimationEffect(scale = CanimationRange(1.1f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 200)
    val Rotate =
        CanimationEffect(
            scale = CanimationRange(1.4f, 1f),
            rotation = CanimationRange(15f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
    val FadeDown =
        CanimationEffect(
            scale = CanimationRange(1.2f, 1f),
            offsetY = CanimationDpRange((-10).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
}

/** Skew / tilt entry animations. */
object CanimationTilt {
    val Left =
        CanimationEffect(
            rotation = CanimationRange(-8f, 0f),
            offsetX = CanimationDpRange((-20).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
    val Right =
        CanimationEffect(
            rotation = CanimationRange(8f, 0f),
            offsetX = CanimationDpRange(20.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
    val Up =
        CanimationEffect(
            rotation = CanimationRange(-5f, 0f),
            offsetY = CanimationDpRange((-15).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
    val Down =
        CanimationEffect(
            rotation = CanimationRange(5f, 0f),
            offsetY = CanimationDpRange(15.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
    val Swing =
        CanimationEffect(
            rotation = CanimationRange(-15f, 0f),
            scale = CanimationRange(0.9f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
}

/** Float / levitate animations with gentle vertical motion. */
object CanimationFloat {
    val Up = CanimationEffect(offsetY = CanimationDpRange(8.dp, 0.dp), alpha = CanimationRange(0.5f, 1f), durationMs = 500)
    val Down = CanimationEffect(offsetY = CanimationDpRange((-8).dp, 0.dp), alpha = CanimationRange(0.5f, 1f), durationMs = 500)
    val Gentle = CanimationEffect(offsetY = CanimationDpRange(4.dp, 0.dp), alpha = CanimationRange(0.7f, 1f), durationMs = 600)
    val ScaleUp =
        CanimationEffect(
            offsetY = CanimationDpRange(6.dp, 0.dp),
            scale = CanimationRange(0.97f, 1f),
            alpha = CanimationRange(0.6f, 1f),
            durationMs = 450,
        )
}

/** Drop / gravity-like animations from above. */
object CanimationDrop {
    val In =
        CanimationEffect(
            offsetY = CanimationDpRange((-50).dp, 0.dp),
            scale = CanimationRange(0.8f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
    val Heavy =
        CanimationEffect(
            offsetY = CanimationDpRange((-80).dp, 0.dp),
            scale = CanimationRange(1.1f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )
    val Light = CanimationEffect(offsetY = CanimationDpRange((-20).dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 300)
    val Rotate =
        CanimationEffect(
            offsetY = CanimationDpRange((-40).dp, 0.dp),
            rotation = CanimationRange(-20f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 450,
        )
}

/** Rise / ascend animations from below. */
object CanimationRise {
    val In =
        CanimationEffect(
            offsetY = CanimationDpRange(50.dp, 0.dp),
            scale = CanimationRange(0.8f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
    val Slow = CanimationEffect(offsetY = CanimationDpRange(30.dp, 0.dp), alpha = CanimationRange(0f, 1f), durationMs = 600)
    val Scale =
        CanimationEffect(
            offsetY = CanimationDpRange(40.dp, 0.dp),
            scale = CanimationRange(0.7f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 450,
        )
    val Rotate =
        CanimationEffect(
            offsetY = CanimationDpRange(35.dp, 0.dp),
            rotation = CanimationRange(10f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )
}

/** Stretch-based deformation animations. */
object CanimationStretch {
    val Horizontal =
        CanimationEffect(
            scale = CanimationRange(0.6f, 1f),
            offsetX = CanimationDpRange((-20).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
    val Vertical =
        CanimationEffect(
            scale = CanimationRange(0.6f, 1f),
            offsetY = CanimationDpRange((-20).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
    val Both = CanimationEffect(scale = CanimationRange(0.5f, 1f), alpha = CanimationRange(0f, 1f), durationMs = 400)
    val Snap =
        CanimationEffect(
            scale = CanimationRange(0.4f, 1f),
            rotation = CanimationRange(-5f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
}

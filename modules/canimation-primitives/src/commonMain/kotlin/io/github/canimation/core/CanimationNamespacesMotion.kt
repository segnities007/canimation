package io.github.canimation.core

import androidx.compose.ui.unit.dp

/** Dramatic entrance animations. */
object CanimationEntrance {
    /** Elevate from below with scale. */
    val Elevate =
        CanimationEffect(
            offsetY = CanimationDpRange(24.dp, 0.dp),
            scale = CanimationRange(0.95f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )

    /** Drop from above. */
    val Drop =
        CanimationEffect(
            offsetY = CanimationDpRange((-30).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )

    /** Jack in the box spring-up. */
    val JackInTheBox =
        CanimationEffect(
            scale = CanimationRange(0.1f, 1f),
            rotation = CanimationRange(30f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )

    /** Roll in from the side. */
    val RollIn =
        CanimationEffect(
            offsetX = CanimationDpRange((-60).dp, 0.dp),
            rotation = CanimationRange(-120f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )

    /** Light speed from left. */
    val LightSpeedLeft =
        CanimationEffect(
            offsetX = CanimationDpRange((-80).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(-10f, 0f),
            durationMs = 400,
        )

    /** Light speed from right. */
    val LightSpeedRight =
        CanimationEffect(
            offsetX = CanimationDpRange(80.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(10f, 0f),
            durationMs = 400,
        )

    /** Back in from above. */
    val BackInUp =
        CanimationEffect(
            offsetY = CanimationDpRange((-40).dp, 0.dp),
            scale = CanimationRange(0.7f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )

    /** Back in from below. */
    val BackInDown =
        CanimationEffect(
            offsetY = CanimationDpRange(40.dp, 0.dp),
            scale = CanimationRange(0.7f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )

    /** Back in from left. */
    val BackInLeft =
        CanimationEffect(
            offsetX = CanimationDpRange((-40).dp, 0.dp),
            scale = CanimationRange(0.7f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )

    /** Back in from right. */
    val BackInRight =
        CanimationEffect(
            offsetX = CanimationDpRange(40.dp, 0.dp),
            scale = CanimationRange(0.7f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )

    /** Shrink from large. */
    val ShrinkIn =
        CanimationEffect(
            scale = CanimationRange(1.5f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )

    /** Tilt entry with rotation. */
    val TiltIn =
        CanimationEffect(
            rotation = CanimationRange(-20f, 0f),
            scale = CanimationRange(0.9f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )

    /** Snap appearance. */
    val Snap =
        CanimationEffect(
            scale = CanimationRange(0.5f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 150,
        )

    /** Swing from hinge. */
    val SwingIn =
        CanimationEffect(
            rotation = CanimationRange(-60f, 0f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )

    /** Unfold/expand appearance. */
    val Unfold =
        CanimationEffect(
            scale = CanimationRange(0f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )

    /** Rise from below with parallax feel. */
    val Rise =
        CanimationEffect(
            offsetY = CanimationDpRange(60.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.98f, 1f),
            durationMs = 500,
        )

    /** Emerge from center with scale. */
    val Emerge =
        CanimationEffect(
            scale = CanimationRange(0.3f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
}

/** Material Design motion patterns. */
object CanimationMaterial {
    /** Fade through (crossfade with scale). */
    val FadeThrough =
        CanimationEffect(
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.92f, 1f),
            durationMs = 300,
        )

    /** Shared axis X (horizontal transition). */
    val SharedAxisX =
        CanimationEffect(
            offsetX = CanimationDpRange(30.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )

    /** Shared axis Y (vertical transition). */
    val SharedAxisY =
        CanimationEffect(
            offsetY = CanimationDpRange(30.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )

    /** Shared axis Z (depth transition). */
    val SharedAxisZ =
        CanimationEffect(
            scale = CanimationRange(0.8f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )

    /** Emphasized entry (larger scale). */
    val Emphasized =
        CanimationEffect(
            offsetY = CanimationDpRange(40.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )

    /** Container transform hint. */
    val ContainerTransform =
        CanimationEffect(
            scale = CanimationRange(0.85f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )
}

/** Morph/transform animations. */
object CanimationMorph {
    /** Scale morph from small. */
    val ScaleUp =
        CanimationEffect(
            scale = CanimationRange(0.5f, 1f),
            alpha = CanimationRange(0.5f, 1f),
            durationMs = 400,
        )

    /** Expand morph with offset. */
    val Expand =
        CanimationEffect(
            scale = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange((-20).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 500,
        )

    /** Collapse morph. */
    val Collapse =
        CanimationEffect(
            scale = CanimationRange(1.2f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )
}

/** Blur-like fade effects using opacity + scale. */
object CanimationBlur {
    /** Blur fade in — scale from large with fade. */
    val In =
        CanimationEffect(
            scale = CanimationRange(1.15f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )

    /** Blur zoom out — scale from small with fade. */
    val Out =
        CanimationEffect(
            scale = CanimationRange(0.85f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 350,
        )

    /** Soft focus — gentle scale with slow fade. */
    val Soft =
        CanimationEffect(
            scale = CanimationRange(1.05f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 600,
        )
}

/** Swipe / fling style animations. */
object CanimationSwipe {
    /** Swipe from left edge (large offset). */
    val Left =
        CanimationEffect(
            offsetX = CanimationDpRange((-100).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )

    /** Swipe from right edge. */
    val Right =
        CanimationEffect(
            offsetX = CanimationDpRange(100.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )

    /** Swipe up from bottom edge. */
    val Up =
        CanimationEffect(
            offsetY = CanimationDpRange(100.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )

    /** Swipe down from top edge. */
    val Down =
        CanimationEffect(
            offsetY = CanimationDpRange((-100).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 300,
        )

    /** Fling in from right with overshoot feel. */
    val FlingRight =
        CanimationEffect(
            offsetX = CanimationDpRange(200.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 250,
        )

    /** Fling in from left with overshoot feel. */
    val FlingLeft =
        CanimationEffect(
            offsetX = CanimationDpRange((-200).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            durationMs = 250,
        )
}

/** Reveal / uncover patterns. */
object CanimationReveal {
    /** Scale up from center with fade. */
    val Center =
        CanimationEffect(
            scale = CanimationRange(0f, 1f),
            alpha = CanimationRange(0f, 1f),
            durationMs = 400,
        )

    /** Reveal from top — slide down into view. */
    val Top =
        CanimationEffect(
            offsetY = CanimationDpRange((-50).dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.9f, 1f),
            durationMs = 400,
        )

    /** Reveal from bottom — slide up into view. */
    val Bottom =
        CanimationEffect(
            offsetY = CanimationDpRange(50.dp, 0.dp),
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.9f, 1f),
            durationMs = 400,
        )

    /** Dramatic reveal — large scale and fade. */
    val Dramatic =
        CanimationEffect(
            scale = CanimationRange(0f, 1f),
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(15f, 0f),
            durationMs = 600,
        )
}

/** Subtle, micro-interaction animations. */
object CanimationMicro {
    /** Tiny nudge up (4dp). */
    val NudgeUp =
        CanimationEffect(
            offsetY = CanimationDpRange(4.dp, 0.dp),
            alpha = CanimationRange(0.8f, 1f),
            durationMs = 150,
        )

    /** Tiny nudge down. */
    val NudgeDown =
        CanimationEffect(
            offsetY = CanimationDpRange((-4).dp, 0.dp),
            alpha = CanimationRange(0.8f, 1f),
            durationMs = 150,
        )

    /** Micro scale pulse. */
    val Pulse =
        CanimationEffect(
            scale = CanimationRange(0.96f, 1f),
            durationMs = DurationTokens.Default.shortMs,
        )

    /** Micro rotation wiggle. */
    val Wiggle =
        CanimationEffect(
            rotation = CanimationRange(3f, 0f),
            durationMs = 200,
        )

    /** Touch feedback — slight shrink. */
    val Press =
        CanimationEffect(
            scale = CanimationRange(0.95f, 1f),
            durationMs = 100,
        )
}

/** Page transition patterns. */
object CanimationPage {
    /** iOS-style push from right. */
    val PushRight =
        CanimationEffect(
            offsetX = CanimationDpRange(300.dp, 0.dp),
            alpha = CanimationRange(0.5f, 1f),
            durationMs = 350,
        )

    /** iOS-style push from left. */
    val PushLeft =
        CanimationEffect(
            offsetX = CanimationDpRange((-300).dp, 0.dp),
            alpha = CanimationRange(0.5f, 1f),
            durationMs = 350,
        )

    /** Android-style bottom-up sheet. */
    val BottomSheet =
        CanimationEffect(
            offsetY = CanimationDpRange(400.dp, 0.dp),
            alpha = CanimationRange(0.5f, 1f),
            durationMs = 350,
        )

    /** Cover from bottom (modal). */
    val CoverUp =
        CanimationEffect(
            offsetY = CanimationDpRange(600.dp, 0.dp),
            durationMs = 400,
        )

    /** Crossfade transition. */
    val Crossfade =
        CanimationEffect(
            alpha = CanimationRange(0f, 1f),
            durationMs = 250,
        )
}

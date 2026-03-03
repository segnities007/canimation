package io.github.canimation.core

/**
 * Built-in animation presets providing common enter/exit/transition patterns.
 */
enum class CanimationPreset {
    /** Fade in with upward slide. */
    FadeUp,

    /** Simple fade (alpha only). */
    Fade,

    /** Scale up from slightly smaller. */
    ScaleIn,

    /** Slide in from the right side (leftward movement). */
    SlideLeft,

    /** Slide in from the left side (rightward movement). */
    SlideRight,

    /** Fade in with downward slide. */
    FadeDown,

    /** Scale down from slightly larger. */
    ScaleUp,

    /** Dramatic zoom in from half size. */
    ZoomIn,

    /** Zoom in from larger than normal. */
    ZoomOut,

    /** Pop in from slightly larger with fade. */
    Pop,

    /** Expand from nothing (scale 0→1). */
    Expand,

    /** Slide up from a large offset. */
    SlideUp,

    /** Slide down from a large offset. */
    SlideDown,

    /** Subtle elevation rise: tiny scale + offset + fade. */
    ElevateIn,

    /** Drop in from above with scale. */
    DropIn,

    /** Counterclockwise rotation entry with fade. Inspired by Motion.dev rotate animations. */
    RotateIn,

    /** Clockwise rotation entry with fade. */
    RotateClockwise,

    /** Full 360° spin with scale and fade. */
    SpinIn,

    /** Half-rotation flip entry with fade. */
    FlipIn,

    /** Subtle rotation swing from corner with slide. */
    SwingIn,

    /** Zoom from small size + upward slide. Inspired by Motion.dev zoom variants. */
    ZoomInUp,

    /** Zoom from small size + downward slide. */
    ZoomInDown,

    /** Zoom from small size + left slide. */
    ZoomInLeft,

    /** Zoom from small size + right slide. */
    ZoomInRight,

    /** Overshoot entry from bottom. Uses Motion.dev backOut easing. */
    BackInUp,

    /** Overshoot entry from top. Uses Motion.dev backOut easing. */
    BackInDown,

    /** Scale down from very large to normal. */
    ShrinkIn,

    /** Long, gentle fade with no movement. */
    GentleFade,

    /** Near-instant cut transition (10ms). */
    Snap,

    // --- Animate.css inspired presets ---

    /** Bouncy scale entry with overshoot. Inspired by Animate.css bounceIn. */
    BounceIn,

    /** Bounce entry from top. Inspired by Animate.css bounceInDown. */
    BounceInDown,

    /** Bounce entry from left. Inspired by Animate.css bounceInLeft. */
    BounceInLeft,

    /** Bounce entry from right. Inspired by Animate.css bounceInRight. */
    BounceInRight,

    /** Large slide from left with fade. Inspired by Animate.css fadeInLeftBig. */
    FadeInLeftBig,

    /** Large slide from right with fade. Inspired by Animate.css fadeInRightBig. */
    FadeInRightBig,

    /** Fast slide from right with slight rotation. Inspired by Animate.css lightSpeedInRight. */
    LightSpeedInRight,

    /** Fast slide from left with slight rotation. Inspired by Animate.css lightSpeedInLeft. */
    LightSpeedInLeft,

    /** Scale + rotation combo entry. Inspired by Animate.css jackInTheBox. */
    JackInTheBox,

    /** Rolling rotation + slide from left. Inspired by Animate.css rollIn. */
    RollIn,

    // --- Framer Motion inspired presets ---

    /** Spring overshoot scale entry. Inspired by Framer Motion spring animations. */
    SpringIn,

    /** Spring slide from below with overshoot. Inspired by Framer Motion spring type. */
    SpringSlideUp,

    /** Spring fade with subtle scale overshoot. Inspired by Framer Motion spring transitions. */
    SpringFadeIn,

    // --- AnimXYZ inspired presets ---

    /** Rotation + upward slide entry. Inspired by AnimXYZ flip-up composable. */
    FlipUp,

    /** Rotation + downward slide entry. Inspired by AnimXYZ flip-down composable. */
    FlipDown,

    /** Slight tilt rotation + scale entry. Inspired by AnimXYZ rotate + small composable. */
    TiltIn,

    // --- Material Motion inspired presets ---

    /** Material fade-through pattern: fade + subtle scale. Inspired by Material Motion patterns. */
    FadeThrough,

    /** Horizontal shared axis transition. Inspired by Material Motion shared axis X. */
    SharedAxisX,

    /** Vertical shared axis transition. Inspired by Material Motion shared axis Y. */
    SharedAxisY,

    /** Material emphasized decelerate entry. Inspired by Material Motion emphasized easing. */
    EmphasizedEntry,

    // --- Animate.css missing directional presets ---

    /** Overshoot entry from left. Inspired by Animate.css backInLeft. */
    BackInLeft,

    /** Overshoot entry from right. Inspired by Animate.css backInRight. */
    BackInRight,

    /** Bounce entry from bottom. Inspired by Animate.css bounceInUp. */
    BounceInUp,

    /** Large slide down with fade. Inspired by Animate.css fadeInDownBig. */
    FadeInDownBig,

    /** Large slide up with fade. Inspired by Animate.css fadeInUpBig. */
    FadeInUpBig,

    /** Fade in from left. Inspired by Animate.css fadeInLeft. */
    FadeInLeft,

    /** Fade in from right. Inspired by Animate.css fadeInRight. */
    FadeInRight,

    /** Diagonal slide from top-left corner. Inspired by Animate.css fadeInTopLeft. */
    FadeInTopLeft,

    /** Diagonal slide from top-right corner. Inspired by Animate.css fadeInTopRight. */
    FadeInTopRight,

    /** Diagonal slide from bottom-left corner. Inspired by Animate.css fadeInBottomLeft. */
    FadeInBottomLeft,

    /** Diagonal slide from bottom-right corner. Inspired by Animate.css fadeInBottomRight. */
    FadeInBottomRight,

    /** Rotate + downward-left entry. Inspired by Animate.css rotateInDownLeft. */
    RotateInDownLeft,

    /** Rotate + downward-right entry. Inspired by Animate.css rotateInDownRight. */
    RotateInDownRight,

    /** Rotate + upward-left entry. Inspired by Animate.css rotateInUpLeft. */
    RotateInUpLeft,

    /** Rotate + upward-right entry. Inspired by Animate.css rotateInUpRight. */
    RotateInUpRight,

    /** Vertical flip entry (Y-axis approximation). Inspired by Animate.css flipInY. */
    FlipInY,

    // --- Animate.css attention seekers ---

    /** Subtle scale pulse emphasis. Inspired by Animate.css pulse. */
    Pulse,

    /** Larger heartbeat-like scale pulse. Inspired by Animate.css heartBeat. */
    HeartBeat,

    /** Rotation + scale attention effect. Inspired by Animate.css tada. */
    Tada,

    /** Side-to-side wobble with rotation. Inspired by Animate.css wobble. */
    Wobble,

    /** Pendulum swing rotation. Inspired by Animate.css swing. */
    Swing,

    /** Scale overshoot bounce. Inspired by Animate.css rubberBand. */
    RubberBand,

    /** Vertical bounce with scale. Inspired by Animate.css bounce. */
    Bounce,

    /** Quick opacity flash. Inspired by Animate.css flash. */
    Flash,

    /** Horizontal shake offset. Inspired by Animate.css shakeX. */
    ShakeX,

    /** Vertical shake offset. Inspired by Animate.css shakeY. */
    ShakeY,

    /** Subtle head-shake rotation. Inspired by Animate.css headShake. */
    HeadShake,

    /** Skew-like rotation distortion. Inspired by Animate.css jello. */
    Jello,

    // --- AnimXYZ composable combinations ---

    /** Fade + small scale entry. Inspired by AnimXYZ "fade small" composable. */
    FadeSmall,

    /** Fade + big scale entry. Inspired by AnimXYZ "fade big" composable. */
    FadeBig,

    /** Diagonal fade from top-left. Inspired by AnimXYZ "fade up left" composable. */
    FadeUpLeft,

    /** Diagonal fade from bottom-right. Inspired by AnimXYZ "fade down right" composable. */
    FadeDownRight,

    /** Rotation + scale combo. Inspired by AnimXYZ "rotate small" composable. */
    RotateScale,

    /** Large upward translate entry. Inspired by AnimXYZ "up-5" (100%) utility. */
    UpBig,
}

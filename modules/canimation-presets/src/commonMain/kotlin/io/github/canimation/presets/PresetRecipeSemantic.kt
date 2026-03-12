package io.github.canimation.presets

import io.github.canimation.core.CanimationIntensity
import io.github.canimation.core.CanimationIntent
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationRecipeSemantic
import io.github.canimation.core.CanimationSurfaceRole

internal fun CanimationPreset.toSemantic(): CanimationRecipeSemantic {
    val intent = intentForPreset()
    val family = familyName()
    return CanimationRecipeSemantic(
        intent = intent,
        surfaceRole = intent.defaultSurfaceRole(),
        intensity = intent.defaultIntensity(),
        family = family,
        tags = setOf(intent.name, family),
    )
}

private fun CanimationPreset.intentForPreset(): CanimationIntent =
    when (this) {
        CanimationPreset.Pulse,
        CanimationPreset.HeartBeat,
        CanimationPreset.Tada,
        CanimationPreset.Wobble,
        CanimationPreset.Swing,
        CanimationPreset.RubberBand,
        CanimationPreset.Bounce,
        CanimationPreset.Flash,
        CanimationPreset.Jello,
        CanimationPreset.Snap,
        -> CanimationIntent.Feedback

        CanimationPreset.ShakeX,
        CanimationPreset.ShakeY,
        CanimationPreset.HeadShake,
        -> CanimationIntent.Recovery

        CanimationPreset.FadeThrough,
        CanimationPreset.SharedAxisX,
        CanimationPreset.SharedAxisY,
        CanimationPreset.EmphasizedEntry,
        -> CanimationIntent.Transition

        CanimationPreset.DropIn,
        CanimationPreset.ElevateIn,
        CanimationPreset.FlipIn,
        CanimationPreset.FlipInY,
        CanimationPreset.FlipUp,
        CanimationPreset.FlipDown,
        -> CanimationIntent.Surface

        CanimationPreset.LightSpeedInLeft,
        CanimationPreset.LightSpeedInRight,
        CanimationPreset.JackInTheBox,
        CanimationPreset.RollIn,
        -> CanimationIntent.Experimental

        else -> CanimationIntent.Content
    }

private fun CanimationIntent.defaultSurfaceRole(): CanimationSurfaceRole =
    when (this) {
        CanimationIntent.Surface -> CanimationSurfaceRole.Overlay
        CanimationIntent.Transition -> CanimationSurfaceRole.Page
        CanimationIntent.Feedback,
        CanimationIntent.Recovery,
        -> CanimationSurfaceRole.Control

        else -> CanimationSurfaceRole.Container
    }

private fun CanimationIntent.defaultIntensity(): CanimationIntensity =
    when (this) {
        CanimationIntent.Feedback -> CanimationIntensity.Subtle
        CanimationIntent.Recovery,
        CanimationIntent.Surface,
        CanimationIntent.Experimental,
        -> CanimationIntensity.Strong

        else -> CanimationIntensity.Standard
    }

internal fun CanimationPreset.familyName(): String =
    when (this) {
        CanimationPreset.Fade,
        CanimationPreset.FadeUp,
        CanimationPreset.FadeDown,
        CanimationPreset.FadeInLeft,
        CanimationPreset.FadeInRight,
        CanimationPreset.FadeInLeftBig,
        CanimationPreset.FadeInRightBig,
        CanimationPreset.FadeInDownBig,
        CanimationPreset.FadeInUpBig,
        CanimationPreset.FadeInTopLeft,
        CanimationPreset.FadeInTopRight,
        CanimationPreset.FadeInBottomLeft,
        CanimationPreset.FadeInBottomRight,
        CanimationPreset.GentleFade,
        CanimationPreset.FadeSmall,
        CanimationPreset.FadeBig,
        CanimationPreset.FadeUpLeft,
        CanimationPreset.FadeDownRight,
        -> "Fade"

        CanimationPreset.ScaleIn,
        CanimationPreset.ScaleUp,
        CanimationPreset.Pop,
        CanimationPreset.Expand,
        CanimationPreset.ShrinkIn,
        -> "Scale"

        CanimationPreset.SlideLeft,
        CanimationPreset.SlideRight,
        CanimationPreset.SlideUp,
        CanimationPreset.SlideDown,
        CanimationPreset.ZoomInUp,
        CanimationPreset.ZoomInDown,
        CanimationPreset.ZoomInLeft,
        CanimationPreset.ZoomInRight,
        CanimationPreset.UpBig,
        -> "Slide"

        CanimationPreset.ZoomIn,
        CanimationPreset.ZoomOut,
        -> "Zoom"

        CanimationPreset.RotateIn,
        CanimationPreset.RotateClockwise,
        CanimationPreset.RotateInDownLeft,
        CanimationPreset.RotateInDownRight,
        CanimationPreset.RotateInUpLeft,
        CanimationPreset.RotateInUpRight,
        CanimationPreset.RotateScale,
        CanimationPreset.SpinIn,
        -> "Rotate"

        CanimationPreset.Pulse,
        CanimationPreset.HeartBeat,
        CanimationPreset.Tada,
        CanimationPreset.Wobble,
        CanimationPreset.Swing,
        CanimationPreset.RubberBand,
        CanimationPreset.Bounce,
        CanimationPreset.Flash,
        CanimationPreset.ShakeX,
        CanimationPreset.ShakeY,
        CanimationPreset.HeadShake,
        CanimationPreset.Jello,
        -> "Attention"

        CanimationPreset.FadeThrough,
        CanimationPreset.SharedAxisX,
        CanimationPreset.SharedAxisY,
        CanimationPreset.EmphasizedEntry,
        -> "Transition"

        CanimationPreset.DropIn,
        CanimationPreset.ElevateIn,
        -> "Surface"

        else -> "Motion"
    }

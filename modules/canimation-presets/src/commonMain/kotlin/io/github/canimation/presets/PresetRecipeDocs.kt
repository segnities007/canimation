package io.github.canimation.presets

import io.github.canimation.core.CanimationIntent
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationRecipeDocs

internal fun CanimationPreset.toDocs(intent: CanimationIntent): CanimationRecipeDocs =
    CanimationRecipeDocs(
        title = name,
        summary = legacyPresetSummary(),
        usageGuidance =
            when (intent) {
                CanimationIntent.Content -> "Use for standard content entry and exit where clarity matters more than spectacle."
                CanimationIntent.Surface -> "Use for overlays and surfaced UI that should feel spatially distinct from background content."
                CanimationIntent.Transition -> "Use for page or state changes that need directional or hierarchical continuity."
                CanimationIntent.Feedback -> "Use for short interaction feedback, not for long-running ambient motion."
                CanimationIntent.Recovery -> "Use to communicate recoverable error, warning, or corrective state changes."
                CanimationIntent.Experimental -> "Use sparingly in showcase or opt-in experiences, not as a stable default."
                else -> "Prefer this only when the motion meaning matches the UI intent."
            },
        doNotUseWhen =
            if (intent == CanimationIntent.Experimental) {
                "Avoid as a default recipe for repeated content rendering or accessibility-sensitive flows."
            } else {
                null
            },
    )

internal fun CanimationPreset.legacyPresetSummary(): String =
    when (this) {
        CanimationPreset.FadeUp -> "Fade + slide up"
        CanimationPreset.Fade -> "Alpha crossfade"
        CanimationPreset.ScaleIn -> "Scale from 92%"
        CanimationPreset.SlideLeft -> "Slide from right"
        CanimationPreset.SlideRight -> "Slide from left"
        CanimationPreset.FadeDown -> "Fade + slide down"
        CanimationPreset.ScaleUp -> "Scale from 108%"
        CanimationPreset.ZoomIn -> "Zoom from 50%"
        CanimationPreset.ZoomOut -> "Zoom from 150%"
        CanimationPreset.Pop -> "Overshoot pop"
        CanimationPreset.Expand -> "Scale from 0%"
        CanimationPreset.SlideUp -> "Long slide up"
        CanimationPreset.SlideDown -> "Long slide down"
        CanimationPreset.ElevateIn -> "Subtle rise + scale"
        CanimationPreset.DropIn -> "Drop with bounce"
        CanimationPreset.RotateIn -> "CCW rotate entry"
        CanimationPreset.RotateClockwise -> "CW rotate entry"
        CanimationPreset.SpinIn -> "360 deg spin + scale"
        CanimationPreset.FlipIn -> "180 deg flip entry"
        CanimationPreset.SwingIn -> "Swing + slide"
        CanimationPreset.ZoomInUp -> "Zoom + upward"
        CanimationPreset.ZoomInDown -> "Zoom + downward"
        CanimationPreset.ZoomInLeft -> "Zoom + from left"
        CanimationPreset.ZoomInRight -> "Zoom + from right"
        CanimationPreset.BackInUp -> "Back easing up"
        CanimationPreset.BackInDown -> "Back easing down"
        CanimationPreset.ShrinkIn -> "Shrink from 200%"
        CanimationPreset.GentleFade -> "600ms gentle fade"
        CanimationPreset.Snap -> "Instant 10ms cut"
        CanimationPreset.BounceIn -> "Bouncy scale entry"
        CanimationPreset.BounceInDown -> "Bounce from top"
        CanimationPreset.BounceInLeft -> "Bounce from left"
        CanimationPreset.BounceInRight -> "Bounce from right"
        CanimationPreset.FadeInLeftBig -> "Big slide from left"
        CanimationPreset.FadeInRightBig -> "Big slide from right"
        CanimationPreset.LightSpeedInRight -> "Fast slide + tilt"
        CanimationPreset.LightSpeedInLeft -> "Fast slide left + tilt"
        CanimationPreset.JackInTheBox -> "Scale + rotate combo"
        CanimationPreset.RollIn -> "Roll + slide left"
        CanimationPreset.SpringIn -> "Spring overshoot scale"
        CanimationPreset.SpringSlideUp -> "Spring slide up"
        CanimationPreset.SpringFadeIn -> "Spring fade + scale"
        CanimationPreset.FlipUp -> "Flip + upward slide"
        CanimationPreset.FlipDown -> "Flip + downward slide"
        CanimationPreset.TiltIn -> "Tilt + scale entry"
        CanimationPreset.FadeThrough -> "Material fade-through"
        CanimationPreset.SharedAxisX -> "Shared axis horizontal"
        CanimationPreset.SharedAxisY -> "Shared axis vertical"
        CanimationPreset.EmphasizedEntry -> "Emphasized decelerate"
        CanimationPreset.BackInLeft -> "Back ease from left"
        CanimationPreset.BackInRight -> "Back ease from right"
        CanimationPreset.BounceInUp -> "Bounce from bottom"
        CanimationPreset.FadeInDownBig -> "Big fade from top"
        CanimationPreset.FadeInUpBig -> "Big fade from bottom"
        CanimationPreset.FadeInLeft -> "Fade from left"
        CanimationPreset.FadeInRight -> "Fade from right"
        CanimationPreset.FadeInTopLeft -> "Diagonal top-left"
        CanimationPreset.FadeInTopRight -> "Diagonal top-right"
        CanimationPreset.FadeInBottomLeft -> "Diagonal bottom-left"
        CanimationPreset.FadeInBottomRight -> "Diagonal bottom-right"
        CanimationPreset.RotateInDownLeft -> "Rotate down-left"
        CanimationPreset.RotateInDownRight -> "Rotate down-right"
        CanimationPreset.RotateInUpLeft -> "Rotate up-left"
        CanimationPreset.RotateInUpRight -> "Rotate up-right"
        CanimationPreset.FlipInY -> "Vertical flip"
        CanimationPreset.Pulse -> "Scale pulse"
        CanimationPreset.HeartBeat -> "Heartbeat pulse"
        CanimationPreset.Tada -> "Tada emphasis"
        CanimationPreset.Wobble -> "Side wobble"
        CanimationPreset.Swing -> "Swing rotation"
        CanimationPreset.RubberBand -> "Rubber band stretch"
        CanimationPreset.Bounce -> "Bounce emphasis"
        CanimationPreset.Flash -> "Quick flash"
        CanimationPreset.ShakeX -> "Horizontal shake"
        CanimationPreset.ShakeY -> "Vertical shake"
        CanimationPreset.HeadShake -> "Head shake"
        CanimationPreset.Jello -> "Jello wobble"
        CanimationPreset.FadeSmall -> "Fade + shrink"
        CanimationPreset.FadeBig -> "Fade + grow"
        CanimationPreset.FadeUpLeft -> "Fade up-left"
        CanimationPreset.FadeDownRight -> "Fade down-right"
        CanimationPreset.RotateScale -> "Rotate + scale"
        CanimationPreset.UpBig -> "Big upward slide"
    }

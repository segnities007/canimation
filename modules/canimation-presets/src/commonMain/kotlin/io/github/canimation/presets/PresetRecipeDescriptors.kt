package io.github.canimation.presets

import io.github.canimation.core.CanimationCost
import io.github.canimation.core.CanimationIntensity
import io.github.canimation.core.CanimationIntent
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRecipeAccessibility
import io.github.canimation.core.CanimationRecipeDescriptor
import io.github.canimation.core.CanimationRecipeDocs
import io.github.canimation.core.CanimationRecipeId
import io.github.canimation.core.CanimationRecipePerformance
import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.CanimationRecipeSemantic
import io.github.canimation.core.CanimationRecipeSpecSet
import io.github.canimation.core.CanimationReducedStrategy
import io.github.canimation.core.CanimationSurfaceRole

internal val defaultPresetRecipeDescriptors: Map<CanimationPreset, CanimationRecipeDescriptor> =
    defaultPresetSpecs.mapValues { (preset, spec) ->
        preset.toRecipeDescriptor(spec)
    }

internal val defaultRecipeRegistry =
    CanimationRecipeRegistry(
        descriptors = defaultPresetRecipeDescriptors.values.associateBy { it.id },
    )

private fun CanimationPreset.toRecipeDescriptor(spec: CanimationPresetSpec): CanimationRecipeDescriptor {
    val semantic = toSemantic()
    val docs = toDocs()
    return CanimationRecipeDescriptor(
        id = CanimationRecipeId("preset.$name"),
        semantic = semantic,
        accessibility =
            CanimationRecipeAccessibility(
                reducedStrategy =
                    when (semantic.intent) {
                        CanimationIntent.Feedback -> CanimationReducedStrategy.FadeOnly
                        CanimationIntent.Experimental -> CanimationReducedStrategy.Alternative
                        else -> CanimationReducedStrategy.Compress
                    },
                supportsOff = true,
                notes = "Built-in preset compatibility descriptor.",
            ),
        performance =
            CanimationRecipePerformance(
                cost =
                    when {
                        spec.fullEnter.offsetX != null || spec.fullEnter.offsetY != null || spec.fullEnter.scale != null ||
                            spec.fullEnter.rotation != null -> CanimationCost.Transform
                        else -> CanimationCost.Draw
                    },
                listSafe = semantic.intent != CanimationIntent.Experimental,
                benchmarkRequired = false,
                notes = "Derived from preset compatibility spec.",
            ),
        docs = docs,
        specs =
            CanimationRecipeSpecSet(
                full = spec.fullEnter,
                reduced = spec.reducedEnter,
                off = spec.fullEnter.copy(durationMs = 0),
            ),
    )
}

private fun CanimationPreset.toSemantic(): CanimationRecipeSemantic {
    val intent =
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

    val surfaceRole =
        when (intent) {
            CanimationIntent.Surface -> CanimationSurfaceRole.Overlay
            CanimationIntent.Transition -> CanimationSurfaceRole.Page
            CanimationIntent.Feedback,
            CanimationIntent.Recovery,
            -> CanimationSurfaceRole.Control
            else -> CanimationSurfaceRole.Container
        }

    val intensity =
        when (intent) {
            CanimationIntent.Feedback -> CanimationIntensity.Subtle
            CanimationIntent.Recovery,
            CanimationIntent.Surface,
            CanimationIntent.Experimental,
            -> CanimationIntensity.Strong
            else -> CanimationIntensity.Standard
        }

    return CanimationRecipeSemantic(
        intent = intent,
        surfaceRole = surfaceRole,
        intensity = intensity,
        family = familyName(),
        tags = setOf(intent.name, familyName()),
    )
}

private fun CanimationPreset.familyName(): String =
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

private fun CanimationPreset.toDocs(): CanimationRecipeDocs =
    CanimationRecipeDocs(
        title = name,
        summary = legacyPresetSummary(),
        usageGuidance =
            when (toSemantic().intent) {
                CanimationIntent.Content -> "Use for standard content entry and exit where clarity matters more than spectacle."
                CanimationIntent.Surface -> "Use for overlays and surfaced UI that should feel spatially distinct from background content."
                CanimationIntent.Transition -> "Use for page or state changes that need directional or hierarchical continuity."
                CanimationIntent.Feedback -> "Use for short interaction feedback, not for long-running ambient motion."
                CanimationIntent.Recovery -> "Use to communicate recoverable error, warning, or corrective state changes."
                CanimationIntent.Experimental -> "Use sparingly in showcase or opt-in experiences, not as a stable default."
                else -> "Prefer this only when the motion meaning matches the UI intent."
            },
        doNotUseWhen =
            if (toSemantic().intent == CanimationIntent.Experimental) {
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

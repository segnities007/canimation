package com.segnities007.canimation.screen.examples

import io.github.canimation.core.CanimationPreset

data class ExampleCategory(
    val id: String,
    val title: String,
    val subtitle: String,
    val accentLabel: String,
    val examples: List<ExampleItem>,
)

data class ExampleItem(
    val preset: CanimationPreset,
    val description: String,
    val codeSnippet: String,
)

val exampleCategories: List<ExampleCategory> = listOf(
    ExampleCategory(
        id = "fade",
        title = "Fading",
        subtitle = "Smooth opacity transitions with optional movement",
        accentLabel = "ENTRANCE",
        examples = listOf(
            ExampleItem(
                CanimationPreset.Fade,
                "Simple alpha crossfade",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.Fade,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeUp,
                "Fade in with upward slide",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeUp,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeDown,
                "Fade in with downward slide",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeDown,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeInLeft,
                "Fade in from left",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeInLeft,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeInRight,
                "Fade in from right",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeInRight,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeInLeftBig,
                "Big slide from left with fade",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeInLeftBig,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeInRightBig,
                "Big slide from right with fade",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeInRightBig,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeInDownBig,
                "Big slide from top with fade",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeInDownBig,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeInUpBig,
                "Big slide from bottom with fade",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeInUpBig,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeInTopLeft,
                "Diagonal entry from top-left",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeInTopLeft,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeInTopRight,
                "Diagonal entry from top-right",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeInTopRight,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeInBottomLeft,
                "Diagonal entry from bottom-left",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeInBottomLeft,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeInBottomRight,
                "Diagonal entry from bottom-right",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeInBottomRight,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.GentleFade,
                "Long, gentle 600ms fade",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.GentleFade,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeThrough,
                "Material fade-through pattern",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeThrough,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeSmall,
                "Fade + shrink entry",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeSmall,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeBig,
                "Fade + grow entry",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeBig,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeUpLeft,
                "Diagonal fade from top-left",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeUpLeft,
) { content() }""",
            ),
            ExampleItem(
                CanimationPreset.FadeDownRight,
                "Diagonal fade from bottom-right",
                """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.FadeDownRight,
) { content() }""",
            ),
        ),
    ),
    ExampleCategory(
        id = "scale",
        title = "Scaling",
        subtitle = "Grow, shrink, zoom, and pop animations",
        accentLabel = "ENTRANCE",
        examples = listOf(
            ExampleItem(CanimationPreset.ScaleIn, "Scale from 92%", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.ScaleIn)"""),
            ExampleItem(CanimationPreset.ScaleUp, "Scale from 108%", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.ScaleUp)"""),
            ExampleItem(CanimationPreset.ZoomIn, "Zoom from 50%", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.ZoomIn)"""),
            ExampleItem(CanimationPreset.ZoomOut, "Zoom from 150%", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.ZoomOut)"""),
            ExampleItem(CanimationPreset.Pop, "Overshoot pop", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.Pop)"""),
            ExampleItem(CanimationPreset.Expand, "Scale from 0% to 100%", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.Expand)"""),
            ExampleItem(CanimationPreset.ShrinkIn, "Shrink from 200%", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.ShrinkIn)"""),
            ExampleItem(CanimationPreset.ElevateIn, "Subtle rise + scale", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.ElevateIn)"""),
            ExampleItem(CanimationPreset.ZoomInUp, "Zoom + upward", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.ZoomInUp)"""),
            ExampleItem(CanimationPreset.ZoomInDown, "Zoom + downward", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.ZoomInDown)"""),
            ExampleItem(CanimationPreset.ZoomInLeft, "Zoom + from left", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.ZoomInLeft)"""),
            ExampleItem(CanimationPreset.ZoomInRight, "Zoom + from right", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.ZoomInRight)"""),
        ),
    ),
    ExampleCategory(
        id = "slide",
        title = "Sliding",
        subtitle = "Translate animations from any direction",
        accentLabel = "ENTRANCE",
        examples = listOf(
            ExampleItem(CanimationPreset.SlideLeft, "Slide from right", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.SlideLeft)"""),
            ExampleItem(CanimationPreset.SlideRight, "Slide from left", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.SlideRight)"""),
            ExampleItem(CanimationPreset.SlideUp, "Long slide up", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.SlideUp)"""),
            ExampleItem(CanimationPreset.SlideDown, "Long slide down", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.SlideDown)"""),
            ExampleItem(CanimationPreset.SharedAxisX, "Shared axis horizontal", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.SharedAxisX)"""),
            ExampleItem(CanimationPreset.SharedAxisY, "Shared axis vertical", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.SharedAxisY)"""),
            ExampleItem(CanimationPreset.UpBig, "Big upward slide", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.UpBig)"""),
            ExampleItem(CanimationPreset.DropIn, "Drop from above", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.DropIn)"""),
        ),
    ),
    ExampleCategory(
        id = "bounce",
        title = "Bouncing",
        subtitle = "Bouncy entry animations from all directions",
        accentLabel = "ENTRANCE",
        examples = listOf(
            ExampleItem(CanimationPreset.BounceIn, "Bouncy scale entry", """CanimationVisibility(visible = isVisible, enterPreset = CanimationPreset.BounceIn) { content() }"""),
            ExampleItem(CanimationPreset.BounceInDown, "Bounce from top", """CanimationVisibility(visible = isVisible, enterPreset = CanimationPreset.BounceInDown) { content() }"""),
            ExampleItem(CanimationPreset.BounceInUp, "Bounce from bottom", """CanimationVisibility(visible = isVisible, enterPreset = CanimationPreset.BounceInUp) { content() }"""),
            ExampleItem(CanimationPreset.BounceInLeft, "Bounce from left", """CanimationVisibility(visible = isVisible, enterPreset = CanimationPreset.BounceInLeft) { content() }"""),
            ExampleItem(CanimationPreset.BounceInRight, "Bounce from right", """CanimationVisibility(visible = isVisible, enterPreset = CanimationPreset.BounceInRight) { content() }"""),
        ),
    ),
    ExampleCategory(
        id = "rotate",
        title = "Rotating",
        subtitle = "Rotation-based entry animations",
        accentLabel = "ENTRANCE",
        examples = listOf(
            ExampleItem(CanimationPreset.RotateIn, "CCW rotation entry", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.RotateIn)"""),
            ExampleItem(CanimationPreset.RotateClockwise, "CW rotation entry", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.RotateClockwise)"""),
            ExampleItem(CanimationPreset.SpinIn, "Full 360° spin + scale", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.SpinIn)"""),
            ExampleItem(CanimationPreset.RotateInDownLeft, "Rotate from down-left", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.RotateInDownLeft)"""),
            ExampleItem(CanimationPreset.RotateInDownRight, "Rotate from down-right", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.RotateInDownRight)"""),
            ExampleItem(CanimationPreset.RotateInUpLeft, "Rotate from up-left", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.RotateInUpLeft)"""),
            ExampleItem(CanimationPreset.RotateInUpRight, "Rotate from up-right", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.RotateInUpRight)"""),
            ExampleItem(CanimationPreset.RotateScale, "Rotation + scale combo", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.RotateScale)"""),
        ),
    ),
    ExampleCategory(
        id = "flip",
        title = "Flipping",
        subtitle = "3D-style flip and tilt animations",
        accentLabel = "ENTRANCE",
        examples = listOf(
            ExampleItem(CanimationPreset.FlipIn, "Half-rotation flip entry", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.FlipIn)"""),
            ExampleItem(CanimationPreset.FlipInY, "Vertical flip (Y-axis)", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.FlipInY)"""),
            ExampleItem(CanimationPreset.FlipUp, "Flip + upward slide", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.FlipUp)"""),
            ExampleItem(CanimationPreset.FlipDown, "Flip + downward slide", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.FlipDown)"""),
            ExampleItem(CanimationPreset.TiltIn, "Slight tilt + scale entry", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.TiltIn)"""),
        ),
    ),
    ExampleCategory(
        id = "spring",
        title = "Spring Physics",
        subtitle = "Overshoot and spring-based animations",
        accentLabel = "PHYSICS",
        examples = listOf(
            ExampleItem(CanimationPreset.SpringIn, "Spring overshoot scale", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.SpringIn)"""),
            ExampleItem(CanimationPreset.SpringSlideUp, "Spring slide from below", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.SpringSlideUp)"""),
            ExampleItem(CanimationPreset.SpringFadeIn, "Spring fade + subtle scale", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.SpringFadeIn)"""),
            ExampleItem(CanimationPreset.BackInUp, "Back easing from bottom", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.BackInUp)"""),
            ExampleItem(CanimationPreset.BackInDown, "Back easing from top", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.BackInDown)"""),
            ExampleItem(CanimationPreset.BackInLeft, "Back easing from left", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.BackInLeft)"""),
            ExampleItem(CanimationPreset.BackInRight, "Back easing from right", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.BackInRight)"""),
        ),
    ),
    ExampleCategory(
        id = "attention",
        title = "Attention Seekers",
        subtitle = "Emphasis and attention-grabbing animations",
        accentLabel = "EMPHASIS",
        examples = listOf(
            ExampleItem(CanimationPreset.Pulse, "Subtle scale pulse", """Modifier.canimationEmphasize(active = isActive, preset = CanimationPreset.Pulse)"""),
            ExampleItem(CanimationPreset.HeartBeat, "Heartbeat-like scale pulse", """Modifier.canimationEmphasize(active = isActive, preset = CanimationPreset.HeartBeat)"""),
            ExampleItem(CanimationPreset.Tada, "Rotation + scale emphasis", """Modifier.canimationEmphasize(active = isActive, preset = CanimationPreset.Tada)"""),
            ExampleItem(CanimationPreset.Wobble, "Side-to-side wobble", """Modifier.canimationEmphasize(active = isActive, preset = CanimationPreset.Wobble)"""),
            ExampleItem(CanimationPreset.Swing, "Pendulum swing", """Modifier.canimationEmphasize(active = isActive, preset = CanimationPreset.Swing)"""),
            ExampleItem(CanimationPreset.RubberBand, "Rubber band stretch", """Modifier.canimationEmphasize(active = isActive, preset = CanimationPreset.RubberBand)"""),
            ExampleItem(CanimationPreset.Bounce, "Vertical bounce", """Modifier.canimationEmphasize(active = isActive, preset = CanimationPreset.Bounce)"""),
            ExampleItem(CanimationPreset.Flash, "Quick opacity flash", """Modifier.canimationEmphasize(active = isActive, preset = CanimationPreset.Flash)"""),
            ExampleItem(CanimationPreset.ShakeX, "Horizontal shake", """Modifier.canimationEmphasize(active = isActive, preset = CanimationPreset.ShakeX)"""),
            ExampleItem(CanimationPreset.ShakeY, "Vertical shake", """Modifier.canimationEmphasize(active = isActive, preset = CanimationPreset.ShakeY)"""),
            ExampleItem(CanimationPreset.HeadShake, "Subtle head shake", """Modifier.canimationEmphasize(active = isActive, preset = CanimationPreset.HeadShake)"""),
            ExampleItem(CanimationPreset.Jello, "Jello wobble", """Modifier.canimationEmphasize(active = isActive, preset = CanimationPreset.Jello)"""),
        ),
    ),
    ExampleCategory(
        id = "special",
        title = "Specials",
        subtitle = "Unique and dramatic entry animations",
        accentLabel = "SPECIAL",
        examples = listOf(
            ExampleItem(CanimationPreset.JackInTheBox, "Scale + rotation combo", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.JackInTheBox)"""),
            ExampleItem(CanimationPreset.RollIn, "Roll + slide from left", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.RollIn)"""),
            ExampleItem(CanimationPreset.LightSpeedInRight, "Fast slide + tilt from right", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.LightSpeedInRight)"""),
            ExampleItem(CanimationPreset.LightSpeedInLeft, "Fast slide + tilt from left", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.LightSpeedInLeft)"""),
            ExampleItem(CanimationPreset.SwingIn, "Swing + slide entry", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.SwingIn)"""),
            ExampleItem(CanimationPreset.Snap, "Instant 10ms cut", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.Snap)"""),
            ExampleItem(CanimationPreset.EmphasizedEntry, "Material emphasized decelerate", """Modifier.canimationEnter(visible = isVisible, preset = CanimationPreset.EmphasizedEntry)"""),
        ),
    ),
)

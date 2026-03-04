package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset

data class ExampleCategory(
    val id: String,
    val title: String,
    val subtitle: String,
    val accentLabel: String,
    val examples: List<ExampleItem>,
    val tags: List<String> = emptyList(),
)

data class ExampleItem(
    val title: String,
    val description: String,
    val codeSnippet: String,
    val demoType: String = "effect",
    val effect: CanimationEffect? = null,
    val enterEffect: CanimationEffect? = null,
    val exitEffect: CanimationEffect? = null,
    val preset: CanimationPreset = CanimationPreset.Fade,
    val componentKey: String? = null,
)

val exampleCategories: List<ExampleCategory> = listOf(

    // 1. Fade Basics
    ExampleCategory(
        id = "fade-basic",
        title = "Fade Basics",
        subtitle = "Core fade-in and fade-out effects",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE"),
        examples = listOf(
            ExampleItem(
                title = "Fade In",
                description = "Standard fade-in animation",
                effect = Canimation.Fade.In,
                preset = CanimationPreset.Fade,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.In,
)""",
            ),
            ExampleItem(
                title = "Fade Out",
                description = "Fade-out animation effect",
                effect = Canimation.Fade.Out,
                preset = CanimationPreset.Fade,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.Out,
)""",
            ),
            ExampleItem(
                title = "Gentle Fade",
                description = "Slow, gentle fade transition",
                effect = Canimation.Fade.Gentle,
                preset = CanimationPreset.GentleFade,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.Gentle,
)""",
            ),
            ExampleItem(
                title = "Quick Fade",
                description = "Fast, snappy fade transition",
                effect = Canimation.Fade.Quick,
                preset = CanimationPreset.Fade,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.Quick,
)""",
            ),
        ),
    ),

    // 2. Directional Fade
    ExampleCategory(
        id = "fade-directional",
        title = "Directional Fade",
        subtitle = "Fade with directional movement",
        accentLabel = "DIRECTION",
        tags = listOf("ENTRANCE", "DIRECTION"),
        examples = listOf(
            ExampleItem(
                title = "Fade Up",
                description = "Fade in while sliding up",
                effect = Canimation.Fade.Up,
                preset = CanimationPreset.FadeUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.Up,
)""",
            ),
            ExampleItem(
                title = "Fade Down",
                description = "Fade in while sliding down",
                effect = Canimation.Fade.Down,
                preset = CanimationPreset.FadeDown,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.Down,
)""",
            ),
            ExampleItem(
                title = "Fade Left",
                description = "Fade in from the right",
                effect = Canimation.Fade.Left,
                preset = CanimationPreset.FadeInLeft,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.Left,
)""",
            ),
            ExampleItem(
                title = "Fade Right",
                description = "Fade in from the left",
                effect = Canimation.Fade.Right,
                preset = CanimationPreset.FadeInRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.Right,
)""",
            ),
        ),
    ),

    // 3. Big Fade
    ExampleCategory(
        id = "fade-big",
        title = "Big Fade",
        subtitle = "Large-distance directional fades",
        accentLabel = "DIRECTION",
        tags = listOf("ENTRANCE", "DIRECTION"),
        examples = listOf(
            ExampleItem(
                title = "Fade Up Big",
                description = "Large upward fade-in",
                effect = Canimation.Fade.UpBig,
                preset = CanimationPreset.FadeInUpBig,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.UpBig,
)""",
            ),
            ExampleItem(
                title = "Fade Down Big",
                description = "Large downward fade-in",
                effect = Canimation.Fade.DownBig,
                preset = CanimationPreset.FadeInDownBig,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.DownBig,
)""",
            ),
            ExampleItem(
                title = "Fade Left Big",
                description = "Large leftward fade-in",
                effect = Canimation.Fade.LeftBig,
                preset = CanimationPreset.FadeInLeftBig,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.LeftBig,
)""",
            ),
            ExampleItem(
                title = "Fade Right Big",
                description = "Large rightward fade-in",
                effect = Canimation.Fade.RightBig,
                preset = CanimationPreset.FadeInRightBig,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.RightBig,
)""",
            ),
        ),
    ),

    // 4. Diagonal Fade
    ExampleCategory(
        id = "fade-diagonal",
        title = "Diagonal Fade",
        subtitle = "Corner and size-based fades",
        accentLabel = "DIRECTION",
        tags = listOf("ENTRANCE", "DIRECTION"),
        examples = listOf(
            ExampleItem(
                title = "Fade Top Left",
                description = "Fade in from top-left corner",
                effect = Canimation.Fade.TopLeft,
                preset = CanimationPreset.FadeInTopLeft,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.TopLeft,
)""",
            ),
            ExampleItem(
                title = "Fade Top Right",
                description = "Fade in from top-right corner",
                effect = Canimation.Fade.TopRight,
                preset = CanimationPreset.FadeInTopRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.TopRight,
)""",
            ),
            ExampleItem(
                title = "Fade Bottom Left",
                description = "Fade in from bottom-left corner",
                effect = Canimation.Fade.BottomLeft,
                preset = CanimationPreset.FadeInBottomLeft,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.BottomLeft,
)""",
            ),
            ExampleItem(
                title = "Fade Bottom Right",
                description = "Fade in from bottom-right corner",
                effect = Canimation.Fade.BottomRight,
                preset = CanimationPreset.FadeInBottomRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.BottomRight,
)""",
            ),
            ExampleItem(
                title = "Fade Small",
                description = "Subtle small-scale fade",
                effect = Canimation.Fade.Small,
                preset = CanimationPreset.FadeSmall,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.Small,
)""",
            ),
            ExampleItem(
                title = "Fade Big",
                description = "Dramatic large-scale fade",
                effect = Canimation.Fade.Big,
                preset = CanimationPreset.FadeBig,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.Big,
)""",
            ),
        ),
    ),

    // 5. Scale Effects
    ExampleCategory(
        id = "scale",
        title = "Scale Effects",
        subtitle = "Size-based entrance animations",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE"),
        examples = listOf(
            ExampleItem(
                title = "Scale In",
                description = "Scale up from zero",
                effect = Canimation.Scale.In,
                preset = CanimationPreset.ScaleIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.In,
)""",
            ),
            ExampleItem(
                title = "Scale Up",
                description = "Scale up with upward motion",
                effect = Canimation.Scale.Up,
                preset = CanimationPreset.ScaleUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.Up,
)""",
            ),
            ExampleItem(
                title = "Scale Down",
                description = "Scale from a larger size",
                effect = Canimation.Scale.Down,
                preset = CanimationPreset.ScaleIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.Down,
)""",
            ),
            ExampleItem(
                title = "Expand",
                description = "Expand outward from center",
                effect = Canimation.Scale.Expand,
                preset = CanimationPreset.Expand,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.Expand,
)""",
            ),
            ExampleItem(
                title = "Shrink",
                description = "Shrink inward to center",
                effect = Canimation.Scale.Shrink,
                preset = CanimationPreset.ScaleIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.Shrink,
)""",
            ),
            ExampleItem(
                title = "Subtle Scale",
                description = "Gentle, barely noticeable scale",
                effect = Canimation.Scale.Subtle,
                preset = CanimationPreset.ScaleIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.Subtle,
)""",
            ),
            ExampleItem(
                title = "Pop",
                description = "Springy pop-in effect",
                effect = Canimation.Scale.Pop,
                preset = CanimationPreset.Pop,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.Pop,
)""",
            ),
            ExampleItem(
                title = "Zoom",
                description = "Zoom-in scale effect",
                effect = Canimation.Zoom.In,
                preset = CanimationPreset.ZoomIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Zoom.In,
)""",
            ),
            ExampleItem(
                title = "Scale Fade In",
                description = "Combine scale with fade",
                effect = Canimation.Scale.FadeIn,
                preset = CanimationPreset.ScaleIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.FadeIn,
)""",
            ),
            ExampleItem(
                title = "Scale Up Fade",
                description = "Scale up with fade",
                effect = Canimation.Scale.UpFade,
                preset = CanimationPreset.ScaleUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.UpFade,
)""",
            ),
            ExampleItem(
                title = "Scale Down Fade",
                description = "Scale down with fade",
                effect = Canimation.Scale.DownFade,
                preset = CanimationPreset.ScaleIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.DownFade,
)""",
            ),
        ),
    ),

    // 6. Slide Effects
    ExampleCategory(
        id = "slide",
        title = "Slide Effects",
        subtitle = "Directional slide-in animations",
        accentLabel = "DIRECTION",
        tags = listOf("ENTRANCE", "DIRECTION"),
        examples = listOf(
            ExampleItem(
                title = "Slide Left",
                description = "Slide in from the right",
                effect = Canimation.Slide.Left,
                preset = CanimationPreset.SlideLeft,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.Left,
)""",
            ),
            ExampleItem(
                title = "Slide Right",
                description = "Slide in from the left",
                effect = Canimation.Slide.Right,
                preset = CanimationPreset.SlideRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.Right,
)""",
            ),
            ExampleItem(
                title = "Slide Up",
                description = "Slide in from below",
                effect = Canimation.Slide.Up,
                preset = CanimationPreset.SlideUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.Up,
)""",
            ),
            ExampleItem(
                title = "Slide Down",
                description = "Slide in from above",
                effect = Canimation.Slide.Down,
                preset = CanimationPreset.SlideDown,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.Down,
)""",
            ),
            ExampleItem(
                title = "Slide Left Big",
                description = "Large-distance leftward slide",
                effect = Canimation.Slide.LeftBig,
                preset = CanimationPreset.SlideLeft,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.LeftBig,
)""",
            ),
            ExampleItem(
                title = "Slide Right Big",
                description = "Large-distance rightward slide",
                effect = Canimation.Slide.RightBig,
                preset = CanimationPreset.SlideRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.RightBig,
)""",
            ),
            ExampleItem(
                title = "Slide Up Big",
                description = "Large-distance upward slide",
                effect = Canimation.Slide.UpBig,
                preset = CanimationPreset.SlideUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.UpBig,
)""",
            ),
            ExampleItem(
                title = "Slide Down Big",
                description = "Large-distance downward slide",
                effect = Canimation.Slide.DownBig,
                preset = CanimationPreset.SlideDown,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.DownBig,
)""",
            ),
        ),
    ),

    // 7. Subtle Slide
    ExampleCategory(
        id = "slide-subtle",
        title = "Subtle Slide",
        subtitle = "Minimal, refined slide effects",
        accentLabel = "DIRECTION",
        tags = listOf("ENTRANCE", "DIRECTION"),
        examples = listOf(
            ExampleItem(
                title = "Slide Up Subtle",
                description = "Barely perceptible upward slide",
                effect = Canimation.Slide.UpSubtle,
                preset = CanimationPreset.SlideUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.UpSubtle,
)""",
            ),
            ExampleItem(
                title = "Slide Down Subtle",
                description = "Barely perceptible downward slide",
                effect = Canimation.Slide.DownSubtle,
                preset = CanimationPreset.SlideDown,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.DownSubtle,
)""",
            ),
            ExampleItem(
                title = "Slide Left Subtle",
                description = "Barely perceptible leftward slide",
                effect = Canimation.Slide.LeftSubtle,
                preset = CanimationPreset.SlideLeft,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.LeftSubtle,
)""",
            ),
            ExampleItem(
                title = "Slide Right Subtle",
                description = "Barely perceptible rightward slide",
                effect = Canimation.Slide.RightSubtle,
                preset = CanimationPreset.SlideRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.RightSubtle,
)""",
            ),
        ),
    ),

    // 8. Rotate Effects
    ExampleCategory(
        id = "rotate",
        title = "Rotate Effects",
        subtitle = "Rotation-based entrance animations",
        accentLabel = "3D",
        tags = listOf("ENTRANCE", "3D"),
        examples = listOf(
            ExampleItem(
                title = "Rotate In",
                description = "Simple rotation entrance",
                effect = Canimation.Rotate.In,
                preset = CanimationPreset.RotateIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.In,
)""",
            ),
            ExampleItem(
                title = "Clockwise",
                description = "Full clockwise rotation",
                effect = Canimation.Rotate.Clockwise,
                preset = CanimationPreset.RotateClockwise,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.Clockwise,
)""",
            ),
            ExampleItem(
                title = "Spin",
                description = "Multi-revolution spin",
                effect = Canimation.Rotate.Spin,
                preset = CanimationPreset.SpinIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.Spin,
)""",
            ),
            ExampleItem(
                title = "Half Rotation",
                description = "180-degree rotation",
                effect = Canimation.Rotate.Half,
                preset = CanimationPreset.RotateIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.Half,
)""",
            ),
            ExampleItem(
                title = "Quarter Rotation",
                description = "90-degree rotation",
                effect = Canimation.Rotate.Quarter,
                preset = CanimationPreset.RotateIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.Quarter,
)""",
            ),
            ExampleItem(
                title = "Rotate Fade In",
                description = "Rotation combined with fade",
                effect = Canimation.Rotate.FadeIn,
                preset = CanimationPreset.RotateIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.FadeIn,
)""",
            ),
            ExampleItem(
                title = "Rotate Scale In",
                description = "Rotation combined with scale",
                effect = Canimation.Rotate.ScaleIn,
                preset = CanimationPreset.RotateScale,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.ScaleIn,
)""",
            ),
        ),
    ),

    // 9. Directional Rotate
    ExampleCategory(
        id = "rotate-directional",
        title = "Directional Rotate",
        subtitle = "Rotation with directional origin",
        accentLabel = "3D",
        tags = listOf("ENTRANCE", "3D", "DIRECTION"),
        examples = listOf(
            ExampleItem(
                title = "Clockwise Fade",
                description = "Clockwise rotation with fade",
                effect = Canimation.Rotate.ClockwiseFade,
                preset = CanimationPreset.RotateClockwise,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.ClockwiseFade,
)""",
            ),
            ExampleItem(
                title = "Spin Fade",
                description = "Spinning with fade effect",
                effect = Canimation.Rotate.SpinFade,
                preset = CanimationPreset.SpinIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.SpinFade,
)""",
            ),
            ExampleItem(
                title = "Down Left",
                description = "Rotate in from down-left",
                effect = Canimation.Rotate.DownLeft,
                preset = CanimationPreset.RotateInDownLeft,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.DownLeft,
)""",
            ),
            ExampleItem(
                title = "Down Right",
                description = "Rotate in from down-right",
                effect = Canimation.Rotate.DownRight,
                preset = CanimationPreset.RotateInDownRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.DownRight,
)""",
            ),
            ExampleItem(
                title = "Up Left",
                description = "Rotate in from up-left",
                effect = Canimation.Rotate.UpLeft,
                preset = CanimationPreset.RotateInUpLeft,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.UpLeft,
)""",
            ),
            ExampleItem(
                title = "Up Right",
                description = "Rotate in from up-right",
                effect = Canimation.Rotate.UpRight,
                preset = CanimationPreset.RotateInUpRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Rotate.UpRight,
)""",
            ),
        ),
    ),

    // 10. Bounce Effects
    ExampleCategory(
        id = "bounce",
        title = "Bounce Effects",
        subtitle = "Playful bouncing entrances",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE"),
        examples = listOf(
            ExampleItem(
                title = "Bounce In",
                description = "Bounce from center",
                effect = Canimation.Bounce.In,
                preset = CanimationPreset.BounceIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Bounce.In,
)""",
            ),
            ExampleItem(
                title = "Bounce Down",
                description = "Bounce in from top",
                effect = Canimation.Bounce.Down,
                preset = CanimationPreset.BounceInDown,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Bounce.Down,
)""",
            ),
            ExampleItem(
                title = "Bounce Up",
                description = "Bounce in from bottom",
                effect = Canimation.Bounce.Up,
                preset = CanimationPreset.BounceInUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Bounce.Up,
)""",
            ),
            ExampleItem(
                title = "Bounce Left",
                description = "Bounce in from the right",
                effect = Canimation.Bounce.Left,
                preset = CanimationPreset.BounceInLeft,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Bounce.Left,
)""",
            ),
            ExampleItem(
                title = "Bounce Right",
                description = "Bounce in from the left",
                effect = Canimation.Bounce.Right,
                preset = CanimationPreset.BounceInRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Bounce.Right,
)""",
            ),
        ),
    ),

    // 11. Spring Effects
    ExampleCategory(
        id = "spring",
        title = "Spring Effects",
        subtitle = "Physics-based spring animations",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE"),
        examples = listOf(
            ExampleItem(
                title = "Spring In",
                description = "Spring-based entrance",
                effect = Canimation.Spring.In,
                preset = CanimationPreset.SpringIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Spring.In,
)""",
            ),
            ExampleItem(
                title = "Spring Up",
                description = "Spring slide upward",
                effect = Canimation.Spring.Up,
                preset = CanimationPreset.SpringSlideUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Spring.Up,
)""",
            ),
            ExampleItem(
                title = "Spring Down",
                description = "Spring slide downward",
                effect = Canimation.Spring.Down,
                preset = CanimationPreset.SpringIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Spring.Down,
)""",
            ),
            ExampleItem(
                title = "Spring Left",
                description = "Spring slide leftward",
                effect = Canimation.Spring.Left,
                preset = CanimationPreset.SpringIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Spring.Left,
)""",
            ),
            ExampleItem(
                title = "Spring Right",
                description = "Spring slide rightward",
                effect = Canimation.Spring.Right,
                preset = CanimationPreset.SpringIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Spring.Right,
)""",
            ),
            ExampleItem(
                title = "Spring Pop",
                description = "Springy pop-in effect",
                effect = Canimation.Spring.Pop,
                preset = CanimationPreset.Pop,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Spring.Pop,
)""",
            ),
            ExampleItem(
                title = "Spring Bounce",
                description = "Springy bounce effect",
                effect = Canimation.Spring.Bounce,
                preset = CanimationPreset.Bounce,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Spring.Bounce,
)""",
            ),
        ),
    ),

    // 12. Flip Effects
    ExampleCategory(
        id = "flip",
        title = "Flip Effects",
        subtitle = "3D flip entrance animations",
        accentLabel = "3D",
        tags = listOf("ENTRANCE", "3D"),
        examples = listOf(
            ExampleItem(
                title = "Flip In",
                description = "Basic flip entrance",
                effect = Canimation.Flip.In,
                preset = CanimationPreset.FlipIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Flip.In,
)""",
            ),
            ExampleItem(
                title = "Flip Up",
                description = "Flip from bottom edge",
                effect = Canimation.Flip.Up,
                preset = CanimationPreset.FlipUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Flip.Up,
)""",
            ),
            ExampleItem(
                title = "Flip Down",
                description = "Flip from top edge",
                effect = Canimation.Flip.Down,
                preset = CanimationPreset.FlipDown,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Flip.Down,
)""",
            ),
            ExampleItem(
                title = "Flip X",
                description = "Flip on X axis",
                effect = Canimation.Flip.X,
                preset = CanimationPreset.FlipIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Flip.X,
)""",
            ),
            ExampleItem(
                title = "Flip Y",
                description = "Flip on Y axis",
                effect = Canimation.Flip.Y,
                preset = CanimationPreset.FlipInY,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Flip.Y,
)""",
            ),
        ),
    ),

    // 13. Zoom Effects
    ExampleCategory(
        id = "zoom",
        title = "Zoom Effects",
        subtitle = "Zoom-based entrance and exit",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE"),
        examples = listOf(
            ExampleItem(
                title = "Zoom In",
                description = "Standard zoom entrance",
                effect = Canimation.Zoom.In,
                preset = CanimationPreset.ZoomIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Zoom.In,
)""",
            ),
            ExampleItem(
                title = "Zoom Out",
                description = "Zoom out exit effect",
                effect = Canimation.Zoom.Out,
                preset = CanimationPreset.ZoomOut,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Zoom.Out,
)""",
            ),
            ExampleItem(
                title = "Zoom In Up",
                description = "Zoom in from below",
                effect = Canimation.Zoom.InUp,
                preset = CanimationPreset.ZoomInUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Zoom.InUp,
)""",
            ),
            ExampleItem(
                title = "Zoom In Down",
                description = "Zoom in from above",
                effect = Canimation.Zoom.InDown,
                preset = CanimationPreset.ZoomInDown,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Zoom.InDown,
)""",
            ),
            ExampleItem(
                title = "Zoom In Left",
                description = "Zoom in from the right",
                effect = Canimation.Zoom.InLeft,
                preset = CanimationPreset.ZoomInLeft,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Zoom.InLeft,
)""",
            ),
            ExampleItem(
                title = "Zoom In Right",
                description = "Zoom in from the left",
                effect = Canimation.Zoom.InRight,
                preset = CanimationPreset.ZoomInRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Zoom.InRight,
)""",
            ),
            ExampleItem(
                title = "Zoom Dramatic",
                description = "Extreme zoom entrance",
                effect = Canimation.Zoom.Dramatic,
                preset = CanimationPreset.ZoomIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Zoom.Dramatic,
)""",
            ),
        ),
    ),

    // 14. Attention Seekers
    ExampleCategory(
        id = "attention",
        title = "Attention Seekers",
        subtitle = "Emphasis and attention effects",
        accentLabel = "EMPHASIS",
        tags = listOf("EMPHASIS"),
        examples = listOf(
            ExampleItem(
                title = "Pulse",
                description = "Gentle pulsing scale",
                demoType = "emphasis",
                preset = CanimationPreset.Pulse,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.Pulse,
)""",
            ),
            ExampleItem(
                title = "HeartBeat",
                description = "Double-pulse heartbeat",
                demoType = "emphasis",
                preset = CanimationPreset.HeartBeat,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.HeartBeat,
)""",
            ),
            ExampleItem(
                title = "Tada",
                description = "Celebratory shake and scale",
                demoType = "emphasis",
                preset = CanimationPreset.Tada,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.Tada,
)""",
            ),
            ExampleItem(
                title = "Wobble",
                description = "Side-to-side wobble",
                demoType = "emphasis",
                preset = CanimationPreset.Wobble,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.Wobble,
)""",
            ),
            ExampleItem(
                title = "Swing",
                description = "Pendulum swing motion",
                demoType = "emphasis",
                preset = CanimationPreset.Swing,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.Swing,
)""",
            ),
            ExampleItem(
                title = "RubberBand",
                description = "Elastic rubber band stretch",
                demoType = "emphasis",
                preset = CanimationPreset.RubberBand,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.RubberBand,
)""",
            ),
            ExampleItem(
                title = "Jello",
                description = "Jiggly jello effect",
                demoType = "emphasis",
                preset = CanimationPreset.Jello,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.Jello,
)""",
            ),
            ExampleItem(
                title = "Flash",
                description = "Quick opacity flash",
                demoType = "emphasis",
                preset = CanimationPreset.Flash,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.Flash,
)""",
            ),
            ExampleItem(
                title = "ShakeX",
                description = "Horizontal shake",
                demoType = "emphasis",
                preset = CanimationPreset.ShakeX,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.ShakeX,
)""",
            ),
            ExampleItem(
                title = "ShakeY",
                description = "Vertical shake",
                demoType = "emphasis",
                preset = CanimationPreset.ShakeY,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.ShakeY,
)""",
            ),
            ExampleItem(
                title = "HeadShake",
                description = "Head shaking no",
                demoType = "emphasis",
                preset = CanimationPreset.HeadShake,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.HeadShake,
)""",
            ),
            ExampleItem(
                title = "Bounce",
                description = "Bouncing attention effect",
                demoType = "emphasis",
                preset = CanimationPreset.Bounce,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.Bounce,
)""",
            ),
        ),
    ),

    // 15. Dramatic Entrances
    ExampleCategory(
        id = "entrance",
        title = "Dramatic Entrances",
        subtitle = "Unique, expressive entrance effects",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE"),
        examples = listOf(
            ExampleItem(
                title = "Elevate",
                description = "Rise with shadow effect",
                effect = Canimation.Entrance.Elevate,
                preset = CanimationPreset.ElevateIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.Elevate,
)""",
            ),
            ExampleItem(
                title = "Drop",
                description = "Drop in from above",
                effect = Canimation.Entrance.Drop,
                preset = CanimationPreset.DropIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.Drop,
)""",
            ),
            ExampleItem(
                title = "Jack In The Box",
                description = "Playful spring-up entrance",
                effect = Canimation.Entrance.JackInTheBox,
                preset = CanimationPreset.JackInTheBox,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.JackInTheBox,
)""",
            ),
            ExampleItem(
                title = "Roll In",
                description = "Rolling entrance from left",
                effect = Canimation.Entrance.RollIn,
                preset = CanimationPreset.RollIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.RollIn,
)""",
            ),
            ExampleItem(
                title = "Light Speed Left",
                description = "Zoom in from left at light speed",
                effect = Canimation.Entrance.LightSpeedLeft,
                preset = CanimationPreset.LightSpeedInLeft,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.LightSpeedLeft,
)""",
            ),
            ExampleItem(
                title = "Light Speed Right",
                description = "Zoom in from right at light speed",
                effect = Canimation.Entrance.LightSpeedRight,
                preset = CanimationPreset.LightSpeedInRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.LightSpeedRight,
)""",
            ),
            ExampleItem(
                title = "Snap",
                description = "Snappy instant entrance",
                effect = Canimation.Entrance.Snap,
                preset = CanimationPreset.Snap,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.Snap,
)""",
            ),
            ExampleItem(
                title = "Swing In",
                description = "Swinging door entrance",
                effect = Canimation.Entrance.SwingIn,
                preset = CanimationPreset.SwingIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.SwingIn,
)""",
            ),
            ExampleItem(
                title = "Unfold",
                description = "Unfolding paper effect",
                effect = Canimation.Entrance.Unfold,
                preset = CanimationPreset.ElevateIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.Unfold,
)""",
            ),
            ExampleItem(
                title = "Rise",
                description = "Gentle rising entrance",
                effect = Canimation.Entrance.Rise,
                preset = CanimationPreset.FadeUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.Rise,
)""",
            ),
            ExampleItem(
                title = "Emerge",
                description = "Emerging from nothing",
                effect = Canimation.Entrance.Emerge,
                preset = CanimationPreset.ScaleIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.Emerge,
)""",
            ),
        ),
    ),

    // 16. Back Entrances
    ExampleCategory(
        id = "entrance-back",
        title = "Back Entrances",
        subtitle = "Depth-based entrance effects",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE", "DIRECTION"),
        examples = listOf(
            ExampleItem(
                title = "Back In Up",
                description = "Slide in from behind, upward",
                effect = Canimation.Entrance.BackInUp,
                preset = CanimationPreset.BackInUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.BackInUp,
)""",
            ),
            ExampleItem(
                title = "Back In Down",
                description = "Slide in from behind, downward",
                effect = Canimation.Entrance.BackInDown,
                preset = CanimationPreset.BackInDown,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.BackInDown,
)""",
            ),
            ExampleItem(
                title = "Back In Left",
                description = "Slide in from behind, leftward",
                effect = Canimation.Entrance.BackInLeft,
                preset = CanimationPreset.BackInLeft,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.BackInLeft,
)""",
            ),
            ExampleItem(
                title = "Back In Right",
                description = "Slide in from behind, rightward",
                effect = Canimation.Entrance.BackInRight,
                preset = CanimationPreset.BackInRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.BackInRight,
)""",
            ),
            ExampleItem(
                title = "Shrink In",
                description = "Shrinking entrance effect",
                effect = Canimation.Entrance.ShrinkIn,
                preset = CanimationPreset.ShrinkIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.ShrinkIn,
)""",
            ),
            ExampleItem(
                title = "Tilt In",
                description = "Tilting entrance effect",
                effect = Canimation.Entrance.TiltIn,
                preset = CanimationPreset.TiltIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.TiltIn,
)""",
            ),
        ),
    ),

    // 17. Material Motion
    ExampleCategory(
        id = "material",
        title = "Material Motion",
        subtitle = "Google Material Design transitions",
        accentLabel = "MATERIAL",
        tags = listOf("MATERIAL"),
        examples = listOf(
            ExampleItem(
                title = "Fade Through",
                description = "Material fade-through transition",
                effect = Canimation.Material.FadeThrough,
                preset = CanimationPreset.FadeThrough,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Material.FadeThrough,
)""",
            ),
            ExampleItem(
                title = "Shared Axis X",
                description = "Horizontal shared-axis transition",
                effect = Canimation.Material.SharedAxisX,
                preset = CanimationPreset.SharedAxisX,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Material.SharedAxisX,
)""",
            ),
            ExampleItem(
                title = "Shared Axis Y",
                description = "Vertical shared-axis transition",
                effect = Canimation.Material.SharedAxisY,
                preset = CanimationPreset.SharedAxisY,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Material.SharedAxisY,
)""",
            ),
            ExampleItem(
                title = "Shared Axis Z",
                description = "Depth shared-axis transition",
                effect = Canimation.Material.SharedAxisZ,
                preset = CanimationPreset.SharedAxisX,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Material.SharedAxisZ,
)""",
            ),
            ExampleItem(
                title = "Emphasized",
                description = "Material emphasized motion",
                effect = Canimation.Material.Emphasized,
                preset = CanimationPreset.EmphasizedEntry,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Material.Emphasized,
)""",
            ),
            ExampleItem(
                title = "Container Transform",
                description = "Material container transform",
                effect = Canimation.Material.ContainerTransform,
                preset = CanimationPreset.EmphasizedEntry,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Material.ContainerTransform,
)""",
            ),
        ),
    ),

    // 18. Morph Effects
    ExampleCategory(
        id = "morph",
        title = "Morph Effects",
        subtitle = "Shape-morphing transitions",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE"),
        examples = listOf(
            ExampleItem(
                title = "Scale Up",
                description = "Morphing scale-up effect",
                effect = Canimation.Morph.ScaleUp,
                preset = CanimationPreset.ScaleUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Morph.ScaleUp,
)""",
            ),
            ExampleItem(
                title = "Expand",
                description = "Morphing expansion",
                effect = Canimation.Morph.Expand,
                preset = CanimationPreset.Expand,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Morph.Expand,
)""",
            ),
            ExampleItem(
                title = "Collapse",
                description = "Morphing collapse",
                effect = Canimation.Morph.Collapse,
                preset = CanimationPreset.ScaleIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Morph.Collapse,
)""",
            ),
        ),
    ),

    // 19. Effect Composition
    ExampleCategory(
        id = "composition",
        title = "Effect Composition",
        subtitle = "Combining effects with the + operator",
        accentLabel = "PATTERN",
        tags = listOf("PATTERN"),
        examples = listOf(
            ExampleItem(
                title = "Fade + Pop",
                description = "Fade in combined with pop scale",
                demoType = "composition",
                effect = Canimation.Fade.In + Canimation.Scale.Pop,
                preset = CanimationPreset.Pop,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.In + Canimation.Scale.Pop,
)""",
            ),
            ExampleItem(
                title = "Fade Up + Rotate",
                description = "Fade up with rotation entrance",
                demoType = "composition",
                effect = Canimation.Fade.Up + Canimation.Rotate.In,
                preset = CanimationPreset.FadeUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.Up + Canimation.Rotate.In,
)""",
            ),
            ExampleItem(
                title = "Zoom + Slide Up",
                description = "Zoom in while sliding upward",
                demoType = "composition",
                effect = Canimation.Zoom.In + Canimation.Slide.Up,
                preset = CanimationPreset.ZoomIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Zoom.In + Canimation.Slide.Up,
)""",
            ),
            ExampleItem(
                title = "Bounce + Fade",
                description = "Bounce in with fade",
                demoType = "composition",
                effect = Canimation.Bounce.In + Canimation.Fade.In,
                preset = CanimationPreset.BounceIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Bounce.In + Canimation.Fade.In,
)""",
            ),
            ExampleItem(
                title = "Spring Pop + Rotate",
                description = "Springy pop with clockwise rotation",
                demoType = "composition",
                effect = Canimation.Spring.Pop + Canimation.Rotate.Clockwise,
                preset = CanimationPreset.Pop,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Spring.Pop + Canimation.Rotate.Clockwise,
)""",
            ),
            ExampleItem(
                title = "Elevate + Gentle Fade",
                description = "Elevation entrance with gentle fade",
                demoType = "composition",
                effect = Canimation.Entrance.Elevate + Canimation.Fade.Gentle,
                preset = CanimationPreset.ElevateIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.Elevate + Canimation.Fade.Gentle,
)""",
            ),
        ),
    ),

    // 20. Asymmetric Transitions
    ExampleCategory(
        id = "transitions",
        title = "Asymmetric Transitions",
        subtitle = "Different enter and exit effects",
        accentLabel = "PATTERN",
        tags = listOf("PATTERN"),
        examples = listOf(
            ExampleItem(
                title = "Fade Up / Fade Down",
                description = "Enter fading up, exit fading down",
                demoType = "transition",
                enterEffect = Canimation.Fade.Up,
                exitEffect = Canimation.Fade.Down,
                preset = CanimationPreset.FadeUp,
                codeSnippet = """Modifier.canimationTransition(
    visible = isVisible,
    enter = Canimation.Fade.Up,
    exit = Canimation.Fade.Down,
)""",
            ),
            ExampleItem(
                title = "Pop / Shrink",
                description = "Enter with pop, exit with shrink",
                demoType = "transition",
                enterEffect = Canimation.Scale.Pop,
                exitEffect = Canimation.Scale.Shrink,
                preset = CanimationPreset.Pop,
                codeSnippet = """Modifier.canimationTransition(
    visible = isVisible,
    enter = Canimation.Scale.Pop,
    exit = Canimation.Scale.Shrink,
)""",
            ),
            ExampleItem(
                title = "Slide Left / Slide Right",
                description = "Enter sliding left, exit sliding right",
                demoType = "transition",
                enterEffect = Canimation.Slide.Left,
                exitEffect = Canimation.Slide.Right,
                preset = CanimationPreset.SlideLeft,
                codeSnippet = """Modifier.canimationTransition(
    visible = isVisible,
    enter = Canimation.Slide.Left,
    exit = Canimation.Slide.Right,
)""",
            ),
            ExampleItem(
                title = "Zoom In / Zoom Out",
                description = "Enter zooming in, exit zooming out",
                demoType = "transition",
                enterEffect = Canimation.Zoom.In,
                exitEffect = Canimation.Zoom.Out,
                preset = CanimationPreset.ZoomIn,
                codeSnippet = """Modifier.canimationTransition(
    visible = isVisible,
    enter = Canimation.Zoom.In,
    exit = Canimation.Zoom.Out,
)""",
            ),
            ExampleItem(
                title = "Drop / Collapse",
                description = "Enter with drop, exit with collapse",
                demoType = "transition",
                enterEffect = Canimation.Entrance.Drop,
                exitEffect = Canimation.Morph.Collapse,
                preset = CanimationPreset.DropIn,
                codeSnippet = """Modifier.canimationTransition(
    visible = isVisible,
    enter = Canimation.Entrance.Drop,
    exit = Canimation.Morph.Collapse,
)""",
            ),
            ExampleItem(
                title = "Shared Axis X (symmetric)",
                description = "Same shared-axis both directions",
                demoType = "transition",
                enterEffect = Canimation.Material.SharedAxisX,
                exitEffect = Canimation.Material.SharedAxisX,
                preset = CanimationPreset.SharedAxisX,
                codeSnippet = """Modifier.canimationTransition(
    visible = isVisible,
    enter = Canimation.Material.SharedAxisX,
    exit = Canimation.Material.SharedAxisX,
)""",
            ),
        ),
    ),

    // 21. Stagger Patterns
    ExampleCategory(
        id = "stagger-patterns",
        title = "Stagger Patterns",
        subtitle = "Sequential staggered list animations",
        accentLabel = "PATTERN",
        tags = listOf("PATTERN"),
        examples = listOf(
            ExampleItem(
                title = "Stagger Fade Up",
                description = "Staggered fade-up list items",
                demoType = "stagger",
                effect = Canimation.Fade.Up,
                preset = CanimationPreset.FadeUp,
                codeSnippet = """items.forEachIndexed { i, item ->
    delay(i * Canimation.Stagger.Normal.toLong())
    Modifier.canimation(
        visible = true,
        effect = Canimation.Fade.Up,
    )
}""",
            ),
            ExampleItem(
                title = "Stagger Scale Pop",
                description = "Staggered pop-in list items",
                demoType = "stagger",
                effect = Canimation.Scale.Pop,
                preset = CanimationPreset.Pop,
                codeSnippet = """items.forEachIndexed { i, item ->
    delay(i * Canimation.Stagger.Quick.toLong())
    Modifier.canimation(
        visible = true,
        effect = Canimation.Scale.Pop,
    )
}""",
            ),
            ExampleItem(
                title = "Stagger Slide Right",
                description = "Staggered slide from left",
                demoType = "stagger",
                effect = Canimation.Slide.Right,
                preset = CanimationPreset.SlideRight,
                codeSnippet = """items.forEachIndexed { i, item ->
    delay(i * Canimation.Stagger.Slow.toLong())
    Modifier.canimation(
        visible = true,
        effect = Canimation.Slide.Right,
    )
}""",
            ),
            ExampleItem(
                title = "Stagger Bounce In",
                description = "Staggered bouncing list items",
                demoType = "stagger",
                effect = Canimation.Bounce.In,
                preset = CanimationPreset.BounceIn,
                codeSnippet = """items.forEachIndexed { i, item ->
    delay(i * Canimation.Stagger.Relaxed.toLong())
    Modifier.canimation(
        visible = true,
        effect = Canimation.Bounce.In,
    )
}""",
            ),
            ExampleItem(
                title = "Stagger Spring Up",
                description = "Staggered spring slide-up",
                demoType = "stagger",
                effect = Canimation.Spring.Up,
                preset = CanimationPreset.SpringSlideUp,
                codeSnippet = """items.forEachIndexed { i, item ->
    delay(i * Canimation.Stagger.Normal.toLong())
    Modifier.canimation(
        visible = true,
        effect = Canimation.Spring.Up,
    )
}""",
            ),
        ),
    ),

    // 22. Real World Patterns
    ExampleCategory(
        id = "real-world",
        title = "Real World Patterns",
        subtitle = "Practical animation patterns for apps",
        accentLabel = "UI",
        tags = listOf("UI", "PATTERN"),
        examples = listOf(
            ExampleItem(
                title = "Card Entry",
                description = "Card entering the screen",
                demoType = "realWorld",
                effect = Canimation.Fade.Up + Canimation.Scale.Subtle,
                preset = CanimationPreset.FadeUp,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.Up + Canimation.Scale.Subtle,
)""",
            ),
            ExampleItem(
                title = "List Item Stagger",
                description = "Staggered list item appearance",
                demoType = "realWorld",
                effect = Canimation.Slide.Right + Canimation.Fade.In,
                preset = CanimationPreset.SlideRight,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Slide.Right + Canimation.Fade.In,
)""",
            ),
            ExampleItem(
                title = "Notification Popup",
                description = "Notification appearing on screen",
                demoType = "realWorld",
                effect = Canimation.Entrance.Drop,
                preset = CanimationPreset.DropIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Entrance.Drop,
)""",
            ),
            ExampleItem(
                title = "Button Emphasis",
                description = "Button drawing attention on interaction",
                demoType = "emphasis",
                preset = CanimationPreset.Pulse,
                codeSnippet = """Modifier.canimationEmphasize(
    active = isPressed,
    preset = CanimationPreset.Pulse,
)""",
            ),
            ExampleItem(
                title = "Modal Entrance",
                description = "Modal dialog appearing",
                demoType = "realWorld",
                effect = Canimation.Scale.Pop + Canimation.Fade.In,
                preset = CanimationPreset.Pop,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.Pop + Canimation.Fade.In,
)""",
            ),
            ExampleItem(
                title = "Tab Content",
                description = "Tab content transitioning",
                demoType = "transition",
                enterEffect = Canimation.Material.FadeThrough,
                exitEffect = Canimation.Material.FadeThrough,
                preset = CanimationPreset.FadeThrough,
                codeSnippet = """Modifier.canimationTransition(
    visible = isVisible,
    enter = Canimation.Material.FadeThrough,
    exit = Canimation.Material.FadeThrough,
)""",
            ),
        ),
    ),

    // ── Text & Typography ──
    ExampleCategory(
        id = "text-typography",
        title = "Text & Typography",
        subtitle = "Animated text effects and typography",
        accentLabel = "TEXT",
        tags = listOf("TEXT"),
        examples = listOf(
            ExampleItem(
                title = "Typewriter Text",
                description = "Characters appear one by one like a typewriter",
                demoType = "component",
                componentKey = "TypewriterText",
                codeSnippet = """TypewriterText(
    text = "Welcome to my app!",
    typingSpeed = 50L,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Scramble Text",
                description = "Random characters resolve into target text",
                demoType = "component",
                componentKey = "ScrambleText",
                codeSnippet = """ScrambleText(
    targetText = "Hello, World!",
    scrambleDuration = 1200,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Wavy Text",
                description = "Letters oscillate in a wave pattern",
                demoType = "component",
                componentKey = "WavyText",
                codeSnippet = """WavyText(
    text = "Animated Wave",
    waveAmplitude = 10f,
    modifier = Modifier.padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Split Text Reveal",
                description = "Text splits and reveals with staggered animation",
                demoType = "component",
                componentKey = "SplitTextReveal",
                codeSnippet = """SplitTextReveal(
    text = "Split Reveal",
    delayPerChar = 80L,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Glitch Text",
                description = "Digital glitch distortion effect on text",
                demoType = "component",
                componentKey = "GlitchText",
                codeSnippet = """GlitchText(
    text = "SYSTEM ERROR",
    glitchIntensity = 0.3f,
    modifier = Modifier.padding(8.dp),
)""",
            ),
            ExampleItem(
                title = "Text Gradient Anim",
                description = "Animated gradient sweep across text",
                demoType = "component",
                componentKey = "TextGradientAnim",
                codeSnippet = """TextGradientAnim(
    text = "Gradient Text",
    colors = listOf(Color.Cyan, Color.Magenta),
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Rolling Digits",
                description = "Digits roll like a slot machine",
                demoType = "component",
                componentKey = "RollingDigits",
                codeSnippet = """RollingDigits(
    targetValue = 42,
    modifier = Modifier.padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Vertical Ticker",
                description = "Text scrolls vertically through values",
                demoType = "component",
                componentKey = "VerticalTicker",
                codeSnippet = """VerticalTicker(
    items = listOf("First", "Second", "Third"),
    modifier = Modifier.height(48.dp),
)""",
            ),
            ExampleItem(
                title = "Animated Underline Text",
                description = "Underline draws beneath text on appear",
                demoType = "component",
                componentKey = "AnimatedUnderlineText",
                codeSnippet = """AnimatedUnderlineText(
    text = "Hover Me",
    underlineColor = Color.Blue,
    modifier = Modifier.padding(8.dp),
)""",
            ),
            ExampleItem(
                title = "Blinking Cursor",
                description = "Terminal-style blinking cursor animation",
                demoType = "component",
                componentKey = "BlinkingCursor",
                codeSnippet = """BlinkingCursor(
    cursorChar = "|",
    blinkInterval = 500L,
    modifier = Modifier.padding(4.dp),
)""",
            ),
            ExampleItem(
                title = "Animated Gradient Text",
                description = "Gradient colors animate across text",
                demoType = "component",
                componentKey = "AnimatedGradientText",
                codeSnippet = """AnimatedGradientText(
    text = "Shimmering Label",
    colors = listOf(Color.Red, Color.Yellow, Color.Green),
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Reveal Text Effect",
                description = "Text reveals with a sliding mask",
                demoType = "component",
                componentKey = "RevealTextEffect",
                codeSnippet = """RevealTextEffect(
    text = "Revealed!",
    revealDuration = 1000,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Scatter Text",
                description = "Characters scatter and reassemble",
                demoType = "component",
                componentKey = "ScatterText",
                codeSnippet = """ScatterText(
    text = "Scatter Me",
    scatterRadius = 100f,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Text Line Reveal",
                description = "Text lines reveal one at a time",
                demoType = "component",
                componentKey = "TextLineReveal",
                codeSnippet = """TextLineReveal(
    lines = listOf("Line one", "Line two", "Line three"),
    delayPerLine = 200L,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Typewriter Delete",
                description = "Characters delete one by one in reverse",
                demoType = "component",
                componentKey = "TypewriterDelete",
                codeSnippet = """TypewriterDelete(
    text = "Deleting this text...",
    deleteSpeed = 40L,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
        ),
    ),

    // ── Cards & Surfaces ──
    ExampleCategory(
        id = "cards-surfaces",
        title = "Cards & Surfaces",
        subtitle = "Card animations, flips, and surface effects",
        accentLabel = "CARDS",
        tags = listOf("CARDS"),
        examples = listOf(
            ExampleItem(
                title = "Flip Card",
                description = "3D card flip between front and back",
                demoType = "component",
                componentKey = "FlipCard",
                codeSnippet = """FlipCard(
    frontText = "Product Name",
    backText = "Product Details",
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Tilt Card",
                description = "Card tilts toward pointer on hover",
                demoType = "component",
                componentKey = "TiltCard",
                codeSnippet = """TiltCard(
    content = { Text("Tilt Me") },
    maxTiltDegrees = 15f,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Card Border Trace",
                description = "Animated border traces around the card",
                demoType = "component",
                componentKey = "CardBorderTrace",
                codeSnippet = """CardBorderTrace(
    borderColor = Color.Cyan,
    traceDuration = 2000,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Card Lift Hover",
                description = "Card elevates with shadow on hover",
                demoType = "component",
                componentKey = "CardLiftHover",
                codeSnippet = """CardLiftHover(
    elevation = 12.dp,
    modifier = Modifier
        .fillMaxWidth()
        .height(120.dp),
)""",
            ),
            ExampleItem(
                title = "Card Gradient Border",
                description = "Animated gradient border effect",
                demoType = "component",
                componentKey = "CardGradientBorder",
                codeSnippet = """CardGradientBorder(
    colors = listOf(Color.Magenta, Color.Cyan),
    borderWidth = 2.dp,
    modifier = Modifier.size(180.dp),
)""",
            ),
            ExampleItem(
                title = "Card Expand Collapse",
                description = "Card smoothly expands and collapses",
                demoType = "component",
                componentKey = "CardExpandCollapse",
                codeSnippet = """CardExpandCollapse(
    title = "Tap to Expand",
    expandedContent = { Text("Expanded details here") },
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Card Parallax Tilt",
                description = "Parallax layers tilt with pointer movement",
                demoType = "component",
                componentKey = "CardParallaxTilt",
                codeSnippet = """CardParallaxTilt(
    parallaxIntensity = 20f,
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
)""",
            ),
            ExampleItem(
                title = "Card Glassmorphism",
                description = "Frosted glass card with blur effect",
                demoType = "component",
                componentKey = "CardGlassmorphism",
                codeSnippet = """CardGlassmorphism(
    blurRadius = 20.dp,
    tintColor = Color.White.copy(alpha = 0.2f),
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Card Reveal Wipe",
                description = "Content reveals with a wipe transition",
                demoType = "component",
                componentKey = "CardRevealWipe",
                codeSnippet = """CardRevealWipe(
    revealDirection = RevealDirection.Left,
    duration = 800,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Card Fan Stack",
                description = "Cards fan out from a stack",
                demoType = "component",
                componentKey = "CardFanStack",
                codeSnippet = """CardFanStack(
    cardCount = 5,
    fanAngle = 30f,
    modifier = Modifier.size(250.dp),
)""",
            ),
            ExampleItem(
                title = "Card Magnetic Snap",
                description = "Card snaps to position with magnetic feel",
                demoType = "component",
                componentKey = "CardMagneticSnap",
                codeSnippet = """CardMagneticSnap(
    snapThreshold = 50.dp,
    modifier = Modifier
        .fillMaxWidth()
        .height(150.dp),
)""",
            ),
            ExampleItem(
                title = "Card Shuffle",
                description = "Cards shuffle like a deck",
                demoType = "component",
                componentKey = "CardShuffle",
                codeSnippet = """CardShuffle(
    cardCount = 4,
    shuffleDuration = 600,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Stacked Cards",
                description = "Cards stacked with depth perspective",
                demoType = "component",
                componentKey = "StackedCards",
                codeSnippet = """StackedCards(
    cardCount = 3,
    offsetStep = 8.dp,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Card Stack Swipe",
                description = "Swipeable card stack like a dating app",
                demoType = "component",
                componentKey = "CardStackSwipe",
                codeSnippet = """CardStackSwipe(
    onSwipe = { direction -> /* handle */ },
    modifier = Modifier
        .fillMaxWidth()
        .height(300.dp),
)""",
            ),
        ),
    ),

    // ── Loading & Progress ──
    ExampleCategory(
        id = "loading-progress",
        title = "Loading & Progress",
        subtitle = "Loading indicators and progress animations",
        accentLabel = "LOADING",
        tags = listOf("LOADING"),
        examples = listOf(
            ExampleItem(
                title = "Pulse Loading Dots",
                description = "Dots pulse in sequence to indicate loading",
                demoType = "component",
                componentKey = "PulseLoadingDots",
                codeSnippet = """PulseLoadingDots(
    dotCount = 3,
    dotSize = 12.dp,
    color = Color.Blue,
    modifier = Modifier.padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Jumping Dots",
                description = "Dots jump up and down in sequence",
                demoType = "component",
                componentKey = "JumpingDots",
                codeSnippet = """JumpingDots(
    dotCount = 3,
    jumpHeight = 20.dp,
    modifier = Modifier.padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Shimmer Effect",
                description = "Shimmering placeholder for loading content",
                demoType = "component",
                componentKey = "ShimmerEffect",
                codeSnippet = """ShimmerEffect(
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
)""",
            ),
            ExampleItem(
                title = "Loading Spinner",
                description = "Classic spinning loader animation",
                demoType = "component",
                componentKey = "LoadingSpinner",
                codeSnippet = """LoadingSpinner(
    strokeWidth = 4.dp,
    color = Color.Blue,
    modifier = Modifier.size(48.dp),
)""",
            ),
            ExampleItem(
                title = "Loading Ripple",
                description = "Ripple rings expand outward from center",
                demoType = "component",
                componentKey = "LoadingRipple",
                codeSnippet = """LoadingRipple(
    rippleColor = Color.Cyan,
    rippleCount = 3,
    modifier = Modifier.size(80.dp),
)""",
            ),
            ExampleItem(
                title = "Skeleton Loader",
                description = "Skeleton placeholder with shimmer",
                demoType = "component",
                componentKey = "SkeletonLoader",
                codeSnippet = """SkeletonLoader(
    shape = SkeletonShape.RoundedRect,
    modifier = Modifier
        .fillMaxWidth()
        .height(20.dp),
)""",
            ),
            ExampleItem(
                title = "Progress Ring",
                description = "Circular progress indicator with animation",
                demoType = "component",
                componentKey = "ProgressRing",
                codeSnippet = """ProgressRing(
    progress = 0.75f,
    strokeWidth = 8.dp,
    color = Color.Green,
    modifier = Modifier.size(64.dp),
)""",
            ),
            ExampleItem(
                title = "Progress Steps",
                description = "Step-by-step progress with transitions",
                demoType = "component",
                componentKey = "ProgressSteps",
                codeSnippet = """ProgressSteps(
    totalSteps = 5,
    currentStep = 3,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Liquid Fill",
                description = "Liquid-style fill animation in a circle",
                demoType = "component",
                componentKey = "LiquidFill",
                codeSnippet = """LiquidFill(
    fillPercent = 0.6f,
    liquidColor = Color.Blue,
    modifier = Modifier.size(120.dp),
)""",
            ),
            ExampleItem(
                title = "Glow Progress Bar",
                description = "Progress bar with glowing edge effect",
                demoType = "component",
                componentKey = "GlowProgressBar",
                codeSnippet = """GlowProgressBar(
    progress = 0.8f,
    glowColor = Color.Cyan,
    modifier = Modifier
        .fillMaxWidth()
        .height(8.dp),
)""",
            ),
            ExampleItem(
                title = "Morph Progress Indicator",
                description = "Progress indicator that morphs between shapes",
                demoType = "component",
                componentKey = "MorphProgressIndicator",
                codeSnippet = """MorphProgressIndicator(
    progress = 0.5f,
    shape = MorphShape.Circle,
    modifier = Modifier.size(60.dp),
)""",
            ),
            ExampleItem(
                title = "Step Indicator",
                description = "Multi-step progress indicator",
                demoType = "component",
                componentKey = "StepIndicator",
                codeSnippet = """StepIndicator(
    steps = listOf("Cart", "Address", "Payment"),
    currentStep = 1,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Progress Scrubber",
                description = "Draggable progress scrubber control",
                demoType = "component",
                componentKey = "ProgressScrubber",
                codeSnippet = """ProgressScrubber(
    progress = 0.4f,
    onProgressChange = { newVal -> /* update */ },
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
        ),
    ),

    // ── Counters & Data ──
    ExampleCategory(
        id = "counters-data",
        title = "Counters & Data",
        subtitle = "Animated counters and data displays",
        accentLabel = "DATA",
        tags = listOf("DATA"),
        examples = listOf(
            ExampleItem(
                title = "Animated Counter",
                description = "Number animates up to target value",
                demoType = "component",
                componentKey = "AnimatedCounter",
                codeSnippet = """AnimatedCounter(
    targetValue = 1234,
    label = "Total Users",
    modifier = Modifier.padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Number Trend",
                description = "Number with up/down trend indicator",
                demoType = "component",
                componentKey = "NumberTrend",
                codeSnippet = """NumberTrend(
    value = 85.5f,
    trend = TrendDirection.Up,
    modifier = Modifier.padding(12.dp),
)""",
            ),
            ExampleItem(
                title = "Number Counter",
                description = "Simple animated number counter",
                demoType = "component",
                componentKey = "NumberCounter",
                codeSnippet = """NumberCounter(
    from = 0,
    to = 9999,
    duration = 2000,
    modifier = Modifier.padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Countdown Timer",
                description = "Animated countdown from a start value",
                demoType = "component",
                componentKey = "CountdownTimer",
                codeSnippet = """CountdownTimer(
    totalSeconds = 120,
    onFinished = { /* timer done */ },
    modifier = Modifier.padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Engagement Stats",
                description = "Animated social engagement statistics",
                demoType = "component",
                componentKey = "EngagementStats",
                codeSnippet = """EngagementStats(
    likes = 1200,
    comments = 340,
    shares = 89,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Price Switcher",
                description = "Animated price toggle between plans",
                demoType = "component",
                componentKey = "PriceSwitcher",
                codeSnippet = """PriceSwitcher(
    monthlyPrice = 9.99f,
    yearlyPrice = 99.99f,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
        ),
    ),

    // ── Navigation & Layout ──
    ExampleCategory(
        id = "navigation-layout",
        title = "Navigation & Layout",
        subtitle = "Animated navigation and layout components",
        accentLabel = "NAV",
        tags = listOf("NAV"),
        examples = listOf(
            ExampleItem(
                title = "Animated Tabs",
                description = "Tab bar with animated selection indicator",
                demoType = "component",
                componentKey = "AnimatedTabs",
                codeSnippet = """AnimatedTabs(
    tabs = listOf("Home", "Search", "Profile"),
    selectedIndex = 0,
    onTabSelected = { index -> /* navigate */ },
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Expandable Accordion",
                description = "Accordion sections expand and collapse",
                demoType = "component",
                componentKey = "ExpandableAccordion",
                codeSnippet = """ExpandableAccordion(
    title = "FAQ Section",
    content = { Text("Detailed answer here") },
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Mega Menu Reveal",
                description = "Large dropdown menu with reveal animation",
                demoType = "component",
                componentKey = "MegaMenuReveal",
                codeSnippet = """MegaMenuReveal(
    menuItems = listOf("Products", "Services", "About"),
    revealDuration = 400,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Accordion Menu",
                description = "Nested accordion menu with smooth transitions",
                demoType = "component",
                componentKey = "AccordionMenu",
                codeSnippet = """AccordionMenu(
    sections = listOf("General", "Account", "Privacy"),
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Smooth Tab Indicator",
                description = "Tab indicator slides smoothly between tabs",
                demoType = "component",
                componentKey = "SmoothTabIndicator",
                codeSnippet = """SmoothTabIndicator(
    tabs = listOf("All", "Active", "Archived"),
    selectedIndex = 0,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Circular Menu",
                description = "Menu items arranged in a circle with animation",
                demoType = "component",
                componentKey = "CircularMenu",
                codeSnippet = """CircularMenu(
    items = listOf("Share", "Edit", "Delete"),
    radius = 80.dp,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Segmented Control",
                description = "iOS-style segmented control with animation",
                demoType = "component",
                componentKey = "SegmentedControl",
                codeSnippet = """SegmentedControl(
    segments = listOf("Day", "Week", "Month"),
    selectedIndex = 0,
    onSegmentSelected = { index -> /* update */ },
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Elastic Drawer",
                description = "Side drawer with elastic spring animation",
                demoType = "component",
                componentKey = "ElasticDrawer",
                codeSnippet = """ElasticDrawer(
    drawerWidth = 280.dp,
    elasticity = 0.3f,
    modifier = Modifier.fillMaxHeight(),
)""",
            ),
            ExampleItem(
                title = "Scroll Direction Header",
                description = "Header hides/shows based on scroll direction",
                demoType = "component",
                componentKey = "ScrollDirectionHeader",
                codeSnippet = """ScrollDirectionHeader(
    title = "My Feed",
    collapseOnScroll = true,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
        ),
    ),

    // ── Interactive Controls ──
    ExampleCategory(
        id = "interactive-controls",
        title = "Interactive Controls",
        subtitle = "Buttons, toggles, and interactive elements",
        accentLabel = "INTERACTIVE",
        tags = listOf("INTERACTIVE"),
        examples = listOf(
            ExampleItem(
                title = "Hold To Confirm",
                description = "Long-press button with progress confirmation",
                demoType = "component",
                componentKey = "HoldToConfirm",
                codeSnippet = """HoldToConfirm(
    text = "Hold to Delete",
    holdDuration = 2000L,
    onConfirmed = { /* delete item */ },
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Swipe Actions",
                description = "Swipe to reveal action buttons",
                demoType = "component",
                componentKey = "SwipeActions",
                codeSnippet = """SwipeActions(
    onSwipeLeft = { /* archive */ },
    onSwipeRight = { /* delete */ },
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Multi State Badge",
                description = "Badge transitions between multiple states",
                demoType = "component",
                componentKey = "MultiStateBadge",
                codeSnippet = """MultiStateBadge(
    state = BadgeState.New,
    text = "3 new",
    modifier = Modifier.padding(4.dp),
)""",
            ),
            ExampleItem(
                title = "Elastic Pull",
                description = "Elastic pull-to-refresh interaction",
                demoType = "component",
                componentKey = "ElasticPull",
                codeSnippet = """ElasticPull(
    onRefresh = { /* reload data */ },
    elasticity = 0.5f,
    modifier = Modifier.fillMaxSize(),
)""",
            ),
            ExampleItem(
                title = "Expanding Search",
                description = "Search bar expands from icon on tap",
                demoType = "component",
                componentKey = "ExpandingSearch",
                codeSnippet = """ExpandingSearch(
    placeholder = "Search...",
    onSearch = { query -> /* filter */ },
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "iOS Slider",
                description = "iOS-style slider with smooth animation",
                demoType = "component",
                componentKey = "IOSSlider",
                codeSnippet = """IOSSlider(
    value = 0.5f,
    onValueChange = { newVal -> /* update */ },
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Checkbox Animation",
                description = "Animated checkbox with check mark draw",
                demoType = "component",
                componentKey = "CheckboxAnimation",
                codeSnippet = """CheckboxAnimation(
    checked = true,
    onCheckedChange = { isChecked -> /* update */ },
    modifier = Modifier.size(24.dp),
)""",
            ),
            ExampleItem(
                title = "Switch Animation",
                description = "Animated toggle switch with spring physics",
                demoType = "component",
                componentKey = "SwitchAnimation",
                codeSnippet = """SwitchAnimation(
    checked = false,
    onCheckedChange = { isOn -> /* toggle */ },
    modifier = Modifier.padding(8.dp),
)""",
            ),
            ExampleItem(
                title = "Magnetic Button",
                description = "Button attracted to pointer with magnetic effect",
                demoType = "component",
                componentKey = "MagneticButton",
                codeSnippet = """MagneticButton(
    text = "Hover Me",
    magneticRadius = 40.dp,
    onClick = { /* action */ },
    modifier = Modifier.padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Ripple Button",
                description = "Button with material ripple on tap",
                demoType = "component",
                componentKey = "RippleButton",
                codeSnippet = """RippleButton(
    text = "Tap Me",
    rippleColor = Color.White,
    onClick = { /* action */ },
    modifier = Modifier.padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Spring Toggle",
                description = "Toggle with spring bounce animation",
                demoType = "component",
                componentKey = "SpringToggle",
                codeSnippet = """SpringToggle(
    isOn = true,
    onToggle = { isOn -> /* update */ },
    modifier = Modifier.padding(8.dp),
)""",
            ),
            ExampleItem(
                title = "Spring Chip",
                description = "Chip component with spring selection animation",
                demoType = "component",
                componentKey = "SpringChip",
                codeSnippet = """SpringChip(
    label = "Selected",
    selected = true,
    onClick = { /* toggle */ },
    modifier = Modifier.padding(4.dp),
)""",
            ),
            ExampleItem(
                title = "Notification Badge",
                description = "Animated notification badge with count",
                demoType = "component",
                componentKey = "NotificationBadge",
                codeSnippet = """NotificationBadge(
    count = 5,
    badgeColor = Color.Red,
    modifier = Modifier.size(20.dp),
)""",
            ),
            ExampleItem(
                title = "Toast Notification",
                description = "Animated toast notification popup",
                demoType = "component",
                componentKey = "ToastNotification",
                codeSnippet = """ToastNotification(
    message = "Item saved successfully!",
    duration = 3000L,
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Coin Flip",
                description = "3D coin flip animation",
                demoType = "component",
                componentKey = "CoinFlip",
                codeSnippet = """CoinFlip(
    frontContent = { Text("Heads") },
    backContent = { Text("Tails") },
    modifier = Modifier.size(100.dp),
)""",
            ),
        ),
    ),

    // ── Visual Effects ──
    ExampleCategory(
        id = "visual-effects",
        title = "Visual Effects",
        subtitle = "Eye-catching visual animations and effects",
        accentLabel = "VISUAL",
        tags = listOf("VISUAL"),
        examples = listOf(
            ExampleItem(
                title = "Color Morph",
                description = "Smooth color morphing transitions",
                demoType = "component",
                componentKey = "ColorMorph",
                codeSnippet = """ColorMorph(
    colors = listOf(Color.Red, Color.Blue, Color.Green),
    duration = 3000,
    modifier = Modifier.size(150.dp),
)""",
            ),
            ExampleItem(
                title = "Morphing Shapes",
                description = "Shapes morph smoothly between forms",
                demoType = "component",
                componentKey = "MorphingShapes",
                codeSnippet = """MorphingShapes(
    fromShape = MorphShape.Circle,
    toShape = MorphShape.Star,
    modifier = Modifier.size(120.dp),
)""",
            ),
            ExampleItem(
                title = "Gradient Shift",
                description = "Animated gradient color shifting",
                demoType = "component",
                componentKey = "GradientShift",
                codeSnippet = """GradientShift(
    colors = listOf(Color.Magenta, Color.Cyan),
    shiftSpeed = 2000,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Breathing Glow",
                description = "Element pulses with a breathing glow",
                demoType = "component",
                componentKey = "BreathingGlow",
                codeSnippet = """BreathingGlow(
    glowColor = Color.Blue,
    glowRadius = 20.dp,
    modifier = Modifier.size(100.dp),
)""",
            ),
            ExampleItem(
                title = "Path Tracer",
                description = "Animated path drawing on canvas",
                demoType = "component",
                componentKey = "PathTracer",
                codeSnippet = """PathTracer(
    pathColor = Color.Cyan,
    strokeWidth = 3.dp,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Confetti Explosion",
                description = "Burst of confetti particles on trigger",
                demoType = "component",
                componentKey = "ConfettiExplosion",
                codeSnippet = """ConfettiExplosion(
    particleCount = 100,
    colors = listOf(Color.Red, Color.Yellow, Color.Blue),
    modifier = Modifier.fillMaxSize(),
)""",
            ),
            ExampleItem(
                title = "Wave Effect",
                description = "Audio visualizer-style wave bars",
                demoType = "component",
                componentKey = "WaveEffect",
                codeSnippet = """WaveEffect(
    waveColor = Color.Blue,
    amplitude = 20f,
    frequency = 2f,
    modifier = Modifier
        .fillMaxWidth()
        .height(100.dp),
)""",
            ),
            ExampleItem(
                title = "Sliding Reveal",
                description = "Content reveals with a sliding mask",
                demoType = "component",
                componentKey = "SlidingReveal",
                codeSnippet = """SlidingReveal(
    direction = RevealDirection.Left,
    duration = 800,
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
)""",
            ),
            ExampleItem(
                title = "Focus Blur Effect",
                description = "Focus shifts with blur transitions",
                demoType = "component",
                componentKey = "FocusBlurEffect",
                codeSnippet = """FocusBlurEffect(
    blurRadius = 10.dp,
    focusArea = FocusArea.Center,
    modifier = Modifier.fillMaxSize(),
)""",
            ),
            ExampleItem(
                title = "Expanding Rings",
                description = "Concentric rings expand outward",
                demoType = "component",
                componentKey = "ExpandingRings",
                codeSnippet = """ExpandingRings(
    ringCount = 3,
    ringColor = Color.Cyan,
    modifier = Modifier.size(150.dp),
)""",
            ),
            ExampleItem(
                title = "Floating Particles",
                description = "Ambient floating particles background",
                demoType = "component",
                componentKey = "FloatingParticles",
                codeSnippet = """FloatingParticles(
    particleCount = 30,
    particleColor = Color.White,
    modifier = Modifier.fillMaxSize(),
)""",
            ),
            ExampleItem(
                title = "Pulsing Avatar",
                description = "Avatar with pulsing ring animation",
                demoType = "component",
                componentKey = "PulsingAvatar",
                codeSnippet = """PulsingAvatar(
    imageSize = 64.dp,
    pulseColor = Color.Green,
    modifier = Modifier.size(80.dp),
)""",
            ),
            ExampleItem(
                title = "Pulse Radar",
                description = "Radar-style pulse scanning animation",
                demoType = "component",
                componentKey = "PulseRadar",
                codeSnippet = """PulseRadar(
    radarColor = Color.Green,
    pulseCount = 3,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Heartbeat Line",
                description = "ECG-style heartbeat line animation",
                demoType = "component",
                componentKey = "HeartbeatLine",
                codeSnippet = """HeartbeatLine(
    lineColor = Color.Red,
    strokeWidth = 2.dp,
    modifier = Modifier
        .fillMaxWidth()
        .height(60.dp),
)""",
            ),
        ),
    ),

    // ── Physics & Motion ──
    ExampleCategory(
        id = "physics-motion",
        title = "Physics & Motion",
        subtitle = "Physics-based and spring-driven animations",
        accentLabel = "PHYSICS",
        tags = listOf("PHYSICS"),
        examples = listOf(
            ExampleItem(
                title = "Bouncy Spring List",
                description = "List items enter with spring bounce",
                demoType = "component",
                componentKey = "BouncySpringList",
                codeSnippet = """BouncySpringList(
    items = listOf("Item 1", "Item 2", "Item 3"),
    stiffness = Spring.StiffnessLow,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Stagger From Center",
                description = "Items stagger outward from center",
                demoType = "component",
                componentKey = "StaggerFromCenter",
                codeSnippet = """StaggerFromCenter(
    itemCount = 7,
    staggerDelay = 100L,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Ticker Marquee",
                description = "Scrolling ticker text marquee",
                demoType = "component",
                componentKey = "TickerMarquee",
                codeSnippet = """TickerMarquee(
    text = "Breaking News: Jetpack Compose is awesome!",
    speed = 60.dp,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Spring Chain",
                description = "Chain of elements connected by springs",
                demoType = "component",
                componentKey = "SpringChain",
                codeSnippet = """SpringChain(
    nodeCount = 5,
    stiffness = Spring.StiffnessMedium,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Orbit Animation",
                description = "Objects orbit around a center point",
                demoType = "component",
                componentKey = "OrbitAnimation",
                codeSnippet = """OrbitAnimation(
    orbitRadius = 80.dp,
    orbitSpeed = 3000L,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Parallax Layers",
                description = "Layered parallax scrolling effect",
                demoType = "component",
                componentKey = "ParallaxLayers",
                codeSnippet = """ParallaxLayers(
    layerCount = 3,
    depthFactor = 0.5f,
    modifier = Modifier.fillMaxSize(),
)""",
            ),
            ExampleItem(
                title = "DNA Helix",
                description = "Animated double helix DNA structure",
                demoType = "component",
                componentKey = "DnaHelix",
                codeSnippet = """DnaHelix(
    helixColor = Color.Cyan,
    rotationSpeed = 4000L,
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
)""",
            ),
            ExampleItem(
                title = "Pendulum Swing",
                description = "Pendulum swinging back and forth",
                demoType = "component",
                componentKey = "PendulumSwing",
                codeSnippet = """PendulumSwing(
    swingAngle = 30f,
    period = 2000L,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Bouncing Ball",
                description = "Ball bouncing with gravity physics",
                demoType = "component",
                componentKey = "BouncingBall",
                codeSnippet = """BouncingBall(
    ballSize = 24.dp,
    bounceHeight = 100.dp,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Slinky Spring",
                description = "Slinky-style cascading spring animation",
                demoType = "component",
                componentKey = "SlinkySpring",
                codeSnippet = """SlinkySpring(
    coilCount = 8,
    springColor = Color.Gray,
    modifier = Modifier
        .fillMaxWidth()
        .height(150.dp),
)""",
            ),
        ),
    ),

    // ── Charts & Data Viz ──
    ExampleCategory(
        id = "charts-dataviz",
        title = "Charts & Data Viz",
        subtitle = "Animated charts and data visualizations",
        accentLabel = "CHARTS",
        tags = listOf("CHARTS"),
        examples = listOf(
            ExampleItem(
                title = "Animated Pie Chart",
                description = "Pie chart with animated segment reveals",
                demoType = "component",
                componentKey = "AnimatedPieChart",
                codeSnippet = """AnimatedPieChart(
    data = listOf(30f, 25f, 20f, 15f, 10f),
    colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Cyan),
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Animated Bar Chart",
                description = "Bar chart with animated bar growth",
                demoType = "component",
                componentKey = "AnimatedBarChart",
                codeSnippet = """AnimatedBarChart(
    data = listOf(80f, 45f, 90f, 60f, 75f),
    barColor = Color.Blue,
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
)""",
            ),
        ),
    ),

    // ── Galleries & Lists ──
    ExampleCategory(
        id = "galleries-lists",
        title = "Galleries & Lists",
        subtitle = "Animated galleries, carousels, and lists",
        accentLabel = "GALLERY",
        tags = listOf("GALLERY"),
        examples = listOf(
            ExampleItem(
                title = "Infinite Loading List",
                description = "List that loads more items on scroll",
                demoType = "component",
                componentKey = "InfiniteLoadingList",
                codeSnippet = """InfiniteLoadingList(
    onLoadMore = { /* fetch next page */ },
    modifier = Modifier.fillMaxSize(),
)""",
            ),
            ExampleItem(
                title = "Horizontal Scroll Gallery",
                description = "Horizontally scrolling image gallery",
                demoType = "component",
                componentKey = "HorizontalScrollGallery",
                codeSnippet = """HorizontalScrollGallery(
    itemCount = 10,
    itemWidth = 150.dp,
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
)""",
            ),
            ExampleItem(
                title = "Vertical Carousel",
                description = "Vertically scrolling carousel with snap",
                demoType = "component",
                componentKey = "VerticalCarousel",
                codeSnippet = """VerticalCarousel(
    itemCount = 5,
    itemHeight = 200.dp,
    modifier = Modifier.fillMaxSize(),
)""",
            ),
            ExampleItem(
                title = "Waterfall Grid",
                description = "Pinterest-style waterfall grid layout",
                demoType = "component",
                componentKey = "WaterfallGrid",
                codeSnippet = """WaterfallGrid(
    columns = 2,
    spacing = 8.dp,
    modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
)""",
            ),
            ExampleItem(
                title = "Zoom Hero Image",
                description = "Image zooms to hero view on tap",
                demoType = "component",
                componentKey = "ZoomHeroImage",
                codeSnippet = """ZoomHeroImage(
    maxZoom = 3f,
    modifier = Modifier
        .fillMaxWidth()
        .height(300.dp),
)""",
            ),
        ),
    ),

    // ── Audio & Visualization ──
    ExampleCategory(
        id = "audio-viz",
        title = "Audio & Visualization",
        subtitle = "Waveforms, visual effects, and animated graphics",
        accentLabel = "VISUAL",
        tags = listOf("VISUAL"),
        examples = listOf(
            ExampleItem(
                title = "Waveform Visualizer",
                description = "Animated audio waveform bars",
                demoType = "component",
                componentKey = "WaveformVisualizer",
                codeSnippet = """WaveformVisualizer(
    barCount = 20,
    barColor = Color.Cyan,
    modifier = Modifier
        .fillMaxWidth()
        .height(100.dp),
)""",
            ),
            ExampleItem(
                title = "Matrix Rain",
                description = "Columns of cascading characters",
                demoType = "component",
                componentKey = "MatrixRain",
                codeSnippet = """MatrixRain(
    columnCount = 15,
    rainColor = Color.Green,
    modifier = Modifier.fillMaxSize(),
)""",
            ),
            ExampleItem(
                title = "Music Equalizer",
                description = "Animated equalizer bars",
                demoType = "component",
                componentKey = "MusicEqualizer",
                codeSnippet = """MusicEqualizer(
    barCount = 5,
    barColor = Color.Magenta,
    modifier = Modifier
        .fillMaxWidth()
        .height(60.dp),
)""",
            ),
            ExampleItem(
                title = "Water Droplet",
                description = "Water droplet ripple effect",
                demoType = "component",
                componentKey = "WaterDroplet",
                codeSnippet = """WaterDroplet(
    dropColor = Color.Cyan,
    rippleRadius = 40.dp,
    modifier = Modifier.size(120.dp),
)""",
            ),
        ),
    ),

    // ── Modern UI Components ──
    ExampleCategory(
        id = "modern-ui",
        title = "Modern UI Components",
        subtitle = "Interactive buttons, cards, indicators, and controls",
        accentLabel = "UI",
        tags = listOf("UI"),
        examples = listOf(
            ExampleItem(
                title = "Pulse Button",
                description = "Button that pulses when idle",
                demoType = "component",
                componentKey = "PulseButton",
                codeSnippet = """PulseButton(
    text = "Tap Me",
    pulseColor = Color.Blue,
    onClick = { /* action */ },
    modifier = Modifier.padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Neumorphism Card",
                description = "Card with animated shadow depth",
                demoType = "component",
                componentKey = "NeumorphismCard",
                codeSnippet = """NeumorphismCard(
    shadowColor = Color.Gray,
    elevation = 8.dp,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Gradient Border Card",
                description = "Card with animated gradient border",
                demoType = "component",
                componentKey = "GradientBorderCard",
                codeSnippet = """GradientBorderCard(
    colors = listOf(Color.Magenta, Color.Cyan),
    borderWidth = 2.dp,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Flip Counter",
                description = "Digit flip counter",
                demoType = "component",
                componentKey = "FlipCounter",
                codeSnippet = """FlipCounter(
    targetValue = 42,
    digitCount = 2,
    modifier = Modifier.padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Expandable Chip",
                description = "Chip that expands with content",
                demoType = "component",
                componentKey = "ExpandableChip",
                codeSnippet = """ExpandableChip(
    label = "Details",
    expandedContent = { Text("Extra info") },
    modifier = Modifier.padding(8.dp),
)""",
            ),
            ExampleItem(
                title = "Stacked Notifications",
                description = "Stacked notification cards",
                demoType = "component",
                componentKey = "StackedNotifications",
                codeSnippet = """StackedNotifications(
    notifications = listOf("New message", "Update available"),
    maxVisible = 3,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Circular Reveal Card",
                description = "Card with circular reveal animation",
                demoType = "component",
                componentKey = "CircularRevealCard",
                codeSnippet = """CircularRevealCard(
    revealDuration = 600,
    modifier = Modifier.size(200.dp),
)""",
            ),
            ExampleItem(
                title = "Typing Indicator",
                description = "Chat typing indicator (3 bouncing dots)",
                demoType = "component",
                componentKey = "TypingIndicator",
                codeSnippet = """TypingIndicator(
    dotCount = 3,
    dotColor = Color.Gray,
    modifier = Modifier.padding(8.dp),
)""",
            ),
            ExampleItem(
                title = "Skeleton Text",
                description = "Skeleton loading for text content",
                demoType = "component",
                componentKey = "SkeletonText",
                codeSnippet = """SkeletonText(
    lineCount = 3,
    lineHeight = 16.dp,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Swipe Card",
                description = "Card that can be swiped away",
                demoType = "component",
                componentKey = "SwipeCard",
                codeSnippet = """SwipeCard(
    onSwipeLeft = { /* dismiss */ },
    onSwipeRight = { /* accept */ },
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
)""",
            ),
            ExampleItem(
                title = "Animated Checkmark",
                description = "Animated checkmark with circle",
                demoType = "component",
                componentKey = "AnimatedCheckmark",
                codeSnippet = """AnimatedCheckmark(
    checked = true,
    checkColor = Color.Green,
    modifier = Modifier.size(48.dp),
)""",
            ),
            ExampleItem(
                title = "Rotating Cube",
                description = "2D representation of a rotating cube",
                demoType = "component",
                componentKey = "RotatingCube",
                codeSnippet = """RotatingCube(
    cubeSize = 100.dp,
    rotationSpeed = 3000L,
    modifier = Modifier.size(150.dp),
)""",
            ),
            ExampleItem(
                title = "Slot Machine",
                description = "Simple slot machine with rolling numbers",
                demoType = "component",
                componentKey = "SlotMachine",
                codeSnippet = """SlotMachine(
    reelCount = 3,
    spinDuration = 2000L,
    modifier = Modifier.padding(16.dp),
)""",
            ),
            ExampleItem(
                title = "Typewriter Cursor",
                description = "Blinking cursor with text typing effect",
                demoType = "component",
                componentKey = "TypewriterCursor",
                codeSnippet = """TypewriterCursor(
    text = "Typing effect...",
    cursorChar = "_",
    typingSpeed = 80L,
    modifier = Modifier.fillMaxWidth(),
)""",
            ),
            ExampleItem(
                title = "Radial Progress",
                description = "Circular progress with animated arc",
                demoType = "component",
                componentKey = "RadialProgress",
                codeSnippet = """RadialProgress(
    progress = 0.7f,
    strokeWidth = 6.dp,
    color = Color.Blue,
    modifier = Modifier.size(80.dp),
)""",
            ),
        ),
    ),
    ExampleCategory(
        id = "wave",
        title = "Wave",
        subtitle = "Wave-like motion effects",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE"),
        examples = listOf(
            ExampleItem("Wave Gentle", "Gentle floating wave motion", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Gentle,\n)", effect = Canimation.Wave.Gentle),
            ExampleItem("Wave Strong", "Strong wave with rotation", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Strong,\n)", effect = Canimation.Wave.Strong),
            ExampleItem("Wave Ripple", "Ripple expansion effect", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Ripple,\n)", effect = Canimation.Wave.Ripple),
            ExampleItem("Wave Float", "Floating upward effect", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Float,\n)", effect = Canimation.Wave.Float),
            ExampleItem("Wave Drift", "Gentle diagonal drift", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Drift,\n)", effect = Canimation.Wave.Drift),
        ),
    ),
    ExampleCategory(
        id = "glitch",
        title = "Glitch",
        subtitle = "Digital glitch effects",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE"),
        examples = listOf(
            ExampleItem("Glitch In", "Digital glitch entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Glitch.In,\n)", effect = Canimation.Glitch.In),
            ExampleItem("Glitch Shake", "Glitchy shake", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Glitch.Shake,\n)", effect = Canimation.Glitch.Shake),
            ExampleItem("Glitch Flicker", "Flickering appearance", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Glitch.Flicker,\n)", effect = Canimation.Glitch.Flicker),
            ExampleItem("Glitch Distort", "Distorted entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Glitch.Distort,\n)", effect = Canimation.Glitch.Distort),
        ),
    ),
    ExampleCategory(
        id = "elastic",
        title = "Elastic",
        subtitle = "Elastic spring-like effects",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE"),
        examples = listOf(
            ExampleItem("Elastic In", "Elastic stretch entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Elastic.In,\n)", effect = Canimation.Elastic.In),
            ExampleItem("Elastic Stretch", "Full elastic stretch", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Elastic.Stretch,\n)", effect = Canimation.Elastic.Stretch),
            ExampleItem("Elastic Squash", "Squash compression", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Elastic.Squash,\n)", effect = Canimation.Elastic.Squash),
            ExampleItem("Elastic Snap", "Snappy elastic entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Elastic.Snap,\n)", effect = Canimation.Elastic.Snap),
            ExampleItem("Elastic Wobble", "Wobbly elastic", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Elastic.Wobble,\n)", effect = Canimation.Elastic.Wobble),
        ),
    ),
    ExampleCategory(
        id = "cinematic",
        title = "Cinematic",
        subtitle = "Cinematic film-inspired effects",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE"),
        examples = listOf(
            ExampleItem("Cinematic Curtain", "Curtain reveal from top", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Cinematic.Curtain,\n)", effect = Canimation.Cinematic.Curtain),
            ExampleItem("Cinematic Zoom Pan", "Camera zoom pan", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Cinematic.ZoomPan,\n)", effect = Canimation.Cinematic.ZoomPan),
            ExampleItem("Cinematic Dolly", "Dolly zoom effect", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Cinematic.Dolly,\n)", effect = Canimation.Cinematic.Dolly),
            ExampleItem("Cinematic Reveal", "Subtle cinematic reveal", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Cinematic.Reveal,\n)", effect = Canimation.Cinematic.Reveal),
            ExampleItem("Cinematic Fade", "Fade to black style", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Cinematic.FadeToBlack,\n)", effect = Canimation.Cinematic.FadeToBlack),
            ExampleItem("Cinematic Dramatic", "Dramatic entrance", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Cinematic.Dramatic,\n)", effect = Canimation.Cinematic.Dramatic),
        ),
    ),
    ExampleCategory(
        id = "playful",
        title = "Playful",
        subtitle = "Playful fun effects",
        accentLabel = "ENTRANCE",
        tags = listOf("ENTRANCE"),
        examples = listOf(
            ExampleItem("Playful Wiggle", "Fun wiggle entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Playful.Wiggle,\n)", effect = Canimation.Playful.Wiggle),
            ExampleItem("Playful Hop", "Hop bounce entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Playful.Hop,\n)", effect = Canimation.Playful.Hop),
            ExampleItem("Playful Spin", "Playful spin entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Playful.Spin,\n)", effect = Canimation.Playful.Spin),
            ExampleItem("Playful Pop", "Quick playful pop", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Playful.Pop,\n)", effect = Canimation.Playful.Pop),
            ExampleItem("Playful Twirl", "Twirling entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Playful.Twirl,\n)", effect = Canimation.Playful.Twirl),
            ExampleItem("Playful Boing", "Spring boing effect", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Playful.Boing,\n)", effect = Canimation.Playful.Boing),
        ),
    ),
    ExampleCategory(
        id = "diagonal",
        title = "Diagonal",
        subtitle = "Diagonal movement entries",
        accentLabel = "MOVEMENT",
        tags = listOf("ENTRANCE", "MOVEMENT"),
        examples = listOf(
            ExampleItem("Diagonal Top Left", "Enter from top-left corner", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Diagonal.TopLeft,\n)", effect = Canimation.Diagonal.TopLeft),
            ExampleItem("Diagonal Top Right", "Enter from top-right corner", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Diagonal.TopRight,\n)", effect = Canimation.Diagonal.TopRight),
            ExampleItem("Diagonal Bottom Left", "Enter from bottom-left corner", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Diagonal.BottomLeft,\n)", effect = Canimation.Diagonal.BottomLeft),
            ExampleItem("Diagonal Bottom Right", "Enter from bottom-right corner", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Diagonal.BottomRight,\n)", effect = Canimation.Diagonal.BottomRight),
            ExampleItem("Diagonal Subtle", "Subtle diagonal entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Diagonal.Subtle,\n)", effect = Canimation.Diagonal.Subtle),
        ),
    ),
    ExampleCategory(
        id = "shrink",
        title = "Shrink",
        subtitle = "Shrink-from-large entry animations",
        accentLabel = "SCALE",
        tags = listOf("ENTRANCE", "SCALE"),
        examples = listOf(
            ExampleItem("Shrink Out", "Shrink from oversized", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Shrink.Out,\n)", effect = Canimation.Shrink.Out),
            ExampleItem("Shrink Subtle", "Subtle shrink entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Shrink.Subtle,\n)", effect = Canimation.Shrink.Subtle),
            ExampleItem("Shrink Rotate", "Shrink with rotation", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Shrink.Rotate,\n)", effect = Canimation.Shrink.Rotate),
            ExampleItem("Shrink Fade Down", "Shrink and drift down", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Shrink.FadeDown,\n)", effect = Canimation.Shrink.FadeDown),
        ),
    ),
    ExampleCategory(
        id = "tilt",
        title = "Tilt",
        subtitle = "Tilting skew entries",
        accentLabel = "ROTATE",
        tags = listOf("ENTRANCE", "ROTATE"),
        examples = listOf(
            ExampleItem("Tilt Left", "Tilt in from left", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Tilt.Left,\n)", effect = Canimation.Tilt.Left),
            ExampleItem("Tilt Right", "Tilt in from right", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Tilt.Right,\n)", effect = Canimation.Tilt.Right),
            ExampleItem("Tilt Up", "Tilt entry from above", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Tilt.Up,\n)", effect = Canimation.Tilt.Up),
            ExampleItem("Tilt Down", "Tilt entry from below", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Tilt.Down,\n)", effect = Canimation.Tilt.Down),
            ExampleItem("Tilt Swing", "Swinging tilt entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Tilt.Swing,\n)", effect = Canimation.Tilt.Swing),
        ),
    ),
    ExampleCategory(
        id = "float",
        title = "Float",
        subtitle = "Gentle floating animations",
        accentLabel = "SUBTLE",
        tags = listOf("ENTRANCE", "SUBTLE"),
        examples = listOf(
            ExampleItem("Float Up", "Float up gently", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Up,\n)", effect = Canimation.Float.Up),
            ExampleItem("Float Down", "Float down gently", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Down,\n)", effect = Canimation.Float.Down),
            ExampleItem("Float Gentle", "Very gentle float", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Gentle,\n)", effect = Canimation.Float.Gentle),
            ExampleItem("Float Scale Up", "Float with subtle scale", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.ScaleUp,\n)", effect = Canimation.Float.ScaleUp),
        ),
    ),
    ExampleCategory(
        id = "drop",
        title = "Drop",
        subtitle = "Gravity-like drop entries",
        accentLabel = "MOVEMENT",
        tags = listOf("ENTRANCE", "MOVEMENT"),
        examples = listOf(
            ExampleItem("Drop In", "Standard drop entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Drop.In,\n)", effect = Canimation.Drop.In),
            ExampleItem("Drop Heavy", "Heavy slam drop", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Drop.Heavy,\n)", effect = Canimation.Drop.Heavy),
            ExampleItem("Drop Light", "Light feather drop", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Drop.Light,\n)", effect = Canimation.Drop.Light),
            ExampleItem("Drop Rotate", "Drop with rotation", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Drop.Rotate,\n)", effect = Canimation.Drop.Rotate),
        ),
    ),
    ExampleCategory(
        id = "rise",
        title = "Rise",
        subtitle = "Ascending rise entries",
        accentLabel = "MOVEMENT",
        tags = listOf("ENTRANCE", "MOVEMENT"),
        examples = listOf(
            ExampleItem("Rise In", "Standard rise entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rise.In,\n)", effect = Canimation.Rise.In),
            ExampleItem("Rise Slow", "Slow rising entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rise.Slow,\n)", effect = Canimation.Rise.Slow),
            ExampleItem("Rise Scale", "Rise with scale", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rise.Scale,\n)", effect = Canimation.Rise.Scale),
            ExampleItem("Rise Rotate", "Rise with rotation", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rise.Rotate,\n)", effect = Canimation.Rise.Rotate),
        ),
    ),
    ExampleCategory(
        id = "stretch",
        title = "Stretch",
        subtitle = "Stretch deformation entries",
        accentLabel = "SCALE",
        tags = listOf("ENTRANCE", "SCALE"),
        examples = listOf(
            ExampleItem("Stretch Horizontal", "Horizontal stretch entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Stretch.Horizontal,\n)", effect = Canimation.Stretch.Horizontal),
            ExampleItem("Stretch Vertical", "Vertical stretch entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Stretch.Vertical,\n)", effect = Canimation.Stretch.Vertical),
            ExampleItem("Stretch Both", "Stretch from both axes", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Stretch.Both,\n)", effect = Canimation.Stretch.Both),
            ExampleItem("Stretch Snap", "Stretch with snap", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Stretch.Snap,\n)", effect = Canimation.Stretch.Snap),
        ),
    ),
    ExampleCategory(
        id = "navigation-ui",
        title = "Navigation UI",
        subtitle = "Animated navigation components",
        accentLabel = "UI",
        tags = listOf("UI", "NAVIGATION"),
        examples = listOf(
            ExampleItem("Animated Breadcrumb", "Staggered breadcrumb trail", "AnimatedBreadcrumb(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Up,\n))", demoType = "component", componentKey = "AnimatedBreadcrumb"),
            ExampleItem("Stepper Progress", "Multi-step progress indicator", "StepperProgress(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = "component", componentKey = "StepperProgress"),
            ExampleItem("Animated Nav Item", "Bottom nav with animated selection", "AnimatedNavItem(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Drop.Light,\n))", demoType = "component", componentKey = "AnimatedNavItem"),
        ),
    ),
    ExampleCategory(
        id = "data-display",
        title = "Data Display",
        subtitle = "Animated data visualization components",
        accentLabel = "UI",
        tags = listOf("UI", "DATA"),
        examples = listOf(
            ExampleItem("Animated Timeline", "Staggered event timeline", "AnimatedTimeline(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rise.In,\n))", demoType = "component", componentKey = "AnimatedTimeline"),
            ExampleItem("Animated Stat Card", "Stats card with tilt entry", "AnimatedStatCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Tilt.Swing,\n))", demoType = "component", componentKey = "AnimatedStatCard"),
            ExampleItem("Animated KPI", "Key performance indicators", "AnimatedKpi(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rise.Scale,\n))", demoType = "component", componentKey = "AnimatedKpi"),
            ExampleItem("Animated Rating", "Star rating animation", "AnimatedRating(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = "component", componentKey = "AnimatedRating"),
        ),
    ),
    ExampleCategory(
        id = "interactive-controls",
        title = "Interactive Controls",
        subtitle = "Animated interactive UI controls",
        accentLabel = "UI",
        tags = listOf("UI", "INTERACTIVE"),
        examples = listOf(
            ExampleItem("Animated Tag Cloud", "Floating tag cloud entry", "AnimatedTagCloud(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.ScaleUp,\n))", demoType = "component", componentKey = "AnimatedTagCloud"),
            ExampleItem("Color Swatch Picker", "Animated color selector", "ColorSwatchPicker(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Elastic.Snap,\n))", demoType = "component", componentKey = "ColorSwatchPicker"),
            ExampleItem("Animated Toggle Card", "Settings toggle with stretch", "AnimatedToggleCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Stretch.Horizontal,\n))", demoType = "component", componentKey = "AnimatedToggleCard"),
            ExampleItem("Animated Chip Group", "Filter chip group animation", "AnimatedChipGroup(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Gentle,\n))", demoType = "component", componentKey = "AnimatedChipGroup"),
            ExampleItem("Animated Pricing Toggle", "Monthly/Annual price switch", "AnimatedPricingToggle(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Shrink.Subtle,\n))", demoType = "component", componentKey = "AnimatedPricingToggle"),
        ),
    ),
    ExampleCategory(
        id = "visual-effects",
        title = "Visual Effects",
        subtitle = "Canvas-based visual animations",
        accentLabel = "CANVAS",
        tags = listOf("UI", "CANVAS"),
        examples = listOf(
            ExampleItem("Pulse Ring", "Expanding pulse ring animation", "PulseRing(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Expand,\n))", demoType = "component", componentKey = "PulseRing"),
            ExampleItem("Wave Progress Bar", "Sine wave progress indicator", "WaveProgressBar(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Gentle,\n))", demoType = "component", componentKey = "WaveProgressBar"),
            ExampleItem("Animated Sine Wave", "Continuous sine wave", "AnimatedSineWave(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Drift,\n))", demoType = "component", componentKey = "AnimatedSineWave"),
        ),
    ),
    ExampleCategory(
        id = "layout-patterns",
        title = "Layout Patterns",
        subtitle = "Animated layout components",
        accentLabel = "LAYOUT",
        tags = listOf("UI", "LAYOUT"),
        examples = listOf(
            ExampleItem("Stacked Avatars", "Overlapping avatar group", "StackedAvatars(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Diagonal.Subtle,\n))", demoType = "component", componentKey = "StackedAvatars"),
            ExampleItem("Animated Code Block", "Line-by-line code reveal", "AnimatedCodeBlock(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.LeftSubtle,\n))", demoType = "component", componentKey = "AnimatedCodeBlock"),
            ExampleItem("Animated List Item", "Staggered list entry", "AnimatedListItem(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Tilt.Left,\n))", demoType = "component", componentKey = "AnimatedListItem"),
            ExampleItem("Animated Feature Grid", "Feature grid with diagonal entry", "AnimatedFeatureGrid(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Diagonal.BottomRight,\n))", demoType = "component", componentKey = "AnimatedFeatureGrid"),
            ExampleItem("Animated Search Result", "Floating search results", "AnimatedSearchResult(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Gentle,\n))", demoType = "component", componentKey = "AnimatedSearchResult"),
        ),
    ),
    // ─── ComponentAnimations7 categories ───
    ExampleCategory(
        id = "text-animations",
        title = "Text & Typography",
        subtitle = "Animated text and typography effects",
        accentLabel = "TEXT",
        tags = listOf("TEXT", "UI"),
        examples = listOf(
            ExampleItem("Text Fade Reveal", "Word-by-word fade reveal", "TextFadeReveal(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Up,\n))", demoType = "component", componentKey = "TextFadeReveal"),
            ExampleItem("Text Count Up", "Animated number counter", "TextCountUp(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = "component", componentKey = "TextCountUp"),
            ExampleItem("Text Highlighter", "Sweeping text highlight", "TextHighlighter(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Reveal.Left,\n))", demoType = "component", componentKey = "TextHighlighter"),
            ExampleItem("Text Shuffle Word", "Cycling word animation", "TextShuffleWord(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Gentle,\n))", demoType = "component", componentKey = "TextShuffleWord"),
            ExampleItem("Text Gradient Reveal", "Gradient sweep text reveal", "TextGradientReveal(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.RightSubtle,\n))", demoType = "component", componentKey = "TextGradientReveal"),
            ExampleItem("Text Rotate Words", "Rotating word carousel", "TextRotateWords(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rotate.In,\n))", demoType = "component", componentKey = "TextRotateWords"),
        ),
    ),
    ExampleCategory(
        id = "card-components",
        title = "Card Components",
        subtitle = "Rich animated card layouts",
        accentLabel = "CARDS",
        tags = listOf("CARDS", "UI"),
        examples = listOf(
            ExampleItem("Profile Card", "User profile with avatar", "ProfileCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = "component", componentKey = "ProfileCard"),
            ExampleItem("Pricing Card", "Animated pricing plan", "PricingCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rise.In,\n))", demoType = "component", componentKey = "PricingCard"),
            ExampleItem("Notification Card", "Alert notification card", "NotificationCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.DownSubtle,\n))", demoType = "component", componentKey = "NotificationCard"),
            ExampleItem("Testimonial Card", "Customer review card", "TestimonialCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Gentle,\n))", demoType = "component", componentKey = "TestimonialCard"),
            ExampleItem("Metric Card", "Data metric display card", "MetricCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Tilt.Swing,\n))", demoType = "component", componentKey = "MetricCard"),
            ExampleItem("Product Card", "E-commerce product card", "ProductCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Up,\n))", demoType = "component", componentKey = "ProductCard"),
        ),
    ),
    ExampleCategory(
        id = "nav-components",
        title = "Navigation Components",
        subtitle = "Animated navigation and menu patterns",
        accentLabel = "NAV",
        tags = listOf("NAV", "NAVIGATION"),
        examples = listOf(
            ExampleItem("Tab Bar Indicator", "Sliding tab selection indicator", "TabBarIndicator(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.RightSubtle,\n))", demoType = "component", componentKey = "TabBarIndicator"),
            ExampleItem("Side Menu Reveal", "Staggered side menu entry", "SideMenuReveal(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.LeftSubtle,\n))", demoType = "component", componentKey = "SideMenuReveal"),
            ExampleItem("Pagination Dots", "Page indicator dots", "PaginationDots(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = "component", componentKey = "PaginationDots"),
            ExampleItem("Command Palette", "Quick command search palette", "CommandPalette(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Down,\n))", demoType = "component", componentKey = "CommandPalette"),
        ),
    ),
    ExampleCategory(
        id = "charts-data",
        title = "Charts & Data Viz",
        subtitle = "Animated charts and data displays",
        accentLabel = "CHARTS",
        tags = listOf("CHARTS", "DATA"),
        examples = listOf(
            ExampleItem("Mini Bar Chart", "Animated bar chart", "MiniBarChart(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rise.In,\n))", demoType = "component", componentKey = "MiniBarChart"),
            ExampleItem("Donut Chart", "Animated donut/pie chart", "DonutChart(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rotate.In,\n))", demoType = "component", componentKey = "DonutChart"),
            ExampleItem("Spark Line", "Mini sparkline chart", "SparkLine(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.RightSubtle,\n))", demoType = "component", componentKey = "SparkLine"),
            ExampleItem("Data Table", "Staggered data table rows", "DataTable(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Up,\n))", demoType = "component", componentKey = "DataTable"),
        ),
    ),
    // ─── ComponentAnimations8 categories ───
    ExampleCategory(
        id = "ui-patterns",
        title = "UI Patterns",
        subtitle = "Common UI components with animations",
        accentLabel = "UI",
        tags = listOf("UI", "INTERACTIVE"),
        examples = listOf(
            ExampleItem("Animated Banner", "Slide-in notification banner", "AnimatedBanner(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.DownSubtle,\n))", demoType = "component", componentKey = "AnimatedBanner"),
            ExampleItem("Animated Tooltip", "Floating tooltip popover", "AnimatedTooltip(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Up,\n))", demoType = "component", componentKey = "AnimatedTooltip"),
            ExampleItem("Animated Alert", "Cycling status alerts", "AnimatedAlert(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.LeftSubtle,\n))", demoType = "component", componentKey = "AnimatedAlert"),
            ExampleItem("Animated Dropdown", "Expanding dropdown menu", "AnimatedDropdown(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Up,\n))", demoType = "component", componentKey = "AnimatedDropdown"),
            ExampleItem("Animated Progress Card", "Upload progress indicator", "AnimatedProgressCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = "component", componentKey = "AnimatedProgressCard"),
            ExampleItem("Animated Dialog", "Modal dialog with scale entry", "AnimatedDialog(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = "component", componentKey = "AnimatedDialog"),
        ),
    ),
    ExampleCategory(
        id = "visual-canvas",
        title = "Visual & Canvas",
        subtitle = "Canvas-based visual effects",
        accentLabel = "VISUAL",
        tags = listOf("VISUAL", "CANVAS"),
        examples = listOf(
            ExampleItem("Orbit Dots", "Orbital dot animation", "OrbitDots(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rotate.In,\n))", demoType = "component", componentKey = "OrbitDots"),
            ExampleItem("Concentric Rings", "Expanding ripple rings", "ConcentricRings(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Expand,\n))", demoType = "component", componentKey = "ConcentricRings"),
            ExampleItem("Bouncing Loader", "Three-dot bouncing loader", "BouncingLoader(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Bounce.In,\n))", demoType = "component", componentKey = "BouncingLoader"),
            ExampleItem("Glow Pulse", "Pulsing glow orb", "GlowPulse(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = "component", componentKey = "GlowPulse"),
            ExampleItem("Waveform Bars", "Audio waveform visualization", "WaveformBars(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Gentle,\n))", demoType = "component", componentKey = "WaveformBars"),
        ),
    ),
    ExampleCategory(
        id = "interactive-widgets",
        title = "Interactive Widgets",
        subtitle = "User-interactive animated components",
        accentLabel = "INTERACTIVE",
        tags = listOf("INTERACTIVE", "UI"),
        examples = listOf(
            ExampleItem("Like Button", "Heart toggle with spring", "LikeButton(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Spring.PopIn,\n))", demoType = "component", componentKey = "LikeButton"),
            ExampleItem("Quantity Selector", "Animated +/- counter", "QuantitySelector(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = "component", componentKey = "QuantitySelector"),
            ExampleItem("Radio Selector", "Animated radio group", "RadioSelector(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Gentle,\n))", demoType = "component", componentKey = "RadioSelector"),
            ExampleItem("Slider Control", "Animated volume slider", "SliderControl(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.RightSubtle,\n))", demoType = "component", componentKey = "SliderControl"),
            ExampleItem("Animated Password Field", "Password input animation", "AnimatedPasswordField(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Micro.NudgeUp,\n))", demoType = "component", componentKey = "AnimatedPasswordField"),
            ExampleItem("Animated File Upload", "Multi-stage upload animation", "AnimatedFileUpload(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Gentle,\n))", demoType = "component", componentKey = "AnimatedFileUpload"),
            ExampleItem("Animated Vote", "Upvote/downvote counter", "AnimatedVote(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = "component", componentKey = "AnimatedVote"),
            ExampleItem("Animated Search Bar", "Expanding search input", "AnimatedSearchBar(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Stretch.Horizontal,\n))", demoType = "component", componentKey = "AnimatedSearchBar"),
            ExampleItem("Animated Form Validation", "Live form field validation", "AnimatedFormValidation(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Micro.NudgeUp,\n))", demoType = "component", componentKey = "AnimatedFormValidation"),
        ),
    ),
)

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
                effect = Canimation.Scale.Zoom,
                preset = CanimationPreset.ZoomIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.Zoom,
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
            ExampleItem(
                title = "Elastic",
                description = "Elastic morphing effect",
                effect = Canimation.Morph.Elastic,
                preset = CanimationPreset.SpringIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Morph.Elastic,
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
                effect = Canimation.Scale.Zoom + Canimation.Slide.Up,
                preset = CanimationPreset.ZoomIn,
                codeSnippet = """Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.Zoom + Canimation.Slide.Up,
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
)

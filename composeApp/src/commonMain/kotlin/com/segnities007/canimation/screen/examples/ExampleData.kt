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
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: TypewriterText with live demo""",
            ),
            ExampleItem(
                title = "Scramble Text",
                description = "Random characters resolve into target text",
                demoType = "component",
                componentKey = "ScrambleText",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ScrambleText with live demo""",
            ),
            ExampleItem(
                title = "Wavy Text",
                description = "Letters oscillate in a wave pattern",
                demoType = "component",
                componentKey = "WavyText",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: WavyText with live demo""",
            ),
            ExampleItem(
                title = "Split Text Reveal",
                description = "Text splits and reveals with staggered animation",
                demoType = "component",
                componentKey = "SplitTextReveal",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: SplitTextReveal with live demo""",
            ),
            ExampleItem(
                title = "Glitch Text",
                description = "Digital glitch distortion effect on text",
                demoType = "component",
                componentKey = "GlitchText",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: GlitchText with live demo""",
            ),
            ExampleItem(
                title = "Text Gradient Anim",
                description = "Animated gradient sweep across text",
                demoType = "component",
                componentKey = "TextGradientAnim",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: TextGradientAnim with live demo""",
            ),
            ExampleItem(
                title = "Rolling Digits",
                description = "Digits roll like a slot machine",
                demoType = "component",
                componentKey = "RollingDigits",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: RollingDigits with live demo""",
            ),
            ExampleItem(
                title = "Vertical Ticker",
                description = "Text scrolls vertically through values",
                demoType = "component",
                componentKey = "VerticalTicker",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: VerticalTicker with live demo""",
            ),
            ExampleItem(
                title = "Animated Underline Text",
                description = "Underline draws beneath text on appear",
                demoType = "component",
                componentKey = "AnimatedUnderlineText",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: AnimatedUnderlineText with live demo""",
            ),
            ExampleItem(
                title = "Blinking Cursor",
                description = "Terminal-style blinking cursor animation",
                demoType = "component",
                componentKey = "BlinkingCursor",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: BlinkingCursor with live demo""",
            ),
            ExampleItem(
                title = "Animated Gradient Text",
                description = "Gradient colors animate across text",
                demoType = "component",
                componentKey = "AnimatedGradientText",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: AnimatedGradientText with live demo""",
            ),
            ExampleItem(
                title = "Reveal Text Effect",
                description = "Text reveals with a sliding mask",
                demoType = "component",
                componentKey = "RevealTextEffect",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: RevealTextEffect with live demo""",
            ),
            ExampleItem(
                title = "Scatter Text",
                description = "Characters scatter and reassemble",
                demoType = "component",
                componentKey = "ScatterText",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ScatterText with live demo""",
            ),
            ExampleItem(
                title = "Text Line Reveal",
                description = "Text lines reveal one at a time",
                demoType = "component",
                componentKey = "TextLineReveal",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: TextLineReveal with live demo""",
            ),
            ExampleItem(
                title = "Typewriter Delete",
                description = "Characters delete one by one in reverse",
                demoType = "component",
                componentKey = "TypewriterDelete",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: TypewriterDelete with live demo""",
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
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: FlipCard with live demo""",
            ),
            ExampleItem(
                title = "Tilt Card",
                description = "Card tilts toward pointer on hover",
                demoType = "component",
                componentKey = "TiltCard",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: TiltCard with live demo""",
            ),
            ExampleItem(
                title = "Card Border Trace",
                description = "Animated border traces around the card",
                demoType = "component",
                componentKey = "CardBorderTrace",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CardBorderTrace with live demo""",
            ),
            ExampleItem(
                title = "Card Lift Hover",
                description = "Card elevates with shadow on hover",
                demoType = "component",
                componentKey = "CardLiftHover",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CardLiftHover with live demo""",
            ),
            ExampleItem(
                title = "Card Gradient Border",
                description = "Animated gradient border effect",
                demoType = "component",
                componentKey = "CardGradientBorder",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CardGradientBorder with live demo""",
            ),
            ExampleItem(
                title = "Card Expand Collapse",
                description = "Card smoothly expands and collapses",
                demoType = "component",
                componentKey = "CardExpandCollapse",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CardExpandCollapse with live demo""",
            ),
            ExampleItem(
                title = "Card Parallax Tilt",
                description = "Parallax layers tilt with pointer movement",
                demoType = "component",
                componentKey = "CardParallaxTilt",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CardParallaxTilt with live demo""",
            ),
            ExampleItem(
                title = "Card Glassmorphism",
                description = "Frosted glass card with blur effect",
                demoType = "component",
                componentKey = "CardGlassmorphism",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CardGlassmorphism with live demo""",
            ),
            ExampleItem(
                title = "Card Reveal Wipe",
                description = "Content reveals with a wipe transition",
                demoType = "component",
                componentKey = "CardRevealWipe",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CardRevealWipe with live demo""",
            ),
            ExampleItem(
                title = "Card Fan Stack",
                description = "Cards fan out from a stack",
                demoType = "component",
                componentKey = "CardFanStack",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CardFanStack with live demo""",
            ),
            ExampleItem(
                title = "Card Magnetic Snap",
                description = "Card snaps to position with magnetic feel",
                demoType = "component",
                componentKey = "CardMagneticSnap",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CardMagneticSnap with live demo""",
            ),
            ExampleItem(
                title = "Card Shuffle",
                description = "Cards shuffle like a deck",
                demoType = "component",
                componentKey = "CardShuffle",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CardShuffle with live demo""",
            ),
            ExampleItem(
                title = "Stacked Cards",
                description = "Cards stacked with depth perspective",
                demoType = "component",
                componentKey = "StackedCards",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: StackedCards with live demo""",
            ),
            ExampleItem(
                title = "Card Stack Swipe",
                description = "Swipeable card stack like a dating app",
                demoType = "component",
                componentKey = "CardStackSwipe",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CardStackSwipe with live demo""",
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
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: PulseLoadingDots with live demo""",
            ),
            ExampleItem(
                title = "Jumping Dots",
                description = "Dots jump up and down in sequence",
                demoType = "component",
                componentKey = "JumpingDots",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: JumpingDots with live demo""",
            ),
            ExampleItem(
                title = "Shimmer Effect",
                description = "Shimmering placeholder for loading content",
                demoType = "component",
                componentKey = "ShimmerEffect",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ShimmerEffect with live demo""",
            ),
            ExampleItem(
                title = "Loading Spinner",
                description = "Classic spinning loader animation",
                demoType = "component",
                componentKey = "LoadingSpinner",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: LoadingSpinner with live demo""",
            ),
            ExampleItem(
                title = "Loading Ripple",
                description = "Ripple rings expand outward from center",
                demoType = "component",
                componentKey = "LoadingRipple",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: LoadingRipple with live demo""",
            ),
            ExampleItem(
                title = "Skeleton Loader",
                description = "Skeleton placeholder with shimmer",
                demoType = "component",
                componentKey = "SkeletonLoader",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: SkeletonLoader with live demo""",
            ),
            ExampleItem(
                title = "Progress Ring",
                description = "Circular progress indicator with animation",
                demoType = "component",
                componentKey = "ProgressRing",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ProgressRing with live demo""",
            ),
            ExampleItem(
                title = "Progress Steps",
                description = "Step-by-step progress with transitions",
                demoType = "component",
                componentKey = "ProgressSteps",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ProgressSteps with live demo""",
            ),
            ExampleItem(
                title = "Liquid Fill",
                description = "Liquid-style fill animation in a circle",
                demoType = "component",
                componentKey = "LiquidFill",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: LiquidFill with live demo""",
            ),
            ExampleItem(
                title = "Glow Progress Bar",
                description = "Progress bar with glowing edge effect",
                demoType = "component",
                componentKey = "GlowProgressBar",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: GlowProgressBar with live demo""",
            ),
            ExampleItem(
                title = "Morph Progress Indicator",
                description = "Progress indicator that morphs between shapes",
                demoType = "component",
                componentKey = "MorphProgressIndicator",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: MorphProgressIndicator with live demo""",
            ),
            ExampleItem(
                title = "Step Indicator",
                description = "Multi-step progress indicator",
                demoType = "component",
                componentKey = "StepIndicator",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: StepIndicator with live demo""",
            ),
            ExampleItem(
                title = "Progress Scrubber",
                description = "Draggable progress scrubber control",
                demoType = "component",
                componentKey = "ProgressScrubber",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ProgressScrubber with live demo""",
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
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: AnimatedCounter with live demo""",
            ),
            ExampleItem(
                title = "Number Trend",
                description = "Number with up/down trend indicator",
                demoType = "component",
                componentKey = "NumberTrend",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: NumberTrend with live demo""",
            ),
            ExampleItem(
                title = "Number Counter",
                description = "Simple animated number counter",
                demoType = "component",
                componentKey = "NumberCounter",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: NumberCounter with live demo""",
            ),
            ExampleItem(
                title = "Countdown Timer",
                description = "Animated countdown from a start value",
                demoType = "component",
                componentKey = "CountdownTimer",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CountdownTimer with live demo""",
            ),
            ExampleItem(
                title = "Engagement Stats",
                description = "Animated social engagement statistics",
                demoType = "component",
                componentKey = "EngagementStats",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: EngagementStats with live demo""",
            ),
            ExampleItem(
                title = "Price Switcher",
                description = "Animated price toggle between plans",
                demoType = "component",
                componentKey = "PriceSwitcher",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: PriceSwitcher with live demo""",
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
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: AnimatedTabs with live demo""",
            ),
            ExampleItem(
                title = "Expandable Accordion",
                description = "Accordion sections expand and collapse",
                demoType = "component",
                componentKey = "ExpandableAccordion",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ExpandableAccordion with live demo""",
            ),
            ExampleItem(
                title = "Mega Menu Reveal",
                description = "Large dropdown menu with reveal animation",
                demoType = "component",
                componentKey = "MegaMenuReveal",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: MegaMenuReveal with live demo""",
            ),
            ExampleItem(
                title = "Accordion Menu",
                description = "Nested accordion menu with smooth transitions",
                demoType = "component",
                componentKey = "AccordionMenu",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: AccordionMenu with live demo""",
            ),
            ExampleItem(
                title = "Smooth Tab Indicator",
                description = "Tab indicator slides smoothly between tabs",
                demoType = "component",
                componentKey = "SmoothTabIndicator",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: SmoothTabIndicator with live demo""",
            ),
            ExampleItem(
                title = "Circular Menu",
                description = "Menu items arranged in a circle with animation",
                demoType = "component",
                componentKey = "CircularMenu",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CircularMenu with live demo""",
            ),
            ExampleItem(
                title = "Segmented Control",
                description = "iOS-style segmented control with animation",
                demoType = "component",
                componentKey = "SegmentedControl",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: SegmentedControl with live demo""",
            ),
            ExampleItem(
                title = "Elastic Drawer",
                description = "Side drawer with elastic spring animation",
                demoType = "component",
                componentKey = "ElasticDrawer",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ElasticDrawer with live demo""",
            ),
            ExampleItem(
                title = "Scroll Direction Header",
                description = "Header hides/shows based on scroll direction",
                demoType = "component",
                componentKey = "ScrollDirectionHeader",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ScrollDirectionHeader with live demo""",
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
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: HoldToConfirm with live demo""",
            ),
            ExampleItem(
                title = "Swipe Actions",
                description = "Swipe to reveal action buttons",
                demoType = "component",
                componentKey = "SwipeActions",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: SwipeActions with live demo""",
            ),
            ExampleItem(
                title = "Multi State Badge",
                description = "Badge transitions between multiple states",
                demoType = "component",
                componentKey = "MultiStateBadge",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: MultiStateBadge with live demo""",
            ),
            ExampleItem(
                title = "Elastic Pull",
                description = "Elastic pull-to-refresh interaction",
                demoType = "component",
                componentKey = "ElasticPull",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ElasticPull with live demo""",
            ),
            ExampleItem(
                title = "Expanding Search",
                description = "Search bar expands from icon on tap",
                demoType = "component",
                componentKey = "ExpandingSearch",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ExpandingSearch with live demo""",
            ),
            ExampleItem(
                title = "iOS Slider",
                description = "iOS-style slider with smooth animation",
                demoType = "component",
                componentKey = "IOSSlider",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: IOSSlider with live demo""",
            ),
            ExampleItem(
                title = "Checkbox Animation",
                description = "Animated checkbox with check mark draw",
                demoType = "component",
                componentKey = "CheckboxAnimation",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CheckboxAnimation with live demo""",
            ),
            ExampleItem(
                title = "Switch Animation",
                description = "Animated toggle switch with spring physics",
                demoType = "component",
                componentKey = "SwitchAnimation",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: SwitchAnimation with live demo""",
            ),
            ExampleItem(
                title = "Magnetic Button",
                description = "Button attracted to pointer with magnetic effect",
                demoType = "component",
                componentKey = "MagneticButton",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: MagneticButton with live demo""",
            ),
            ExampleItem(
                title = "Ripple Button",
                description = "Button with material ripple on tap",
                demoType = "component",
                componentKey = "RippleButton",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: RippleButton with live demo""",
            ),
            ExampleItem(
                title = "Spring Toggle",
                description = "Toggle with spring bounce animation",
                demoType = "component",
                componentKey = "SpringToggle",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: SpringToggle with live demo""",
            ),
            ExampleItem(
                title = "Spring Chip",
                description = "Chip component with spring selection animation",
                demoType = "component",
                componentKey = "SpringChip",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: SpringChip with live demo""",
            ),
            ExampleItem(
                title = "Notification Badge",
                description = "Animated notification badge with count",
                demoType = "component",
                componentKey = "NotificationBadge",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: NotificationBadge with live demo""",
            ),
            ExampleItem(
                title = "Toast Notification",
                description = "Animated toast notification popup",
                demoType = "component",
                componentKey = "ToastNotification",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ToastNotification with live demo""",
            ),
            ExampleItem(
                title = "Coin Flip",
                description = "3D coin flip animation",
                demoType = "component",
                componentKey = "CoinFlip",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: CoinFlip with live demo""",
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
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ColorMorph with live demo""",
            ),
            ExampleItem(
                title = "Morphing Shapes",
                description = "Shapes morph smoothly between forms",
                demoType = "component",
                componentKey = "MorphingShapes",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: MorphingShapes with live demo""",
            ),
            ExampleItem(
                title = "Gradient Shift",
                description = "Animated gradient color shifting",
                demoType = "component",
                componentKey = "GradientShift",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: GradientShift with live demo""",
            ),
            ExampleItem(
                title = "Breathing Glow",
                description = "Element pulses with a breathing glow",
                demoType = "component",
                componentKey = "BreathingGlow",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: BreathingGlow with live demo""",
            ),
            ExampleItem(
                title = "Path Tracer",
                description = "Animated path drawing on canvas",
                demoType = "component",
                componentKey = "PathTracer",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: PathTracer with live demo""",
            ),
            ExampleItem(
                title = "Confetti Explosion",
                description = "Burst of confetti particles on trigger",
                demoType = "component",
                componentKey = "ConfettiExplosion",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ConfettiExplosion with live demo""",
            ),
            ExampleItem(
                title = "Wave Effect",
                description = "Audio visualizer-style wave bars",
                demoType = "component",
                componentKey = "WaveEffect",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: WaveEffect with live demo""",
            ),
            ExampleItem(
                title = "Sliding Reveal",
                description = "Content reveals with a sliding mask",
                demoType = "component",
                componentKey = "SlidingReveal",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: SlidingReveal with live demo""",
            ),
            ExampleItem(
                title = "Focus Blur Effect",
                description = "Focus shifts with blur transitions",
                demoType = "component",
                componentKey = "FocusBlurEffect",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: FocusBlurEffect with live demo""",
            ),
            ExampleItem(
                title = "Expanding Rings",
                description = "Concentric rings expand outward",
                demoType = "component",
                componentKey = "ExpandingRings",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ExpandingRings with live demo""",
            ),
            ExampleItem(
                title = "Floating Particles",
                description = "Ambient floating particles background",
                demoType = "component",
                componentKey = "FloatingParticles",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: FloatingParticles with live demo""",
            ),
            ExampleItem(
                title = "Pulsing Avatar",
                description = "Avatar with pulsing ring animation",
                demoType = "component",
                componentKey = "PulsingAvatar",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: PulsingAvatar with live demo""",
            ),
            ExampleItem(
                title = "Pulse Radar",
                description = "Radar-style pulse scanning animation",
                demoType = "component",
                componentKey = "PulseRadar",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: PulseRadar with live demo""",
            ),
            ExampleItem(
                title = "Heartbeat Line",
                description = "ECG-style heartbeat line animation",
                demoType = "component",
                componentKey = "HeartbeatLine",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: HeartbeatLine with live demo""",
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
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: BouncySpringList with live demo""",
            ),
            ExampleItem(
                title = "Stagger From Center",
                description = "Items stagger outward from center",
                demoType = "component",
                componentKey = "StaggerFromCenter",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: StaggerFromCenter with live demo""",
            ),
            ExampleItem(
                title = "Ticker Marquee",
                description = "Scrolling ticker text marquee",
                demoType = "component",
                componentKey = "TickerMarquee",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: TickerMarquee with live demo""",
            ),
            ExampleItem(
                title = "Spring Chain",
                description = "Chain of elements connected by springs",
                demoType = "component",
                componentKey = "SpringChain",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: SpringChain with live demo""",
            ),
            ExampleItem(
                title = "Orbit Animation",
                description = "Objects orbit around a center point",
                demoType = "component",
                componentKey = "OrbitAnimation",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: OrbitAnimation with live demo""",
            ),
            ExampleItem(
                title = "Parallax Layers",
                description = "Layered parallax scrolling effect",
                demoType = "component",
                componentKey = "ParallaxLayers",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ParallaxLayers with live demo""",
            ),
            ExampleItem(
                title = "DNA Helix",
                description = "Animated double helix DNA structure",
                demoType = "component",
                componentKey = "DnaHelix",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: DnaHelix with live demo""",
            ),
            ExampleItem(
                title = "Pendulum Swing",
                description = "Pendulum swinging back and forth",
                demoType = "component",
                componentKey = "PendulumSwing",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: PendulumSwing with live demo""",
            ),
            ExampleItem(
                title = "Bouncing Ball",
                description = "Ball bouncing with gravity physics",
                demoType = "component",
                componentKey = "BouncingBall",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: BouncingBall with live demo""",
            ),
            ExampleItem(
                title = "Slinky Spring",
                description = "Slinky-style cascading spring animation",
                demoType = "component",
                componentKey = "SlinkySpring",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: SlinkySpring with live demo""",
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
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: AnimatedPieChart with live demo""",
            ),
            ExampleItem(
                title = "Animated Bar Chart",
                description = "Bar chart with animated bar growth",
                demoType = "component",
                componentKey = "AnimatedBarChart",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: AnimatedBarChart with live demo""",
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
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: InfiniteLoadingList with live demo""",
            ),
            ExampleItem(
                title = "Horizontal Scroll Gallery",
                description = "Horizontally scrolling image gallery",
                demoType = "component",
                componentKey = "HorizontalScrollGallery",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: HorizontalScrollGallery with live demo""",
            ),
            ExampleItem(
                title = "Vertical Carousel",
                description = "Vertically scrolling carousel with snap",
                demoType = "component",
                componentKey = "VerticalCarousel",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: VerticalCarousel with live demo""",
            ),
            ExampleItem(
                title = "Waterfall Grid",
                description = "Pinterest-style waterfall grid layout",
                demoType = "component",
                componentKey = "WaterfallGrid",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: WaterfallGrid with live demo""",
            ),
            ExampleItem(
                title = "Zoom Hero Image",
                description = "Image zooms to hero view on tap",
                demoType = "component",
                componentKey = "ZoomHeroImage",
                codeSnippet = """// Entry animation with Canimation API
Modifier.canimation(
    visible = entered,
    effect = Canimation.Fade.Up,
)
// Internal: ZoomHeroImage with live demo""",
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
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Fade.Up,
)""",
            ),
            ExampleItem(
                title = "Matrix Rain",
                description = "Columns of cascading characters",
                demoType = "component",
                componentKey = "MatrixRain",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Fade.Up,
)""",
            ),
            ExampleItem(
                title = "Music Equalizer",
                description = "Animated equalizer bars",
                demoType = "component",
                componentKey = "MusicEqualizer",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Fade.Up,
)""",
            ),
            ExampleItem(
                title = "Water Droplet",
                description = "Water droplet ripple effect",
                demoType = "component",
                componentKey = "WaterDroplet",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Blur.Soft,
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
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Fade.Up,
)""",
            ),
            ExampleItem(
                title = "Neumorphism Card",
                description = "Card with animated shadow depth",
                demoType = "component",
                componentKey = "NeumorphismCard",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Blur.Soft,
)""",
            ),
            ExampleItem(
                title = "Gradient Border Card",
                description = "Card with animated gradient border",
                demoType = "component",
                componentKey = "GradientBorderCard",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Reveal.Center,
)""",
            ),
            ExampleItem(
                title = "Flip Counter",
                description = "Digit flip counter",
                demoType = "component",
                componentKey = "FlipCounter",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Fade.Up,
)""",
            ),
            ExampleItem(
                title = "Expandable Chip",
                description = "Chip that expands with content",
                demoType = "component",
                componentKey = "ExpandableChip",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Micro.Pulse,
)""",
            ),
            ExampleItem(
                title = "Stacked Notifications",
                description = "Stacked notification cards",
                demoType = "component",
                componentKey = "StackedNotifications",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Swipe.Right,
)""",
            ),
            ExampleItem(
                title = "Sliding Toggle",
                description = "Custom toggle switch with sliding animation",
                demoType = "component",
                componentKey = "SlidingToggle",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Fade.Up,
)""",
            ),
            ExampleItem(
                title = "Circular Reveal Card",
                description = "Card with circular reveal animation",
                demoType = "component",
                componentKey = "CircularRevealCard",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Reveal.Center,
)""",
            ),
            ExampleItem(
                title = "Typing Indicator",
                description = "Chat typing indicator (3 bouncing dots)",
                demoType = "component",
                componentKey = "TypingIndicator",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Fade.Up,
)""",
            ),
            ExampleItem(
                title = "Skeleton Text",
                description = "Skeleton loading for text content",
                demoType = "component",
                componentKey = "SkeletonText",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Fade.Up,
)""",
            ),
            ExampleItem(
                title = "Swipe Card",
                description = "Card that can be swiped away",
                demoType = "component",
                componentKey = "SwipeCard",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Swipe.Left,
)""",
            ),
            ExampleItem(
                title = "Animated Checkmark",
                description = "Animated checkmark with circle",
                demoType = "component",
                componentKey = "AnimatedCheckmark",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Reveal.Center,
)""",
            ),
            ExampleItem(
                title = "Rotating Cube",
                description = "2D representation of a rotating cube",
                demoType = "component",
                componentKey = "RotatingCube",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Fade.Up,
)""",
            ),
            ExampleItem(
                title = "Slot Machine",
                description = "Simple slot machine with rolling numbers",
                demoType = "component",
                componentKey = "SlotMachine",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Page.BottomSheet,
)""",
            ),
            ExampleItem(
                title = "Typewriter Cursor",
                description = "Blinking cursor with text typing effect",
                demoType = "component",
                componentKey = "TypewriterCursor",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Fade.Up,
)""",
            ),
            ExampleItem(
                title = "Radial Progress",
                description = "Circular progress with animated arc",
                demoType = "component",
                componentKey = "RadialProgress",
                codeSnippet = """Modifier.canimation(
    visible = entryVisible,
    effect = Canimation.Fade.Up,
)""",
            ),
        ),
    ),
)

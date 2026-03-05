package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset


internal val exampleCategoriesChunk01Part02: List<ExampleCategory> = listOf(
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
)

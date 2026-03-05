package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset


internal val exampleCategoriesChunk01Part01: List<ExampleCategory> = listOf(
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
)

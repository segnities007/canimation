package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset


internal val exampleCategoriesChunk01Part03: List<ExampleCategory> = listOf(
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
)

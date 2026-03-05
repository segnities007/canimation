package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset


internal val exampleCategoriesChunk02Part02: List<ExampleCategory> = listOf(
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
                    demoType = DemoType.Composition,
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
                    demoType = DemoType.Composition,
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
                    demoType = DemoType.Composition,
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
                    demoType = DemoType.Composition,
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
                    demoType = DemoType.Composition,
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
                    demoType = DemoType.Composition,
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
                    demoType = DemoType.Transition,
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
                    demoType = DemoType.Transition,
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
                    demoType = DemoType.Transition,
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
                    demoType = DemoType.Transition,
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
                    demoType = DemoType.Transition,
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
                    demoType = DemoType.Transition,
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
                    demoType = DemoType.Stagger,
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
                    demoType = DemoType.Stagger,
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
                    demoType = DemoType.Stagger,
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
                    demoType = DemoType.Stagger,
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
                    demoType = DemoType.Stagger,
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
)

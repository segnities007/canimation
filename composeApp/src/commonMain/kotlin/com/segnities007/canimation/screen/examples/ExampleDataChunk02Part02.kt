package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*



internal val exampleCategoriesChunk02Part02: List<ExampleCategory> = listOf(
        // 18. Morph Effects
        ExampleCategory(
            id = "morph",
            title = Res.string.examples_data_morph_title,
            subtitle = Res.string.examples_data_morph_subtitle,
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_morph_item_01_title,
                    description = Res.string.examples_data_morph_item_01_description,
                    effect = Canimation.Morph.ScaleUp,
                    preset = CanimationPreset.ScaleUp,
                    codeSnippet = Res.string.examples_data_morph_item_01_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_morph_item_02_title,
                    description = Res.string.examples_data_morph_item_02_description,
                    effect = Canimation.Morph.Expand,
                    preset = CanimationPreset.Expand,
                    codeSnippet = Res.string.examples_data_morph_item_02_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_morph_item_03_title,
                    description = Res.string.examples_data_morph_item_03_description,
                    effect = Canimation.Morph.Collapse,
                    preset = CanimationPreset.ScaleIn,
                    codeSnippet = Res.string.examples_data_morph_item_03_code,
                ),
            ),
        ),
        // 19. Effect Composition
        ExampleCategory(
            id = "composition",
            title = Res.string.examples_data_composition_title,
            subtitle = Res.string.examples_data_composition_subtitle,
            accentLabel = "PATTERN",
            tags = listOf("PATTERN"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_composition_item_01_title,
                    description = Res.string.examples_data_composition_item_01_description,
                    demoType = DemoType.Composition,
                    effect = Canimation.Fade.In + Canimation.Scale.Pop,
                    preset = CanimationPreset.Pop,
                    codeSnippet = Res.string.examples_data_composition_item_04_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_composition_item_02_title,
                    description = Res.string.examples_data_composition_item_02_description,
                    demoType = DemoType.Composition,
                    effect = Canimation.Fade.Up + Canimation.Rotate.In,
                    preset = CanimationPreset.FadeUp,
                    codeSnippet = Res.string.examples_data_composition_item_05_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_composition_item_03_title,
                    description = Res.string.examples_data_composition_item_03_description,
                    demoType = DemoType.Composition,
                    effect = Canimation.Zoom.In + Canimation.Slide.Up,
                    preset = CanimationPreset.ZoomIn,
                    codeSnippet = Res.string.examples_data_composition_item_06_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_composition_item_04_title,
                    description = Res.string.examples_data_composition_item_04_description,
                    demoType = DemoType.Composition,
                    effect = Canimation.Bounce.In + Canimation.Fade.In,
                    preset = CanimationPreset.BounceIn,
                    codeSnippet = Res.string.examples_data_composition_item_07_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_composition_item_05_title,
                    description = Res.string.examples_data_composition_item_05_description,
                    demoType = DemoType.Composition,
                    effect = Canimation.Spring.Pop + Canimation.Rotate.Clockwise,
                    preset = CanimationPreset.Pop,
                    codeSnippet = Res.string.examples_data_composition_item_08_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_composition_item_06_title,
                    description = Res.string.examples_data_composition_item_06_description,
                    demoType = DemoType.Composition,
                    effect = Canimation.Entrance.Elevate + Canimation.Fade.Gentle,
                    preset = CanimationPreset.ElevateIn,
                    codeSnippet = Res.string.examples_data_composition_item_09_code,
                ),
            ),
        ),
        // 20. Asymmetric Transitions
        ExampleCategory(
            id = "transitions",
            title = Res.string.examples_data_transitions_title,
            subtitle = Res.string.examples_data_transitions_subtitle,
            accentLabel = "PATTERN",
            tags = listOf("PATTERN"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_transitions_item_01_title,
                    description = Res.string.examples_data_transitions_item_01_description,
                    demoType = DemoType.Transition,
                    enterEffect = Canimation.Fade.Up,
                    exitEffect = Canimation.Fade.Down,
                    preset = CanimationPreset.FadeUp,
                    codeSnippet = Res.string.examples_data_transitions_item_10_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_transitions_item_02_title,
                    description = Res.string.examples_data_transitions_item_02_description,
                    demoType = DemoType.Transition,
                    enterEffect = Canimation.Scale.Pop,
                    exitEffect = Canimation.Scale.Shrink,
                    preset = CanimationPreset.Pop,
                    codeSnippet = Res.string.examples_data_transitions_item_11_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_transitions_item_03_title,
                    description = Res.string.examples_data_transitions_item_03_description,
                    demoType = DemoType.Transition,
                    enterEffect = Canimation.Slide.Left,
                    exitEffect = Canimation.Slide.Right,
                    preset = CanimationPreset.SlideLeft,
                    codeSnippet = Res.string.examples_data_transitions_item_12_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_transitions_item_04_title,
                    description = Res.string.examples_data_transitions_item_04_description,
                    demoType = DemoType.Transition,
                    enterEffect = Canimation.Zoom.In,
                    exitEffect = Canimation.Zoom.Out,
                    preset = CanimationPreset.ZoomIn,
                    codeSnippet = Res.string.examples_data_transitions_item_13_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_transitions_item_05_title,
                    description = Res.string.examples_data_transitions_item_05_description,
                    demoType = DemoType.Transition,
                    enterEffect = Canimation.Entrance.Drop,
                    exitEffect = Canimation.Morph.Collapse,
                    preset = CanimationPreset.DropIn,
                    codeSnippet = Res.string.examples_data_transitions_item_14_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_transitions_item_06_title,
                    description = Res.string.examples_data_transitions_item_06_description,
                    demoType = DemoType.Transition,
                    enterEffect = Canimation.Material.SharedAxisX,
                    exitEffect = Canimation.Material.SharedAxisX,
                    preset = CanimationPreset.SharedAxisX,
                    codeSnippet = Res.string.examples_data_transitions_item_15_code,
                ),
            ),
        ),
        // 21. Stagger Patterns
        ExampleCategory(
            id = "stagger-patterns",
            title = Res.string.examples_data_stagger_patterns_title,
            subtitle = Res.string.examples_data_stagger_patterns_subtitle,
            accentLabel = "PATTERN",
            tags = listOf("PATTERN"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_stagger_patterns_item_01_title,
                    description = Res.string.examples_data_stagger_patterns_item_01_description,
                    demoType = DemoType.Stagger,
                    effect = Canimation.Fade.Up,
                    preset = CanimationPreset.FadeUp,
                    codeSnippet = Res.string.examples_data_stagger_patterns_item_16_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_stagger_patterns_item_02_title,
                    description = Res.string.examples_data_stagger_patterns_item_02_description,
                    demoType = DemoType.Stagger,
                    effect = Canimation.Scale.Pop,
                    preset = CanimationPreset.Pop,
                    codeSnippet = Res.string.examples_data_stagger_patterns_item_17_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_stagger_patterns_item_03_title,
                    description = Res.string.examples_data_stagger_patterns_item_03_description,
                    demoType = DemoType.Stagger,
                    effect = Canimation.Slide.Right,
                    preset = CanimationPreset.SlideRight,
                    codeSnippet = Res.string.examples_data_stagger_patterns_item_18_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_stagger_patterns_item_04_title,
                    description = Res.string.examples_data_stagger_patterns_item_04_description,
                    demoType = DemoType.Stagger,
                    effect = Canimation.Bounce.In,
                    preset = CanimationPreset.BounceIn,
                    codeSnippet = Res.string.examples_data_stagger_patterns_item_19_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_stagger_patterns_item_05_title,
                    description = Res.string.examples_data_stagger_patterns_item_05_description,
                    demoType = DemoType.Stagger,
                    effect = Canimation.Spring.Up,
                    preset = CanimationPreset.SpringSlideUp,
                    codeSnippet = Res.string.examples_data_stagger_patterns_item_20_code,
                ),
            ),
        ),
)

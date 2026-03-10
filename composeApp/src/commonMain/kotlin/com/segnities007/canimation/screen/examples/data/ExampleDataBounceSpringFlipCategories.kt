package com.segnities007.canimation.screen.examples.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*



internal val exampleCategoriesBounceSpringAndFlip: List<ExampleCategory> = listOf(
        // 10. Bounce Effects
        ExampleCategory(
            id = "bounce",
            title = Res.string.examples_data_bounce_title,
            subtitle = Res.string.examples_data_bounce_subtitle,
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_bounce_item_01_title,
                    description = Res.string.examples_data_bounce_item_01_description,
                    effect = Canimation.Bounce.In,
                    preset = CanimationPreset.BounceIn,
                    codeSnippet = Res.string.examples_data_bounce_item_01_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_bounce_item_02_title,
                    description = Res.string.examples_data_bounce_item_02_description,
                    effect = Canimation.Bounce.Down,
                    preset = CanimationPreset.BounceInDown,
                    codeSnippet = Res.string.examples_data_bounce_item_02_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_bounce_item_03_title,
                    description = Res.string.examples_data_bounce_item_03_description,
                    effect = Canimation.Bounce.Up,
                    preset = CanimationPreset.BounceInUp,
                    codeSnippet = Res.string.examples_data_bounce_item_03_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_bounce_item_04_title,
                    description = Res.string.examples_data_bounce_item_04_description,
                    effect = Canimation.Bounce.Left,
                    preset = CanimationPreset.BounceInLeft,
                    codeSnippet = Res.string.examples_data_bounce_item_04_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_bounce_item_05_title,
                    description = Res.string.examples_data_bounce_item_05_description,
                    effect = Canimation.Bounce.Right,
                    preset = CanimationPreset.BounceInRight,
                    codeSnippet = Res.string.examples_data_bounce_item_05_code,
                ),
            ),
        ),
        // 11. Spring Effects
        ExampleCategory(
            id = "spring",
            title = Res.string.examples_data_spring_title,
            subtitle = Res.string.examples_data_spring_subtitle,
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_spring_item_01_title,
                    description = Res.string.examples_data_spring_item_01_description,
                    effect = Canimation.Spring.In,
                    preset = CanimationPreset.SpringIn,
                    codeSnippet = Res.string.examples_data_spring_item_06_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_spring_item_02_title,
                    description = Res.string.examples_data_spring_item_02_description,
                    effect = Canimation.Spring.Up,
                    preset = CanimationPreset.SpringSlideUp,
                    codeSnippet = Res.string.examples_data_spring_item_07_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_spring_item_03_title,
                    description = Res.string.examples_data_spring_item_03_description,
                    effect = Canimation.Spring.Down,
                    preset = CanimationPreset.SpringIn,
                    codeSnippet = Res.string.examples_data_spring_item_08_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_spring_item_04_title,
                    description = Res.string.examples_data_spring_item_04_description,
                    effect = Canimation.Spring.Left,
                    preset = CanimationPreset.SpringIn,
                    codeSnippet = Res.string.examples_data_spring_item_09_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_spring_item_05_title,
                    description = Res.string.examples_data_spring_item_05_description,
                    effect = Canimation.Spring.Right,
                    preset = CanimationPreset.SpringIn,
                    codeSnippet = Res.string.examples_data_spring_item_10_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_spring_item_06_title,
                    description = Res.string.examples_data_spring_item_06_description,
                    effect = Canimation.Spring.Pop,
                    preset = CanimationPreset.Pop,
                    codeSnippet = Res.string.examples_data_spring_item_11_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_spring_item_07_title,
                    description = Res.string.examples_data_spring_item_07_description,
                    effect = Canimation.Spring.Bounce,
                    preset = CanimationPreset.Bounce,
                    codeSnippet = Res.string.examples_data_spring_item_12_code,
                ),
            ),
        ),
        // 12. Flip Effects
        ExampleCategory(
            id = "flip",
            title = Res.string.examples_data_flip_title,
            subtitle = Res.string.examples_data_flip_subtitle,
            accentLabel = "3D",
            tags = listOf("ENTRANCE", "3D"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_flip_item_01_title,
                    description = Res.string.examples_data_flip_item_01_description,
                    effect = Canimation.Flip.In,
                    preset = CanimationPreset.FlipIn,
                    codeSnippet = Res.string.examples_data_flip_item_13_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_flip_item_02_title,
                    description = Res.string.examples_data_flip_item_02_description,
                    effect = Canimation.Flip.Up,
                    preset = CanimationPreset.FlipUp,
                    codeSnippet = Res.string.examples_data_flip_item_14_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_flip_item_03_title,
                    description = Res.string.examples_data_flip_item_03_description,
                    effect = Canimation.Flip.Down,
                    preset = CanimationPreset.FlipDown,
                    codeSnippet = Res.string.examples_data_flip_item_15_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_flip_item_04_title,
                    description = Res.string.examples_data_flip_item_04_description,
                    effect = Canimation.Flip.X,
                    preset = CanimationPreset.FlipIn,
                    codeSnippet = Res.string.examples_data_flip_item_16_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_flip_item_05_title,
                    description = Res.string.examples_data_flip_item_05_description,
                    effect = Canimation.Flip.Y,
                    preset = CanimationPreset.FlipInY,
                    codeSnippet = Res.string.examples_data_flip_item_17_code,
                ),
            ),
        ),
)

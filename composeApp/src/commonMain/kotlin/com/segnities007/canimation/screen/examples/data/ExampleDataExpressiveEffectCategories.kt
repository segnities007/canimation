package com.segnities007.canimation.screen.examples.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val exampleCategoriesExpressiveEffects: List<ExampleCategory> = listOf(
        ExampleCategory(
            id = "elastic",
            title = Res.string.examples_data_elastic_title,
            subtitle = Res.string.examples_data_elastic_subtitle,
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_elastic_item_01_title,
                    description = Res.string.examples_data_elastic_item_01_description,
                    codeSnippet = Res.string.examples_data_elastic_item_01_code, effect = Canimation.Elastic.In
                ),
                ExampleItem(
                    title = Res.string.examples_data_elastic_item_02_title,
                    description = Res.string.examples_data_elastic_item_02_description,
                    codeSnippet = Res.string.examples_data_elastic_item_02_code, effect = Canimation.Elastic.Stretch
                ),
                ExampleItem(
                    title = Res.string.examples_data_elastic_item_03_title,
                    description = Res.string.examples_data_elastic_item_03_description,
                    codeSnippet = Res.string.examples_data_elastic_item_03_code, effect = Canimation.Elastic.Squash
                ),
                ExampleItem(
                    title = Res.string.examples_data_elastic_item_04_title,
                    description = Res.string.examples_data_elastic_item_04_description,
                    codeSnippet = Res.string.examples_data_elastic_item_04_code, effect = Canimation.Elastic.Snap
                ),
                ExampleItem(
                    title = Res.string.examples_data_elastic_item_05_title,
                    description = Res.string.examples_data_elastic_item_05_description,
                    codeSnippet = Res.string.examples_data_elastic_item_05_code, effect = Canimation.Elastic.Wobble
                ),
            ),
        ),
        ExampleCategory(
            id = "cinematic",
            title = Res.string.examples_data_cinematic_title,
            subtitle = Res.string.examples_data_cinematic_subtitle,
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_cinematic_item_06_title,
                    description = Res.string.examples_data_cinematic_item_06_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_06_code, effect = Canimation.Cinematic.Curtain
                ),
                ExampleItem(
                    title = Res.string.examples_data_cinematic_item_07_title,
                    description = Res.string.examples_data_cinematic_item_07_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_07_code, effect = Canimation.Cinematic.ZoomPan
                ),
                ExampleItem(
                    title = Res.string.examples_data_cinematic_item_08_title,
                    description = Res.string.examples_data_cinematic_item_08_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_08_code, effect = Canimation.Cinematic.Dolly
                ),
                ExampleItem(
                    title = Res.string.examples_data_cinematic_item_09_title,
                    description = Res.string.examples_data_cinematic_item_09_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_09_code, effect = Canimation.Cinematic.Reveal
                ),
                ExampleItem(
                    title = Res.string.examples_data_cinematic_item_10_title,
                    description = Res.string.examples_data_cinematic_item_10_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_10_code, effect = Canimation.Cinematic.FadeToBlack
                ),
                ExampleItem(
                    title = Res.string.examples_data_cinematic_item_11_title,
                    description = Res.string.examples_data_cinematic_item_11_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_11_code, effect = Canimation.Cinematic.Dramatic
                ),
            ),
        ),
        ExampleCategory(
            id = "playful",
            title = Res.string.examples_data_playful_title,
            subtitle = Res.string.examples_data_playful_subtitle,
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_playful_item_12_title,
                    description = Res.string.examples_data_playful_item_12_description,
                    codeSnippet = Res.string.examples_data_playful_item_12_code, effect = Canimation.Playful.Wiggle
                ),
                ExampleItem(
                    title = Res.string.examples_data_playful_item_13_title,
                    description = Res.string.examples_data_playful_item_13_description,
                    codeSnippet = Res.string.examples_data_playful_item_13_code, effect = Canimation.Playful.Hop
                ),
                ExampleItem(
                    title = Res.string.examples_data_playful_item_14_title,
                    description = Res.string.examples_data_playful_item_14_description,
                    codeSnippet = Res.string.examples_data_playful_item_14_code, effect = Canimation.Playful.Spin
                ),
                ExampleItem(
                    title = Res.string.examples_data_playful_item_15_title,
                    description = Res.string.examples_data_playful_item_15_description,
                    codeSnippet = Res.string.examples_data_playful_item_15_code, effect = Canimation.Playful.Pop
                ),
                ExampleItem(
                    title = Res.string.examples_data_playful_item_16_title,
                    description = Res.string.examples_data_playful_item_16_description,
                    codeSnippet = Res.string.examples_data_playful_item_16_code, effect = Canimation.Playful.Twirl
                ),
                ExampleItem(
                    title = Res.string.examples_data_playful_item_17_title,
                    description = Res.string.examples_data_playful_item_17_description,
                    codeSnippet = Res.string.examples_data_playful_item_17_code, effect = Canimation.Playful.Boing
                ),
            ),
        ),
        ExampleCategory(
            id = "diagonal",
            title = Res.string.examples_data_diagonal_title,
            subtitle = Res.string.examples_data_diagonal_subtitle,
            accentLabel = "MOVEMENT",
            tags = listOf("ENTRANCE", "MOVEMENT"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_diagonal_item_18_title,
                    description = Res.string.examples_data_diagonal_item_18_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_18_code, effect = Canimation.Diagonal.TopLeft
                ),
                ExampleItem(
                    title = Res.string.examples_data_diagonal_item_19_title,
                    description = Res.string.examples_data_diagonal_item_19_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_19_code, effect = Canimation.Diagonal.TopRight
                ),
                ExampleItem(
                    title = Res.string.examples_data_diagonal_item_20_title,
                    description = Res.string.examples_data_diagonal_item_20_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_20_code, effect = Canimation.Diagonal.BottomLeft
                ),
                ExampleItem(
                    title = Res.string.examples_data_diagonal_item_21_title,
                    description = Res.string.examples_data_diagonal_item_21_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_21_code, effect = Canimation.Diagonal.BottomRight
                ),
                ExampleItem(
                    title = Res.string.examples_data_diagonal_item_22_title,
                    description = Res.string.examples_data_diagonal_item_22_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_22_code, effect = Canimation.Diagonal.Subtle
                ),
            ),
        ),
        ExampleCategory(
            id = "shrink",
            title = Res.string.examples_data_shrink_title,
            subtitle = Res.string.examples_data_shrink_subtitle,
            accentLabel = "SCALE",
            tags = listOf("ENTRANCE", "SCALE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_shrink_item_23_title,
                    description = Res.string.examples_data_shrink_item_23_description,
                    codeSnippet = Res.string.examples_data_shrink_item_23_code, effect = Canimation.Shrink.Out
                ),
                ExampleItem(
                    title = Res.string.examples_data_shrink_item_24_title,
                    description = Res.string.examples_data_shrink_item_24_description,
                    codeSnippet = Res.string.examples_data_shrink_item_24_code, effect = Canimation.Shrink.Subtle
                ),
                ExampleItem(
                    title = Res.string.examples_data_shrink_item_25_title,
                    description = Res.string.examples_data_shrink_item_25_description,
                    codeSnippet = Res.string.examples_data_shrink_item_25_code, effect = Canimation.Shrink.Rotate
                ),
                ExampleItem(
                    title = Res.string.examples_data_shrink_item_26_title,
                    description = Res.string.examples_data_shrink_item_26_description,
                    codeSnippet = Res.string.examples_data_shrink_item_26_code, effect = Canimation.Shrink.FadeDown
                ),
            ),
        ),
        ExampleCategory(
            id = "tilt",
            title = Res.string.examples_data_tilt_title,
            subtitle = Res.string.examples_data_tilt_subtitle,
            accentLabel = "ROTATE",
            tags = listOf("ENTRANCE", "ROTATE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_tilt_item_27_title,
                    description = Res.string.examples_data_tilt_item_27_description,
                    codeSnippet = Res.string.examples_data_tilt_item_27_code, effect = Canimation.Tilt.Left
                ),
                ExampleItem(
                    title = Res.string.examples_data_tilt_item_28_title,
                    description = Res.string.examples_data_tilt_item_28_description,
                    codeSnippet = Res.string.examples_data_tilt_item_28_code, effect = Canimation.Tilt.Right
                ),
                ExampleItem(
                    title = Res.string.examples_data_tilt_item_29_title,
                    description = Res.string.examples_data_tilt_item_29_description,
                    codeSnippet = Res.string.examples_data_tilt_item_29_code, effect = Canimation.Tilt.Up
                ),
                ExampleItem(
                    title = Res.string.examples_data_tilt_item_30_title,
                    description = Res.string.examples_data_tilt_item_30_description,
                    codeSnippet = Res.string.examples_data_tilt_item_30_code, effect = Canimation.Tilt.Down
                ),
                ExampleItem(
                    title = Res.string.examples_data_tilt_item_31_title,
                    description = Res.string.examples_data_tilt_item_31_description,
                    codeSnippet = Res.string.examples_data_tilt_item_31_code, effect = Canimation.Tilt.Swing
                ),
            ),
        ),
)

package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val showcaseCategoriesExpressiveEffects: List<ShowcaseCategory> = listOf(
        ShowcaseCategory(
            id = "elastic",
            title = Res.string.examples_data_elastic_title,
            subtitle = Res.string.examples_data_elastic_subtitle,
            tags = listOf(ShowcaseTagId.Entrance),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_elastic_item_01_title,
                    description = Res.string.examples_data_elastic_item_01_description,
                    codeSnippet = Res.string.examples_data_elastic_item_01_code, effect = Canimation.Elastic.In
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_elastic_item_02_title,
                    description = Res.string.examples_data_elastic_item_02_description,
                    codeSnippet = Res.string.examples_data_elastic_item_02_code, effect = Canimation.Elastic.Stretch
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_elastic_item_03_title,
                    description = Res.string.examples_data_elastic_item_03_description,
                    codeSnippet = Res.string.examples_data_elastic_item_03_code, effect = Canimation.Elastic.Squash
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_elastic_item_04_title,
                    description = Res.string.examples_data_elastic_item_04_description,
                    codeSnippet = Res.string.examples_data_elastic_item_04_code, effect = Canimation.Elastic.Snap
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_elastic_item_05_title,
                    description = Res.string.examples_data_elastic_item_05_description,
                    codeSnippet = Res.string.examples_data_elastic_item_05_code, effect = Canimation.Elastic.Wobble
                ),
            ),
        ),
        ShowcaseCategory(
            id = "cinematic",
            title = Res.string.examples_data_cinematic_title,
            subtitle = Res.string.examples_data_cinematic_subtitle,
            tags = listOf(ShowcaseTagId.Entrance),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_cinematic_item_06_title,
                    description = Res.string.examples_data_cinematic_item_06_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_06_code, effect = Canimation.Cinematic.Curtain
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_cinematic_item_07_title,
                    description = Res.string.examples_data_cinematic_item_07_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_07_code, effect = Canimation.Cinematic.ZoomPan
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_cinematic_item_08_title,
                    description = Res.string.examples_data_cinematic_item_08_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_08_code, effect = Canimation.Cinematic.Dolly
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_cinematic_item_09_title,
                    description = Res.string.examples_data_cinematic_item_09_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_09_code, effect = Canimation.Cinematic.Reveal
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_cinematic_item_10_title,
                    description = Res.string.examples_data_cinematic_item_10_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_10_code, effect = Canimation.Cinematic.FadeToBlack
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_cinematic_item_11_title,
                    description = Res.string.examples_data_cinematic_item_11_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_11_code, effect = Canimation.Cinematic.Dramatic
                ),
            ),
        ),
        ShowcaseCategory(
            id = "playful",
            title = Res.string.examples_data_playful_title,
            subtitle = Res.string.examples_data_playful_subtitle,
            tags = listOf(ShowcaseTagId.Entrance),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_playful_item_12_title,
                    description = Res.string.examples_data_playful_item_12_description,
                    codeSnippet = Res.string.examples_data_playful_item_12_code, effect = Canimation.Playful.Wiggle
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_playful_item_13_title,
                    description = Res.string.examples_data_playful_item_13_description,
                    codeSnippet = Res.string.examples_data_playful_item_13_code, effect = Canimation.Playful.Hop
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_playful_item_14_title,
                    description = Res.string.examples_data_playful_item_14_description,
                    codeSnippet = Res.string.examples_data_playful_item_14_code, effect = Canimation.Playful.Spin
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_playful_item_15_title,
                    description = Res.string.examples_data_playful_item_15_description,
                    codeSnippet = Res.string.examples_data_playful_item_15_code, effect = Canimation.Playful.Pop
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_playful_item_16_title,
                    description = Res.string.examples_data_playful_item_16_description,
                    codeSnippet = Res.string.examples_data_playful_item_16_code, effect = Canimation.Playful.Twirl
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_playful_item_17_title,
                    description = Res.string.examples_data_playful_item_17_description,
                    codeSnippet = Res.string.examples_data_playful_item_17_code, effect = Canimation.Playful.Boing
                ),
            ),
        ),
        ShowcaseCategory(
            id = "diagonal",
            title = Res.string.examples_data_diagonal_title,
            subtitle = Res.string.examples_data_diagonal_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Movement),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_diagonal_item_18_title,
                    description = Res.string.examples_data_diagonal_item_18_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_18_code, effect = Canimation.Diagonal.TopLeft
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_diagonal_item_19_title,
                    description = Res.string.examples_data_diagonal_item_19_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_19_code, effect = Canimation.Diagonal.TopRight
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_diagonal_item_20_title,
                    description = Res.string.examples_data_diagonal_item_20_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_20_code, effect = Canimation.Diagonal.BottomLeft
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_diagonal_item_21_title,
                    description = Res.string.examples_data_diagonal_item_21_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_21_code, effect = Canimation.Diagonal.BottomRight
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_diagonal_item_22_title,
                    description = Res.string.examples_data_diagonal_item_22_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_22_code, effect = Canimation.Diagonal.Subtle
                ),
            ),
        ),
        ShowcaseCategory(
            id = "shrink",
            title = Res.string.examples_data_shrink_title,
            subtitle = Res.string.examples_data_shrink_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Scale),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_shrink_item_23_title,
                    description = Res.string.examples_data_shrink_item_23_description,
                    codeSnippet = Res.string.examples_data_shrink_item_23_code, effect = Canimation.Shrink.Out
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_shrink_item_24_title,
                    description = Res.string.examples_data_shrink_item_24_description,
                    codeSnippet = Res.string.examples_data_shrink_item_24_code, effect = Canimation.Shrink.Subtle
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_shrink_item_25_title,
                    description = Res.string.examples_data_shrink_item_25_description,
                    codeSnippet = Res.string.examples_data_shrink_item_25_code, effect = Canimation.Shrink.Rotate
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_shrink_item_26_title,
                    description = Res.string.examples_data_shrink_item_26_description,
                    codeSnippet = Res.string.examples_data_shrink_item_26_code, effect = Canimation.Shrink.FadeDown
                ),
            ),
        ),
        ShowcaseCategory(
            id = "tilt",
            title = Res.string.examples_data_tilt_title,
            subtitle = Res.string.examples_data_tilt_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Rotate),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_tilt_item_27_title,
                    description = Res.string.examples_data_tilt_item_27_description,
                    codeSnippet = Res.string.examples_data_tilt_item_27_code, effect = Canimation.Tilt.Left
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_tilt_item_28_title,
                    description = Res.string.examples_data_tilt_item_28_description,
                    codeSnippet = Res.string.examples_data_tilt_item_28_code, effect = Canimation.Tilt.Right
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_tilt_item_29_title,
                    description = Res.string.examples_data_tilt_item_29_description,
                    codeSnippet = Res.string.examples_data_tilt_item_29_code, effect = Canimation.Tilt.Up
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_tilt_item_30_title,
                    description = Res.string.examples_data_tilt_item_30_description,
                    codeSnippet = Res.string.examples_data_tilt_item_30_code, effect = Canimation.Tilt.Down
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_tilt_item_31_title,
                    description = Res.string.examples_data_tilt_item_31_description,
                    codeSnippet = Res.string.examples_data_tilt_item_31_code, effect = Canimation.Tilt.Swing
                ),
            ),
        ),
)

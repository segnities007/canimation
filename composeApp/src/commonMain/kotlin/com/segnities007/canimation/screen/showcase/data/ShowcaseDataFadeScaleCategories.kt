package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*



internal val showcaseCategoriesFadeAndScale: List<ShowcaseCategory> = listOf(
        // 1. Fade Basics
        ShowcaseCategory(
            id = "fade-basic",
            title = Res.string.examples_data_fade_basic_title,
            subtitle = Res.string.examples_data_fade_basic_subtitle,
            tags = listOf(ShowcaseTagId.Entrance),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_fade_basic_item_01_title,
                    description = Res.string.examples_data_fade_basic_item_01_description,
                    effect = Canimation.Fade.In,
                    preset = CanimationPreset.Fade,
                    codeSnippet = Res.string.examples_data_fade_basic_item_01_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_basic_item_02_title,
                    description = Res.string.examples_data_fade_basic_item_02_description,
                    effect = Canimation.Fade.Out,
                    preset = CanimationPreset.Fade,
                    codeSnippet = Res.string.examples_data_fade_basic_item_02_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_basic_item_03_title,
                    description = Res.string.examples_data_fade_basic_item_03_description,
                    effect = Canimation.Fade.Gentle,
                    preset = CanimationPreset.GentleFade,
                    codeSnippet = Res.string.examples_data_fade_basic_item_03_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_basic_item_04_title,
                    description = Res.string.examples_data_fade_basic_item_04_description,
                    effect = Canimation.Fade.Quick,
                    preset = CanimationPreset.Fade,
                    codeSnippet = Res.string.examples_data_fade_basic_item_04_code,
                ),
            ),
        ),
        // 2. Directional Fade
        ShowcaseCategory(
            id = "fade-directional",
            title = Res.string.examples_data_fade_directional_title,
            subtitle = Res.string.examples_data_fade_directional_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Direction),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_fade_directional_item_01_title,
                    description = Res.string.examples_data_fade_directional_item_01_description,
                    effect = Canimation.Fade.Up,
                    preset = CanimationPreset.FadeUp,
                    codeSnippet = Res.string.examples_data_fade_directional_item_05_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_directional_item_02_title,
                    description = Res.string.examples_data_fade_directional_item_02_description,
                    effect = Canimation.Fade.Down,
                    preset = CanimationPreset.FadeDown,
                    codeSnippet = Res.string.examples_data_fade_directional_item_06_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_directional_item_03_title,
                    description = Res.string.examples_data_fade_directional_item_03_description,
                    effect = Canimation.Fade.Left,
                    preset = CanimationPreset.FadeInLeft,
                    codeSnippet = Res.string.examples_data_fade_directional_item_07_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_directional_item_04_title,
                    description = Res.string.examples_data_fade_directional_item_04_description,
                    effect = Canimation.Fade.Right,
                    preset = CanimationPreset.FadeInRight,
                    codeSnippet = Res.string.examples_data_fade_directional_item_08_code,
                ),
            ),
        ),
        // 3. Big Fade
        ShowcaseCategory(
            id = "fade-big",
            title = Res.string.examples_data_fade_big_title,
            subtitle = Res.string.examples_data_fade_big_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Direction),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_fade_big_item_01_title,
                    description = Res.string.examples_data_fade_big_item_01_description,
                    effect = Canimation.Fade.UpBig,
                    preset = CanimationPreset.FadeInUpBig,
                    codeSnippet = Res.string.examples_data_fade_big_item_09_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_big_item_02_title,
                    description = Res.string.examples_data_fade_big_item_02_description,
                    effect = Canimation.Fade.DownBig,
                    preset = CanimationPreset.FadeInDownBig,
                    codeSnippet = Res.string.examples_data_fade_big_item_10_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_big_item_03_title,
                    description = Res.string.examples_data_fade_big_item_03_description,
                    effect = Canimation.Fade.LeftBig,
                    preset = CanimationPreset.FadeInLeftBig,
                    codeSnippet = Res.string.examples_data_fade_big_item_11_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_big_item_04_title,
                    description = Res.string.examples_data_fade_big_item_04_description,
                    effect = Canimation.Fade.RightBig,
                    preset = CanimationPreset.FadeInRightBig,
                    codeSnippet = Res.string.examples_data_fade_big_item_12_code,
                ),
            ),
        ),
        // 4. Diagonal Fade
        ShowcaseCategory(
            id = "fade-diagonal",
            title = Res.string.examples_data_fade_diagonal_title,
            subtitle = Res.string.examples_data_fade_diagonal_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Direction),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_fade_diagonal_item_01_title,
                    description = Res.string.examples_data_fade_diagonal_item_01_description,
                    effect = Canimation.Fade.TopLeft,
                    preset = CanimationPreset.FadeInTopLeft,
                    codeSnippet = Res.string.examples_data_fade_diagonal_item_13_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_diagonal_item_02_title,
                    description = Res.string.examples_data_fade_diagonal_item_02_description,
                    effect = Canimation.Fade.TopRight,
                    preset = CanimationPreset.FadeInTopRight,
                    codeSnippet = Res.string.examples_data_fade_diagonal_item_14_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_diagonal_item_03_title,
                    description = Res.string.examples_data_fade_diagonal_item_03_description,
                    effect = Canimation.Fade.BottomLeft,
                    preset = CanimationPreset.FadeInBottomLeft,
                    codeSnippet = Res.string.examples_data_fade_diagonal_item_15_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_diagonal_item_04_title,
                    description = Res.string.examples_data_fade_diagonal_item_04_description,
                    effect = Canimation.Fade.BottomRight,
                    preset = CanimationPreset.FadeInBottomRight,
                    codeSnippet = Res.string.examples_data_fade_diagonal_item_16_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_diagonal_item_05_title,
                    description = Res.string.examples_data_fade_diagonal_item_05_description,
                    effect = Canimation.Fade.Small,
                    preset = CanimationPreset.FadeSmall,
                    codeSnippet = Res.string.examples_data_fade_diagonal_item_17_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_fade_diagonal_item_06_title,
                    description = Res.string.examples_data_fade_diagonal_item_06_description,
                    effect = Canimation.Fade.Big,
                    preset = CanimationPreset.FadeBig,
                    codeSnippet = Res.string.examples_data_fade_diagonal_item_18_code,
                ),
            ),
        ),
        // 5. Scale Effects
        ShowcaseCategory(
            id = "scale",
            title = Res.string.examples_data_scale_title,
            subtitle = Res.string.examples_data_scale_subtitle,
            tags = listOf(ShowcaseTagId.Entrance),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_scale_item_01_title,
                    description = Res.string.examples_data_scale_item_01_description,
                    effect = Canimation.Scale.In,
                    preset = CanimationPreset.ScaleIn,
                    codeSnippet = Res.string.examples_data_scale_item_19_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_scale_item_02_title,
                    description = Res.string.examples_data_scale_item_02_description,
                    effect = Canimation.Scale.Up,
                    preset = CanimationPreset.ScaleUp,
                    codeSnippet = Res.string.examples_data_scale_item_20_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_scale_item_03_title,
                    description = Res.string.examples_data_scale_item_03_description,
                    effect = Canimation.Scale.Down,
                    preset = CanimationPreset.ScaleIn,
                    codeSnippet = Res.string.examples_data_scale_item_21_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_scale_item_04_title,
                    description = Res.string.examples_data_scale_item_04_description,
                    effect = Canimation.Scale.Expand,
                    preset = CanimationPreset.Expand,
                    codeSnippet = Res.string.examples_data_scale_item_22_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_scale_item_05_title,
                    description = Res.string.examples_data_scale_item_05_description,
                    effect = Canimation.Scale.Shrink,
                    preset = CanimationPreset.ScaleIn,
                    codeSnippet = Res.string.examples_data_scale_item_23_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_scale_item_06_title,
                    description = Res.string.examples_data_scale_item_06_description,
                    effect = Canimation.Scale.Subtle,
                    preset = CanimationPreset.ScaleIn,
                    codeSnippet = Res.string.examples_data_scale_item_24_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_scale_item_07_title,
                    description = Res.string.examples_data_scale_item_07_description,
                    effect = Canimation.Scale.Pop,
                    preset = CanimationPreset.Pop,
                    codeSnippet = Res.string.examples_data_scale_item_25_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_scale_item_08_title,
                    description = Res.string.examples_data_scale_item_08_description,
                    effect = Canimation.Zoom.In,
                    preset = CanimationPreset.ZoomIn,
                    codeSnippet = Res.string.examples_data_scale_item_26_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_scale_item_09_title,
                    description = Res.string.examples_data_scale_item_09_description,
                    effect = Canimation.Scale.FadeIn,
                    preset = CanimationPreset.ScaleIn,
                    codeSnippet = Res.string.examples_data_scale_item_27_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_scale_item_10_title,
                    description = Res.string.examples_data_scale_item_10_description,
                    effect = Canimation.Scale.UpFade,
                    preset = CanimationPreset.ScaleUp,
                    codeSnippet = Res.string.examples_data_scale_item_28_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_scale_item_11_title,
                    description = Res.string.examples_data_scale_item_11_description,
                    effect = Canimation.Scale.DownFade,
                    preset = CanimationPreset.ScaleIn,
                    codeSnippet = Res.string.examples_data_scale_item_29_code,
                ),
            ),
        ),
)

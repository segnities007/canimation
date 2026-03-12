package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*



internal val showcaseCategoriesSlideAndRotate: List<ShowcaseCategory> = listOf(
        // 6. Slide Effects
        ShowcaseCategory(
            id = "slide",
            title = Res.string.examples_data_slide_title,
            subtitle = Res.string.examples_data_slide_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Direction),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_slide_item_01_title,
                    description = Res.string.examples_data_slide_item_01_description,
                    effect = Canimation.Slide.Left,
                    preset = CanimationPreset.SlideLeft,
                    codeSnippet = Res.string.examples_data_slide_item_01_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_slide_item_02_title,
                    description = Res.string.examples_data_slide_item_02_description,
                    effect = Canimation.Slide.Right,
                    preset = CanimationPreset.SlideRight,
                    codeSnippet = Res.string.examples_data_slide_item_02_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_slide_item_03_title,
                    description = Res.string.examples_data_slide_item_03_description,
                    effect = Canimation.Slide.Up,
                    preset = CanimationPreset.SlideUp,
                    codeSnippet = Res.string.examples_data_slide_item_03_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_slide_item_04_title,
                    description = Res.string.examples_data_slide_item_04_description,
                    effect = Canimation.Slide.Down,
                    preset = CanimationPreset.SlideDown,
                    codeSnippet = Res.string.examples_data_slide_item_04_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_slide_item_05_title,
                    description = Res.string.examples_data_slide_item_05_description,
                    effect = Canimation.Slide.LeftBig,
                    preset = CanimationPreset.SlideLeft,
                    codeSnippet = Res.string.examples_data_slide_item_05_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_slide_item_06_title,
                    description = Res.string.examples_data_slide_item_06_description,
                    effect = Canimation.Slide.RightBig,
                    preset = CanimationPreset.SlideRight,
                    codeSnippet = Res.string.examples_data_slide_item_06_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_slide_item_07_title,
                    description = Res.string.examples_data_slide_item_07_description,
                    effect = Canimation.Slide.UpBig,
                    preset = CanimationPreset.SlideUp,
                    codeSnippet = Res.string.examples_data_slide_item_07_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_slide_item_08_title,
                    description = Res.string.examples_data_slide_item_08_description,
                    effect = Canimation.Slide.DownBig,
                    preset = CanimationPreset.SlideDown,
                    codeSnippet = Res.string.examples_data_slide_item_08_code,
                ),
            ),
        ),
        // 7. Subtle Slide
        ShowcaseCategory(
            id = "slide-subtle",
            title = Res.string.examples_data_slide_subtle_title,
            subtitle = Res.string.examples_data_slide_subtle_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Direction),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_slide_subtle_item_01_title,
                    description = Res.string.examples_data_slide_subtle_item_01_description,
                    effect = Canimation.Slide.UpSubtle,
                    preset = CanimationPreset.SlideUp,
                    codeSnippet = Res.string.examples_data_slide_subtle_item_09_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_slide_subtle_item_02_title,
                    description = Res.string.examples_data_slide_subtle_item_02_description,
                    effect = Canimation.Slide.DownSubtle,
                    preset = CanimationPreset.SlideDown,
                    codeSnippet = Res.string.examples_data_slide_subtle_item_10_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_slide_subtle_item_03_title,
                    description = Res.string.examples_data_slide_subtle_item_03_description,
                    effect = Canimation.Slide.LeftSubtle,
                    preset = CanimationPreset.SlideLeft,
                    codeSnippet = Res.string.examples_data_slide_subtle_item_11_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_slide_subtle_item_04_title,
                    description = Res.string.examples_data_slide_subtle_item_04_description,
                    effect = Canimation.Slide.RightSubtle,
                    preset = CanimationPreset.SlideRight,
                    codeSnippet = Res.string.examples_data_slide_subtle_item_12_code,
                ),
            ),
        ),
        // 8. Rotate Effects
        ShowcaseCategory(
            id = "rotate",
            title = Res.string.examples_data_rotate_title,
            subtitle = Res.string.examples_data_rotate_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.ThreeDimensional),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_item_01_title,
                    description = Res.string.examples_data_rotate_item_01_description,
                    effect = Canimation.Rotate.In,
                    preset = CanimationPreset.RotateIn,
                    codeSnippet = Res.string.examples_data_rotate_item_13_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_item_02_title,
                    description = Res.string.examples_data_rotate_item_02_description,
                    effect = Canimation.Rotate.Clockwise,
                    preset = CanimationPreset.RotateClockwise,
                    codeSnippet = Res.string.examples_data_rotate_item_14_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_item_03_title,
                    description = Res.string.examples_data_rotate_item_03_description,
                    effect = Canimation.Rotate.Spin,
                    preset = CanimationPreset.SpinIn,
                    codeSnippet = Res.string.examples_data_rotate_item_15_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_item_04_title,
                    description = Res.string.examples_data_rotate_item_04_description,
                    effect = Canimation.Rotate.Half,
                    preset = CanimationPreset.RotateIn,
                    codeSnippet = Res.string.examples_data_rotate_item_16_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_item_05_title,
                    description = Res.string.examples_data_rotate_item_05_description,
                    effect = Canimation.Rotate.Quarter,
                    preset = CanimationPreset.RotateIn,
                    codeSnippet = Res.string.examples_data_rotate_item_17_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_item_06_title,
                    description = Res.string.examples_data_rotate_item_06_description,
                    effect = Canimation.Rotate.FadeIn,
                    preset = CanimationPreset.RotateIn,
                    codeSnippet = Res.string.examples_data_rotate_item_18_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_item_07_title,
                    description = Res.string.examples_data_rotate_item_07_description,
                    effect = Canimation.Rotate.ScaleIn,
                    preset = CanimationPreset.RotateScale,
                    codeSnippet = Res.string.examples_data_rotate_item_19_code,
                ),
            ),
        ),
        // 9. Directional Rotate
        ShowcaseCategory(
            id = "rotate-directional",
            title = Res.string.examples_data_rotate_directional_title,
            subtitle = Res.string.examples_data_rotate_directional_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.ThreeDimensional, ShowcaseTagId.Direction),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_directional_item_01_title,
                    description = Res.string.examples_data_rotate_directional_item_01_description,
                    effect = Canimation.Rotate.ClockwiseFade,
                    preset = CanimationPreset.RotateClockwise,
                    codeSnippet = Res.string.examples_data_rotate_directional_item_20_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_directional_item_02_title,
                    description = Res.string.examples_data_rotate_directional_item_02_description,
                    effect = Canimation.Rotate.SpinFade,
                    preset = CanimationPreset.SpinIn,
                    codeSnippet = Res.string.examples_data_rotate_directional_item_21_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_directional_item_03_title,
                    description = Res.string.examples_data_rotate_directional_item_03_description,
                    effect = Canimation.Rotate.DownLeft,
                    preset = CanimationPreset.RotateInDownLeft,
                    codeSnippet = Res.string.examples_data_rotate_directional_item_22_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_directional_item_04_title,
                    description = Res.string.examples_data_rotate_directional_item_04_description,
                    effect = Canimation.Rotate.DownRight,
                    preset = CanimationPreset.RotateInDownRight,
                    codeSnippet = Res.string.examples_data_rotate_directional_item_23_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_directional_item_05_title,
                    description = Res.string.examples_data_rotate_directional_item_05_description,
                    effect = Canimation.Rotate.UpLeft,
                    preset = CanimationPreset.RotateInUpLeft,
                    codeSnippet = Res.string.examples_data_rotate_directional_item_24_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rotate_directional_item_06_title,
                    description = Res.string.examples_data_rotate_directional_item_06_description,
                    effect = Canimation.Rotate.UpRight,
                    preset = CanimationPreset.RotateInUpRight,
                    codeSnippet = Res.string.examples_data_rotate_directional_item_25_code,
                ),
            ),
        ),
)

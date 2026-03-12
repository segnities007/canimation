package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val showcaseCategoriesZoomAttention: List<ShowcaseCategory> = listOf(
        ShowcaseCategory(
            id = "zoom",
            title = Res.string.examples_data_zoom_title,
            subtitle = Res.string.examples_data_zoom_subtitle,
            tags = listOf(ShowcaseTagId.Entrance),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_zoom_item_01_title,
                    description = Res.string.examples_data_zoom_item_01_description,
                    effect = Canimation.Zoom.In,
                    preset = CanimationPreset.ZoomIn,
                    codeSnippet = Res.string.examples_data_zoom_item_01_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_zoom_item_02_title,
                    description = Res.string.examples_data_zoom_item_02_description,
                    effect = Canimation.Zoom.Out,
                    preset = CanimationPreset.ZoomOut,
                    codeSnippet = Res.string.examples_data_zoom_item_02_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_zoom_item_03_title,
                    description = Res.string.examples_data_zoom_item_03_description,
                    effect = Canimation.Zoom.InUp,
                    preset = CanimationPreset.ZoomInUp,
                    codeSnippet = Res.string.examples_data_zoom_item_03_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_zoom_item_04_title,
                    description = Res.string.examples_data_zoom_item_04_description,
                    effect = Canimation.Zoom.InDown,
                    preset = CanimationPreset.ZoomInDown,
                    codeSnippet = Res.string.examples_data_zoom_item_04_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_zoom_item_05_title,
                    description = Res.string.examples_data_zoom_item_05_description,
                    effect = Canimation.Zoom.InLeft,
                    preset = CanimationPreset.ZoomInLeft,
                    codeSnippet = Res.string.examples_data_zoom_item_05_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_zoom_item_06_title,
                    description = Res.string.examples_data_zoom_item_06_description,
                    effect = Canimation.Zoom.InRight,
                    preset = CanimationPreset.ZoomInRight,
                    codeSnippet = Res.string.examples_data_zoom_item_06_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_zoom_item_07_title,
                    description = Res.string.examples_data_zoom_item_07_description,
                    effect = Canimation.Zoom.Dramatic,
                    preset = CanimationPreset.ZoomIn,
                    codeSnippet = Res.string.examples_data_zoom_item_07_code,
                ),
            ),
        ),
        ShowcaseCategory(
            id = "attention",
            title = Res.string.examples_data_attention_title,
            subtitle = Res.string.examples_data_attention_subtitle,
            tags = listOf(ShowcaseTagId.Emphasis),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_attention_item_01_title,
                    description = Res.string.examples_data_attention_item_01_description,
                    demoType = ShowcaseDemoType.Emphasis,
                    preset = CanimationPreset.Pulse,
                    codeSnippet = Res.string.examples_data_attention_item_08_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_attention_item_02_title,
                    description = Res.string.examples_data_attention_item_02_description,
                    demoType = ShowcaseDemoType.Emphasis,
                    preset = CanimationPreset.HeartBeat,
                    codeSnippet = Res.string.examples_data_attention_item_09_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_attention_item_03_title,
                    description = Res.string.examples_data_attention_item_03_description,
                    demoType = ShowcaseDemoType.Emphasis,
                    preset = CanimationPreset.Tada,
                    codeSnippet = Res.string.examples_data_attention_item_10_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_attention_item_04_title,
                    description = Res.string.examples_data_attention_item_04_description,
                    demoType = ShowcaseDemoType.Emphasis,
                    preset = CanimationPreset.Wobble,
                    codeSnippet = Res.string.examples_data_attention_item_11_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_attention_item_05_title,
                    description = Res.string.examples_data_attention_item_05_description,
                    demoType = ShowcaseDemoType.Emphasis,
                    preset = CanimationPreset.Swing,
                    codeSnippet = Res.string.examples_data_attention_item_12_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_attention_item_06_title,
                    description = Res.string.examples_data_attention_item_06_description,
                    demoType = ShowcaseDemoType.Emphasis,
                    preset = CanimationPreset.RubberBand,
                    codeSnippet = Res.string.examples_data_attention_item_13_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_attention_item_07_title,
                    description = Res.string.examples_data_attention_item_07_description,
                    demoType = ShowcaseDemoType.Emphasis,
                    preset = CanimationPreset.Jello,
                    codeSnippet = Res.string.examples_data_attention_item_14_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_attention_item_08_title,
                    description = Res.string.examples_data_attention_item_08_description,
                    demoType = ShowcaseDemoType.Emphasis,
                    preset = CanimationPreset.Flash,
                    codeSnippet = Res.string.examples_data_attention_item_15_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_attention_item_09_title,
                    description = Res.string.examples_data_attention_item_09_description,
                    demoType = ShowcaseDemoType.Emphasis,
                    preset = CanimationPreset.ShakeX,
                    codeSnippet = Res.string.examples_data_attention_item_16_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_attention_item_10_title,
                    description = Res.string.examples_data_attention_item_10_description,
                    demoType = ShowcaseDemoType.Emphasis,
                    preset = CanimationPreset.ShakeY,
                    codeSnippet = Res.string.examples_data_attention_item_17_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_attention_item_11_title,
                    description = Res.string.examples_data_attention_item_11_description,
                    demoType = ShowcaseDemoType.Emphasis,
                    preset = CanimationPreset.HeadShake,
                    codeSnippet = Res.string.examples_data_attention_item_18_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_attention_item_12_title,
                    description = Res.string.examples_data_attention_item_12_description,
                    demoType = ShowcaseDemoType.Emphasis,
                    preset = CanimationPreset.Bounce,
                    codeSnippet = Res.string.examples_data_attention_item_19_code,
                ),
            ),
        ),
)

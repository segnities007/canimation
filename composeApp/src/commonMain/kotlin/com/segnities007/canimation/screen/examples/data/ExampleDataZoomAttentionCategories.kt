package com.segnities007.canimation.screen.examples.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val exampleCategoriesZoomAttention: List<ExampleCategory> = listOf(
        ExampleCategory(
            id = "zoom",
            title = Res.string.examples_data_zoom_title,
            subtitle = Res.string.examples_data_zoom_subtitle,
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_zoom_item_01_title,
                    description = Res.string.examples_data_zoom_item_01_description,
                    effect = Canimation.Zoom.In,
                    preset = CanimationPreset.ZoomIn,
                    codeSnippet = Res.string.examples_data_zoom_item_01_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_zoom_item_02_title,
                    description = Res.string.examples_data_zoom_item_02_description,
                    effect = Canimation.Zoom.Out,
                    preset = CanimationPreset.ZoomOut,
                    codeSnippet = Res.string.examples_data_zoom_item_02_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_zoom_item_03_title,
                    description = Res.string.examples_data_zoom_item_03_description,
                    effect = Canimation.Zoom.InUp,
                    preset = CanimationPreset.ZoomInUp,
                    codeSnippet = Res.string.examples_data_zoom_item_03_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_zoom_item_04_title,
                    description = Res.string.examples_data_zoom_item_04_description,
                    effect = Canimation.Zoom.InDown,
                    preset = CanimationPreset.ZoomInDown,
                    codeSnippet = Res.string.examples_data_zoom_item_04_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_zoom_item_05_title,
                    description = Res.string.examples_data_zoom_item_05_description,
                    effect = Canimation.Zoom.InLeft,
                    preset = CanimationPreset.ZoomInLeft,
                    codeSnippet = Res.string.examples_data_zoom_item_05_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_zoom_item_06_title,
                    description = Res.string.examples_data_zoom_item_06_description,
                    effect = Canimation.Zoom.InRight,
                    preset = CanimationPreset.ZoomInRight,
                    codeSnippet = Res.string.examples_data_zoom_item_06_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_zoom_item_07_title,
                    description = Res.string.examples_data_zoom_item_07_description,
                    effect = Canimation.Zoom.Dramatic,
                    preset = CanimationPreset.ZoomIn,
                    codeSnippet = Res.string.examples_data_zoom_item_07_code,
                ),
            ),
        ),
        ExampleCategory(
            id = "attention",
            title = Res.string.examples_data_attention_title,
            subtitle = Res.string.examples_data_attention_subtitle,
            accentLabel = "EMPHASIS",
            tags = listOf("EMPHASIS"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_attention_item_01_title,
                    description = Res.string.examples_data_attention_item_01_description,
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Pulse,
                    codeSnippet = Res.string.examples_data_attention_item_08_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_attention_item_02_title,
                    description = Res.string.examples_data_attention_item_02_description,
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.HeartBeat,
                    codeSnippet = Res.string.examples_data_attention_item_09_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_attention_item_03_title,
                    description = Res.string.examples_data_attention_item_03_description,
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Tada,
                    codeSnippet = Res.string.examples_data_attention_item_10_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_attention_item_04_title,
                    description = Res.string.examples_data_attention_item_04_description,
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Wobble,
                    codeSnippet = Res.string.examples_data_attention_item_11_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_attention_item_05_title,
                    description = Res.string.examples_data_attention_item_05_description,
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Swing,
                    codeSnippet = Res.string.examples_data_attention_item_12_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_attention_item_06_title,
                    description = Res.string.examples_data_attention_item_06_description,
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.RubberBand,
                    codeSnippet = Res.string.examples_data_attention_item_13_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_attention_item_07_title,
                    description = Res.string.examples_data_attention_item_07_description,
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Jello,
                    codeSnippet = Res.string.examples_data_attention_item_14_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_attention_item_08_title,
                    description = Res.string.examples_data_attention_item_08_description,
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Flash,
                    codeSnippet = Res.string.examples_data_attention_item_15_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_attention_item_09_title,
                    description = Res.string.examples_data_attention_item_09_description,
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.ShakeX,
                    codeSnippet = Res.string.examples_data_attention_item_16_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_attention_item_10_title,
                    description = Res.string.examples_data_attention_item_10_description,
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.ShakeY,
                    codeSnippet = Res.string.examples_data_attention_item_17_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_attention_item_11_title,
                    description = Res.string.examples_data_attention_item_11_description,
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.HeadShake,
                    codeSnippet = Res.string.examples_data_attention_item_18_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_attention_item_12_title,
                    description = Res.string.examples_data_attention_item_12_description,
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Bounce,
                    codeSnippet = Res.string.examples_data_attention_item_19_code,
                ),
            ),
        ),
)

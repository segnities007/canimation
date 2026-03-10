package com.segnities007.canimation.screen.examples.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val exampleCategoriesComponentAdvanced: List<ExampleCategory> = listOf(
        ExampleCategory(
            id = "ui-advanced",
            title = Res.string.examples_data_ui_advanced_title,
            subtitle = Res.string.examples_data_ui_advanced_subtitle,
            accentLabel = "UI",
            tags = listOf("UI", "INTERACTIVE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_ui_advanced_item_49_title,
                    description = Res.string.examples_data_ui_advanced_item_49_description,
                    codeSnippet = Res.string.examples_data_ui_advanced_item_49_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedBottomSheet
                ),
                ExampleItem(
                    title = Res.string.examples_data_ui_advanced_item_50_title,
                    description = Res.string.examples_data_ui_advanced_item_50_description,
                    codeSnippet = Res.string.examples_data_ui_advanced_item_50_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedSnackbar
                ),
                ExampleItem(
                    title = Res.string.examples_data_ui_advanced_item_51_title,
                    description = Res.string.examples_data_ui_advanced_item_51_description,
                    codeSnippet = Res.string.examples_data_ui_advanced_item_51_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedFab
                ),
                ExampleItem(
                    title = Res.string.examples_data_ui_advanced_item_52_title,
                    description = Res.string.examples_data_ui_advanced_item_52_description,
                    codeSnippet = Res.string.examples_data_ui_advanced_item_52_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedChipInput
                ),
                ExampleItem(
                    title = Res.string.examples_data_ui_advanced_item_53_title,
                    description = Res.string.examples_data_ui_advanced_item_53_description,
                    codeSnippet = Res.string.examples_data_ui_advanced_item_53_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedAccordion
                ),
                ExampleItem(
                    title = Res.string.examples_data_ui_advanced_item_54_title,
                    description = Res.string.examples_data_ui_advanced_item_54_description,
                    codeSnippet = Res.string.examples_data_ui_advanced_item_54_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedStepper
                ),
            ),
        ),
        ExampleCategory(
            id = "visual-advanced",
            title = Res.string.examples_data_visual_advanced_title,
            subtitle = Res.string.examples_data_visual_advanced_subtitle,
            accentLabel = "VISUAL",
            tags = listOf("VISUAL", "CANVAS"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_visual_advanced_item_55_title,
                    description = Res.string.examples_data_visual_advanced_item_55_description,
                    codeSnippet = Res.string.examples_data_visual_advanced_item_55_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.MorphingShape
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_advanced_item_56_title,
                    description = Res.string.examples_data_visual_advanced_item_56_description,
                    codeSnippet = Res.string.examples_data_visual_advanced_item_56_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.SpiralDots
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_advanced_item_57_title,
                    description = Res.string.examples_data_visual_advanced_item_57_description,
                    codeSnippet = Res.string.examples_data_visual_advanced_item_57_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.DnaHelixCanvas
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_advanced_item_58_title,
                    description = Res.string.examples_data_visual_advanced_item_58_description,
                    codeSnippet = Res.string.examples_data_visual_advanced_item_58_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.HexGrid
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_advanced_item_59_title,
                    description = Res.string.examples_data_visual_advanced_item_59_description,
                    codeSnippet = Res.string.examples_data_visual_advanced_item_59_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.ParticleField
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_advanced_item_60_title,
                    description = Res.string.examples_data_visual_advanced_item_60_description,
                    codeSnippet = Res.string.examples_data_visual_advanced_item_60_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.RotatingSquares
                ),
            ),
        ),
)

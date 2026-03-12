package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val showcaseCategoriesComponentAdvanced: List<ShowcaseCategory> = listOf(
        ShowcaseCategory(
            id = "ui-advanced",
            title = Res.string.examples_data_ui_advanced_title,
            subtitle = Res.string.examples_data_ui_advanced_subtitle,
            tags = listOf(ShowcaseTagId.Ui, ShowcaseTagId.Interactive),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_ui_advanced_item_49_title,
                    description = Res.string.examples_data_ui_advanced_item_49_description,
                    codeSnippet = Res.string.examples_data_ui_advanced_item_49_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedBottomSheet
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_ui_advanced_item_50_title,
                    description = Res.string.examples_data_ui_advanced_item_50_description,
                    codeSnippet = Res.string.examples_data_ui_advanced_item_50_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedSnackbar
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_ui_advanced_item_51_title,
                    description = Res.string.examples_data_ui_advanced_item_51_description,
                    codeSnippet = Res.string.examples_data_ui_advanced_item_51_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedFab
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_ui_advanced_item_52_title,
                    description = Res.string.examples_data_ui_advanced_item_52_description,
                    codeSnippet = Res.string.examples_data_ui_advanced_item_52_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedChipInput
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_ui_advanced_item_53_title,
                    description = Res.string.examples_data_ui_advanced_item_53_description,
                    codeSnippet = Res.string.examples_data_ui_advanced_item_53_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedAccordion
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_ui_advanced_item_54_title,
                    description = Res.string.examples_data_ui_advanced_item_54_description,
                    codeSnippet = Res.string.examples_data_ui_advanced_item_54_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedStepper
                ),
            ),
        ),
        ShowcaseCategory(
            id = "visual-advanced",
            title = Res.string.examples_data_visual_advanced_title,
            subtitle = Res.string.examples_data_visual_advanced_subtitle,
            tags = listOf(ShowcaseTagId.Visual, ShowcaseTagId.Canvas),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_visual_advanced_item_55_title,
                    description = Res.string.examples_data_visual_advanced_item_55_description,
                    codeSnippet = Res.string.examples_data_visual_advanced_item_55_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.MorphingShape
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_advanced_item_56_title,
                    description = Res.string.examples_data_visual_advanced_item_56_description,
                    codeSnippet = Res.string.examples_data_visual_advanced_item_56_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.SpiralDots
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_advanced_item_57_title,
                    description = Res.string.examples_data_visual_advanced_item_57_description,
                    codeSnippet = Res.string.examples_data_visual_advanced_item_57_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.DnaHelixCanvas
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_advanced_item_58_title,
                    description = Res.string.examples_data_visual_advanced_item_58_description,
                    codeSnippet = Res.string.examples_data_visual_advanced_item_58_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.HexGrid
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_advanced_item_59_title,
                    description = Res.string.examples_data_visual_advanced_item_59_description,
                    codeSnippet = Res.string.examples_data_visual_advanced_item_59_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.ParticleField
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_advanced_item_60_title,
                    description = Res.string.examples_data_visual_advanced_item_60_description,
                    codeSnippet = Res.string.examples_data_visual_advanced_item_60_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.RotatingSquares
                ),
            ),
        ),
)

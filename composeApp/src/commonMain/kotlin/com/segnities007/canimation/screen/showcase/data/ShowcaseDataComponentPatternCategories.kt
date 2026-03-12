package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val showcaseCategoriesComponentPatterns: List<ShowcaseCategory> = listOf(
        ShowcaseCategory(
            id = "ui-patterns",
            title = Res.string.examples_data_ui_patterns_title,
            subtitle = Res.string.examples_data_ui_patterns_subtitle,
            tags = listOf(ShowcaseTagId.Ui, ShowcaseTagId.Interactive),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_ui_patterns_item_30_title,
                    description = Res.string.examples_data_ui_patterns_item_30_description,
                    codeSnippet = Res.string.examples_data_ui_patterns_item_30_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedBanner
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_ui_patterns_item_31_title,
                    description = Res.string.examples_data_ui_patterns_item_31_description,
                    codeSnippet = Res.string.examples_data_ui_patterns_item_31_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedTooltip
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_ui_patterns_item_32_title,
                    description = Res.string.examples_data_ui_patterns_item_32_description,
                    codeSnippet = Res.string.examples_data_ui_patterns_item_32_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedAlert
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_ui_patterns_item_33_title,
                    description = Res.string.examples_data_ui_patterns_item_33_description,
                    codeSnippet = Res.string.examples_data_ui_patterns_item_33_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedDropdown
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_ui_patterns_item_34_title,
                    description = Res.string.examples_data_ui_patterns_item_34_description,
                    codeSnippet = Res.string.examples_data_ui_patterns_item_34_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedProgressCard
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_ui_patterns_item_35_title,
                    description = Res.string.examples_data_ui_patterns_item_35_description,
                    codeSnippet = Res.string.examples_data_ui_patterns_item_35_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedDialog
                ),
            ),
        ),
        ShowcaseCategory(
            id = "visual-canvas",
            title = Res.string.examples_data_visual_canvas_title,
            subtitle = Res.string.examples_data_visual_canvas_subtitle,
            tags = listOf(ShowcaseTagId.Visual, ShowcaseTagId.Canvas),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_visual_canvas_item_36_title,
                    description = Res.string.examples_data_visual_canvas_item_36_description,
                    codeSnippet = Res.string.examples_data_visual_canvas_item_36_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.OrbitDots
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_canvas_item_37_title,
                    description = Res.string.examples_data_visual_canvas_item_37_description,
                    codeSnippet = Res.string.examples_data_visual_canvas_item_37_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.BouncingLoader
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_canvas_item_38_title,
                    description = Res.string.examples_data_visual_canvas_item_38_description,
                    codeSnippet = Res.string.examples_data_visual_canvas_item_38_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.GlowPulse
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_canvas_item_39_title,
                    description = Res.string.examples_data_visual_canvas_item_39_description,
                    codeSnippet = Res.string.examples_data_visual_canvas_item_39_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.WaveformBars
                ),
            ),
        ),
        ShowcaseCategory(
            id = "interactive-widgets",
            title = Res.string.examples_data_interactive_widgets_title,
            subtitle = Res.string.examples_data_interactive_widgets_subtitle,
            tags = listOf(ShowcaseTagId.Interactive, ShowcaseTagId.Ui),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_widgets_item_40_title,
                    description = Res.string.examples_data_interactive_widgets_item_40_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_40_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.LikeButton
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_widgets_item_41_title,
                    description = Res.string.examples_data_interactive_widgets_item_41_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_41_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.QuantitySelector
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_widgets_item_42_title,
                    description = Res.string.examples_data_interactive_widgets_item_42_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_42_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.RadioSelector
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_widgets_item_43_title,
                    description = Res.string.examples_data_interactive_widgets_item_43_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_43_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.SliderControl
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_widgets_item_44_title,
                    description = Res.string.examples_data_interactive_widgets_item_44_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_44_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedPasswordField
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_widgets_item_45_title,
                    description = Res.string.examples_data_interactive_widgets_item_45_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_45_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedFileUpload
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_widgets_item_46_title,
                    description = Res.string.examples_data_interactive_widgets_item_46_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_46_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedVote
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_widgets_item_47_title,
                    description = Res.string.examples_data_interactive_widgets_item_47_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_47_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedSearchBar
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_widgets_item_48_title,
                    description = Res.string.examples_data_interactive_widgets_item_48_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_48_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedFormValidation
                ),
            ),
        ),
)

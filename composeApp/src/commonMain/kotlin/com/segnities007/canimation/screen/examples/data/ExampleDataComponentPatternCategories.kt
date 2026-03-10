package com.segnities007.canimation.screen.examples.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val exampleCategoriesComponentPatterns: List<ExampleCategory> = listOf(
        ExampleCategory(
            id = "ui-patterns",
            title = Res.string.examples_data_ui_patterns_title,
            subtitle = Res.string.examples_data_ui_patterns_subtitle,
            accentLabel = "UI",
            tags = listOf("UI", "INTERACTIVE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_ui_patterns_item_30_title,
                    description = Res.string.examples_data_ui_patterns_item_30_description,
                    codeSnippet = Res.string.examples_data_ui_patterns_item_30_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedBanner
                ),
                ExampleItem(
                    title = Res.string.examples_data_ui_patterns_item_31_title,
                    description = Res.string.examples_data_ui_patterns_item_31_description,
                    codeSnippet = Res.string.examples_data_ui_patterns_item_31_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedTooltip
                ),
                ExampleItem(
                    title = Res.string.examples_data_ui_patterns_item_32_title,
                    description = Res.string.examples_data_ui_patterns_item_32_description,
                    codeSnippet = Res.string.examples_data_ui_patterns_item_32_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedAlert
                ),
                ExampleItem(
                    title = Res.string.examples_data_ui_patterns_item_33_title,
                    description = Res.string.examples_data_ui_patterns_item_33_description,
                    codeSnippet = Res.string.examples_data_ui_patterns_item_33_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedDropdown
                ),
                ExampleItem(
                    title = Res.string.examples_data_ui_patterns_item_34_title,
                    description = Res.string.examples_data_ui_patterns_item_34_description,
                    codeSnippet = Res.string.examples_data_ui_patterns_item_34_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedProgressCard
                ),
                ExampleItem(
                    title = Res.string.examples_data_ui_patterns_item_35_title,
                    description = Res.string.examples_data_ui_patterns_item_35_description,
                    codeSnippet = Res.string.examples_data_ui_patterns_item_35_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedDialog
                ),
            ),
        ),
        ExampleCategory(
            id = "visual-canvas",
            title = Res.string.examples_data_visual_canvas_title,
            subtitle = Res.string.examples_data_visual_canvas_subtitle,
            accentLabel = "VISUAL",
            tags = listOf("VISUAL", "CANVAS"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_visual_canvas_item_36_title,
                    description = Res.string.examples_data_visual_canvas_item_36_description,
                    codeSnippet = Res.string.examples_data_visual_canvas_item_36_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.OrbitDots
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_canvas_item_37_title,
                    description = Res.string.examples_data_visual_canvas_item_37_description,
                    codeSnippet = Res.string.examples_data_visual_canvas_item_37_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.BouncingLoader
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_canvas_item_38_title,
                    description = Res.string.examples_data_visual_canvas_item_38_description,
                    codeSnippet = Res.string.examples_data_visual_canvas_item_38_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.GlowPulse
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_canvas_item_39_title,
                    description = Res.string.examples_data_visual_canvas_item_39_description,
                    codeSnippet = Res.string.examples_data_visual_canvas_item_39_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.WaveformBars
                ),
            ),
        ),
        ExampleCategory(
            id = "interactive-widgets",
            title = Res.string.examples_data_interactive_widgets_title,
            subtitle = Res.string.examples_data_interactive_widgets_subtitle,
            accentLabel = "INTERACTIVE",
            tags = listOf("INTERACTIVE", "UI"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_interactive_widgets_item_40_title,
                    description = Res.string.examples_data_interactive_widgets_item_40_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_40_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.LikeButton
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_widgets_item_41_title,
                    description = Res.string.examples_data_interactive_widgets_item_41_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_41_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.QuantitySelector
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_widgets_item_42_title,
                    description = Res.string.examples_data_interactive_widgets_item_42_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_42_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.RadioSelector
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_widgets_item_43_title,
                    description = Res.string.examples_data_interactive_widgets_item_43_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_43_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.SliderControl
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_widgets_item_44_title,
                    description = Res.string.examples_data_interactive_widgets_item_44_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_44_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedPasswordField
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_widgets_item_45_title,
                    description = Res.string.examples_data_interactive_widgets_item_45_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_45_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedFileUpload
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_widgets_item_46_title,
                    description = Res.string.examples_data_interactive_widgets_item_46_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_46_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedVote
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_widgets_item_47_title,
                    description = Res.string.examples_data_interactive_widgets_item_47_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_47_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedSearchBar
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_widgets_item_48_title,
                    description = Res.string.examples_data_interactive_widgets_item_48_description,
                    codeSnippet = Res.string.examples_data_interactive_widgets_item_48_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedFormValidation
                ),
            ),
        ),
)

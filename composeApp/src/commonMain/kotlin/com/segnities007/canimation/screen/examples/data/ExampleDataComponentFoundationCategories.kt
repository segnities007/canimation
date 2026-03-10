package com.segnities007.canimation.screen.examples.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val exampleCategoriesComponentFoundations: List<ExampleCategory> = listOf(
        ExampleCategory(
            id = "interactive-controls",
            title = Res.string.examples_data_interactive_controls_title_2,
            subtitle = Res.string.examples_data_interactive_controls_subtitle_2,
            accentLabel = "UI",
            tags = listOf("UI", "INTERACTIVE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_01_title_2,
                    description = Res.string.examples_data_interactive_controls_item_01_description_2,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_01_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedTagCloud
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_02_title_2,
                    description = Res.string.examples_data_interactive_controls_item_02_description_2,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_02_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.ColorSwatchPicker
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_03_title_2,
                    description = Res.string.examples_data_interactive_controls_item_03_description_2,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_03_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedChipGroup
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_04_title_2,
                    description = Res.string.examples_data_interactive_controls_item_04_description_2,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_04_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedPricingToggle
                ),
            ),
        ),
        ExampleCategory(
            id = "visual-effects",
            title = Res.string.examples_data_visual_effects_title_2,
            subtitle = Res.string.examples_data_visual_effects_subtitle_2,
            accentLabel = "CANVAS",
            tags = listOf("UI", "CANVAS"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_05_title_2,
                    description = Res.string.examples_data_visual_effects_item_05_description_2,
                    codeSnippet = Res.string.examples_data_visual_effects_item_05_code_2, demoType = DemoType.Component, componentKey = ComponentDemoKeys.PulseRing
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_06_title_2,
                    description = Res.string.examples_data_visual_effects_item_06_description_2,
                    codeSnippet = Res.string.examples_data_visual_effects_item_06_code_2, demoType = DemoType.Component, componentKey = ComponentDemoKeys.WaveProgressBar
                ),
            ),
        ),
        ExampleCategory(
            id = "layout-patterns",
            title = Res.string.examples_data_layout_patterns_title,
            subtitle = Res.string.examples_data_layout_patterns_subtitle,
            accentLabel = "LAYOUT",
            tags = listOf("UI", "LAYOUT"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_layout_patterns_item_07_title,
                    description = Res.string.examples_data_layout_patterns_item_07_description,
                    codeSnippet = Res.string.examples_data_layout_patterns_item_07_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.StackedAvatars
                ),
                ExampleItem(
                    title = Res.string.examples_data_layout_patterns_item_08_title,
                    description = Res.string.examples_data_layout_patterns_item_08_description,
                    codeSnippet = Res.string.examples_data_layout_patterns_item_08_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedCodeBlock
                ),
                ExampleItem(
                    title = Res.string.examples_data_layout_patterns_item_09_title,
                    description = Res.string.examples_data_layout_patterns_item_09_description,
                    codeSnippet = Res.string.examples_data_layout_patterns_item_09_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedListItem
                ),
                ExampleItem(
                    title = Res.string.examples_data_layout_patterns_item_10_title,
                    description = Res.string.examples_data_layout_patterns_item_10_description,
                    codeSnippet = Res.string.examples_data_layout_patterns_item_10_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedFeatureGrid
                ),
                ExampleItem(
                    title = Res.string.examples_data_layout_patterns_item_11_title,
                    description = Res.string.examples_data_layout_patterns_item_11_description,
                    codeSnippet = Res.string.examples_data_layout_patterns_item_11_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedSearchResult
                ),
            ),
        ),
        ExampleCategory(
            id = "text-animations",
            title = Res.string.examples_data_text_animations_title,
            subtitle = Res.string.examples_data_text_animations_subtitle,
            accentLabel = "TEXT",
            tags = listOf("TEXT", "UI"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_text_animations_item_12_title,
                    description = Res.string.examples_data_text_animations_item_12_description,
                    codeSnippet = Res.string.examples_data_text_animations_item_12_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextFadeReveal
                ),
                ExampleItem(
                    title = Res.string.examples_data_text_animations_item_13_title,
                    description = Res.string.examples_data_text_animations_item_13_description,
                    codeSnippet = Res.string.examples_data_text_animations_item_13_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextHighlighter
                ),
                ExampleItem(
                    title = Res.string.examples_data_text_animations_item_14_title,
                    description = Res.string.examples_data_text_animations_item_14_description,
                    codeSnippet = Res.string.examples_data_text_animations_item_14_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextShuffleWord
                ),
                ExampleItem(
                    title = Res.string.examples_data_text_animations_item_15_title,
                    description = Res.string.examples_data_text_animations_item_15_description,
                    codeSnippet = Res.string.examples_data_text_animations_item_15_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextGradientReveal
                ),
                ExampleItem(
                    title = Res.string.examples_data_text_animations_item_16_title,
                    description = Res.string.examples_data_text_animations_item_16_description,
                    codeSnippet = Res.string.examples_data_text_animations_item_16_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextRotateWords
                ),
            ),
        ),
        ExampleCategory(
            id = "card-components",
            title = Res.string.examples_data_card_components_title,
            subtitle = Res.string.examples_data_card_components_subtitle,
            accentLabel = "CARDS",
            tags = listOf("CARDS", "UI"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_card_components_item_17_title,
                    description = Res.string.examples_data_card_components_item_17_description,
                    codeSnippet = Res.string.examples_data_card_components_item_17_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.ProfileCard
                ),
                ExampleItem(
                    title = Res.string.examples_data_card_components_item_18_title,
                    description = Res.string.examples_data_card_components_item_18_description,
                    codeSnippet = Res.string.examples_data_card_components_item_18_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.PricingCard
                ),
                ExampleItem(
                    title = Res.string.examples_data_card_components_item_19_title,
                    description = Res.string.examples_data_card_components_item_19_description,
                    codeSnippet = Res.string.examples_data_card_components_item_19_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.NotificationCard
                ),
                ExampleItem(
                    title = Res.string.examples_data_card_components_item_20_title,
                    description = Res.string.examples_data_card_components_item_20_description,
                    codeSnippet = Res.string.examples_data_card_components_item_20_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.TestimonialCard
                ),
                ExampleItem(
                    title = Res.string.examples_data_card_components_item_21_title,
                    description = Res.string.examples_data_card_components_item_21_description,
                    codeSnippet = Res.string.examples_data_card_components_item_21_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.MetricCard
                ),
                ExampleItem(
                    title = Res.string.examples_data_card_components_item_22_title,
                    description = Res.string.examples_data_card_components_item_22_description,
                    codeSnippet = Res.string.examples_data_card_components_item_22_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.ProductCard
                ),
            ),
        ),
        ExampleCategory(
            id = "nav-components",
            title = Res.string.examples_data_nav_components_title,
            subtitle = Res.string.examples_data_nav_components_subtitle,
            accentLabel = "NAV",
            tags = listOf("NAV", "NAVIGATION"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_nav_components_item_23_title,
                    description = Res.string.examples_data_nav_components_item_23_description,
                    codeSnippet = Res.string.examples_data_nav_components_item_23_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.SideMenuReveal
                ),
                ExampleItem(
                    title = Res.string.examples_data_nav_components_item_24_title,
                    description = Res.string.examples_data_nav_components_item_24_description,
                    codeSnippet = Res.string.examples_data_nav_components_item_24_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.PaginationDots
                ),
                ExampleItem(
                    title = Res.string.examples_data_nav_components_item_25_title,
                    description = Res.string.examples_data_nav_components_item_25_description,
                    codeSnippet = Res.string.examples_data_nav_components_item_25_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.CommandPalette
                ),
            ),
        ),
        ExampleCategory(
            id = "charts-data",
            title = Res.string.examples_data_charts_data_title,
            subtitle = Res.string.examples_data_charts_data_subtitle,
            accentLabel = "CHARTS",
            tags = listOf("CHARTS", "DATA"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_charts_data_item_26_title,
                    description = Res.string.examples_data_charts_data_item_26_description,
                    codeSnippet = Res.string.examples_data_charts_data_item_26_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.MiniBarChart
                ),
                ExampleItem(
                    title = Res.string.examples_data_charts_data_item_27_title,
                    description = Res.string.examples_data_charts_data_item_27_description,
                    codeSnippet = Res.string.examples_data_charts_data_item_27_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.DonutChart
                ),
                ExampleItem(
                    title = Res.string.examples_data_charts_data_item_28_title,
                    description = Res.string.examples_data_charts_data_item_28_description,
                    codeSnippet = Res.string.examples_data_charts_data_item_28_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.SparkLine
                ),
                ExampleItem(
                    title = Res.string.examples_data_charts_data_item_29_title,
                    description = Res.string.examples_data_charts_data_item_29_description,
                    codeSnippet = Res.string.examples_data_charts_data_item_29_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.DataTable
                ),
            ),
        ),
)

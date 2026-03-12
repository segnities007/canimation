package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val showcaseCategoriesComponentFoundations: List<ShowcaseCategory> = listOf(
        ShowcaseCategory(
            id = "interactive-controls",
            title = Res.string.examples_data_interactive_controls_title_2,
            subtitle = Res.string.examples_data_interactive_controls_subtitle_2,
            tags = listOf(ShowcaseTagId.Ui, ShowcaseTagId.Interactive),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_01_title_2,
                    description = Res.string.examples_data_interactive_controls_item_01_description_2,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_01_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedTagCloud
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_02_title_2,
                    description = Res.string.examples_data_interactive_controls_item_02_description_2,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_02_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.ColorSwatchPicker
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_03_title_2,
                    description = Res.string.examples_data_interactive_controls_item_03_description_2,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_03_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedChipGroup
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_04_title_2,
                    description = Res.string.examples_data_interactive_controls_item_04_description_2,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_04_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedPricingToggle
                ),
            ),
        ),
        ShowcaseCategory(
            id = "visual-effects",
            title = Res.string.examples_data_visual_effects_title_2,
            subtitle = Res.string.examples_data_visual_effects_subtitle_2,
            tags = listOf(ShowcaseTagId.Ui, ShowcaseTagId.Canvas),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_05_title_2,
                    description = Res.string.examples_data_visual_effects_item_05_description_2,
                    codeSnippet = Res.string.examples_data_visual_effects_item_05_code_2, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.PulseRing
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_06_title_2,
                    description = Res.string.examples_data_visual_effects_item_06_description_2,
                    codeSnippet = Res.string.examples_data_visual_effects_item_06_code_2, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.WaveProgressBar
                ),
            ),
        ),
        ShowcaseCategory(
            id = "layout-patterns",
            title = Res.string.examples_data_layout_patterns_title,
            subtitle = Res.string.examples_data_layout_patterns_subtitle,
            tags = listOf(ShowcaseTagId.Ui, ShowcaseTagId.Layout),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_layout_patterns_item_07_title,
                    description = Res.string.examples_data_layout_patterns_item_07_description,
                    codeSnippet = Res.string.examples_data_layout_patterns_item_07_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.StackedAvatars
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_layout_patterns_item_08_title,
                    description = Res.string.examples_data_layout_patterns_item_08_description,
                    codeSnippet = Res.string.examples_data_layout_patterns_item_08_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedCodeBlock
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_layout_patterns_item_09_title,
                    description = Res.string.examples_data_layout_patterns_item_09_description,
                    codeSnippet = Res.string.examples_data_layout_patterns_item_09_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedListItem
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_layout_patterns_item_10_title,
                    description = Res.string.examples_data_layout_patterns_item_10_description,
                    codeSnippet = Res.string.examples_data_layout_patterns_item_10_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedFeatureGrid
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_layout_patterns_item_11_title,
                    description = Res.string.examples_data_layout_patterns_item_11_description,
                    codeSnippet = Res.string.examples_data_layout_patterns_item_11_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedSearchResult
                ),
            ),
        ),
        ShowcaseCategory(
            id = "text-animations",
            title = Res.string.examples_data_text_animations_title,
            subtitle = Res.string.examples_data_text_animations_subtitle,
            tags = listOf(ShowcaseTagId.Text, ShowcaseTagId.Ui),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_text_animations_item_12_title,
                    description = Res.string.examples_data_text_animations_item_12_description,
                    codeSnippet = Res.string.examples_data_text_animations_item_12_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.TextFadeReveal
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_text_animations_item_13_title,
                    description = Res.string.examples_data_text_animations_item_13_description,
                    codeSnippet = Res.string.examples_data_text_animations_item_13_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.TextHighlighter
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_text_animations_item_14_title,
                    description = Res.string.examples_data_text_animations_item_14_description,
                    codeSnippet = Res.string.examples_data_text_animations_item_14_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.TextShuffleWord
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_text_animations_item_15_title,
                    description = Res.string.examples_data_text_animations_item_15_description,
                    codeSnippet = Res.string.examples_data_text_animations_item_15_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.TextGradientReveal
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_text_animations_item_16_title,
                    description = Res.string.examples_data_text_animations_item_16_description,
                    codeSnippet = Res.string.examples_data_text_animations_item_16_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.TextRotateWords
                ),
            ),
        ),
        ShowcaseCategory(
            id = "card-components",
            title = Res.string.examples_data_card_components_title,
            subtitle = Res.string.examples_data_card_components_subtitle,
            tags = listOf(ShowcaseTagId.Cards, ShowcaseTagId.Ui),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_card_components_item_17_title,
                    description = Res.string.examples_data_card_components_item_17_description,
                    codeSnippet = Res.string.examples_data_card_components_item_17_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.ProfileCard
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_card_components_item_18_title,
                    description = Res.string.examples_data_card_components_item_18_description,
                    codeSnippet = Res.string.examples_data_card_components_item_18_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.PricingCard
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_card_components_item_19_title,
                    description = Res.string.examples_data_card_components_item_19_description,
                    codeSnippet = Res.string.examples_data_card_components_item_19_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.NotificationCard
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_card_components_item_20_title,
                    description = Res.string.examples_data_card_components_item_20_description,
                    codeSnippet = Res.string.examples_data_card_components_item_20_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.TestimonialCard
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_card_components_item_21_title,
                    description = Res.string.examples_data_card_components_item_21_description,
                    codeSnippet = Res.string.examples_data_card_components_item_21_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.MetricCard
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_card_components_item_22_title,
                    description = Res.string.examples_data_card_components_item_22_description,
                    codeSnippet = Res.string.examples_data_card_components_item_22_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.ProductCard
                ),
            ),
        ),
        ShowcaseCategory(
            id = "nav-components",
            title = Res.string.examples_data_nav_components_title,
            subtitle = Res.string.examples_data_nav_components_subtitle,
            tags = listOf(ShowcaseTagId.Nav, ShowcaseTagId.Navigation),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_nav_components_item_23_title,
                    description = Res.string.examples_data_nav_components_item_23_description,
                    codeSnippet = Res.string.examples_data_nav_components_item_23_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.SideMenuReveal
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_nav_components_item_24_title,
                    description = Res.string.examples_data_nav_components_item_24_description,
                    codeSnippet = Res.string.examples_data_nav_components_item_24_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.PaginationDots
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_nav_components_item_25_title,
                    description = Res.string.examples_data_nav_components_item_25_description,
                    codeSnippet = Res.string.examples_data_nav_components_item_25_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.CommandPalette
                ),
            ),
        ),
        ShowcaseCategory(
            id = "charts-data",
            title = Res.string.examples_data_charts_data_title,
            subtitle = Res.string.examples_data_charts_data_subtitle,
            tags = listOf(ShowcaseTagId.Charts, ShowcaseTagId.Data),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_charts_data_item_26_title,
                    description = Res.string.examples_data_charts_data_item_26_description,
                    codeSnippet = Res.string.examples_data_charts_data_item_26_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.MiniBarChart
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_charts_data_item_27_title,
                    description = Res.string.examples_data_charts_data_item_27_description,
                    codeSnippet = Res.string.examples_data_charts_data_item_27_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.DonutChart
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_charts_data_item_28_title,
                    description = Res.string.examples_data_charts_data_item_28_description,
                    codeSnippet = Res.string.examples_data_charts_data_item_28_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.SparkLine
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_charts_data_item_29_title,
                    description = Res.string.examples_data_charts_data_item_29_description,
                    codeSnippet = Res.string.examples_data_charts_data_item_29_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.DataTable
                ),
            ),
        ),
)

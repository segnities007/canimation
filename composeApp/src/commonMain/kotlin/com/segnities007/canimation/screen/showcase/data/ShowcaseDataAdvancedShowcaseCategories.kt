package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*


internal val showcaseCategoriesAdvancedShowcase: List<ShowcaseCategory> = listOf(
        ShowcaseCategory(
            id = "interactive-advanced",
            title = Res.string.examples_data_interactive_advanced_title,
            subtitle = Res.string.examples_data_interactive_advanced_subtitle,
            tags = listOf(ShowcaseTagId.Interactive, ShowcaseTagId.Ui),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_advanced_item_01_title,
                    description = Res.string.examples_data_interactive_advanced_item_01_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_01_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.SegmentedControl
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_advanced_item_02_title,
                    description = Res.string.examples_data_interactive_advanced_item_02_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_02_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedSwitch
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_advanced_item_03_title,
                    description = Res.string.examples_data_interactive_advanced_item_03_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_03_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedPinInput
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_advanced_item_04_title,
                    description = Res.string.examples_data_interactive_advanced_item_04_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_04_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedColorPicker
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_advanced_item_05_title,
                    description = Res.string.examples_data_interactive_advanced_item_05_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_05_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedNotificationBell
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_advanced_item_06_title,
                    description = Res.string.examples_data_interactive_advanced_item_06_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_06_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedCountdownTimer
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_advanced_item_07_title,
                    description = Res.string.examples_data_interactive_advanced_item_07_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_07_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedCreditCard
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_advanced_item_08_title,
                    description = Res.string.examples_data_interactive_advanced_item_08_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_08_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedThemeToggle
                ),
            ),
        ),
        // ─── Component advanced showcase categories ───
        ShowcaseCategory(
            id = "text-advanced",
            title = Res.string.examples_data_text_advanced_title,
            subtitle = Res.string.examples_data_text_advanced_subtitle,
            tags = listOf(ShowcaseTagId.Text, ShowcaseTagId.Ui),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_text_advanced_item_09_title,
                    description = Res.string.examples_data_text_advanced_item_09_description,
                    codeSnippet = Res.string.examples_data_text_advanced_item_09_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.TypewriterEffect
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_text_advanced_item_10_title,
                    description = Res.string.examples_data_text_advanced_item_10_description,
                    codeSnippet = Res.string.examples_data_text_advanced_item_10_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.TextMorph
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_text_advanced_item_11_title,
                    description = Res.string.examples_data_text_advanced_item_11_description,
                    codeSnippet = Res.string.examples_data_text_advanced_item_11_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.TextStrikethrough
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_text_advanced_item_12_title,
                    description = Res.string.examples_data_text_advanced_item_12_description,
                    codeSnippet = Res.string.examples_data_text_advanced_item_12_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.TextGlitch
                ),
            ),
        ),
        ShowcaseCategory(
            id = "cards-advanced",
            title = Res.string.examples_data_cards_advanced_title,
            subtitle = Res.string.examples_data_cards_advanced_subtitle,
            tags = listOf(ShowcaseTagId.Cards, ShowcaseTagId.Ui),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_cards_advanced_item_13_title,
                    description = Res.string.examples_data_cards_advanced_item_13_description,
                    codeSnippet = Res.string.examples_data_cards_advanced_item_13_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.RecipeCard
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_cards_advanced_item_14_title,
                    description = Res.string.examples_data_cards_advanced_item_14_description,
                    codeSnippet = Res.string.examples_data_cards_advanced_item_14_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.WeatherCard
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_cards_advanced_item_15_title,
                    description = Res.string.examples_data_cards_advanced_item_15_description,
                    codeSnippet = Res.string.examples_data_cards_advanced_item_15_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.EventCard
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_cards_advanced_item_16_title,
                    description = Res.string.examples_data_cards_advanced_item_16_description,
                    codeSnippet = Res.string.examples_data_cards_advanced_item_16_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.MusicPlayerCard
                ),
            ),
        ),
        ShowcaseCategory(
            id = "nav-advanced",
            title = Res.string.examples_data_nav_advanced_title,
            subtitle = Res.string.examples_data_nav_advanced_subtitle,
            tags = listOf(ShowcaseTagId.Nav, ShowcaseTagId.Navigation),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_nav_advanced_item_17_title,
                    description = Res.string.examples_data_nav_advanced_item_17_description,
                    codeSnippet = Res.string.examples_data_nav_advanced_item_17_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.BottomNavBar
                ),
            ),
        ),
        ShowcaseCategory(
            id = "data-advanced",
            title = Res.string.examples_data_data_advanced_title,
            subtitle = Res.string.examples_data_data_advanced_subtitle,
            tags = listOf(ShowcaseTagId.Charts, ShowcaseTagId.Data),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_data_advanced_item_18_title,
                    description = Res.string.examples_data_data_advanced_item_18_description,
                    codeSnippet = Res.string.examples_data_data_advanced_item_18_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.RadarChart
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_data_advanced_item_19_title,
                    description = Res.string.examples_data_data_advanced_item_19_description,
                    codeSnippet = Res.string.examples_data_data_advanced_item_19_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.HorizontalBarChart
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_data_advanced_item_20_title,
                    description = Res.string.examples_data_data_advanced_item_20_description,
                    codeSnippet = Res.string.examples_data_data_advanced_item_20_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.GaugeChart
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_data_advanced_item_21_title,
                    description = Res.string.examples_data_data_advanced_item_21_description,
                    codeSnippet = Res.string.examples_data_data_advanced_item_21_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.LiveCounter
                ),
            ),
        ),
)

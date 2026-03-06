package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*


internal val exampleCategoriesChunk06: List<ExampleCategory> = listOf(
        ExampleCategory(
            id = "interactive-advanced",
            title = Res.string.examples_data_interactive_advanced_title,
            subtitle = Res.string.examples_data_interactive_advanced_subtitle,
            accentLabel = "INTERACTIVE",
            tags = listOf("INTERACTIVE", "UI"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_interactive_advanced_item_01_title,
                    description = Res.string.examples_data_interactive_advanced_item_01_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_01_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.SegmentedControl
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_advanced_item_02_title,
                    description = Res.string.examples_data_interactive_advanced_item_02_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_02_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedSwitch
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_advanced_item_03_title,
                    description = Res.string.examples_data_interactive_advanced_item_03_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_03_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedPinInput
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_advanced_item_04_title,
                    description = Res.string.examples_data_interactive_advanced_item_04_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_04_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedColorPicker
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_advanced_item_05_title,
                    description = Res.string.examples_data_interactive_advanced_item_05_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_05_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedNotificationBell
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_advanced_item_06_title,
                    description = Res.string.examples_data_interactive_advanced_item_06_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_06_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedCountdownTimer
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_advanced_item_07_title,
                    description = Res.string.examples_data_interactive_advanced_item_07_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_07_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedCreditCard
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_advanced_item_08_title,
                    description = Res.string.examples_data_interactive_advanced_item_08_description,
                    codeSnippet = Res.string.examples_data_interactive_advanced_item_08_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedThemeToggle
                ),
            ),
        ),
        // ─── ComponentAnimations10 categories ───
        ExampleCategory(
            id = "text-advanced",
            title = Res.string.examples_data_text_advanced_title,
            subtitle = Res.string.examples_data_text_advanced_subtitle,
            accentLabel = "TEXT",
            tags = listOf("TEXT", "UI"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_text_advanced_item_09_title,
                    description = Res.string.examples_data_text_advanced_item_09_description,
                    codeSnippet = Res.string.examples_data_text_advanced_item_09_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.TypewriterEffect
                ),
                ExampleItem(
                    title = Res.string.examples_data_text_advanced_item_10_title,
                    description = Res.string.examples_data_text_advanced_item_10_description,
                    codeSnippet = Res.string.examples_data_text_advanced_item_10_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextMorph
                ),
                ExampleItem(
                    title = Res.string.examples_data_text_advanced_item_11_title,
                    description = Res.string.examples_data_text_advanced_item_11_description,
                    codeSnippet = Res.string.examples_data_text_advanced_item_11_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextStrikethrough
                ),
                ExampleItem(
                    title = Res.string.examples_data_text_advanced_item_12_title,
                    description = Res.string.examples_data_text_advanced_item_12_description,
                    codeSnippet = Res.string.examples_data_text_advanced_item_12_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextGlitch
                ),
            ),
        ),
        ExampleCategory(
            id = "cards-advanced",
            title = Res.string.examples_data_cards_advanced_title,
            subtitle = Res.string.examples_data_cards_advanced_subtitle,
            accentLabel = "CARDS",
            tags = listOf("CARDS", "UI"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_cards_advanced_item_13_title,
                    description = Res.string.examples_data_cards_advanced_item_13_description,
                    codeSnippet = Res.string.examples_data_cards_advanced_item_13_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.RecipeCard
                ),
                ExampleItem(
                    title = Res.string.examples_data_cards_advanced_item_14_title,
                    description = Res.string.examples_data_cards_advanced_item_14_description,
                    codeSnippet = Res.string.examples_data_cards_advanced_item_14_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.WeatherCard
                ),
                ExampleItem(
                    title = Res.string.examples_data_cards_advanced_item_15_title,
                    description = Res.string.examples_data_cards_advanced_item_15_description,
                    codeSnippet = Res.string.examples_data_cards_advanced_item_15_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.EventCard
                ),
                ExampleItem(
                    title = Res.string.examples_data_cards_advanced_item_16_title,
                    description = Res.string.examples_data_cards_advanced_item_16_description,
                    codeSnippet = Res.string.examples_data_cards_advanced_item_16_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.MusicPlayerCard
                ),
            ),
        ),
        ExampleCategory(
            id = "nav-advanced",
            title = Res.string.examples_data_nav_advanced_title,
            subtitle = Res.string.examples_data_nav_advanced_subtitle,
            accentLabel = "NAV",
            tags = listOf("NAV", "NAVIGATION"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_nav_advanced_item_17_title,
                    description = Res.string.examples_data_nav_advanced_item_17_description,
                    codeSnippet = Res.string.examples_data_nav_advanced_item_17_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.BottomNavBar
                ),
            ),
        ),
        ExampleCategory(
            id = "data-advanced",
            title = Res.string.examples_data_data_advanced_title,
            subtitle = Res.string.examples_data_data_advanced_subtitle,
            accentLabel = "CHARTS",
            tags = listOf("CHARTS", "DATA"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_data_advanced_item_18_title,
                    description = Res.string.examples_data_data_advanced_item_18_description,
                    codeSnippet = Res.string.examples_data_data_advanced_item_18_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.RadarChart
                ),
                ExampleItem(
                    title = Res.string.examples_data_data_advanced_item_19_title,
                    description = Res.string.examples_data_data_advanced_item_19_description,
                    codeSnippet = Res.string.examples_data_data_advanced_item_19_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.HorizontalBarChart
                ),
                ExampleItem(
                    title = Res.string.examples_data_data_advanced_item_20_title,
                    description = Res.string.examples_data_data_advanced_item_20_description,
                    codeSnippet = Res.string.examples_data_data_advanced_item_20_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.GaugeChart
                ),
                ExampleItem(
                    title = Res.string.examples_data_data_advanced_item_21_title,
                    description = Res.string.examples_data_data_advanced_item_21_description,
                    codeSnippet = Res.string.examples_data_data_advanced_item_21_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.LiveCounter
                ),
            ),
        ),
)

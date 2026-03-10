package com.segnities007.canimation.screen.examples.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val exampleCategoriesExtendedUi: List<ExampleCategory> = listOf(
        ExampleCategory(
            id = "navigation-ui",
            title = Res.string.examples_data_navigation_ui_title,
            subtitle = Res.string.examples_data_navigation_ui_subtitle,
            accentLabel = "UI",
            tags = listOf("UI", "NAVIGATION"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_navigation_ui_item_48_title,
                    description = Res.string.examples_data_navigation_ui_item_48_description,
                    codeSnippet = Res.string.examples_data_navigation_ui_item_48_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedBreadcrumb
                ),
                ExampleItem(
                    title = Res.string.examples_data_navigation_ui_item_49_title,
                    description = Res.string.examples_data_navigation_ui_item_49_description,
                    codeSnippet = Res.string.examples_data_navigation_ui_item_49_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedNavItem
                ),
            ),
        ),
        ExampleCategory(
            id = "data-display",
            title = Res.string.examples_data_data_display_title,
            subtitle = Res.string.examples_data_data_display_subtitle,
            accentLabel = "UI",
            tags = listOf("UI", "DATA"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_data_display_item_50_title,
                    description = Res.string.examples_data_data_display_item_50_description,
                    codeSnippet = Res.string.examples_data_data_display_item_50_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedTimeline
                ),
                ExampleItem(
                    title = Res.string.examples_data_data_display_item_51_title,
                    description = Res.string.examples_data_data_display_item_51_description,
                    codeSnippet = Res.string.examples_data_data_display_item_51_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedStatCard
                ),
                ExampleItem(
                    title = Res.string.examples_data_data_display_item_52_title,
                    description = Res.string.examples_data_data_display_item_52_description,
                    codeSnippet = Res.string.examples_data_data_display_item_52_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedKpi
                ),
                ExampleItem(
                    title = Res.string.examples_data_data_display_item_53_title,
                    description = Res.string.examples_data_data_display_item_53_description,
                    codeSnippet = Res.string.examples_data_data_display_item_53_code, demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedRating
                ),
            ),
        ),
)

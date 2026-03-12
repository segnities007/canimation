package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val showcaseCategoriesExtendedUi: List<ShowcaseCategory> = listOf(
        ShowcaseCategory(
            id = "navigation-ui",
            title = Res.string.examples_data_navigation_ui_title,
            subtitle = Res.string.examples_data_navigation_ui_subtitle,
            tags = listOf(ShowcaseTagId.Ui, ShowcaseTagId.Navigation),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_navigation_ui_item_48_title,
                    description = Res.string.examples_data_navigation_ui_item_48_description,
                    codeSnippet = Res.string.examples_data_navigation_ui_item_48_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedBreadcrumb
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_navigation_ui_item_49_title,
                    description = Res.string.examples_data_navigation_ui_item_49_description,
                    codeSnippet = Res.string.examples_data_navigation_ui_item_49_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedNavItem
                ),
            ),
        ),
        ShowcaseCategory(
            id = "data-display",
            title = Res.string.examples_data_data_display_title,
            subtitle = Res.string.examples_data_data_display_subtitle,
            tags = listOf(ShowcaseTagId.Ui, ShowcaseTagId.Data),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_data_display_item_50_title,
                    description = Res.string.examples_data_data_display_item_50_description,
                    codeSnippet = Res.string.examples_data_data_display_item_50_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedTimeline
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_data_display_item_51_title,
                    description = Res.string.examples_data_data_display_item_51_description,
                    codeSnippet = Res.string.examples_data_data_display_item_51_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedStatCard
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_data_display_item_52_title,
                    description = Res.string.examples_data_data_display_item_52_description,
                    codeSnippet = Res.string.examples_data_data_display_item_52_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedKpi
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_data_display_item_53_title,
                    description = Res.string.examples_data_data_display_item_53_description,
                    codeSnippet = Res.string.examples_data_data_display_item_53_code, demoType = ShowcaseDemoType.Component, componentKey = ShowcaseComponentDemoKeys.AnimatedRating
                ),
            ),
        ),
)

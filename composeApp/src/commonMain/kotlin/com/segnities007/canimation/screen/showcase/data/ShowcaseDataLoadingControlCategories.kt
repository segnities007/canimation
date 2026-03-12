package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*



internal val showcaseCategoriesLoadingAndControls: List<ShowcaseCategory> = listOf(
        // ── Loading & Progress ──
        ShowcaseCategory(
            id = "loading-progress",
            title = Res.string.examples_data_loading_progress_title,
            subtitle = Res.string.examples_data_loading_progress_subtitle,
            tags = listOf(ShowcaseTagId.Loading),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_loading_progress_item_01_title,
                    description = Res.string.examples_data_loading_progress_item_01_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.PulseLoadingDots,
                    codeSnippet = Res.string.examples_data_loading_progress_item_01_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_loading_progress_item_02_title,
                    description = Res.string.examples_data_loading_progress_item_02_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ShimmerEffect,
                    codeSnippet = Res.string.examples_data_loading_progress_item_02_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_loading_progress_item_03_title,
                    description = Res.string.examples_data_loading_progress_item_03_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.SkeletonLoader,
                    codeSnippet = Res.string.examples_data_loading_progress_item_03_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_loading_progress_item_04_title,
                    description = Res.string.examples_data_loading_progress_item_04_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ProgressRing,
                    codeSnippet = Res.string.examples_data_loading_progress_item_04_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_loading_progress_item_05_title,
                    description = Res.string.examples_data_loading_progress_item_05_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.LiquidFill,
                    codeSnippet = Res.string.examples_data_loading_progress_item_05_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_loading_progress_item_06_title,
                    description = Res.string.examples_data_loading_progress_item_06_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.GlowProgressBar,
                    codeSnippet = Res.string.examples_data_loading_progress_item_06_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_loading_progress_item_07_title,
                    description = Res.string.examples_data_loading_progress_item_07_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.MorphProgressIndicator,
                    codeSnippet = Res.string.examples_data_loading_progress_item_07_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_loading_progress_item_08_title,
                    description = Res.string.examples_data_loading_progress_item_08_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ProgressScrubber,
                    codeSnippet = Res.string.examples_data_loading_progress_item_08_code,
                ),
            ),
        ),
        // ── Counters & Data ──
        ShowcaseCategory(
            id = "counters-data",
            title = Res.string.examples_data_counters_data_title,
            subtitle = Res.string.examples_data_counters_data_subtitle,
            tags = listOf(ShowcaseTagId.Data),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_counters_data_item_01_title,
                    description = Res.string.examples_data_counters_data_item_01_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.AnimatedCounter,
                    codeSnippet = Res.string.examples_data_counters_data_item_09_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_counters_data_item_02_title,
                    description = Res.string.examples_data_counters_data_item_02_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.EngagementStats,
                    codeSnippet = Res.string.examples_data_counters_data_item_10_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_counters_data_item_03_title,
                    description = Res.string.examples_data_counters_data_item_03_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.PriceSwitcher,
                    codeSnippet = Res.string.examples_data_counters_data_item_11_code,
                ),
            ),
        ),
        // ── Navigation & Layout ──
        ShowcaseCategory(
            id = "navigation-layout",
            title = Res.string.examples_data_navigation_layout_title,
            subtitle = Res.string.examples_data_navigation_layout_subtitle,
            tags = listOf(ShowcaseTagId.Nav),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_navigation_layout_item_01_title,
                    description = Res.string.examples_data_navigation_layout_item_01_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.AnimatedTabs,
                    codeSnippet = Res.string.examples_data_navigation_layout_item_12_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_navigation_layout_item_02_title,
                    description = Res.string.examples_data_navigation_layout_item_02_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.MegaMenuReveal,
                    codeSnippet = Res.string.examples_data_navigation_layout_item_13_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_navigation_layout_item_03_title,
                    description = Res.string.examples_data_navigation_layout_item_03_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.SmoothTabIndicator,
                    codeSnippet = Res.string.examples_data_navigation_layout_item_14_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_navigation_layout_item_04_title,
                    description = Res.string.examples_data_navigation_layout_item_04_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.CircularMenu,
                    codeSnippet = Res.string.examples_data_navigation_layout_item_15_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_navigation_layout_item_05_title,
                    description = Res.string.examples_data_navigation_layout_item_05_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ElasticDrawer,
                    codeSnippet = Res.string.examples_data_navigation_layout_item_16_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_navigation_layout_item_06_title,
                    description = Res.string.examples_data_navigation_layout_item_06_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ScrollDirectionHeader,
                    codeSnippet = Res.string.examples_data_navigation_layout_item_17_code,
                ),
            ),
        ),
        // ── Interactive Controls ──
        ShowcaseCategory(
            id = "interactive-controls",
            title = Res.string.examples_data_interactive_controls_title,
            subtitle = Res.string.examples_data_interactive_controls_subtitle,
            tags = listOf(ShowcaseTagId.Interactive),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_01_title,
                    description = Res.string.examples_data_interactive_controls_item_01_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.HoldToConfirm,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_18_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_02_title,
                    description = Res.string.examples_data_interactive_controls_item_02_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.SwipeActions,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_19_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_03_title,
                    description = Res.string.examples_data_interactive_controls_item_03_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.MultiStateBadge,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_20_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_04_title,
                    description = Res.string.examples_data_interactive_controls_item_04_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ElasticPull,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_21_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_05_title,
                    description = Res.string.examples_data_interactive_controls_item_05_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ExpandingSearch,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_22_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_06_title,
                    description = Res.string.examples_data_interactive_controls_item_06_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.IOSSlider,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_23_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_07_title,
                    description = Res.string.examples_data_interactive_controls_item_07_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.CheckboxAnimation,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_24_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_08_title,
                    description = Res.string.examples_data_interactive_controls_item_08_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.SwitchAnimation,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_25_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_09_title,
                    description = Res.string.examples_data_interactive_controls_item_09_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.MagneticButton,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_26_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_10_title,
                    description = Res.string.examples_data_interactive_controls_item_10_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.RippleButton,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_27_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_11_title,
                    description = Res.string.examples_data_interactive_controls_item_11_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.SpringChip,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_28_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_12_title,
                    description = Res.string.examples_data_interactive_controls_item_12_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ToastNotification,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_29_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_interactive_controls_item_13_title,
                    description = Res.string.examples_data_interactive_controls_item_13_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.CoinFlip,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_30_code,
                ),
            ),
        ),
)

package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*



internal val exampleCategoriesChunk03Part01: List<ExampleCategory> = listOf(
        // ── Loading & Progress ──
        ExampleCategory(
            id = "loading-progress",
            title = Res.string.examples_data_loading_progress_title,
            subtitle = Res.string.examples_data_loading_progress_subtitle,
            accentLabel = "LOADING",
            tags = listOf("LOADING"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_loading_progress_item_01_title,
                    description = Res.string.examples_data_loading_progress_item_01_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PulseLoadingDots,
                    codeSnippet = Res.string.examples_data_loading_progress_item_01_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_loading_progress_item_02_title,
                    description = Res.string.examples_data_loading_progress_item_02_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ShimmerEffect,
                    codeSnippet = Res.string.examples_data_loading_progress_item_02_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_loading_progress_item_03_title,
                    description = Res.string.examples_data_loading_progress_item_03_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SkeletonLoader,
                    codeSnippet = Res.string.examples_data_loading_progress_item_03_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_loading_progress_item_04_title,
                    description = Res.string.examples_data_loading_progress_item_04_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ProgressRing,
                    codeSnippet = Res.string.examples_data_loading_progress_item_04_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_loading_progress_item_05_title,
                    description = Res.string.examples_data_loading_progress_item_05_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.LiquidFill,
                    codeSnippet = Res.string.examples_data_loading_progress_item_05_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_loading_progress_item_06_title,
                    description = Res.string.examples_data_loading_progress_item_06_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.GlowProgressBar,
                    codeSnippet = Res.string.examples_data_loading_progress_item_06_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_loading_progress_item_07_title,
                    description = Res.string.examples_data_loading_progress_item_07_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.MorphProgressIndicator,
                    codeSnippet = Res.string.examples_data_loading_progress_item_07_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_loading_progress_item_08_title,
                    description = Res.string.examples_data_loading_progress_item_08_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ProgressScrubber,
                    codeSnippet = Res.string.examples_data_loading_progress_item_08_code,
                ),
            ),
        ),
        // ── Counters & Data ──
        ExampleCategory(
            id = "counters-data",
            title = Res.string.examples_data_counters_data_title,
            subtitle = Res.string.examples_data_counters_data_subtitle,
            accentLabel = "DATA",
            tags = listOf("DATA"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_counters_data_item_01_title,
                    description = Res.string.examples_data_counters_data_item_01_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.AnimatedCounter,
                    codeSnippet = Res.string.examples_data_counters_data_item_09_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_counters_data_item_02_title,
                    description = Res.string.examples_data_counters_data_item_02_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.EngagementStats,
                    codeSnippet = Res.string.examples_data_counters_data_item_10_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_counters_data_item_03_title,
                    description = Res.string.examples_data_counters_data_item_03_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PriceSwitcher,
                    codeSnippet = Res.string.examples_data_counters_data_item_11_code,
                ),
            ),
        ),
        // ── Navigation & Layout ──
        ExampleCategory(
            id = "navigation-layout",
            title = Res.string.examples_data_navigation_layout_title,
            subtitle = Res.string.examples_data_navigation_layout_subtitle,
            accentLabel = "NAV",
            tags = listOf("NAV"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_navigation_layout_item_01_title,
                    description = Res.string.examples_data_navigation_layout_item_01_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.AnimatedTabs,
                    codeSnippet = Res.string.examples_data_navigation_layout_item_12_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_navigation_layout_item_02_title,
                    description = Res.string.examples_data_navigation_layout_item_02_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.MegaMenuReveal,
                    codeSnippet = Res.string.examples_data_navigation_layout_item_13_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_navigation_layout_item_03_title,
                    description = Res.string.examples_data_navigation_layout_item_03_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SmoothTabIndicator,
                    codeSnippet = Res.string.examples_data_navigation_layout_item_14_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_navigation_layout_item_04_title,
                    description = Res.string.examples_data_navigation_layout_item_04_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CircularMenu,
                    codeSnippet = Res.string.examples_data_navigation_layout_item_15_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_navigation_layout_item_05_title,
                    description = Res.string.examples_data_navigation_layout_item_05_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ElasticDrawer,
                    codeSnippet = Res.string.examples_data_navigation_layout_item_16_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_navigation_layout_item_06_title,
                    description = Res.string.examples_data_navigation_layout_item_06_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ScrollDirectionHeader,
                    codeSnippet = Res.string.examples_data_navigation_layout_item_17_code,
                ),
            ),
        ),
        // ── Interactive Controls ──
        ExampleCategory(
            id = "interactive-controls",
            title = Res.string.examples_data_interactive_controls_title,
            subtitle = Res.string.examples_data_interactive_controls_subtitle,
            accentLabel = "INTERACTIVE",
            tags = listOf("INTERACTIVE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_01_title,
                    description = Res.string.examples_data_interactive_controls_item_01_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.HoldToConfirm,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_18_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_02_title,
                    description = Res.string.examples_data_interactive_controls_item_02_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SwipeActions,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_19_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_03_title,
                    description = Res.string.examples_data_interactive_controls_item_03_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.MultiStateBadge,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_20_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_04_title,
                    description = Res.string.examples_data_interactive_controls_item_04_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ElasticPull,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_21_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_05_title,
                    description = Res.string.examples_data_interactive_controls_item_05_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ExpandingSearch,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_22_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_06_title,
                    description = Res.string.examples_data_interactive_controls_item_06_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.IOSSlider,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_23_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_07_title,
                    description = Res.string.examples_data_interactive_controls_item_07_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CheckboxAnimation,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_24_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_08_title,
                    description = Res.string.examples_data_interactive_controls_item_08_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SwitchAnimation,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_25_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_09_title,
                    description = Res.string.examples_data_interactive_controls_item_09_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.MagneticButton,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_26_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_10_title,
                    description = Res.string.examples_data_interactive_controls_item_10_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.RippleButton,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_27_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_11_title,
                    description = Res.string.examples_data_interactive_controls_item_11_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SpringChip,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_28_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_12_title,
                    description = Res.string.examples_data_interactive_controls_item_12_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ToastNotification,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_29_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_interactive_controls_item_13_title,
                    description = Res.string.examples_data_interactive_controls_item_13_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CoinFlip,
                    codeSnippet = Res.string.examples_data_interactive_controls_item_30_code,
                ),
            ),
        ),
)

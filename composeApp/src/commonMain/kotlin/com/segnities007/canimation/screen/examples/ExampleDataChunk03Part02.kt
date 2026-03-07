package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*



internal val exampleCategoriesChunk03Part02: List<ExampleCategory> = listOf(
        // ── Visual Effects ──
        ExampleCategory(
            id = "visual-effects",
            title = Res.string.examples_data_visual_effects_title,
            subtitle = Res.string.examples_data_visual_effects_subtitle,
            accentLabel = "VISUAL",
            tags = listOf("VISUAL"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_01_title,
                    description = Res.string.examples_data_visual_effects_item_01_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ColorMorph,
                    codeSnippet = Res.string.examples_data_visual_effects_item_01_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_02_title,
                    description = Res.string.examples_data_visual_effects_item_02_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.GradientShift,
                    codeSnippet = Res.string.examples_data_visual_effects_item_02_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_03_title,
                    description = Res.string.examples_data_visual_effects_item_03_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PathTracer,
                    codeSnippet = Res.string.examples_data_visual_effects_item_03_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_04_title,
                    description = Res.string.examples_data_visual_effects_item_04_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ConfettiExplosion,
                    codeSnippet = Res.string.examples_data_visual_effects_item_04_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_05_title,
                    description = Res.string.examples_data_visual_effects_item_05_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.WaveEffect,
                    codeSnippet = Res.string.examples_data_visual_effects_item_05_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_06_title,
                    description = Res.string.examples_data_visual_effects_item_06_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SlidingReveal,
                    codeSnippet = Res.string.examples_data_visual_effects_item_06_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_07_title,
                    description = Res.string.examples_data_visual_effects_item_07_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.FocusBlurEffect,
                    codeSnippet = Res.string.examples_data_visual_effects_item_07_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_08_title,
                    description = Res.string.examples_data_visual_effects_item_08_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.FloatingParticles,
                    codeSnippet = Res.string.examples_data_visual_effects_item_08_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_09_title,
                    description = Res.string.examples_data_visual_effects_item_09_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PulsingAvatar,
                    codeSnippet = Res.string.examples_data_visual_effects_item_09_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_10_title,
                    description = Res.string.examples_data_visual_effects_item_10_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PulseRadar,
                    codeSnippet = Res.string.examples_data_visual_effects_item_10_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_visual_effects_item_11_title,
                    description = Res.string.examples_data_visual_effects_item_11_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.HeartbeatLine,
                    codeSnippet = Res.string.examples_data_visual_effects_item_11_code,
                ),
            ),
        ),
        // ── Physics & Motion ──
        ExampleCategory(
            id = "physics-motion",
            title = Res.string.examples_data_physics_motion_title,
            subtitle = Res.string.examples_data_physics_motion_subtitle,
            accentLabel = "PHYSICS",
            tags = listOf("PHYSICS"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_physics_motion_item_01_title,
                    description = Res.string.examples_data_physics_motion_item_01_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.BouncySpringList,
                    codeSnippet = Res.string.examples_data_physics_motion_item_12_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_physics_motion_item_02_title,
                    description = Res.string.examples_data_physics_motion_item_02_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.StaggerFromCenter,
                    codeSnippet = Res.string.examples_data_physics_motion_item_13_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_physics_motion_item_03_title,
                    description = Res.string.examples_data_physics_motion_item_03_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.TickerMarquee,
                    codeSnippet = Res.string.examples_data_physics_motion_item_14_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_physics_motion_item_04_title,
                    description = Res.string.examples_data_physics_motion_item_04_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SpringChain,
                    codeSnippet = Res.string.examples_data_physics_motion_item_15_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_physics_motion_item_05_title,
                    description = Res.string.examples_data_physics_motion_item_05_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ParallaxLayers,
                    codeSnippet = Res.string.examples_data_physics_motion_item_16_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_physics_motion_item_06_title,
                    description = Res.string.examples_data_physics_motion_item_06_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PendulumSwing,
                    codeSnippet = Res.string.examples_data_physics_motion_item_17_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_physics_motion_item_07_title,
                    description = Res.string.examples_data_physics_motion_item_07_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.BouncingBall,
                    codeSnippet = Res.string.examples_data_physics_motion_item_18_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_physics_motion_item_08_title,
                    description = Res.string.examples_data_physics_motion_item_08_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SlinkySpring,
                    codeSnippet = Res.string.examples_data_physics_motion_item_19_code,
                ),
            ),
        ),
        // ── Charts & Data Viz ──
        ExampleCategory(
            id = "charts-dataviz",
            title = Res.string.examples_data_charts_dataviz_title,
            subtitle = Res.string.examples_data_charts_dataviz_subtitle,
            accentLabel = "CHARTS",
            tags = listOf("CHARTS"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_charts_dataviz_item_01_title,
                    description = Res.string.examples_data_charts_dataviz_item_01_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.AnimatedPieChart,
                    codeSnippet = Res.string.examples_data_charts_dataviz_item_20_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_charts_dataviz_item_02_title,
                    description = Res.string.examples_data_charts_dataviz_item_02_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.AnimatedBarChart,
                    codeSnippet = Res.string.examples_data_charts_dataviz_item_21_code,
                ),
            ),
        ),
        // ── Galleries & Lists ──
        ExampleCategory(
            id = "galleries-lists",
            title = Res.string.examples_data_galleries_lists_title,
            subtitle = Res.string.examples_data_galleries_lists_subtitle,
            accentLabel = "GALLERY",
            tags = listOf("GALLERY"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_galleries_lists_item_01_title,
                    description = Res.string.examples_data_galleries_lists_item_01_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.InfiniteLoadingList,
                    codeSnippet = Res.string.examples_data_galleries_lists_item_22_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_galleries_lists_item_02_title,
                    description = Res.string.examples_data_galleries_lists_item_02_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.HorizontalScrollGallery,
                    codeSnippet = Res.string.examples_data_galleries_lists_item_23_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_galleries_lists_item_03_title,
                    description = Res.string.examples_data_galleries_lists_item_03_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.VerticalCarousel,
                    codeSnippet = Res.string.examples_data_galleries_lists_item_24_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_galleries_lists_item_04_title,
                    description = Res.string.examples_data_galleries_lists_item_04_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.WaterfallGrid,
                    codeSnippet = Res.string.examples_data_galleries_lists_item_25_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_galleries_lists_item_05_title,
                    description = Res.string.examples_data_galleries_lists_item_05_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ZoomHeroImage,
                    codeSnippet = Res.string.examples_data_galleries_lists_item_26_code,
                ),
            ),
        ),
)

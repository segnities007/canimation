package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*



internal val showcaseCategoriesVisualAndPhysics: List<ShowcaseCategory> = listOf(
        // ── Visual Effects ──
        ShowcaseCategory(
            id = "visual-effects",
            title = Res.string.examples_data_visual_effects_title,
            subtitle = Res.string.examples_data_visual_effects_subtitle,
            tags = listOf(ShowcaseTagId.Visual),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_01_title,
                    description = Res.string.examples_data_visual_effects_item_01_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ColorMorph,
                    codeSnippet = Res.string.examples_data_visual_effects_item_01_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_02_title,
                    description = Res.string.examples_data_visual_effects_item_02_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.GradientShift,
                    codeSnippet = Res.string.examples_data_visual_effects_item_02_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_03_title,
                    description = Res.string.examples_data_visual_effects_item_03_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.PathTracer,
                    codeSnippet = Res.string.examples_data_visual_effects_item_03_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_04_title,
                    description = Res.string.examples_data_visual_effects_item_04_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ConfettiExplosion,
                    codeSnippet = Res.string.examples_data_visual_effects_item_04_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_05_title,
                    description = Res.string.examples_data_visual_effects_item_05_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.WaveEffect,
                    codeSnippet = Res.string.examples_data_visual_effects_item_05_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_06_title,
                    description = Res.string.examples_data_visual_effects_item_06_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.SlidingReveal,
                    codeSnippet = Res.string.examples_data_visual_effects_item_06_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_07_title,
                    description = Res.string.examples_data_visual_effects_item_07_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.FocusBlurEffect,
                    codeSnippet = Res.string.examples_data_visual_effects_item_07_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_08_title,
                    description = Res.string.examples_data_visual_effects_item_08_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.FloatingParticles,
                    codeSnippet = Res.string.examples_data_visual_effects_item_08_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_09_title,
                    description = Res.string.examples_data_visual_effects_item_09_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.PulsingAvatar,
                    codeSnippet = Res.string.examples_data_visual_effects_item_09_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_10_title,
                    description = Res.string.examples_data_visual_effects_item_10_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.PulseRadar,
                    codeSnippet = Res.string.examples_data_visual_effects_item_10_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_visual_effects_item_11_title,
                    description = Res.string.examples_data_visual_effects_item_11_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.HeartbeatLine,
                    codeSnippet = Res.string.examples_data_visual_effects_item_11_code,
                ),
            ),
        ),
        // ── Physics & Motion ──
        ShowcaseCategory(
            id = "physics-motion",
            title = Res.string.examples_data_physics_motion_title,
            subtitle = Res.string.examples_data_physics_motion_subtitle,
            tags = listOf(ShowcaseTagId.Physics),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_physics_motion_item_01_title,
                    description = Res.string.examples_data_physics_motion_item_01_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.BouncySpringList,
                    codeSnippet = Res.string.examples_data_physics_motion_item_12_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_physics_motion_item_02_title,
                    description = Res.string.examples_data_physics_motion_item_02_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.StaggerFromCenter,
                    codeSnippet = Res.string.examples_data_physics_motion_item_13_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_physics_motion_item_03_title,
                    description = Res.string.examples_data_physics_motion_item_03_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.TickerMarquee,
                    codeSnippet = Res.string.examples_data_physics_motion_item_14_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_physics_motion_item_04_title,
                    description = Res.string.examples_data_physics_motion_item_04_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.SpringChain,
                    codeSnippet = Res.string.examples_data_physics_motion_item_15_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_physics_motion_item_05_title,
                    description = Res.string.examples_data_physics_motion_item_05_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ParallaxLayers,
                    codeSnippet = Res.string.examples_data_physics_motion_item_16_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_physics_motion_item_06_title,
                    description = Res.string.examples_data_physics_motion_item_06_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.PendulumSwing,
                    codeSnippet = Res.string.examples_data_physics_motion_item_17_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_physics_motion_item_07_title,
                    description = Res.string.examples_data_physics_motion_item_07_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.BouncingBall,
                    codeSnippet = Res.string.examples_data_physics_motion_item_18_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_physics_motion_item_08_title,
                    description = Res.string.examples_data_physics_motion_item_08_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.SlinkySpring,
                    codeSnippet = Res.string.examples_data_physics_motion_item_19_code,
                ),
            ),
        ),
        // ── Charts & Data Viz ──
        ShowcaseCategory(
            id = "charts-dataviz",
            title = Res.string.examples_data_charts_dataviz_title,
            subtitle = Res.string.examples_data_charts_dataviz_subtitle,
            tags = listOf(ShowcaseTagId.Charts),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_charts_dataviz_item_01_title,
                    description = Res.string.examples_data_charts_dataviz_item_01_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.AnimatedPieChart,
                    codeSnippet = Res.string.examples_data_charts_dataviz_item_20_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_charts_dataviz_item_02_title,
                    description = Res.string.examples_data_charts_dataviz_item_02_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.AnimatedBarChart,
                    codeSnippet = Res.string.examples_data_charts_dataviz_item_21_code,
                ),
            ),
        ),
        // ── Galleries & Lists ──
        ShowcaseCategory(
            id = "galleries-lists",
            title = Res.string.examples_data_galleries_lists_title,
            subtitle = Res.string.examples_data_galleries_lists_subtitle,
            tags = listOf(ShowcaseTagId.Gallery),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_galleries_lists_item_01_title,
                    description = Res.string.examples_data_galleries_lists_item_01_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.InfiniteLoadingList,
                    codeSnippet = Res.string.examples_data_galleries_lists_item_22_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_galleries_lists_item_02_title,
                    description = Res.string.examples_data_galleries_lists_item_02_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.HorizontalScrollGallery,
                    codeSnippet = Res.string.examples_data_galleries_lists_item_23_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_galleries_lists_item_03_title,
                    description = Res.string.examples_data_galleries_lists_item_03_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.VerticalCarousel,
                    codeSnippet = Res.string.examples_data_galleries_lists_item_24_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_galleries_lists_item_04_title,
                    description = Res.string.examples_data_galleries_lists_item_04_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.WaterfallGrid,
                    codeSnippet = Res.string.examples_data_galleries_lists_item_25_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_galleries_lists_item_05_title,
                    description = Res.string.examples_data_galleries_lists_item_05_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ZoomHeroImage,
                    codeSnippet = Res.string.examples_data_galleries_lists_item_26_code,
                ),
            ),
        ),
)

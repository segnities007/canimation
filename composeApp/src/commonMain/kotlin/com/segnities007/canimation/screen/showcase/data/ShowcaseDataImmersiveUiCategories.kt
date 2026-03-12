package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*



internal val showcaseCategoriesImmersiveUi: List<ShowcaseCategory> = listOf(
        // ── Audio & Visualization ──
        ShowcaseCategory(
            id = "audio-viz",
            title = Res.string.examples_data_audio_viz_title,
            subtitle = Res.string.examples_data_audio_viz_subtitle,
            tags = listOf(ShowcaseTagId.Visual),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_audio_viz_item_01_title,
                    description = Res.string.examples_data_audio_viz_item_01_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.WaveformVisualizer,
                    codeSnippet = Res.string.examples_data_audio_viz_item_01_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_audio_viz_item_02_title,
                    description = Res.string.examples_data_audio_viz_item_02_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.MatrixRain,
                    codeSnippet = Res.string.examples_data_audio_viz_item_02_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_audio_viz_item_03_title,
                    description = Res.string.examples_data_audio_viz_item_03_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.MusicEqualizer,
                    codeSnippet = Res.string.examples_data_audio_viz_item_03_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_audio_viz_item_04_title,
                    description = Res.string.examples_data_audio_viz_item_04_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.WaterDroplet,
                    codeSnippet = Res.string.examples_data_audio_viz_item_04_code,
                ),
            ),
        ),
        // ── Modern UI Components ──
        ShowcaseCategory(
            id = "modern-ui",
            title = Res.string.examples_data_modern_ui_title,
            subtitle = Res.string.examples_data_modern_ui_subtitle,
            tags = listOf(ShowcaseTagId.Ui),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_modern_ui_item_01_title,
                    description = Res.string.examples_data_modern_ui_item_01_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.PulseButton,
                    codeSnippet = Res.string.examples_data_modern_ui_item_05_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_modern_ui_item_02_title,
                    description = Res.string.examples_data_modern_ui_item_02_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.NeumorphismCard,
                    codeSnippet = Res.string.examples_data_modern_ui_item_06_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_modern_ui_item_03_title,
                    description = Res.string.examples_data_modern_ui_item_03_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.GradientBorderCard,
                    codeSnippet = Res.string.examples_data_modern_ui_item_07_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_modern_ui_item_04_title,
                    description = Res.string.examples_data_modern_ui_item_04_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.FlipCounter,
                    codeSnippet = Res.string.examples_data_modern_ui_item_08_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_modern_ui_item_05_title,
                    description = Res.string.examples_data_modern_ui_item_05_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.ExpandableChip,
                    codeSnippet = Res.string.examples_data_modern_ui_item_09_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_modern_ui_item_06_title,
                    description = Res.string.examples_data_modern_ui_item_06_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.StackedNotifications,
                    codeSnippet = Res.string.examples_data_modern_ui_item_10_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_modern_ui_item_07_title,
                    description = Res.string.examples_data_modern_ui_item_07_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.CircularRevealCard,
                    codeSnippet = Res.string.examples_data_modern_ui_item_11_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_modern_ui_item_08_title,
                    description = Res.string.examples_data_modern_ui_item_08_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.SwipeCard,
                    codeSnippet = Res.string.examples_data_modern_ui_item_12_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_modern_ui_item_09_title,
                    description = Res.string.examples_data_modern_ui_item_09_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.AnimatedCheckmark,
                    codeSnippet = Res.string.examples_data_modern_ui_item_13_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_modern_ui_item_10_title,
                    description = Res.string.examples_data_modern_ui_item_10_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.RotatingCube,
                    codeSnippet = Res.string.examples_data_modern_ui_item_14_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_modern_ui_item_11_title,
                    description = Res.string.examples_data_modern_ui_item_11_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.SlotMachine,
                    codeSnippet = Res.string.examples_data_modern_ui_item_15_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_modern_ui_item_12_title,
                    description = Res.string.examples_data_modern_ui_item_12_description,
                    demoType = ShowcaseDemoType.Component,
                    componentKey = ShowcaseComponentDemoKeys.RadialProgress,
                    codeSnippet = Res.string.examples_data_modern_ui_item_16_code,
                ),
            ),
        ),
        ShowcaseCategory(
            id = "wave",
            title = Res.string.examples_data_wave_title,
            subtitle = Res.string.examples_data_wave_subtitle,
            tags = listOf(ShowcaseTagId.Entrance),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_wave_item_17_title,
                    description = Res.string.examples_data_wave_item_17_description,
                    codeSnippet = Res.string.examples_data_wave_item_17_code, effect = Canimation.Wave.Gentle
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_wave_item_18_title,
                    description = Res.string.examples_data_wave_item_18_description,
                    codeSnippet = Res.string.examples_data_wave_item_18_code, effect = Canimation.Wave.Strong
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_wave_item_19_title,
                    description = Res.string.examples_data_wave_item_19_description,
                    codeSnippet = Res.string.examples_data_wave_item_19_code, effect = Canimation.Wave.Ripple
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_wave_item_20_title,
                    description = Res.string.examples_data_wave_item_20_description,
                    codeSnippet = Res.string.examples_data_wave_item_20_code, effect = Canimation.Wave.Float
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_wave_item_21_title,
                    description = Res.string.examples_data_wave_item_21_description,
                    codeSnippet = Res.string.examples_data_wave_item_21_code, effect = Canimation.Wave.Drift
                ),
            ),
        ),
        ShowcaseCategory(
            id = "glitch",
            title = Res.string.examples_data_glitch_title,
            subtitle = Res.string.examples_data_glitch_subtitle,
            tags = listOf(ShowcaseTagId.Entrance),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_glitch_item_22_title,
                    description = Res.string.examples_data_glitch_item_22_description,
                    codeSnippet = Res.string.examples_data_glitch_item_22_code, effect = Canimation.Glitch.In
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_glitch_item_23_title,
                    description = Res.string.examples_data_glitch_item_23_description,
                    codeSnippet = Res.string.examples_data_glitch_item_23_code, effect = Canimation.Glitch.Shake
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_glitch_item_24_title,
                    description = Res.string.examples_data_glitch_item_24_description,
                    codeSnippet = Res.string.examples_data_glitch_item_24_code, effect = Canimation.Glitch.Flicker
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_glitch_item_25_title,
                    description = Res.string.examples_data_glitch_item_25_description,
                    codeSnippet = Res.string.examples_data_glitch_item_25_code, effect = Canimation.Glitch.Distort
                ),
            ),
        ),
)

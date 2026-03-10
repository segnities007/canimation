package com.segnities007.canimation.screen.examples.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*



internal val exampleCategoriesImmersiveUi: List<ExampleCategory> = listOf(
        // ── Audio & Visualization ──
        ExampleCategory(
            id = "audio-viz",
            title = Res.string.examples_data_audio_viz_title,
            subtitle = Res.string.examples_data_audio_viz_subtitle,
            accentLabel = "VISUAL",
            tags = listOf("VISUAL"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_audio_viz_item_01_title,
                    description = Res.string.examples_data_audio_viz_item_01_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.WaveformVisualizer,
                    codeSnippet = Res.string.examples_data_audio_viz_item_01_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_audio_viz_item_02_title,
                    description = Res.string.examples_data_audio_viz_item_02_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.MatrixRain,
                    codeSnippet = Res.string.examples_data_audio_viz_item_02_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_audio_viz_item_03_title,
                    description = Res.string.examples_data_audio_viz_item_03_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.MusicEqualizer,
                    codeSnippet = Res.string.examples_data_audio_viz_item_03_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_audio_viz_item_04_title,
                    description = Res.string.examples_data_audio_viz_item_04_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.WaterDroplet,
                    codeSnippet = Res.string.examples_data_audio_viz_item_04_code,
                ),
            ),
        ),
        // ── Modern UI Components ──
        ExampleCategory(
            id = "modern-ui",
            title = Res.string.examples_data_modern_ui_title,
            subtitle = Res.string.examples_data_modern_ui_subtitle,
            accentLabel = "UI",
            tags = listOf("UI"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_modern_ui_item_01_title,
                    description = Res.string.examples_data_modern_ui_item_01_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PulseButton,
                    codeSnippet = Res.string.examples_data_modern_ui_item_05_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_modern_ui_item_02_title,
                    description = Res.string.examples_data_modern_ui_item_02_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.NeumorphismCard,
                    codeSnippet = Res.string.examples_data_modern_ui_item_06_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_modern_ui_item_03_title,
                    description = Res.string.examples_data_modern_ui_item_03_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.GradientBorderCard,
                    codeSnippet = Res.string.examples_data_modern_ui_item_07_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_modern_ui_item_04_title,
                    description = Res.string.examples_data_modern_ui_item_04_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.FlipCounter,
                    codeSnippet = Res.string.examples_data_modern_ui_item_08_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_modern_ui_item_05_title,
                    description = Res.string.examples_data_modern_ui_item_05_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ExpandableChip,
                    codeSnippet = Res.string.examples_data_modern_ui_item_09_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_modern_ui_item_06_title,
                    description = Res.string.examples_data_modern_ui_item_06_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.StackedNotifications,
                    codeSnippet = Res.string.examples_data_modern_ui_item_10_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_modern_ui_item_07_title,
                    description = Res.string.examples_data_modern_ui_item_07_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CircularRevealCard,
                    codeSnippet = Res.string.examples_data_modern_ui_item_11_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_modern_ui_item_08_title,
                    description = Res.string.examples_data_modern_ui_item_08_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SwipeCard,
                    codeSnippet = Res.string.examples_data_modern_ui_item_12_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_modern_ui_item_09_title,
                    description = Res.string.examples_data_modern_ui_item_09_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.AnimatedCheckmark,
                    codeSnippet = Res.string.examples_data_modern_ui_item_13_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_modern_ui_item_10_title,
                    description = Res.string.examples_data_modern_ui_item_10_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.RotatingCube,
                    codeSnippet = Res.string.examples_data_modern_ui_item_14_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_modern_ui_item_11_title,
                    description = Res.string.examples_data_modern_ui_item_11_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SlotMachine,
                    codeSnippet = Res.string.examples_data_modern_ui_item_15_code,
                ),
                ExampleItem(
                    title = Res.string.examples_data_modern_ui_item_12_title,
                    description = Res.string.examples_data_modern_ui_item_12_description,
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.RadialProgress,
                    codeSnippet = Res.string.examples_data_modern_ui_item_16_code,
                ),
            ),
        ),
        ExampleCategory(
            id = "wave",
            title = Res.string.examples_data_wave_title,
            subtitle = Res.string.examples_data_wave_subtitle,
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_wave_item_17_title,
                    description = Res.string.examples_data_wave_item_17_description,
                    codeSnippet = Res.string.examples_data_wave_item_17_code, effect = Canimation.Wave.Gentle
                ),
                ExampleItem(
                    title = Res.string.examples_data_wave_item_18_title,
                    description = Res.string.examples_data_wave_item_18_description,
                    codeSnippet = Res.string.examples_data_wave_item_18_code, effect = Canimation.Wave.Strong
                ),
                ExampleItem(
                    title = Res.string.examples_data_wave_item_19_title,
                    description = Res.string.examples_data_wave_item_19_description,
                    codeSnippet = Res.string.examples_data_wave_item_19_code, effect = Canimation.Wave.Ripple
                ),
                ExampleItem(
                    title = Res.string.examples_data_wave_item_20_title,
                    description = Res.string.examples_data_wave_item_20_description,
                    codeSnippet = Res.string.examples_data_wave_item_20_code, effect = Canimation.Wave.Float
                ),
                ExampleItem(
                    title = Res.string.examples_data_wave_item_21_title,
                    description = Res.string.examples_data_wave_item_21_description,
                    codeSnippet = Res.string.examples_data_wave_item_21_code, effect = Canimation.Wave.Drift
                ),
            ),
        ),
        ExampleCategory(
            id = "glitch",
            title = Res.string.examples_data_glitch_title,
            subtitle = Res.string.examples_data_glitch_subtitle,
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_glitch_item_22_title,
                    description = Res.string.examples_data_glitch_item_22_description,
                    codeSnippet = Res.string.examples_data_glitch_item_22_code, effect = Canimation.Glitch.In
                ),
                ExampleItem(
                    title = Res.string.examples_data_glitch_item_23_title,
                    description = Res.string.examples_data_glitch_item_23_description,
                    codeSnippet = Res.string.examples_data_glitch_item_23_code, effect = Canimation.Glitch.Shake
                ),
                ExampleItem(
                    title = Res.string.examples_data_glitch_item_24_title,
                    description = Res.string.examples_data_glitch_item_24_description,
                    codeSnippet = Res.string.examples_data_glitch_item_24_code, effect = Canimation.Glitch.Flicker
                ),
                ExampleItem(
                    title = Res.string.examples_data_glitch_item_25_title,
                    description = Res.string.examples_data_glitch_item_25_description,
                    codeSnippet = Res.string.examples_data_glitch_item_25_code, effect = Canimation.Glitch.Distort
                ),
            ),
        ),
)

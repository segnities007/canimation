package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*


internal val exampleCategoriesChunk04: List<ExampleCategory> = listOf(
        ExampleCategory(
            id = "elastic",
            title = Res.string.examples_data_elastic_title,
            subtitle = Res.string.examples_data_elastic_subtitle,
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_elastic_item_01_title,
                    description = Res.string.examples_data_elastic_item_01_description,
                    codeSnippet = Res.string.examples_data_elastic_item_01_code, effect = Canimation.Elastic.In
                ),
                ExampleItem(
                    title = Res.string.examples_data_elastic_item_02_title,
                    description = Res.string.examples_data_elastic_item_02_description,
                    codeSnippet = Res.string.examples_data_elastic_item_02_code, effect = Canimation.Elastic.Stretch
                ),
                ExampleItem(
                    title = Res.string.examples_data_elastic_item_03_title,
                    description = Res.string.examples_data_elastic_item_03_description,
                    codeSnippet = Res.string.examples_data_elastic_item_03_code, effect = Canimation.Elastic.Squash
                ),
                ExampleItem(
                    title = Res.string.examples_data_elastic_item_04_title,
                    description = Res.string.examples_data_elastic_item_04_description,
                    codeSnippet = Res.string.examples_data_elastic_item_04_code, effect = Canimation.Elastic.Snap
                ),
                ExampleItem(
                    title = Res.string.examples_data_elastic_item_05_title,
                    description = Res.string.examples_data_elastic_item_05_description,
                    codeSnippet = Res.string.examples_data_elastic_item_05_code, effect = Canimation.Elastic.Wobble
                ),
            ),
        ),
        ExampleCategory(
            id = "cinematic",
            title = Res.string.examples_data_cinematic_title,
            subtitle = Res.string.examples_data_cinematic_subtitle,
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_cinematic_item_06_title,
                    description = Res.string.examples_data_cinematic_item_06_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_06_code, effect = Canimation.Cinematic.Curtain
                ),
                ExampleItem(
                    title = Res.string.examples_data_cinematic_item_07_title,
                    description = Res.string.examples_data_cinematic_item_07_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_07_code, effect = Canimation.Cinematic.ZoomPan
                ),
                ExampleItem(
                    title = Res.string.examples_data_cinematic_item_08_title,
                    description = Res.string.examples_data_cinematic_item_08_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_08_code, effect = Canimation.Cinematic.Dolly
                ),
                ExampleItem(
                    title = Res.string.examples_data_cinematic_item_09_title,
                    description = Res.string.examples_data_cinematic_item_09_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_09_code, effect = Canimation.Cinematic.Reveal
                ),
                ExampleItem(
                    title = Res.string.examples_data_cinematic_item_10_title,
                    description = Res.string.examples_data_cinematic_item_10_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_10_code, effect = Canimation.Cinematic.FadeToBlack
                ),
                ExampleItem(
                    title = Res.string.examples_data_cinematic_item_11_title,
                    description = Res.string.examples_data_cinematic_item_11_description,
                    codeSnippet = Res.string.examples_data_cinematic_item_11_code, effect = Canimation.Cinematic.Dramatic
                ),
            ),
        ),
        ExampleCategory(
            id = "playful",
            title = Res.string.examples_data_playful_title,
            subtitle = Res.string.examples_data_playful_subtitle,
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_playful_item_12_title,
                    description = Res.string.examples_data_playful_item_12_description,
                    codeSnippet = Res.string.examples_data_playful_item_12_code, effect = Canimation.Playful.Wiggle
                ),
                ExampleItem(
                    title = Res.string.examples_data_playful_item_13_title,
                    description = Res.string.examples_data_playful_item_13_description,
                    codeSnippet = Res.string.examples_data_playful_item_13_code, effect = Canimation.Playful.Hop
                ),
                ExampleItem(
                    title = Res.string.examples_data_playful_item_14_title,
                    description = Res.string.examples_data_playful_item_14_description,
                    codeSnippet = Res.string.examples_data_playful_item_14_code, effect = Canimation.Playful.Spin
                ),
                ExampleItem(
                    title = Res.string.examples_data_playful_item_15_title,
                    description = Res.string.examples_data_playful_item_15_description,
                    codeSnippet = Res.string.examples_data_playful_item_15_code, effect = Canimation.Playful.Pop
                ),
                ExampleItem(
                    title = Res.string.examples_data_playful_item_16_title,
                    description = Res.string.examples_data_playful_item_16_description,
                    codeSnippet = Res.string.examples_data_playful_item_16_code, effect = Canimation.Playful.Twirl
                ),
                ExampleItem(
                    title = Res.string.examples_data_playful_item_17_title,
                    description = Res.string.examples_data_playful_item_17_description,
                    codeSnippet = Res.string.examples_data_playful_item_17_code, effect = Canimation.Playful.Boing
                ),
            ),
        ),
        ExampleCategory(
            id = "diagonal",
            title = Res.string.examples_data_diagonal_title,
            subtitle = Res.string.examples_data_diagonal_subtitle,
            accentLabel = "MOVEMENT",
            tags = listOf("ENTRANCE", "MOVEMENT"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_diagonal_item_18_title,
                    description = Res.string.examples_data_diagonal_item_18_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_18_code, effect = Canimation.Diagonal.TopLeft
                ),
                ExampleItem(
                    title = Res.string.examples_data_diagonal_item_19_title,
                    description = Res.string.examples_data_diagonal_item_19_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_19_code, effect = Canimation.Diagonal.TopRight
                ),
                ExampleItem(
                    title = Res.string.examples_data_diagonal_item_20_title,
                    description = Res.string.examples_data_diagonal_item_20_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_20_code, effect = Canimation.Diagonal.BottomLeft
                ),
                ExampleItem(
                    title = Res.string.examples_data_diagonal_item_21_title,
                    description = Res.string.examples_data_diagonal_item_21_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_21_code, effect = Canimation.Diagonal.BottomRight
                ),
                ExampleItem(
                    title = Res.string.examples_data_diagonal_item_22_title,
                    description = Res.string.examples_data_diagonal_item_22_description,
                    codeSnippet = Res.string.examples_data_diagonal_item_22_code, effect = Canimation.Diagonal.Subtle
                ),
            ),
        ),
        ExampleCategory(
            id = "shrink",
            title = Res.string.examples_data_shrink_title,
            subtitle = Res.string.examples_data_shrink_subtitle,
            accentLabel = "SCALE",
            tags = listOf("ENTRANCE", "SCALE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_shrink_item_23_title,
                    description = Res.string.examples_data_shrink_item_23_description,
                    codeSnippet = Res.string.examples_data_shrink_item_23_code, effect = Canimation.Shrink.Out
                ),
                ExampleItem(
                    title = Res.string.examples_data_shrink_item_24_title,
                    description = Res.string.examples_data_shrink_item_24_description,
                    codeSnippet = Res.string.examples_data_shrink_item_24_code, effect = Canimation.Shrink.Subtle
                ),
                ExampleItem(
                    title = Res.string.examples_data_shrink_item_25_title,
                    description = Res.string.examples_data_shrink_item_25_description,
                    codeSnippet = Res.string.examples_data_shrink_item_25_code, effect = Canimation.Shrink.Rotate
                ),
                ExampleItem(
                    title = Res.string.examples_data_shrink_item_26_title,
                    description = Res.string.examples_data_shrink_item_26_description,
                    codeSnippet = Res.string.examples_data_shrink_item_26_code, effect = Canimation.Shrink.FadeDown
                ),
            ),
        ),
        ExampleCategory(
            id = "tilt",
            title = Res.string.examples_data_tilt_title,
            subtitle = Res.string.examples_data_tilt_subtitle,
            accentLabel = "ROTATE",
            tags = listOf("ENTRANCE", "ROTATE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_tilt_item_27_title,
                    description = Res.string.examples_data_tilt_item_27_description,
                    codeSnippet = Res.string.examples_data_tilt_item_27_code, effect = Canimation.Tilt.Left
                ),
                ExampleItem(
                    title = Res.string.examples_data_tilt_item_28_title,
                    description = Res.string.examples_data_tilt_item_28_description,
                    codeSnippet = Res.string.examples_data_tilt_item_28_code, effect = Canimation.Tilt.Right
                ),
                ExampleItem(
                    title = Res.string.examples_data_tilt_item_29_title,
                    description = Res.string.examples_data_tilt_item_29_description,
                    codeSnippet = Res.string.examples_data_tilt_item_29_code, effect = Canimation.Tilt.Up
                ),
                ExampleItem(
                    title = Res.string.examples_data_tilt_item_30_title,
                    description = Res.string.examples_data_tilt_item_30_description,
                    codeSnippet = Res.string.examples_data_tilt_item_30_code, effect = Canimation.Tilt.Down
                ),
                ExampleItem(
                    title = Res.string.examples_data_tilt_item_31_title,
                    description = Res.string.examples_data_tilt_item_31_description,
                    codeSnippet = Res.string.examples_data_tilt_item_31_code, effect = Canimation.Tilt.Swing
                ),
            ),
        ),
        ExampleCategory(
            id = "float",
            title = Res.string.examples_data_float_title,
            subtitle = Res.string.examples_data_float_subtitle,
            accentLabel = "SUBTLE",
            tags = listOf("ENTRANCE", "SUBTLE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_float_item_32_title,
                    description = Res.string.examples_data_float_item_32_description,
                    codeSnippet = Res.string.examples_data_float_item_32_code, effect = Canimation.Float.Up
                ),
                ExampleItem(
                    title = Res.string.examples_data_float_item_33_title,
                    description = Res.string.examples_data_float_item_33_description,
                    codeSnippet = Res.string.examples_data_float_item_33_code, effect = Canimation.Float.Down
                ),
                ExampleItem(
                    title = Res.string.examples_data_float_item_34_title,
                    description = Res.string.examples_data_float_item_34_description,
                    codeSnippet = Res.string.examples_data_float_item_34_code, effect = Canimation.Float.Gentle
                ),
                ExampleItem(
                    title = Res.string.examples_data_float_item_35_title,
                    description = Res.string.examples_data_float_item_35_description,
                    codeSnippet = Res.string.examples_data_float_item_35_code, effect = Canimation.Float.ScaleUp
                ),
            ),
        ),
        ExampleCategory(
            id = "drop",
            title = Res.string.examples_data_drop_title,
            subtitle = Res.string.examples_data_drop_subtitle,
            accentLabel = "MOVEMENT",
            tags = listOf("ENTRANCE", "MOVEMENT"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_drop_item_36_title,
                    description = Res.string.examples_data_drop_item_36_description,
                    codeSnippet = Res.string.examples_data_drop_item_36_code, effect = Canimation.Drop.In
                ),
                ExampleItem(
                    title = Res.string.examples_data_drop_item_37_title,
                    description = Res.string.examples_data_drop_item_37_description,
                    codeSnippet = Res.string.examples_data_drop_item_37_code, effect = Canimation.Drop.Heavy
                ),
                ExampleItem(
                    title = Res.string.examples_data_drop_item_38_title,
                    description = Res.string.examples_data_drop_item_38_description,
                    codeSnippet = Res.string.examples_data_drop_item_38_code, effect = Canimation.Drop.Light
                ),
                ExampleItem(
                    title = Res.string.examples_data_drop_item_39_title,
                    description = Res.string.examples_data_drop_item_39_description,
                    codeSnippet = Res.string.examples_data_drop_item_39_code, effect = Canimation.Drop.Rotate
                ),
            ),
        ),
        ExampleCategory(
            id = "rise",
            title = Res.string.examples_data_rise_title,
            subtitle = Res.string.examples_data_rise_subtitle,
            accentLabel = "MOVEMENT",
            tags = listOf("ENTRANCE", "MOVEMENT"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_rise_item_40_title,
                    description = Res.string.examples_data_rise_item_40_description,
                    codeSnippet = Res.string.examples_data_rise_item_40_code, effect = Canimation.Rise.In
                ),
                ExampleItem(
                    title = Res.string.examples_data_rise_item_41_title,
                    description = Res.string.examples_data_rise_item_41_description,
                    codeSnippet = Res.string.examples_data_rise_item_41_code, effect = Canimation.Rise.Slow
                ),
                ExampleItem(
                    title = Res.string.examples_data_rise_item_42_title,
                    description = Res.string.examples_data_rise_item_42_description,
                    codeSnippet = Res.string.examples_data_rise_item_42_code, effect = Canimation.Rise.Scale
                ),
                ExampleItem(
                    title = Res.string.examples_data_rise_item_43_title,
                    description = Res.string.examples_data_rise_item_43_description,
                    codeSnippet = Res.string.examples_data_rise_item_43_code, effect = Canimation.Rise.Rotate
                ),
            ),
        ),
        ExampleCategory(
            id = "stretch",
            title = Res.string.examples_data_stretch_title,
            subtitle = Res.string.examples_data_stretch_subtitle,
            accentLabel = "SCALE",
            tags = listOf("ENTRANCE", "SCALE"),
            examples = listOf(
                ExampleItem(
                    title = Res.string.examples_data_stretch_item_44_title,
                    description = Res.string.examples_data_stretch_item_44_description,
                    codeSnippet = Res.string.examples_data_stretch_item_44_code, effect = Canimation.Stretch.Horizontal
                ),
                ExampleItem(
                    title = Res.string.examples_data_stretch_item_45_title,
                    description = Res.string.examples_data_stretch_item_45_description,
                    codeSnippet = Res.string.examples_data_stretch_item_45_code, effect = Canimation.Stretch.Vertical
                ),
                ExampleItem(
                    title = Res.string.examples_data_stretch_item_46_title,
                    description = Res.string.examples_data_stretch_item_46_description,
                    codeSnippet = Res.string.examples_data_stretch_item_46_code, effect = Canimation.Stretch.Both
                ),
                ExampleItem(
                    title = Res.string.examples_data_stretch_item_47_title,
                    description = Res.string.examples_data_stretch_item_47_description,
                    codeSnippet = Res.string.examples_data_stretch_item_47_code, effect = Canimation.Stretch.Snap
                ),
            ),
        ),
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

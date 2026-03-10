package com.segnities007.canimation.screen.examples.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val exampleCategoriesMovementEffects: List<ExampleCategory> = listOf(
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
)

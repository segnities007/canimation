package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val showcaseCategoriesMovementEffects: List<ShowcaseCategory> = listOf(
        ShowcaseCategory(
            id = "float",
            title = Res.string.examples_data_float_title,
            subtitle = Res.string.examples_data_float_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Subtle),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_float_item_32_title,
                    description = Res.string.examples_data_float_item_32_description,
                    codeSnippet = Res.string.examples_data_float_item_32_code, effect = Canimation.Float.Up
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_float_item_33_title,
                    description = Res.string.examples_data_float_item_33_description,
                    codeSnippet = Res.string.examples_data_float_item_33_code, effect = Canimation.Float.Down
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_float_item_34_title,
                    description = Res.string.examples_data_float_item_34_description,
                    codeSnippet = Res.string.examples_data_float_item_34_code, effect = Canimation.Float.Gentle
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_float_item_35_title,
                    description = Res.string.examples_data_float_item_35_description,
                    codeSnippet = Res.string.examples_data_float_item_35_code, effect = Canimation.Float.ScaleUp
                ),
            ),
        ),
        ShowcaseCategory(
            id = "drop",
            title = Res.string.examples_data_drop_title,
            subtitle = Res.string.examples_data_drop_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Movement),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_drop_item_36_title,
                    description = Res.string.examples_data_drop_item_36_description,
                    codeSnippet = Res.string.examples_data_drop_item_36_code, effect = Canimation.Drop.In
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_drop_item_37_title,
                    description = Res.string.examples_data_drop_item_37_description,
                    codeSnippet = Res.string.examples_data_drop_item_37_code, effect = Canimation.Drop.Heavy
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_drop_item_38_title,
                    description = Res.string.examples_data_drop_item_38_description,
                    codeSnippet = Res.string.examples_data_drop_item_38_code, effect = Canimation.Drop.Light
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_drop_item_39_title,
                    description = Res.string.examples_data_drop_item_39_description,
                    codeSnippet = Res.string.examples_data_drop_item_39_code, effect = Canimation.Drop.Rotate
                ),
            ),
        ),
        ShowcaseCategory(
            id = "rise",
            title = Res.string.examples_data_rise_title,
            subtitle = Res.string.examples_data_rise_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Movement),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_rise_item_40_title,
                    description = Res.string.examples_data_rise_item_40_description,
                    codeSnippet = Res.string.examples_data_rise_item_40_code, effect = Canimation.Rise.In
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rise_item_41_title,
                    description = Res.string.examples_data_rise_item_41_description,
                    codeSnippet = Res.string.examples_data_rise_item_41_code, effect = Canimation.Rise.Slow
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rise_item_42_title,
                    description = Res.string.examples_data_rise_item_42_description,
                    codeSnippet = Res.string.examples_data_rise_item_42_code, effect = Canimation.Rise.Scale
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_rise_item_43_title,
                    description = Res.string.examples_data_rise_item_43_description,
                    codeSnippet = Res.string.examples_data_rise_item_43_code, effect = Canimation.Rise.Rotate
                ),
            ),
        ),
        ShowcaseCategory(
            id = "stretch",
            title = Res.string.examples_data_stretch_title,
            subtitle = Res.string.examples_data_stretch_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Scale),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_stretch_item_44_title,
                    description = Res.string.examples_data_stretch_item_44_description,
                    codeSnippet = Res.string.examples_data_stretch_item_44_code, effect = Canimation.Stretch.Horizontal
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_stretch_item_45_title,
                    description = Res.string.examples_data_stretch_item_45_description,
                    codeSnippet = Res.string.examples_data_stretch_item_45_code, effect = Canimation.Stretch.Vertical
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_stretch_item_46_title,
                    description = Res.string.examples_data_stretch_item_46_description,
                    codeSnippet = Res.string.examples_data_stretch_item_46_code, effect = Canimation.Stretch.Both
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_stretch_item_47_title,
                    description = Res.string.examples_data_stretch_item_47_description,
                    codeSnippet = Res.string.examples_data_stretch_item_47_code, effect = Canimation.Stretch.Snap
                ),
            ),
        ),
)

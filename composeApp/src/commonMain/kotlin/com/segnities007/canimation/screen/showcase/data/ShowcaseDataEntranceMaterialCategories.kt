package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import canimation.composeapp.generated.resources.*

internal val showcaseCategoriesEntranceMaterial: List<ShowcaseCategory> = listOf(
        ShowcaseCategory(
            id = "entrance",
            title = Res.string.examples_data_entrance_title,
            subtitle = Res.string.examples_data_entrance_subtitle,
            tags = listOf(ShowcaseTagId.Entrance),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_item_01_title,
                    description = Res.string.examples_data_entrance_item_01_description,
                    effect = Canimation.Entrance.Elevate,
                    preset = CanimationPreset.ElevateIn,
                    codeSnippet = Res.string.examples_data_entrance_item_20_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_item_02_title,
                    description = Res.string.examples_data_entrance_item_02_description,
                    effect = Canimation.Entrance.Drop,
                    preset = CanimationPreset.DropIn,
                    codeSnippet = Res.string.examples_data_entrance_item_21_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_item_03_title,
                    description = Res.string.examples_data_entrance_item_03_description,
                    effect = Canimation.Entrance.JackInTheBox,
                    preset = CanimationPreset.JackInTheBox,
                    codeSnippet = Res.string.examples_data_entrance_item_22_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_item_04_title,
                    description = Res.string.examples_data_entrance_item_04_description,
                    effect = Canimation.Entrance.RollIn,
                    preset = CanimationPreset.RollIn,
                    codeSnippet = Res.string.examples_data_entrance_item_23_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_item_05_title,
                    description = Res.string.examples_data_entrance_item_05_description,
                    effect = Canimation.Entrance.LightSpeedLeft,
                    preset = CanimationPreset.LightSpeedInLeft,
                    codeSnippet = Res.string.examples_data_entrance_item_24_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_item_06_title,
                    description = Res.string.examples_data_entrance_item_06_description,
                    effect = Canimation.Entrance.LightSpeedRight,
                    preset = CanimationPreset.LightSpeedInRight,
                    codeSnippet = Res.string.examples_data_entrance_item_25_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_item_07_title,
                    description = Res.string.examples_data_entrance_item_07_description,
                    effect = Canimation.Entrance.Snap,
                    preset = CanimationPreset.Snap,
                    codeSnippet = Res.string.examples_data_entrance_item_26_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_item_08_title,
                    description = Res.string.examples_data_entrance_item_08_description,
                    effect = Canimation.Entrance.SwingIn,
                    preset = CanimationPreset.SwingIn,
                    codeSnippet = Res.string.examples_data_entrance_item_27_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_item_09_title,
                    description = Res.string.examples_data_entrance_item_09_description,
                    effect = Canimation.Entrance.Unfold,
                    preset = CanimationPreset.ElevateIn,
                    codeSnippet = Res.string.examples_data_entrance_item_28_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_item_10_title,
                    description = Res.string.examples_data_entrance_item_10_description,
                    effect = Canimation.Entrance.Rise,
                    preset = CanimationPreset.FadeUp,
                    codeSnippet = Res.string.examples_data_entrance_item_29_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_item_11_title,
                    description = Res.string.examples_data_entrance_item_11_description,
                    effect = Canimation.Entrance.Emerge,
                    preset = CanimationPreset.ScaleIn,
                    codeSnippet = Res.string.examples_data_entrance_item_30_code,
                ),
            ),
        ),
        ShowcaseCategory(
            id = "entrance-back",
            title = Res.string.examples_data_entrance_back_title,
            subtitle = Res.string.examples_data_entrance_back_subtitle,
            tags = listOf(ShowcaseTagId.Entrance, ShowcaseTagId.Direction),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_back_item_01_title,
                    description = Res.string.examples_data_entrance_back_item_01_description,
                    effect = Canimation.Entrance.BackInUp,
                    preset = CanimationPreset.BackInUp,
                    codeSnippet = Res.string.examples_data_entrance_back_item_31_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_back_item_02_title,
                    description = Res.string.examples_data_entrance_back_item_02_description,
                    effect = Canimation.Entrance.BackInDown,
                    preset = CanimationPreset.BackInDown,
                    codeSnippet = Res.string.examples_data_entrance_back_item_32_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_back_item_03_title,
                    description = Res.string.examples_data_entrance_back_item_03_description,
                    effect = Canimation.Entrance.BackInLeft,
                    preset = CanimationPreset.BackInLeft,
                    codeSnippet = Res.string.examples_data_entrance_back_item_33_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_back_item_04_title,
                    description = Res.string.examples_data_entrance_back_item_04_description,
                    effect = Canimation.Entrance.BackInRight,
                    preset = CanimationPreset.BackInRight,
                    codeSnippet = Res.string.examples_data_entrance_back_item_34_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_back_item_05_title,
                    description = Res.string.examples_data_entrance_back_item_05_description,
                    effect = Canimation.Entrance.ShrinkIn,
                    preset = CanimationPreset.ShrinkIn,
                    codeSnippet = Res.string.examples_data_entrance_back_item_35_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_entrance_back_item_06_title,
                    description = Res.string.examples_data_entrance_back_item_06_description,
                    effect = Canimation.Entrance.TiltIn,
                    preset = CanimationPreset.TiltIn,
                    codeSnippet = Res.string.examples_data_entrance_back_item_36_code,
                ),
            ),
        ),
        ShowcaseCategory(
            id = "material",
            title = Res.string.examples_data_material_title,
            subtitle = Res.string.examples_data_material_subtitle,
            tags = listOf(ShowcaseTagId.Material),
            examples = listOf(
                ShowcaseItem(
                    title = Res.string.examples_data_material_item_01_title,
                    description = Res.string.examples_data_material_item_01_description,
                    effect = Canimation.Material.FadeThrough,
                    preset = CanimationPreset.FadeThrough,
                    codeSnippet = Res.string.examples_data_material_item_37_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_material_item_02_title,
                    description = Res.string.examples_data_material_item_02_description,
                    effect = Canimation.Material.SharedAxisX,
                    preset = CanimationPreset.SharedAxisX,
                    codeSnippet = Res.string.examples_data_material_item_38_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_material_item_03_title,
                    description = Res.string.examples_data_material_item_03_description,
                    effect = Canimation.Material.SharedAxisY,
                    preset = CanimationPreset.SharedAxisY,
                    codeSnippet = Res.string.examples_data_material_item_39_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_material_item_04_title,
                    description = Res.string.examples_data_material_item_04_description,
                    effect = Canimation.Material.SharedAxisZ,
                    preset = CanimationPreset.SharedAxisX,
                    codeSnippet = Res.string.examples_data_material_item_40_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_material_item_05_title,
                    description = Res.string.examples_data_material_item_05_description,
                    effect = Canimation.Material.Emphasized,
                    preset = CanimationPreset.EmphasizedEntry,
                    codeSnippet = Res.string.examples_data_material_item_41_code,
                ),
                ShowcaseItem(
                    title = Res.string.examples_data_material_item_06_title,
                    description = Res.string.examples_data_material_item_06_description,
                    effect = Canimation.Material.ContainerTransform,
                    preset = CanimationPreset.EmphasizedEntry,
                    codeSnippet = Res.string.examples_data_material_item_42_code,
                ),
            ),
        ),
)

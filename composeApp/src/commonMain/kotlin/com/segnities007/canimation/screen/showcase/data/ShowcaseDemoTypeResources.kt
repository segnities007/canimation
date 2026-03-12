package com.segnities007.canimation.screen.showcase.data

import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.examples_detail_type_component
import canimation.composeapp.generated.resources.examples_detail_type_composition
import canimation.composeapp.generated.resources.examples_detail_type_effect
import canimation.composeapp.generated.resources.examples_detail_type_emphasis
import canimation.composeapp.generated.resources.examples_detail_type_enter_exit
import canimation.composeapp.generated.resources.examples_detail_type_real_world
import canimation.composeapp.generated.resources.examples_detail_type_stagger
import canimation.composeapp.generated.resources.examples_detail_type_transition
import canimation.composeapp.generated.resources.examples_detail_type_visibility
import org.jetbrains.compose.resources.StringResource

internal fun showcaseDemoTypeLabelRes(demoType: ShowcaseDemoType): StringResource =
    when (demoType) {
        ShowcaseDemoType.Effect -> Res.string.examples_detail_type_effect
        ShowcaseDemoType.Transition -> Res.string.examples_detail_type_transition
        ShowcaseDemoType.Composition -> Res.string.examples_detail_type_composition
        ShowcaseDemoType.Stagger -> Res.string.examples_detail_type_stagger
        ShowcaseDemoType.Emphasis -> Res.string.examples_detail_type_emphasis
        ShowcaseDemoType.RealWorld -> Res.string.examples_detail_type_real_world
        ShowcaseDemoType.Component -> Res.string.examples_detail_type_component
        ShowcaseDemoType.EnterExit -> Res.string.examples_detail_type_enter_exit
        ShowcaseDemoType.Visibility -> Res.string.examples_detail_type_visibility
    }

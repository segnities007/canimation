package com.segnities007.canimation.screen.apireference

import canimation.composeapp.generated.resources.*

internal fun MutableList<ApiEntry>.addFactoryEntries() {
    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_fade,
        signature = Res.string.api_auto_fun_fade_n_from_float_0f_n_to_float_1f_n_canimat,
        description = Res.string.api_auto_create_a_fade_opacity_effect_default_transitions,
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_slideup,
        signature = Res.string.api_auto_fun_slideup_n_offset_dp_16_dp_n_canimationeffect,
        description = Res.string.api_auto_create_a_slide_up_effect_element_enters_from_bel,
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_slidedown,
        signature = Res.string.api_auto_fun_slidedown_n_offset_dp_16_dp_n_canimationeffe,
        description = Res.string.api_auto_create_a_slide_down_effect_element_enters_from_a,
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_slideleft,
        signature = Res.string.api_auto_fun_slideleft_n_offset_dp_24_dp_n_canimationeffe,
        description = Res.string.api_auto_create_a_slide_left_effect_element_enters_from_t,
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_slideright,
        signature = Res.string.api_auto_fun_slideright_n_offset_dp_24_dp_n_canimationeff,
        description = Res.string.api_auto_create_a_slide_right_effect_element_enters_from_,
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_scale,
        signature = Res.string.api_auto_fun_scale_n_from_float_0_92f_n_to_float_1f_n_can,
        description = Res.string.api_auto_create_a_scale_effect_default_scales_from_92_to_,
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_pop,
        signature = Res.string.api_auto_fun_pop_n_from_float_0_8f_n_to_float_1f_n_canima,
        description = Res.string.api_auto_create_a_pop_effect_with_overshoot_feel_300ms_du,
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_rotate,
        signature = Res.string.api_auto_fun_rotate_n_fromdegrees_float_15f_n_todegrees_f,
        description = Res.string.api_auto_create_a_rotation_effect_default_rotates_from_15,
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_spin,
        signature = Res.string.api_auto_fun_spin_n_fromdegrees_float_360f_n_canimationef,
        description = Res.string.api_auto_create_a_full_360_spin_effect_400ms_duration,
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_zoom,
        signature = Res.string.api_auto_fun_zoom_n_from_float_0_5f_n_canimationeffect,
        description = Res.string.api_auto_create_a_zoom_effect_scale_fade_from_50_scale_to,
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_bounce,
        signature = Res.string.api_auto_fun_bounce_canimationeffect,
        description = Res.string.api_auto_create_a_bounce_entry_scale_0_3_1_fade_400ms_dur,
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_duration,
        signature = Res.string.api_auto_fun_duration_ms_int_canimationeffect,
        description = Res.string.api_auto_create_an_effect_wrapper_with_custom_duration_co,
        category = RefFilter.Factories,
        codeExample = Res.string.api_auto_canimation_fade_up_canimationeffect_duration_500,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect_easing,
        signature = Res.string.api_auto_fun_easing_easing_easing_canimationeffect,
        description = Res.string.api_auto_create_an_effect_wrapper_with_custom_easing_curv,
        category = RefFilter.Factories,
        codeExample = Res.string.api_auto_canimation_fade_up_canimationeffect_easing_linea,
    ))
}

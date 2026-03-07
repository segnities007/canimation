package com.segnities007.canimation.screen

import canimation.composeapp.generated.resources.*
internal val apiEntries: List<ApiEntry> = buildList {

    // ── Modifier extensions ──

    add(ApiEntry(
        name = Res.string.api_auto_modifier_canimation,
        signature = Res.string.api_auto_fun_modifier_canimation_n_visible_boolean_n_effe,
        description = Res.string.api_auto_primary_api_apply_enter_exit_animations_using_co,
        category = RefFilter.Modifiers,
        badge = Res.string.api_auto_recommended,
        codeExample = Res.string.api_auto_simple_fade_up_modifier_canimation_visible_isvis,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_modifier_canimationtransition,
        signature = Res.string.api_auto_fun_modifier_canimationtransition_n_visible_bool,
        description = Res.string.api_auto_asymmetric_enter_exit_transitions_using_canimati,
        category = RefFilter.Modifiers,
        badge = Res.string.api_auto_advanced,
        codeExample = Res.string.api_auto_modifier_canimationtransition_visible_isvisible_,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_modifier_canimationenter,
        signature = Res.string.api_auto_fun_modifier_canimationenter_n_visible_boolean_n,
        description = Res.string.api_auto_enter_only_animation_using_a_named_canimationpre,
        category = RefFilter.Modifiers,
        badge = Res.string.api_auto_lists,
        codeExample = Res.string.api_auto_lazycolumn_itemsindexed_items_index_item_card_mo,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_modifier_canimationenter_custom_spec,
        signature = Res.string.api_auto_fun_modifier_canimationenter_n_visible_boolean_n_2,
        description = Res.string.api_auto_enter_only_animation_with_custom_canimationspec_,
        category = RefFilter.Modifiers,
        codeExample = Res.string.api_auto_val_spec_canimationspec_durationms_400_easing_fa,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_modifier_canimationexit,
        signature = Res.string.api_auto_fun_modifier_canimationexit_n_visible_boolean_n_,
        description = Res.string.api_auto_exit_only_animation_using_a_named_canimationpres,
        category = RefFilter.Modifiers,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_modifier_canimationexit_custom_spec,
        signature = Res.string.api_auto_fun_modifier_canimationexit_n_visible_boolean_n__2,
        description = Res.string.api_auto_exit_only_animation_with_custom_canimationspec_f,
        category = RefFilter.Modifiers,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_modifier_canimationtransition_preset,
        signature = Res.string.api_auto_fun_modifier_canimationtransition_n_visible_bool_2,
        description = Res.string.api_auto_full_enter_exit_transition_using_named_canimatio,
        category = RefFilter.Modifiers,
        codeExample = Res.string.api_auto_modifier_canimationtransition_visible_expanded_e,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_modifier_canimationtransition_full_spec,
        signature = Res.string.api_auto_fun_modifier_canimationtransition_n_visible_bool_3,
        description = Res.string.api_auto_most_flexible_transition_define_separate_enter_e,
        category = RefFilter.Modifiers,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_modifier_canimationemphasize,
        signature = Res.string.api_auto_fun_modifier_canimationemphasize_n_active_boolea,
        description = Res.string.api_auto_emphasis_animation_that_draws_attention_to_inter,
        category = RefFilter.Modifiers,
        badge = Res.string.api_auto_attention_14,
        codeExample = Res.string.api_auto_modifier_canimationemphasize_active_hasnotificat,
    ))

    // ── Composable functions ──

    add(ApiEntry(
        name = Res.string.api_auto_canimationvisibility,
        signature = Res.string.api_auto_composable_nfun_canimationvisibility_n_visible_b,
        description = Res.string.api_auto_animatedvisibility_wrapper_with_named_presets_wr,
        category = RefFilter.Composables,
        badge = Res.string.api_auto_classic,
        codeExample = Res.string.api_auto_canimationvisibility_visible_showcontent_enterpr,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationvisibility_custom_spec,
        signature = Res.string.api_auto_composable_nfun_canimationvisibility_n_visible_b_2,
        description = Res.string.api_auto_custom_spec_version_of_canimationvisibility_for_,
        category = RefFilter.Composables,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationprovider,
        signature = Res.string.api_auto_composable_nfun_canimationprovider_n_tokens_cani,
        description = Res.string.api_auto_scoped_provider_that_sets_the_animation_context_,
        category = RefFilter.Composables,
        badge = Res.string.api_auto_provider,
        codeExample = Res.string.api_auto_always_play_full_animations_regardless_of_system,
    ))

    // ── Data classes ──

    add(ApiEntry(
        name = Res.string.api_auto_canimationeffect,
        signature = Res.string.api_auto_data_class_canimationeffect_n_val_alpha_canimati,
        description = Res.string.api_auto_composable_animation_effect_all_properties_are_o,
        category = RefFilter.DataClasses,
        badge = Res.string.api_auto_core,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationspec,
        signature = Res.string.api_auto_data_class_canimationspec_n_val_durationms_int_n,
        description = Res.string.api_auto_low_level_animation_specification_with_required_,
        category = RefFilter.DataClasses,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationpresetspec,
        signature = Res.string.api_auto_data_class_canimationpresetspec_n_val_fullenter_,
        description = Res.string.api_auto_complete_specification_for_a_named_preset_define,
        category = RefFilter.DataClasses,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationrange,
        signature = Res.string.api_auto_data_class_canimationrange_n_val_from_float_n_va,
        description = Res.string.api_auto_a_range_of_float_values_for_animatable_propertie,
        category = RefFilter.DataClasses,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationdprange,
        signature = Res.string.api_auto_data_class_canimationdprange_n_val_from_dp_n_val,
        description = Res.string.api_auto_a_range_of_dp_values_for_offset_properties_offse,
        category = RefFilter.DataClasses,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationcontext,
        signature = Res.string.api_auto_data_class_canimationcontext_n_val_tokens_canima,
        description = Res.string.api_auto_runtime_context_providing_tokens_motion_level_an,
        category = RefFilter.DataClasses,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationtokens,
        signature = Res.string.api_auto_data_class_canimationtokens_n_val_duration_durat,
        description = Res.string.api_auto_design_tokens_for_animation_timing_easing_curves,
        category = RefFilter.DataClasses,
    ))

    // ── Enums & policies ──

    add(ApiEntry(
        name = Res.string.api_auto_canimationpreset,
        signature = Res.string.api_auto_enum_class_canimationpreset,
        description = Res.string.api_auto_112_named_animation_presets_covers_fade_scale_sl,
        category = RefFilter.Enums,
        badge = Res.string.api_auto_112_entries,
        codeExample = Res.string.api_auto_common_presets_canimationpreset_fadeup_fade_slid,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationpolicy,
        signature = Res.string.api_auto_sealed_interface_canimationpolicy,
        description = Res.string.api_auto_controls_motion_behavior_in_canimationprovider_s,
        category = RefFilter.Enums,
        codeExample = Res.string.api_auto_canimationpolicy_systemaware_default_respects_os,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_canimationlevel,
        signature = Res.string.api_auto_enum_class_canimationlevel_full_reduced_off,
        description = Res.string.api_auto_resolved_motion_level_full_plays_all_animations_,
        category = RefFilter.Enums,
    ))

    add(ApiEntry(
        name = Res.string.api_auto_animationdirection,
        signature = Res.string.api_auto_enum_class_animationdirection_enter_exit,
        description = Res.string.api_auto_indicates_which_direction_an_animation_is_playin,
        category = RefFilter.Enums,
    ))

    // ── Factory methods ──

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

    addNamespaceEntries()
}

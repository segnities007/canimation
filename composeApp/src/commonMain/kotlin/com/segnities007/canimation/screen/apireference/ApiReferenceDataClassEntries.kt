package com.segnities007.canimation.screen.apireference

import canimation.composeapp.generated.resources.*

internal fun MutableList<ApiEntry>.addDataClassEntries() {
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
}

package com.segnities007.canimation.screen.apireference

import canimation.composeapp.generated.resources.*

internal fun MutableList<ApiEntry>.addEnumEntries() {
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
}

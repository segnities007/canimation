package com.segnities007.canimation.screen.apireference

import canimation.composeapp.generated.resources.*

internal fun MutableList<ApiEntry>.addModifierEntries() {
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
}

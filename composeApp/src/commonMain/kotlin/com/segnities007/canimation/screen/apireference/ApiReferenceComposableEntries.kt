package com.segnities007.canimation.screen.apireference

import canimation.composeapp.generated.resources.*

internal fun MutableList<ApiEntry>.addComposableEntries() {
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
}

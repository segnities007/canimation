package com.segnities007.canimation.screen.home

import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.features_accessibility
import canimation.composeapp.generated.resources.features_accessibility_subtitle
import canimation.composeapp.generated.resources.features_diagnostics
import canimation.composeapp.generated.resources.features_diagnostics_subtitle
import canimation.composeapp.generated.resources.features_multiplatform
import canimation.composeapp.generated.resources.features_multiplatform_subtitle
import canimation.composeapp.generated.resources.features_presets_count
import canimation.composeapp.generated.resources.features_presets_subtitle
import canimation.composeapp.generated.resources.features_testable
import canimation.composeapp.generated.resources.features_testable_subtitle
import canimation.composeapp.generated.resources.features_token_system
import canimation.composeapp.generated.resources.features_token_system_subtitle
import io.github.canimation.core.CanimationPreset
import org.jetbrains.compose.resources.StringResource

internal data class FeatureCardSpec(
    val titleRes: StringResource,
    val subtitleRes: StringResource,
    val demoPreset: CanimationPreset,
    val demoDelay: Long,
    val usesPresetCount: Boolean = false,
)

internal val homeFeatureRows: List<List<FeatureCardSpec>> =
    listOf(
        listOf(
            FeatureCardSpec(
                titleRes = Res.string.features_presets_count,
                subtitleRes = Res.string.features_presets_subtitle,
                demoPreset = CanimationPreset.BounceIn,
                demoDelay = 0L,
                usesPresetCount = true,
            ),
            FeatureCardSpec(
                titleRes = Res.string.features_accessibility,
                subtitleRes = Res.string.features_accessibility_subtitle,
                demoPreset = CanimationPreset.GentleFade,
                demoDelay = 400L,
            ),
            FeatureCardSpec(
                titleRes = Res.string.features_diagnostics,
                subtitleRes = Res.string.features_diagnostics_subtitle,
                demoPreset = CanimationPreset.Pulse,
                demoDelay = 800L,
            ),
        ),
        listOf(
            FeatureCardSpec(
                titleRes = Res.string.features_testable,
                subtitleRes = Res.string.features_testable_subtitle,
                demoPreset = CanimationPreset.Snap,
                demoDelay = 200L,
            ),
            FeatureCardSpec(
                titleRes = Res.string.features_multiplatform,
                subtitleRes = Res.string.features_multiplatform_subtitle,
                demoPreset = CanimationPreset.SpringSlideUp,
                demoDelay = 600L,
            ),
            FeatureCardSpec(
                titleRes = Res.string.features_token_system,
                subtitleRes = Res.string.features_token_system_subtitle,
                demoPreset = CanimationPreset.SharedAxisY,
                demoDelay = 1000L,
            ),
        ),
    )

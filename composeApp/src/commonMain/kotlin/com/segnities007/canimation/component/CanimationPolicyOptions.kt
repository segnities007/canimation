package com.segnities007.canimation.component

import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.settings_policy_full_motion
import canimation.composeapp.generated.resources.settings_policy_full_motion_desc
import canimation.composeapp.generated.resources.settings_policy_motion_off
import canimation.composeapp.generated.resources.settings_policy_motion_off_desc
import canimation.composeapp.generated.resources.settings_policy_reduced_motion
import canimation.composeapp.generated.resources.settings_policy_reduced_motion_desc
import canimation.composeapp.generated.resources.settings_policy_system_aware
import canimation.composeapp.generated.resources.settings_policy_system_aware_desc
import io.github.canimation.core.CanimationPolicy
import org.jetbrains.compose.resources.StringResource

internal data class CanimationPolicyOption(
    val policy: CanimationPolicy,
    val labelRes: StringResource,
    val descriptionRes: StringResource,
)

internal val canimationPolicyOptions: List<CanimationPolicyOption> =
    listOf(
        CanimationPolicyOption(
            policy = CanimationPolicy.SystemAware,
            labelRes = Res.string.settings_policy_system_aware,
            descriptionRes = Res.string.settings_policy_system_aware_desc,
        ),
        CanimationPolicyOption(
            policy = CanimationPolicy.AlwaysFull,
            labelRes = Res.string.settings_policy_full_motion,
            descriptionRes = Res.string.settings_policy_full_motion_desc,
        ),
        CanimationPolicyOption(
            policy = CanimationPolicy.AlwaysReduced,
            labelRes = Res.string.settings_policy_reduced_motion,
            descriptionRes = Res.string.settings_policy_reduced_motion_desc,
        ),
        CanimationPolicyOption(
            policy = CanimationPolicy.AlwaysOff,
            labelRes = Res.string.settings_policy_motion_off,
            descriptionRes = Res.string.settings_policy_motion_off_desc,
        ),
    )

package com.segnities007.canimation.screen.showcase.detail

import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.settings_policy_full_motion
import canimation.composeapp.generated.resources.settings_policy_full_motion_desc
import canimation.composeapp.generated.resources.settings_policy_motion_off
import canimation.composeapp.generated.resources.settings_policy_motion_off_desc
import canimation.composeapp.generated.resources.settings_policy_reduced_motion
import canimation.composeapp.generated.resources.settings_policy_reduced_motion_desc
import io.github.canimation.core.CanimationPolicy
import org.jetbrains.compose.resources.StringResource

internal enum class ShowcasePreviewMotion(
    val policy: CanimationPolicy,
    val labelRes: StringResource,
    val descriptionRes: StringResource,
) {
    Full(
        policy = CanimationPolicy.AlwaysFull,
        labelRes = Res.string.settings_policy_full_motion,
        descriptionRes = Res.string.settings_policy_full_motion_desc,
    ),
    Reduced(
        policy = CanimationPolicy.AlwaysReduced,
        labelRes = Res.string.settings_policy_reduced_motion,
        descriptionRes = Res.string.settings_policy_reduced_motion_desc,
    ),
    Off(
        policy = CanimationPolicy.AlwaysOff,
        labelRes = Res.string.settings_policy_motion_off,
        descriptionRes = Res.string.settings_policy_motion_off_desc,
    ),
}

internal data class ShowcaseDetailUiState(
    val entryStage: Int = -1,
    val previewMotion: ShowcasePreviewMotion = ShowcasePreviewMotion.Full,
)

internal sealed interface ShowcaseDetailEvent {
    data class EntryStageUpdated(val stage: Int) : ShowcaseDetailEvent

    data class PreviewMotionSelected(val previewMotion: ShowcasePreviewMotion) : ShowcaseDetailEvent
}

internal fun reduceShowcaseDetailState(
    current: ShowcaseDetailUiState,
    event: ShowcaseDetailEvent,
): ShowcaseDetailUiState = when (event) {
    is ShowcaseDetailEvent.EntryStageUpdated -> current.copy(entryStage = event.stage)
    is ShowcaseDetailEvent.PreviewMotionSelected -> current.copy(previewMotion = event.previewMotion)
}

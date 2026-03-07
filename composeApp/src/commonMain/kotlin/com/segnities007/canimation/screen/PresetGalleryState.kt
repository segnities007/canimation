package com.segnities007.canimation.screen

import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.filter_all
import canimation.composeapp.generated.resources.filter_fade_only
import canimation.composeapp.generated.resources.filter_move
import canimation.composeapp.generated.resources.filter_rotate
import canimation.composeapp.generated.resources.filter_scale
import io.github.canimation.core.CanimationPreset
import org.jetbrains.compose.resources.StringResource

internal enum class MotionFilter(val labelRes: StringResource) {
    All(Res.string.filter_all),
    Translation(Res.string.filter_move),
    Scale(Res.string.filter_scale),
    Rotation(Res.string.filter_rotate),
    AlphaOnly(Res.string.filter_fade_only),
}

internal data class PresetGalleryUiState(
    val motionFilter: MotionFilter = MotionFilter.All,
    val headerStage: Int = -1,
    val codeDialogPreset: CanimationPreset? = null,
)

internal sealed interface PresetGalleryEvent {
    data class HeaderStageUpdated(val stage: Int) : PresetGalleryEvent
    data class MotionFilterSelected(val filter: MotionFilter) : PresetGalleryEvent
    data class CodeDialogOpened(val preset: CanimationPreset) : PresetGalleryEvent
    data object CodeDialogClosed : PresetGalleryEvent
}

internal fun reducePresetGalleryState(
    current: PresetGalleryUiState,
    event: PresetGalleryEvent,
): PresetGalleryUiState = when (event) {
    is PresetGalleryEvent.HeaderStageUpdated -> current.copy(headerStage = event.stage)
    is PresetGalleryEvent.MotionFilterSelected -> current.copy(motionFilter = event.filter)
    is PresetGalleryEvent.CodeDialogOpened -> current.copy(codeDialogPreset = event.preset)
    PresetGalleryEvent.CodeDialogClosed -> current.copy(codeDialogPreset = null)
}

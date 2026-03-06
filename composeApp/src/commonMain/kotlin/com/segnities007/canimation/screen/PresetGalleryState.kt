package com.segnities007.canimation.screen

import io.github.canimation.core.CanimationPreset

internal enum class MotionFilter(val label: String) {
    All("All"),
    Translation("Move"),
    Scale("Scale"),
    Rotation("Rotate"),
    AlphaOnly("Fade Only"),
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

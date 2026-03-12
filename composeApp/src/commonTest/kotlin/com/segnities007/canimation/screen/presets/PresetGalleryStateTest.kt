package com.segnities007.canimation.screen.presets

import io.github.canimation.core.CanimationPreset
import kotlin.test.Test
import kotlin.test.assertEquals

class PresetGalleryStateTest {
    @Test
    fun reducerTracksDialogPresetAndFilterSelection() {
        val base = PresetGalleryUiState()
        val filtered = reducePresetGalleryState(base, PresetGalleryEvent.MotionFilterSelected(MotionFilter.Scale))
        val opened = reducePresetGalleryState(filtered, PresetGalleryEvent.CodeDialogOpened(CanimationPreset.FadeUp))
        val closed = reducePresetGalleryState(opened, PresetGalleryEvent.CodeDialogClosed)

        assertEquals(MotionFilter.Scale, filtered.motionFilter)
        assertEquals(CanimationPreset.FadeUp, opened.codeDialogPreset)
        assertEquals(null, closed.codeDialogPreset)
        assertEquals(MotionFilter.Scale, closed.motionFilter)
    }

    @Test
    fun stateHolderAppliesReducerOnEvent() {
        val stateHolder = PresetGalleryStateHolder()

        stateHolder.onEvent(PresetGalleryEvent.HeaderStageUpdated(3))
        stateHolder.onEvent(PresetGalleryEvent.MotionFilterSelected(MotionFilter.Rotation))

        assertEquals(3, stateHolder.uiState.headerStage)
        assertEquals(MotionFilter.Rotation, stateHolder.uiState.motionFilter)
    }
}

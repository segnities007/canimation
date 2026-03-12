package com.segnities007.canimation.screen.showcase.detail

import kotlin.test.Test
import kotlin.test.assertEquals

class ShowcaseDetailStateTest {

    @Test
    fun entryStageIsUpdatedByReducer() {
        val base = ShowcaseDetailUiState(entryStage = -1)

        val updated = reduceShowcaseDetailState(base, ShowcaseDetailEvent.EntryStageUpdated(3))

        assertEquals(3, updated.entryStage)
    }
}

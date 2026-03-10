package com.segnities007.canimation.screen.examples.detail

import kotlin.test.Test
import kotlin.test.assertEquals

class ExampleDetailStateTest {

    @Test
    fun entryStageIsUpdatedByReducer() {
        val base = ExampleDetailUiState(entryStage = -1)

        val updated = reduceExampleDetailState(base, ExampleDetailEvent.EntryStageUpdated(3))

        assertEquals(3, updated.entryStage)
    }
}

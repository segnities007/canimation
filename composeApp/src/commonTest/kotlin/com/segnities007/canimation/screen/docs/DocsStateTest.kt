package com.segnities007.canimation.screen.docs

import kotlin.test.Test
import kotlin.test.assertEquals

class DocsStateTest {
    @Test
    fun reducerTracksStageAndSectionIndependently() {
        val base = DocsUiState()
        val staged = reduceDocsState(base, DocsEvent.StageUpdated(5))
        val selected = reduceDocsState(staged, DocsEvent.SectionSelected(DocSection.Roadmap))

        assertEquals(5, staged.stage)
        assertEquals(DocSection.Overview, staged.activeSection)
        assertEquals(5, selected.stage)
        assertEquals(DocSection.Roadmap, selected.activeSection)
    }
}

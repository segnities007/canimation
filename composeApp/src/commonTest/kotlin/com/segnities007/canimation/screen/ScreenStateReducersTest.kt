package com.segnities007.canimation.screen

import androidx.compose.animation.core.LinearEasing
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ScreenStateReducersTest {

    @Test
    fun apiReferenceFilterUpdatesState() {
        val base = ApiReferenceUiState()

        val updated = reduceApiReferenceState(
            current = base,
            event = ApiReferenceEvent.FilterSelected(RefFilter.Namespace),
        )

        assertEquals(RefFilter.Namespace, updated.selectedFilter)
    }

    @Test
    fun docsReducerUpdatesActiveSection() {
        val base = DocsUiState()

        val updated = reduceDocsState(base, DocsEvent.SectionSelected(DocSection.Platforms))

        assertEquals(DocSection.Platforms, updated.activeSection)
    }

    @Test
    fun presetGalleryReducerTracksDialogPreset() {
        val base = PresetGalleryUiState()
        val opened = reducePresetGalleryState(base, PresetGalleryEvent.CodeDialogOpened(CanimationPreset.FadeUp))
        val closed = reducePresetGalleryState(opened, PresetGalleryEvent.CodeDialogClosed)

        assertEquals(CanimationPreset.FadeUp, opened.codeDialogPreset)
        assertEquals(null, closed.codeDialogPreset)
    }

    @Test
    fun motionFilterMatchesRotationAndRejectsScaleFilter() {
        val rotationOnly = CanimationSpec(
            durationMs = 200,
            easing = LinearEasing,
            rotation = CanimationRange(from = -30f, to = 0f),
        )

        assertTrue(matchesMotionFilter(rotationOnly, MotionFilter.Rotation))
        assertFalse(matchesMotionFilter(rotationOnly, MotionFilter.Scale))
    }

    @Test
    fun presetCodeSampleContainsPresetName() {
        val spec = CanimationSpec(
            durationMs = 220,
            easing = LinearEasing,
            alpha = CanimationRange(from = 0f, to = 1f),
        )
        val presetSpec = io.github.canimation.core.CanimationPresetSpec(
            fullEnter = spec,
            fullExit = spec,
            reducedEnter = spec,
            reducedExit = spec,
        )

        val snippet = buildPresetCodeSample(CanimationPreset.FadeUp, presetSpec)

        assertTrue(snippet.contains("CanimationPreset.FadeUp"))
        assertTrue(snippet.contains("Modifier.canimationTransition"))
    }
}

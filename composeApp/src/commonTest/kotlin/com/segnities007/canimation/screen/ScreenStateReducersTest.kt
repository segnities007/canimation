package com.segnities007.canimation.screen

import androidx.compose.animation.core.LinearEasing
import com.segnities007.canimation.app.settings.AppSettingsSheetStageEvent
import com.segnities007.canimation.app.settings.AppSettingsSheetStageState
import com.segnities007.canimation.app.settings.AppSettingsSheetEvent
import com.segnities007.canimation.app.settings.onAppSettingsSheetEvent
import com.segnities007.canimation.app.settings.reduceAppSettingsSheetStageState
import com.segnities007.canimation.app.settings.toAppSettingsSheetState
import com.segnities007.canimation.app.state.AppEvent
import com.segnities007.canimation.app.state.AppStateHolder
import com.segnities007.canimation.app.state.AppUiState
import com.segnities007.canimation.app.state.reduceAppState
import com.segnities007.canimation.screen.apireference.ApiReferenceEvent
import com.segnities007.canimation.screen.apireference.ApiReferenceUiState
import com.segnities007.canimation.screen.apireference.RefFilter
import com.segnities007.canimation.screen.apireference.reduceApiReferenceState
import com.segnities007.canimation.screen.docs.DocSection
import com.segnities007.canimation.screen.docs.DocsEvent
import com.segnities007.canimation.screen.docs.DocsUiState
import com.segnities007.canimation.screen.docs.reduceDocsState
import com.segnities007.canimation.screen.home.HomeEvent
import com.segnities007.canimation.screen.home.HomeUiState
import com.segnities007.canimation.screen.home.reduceHomeState
import com.segnities007.canimation.screen.presets.MotionFilter
import com.segnities007.canimation.screen.presets.PresetGalleryEvent
import com.segnities007.canimation.screen.presets.PresetGalleryUiState
import com.segnities007.canimation.screen.presets.buildPresetGalleryCodeSample
import com.segnities007.canimation.screen.presets.matches
import com.segnities007.canimation.screen.presets.reducePresetGalleryState
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ScreenStateReducersTest {
    @Test
    fun appReducerResetsPreviewTuningWithoutTouchingOtherState() {
        val base =
            AppUiState(
                durationScale = 1.8f,
                distanceScale = 0.6f,
                scaleIntensity = 2.2f,
                rotationScale = 0.4f,
                diagnosticsEnabled = true,
            )

        val updated = reduceAppState(base, AppEvent.PreviewTuningReset)

        assertEquals(1f, updated.durationScale)
        assertEquals(1f, updated.distanceScale)
        assertEquals(1f, updated.scaleIntensity)
        assertEquals(1f, updated.rotationScale)
        assertTrue(updated.diagnosticsEnabled)
    }

    @Test
    fun appUiStateBuildsSettingsSheetStateFromSingleSourceOfTruth() {
        val base =
            AppUiState(
                policy = CanimationPolicy.AlwaysReduced,
                diagnosticsEnabled = true,
                autoPlayEnabled = false,
                cycleMs = 900f,
                durationScale = 1.4f,
                distanceScale = 0.8f,
                scaleIntensity = 1.2f,
                rotationScale = 0.6f,
            )

        val state =
            base.toAppSettingsSheetState(
                isDarkMode = true,
                showPresetTuning = false,
            )

        assertTrue(state.isDarkMode)
        assertEquals(CanimationPolicy.AlwaysReduced, state.policy)
        assertFalse(state.showPresetTuning)
        assertFalse(state.autoPlayEnabled)
        assertEquals(900f, state.cycleMs)
        assertEquals(1.4f, state.durationScale)
        assertEquals(0.8f, state.distanceScale)
        assertEquals(1.2f, state.scaleIntensity)
        assertEquals(0.6f, state.rotationScale)
        assertTrue(state.diagnosticsEnabled)
    }

    @Test
    fun appSettingsSheetEventsDispatchIntoAppStateHolder() {
        val stateHolder = AppStateHolder()

        stateHolder.onAppSettingsSheetEvent(AppSettingsSheetEvent.DarkModeToggled, isDarkMode = true)
        stateHolder.onAppSettingsSheetEvent(
            AppSettingsSheetEvent.PolicyChanged(CanimationPolicy.AlwaysOff),
            isDarkMode = true,
        )
        stateHolder.onAppSettingsSheetEvent(
            AppSettingsSheetEvent.DiagnosticsEnabledChanged(true),
            isDarkMode = true,
        )

        assertEquals(false, stateHolder.uiState.darkOverride)
        assertEquals(CanimationPolicy.AlwaysOff, stateHolder.uiState.policy)
        assertTrue(stateHolder.uiState.diagnosticsEnabled)
    }

    @Test
    fun appSettingsSheetStageReducerTracksEntryStage() {
        val base = AppSettingsSheetStageState()

        val updated =
            reduceAppSettingsSheetStageState(
                current = base,
                event = AppSettingsSheetStageEvent.StageChanged(4),
            )

        assertEquals(4, updated.stage)
    }

    @Test
    fun homeReducerTracksHeroStageProgression() {
        val base = HomeUiState()

        val updated = reduceHomeState(base, HomeEvent.StageChanged(7))

        assertEquals(7, updated.stage)
    }

    @Test
    fun apiReferenceFilterUpdatesState() {
        val base = ApiReferenceUiState()

        val updated =
            reduceApiReferenceState(
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
        val rotationOnly =
            CanimationSpec(
                durationMs = 200,
                easing = LinearEasing,
                rotation = CanimationRange(from = -30f, to = 0f),
            )

        assertTrue(MotionFilter.Rotation.matches(rotationOnly))
        assertFalse(MotionFilter.Scale.matches(rotationOnly))
    }

    @Test
    fun presetCodeSampleContainsPresetName() {
        val spec =
            CanimationSpec(
                durationMs = 220,
                easing = LinearEasing,
                alpha = CanimationRange(from = 0f, to = 1f),
            )
        val presetSpec =
            io.github.canimation.core.CanimationPresetSpec(
                fullEnter = spec,
                fullExit = spec,
                reducedEnter = spec,
                reducedExit = spec,
            )

        val snippet = buildPresetGalleryCodeSample(CanimationPreset.FadeUp, presetSpec)

        assertTrue(snippet.contains("CanimationPreset.FadeUp"))
        assertTrue(snippet.contains("Modifier.canimationTransition"))
    }
}

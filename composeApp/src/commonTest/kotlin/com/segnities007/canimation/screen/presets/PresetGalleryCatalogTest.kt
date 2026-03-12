package com.segnities007.canimation.screen.presets

import androidx.compose.animation.core.LinearEasing
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PresetGalleryCatalogTest {
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
            CanimationPresetSpec(
                fullEnter = spec,
                fullExit = spec,
                reducedEnter = spec,
                reducedExit = spec,
            )

        val snippet = buildPresetGalleryCodeSample(CanimationPreset.FadeUp, presetSpec)

        assertTrue(snippet.contains("CanimationPreset.FadeUp"))
        assertTrue(snippet.contains("Modifier.canimationTransition"))
    }

    @Test
    fun allFilterReturnsOriginalEntries() {
        val entries = buildPresetGalleryEntries(io.github.canimation.presets.PresetsExtensionRegistry.allPresetSpecs)

        assertEquals(entries, filterPresetGalleryEntries(entries, MotionFilter.All))
    }
}

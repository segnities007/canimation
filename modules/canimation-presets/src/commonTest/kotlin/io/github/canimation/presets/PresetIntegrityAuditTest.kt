package io.github.canimation.presets

import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class PresetIntegrityAuditTest {
    @Test
    fun allPresetsAreRegisteredInExtensionRegistry() {
        val expected = CanimationPreset.entries.toSet()
        val actual = PresetsExtensionRegistry.allPresetSpecs.keys
        assertEquals(expected, actual, "Preset registry keys must match CanimationPreset enum entries.")
    }

    @Test
    fun reducedDurationsRespectPolicyAcrossAllPresets() {
        PresetsExtensionRegistry.allPresetSpecs.forEach { (preset, presetSpec) ->
            assertTrue(
                presetSpec.reducedEnter.durationMs in 1..120,
                "$preset reducedEnter duration must be 1..120ms but was ${presetSpec.reducedEnter.durationMs}",
            )
            assertTrue(
                presetSpec.reducedExit.durationMs in 1..120,
                "$preset reducedExit duration must be 1..120ms but was ${presetSpec.reducedExit.durationMs}",
            )
            assertTrue(
                presetSpec.reducedEnter.durationMs <= presetSpec.fullEnter.durationMs,
                "$preset reducedEnter must not be longer than fullEnter (${presetSpec.reducedEnter.durationMs} > ${presetSpec.fullEnter.durationMs})",
            )
            assertTrue(
                presetSpec.reducedExit.durationMs <= presetSpec.fullExit.durationMs,
                "$preset reducedExit must not be longer than fullExit (${presetSpec.reducedExit.durationMs} > ${presetSpec.fullExit.durationMs})",
            )
        }
    }

    @Test
    fun reducedSpecsDoNotAmplifyMotionMagnitude() {
        PresetsExtensionRegistry.allPresetSpecs.forEach { (preset, presetSpec) ->
            assertNoChannelAdded(preset, "enter.offsetX", presetSpec.fullEnter.offsetX, presetSpec.reducedEnter.offsetX)
            assertNoChannelAdded(preset, "enter.offsetY", presetSpec.fullEnter.offsetY, presetSpec.reducedEnter.offsetY)
            assertNoChannelAdded(preset, "enter.rotation", presetSpec.fullEnter.rotation, presetSpec.reducedEnter.rotation)
            assertNoChannelAdded(preset, "enter.scale", presetSpec.fullEnter.scale, presetSpec.reducedEnter.scale)

            assertNoChannelAdded(preset, "exit.offsetX", presetSpec.fullExit.offsetX, presetSpec.reducedExit.offsetX)
            assertNoChannelAdded(preset, "exit.offsetY", presetSpec.fullExit.offsetY, presetSpec.reducedExit.offsetY)
            assertNoChannelAdded(preset, "exit.rotation", presetSpec.fullExit.rotation, presetSpec.reducedExit.rotation)
            assertNoChannelAdded(preset, "exit.scale", presetSpec.fullExit.scale, presetSpec.reducedExit.scale)

            assertReducedDpMagnitudeNotGreater(
                preset = preset,
                channel = "enter.offsetX",
                full = presetSpec.fullEnter.offsetX,
                reduced = presetSpec.reducedEnter.offsetX,
            )
            assertReducedDpMagnitudeNotGreater(
                preset = preset,
                channel = "enter.offsetY",
                full = presetSpec.fullEnter.offsetY,
                reduced = presetSpec.reducedEnter.offsetY,
            )
            assertReducedRotationNotGreater(
                preset = preset,
                channel = "enter.rotation",
                full = presetSpec.fullEnter.rotation,
                reduced = presetSpec.reducedEnter.rotation,
            )
            assertReducedScaleNotGreater(
                preset = preset,
                channel = "enter.scale",
                full = presetSpec.fullEnter.scale,
                reduced = presetSpec.reducedEnter.scale,
            )

            assertReducedDpMagnitudeNotGreater(
                preset = preset,
                channel = "exit.offsetX",
                full = presetSpec.fullExit.offsetX,
                reduced = presetSpec.reducedExit.offsetX,
            )
            assertReducedDpMagnitudeNotGreater(
                preset = preset,
                channel = "exit.offsetY",
                full = presetSpec.fullExit.offsetY,
                reduced = presetSpec.reducedExit.offsetY,
            )
            assertReducedRotationNotGreater(
                preset = preset,
                channel = "exit.rotation",
                full = presetSpec.fullExit.rotation,
                reduced = presetSpec.reducedExit.rotation,
            )
            assertReducedScaleNotGreater(
                preset = preset,
                channel = "exit.scale",
                full = presetSpec.fullExit.scale,
                reduced = presetSpec.reducedExit.scale,
            )

            assertAlphaChannelConsistency(preset, presetSpec.fullEnter, presetSpec.reducedEnter, "enter")
            assertAlphaChannelConsistency(preset, presetSpec.fullExit, presetSpec.reducedExit, "exit")
        }
    }

    private fun assertAlphaChannelConsistency(
        preset: CanimationPreset,
        full: CanimationSpec,
        reduced: CanimationSpec,
        direction: String,
    ) {
        if (full.alpha == null) {
            assertNull(reduced.alpha, "$preset $direction alpha must not be introduced in reduced mode.")
            return
        }
        assertTrue(
            reduced.alpha != null,
            "$preset $direction alpha must remain defined in reduced mode when full mode defines it.",
        )
    }

    private fun assertNoChannelAdded(
        preset: CanimationPreset,
        channel: String,
        full: Any?,
        reduced: Any?,
    ) {
        if (full == null) {
            assertNull(reduced, "$preset $channel must not be introduced in reduced mode.")
        }
    }

    private fun assertReducedDpMagnitudeNotGreater(
        preset: CanimationPreset,
        channel: String,
        full: CanimationDpRange?,
        reduced: CanimationDpRange?,
    ) {
        if (full == null || reduced == null) return
        assertTrue(
            dpMagnitude(reduced) <= dpMagnitude(full) + 0.0001f,
            "$preset $channel reduced magnitude must be <= full magnitude.",
        )
    }

    private fun assertReducedRotationNotGreater(
        preset: CanimationPreset,
        channel: String,
        full: CanimationRange?,
        reduced: CanimationRange?,
    ) {
        if (full == null || reduced == null) return
        assertTrue(
            rangeMagnitude(reduced) <= rangeMagnitude(full) + 0.0001f,
            "$preset $channel reduced magnitude must be <= full magnitude.",
        )
    }

    private fun assertReducedScaleNotGreater(
        preset: CanimationPreset,
        channel: String,
        full: CanimationRange?,
        reduced: CanimationRange?,
    ) {
        if (full == null || reduced == null) return
        assertTrue(
            scaleMagnitude(reduced) <= scaleMagnitude(full) + 0.0001f,
            "$preset $channel reduced deviation from 1f must be <= full deviation.",
        )
    }

    private fun dpMagnitude(range: CanimationDpRange): Float {
        val from = abs(range.from.value)
        val to = abs(range.to.value)
        return maxOf(from, to)
    }

    private fun rangeMagnitude(range: CanimationRange): Float {
        return maxOf(abs(range.from), abs(range.to))
    }

    private fun scaleMagnitude(range: CanimationRange): Float {
        val from = abs(range.from - 1f)
        val to = abs(range.to - 1f)
        return maxOf(from, to)
    }
}

package com.segnities007.canimation.screen.presets

import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationSpec
import io.github.canimation.presets.PresetsExtensionRegistry

internal data class PresetGalleryEntry(
    val preset: CanimationPreset,
    val baseSpec: CanimationPresetSpec,
    val summary: String,
)

internal fun buildPresetGalleryEntries(
    presetSpecs: Map<CanimationPreset, CanimationPresetSpec>,
): List<PresetGalleryEntry> =
    CanimationPreset.entries.map { preset ->
        PresetGalleryEntry(
            preset = preset,
            baseSpec = presetSpecs.getValue(preset),
            summary = PresetsExtensionRegistry.descriptorFor(preset).docs.summary,
        )
    }

internal fun filterPresetGalleryEntries(
    entries: List<PresetGalleryEntry>,
    filter: MotionFilter,
): List<PresetGalleryEntry> =
    if (filter == MotionFilter.All) {
        entries
    } else {
        entries.filter { filter.matches(it.baseSpec.fullEnter) }
    }

internal fun MotionFilter.matches(
    spec: CanimationSpec,
): Boolean =
    when (this) {
        MotionFilter.All -> true
        MotionFilter.Translation -> spec.offsetX != null || spec.offsetY != null
        MotionFilter.Scale -> spec.scale != null
        MotionFilter.Rotation -> spec.rotation != null
        MotionFilter.AlphaOnly -> spec.offsetX == null && spec.offsetY == null && spec.scale == null && spec.rotation == null
    }

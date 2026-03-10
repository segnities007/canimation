package io.github.canimation.presets

import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

internal val DEFAULT_FADE_SPEC = CanimationPresetSpec(
    fullEnter = CanimationSpec(
        durationMs = STANDARD_DURATION_MS,
        easing = standardEasing,
        alpha = CanimationRange(0f, 1f),
    ),
    fullExit = CanimationSpec(
        durationMs = STANDARD_DURATION_MS,
        easing = accelerateEasing,
        alpha = CanimationRange(1f, 0f),
    ),
    reducedEnter = CanimationSpec(
        durationMs = REDUCED_DURATION_MS,
        easing = decelerateEasing,
        alpha = CanimationRange(0f, 1f),
    ),
    reducedExit = CanimationSpec(
        durationMs = REDUCED_DURATION_MS,
        easing = accelerateEasing,
        alpha = CanimationRange(1f, 0f),
    ),
)

internal val defaultPresetSpecsCore: Map<CanimationPreset, CanimationPresetSpec> = mapOf(
    CanimationPreset.FadeUp to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = STANDARD_DURATION_MS,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange(16.dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = STANDARD_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetY = CanimationDpRange(0.dp, 16.dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange(4.dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetY = CanimationDpRange(0.dp, 4.dp),
        ),
    ),
    CanimationPreset.Fade to DEFAULT_FADE_SPEC,
    CanimationPreset.ScaleIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = STANDARD_DURATION_MS,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.92f, 1.0f),
        ),
        fullExit = CanimationSpec(
            durationMs = STANDARD_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.92f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.98f, 1.0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.98f),
        ),
    ),
    CanimationPreset.SlideLeft to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = STANDARD_DURATION_MS,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            offsetX = CanimationDpRange(16.dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = STANDARD_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetX = CanimationDpRange(0.dp, 16.dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            offsetX = CanimationDpRange(4.dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetX = CanimationDpRange(0.dp, 4.dp),
        ),
    ),
    CanimationPreset.SlideRight to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = STANDARD_DURATION_MS,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            offsetX = CanimationDpRange((-16).dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = STANDARD_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetX = CanimationDpRange(0.dp, (-16).dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            offsetX = CanimationDpRange((-4).dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetX = CanimationDpRange(0.dp, (-4).dp),
        ),
    ),
    CanimationPreset.FadeDown to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = STANDARD_DURATION_MS,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange((-16).dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = STANDARD_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetY = CanimationDpRange(0.dp, (-16).dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange((-4).dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetY = CanimationDpRange(0.dp, (-4).dp),
        ),
    ),
    CanimationPreset.ScaleUp to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = STANDARD_DURATION_MS,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(1.08f, 1.0f),
        ),
        fullExit = CanimationSpec(
            durationMs = STANDARD_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 1.08f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(1.02f, 1.0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 1.02f),
        ),
    ),
    CanimationPreset.ZoomIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 300,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.5f, 1.0f),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.5f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.88f, 1.0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.88f),
        ),
    ),
    CanimationPreset.ZoomOut to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 300,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(1.5f, 1.0f),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 1.5f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(1.12f, 1.0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 1.12f),
        ),
    ),
    CanimationPreset.Pop to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 250,
            easing = springEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.6f, 1.0f),
        ),
        fullExit = CanimationSpec(
            durationMs = 200,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.6f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.9f, 1.0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.9f),
        ),
    ),
    CanimationPreset.Expand to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 350,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.0f, 1.0f),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.0f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.75f, 1.0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.75f),
        ),
    ),
    CanimationPreset.SlideUp to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 300,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange(40.dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetY = CanimationDpRange(0.dp, 40.dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange(10.dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetY = CanimationDpRange(0.dp, 10.dp),
        ),
    ),
    CanimationPreset.SlideDown to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 300,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange((-40).dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetY = CanimationDpRange(0.dp, (-40).dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange((-10).dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetY = CanimationDpRange(0.dp, (-10).dp),
        ),
    ),
    CanimationPreset.ElevateIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 200,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.96f, 1.0f),
            offsetY = CanimationDpRange(4.dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 200,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.96f),
            offsetY = CanimationDpRange(0.dp, 4.dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.99f, 1.0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.99f),
        ),
    ),
)

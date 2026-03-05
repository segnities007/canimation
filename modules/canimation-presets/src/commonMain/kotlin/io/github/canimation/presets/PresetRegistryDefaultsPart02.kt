package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

internal val defaultPresetSpecsPart02: Map<CanimationPreset, CanimationPresetSpec> = mapOf(
    CanimationPreset.DropIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 350,
            easing = CubicBezierEasing(0.175f, 0.885f, 0.32f, 1.1f),
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.95f, 1.0f),
            offsetY = CanimationDpRange((-40).dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 250,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.95f),
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
    // Motion.dev-inspired presets: Rotation, Zoom combos, Back easing, etc.
    CanimationPreset.RotateIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 300,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(-90f, 0f),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            rotation = CanimationRange(0f, -90f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(-22f, 0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            rotation = CanimationRange(0f, -22f),
        ),
    ),
    CanimationPreset.RotateClockwise to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 300,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(90f, 0f),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            rotation = CanimationRange(0f, 90f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(22f, 0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            rotation = CanimationRange(0f, 22f),
        ),
    ),
    CanimationPreset.SpinIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 400,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0f, 1.0f),
            rotation = CanimationRange(-360f, 0f),
        ),
        fullExit = CanimationSpec(
            durationMs = 350,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0f),
            rotation = CanimationRange(0f, 360f),
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
    CanimationPreset.FlipIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 350,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(-180f, 0f),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            rotation = CanimationRange(0f, 180f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(-45f, 0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            rotation = CanimationRange(0f, 45f),
        ),
    ),
    CanimationPreset.SwingIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 300,
            easing = springEasing,
            alpha = CanimationRange(0f, 1f),
            rotation = CanimationRange(-15f, 0f),
            offsetY = CanimationDpRange(20.dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 250,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            rotation = CanimationRange(0f, 15f),
            offsetY = CanimationDpRange(0.dp, 20.dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            offsetY = CanimationDpRange(5.dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            offsetY = CanimationDpRange(0.dp, 5.dp),
        ),
    ),
    CanimationPreset.ZoomInUp to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 350,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.6f, 1.0f),
            offsetY = CanimationDpRange(40.dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.6f),
            offsetY = CanimationDpRange(0.dp, 40.dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.9f, 1.0f),
            offsetY = CanimationDpRange(10.dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.9f),
            offsetY = CanimationDpRange(0.dp, 10.dp),
        ),
    ),
    CanimationPreset.ZoomInDown to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 350,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.6f, 1.0f),
            offsetY = CanimationDpRange((-40).dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.6f),
            offsetY = CanimationDpRange(0.dp, (-40).dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.9f, 1.0f),
            offsetY = CanimationDpRange((-10).dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.9f),
            offsetY = CanimationDpRange(0.dp, (-10).dp),
        ),
    ),
    CanimationPreset.ZoomInLeft to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 350,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.6f, 1.0f),
            offsetX = CanimationDpRange((-40).dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.6f),
            offsetX = CanimationDpRange(0.dp, (-40).dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.9f, 1.0f),
            offsetX = CanimationDpRange((-10).dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.9f),
            offsetX = CanimationDpRange(0.dp, (-10).dp),
        ),
    ),
    CanimationPreset.ZoomInRight to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 350,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.6f, 1.0f),
            offsetX = CanimationDpRange(40.dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.6f),
            offsetX = CanimationDpRange(0.dp, 40.dp),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.9f, 1.0f),
            offsetX = CanimationDpRange(10.dp, 0.dp),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.9f),
            offsetX = CanimationDpRange(0.dp, 10.dp),
        ),
    ),
    CanimationPreset.BackInUp to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 350,
            easing = backOutEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.7f, 1.0f),
            offsetY = CanimationDpRange(40.dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = backInEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.7f),
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
    CanimationPreset.BackInDown to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 350,
            easing = backOutEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(0.7f, 1.0f),
            offsetY = CanimationDpRange((-40).dp, 0.dp),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = backInEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 0.7f),
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
    CanimationPreset.ShrinkIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 300,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(2.0f, 1.0f),
        ),
        fullExit = CanimationSpec(
            durationMs = 300,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 2.0f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = decelerateEasing,
            alpha = CanimationRange(0f, 1f),
            scale = CanimationRange(1.25f, 1.0f),
        ),
        reducedExit = CanimationSpec(
            durationMs = REDUCED_DURATION_MS,
            easing = accelerateEasing,
            alpha = CanimationRange(1f, 0f),
            scale = CanimationRange(1.0f, 1.25f),
        ),
    ),
)

package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec

internal val defaultPresetSpecsExpressive: Map<CanimationPreset, CanimationPresetSpec> = mapOf(
    CanimationPreset.GentleFade to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 600,
            easing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f),
            alpha = CanimationRange(0f, 1f),
        ),
        fullExit = CanimationSpec(
            durationMs = 600,
            easing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f),
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
    ),
    CanimationPreset.Snap to CanimationPresetSpec(
        fullEnter = CanimationSpec(
            durationMs = 10,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
        ),
        fullExit = CanimationSpec(
            durationMs = 10,
            easing = standardEasing,
            alpha = CanimationRange(1f, 0f),
        ),
        reducedEnter = CanimationSpec(
            durationMs = 10,
            easing = standardEasing,
            alpha = CanimationRange(0f, 1f),
        ),
        reducedExit = CanimationSpec(
            durationMs = 10,
            easing = standardEasing,
            alpha = CanimationRange(1f, 0f),
        ),
    ),
    // --- Animate.css inspired ---
    CanimationPreset.BounceIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.3f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.3f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
    ),
    CanimationPreset.BounceInDown to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 450, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.7f, 1.0f), offsetY = CanimationDpRange((-60).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.7f), offsetY = CanimationDpRange(0.dp, (-60).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-15).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-15).dp)),
    ),
    CanimationPreset.BounceInLeft to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 450, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.8f, 1.0f), offsetX = CanimationDpRange((-80).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.8f), offsetX = CanimationDpRange(0.dp, (-80).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-20).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-20).dp)),
    ),
    CanimationPreset.BounceInRight to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 450, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.8f, 1.0f), offsetX = CanimationDpRange(80.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.8f), offsetX = CanimationDpRange(0.dp, 80.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(20.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 20.dp)),
    ),
    CanimationPreset.FadeInLeftBig to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 500, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-200).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-200).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-50).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-50).dp)),
    ),
    CanimationPreset.FadeInRightBig to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 500, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(200.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 200.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(50.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 50.dp)),
    ),
    CanimationPreset.LightSpeedInRight to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(200.dp, 0.dp), rotation = CanimationRange(15f, 0f)),
        fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 200.dp), rotation = CanimationRange(0f, 15f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(50.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 50.dp)),
    ),
    CanimationPreset.LightSpeedInLeft to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-200).dp, 0.dp), rotation = CanimationRange(-15f, 0f)),
        fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-200).dp), rotation = CanimationRange(0f, -15f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-50).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-50).dp)),
    ),
    CanimationPreset.JackInTheBox to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 500, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.1f, 1.0f), rotation = CanimationRange(-30f, 0f)),
        fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.1f), rotation = CanimationRange(0f, -30f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.7f, 1.0f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.7f)),
    ),
    CanimationPreset.RollIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 500, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-120).dp, 0.dp), rotation = CanimationRange(-120f, 0f)),
        fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-120).dp), rotation = CanimationRange(0f, -120f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-30).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-30).dp)),
    ),
    // --- Framer Motion inspired ---
    CanimationPreset.SpringIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = springEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.5f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.5f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
    ),
    CanimationPreset.SpringSlideUp to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = springEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(50.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 50.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(12.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 12.dp)),
    ),
    CanimationPreset.SpringFadeIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = springEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f)),
    ),
    // --- AnimXYZ inspired ---
    CanimationPreset.FlipUp to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = standardEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(20.dp, 0.dp), rotation = CanimationRange(45f, 0f)),
        fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 20.dp), rotation = CanimationRange(0f, 45f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(5.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 5.dp)),
    ),
    CanimationPreset.FlipDown to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = standardEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-20).dp, 0.dp), rotation = CanimationRange(-45f, 0f)),
        fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-20).dp), rotation = CanimationRange(0f, -45f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-5).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-5).dp)),
    ),
    CanimationPreset.TiltIn to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.9f, 1.0f), rotation = CanimationRange(10f, 0f)),
        fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.9f), rotation = CanimationRange(0f, 10f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f)),
    ),
    // --- Material Motion inspired ---
    CanimationPreset.FadeThrough to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = emphasizedDecelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.92f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.92f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f)),
    ),
    CanimationPreset.SharedAxisX to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(30.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-30).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(8.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-8).dp)),
    ),
    CanimationPreset.SharedAxisY to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(30.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-30).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(8.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-8).dp)),
    ),
    CanimationPreset.EmphasizedEntry to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 500, easing = emphasizedDecelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f), offsetY = CanimationDpRange(20.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f), offsetY = CanimationDpRange(0.dp, 20.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(5.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 5.dp)),
    ),
    // --- Animate.css remaining directional presets ---
    CanimationPreset.BackInLeft to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = backOutEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.7f, 1.0f), offsetX = CanimationDpRange((-40).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = backInEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.7f), offsetX = CanimationDpRange(0.dp, (-40).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-10).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-10).dp)),
    ),
    CanimationPreset.BackInRight to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = backOutEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.7f, 1.0f), offsetX = CanimationDpRange(40.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = backInEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.7f), offsetX = CanimationDpRange(0.dp, 40.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(10.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 10.dp)),
    ),
    CanimationPreset.BounceInUp to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 450, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.7f, 1.0f), offsetY = CanimationDpRange(60.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.7f), offsetY = CanimationDpRange(0.dp, 60.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(15.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 15.dp)),
    ),
    CanimationPreset.FadeInDownBig to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 500, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-200).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-200).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-50).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-50).dp)),
    ),
    CanimationPreset.FadeInUpBig to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 500, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(200.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 200.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(50.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 50.dp)),
    ),
    CanimationPreset.FadeInLeft to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-40).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-40).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-10).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-10).dp)),
    ),
    CanimationPreset.FadeInRight to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(40.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 40.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(10.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 10.dp)),
    ),
    CanimationPreset.FadeInTopLeft to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-30).dp, 0.dp), offsetY = CanimationDpRange((-30).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-30).dp), offsetY = CanimationDpRange(0.dp, (-30).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-8).dp, 0.dp), offsetY = CanimationDpRange((-8).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-8).dp), offsetY = CanimationDpRange(0.dp, (-8).dp)),
    ),
    CanimationPreset.FadeInTopRight to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(30.dp, 0.dp), offsetY = CanimationDpRange((-30).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 30.dp), offsetY = CanimationDpRange(0.dp, (-30).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(8.dp, 0.dp), offsetY = CanimationDpRange((-8).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 8.dp), offsetY = CanimationDpRange(0.dp, (-8).dp)),
    ),
    CanimationPreset.FadeInBottomLeft to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-30).dp, 0.dp), offsetY = CanimationDpRange(30.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-30).dp), offsetY = CanimationDpRange(0.dp, 30.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-8).dp, 0.dp), offsetY = CanimationDpRange(8.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-8).dp), offsetY = CanimationDpRange(0.dp, 8.dp)),
    ),
    CanimationPreset.FadeInBottomRight to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(30.dp, 0.dp), offsetY = CanimationDpRange(30.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 30.dp), offsetY = CanimationDpRange(0.dp, 30.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(8.dp, 0.dp), offsetY = CanimationDpRange(8.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 8.dp), offsetY = CanimationDpRange(0.dp, 8.dp)),
    ),
    CanimationPreset.RotateInDownLeft to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), rotation = CanimationRange(-45f, 0f), offsetX = CanimationDpRange((-20).dp, 0.dp), offsetY = CanimationDpRange(20.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), rotation = CanimationRange(0f, -45f), offsetX = CanimationDpRange(0.dp, (-20).dp), offsetY = CanimationDpRange(0.dp, 20.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-5).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-5).dp)),
    ),
    CanimationPreset.RotateInDownRight to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), rotation = CanimationRange(45f, 0f), offsetX = CanimationDpRange(20.dp, 0.dp), offsetY = CanimationDpRange(20.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), rotation = CanimationRange(0f, 45f), offsetX = CanimationDpRange(0.dp, 20.dp), offsetY = CanimationDpRange(0.dp, 20.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(5.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 5.dp)),
    ),
    CanimationPreset.RotateInUpLeft to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), rotation = CanimationRange(45f, 0f), offsetX = CanimationDpRange((-20).dp, 0.dp), offsetY = CanimationDpRange((-20).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), rotation = CanimationRange(0f, 45f), offsetX = CanimationDpRange(0.dp, (-20).dp), offsetY = CanimationDpRange(0.dp, (-20).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-5).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-5).dp)),
    ),
    CanimationPreset.RotateInUpRight to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), rotation = CanimationRange(-45f, 0f), offsetX = CanimationDpRange(20.dp, 0.dp), offsetY = CanimationDpRange((-20).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), rotation = CanimationRange(0f, -45f), offsetX = CanimationDpRange(0.dp, 20.dp), offsetY = CanimationDpRange(0.dp, (-20).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(5.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 5.dp)),
    ),
    CanimationPreset.FlipInY to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = standardEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.6f, 1.0f), rotation = CanimationRange(90f, 0f)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.6f), rotation = CanimationRange(0f, 90f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.9f, 1.0f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.9f)),
    ),
    // --- Animate.css attention seekers (from→to approximations) ---
    CanimationPreset.Pulse to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(0.8f, 1f), scale = CanimationRange(1.05f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(1f, 0.8f), scale = CanimationRange(1.0f, 1.05f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
    ),
    CanimationPreset.HeartBeat to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = standardEasing, alpha = CanimationRange(0.7f, 1f), scale = CanimationRange(1.3f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 400, easing = standardEasing, alpha = CanimationRange(1f, 0.7f), scale = CanimationRange(1.0f, 1.3f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
    ),
    CanimationPreset.Tada to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), scale = CanimationRange(1.1f, 1.0f), rotation = CanimationRange(-3f, 0f)),
        fullExit = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), scale = CanimationRange(1.0f, 1.1f), rotation = CanimationRange(0f, 3f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
    ),
    CanimationPreset.Wobble to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), offsetX = CanimationDpRange((-25).dp, 0.dp), rotation = CanimationRange(-5f, 0f)),
        fullExit = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), offsetX = CanimationDpRange(0.dp, 25.dp), rotation = CanimationRange(0f, 5f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
    ),
    CanimationPreset.Swing to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), rotation = CanimationRange(15f, 0f)),
        fullExit = CanimationSpec(durationMs = 350, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), rotation = CanimationRange(0f, -15f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
    ),
    CanimationPreset.RubberBand to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0.7f, 1f), scale = CanimationRange(1.25f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(1f, 0.7f), scale = CanimationRange(1.0f, 1.25f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
    ),
    CanimationPreset.Bounce to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 450, easing = bounceEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-30).dp, 0.dp), scale = CanimationRange(0.9f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-30).dp), scale = CanimationRange(1.0f, 0.9f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-8).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-8).dp)),
    ),
    CanimationPreset.Flash to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 200, easing = standardEasing, alpha = CanimationRange(0f, 1f)),
        fullExit = CanimationSpec(durationMs = 200, easing = standardEasing, alpha = CanimationRange(1f, 0f)),
        reducedEnter = CanimationSpec(durationMs = 100, easing = decelerateEasing, alpha = CanimationRange(0f, 1f)),
        reducedExit = CanimationSpec(durationMs = 100, easing = accelerateEasing, alpha = CanimationRange(1f, 0f)),
    ),
    CanimationPreset.ShakeX to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), offsetX = CanimationDpRange((-10).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), offsetX = CanimationDpRange(0.dp, 10.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
    ),
    CanimationPreset.ShakeY to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), offsetY = CanimationDpRange((-10).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), offsetY = CanimationDpRange(0.dp, 10.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
    ),
    CanimationPreset.HeadShake to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), offsetX = CanimationDpRange((-6).dp, 0.dp), rotation = CanimationRange(-9f, 0f)),
        fullExit = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), offsetX = CanimationDpRange(0.dp, 6.dp), rotation = CanimationRange(0f, 9f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
    ),
    CanimationPreset.Jello to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), rotation = CanimationRange(-12.5f, 0f), scale = CanimationRange(1.0f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), rotation = CanimationRange(0f, 12.5f), scale = CanimationRange(1.0f, 1.0f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
    ),
    // --- AnimXYZ composable combinations ---
    CanimationPreset.FadeSmall to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.5f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.5f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
    ),
    CanimationPreset.FadeBig to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(1.5f, 1.0f)),
        fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 1.5f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(1.15f, 1.0f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 1.15f)),
    ),
    CanimationPreset.FadeUpLeft to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = standardEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-25).dp, 0.dp), offsetY = CanimationDpRange((-25).dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-25).dp), offsetY = CanimationDpRange(0.dp, (-25).dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-6).dp, 0.dp), offsetY = CanimationDpRange((-6).dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-6).dp), offsetY = CanimationDpRange(0.dp, (-6).dp)),
    ),
    CanimationPreset.FadeDownRight to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 350, easing = standardEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(25.dp, 0.dp), offsetY = CanimationDpRange(25.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 25.dp), offsetY = CanimationDpRange(0.dp, 25.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(6.dp, 0.dp), offsetY = CanimationDpRange(6.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 6.dp), offsetY = CanimationDpRange(0.dp, 6.dp)),
    ),
    CanimationPreset.RotateScale to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = standardEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.5f, 1.0f), rotation = CanimationRange(-45f, 0f)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.5f), rotation = CanimationRange(0f, -45f)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
    ),
    CanimationPreset.UpBig to CanimationPresetSpec(
        fullEnter = CanimationSpec(durationMs = 400, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(100.dp, 0.dp)),
        fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 100.dp)),
        reducedEnter = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(25.dp, 0.dp)),
        reducedExit = CanimationSpec(durationMs = REDUCED_DURATION_MS, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 25.dp)),
    ),
)

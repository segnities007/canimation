package io.github.canimation.presets

import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.PresetRegistry

/**
 * Extension utilities for combining additional presets with the default [PresetRegistry].
 *
 * Use [createExtendedRegistry] to produce a registry that includes both built-in and
 * additional presets.
 */
object PresetsExtensionRegistry {

    /**
     * All preset specs defined in the presets module, keyed by [CanimationPreset].
     */
    val allPresetSpecs: Map<CanimationPreset, CanimationPresetSpec> = mapOf(
        CanimationPreset.FadeUp to FadeUpPreset.spec,
        CanimationPreset.Fade to FadePreset.spec,
        CanimationPreset.ScaleIn to ScaleInPreset.spec,
        CanimationPreset.SlideLeft to SlideLeftPreset.spec,
        CanimationPreset.SlideRight to SlideRightPreset.spec,
        CanimationPreset.FadeDown to FadeDownPreset.spec,
        CanimationPreset.ScaleUp to ScaleUpPreset.spec,
        CanimationPreset.ZoomIn to ZoomInPreset.spec,
        CanimationPreset.ZoomOut to ZoomOutPreset.spec,
        CanimationPreset.Pop to PopPreset.spec,
        CanimationPreset.Expand to ExpandPreset.spec,
        CanimationPreset.SlideUp to SlideUpPreset.spec,
        CanimationPreset.SlideDown to SlideDownPreset.spec,
        CanimationPreset.ElevateIn to ElevateInPreset.spec,
        CanimationPreset.DropIn to DropInPreset.spec,
        CanimationPreset.RotateIn to RotateInPreset.spec,
        CanimationPreset.RotateClockwise to RotateClockwisePreset.spec,
        CanimationPreset.SpinIn to SpinInPreset.spec,
        CanimationPreset.FlipIn to FlipInPreset.spec,
        CanimationPreset.SwingIn to SwingInPreset.spec,
        CanimationPreset.ZoomInUp to ZoomInUpPreset.spec,
        CanimationPreset.ZoomInDown to ZoomInDownPreset.spec,
        CanimationPreset.ZoomInLeft to ZoomInLeftPreset.spec,
        CanimationPreset.ZoomInRight to ZoomInRightPreset.spec,
        CanimationPreset.BackInUp to BackInUpPreset.spec,
        CanimationPreset.BackInDown to BackInDownPreset.spec,
        CanimationPreset.ShrinkIn to ShrinkInPreset.spec,
        CanimationPreset.GentleFade to GentleFadePreset.spec,
        CanimationPreset.Snap to SnapPreset.spec,
        // Animate.css inspired
        CanimationPreset.BounceIn to BounceInPreset.spec,
        CanimationPreset.BounceInDown to BounceInDownPreset.spec,
        CanimationPreset.BounceInLeft to BounceInLeftPreset.spec,
        CanimationPreset.BounceInRight to BounceInRightPreset.spec,
        CanimationPreset.FadeInLeftBig to FadeInLeftBigPreset.spec,
        CanimationPreset.FadeInRightBig to FadeInRightBigPreset.spec,
        CanimationPreset.LightSpeedInRight to LightSpeedInRightPreset.spec,
        CanimationPreset.LightSpeedInLeft to LightSpeedInLeftPreset.spec,
        CanimationPreset.JackInTheBox to JackInTheBoxPreset.spec,
        CanimationPreset.RollIn to RollInPreset.spec,
        // Framer Motion inspired
        CanimationPreset.SpringIn to SpringInPreset.spec,
        CanimationPreset.SpringSlideUp to SpringSlideUpPreset.spec,
        CanimationPreset.SpringFadeIn to SpringFadeInPreset.spec,
        // AnimXYZ inspired
        CanimationPreset.FlipUp to FlipUpPreset.spec,
        CanimationPreset.FlipDown to FlipDownPreset.spec,
        CanimationPreset.TiltIn to TiltInPreset.spec,
        // Material Motion inspired
        CanimationPreset.FadeThrough to FadeThroughPreset.spec,
        CanimationPreset.SharedAxisX to SharedAxisXPreset.spec,
        CanimationPreset.SharedAxisY to SharedAxisYPreset.spec,
        CanimationPreset.EmphasizedEntry to EmphasizedEntryPreset.spec,
        // Animate.css remaining directional
        CanimationPreset.BackInLeft to BackInLeftPreset.spec,
        CanimationPreset.BackInRight to BackInRightPreset.spec,
        CanimationPreset.BounceInUp to BounceInUpPreset.spec,
        CanimationPreset.FadeInDownBig to FadeInDownBigPreset.spec,
        CanimationPreset.FadeInUpBig to FadeInUpBigPreset.spec,
        CanimationPreset.FadeInLeft to FadeInLeftPreset.spec,
        CanimationPreset.FadeInRight to FadeInRightPreset.spec,
        CanimationPreset.FadeInTopLeft to FadeInTopLeftPreset.spec,
        CanimationPreset.FadeInTopRight to FadeInTopRightPreset.spec,
        CanimationPreset.FadeInBottomLeft to FadeInBottomLeftPreset.spec,
        CanimationPreset.FadeInBottomRight to FadeInBottomRightPreset.spec,
        CanimationPreset.RotateInDownLeft to RotateInDownLeftPreset.spec,
        CanimationPreset.RotateInDownRight to RotateInDownRightPreset.spec,
        CanimationPreset.RotateInUpLeft to RotateInUpLeftPreset.spec,
        CanimationPreset.RotateInUpRight to RotateInUpRightPreset.spec,
        CanimationPreset.FlipInY to FlipInYPreset.spec,
        // Animate.css attention seekers
        CanimationPreset.Pulse to PulsePreset.spec,
        CanimationPreset.HeartBeat to HeartBeatPreset.spec,
        CanimationPreset.Tada to TadaPreset.spec,
        CanimationPreset.Wobble to WobblePreset.spec,
        CanimationPreset.Swing to SwingPreset.spec,
        CanimationPreset.RubberBand to RubberBandPreset.spec,
        CanimationPreset.Bounce to BouncePreset.spec,
        CanimationPreset.Flash to FlashPreset.spec,
        CanimationPreset.ShakeX to ShakeXPreset.spec,
        CanimationPreset.ShakeY to ShakeYPreset.spec,
        CanimationPreset.HeadShake to HeadShakePreset.spec,
        CanimationPreset.Jello to JelloPreset.spec,
        // AnimXYZ compositions
        CanimationPreset.FadeSmall to FadeSmallPreset.spec,
        CanimationPreset.FadeBig to FadeBigPreset.spec,
        CanimationPreset.FadeUpLeft to FadeUpLeftPreset.spec,
        CanimationPreset.FadeDownRight to FadeDownRightPreset.spec,
        CanimationPreset.RotateScale to RotateScalePreset.spec,
        CanimationPreset.UpBig to UpBigPreset.spec,
    )

    /**
     * Creates an extended [PresetRegistry] that includes both default and additional presets.
     *
     * @param additional Additional presets to register. These override defaults if keys conflict.
     */
    fun createExtendedRegistry(
        additional: Map<CanimationPreset, CanimationPresetSpec> = emptyMap(),
    ): PresetRegistry {
        return PresetRegistry.Default.withOverrides(allPresetSpecs + additional)
    }
}

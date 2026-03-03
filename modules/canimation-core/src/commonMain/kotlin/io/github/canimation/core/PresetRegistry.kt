package io.github.canimation.core

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.dp

/**
 * Registry mapping [CanimationPreset] to their [CanimationPresetSpec].
 *
 * The [Default] instance contains the 15 built-in presets with values from the spec.
 * Users can create custom registries to override or extend preset definitions.
 */
@Stable
class PresetRegistry private constructor(
    private val specs: Map<CanimationPreset, CanimationPresetSpec>,
    private val fallback: PresetRegistry? = null,
) {
    /**
     * Resolves a [CanimationSpec] for the given preset, level, and direction.
     *
     * Falls back to [CanimationPreset.Fade] if the preset is not registered.
     * When level is [CanimationLevel.Off], returns a snap spec (duration=0ms).
     */
    fun resolve(
        preset: CanimationPreset,
        level: CanimationLevel,
        direction: AnimationDirection,
    ): CanimationSpec {
        if (level == CanimationLevel.Off) {
            val base = resolvePresetSpec(preset)
            val spec = when (direction) {
                AnimationDirection.Enter -> base.fullEnter
                AnimationDirection.Exit -> base.fullExit
            }
            return spec.copy(durationMs = 0)
        }

        val presetSpec = resolvePresetSpec(preset)
        return when {
            level == CanimationLevel.Full && direction == AnimationDirection.Enter -> presetSpec.fullEnter
            level == CanimationLevel.Full && direction == AnimationDirection.Exit -> presetSpec.fullExit
            level == CanimationLevel.Reduced && direction == AnimationDirection.Enter -> presetSpec.reducedEnter
            level == CanimationLevel.Reduced && direction == AnimationDirection.Exit -> presetSpec.reducedExit
            else -> presetSpec.fullEnter
        }
    }

    private fun resolvePresetSpec(preset: CanimationPreset): CanimationPresetSpec {
        return specs[preset]
            ?: fallback?.specs?.get(preset)
            ?: specs[CanimationPreset.Fade]
            ?: DEFAULT_FADE_SPEC
    }

    /**
     * Creates a new [PresetRegistry] with additional or overridden preset specs.
     */
    fun withOverrides(overrides: Map<CanimationPreset, CanimationPresetSpec>): PresetRegistry {
        return PresetRegistry(specs = specs + overrides, fallback = fallback)
    }

    /**
     * Creates a new [PresetRegistry] registering a single preset spec.
     */
    fun withPreset(preset: CanimationPreset, spec: CanimationPresetSpec): PresetRegistry {
        return PresetRegistry(specs = specs + (preset to spec), fallback = fallback)
    }

    companion object {
        private val standardEasing = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)
        private val accelerateEasing = CubicBezierEasing(0.3f, 0.0f, 1.0f, 1.0f)
        private val decelerateEasing = CubicBezierEasing(0.0f, 0.0f, 0.0f, 1.0f)
        // Motion.dev backIn/backOut easing curves
        private val backInEasing = CubicBezierEasing(0.36f, 0f, 0.66f, -0.56f)
        private val backOutEasing = CubicBezierEasing(0.34f, 1.56f, 0.64f, 1f)
        // Animate.css bounce-like overshoot easing
        private val bounceEasing = CubicBezierEasing(0.68f, -0.55f, 0.27f, 1.55f)
        // Framer Motion spring-like easing
        private val springEasing = CubicBezierEasing(0.175f, 0.885f, 0.32f, 1.275f)
        // Material Motion emphasized decelerate easing
        private val emphasizedDecelerateEasing = CubicBezierEasing(0.05f, 0.7f, 0.1f, 1.0f)

        private val DEFAULT_FADE_SPEC = CanimationPresetSpec(
            fullEnter = CanimationSpec(
                durationMs = 220,
                easing = standardEasing,
                alpha = CanimationRange(0f, 1f),
            ),
            fullExit = CanimationSpec(
                durationMs = 220,
                easing = accelerateEasing,
                alpha = CanimationRange(1f, 0f),
            ),
            reducedEnter = CanimationSpec(
                durationMs = 120,
                easing = decelerateEasing,
                alpha = CanimationRange(0f, 1f),
            ),
            reducedExit = CanimationSpec(
                durationMs = 120,
                easing = accelerateEasing,
                alpha = CanimationRange(1f, 0f),
            ),
        )

        /**
         * Default registry containing all 5 built-in presets.
         */
        val Default: PresetRegistry = PresetRegistry(
            specs = mapOf(
                CanimationPreset.FadeUp to CanimationPresetSpec(
                    fullEnter = CanimationSpec(
                        durationMs = 220,
                        easing = standardEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetY = CanimationDpRange(16.dp, 0.dp),
                    ),
                    fullExit = CanimationSpec(
                        durationMs = 220,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        offsetY = CanimationDpRange(0.dp, 16.dp),
                    ),
                    reducedEnter = CanimationSpec(
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetY = CanimationDpRange(4.dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        offsetY = CanimationDpRange(0.dp, 4.dp),
                    ),
                ),
                CanimationPreset.Fade to DEFAULT_FADE_SPEC,
                CanimationPreset.ScaleIn to CanimationPresetSpec(
                    fullEnter = CanimationSpec(
                        durationMs = 220,
                        easing = standardEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(0.92f, 1.0f),
                    ),
                    fullExit = CanimationSpec(
                        durationMs = 220,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        scale = CanimationRange(1.0f, 0.92f),
                    ),
                    reducedEnter = CanimationSpec(
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(0.98f, 1.0f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        scale = CanimationRange(1.0f, 0.98f),
                    ),
                ),
                CanimationPreset.SlideLeft to CanimationPresetSpec(
                    fullEnter = CanimationSpec(
                        durationMs = 220,
                        easing = standardEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetX = CanimationDpRange(16.dp, 0.dp),
                    ),
                    fullExit = CanimationSpec(
                        durationMs = 220,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        offsetX = CanimationDpRange(0.dp, 16.dp),
                    ),
                    reducedEnter = CanimationSpec(
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetX = CanimationDpRange(4.dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        offsetX = CanimationDpRange(0.dp, 4.dp),
                    ),
                ),
                CanimationPreset.SlideRight to CanimationPresetSpec(
                    fullEnter = CanimationSpec(
                        durationMs = 220,
                        easing = standardEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetX = CanimationDpRange((-16).dp, 0.dp),
                    ),
                    fullExit = CanimationSpec(
                        durationMs = 220,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        offsetX = CanimationDpRange(0.dp, (-16).dp),
                    ),
                    reducedEnter = CanimationSpec(
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetX = CanimationDpRange((-4).dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        offsetX = CanimationDpRange(0.dp, (-4).dp),
                    ),
                ),
                CanimationPreset.FadeDown to CanimationPresetSpec(
                    fullEnter = CanimationSpec(
                        durationMs = 220,
                        easing = standardEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetY = CanimationDpRange((-16).dp, 0.dp),
                    ),
                    fullExit = CanimationSpec(
                        durationMs = 220,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        offsetY = CanimationDpRange(0.dp, (-16).dp),
                    ),
                    reducedEnter = CanimationSpec(
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetY = CanimationDpRange((-4).dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        offsetY = CanimationDpRange(0.dp, (-4).dp),
                    ),
                ),
                CanimationPreset.ScaleUp to CanimationPresetSpec(
                    fullEnter = CanimationSpec(
                        durationMs = 220,
                        easing = standardEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(1.08f, 1.0f),
                    ),
                    fullExit = CanimationSpec(
                        durationMs = 220,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        scale = CanimationRange(1.0f, 1.08f),
                    ),
                    reducedEnter = CanimationSpec(
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(1.02f, 1.0f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(0.88f, 1.0f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(1.12f, 1.0f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        scale = CanimationRange(1.0f, 1.12f),
                    ),
                ),
                CanimationPreset.Pop to CanimationPresetSpec(
                    fullEnter = CanimationSpec(
                        durationMs = 250,
                        easing = CubicBezierEasing(0.175f, 0.885f, 0.32f, 1.275f),
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(0.9f, 1.0f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(0.75f, 1.0f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetY = CanimationDpRange(10.dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetY = CanimationDpRange((-10).dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(0.99f, 1.0f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        scale = CanimationRange(1.0f, 0.99f),
                    ),
                ),
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetY = CanimationDpRange((-10).dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        rotation = CanimationRange(-22f, 0f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        rotation = CanimationRange(22f, 0f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(0.75f, 1.0f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        rotation = CanimationRange(-45f, 0f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        rotation = CanimationRange(0f, 45f),
                    ),
                ),
                CanimationPreset.SwingIn to CanimationPresetSpec(
                    fullEnter = CanimationSpec(
                        durationMs = 300,
                        easing = CubicBezierEasing(0.175f, 0.885f, 0.32f, 1.275f),
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetY = CanimationDpRange(5.dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(0.9f, 1.0f),
                        offsetY = CanimationDpRange(10.dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(0.9f, 1.0f),
                        offsetY = CanimationDpRange((-10).dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(0.9f, 1.0f),
                        offsetX = CanimationDpRange((-10).dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(0.9f, 1.0f),
                        offsetX = CanimationDpRange(10.dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetY = CanimationDpRange(10.dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        offsetY = CanimationDpRange((-10).dp, 0.dp),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
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
                        durationMs = 120,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                        scale = CanimationRange(1.25f, 1.0f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 120,
                        easing = accelerateEasing,
                        alpha = CanimationRange(1f, 0f),
                        scale = CanimationRange(1.0f, 1.25f),
                    ),
                ),
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
                        durationMs = 200,
                        easing = decelerateEasing,
                        alpha = CanimationRange(0f, 1f),
                    ),
                    reducedExit = CanimationSpec(
                        durationMs = 200,
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
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
                ),
                CanimationPreset.BounceInDown to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 450, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.7f, 1.0f), offsetY = CanimationDpRange((-60).dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.7f), offsetY = CanimationDpRange(0.dp, (-60).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-15).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-15).dp)),
                ),
                CanimationPreset.BounceInLeft to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 450, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.8f, 1.0f), offsetX = CanimationDpRange((-80).dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.8f), offsetX = CanimationDpRange(0.dp, (-80).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-20).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-20).dp)),
                ),
                CanimationPreset.BounceInRight to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 450, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.8f, 1.0f), offsetX = CanimationDpRange(80.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.8f), offsetX = CanimationDpRange(0.dp, 80.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(20.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 20.dp)),
                ),
                CanimationPreset.FadeInLeftBig to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 500, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-200).dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-200).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-50).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-50).dp)),
                ),
                CanimationPreset.FadeInRightBig to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 500, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(200.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 200.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(50.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 50.dp)),
                ),
                CanimationPreset.LightSpeedInRight to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 300, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(200.dp, 0.dp), rotation = CanimationRange(15f, 0f)),
                    fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 200.dp), rotation = CanimationRange(0f, 15f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(50.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 50.dp)),
                ),
                CanimationPreset.LightSpeedInLeft to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 300, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-200).dp, 0.dp), rotation = CanimationRange(-15f, 0f)),
                    fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-200).dp), rotation = CanimationRange(0f, -15f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-50).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-50).dp)),
                ),
                CanimationPreset.JackInTheBox to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 500, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.1f, 1.0f), rotation = CanimationRange(-30f, 0f)),
                    fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.1f), rotation = CanimationRange(0f, -30f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.7f, 1.0f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.7f)),
                ),
                CanimationPreset.RollIn to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 500, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-120).dp, 0.dp), rotation = CanimationRange(-120f, 0f)),
                    fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-120).dp), rotation = CanimationRange(0f, -120f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-30).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-30).dp)),
                ),
                // --- Framer Motion inspired ---
                CanimationPreset.SpringIn to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = springEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.5f, 1.0f)),
                    fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.5f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
                ),
                CanimationPreset.SpringSlideUp to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = springEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(50.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 50.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(12.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 12.dp)),
                ),
                CanimationPreset.SpringFadeIn to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 300, easing = springEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
                    fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f)),
                ),
                // --- AnimXYZ inspired ---
                CanimationPreset.FlipUp to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = standardEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(20.dp, 0.dp), rotation = CanimationRange(45f, 0f)),
                    fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 20.dp), rotation = CanimationRange(0f, 45f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(5.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 5.dp)),
                ),
                CanimationPreset.FlipDown to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = standardEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-20).dp, 0.dp), rotation = CanimationRange(-45f, 0f)),
                    fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-20).dp), rotation = CanimationRange(0f, -45f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-5).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-5).dp)),
                ),
                CanimationPreset.TiltIn to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.9f, 1.0f), rotation = CanimationRange(10f, 0f)),
                    fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.9f), rotation = CanimationRange(0f, 10f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f)),
                ),
                // --- Material Motion inspired ---
                CanimationPreset.FadeThrough to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 300, easing = emphasizedDecelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.92f, 1.0f)),
                    fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.92f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f)),
                ),
                CanimationPreset.SharedAxisX to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(30.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-30).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(8.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-8).dp)),
                ),
                CanimationPreset.SharedAxisY to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(30.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-30).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(8.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-8).dp)),
                ),
                CanimationPreset.EmphasizedEntry to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 500, easing = emphasizedDecelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f), offsetY = CanimationDpRange(20.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f), offsetY = CanimationDpRange(0.dp, 20.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(5.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 5.dp)),
                ),
                // --- Animate.css remaining directional presets ---
                CanimationPreset.BackInLeft to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = backOutEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.7f, 1.0f), offsetX = CanimationDpRange((-40).dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 300, easing = backInEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.7f), offsetX = CanimationDpRange(0.dp, (-40).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-10).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-10).dp)),
                ),
                CanimationPreset.BackInRight to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = backOutEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.7f, 1.0f), offsetX = CanimationDpRange(40.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 300, easing = backInEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.7f), offsetX = CanimationDpRange(0.dp, 40.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(10.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 10.dp)),
                ),
                CanimationPreset.BounceInUp to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 450, easing = bounceEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.7f, 1.0f), offsetY = CanimationDpRange(60.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.7f), offsetY = CanimationDpRange(0.dp, 60.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(15.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 15.dp)),
                ),
                CanimationPreset.FadeInDownBig to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 500, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-200).dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-200).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-50).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-50).dp)),
                ),
                CanimationPreset.FadeInUpBig to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 500, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(200.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 400, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 200.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(50.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 50.dp)),
                ),
                CanimationPreset.FadeInLeft to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 300, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-40).dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-40).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-10).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-10).dp)),
                ),
                CanimationPreset.FadeInRight to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 300, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(40.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 40.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(10.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 10.dp)),
                ),
                CanimationPreset.FadeInTopLeft to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-30).dp, 0.dp), offsetY = CanimationDpRange((-30).dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-30).dp), offsetY = CanimationDpRange(0.dp, (-30).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-8).dp, 0.dp), offsetY = CanimationDpRange((-8).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-8).dp), offsetY = CanimationDpRange(0.dp, (-8).dp)),
                ),
                CanimationPreset.FadeInTopRight to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(30.dp, 0.dp), offsetY = CanimationDpRange((-30).dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 30.dp), offsetY = CanimationDpRange(0.dp, (-30).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(8.dp, 0.dp), offsetY = CanimationDpRange((-8).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 8.dp), offsetY = CanimationDpRange(0.dp, (-8).dp)),
                ),
                CanimationPreset.FadeInBottomLeft to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-30).dp, 0.dp), offsetY = CanimationDpRange(30.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-30).dp), offsetY = CanimationDpRange(0.dp, 30.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-8).dp, 0.dp), offsetY = CanimationDpRange(8.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-8).dp), offsetY = CanimationDpRange(0.dp, 8.dp)),
                ),
                CanimationPreset.FadeInBottomRight to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(30.dp, 0.dp), offsetY = CanimationDpRange(30.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 30.dp), offsetY = CanimationDpRange(0.dp, 30.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(8.dp, 0.dp), offsetY = CanimationDpRange(8.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 8.dp), offsetY = CanimationDpRange(0.dp, 8.dp)),
                ),
                CanimationPreset.RotateInDownLeft to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), rotation = CanimationRange(-45f, 0f), offsetX = CanimationDpRange((-20).dp, 0.dp), offsetY = CanimationDpRange(20.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), rotation = CanimationRange(0f, -45f), offsetX = CanimationDpRange(0.dp, (-20).dp), offsetY = CanimationDpRange(0.dp, 20.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-5).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-5).dp)),
                ),
                CanimationPreset.RotateInDownRight to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), rotation = CanimationRange(45f, 0f), offsetX = CanimationDpRange(20.dp, 0.dp), offsetY = CanimationDpRange(20.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), rotation = CanimationRange(0f, 45f), offsetX = CanimationDpRange(0.dp, 20.dp), offsetY = CanimationDpRange(0.dp, 20.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(5.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 5.dp)),
                ),
                CanimationPreset.RotateInUpLeft to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), rotation = CanimationRange(45f, 0f), offsetX = CanimationDpRange((-20).dp, 0.dp), offsetY = CanimationDpRange((-20).dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), rotation = CanimationRange(0f, 45f), offsetX = CanimationDpRange(0.dp, (-20).dp), offsetY = CanimationDpRange(0.dp, (-20).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-5).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-5).dp)),
                ),
                CanimationPreset.RotateInUpRight to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), rotation = CanimationRange(-45f, 0f), offsetX = CanimationDpRange(20.dp, 0.dp), offsetY = CanimationDpRange((-20).dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), rotation = CanimationRange(0f, -45f), offsetX = CanimationDpRange(0.dp, 20.dp), offsetY = CanimationDpRange(0.dp, (-20).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(5.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 5.dp)),
                ),
                CanimationPreset.FlipInY to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = standardEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.6f, 1.0f), rotation = CanimationRange(90f, 0f)),
                    fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.6f), rotation = CanimationRange(0f, 90f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.9f, 1.0f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.9f)),
                ),
                // --- Animate.css attention seekers (from→to approximations) ---
                CanimationPreset.Pulse to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(0.8f, 1f), scale = CanimationRange(1.05f, 1.0f)),
                    fullExit = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(1f, 0.8f), scale = CanimationRange(1.0f, 1.05f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
                ),
                CanimationPreset.HeartBeat to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = standardEasing, alpha = CanimationRange(0.7f, 1f), scale = CanimationRange(1.3f, 1.0f)),
                    fullExit = CanimationSpec(durationMs = 400, easing = standardEasing, alpha = CanimationRange(1f, 0.7f), scale = CanimationRange(1.0f, 1.3f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
                ),
                CanimationPreset.Tada to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), scale = CanimationRange(1.1f, 1.0f), rotation = CanimationRange(-3f, 0f)),
                    fullExit = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), scale = CanimationRange(1.0f, 1.1f), rotation = CanimationRange(0f, 3f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
                ),
                CanimationPreset.Wobble to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), offsetX = CanimationDpRange((-25).dp, 0.dp), rotation = CanimationRange(-5f, 0f)),
                    fullExit = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), offsetX = CanimationDpRange(0.dp, 25.dp), rotation = CanimationRange(0f, 5f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
                ),
                CanimationPreset.Swing to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), rotation = CanimationRange(15f, 0f)),
                    fullExit = CanimationSpec(durationMs = 350, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), rotation = CanimationRange(0f, -15f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
                ),
                CanimationPreset.RubberBand to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0.7f, 1f), scale = CanimationRange(1.25f, 1.0f)),
                    fullExit = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(1f, 0.7f), scale = CanimationRange(1.0f, 1.25f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
                ),
                CanimationPreset.Bounce to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 450, easing = bounceEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-30).dp, 0.dp), scale = CanimationRange(0.9f, 1.0f)),
                    fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-30).dp), scale = CanimationRange(1.0f, 0.9f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange((-8).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, (-8).dp)),
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
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
                ),
                CanimationPreset.ShakeY to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), offsetY = CanimationDpRange((-10).dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 350, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), offsetY = CanimationDpRange(0.dp, 10.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
                ),
                CanimationPreset.HeadShake to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), offsetX = CanimationDpRange((-6).dp, 0.dp), rotation = CanimationRange(-9f, 0f)),
                    fullExit = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), offsetX = CanimationDpRange(0.dp, 6.dp), rotation = CanimationRange(0f, 9f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
                ),
                CanimationPreset.Jello to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(0.8f, 1f), rotation = CanimationRange(-12.5f, 0f), scale = CanimationRange(1.0f, 1.0f)),
                    fullExit = CanimationSpec(durationMs = 400, easing = bounceEasing, alpha = CanimationRange(1f, 0.8f), rotation = CanimationRange(0f, 12.5f), scale = CanimationRange(1.0f, 1.0f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0.9f, 1f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0.9f)),
                ),
                // --- AnimXYZ composable combinations ---
                CanimationPreset.FadeSmall to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.5f, 1.0f)),
                    fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.5f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
                ),
                CanimationPreset.FadeBig to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 300, easing = standardEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(1.5f, 1.0f)),
                    fullExit = CanimationSpec(durationMs = 250, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 1.5f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(1.15f, 1.0f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 1.15f)),
                ),
                CanimationPreset.FadeUpLeft to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = standardEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-25).dp, 0.dp), offsetY = CanimationDpRange((-25).dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-25).dp), offsetY = CanimationDpRange(0.dp, (-25).dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange((-6).dp, 0.dp), offsetY = CanimationDpRange((-6).dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, (-6).dp), offsetY = CanimationDpRange(0.dp, (-6).dp)),
                ),
                CanimationPreset.FadeDownRight to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 350, easing = standardEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(25.dp, 0.dp), offsetY = CanimationDpRange(25.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 300, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 25.dp), offsetY = CanimationDpRange(0.dp, 25.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetX = CanimationDpRange(6.dp, 0.dp), offsetY = CanimationDpRange(6.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetX = CanimationDpRange(0.dp, 6.dp), offsetY = CanimationDpRange(0.dp, 6.dp)),
                ),
                CanimationPreset.RotateScale to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = standardEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.5f, 1.0f), rotation = CanimationRange(-45f, 0f)),
                    fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.5f), rotation = CanimationRange(0f, -45f)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), scale = CanimationRange(0.85f, 1.0f)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), scale = CanimationRange(1.0f, 0.85f)),
                ),
                CanimationPreset.UpBig to CanimationPresetSpec(
                    fullEnter = CanimationSpec(durationMs = 400, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(100.dp, 0.dp)),
                    fullExit = CanimationSpec(durationMs = 350, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 100.dp)),
                    reducedEnter = CanimationSpec(durationMs = 120, easing = decelerateEasing, alpha = CanimationRange(0f, 1f), offsetY = CanimationDpRange(25.dp, 0.dp)),
                    reducedExit = CanimationSpec(durationMs = 120, easing = accelerateEasing, alpha = CanimationRange(1f, 0f), offsetY = CanimationDpRange(0.dp, 25.dp)),
                ),
            ),
        )

        /**
         * Creates a new [PresetRegistry] from the given specs, falling back to [Default].
         */
        fun create(specs: Map<CanimationPreset, CanimationPresetSpec>): PresetRegistry {
            return PresetRegistry(specs = specs, fallback = Default)
        }
    }
}

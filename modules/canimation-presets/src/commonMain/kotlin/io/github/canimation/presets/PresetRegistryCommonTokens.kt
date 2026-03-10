package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import io.github.canimation.core.DurationTokens
import io.github.canimation.core.EasingTokens

internal val standardEasing = EasingTokens.Default.standard
internal val accelerateEasing = EasingTokens.Default.accelerate
internal val decelerateEasing = EasingTokens.Default.decelerate

internal val backInEasing = CubicBezierEasing(0.36f, 0f, 0.66f, -0.56f)
internal val backOutEasing = CubicBezierEasing(0.34f, 1.56f, 0.64f, 1f)
internal val bounceEasing = CubicBezierEasing(0.68f, -0.55f, 0.27f, 1.55f)
internal val springEasing = CubicBezierEasing(0.175f, 0.885f, 0.32f, 1.275f)
internal val emphasizedDecelerateEasing = EasingTokens.Default.emphasizedDecelerate

internal val REDUCED_DURATION_MS = DurationTokens.Default.shortMs
internal val STANDARD_DURATION_MS = DurationTokens.Default.mediumMs

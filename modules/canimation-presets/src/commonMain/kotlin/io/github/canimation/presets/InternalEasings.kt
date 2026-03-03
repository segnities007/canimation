package io.github.canimation.presets

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing

/**
 * Shared easing curves used by all preset definitions.
 * Values match Material Design 3 Motion specification.
 */
internal object InternalEasings {
    val standard: Easing = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)
    val accelerate: Easing = CubicBezierEasing(0.3f, 0.0f, 1.0f, 1.0f)
    val decelerate: Easing = CubicBezierEasing(0.0f, 0.0f, 0.0f, 1.0f)
}

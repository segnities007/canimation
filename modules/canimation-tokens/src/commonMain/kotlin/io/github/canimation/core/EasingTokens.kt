package io.github.canimation.core

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable

/**
 * Easing tokens for animation curves.
 *
 * Values follow Material Design 3 emphasized motion specification.
 * Enter animations use [emphasizedDecelerate], exit animations use [emphasizedAccelerate].
 */
@Immutable
data class EasingTokens(
    val standard: Easing,
    val emphasizedDecelerate: Easing,
    val emphasizedAccelerate: Easing,
    val decelerate: Easing,
    val accelerate: Easing,
) {
    companion object {
        val Default =
            EasingTokens(
                standard = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f),
                emphasizedDecelerate = CubicBezierEasing(0.05f, 0.7f, 0.1f, 1.0f),
                emphasizedAccelerate = CubicBezierEasing(0.3f, 0.0f, 0.8f, 0.15f),
                decelerate = CubicBezierEasing(0.0f, 0.0f, 0.0f, 1.0f),
                accelerate = CubicBezierEasing(0.3f, 0.0f, 1.0f, 1.0f),
            )
    }
}

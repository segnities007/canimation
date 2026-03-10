package io.github.canimation.core

import androidx.compose.runtime.Immutable

/**
 * Aggregated animation token set providing consistent motion values across the application.
 *
 * Use [CanimationTokens.Default] for the standard Material Design 3 aligned values,
 * or create a custom instance to override specific token categories.
 */
@Immutable
data class CanimationTokens(
    val duration: DurationTokens,
    val easing: EasingTokens,
    val distance: DistanceTokens,
    val spring: SpringTokens,
) {
    companion object {
        val Default =
            CanimationTokens(
                duration = DurationTokens.Default,
                easing = EasingTokens.Default,
                distance = DistanceTokens.Default,
                spring = SpringTokens.Default,
            )
    }
}

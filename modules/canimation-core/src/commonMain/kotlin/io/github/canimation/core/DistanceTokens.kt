package io.github.canimation.core

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Distance tokens for animation translation amounts.
 */
@Immutable
data class DistanceTokens(
    val small: Dp,
    val medium: Dp,
    val large: Dp,
) {
    companion object {
        val Default = DistanceTokens(
            small = 8.dp,
            medium = 16.dp,
            large = 24.dp,
        )
    }
}

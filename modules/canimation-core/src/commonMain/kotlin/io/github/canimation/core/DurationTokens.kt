package io.github.canimation.core

import androidx.compose.runtime.Immutable
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Duration tokens for animation timing.
 *
 * Values follow Material Design 3 motion duration scale.
 */
@Immutable
data class DurationTokens(
    val short: Duration,
    val medium: Duration,
    val long: Duration,
) {
    companion object {
        val Default = DurationTokens(
            short = 120.milliseconds,
            medium = 220.milliseconds,
            long = 360.milliseconds,
        )
    }
}

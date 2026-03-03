package io.github.canimation.core

import androidx.compose.runtime.Immutable

/**
 * Holds the resolved animation context used by all canimation APIs.
 *
 * @property tokens The resolved animation tokens.
 * @property level The current animation level (Full/Reduced/Off).
 * @property presetRegistry The registry used to resolve preset specs.
 * @property timeSourceMillis Provider for current time in milliseconds (for testability).
 */
@Immutable
data class CanimationContext(
    val tokens: CanimationTokens,
    val level: CanimationLevel,
    val presetRegistry: PresetRegistry,
    val timeSourceMillis: () -> Long = { currentTimeMillis() },
) {
    companion object {
        /**
         * Safe default context used when [CanimationProvider] is not configured.
         * Animations work normally with default tokens and full motion.
         */
        val Default = CanimationContext(
            tokens = CanimationTokens.Default,
            level = CanimationLevel.Full,
            presetRegistry = PresetRegistry.Default,
        )
    }
}

// Using a platform-agnostic time source instead of System.currentTimeMillis()
internal expect fun currentTimeMillis(): Long

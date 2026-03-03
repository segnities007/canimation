package io.github.canimation.core

/**
 * Resolves a [CanimationLevel] from a [CanimationPolicy] and the system's reduce-motion preference.
 *
 * When the system preference cannot be determined, defaults to [CanimationLevel.Reduced] (safe fallback).
 */
internal object CanimationPolicyResolver {

    /**
     * @param policy The configured animation policy.
     * @param systemReducedMotion Whether the OS has reduce-motion enabled.
     *   `null` indicates the preference could not be determined.
     */
    fun resolve(policy: CanimationPolicy, systemReducedMotion: Boolean?): CanimationLevel {
        return when (policy) {
            is CanimationPolicy.AlwaysFull -> CanimationLevel.Full
            is CanimationPolicy.AlwaysReduced -> CanimationLevel.Reduced
            is CanimationPolicy.AlwaysOff -> CanimationLevel.Off
            is CanimationPolicy.SystemAware -> {
                when (systemReducedMotion) {
                    true -> CanimationLevel.Reduced
                    false -> CanimationLevel.Full
                    null -> CanimationLevel.Reduced // safe fallback
                }
            }
        }
    }
}

package io.github.canimation.a11y

import io.github.canimation.core.CanimationLevel
import io.github.canimation.core.CanimationPolicy

/**
 * Adapts [SystemMotionPreference] and [CanimationPolicy] into a resolved [CanimationLevel].
 *
 * This provides the bridge between OS accessibility settings and the canimation system.
 */
object A11yMotionPolicyAdapter {

    /**
     * Converts a [SystemMotionPreference] to the `systemReducedMotion` boolean
     * expected by the core policy resolver.
     */
    fun toReducedMotionFlag(preference: SystemMotionPreference): Boolean {
        return when (preference) {
            SystemMotionPreference.ReduceMotion -> true
            SystemMotionPreference.NoPreference -> false
        }
    }

    /**
     * Resolves a [CanimationLevel] from policy and system preference.
     */
    fun resolve(policy: CanimationPolicy, preference: SystemMotionPreference): CanimationLevel {
        return when (policy) {
            is CanimationPolicy.AlwaysFull -> CanimationLevel.Full
            is CanimationPolicy.AlwaysReduced -> CanimationLevel.Reduced
            is CanimationPolicy.AlwaysOff -> CanimationLevel.Off
            is CanimationPolicy.SystemAware -> {
                when (preference) {
                    SystemMotionPreference.ReduceMotion -> CanimationLevel.Reduced
                    SystemMotionPreference.NoPreference -> CanimationLevel.Full
                }
            }
        }
    }
}

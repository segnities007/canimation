package io.github.canimation.a11y

import kotlinx.coroutines.flow.Flow

/**
 * Contract for platform-specific motion preference data sources.
 *
 * Each platform module provides an `actual` implementation that reads the OS accessibility setting.
 */
interface MotionPreferenceDataSource {
    /**
     * Returns the current system motion preference.
     *
     * If the preference cannot be determined, returns [SystemMotionPreference.ReduceMotion]
     * as a safe fallback.
     */
    fun currentPreference(): SystemMotionPreference

    /**
     * Observes changes to the system motion preference.
     *
     * Implementations should emit immediately with the current value, then on each change.
     */
    fun observePreference(): Flow<SystemMotionPreference>
}

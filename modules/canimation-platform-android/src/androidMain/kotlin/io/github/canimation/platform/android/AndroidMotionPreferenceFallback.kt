package io.github.canimation.platform.android

import io.github.canimation.a11y.MotionPreferenceDataSource
import io.github.canimation.a11y.SystemMotionPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Fallback implementation that returns [SystemMotionPreference.ReduceMotion]
 * when the actual Android motion preference cannot be determined.
 */
object AndroidMotionPreferenceFallback : MotionPreferenceDataSource {
    override fun currentPreference(): SystemMotionPreference = SystemMotionPreference.ReduceMotion
    override fun observePreference(): Flow<SystemMotionPreference> =
        flowOf(SystemMotionPreference.ReduceMotion)
}

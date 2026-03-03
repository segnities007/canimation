package io.github.canimation.platform.ios

import io.github.canimation.a11y.MotionPreferenceDataSource
import io.github.canimation.a11y.SystemMotionPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Fallback implementation for iOS that returns [SystemMotionPreference.ReduceMotion].
 */
object IosMotionPreferenceFallback : MotionPreferenceDataSource {
    override fun currentPreference(): SystemMotionPreference = SystemMotionPreference.ReduceMotion
    override fun observePreference(): Flow<SystemMotionPreference> =
        flowOf(SystemMotionPreference.ReduceMotion)
}

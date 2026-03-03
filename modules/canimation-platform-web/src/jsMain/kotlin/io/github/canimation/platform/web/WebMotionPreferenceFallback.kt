package io.github.canimation.platform.web

import io.github.canimation.a11y.MotionPreferenceDataSource
import io.github.canimation.a11y.SystemMotionPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Fallback implementation for Web that returns [SystemMotionPreference.ReduceMotion].
 */
object WebMotionPreferenceFallback : MotionPreferenceDataSource {
    override fun currentPreference(): SystemMotionPreference = SystemMotionPreference.ReduceMotion
    override fun observePreference(): Flow<SystemMotionPreference> =
        flowOf(SystemMotionPreference.ReduceMotion)
}

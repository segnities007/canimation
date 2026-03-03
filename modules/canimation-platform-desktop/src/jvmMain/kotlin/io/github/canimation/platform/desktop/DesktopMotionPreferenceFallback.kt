package io.github.canimation.platform.desktop

import io.github.canimation.a11y.MotionPreferenceDataSource
import io.github.canimation.a11y.SystemMotionPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Fallback implementation for Desktop that returns [SystemMotionPreference.ReduceMotion].
 */
object DesktopMotionPreferenceFallback : MotionPreferenceDataSource {
    override fun currentPreference(): SystemMotionPreference = SystemMotionPreference.ReduceMotion
    override fun observePreference(): Flow<SystemMotionPreference> =
        flowOf(SystemMotionPreference.ReduceMotion)
}

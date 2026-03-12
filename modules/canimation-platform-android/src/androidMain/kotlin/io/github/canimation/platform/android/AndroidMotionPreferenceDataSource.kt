package io.github.canimation.platform.android

import android.content.Context
import android.provider.Settings
import io.github.canimation.a11y.MotionPreferenceDataSource
import io.github.canimation.a11y.SystemMotionPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Android implementation of [MotionPreferenceDataSource].
 *
 * Reads the system's "Remove animations" / "Animator duration scale" setting.
 */
class AndroidMotionPreferenceDataSource(
    private val context: Context,
) : MotionPreferenceDataSource {

    private val preferenceFlow = MutableStateFlow(readPreference())

    override fun currentPreference(): SystemMotionPreference = readPreference()

    override fun observePreference(): Flow<SystemMotionPreference> = preferenceFlow

    private fun readPreference(): SystemMotionPreference {
        val scale = Settings.Global.getString(
            context.contentResolver,
            Settings.Global.ANIMATOR_DURATION_SCALE,
        )?.toFloatOrNull() ?: 1.0f
        return if (scale == 0f) {
            SystemMotionPreference.ReduceMotion
        } else {
            SystemMotionPreference.NoPreference
        }
    }

    /**
     * Call this when the system settings change to update observers.
     */
    fun refresh() {
        preferenceFlow.value = readPreference()
    }
}

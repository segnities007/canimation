package io.github.canimation.platform.ios

import io.github.canimation.a11y.MotionPreferenceDataSource
import io.github.canimation.a11y.SystemMotionPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import platform.UIKit.UIAccessibilityIsReduceMotionEnabled

/**
 * iOS implementation of [MotionPreferenceDataSource].
 *
 * Reads `UIAccessibility.isReduceMotionEnabled` from UIKit.
 */
class IosMotionPreferenceDataSource : MotionPreferenceDataSource {

    private val preferenceFlow = MutableStateFlow(readPreference())

    override fun currentPreference(): SystemMotionPreference = readPreference()

    override fun observePreference(): Flow<SystemMotionPreference> = preferenceFlow

    private fun readPreference(): SystemMotionPreference {
        return try {
            if (UIAccessibilityIsReduceMotionEnabled()) {
                SystemMotionPreference.ReduceMotion
            } else {
                SystemMotionPreference.NoPreference
            }
        } catch (_: Exception) {
            SystemMotionPreference.ReduceMotion
        }
    }

    /** Call when accessibility settings change to update observers. */
    fun refresh() {
        preferenceFlow.value = readPreference()
    }
}

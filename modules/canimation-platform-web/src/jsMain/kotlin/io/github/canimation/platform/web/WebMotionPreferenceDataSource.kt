package io.github.canimation.platform.web

import io.github.canimation.a11y.MotionPreferenceDataSource
import io.github.canimation.a11y.SystemMotionPreference
import kotlinx.browser.window
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Web (JS) implementation of [MotionPreferenceDataSource].
 *
 * Uses the `prefers-reduced-motion` CSS media query to detect the user's preference.
 */
class WebMotionPreferenceDataSource : MotionPreferenceDataSource {

    private val preferenceFlow = MutableStateFlow(readPreference())

    init {
        try {
            val mediaQuery = window.matchMedia("(prefers-reduced-motion: reduce)")
            mediaQuery.addEventListener("change", {
                preferenceFlow.value = readPreference()
            })
        } catch (_: Exception) {
            // Ignore if matchMedia is not available
        }
    }

    override fun currentPreference(): SystemMotionPreference = readPreference()

    override fun observePreference(): Flow<SystemMotionPreference> = preferenceFlow

    private fun readPreference(): SystemMotionPreference {
        return try {
            val matches = window.matchMedia("(prefers-reduced-motion: reduce)").matches
            if (matches) {
                SystemMotionPreference.ReduceMotion
            } else {
                SystemMotionPreference.NoPreference
            }
        } catch (_: Exception) {
            SystemMotionPreference.ReduceMotion
        }
    }
}

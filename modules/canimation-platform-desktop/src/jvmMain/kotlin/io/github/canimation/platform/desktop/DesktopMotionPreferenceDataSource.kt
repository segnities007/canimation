package io.github.canimation.platform.desktop

import io.github.canimation.a11y.MotionPreferenceDataSource
import io.github.canimation.a11y.SystemMotionPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.awt.HeadlessException
import java.awt.Toolkit

/**
 * Desktop (JVM) implementation of [MotionPreferenceDataSource].
 *
 * Checks the system's desktop accessibility settings where available.
 * Falls back to [SystemMotionPreference.NoPreference] on most desktop platforms.
 */
class DesktopMotionPreferenceDataSource : MotionPreferenceDataSource {

    private val preferenceFlow = MutableStateFlow(readPreference())

    override fun currentPreference(): SystemMotionPreference = readPreference()

    override fun observePreference(): Flow<SystemMotionPreference> = preferenceFlow

    private fun readPreference(): SystemMotionPreference {
        return try {
            val desktop = Toolkit.getDefaultToolkit()
            val reduceMotion = desktop.getDesktopProperty("gnome.Gtk/EnableAnimations")
            when (reduceMotion) {
                0, false -> SystemMotionPreference.ReduceMotion
                else -> SystemMotionPreference.NoPreference
            }
        } catch (_: HeadlessException) {
            // Safe fallback
            SystemMotionPreference.ReduceMotion
        } catch (_: SecurityException) {
            // Safe fallback
            SystemMotionPreference.ReduceMotion
        }
    }
}

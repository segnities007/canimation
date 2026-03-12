package com.segnities007.canimation.app.settings

import com.segnities007.canimation.app.state.AppEvent
import com.segnities007.canimation.app.state.AppStateHolder
import com.segnities007.canimation.app.state.AppUiState
import io.github.canimation.core.CanimationPolicy

internal data class AppSettingsSheetState(
    val isDarkMode: Boolean,
    val policy: CanimationPolicy,
    val showPresetTuning: Boolean,
    val autoPlayEnabled: Boolean,
    val cycleMs: Float,
    val durationScale: Float,
    val distanceScale: Float,
    val scaleIntensity: Float,
    val rotationScale: Float,
    val diagnosticsEnabled: Boolean,
)

internal sealed interface AppSettingsSheetEvent {
    data object DarkModeToggled : AppSettingsSheetEvent

    data class PolicyChanged(
        val policy: CanimationPolicy,
    ) : AppSettingsSheetEvent

    data class AutoPlayEnabledChanged(
        val enabled: Boolean,
    ) : AppSettingsSheetEvent

    data class CycleMsChanged(
        val value: Float,
    ) : AppSettingsSheetEvent

    data class DurationScaleChanged(
        val value: Float,
    ) : AppSettingsSheetEvent

    data class DistanceScaleChanged(
        val value: Float,
    ) : AppSettingsSheetEvent

    data class ScaleIntensityChanged(
        val value: Float,
    ) : AppSettingsSheetEvent

    data class RotationScaleChanged(
        val value: Float,
    ) : AppSettingsSheetEvent

    data object PreviewTuningReset : AppSettingsSheetEvent

    data class DiagnosticsEnabledChanged(
        val enabled: Boolean,
    ) : AppSettingsSheetEvent
}

internal fun AppUiState.toAppSettingsSheetState(
    isDarkMode: Boolean,
    showPresetTuning: Boolean,
): AppSettingsSheetState =
    AppSettingsSheetState(
        isDarkMode = isDarkMode,
        policy = policy,
        showPresetTuning = showPresetTuning,
        autoPlayEnabled = autoPlayEnabled,
        cycleMs = cycleMs,
        durationScale = durationScale,
        distanceScale = distanceScale,
        scaleIntensity = scaleIntensity,
        rotationScale = rotationScale,
        diagnosticsEnabled = diagnosticsEnabled,
    )

internal fun AppStateHolder.onAppSettingsSheetEvent(
    event: AppSettingsSheetEvent,
    isDarkMode: Boolean,
) {
    onEvent(
        when (event) {
            AppSettingsSheetEvent.DarkModeToggled -> AppEvent.DarkOverrideChanged(!isDarkMode)
            is AppSettingsSheetEvent.PolicyChanged -> AppEvent.PolicyChanged(event.policy)
            is AppSettingsSheetEvent.AutoPlayEnabledChanged -> AppEvent.AutoPlayEnabledChanged(event.enabled)
            is AppSettingsSheetEvent.CycleMsChanged -> AppEvent.CycleMsChanged(event.value)
            is AppSettingsSheetEvent.DurationScaleChanged -> AppEvent.DurationScaleChanged(event.value)
            is AppSettingsSheetEvent.DistanceScaleChanged -> AppEvent.DistanceScaleChanged(event.value)
            is AppSettingsSheetEvent.ScaleIntensityChanged -> AppEvent.ScaleIntensityChanged(event.value)
            is AppSettingsSheetEvent.RotationScaleChanged -> AppEvent.RotationScaleChanged(event.value)
            AppSettingsSheetEvent.PreviewTuningReset -> AppEvent.PreviewTuningReset
            is AppSettingsSheetEvent.DiagnosticsEnabledChanged -> AppEvent.DiagnosticsEnabledChanged(event.enabled)
        },
    )
}

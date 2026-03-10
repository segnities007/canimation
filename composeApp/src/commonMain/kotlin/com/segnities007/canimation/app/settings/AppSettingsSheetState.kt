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

internal data class AppSettingsSheetActions(
    val onToggleDark: () -> Unit,
    val onPolicyChange: (CanimationPolicy) -> Unit,
    val onAutoPlayChange: (Boolean) -> Unit,
    val onCycleMsChange: (Float) -> Unit,
    val onDurationScaleChange: (Float) -> Unit,
    val onDistanceScaleChange: (Float) -> Unit,
    val onScaleIntensityChange: (Float) -> Unit,
    val onRotationScaleChange: (Float) -> Unit,
    val onResetParams: () -> Unit,
    val onDiagnosticsEnabledChange: (Boolean) -> Unit,
)

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

internal fun AppStateHolder.appSettingsSheetActions(isDarkMode: Boolean): AppSettingsSheetActions =
    AppSettingsSheetActions(
        onToggleDark = {
            onEvent(AppEvent.DarkOverrideChanged(!isDarkMode))
        },
        onPolicyChange = { onEvent(AppEvent.PolicyChanged(it)) },
        onAutoPlayChange = { onEvent(AppEvent.AutoPlayEnabledChanged(it)) },
        onCycleMsChange = { onEvent(AppEvent.CycleMsChanged(it)) },
        onDurationScaleChange = { onEvent(AppEvent.DurationScaleChanged(it)) },
        onDistanceScaleChange = { onEvent(AppEvent.DistanceScaleChanged(it)) },
        onScaleIntensityChange = { onEvent(AppEvent.ScaleIntensityChanged(it)) },
        onRotationScaleChange = { onEvent(AppEvent.RotationScaleChanged(it)) },
        onResetParams = { onEvent(AppEvent.PreviewTuningReset) },
        onDiagnosticsEnabledChange = { onEvent(AppEvent.DiagnosticsEnabledChanged(it)) },
    )

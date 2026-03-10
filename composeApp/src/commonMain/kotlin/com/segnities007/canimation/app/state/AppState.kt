package com.segnities007.canimation.app.state

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.segnities007.canimation.component.PresetPreviewTuning
import com.segnities007.canimation.compose.RunStagedProgressionEffect
import com.segnities007.canimation.compose.StagedProgressionSpec
import io.github.canimation.core.CanimationPolicy
import kotlinx.coroutines.delay

internal data class AppUiState(
    val policy: CanimationPolicy = CanimationPolicy.SystemAware,
    val darkOverride: Boolean? = null,
    val showSettings: Boolean = false,
    val diagnosticsEnabled: Boolean = false,
    val navStage: Int = -1,
    val autoPlayEnabled: Boolean = true,
    val cycleMs: Float = 1400f,
    val durationScale: Float = 1f,
    val distanceScale: Float = 1f,
    val scaleIntensity: Float = 1f,
    val rotationScale: Float = 1f,
    val autoPlayTick: Int = 0,
)

internal sealed interface AppEvent {
    data class PolicyChanged(
        val policy: CanimationPolicy,
    ) : AppEvent

    data class DarkOverrideChanged(
        val override: Boolean?,
    ) : AppEvent

    data class SettingsVisibilityChanged(
        val visible: Boolean,
    ) : AppEvent

    data class DiagnosticsEnabledChanged(
        val enabled: Boolean,
    ) : AppEvent

    data class NavStageChanged(
        val stage: Int,
    ) : AppEvent

    data class AutoPlayEnabledChanged(
        val enabled: Boolean,
    ) : AppEvent

    data class CycleMsChanged(
        val value: Float,
    ) : AppEvent

    data class DurationScaleChanged(
        val value: Float,
    ) : AppEvent

    data class DistanceScaleChanged(
        val value: Float,
    ) : AppEvent

    data class ScaleIntensityChanged(
        val value: Float,
    ) : AppEvent

    data class RotationScaleChanged(
        val value: Float,
    ) : AppEvent

    data object AutoPlayTicked : AppEvent

    data object PreviewTuningReset : AppEvent
}

internal fun reduceAppState(
    current: AppUiState,
    event: AppEvent,
): AppUiState =
    when (event) {
        is AppEvent.PolicyChanged -> current.copy(policy = event.policy)
        is AppEvent.DarkOverrideChanged -> current.copy(darkOverride = event.override)
        is AppEvent.SettingsVisibilityChanged -> current.copy(showSettings = event.visible)
        is AppEvent.DiagnosticsEnabledChanged -> current.copy(diagnosticsEnabled = event.enabled)
        is AppEvent.NavStageChanged -> current.copy(navStage = event.stage)
        is AppEvent.AutoPlayEnabledChanged -> current.copy(autoPlayEnabled = event.enabled)
        is AppEvent.CycleMsChanged -> current.copy(cycleMs = event.value)
        is AppEvent.DurationScaleChanged -> current.copy(durationScale = event.value)
        is AppEvent.DistanceScaleChanged -> current.copy(distanceScale = event.value)
        is AppEvent.ScaleIntensityChanged -> current.copy(scaleIntensity = event.value)
        is AppEvent.RotationScaleChanged -> current.copy(rotationScale = event.value)
        AppEvent.AutoPlayTicked -> current.copy(autoPlayTick = current.autoPlayTick + 1)
        AppEvent.PreviewTuningReset ->
            current.copy(
                durationScale = 1f,
                distanceScale = 1f,
                scaleIntensity = 1f,
                rotationScale = 1f,
            )
    }

@Stable
internal class AppStateHolder(
    initialState: AppUiState = AppUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: AppEvent) {
        uiState = reduceAppState(uiState, event)
    }
}

@Composable
internal fun rememberAppStateHolder(): AppStateHolder {
    val stateHolder = remember { AppStateHolder() }

    RunStagedProgressionEffect(
        spec = StagedProgressionSpec(maxStage = 7, stepDelayMillis = 40L),
        onStage = { stateHolder.onEvent(AppEvent.NavStageChanged(it)) },
    )

    LaunchedEffect(stateHolder.uiState.autoPlayEnabled, stateHolder.uiState.cycleMs) {
        if (stateHolder.uiState.autoPlayEnabled) {
            while (true) {
                stateHolder.onEvent(AppEvent.AutoPlayTicked)
                delay(
                    stateHolder.uiState.cycleMs
                        .toLong()
                        .coerceAtLeast(350L),
                )
            }
        }
    }

    return stateHolder
}

@Composable
internal fun AppUiState.resolveDarkMode(): Boolean {
    val systemDarkMode = isSystemInDarkTheme()
    return darkOverride ?: systemDarkMode
}

internal fun AppUiState.toPresetPreviewTuning(): PresetPreviewTuning =
    PresetPreviewTuning(
        durationScale = durationScale,
        distanceScale = distanceScale,
        scaleIntensity = scaleIntensity,
        rotationScale = rotationScale,
    )

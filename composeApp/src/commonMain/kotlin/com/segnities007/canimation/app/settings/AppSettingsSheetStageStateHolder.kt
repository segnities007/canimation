package com.segnities007.canimation.app.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.segnities007.canimation.compose.RunStagedProgressionEffect
import com.segnities007.canimation.compose.StagedProgressionSpec

internal data class AppSettingsSheetStageState(
    val stage: Int = -1,
)

internal sealed interface AppSettingsSheetStageEvent {
    data class StageChanged(
        val value: Int,
    ) : AppSettingsSheetStageEvent
}

internal fun reduceAppSettingsSheetStageState(
    current: AppSettingsSheetStageState,
    event: AppSettingsSheetStageEvent,
): AppSettingsSheetStageState =
    when (event) {
        is AppSettingsSheetStageEvent.StageChanged -> current.copy(stage = event.value)
    }

@Stable
internal class AppSettingsSheetStageStateHolder(
    initialState: AppSettingsSheetStageState = AppSettingsSheetStageState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: AppSettingsSheetStageEvent) {
        uiState = reduceAppSettingsSheetStageState(uiState, event)
    }
}

@Composable
internal fun rememberAppSettingsSheetStageStateHolder(
    maxStage: Int = 8,
    stepDelayMillis: Long = 40L,
): AppSettingsSheetStageStateHolder {
    val stateHolder = remember { AppSettingsSheetStageStateHolder() }

    RunStagedProgressionEffect(
        spec = StagedProgressionSpec(maxStage = maxStage, stepDelayMillis = stepDelayMillis),
        onStage = { stateHolder.onEvent(AppSettingsSheetStageEvent.StageChanged(it)) },
    )

    return stateHolder
}

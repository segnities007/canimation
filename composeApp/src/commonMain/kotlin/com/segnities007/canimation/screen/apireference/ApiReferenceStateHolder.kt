package com.segnities007.canimation.screen.apireference

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.segnities007.canimation.compose.RunStagedProgressionEffect
import com.segnities007.canimation.compose.StagedProgressionSpec

@Stable
internal class ApiReferenceStateHolder(
    initialState: ApiReferenceUiState = ApiReferenceUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: ApiReferenceEvent) {
        uiState = reduceApiReferenceState(uiState, event)
    }
}

@Composable
internal fun rememberApiReferenceStateHolder(): ApiReferenceStateHolder {
    val stateHolder = remember { ApiReferenceStateHolder() }

    RunStagedProgressionEffect(
        spec = StagedProgressionSpec(maxStage = 3, stepDelayMillis = 60L),
        onStage = { stateHolder.onEvent(ApiReferenceEvent.HeaderStageUpdated(it)) },
    )

    return stateHolder
}

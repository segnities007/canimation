package com.segnities007.canimation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.segnities007.canimation.compose.RunStagedProgressionEffect
import com.segnities007.canimation.compose.StagedProgressionSpec

@Stable
internal class HomeStateHolder(
    initialState: HomeUiState = HomeUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: HomeEvent) {
        uiState = reduceHomeState(uiState, event)
    }
}

@Composable
internal fun rememberHomeStateHolder(
    maxStage: Int = 16,
    stepDelayMillis: Long = 70L,
): HomeStateHolder {
    val stateHolder = remember { HomeStateHolder() }

    RunStagedProgressionEffect(
        spec = StagedProgressionSpec(maxStage = maxStage, stepDelayMillis = stepDelayMillis),
        onStage = { stateHolder.onEvent(HomeEvent.StageChanged(it)) },
    )

    return stateHolder
}

package com.segnities007.canimation.screen.examples.detail

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
internal class ExampleDetailStateHolder(
    initialState: ExampleDetailUiState = ExampleDetailUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: ExampleDetailEvent) {
        uiState = reduceExampleDetailState(uiState, event)
    }
}

@Composable
internal fun rememberExampleDetailStateHolder(): ExampleDetailStateHolder {
    val stateHolder = remember { ExampleDetailStateHolder() }

    RunStagedProgressionEffect(
        spec = StagedProgressionSpec(maxStage = 4, stepDelayMillis = 60L),
        onStage = { stateHolder.onEvent(ExampleDetailEvent.EntryStageUpdated(it)) },
    )

    return stateHolder
}

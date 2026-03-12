package com.segnities007.canimation.screen.showcase.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.segnities007.canimation.compose.RunStagedProgressionEffect
import com.segnities007.canimation.compose.StagedProgressionSpec

@Stable
internal class ShowcaseDetailStateHolder(
    initialState: ShowcaseDetailUiState = ShowcaseDetailUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: ShowcaseDetailEvent) {
        uiState = reduceShowcaseDetailState(uiState, event)
    }
}

@Composable
internal fun rememberShowcaseDetailStateHolder(): ShowcaseDetailStateHolder {
    val stateHolder = remember { ShowcaseDetailStateHolder() }

    RunStagedProgressionEffect(
        spec = StagedProgressionSpec(maxStage = 4, stepDelayMillis = 60L),
        onStage = { stateHolder.onEvent(ShowcaseDetailEvent.EntryStageUpdated(it)) },
    )

    return stateHolder
}

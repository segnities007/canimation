package com.segnities007.canimation.screen.docs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.segnities007.canimation.compose.RunStagedProgressionEffect
import com.segnities007.canimation.compose.StagedProgressionSpec

@Stable
internal class DocsStateHolder(
    initialState: DocsUiState = DocsUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: DocsEvent) {
        uiState = reduceDocsState(uiState, event)
    }
}

@Composable
internal fun rememberDocsStateHolder(): DocsStateHolder {
    val stateHolder = remember { DocsStateHolder() }

    RunStagedProgressionEffect(
        spec = StagedProgressionSpec(maxStage = 20, stepDelayMillis = 40L),
        onStage = { stateHolder.onEvent(DocsEvent.StageUpdated(it)) },
    )

    return stateHolder
}

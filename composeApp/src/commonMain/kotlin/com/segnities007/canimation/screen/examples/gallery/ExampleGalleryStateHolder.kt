package com.segnities007.canimation.screen.examples.gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.segnities007.canimation.compose.RunStagedProgressionEffect
import com.segnities007.canimation.compose.StagedProgressionSpec

@Stable
internal class ExampleGalleryStateHolder(
    initialState: ExampleGalleryUiState = ExampleGalleryUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: ExampleGalleryEvent) {
        uiState = reduceExampleGalleryState(uiState, event)
    }
}

@Composable
internal fun rememberExampleGalleryStateHolder(): ExampleGalleryStateHolder {
    val stateHolder = remember { ExampleGalleryStateHolder() }

    RunStagedProgressionEffect(
        spec = StagedProgressionSpec(maxStage = 3, stepDelayMillis = 50L),
        onStage = { stateHolder.onEvent(ExampleGalleryEvent.HeaderStageUpdated(it)) },
    )

    return stateHolder
}

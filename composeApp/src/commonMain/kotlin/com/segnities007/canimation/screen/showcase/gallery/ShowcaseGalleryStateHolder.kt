package com.segnities007.canimation.screen.showcase.gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.segnities007.canimation.compose.RunStagedProgressionEffect
import com.segnities007.canimation.compose.StagedProgressionSpec

@Stable
internal class ShowcaseGalleryStateHolder(
    initialState: ShowcaseGalleryUiState = ShowcaseGalleryUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: ShowcaseGalleryEvent) {
        uiState = reduceShowcaseGalleryState(uiState, event)
    }
}

@Composable
internal fun rememberShowcaseGalleryStateHolder(): ShowcaseGalleryStateHolder {
    val stateHolder = remember { ShowcaseGalleryStateHolder() }

    RunStagedProgressionEffect(
        spec = StagedProgressionSpec(maxStage = 3, stepDelayMillis = 50L),
        onStage = { stateHolder.onEvent(ShowcaseGalleryEvent.HeaderStageUpdated(it)) },
    )

    return stateHolder
}

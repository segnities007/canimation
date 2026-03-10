package com.segnities007.canimation.screen.presets

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
internal class PresetGalleryStateHolder(
    initialState: PresetGalleryUiState = PresetGalleryUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: PresetGalleryEvent) {
        uiState = reducePresetGalleryState(uiState, event)
    }
}

@Composable
internal fun rememberPresetGalleryStateHolder(): PresetGalleryStateHolder {
    val stateHolder = remember { PresetGalleryStateHolder() }

    RunStagedProgressionEffect(
        spec = StagedProgressionSpec(maxStage = 3, stepDelayMillis = 80L),
        onStage = { stateHolder.onEvent(PresetGalleryEvent.HeaderStageUpdated(it)) },
    )

    return stateHolder
}

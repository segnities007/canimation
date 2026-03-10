# State Holder Template

```kotlin
package com.example.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

internal data class FeatureUiState(
    val stage: Int = -1,
    val isLoading: Boolean = false,
)

internal sealed interface FeatureEvent {
    data class StageChanged(val value: Int) : FeatureEvent
    data class LoadingChanged(val value: Boolean) : FeatureEvent
}

internal fun reduceFeatureState(
    current: FeatureUiState,
    event: FeatureEvent,
): FeatureUiState = when (event) {
    is FeatureEvent.StageChanged -> current.copy(stage = event.value)
    is FeatureEvent.LoadingChanged -> current.copy(isLoading = event.value)
}

@Stable
internal class FeatureStateHolder(
    initialState: FeatureUiState = FeatureUiState(),
) {
    var uiState by mutableStateOf(initialState)
        private set

    fun onEvent(event: FeatureEvent) {
        uiState = reduceFeatureState(uiState, event)
    }
}

@Composable
internal fun rememberFeatureStateHolder(): FeatureStateHolder {
    val stateHolder = remember { FeatureStateHolder() }

    LaunchedEffect(Unit) {
        stateHolder.onEvent(FeatureEvent.StageChanged(0))
    }

    return stateHolder
}
```

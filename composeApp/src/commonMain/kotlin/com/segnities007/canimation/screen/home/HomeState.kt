package com.segnities007.canimation.screen.home

internal data class HomeUiState(
    val stage: Int = -1,
)

internal sealed interface HomeEvent {
    data class StageChanged(val stage: Int) : HomeEvent
}

internal fun reduceHomeState(
    current: HomeUiState,
    event: HomeEvent,
): HomeUiState = when (event) {
    is HomeEvent.StageChanged -> current.copy(stage = event.stage)
}

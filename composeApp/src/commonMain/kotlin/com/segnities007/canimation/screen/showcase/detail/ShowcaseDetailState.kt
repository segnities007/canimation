package com.segnities007.canimation.screen.showcase.detail

internal data class ShowcaseDetailUiState(
    val entryStage: Int = -1,
)

internal sealed interface ShowcaseDetailEvent {
    data class EntryStageUpdated(val stage: Int) : ShowcaseDetailEvent
}

internal fun reduceShowcaseDetailState(
    current: ShowcaseDetailUiState,
    event: ShowcaseDetailEvent,
): ShowcaseDetailUiState = when (event) {
    is ShowcaseDetailEvent.EntryStageUpdated -> current.copy(entryStage = event.stage)
}

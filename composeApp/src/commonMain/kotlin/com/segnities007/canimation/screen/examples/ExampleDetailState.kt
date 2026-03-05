package com.segnities007.canimation.screen.examples

internal data class ExampleDetailUiState(
    val entryStage: Int = -1,
)

internal sealed interface ExampleDetailEvent {
    data class EntryStageUpdated(val stage: Int) : ExampleDetailEvent
}

internal fun reduceExampleDetailState(
    current: ExampleDetailUiState,
    event: ExampleDetailEvent,
): ExampleDetailUiState = when (event) {
    is ExampleDetailEvent.EntryStageUpdated -> current.copy(entryStage = event.stage)
}

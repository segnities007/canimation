package com.segnities007.canimation.screen

internal enum class RefFilter(val label: String) {
    All("All"),
    Modifiers("Modifiers"),
    Composables("Composables"),
    DataClasses("Data Classes"),
    Namespace("Canimation.*"),
    Factories("Factories"),
    Enums("Enums & Policies"),
}

internal data class ApiEntry(
    val name: String,
    val signature: String,
    val description: String,
    val category: RefFilter,
    val badge: String = "",
    val codeExample: String = "",
)

internal data class ApiReferenceUiState(
    val selectedFilter: RefFilter = RefFilter.All,
    val headerStage: Int = -1,
)

internal sealed interface ApiReferenceEvent {
    data class FilterSelected(val filter: RefFilter) : ApiReferenceEvent
    data class HeaderStageUpdated(val stage: Int) : ApiReferenceEvent
}

internal fun reduceApiReferenceState(
    current: ApiReferenceUiState,
    event: ApiReferenceEvent,
): ApiReferenceUiState = when (event) {
    is ApiReferenceEvent.FilterSelected -> current.copy(selectedFilter = event.filter)
    is ApiReferenceEvent.HeaderStageUpdated -> current.copy(headerStage = event.stage)
}

internal fun filterApiEntries(
    entries: List<ApiEntry>,
    filter: RefFilter,
): List<ApiEntry> {
    return if (filter == RefFilter.All) entries else entries.filter { it.category == filter }
}

package com.segnities007.canimation.screen.apireference

import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.api_filter_all
import canimation.composeapp.generated.resources.api_filter_composables
import canimation.composeapp.generated.resources.api_filter_data_classes
import canimation.composeapp.generated.resources.api_filter_enums
import canimation.composeapp.generated.resources.api_filter_factories
import canimation.composeapp.generated.resources.api_filter_modifiers
import canimation.composeapp.generated.resources.api_filter_namespace
import org.jetbrains.compose.resources.StringResource

internal enum class RefFilter(val labelRes: StringResource) {
    All(Res.string.api_filter_all),
    Modifiers(Res.string.api_filter_modifiers),
    Composables(Res.string.api_filter_composables),
    DataClasses(Res.string.api_filter_data_classes),
    Namespace(Res.string.api_filter_namespace),
    Factories(Res.string.api_filter_factories),
    Enums(Res.string.api_filter_enums),
}

internal data class ApiEntry(
    val name: StringResource,
    val signature: StringResource,
    val description: StringResource,
    val category: RefFilter,
    val badge: StringResource? = null,
    val codeExample: StringResource? = null,
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

internal fun filterApiReferenceEntries(
    entries: List<ApiEntry>,
    filter: RefFilter,
): List<ApiEntry> {
    return if (filter == RefFilter.All) entries else entries.filter { it.category == filter }
}

package com.segnities007.canimation.screen.examples

internal data class ExamplesUiState(
    val searchQuery: String = "",
    val selectedTag: String = ALL_TAG,
    val headerStage: Int = -1,
    val showFilters: Boolean = true,
)

internal data class ResolvedGalleryItem(
    val galleryItem: GalleryItem,
    val title: String,
    val description: String,
    val tagLabel: String,
)

internal sealed interface ExamplesEvent {
    data class SearchQueryChanged(val query: String) : ExamplesEvent
    data class TagSelected(val tag: String) : ExamplesEvent
    data class HeaderStageUpdated(val stage: Int) : ExamplesEvent
    data object FiltersToggled : ExamplesEvent
}

internal fun reduceExamplesState(
    current: ExamplesUiState,
    event: ExamplesEvent,
): ExamplesUiState = when (event) {
    is ExamplesEvent.SearchQueryChanged -> current.copy(searchQuery = event.query)
    is ExamplesEvent.TagSelected -> {
        val normalized = if (event.tag == ALL_TAG) ALL_TAG else event.tag
        val nextTag = if (normalized != ALL_TAG && normalized == current.selectedTag) {
            ALL_TAG
        } else {
            normalized
        }
        current.copy(selectedTag = nextTag)
    }
    is ExamplesEvent.HeaderStageUpdated -> current.copy(headerStage = event.stage)
    ExamplesEvent.FiltersToggled -> current.copy(showFilters = !current.showFilters)
}

internal fun filterGalleryItems(
    items: List<ResolvedGalleryItem>,
    state: ExamplesUiState,
): List<ResolvedGalleryItem> = items.filter { resolvedItem ->
    val matchesSearch = state.searchQuery.isBlank() ||
        resolvedItem.title.contains(state.searchQuery, ignoreCase = true) ||
        resolvedItem.description.contains(state.searchQuery, ignoreCase = true) ||
        resolvedItem.tagLabel.contains(state.searchQuery, ignoreCase = true)
    val matchesTag = state.selectedTag == ALL_TAG || resolvedItem.galleryItem.tag == state.selectedTag
    matchesSearch && matchesTag
}

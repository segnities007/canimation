package com.segnities007.canimation.screen.examples.gallery

internal data class ExampleGalleryUiState(
    val searchQuery: String = "",
    val selectedTag: String = ALL_TAG,
    val headerStage: Int = -1,
    val showFilters: Boolean = true,
)

internal sealed interface ExampleGalleryEvent {
    data class SearchQueryChanged(
        val query: String,
    ) : ExampleGalleryEvent

    data class TagSelected(
        val tag: String,
    ) : ExampleGalleryEvent

    data class HeaderStageUpdated(
        val stage: Int,
    ) : ExampleGalleryEvent

    data object FiltersToggled : ExampleGalleryEvent
}

internal fun reduceExampleGalleryState(
    current: ExampleGalleryUiState,
    event: ExampleGalleryEvent,
): ExampleGalleryUiState =
    when (event) {
        is ExampleGalleryEvent.SearchQueryChanged -> current.copy(searchQuery = event.query)
        is ExampleGalleryEvent.TagSelected -> {
            val normalized = if (event.tag == ALL_TAG) ALL_TAG else event.tag
            val nextTag =
                if (normalized != ALL_TAG && normalized == current.selectedTag) {
                    ALL_TAG
                } else {
                    normalized
                }
            current.copy(selectedTag = nextTag)
        }
        is ExampleGalleryEvent.HeaderStageUpdated -> current.copy(headerStage = event.stage)
        ExampleGalleryEvent.FiltersToggled -> current.copy(showFilters = !current.showFilters)
    }

package com.segnities007.canimation.screen.showcase.gallery

import com.segnities007.canimation.screen.showcase.data.ShowcaseDemoType
import com.segnities007.canimation.screen.showcase.data.ShowcaseTagId

internal data class ShowcaseGalleryUiState(
    val searchQuery: String = "",
    val selectedTag: ShowcaseTagId? = ALL_TAG,
    val selectedDemoType: ShowcaseDemoType? = ALL_DEMO_TYPE,
    val headerStage: Int = -1,
    val showFilters: Boolean = true,
)

internal sealed interface ShowcaseGalleryEvent {
    data class SearchQueryChanged(
        val query: String,
    ) : ShowcaseGalleryEvent

    data class TagSelected(
        val tag: ShowcaseTagId?,
    ) : ShowcaseGalleryEvent

    data class DemoTypeSelected(
        val demoType: ShowcaseDemoType?,
    ) : ShowcaseGalleryEvent

    data class HeaderStageUpdated(
        val stage: Int,
    ) : ShowcaseGalleryEvent

    data object FiltersToggled : ShowcaseGalleryEvent
}

internal fun reduceShowcaseGalleryState(
    current: ShowcaseGalleryUiState,
    event: ShowcaseGalleryEvent,
): ShowcaseGalleryUiState =
    when (event) {
        is ShowcaseGalleryEvent.SearchQueryChanged -> current.copy(searchQuery = event.query)
        is ShowcaseGalleryEvent.TagSelected -> {
            val normalized = event.tag
            val nextTag = if (normalized != ALL_TAG && normalized == current.selectedTag) ALL_TAG else normalized
            current.copy(selectedTag = nextTag)
        }
        is ShowcaseGalleryEvent.DemoTypeSelected -> {
            val normalized = event.demoType
            val nextDemoType =
                if (normalized != ALL_DEMO_TYPE && normalized == current.selectedDemoType) {
                    ALL_DEMO_TYPE
                } else {
                    normalized
                }
            current.copy(selectedDemoType = nextDemoType)
        }
        is ShowcaseGalleryEvent.HeaderStageUpdated -> current.copy(headerStage = event.stage)
        ShowcaseGalleryEvent.FiltersToggled -> current.copy(showFilters = !current.showFilters)
    }

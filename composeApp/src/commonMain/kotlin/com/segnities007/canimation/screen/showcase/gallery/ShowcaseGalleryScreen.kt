package com.segnities007.canimation.screen.showcase.gallery

import com.segnities007.canimation.screen.showcase.data.showcaseDemoTypeLabelRes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource

@Composable
fun ShowcaseGalleryScreen(
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val stateHolder = rememberShowcaseGalleryStateHolder()
    val uiState = stateHolder.uiState
    val catalog = showcaseGalleryCatalog

    val displayItems =
        catalog.entries.map { entry ->
            ShowcaseGalleryDisplayItem(
                entry = entry,
                title = stringResource(entry.item.title),
                description = stringResource(entry.item.description),
                tagLabel = stringResource(entry.accentTag.labelRes),
                demoTypeLabel = stringResource(showcaseDemoTypeLabelRes(entry.item.demoType)),
            )
        }
    val filteredItems =
        remember(displayItems, uiState.searchQuery, uiState.selectedTag, uiState.selectedDemoType) {
            filterShowcaseGalleryItems(displayItems, uiState)
        }

    ShowcaseGalleryContent(
        uiState = uiState,
        totalCount = catalog.totalCount,
        filterTags = catalog.filterTags,
        demoTypeFilters = catalog.demoTypeFilters,
        filteredItems = filteredItems,
        onSearchQueryChanged = { stateHolder.onEvent(ShowcaseGalleryEvent.SearchQueryChanged(it)) },
        onFiltersToggled = { stateHolder.onEvent(ShowcaseGalleryEvent.FiltersToggled) },
        onTagSelected = { stateHolder.onEvent(ShowcaseGalleryEvent.TagSelected(it)) },
        onDemoTypeSelected = { stateHolder.onEvent(ShowcaseGalleryEvent.DemoTypeSelected(it)) },
        onItemClick = onItemClick,
        modifier = modifier,
    )
}

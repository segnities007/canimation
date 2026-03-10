package com.segnities007.canimation.screen.examples.gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExamplesScreen(
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val stateHolder = rememberExampleGalleryStateHolder()
    val uiState = stateHolder.uiState
    val catalog = exampleGalleryCatalog

    val displayItems =
        catalog.entries.map { entry ->
            ExampleGalleryDisplayItem(
                entry = entry,
                title = stringResource(entry.item.title),
                description = stringResource(entry.item.description),
                tagLabel = stringResource(entry.tag.labelRes),
            )
        }
    val filteredItems =
        remember(displayItems, uiState.searchQuery, uiState.selectedTag) {
            filterExampleGalleryItems(displayItems, uiState)
        }

    ExampleGalleryContent(
        uiState = uiState,
        totalCount = catalog.totalCount,
        filterTags = catalog.filterTags,
        filteredItems = filteredItems,
        onSearchQueryChanged = { stateHolder.onEvent(ExampleGalleryEvent.SearchQueryChanged(it)) },
        onFiltersToggled = { stateHolder.onEvent(ExampleGalleryEvent.FiltersToggled) },
        onTagSelected = { stateHolder.onEvent(ExampleGalleryEvent.TagSelected(it)) },
        onItemClick = onItemClick,
        modifier = modifier,
    )
}

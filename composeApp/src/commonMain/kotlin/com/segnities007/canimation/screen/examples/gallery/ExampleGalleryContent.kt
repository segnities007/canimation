package com.segnities007.canimation.screen.examples.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ExampleGalleryContent(
    uiState: ExampleGalleryUiState,
    totalCount: Int,
    filterTags: List<ExampleGalleryFilterTag>,
    filteredItems: List<ExampleGalleryDisplayItem>,
    onSearchQueryChanged: (String) -> Unit,
    onFiltersToggled: () -> Unit,
    onTagSelected: (String) -> Unit,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 200.dp),
            modifier = Modifier.widthIn(max = 1400.dp),
            contentPadding =
                PaddingValues(
                    horizontal = 24.dp,
                    vertical = 24.dp,
                ),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                ExampleGalleryHeader(
                    uiState = uiState,
                    totalCount = totalCount,
                    filterTags = filterTags,
                    resultCount = filteredItems.size,
                    onSearchQueryChanged = onSearchQueryChanged,
                    onFiltersToggled = onFiltersToggled,
                    onTagSelected = onTagSelected,
                )
            }

            items(filteredItems, key = { it.entry.globalIndex }) { galleryItem ->
                ExampleGalleryPreviewCard(
                    galleryItem = galleryItem,
                    onClick = { onItemClick(galleryItem.entry.globalIndex) },
                )
            }
        }
    }
}

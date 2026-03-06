package com.segnities007.canimation.screen.examples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@Composable
fun ExamplesScreen(
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var uiState by remember { mutableStateOf(ExamplesUiState()) }
    val onEvent: (ExamplesEvent) -> Unit = { event ->
        uiState = reduceExamplesState(uiState, event)
    }

    LaunchedEffect(Unit) {
        for (i in 0..3) {
            delay(50)
            onEvent(ExamplesEvent.HeaderStageUpdated(i))
        }
    }

    val filteredItems by remember(uiState.searchQuery, uiState.selectedTag) {
        derivedStateOf {
            filterGalleryItems(allGalleryItems, uiState)
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 200.dp),
            modifier = Modifier.widthIn(max = 1400.dp),
            contentPadding = PaddingValues(
                horizontal = 24.dp,
                vertical = 24.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            // Header
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column(
                    modifier = Modifier.padding(bottom = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(
                        text = "GALLERY",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.canimation(
                            visible = uiState.headerStage >= 0,
                            effect = Canimation.Fade.Up,
                        ),
                    )
                    Text(
                        text = "Animation Gallery",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.canimation(
                            visible = uiState.headerStage >= 1,
                            effect = Canimation.Fade.Up,
                        ),
                    )
                    Text(
                        text = "${allGalleryItems.size} animations",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.canimation(
                            visible = uiState.headerStage >= 2,
                            effect = Canimation.Fade.Up,
                        ),
                    )

                    // Search bar + filter icon
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .canimation(
                                visible = uiState.headerStage >= 3,
                                effect = Canimation.Fade.Up,
                            ),
                    ) {
                        OutlinedTextField(
                            value = uiState.searchQuery,
                            onValueChange = { onEvent(ExamplesEvent.SearchQueryChanged(it)) },
                            placeholder = { Text("Search animations...") },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = "Search",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.weight(1f),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            ),
                        )
                        IconButton(onClick = { onEvent(ExamplesEvent.FiltersToggled) }) {
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = "Filter",
                                tint = if (uiState.selectedTag != ALL_TAG)
                                    MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }

                    // Filter chips (toggled by filter icon)
                    if (uiState.showFilters) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                        ) {
                            filterTags.forEach { label ->
                                val chipColor = tagColor(label)
                                FilterChip(
                                    selected = uiState.selectedTag == label,
                                    onClick = { onEvent(ExamplesEvent.TagSelected(label)) },
                                    label = {
                                        Text(
                                            text = label,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = if (uiState.selectedTag == label) FontWeight.Bold
                                            else FontWeight.Normal,
                                        )
                                    },
                                    leadingIcon = if (label != ALL_TAG) {
                                        {
                                            Box(
                                                Modifier.size(8.dp)
                                                    .background(chipColor, CircleShape)
                                            )
                                        }
                                    } else null,
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = chipColor.copy(alpha = 0.15f),
                                        selectedLabelColor = chipColor,
                                    ),
                                )
                            }
                        }
                    }

                    // Results count
                    if (uiState.searchQuery.isNotBlank() || uiState.selectedTag != ALL_TAG) {
                        Text(
                            text = "${filteredItems.size} results",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }

            // Flat animation cards
            items(filteredItems, key = { it.globalIndex }) { galleryItem ->
                AnimationPreviewCard(
                    galleryItem = galleryItem,
                    onClick = { onItemClick(galleryItem.globalIndex) },
                )
            }
        }
    }
}

// ===== Preview Card =====

@Composable
private fun AnimationPreviewCard(
    galleryItem: GalleryItem,
    onClick: () -> Unit,
) {
    val item = galleryItem.item
    val accent = tagColor(galleryItem.tag)
    var entered by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entered = true }

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        border = BorderStroke(1.dp, accent.copy(alpha = 0.3f)),
        modifier = Modifier
            .canimation(visible = entered, effect = Canimation.Fade.Up),
    ) {
        Column {
            // Top: Live animation preview with category-tinted background
            Surface(
                color = accent.copy(alpha = 0.06f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    LivePreview(item = item)
                }
            }

            // Bottom: Color strip + Tag + Title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, end = 12.dp, top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Color accent strip
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(32.dp)
                        .background(accent, RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp)),
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = galleryItem.tag,
                        style = MaterialTheme.typography.labelSmall,
                        color = accent,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

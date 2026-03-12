package com.segnities007.canimation.screen.showcase.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.examples_filter_action
import canimation.composeapp.generated.resources.examples_gallery_count
import canimation.composeapp.generated.resources.examples_gallery_label
import canimation.composeapp.generated.resources.examples_gallery_title
import canimation.composeapp.generated.resources.examples_results_count
import canimation.composeapp.generated.resources.examples_search_action
import canimation.composeapp.generated.resources.examples_search_placeholder
import com.segnities007.canimation.screen.showcase.data.ShowcaseTagId
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ShowcaseGalleryHeader(
    uiState: ShowcaseGalleryUiState,
    totalCount: Int,
    filterTags: List<ShowcaseGalleryFilterTag>,
    resultCount: Int,
    onSearchQueryChanged: (String) -> Unit,
    onFiltersToggled: () -> Unit,
    onTagSelected: (ShowcaseTagId?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = stringResource(Res.string.examples_gallery_label),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            modifier =
                Modifier.canimation(
                    visible = uiState.headerStage >= 0,
                    effect = Canimation.Fade.Up,
                ),
        )
        Text(
            text = stringResource(Res.string.examples_gallery_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier =
                Modifier.canimation(
                    visible = uiState.headerStage >= 1,
                    effect = Canimation.Fade.Up,
                ),
        )
        Text(
            text = stringResource(Res.string.examples_gallery_count, totalCount),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier =
                Modifier.canimation(
                    visible = uiState.headerStage >= 2,
                    effect = Canimation.Fade.Up,
                ),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .canimation(
                        visible = uiState.headerStage >= 3,
                        effect = Canimation.Fade.Up,
                    ),
        ) {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = onSearchQueryChanged,
                placeholder = { Text(stringResource(Res.string.examples_search_placeholder)) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = stringResource(Res.string.examples_search_action),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f),
                colors =
                    OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    ),
            )
            IconButton(onClick = onFiltersToggled) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = stringResource(Res.string.examples_filter_action),
                    tint =
                        if (uiState.selectedTag != ALL_TAG) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                )
            }
        }

        if (uiState.showFilters) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
            ) {
                filterTags.forEach { tag ->
                    val chipColor = tag.accentTag?.accentColor ?: MaterialTheme.colorScheme.primary
                    FilterChip(
                        selected = uiState.selectedTag == tag.id,
                        onClick = { onTagSelected(tag.id) },
                        label = {
                            Text(
                                text = stringResource(tag.labelRes),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight =
                                    if (uiState.selectedTag == tag.id) {
                                        FontWeight.Bold
                                    } else {
                                        FontWeight.Normal
                                    },
                            )
                        },
                        leadingIcon =
                            if (tag.id != ALL_TAG) {
                                {
                                    Box(
                                        Modifier
                                            .size(8.dp)
                                            .background(chipColor, CircleShape),
                                    )
                                }
                            } else {
                                null
                            },
                        colors =
                            FilterChipDefaults.filterChipColors(
                                selectedContainerColor = chipColor.copy(alpha = 0.15f),
                                selectedLabelColor = chipColor,
                            ),
                    )
                }
            }
        }

        if (uiState.searchQuery.isNotBlank() || uiState.selectedTag != ALL_TAG) {
            Text(
                text = stringResource(Res.string.examples_results_count, resultCount),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

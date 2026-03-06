package com.segnities007.canimation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@Composable
fun ApiReferenceScreen(modifier: Modifier = Modifier) {
    var uiState by remember { mutableStateOf(ApiReferenceUiState()) }
    val onEvent: (ApiReferenceEvent) -> Unit = { event ->
        uiState = reduceApiReferenceState(uiState, event)
    }

    LaunchedEffect(Unit) {
        for (i in 0..3) {
            onEvent(ApiReferenceEvent.HeaderStageUpdated(i))
            delay(60)
        }
    }

    val filtered = remember(uiState.selectedFilter) {
        filterApiEntries(apiEntries, uiState.selectedFilter)
    }

    val groupedByCategory = remember(filtered) {
        filtered.groupBy { it.category }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        LazyColumn(
            modifier = Modifier.widthIn(max = 960.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Header
            item(key = "header") {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "API REFERENCE",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.canimation(
                            visible = uiState.headerStage >= 0,
                            effect = Canimation.Fade.Up,
                        ),
                    )
                    Text(
                        text = "Complete API documentation",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.canimation(
                            visible = uiState.headerStage >= 1,
                            effect = Canimation.Fade.Up,
                        ),
                    )
                    Text(
                        text = "${apiEntries.size} API entries — Modifiers, Composables, Data Classes, Namespace effects, and more",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.canimation(
                            visible = uiState.headerStage >= 2,
                            effect = Canimation.Fade.Up,
                        ),
                    )
                }
            }

            // Filters
            item(key = "filters") {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .canimation(visible = uiState.headerStage >= 3, effect = Canimation.Fade.Up),
                ) {
                    RefFilter.entries.forEach { filter ->
                        val count = if (filter == RefFilter.All) apiEntries.size
                        else apiEntries.count { it.category == filter }
                        FilterChip(
                            selected = uiState.selectedFilter == filter,
                            onClick = { onEvent(ApiReferenceEvent.FilterSelected(filter)) },
                            label = {
                                Text(
                                    text = "${filter.label} ($count)",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = if (uiState.selectedFilter == filter) FontWeight.Bold else FontWeight.Normal,
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            ),
                        )
                    }
                }
            }

            // Grouped entries
            groupedByCategory.forEach { (category, entries) ->
                item(key = "section-${category.name}") {
                    var visible by remember { mutableStateOf(false) }
                    LaunchedEffect(category.name) { visible = true }
                    Box(Modifier.canimation(visible = visible, effect = Canimation.Fade.Left)) {
                        Column {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = MaterialTheme.colorScheme.outlineVariant,
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(MaterialTheme.colorScheme.primary, CircleShape),
                                )
                                Text(
                                    text = category.label.uppercase(),
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.5.sp,
                                )
                                Text(
                                    text = "${entries.size}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                        }
                    }
                }

                if (category == RefFilter.Namespace) {
                    val subGroups = entries.groupBy { entry ->
                        entry.name.removePrefix("Canimation.").substringBefore(".")
                    }
                    subGroups.forEach { (subCategory, subEntries) ->
                        item(key = "sub-${category.name}-$subCategory") {
                            Text(
                                text = subCategory,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                            )
                        }
                        items(subEntries, key = { it.name }) { entry ->
                            var visible by remember { mutableStateOf(false) }
                            LaunchedEffect(entry.name) { visible = true }
                            Box(
                                Modifier.canimation(visible = visible, effect = Canimation.Fade.Up),
                            ) {
                                ApiEntryCard(entry)
                            }
                        }
                    }
                } else {
                    items(entries, key = { it.name }) { entry ->
                        var visible by remember { mutableStateOf(false) }
                        LaunchedEffect(entry.name) { visible = true }
                        Box(
                            Modifier.canimation(visible = visible, effect = Canimation.Fade.Up),
                        ) {
                            ApiEntryCard(entry)
                        }
                    }
                }
            }

            // Footer
            item(key = "footer") {
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { visible = true }
                Box(Modifier.canimation(visible = visible, effect = Canimation.Fade.In)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = "canimation — Compose Multiplatform Animation Library",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Text(
                            text = "io.github.canimation:canimation-core",
                            style = MaterialTheme.typography.labelSmall,
                            fontFamily = FontFamily.Monospace,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

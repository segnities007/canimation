package com.segnities007.canimation.screen.examples

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.canimation
import io.github.canimation.core.canimationEmphasize
import kotlinx.coroutines.delay

// Flat item for the grid — carries parent category tag for filtering
private data class GalleryItem(
    val item: ExampleItem,
    val tag: String,
    val uniqueId: String,
)

private val filterTags = listOf(
    "ALL", "ENTRANCE", "EMPHASIS", "PATTERN",
    "MATERIAL", "DIRECTION", "3D", "UI",
    "TEXT", "CARDS", "LOADING", "DATA",
    "NAV", "INTERACTIVE", "VISUAL", "PHYSICS",
    "CHARTS", "GALLERY",
)

// Pre-built flat list
private val allGalleryItems: List<GalleryItem> by lazy {
    var idx = 0
    exampleCategories.flatMap { cat ->
        cat.examples.map { example ->
            GalleryItem(
                item = example,
                tag = cat.accentLabel,
                uniqueId = "item-${idx++}",
            )
        }
    }
}

@Composable
fun ExamplesScreen(
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTag by remember { mutableStateOf("ALL") }
    var headerStage by remember { mutableIntStateOf(-1) }
    var selectedItem by remember { mutableStateOf<GalleryItem?>(null) }

    LaunchedEffect(Unit) { for (i in 0..3) { delay(50); headerStage = i } }

    val filteredItems by remember(searchQuery, selectedTag) {
        derivedStateOf {
            allGalleryItems.filter { gi ->
                val matchesSearch = searchQuery.isBlank() ||
                    gi.item.title.contains(searchQuery, ignoreCase = true) ||
                    gi.item.description.contains(searchQuery, ignoreCase = true) ||
                    gi.tag.contains(searchQuery, ignoreCase = true)
                val matchesTag = selectedTag == "ALL" || gi.tag == selectedTag
                matchesSearch && matchesTag
            }
        }
    }

    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 200.dp),
                modifier = Modifier.widthIn(max = 1400.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
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
                                visible = headerStage >= 0,
                                effect = Canimation.Fade.Up,
                            ),
                        )
                        Text(
                            text = "Animation Gallery",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.canimation(
                                visible = headerStage >= 1,
                                effect = Canimation.Fade.Up,
                            ),
                        )
                        Text(
                            text = "${allGalleryItems.size} animations",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.canimation(
                                visible = headerStage >= 2,
                                effect = Canimation.Fade.Up,
                            ),
                        )

                        // Search bar
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
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
                            modifier = Modifier.fillMaxWidth()
                                .canimation(
                                    visible = headerStage >= 3,
                                    effect = Canimation.Fade.Up,
                                ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            ),
                        )

                        // Filter chips
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.horizontalScroll(rememberScrollState()),
                        ) {
                            filterTags.forEach { label ->
                                FilterChip(
                                    selected = selectedTag == label,
                                    onClick = {
                                        selectedTag =
                                            if (selectedTag == label) "ALL" else label
                                    },
                                    label = {
                                        Text(
                                            text = label,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = if (selectedTag == label) FontWeight.Bold
                                            else FontWeight.Normal,
                                        )
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                        selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                    ),
                                )
                            }
                        }

                        // Results count
                        if (searchQuery.isNotBlank() || selectedTag != "ALL") {
                            Text(
                                text = "${filteredItems.size} results",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }

                // Flat animation cards
                items(filteredItems, key = { it.uniqueId }) { galleryItem ->
                    AnimationPreviewCard(
                        galleryItem = galleryItem,
                        onClick = { selectedItem = galleryItem },
                    )
                }
            }

            // Detail dialog
            selectedItem?.let { gi ->
                DetailDialog(
                    galleryItem = gi,
                    onDismiss = { selectedItem = null },
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
    var entered by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entered = true }

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier
            .canimation(visible = entered, effect = Canimation.Fade.Up),
    ) {
        Column {
            // Top: Live animation preview
            Surface(
                color = MaterialTheme.colorScheme.surface,
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

            // Bottom: Info area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Text(
                        text = galleryItem.tag,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

// ===== Live Preview Renderers =====

@Composable
private fun LivePreview(item: ExampleItem) {
    when (item.demoType) {
        "effect", "composition" -> EffectPreview(item.effect ?: Canimation.Fade.In)
        "transition" -> TransitionPreview(item.enterEffect, item.exitEffect)
        "stagger" -> StaggerPreview(item.effect ?: Canimation.Fade.Up)
        "emphasis" -> EmphasisPreview(item.preset)
        "component" -> ComponentPreview(item.componentKey)
        else -> PresetPreview(item.preset)
    }
}

@Composable
private fun EffectPreview(effect: CanimationEffect) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(300)
        while (true) { visible = true; delay(1800); visible = false; delay(600) }
    }
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
        modifier = Modifier
            .size(56.dp)
            .canimation(visible = visible, effect = effect),
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("✦", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun TransitionPreview(enter: CanimationEffect?, exit: CanimationEffect?) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(300)
        while (true) { visible = true; delay(1800); visible = false; delay(600) }
    }
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f),
        ),
        modifier = Modifier.size(56.dp).let { mod ->
            if (enter != null) {
                mod.canimation(visible = visible, effect = enter)
            } else {
                mod
            }
        },
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("⇄", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun StaggerPreview(effect: CanimationEffect) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(3) { i ->
            var visible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                delay(300L + i * 120L)
                while (true) { visible = true; delay(2000); visible = false; delay(600) }
            }
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .size(width = 28.dp, height = 36.dp)
                    .canimation(visible = visible, effect = effect),
            ) {}
        }
    }
}

@Composable
private fun EmphasisPreview(preset: CanimationPreset) {
    var active by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(400)
        while (true) { active = true; delay(1600); active = false; delay(600) }
    }
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
        modifier = Modifier
            .size(56.dp)
            .canimationEmphasize(active = active, preset = preset),
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("A", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun PresetPreview(preset: CanimationPreset) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(300)
        while (true) { visible = true; delay(1800); visible = false; delay(600) }
    }
    Box(modifier = Modifier.size(56.dp), contentAlignment = Alignment.Center) {
        CanimationVisibility(
            visible = visible,
            enterPreset = preset,
            exitPreset = preset,
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                ),
            ) {
                Box(Modifier.size(48.dp))
            }
        }
    }
}

@Composable
private fun ComponentPreview(componentKey: String?) {
    if (componentKey == null) return
    val demo = componentDemos[componentKey]
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)),
        contentAlignment = Alignment.Center,
    ) {
        if (demo != null) {
            demo()
        }
    }
}

// ===== Detail Dialog =====

@Composable
private fun DetailDialog(
    galleryItem: GalleryItem,
    onDismiss: () -> Unit,
) {
    val item = galleryItem.item

    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(20.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        title = {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = galleryItem.tag,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // Full-size demo
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        LivePreview(item = item)
                    }
                }

                // Code snippet
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = item.codeSnippet,
                        modifier = Modifier.padding(12.dp),
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text("Close")
            }
        },
    )
}

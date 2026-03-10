package com.segnities007.canimation.screen.examples.gallery

import com.segnities007.canimation.screen.examples.data.DemoType
import io.github.canimation.core.Canimation
import kotlin.test.Test
import kotlin.test.assertEquals

class ExampleGalleryStateTest {
    @Test
    fun tagSelectionTogglesBackToAllWhenSameTagIsSelectedTwice() {
        val base = ExampleGalleryUiState(selectedTag = ALL_TAG)

        val selected = reduceExampleGalleryState(base, ExampleGalleryEvent.TagSelected("EMPHASIS"))
        assertEquals("EMPHASIS", selected.selectedTag)

        val toggledBack = reduceExampleGalleryState(selected, ExampleGalleryEvent.TagSelected("EMPHASIS"))
        assertEquals(ALL_TAG, toggledBack.selectedTag)
    }

    @Test
    fun filterGalleryItemsAppliesSearchAndTagTogether() {
        val baseExample = exampleGalleryCatalog.entries.first().item
        val items =
            listOf(
                ExampleGalleryDisplayItem(
                    entry =
                        ExampleGalleryEntry(
                            item = baseExample.copy(effect = Canimation.Fade.Up),
                            tag =
                                ExampleGalleryFilterTag(
                                    id = "ENTRANCE",
                                    labelRes = exampleGalleryCatalog.filterTags.first { it.id == "ENTRANCE" }.labelRes,
                                ),
                            globalIndex = 0,
                        ),
                    title = "Fade Up",
                    description = "Simple entrance",
                    tagLabel = "Entrance",
                ),
                ExampleGalleryDisplayItem(
                    entry =
                        ExampleGalleryEntry(
                            item = baseExample.copy(demoType = DemoType.Emphasis, effect = null),
                            tag =
                                ExampleGalleryFilterTag(
                                    id = "EMPHASIS",
                                    labelRes = exampleGalleryCatalog.filterTags.first { it.id == "EMPHASIS" }.labelRes,
                                ),
                            globalIndex = 1,
                        ),
                    title = "Pulse",
                    description = "Attention seeker",
                    tagLabel = "Emphasis",
                ),
            )

        val state = ExampleGalleryUiState(searchQuery = "pulse", selectedTag = "EMPHASIS")
        val filtered = filterExampleGalleryItems(items, state)

        assertEquals(1, filtered.size)
        assertEquals("Pulse", filtered.first().title)
    }

    @Test
    fun galleryCatalogKeepsAllTagFirstAndStableIndices() {
        val catalog = exampleGalleryCatalog

        assertEquals(ALL_TAG, catalog.filterTags.first().id)
        assertEquals(catalog.entries.indices.toList(), catalog.entries.map { it.globalIndex })
    }
}

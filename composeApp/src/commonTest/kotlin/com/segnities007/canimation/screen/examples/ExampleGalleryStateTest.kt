package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import kotlin.test.Test
import kotlin.test.assertEquals

class ExampleGalleryStateTest {

    @Test
    fun tagSelectionTogglesBackToAllWhenSameTagIsSelectedTwice() {
        val base = ExamplesUiState(selectedTag = ALL_TAG)

        val selected = reduceExamplesState(base, ExamplesEvent.TagSelected("EMPHASIS"))
        assertEquals("EMPHASIS", selected.selectedTag)

        val toggledBack = reduceExamplesState(selected, ExamplesEvent.TagSelected("EMPHASIS"))
        assertEquals(ALL_TAG, toggledBack.selectedTag)
    }

    @Test
    fun filterGalleryItemsAppliesSearchAndTagTogether() {
        val baseExample = allGalleryItems.first().item
        val items = listOf(
            ResolvedGalleryItem(
                galleryItem = GalleryItem(
                    item = baseExample.copy(effect = Canimation.Fade.Up),
                    tag = "ENTRANCE",
                    globalIndex = 0,
                ),
                title = "Fade Up",
                description = "Simple entrance",
                tagLabel = "Entrance",
            ),
            ResolvedGalleryItem(
                galleryItem = GalleryItem(
                    item = baseExample.copy(demoType = DemoType.Emphasis, effect = null),
                    tag = "EMPHASIS",
                    globalIndex = 1,
                ),
                title = "Pulse",
                description = "Attention seeker",
                tagLabel = "Emphasis",
            ),
        )

        val state = ExamplesUiState(searchQuery = "pulse", selectedTag = "EMPHASIS")
        val filtered = filterGalleryItems(items, state)

        assertEquals(1, filtered.size)
        assertEquals("Pulse", filtered.first().title)
    }
}

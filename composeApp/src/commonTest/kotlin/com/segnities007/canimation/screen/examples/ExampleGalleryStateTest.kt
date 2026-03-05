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
        val items = listOf(
            GalleryItem(
                item = ExampleItem(
                    title = "Fade Up",
                    description = "Simple entrance",
                    codeSnippet = "Modifier.canimation(...)",
                    effect = Canimation.Fade.Up,
                ),
                tag = "ENTRANCE",
                globalIndex = 0,
            ),
            GalleryItem(
                item = ExampleItem(
                    title = "Pulse",
                    description = "Attention seeker",
                    codeSnippet = "Modifier.canimationEmphasize(...)",
                    demoType = DemoType.Emphasis,
                ),
                tag = "EMPHASIS",
                globalIndex = 1,
            ),
        )

        val state = ExamplesUiState(searchQuery = "pulse", selectedTag = "EMPHASIS")
        val filtered = filterGalleryItems(items, state)

        assertEquals(1, filtered.size)
        assertEquals("Pulse", filtered.first().item.title)
    }
}

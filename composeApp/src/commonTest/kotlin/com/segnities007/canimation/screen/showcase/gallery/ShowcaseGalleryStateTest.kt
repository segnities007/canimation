package com.segnities007.canimation.screen.showcase.gallery

import com.segnities007.canimation.screen.showcase.data.ShowcaseDemoType
import com.segnities007.canimation.screen.showcase.data.ShowcaseTagId
import com.segnities007.canimation.screen.showcase.data.showcaseTagDescriptorsById
import io.github.canimation.core.Canimation
import kotlin.test.Test
import kotlin.test.assertEquals

class ShowcaseGalleryStateTest {
    @Test
    fun tagSelectionTogglesBackToAllWhenSameTagIsSelectedTwice() {
        val base = ShowcaseGalleryUiState(selectedTag = ALL_TAG)

        val selected = reduceShowcaseGalleryState(base, ShowcaseGalleryEvent.TagSelected(ShowcaseTagId.Emphasis))
        assertEquals(ShowcaseTagId.Emphasis, selected.selectedTag)

        val toggledBack = reduceShowcaseGalleryState(selected, ShowcaseGalleryEvent.TagSelected(ShowcaseTagId.Emphasis))
        assertEquals(ALL_TAG, toggledBack.selectedTag)
    }

    @Test
    fun filterGalleryItemsAppliesSearchAndTagTogether() {
        val baseExample = showcaseGalleryCatalog.entries.first().item
        val entranceTag = showcaseTagDescriptorsById.getValue(ShowcaseTagId.Entrance)
        val emphasisTag = showcaseTagDescriptorsById.getValue(ShowcaseTagId.Emphasis)
        val items =
            listOf(
                ShowcaseGalleryDisplayItem(
                    entry =
                        ShowcaseGalleryEntry(
                            item = baseExample.copy(effect = Canimation.Fade.Up),
                            accentTag = entranceTag,
                            tags = listOf(entranceTag),
                            globalIndex = 0,
                        ),
                    title = "Fade Up",
                    description = "Simple entrance",
                    tagLabel = "Entrance",
                ),
                ShowcaseGalleryDisplayItem(
                    entry =
                        ShowcaseGalleryEntry(
                            item = baseExample.copy(demoType = ShowcaseDemoType.Emphasis, effect = null),
                            accentTag = emphasisTag,
                            tags = listOf(emphasisTag),
                            globalIndex = 1,
                        ),
                    title = "Pulse",
                    description = "Attention seeker",
                    tagLabel = "Emphasis",
                ),
            )

        val state = ShowcaseGalleryUiState(searchQuery = "pulse", selectedTag = ShowcaseTagId.Emphasis)
        val filtered = filterShowcaseGalleryItems(items, state)

        assertEquals(1, filtered.size)
        assertEquals("Pulse", filtered.first().title)
    }

    @Test
    fun galleryCatalogKeepsAllTagFirstAndStableIndices() {
        val catalog = showcaseGalleryCatalog

        assertEquals(ALL_TAG, catalog.filterTags.first().id)
        assertEquals(catalog.entries.indices.toList(), catalog.entries.map { it.globalIndex })
    }

    @Test
    fun galleryCatalogDerivesFilterTagsFromCategoryTags() {
        val filterTagIds = showcaseGalleryCatalog.filterTags.mapNotNull(ShowcaseGalleryFilterTag::id).toSet()
        val categoryTagIds = showcaseGalleryCatalog.entries.flatMap { entry -> entry.tags.map { it.id } }.toSet()

        assertEquals(categoryTagIds, filterTagIds)
    }
}

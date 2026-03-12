package com.segnities007.canimation.screen.showcase.gallery

import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.filter_all
import com.segnities007.canimation.screen.showcase.data.ShowcaseItem
import com.segnities007.canimation.screen.showcase.data.ShowcaseTagDescriptor
import com.segnities007.canimation.screen.showcase.data.ShowcaseTagId
import com.segnities007.canimation.screen.showcase.data.showcaseCategories
import com.segnities007.canimation.screen.showcase.data.showcaseTagCatalog
import com.segnities007.canimation.screen.showcase.data.showcaseTagDescriptorsById
import org.jetbrains.compose.resources.StringResource

internal val ALL_TAG: ShowcaseTagId? = null

internal data class ShowcaseGalleryFilterTag(
    val id: ShowcaseTagId?,
    val labelRes: StringResource,
    val accentTag: ShowcaseTagDescriptor? = null,
)

internal data class ShowcaseGalleryEntry(
    val item: ShowcaseItem,
    val accentTag: ShowcaseTagDescriptor,
    val tags: List<ShowcaseTagDescriptor>,
    val globalIndex: Int,
)

internal data class ShowcaseGalleryDisplayItem(
    val entry: ShowcaseGalleryEntry,
    val title: String,
    val description: String,
    val tagLabel: String,
)

internal data class ShowcaseGalleryCatalog(
    val entries: List<ShowcaseGalleryEntry>,
    val filterTags: List<ShowcaseGalleryFilterTag>,
) {
    val totalCount: Int
        get() = entries.size

    fun entry(itemIndex: Int): ShowcaseGalleryEntry? = entries.getOrNull(itemIndex)
}

private val galleryTagCatalog =
    listOf(ShowcaseGalleryFilterTag(id = ALL_TAG, labelRes = Res.string.filter_all))

internal val showcaseGalleryCatalog: ShowcaseGalleryCatalog by lazy {
    var index = 0
    val entries =
        showcaseCategories.flatMap { category ->
            val accentTag = showcaseTagDescriptorsById.getValue(category.accentTag)
            val tags = category.tags.map(showcaseTagDescriptorsById::getValue)
            category.examples.map { example ->
                ShowcaseGalleryEntry(
                    item = example,
                    accentTag = accentTag,
                    tags = tags,
                    globalIndex = index++,
                )
            }
        }

    val tagIds = entries.flatMap { entry -> entry.tags.map(ShowcaseTagDescriptor::id) }.toSet()
    val filterTags =
        galleryTagCatalog +
            showcaseTagCatalog
                .filter { descriptor -> descriptor.id in tagIds }
                .map { descriptor ->
                    ShowcaseGalleryFilterTag(
                        id = descriptor.id,
                        labelRes = descriptor.labelRes,
                        accentTag = descriptor,
                    )
                }

    ShowcaseGalleryCatalog(
        entries = entries,
        filterTags = filterTags,
    )
}

internal fun filterShowcaseGalleryItems(
    items: List<ShowcaseGalleryDisplayItem>,
    state: ShowcaseGalleryUiState,
): List<ShowcaseGalleryDisplayItem> =
    items.filter { item ->
        val matchesSearch =
            state.searchQuery.isBlank() ||
                item.title.contains(state.searchQuery, ignoreCase = true) ||
                item.description.contains(state.searchQuery, ignoreCase = true) ||
                item.tagLabel.contains(state.searchQuery, ignoreCase = true)
        val matchesTag = state.selectedTag == ALL_TAG || item.entry.tags.any { tag -> tag.id == state.selectedTag }
        matchesSearch && matchesTag
    }

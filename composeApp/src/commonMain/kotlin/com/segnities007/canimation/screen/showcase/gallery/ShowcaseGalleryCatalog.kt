package com.segnities007.canimation.screen.showcase.gallery

import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.filter_all
import com.segnities007.canimation.screen.showcase.data.ShowcaseDemoType
import com.segnities007.canimation.screen.showcase.data.ShowcaseItem
import com.segnities007.canimation.screen.showcase.data.ShowcaseTagDescriptor
import com.segnities007.canimation.screen.showcase.data.ShowcaseTagId
import com.segnities007.canimation.screen.showcase.data.showcaseDemoTypeLabelRes
import com.segnities007.canimation.screen.showcase.data.showcaseCategories
import com.segnities007.canimation.screen.showcase.data.showcaseTagCatalog
import com.segnities007.canimation.screen.showcase.data.showcaseTagDescriptorsById
import org.jetbrains.compose.resources.StringResource

internal val ALL_TAG: ShowcaseTagId? = null
internal val ALL_DEMO_TYPE: ShowcaseDemoType? = null

internal data class ShowcaseGalleryFilterTag(
    val id: ShowcaseTagId?,
    val labelRes: StringResource,
    val accentTag: ShowcaseTagDescriptor? = null,
)

internal data class ShowcaseGalleryDemoTypeFilter(
    val demoType: ShowcaseDemoType?,
    val labelRes: StringResource,
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
    val demoTypeLabel: String,
)

internal data class ShowcaseGalleryCatalog(
    val entries: List<ShowcaseGalleryEntry>,
    val filterTags: List<ShowcaseGalleryFilterTag>,
    val demoTypeFilters: List<ShowcaseGalleryDemoTypeFilter>,
) {
    val totalCount: Int
        get() = entries.size

    fun entry(itemIndex: Int): ShowcaseGalleryEntry? = entries.getOrNull(itemIndex)
}

private val galleryTagCatalog =
    listOf(ShowcaseGalleryFilterTag(id = ALL_TAG, labelRes = Res.string.filter_all))
private val galleryDemoTypeCatalog =
    listOf(ShowcaseGalleryDemoTypeFilter(demoType = ALL_DEMO_TYPE, labelRes = Res.string.filter_all))

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

    val demoTypes = entries.map { entry -> entry.item.demoType }.toSet()
    val demoTypeFilters =
        galleryDemoTypeCatalog +
            ShowcaseDemoType.values()
                .filter { demoType -> demoType in demoTypes }
                .map { demoType ->
                    ShowcaseGalleryDemoTypeFilter(
                        demoType = demoType,
                        labelRes = showcaseDemoTypeLabelRes(demoType),
                    )
                }

    ShowcaseGalleryCatalog(
        entries = entries,
        filterTags = filterTags,
        demoTypeFilters = demoTypeFilters,
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
                item.tagLabel.contains(state.searchQuery, ignoreCase = true) ||
                item.demoTypeLabel.contains(state.searchQuery, ignoreCase = true)
        val matchesTag = state.selectedTag == ALL_TAG || item.entry.tags.any { tag -> tag.id == state.selectedTag }
        val matchesDemoType = state.selectedDemoType == ALL_DEMO_TYPE || item.entry.item.demoType == state.selectedDemoType
        matchesSearch && matchesTag && matchesDemoType
    }

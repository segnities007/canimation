package com.segnities007.canimation.screen.examples.gallery

import androidx.compose.ui.graphics.Color
import canimation.composeapp.generated.resources.*
import com.segnities007.canimation.screen.examples.data.ExampleItem
import com.segnities007.canimation.screen.examples.data.exampleCategories
import org.jetbrains.compose.resources.StringResource

internal const val ALL_TAG = "ALL"

internal data class ExampleGalleryFilterTag(
    val id: String,
    val labelRes: StringResource,
    val accentColor: Color? = null,
)

internal data class ExampleGalleryEntry(
    val item: ExampleItem,
    val tag: ExampleGalleryFilterTag,
    val globalIndex: Int,
)

internal data class ExampleGalleryDisplayItem(
    val entry: ExampleGalleryEntry,
    val title: String,
    val description: String,
    val tagLabel: String,
)

internal data class ExampleGalleryCatalog(
    val entries: List<ExampleGalleryEntry>,
    val filterTags: List<ExampleGalleryFilterTag>,
) {
    val totalCount: Int
        get() = entries.size

    fun entry(itemIndex: Int): ExampleGalleryEntry? = entries.getOrNull(itemIndex)
}

private val galleryTagCatalog =
    listOf(
        ExampleGalleryFilterTag(ALL_TAG, Res.string.filter_all),
        ExampleGalleryFilterTag("ENTRANCE", Res.string.examples_tag_entrance, Color(0xFF6366F1)),
        ExampleGalleryFilterTag("EMPHASIS", Res.string.examples_tag_emphasis, Color(0xFFEC4899)),
        ExampleGalleryFilterTag("PATTERN", Res.string.examples_tag_pattern, Color(0xFF8B5CF6)),
        ExampleGalleryFilterTag("MATERIAL", Res.string.examples_tag_material, Color(0xFF14B8A6)),
        ExampleGalleryFilterTag("DIRECTION", Res.string.examples_tag_direction, Color(0xFF3B82F6)),
        ExampleGalleryFilterTag("3D", Res.string.examples_tag_3d, Color(0xFFF97316)),
        ExampleGalleryFilterTag("UI", Res.string.examples_tag_ui, Color(0xFF22C55E)),
        ExampleGalleryFilterTag("TEXT", Res.string.examples_tag_text, Color(0xFFEAB308)),
        ExampleGalleryFilterTag("CARDS", Res.string.examples_tag_cards, Color(0xFFE11D48)),
        ExampleGalleryFilterTag("LOADING", Res.string.examples_tag_loading, Color(0xFF06B6D4)),
        ExampleGalleryFilterTag("DATA", Res.string.examples_tag_data, Color(0xFF7C3AED)),
        ExampleGalleryFilterTag("NAVIGATION", Res.string.examples_tag_navigation, Color(0xFF0EA5E9)),
        ExampleGalleryFilterTag("INTERACTIVE", Res.string.examples_tag_interactive, Color(0xFFF59E0B)),
        ExampleGalleryFilterTag("CANVAS", Res.string.examples_tag_canvas, Color(0xFFD946EF)),
        ExampleGalleryFilterTag("LAYOUT", Res.string.examples_tag_layout, Color(0xFF10B981)),
        ExampleGalleryFilterTag("SCALE", Res.string.examples_tag_scale, Color(0xFF6366F1)),
        ExampleGalleryFilterTag("MOVEMENT", Res.string.examples_tag_movement, Color(0xFF3B82F6)),
        ExampleGalleryFilterTag("ROTATE", Res.string.examples_tag_rotate, Color(0xFFF97316)),
        ExampleGalleryFilterTag("SUBTLE", Res.string.examples_tag_subtle, Color(0xFF94A3B8)),
        ExampleGalleryFilterTag("PHYSICS", Res.string.examples_tag_physics, Color(0xFF0891B2)),
        ExampleGalleryFilterTag("CHARTS", Res.string.examples_tag_charts, Color(0xFF7C3AED)),
        ExampleGalleryFilterTag("GALLERY", Res.string.examples_tag_gallery, Color(0xFFE11D48)),
        ExampleGalleryFilterTag("NAV", Res.string.examples_tag_nav, Color(0xFF0EA5E9)),
        ExampleGalleryFilterTag("VISUAL", Res.string.examples_tag_visual, Color(0xFFD946EF)),
    )

private val galleryTagsById = galleryTagCatalog.associateBy(ExampleGalleryFilterTag::id)

internal val exampleGalleryCatalog: ExampleGalleryCatalog by lazy {
    var index = 0
    val entries =
        exampleCategories.flatMap { category ->
            val filterTag = galleryTagsById.getValue(category.accentLabel)
            category.examples.map { example ->
                ExampleGalleryEntry(
                    item = example,
                    tag = filterTag,
                    globalIndex = index++,
                )
            }
        }

    ExampleGalleryCatalog(
        entries = entries,
        filterTags = listOf(galleryTagsById.getValue(ALL_TAG)) + entries.map { it.tag }.distinctBy(ExampleGalleryFilterTag::id),
    )
}

internal fun filterExampleGalleryItems(
    items: List<ExampleGalleryDisplayItem>,
    state: ExampleGalleryUiState,
): List<ExampleGalleryDisplayItem> =
    items.filter { item ->
        val matchesSearch =
            state.searchQuery.isBlank() ||
                item.title.contains(state.searchQuery, ignoreCase = true) ||
                item.description.contains(state.searchQuery, ignoreCase = true) ||
                item.tagLabel.contains(state.searchQuery, ignoreCase = true)
        val matchesTag = state.selectedTag == ALL_TAG || item.entry.tag.id == state.selectedTag
        matchesSearch && matchesTag
    }

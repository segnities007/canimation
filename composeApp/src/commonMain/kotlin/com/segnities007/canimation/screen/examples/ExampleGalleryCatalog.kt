package com.segnities007.canimation.screen.examples

import androidx.compose.ui.graphics.Color
import canimation.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource

internal const val ALL_TAG = "ALL"

private val tagLabelResources: Map<String, StringResource> = mapOf(
    ALL_TAG to Res.string.filter_all,
    "ENTRANCE" to Res.string.examples_tag_entrance,
    "EMPHASIS" to Res.string.examples_tag_emphasis,
    "PATTERN" to Res.string.examples_tag_pattern,
    "MATERIAL" to Res.string.examples_tag_material,
    "DIRECTION" to Res.string.examples_tag_direction,
    "3D" to Res.string.examples_tag_3d,
    "UI" to Res.string.examples_tag_ui,
    "TEXT" to Res.string.examples_tag_text,
    "CARDS" to Res.string.examples_tag_cards,
    "LOADING" to Res.string.examples_tag_loading,
    "DATA" to Res.string.examples_tag_data,
    "NAVIGATION" to Res.string.examples_tag_navigation,
    "INTERACTIVE" to Res.string.examples_tag_interactive,
    "CANVAS" to Res.string.examples_tag_canvas,
    "LAYOUT" to Res.string.examples_tag_layout,
    "SCALE" to Res.string.examples_tag_scale,
    "MOVEMENT" to Res.string.examples_tag_movement,
    "ROTATE" to Res.string.examples_tag_rotate,
    "SUBTLE" to Res.string.examples_tag_subtle,
    "PHYSICS" to Res.string.examples_tag_physics,
    "CHARTS" to Res.string.examples_tag_charts,
    "GALLERY" to Res.string.examples_tag_gallery,
    "NAV" to Res.string.examples_tag_nav,
    "VISUAL" to Res.string.examples_tag_visual,
)

private val tagColors: Map<String, Color> = mapOf(
    "ENTRANCE" to Color(0xFF6366F1),   // indigo
    "EMPHASIS" to Color(0xFFEC4899),    // pink
    "PATTERN" to Color(0xFF8B5CF6),     // violet
    "MATERIAL" to Color(0xFF14B8A6),    // teal
    "DIRECTION" to Color(0xFF3B82F6),   // blue
    "3D" to Color(0xFFF97316),          // orange
    "UI" to Color(0xFF22C55E),          // green
    "TEXT" to Color(0xFFEAB308),         // yellow
    "CARDS" to Color(0xFFE11D48),       // rose
    "LOADING" to Color(0xFF06B6D4),     // cyan
    "DATA" to Color(0xFF7C3AED),        // purple
    "NAVIGATION" to Color(0xFF0EA5E9),  // sky
    "INTERACTIVE" to Color(0xFFF59E0B), // amber
    "CANVAS" to Color(0xFFD946EF),      // fuchsia
    "LAYOUT" to Color(0xFF10B981),      // emerald
    "SCALE" to Color(0xFF6366F1),       // indigo
    "MOVEMENT" to Color(0xFF3B82F6),    // blue
    "ROTATE" to Color(0xFFF97316),      // orange
    "SUBTLE" to Color(0xFF94A3B8),      // slate
    "PHYSICS" to Color(0xFF0891B2),     // cyan-dark
    "CHARTS" to Color(0xFF7C3AED),      // purple
    "GALLERY" to Color(0xFFE11D48),     // rose
    "NAV" to Color(0xFF0EA5E9),         // sky
    "VISUAL" to Color(0xFFD946EF),      // fuchsia
)

internal fun tagColor(tag: String): Color = tagColors[tag] ?: Color(0xFF6366F1)

internal fun tagLabelRes(tag: String): StringResource = tagLabelResources.getValue(tag)

internal data class GalleryItem(
    val item: ExampleItem,
    val tag: String,
    val globalIndex: Int,
)

internal val allGalleryItems: List<GalleryItem> by lazy {
    var idx = 0
    exampleCategories.flatMap { category ->
        category.examples.map { example ->
            GalleryItem(
                item = example,
                tag = category.accentLabel,
                globalIndex = idx++,
            )
        }
    }
}

internal val filterTags: List<String> by lazy {
    listOf(ALL_TAG) + allGalleryItems.asSequence().map { it.tag }.distinct().toList()
}

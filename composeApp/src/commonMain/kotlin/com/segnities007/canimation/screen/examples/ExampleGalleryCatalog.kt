package com.segnities007.canimation.screen.examples

import androidx.compose.ui.graphics.Color

internal const val ALL_TAG = "ALL"

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

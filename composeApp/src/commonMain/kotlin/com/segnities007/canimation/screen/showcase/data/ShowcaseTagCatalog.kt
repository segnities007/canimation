package com.segnities007.canimation.screen.showcase.data

import androidx.compose.ui.graphics.Color
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.examples_tag_3d
import canimation.composeapp.generated.resources.examples_tag_canvas
import canimation.composeapp.generated.resources.examples_tag_cards
import canimation.composeapp.generated.resources.examples_tag_charts
import canimation.composeapp.generated.resources.examples_tag_data
import canimation.composeapp.generated.resources.examples_tag_direction
import canimation.composeapp.generated.resources.examples_tag_emphasis
import canimation.composeapp.generated.resources.examples_tag_entrance
import canimation.composeapp.generated.resources.examples_tag_gallery
import canimation.composeapp.generated.resources.examples_tag_interactive
import canimation.composeapp.generated.resources.examples_tag_layout
import canimation.composeapp.generated.resources.examples_tag_loading
import canimation.composeapp.generated.resources.examples_tag_material
import canimation.composeapp.generated.resources.examples_tag_movement
import canimation.composeapp.generated.resources.examples_tag_nav
import canimation.composeapp.generated.resources.examples_tag_navigation
import canimation.composeapp.generated.resources.examples_tag_pattern
import canimation.composeapp.generated.resources.examples_tag_physics
import canimation.composeapp.generated.resources.examples_tag_rotate
import canimation.composeapp.generated.resources.examples_tag_scale
import canimation.composeapp.generated.resources.examples_tag_subtle
import canimation.composeapp.generated.resources.examples_tag_text
import canimation.composeapp.generated.resources.examples_tag_ui
import canimation.composeapp.generated.resources.examples_tag_visual
import org.jetbrains.compose.resources.StringResource

internal enum class ShowcaseTagId {
    Entrance,
    Emphasis,
    Pattern,
    Material,
    Direction,
    ThreeDimensional,
    Ui,
    Text,
    Cards,
    Loading,
    Data,
    Navigation,
    Interactive,
    Canvas,
    Layout,
    Scale,
    Movement,
    Rotate,
    Subtle,
    Physics,
    Charts,
    Gallery,
    Nav,
    Visual,
}

internal data class ShowcaseTagDescriptor(
    val id: ShowcaseTagId,
    val labelRes: StringResource,
    val accentColor: Color,
)

internal val showcaseTagCatalog: List<ShowcaseTagDescriptor> =
    listOf(
        ShowcaseTagDescriptor(ShowcaseTagId.Entrance, Res.string.examples_tag_entrance, Color(0xFF6366F1)),
        ShowcaseTagDescriptor(ShowcaseTagId.Emphasis, Res.string.examples_tag_emphasis, Color(0xFFEC4899)),
        ShowcaseTagDescriptor(ShowcaseTagId.Pattern, Res.string.examples_tag_pattern, Color(0xFF8B5CF6)),
        ShowcaseTagDescriptor(ShowcaseTagId.Material, Res.string.examples_tag_material, Color(0xFF14B8A6)),
        ShowcaseTagDescriptor(ShowcaseTagId.Direction, Res.string.examples_tag_direction, Color(0xFF3B82F6)),
        ShowcaseTagDescriptor(ShowcaseTagId.ThreeDimensional, Res.string.examples_tag_3d, Color(0xFFF97316)),
        ShowcaseTagDescriptor(ShowcaseTagId.Ui, Res.string.examples_tag_ui, Color(0xFF22C55E)),
        ShowcaseTagDescriptor(ShowcaseTagId.Text, Res.string.examples_tag_text, Color(0xFFEAB308)),
        ShowcaseTagDescriptor(ShowcaseTagId.Cards, Res.string.examples_tag_cards, Color(0xFFE11D48)),
        ShowcaseTagDescriptor(ShowcaseTagId.Loading, Res.string.examples_tag_loading, Color(0xFF06B6D4)),
        ShowcaseTagDescriptor(ShowcaseTagId.Data, Res.string.examples_tag_data, Color(0xFF7C3AED)),
        ShowcaseTagDescriptor(ShowcaseTagId.Navigation, Res.string.examples_tag_navigation, Color(0xFF0EA5E9)),
        ShowcaseTagDescriptor(ShowcaseTagId.Interactive, Res.string.examples_tag_interactive, Color(0xFFF59E0B)),
        ShowcaseTagDescriptor(ShowcaseTagId.Canvas, Res.string.examples_tag_canvas, Color(0xFFD946EF)),
        ShowcaseTagDescriptor(ShowcaseTagId.Layout, Res.string.examples_tag_layout, Color(0xFF10B981)),
        ShowcaseTagDescriptor(ShowcaseTagId.Scale, Res.string.examples_tag_scale, Color(0xFF6366F1)),
        ShowcaseTagDescriptor(ShowcaseTagId.Movement, Res.string.examples_tag_movement, Color(0xFF3B82F6)),
        ShowcaseTagDescriptor(ShowcaseTagId.Rotate, Res.string.examples_tag_rotate, Color(0xFFF97316)),
        ShowcaseTagDescriptor(ShowcaseTagId.Subtle, Res.string.examples_tag_subtle, Color(0xFF94A3B8)),
        ShowcaseTagDescriptor(ShowcaseTagId.Physics, Res.string.examples_tag_physics, Color(0xFF0891B2)),
        ShowcaseTagDescriptor(ShowcaseTagId.Charts, Res.string.examples_tag_charts, Color(0xFF7C3AED)),
        ShowcaseTagDescriptor(ShowcaseTagId.Gallery, Res.string.examples_tag_gallery, Color(0xFFE11D48)),
        ShowcaseTagDescriptor(ShowcaseTagId.Nav, Res.string.examples_tag_nav, Color(0xFF0EA5E9)),
        ShowcaseTagDescriptor(ShowcaseTagId.Visual, Res.string.examples_tag_visual, Color(0xFFD946EF)),
    )

internal val showcaseTagDescriptorsById: Map<ShowcaseTagId, ShowcaseTagDescriptor> =
    showcaseTagCatalog.associateBy(ShowcaseTagDescriptor::id)

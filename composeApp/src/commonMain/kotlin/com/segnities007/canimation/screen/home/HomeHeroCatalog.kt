package com.segnities007.canimation.screen.home

import androidx.compose.ui.graphics.Color
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.hero_badge_accessible
import canimation.composeapp.generated.resources.hero_badge_atomic
import canimation.composeapp.generated.resources.hero_badge_effects
import canimation.composeapp.generated.resources.hero_badge_multiplatform
import canimation.composeapp.generated.resources.hero_badge_open_source
import canimation.composeapp.generated.resources.showcase_bounce_in
import canimation.composeapp.generated.resources.showcase_cinematic
import canimation.composeapp.generated.resources.showcase_diagonal_tl
import canimation.composeapp.generated.resources.showcase_drop_heavy
import canimation.composeapp.generated.resources.showcase_elastic_snap
import canimation.composeapp.generated.resources.showcase_fade_up
import canimation.composeapp.generated.resources.showcase_flip_in
import canimation.composeapp.generated.resources.showcase_float_up
import canimation.composeapp.generated.resources.showcase_glitch_in
import canimation.composeapp.generated.resources.showcase_rise_scale
import canimation.composeapp.generated.resources.showcase_scale_pop
import canimation.composeapp.generated.resources.showcase_shrink_in
import canimation.composeapp.generated.resources.showcase_spring_up
import canimation.composeapp.generated.resources.showcase_stretch_snap
import canimation.composeapp.generated.resources.showcase_tilt_swing
import canimation.composeapp.generated.resources.showcase_wave_gentle
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import org.jetbrains.compose.resources.StringResource

internal data class HomeHeroBadge(
    val labelRes: StringResource,
    val effect: CanimationEffect,
)

internal enum class HomeShowcaseShape {
    Circle,
    Pill,
    Star,
    Diamond,
    Text,
    Row,
    Button,
    Card,
    Badge,
    Icon,
    Avatar,
    Ring,
    Line,
    Dots,
    Tag,
    Chip,
}

internal data class HomeShowcaseItem(
    val nameRes: StringResource,
    val effect: CanimationEffect,
    val shape: HomeShowcaseShape,
    val accentColor: Color,
)

internal val homeHeroBadges: List<HomeHeroBadge> =
    listOf(
        HomeHeroBadge(labelRes = Res.string.hero_badge_effects, effect = Canimation.Fade.Up),
        HomeHeroBadge(labelRes = Res.string.hero_badge_atomic, effect = Canimation.Entrance.Elevate),
        HomeHeroBadge(labelRes = Res.string.hero_badge_accessible, effect = Canimation.Scale.In),
        HomeHeroBadge(labelRes = Res.string.hero_badge_multiplatform, effect = Canimation.Bounce.In),
        HomeHeroBadge(labelRes = Res.string.hero_badge_open_source, effect = Canimation.Scale.Pop),
    )

internal val homeLiveShowcaseItems: List<HomeShowcaseItem> =
    listOf(
        HomeShowcaseItem(Res.string.showcase_fade_up, Canimation.Fade.Up, HomeShowcaseShape.Circle, Color(0xFF6366F1)),
        HomeShowcaseItem(Res.string.showcase_scale_pop, Canimation.Scale.Pop, HomeShowcaseShape.Pill, Color(0xFFEC4899)),
        HomeShowcaseItem(Res.string.showcase_bounce_in, Canimation.Bounce.In, HomeShowcaseShape.Star, Color(0xFF14B8A6)),
        HomeShowcaseItem(Res.string.showcase_spring_up, Canimation.Spring.Up, HomeShowcaseShape.Diamond, Color(0xFFF59E0B)),
        HomeShowcaseItem(Res.string.showcase_flip_in, Canimation.Flip.In, HomeShowcaseShape.Text, Color(0xFF3B82F6)),
        HomeShowcaseItem(Res.string.showcase_diagonal_tl, Canimation.Diagonal.TopLeft, HomeShowcaseShape.Row, Color(0xFF8B5CF6)),
        HomeShowcaseItem(Res.string.showcase_drop_heavy, Canimation.Drop.Heavy, HomeShowcaseShape.Button, Color(0xFF22C55E)),
        HomeShowcaseItem(Res.string.showcase_tilt_swing, Canimation.Tilt.Swing, HomeShowcaseShape.Card, Color(0xFFE11D48)),
        HomeShowcaseItem(Res.string.showcase_elastic_snap, Canimation.Elastic.Snap, HomeShowcaseShape.Badge, Color(0xFF06B6D4)),
        HomeShowcaseItem(Res.string.showcase_rise_scale, Canimation.Rise.Scale, HomeShowcaseShape.Icon, Color(0xFFF97316)),
        HomeShowcaseItem(Res.string.showcase_float_up, Canimation.Float.Up, HomeShowcaseShape.Avatar, Color(0xFF7C3AED)),
        HomeShowcaseItem(Res.string.showcase_shrink_in, Canimation.Shrink.Out, HomeShowcaseShape.Ring, Color(0xFF0EA5E9)),
        HomeShowcaseItem(Res.string.showcase_stretch_snap, Canimation.Stretch.Snap, HomeShowcaseShape.Line, Color(0xFFD946EF)),
        HomeShowcaseItem(Res.string.showcase_wave_gentle, Canimation.Wave.Gentle, HomeShowcaseShape.Dots, Color(0xFF10B981)),
        HomeShowcaseItem(Res.string.showcase_glitch_in, Canimation.Glitch.In, HomeShowcaseShape.Tag, Color(0xFFEF4444)),
        HomeShowcaseItem(Res.string.showcase_cinematic, Canimation.Cinematic.Dolly, HomeShowcaseShape.Chip, Color(0xFF0891B2)),
    )

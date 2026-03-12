package com.segnities007.canimation.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberLoopingVisibility
import com.segnities007.canimation.navigation.HomeDestination
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import canimation.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

// ======= HERO =======

@Composable
internal fun HeroSection(stage: Int, presetCount: Int, onNavigate: (HomeDestination) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(listOf(MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.background)),
            ),
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .widthIn(max = 900.dp)
                    .padding(horizontal = 32.dp, vertical = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Box(Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up)) {
                    Text(
                        text = stringResource(Res.string.app_title),
                        style = MaterialTheme.typography.displayLarge.copy(
                            brush = Brush.linearGradient(
                                listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimaryContainer, MaterialTheme.colorScheme.secondary),
                            ),
                        ),
                        fontWeight = FontWeight.Bold,
                    )
                }

                Box(Modifier.canimation(visible = stage >= 1, effect = Canimation.Fade.Up)) {
                    Text(
                        text = stringResource(Res.string.hero_subtitle),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )
                }

                Spacer(Modifier.height(4.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                ) {
                    listOf(
                        stringResource(Res.string.hero_badge_effects) to Canimation.Fade.Up,
                        stringResource(Res.string.hero_badge_atomic) to Canimation.Entrance.Elevate,
                        stringResource(Res.string.hero_badge_accessible) to Canimation.Scale.In,
                        stringResource(Res.string.hero_badge_multiplatform) to Canimation.Bounce.In,
                        stringResource(Res.string.hero_badge_open_source) to Canimation.Scale.Pop,
                    ).forEachIndexed { index, (label, effect) ->
                        Box(Modifier.canimation(visible = stage >= 2 + index, effect = effect)) {
                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.06f),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            ) {
                                Text(
                                    text = label,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                Box(Modifier.canimation(visible = stage >= 7, effect = Canimation.Scale.Pop)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(onClick = { onNavigate(HomeDestination.Docs) }) {
                            Text(stringResource(Res.string.hero_get_started))
                        }
                        FilledTonalButton(onClick = { onNavigate(HomeDestination.ShowcaseGallery) }) {
                            Text(stringResource(Res.string.hero_examples))
                        }
                    }
                }
            }
        }
    }
}

// ======= LIVE SHOWCASE =======

@Composable
internal fun LiveShowcaseSection(stage: Int) {
    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
        DarkCenteredSection {
            Box(Modifier.canimation(visible = stage >= 7, effect = Canimation.Fade.Up)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(Res.string.showcase_label),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = stringResource(Res.string.showcase_title),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            data class ShowcaseItem(val name: String, val effect: CanimationEffect, val shape: String, val color: Color)
            val showcaseItems = listOf(
                ShowcaseItem(stringResource(Res.string.showcase_fade_up), Canimation.Fade.Up, "circle", Color(0xFF6366F1)),
                ShowcaseItem(stringResource(Res.string.showcase_scale_pop), Canimation.Scale.Pop, "pill", Color(0xFFEC4899)),
                ShowcaseItem(stringResource(Res.string.showcase_bounce_in), Canimation.Bounce.In, "star", Color(0xFF14B8A6)),
                ShowcaseItem(stringResource(Res.string.showcase_spring_up), Canimation.Spring.Up, "diamond", Color(0xFFF59E0B)),
                ShowcaseItem(stringResource(Res.string.showcase_flip_in), Canimation.Flip.In, "text", Color(0xFF3B82F6)),
                ShowcaseItem(stringResource(Res.string.showcase_diagonal_tl), Canimation.Diagonal.TopLeft, "row", Color(0xFF8B5CF6)),
                ShowcaseItem(stringResource(Res.string.showcase_drop_heavy), Canimation.Drop.Heavy, "button", Color(0xFF22C55E)),
                ShowcaseItem(stringResource(Res.string.showcase_tilt_swing), Canimation.Tilt.Swing, "card", Color(0xFFE11D48)),
                ShowcaseItem(stringResource(Res.string.showcase_elastic_snap), Canimation.Elastic.Snap, "badge", Color(0xFF06B6D4)),
                ShowcaseItem(stringResource(Res.string.showcase_rise_scale), Canimation.Rise.Scale, "icon", Color(0xFFF97316)),
                ShowcaseItem(stringResource(Res.string.showcase_float_up), Canimation.Float.Up, "avatar", Color(0xFF7C3AED)),
                ShowcaseItem(stringResource(Res.string.showcase_shrink_in), Canimation.Shrink.Out, "ring", Color(0xFF0EA5E9)),
                ShowcaseItem(stringResource(Res.string.showcase_stretch_snap), Canimation.Stretch.Snap, "line", Color(0xFFD946EF)),
                ShowcaseItem(stringResource(Res.string.showcase_wave_gentle), Canimation.Wave.Gentle, "dots", Color(0xFF10B981)),
                ShowcaseItem(stringResource(Res.string.showcase_glitch_in), Canimation.Glitch.In, "tag", Color(0xFFEF4444)),
                ShowcaseItem(stringResource(Res.string.showcase_cinematic), Canimation.Cinematic.Dolly, "chip", Color(0xFF0891B2)),
            )
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                showcaseItems.chunked(4).forEachIndexed { rowIndex, row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        row.forEachIndexed { colIndex, si ->
                            AnimatedEffectCell(
                                name = si.name,
                                effect = si.effect,
                                shape = si.shape,
                                accent = si.color,
                                delayMs = ((rowIndex * 4 + colIndex) * 200L),
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun AnimatedEffectCell(
    name: String,
    effect: CanimationEffect,
    shape: String,
    accent: Color,
    delayMs: Long,
    modifier: Modifier = Modifier,
) {
    val visible = rememberLoopingVisibility(
        initialDelayMillis = delayMs,
        visibleDurationMillis = 2000L,
        hiddenDurationMillis = 600L,
    )

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, accent.copy(alpha = 0.3f)),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(12.dp).height(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Box(Modifier.canimation(visible = visible, effect = effect)) {
                    ShowcaseShape(shape, accent)
                }
            }
            Text(
                text = name,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
internal fun ShowcaseShape(shape: String, accent: Color) {
    when (shape) {
        "circle" -> Surface(shape = CircleShape, color = accent.copy(alpha = 0.25f),
            border = BorderStroke(1.dp, accent.copy(alpha = 0.5f)), modifier = Modifier.size(40.dp)) {}
        "pill" -> Surface(shape = RoundedCornerShape(50), color = accent.copy(alpha = 0.2f),
            border = BorderStroke(1.dp, accent.copy(alpha = 0.4f)), modifier = Modifier.size(56.dp, 28.dp)) {}
        "star" -> Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = accent,
            modifier = Modifier.size(24.dp),
        )
        "diamond" -> Surface(shape = RoundedCornerShape(4.dp), color = accent.copy(alpha = 0.25f),
            border = BorderStroke(1.dp, accent.copy(alpha = 0.5f)),
            modifier = Modifier.size(32.dp).graphicsLayer { rotationZ = 45f }) {}
        "text" -> Text(stringResource(Res.string.showcase_demo_abc), style = MaterialTheme.typography.titleMedium, color = accent, fontWeight = FontWeight.Bold)
        "row" -> Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(3) { Box(Modifier.size(12.dp).background(accent.copy(alpha = 0.4f), RoundedCornerShape(3.dp))) }
        }
        "button" -> Surface(shape = RoundedCornerShape(8.dp), color = accent) {
            Text(stringResource(Res.string.showcase_demo_click), Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelSmall, color = Color.White)
        }
        "card" -> Surface(shape = RoundedCornerShape(8.dp), color = accent.copy(alpha = 0.15f),
            border = BorderStroke(1.dp, accent.copy(alpha = 0.3f)), modifier = Modifier.size(48.dp, 36.dp)) {
            Column(Modifier.padding(6.dp)) {
                Box(Modifier.fillMaxWidth().height(4.dp).background(accent.copy(alpha = 0.4f), RoundedCornerShape(2.dp)))
                Spacer(Modifier.height(4.dp))
                Box(Modifier.fillMaxWidth(0.6f).height(3.dp).background(accent.copy(alpha = 0.2f), RoundedCornerShape(2.dp)))
            }
        }
        "badge" -> Surface(shape = RoundedCornerShape(12.dp), color = accent) {
            Text(stringResource(Res.string.showcase_demo_new), Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                style = MaterialTheme.typography.labelSmall, color = Color.White, fontWeight = FontWeight.Bold)
        }
        "icon" -> Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = accent,
            modifier = Modifier.size(24.dp),
        )
        "avatar" -> Surface(shape = CircleShape, color = accent, modifier = Modifier.size(36.dp)) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(stringResource(Res.string.showcase_demo_avatar), style = MaterialTheme.typography.labelLarge, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
        "ring" -> Box(Modifier.size(40.dp).background(Color.Transparent, CircleShape)
            .then(Modifier.background(accent.copy(alpha = 0.0f), CircleShape)),
            contentAlignment = Alignment.Center) {
            Surface(shape = CircleShape, color = Color.Transparent, border = BorderStroke(3.dp, accent),
                modifier = Modifier.size(36.dp)) {}
        }
        "line" -> Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Box(Modifier.size(40.dp, 4.dp).background(accent.copy(alpha = 0.6f), RoundedCornerShape(2.dp)))
            Box(Modifier.size(28.dp, 4.dp).background(accent.copy(alpha = 0.3f), RoundedCornerShape(2.dp)))
        }
        "dots" -> Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            repeat(3) { Surface(shape = CircleShape, color = accent.copy(alpha = 0.5f + it * 0.15f), modifier = Modifier.size(10.dp)) {} }
        }
        "tag" -> Surface(shape = RoundedCornerShape(4.dp), color = accent.copy(alpha = 0.15f),
            border = BorderStroke(1.dp, accent.copy(alpha = 0.4f))) {
            Text(stringResource(Res.string.showcase_demo_tag), Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                style = MaterialTheme.typography.labelSmall, color = accent)
        }
        "chip" -> Surface(shape = RoundedCornerShape(16.dp), color = accent.copy(alpha = 0.15f)) {
            Row(Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Surface(shape = CircleShape, color = accent, modifier = Modifier.size(8.dp)) {}
                Text(stringResource(Res.string.showcase_demo_label), style = MaterialTheme.typography.labelSmall, color = accent)
            }
        }
        else -> Surface(shape = RoundedCornerShape(8.dp), color = accent.copy(alpha = 0.25f),
            modifier = Modifier.size(40.dp)) {}
    }
}

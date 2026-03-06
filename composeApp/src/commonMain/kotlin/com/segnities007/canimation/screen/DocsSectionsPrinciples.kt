package com.segnities007.canimation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.canimation
import io.github.canimation.core.canimationEmphasize
import io.github.canimation.core.canimationEnter
import canimation.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import kotlinx.coroutines.delay

// ━━━━━━━━━━━━━━━━━━━━━━━━ Core Principles ━━━━━━━━━━━━━━━━━━━━━━━━

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun CorePrinciplesContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        PageTitle(stringResource(Res.string.docs_principles_title), stringResource(Res.string.docs_principles_desc))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PrincipleCard(
                Modifier.weight(1f),
                icon = Icons.Default.Tune,
                title = stringResource(Res.string.docs_composability_title),
                body = stringResource(Res.string.docs_composability_body),
                effect = Canimation.Fade.Up,
            )
            PrincipleCard(
                Modifier.weight(1f),
                icon = Icons.Default.AccessibilityNew,
                title = stringResource(Res.string.docs_a11y_title),
                body = stringResource(Res.string.docs_a11y_body),
                effect = Canimation.Scale.Pop,
            )
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PrincipleCard(
                Modifier.weight(1f),
                icon = Icons.Default.Lightbulb,
                title = stringResource(Res.string.docs_progressive_title),
                body = stringResource(Res.string.docs_progressive_body),
                effect = Canimation.Slide.Up,
            )
            PrincipleCard(
                Modifier.weight(1f),
                icon = Icons.Default.Visibility,
                title = stringResource(Res.string.docs_show_title),
                body = stringResource(Res.string.docs_show_body),
                effect = Canimation.Bounce.In,
            )
        }
    }
}

@Composable
private fun PrincipleCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    body: String,
    effect: CanimationEffect,
) {
    var pulse by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { delay(500); while (true) { pulse = true; delay(3000); pulse = false; delay(800) } }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
    ) {
        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                    modifier = Modifier.size(36.dp).canimation(visible = pulse, effect = effect),
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                    }
                }
                Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
            }
            Text(body, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 18.sp)
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Inspiration ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
internal fun InspirationContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        PageTitle(stringResource(Res.string.docs_inspiration_title), stringResource(Res.string.docs_inspiration_desc))

        BodyText(stringResource(Res.string.docs_inspiration_intro))

        InspirationCard(
            name = stringResource(Res.string.docs_inspiration_motion_dev),
            lesson = stringResource(Res.string.docs_inspiration_motion_dev_lesson),
            demoEffect = Canimation.Fade.Up + CanimationEffect.scale(0.95f),
        )
        InspirationCard(
            name = stringResource(Res.string.docs_inspiration_animate_css),
            lesson = stringResource(Res.string.docs_inspiration_animate_css_lesson),
            demoEffect = Canimation.Bounce.In,
        )
        InspirationCard(
            name = stringResource(Res.string.docs_inspiration_animxyz),
            lesson = stringResource(Res.string.docs_inspiration_animxyz_lesson),
            demoEffect = Canimation.Slide.Left + Canimation.Fade.In,
        )
        InspirationCard(
            name = stringResource(Res.string.docs_inspiration_material),
            lesson = stringResource(Res.string.docs_inspiration_material_lesson),
            demoEffect = Canimation.Material.FadeThrough,
        )
    }
}

@Composable
private fun InspirationCard(name: String, lesson: String, demoEffect: CanimationEffect) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { delay(300); while (true) { vis = true; delay(2800); vis = false; delay(700) } }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            // Live demo
            Box(
                Modifier.size(80.dp).clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center,
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                    modifier = Modifier.size(40.dp).canimation(visible = vis, effect = demoEffect),
                ) {}
            }
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text(lesson, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 18.sp)
            }
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ What Sets Us Apart ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
internal fun DifferentiatorsContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PageTitle(stringResource(Res.string.docs_differentiators_title), stringResource(Res.string.docs_differentiators_desc))

        val items = listOf(
            Triple(stringResource(Res.string.docs_diff_compose_native), stringResource(Res.string.docs_diff_compose_native_desc), Canimation.Fade.Up),
            Triple(stringResource(Res.string.docs_diff_multiplatform), stringResource(Res.string.docs_diff_multiplatform_desc), Canimation.Scale.Pop),
            Triple(stringResource(Res.string.docs_diff_algebra), stringResource(Res.string.docs_diff_algebra_desc), Canimation.Slide.Left + Canimation.Fade.In),
            Triple(stringResource(Res.string.docs_diff_atomic), stringResource(Res.string.docs_diff_atomic_desc), Canimation.Entrance.Elevate),
            Triple(stringResource(Res.string.docs_diff_a11y), stringResource(Res.string.docs_diff_a11y_desc), Canimation.Fade.Gentle),
        )

        items.forEachIndexed { i, (title, desc, effect) ->
            var vis by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { delay(i * 200L + 400L); vis = true }
            DifferentiatorRow(title, desc, effect, vis)
        }
    }
}

@Composable
private fun DifferentiatorRow(title: String, desc: String, effect: CanimationEffect, visible: Boolean) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth().canimation(visible = visible, effect = Canimation.Fade.Up),
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            var pulse by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { while (true) { pulse = true; delay(2500); pulse = false; delay(700) } }
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                modifier = Modifier.size(48.dp).canimation(visible = pulse, effect = effect),
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.AutoAwesome, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                }
            }
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text(desc, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 18.sp)
            }
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Vision & Roadmap ━━━━━━━━━━━━━━━━━━━━━━━━

// ━━━━━━━━━━━━━━━━━━━━━━━━ Roadmap ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
internal fun RoadmapContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        PageTitle(stringResource(Res.string.docs_roadmap_title), stringResource(Res.string.docs_roadmap_desc))

        BodyText(stringResource(Res.string.docs_roadmap_intro))

        SectionLabel(stringResource(Res.string.docs_roadmap_now))
        RoadmapItem(stringResource(Res.string.docs_roadmap_now_1))
        RoadmapItem(stringResource(Res.string.docs_roadmap_now_2))
        RoadmapItem(stringResource(Res.string.docs_roadmap_now_3))
        RoadmapItem(stringResource(Res.string.docs_roadmap_now_4))
        RoadmapItem(stringResource(Res.string.docs_roadmap_now_5))

        SectionLabel(stringResource(Res.string.docs_roadmap_next))
        RoadmapItem(stringResource(Res.string.docs_roadmap_next_1))
        RoadmapItem(stringResource(Res.string.docs_roadmap_next_2))
        RoadmapItem(stringResource(Res.string.docs_roadmap_next_3))
        RoadmapItem(stringResource(Res.string.docs_roadmap_next_4))

        SectionLabel(stringResource(Res.string.docs_roadmap_future))
        RoadmapItem(stringResource(Res.string.docs_roadmap_future_1))
        RoadmapItem(stringResource(Res.string.docs_roadmap_future_2))
        RoadmapItem(stringResource(Res.string.docs_roadmap_future_3))
    }
}

@Composable
private fun RoadmapItem(text: String) {
    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp).padding(top = 2.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 22.sp)
    }
}

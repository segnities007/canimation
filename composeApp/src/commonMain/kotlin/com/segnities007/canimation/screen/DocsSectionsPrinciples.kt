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
import kotlinx.coroutines.delay

// ━━━━━━━━━━━━━━━━━━━━━━━━ Core Principles ━━━━━━━━━━━━━━━━━━━━━━━━

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun CorePrinciplesContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        PageTitle("Core Principles", "The values that shape every API decision")

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PrincipleCard(
                Modifier.weight(1f),
                icon = Icons.Default.Tune,
                title = "Composability Over Configuration",
                body = "Instead of massive parameter lists, combine small focused effects with +. " +
                       "CanimationEffect.fade() + CanimationEffect.slideUp() is more expressive than " +
                       "any config object.",
                effect = Canimation.Fade.Up,
            )
            PrincipleCard(
                Modifier.weight(1f),
                icon = Icons.Default.AccessibilityNew,
                title = "Accessibility Is Not Optional",
                body = "Every animation respects CanimationPolicy. When the OS says 'reduce motion', " +
                       "we listen automatically. No extra code. No opt-in.",
                effect = Canimation.Scale.Pop,
            )
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PrincipleCard(
                Modifier.weight(1f),
                icon = Icons.Default.Lightbulb,
                title = "Progressive Disclosure",
                body = "Start with Modifier.canimation(visible, Canimation.Fade.Up). Need more? " +
                       "Use CanimationEffect primitives. Need full control? Drop to CanimationSpec.",
                effect = Canimation.Slide.Up,
            )
            PrincipleCard(
                Modifier.weight(1f),
                icon = Icons.Default.Visibility,
                title = "Show, Don't Tell",
                body = "This entire documentation site is built with canimation — every card entrance, " +
                       "every demo uses the same API you'll use in your app.",
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
        PageTitle("Inspiration & References", "Standing on the shoulders of giants")

        BodyText(
            "canimation didn't emerge in a vacuum. We carefully studied the animation ecosystem " +
            "across web, mobile, and design systems."
        )

        InspirationCard(
            name = "Motion.dev (Framer Motion)",
            lesson = "The composable, declarative API pattern. Our CanimationEffect + operator " +
                     "and the Canimation.* namespace are directly inspired by this philosophy.",
            demoEffect = Canimation.Fade.Up + CanimationEffect.scale(0.95f),
        )
        InspirationCard(
            name = "Animate.css",
            lesson = "The power of named presets. Our 100+ CanimationPreset entries " +
                     "follow this exact pattern — one line, done.",
            demoEffect = Canimation.Bounce.In,
        )
        InspirationCard(
            name = "AnimXYZ",
            lesson = "Compositional CSS animation utilities with multi-axis control. " +
                     "Influenced our enter/exit distinction and multi-axis composition.",
            demoEffect = Canimation.Slide.Left + Canimation.Fade.In,
        )
        InspirationCard(
            name = "Material Design Motion",
            lesson = "Meaningful transitions with container transform and shared axis. " +
                     "Our Material.* namespace comes directly from Material Motion.",
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
        PageTitle("What Sets canimation Apart", "More than just another animation library")

        val items = listOf(
            Triple("Compose-Native", "Built from the ground up for Compose's graphicsLayer, " +
                "state-driven rendering, and recomposition model.", Canimation.Fade.Up),
            Triple("Truly Multiplatform", "One API, five targets. Android, iOS, Desktop, Web (JS), Web (WasmJs).", Canimation.Scale.Pop),
            Triple("Effect Algebra", "The + operator composes effects predictably: properties from the right " +
                "override the left, duration takes the maximum.", Canimation.Slide.Left + Canimation.Fade.In),
            Triple("Atomic Design", "Organized as Atoms (Fade.In), Molecules (Fade.Up), Organisms (Entrance.Drop). " +
                "Pick the complexity level you need.", Canimation.Entrance.Elevate),
            Triple("A11y by Default", "CanimationProvider + CanimationPolicy means accessibility is the default behavior.", Canimation.Fade.Gentle),
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
        PageTitle("Vision & Roadmap", "Where canimation is heading")

        BodyText(
            "canimation aims to be the definitive animation toolkit for Compose Multiplatform."
        )

        SectionLabel("NOW")
        RoadmapItem("100+ animation effects across 13 categories (Atoms, Molecules, Organisms)")
        RoadmapItem("CanimationEffect DSL with algebraic + composition")
        RoadmapItem("Atomic Design organization: Fade, Scale, Slide, Rotate, Bounce, Spring, Flip, Zoom, Attention, Entrance, Material, Morph")
        RoadmapItem("Full accessibility support with CanimationPolicy")
        RoadmapItem("Five-platform support: Android, iOS, Desktop, Web JS, Web WasmJs")

        SectionLabel("NEXT")
        RoadmapItem("Layout animations — shared element transitions and container transforms")
        RoadmapItem("Gesture-driven animations — spring-based drag, swipe, and fling")
        RoadmapItem("Scroll-linked animations — parallax, reveal, and pinning")
        RoadmapItem("Stagger orchestration — automatic stagger for list items")

        SectionLabel("FUTURE")
        RoadmapItem("Visual animation editor — design animations graphically, export as code")
        RoadmapItem("AI-assisted motion — suggest appropriate animations based on context")
        RoadmapItem("Design system integration — Material 3, iOS HIG, and custom token presets")
    }
}

@Composable
private fun RoadmapItem(text: String) {
    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp).padding(top = 2.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 22.sp)
    }
}

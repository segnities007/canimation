package com.segnities007.canimation.screen

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
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val presetCount = CanimationPreset.entries.size
    var stage by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        for (i in 0..11) { delay(70); stage = i }
    }

    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()),
        ) {
            HeroSection(stage = stage, presetCount = presetCount, onNavigate = onNavigate)
            LiveShowcaseSection(stage = stage)
            FeaturesSection(stage = stage, presetCount = presetCount)
            CodeExampleSection(stage = stage)
            ExploreSection(onNavigate = onNavigate)
            PlatformSection(stage = stage)
            FooterSection()
        }
    }
}

// ======= HERO =======

@Composable
private fun HeroSection(stage: Int, presetCount: Int, onNavigate: (String) -> Unit) {
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
                        text = "canimation",
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
                        text = "A production-grade animation library\nfor Compose Multiplatform.",
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
                        "100+ Effects" to Canimation.Fade.Up,
                        "Atomic Design" to Canimation.Entrance.Elevate,
                        "Accessible" to Canimation.Scale.In,
                        "Multiplatform" to Canimation.Bounce.In,
                        "Open Source" to Canimation.Scale.Pop,
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
                        Button(onClick = { onNavigate("docs") }) {
                            Text("Get Started")
                        }
                        FilledTonalButton(onClick = { onNavigate("examples") }) {
                            Text("Examples")
                        }
                    }
                }
            }
        }
    }
}

// ======= LIVE SHOWCASE =======

@Composable
private fun LiveShowcaseSection(stage: Int) {
    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
        DarkCenteredSection {
            Box(Modifier.canimation(visible = stage >= 7, effect = Canimation.Fade.Up)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "SHOWCASE",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Animations that move",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            data class ShowcaseItem(val name: String, val effect: CanimationEffect, val shape: String, val color: Color)
            val showcaseItems = listOf(
                ShowcaseItem("Fade Up", Canimation.Fade.Up, "circle", Color(0xFF6366F1)),
                ShowcaseItem("Scale Pop", Canimation.Scale.Pop, "pill", Color(0xFFEC4899)),
                ShowcaseItem("Bounce In", Canimation.Bounce.In, "star", Color(0xFF14B8A6)),
                ShowcaseItem("Spring Up", Canimation.Spring.Up, "diamond", Color(0xFFF59E0B)),
                ShowcaseItem("Flip In", Canimation.Flip.In, "text", Color(0xFF3B82F6)),
                ShowcaseItem("Diagonal TL", Canimation.Diagonal.TopLeft, "row", Color(0xFF8B5CF6)),
                ShowcaseItem("Drop Heavy", Canimation.Drop.Heavy, "button", Color(0xFF22C55E)),
                ShowcaseItem("Tilt Swing", Canimation.Tilt.Swing, "card", Color(0xFFE11D48)),
                ShowcaseItem("Elastic Snap", Canimation.Elastic.Snap, "badge", Color(0xFF06B6D4)),
                ShowcaseItem("Rise Scale", Canimation.Rise.Scale, "icon", Color(0xFFF97316)),
                ShowcaseItem("Float Up", Canimation.Float.Up, "avatar", Color(0xFF7C3AED)),
                ShowcaseItem("Shrink In", Canimation.Shrink.Out, "ring", Color(0xFF0EA5E9)),
                ShowcaseItem("Stretch Snap", Canimation.Stretch.Snap, "line", Color(0xFFD946EF)),
                ShowcaseItem("Wave Gentle", Canimation.Wave.Gentle, "dots", Color(0xFF10B981)),
                ShowcaseItem("Glitch In", Canimation.Glitch.In, "tag", Color(0xFFEF4444)),
                ShowcaseItem("Cinematic", Canimation.Cinematic.Dolly, "chip", Color(0xFF0891B2)),
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
private fun AnimatedEffectCell(
    name: String,
    effect: CanimationEffect,
    shape: String,
    accent: Color,
    delayMs: Long,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(delayMs)
        while (true) {
            visible = true
            delay(2000)
            visible = false
            delay(600)
        }
    }

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
private fun ShowcaseShape(shape: String, accent: Color) {
    when (shape) {
        "circle" -> Surface(shape = CircleShape, color = accent.copy(alpha = 0.25f),
            border = BorderStroke(1.dp, accent.copy(alpha = 0.5f)), modifier = Modifier.size(40.dp)) {}
        "pill" -> Surface(shape = RoundedCornerShape(50), color = accent.copy(alpha = 0.2f),
            border = BorderStroke(1.dp, accent.copy(alpha = 0.4f)), modifier = Modifier.size(56.dp, 28.dp)) {}
        "star" -> Text("✦", style = MaterialTheme.typography.headlineMedium, color = accent)
        "diamond" -> Surface(shape = RoundedCornerShape(4.dp), color = accent.copy(alpha = 0.25f),
            border = BorderStroke(1.dp, accent.copy(alpha = 0.5f)),
            modifier = Modifier.size(32.dp).graphicsLayer { rotationZ = 45f }) {}
        "text" -> Text("Abc", style = MaterialTheme.typography.titleMedium, color = accent, fontWeight = FontWeight.Bold)
        "row" -> Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(3) { Box(Modifier.size(12.dp).background(accent.copy(alpha = 0.4f), RoundedCornerShape(3.dp))) }
        }
        "button" -> Surface(shape = RoundedCornerShape(8.dp), color = accent) {
            Text("Click", Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
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
            Text("NEW", Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                style = MaterialTheme.typography.labelSmall, color = Color.White, fontWeight = FontWeight.Bold)
        }
        "icon" -> Text("◈", style = MaterialTheme.typography.headlineMedium, color = accent)
        "avatar" -> Surface(shape = CircleShape, color = accent, modifier = Modifier.size(36.dp)) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("A", style = MaterialTheme.typography.labelLarge, color = Color.White, fontWeight = FontWeight.Bold)
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
            Text("#tag", Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                style = MaterialTheme.typography.labelSmall, color = accent)
        }
        "chip" -> Surface(shape = RoundedCornerShape(16.dp), color = accent.copy(alpha = 0.15f)) {
            Row(Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Surface(shape = CircleShape, color = accent, modifier = Modifier.size(8.dp)) {}
                Text("Label", style = MaterialTheme.typography.labelSmall, color = accent)
            }
        }
        else -> Surface(shape = RoundedCornerShape(8.dp), color = accent.copy(alpha = 0.25f),
            modifier = Modifier.size(40.dp)) {}
    }
}

// ======= FEATURES WITH LIVE DEMOS =======

@Composable
private fun FeaturesSection(stage: Int, presetCount: Int) {
    DarkCenteredSection {
        Box(Modifier.canimation(visible = stage >= 8, effect = Canimation.Fade.Up)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "WHY CANIMATION",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Built for every use case",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .canimation(visible = stage >= 9, effect = Canimation.Fade.Up),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                LiveFeatureCard(
                    title = "$presetCount Presets",
                    subtitle = "Bounce, Spring, Flip, Material Motion and more",
                    demoPreset = CanimationPreset.BounceIn,
                    demoDelay = 0L,
                    modifier = Modifier.weight(1f),
                )
                LiveFeatureCard(
                    title = "Accessibility",
                    subtitle = "System-aware Reduce Motion policies",
                    demoPreset = CanimationPreset.GentleFade,
                    demoDelay = 400L,
                    modifier = Modifier.weight(1f),
                )
                LiveFeatureCard(
                    title = "Diagnostics",
                    subtitle = "Real-time FPS and jank overlay",
                    demoPreset = CanimationPreset.Pulse,
                    demoDelay = 800L,
                    modifier = Modifier.weight(1f),
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .canimation(visible = stage >= 10, effect = Canimation.Spring.Up),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                LiveFeatureCard(
                    title = "Testable",
                    subtitle = "Deterministic test clock for assertions",
                    demoPreset = CanimationPreset.Snap,
                    demoDelay = 200L,
                    modifier = Modifier.weight(1f),
                )
                LiveFeatureCard(
                    title = "Multiplatform",
                    subtitle = "Android, iOS, Desktop, and Web",
                    demoPreset = CanimationPreset.SpringSlideUp,
                    demoDelay = 600L,
                    modifier = Modifier.weight(1f),
                )
                LiveFeatureCard(
                    title = "Token System",
                    subtitle = "Duration, easing, distance, spring tokens",
                    demoPreset = CanimationPreset.SharedAxisY,
                    demoDelay = 1000L,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun LiveFeatureCard(
    title: String,
    subtitle: String,
    demoPreset: CanimationPreset,
    demoDelay: Long,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(demoDelay + 500L)
        while (true) { visible = true; delay(2000); visible = false; delay(700) }
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(64.dp),
                contentAlignment = Alignment.Center,
            ) {
                CanimationVisibility(
                    visible = visible,
                    enterPreset = demoPreset,
                    exitPreset = demoPreset,
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                    ) {
                        Box(Modifier.size(width = 48.dp, height = 32.dp))
                    }
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

// ======= CODE EXAMPLE =======

@Composable
private fun CodeExampleSection(stage: Int) {
    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
        DarkCenteredSection {
            Box(Modifier.canimation(visible = stage >= 9, effect = Canimation.Fade.Up)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "QUICK START",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Simple, expressive API",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier.fillMaxWidth()
                    .canimation(visible = stage >= 10, effect = Canimation.Scale.FadeIn),
            ) {
                Text(
                    text = """
                        |@Composable
                        |fun App() {
                        |    CanimationProvider(
                        |        policy = CanimationPolicy.SystemAware
                        |    ) {
                        |        // ✨ Recommended: Effect API
                        |        Box(
                        |            Modifier.canimation(
                        |                visible = isVisible,
                        |                effect = Canimation.Fade.Up
                        |            )
                        |        ) {
                        |            Text("Hello, canimation!")
                        |        }
                        |
                        |        // Combine effects freely
                        |        Modifier.canimation(
                        |            visible = show,
                        |            effect = Canimation.Scale.Pop
                        |                + Canimation.Fade.In
                        |        )
                        |
                        |        // Attention seekers
                        |        Modifier.canimation(
                        |            visible = pulse,
                        |            effect = Canimation.Attention.Tada
                        |        )
                        |    }
                        |}
                    """.trimMargin(),
                    modifier = Modifier.padding(24.dp),
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

// ======= EXPLORE =======

@Composable
private fun ExploreSection(onNavigate: (String) -> Unit) {
    var entryStage by remember { mutableIntStateOf(-1) }
    LaunchedEffect(Unit) { for (i in 0..5) { delay(60); entryStage = i } }

    DarkCenteredSection {
        Box(Modifier.canimation(visible = entryStage >= 0, effect = Canimation.Fade.Up)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "EXPLORE",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Dive deeper",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        listOf(
            Triple("Preset Gallery", "All built-in presets with interactive demos", "presets"),
            Triple("Examples", "Categorized interactive examples — fade, scale, spring, attention seekers & more", "examples"),
            Triple("API Reference", "Complete documentation of every Modifier, Composable, data class, and namespace effect", "api"),
            Triple("Documentation", "Getting started guide, Atomic Design concepts, and interactive playground", "docs"),
        ).forEachIndexed { index, (title, description, route) ->
            Box(Modifier.canimation(visible = entryStage >= 1 + index, effect = Canimation.Entrance.Rise)) {
                Surface(
                    onClick = { onNavigate(route) },
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        Text(
                            "→",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }
    }
}

// ======= PLATFORM =======

@Composable
private fun PlatformSection(stage: Int) {
    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 900.dp)
                    .padding(horizontal = 32.dp, vertical = 56.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(Modifier.canimation(visible = stage >= 8, effect = Canimation.Fade.Up)) {
                    Text(
                        text = "Built for every platform",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    listOf("Android", "iOS", "Desktop", "Web").forEachIndexed { index, platform ->
                        Box(Modifier.canimation(
                            visible = stage >= 8 + index,
                            effect = Canimation.Bounce.In,
                        )) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                            ) {
                                Text(
                                    text = platform,
                                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// ======= FOOTER =======

@Composable
private fun FooterSection() {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 32.dp)
            .canimation(visible = visible, effect = Canimation.Fade.In),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Apache License 2.0 · canimation contributors",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

// ======= HELPERS =======

@Composable
private fun DarkCenteredSection(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 900.dp)
                .padding(horizontal = 32.dp, vertical = 56.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            content()
        }
    }
}

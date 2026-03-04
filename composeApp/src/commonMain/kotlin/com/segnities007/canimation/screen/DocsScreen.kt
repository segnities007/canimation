package com.segnities007.canimation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.canimation
import io.github.canimation.core.canimationEmphasize
import io.github.canimation.core.canimationEnter
import kotlinx.coroutines.delay

// ━━━━━━━━━━━━━━━━━━━━━━━━ Sidebar Sections ━━━━━━━━━━━━━━━━━━━━━━━━

private enum class DocSection(val label: String, val group: String) {
    Overview("Overview", "Getting Started"),
    QuickStart("Quick Start", "Getting Started"),
    Philosophy("Philosophy", "Getting Started"),
    ApiSurface("API Surface", "API Reference"),
    Namespace("Canimation.*", "API Reference"),
    Primitives("Effect Primitives", "API Reference"),
    AtomicDesign("Atomic Design", "API Reference"),
    Playground("Playground", "Interactive"),
    Principles("Core Principles", "Concepts"),
    Inspiration("Inspiration", "Concepts"),
    Differentiators("What Sets Us Apart", "Concepts"),
    Modules("Modules", "Architecture"),
    Platforms("Platforms", "Architecture"),
    Roadmap("Roadmap", "Architecture"),
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocsScreen(modifier: Modifier = Modifier) {
    var stage by remember { mutableIntStateOf(-1) }
    var activeSection by remember { mutableStateOf(DocSection.Overview) }
    var sidebarOpen by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) { for (i in 0..20) { delay(40); stage = i } }

    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        Row(modifier.fillMaxSize()) {
        // ─── Sidebar toggle ───
        IconButton(onClick = { sidebarOpen = !sidebarOpen }) {
            Icon(
                if (sidebarOpen) Icons.Default.ChevronLeft else Icons.Default.Menu,
                contentDescription = if (sidebarOpen) "Close sidebar" else "Open sidebar",
            )
        }
        // ─── Sidebar (Motion.dev-style) ───
        AnimatedVisibility(visible = sidebarOpen) {
        Surface(
            modifier = Modifier
                .width(220.dp)
                .fillMaxHeight()
                .canimation(visible = stage >= 0, effect = Canimation.Fade.Left),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        ) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 16.dp),
            ) {
                var currentGroup = ""
                DocSection.entries.forEachIndexed { i, section ->
                    if (section.group != currentGroup) {
                        currentGroup = section.group
                        if (i > 0) Spacer(Modifier.height(12.dp))
                        Text(
                            currentGroup.uppercase(),
                            Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp,
                        )
                    }

                    val selected = activeSection == section
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 1.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .clickable { activeSection = section }
                            .canimation(visible = stage >= i, effect = Canimation.Fade.Right),
                        color = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                        else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0f),
                        shape = RoundedCornerShape(6.dp),
                    ) {
                        Row(
                            Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            if (selected) {
                                Box(
                                    Modifier
                                        .size(3.dp, 16.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(MaterialTheme.colorScheme.primary)
                                )
                                Spacer(Modifier.width(8.dp))
                            }
                            Text(
                                section.label,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                                color = if (selected) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))
            }
        }
        }

        VerticalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        // ─── Main Content ───
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(
                Modifier
                    .widthIn(max = 800.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 32.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                when (activeSection) {
                    DocSection.Overview -> OverviewContent(stage)
                    DocSection.QuickStart -> QuickStartContent(stage)
                    DocSection.Philosophy -> PhilosophyContent(stage)
                    DocSection.ApiSurface -> ApiSurfaceContent(stage)
                    DocSection.Namespace -> NamespaceContent(stage)
                    DocSection.Primitives -> PrimitivesContent(stage)
                    DocSection.AtomicDesign -> AtomicDesignContent(stage)
                    DocSection.Playground -> PlaygroundContent(stage)
                    DocSection.Principles -> CorePrinciplesContent(stage)
                    DocSection.Inspiration -> InspirationContent(stage)
                    DocSection.Differentiators -> DifferentiatorsContent(stage)
                    DocSection.Modules -> ModulesContent(stage)
                    DocSection.Platforms -> PlatformContent(stage)
                    DocSection.Roadmap -> RoadmapContent(stage)
                }
                Spacer(Modifier.height(40.dp))
            }
        }
    }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Overview ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun OverviewContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                "Getting Started",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Black,
            )
            Text(
                "Production-ready animations for Compose Multiplatform.\nMake your UI speak through motion.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 26.sp,
            )
        }

        HeroDemo()

        SectionCard("Getting Started", "Add one dependency, wrap your app, animate anything.") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                CodeBlock("""// 1. Add dependency
implementation("io.github.canimation:canimation-core:<version>")

// 2. Wrap with provider
CanimationProvider(policy = CanimationPolicy.SystemAware) {
    MyApp()
}

// 3. Animate!
Modifier.canimation(visible = show, effect = Canimation.Fade.Up)""")
            }
        }

        SectionCard("Atomic Design", "Animations organized into Atoms, Molecules, and Organisms.") {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AtomicLevelRow("Atoms", "Single-property primitives", "Fade.In, Slide.Up, Scale.In, Rotate.In")
                AtomicLevelRow("Molecules", "Two-property combos", "Fade.Up, Scale.Pop, Zoom.In, Spring.Up")
                AtomicLevelRow("Organisms", "Complex multi-property", "Attention.Pulse, Entrance.Drop, Material.FadeThrough")
            }
        }

        SectionCard("What's New", "Expanded Canimation namespace with 100+ effects.") {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf(
                    "Canimation.Attention" to "Pulse, HeartBeat, Tada, Wobble, Swing, RubberBand, Jello, Flash, ShakeX, ShakeY...",
                    "Canimation.Entrance" to "Elevate, Drop, JackInTheBox, RollIn, LightSpeedLeft, BackInUp, TiltIn, Rise...",
                    "Canimation.Zoom" to "In, Out, InUp, InDown, InLeft, InRight, Dramatic",
                    "Canimation.Material" to "FadeThrough, SharedAxisX, SharedAxisY, SharedAxisZ, Emphasized, ContainerTransform",
                    "Canimation.Morph" to "ScaleUp, Expand, Collapse, Elastic",
                ).forEach { (ns, members) ->
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            ns,
                            fontFamily = FontFamily.Monospace,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.widthIn(min = 140.dp),
                        )
                        Text(
                            members,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Quick Start ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun QuickStartContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        PageTitle("Quick Start", "Three steps to your first animation")

        StepBlock(1, "Add the dependency",
            """// build.gradle.kts (commonMain)
implementation("io.github.canimation:canimation-core:<version>")
implementation("io.github.canimation:canimation-presets:<version>")""")
        StepBlock(2, "Wrap your app with CanimationProvider",
            """CanimationProvider(policy = CanimationPolicy.SystemAware) {
    MyApp()
}""")
        StepBlock(3, "Animate anything with one line",
            """// Recommended: Effect API
Modifier.canimation(visible = show, effect = Canimation.Fade.Up)

// Compose effects freely
Modifier.canimation(visible = show, effect = Canimation.Scale.Pop + Canimation.Fade.In)

// Use the new categories
Modifier.canimation(visible = show, effect = Canimation.Entrance.Drop)
Modifier.canimation(visible = show, effect = Canimation.Material.FadeThrough)

// Attention seekers for emphasis
Modifier.canimation(visible = pulse, effect = Canimation.Attention.Tada)""")

        DemoBox("Effect API in action") { EffectDemo() }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Design Philosophy ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun PhilosophyContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        PageTitle("Design Philosophy", "What we believe about animation and why canimation exists")

        BodyText(
            "Animation is not decoration — it is communication. Every transition, every entrance, " +
            "every emphasis tells the user something: where they came from, where they're going, " +
            "and what matters right now."
        )
        BodyText(
            "canimation was born from the conviction that great animation should be as easy to add " +
            "as a color or a font, not a week-long engineering project."
        )

        PhilosophyDemo()

        BodyText(
            "The result is a library where a single line — Modifier.canimation(visible, Canimation.Fade.Up) — " +
            "gives you production-quality motion that respects accessibility settings, adapts to platform conventions, " +
            "and composes naturally with other effects via the + operator."
        )
    }
}

@Composable
private fun PhilosophyDemo() {
    DemoBox("Animation as Communication") {
        var step by remember { mutableIntStateOf(0) }
        LaunchedEffect(Unit) {
            while (true) { for (i in 0..3) { delay(if (i == 0) 800L else 1200L); step = i }; delay(1600); step = -1; delay(600) }
        }
        Column(
            Modifier.fillMaxWidth().height(120.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf(
                    "Origin" to Canimation.Fade.Left,
                    "Focus" to Canimation.Scale.Pop,
                    "Destination" to Canimation.Fade.Right,
                ).forEachIndexed { i, (label, effect) ->
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = when (i) {
                            0 -> MaterialTheme.colorScheme.secondaryContainer
                            1 -> MaterialTheme.colorScheme.primaryContainer
                            else -> MaterialTheme.colorScheme.tertiaryContainer
                        },
                        border = BorderStroke(1.dp, when (i) {
                            0 -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
                            1 -> MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                            else -> MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f)
                        }),
                        modifier = Modifier.canimation(visible = step >= i, effect = effect),
                    ) {
                        Text(
                            label,
                            Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = when (i) {
                                0 -> MaterialTheme.colorScheme.onSecondaryContainer
                                1 -> MaterialTheme.colorScheme.onPrimaryContainer
                                else -> MaterialTheme.colorScheme.onTertiaryContainer
                            },
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                when {
                    step < 0 -> ""
                    step == 0 -> "← Where you came from"
                    step == 1 -> "◉ What matters now"
                    else -> "→ Where you're going"
                },
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Core Principles ━━━━━━━━━━━━━━━━━━━━━━━━

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CorePrinciplesContent(stage: Int) {
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
private fun InspirationContent(stage: Int) {
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
private fun DifferentiatorsContent(stage: Int) {
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
private fun RoadmapContent(stage: Int) {
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

// ━━━━━━━━━━━━━━━━━━━━━━━━ API Surface ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun ApiSurfaceContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PageTitle("API Surface", "Every way to animate — pick your level of control")

        ApiCard(
            "✨ Modifier.canimation(visible, effect)",
            "RECOMMENDED",
            "Compose effects with the + operator. Use Canimation.* namespace.",
            """Modifier.canimation(visible = true, effect = Canimation.Fade.Up)
Modifier.canimation(visible = true, effect = Canimation.Scale.Pop + Canimation.Fade.In)
Modifier.canimation(visible = true, effect = Canimation.Entrance.Drop)""",
        )
        ApiCard(
            "Modifier.canimationTransition(visible, enter, exit)",
            "ADVANCED",
            "Separate enter and exit effects for asymmetric transitions.",
            """Modifier.canimationTransition(
    visible = isVisible,
    enter = Canimation.Fade.Up,
    exit = Canimation.Fade.Down,
)""",
        )
        ApiCard(
            "CanimationVisibility(visible, enterPreset)",
            "CLASSIC",
            "AnimatedVisibility wrapper with named presets.",
            """CanimationVisibility(
    visible = visible,
    enterPreset = CanimationPreset.BounceIn,
) { Text("Content") }""",
        )
        ApiCard(
            "Modifier.canimationEnter(visible, preset)",
            "LISTS",
            "Perfect for lazy layouts and staggered list items.",
            """Card(Modifier.canimationEnter(visible = show, preset = CanimationPreset.FadeUp)) {
    Text(item.name)
}""",
        )
        ApiCard(
            "Modifier.canimationEmphasize(active, preset)",
            "ATTENTION",
            "Pulse, Tada, Wobble — draw attention to interactive elements.",
            """Modifier.canimationEmphasize(active = hasNew, preset = CanimationPreset.Pulse)""",
        )
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Namespace Reference ━━━━━━━━━━━━━━━━━━━━━━━━

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun NamespaceContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        PageTitle("Canimation.* Namespace", "All animation effects organized by category")

        // Atoms
        SectionLabel("ATOMS — Single-property primitives")
        NamespaceRow("Canimation.Fade", listOf("In", "Out", "Gentle", "Quick"))
        NamespaceRow("Canimation.Scale", listOf("In", "Up", "Down", "Expand", "Shrink", "Subtle"))
        NamespaceRow("Canimation.Slide", listOf("Left", "Right", "Up", "Down", "LeftBig", "RightBig", "UpBig", "DownBig", "LeftSubtle", "RightSubtle", "UpSubtle", "DownSubtle"))
        NamespaceRow("Canimation.Rotate", listOf("In", "Clockwise", "Spin", "Half", "Quarter"))

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        // Molecules
        SectionLabel("MOLECULES — Two-property combinations")
        NamespaceRow("Canimation.Fade", listOf("Up", "Down", "Left", "Right", "UpBig", "DownBig", "LeftBig", "RightBig", "TopLeft", "TopRight", "BottomLeft", "BottomRight", "Small", "Big"))
        NamespaceRow("Canimation.Scale", listOf("Pop", "Zoom", "FadeIn", "UpFade", "DownFade"))
        NamespaceRow("Canimation.Rotate", listOf("FadeIn", "ClockwiseFade", "SpinFade", "ScaleIn", "DownLeft", "DownRight", "UpLeft", "UpRight"))
        NamespaceRow("Canimation.Bounce", listOf("In", "Down", "Up", "Left", "Right"))
        NamespaceRow("Canimation.Spring", listOf("In", "Up", "Down", "Left", "Right", "Pop", "Bounce"))
        NamespaceRow("Canimation.Flip", listOf("In", "Up", "Down", "X", "Y"))
        NamespaceRow("Canimation.Zoom", listOf("In", "Out", "InUp", "InDown", "InLeft", "InRight", "Dramatic"))

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        // Organisms
        SectionLabel("ORGANISMS — Complex multi-property effects")
        NamespaceRow("Canimation.Attention", listOf("Pulse", "HeartBeat", "Tada", "Wobble", "Swing", "RubberBand", "Jello", "Flash", "ShakeX", "ShakeY", "HeadShake", "Glow", "Ring"))
        NamespaceRow("Canimation.Entrance", listOf("Elevate", "Drop", "JackInTheBox", "RollIn", "LightSpeedLeft", "LightSpeedRight", "BackInUp", "BackInDown", "BackInLeft", "BackInRight", "ShrinkIn", "TiltIn", "Snap", "SwingIn", "Unfold", "Rise", "Emerge"))
        NamespaceRow("Canimation.Material", listOf("FadeThrough", "SharedAxisX", "SharedAxisY", "SharedAxisZ", "Emphasized", "ContainerTransform"))
        NamespaceRow("Canimation.Morph", listOf("ScaleUp", "Expand", "Collapse", "Elastic"))

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        // Stagger helper
        SectionLabel("UTILITIES")
        NamespaceRow("Canimation.Stagger", listOf("Quick (40ms)", "Normal (70ms)", "Slow (120ms)", "Relaxed (200ms)"))

        // Effect primitives
        Text("CanimationEffect Primitives", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            listOf(
                "fade()", "slideUp()", "slideDown()", "slideLeft()", "slideRight()",
                "scale()", "pop()", "rotate()", "spin()", "zoom()", "bounce()",
                "duration(ms)", "easing(e)",
            ).forEach { name ->
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f),
                ) {
                    Text(
                        name,
                        Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontFamily = FontFamily.Monospace,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Effect Primitives ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun PrimitivesContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        PageTitle("Effect Primitives", "Build custom animations from building blocks")

        BodyText("Each primitive controls a single animation property. Combine with + to create complex effects.")

        CodeBlock("""// Single primitives
CanimationEffect.fade()              // alpha: 0 → 1
CanimationEffect.slideUp(16.dp)      // offsetY: 16dp → 0dp
CanimationEffect.scale(0.9f)         // scale: 0.9 → 1.0
CanimationEffect.rotate(-15f)        // rotation: -15° → 0°

// Combine freely with +
val custom = CanimationEffect.fade() + 
    CanimationEffect.slideUp(24.dp) + 
    CanimationEffect.scale(0.8f) + 
    CanimationEffect.duration(400)

Modifier.canimation(visible = show, effect = custom)""")

        SectionCard("Available Primitives", "All building blocks at a glance") {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                PrimitiveRow("fade(from, to)", "Opacity transition. Default: 0f → 1f")
                PrimitiveRow("slideUp(offset)", "Vertical slide from below. Default: 16.dp")
                PrimitiveRow("slideDown(offset)", "Vertical slide from above. Default: 16.dp")
                PrimitiveRow("slideLeft(offset)", "Horizontal slide from right. Default: 24.dp")
                PrimitiveRow("slideRight(offset)", "Horizontal slide from left. Default: 24.dp")
                PrimitiveRow("scale(from, to)", "Scale transform. Default: 0.92f → 1f")
                PrimitiveRow("pop(from, to)", "Pop with overshoot. Default: 0.8f → 1f, 300ms")
                PrimitiveRow("rotate(from, to)", "Rotation in degrees. Default: -15° → 0°")
                PrimitiveRow("spin(from)", "Full 360° rotation. Default: -360°, 400ms")
                PrimitiveRow("zoom(from)", "Scale + fade. Default: 0.5f, 300ms")
                PrimitiveRow("bounce()", "Scale bounce. Default: 0.3f → 1f, 400ms")
                PrimitiveRow("duration(ms)", "Override duration in milliseconds")
                PrimitiveRow("easing(e)", "Override easing curve")
            }
        }
    }
}

@Composable
private fun PrimitiveRow(signature: String, description: String) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            signature,
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.widthIn(min = 160.dp),
        )
        Text(
            description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Atomic Design ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun AtomicDesignContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        PageTitle("Atomic Design", "Animation hierarchy inspired by Brad Frost's Atomic Design")

        BodyText(
            "Animations in canimation are organized into three levels of complexity, " +
            "inspired by Atomic Design methodology."
        )

        SectionCard("🔵 Atoms", "Single-property animation primitives — the smallest building blocks") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                AtomicDemoRow("Canimation.Fade.In", "Opacity only", Canimation.Fade.In)
                AtomicDemoRow("Canimation.Slide.Up", "Translation only", Canimation.Slide.Up)
                AtomicDemoRow("Canimation.Scale.In", "Scale only", Canimation.Scale.In)
                AtomicDemoRow("Canimation.Rotate.In", "Rotation only", Canimation.Rotate.In)
            }
        }

        SectionCard("🟢 Molecules", "Two-property combinations — atoms working together") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                AtomicDemoRow("Canimation.Fade.Up", "fade + slideUp", Canimation.Fade.Up)
                AtomicDemoRow("Canimation.Scale.Pop", "scale + overshoot", Canimation.Scale.Pop)
                AtomicDemoRow("Canimation.Zoom.In", "scale + fade", Canimation.Zoom.In)
                AtomicDemoRow("Canimation.Spring.Up", "fade + slide + spring timing", Canimation.Spring.Up)
                AtomicDemoRow("Canimation.Bounce.Down", "bounce + slideDown", Canimation.Bounce.Down)
            }
        }

        SectionCard("🔴 Organisms", "Complex multi-property effects — molecules forming patterns") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                AtomicDemoRow("Canimation.Attention.Tada", "scale + rotation emphasis", Canimation.Attention.Tada)
                AtomicDemoRow("Canimation.Entrance.Drop", "offset + fade dramatic entry", Canimation.Entrance.Drop)
                AtomicDemoRow("Canimation.Entrance.JackInTheBox", "scale + rotation + fade", Canimation.Entrance.JackInTheBox)
                AtomicDemoRow("Canimation.Material.FadeThrough", "Material design pattern", Canimation.Material.FadeThrough)
                AtomicDemoRow("Canimation.Morph.Elastic", "elastic stretch effect", Canimation.Morph.Elastic)
            }
        }

        CodeBlock("""// Atoms compose into Molecules
val molecule = CanimationEffect.fade() + CanimationEffect.slideUp()
// equivalent to: Canimation.Fade.Up

// Molecules compose into Organisms  
val organism = Canimation.Fade.Up + CanimationEffect.scale(0.8f) + CanimationEffect.rotate(-10f)
// Custom complex animation!""")
    }
}

@Composable
private fun AtomicDemoRow(code: String, description: String, effect: CanimationEffect) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { delay(300); while (true) { vis = true; delay(2000); vis = false; delay(600) } }

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
            modifier = Modifier.size(40.dp).canimation(visible = vis, effect = effect),
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.PlayArrow, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
            }
        }
        Column(Modifier.weight(1f)) {
            Text(code, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Playground ━━━━━━━━━━━━━━━━━━━━━━━━

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PlaygroundContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        PageTitle("Animation Playground", "See all effects in action")

        // Interactive animation grid
        val allEffects = listOf(
            "Fade.In" to Canimation.Fade.In,
            "Fade.Up" to Canimation.Fade.Up,
            "Fade.Down" to Canimation.Fade.Down,
            "Fade.Left" to Canimation.Fade.Left,
            "Fade.Right" to Canimation.Fade.Right,
            "Fade.Small" to Canimation.Fade.Small,
            "Fade.Big" to Canimation.Fade.Big,
            "Scale.In" to Canimation.Scale.In,
            "Scale.Pop" to Canimation.Scale.Pop,
            "Scale.Zoom" to Canimation.Scale.Zoom,
            "Scale.Expand" to Canimation.Scale.Expand,
            "Slide.Up" to Canimation.Slide.Up,
            "Slide.Down" to Canimation.Slide.Down,
            "Slide.Left" to Canimation.Slide.Left,
            "Rotate.In" to Canimation.Rotate.In,
            "Rotate.Spin" to Canimation.Rotate.Spin,
            "Rotate.ScaleIn" to Canimation.Rotate.ScaleIn,
            "Bounce.In" to Canimation.Bounce.In,
            "Bounce.Up" to Canimation.Bounce.Up,
            "Spring.Pop" to Canimation.Spring.Pop,
            "Flip.In" to Canimation.Flip.In,
            "Flip.Y" to Canimation.Flip.Y,
            "Zoom.In" to Canimation.Zoom.In,
            "Zoom.Out" to Canimation.Zoom.Out,
            "Zoom.Dramatic" to Canimation.Zoom.Dramatic,
            "Entrance.Elevate" to Canimation.Entrance.Elevate,
            "Entrance.Drop" to Canimation.Entrance.Drop,
            "Entrance.JackInTheBox" to Canimation.Entrance.JackInTheBox,
            "Entrance.RollIn" to Canimation.Entrance.RollIn,
            "Entrance.Rise" to Canimation.Entrance.Rise,
            "Material.FadeThrough" to Canimation.Material.FadeThrough,
            "Material.SharedAxisX" to Canimation.Material.SharedAxisX,
            "Morph.Elastic" to Canimation.Morph.Elastic,
            "Attention.Pulse" to Canimation.Attention.Pulse,
            "Attention.Tada" to Canimation.Attention.Tada,
            "Attention.Wobble" to Canimation.Attention.Wobble,
        )

        var globalVis by remember { mutableStateOf(true) }
        LaunchedEffect(Unit) { while (true) { globalVis = true; delay(2500); globalVis = false; delay(800) } }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            allEffects.forEachIndexed { i, (label, effect) ->
                var vis by remember { mutableStateOf(false) }
                LaunchedEffect(globalVis) {
                    if (globalVis) { delay(i * 50L); vis = true } else { vis = false }
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.canimation(visible = vis, effect = effect),
                ) {
                    Text(
                        label,
                        Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        fontFamily = FontFamily.Monospace,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Modules ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun ModulesContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PageTitle("Modules", "Modular architecture — import only what you need")

        ModuleRow("canimation-core", "Core engine, CanimationEffect, Modifier.canimation(), 100+ namespace effects", Icons.Default.Animation)
        ModuleRow("canimation-presets", "100+ ready-made presets (CanimationPreset enum)", Icons.Default.AutoAwesome)
        ModuleRow("canimation-a11y", "Accessibility helpers, reduced-motion detection", Icons.Default.AccessibilityNew)
        ModuleRow("canimation-diagnostics", "Debug overlays, animation performance logging", Icons.Default.Tune)
        ModuleRow("canimation-test", "Testing utilities for animation assertions", Icons.Default.Extension)
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Platform ━━━━━━━━━━━━━━━━━━━━━━━━

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PlatformContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        PageTitle("Platform Support", "One API, five targets")

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text("Compose Multiplatform", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    val platforms = listOf(
                        "Android" to Canimation.Fade.Up,
                        "iOS" to Canimation.Fade.Left,
                        "Desktop (JVM)" to Canimation.Scale.Pop,
                        "Web (JS)" to Canimation.Fade.Right,
                        "Web (WasmJs)" to Canimation.Bounce.In,
                    )
                    platforms.forEachIndexed { i, (name, effect) ->
                        var vis by remember { mutableStateOf(false) }
                        LaunchedEffect(Unit) { delay(i * 150L + 200L); vis = true }
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            modifier = Modifier.canimation(visible = vis, effect = effect),
                        ) {
                            Text(
                                name,
                                Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }
            }
        }

        CodeBlock("""// Same code runs on all platforms
Modifier.canimation(visible = show, effect = Canimation.Fade.Up)
// Works on: Android, iOS, Desktop, Web JS, Web WasmJs""")
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Shared Components ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun PageTitle(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(8.dp))
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }
}

@Composable
private fun SectionCard(title: String, description: String, content: @Composable () -> Unit) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        content()
    }
}

@Composable
private fun AtomicLevelRow(level: String, description: String, examples: String) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            level,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.widthIn(min = 80.dp),
        )
        Column {
            Text(description, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
            Text(examples, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontFamily = FontFamily.Monospace)
        }
    }
}

@Composable
private fun ApiCard(signature: String, badge: String, desc: String, code: String) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(signature, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                ) {
                    Text(badge, Modifier.padding(horizontal = 8.dp, vertical = 2.dp), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                }
            }
            Text(desc, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            CodeBlock(code)
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(text, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp)
}

@Composable
private fun BodyText(text: String) {
    Text(text, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 24.sp)
}

@Composable
private fun StepBlock(step: Int, title: String, code: String) {
    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primary, modifier = Modifier.size(28.dp)) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("$step", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
            }
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
            CodeBlock(code)
        }
    }
}

@Composable
private fun CodeBlock(code: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            code,
            Modifier.padding(16.dp).horizontalScroll(rememberScrollState()),
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            lineHeight = 20.sp,
        )
    }
}

@Composable
private fun DemoBox(label: String, content: @Composable () -> Unit) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(label.uppercase(), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            content()
        }
    }
}

@Composable
private fun NamespaceRow(namespace: String, members: List<String>) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(namespace, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.widthIn(min = 140.dp))
        Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.outlineVariant, modifier = Modifier.size(16.dp))
        Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            members.forEach { m ->
                Surface(shape = RoundedCornerShape(4.dp), color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f)) {
                    Text(".$m", Modifier.padding(horizontal = 8.dp, vertical = 3.dp), fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.tertiary)
                }
            }
        }
    }
}

@Composable
private fun ModuleRow(name: String, desc: String, icon: ImageVector) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { delay(200); vis = true }
    Row(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp)
            .canimation(visible = vis, effect = Canimation.Fade.Up),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
        Column {
            Text(name, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Text(desc, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Live Demos ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun HeroDemo() {
    val effects = listOf(Canimation.Fade.Up, Canimation.Scale.Pop, Canimation.Bounce.In, Canimation.Rotate.In)
    val labels = listOf("Fade.Up", "Scale.Pop", "Bounce.In", "Rotate.In")
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { visible = true; delay(2200); visible = false; delay(600) } }
    Row(
        Modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        effects.forEachIndexed { i, effect ->
            var itemVis by remember { mutableStateOf(false) }
            LaunchedEffect(visible) { if (visible) { delay(i * 100L); itemVis = true } else { itemVis = false } }
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                modifier = Modifier.padding(horizontal = 6.dp).canimation(visible = itemVis, effect = effect),
            ) {
                Text(
                    labels[i],
                    Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }
    }
}

@Composable
private fun EffectDemo() {
    val effects = listOf(
        "Fade.Up" to Canimation.Fade.Up,
        "Scale.Pop" to Canimation.Scale.Pop,
        "Slide.Left + Fade" to (Canimation.Slide.Left + Canimation.Fade.In),
        "Bounce.In" to Canimation.Bounce.In,
    )
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { visible = true; delay(2500); visible = false; delay(800) } }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        effects.forEachIndexed { i, (label, effect) ->
            var show by remember { mutableStateOf(false) }
            LaunchedEffect(visible) { if (visible) { delay(i * 120L); show = true } else { show = false } }
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f)),
                modifier = Modifier.canimation(visible = show, effect = effect),
            ) {
                Text(
                    label, Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
        }
    }
}

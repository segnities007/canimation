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
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Rocket
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocsScreen(modifier: Modifier = Modifier) {
    var stage by remember { mutableIntStateOf(-1) }
    LaunchedEffect(Unit) { for (i in 0..20) { delay(50); stage = i } }

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(
            Modifier
                .widthIn(max = 960.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            // ─── Hero ───
            Box(Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up)) {
                Column(
                    Modifier.fillMaxWidth().padding(vertical = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                    ) {
                        Text(
                            "v1.0 — Compose Multiplatform",
                            Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onSurface)) { append("c") }
                            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) { append("animation") }
                        },
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        "Production-ready animations for Compose Multiplatform.\nMake your UI speak through motion.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        lineHeight = 26.sp,
                    )
                    HeroDemo()
                }
            }

            // ─── Design Philosophy ───
            Box(Modifier.canimation(visible = stage >= 1, effect = Canimation.Fade.Up)) {
                PhilosophySection()
            }

            // ─── Core Principles ───
            Box(Modifier.canimation(visible = stage >= 3, effect = Canimation.Fade.Up)) {
                CorePrinciplesSection()
            }

            // ─── Inspiration ───
            Box(Modifier.canimation(visible = stage >= 5, effect = Canimation.Fade.Up)) {
                InspirationSection()
            }

            // ─── What Sets Us Apart ───
            Box(Modifier.canimation(visible = stage >= 7, effect = Canimation.Fade.Up)) {
                DifferentiatorsSection()
            }

            // ─── Vision & Roadmap ───
            Box(Modifier.canimation(visible = stage >= 9, effect = Canimation.Fade.Up)) {
                VisionSection()
            }

            // ─── Quick Start (concise) ───
            Box(Modifier.canimation(visible = stage >= 11, effect = Canimation.Fade.Up)) {
                QuickStartSection()
            }

            // ─── API Overview (compact) ───
            Box(Modifier.canimation(visible = stage >= 13, effect = Canimation.Fade.Up)) {
                ApiOverviewSection()
            }

            // ─── Modules ───
            Box(Modifier.canimation(visible = stage >= 15, effect = Canimation.Fade.Up)) {
                ModulesSection()
            }

            // ─── Platform Support ───
            Box(Modifier.canimation(visible = stage >= 17, effect = Canimation.Fade.Up)) {
                PlatformSection()
            }

            Spacer(Modifier.height(40.dp))
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Design Philosophy ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun PhilosophySection() {
    DocSection(
        "Design Philosophy",
        "What we believe about animation and why canimation exists",
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            BodyText(
                "Animation is not decoration — it is communication. Every transition, every entrance, " +
                "every emphasis tells the user something: where they came from, where they're going, " +
                "and what matters right now. canimation was born from the conviction that great animation " +
                "should be as easy to add as a color or a font, not a week-long engineering project."
            )
            BodyText(
                "We studied how the best animation libraries — Motion.dev, Animate.css, AnimXYZ, " +
                "and Material Design Motion — make developers productive and users delighted. We took " +
                "those lessons and rebuilt them for the Compose Multiplatform ecosystem, where animations " +
                "have traditionally required deep knowledge of coroutines, state machines, and platform-specific APIs."
            )

            // Live philosophy demo: animation as communication
            PhilosophyDemo()

            BodyText(
                "The result is a library where a single line — Modifier.canimation(visible, Canimation.Fade.Up) — " +
                "gives you production-quality motion that respects accessibility settings, adapts to platform conventions, " +
                "and composes naturally with other effects via the + operator."
            )
        }
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
private fun CorePrinciplesSection() {
    DocSection("Core Principles", "The values that shape every API decision") {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                PrincipleCard(
                    Modifier.weight(1f),
                    icon = Icons.Default.Tune,
                    title = "Composability Over Configuration",
                    body = "Instead of massive parameter lists, combine small focused effects with +. " +
                           "CanimationEffect.fade() + CanimationEffect.slideUp() is more expressive than " +
                           "any config object. Build exactly what you need from simple building blocks.",
                    effect = Canimation.Fade.Up,
                )
                PrincipleCard(
                    Modifier.weight(1f),
                    icon = Icons.Default.AccessibilityNew,
                    title = "Accessibility Is Not Optional",
                    body = "Every animation respects CanimationPolicy. When the OS says 'reduce motion', " +
                           "we listen automatically. No extra code. No opt-in. It's built into the core engine, " +
                           "not bolted on as an afterthought.",
                    effect = Canimation.Scale.Pop,
                )
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                PrincipleCard(
                    Modifier.weight(1f),
                    icon = Icons.Default.Lightbulb,
                    title = "Progressive Disclosure",
                    body = "Start with Modifier.canimation(visible, Canimation.Fade.Up). Need more control? " +
                           "Use CanimationEffect primitives. Need full control? Drop down to CanimationSpec. " +
                           "Simple things stay simple, complex things stay possible.",
                    effect = Canimation.Slide.Up,
                )
                PrincipleCard(
                    Modifier.weight(1f),
                    icon = Icons.Default.Visibility,
                    title = "Show, Don't Tell",
                    body = "Animation should explain itself. That's why this entire documentation site is built " +
                           "with canimation — every card entrance, every demo, every transition you see here uses " +
                           "the same API you'll use in your app.",
                    effect = Canimation.Bounce.In,
                )
            }
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
private fun InspirationSection() {
    DocSection("Inspiration & References", "Standing on the shoulders of giants") {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            BodyText(
                "canimation didn't emerge in a vacuum. We carefully studied the animation ecosystem " +
                "across web, mobile, and design systems to create something that feels familiar yet " +
                "uniquely suited for Compose Multiplatform."
            )

            InspirationCard(
                name = "Motion.dev (Framer Motion)",
                lesson = "The composable, declarative API pattern. Motion.dev proved that animation primitives " +
                         "should compose together freely, not be rigid presets. Our CanimationEffect + operator " +
                         "and the Canimation.* namespace are directly inspired by this philosophy.",
                demoEffect = Canimation.Fade.Up + CanimationEffect.scale(0.95f),
            )
            InspirationCard(
                name = "Animate.css",
                lesson = "The power of named presets. Animate.css showed that developers want ready-made " +
                         "animations they can apply with a single class name. Our 83+ CanimationPreset entries " +
                         "(FadeUp, BounceIn, SpringSlideUp) follow this exact pattern — one line, done.",
                demoEffect = Canimation.Bounce.In,
            )
            InspirationCard(
                name = "AnimXYZ",
                lesson = "Compositional CSS animation utilities with stagger and multi-axis control. " +
                         "AnimXYZ's xyz-appear/xyz-in model influenced our enter/exit distinction and " +
                         "our support for multi-axis animation composition.",
                demoEffect = Canimation.Slide.Left + Canimation.Fade.In,
            )
            InspirationCard(
                name = "Material Design Motion",
                lesson = "Meaningful, purposeful transitions with container transform and shared axis. " +
                         "We adopted Material's philosophy that animation should convey spatial relationships " +
                         "and hierarchy. Our SharedAxisX/Y presets come directly from Material Motion.",
                demoEffect = Canimation.Scale.Zoom,
            )
            InspirationCard(
                name = "Kotlin / Ktor / Koin Documentation",
                lesson = "Clean, developer-friendly documentation with progressive complexity. " +
                         "These sites proved that Kotlin developers expect concise, well-organized docs " +
                         "with runnable examples — exactly what this showcase aims to be.",
                demoEffect = Canimation.Fade.Gentle,
            )
        }
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
private fun DifferentiatorsSection() {
    DocSection("What Sets canimation Apart", "More than just another animation library") {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            val items = listOf(
                Triple("Compose-Native", "Not a port from CSS or web. Built from the ground up for Compose's " +
                    "graphicsLayer, state-driven rendering, and recomposition model. No wrappers, no bridges.", Canimation.Fade.Up),
                Triple("Truly Multiplatform", "One API, five targets. Android, iOS, Desktop, Web (JS), " +
                    "Web (WasmJs). Same animation code runs everywhere without platform-specific branches.", Canimation.Scale.Pop),
                Triple("Effect Algebra", "The + operator isn't syntactic sugar — it's a principled algebraic " +
                    "composition. Effects combine predictably: properties from the right override the left, " +
                    "duration takes the maximum. No surprises.", Canimation.Slide.Left + Canimation.Fade.In),
                Triple("Three Layers of Control", "Quick: CanimationPreset.FadeUp (one line). Medium: " +
                    "Canimation.Fade.Up + CanimationEffect.scale(0.9f) (composable). Full: CanimationSpec " +
                    "with custom ranges, easing, and duration (total control).", Canimation.Spring.Up),
                Triple("A11y by Default", "CanimationProvider + CanimationPolicy means accessibility is not " +
                    "a feature flag. It's the default behavior. SystemAware reads the OS preference automatically. " +
                    "Reduced mode simplifies, Off mode disables — your animation code doesn't change.", Canimation.Fade.Gentle),
            )

            items.forEachIndexed { i, (title, desc, effect) ->
                var vis by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { delay(i * 200L + 400L); vis = true }
                DifferentiatorRow(title, desc, effect, vis)
            }
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

@Composable
private fun VisionSection() {
    DocSection("Vision & Roadmap", "Where canimation is heading") {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            BodyText(
                "canimation aims to be the definitive animation toolkit for Compose Multiplatform — " +
                "the way Animate.css became the go-to for web animations and Motion.dev became the " +
                "standard for React motion. We're building toward a world where adding beautiful, " +
                "accessible animation to any Compose app takes seconds, not hours."
            )

            SectionLabel("NOW")
            RoadmapItem("83+ animation presets covering entrance, emphasis, and attention patterns")
            RoadmapItem("CanimationEffect DSL with algebraic + composition")
            RoadmapItem("Full accessibility support with CanimationPolicy")
            RoadmapItem("Five-platform support: Android, iOS, Desktop, Web JS, Web WasmJs")
            RoadmapItem("Diagnostic overlay for animation performance monitoring")

            SectionLabel("NEXT")
            RoadmapItem("Layout animations — shared element transitions and container transforms")
            RoadmapItem("Gesture-driven animations — spring-based drag, swipe, and fling")
            RoadmapItem("Scroll-linked animations — parallax, reveal, and pinning")
            RoadmapItem("Stagger orchestration — automatic stagger for list items")
            RoadmapItem("Animation timeline — sequence multiple effects with precise timing")

            SectionLabel("FUTURE")
            RoadmapItem("Visual animation editor — design animations graphically, export as code")
            RoadmapItem("AI-assisted motion — suggest appropriate animations based on context")
            RoadmapItem("Design system integration — Material 3, iOS HIG, and custom token presets")
            RoadmapItem("Performance profiling — identify and optimize expensive animation paths")
        }
    }
}

@Composable
private fun RoadmapItem(text: String) {
    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp).padding(top = 2.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 22.sp)
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Quick Start (Compact) ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun QuickStartSection() {
    DocSection("Quick Start", "Three steps to your first animation") {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
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

// Classic: Preset API
CanimationVisibility(visible = show, enterPreset = CanimationPreset.BounceIn) {
    Card { Text("Hello!") }
}""")
            DemoBox("Effect API in action") { EffectDemo() }
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ API Overview (Compact) ━━━━━━━━━━━━━━━━━━━━━━━━

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ApiOverviewSection() {
    DocSection("API Surface", "Every way to animate — pick the level of control you need") {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            ApiCard(
                "✨ Modifier.canimation(visible, effect)",
                "RECOMMENDED",
                "Compose effects with the + operator. Use Canimation.* namespace for presets.",
                """Modifier.canimation(visible = true, effect = Canimation.Fade.Up)
Modifier.canimation(visible = true, effect = Canimation.Scale.Pop + Canimation.Fade.In)""",
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

            // Canimation Namespace reference
            Text("Canimation Namespace", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                NamespaceRow("Canimation.Fade", listOf("In", "Up", "Down", "Left", "Right", "Gentle", "UpBig", "DownBig"))
                NamespaceRow("Canimation.Scale", listOf("In", "Up", "Pop", "Zoom", "Expand", "Shrink"))
                NamespaceRow("Canimation.Slide", listOf("Left", "Right", "Up", "Down", "LeftBig", "RightBig"))
                NamespaceRow("Canimation.Rotate", listOf("In", "Clockwise", "Spin"))
                NamespaceRow("Canimation.Bounce", listOf("In", "Down", "Up"))
                NamespaceRow("Canimation.Spring", listOf("In", "Up"))
                NamespaceRow("Canimation.Flip", listOf("In", "Up"))
            }

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

// ━━━━━━━━━━━━━━━━━━━━━━━━ Modules ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun ModulesSection() {
    DocSection("Modules", "Modular architecture — import only what you need") {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            ModuleRow("canimation-core", "Core engine, CanimationEffect, Modifier.canimation()", Icons.Default.Animation)
            ModuleRow("canimation-presets", "83+ ready-made presets (CanimationPreset enum)", Icons.Default.AutoAwesome)
            ModuleRow("canimation-a11y", "Accessibility helpers, reduced-motion detection", Icons.Default.AccessibilityNew)
            ModuleRow("canimation-diagnostics", "Debug overlays, animation performance logging", Icons.Default.Tune)
            ModuleRow("canimation-test", "Testing utilities for animation assertions", Icons.Default.Extension)
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Platform ━━━━━━━━━━━━━━━━━━━━━━━━

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PlatformSection() {
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
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Shared Components ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun SectionLabel(text: String) {
    Text(text, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp)
}

@Composable
private fun BodyText(text: String) {
    Text(text, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 24.sp)
}

@Composable
private fun DocSection(title: String, description: String, content: @Composable () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            content()
        }
    }
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

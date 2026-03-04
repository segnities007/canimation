package com.segnities007.canimation.screen

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
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Extension
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
import androidx.compose.ui.graphics.Brush
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
    LaunchedEffect(Unit) { for (i in 0..15) { delay(50); stage = i } }

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
            Box(Modifier.canimationEnter(visible = stage >= 0, preset = CanimationPreset.FadeUp)) {
                Column(
                    Modifier.fillMaxWidth().padding(vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    // Version badge
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
                    // Title
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
                        "Production-ready animations for Compose Multiplatform.\nDrop-in presets, composable effects, full a11y support.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        lineHeight = 26.sp,
                    )
                    // Live hero demo
                    HeroDemo()
                }
            }

            // ─── Why canimation — Feature Cards ───
            Box(Modifier.canimationEnter(visible = stage >= 1, preset = CanimationPreset.FadeUp)) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    SectionLabel("WHY CANIMATION")
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        FeatureCard(
                            Modifier.weight(1f),
                            icon = Icons.Default.Animation,
                            title = "83+ Presets",
                            desc = "Fade, scale, slide, bounce, flip, spring — one line to animate anything.",
                        )
                        FeatureCard(
                            Modifier.weight(1f),
                            icon = Icons.Default.AutoAwesome,
                            title = "Effect DSL",
                            desc = "Combine primitives with the + operator. Infinite possibilities.",
                        )
                        FeatureCard(
                            Modifier.weight(1f),
                            icon = Icons.Default.AccessibilityNew,
                            title = "A11y Ready",
                            desc = "Respects OS reduced-motion. Fine-grained policy control built in.",
                        )
                    }
                }
            }

            // ─── Installation ───
            Box(Modifier.canimationEnter(visible = stage >= 2, preset = CanimationPreset.FadeUp)) {
                DocSection("Installation", "Add canimation to your Gradle project") {
                    CodeBlock("""// build.gradle.kts (commonMain)
dependencies {
    implementation("io.github.canimation:canimation-core:<version>")
    implementation("io.github.canimation:canimation-presets:<version>")

    // Optional
    implementation("io.github.canimation:canimation-a11y:<version>")
    implementation("io.github.canimation:canimation-diagnostics:<version>")
}""")
                }
            }

            // ─── Quick Start — 3 Steps ───
            Box(Modifier.canimationEnter(visible = stage >= 3, preset = CanimationPreset.FadeUp)) {
                DocSection("Quick Start", "Three steps to your first animation") {
                    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                        // Step 1
                        StepBlock(1, "Wrap your app with CanimationProvider",
                            """CanimationProvider(
    policy = CanimationPolicy.SystemAware,
) {
    MyApp()
}""")
                        // Step 2
                        StepBlock(2, "Animate with the recommended Effect API",
                            """var show by remember { mutableStateOf(false) }

Box(
    Modifier.canimation(
        visible = show,
        effect = Canimation.Fade.Up,
    )
) {
    Text("Hello, animated world!")
}""")
                        // Step 3
                        StepBlock(3, "Or use presets for quick results",
                            """CanimationVisibility(
    visible = show,
    enterPreset = CanimationPreset.BounceIn,
) {
    Card { Text("Bounced in!") }
}""")
                        // Live demo
                        DemoBox { QuickStartDemo() }
                    }
                }
            }

            // ─── Recommended API: Modifier.canimation() ───
            Box(Modifier.canimationEnter(visible = stage >= 4, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    "Modifier.canimation()  ✨",
                    "The recommended API — compose effects with the + operator",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Text(
                            "Use Canimation.* namespace or build custom effects from primitives.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        CodeBlock("""// Namespace shortcuts
Modifier.canimation(visible = true, effect = Canimation.Fade.Up)
Modifier.canimation(visible = true, effect = Canimation.Scale.Pop)

// Combine freely with +
Modifier.canimation(
    visible = true,
    effect = Canimation.Fade.In + Canimation.Scale.In
                + CanimationEffect.rotate(-10f),
)

// Build from primitives
val custom = CanimationEffect.fade() +
    CanimationEffect.slideUp(16.dp) +
    CanimationEffect.scale(0.9f)
Modifier.canimation(visible = true, effect = custom)""")
                        ParamTable(listOf(
                            Triple("visible", "Boolean", "Controls enter/exit animation"),
                            Triple("effect", "CanimationEffect", "Composable effect — combine with + operator"),
                        ))
                        DemoBox { CanimationEffectDemo() }
                    }
                }
            }

            // ─── Modifier.canimationTransition() ───
            Box(Modifier.canimationEnter(visible = stage >= 5, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    "Modifier.canimationTransition()",
                    "Separate enter and exit effects for full control",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CodeBlock("""Modifier.canimationTransition(
    visible = isVisible,
    enter = Canimation.Fade.Up,
    exit = Canimation.Fade.Down,    // null = reverse enter
)""")
                        ParamTable(listOf(
                            Triple("visible", "Boolean", "Controls direction"),
                            Triple("enter", "CanimationEffect", "Enter effect"),
                            Triple("exit", "CanimationEffect?", "Exit effect (null reverses enter)"),
                        ))
                    }
                }
            }

            // ─── CanimationEffect Builder ───
            Box(Modifier.canimationEnter(visible = stage >= 6, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    "CanimationEffect Primitives",
                    "Build blocks for the + operator — combine any number of these",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        PrimitiveGrid()
                        CodeBlock("""// All primitives
CanimationEffect.fade(from = 0f, to = 1f)
CanimationEffect.slideUp(offset = 16.dp)
CanimationEffect.slideDown(offset = 16.dp)
CanimationEffect.slideLeft(offset = 24.dp)
CanimationEffect.slideRight(offset = 24.dp)
CanimationEffect.scale(from = 0.92f, to = 1f)
CanimationEffect.pop(from = 0.8f)
CanimationEffect.rotate(fromDegrees = -15f)
CanimationEffect.spin(fromDegrees = -360f)
CanimationEffect.zoom(from = 0.5f)
CanimationEffect.bounce()
CanimationEffect.duration(ms = 400)
CanimationEffect.easing(myEasing)""")
                    }
                }
            }

            // ─── Canimation Namespace ───
            Box(Modifier.canimationEnter(visible = stage >= 7, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    "Canimation Namespace",
                    "Pre-built effect combos — IDE autocomplete friendly",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        NamespaceRow("Canimation.Fade", listOf("In", "Up", "Down", "Left", "Right", "Gentle", "UpBig", "DownBig"))
                        NamespaceRow("Canimation.Scale", listOf("In", "Up", "Pop", "Zoom", "Expand", "Shrink"))
                        NamespaceRow("Canimation.Slide", listOf("Left", "Right", "Up", "Down", "LeftBig", "RightBig"))
                        NamespaceRow("Canimation.Rotate", listOf("In", "Clockwise", "Spin"))
                        NamespaceRow("Canimation.Bounce", listOf("In", "Down", "Up"))
                        NamespaceRow("Canimation.Spring", listOf("In", "Up"))
                        NamespaceRow("Canimation.Flip", listOf("In", "Up"))
                    }
                }
            }

            // ─── Classic: CanimationVisibility ───
            Box(Modifier.canimationEnter(visible = stage >= 8, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    "CanimationVisibility",
                    "AnimatedVisibility wrapper with preset-based enter/exit",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CodeBlock("""CanimationVisibility(
    visible = visible,
    enterPreset = CanimationPreset.BounceIn,
    exitPreset = CanimationPreset.FadeDown,
) {
    Text("Content here")
}""")
                        ParamTable(listOf(
                            Triple("visible", "Boolean", "Controls show/hide"),
                            Triple("enterPreset", "CanimationPreset", "Preset for enter transition"),
                            Triple("exitPreset", "CanimationPreset", "Preset for exit (defaults to enter)"),
                        ))
                        DemoBox { VisibilityApiDemo() }
                    }
                }
            }

            // ─── Classic: Modifier.canimationEnter ───
            Box(Modifier.canimationEnter(visible = stage >= 9, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    "Modifier.canimationEnter",
                    "Modifier-based enter animation — great for lists and lazy layouts",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CodeBlock("""items.forEachIndexed { i, item ->
    var show by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { delay(i * 60L); show = true }

    Card(
        Modifier.canimationEnter(
            visible = show,
            preset = CanimationPreset.FadeUp,
        )
    ) { Text(item.name) }
}""")
                        DemoBox { EnterModifierDemo() }
                    }
                }
            }

            // ─── Modifier.canimationEmphasize ───
            Box(Modifier.canimationEnter(visible = stage >= 10, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    "Modifier.canimationEmphasize",
                    "Attention animations — pulse, tada, wobble and more",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CodeBlock("""Button(
    modifier = Modifier.canimationEmphasize(
        active = hasNotification,
        preset = CanimationPreset.Pulse,
    ),
    onClick = { /* ... */ },
) { Text("Notifications") }""")
                        DemoBox { EmphasizeDemo() }
                    }
                }
            }

            // ─── CanimationProvider ───
            Box(Modifier.canimationEnter(visible = stage >= 11, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    "CanimationProvider",
                    "Global motion policy — a11y control for your entire app",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CodeBlock("""CanimationProvider(
    policy = CanimationPolicy.SystemAware,
) { App() }

// Policies:
// SystemAware    — Follows OS reduced-motion
// AlwaysFull     — Full animations always
// AlwaysReduced  — Reduced motion always
// AlwaysOff      — Disable all animations""")
                        DemoBox { PolicyDemo() }
                    }
                }
            }

            // ─── Presets Reference ───
            Box(Modifier.canimationEnter(visible = stage >= 12, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    "Preset Reference",
                    "83+ built-in presets organized by category",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        PresetRow("Fade", listOf("Fade", "FadeUp", "FadeDown", "FadeInLeft", "FadeInRight", "GentleFade", "FadeThrough"))
                        PresetRow("Scale", listOf("ScaleIn", "ScaleUp", "ZoomIn", "ZoomOut", "Pop", "Expand"))
                        PresetRow("Slide", listOf("SlideLeft", "SlideRight", "SlideUp", "SlideDown", "SharedAxisX", "SharedAxisY"))
                        PresetRow("Bounce", listOf("BounceIn", "BounceInDown", "BounceInUp", "BounceInLeft", "BounceInRight"))
                        PresetRow("Rotate", listOf("RotateIn", "RotateClockwise", "SpinIn", "RotateScale"))
                        PresetRow("Flip", listOf("FlipIn", "FlipInY", "FlipUp", "FlipDown", "TiltIn"))
                        PresetRow("Spring", listOf("SpringIn", "SpringSlideUp", "SpringFadeIn", "BackInUp"))
                        PresetRow("Emphasis", listOf("Pulse", "HeartBeat", "Tada", "Wobble", "Swing", "RubberBand"))
                        PresetRow("Special", listOf("JackInTheBox", "RollIn", "LightSpeedInRight", "Snap"))
                    }
                }
            }

            // ─── Module Architecture ───
            Box(Modifier.canimationEnter(visible = stage >= 13, preset = CanimationPreset.FadeUp)) {
                DocSection("Modules", "Modular architecture — import only what you need") {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        ModuleRow("canimation-core", "Core engine, CanimationEffect, Modifier.canimation()")
                        ModuleRow("canimation-presets", "83+ ready-made presets (CanimationPreset enum)")
                        ModuleRow("canimation-a11y", "Accessibility helpers, reduced-motion detection")
                        ModuleRow("canimation-diagnostics", "Debug overlays, animation performance logging")
                        ModuleRow("canimation-test", "Testing utilities for animation assertions")
                    }
                }
            }

            // ─── Platform Support ───
            Box(Modifier.canimationEnter(visible = stage >= 14, preset = CanimationPreset.FadeUp)) {
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
                        Text(
                            "Compose Multiplatform",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            listOf("Android", "iOS", "Desktop (JVM)", "Web (JS)", "Web (WasmJs)").forEach {
                                Surface(
                                    shape = RoundedCornerShape(50),
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                ) {
                                    Text(
                                        it,
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

            Spacer(Modifier.height(40.dp))
        }
    }
}

// ──────────────────────────── Shared Components ────────────────────────────

@Composable
private fun SectionLabel(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.5.sp,
    )
}

@Composable
private fun FeatureCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    desc: String,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
    ) {
        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                modifier = Modifier.size(40.dp),
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(22.dp))
                }
            }
            Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
            Text(desc, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 18.sp)
        }
    }
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
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(28.dp),
        ) {
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
private fun DemoBox(content: @Composable () -> Unit) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("LIVE DEMO", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            content()
        }
    }
}

@Composable
private fun ParamTable(params: List<Triple<String, String, String>>) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth().padding(bottom = 8.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Param", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Type", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Description", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            params.forEach { (name, type, desc) ->
                Row(Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(name, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                    Text(type, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
                    Text(desc, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2f))
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PrimitiveGrid() {
    val primitives = listOf(
        "fade()" to "Alpha transition",
        "slideUp()" to "Slide from below",
        "slideDown()" to "Slide from above",
        "slideLeft()" to "Slide from left",
        "slideRight()" to "Slide from right",
        "scale()" to "Scale transition",
        "pop()" to "Overshoot scale",
        "rotate()" to "Rotation entry",
        "spin()" to "Full 360° spin",
        "zoom()" to "Zoom + fade",
        "bounce()" to "Bouncy entry",
        "duration()" to "Custom timing",
    )
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        primitives.forEach { (name, desc) ->
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f)),
            ) {
                Column(Modifier.padding(10.dp)) {
                    Text(name, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.tertiary, fontWeight = FontWeight.Bold)
                    Text(desc, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
private fun NamespaceRow(namespace: String, members: List<String>) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            namespace,
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.widthIn(min = 140.dp),
        )
        Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.outlineVariant, modifier = Modifier.size(16.dp))
        Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            members.forEach { m ->
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f),
                ) {
                    Text(
                        ".$m",
                        Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        fontFamily = FontFamily.Monospace,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }
            }
        }
    }
}

@Composable
private fun PresetRow(category: String, presets: List<String>) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
        ) {
            Text(
                category,
                Modifier.padding(horizontal = 10.dp, vertical = 4.dp).widthIn(min = 70.dp),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            presets.forEach { name ->
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                ) {
                    Text(
                        name,
                        Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        fontFamily = FontFamily.Monospace,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
    }
}

@Composable
private fun ModuleRow(name: String, desc: String) {
    Row(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(Icons.Default.Extension, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
        Column {
            Text(name, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Text(desc, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

// ──────────────────────────── Live Demos ────────────────────────────

@Composable
private fun HeroDemo() {
    val effects = listOf(Canimation.Fade.Up, Canimation.Scale.Pop, Canimation.Bounce.In)
    var cycle by remember { mutableIntStateOf(0) }
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { visible = true; delay(2000); visible = false; delay(600); cycle++ } }
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
                    when (i) { 0 -> "Fade.Up"; 1 -> "Scale.Pop"; else -> "Bounce.In" },
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
private fun QuickStartDemo() {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { visible = true; delay(2200); visible = false; delay(600) } }
    Box(Modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        CanimationVisibility(visible = visible, enterPreset = CanimationPreset.FadeUp) {
            Text(
                "Hello, animated world!",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
private fun VisibilityApiDemo() {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { visible = true; delay(2500); visible = false; delay(800) } }
    Box(Modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        CanimationVisibility(visible = visible, enterPreset = CanimationPreset.BounceIn, exitPreset = CanimationPreset.FadeDown) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
            ) {
                Text(
                    "BounceIn → FadeDown",
                    Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }
    }
}

@Composable
private fun EnterModifierDemo() {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { visible = true; delay(3000); visible = false; delay(800) } }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        listOf(CanimationPreset.FadeUp, CanimationPreset.ScaleIn, CanimationPreset.SlideLeft).forEachIndexed { i, preset ->
            var show by remember { mutableStateOf(false) }
            LaunchedEffect(visible) { if (visible) { delay(i * 120L); show = true } else { show = false } }
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                modifier = Modifier.canimationEnter(visible = show, preset = preset),
            ) {
                Text(
                    preset.name, Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }
    }
}

@Composable
private fun EmphasizeDemo() {
    var active by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { active = true; delay(1800); active = false; delay(600) } }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        listOf(CanimationPreset.Pulse, CanimationPreset.Tada, CanimationPreset.Wobble).forEach { preset ->
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.secondaryContainer,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)),
                modifier = Modifier.canimationEmphasize(active = active, preset = preset),
            ) {
                Text(
                    preset.name, Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }
    }
}

@Composable
private fun PolicyDemo() {
    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        var visible by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) { while (true) { visible = true; delay(2200); visible = false; delay(600) } }
        Box(Modifier.fillMaxWidth().height(64.dp), contentAlignment = Alignment.Center) {
            CanimationVisibility(visible = visible, enterPreset = CanimationPreset.SpringIn) {
                Text(
                    "AlwaysFull policy active ✓",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }
        }
    }
}

@Composable
private fun CanimationEffectDemo() {
    val effects = listOf(
        "Fade.Up" to Canimation.Fade.Up,
        "Scale.Pop" to Canimation.Scale.Pop,
        "Slide.Left + Fade" to (Canimation.Slide.Left + Canimation.Fade.In),
        "Rotate.Spin" to Canimation.Rotate.Spin,
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

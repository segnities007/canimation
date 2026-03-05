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

// ━━━━━━━━━━━━━━━━━━━━━━━━ API Surface ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
internal fun ApiSurfaceContent(stage: Int) {
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
internal fun NamespaceContent(stage: Int) {
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
internal fun PrimitivesContent(stage: Int) {
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
internal fun AtomicDesignContent(stage: Int) {
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
                AtomicDemoRow("Canimation.Elastic.In", "elastic stretch effect", Canimation.Elastic.In)
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
internal fun PlaygroundContent(stage: Int) {
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
            "Elastic.In" to Canimation.Elastic.In,
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
internal fun ModulesContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PageTitle("Modules", "Modular architecture — import only what you need")

        ModuleRow("canimation-core", "Core engine, CanimationEffect, Modifier.canimation(), 100+ namespace effects", Icons.Default.AutoAwesome)
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Platform ━━━━━━━━━━━━━━━━━━━━━━━━

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun PlatformContent(stage: Int) {
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

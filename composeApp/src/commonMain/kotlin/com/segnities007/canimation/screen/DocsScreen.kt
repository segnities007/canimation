package com.segnities007.canimation.screen

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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

@Composable
fun DocsScreen(
    modifier: Modifier = Modifier,
) {
    var entryStage by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        for (i in 0..9) { delay(60); entryStage = i }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 960.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            // Header
            Box(Modifier.canimationEnter(visible = entryStage >= 0, preset = CanimationPreset.FadeUp)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "DOCUMENTATION",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Getting Started",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Everything you need to add beautiful animations to your Compose Multiplatform app",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            // Installation
            Box(Modifier.canimationEnter(visible = entryStage >= 1, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    title = "Installation",
                    description = "Add canimation to your project",
                ) {
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

            // Quick Start
            Box(Modifier.canimationEnter(visible = entryStage >= 2, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    title = "Quick Start",
                    description = "Add your first animation in 3 lines",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CodeBlock("""// 1. Wrap content with CanimationVisibility
var visible by remember { mutableStateOf(false) }

CanimationVisibility(
    visible = visible,
    enterPreset = CanimationPreset.FadeUp,
) {
    Text("Hello, animated world!")
}""")
                        // Live demo
                        QuickStartDemo()
                    }
                }
            }

            // API Reference — CanimationVisibility
            Box(Modifier.canimationEnter(visible = entryStage >= 3, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    title = "CanimationVisibility",
                    description = "AnimatedVisibility wrapper with preset-based enter/exit animations",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CodeBlock("""CanimationVisibility(
    visible: Boolean,
    enterPreset: CanimationPreset,
    exitPreset: CanimationPreset = enterPreset,
    content: @Composable () -> Unit,
)""")
                        ParamTable(listOf(
                            Triple("visible", "Boolean", "Controls whether content is shown"),
                            Triple("enterPreset", "CanimationPreset", "Animation preset for enter transition"),
                            Triple("exitPreset", "CanimationPreset", "Animation preset for exit (defaults to enterPreset)"),
                        ))
                        VisibilityApiDemo()
                    }
                }
            }

            // API Reference — Modifier.canimationEnter
            Box(Modifier.canimationEnter(visible = entryStage >= 4, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    title = "Modifier.canimationEnter",
                    description = "Modifier-based enter animation — great for lists and lazy layouts",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CodeBlock("""Modifier.canimationEnter(
    visible: Boolean,
    preset: CanimationPreset,
)""")
                        ParamTable(listOf(
                            Triple("visible", "Boolean", "Triggers the enter animation when true"),
                            Triple("preset", "CanimationPreset", "Which animation preset to use"),
                        ))
                        EnterModifierDemo()
                    }
                }
            }

            // API Reference — Modifier.canimationEmphasize
            Box(Modifier.canimationEnter(visible = entryStage >= 5, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    title = "Modifier.canimationEmphasize",
                    description = "Attention-grabbing animations for hover, tap, or state changes",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CodeBlock("""Modifier.canimationEmphasize(
    active: Boolean,
    preset: CanimationPreset,
)""")
                        ParamTable(listOf(
                            Triple("active", "Boolean", "When true, applies the emphasis animation"),
                            Triple("preset", "CanimationPreset", "Emphasis preset (Pulse, Tada, Wobble, etc.)"),
                        ))
                        EmphasizeDemo()
                    }
                }
            }

            // CanimationProvider
            Box(Modifier.canimationEnter(visible = entryStage >= 6, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    title = "CanimationProvider",
                    description = "Global motion policy — control animation behavior across your entire app",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CodeBlock("""CanimationProvider(
    policy: CanimationPolicy = CanimationPolicy.SystemAware,
) {
    // All canimation children respect this policy
    App()
}

// Available policies:
// CanimationPolicy.SystemAware    — Follows OS reduced-motion setting
// CanimationPolicy.AlwaysFull     — Full animations regardless of OS
// CanimationPolicy.AlwaysReduced  — Reduced motion for all
// CanimationPolicy.AlwaysOff      — Disable all animations""")
                        PolicyDemo()
                    }
                }
            }

            // NEW: Modifier.canimation() — recommended API
            Box(Modifier.canimationEnter(visible = entryStage >= 7, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    title = "Modifier.canimation() ✨",
                    description = "Recommended API — composable effects with the + operator",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CodeBlock("""// Compose effects with the + operator
val effect = CanimationEffect.fade() + CanimationEffect.slideUp(16.dp)
Modifier.canimation(visible = true, effect = effect)

// Or use the hierarchical namespace
Modifier.canimation(visible = true, effect = Canimation.Fade.Up)
Modifier.canimation(visible = true, effect = Canimation.Scale.Pop)

// Combine any effects freely
Modifier.canimation(
    visible = true,
    effect = Canimation.Fade.In + Canimation.Scale.In + CanimationEffect.rotate(-10f),
)""")
                        ParamTable(listOf(
                            Triple("visible", "Boolean", "Controls enter/exit animation"),
                            Triple("effect", "CanimationEffect", "Composable effect — combine with + operator"),
                        ))
                        CanimationEffectDemo()
                    }
                }
            }

            // NEW: CanimationEffect reference
            Box(Modifier.canimationEnter(visible = entryStage >= 8, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    title = "CanimationEffect Builder",
                    description = "Build effects from primitives — fade, slide, scale, rotate, zoom, bounce",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        CodeBlock("""// Primitives
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
CanimationEffect.easing(EmphasizedDecelerateEasing)""")
                    }
                }
            }

            // NEW: Canimation namespace
            Box(Modifier.canimationEnter(visible = entryStage >= 9, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    title = "Canimation Namespace",
                    description = "Pre-built effect combos — discoverable via IDE autocomplete",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        EffectNamespaceRow("Canimation.Fade", listOf(".In", ".Up", ".Down", ".Left", ".Right", ".Gentle", ".UpBig", ".DownBig"))
                        EffectNamespaceRow("Canimation.Scale", listOf(".In", ".Up", ".Pop", ".Zoom", ".Expand", ".Shrink"))
                        EffectNamespaceRow("Canimation.Slide", listOf(".Left", ".Right", ".Up", ".Down", ".LeftBig", ".RightBig"))
                        EffectNamespaceRow("Canimation.Rotate", listOf(".In", ".Clockwise", ".Spin"))
                        EffectNamespaceRow("Canimation.Bounce", listOf(".In", ".Down", ".Up"))
                        EffectNamespaceRow("Canimation.Spring", listOf(".In", ".Up"))
                        EffectNamespaceRow("Canimation.Flip", listOf(".In", ".Up"))
                    }
                }
            }

            // Presets overview
            Box(Modifier.canimationEnter(visible = entryStage >= 6, preset = CanimationPreset.FadeUp)) {
                DocSection(
                    title = "Available Presets",
                    description = "83+ built-in animation presets organized by category",
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        PresetCategoryRow("Fade", listOf("Fade", "FadeUp", "FadeDown", "FadeInLeft", "FadeInRight", "GentleFade", "FadeThrough"))
                        PresetCategoryRow("Scale", listOf("ScaleIn", "ScaleUp", "ZoomIn", "ZoomOut", "Pop", "Expand"))
                        PresetCategoryRow("Slide", listOf("SlideLeft", "SlideRight", "SlideUp", "SlideDown", "SharedAxisX", "SharedAxisY"))
                        PresetCategoryRow("Bounce", listOf("BounceIn", "BounceInDown", "BounceInUp", "BounceInLeft", "BounceInRight"))
                        PresetCategoryRow("Rotate", listOf("RotateIn", "RotateClockwise", "SpinIn", "RotateScale"))
                        PresetCategoryRow("Flip", listOf("FlipIn", "FlipInY", "FlipUp", "FlipDown", "TiltIn"))
                        PresetCategoryRow("Spring", listOf("SpringIn", "SpringSlideUp", "SpringFadeIn", "BackInUp"))
                        PresetCategoryRow("Emphasis", listOf("Pulse", "HeartBeat", "Tada", "Wobble", "Swing", "RubberBand", "Bounce"))
                        PresetCategoryRow("Special", listOf("JackInTheBox", "RollIn", "LightSpeedInRight", "Snap"))
                    }
                }
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun DocSection(
    title: String,
    description: String,
    content: @Composable () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            content()
        }
    }
}

@Composable
private fun CodeBlock(code: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = code,
            modifier = Modifier
                .padding(16.dp)
                .horizontalScroll(rememberScrollState()),
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun ParamTable(params: List<Triple<String, String, String>>) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text("Parameter", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Type", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Description", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            params.forEach { (name, type, desc) ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(name, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1f))
                    Text(type, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
                    Text(desc, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(2f))
                }
            }
        }
    }
}

@Composable
private fun QuickStartDemo() {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { visible = true; delay(2200); visible = false; delay(600) }
    }
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(80.dp),
            contentAlignment = Alignment.Center,
        ) {
            CanimationVisibility(
                visible = visible,
                enterPreset = CanimationPreset.FadeUp,
            ) {
                Text(
                    text = "Hello, animated world!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
private fun VisibilityApiDemo() {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { visible = true; delay(2500); visible = false; delay(800) }
    }
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(100.dp),
            contentAlignment = Alignment.Center,
        ) {
            CanimationVisibility(
                visible = visible,
                enterPreset = CanimationPreset.BounceIn,
                exitPreset = CanimationPreset.FadeDown,
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
                ) {
                    Text(
                        text = "Bounce In → Fade Down",
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }
}

@Composable
private fun EnterModifierDemo() {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { visible = true; delay(3000); visible = false; delay(800) }
    }
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            listOf(CanimationPreset.FadeUp, CanimationPreset.ScaleIn, CanimationPreset.SlideLeft).forEachIndexed { i, preset ->
                var itemVisible by remember { mutableStateOf(false) }
                LaunchedEffect(visible) {
                    if (visible) { delay(i * 120L); itemVisible = true } else { itemVisible = false }
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                    modifier = Modifier.canimationEnter(visible = itemVisible, preset = preset),
                ) {
                    Text(
                        text = preset.name,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }
}

@Composable
private fun EmphasizeDemo() {
    var active by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { active = true; delay(1800); active = false; delay(600) }
    }
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            listOf(CanimationPreset.Pulse, CanimationPreset.Tada, CanimationPreset.Wobble).forEach { preset ->
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)),
                    modifier = Modifier.canimationEmphasize(active = active, preset = preset),
                ) {
                    Text(
                        text = preset.name,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }
            }
        }
    }
}

@Composable
private fun PolicyDemo() {
    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        var visible by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            while (true) { visible = true; delay(2200); visible = false; delay(600) }
        }
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(80.dp),
                contentAlignment = Alignment.Center,
            ) {
                CanimationVisibility(
                    visible = visible,
                    enterPreset = CanimationPreset.SpringIn,
                ) {
                    Text(
                        text = "AlwaysFull policy active",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }
            }
        }
    }
}

@Composable
private fun PresetCategoryRow(category: String, presets: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = category,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState()),
        ) {
            presets.forEach { name ->
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                ) {
                    Text(
                        text = name,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
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
private fun EffectNamespaceRow(namespace: String, members: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = namespace,
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.horizontalScroll(rememberScrollState()),
        ) {
            members.forEach { member ->
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f)),
                ) {
                    Text(
                        text = member,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
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
private fun CanimationEffectDemo() {
    val effects = listOf(
        "Fade.Up" to Canimation.Fade.Up,
        "Scale.Pop" to Canimation.Scale.Pop,
        "Slide.Left + Fade" to (Canimation.Slide.Left + Canimation.Fade.In),
    )
    var cycle by remember { mutableIntStateOf(0) }
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) { visible = true; delay(2500); visible = false; delay(800); cycle++ }
    }
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f)),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            effects.forEachIndexed { i, (label, effect) ->
                var itemVisible by remember { mutableStateOf(false) }
                LaunchedEffect(visible) {
                    if (visible) { delay(i * 150L); itemVisible = true } else { itemVisible = false }
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f)),
                    modifier = Modifier.canimation(visible = itemVisible, effect = effect),
                ) {
                    Text(
                        text = label,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                    )
                }
            }
        }
    }
}

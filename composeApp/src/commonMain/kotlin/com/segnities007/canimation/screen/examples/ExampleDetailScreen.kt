package com.segnities007.canimation.screen.examples

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@Composable
fun ExampleDetailScreen(
    itemIndex: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val galleryItem = remember(itemIndex) {
        allGalleryItems.getOrNull(itemIndex)
    }

    if (galleryItem == null) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Item not found")
        }
        return
    }

    val item = galleryItem.item
    var entryStage by remember { mutableIntStateOf(-1) }
    LaunchedEffect(Unit) {
        for (i in 0..4) { delay(60); entryStage = i }
    }

    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 800.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 32.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                // Header
                Box(Modifier.canimation(visible = entryStage >= 0, effect = Canimation.Fade.Up)) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            TextButton(onClick = onBack) { Text("← Gallery") }
                            Text(
                                text = galleryItem.tag,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }

                // Live Demo
                Box(Modifier.canimation(visible = entryStage >= 1, effect = Canimation.Fade.Up)) {
                    SectionCard(title = "Live Demo") {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            modifier = Modifier.fillMaxWidth().height(220.dp),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center,
                            ) {
                                LivePreview(item = item)
                            }
                        }
                    }
                }

                // Usage / Code
                Box(Modifier.canimation(visible = entryStage >= 2, effect = Canimation.Fade.Up)) {
                    SectionCard(title = "Usage") {
                        CodeBlock(generateUsageCode(item))
                    }
                }

                // API Details
                Box(Modifier.canimation(visible = entryStage >= 3, effect = Canimation.Fade.Up)) {
                    SectionCard(title = "Implementation Details") {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            DetailRow("Type", formatDemoType(item.demoType))
                            DetailRow("Category", galleryItem.tag)

                            if (item.effect != null) {
                                DetailRow("Effect", describeEffect(item.effect))
                            }
                            if (item.enterEffect != null) {
                                DetailRow("Enter Effect", describeEffect(item.enterEffect))
                            }
                            if (item.exitEffect != null) {
                                DetailRow("Exit Effect", describeEffect(item.exitEffect))
                            }
                            if (item.demoType == "component") {
                                DetailRow("Component", item.componentKey ?: "—")
                            }

                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "How it works",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = generateHowItWorks(item),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }

                // Integration Guide
                Box(Modifier.canimation(visible = entryStage >= 4, effect = Canimation.Fade.Up)) {
                    SectionCard(title = "Integration Guide") {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Text(
                                text = generateIntegrationGuide(item, galleryItem.tag),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            CodeBlock(generateFullExample(item))
                        }
                    }
                }
            }
        }
    }
}

// ===== Section Components =====

@Composable
private fun SectionCard(
    title: String,
    content: @Composable () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            content()
        }
    }
}

@Composable
private fun CodeBlock(code: String) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = code,
            modifier = Modifier.padding(16.dp),
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold,
        )
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
        ) {
            Text(
                text = value,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

// ===== Content Generators =====

private fun formatDemoType(demoType: String): String = when (demoType) {
    "effect" -> "Modifier.canimation"
    "transition" -> "Modifier.canimationTransition"
    "composition" -> "Effect Composition (+)"
    "stagger" -> "Stagger Pattern"
    "emphasis" -> "Modifier.canimationEmphasize"
    "realWorld" -> "Real World Pattern"
    "component" -> "Rich Component"
    "enterExit" -> "Enter / Exit"
    "visibility" -> "CanimationVisibility"
    else -> demoType
}

private fun describeEffect(effect: io.github.canimation.core.CanimationEffect): String {
    val parts = mutableListOf<String>()
    effect.alpha?.let { parts.add("alpha: ${it.from}→${it.to}") }
    effect.offsetX?.let { parts.add("offsetX: ${it.from}→${it.to}") }
    effect.offsetY?.let { parts.add("offsetY: ${it.from}→${it.to}") }
    effect.scale?.let { parts.add("scale: ${it.from}→${it.to}") }
    effect.rotation?.let { parts.add("rotation: ${it.from}°→${it.to}°") }
    parts.add("${effect.durationMs}ms")
    return if (parts.size == 1) "Default (${effect.durationMs}ms)" else parts.joinToString(", ")
}

private fun generateHowItWorks(item: ExampleItem): String = when (item.demoType) {
    "effect" -> """This animation uses Modifier.canimation() to animate element properties between hidden and visible states. When visible becomes true, the element transitions from the initial effect values (offset, alpha, scale, rotation) to their default state using an easing curve. The animation is GPU-accelerated through graphicsLayer."""
    "transition" -> """This uses Modifier.canimationTransition() with separate enter and exit effects. The enter effect plays when visible becomes true, and the exit effect (reversed) plays when visible becomes false. This allows asymmetric transitions — for example, sliding in from the left but fading out."""
    "composition" -> """Effects are combined using the + operator. When two CanimationEffect objects are combined, the right-hand side fills in any null values from the left-hand side. This lets you compose complex multi-property animations from simple building blocks: Canimation.Fade.Up + Canimation.Rotate.In creates a fade-up with rotation."""
    "stagger" -> """Stagger patterns apply the same animation to multiple elements with increasing delays. Each item receives a delay of index × staggerMs, creating a cascading wave effect. Use Canimation.Stagger constants (Fast=60ms, Normal=80ms, Slow=120ms) to control timing."""
    "emphasis" -> """Modifier.canimationEmphasize() creates attention-seeking animations triggered by a boolean state. Unlike enter/exit animations, emphasis effects play while the element is already visible — pulse, shake, bounce, etc. Useful for form validation feedback, notification badges, or interactive highlights."""
    "component" -> """This is a rich animated component that uses Modifier.canimation() for its entry animation, combined with internal Compose animation APIs for its unique behavior. The entry animation ensures smooth appearance using the canimation system, while custom animations handle the component's specific interaction pattern."""
    else -> """This animation demonstrates the canimation library's approach to declarative animations in Compose Multiplatform. Apply effects through Modifier extensions for a clean, composable API."""
}

private fun generateIntegrationGuide(item: ExampleItem, tag: String): String = when (item.demoType) {
    "effect" -> """To use this animation in your project:
1. Add the canimation-core dependency
2. Create a boolean state for visibility
3. Apply Modifier.canimation() to your composable
4. Toggle the visibility state to trigger the animation

The animation respects CanimationProvider policy — wrap your app in CanimationProvider to globally control animation behavior (SystemAware, AlwaysFull, AlwaysReduced, AlwaysOff)."""
    "transition" -> """To use asymmetric transitions:
1. Define separate enter and exit CanimationEffect instances
2. Apply Modifier.canimationTransition(visible, enter, exit)
3. The enter effect plays forward when visible=true
4. The exit effect plays in reverse when visible=false

Combine with CanimationVisibility for composable-level enter/exit."""
    "composition" -> """To compose effects:
1. Combine effects with the + operator: effectA + effectB
2. The right-hand side overrides non-null values of the left
3. Use this to create complex animations from simple primitives
4. Example: Canimation.Fade.Up + Canimation.Scale.Pop"""
    "component" -> """This component uses canimation for entry animation:
1. Import the component from the examples package
2. The component handles its own internal animation logic
3. Entry animation is powered by Modifier.canimation()
4. Wrap in CanimationProvider to control animation policy"""
    else -> """Add canimation-core to your dependencies and apply the appropriate Modifier extension. All animations work cross-platform on Android, iOS, Desktop, and Web (JS/WASM)."""
}

private fun generateUsageCode(item: ExampleItem): String = when (item.demoType) {
    "effect", "transition", "composition", "stagger", "emphasis",
    "enterExit", "visibility", "realWorld" -> {
        val snippet = item.codeSnippet.trimIndent()
        "var visible by remember { mutableStateOf(false) }\n" +
            "LaunchedEffect(Unit) { visible = true }\n\n" +
            "Box(\n" +
            "    modifier = ${snippet}\n" +
            ") {\n" +
            "    // Your content here\n" +
            "}"
    }
    "component" -> {
        val snippet = item.codeSnippet.trimIndent()
        "// Component with canimation entry animation\n" +
            snippet + "\n\n" +
            "// The component uses Modifier.canimation() internally:\n" +
            "// modifier.canimation(visible = entryVisible, effect = Canimation.XXX.YYY)"
    }
    else -> item.codeSnippet
}

private fun generateFullExample(item: ExampleItem): String = when (item.demoType) {
    "effect", "transition", "composition", "stagger", "emphasis",
    "enterExit", "visibility", "realWorld" -> {
        val snippet = item.codeSnippet.trimIndent()
        "@Composable\n" +
            "fun MyScreen() {\n" +
            "    var visible by remember { mutableStateOf(false) }\n" +
            "    LaunchedEffect(Unit) { visible = true }\n\n" +
            "    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {\n" +
            "        Box(\n" +
            "            modifier = ${snippet.lines().joinToString("\n                ")}\n" +
            "        ) {\n" +
            "            Text(\"Hello, Canimation!\")\n" +
            "        }\n" +
            "    }\n" +
            "}"
    }
    "component" -> {
        val snippet = item.codeSnippet.trimIndent()
        "@Composable\n" +
            "fun MyScreen() {\n" +
            "    // The component uses Modifier.canimation() internally\n" +
            "    // for its entry animation\n" +
            "    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {\n" +
            "        ${snippet.lines().joinToString("\n        ")}\n" +
            "    }\n" +
            "}"
    }
    else -> {
        val snippet = item.codeSnippet.trimIndent()
        "@Composable\n" +
            "fun MyScreen() {\n" +
            "    ${snippet.lines().joinToString("\n    ")}\n" +
            "}"
    }
}

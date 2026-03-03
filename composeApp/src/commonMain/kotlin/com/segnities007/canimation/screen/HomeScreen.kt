package com.segnities007.canimation.screen

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.canimationEnter
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val presetCount = CanimationPreset.entries.size
    var stage by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        for (i in 0..6) {
            delay(80)
            stage = i
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(Modifier.canimationEnter(visible = stage >= 0, preset = CanimationPreset.FadeUp)) {
            HeroSection(presetCount = presetCount, onNavigate = onNavigate)
        }
        Box(Modifier.canimationEnter(visible = stage >= 1, preset = CanimationPreset.FadeUp)) {
            CodeExampleSection()
        }
        Box(Modifier.canimationEnter(visible = stage >= 2, preset = CanimationPreset.FadeUp)) {
            InteractiveDemoSection()
        }
        Box(Modifier.canimationEnter(visible = stage >= 3, preset = CanimationPreset.FadeUp)) {
            FeaturesSection(presetCount = presetCount)
        }
        Box(Modifier.canimationEnter(visible = stage >= 4, preset = CanimationPreset.FadeUp)) {
            ExploreSection(presetCount = presetCount, onNavigate = onNavigate)
        }
        Box(Modifier.canimationEnter(visible = stage >= 5, preset = CanimationPreset.FadeUp)) {
            PlatformSection()
        }
        Box(Modifier.canimationEnter(visible = stage >= 6, preset = CanimationPreset.FadeUp)) {
            FooterSection()
        }
    }
}

@Composable
private fun HeroSection(presetCount: Int, onNavigate: (String) -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 800.dp)
                    .padding(horizontal = 32.dp, vertical = 56.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = "canimation",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Text(
                    text = "Accessible. Composable. Multiplatform.",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Token-based animations with $presetCount presets, accessibility support, " +
                        "diagnostics overlay, and deterministic testing — " +
                        "across Android, iOS, Desktop, and Web.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                )
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(onClick = { onNavigate("presets") }) {
                        Text("Get Started")
                    }
                    FilledTonalButton(onClick = { onNavigate("tokens") }) {
                        Text("Documentation")
                    }
                }
            }
        }
    }
}

@Composable
private fun CodeExampleSection() {
    CenteredSection {
        Text(
            text = "Quick Start",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF1E1E2E),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = """
                    |@Composable
                    |fun App() {
                    |    CanimationProvider(
                    |        policy = CanimationPolicy.SystemAware
                    |    ) {
                    |        CanimationVisibility(
                    |            visible = isVisible,
                    |            enterPreset = CanimationPreset.FadeUp,
                    |        ) {
                    |            Text("Hello, canimation!")
                    |        }
                    |    }
                    |}
                """.trimMargin(),
                modifier = Modifier.padding(20.dp),
                fontFamily = FontFamily.Monospace,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFCDD6F4),
            )
        }
    }
}

@Composable
private fun InteractiveDemoSection() {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
        modifier = Modifier.fillMaxWidth(),
    ) {
        CenteredSection {
            Text(
                text = "Try it",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
            var heroVisible by remember { mutableStateOf(false) }
            ElevatedCard(
                onClick = { heroVisible = !heroVisible },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        "Tap to preview FadeUp animation",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Spacer(Modifier.height(16.dp))
                    CanimationVisibility(
                        visible = heroVisible,
                        enterPreset = CanimationPreset.FadeUp,
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                            ),
                        ) {
                            Text(
                                text = "Hello, canimation! 🎬",
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FeaturesSection(presetCount: Int) {
    CenteredSection {
        Text(
            text = "Why canimation?",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                FeatureCard("🎯", "$presetCount Presets", "Bounce, Spring, Flip, Material Motion and more — with Full & Reduced variants", Modifier.weight(1f))
                FeatureCard("♿", "Accessibility First", "System-aware Reduce Motion with Full, Reduced, and Off policies", Modifier.weight(1f))
                FeatureCard("📊", "Diagnostics", "Real-time FPS, frame time, and jank overlay for performance monitoring", Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                FeatureCard("🧪", "Testable", "Deterministic test clock for reliable animation assertions", Modifier.weight(1f))
                FeatureCard("🌍", "Multiplatform", "Android, iOS, Desktop (JVM), and Web (JS / WasmJs) support", Modifier.weight(1f))
                FeatureCard("🎛️", "Token System", "Duration, easing, distance, and spring tokens for consistent design", Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun ExploreSection(presetCount: Int, onNavigate: (String) -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
        modifier = Modifier.fillMaxWidth(),
    ) {
        CenteredSection {
            Text(
                text = "Explore",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
            listOf(
                Triple("Preset Gallery", "All $presetCount built-in presets with interactive demos", "presets"),
                Triple("Custom Spec Lab", "Build your own animation specs with live preview", "custom"),
                Triple("Accessibility", "Full / Reduced / Off motion policy comparison", "a11y"),
                Triple("Diagnostics", "FPS overlay and performance monitoring tools", "diagnostics"),
                Triple("Token Reference", "Duration, easing, distance, and spring design tokens", "tokens"),
            ).forEach { (title, description, route) ->
                ElevatedCard(
                    onClick = { onNavigate(route) },
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
                            )
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

@Composable
private fun PlatformSection() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 800.dp)
                .padding(horizontal = 32.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = "Built for every platform",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                listOf("Android", "iOS", "Desktop", "Web").forEach { platform ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                    ) {
                        Text(
                            text = platform,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FooterSection() {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Apache License 2.0 · canimation contributors",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun CenteredSection(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 800.dp)
                .padding(horizontal = 32.dp, vertical = 48.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            content()
        }
    }
}

@Composable
private fun FeatureCard(
    emoji: String,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = emoji,
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

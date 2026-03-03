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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationProvider
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
            ExploreSection(presetCount = presetCount, onNavigate = onNavigate)
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
                Box(Modifier.canimationEnter(visible = stage >= 0, preset = CanimationPreset.FadeUp)) {
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

                Box(Modifier.canimationEnter(visible = stage >= 1, preset = CanimationPreset.FadeUp)) {
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
                        "$presetCount Presets" to CanimationPreset.FadeUp,
                        "Accessible" to CanimationPreset.ScaleIn,
                        "Multiplatform" to CanimationPreset.BounceIn,
                        "Testable" to CanimationPreset.SlideLeft,
                        "Open Source" to CanimationPreset.Pop,
                    ).forEachIndexed { index, (label, preset) ->
                        Box(Modifier.canimationEnter(visible = stage >= 2 + index, preset = preset)) {
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

// ======= LIVE SHOWCASE =======

@Composable
private fun LiveShowcaseSection(stage: Int) {
    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
        DarkCenteredSection {
            Box(Modifier.canimationEnter(visible = stage >= 7, preset = CanimationPreset.FadeUp)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "SHOWCASE",
                        style = MaterialTheme.typography.labelMedium,
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

            val showcasePresets = listOf(
                CanimationPreset.FadeUp, CanimationPreset.ScaleIn,
                CanimationPreset.BounceIn, CanimationPreset.SlideLeft,
                CanimationPreset.SpinIn, CanimationPreset.FlipIn,
                CanimationPreset.SpringIn, CanimationPreset.EmphasizedEntry,
            )
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                showcasePresets.chunked(4).forEachIndexed { rowIndex, row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        row.forEachIndexed { colIndex, preset ->
                            AnimatedPresetCell(
                                preset = preset,
                                delayMs = ((rowIndex * 4 + colIndex) * 250L),
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
private fun AnimatedPresetCell(
    preset: CanimationPreset,
    delayMs: Long,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(delayMs)
        while (true) {
            visible = true
            delay(2200)
            visible = false
            delay(600)
        }
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
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
                CanimationVisibility(
                    visible = visible,
                    enterPreset = preset,
                    exitPreset = preset,
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.25f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
                    ) {
                        Box(Modifier.size(40.dp))
                    }
                }
            }
            Text(
                text = preset.name,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

// ======= FEATURES WITH LIVE DEMOS =======

@Composable
private fun FeaturesSection(stage: Int, presetCount: Int) {
    DarkCenteredSection {
        Box(Modifier.canimationEnter(visible = stage >= 8, preset = CanimationPreset.FadeUp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "WHY CANIMATION",
                    style = MaterialTheme.typography.labelMedium,
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
                modifier = Modifier.fillMaxWidth(),
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
                modifier = Modifier.fillMaxWidth(),
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
            Box(Modifier.canimationEnter(visible = stage >= 9, preset = CanimationPreset.FadeUp)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "QUICK START",
                        style = MaterialTheme.typography.labelMedium,
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
private fun ExploreSection(presetCount: Int, onNavigate: (String) -> Unit) {
    DarkCenteredSection {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "EXPLORE",
                style = MaterialTheme.typography.labelMedium,
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

        Spacer(Modifier.height(8.dp))

        listOf(
            Triple("Preset Gallery", "All $presetCount built-in presets with interactive demos", "presets"),
            Triple("Custom Spec Lab", "Build your own animation specs with live preview", "custom"),
            Triple("Accessibility", "Full / Reduced / Off motion policy comparison", "a11y"),
            Triple("Diagnostics", "FPS overlay and performance monitoring tools", "diagnostics"),
            Triple("Token Reference", "Duration, easing, distance, and spring design tokens", "tokens"),
        ).forEach { (title, description, route) ->
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
                Text(
                    text = "Built for every platform",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    listOf("Android", "iOS", "Desktop", "Web").forEachIndexed { index, platform ->
                        Box(Modifier.canimationEnter(
                            visible = stage >= 8 + index,
                            preset = CanimationPreset.BounceIn,
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 32.dp),
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

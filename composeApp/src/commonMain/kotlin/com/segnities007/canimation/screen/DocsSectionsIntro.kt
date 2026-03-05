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

// ━━━━━━━━━━━━━━━━━━━━━━━━ Overview ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
internal fun OverviewContent(stage: Int) {
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
internal fun QuickStartContent(stage: Int) {
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
internal fun PhilosophyContent(stage: Int) {
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

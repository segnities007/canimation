package com.segnities007.canimation.screen.docs

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
import androidx.compose.runtime.getValue
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
import canimation.composeapp.generated.resources.*
import com.segnities007.canimation.compose.LoopingIntStep
import com.segnities007.canimation.compose.rememberLoopingIntSequence
import org.jetbrains.compose.resources.stringResource

// ━━━━━━━━━━━━━━━━━━━━━━━━ Overview ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
internal fun OverviewContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                stringResource(Res.string.docs_overview_title),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Black,
            )
            Text(
                stringResource(Res.string.docs_overview_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 26.sp,
            )
        }

        HeroDemo()

        SectionCard(stringResource(Res.string.docs_getting_started_title), stringResource(Res.string.docs_getting_started_desc)) {
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

        SectionCard(stringResource(Res.string.docs_atomic_design_title), stringResource(Res.string.docs_atomic_design_desc)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AtomicLevelRow(stringResource(Res.string.docs_atoms_label), stringResource(Res.string.docs_atoms_desc), stringResource(Res.string.docs_atoms_examples))
                AtomicLevelRow(stringResource(Res.string.docs_molecules_label), stringResource(Res.string.docs_molecules_desc), stringResource(Res.string.docs_molecules_examples))
                AtomicLevelRow(stringResource(Res.string.docs_organisms_label), stringResource(Res.string.docs_organisms_desc), stringResource(Res.string.docs_organisms_examples))
            }
        }

        SectionCard(stringResource(Res.string.docs_whats_new_title), stringResource(Res.string.docs_whats_new_desc)) {
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
        PageTitle(stringResource(Res.string.docs_quickstart_title), stringResource(Res.string.docs_quickstart_desc))

        StepBlock(1, stringResource(Res.string.docs_step1_title),
            """// build.gradle.kts (commonMain)
implementation("io.github.canimation:canimation-core:<version>")
implementation("io.github.canimation:canimation-presets:<version>")""")
        StepBlock(2, stringResource(Res.string.docs_step2_title),
            """CanimationProvider(policy = CanimationPolicy.SystemAware) {
    MyApp()
}""")
        StepBlock(3, stringResource(Res.string.docs_step3_title),
            """// Recommended: Effect API
Modifier.canimation(visible = show, effect = Canimation.Fade.Up)

// Compose effects freely
Modifier.canimation(visible = show, effect = Canimation.Scale.Pop + Canimation.Fade.In)

// Use the new categories
Modifier.canimation(visible = show, effect = Canimation.Entrance.Drop)
Modifier.canimation(visible = show, effect = Canimation.Material.FadeThrough)

// Attention seekers for emphasis
Modifier.canimation(visible = pulse, effect = Canimation.Attention.Tada)""")

        DemoBox(stringResource(Res.string.docs_effect_demo_title)) { EffectDemo() }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Design Philosophy ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
internal fun PhilosophyContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        PageTitle(stringResource(Res.string.docs_philosophy_title), stringResource(Res.string.docs_philosophy_desc))

        BodyText(stringResource(Res.string.docs_philosophy_body1))
        BodyText(stringResource(Res.string.docs_philosophy_body2))

        PhilosophyDemo()

        BodyText(stringResource(Res.string.docs_philosophy_body3))
    }
}

@Composable
private fun PhilosophyDemo() {
    DemoBox(stringResource(Res.string.docs_philosophy_demo_title)) {
        val step = rememberLoopingIntSequence(
            initialValue = 0,
            steps = listOf(
                LoopingIntStep(value = 0, holdDurationMillis = 800L),
                LoopingIntStep(value = 1, holdDurationMillis = 1200L),
                LoopingIntStep(value = 2, holdDurationMillis = 1200L),
                LoopingIntStep(value = 3, holdDurationMillis = 1600L),
                LoopingIntStep(value = -1, holdDurationMillis = 600L),
            ),
        )
        Column(
            Modifier.fillMaxWidth().height(120.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf(
                    stringResource(Res.string.docs_philosophy_origin) to Canimation.Fade.Left,
                    stringResource(Res.string.docs_philosophy_focus) to Canimation.Scale.Pop,
                    stringResource(Res.string.docs_philosophy_destination) to Canimation.Fade.Right,
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
                    step == 0 -> stringResource(Res.string.docs_philosophy_hint_origin)
                    step == 1 -> stringResource(Res.string.docs_philosophy_hint_focus)
                    else -> stringResource(Res.string.docs_philosophy_hint_destination)
                },
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

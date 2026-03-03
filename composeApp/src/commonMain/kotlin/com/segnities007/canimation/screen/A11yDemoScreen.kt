package com.segnities007.canimation.screen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.canimationEnter
import kotlinx.coroutines.delay

@Composable
fun A11yDemoScreen(modifier: Modifier = Modifier) {
    var replayTrigger by remember { mutableIntStateOf(0) }
    var entryStage by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        for (i in 0..3) { delay(80); entryStage = i }
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
                .padding(horizontal = 32.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Header
            Box(Modifier.canimationEnter(visible = entryStage >= 0, preset = CanimationPreset.FadeUp)) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "ACCESSIBILITY",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Motion policies in action",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Compare how animations adapt across Full, Reduced, and Off policies",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            // Replay button
            Box(Modifier.canimationEnter(visible = entryStage >= 1, preset = CanimationPreset.FadeUp)) {
                FilledTonalButton(onClick = { replayTrigger++ }) {
                    Text("▶  Replay All")
                }
            }

            // Three-column comparison
            Box(
                Modifier
                    .fillMaxWidth()
                    .canimationEnter(visible = entryStage >= 2, preset = CanimationPreset.FadeUp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    PolicyColumn(
                        label = "Full",
                        policy = CanimationPolicy.AlwaysFull,
                        replayTrigger = replayTrigger,
                        modifier = Modifier.weight(1f),
                    )
                    PolicyColumn(
                        label = "Reduced",
                        policy = CanimationPolicy.AlwaysReduced,
                        replayTrigger = replayTrigger,
                        modifier = Modifier.weight(1f),
                    )
                    PolicyColumn(
                        label = "Off",
                        policy = CanimationPolicy.AlwaysOff,
                        replayTrigger = replayTrigger,
                        modifier = Modifier.weight(1f),
                    )
                }
            }

            // Auto-replay showcase
            Box(
                Modifier
                    .fillMaxWidth()
                    .canimationEnter(visible = entryStage >= 3, preset = CanimationPreset.FadeUp),
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Text(
                            text = "Continuous Demo",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            listOf(
                                "Full" to CanimationPolicy.AlwaysFull,
                                "Reduced" to CanimationPolicy.AlwaysReduced,
                            ).forEach { (label, policy) ->
                                CanimationProvider(policy = policy) {
                                    AutoReplayCell(
                                        label = label,
                                        preset = CanimationPreset.SpringIn,
                                        modifier = Modifier.weight(1f),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PolicyColumn(
    label: String,
    policy: CanimationPolicy,
    replayTrigger: Int,
    modifier: Modifier = Modifier,
) {
    CanimationProvider(policy = policy) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                ) {
                    Text(
                        text = label,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }

                listOf(
                    CanimationPreset.FadeUp,
                    CanimationPreset.ScaleIn,
                    CanimationPreset.BounceIn,
                    CanimationPreset.SpinIn,
                    CanimationPreset.SlideLeft,
                    CanimationPreset.SpringIn,
                    CanimationPreset.FlipIn,
                    CanimationPreset.EmphasizedEntry,
                ).forEach { preset ->
                    A11yPreviewCard(preset = preset, replayTrigger = replayTrigger)
                }
            }
        }
    }
}

@Composable
private fun A11yPreviewCard(
    preset: CanimationPreset,
    replayTrigger: Int,
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(replayTrigger) {
        visible = false
        delay(200)
        visible = true
    }

    LaunchedEffect(Unit) {
        delay(400)
        visible = true
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            contentAlignment = Alignment.Center,
        ) {
            CanimationVisibility(
                visible = visible,
                enterPreset = preset,
                exitPreset = preset,
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                ) {
                    Text(
                        text = preset.name,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }
}

@Composable
private fun AutoReplayCell(
    label: String,
    preset: CanimationPreset,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            visible = true
            delay(2000)
            visible = false
            delay(600)
        }
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp).height(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
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
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
                    ) {
                        Box(Modifier.size(40.dp))
                    }
                }
            }
        }
    }
}

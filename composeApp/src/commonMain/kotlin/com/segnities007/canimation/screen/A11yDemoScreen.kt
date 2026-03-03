package com.segnities007.canimation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
        for (i in 0..2) { delay(100); entryStage = i }
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
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(Modifier.canimationEnter(visible = entryStage >= 0, preset = CanimationPreset.FadeUp)) {
                Text(
                    text = "See the difference",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
            }

            Box(Modifier.canimationEnter(visible = entryStage >= 1, preset = CanimationPreset.FadeUp)) {
                Button(onClick = { replayTrigger++ }) {
                    Text("▶ Replay All")
                }
            }

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
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
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
                CanimationPreset.SlideLeft,
                CanimationPreset.BounceIn,
                CanimationPreset.SpinIn,
            ).forEach { preset ->
                A11yPreviewCard(preset = preset, replayTrigger = replayTrigger)
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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        contentAlignment = Alignment.Center,
    ) {
        CanimationVisibility(
            visible = visible,
            enterPreset = preset,
            exitPreset = preset,
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
            ) {
                Text(
                    text = preset.name,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }
    }
}

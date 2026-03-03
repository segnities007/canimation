package com.segnities007.canimation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.canimationEmphasize
import io.github.canimation.core.canimationEnter
import io.github.canimation.diagnostics.CanimationDiagnosticsOverlay
import kotlinx.coroutines.delay

@Composable
fun DiagnosticsScreen(modifier: Modifier = Modifier) {
    var overlayEnabled by remember { mutableStateOf(true) }
    var entryStage by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        for (i in 0..2) { delay(100); entryStage = i }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(Modifier.canimationEnter(visible = entryStage >= 0, preset = CanimationPreset.FadeUp)) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "PERFORMANCE",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Diagnostics",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )
        }
        }

        // Overlay toggle
        Box(Modifier.fillMaxWidth().canimationEnter(visible = entryStage >= 1, preset = CanimationPreset.FadeUp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Diagnostics Overlay", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                Switch(
                    checked = overlayEnabled,
                    onCheckedChange = { overlayEnabled = it },
                )
            }
        }
        }

        // Stress test grid with diagnostics overlay
        Box(Modifier.fillMaxWidth().canimationEnter(visible = entryStage >= 2, preset = CanimationPreset.FadeUp)) {
        CanimationDiagnosticsOverlay(
            enabled = overlayEnabled,
            showFps = true,
            showFrameTime = true,
            jankThresholdMs = 16,
        ) {
            val items = remember { (1..24).toList() }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(items) { index ->
                    var emphasized by remember { mutableStateOf(false) }
                    Card(
                        onClick = { emphasized = !emphasized },
                        modifier = Modifier.canimationEmphasize(
                            active = emphasized,
                            preset = CanimationPreset.ScaleIn,
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (emphasized) {
                                MaterialTheme.colorScheme.primaryContainer
                            } else {
                                MaterialTheme.colorScheme.surfaceVariant
                            },
                        ),
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "#$index",
                                style = MaterialTheme.typography.titleSmall,
                            )
                            Text(
                                text = if (emphasized) "Active" else "Tap me",
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }
                    }
                }
            }
        }
        }
    }
}

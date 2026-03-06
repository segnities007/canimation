package com.segnities007.canimation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.segnities007.canimation.component.AnimationShowcase
import com.segnities007.canimation.component.PresetPreviewTuning
import com.segnities007.canimation.component.tunePresetSpec
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.presets.PresetsExtensionRegistry
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PresetGalleryScreen(
    modifier: Modifier = Modifier,
    autoPlayEnabled: Boolean = true,
    autoPlayTick: Int = 0,
    tuning: PresetPreviewTuning = PresetPreviewTuning(),
) {
    var uiState by remember { mutableStateOf(PresetGalleryUiState()) }
    val onEvent: (PresetGalleryEvent) -> Unit = { event ->
        uiState = reducePresetGalleryState(uiState, event)
    }

    LaunchedEffect(Unit) {
        for (i in 0..3) {
            onEvent(PresetGalleryEvent.HeaderStageUpdated(i))
            delay(80)
        }
    }

    val allPresetSpecs = PresetsExtensionRegistry.allPresetSpecs

    val filteredPresets = remember(allPresetSpecs, uiState.motionFilter) {
        CanimationPreset.entries.filter { preset ->
            matchesMotionFilter(allPresetSpecs.getValue(preset).fullEnter, uiState.motionFilter)
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 220.dp),
            modifier = Modifier.widthIn(max = 1200.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column(
                    modifier = Modifier.padding(bottom = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = "GALLERY",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.canimation(
                            visible = uiState.headerStage >= 0,
                            effect = Canimation.Fade.Up,
                        ),
                    )
                    Text(
                        text = "${CanimationPreset.entries.size} Presets",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.canimation(
                            visible = uiState.headerStage >= 1,
                            effect = Canimation.Fade.Up,
                        ),
                    )
                    Text(
                        text = "Browse, compare, and tune every built-in animation",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.canimation(
                            visible = uiState.headerStage >= 2,
                            effect = Canimation.Fade.Up,
                        ),
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .canimation(visible = uiState.headerStage >= 3, effect = Canimation.Fade.Up),
                    ) {
                        MotionFilter.entries.forEach { filter ->
                            FilterChip(
                                selected = uiState.motionFilter == filter,
                                onClick = { onEvent(PresetGalleryEvent.MotionFilterSelected(filter)) },
                                label = { Text(filter.label) },
                            )
                        }
                    }
                }
            }

            items(filteredPresets, key = { it.name }) { preset ->
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(preset) { visible = true }
                Box(Modifier.canimation(visible = visible, effect = Canimation.Fade.Up)) {
                    AnimationShowcase(
                        title = presetDescription(preset),
                        preset = preset,
                        baseSpec = allPresetSpecs.getValue(preset),
                        tuning = tuning,
                        autoPlayEnabled = autoPlayEnabled,
                        autoPlayTick = autoPlayTick,
                        selectedForCompare = false,
                        onCardClick = { tapped ->
                            onEvent(PresetGalleryEvent.CodeDialogOpened(tapped))
                        },
                    )
                }
            }
        }

        val dialogPreset = uiState.codeDialogPreset
        if (dialogPreset != null) {
            val dialogSpec = tunePresetSpec(allPresetSpecs.getValue(dialogPreset), tuning)
            CodeSampleDialog(
                preset = dialogPreset,
                spec = dialogSpec,
                onDismiss = { onEvent(PresetGalleryEvent.CodeDialogClosed) },
            )
        }
    }
}

@Composable
private fun CodeSampleDialog(
    preset: CanimationPreset,
    spec: CanimationPresetSpec,
    onDismiss: () -> Unit,
) {
    // Temporary(2026-06-30, ticket: CLIPBOARD-API-MIGRATION):
    // Compose 1.10.0 common API does not expose a stable plain-text ClipEntry factory.
    // Remove this suppression after LocalClipboard gains a common text writer helper.
    @Suppress("DEPRECATION")
    val clipboard = LocalClipboardManager.current
    val codeSample = remember(preset, spec) { buildPresetCodeSample(preset, spec) }
    var dialogVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { dialogVisible = true }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Box(Modifier.canimation(visible = dialogVisible, effect = Canimation.Scale.Pop)) {
                Text("${preset.name} code sample")
            }
        },
        text = {
            Box(Modifier.canimation(visible = dialogVisible, effect = Canimation.Fade.In)) {
                SelectionContainer {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(320.dp)
                            .verticalScroll(rememberScrollState()),
                    ) {
                        Text(
                            text = codeSample,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = { clipboard.setText(AnnotatedString(codeSample)) },
            ) {
                Text("Copy")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        },
    )
}

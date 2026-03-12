package com.segnities007.canimation.screen.presets

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.*
import com.segnities007.canimation.component.AnimationShowcase
import com.segnities007.canimation.component.PresetPreviewTuning
import com.segnities007.canimation.component.tunePresetSpec
import com.segnities007.canimation.compose.rememberReplayVisibility
import com.segnities007.canimation.platform.rememberClipboardTextWriter
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.canimation
import io.github.canimation.presets.PresetsExtensionRegistry
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PresetGalleryScreen(
    modifier: Modifier = Modifier,
    autoPlayEnabled: Boolean = true,
    autoPlayTick: Int = 0,
    tuning: PresetPreviewTuning = PresetPreviewTuning(),
) {
    val stateHolder = rememberPresetGalleryStateHolder()
    val uiState = stateHolder.uiState

    val allPresetSpecs = PresetsExtensionRegistry.allPresetSpecs
    val presetEntries =
        remember(allPresetSpecs) {
            buildPresetGalleryEntries(allPresetSpecs)
        }

    val filteredPresetEntries =
        remember(presetEntries, uiState.motionFilter) {
            filterPresetGalleryEntries(presetEntries, uiState.motionFilter)
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
                        text = stringResource(Res.string.gallery_label),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier =
                            Modifier.canimation(
                                visible = uiState.headerStage >= 0,
                                effect = Canimation.Fade.Up,
                            ),
                    )
                    Text(
                        text = stringResource(Res.string.gallery_presets_count, presetEntries.size),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier =
                            Modifier.canimation(
                                visible = uiState.headerStage >= 1,
                                effect = Canimation.Fade.Up,
                            ),
                    )
                    Text(
                        text = stringResource(Res.string.gallery_description),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier =
                            Modifier.canimation(
                                visible = uiState.headerStage >= 2,
                                effect = Canimation.Fade.Up,
                            ),
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier =
                            Modifier
                                .padding(top = 4.dp)
                                .canimation(visible = uiState.headerStage >= 3, effect = Canimation.Fade.Up),
                    ) {
                        MotionFilter.entries.forEach { filter ->
                            FilterChip(
                                selected = uiState.motionFilter == filter,
                                onClick = {
                                    stateHolder.onEvent(PresetGalleryEvent.MotionFilterSelected(filter))
                                },
                                label = { Text(stringResource(filter.labelRes)) },
                            )
                        }
                    }
                }
            }

            items(filteredPresetEntries, key = { it.preset.name }) { entry ->
                val visible =
                    rememberReplayVisibility(
                        replayKey = entry.preset.name,
                        resetDurationMillis = 0L,
                    )
                Box(Modifier.canimation(visible = visible, effect = Canimation.Fade.Up)) {
                    AnimationShowcase(
                        title = entry.summary,
                        preset = entry.preset,
                        baseSpec = entry.baseSpec,
                        descriptor = PresetsExtensionRegistry.descriptorFor(entry.preset),
                        tuning = tuning,
                        autoPlayEnabled = autoPlayEnabled,
                        autoPlayTick = autoPlayTick,
                        selectedForCompare = false,
                        onCardClick = { tapped ->
                            stateHolder.onEvent(PresetGalleryEvent.CodeDialogOpened(tapped))
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
                onDismiss = { stateHolder.onEvent(PresetGalleryEvent.CodeDialogClosed) },
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
    val clipboardWriter = rememberClipboardTextWriter()
    val codeSample = remember(preset, spec) { buildPresetGalleryCodeSample(preset, spec) }
    val dialogVisible =
        rememberReplayVisibility(
            replayKey = preset,
            resetDurationMillis = 0L,
        )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Box(Modifier.canimation(visible = dialogVisible, effect = Canimation.Scale.Pop)) {
                Text(stringResource(Res.string.gallery_code_sample_title, preset.name))
            }
        },
        text = {
            Box(Modifier.canimation(visible = dialogVisible, effect = Canimation.Fade.In)) {
                SelectionContainer {
                    Column(
                        modifier =
                            Modifier
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
                onClick = { clipboardWriter.writeText(codeSample) },
            ) {
                Text(stringResource(Res.string.gallery_copy))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(Res.string.gallery_close))
            }
        },
    )
}

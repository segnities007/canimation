package com.segnities007.canimation.app.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.settings_auto_play
import canimation.composeapp.generated.resources.settings_cycle_interval
import canimation.composeapp.generated.resources.settings_dark_mode
import canimation.composeapp.generated.resources.settings_dark_theme_active
import canimation.composeapp.generated.resources.settings_diagnostics
import canimation.composeapp.generated.resources.settings_diagnostics_desc
import canimation.composeapp.generated.resources.settings_light_theme_active
import canimation.composeapp.generated.resources.settings_title
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsBottomSheet(
    state: AppSettingsSheetState,
    onEvent: (AppSettingsSheetEvent) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val stageStateHolder = rememberAppSettingsSheetStageStateHolder()
    val sheetUiState = stageStateHolder.uiState

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = stringResource(Res.string.settings_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier =
                    Modifier.canimation(
                        visible = sheetUiState.stage >= 0,
                        effect = Canimation.Fade.Up,
                    ),
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .canimation(visible = sheetUiState.stage >= 1, effect = Canimation.Fade.Up),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = if (state.isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp),
                    )
                    Column {
                        Text(
                            text = stringResource(Res.string.settings_dark_mode),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                        )
                        Text(
                            text =
                                if (state.isDarkMode) {
                                    stringResource(
                                        Res.string.settings_dark_theme_active,
                                    )
                                } else {
                                    stringResource(Res.string.settings_light_theme_active)
                                },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
                Switch(
                    checked = state.isDarkMode,
                    onCheckedChange = { onEvent(AppSettingsSheetEvent.DarkModeToggled) },
                )
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            AppSettingsPolicySection(
                selectedPolicy = state.policy,
                visibleStage = sheetUiState.stage,
                onEvent = onEvent,
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .canimation(visible = sheetUiState.stage >= 7, effect = Canimation.Fade.Up),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(
                        text = stringResource(Res.string.settings_diagnostics),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = stringResource(Res.string.settings_diagnostics_desc),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Switch(
                    checked = state.diagnosticsEnabled,
                    onCheckedChange = {
                        onEvent(AppSettingsSheetEvent.DiagnosticsEnabledChanged(it))
                    },
                )
            }

            if (state.showPresetTuning) {
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                AppSettingsPresetTuningSection(
                    state = state,
                    visibleStage = sheetUiState.stage,
                    onEvent = onEvent,
                )
            }
        }
    }
}

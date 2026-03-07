package com.segnities007.canimation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.canimation
import canimation.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import kotlinx.coroutines.delay

internal data class AppSettingsSheetState(
    val isDarkMode: Boolean,
    val policy: CanimationPolicy,
    val showPresetTuning: Boolean,
    val autoPlayEnabled: Boolean,
    val cycleMs: Float,
    val durationScale: Float,
    val distanceScale: Float,
    val scaleIntensity: Float,
    val rotationScale: Float,
    val diagnosticsEnabled: Boolean,
)

internal data class AppSettingsSheetActions(
    val onToggleDark: () -> Unit,
    val onPolicyChange: (CanimationPolicy) -> Unit,
    val onAutoPlayChange: (Boolean) -> Unit,
    val onCycleMsChange: (Float) -> Unit,
    val onDurationScaleChange: (Float) -> Unit,
    val onDistanceScaleChange: (Float) -> Unit,
    val onScaleIntensityChange: (Float) -> Unit,
    val onRotationScaleChange: (Float) -> Unit,
    val onResetParams: () -> Unit,
    val onDiagnosticsEnabledChange: (Boolean) -> Unit,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsBottomSheet(
    state: AppSettingsSheetState,
    actions: AppSettingsSheetActions,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        var sheetStage by remember { mutableIntStateOf(-1) }
        LaunchedEffect(Unit) { for (i in 0..8) { delay(40); sheetStage = i } }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = stringResource(Res.string.settings_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.canimation(visible = sheetStage >= 0, effect = Canimation.Fade.Up),
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .canimation(visible = sheetStage >= 1, effect = Canimation.Fade.Up),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = if (state.isDarkMode) Icons.Default.DarkMode
                        else Icons.Default.LightMode,
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
                            text = if (state.isDarkMode) stringResource(Res.string.settings_dark_theme_active) else stringResource(Res.string.settings_light_theme_active),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
                Switch(
                    checked = state.isDarkMode,
                    onCheckedChange = { actions.onToggleDark() },
                )
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.canimation(visible = sheetStage >= 2, effect = Canimation.Fade.Up),
            ) {
                Text(
                    text = stringResource(Res.string.settings_motion_policy),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = stringResource(Res.string.settings_motion_policy_desc),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.height(4.dp))
                listOf(
                    Triple(CanimationPolicy.SystemAware, stringResource(Res.string.settings_policy_system_aware), stringResource(Res.string.settings_policy_system_aware_desc)),
                    Triple(CanimationPolicy.AlwaysFull, stringResource(Res.string.settings_policy_full_motion), stringResource(Res.string.settings_policy_full_motion_desc)),
                    Triple(CanimationPolicy.AlwaysReduced, stringResource(Res.string.settings_policy_reduced_motion), stringResource(Res.string.settings_policy_reduced_motion_desc)),
                    Triple(CanimationPolicy.AlwaysOff, stringResource(Res.string.settings_policy_motion_off), stringResource(Res.string.settings_policy_motion_off_desc)),
                ).forEachIndexed { idx, (candidate, label, desc) ->
                    Surface(
                        onClick = { actions.onPolicyChange(candidate) },
                        shape = MaterialTheme.shapes.medium,
                        color = if (state.policy == candidate) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        },
                        border = BorderStroke(
                            1.dp,
                            if (state.policy == candidate) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.outline
                            },
                        ),
                        modifier = Modifier.canimation(
                            visible = sheetStage >= 3 + idx,
                            effect = Canimation.Fade.Up,
                        ),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium,
                                    color = if (state.policy == candidate) {
                                        MaterialTheme.colorScheme.onPrimaryContainer
                                    } else {
                                        MaterialTheme.colorScheme.onSurface
                                    },
                                )
                                Text(
                                    text = desc,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (state.policy == candidate) {
                                        MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    },
                                )
                            }
                            if (state.policy == candidate) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                        }
                    }
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .canimation(visible = sheetStage >= 7, effect = Canimation.Fade.Up),
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
                    onCheckedChange = actions.onDiagnosticsEnabledChange,
                )
            }

            if (state.showPresetTuning) {
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.canimation(visible = sheetStage >= 8, effect = Canimation.Fade.Up),
                ) {
                    Text(
                        text = stringResource(Res.string.settings_preset_gallery),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(stringResource(Res.string.settings_auto_play), style = MaterialTheme.typography.bodyMedium)
                        Switch(
                            checked = state.autoPlayEnabled,
                            onCheckedChange = actions.onAutoPlayChange,
                        )
                    }

                    SettingsSlider(
                        label = stringResource(Res.string.settings_cycle_interval),
                        value = state.cycleMs,
                        onValueChange = actions.onCycleMsChange,
                        valueRange = 350f..2500f,
                        displayValue = "${state.cycleMs.toInt()}ms",
                    )
                    SettingsSlider(
                        label = stringResource(Res.string.settings_duration_scale),
                        value = state.durationScale,
                        onValueChange = actions.onDurationScaleChange,
                        valueRange = 0.5f..2.0f,
                        displayValue = "${fmtFloat(state.durationScale)}x",
                    )
                    SettingsSlider(
                        label = stringResource(Res.string.settings_distance_scale),
                        value = state.distanceScale,
                        onValueChange = actions.onDistanceScaleChange,
                        valueRange = 0.2f..2.5f,
                        displayValue = "${fmtFloat(state.distanceScale)}x",
                    )
                    SettingsSlider(
                        label = stringResource(Res.string.settings_scale_intensity),
                        value = state.scaleIntensity,
                        onValueChange = actions.onScaleIntensityChange,
                        valueRange = 0.2f..2.5f,
                        displayValue = "${fmtFloat(state.scaleIntensity)}x",
                    )
                    SettingsSlider(
                        label = stringResource(Res.string.settings_rotation_scale),
                        value = state.rotationScale,
                        onValueChange = actions.onRotationScaleChange,
                        valueRange = 0.2f..2.5f,
                        displayValue = "${fmtFloat(state.rotationScale)}x",
                    )

                    TextButton(onClick = actions.onResetParams) {
                        Text(stringResource(Res.string.settings_reset_params))
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    displayValue: String,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(label, style = MaterialTheme.typography.bodySmall)
            Text(
                text = displayValue,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Slider(value = value, onValueChange = onValueChange, valueRange = valueRange)
    }
}

private fun fmtFloat(value: Float): String {
    val rounded = (value * 100).toInt()
    val sign = if (rounded < 0) "-" else ""
    val abs = if (rounded < 0) -rounded else rounded
    return "$sign${abs / 100}.${(abs % 100).toString().padStart(2, '0')}"
}

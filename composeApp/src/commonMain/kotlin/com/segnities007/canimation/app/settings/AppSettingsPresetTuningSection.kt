package com.segnities007.canimation.app.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.settings_auto_play
import canimation.composeapp.generated.resources.settings_cycle_interval
import canimation.composeapp.generated.resources.settings_distance_scale
import canimation.composeapp.generated.resources.settings_duration_scale
import canimation.composeapp.generated.resources.settings_preset_gallery
import canimation.composeapp.generated.resources.settings_reset_params
import canimation.composeapp.generated.resources.settings_rotation_scale
import canimation.composeapp.generated.resources.settings_scale_intensity
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AppSettingsPresetTuningSection(
    state: AppSettingsSheetState,
    visibleStage: Int,
    onEvent: (AppSettingsSheetEvent) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier =
            Modifier.canimation(
                visible = visibleStage >= 8,
                effect = Canimation.Fade.Up,
            ),
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
            Text(
                stringResource(Res.string.settings_auto_play),
                style = MaterialTheme.typography.bodyMedium,
            )
            Switch(
                checked = state.autoPlayEnabled,
                onCheckedChange = {
                    onEvent(AppSettingsSheetEvent.AutoPlayEnabledChanged(it))
                },
            )
        }

        SettingsSlider(
            label = stringResource(Res.string.settings_cycle_interval),
            value = state.cycleMs,
            onValueChange = {
                onEvent(AppSettingsSheetEvent.CycleMsChanged(it))
            },
            valueRange = 350f..2500f,
            displayValue = "${state.cycleMs.toInt()}ms",
        )
        SettingsSlider(
            label = stringResource(Res.string.settings_duration_scale),
            value = state.durationScale,
            onValueChange = {
                onEvent(AppSettingsSheetEvent.DurationScaleChanged(it))
            },
            valueRange = 0.5f..2.0f,
            displayValue = "${formatSettingsScale(state.durationScale)}x",
        )
        SettingsSlider(
            label = stringResource(Res.string.settings_distance_scale),
            value = state.distanceScale,
            onValueChange = {
                onEvent(AppSettingsSheetEvent.DistanceScaleChanged(it))
            },
            valueRange = 0.2f..2.5f,
            displayValue = "${formatSettingsScale(state.distanceScale)}x",
        )
        SettingsSlider(
            label = stringResource(Res.string.settings_scale_intensity),
            value = state.scaleIntensity,
            onValueChange = {
                onEvent(AppSettingsSheetEvent.ScaleIntensityChanged(it))
            },
            valueRange = 0.2f..2.5f,
            displayValue = "${formatSettingsScale(state.scaleIntensity)}x",
        )
        SettingsSlider(
            label = stringResource(Res.string.settings_rotation_scale),
            value = state.rotationScale,
            onValueChange = {
                onEvent(AppSettingsSheetEvent.RotationScaleChanged(it))
            },
            valueRange = 0.2f..2.5f,
            displayValue = "${formatSettingsScale(state.rotationScale)}x",
        )

        TextButton(
            onClick = {
                onEvent(AppSettingsSheetEvent.PreviewTuningReset)
            },
        ) {
            Text(stringResource(Res.string.settings_reset_params))
        }
    }
}

package com.segnities007.canimation.app.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.settings_motion_policy
import canimation.composeapp.generated.resources.settings_motion_policy_desc
import com.segnities007.canimation.component.CanimationPolicyOption
import com.segnities007.canimation.component.canimationPolicyOptions
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AppSettingsPolicySection(
    selectedPolicy: CanimationPolicy,
    visibleStage: Int,
    onEvent: (AppSettingsSheetEvent) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier =
            Modifier.canimation(
                visible = visibleStage >= 2,
                effect = Canimation.Fade.Up,
            ),
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
        Spacer(Modifier.size(4.dp))
        canimationPolicyOptions.forEachIndexed { index, option ->
            SettingsPolicyOptionCard(
                option = option,
                selected = selectedPolicy == option.policy,
                visibleStage = visibleStage,
                optionIndex = index,
                onSelect = {
                    onEvent(AppSettingsSheetEvent.PolicyChanged(option.policy))
                },
            )
        }
    }
}

@Composable
private fun SettingsPolicyOptionCard(
    option: CanimationPolicyOption,
    selected: Boolean,
    visibleStage: Int,
    optionIndex: Int,
    onSelect: () -> Unit,
) {
    Surface(
        onClick = onSelect,
        shape = MaterialTheme.shapes.medium,
        color =
            if (selected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
        border =
            BorderStroke(
                1.dp,
                if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
            ),
        modifier =
            Modifier.canimation(
                visible = visibleStage >= 3 + optionIndex,
                effect = Canimation.Fade.Up,
            ),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = stringResource(option.labelRes),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color =
                        if (selected) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                )
                Text(
                    text = stringResource(option.descriptionRes),
                    style = MaterialTheme.typography.bodySmall,
                    color =
                        if (selected) {
                            MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                )
            }
            if (selected) {
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

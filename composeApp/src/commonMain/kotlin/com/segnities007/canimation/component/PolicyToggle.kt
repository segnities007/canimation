package com.segnities007.canimation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationPolicy
import org.jetbrains.compose.resources.stringResource

@Composable
fun PolicyToggle(
    currentPolicy: CanimationPolicy,
    onPolicyChange: (CanimationPolicy) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        canimationPolicyOptions.forEach { option ->
            FilterChip(
                selected = currentPolicy == option.policy,
                onClick = { onPolicyChange(option.policy) },
                label = { Text(stringResource(option.labelRes)) },
            )
        }
    }
}

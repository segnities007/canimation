package com.segnities007.canimation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationLevel
import io.github.canimation.core.CanimationPolicy

@Composable
fun PolicyToggle(
    currentPolicy: CanimationPolicy,
    onPolicyChange: (CanimationPolicy) -> Unit,
    modifier: Modifier = Modifier,
) {
    val options = listOf(
        "Full" to CanimationPolicy.AlwaysFull,
        "Reduced" to CanimationPolicy.AlwaysReduced,
        "Off" to CanimationPolicy.AlwaysOff,
        "System" to CanimationPolicy.SystemAware,
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        options.forEach { (label, policy) ->
            FilterChip(
                selected = currentPolicy == policy,
                onClick = { onPolicyChange(policy) },
                label = { Text(label) },
            )
        }
    }
}

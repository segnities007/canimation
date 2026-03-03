package com.segnities007.canimation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.component.PolicyToggle
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.CanimationVisibility

@Composable
fun A11yDemoScreen(modifier: Modifier = Modifier) {
    var policy by remember { mutableStateOf<CanimationPolicy>(CanimationPolicy.AlwaysFull) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Accessibility Demo",
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = "Switch between motion levels to see how animations adapt for accessibility.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        // Policy toggle
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Motion Policy", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                PolicyToggle(
                    currentPolicy = policy,
                    onPolicyChange = { policy = it },
                )
            }
        }

        // Description
        val description = when (policy) {
            is CanimationPolicy.AlwaysFull -> "Full motion: All animations play at full duration (220ms) and distance."
            is CanimationPolicy.AlwaysReduced -> "Reduced motion: Animations use shorter duration (120ms), 1/4 distance, and simplified easing."
            is CanimationPolicy.AlwaysOff -> "No motion: Animations are instant (0ms duration). Content appears/disappears immediately."
            is CanimationPolicy.SystemAware -> "System-aware: Follows OS reduce-motion preference."
        }
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        // Side-by-side comparison
        CanimationProvider(policy = policy) {
            val presets = listOf(
                CanimationPreset.FadeUp to "FadeUp",
                CanimationPreset.ScaleIn to "ScaleIn",
                CanimationPreset.SlideLeft to "SlideLeft",
            )

            presets.forEach { (preset, name) ->
                var visible by remember { mutableStateOf(true) }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(name, style = MaterialTheme.typography.titleSmall)

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            CanimationVisibility(
                                visible = visible,
                                enterPreset = preset,
                                exitPreset = preset,
                            ) {
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    ),
                                    border = BorderStroke(
                                        1.dp,
                                        MaterialTheme.colorScheme.primary,
                                    ),
                                ) {
                                    Text(
                                        text = "Animated with $name",
                                        modifier = Modifier.padding(
                                            horizontal = 20.dp,
                                            vertical = 10.dp,
                                        ),
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    )
                                }
                            }
                        }

                        OutlinedButton(
                            onClick = { visible = !visible },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(if (visible) "Hide" else "Show")
                        }
                    }
                }
            }
        }
    }
}

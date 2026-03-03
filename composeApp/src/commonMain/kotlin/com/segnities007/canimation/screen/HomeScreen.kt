package com.segnities007.canimation.screen

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationVisibility

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Hero
        Text(
            text = "canimation",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "Compose Multiplatform Animation Library",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = "Token-based animations with accessibility support, " +
                "diagnostics overlay, and deterministic testing — " +
                "across Android, iOS, Desktop, and Web.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(Modifier.height(8.dp))

        // Live demo
        var heroVisible by remember { mutableStateOf(false) }
        ElevatedCard(
            onClick = { heroVisible = !heroVisible },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("▶ Tap to preview", style = MaterialTheme.typography.labelLarge)
                Spacer(Modifier.height(12.dp))
                CanimationVisibility(
                    visible = heroVisible,
                    enterPreset = CanimationPreset.FadeUp,
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                        ),
                    ) {
                        Text(
                            text = "Hello, canimation! 🎬",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // Navigation cards
        Text("Explore", style = MaterialTheme.typography.titleLarge)
        val presetCount = CanimationPreset.entries.size

        val sections = listOf(
            Triple("Preset Gallery", "All $presetCount built-in presets with interactive demos", "presets"),
            Triple("Custom Spec Lab", "Build your own animation specs with live preview", "custom"),
            Triple("Accessibility", "Full / Reduced / Off motion comparison", "a11y"),
            Triple("Diagnostics", "FPS overlay and performance monitoring", "diagnostics"),
            Triple("Token Reference", "Duration, easing, distance, and spring values", "tokens"),
        )

        sections.forEach { (title, description, route) ->
            ElevatedCard(
                onClick = { onNavigate(route) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = title, style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        // Features overview
        Spacer(Modifier.height(8.dp))
        Text("Features", style = MaterialTheme.typography.titleLarge)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            listOf(
                "🎯 $presetCount Presets" to "Bounce, Spring, Flip, Material Motion and more",
                "♿ A11y" to "System-aware Reduce Motion",
                "📊 Diagnostics" to "Real-time FPS & jank overlay",
            ).forEach { (title, desc) ->
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = title, style = MaterialTheme.typography.labelLarge)
                        Text(
                            text = desc,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                    }
                }
            }
        }
    }
}

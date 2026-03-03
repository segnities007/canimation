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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationTokens
import io.github.canimation.core.DistanceTokens
import io.github.canimation.core.DurationTokens
import io.github.canimation.core.EasingTokens
import io.github.canimation.core.SpringTokens

@Composable
fun TokenReferenceScreen(modifier: Modifier = Modifier) {
    val tokens = CanimationTokens.Default

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Token Reference",
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = "All canimation design tokens aligned with Material Design 3 motion scale.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        // Duration tokens
        TokenCard(title = "Duration Tokens") {
            TokenRow("short", tokens.duration.short.toString())
            TokenRow("medium", tokens.duration.medium.toString())
            TokenRow("long", tokens.duration.long.toString())
        }

        // Easing tokens
        TokenCard(title = "Easing Tokens") {
            TokenRow("standard", "CubicBezier(0.2, 0.0, 0.0, 1.0)")
            TokenRow("emphasizedDecelerate", "CubicBezier(0.05, 0.7, 0.1, 1.0)")
            TokenRow("emphasizedAccelerate", "CubicBezier(0.3, 0.0, 0.8, 0.15)")
            TokenRow("decelerate", "CubicBezier(0.0, 0.0, 0.0, 1.0)")
            TokenRow("accelerate", "CubicBezier(0.3, 0.0, 1.0, 1.0)")
        }

        // Distance tokens
        TokenCard(title = "Distance Tokens") {
            TokenRow("small", tokens.distance.small.toString())
            TokenRow("medium", tokens.distance.medium.toString())
            TokenRow("large", tokens.distance.large.toString())
        }

        // Spring tokens
        TokenCard(title = "Spring Tokens") {
            TokenRow("gentle", "damping=0.85, stiffness=280f")
            TokenRow("snappy", "damping=0.80, stiffness=420f")
        }

        // Preset specs summary
        TokenCard(title = "Preset Specs (Full Enter)") {
            TokenRow("FadeUp", "220ms, standard, alpha 0→1, offsetY 16dp→0dp")
            TokenRow("Fade", "220ms, standard, alpha 0→1")
            TokenRow("ScaleIn", "220ms, standard, alpha 0→1, scale 0.92→1.0")
            TokenRow("SlideLeft", "220ms, standard, alpha 0→1, offsetX 16dp→0dp")
            TokenRow("SlideRight", "220ms, standard, alpha 0→1, offsetX -16dp→0dp")
        }

        TokenCard(title = "Reduced Motion Rules") {
            TokenRow("duration", "→ 120ms (short)")
            TokenRow("offset", "→ × 0.25 (1/4 distance)")
            TokenRow("scale", "→ compress toward 1.0 by 75%")
            TokenRow("alpha", "→ unchanged")
            TokenRow("easing", "→ decelerate")
        }
    }
}

@Composable
private fun TokenCard(
    title: String,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
private fun TokenRow(name: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

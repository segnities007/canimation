package com.segnities007.canimation.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberLoopingVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.features_accessibility
import canimation.composeapp.generated.resources.features_accessibility_subtitle
import canimation.composeapp.generated.resources.features_diagnostics
import canimation.composeapp.generated.resources.features_diagnostics_subtitle
import canimation.composeapp.generated.resources.features_label
import canimation.composeapp.generated.resources.features_multiplatform
import canimation.composeapp.generated.resources.features_multiplatform_subtitle
import canimation.composeapp.generated.resources.features_presets_count
import canimation.composeapp.generated.resources.features_presets_subtitle
import canimation.composeapp.generated.resources.features_testable
import canimation.composeapp.generated.resources.features_testable_subtitle
import canimation.composeapp.generated.resources.features_title
import canimation.composeapp.generated.resources.features_token_system
import canimation.composeapp.generated.resources.features_token_system_subtitle
import canimation.composeapp.generated.resources.quickstart_label
import canimation.composeapp.generated.resources.quickstart_title
import org.jetbrains.compose.resources.stringResource

// ======= FEATURES WITH LIVE DEMOS =======

@Composable
internal fun FeaturesSection(stage: Int, presetCount: Int) {
    DarkCenteredSection {
        Box(Modifier.canimation(visible = stage >= 8, effect = Canimation.Fade.Up)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(Res.string.features_label),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = stringResource(Res.string.features_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .canimation(visible = stage >= 9, effect = Canimation.Fade.Up),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                LiveFeatureCard(
                    title = stringResource(Res.string.features_presets_count, presetCount),
                    subtitle = stringResource(Res.string.features_presets_subtitle),
                    demoPreset = CanimationPreset.BounceIn,
                    demoDelay = 0L,
                    modifier = Modifier.weight(1f),
                )
                LiveFeatureCard(
                    title = stringResource(Res.string.features_accessibility),
                    subtitle = stringResource(Res.string.features_accessibility_subtitle),
                    demoPreset = CanimationPreset.GentleFade,
                    demoDelay = 400L,
                    modifier = Modifier.weight(1f),
                )
                LiveFeatureCard(
                    title = stringResource(Res.string.features_diagnostics),
                    subtitle = stringResource(Res.string.features_diagnostics_subtitle),
                    demoPreset = CanimationPreset.Pulse,
                    demoDelay = 800L,
                    modifier = Modifier.weight(1f),
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .canimation(visible = stage >= 10, effect = Canimation.Spring.Up),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                LiveFeatureCard(
                    title = stringResource(Res.string.features_testable),
                    subtitle = stringResource(Res.string.features_testable_subtitle),
                    demoPreset = CanimationPreset.Snap,
                    demoDelay = 200L,
                    modifier = Modifier.weight(1f),
                )
                LiveFeatureCard(
                    title = stringResource(Res.string.features_multiplatform),
                    subtitle = stringResource(Res.string.features_multiplatform_subtitle),
                    demoPreset = CanimationPreset.SpringSlideUp,
                    demoDelay = 600L,
                    modifier = Modifier.weight(1f),
                )
                LiveFeatureCard(
                    title = stringResource(Res.string.features_token_system),
                    subtitle = stringResource(Res.string.features_token_system_subtitle),
                    demoPreset = CanimationPreset.SharedAxisY,
                    demoDelay = 1000L,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
internal fun LiveFeatureCard(
    title: String,
    subtitle: String,
    demoPreset: CanimationPreset,
    demoDelay: Long,
    modifier: Modifier = Modifier,
) {
    val visible = rememberLoopingVisibility(
        initialDelayMillis = demoDelay + 500L,
        visibleDurationMillis = 2000L,
        hiddenDurationMillis = 700L,
    )

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(64.dp),
                contentAlignment = Alignment.Center,
            ) {
                CanimationVisibility(
                    visible = visible,
                    enterPreset = demoPreset,
                    exitPreset = demoPreset,
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                    ) {
                        Box(Modifier.size(width = 48.dp, height = 32.dp))
                    }
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

// ======= CODE EXAMPLE =======

@Composable
internal fun CodeExampleSection(stage: Int) {
    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
        DarkCenteredSection {
            Box(Modifier.canimation(visible = stage >= 9, effect = Canimation.Fade.Up)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(Res.string.quickstart_label),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = stringResource(Res.string.quickstart_title),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier.fillMaxWidth()
                    .canimation(visible = stage >= 10, effect = Canimation.Scale.FadeIn),
            ) {
                Text(
                    text = """
                        |@Composable
                        |fun App() {
                        |    CanimationProvider(
                        |        policy = CanimationPolicy.SystemAware
                        |    ) {
                        |        // Recommended: Effect API
                        |        Box(
                        |            Modifier.canimation(
                        |                visible = isVisible,
                        |                effect = Canimation.Fade.Up
                        |            )
                        |        ) {
                        |            Text("Hello, canimation!")
                        |        }
                        |
                        |        // Combine effects freely
                        |        Modifier.canimation(
                        |            visible = show,
                        |            effect = Canimation.Scale.Pop
                        |                + Canimation.Fade.In
                        |        )
                        |
                        |        // Attention seekers
                        |        Modifier.canimation(
                        |            visible = pulse,
                        |            effect = Canimation.Attention.Tada
                        |        )
                        |    }
                        |}
                    """.trimMargin(),
                    modifier = Modifier.padding(24.dp),
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

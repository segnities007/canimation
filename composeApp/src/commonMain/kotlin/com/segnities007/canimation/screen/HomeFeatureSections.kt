package com.segnities007.canimation.screen

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
import com.segnities007.canimation.navigation.HomeDestination
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import canimation.composeapp.generated.resources.*
import kotlinx.coroutines.delay
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
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(demoDelay + 500L)
        while (true) { visible = true; delay(2000); visible = false; delay(700) }
    }

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

// ======= EXPLORE =======

@Composable
internal fun ExploreSection(onNavigate: (HomeDestination) -> Unit) {
    var entryStage by remember { mutableIntStateOf(-1) }
    LaunchedEffect(Unit) { for (i in 0..5) { delay(60); entryStage = i } }

    DarkCenteredSection {
        Box(Modifier.canimation(visible = entryStage >= 0, effect = Canimation.Fade.Up)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(Res.string.explore_label),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = stringResource(Res.string.explore_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        listOf(
            Triple(
                stringResource(Res.string.explore_preset_gallery),
                stringResource(Res.string.explore_preset_gallery_desc),
                HomeDestination.PresetGallery,
            ),
            Triple(
                stringResource(Res.string.explore_examples),
                stringResource(Res.string.explore_examples_desc),
                HomeDestination.Examples,
            ),
            Triple(
                stringResource(Res.string.explore_api_reference),
                stringResource(Res.string.explore_api_reference_desc),
                HomeDestination.ApiReference,
            ),
            Triple(
                stringResource(Res.string.explore_documentation),
                stringResource(Res.string.explore_documentation_desc),
                HomeDestination.Docs,
            ),
        ).forEachIndexed { index, (title, description, route) ->
            Box(Modifier.canimation(visible = entryStage >= 1 + index, effect = Canimation.Entrance.Rise)) {
                Surface(
                    onClick = { onNavigate(route) },
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }
    }
}

// ======= PLATFORM =======

@Composable
internal fun PlatformSection(stage: Int) {
    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 900.dp)
                    .padding(horizontal = 32.dp, vertical = 56.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(Modifier.canimation(visible = stage >= 8, effect = Canimation.Fade.Up)) {
                    Text(
                        text = stringResource(Res.string.platform_title),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    listOf(
                        stringResource(Res.string.platform_android),
                        stringResource(Res.string.platform_ios),
                        stringResource(Res.string.platform_desktop),
                        stringResource(Res.string.platform_web),
                    ).forEachIndexed { index, platform ->
                        Box(Modifier.canimation(
                            visible = stage >= 8 + index,
                            effect = Canimation.Bounce.In,
                        )) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                            ) {
                                Text(
                                    text = platform,
                                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// ======= FOOTER =======

@Composable
internal fun FooterSection() {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 32.dp)
            .canimation(visible = visible, effect = Canimation.Fade.In),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(Res.string.footer_license),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

// ======= HELPERS =======

@Composable
internal fun DarkCenteredSection(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 900.dp)
                .padding(horizontal = 32.dp, vertical = 56.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            content()
        }
    }
}

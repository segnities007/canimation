package com.segnities007.canimation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import canimation.composeapp.generated.resources.explore_api_reference
import canimation.composeapp.generated.resources.explore_api_reference_desc
import canimation.composeapp.generated.resources.explore_documentation
import canimation.composeapp.generated.resources.explore_documentation_desc
import canimation.composeapp.generated.resources.explore_examples
import canimation.composeapp.generated.resources.explore_examples_desc
import canimation.composeapp.generated.resources.explore_label
import canimation.composeapp.generated.resources.explore_preset_gallery
import canimation.composeapp.generated.resources.explore_preset_gallery_desc
import canimation.composeapp.generated.resources.explore_title
import canimation.composeapp.generated.resources.footer_license
import canimation.composeapp.generated.resources.platform_android
import canimation.composeapp.generated.resources.platform_desktop
import canimation.composeapp.generated.resources.platform_ios
import canimation.composeapp.generated.resources.platform_title
import canimation.composeapp.generated.resources.platform_web
import com.segnities007.canimation.navigation.HomeDestination
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ExploreSection(stage: Int, onNavigate: (HomeDestination) -> Unit) {
    DarkCenteredSection {
        Box(Modifier.canimation(visible = stage >= 11, effect = Canimation.Fade.Up)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(Res.string.explore_label), style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
                Text(stringResource(Res.string.explore_title), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
            }
        }

        Spacer(Modifier.height(8.dp))

        listOf(
            Triple(stringResource(Res.string.explore_preset_gallery), stringResource(Res.string.explore_preset_gallery_desc), HomeDestination.PresetGallery),
            Triple(stringResource(Res.string.explore_examples), stringResource(Res.string.explore_examples_desc), HomeDestination.ShowcaseGallery),
            Triple(stringResource(Res.string.explore_api_reference), stringResource(Res.string.explore_api_reference_desc), HomeDestination.ApiReference),
            Triple(stringResource(Res.string.explore_documentation), stringResource(Res.string.explore_documentation_desc), HomeDestination.Docs),
        ).forEachIndexed { index, (title, description, route) ->
            Box(Modifier.canimation(visible = stage >= 12 + index, effect = Canimation.Entrance.Rise)) {
                Surface(onClick = { onNavigate(route) }, shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.surfaceVariant, border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline), modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                            Spacer(Modifier.height(4.dp))
                            Text(description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}

@Composable
internal fun PlatformSection(stage: Int) {
    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.widthIn(max = 900.dp).padding(horizontal = 32.dp, vertical = 56.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(Modifier.canimation(visible = stage >= 8, effect = Canimation.Fade.Up)) {
                    Text(stringResource(Res.string.platform_title), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    listOf(stringResource(Res.string.platform_android), stringResource(Res.string.platform_ios), stringResource(Res.string.platform_desktop), stringResource(Res.string.platform_web)).forEachIndexed { index, platform ->
                        Box(Modifier.canimation(visible = stage >= 8 + index, effect = Canimation.Bounce.In)) {
                            Surface(shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))) {
                                Text(platform, modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp), style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun FooterSection(stage: Int) {
    val visible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 32.dp)
            .canimation(visible = visible && stage >= 16, effect = Canimation.Fade.In),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(Res.string.footer_license), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
internal fun DarkCenteredSection(content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.widthIn(max = 900.dp).padding(horizontal = 32.dp, vertical = 56.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            content()
        }
    }
}

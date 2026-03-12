package com.segnities007.canimation.screen.showcase.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.examples_detail_back_to_gallery
import canimation.composeapp.generated.resources.examples_detail_component
import canimation.composeapp.generated.resources.examples_detail_component_none
import canimation.composeapp.generated.resources.examples_detail_effect
import canimation.composeapp.generated.resources.examples_detail_enter_effect
import canimation.composeapp.generated.resources.examples_detail_implementation_details
import canimation.composeapp.generated.resources.examples_detail_integration_guide
import canimation.composeapp.generated.resources.examples_detail_live_demo
import canimation.composeapp.generated.resources.examples_detail_type
import canimation.composeapp.generated.resources.examples_detail_category
import canimation.composeapp.generated.resources.examples_detail_exit_effect
import canimation.composeapp.generated.resources.examples_detail_usage
import canimation.composeapp.generated.resources.examples_how_it_works
import com.segnities007.canimation.screen.showcase.data.ShowcaseDemoType
import com.segnities007.canimation.screen.showcase.data.ShowcaseItem
import com.segnities007.canimation.screen.showcase.preview.LivePreview
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ShowcaseDetailContent(
    item: ShowcaseItem,
    title: String,
    description: String,
    tagLabel: String,
    codeSnippet: String,
    uiState: ShowcaseDetailUiState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 800.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            ShowcaseDetailHeaderSection(
                title = title,
                description = description,
                tagLabel = tagLabel,
                visible = uiState.entryStage >= 0,
                onBack = onBack,
            )
            ShowcaseDetailLiveDemoSection(
                item = item,
                visible = uiState.entryStage >= 1,
            )
            ShowcaseDetailUsageSection(
                demoType = item.demoType,
                codeSnippet = codeSnippet,
                visible = uiState.entryStage >= 2,
            )
            ShowcaseDetailImplementationSection(
                item = item,
                tagLabel = tagLabel,
                visible = uiState.entryStage >= 3,
            )
            ShowcaseDetailIntegrationSection(
                demoType = item.demoType,
                tagLabel = tagLabel,
                codeSnippet = codeSnippet,
                visible = uiState.entryStage >= 4,
            )
        }
    }
}

@Composable
private fun ShowcaseDetailHeaderSection(
    title: String,
    description: String,
    tagLabel: String,
    visible: Boolean,
    onBack: () -> Unit,
) {
    Box(Modifier.canimation(visible = visible, effect = Canimation.Fade.Up)) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            androidx.compose.foundation.layout.Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                TextButton(onClick = onBack) { Text(stringResource(Res.string.examples_detail_back_to_gallery)) }
                Text(
                    text = tagLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun ShowcaseDetailLiveDemoSection(
    item: ShowcaseItem,
    visible: Boolean,
) {
    Box(Modifier.canimation(visible = visible, effect = Canimation.Fade.Up)) {
        SectionCard(title = stringResource(Res.string.examples_detail_live_demo)) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    LivePreview(item = item)
                }
            }
        }
    }
}

@Composable
private fun ShowcaseDetailUsageSection(
    demoType: ShowcaseDemoType,
    codeSnippet: String,
    visible: Boolean,
) {
    Box(Modifier.canimation(visible = visible, effect = Canimation.Fade.Up)) {
        SectionCard(title = stringResource(Res.string.examples_detail_usage)) {
            CodeBlock(generateUsageCode(demoType, codeSnippet))
        }
    }
}

@Composable
private fun ShowcaseDetailImplementationSection(
    item: ShowcaseItem,
    tagLabel: String,
    visible: Boolean,
) {
    Box(Modifier.canimation(visible = visible, effect = Canimation.Fade.Up)) {
        SectionCard(title = stringResource(Res.string.examples_detail_implementation_details)) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                DetailRow(stringResource(Res.string.examples_detail_type), formatShowcaseDemoType(item.demoType))
                DetailRow(stringResource(Res.string.examples_detail_category), tagLabel)

                if (item.effect != null) {
                    DetailRow(stringResource(Res.string.examples_detail_effect), describeEffect(item.effect))
                }
                if (item.enterEffect != null) {
                    DetailRow(stringResource(Res.string.examples_detail_enter_effect), describeEffect(item.enterEffect))
                }
                if (item.exitEffect != null) {
                    DetailRow(stringResource(Res.string.examples_detail_exit_effect), describeEffect(item.exitEffect))
                }
                if (item.demoType == ShowcaseDemoType.Component) {
                    DetailRow(
                        stringResource(Res.string.examples_detail_component),
                        item.componentKey?.value ?: stringResource(Res.string.examples_detail_component_none),
                    )
                }

                Spacer(Modifier.height(4.dp))
                Text(
                    text = stringResource(Res.string.examples_how_it_works),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = generateHowItWorks(item.demoType),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun ShowcaseDetailIntegrationSection(
    demoType: ShowcaseDemoType,
    tagLabel: String,
    codeSnippet: String,
    visible: Boolean,
) {
    Box(Modifier.canimation(visible = visible, effect = Canimation.Fade.Up)) {
        SectionCard(title = stringResource(Res.string.examples_detail_integration_guide)) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = generateIntegrationGuide(demoType, tagLabel),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                CodeBlock(generateFullExample(demoType, codeSnippet))
            }
        }
    }
}

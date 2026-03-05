package com.segnities007.canimation.screen.examples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@Composable
fun ExampleDetailScreen(
    itemIndex: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val galleryItem = remember(itemIndex) {
        allGalleryItems.getOrNull(itemIndex)
    }

    if (galleryItem == null) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Item not found")
        }
        return
    }

    val item = galleryItem.item
    var uiState by remember { mutableStateOf(ExampleDetailUiState()) }
    val onEvent: (ExampleDetailEvent) -> Unit = { event ->
        uiState = reduceExampleDetailState(uiState, event)
    }
    LaunchedEffect(Unit) {
        for (i in 0..4) {
            delay(60)
            onEvent(ExampleDetailEvent.EntryStageUpdated(i))
        }
    }

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
            // Header
            Box(Modifier.canimation(visible = uiState.entryStage >= 0, effect = Canimation.Fade.Up)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        TextButton(onClick = onBack) { Text("← Gallery") }
                        Text(
                            text = galleryItem.tag,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            // Live Demo
            Box(Modifier.canimation(visible = uiState.entryStage >= 1, effect = Canimation.Fade.Up)) {
                SectionCard(title = "Live Demo") {
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

            // Usage / Code
            Box(Modifier.canimation(visible = uiState.entryStage >= 2, effect = Canimation.Fade.Up)) {
                SectionCard(title = "Usage") {
                    CodeBlock(generateUsageCode(item))
                }
            }

            // API Details
            Box(Modifier.canimation(visible = uiState.entryStage >= 3, effect = Canimation.Fade.Up)) {
                SectionCard(title = "Implementation Details") {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        DetailRow("Type", formatDemoType(item.demoType))
                        DetailRow("Category", galleryItem.tag)

                        if (item.effect != null) {
                            DetailRow("Effect", describeEffect(item.effect))
                        }
                        if (item.enterEffect != null) {
                            DetailRow("Enter Effect", describeEffect(item.enterEffect))
                        }
                        if (item.exitEffect != null) {
                            DetailRow("Exit Effect", describeEffect(item.exitEffect))
                        }
                        if (item.demoType == DemoType.Component) {
                            DetailRow("Component", item.componentKey?.value ?: "—")
                        }

                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "How it works",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = generateHowItWorks(item),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }

            // Integration Guide
            Box(Modifier.canimation(visible = uiState.entryStage >= 4, effect = Canimation.Fade.Up)) {
                SectionCard(title = "Integration Guide") {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            text = generateIntegrationGuide(item, galleryItem.tag),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        CodeBlock(generateFullExample(item))
                    }
                }
            }
        }
    }
}

// ===== Section Components =====

@Composable
private fun SectionCard(
    title: String,
    content: @Composable () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            content()
        }
    }
}

@Composable
private fun CodeBlock(code: String) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = code,
            modifier = Modifier.padding(16.dp),
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold,
        )
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
        ) {
            Text(
                text = value,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

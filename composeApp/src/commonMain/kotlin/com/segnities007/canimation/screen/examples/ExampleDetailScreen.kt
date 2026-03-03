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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.canimationEnter
import kotlinx.coroutines.delay

@Composable
fun ExampleDetailScreen(
    categoryId: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val category = remember(categoryId) {
        exampleCategories.find { it.id == categoryId }
    }

    if (category == null) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Category not found")
        }
        return
    }

    var replayTrigger by remember { mutableIntStateOf(0) }
    var entryStage by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        for (i in 0..1) { delay(80); entryStage = i }
    }

    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            LazyColumn(
                modifier = Modifier
                    .widthIn(max = 960.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 32.dp),
            ) {
                // Header
                item {
                    Box(Modifier.canimationEnter(visible = entryStage >= 0, preset = CanimationPreset.FadeUp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                            ) {
                                TextButton(onClick = onBack) {
                                    Text("← Examples")
                                }
                                Text(
                                    text = category.accentLabel,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            Text(
                                text = category.title,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "${category.examples.size} examples — ${category.subtitle}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }

                // Replay all button
                item {
                    Box(Modifier.canimationEnter(visible = entryStage >= 1, preset = CanimationPreset.FadeUp)) {
                        FilledTonalButton(onClick = { replayTrigger++ }) {
                            Text("▶  Replay All")
                        }
                    }
                }

                // Example items
                itemsIndexed(
                    items = category.examples,
                    key = { _, item -> item.preset.name },
                ) { index, example ->
                    ExampleCard(
                        example = example,
                        index = index,
                        replayTrigger = replayTrigger,
                    )
                }
            }
        }
    }
}

@Composable
private fun ExampleCard(
    example: ExampleItem,
    index: Int,
    replayTrigger: Int,
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(replayTrigger) {
        visible = false
        delay(100L + index * 80L)
        visible = true
    }

    LaunchedEffect(Unit) {
        delay(300L + index * 80L)
        visible = true
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            // Title row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = example.preset.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = example.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                FilledTonalButton(
                    onClick = {
                        // Trigger handled by replayTrigger
                    },
                ) {
                    Text("▶", style = MaterialTheme.typography.labelMedium)
                }
            }

            // Live demo area
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    // Center dot
                    Surface(
                        shape = RoundedCornerShape(3.dp),
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                        modifier = Modifier.size(6.dp),
                    ) {}

                    CanimationVisibility(
                        visible = visible,
                        enterPreset = example.preset,
                        exitPreset = example.preset,
                    ) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            border = BorderStroke(
                                1.dp,
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                            ),
                        ) {
                            Box(
                                modifier = Modifier.size(width = 64.dp, height = 48.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "A",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                )
                            }
                        }
                    }
                }
            }

            // Code snippet
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = example.codeSnippet,
                    modifier = Modifier.padding(16.dp),
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

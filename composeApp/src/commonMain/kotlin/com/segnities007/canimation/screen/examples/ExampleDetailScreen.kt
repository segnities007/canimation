package com.segnities007.canimation.screen.examples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import io.github.canimation.core.canimationEmphasize
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

                // Example items
                itemsIndexed(
                    items = category.examples,
                    key = { _, item -> "${category.id}-${item.preset.name}" },
                ) { index, example ->
                    ExampleCard(
                        example = example,
                        index = index,
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
) {
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
            // Title
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

            // Live demo area — renders based on demoType
            when (example.demoType) {
                "emphasis" -> EmphasisDemo(example.preset)
                "stagger" -> StaggerDemo(example.preset, index)
                "enterExit" -> EnterExitDemo(example.preset, index)
                "grid" -> GridDemo(example.preset, index)
                else -> VisibilityDemo(example.preset, index)
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

// ===== Demo Renderers =====

@Composable
private fun VisibilityDemo(preset: CanimationPreset, index: Int) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200L + index * 60L)
        while (true) {
            visible = true
            delay(2000)
            visible = false
            delay(600)
        }
    }

    DemoSurface {
        DemoDot()
        CanimationVisibility(
            visible = visible,
            enterPreset = preset,
            exitPreset = preset,
        ) {
            DemoBox()
        }
    }
}

@Composable
private fun EmphasisDemo(preset: CanimationPreset) {
    var active by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        while (true) {
            active = true
            delay(1800)
            active = false
            delay(600)
        }
    }

    DemoSurface {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
            modifier = Modifier.canimationEmphasize(active = active, preset = preset),
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

@Composable
private fun EnterExitDemo(preset: CanimationPreset, index: Int) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200L + index * 60L)
        while (true) {
            visible = true
            delay(2400)
            visible = false
            delay(800)
        }
    }

    DemoSurface {
        DemoDot()
        CanimationVisibility(
            visible = visible,
            enterPreset = preset,
            exitPreset = preset,
        ) {
            DemoBox(label = "↔")
        }
    }
}

@Composable
private fun StaggerDemo(preset: CanimationPreset, index: Int) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200L + index * 60L)
        while (true) {
            visible = true
            delay(3000)
            visible = false
            delay(800)
        }
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            repeat(4) { i ->
                var itemVisible by remember { mutableStateOf(false) }

                LaunchedEffect(visible) {
                    if (visible) {
                        delay(i * 100L)
                        itemVisible = true
                    } else {
                        itemVisible = false
                    }
                }

                CanimationVisibility(
                    visible = itemVisible,
                    enterPreset = preset,
                    exitPreset = preset,
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        border = BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        ),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(28.dp),
                            contentAlignment = Alignment.CenterStart,
                        ) {
                            Text(
                                text = "  Item ${i + 1}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GridDemo(preset: CanimationPreset, index: Int) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200L + index * 60L)
        while (true) {
            visible = true
            delay(3000)
            visible = false
            delay(800)
        }
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            repeat(2) { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    repeat(2) { col ->
                        val cellIndex = row * 2 + col
                        var cellVisible by remember { mutableStateOf(false) }

                        LaunchedEffect(visible) {
                            if (visible) {
                                delay(cellIndex * 120L)
                                cellVisible = true
                            } else {
                                cellVisible = false
                            }
                        }

                        CanimationVisibility(
                            visible = cellVisible,
                            enterPreset = preset,
                            exitPreset = preset,
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primaryContainer,
                                border = BorderStroke(
                                    1.dp,
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp),
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "${cellIndex + 1}",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// ===== Shared Demo Components =====

@Composable
private fun DemoSurface(content: @Composable () -> Unit) {
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
            content()
        }
    }
}

@Composable
private fun DemoDot() {
    Surface(
        shape = RoundedCornerShape(3.dp),
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
        modifier = Modifier.size(6.dp),
    ) {}
}

@Composable
private fun DemoBox(label: String = "A") {
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
                text = label,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}

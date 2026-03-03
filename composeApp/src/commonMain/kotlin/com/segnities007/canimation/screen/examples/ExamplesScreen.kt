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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.CanimationVisibility
import kotlinx.coroutines.delay

@Composable
fun ExamplesScreen(
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 280.dp),
                modifier = Modifier.widthIn(max = 1200.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    horizontal = 32.dp,
                    vertical = 24.dp,
                ),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                // Header
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Column(
                        modifier = Modifier.padding(bottom = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = "EXAMPLES",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Animation showcase",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Explore ${exampleCategories.sumOf { it.examples.size }} examples across ${exampleCategories.size} categories — each with live demos and code",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }

                // Category cards
                items(exampleCategories, key = { it.id }) { category ->
                    CategoryCard(
                        category = category,
                        onClick = { onCategoryClick(category.id) },
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryCard(
    category: ExampleCategory,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            // Category label
            Text(
                text = category.accentLabel,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
            )

            // Live preview: actual animations
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier.fillMaxWidth(),
            ) {
                val isComponent = category.examples.firstOrNull()?.demoType in standaloneDemoTypes
                if (isComponent) {
                    // Render actual component animation (scaled down) for standalone categories
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .clipToBounds(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Box(
                            modifier = Modifier.graphicsLayer {
                                scaleX = 0.6f
                                scaleY = 0.6f
                            },
                        ) {
                            ComponentPreviewCell(
                                demoType = category.examples.first().demoType,
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        category.examples.take(3).forEachIndexed { index, example ->
                            PreviewCell(
                                preset = example.preset,
                                delayMs = index * 300L,
                            )
                        }
                    }
                }
            }

            // Title and count
            Text(
                text = category.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = category.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f),
                )
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                ) {
                    Text(
                        text = "${category.examples.size}",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
private fun PreviewCell(
    preset: io.github.canimation.core.CanimationPreset,
    delayMs: Long,
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(delayMs + 300L)
        while (true) {
            visible = true
            delay(1800)
            visible = false
            delay(500)
        }
    }

    Box(
        modifier = Modifier.size(56.dp),
        contentAlignment = Alignment.Center,
    ) {
        CanimationVisibility(
            visible = visible,
            enterPreset = preset,
            exitPreset = preset,
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
            ) {
                Box(Modifier.size(32.dp))
            }
        }
    }
}

private val standaloneDemoTypes = setOf(
    "counter", "numberTrend", "typewriter", "scramble", "wavy",
    "pulseDots", "jumpingDots", "shimmer", "tabs", "accordion",
    "flipCard", "colorMorph", "progressRing", "holdConfirm",
    "splitReveal", "staggerCenter", "ticker", "bouncyList",
    "spinner", "ripple", "swipeActions", "tiltCard",
    "priceSwitcher", "engagementStats", "multiStateBadge",
)

@Composable
private fun ComponentPreviewCell(demoType: String) {
    when (demoType) {
        "counter" -> AnimatedCounter()
        "numberTrend" -> NumberTrend()
        "typewriter" -> TypewriterText()
        "scramble" -> ScrambleText()
        "wavy" -> WavyText()
        "pulseDots" -> PulseLoadingDots()
        "jumpingDots" -> JumpingDots()
        "shimmer" -> ShimmerEffect()
        "tabs" -> AnimatedTabs()
        "accordion" -> ExpandableAccordion()
        "flipCard" -> FlipCard()
        "colorMorph" -> ColorMorph()
        "progressRing" -> ProgressRing()
        "holdConfirm" -> HoldToConfirm()
        "splitReveal" -> SplitTextReveal()
        "staggerCenter" -> StaggerFromCenter()
        "ticker" -> TickerMarquee()
        "bouncyList" -> BouncySpringList()
        "spinner" -> LoadingSpinner()
        "ripple" -> LoadingRipple()
        "swipeActions" -> SwipeActions()
        "tiltCard" -> TiltCard()
        "priceSwitcher" -> PriceSwitcher()
        "engagementStats" -> EngagementStats()
        "multiStateBadge" -> MultiStateBadge()
    }
}

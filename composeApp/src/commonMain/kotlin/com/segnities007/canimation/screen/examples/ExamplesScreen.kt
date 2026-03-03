package com.segnities007.canimation.screen.examples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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

private val accentLabels = listOf(
    "ALL", "ENTRANCE", "EMPHASIS", "PATTERN", "INTERACTION",
    "CARD", "TEXT", "LOADING", "CANVAS", "PHYSICS",
    "3D", "UI", "SPECIAL", "MATERIAL", "DIRECTION",
)

@Composable
fun ExamplesScreen(
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTag by remember { mutableStateOf("ALL") }

    val filteredCategories by remember(searchQuery, selectedTag) {
        derivedStateOf {
            exampleCategories.filter { cat ->
                val matchesSearch = searchQuery.isBlank() ||
                    cat.title.contains(searchQuery, ignoreCase = true) ||
                    cat.subtitle.contains(searchQuery, ignoreCase = true) ||
                    cat.accentLabel.contains(searchQuery, ignoreCase = true) ||
                    cat.tags.any { it.contains(searchQuery, ignoreCase = true) }
                val matchesTag = selectedTag == "ALL" || cat.accentLabel == selectedTag
                matchesSearch && matchesTag
            }
        }
    }

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
                        verticalArrangement = Arrangement.spacedBy(12.dp),
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

                        // Search bar
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = { Text("Search animations...") },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = "Search",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            ),
                            modifier = Modifier.fillMaxWidth(),
                        )

                        // Tag filter chips
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.horizontalScroll(rememberScrollState()),
                        ) {
                            accentLabels.forEach { label ->
                                FilterChip(
                                    selected = selectedTag == label,
                                    onClick = { selectedTag = if (selectedTag == label) "ALL" else label },
                                    label = {
                                        Text(
                                            text = label,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = if (selectedTag == label) FontWeight.Bold else FontWeight.Normal,
                                        )
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                        selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                    ),
                                )
                            }
                        }

                        // Results count
                        if (searchQuery.isNotBlank() || selectedTag != "ALL") {
                            Text(
                                text = "${filteredCategories.size} categories found",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }

                // Category cards
                items(filteredCategories, key = { it.id }) { category ->
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
    // Batch 2
    "morphShapes", "gradientShift", "skeletonLoader", "elasticPull",
    "parallaxLayers", "orbitAnim", "breathingGlow", "pathTracer",
    "textGradient", "cardShuffle", "confetti", "waveEffect",
    "progressSteps", "liquidFill", "slidingReveal", "focusBlur",
    "rollingDigits", "springChain", "glitchText", "expandingRings",
    "stackedCards", "countdownTimer", "verticalTicker", "heartbeatLine",
    "expandingSearch",
    // Batch 3
    "cardBorderTrace", "cardLiftHover", "cardGradientBorder",
    "cardExpandCollapse", "cardParallaxTilt", "cardGlassmorphism",
    "cardRevealWipe", "cardFanStack", "cardMagneticSnap",
    "notificationBadge", "glowProgress", "springToggle",
    "pulseRadar", "morphProgress", "stepIndicator",
    "animatedUnderline", "blinkingCursor", "springChip",
    "coinFlip", "dnaHelix", "animatedPie",
    "pendulumSwing", "bouncingBall", "circularMenu",
    "animatedBarChart", "slinkySpring", "typewriterDelete",
    "animatedGradientText",
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
        // Batch 2
        "morphShapes" -> MorphingShapes()
        "gradientShift" -> GradientShift()
        "skeletonLoader" -> SkeletonLoader()
        "elasticPull" -> ElasticPull()
        "parallaxLayers" -> ParallaxLayers()
        "orbitAnim" -> OrbitAnimation()
        "breathingGlow" -> BreathingGlow()
        "pathTracer" -> PathTracer()
        "textGradient" -> TextGradientAnim()
        "cardShuffle" -> CardShuffle()
        "confetti" -> ConfettiExplosion()
        "waveEffect" -> WaveEffect()
        "progressSteps" -> ProgressSteps()
        "liquidFill" -> LiquidFill()
        "slidingReveal" -> SlidingReveal()
        "focusBlur" -> FocusBlurEffect()
        "rollingDigits" -> RollingDigits()
        "springChain" -> SpringChain()
        "glitchText" -> GlitchText()
        "expandingRings" -> ExpandingRings()
        "stackedCards" -> StackedCards()
        "countdownTimer" -> CountdownTimer()
        "verticalTicker" -> VerticalTicker()
        "heartbeatLine" -> HeartbeatLine()
        "expandingSearch" -> ExpandingSearch()
        // Batch 3
        "cardBorderTrace" -> CardBorderTrace()
        "cardLiftHover" -> CardLiftHover()
        "cardGradientBorder" -> CardGradientBorder()
        "cardExpandCollapse" -> CardExpandCollapse()
        "cardParallaxTilt" -> CardParallaxTilt()
        "cardGlassmorphism" -> CardGlassmorphism()
        "cardRevealWipe" -> CardRevealWipe()
        "cardFanStack" -> CardFanStack()
        "cardMagneticSnap" -> CardMagneticSnap()
        "notificationBadge" -> NotificationBadge()
        "glowProgress" -> GlowProgressBar()
        "springToggle" -> SpringToggle()
        "pulseRadar" -> PulseRadar()
        "morphProgress" -> MorphProgressIndicator()
        "stepIndicator" -> StepIndicator()
        "animatedUnderline" -> AnimatedUnderlineText()
        "blinkingCursor" -> BlinkingCursor()
        "springChip" -> SpringChip()
        "coinFlip" -> CoinFlip()
        "dnaHelix" -> DnaHelix()
        "animatedPie" -> AnimatedPieChart()
        "pendulumSwing" -> PendulumSwing()
        "bouncingBall" -> BouncingBall()
        "circularMenu" -> CircularMenu()
        "animatedBarChart" -> AnimatedBarChart()
        "slinkySpring" -> SlinkySpring()
        "typewriterDelete" -> TypewriterDelete()
        "animatedGradientText" -> AnimatedGradientText()
    }
}

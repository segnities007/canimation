package com.segnities007.canimation.screen.examples

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
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
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.canimationEmphasize
import io.github.canimation.core.canimationEnter
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
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
                    Box(Modifier.canimation(visible = entryStage >= 0, effect = Canimation.Fade.Up)) {
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
                    key = { idx, item -> "${category.id}-$idx" },
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
                    text = example.title,
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
                "tap" -> TapDemo(example.preset)
                "tapEmphasis" -> TapEmphasisDemo(example.preset)
                "hover" -> HoverDemo(example.preset)
                "longPress" -> LongPressDemo(example.preset)
                "toggle" -> ToggleDemo(example.preset)
                "drag" -> DragDemo(example.preset)
                // Standalone component animations
                "counter" -> ComponentDemoSurface { AnimatedCounter() }
                "numberTrend" -> ComponentDemoSurface { NumberTrend() }
                "typewriter" -> ComponentDemoSurface { TypewriterText() }
                "scramble" -> ComponentDemoSurface { ScrambleText() }
                "wavy" -> ComponentDemoSurface { WavyText() }
                "pulseDots" -> ComponentDemoSurface { PulseLoadingDots() }
                "jumpingDots" -> ComponentDemoSurface { JumpingDots() }
                "shimmer" -> ComponentDemoSurface(height = 140) { ShimmerEffect() }
                "tabs" -> ComponentDemoSurface { AnimatedTabs() }
                "accordion" -> ComponentDemoSurface(height = 200) { ExpandableAccordion() }
                "flipCard" -> ComponentDemoSurface { FlipCard() }
                "colorMorph" -> ComponentDemoSurface { ColorMorph() }
                "progressRing" -> ComponentDemoSurface { ProgressRing() }
                "holdConfirm" -> ComponentDemoSurface { HoldToConfirm() }
                "splitReveal" -> ComponentDemoSurface { SplitTextReveal() }
                "staggerCenter" -> ComponentDemoSurface { StaggerFromCenter() }
                "ticker" -> ComponentDemoSurface { TickerMarquee() }
                "bouncyList" -> ComponentDemoSurface(height = 220) { BouncySpringList() }
                "spinner" -> ComponentDemoSurface { LoadingSpinner() }
                "ripple" -> ComponentDemoSurface { LoadingRipple() }
                "swipeActions" -> ComponentDemoSurface { SwipeActions() }
                "tiltCard" -> ComponentDemoSurface { TiltCard() }
                "priceSwitcher" -> ComponentDemoSurface { PriceSwitcher() }
                "engagementStats" -> ComponentDemoSurface { EngagementStats() }
                "multiStateBadge" -> ComponentDemoSurface { MultiStateBadge() }
                // Standalone component animations (batch 2)
                "morphShapes" -> ComponentDemoSurface { MorphingShapes() }
                "gradientShift" -> ComponentDemoSurface { GradientShift() }
                "skeletonLoader" -> ComponentDemoSurface(height = 140) { SkeletonLoader() }
                "elasticPull" -> ComponentDemoSurface { ElasticPull() }
                "parallaxLayers" -> ComponentDemoSurface { ParallaxLayers() }
                "orbitAnim" -> ComponentDemoSurface { OrbitAnimation() }
                "breathingGlow" -> ComponentDemoSurface { BreathingGlow() }
                "pathTracer" -> ComponentDemoSurface { PathTracer() }
                "textGradient" -> ComponentDemoSurface { TextGradientAnim() }
                "cardShuffle" -> ComponentDemoSurface(height = 200) { CardShuffle() }
                "confetti" -> ComponentDemoSurface(height = 200) { ConfettiExplosion() }
                "waveEffect" -> ComponentDemoSurface { WaveEffect() }
                "progressSteps" -> ComponentDemoSurface { ProgressSteps() }
                "liquidFill" -> ComponentDemoSurface { LiquidFill() }
                "slidingReveal" -> ComponentDemoSurface { SlidingReveal() }
                "focusBlur" -> ComponentDemoSurface { FocusBlurEffect() }
                "rollingDigits" -> ComponentDemoSurface { RollingDigits() }
                "springChain" -> ComponentDemoSurface { SpringChain() }
                "glitchText" -> ComponentDemoSurface { GlitchText() }
                "expandingRings" -> ComponentDemoSurface { ExpandingRings() }
                "stackedCards" -> ComponentDemoSurface(height = 200) { StackedCards() }
                "countdownTimer" -> ComponentDemoSurface { CountdownTimer() }
                "verticalTicker" -> ComponentDemoSurface { VerticalTicker() }
                "heartbeatLine" -> ComponentDemoSurface { HeartbeatLine() }
                "expandingSearch" -> ComponentDemoSurface { ExpandingSearch() }
                // Batch 3
                "cardBorderTrace" -> ComponentDemoSurface { CardBorderTrace() }
                "cardLiftHover" -> ComponentDemoSurface { CardLiftHover() }
                "cardGradientBorder" -> ComponentDemoSurface { CardGradientBorder() }
                "cardExpandCollapse" -> ComponentDemoSurface(height = 140) { CardExpandCollapse() }
                "cardParallaxTilt" -> ComponentDemoSurface { CardParallaxTilt() }
                "cardGlassmorphism" -> ComponentDemoSurface { CardGlassmorphism() }
                "cardRevealWipe" -> ComponentDemoSurface { CardRevealWipe() }
                "cardFanStack" -> ComponentDemoSurface { CardFanStack() }
                "cardMagneticSnap" -> ComponentDemoSurface { CardMagneticSnap() }
                "notificationBadge" -> ComponentDemoSurface { NotificationBadge() }
                "glowProgress" -> ComponentDemoSurface { GlowProgressBar() }
                "springToggle" -> ComponentDemoSurface { SpringToggle() }
                "pulseRadar" -> ComponentDemoSurface { PulseRadar() }
                "morphProgress" -> ComponentDemoSurface { MorphProgressIndicator() }
                "stepIndicator" -> ComponentDemoSurface { StepIndicator() }
                "animatedUnderline" -> ComponentDemoSurface { AnimatedUnderlineText() }
                "blinkingCursor" -> ComponentDemoSurface { BlinkingCursor() }
                "springChip" -> ComponentDemoSurface { SpringChip() }
                "coinFlip" -> ComponentDemoSurface { CoinFlip() }
                "dnaHelix" -> ComponentDemoSurface { DnaHelix() }
                "animatedPie" -> ComponentDemoSurface { AnimatedPieChart() }
                "pendulumSwing" -> ComponentDemoSurface { PendulumSwing() }
                "bouncingBall" -> ComponentDemoSurface { BouncingBall() }
                "circularMenu" -> ComponentDemoSurface { CircularMenu() }
                "animatedBarChart" -> ComponentDemoSurface { AnimatedBarChart() }
                "slinkySpring" -> ComponentDemoSurface { SlinkySpring() }
                "typewriterDelete" -> ComponentDemoSurface { TypewriterDelete() }
                "animatedGradientText" -> ComponentDemoSurface { AnimatedGradientText() }
                // Batch 4
                "megaMenuReveal" -> ComponentDemoSurface { MegaMenuReveal() }
                "smoothTabIndicator" -> ComponentDemoSurface { SmoothTabIndicator() }
                "numberCounter" -> ComponentDemoSurface { NumberCounter() }
                "revealText" -> ComponentDemoSurface { RevealTextEffect() }
                "scatterText" -> ComponentDemoSurface { ScatterText() }
                "infiniteLoadingList" -> ComponentDemoSurface { InfiniteLoadingList() }
                "cardStackSwipe" -> ComponentDemoSurface { CardStackSwipe() }
                "horizontalScrollGallery" -> ComponentDemoSurface { HorizontalScrollGallery() }
                "iosSlider" -> ComponentDemoSurface { IOSSlider() }
                "checkboxAnim" -> ComponentDemoSurface { CheckboxAnimation() }
                "switchAnim" -> ComponentDemoSurface { SwitchAnimation() }
                "toastNotification" -> ComponentDemoSurface { ToastNotification() }
                "accordionMenu" -> ComponentDemoSurface { AccordionMenu() }
                "magneticButton" -> ComponentDemoSurface { MagneticButton() }
                "rippleButton" -> ComponentDemoSurface { RippleButton() }
                "floatingParticles" -> ComponentDemoSurface { FloatingParticles() }
                "scrollDirectionHeader" -> ComponentDemoSurface { ScrollDirectionHeader() }
                "textLineReveal" -> ComponentDemoSurface { TextLineReveal() }
                "zoomHeroImage" -> ComponentDemoSurface { ZoomHeroImage() }
                "progressScrubber" -> ComponentDemoSurface { ProgressScrubber() }
                "verticalCarousel" -> ComponentDemoSurface { VerticalCarousel() }
                "waterfallGrid" -> ComponentDemoSurface { WaterfallGrid() }
                "pulsingAvatar" -> ComponentDemoSurface { PulsingAvatar() }
                "segmentedControl" -> ComponentDemoSurface { SegmentedControl() }
                "elasticDrawer" -> ComponentDemoSurface { ElasticDrawer() }
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

// ===== Interactive Demo Renderers =====

@Composable
private fun TapDemo(preset: CanimationPreset) {
    var visible by remember { mutableStateOf(false) }

    InteractiveDemoSurface(hint = "Tap to toggle") {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { visible = !visible },
            contentAlignment = Alignment.Center,
        ) {
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
}

@Composable
private fun TapEmphasisDemo(preset: CanimationPreset) {
    var active by remember { mutableStateOf(false) }

    LaunchedEffect(active) {
        if (active) {
            delay(1200)
            active = false
        }
    }

    InteractiveDemoSurface(hint = "Tap to emphasize") {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { active = true },
            contentAlignment = Alignment.Center,
        ) {
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
}

@Composable
private fun HoverDemo(preset: CanimationPreset) {
    var hovered by remember { mutableStateOf(false) }

    InteractiveDemoSurface(hint = "Hover / pointer over") {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            when (event.type) {
                                PointerEventType.Enter -> hovered = true
                                PointerEventType.Exit -> hovered = false
                            }
                        }
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
                modifier = Modifier.canimationEmphasize(active = hovered, preset = preset),
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LongPressDemo(preset: CanimationPreset) {
    var active by remember { mutableStateOf(false) }

    LaunchedEffect(active) {
        if (active) {
            delay(1200)
            active = false
        }
    }

    InteractiveDemoSurface(hint = "Long press") {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .combinedClickable(
                    onClick = {},
                    onLongClick = { active = true },
                ),
            contentAlignment = Alignment.Center,
        ) {
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
}

@Composable
private fun ToggleDemo(preset: CanimationPreset) {
    var stateA by remember { mutableStateOf(true) }

    InteractiveDemoSurface(hint = "Tap to switch A ↔ B") {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { stateA = !stateA },
            contentAlignment = Alignment.Center,
        ) {
            CanimationVisibility(
                visible = stateA,
                enterPreset = preset,
                exitPreset = preset,
            ) {
                DemoBox(label = "A")
            }
            CanimationVisibility(
                visible = !stateA,
                enterPreset = preset,
                exitPreset = preset,
            ) {
                DemoBox(label = "B")
            }
        }
    }
}

@Composable
private fun DragDemo(preset: CanimationPreset) {
    var revealed by remember { mutableStateOf(false) }
    val draggableState = rememberDraggableState { delta ->
        if (delta > 8f) revealed = true
        if (delta < -8f) revealed = false
    }

    InteractiveDemoSurface(hint = "Drag → to reveal, ← to hide") {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .draggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal,
                ),
            contentAlignment = Alignment.Center,
        ) {
            // Track indicator
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "←",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                )
                Surface(
                    shape = RoundedCornerShape(2.dp),
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f),
                    modifier = Modifier.size(width = 60.dp, height = 4.dp),
                ) {}
                Text(
                    text = "→",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                )
            }

            CanimationVisibility(
                visible = revealed,
                enterPreset = preset,
                exitPreset = preset,
            ) {
                DemoBox()
            }
        }
    }
}

@Composable
private fun ComponentDemoSurface(
    height: Int = 120,
    content: @Composable () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f),
        ),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}

@Composable
private fun InteractiveDemoSurface(
    hint: String,
    content: @Composable () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = hint,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
        )
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f),
            ),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
            ) {
                content()
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

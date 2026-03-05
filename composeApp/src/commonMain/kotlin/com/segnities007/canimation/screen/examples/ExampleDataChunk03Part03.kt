package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset


internal val exampleCategoriesChunk03Part03: List<ExampleCategory> = listOf(
        // ── Audio & Visualization ──
        ExampleCategory(
            id = "audio-viz",
            title = "Audio & Visualization",
            subtitle = "Waveforms, visual effects, and animated graphics",
            accentLabel = "VISUAL",
            tags = listOf("VISUAL"),
            examples = listOf(
                ExampleItem(
                    title = "Waveform Visualizer",
                    description = "Animated audio waveform bars",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.WaveformVisualizer,
                    codeSnippet = """WaveformVisualizer(
        barCount = 20,
        barColor = Color.Cyan,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    )""",
                ),
                ExampleItem(
                    title = "Matrix Rain",
                    description = "Columns of cascading characters",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.MatrixRain,
                    codeSnippet = """MatrixRain(
        columnCount = 15,
        rainColor = Color.Green,
        modifier = Modifier.fillMaxSize(),
    )""",
                ),
                ExampleItem(
                    title = "Music Equalizer",
                    description = "Animated equalizer bars",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.MusicEqualizer,
                    codeSnippet = """MusicEqualizer(
        barCount = 5,
        barColor = Color.Magenta,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
    )""",
                ),
                ExampleItem(
                    title = "Water Droplet",
                    description = "Water droplet ripple effect",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.WaterDroplet,
                    codeSnippet = """WaterDroplet(
        dropColor = Color.Cyan,
        rippleRadius = 40.dp,
        modifier = Modifier.size(120.dp),
    )""",
                ),
            ),
        ),
        // ── Modern UI Components ──
        ExampleCategory(
            id = "modern-ui",
            title = "Modern UI Components",
            subtitle = "Interactive buttons, cards, indicators, and controls",
            accentLabel = "UI",
            tags = listOf("UI"),
            examples = listOf(
                ExampleItem(
                    title = "Pulse Button",
                    description = "Button that pulses when idle",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PulseButton,
                    codeSnippet = """PulseButton(
        text = "Tap Me",
        pulseColor = Color.Blue,
        onClick = { /* action */ },
        modifier = Modifier.padding(16.dp),
    )""",
                ),
                ExampleItem(
                    title = "Neumorphism Card",
                    description = "Card with animated shadow depth",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.NeumorphismCard,
                    codeSnippet = """NeumorphismCard(
        shadowColor = Color.Gray,
        elevation = 8.dp,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Gradient Border Card",
                    description = "Card with animated gradient border",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.GradientBorderCard,
                    codeSnippet = """GradientBorderCard(
        colors = listOf(Color.Magenta, Color.Cyan),
        borderWidth = 2.dp,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Flip Counter",
                    description = "Digit flip counter",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.FlipCounter,
                    codeSnippet = """FlipCounter(
        targetValue = 42,
        digitCount = 2,
        modifier = Modifier.padding(16.dp),
    )""",
                ),
                ExampleItem(
                    title = "Expandable Chip",
                    description = "Chip that expands with content",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ExpandableChip,
                    codeSnippet = """ExpandableChip(
        label = "Details",
        expandedContent = { Text("Extra info") },
        modifier = Modifier.padding(8.dp),
    )""",
                ),
                ExampleItem(
                    title = "Stacked Notifications",
                    description = "Stacked notification cards",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.StackedNotifications,
                    codeSnippet = """StackedNotifications(
        notifications = listOf("New message", "Update available"),
        maxVisible = 3,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Circular Reveal Card",
                    description = "Card with circular reveal animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CircularRevealCard,
                    codeSnippet = """CircularRevealCard(
        revealDuration = 600,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Swipe Card",
                    description = "Card that can be swiped away",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SwipeCard,
                    codeSnippet = """SwipeCard(
        onSwipeLeft = { /* dismiss */ },
        onSwipeRight = { /* accept */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Animated Checkmark",
                    description = "Animated checkmark with circle",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.AnimatedCheckmark,
                    codeSnippet = """AnimatedCheckmark(
        checked = true,
        checkColor = Color.Green,
        modifier = Modifier.size(48.dp),
    )""",
                ),
                ExampleItem(
                    title = "Rotating Cube",
                    description = "2D representation of a rotating cube",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.RotatingCube,
                    codeSnippet = """RotatingCube(
        cubeSize = 100.dp,
        rotationSpeed = 3000L,
        modifier = Modifier.size(150.dp),
    )""",
                ),
                ExampleItem(
                    title = "Slot Machine",
                    description = "Simple slot machine with rolling numbers",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SlotMachine,
                    codeSnippet = """SlotMachine(
        reelCount = 3,
        spinDuration = 2000L,
        modifier = Modifier.padding(16.dp),
    )""",
                ),
                ExampleItem(
                    title = "Radial Progress",
                    description = "Circular progress with animated arc",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.RadialProgress,
                    codeSnippet = """RadialProgress(
        progress = 0.7f,
        strokeWidth = 6.dp,
        color = Color.Blue,
        modifier = Modifier.size(80.dp),
    )""",
                ),
            ),
        ),
        ExampleCategory(
            id = "wave",
            title = "Wave",
            subtitle = "Wave-like motion effects",
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem("Wave Gentle", "Gentle floating wave motion", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Gentle,\n)", effect = Canimation.Wave.Gentle),
                ExampleItem("Wave Strong", "Strong wave with rotation", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Strong,\n)", effect = Canimation.Wave.Strong),
                ExampleItem("Wave Ripple", "Ripple expansion effect", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Ripple,\n)", effect = Canimation.Wave.Ripple),
                ExampleItem("Wave Float", "Floating upward effect", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Float,\n)", effect = Canimation.Wave.Float),
                ExampleItem("Wave Drift", "Gentle diagonal drift", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Drift,\n)", effect = Canimation.Wave.Drift),
            ),
        ),
        ExampleCategory(
            id = "glitch",
            title = "Glitch",
            subtitle = "Digital glitch effects",
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem("Glitch In", "Digital glitch entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Glitch.In,\n)", effect = Canimation.Glitch.In),
                ExampleItem("Glitch Shake", "Glitchy shake", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Glitch.Shake,\n)", effect = Canimation.Glitch.Shake),
                ExampleItem("Glitch Flicker", "Flickering appearance", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Glitch.Flicker,\n)", effect = Canimation.Glitch.Flicker),
                ExampleItem("Glitch Distort", "Distorted entry", "Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Glitch.Distort,\n)", effect = Canimation.Glitch.Distort),
            ),
        ),
)

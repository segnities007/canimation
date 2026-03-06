package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset


internal val exampleCategoriesChunk02Part03: List<ExampleCategory> = listOf(
        // 22. Real World Patterns
        ExampleCategory(
            id = "real-world",
            title = "Real World Patterns",
            subtitle = "Practical animation patterns for apps",
            accentLabel = "UI",
            tags = listOf("UI", "PATTERN"),
            examples = listOf(
                ExampleItem(
                    title = "Card Entry",
                    description = "Card entering the screen",
                    demoType = DemoType.RealWorld,
                    effect = Canimation.Fade.Up + Canimation.Scale.Subtle,
                    preset = CanimationPreset.FadeUp,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Fade.Up + Canimation.Scale.Subtle,
    )""",
                ),
                ExampleItem(
                    title = "List Item Stagger",
                    description = "Staggered list item appearance",
                    demoType = DemoType.RealWorld,
                    effect = Canimation.Slide.Right + Canimation.Fade.In,
                    preset = CanimationPreset.SlideRight,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Slide.Right + Canimation.Fade.In,
    )""",
                ),
                ExampleItem(
                    title = "Notification Popup",
                    description = "Notification appearing on screen",
                    demoType = DemoType.RealWorld,
                    effect = Canimation.Entrance.Drop,
                    preset = CanimationPreset.DropIn,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.Drop,
    )""",
                ),
                ExampleItem(
                    title = "Button Emphasis",
                    description = "Button drawing attention on interaction",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Pulse,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isPressed,
        preset = CanimationPreset.Pulse,
    )""",
                ),
                ExampleItem(
                    title = "Modal Entrance",
                    description = "Modal dialog appearing",
                    demoType = DemoType.RealWorld,
                    effect = Canimation.Scale.Pop + Canimation.Fade.In,
                    preset = CanimationPreset.Pop,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Scale.Pop + Canimation.Fade.In,
    )""",
                ),
                ExampleItem(
                    title = "Tab Content",
                    description = "Tab content transitioning",
                    demoType = DemoType.Transition,
                    enterEffect = Canimation.Material.FadeThrough,
                    exitEffect = Canimation.Material.FadeThrough,
                    preset = CanimationPreset.FadeThrough,
                    codeSnippet = """Modifier.canimationTransition(
        visible = isVisible,
        enter = Canimation.Material.FadeThrough,
        exit = Canimation.Material.FadeThrough,
    )""",
                ),
            ),
        ),
        // ── Text & Typography ──
        ExampleCategory(
            id = "text-typography",
            title = "Text & Typography",
            subtitle = "Animated text effects and typography",
            accentLabel = "TEXT",
            tags = listOf("TEXT"),
            examples = listOf(
                ExampleItem(
                    title = "Scramble Text",
                    description = "Random characters resolve into target text",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ScrambleText,
                    codeSnippet = """ScrambleText(
        targetText = "Hello, World!",
        scrambleDuration = 1200,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Wavy Text",
                    description = "Letters oscillate in a wave pattern",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.WavyText,
                    codeSnippet = """WavyText(
        text = "Animated Wave",
        waveAmplitude = 10f,
        modifier = Modifier.padding(16.dp),
    )""",
                ),
                ExampleItem(
                    title = "Split Text Reveal",
                    description = "Text splits and reveals with staggered animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SplitTextReveal,
                    codeSnippet = """SplitTextReveal(
        text = "Split Reveal",
        delayPerChar = 80L,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Glitch Text",
                    description = "Digital glitch distortion effect on text",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.GlitchText,
                    codeSnippet = """GlitchText(
        text = "SYSTEM ERROR",
        glitchIntensity = 0.3f,
        modifier = Modifier.padding(8.dp),
    )""",
                ),
                ExampleItem(
                    title = "Rolling Digits",
                    description = "Digits roll like a slot machine",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.RollingDigits,
                    codeSnippet = """RollingDigits(
        targetValue = 42,
        modifier = Modifier.padding(16.dp),
    )""",
                ),
                ExampleItem(
                    title = "Vertical Ticker",
                    description = "Text scrolls vertically through values",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.VerticalTicker,
                    codeSnippet = """VerticalTicker(
        items = listOf("First", "Second", "Third"),
        modifier = Modifier.height(48.dp),
    )""",
                ),
                ExampleItem(
                    title = "Animated Underline Text",
                    description = "Underline draws beneath text on appear",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.AnimatedUnderlineText,
                    codeSnippet = """AnimatedUnderlineText(
        text = "Hover Me",
        underlineColor = Color.Blue,
        modifier = Modifier.padding(8.dp),
    )""",
                ),
                ExampleItem(
                    title = "Animated Gradient Text",
                    description = "Gradient colors animate across text",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.AnimatedGradientText,
                    codeSnippet = """AnimatedGradientText(
        text = "Shimmering Label",
        colors = listOf(Color.Red, Color.Yellow, Color.Green),
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Reveal Text Effect",
                    description = "Text reveals with a sliding mask",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.RevealTextEffect,
                    codeSnippet = """RevealTextEffect(
        text = "Revealed!",
        revealDuration = 1000,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Scatter Text",
                    description = "Characters scatter and reassemble",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ScatterText,
                    codeSnippet = """ScatterText(
        text = "Scatter Me",
        scatterRadius = 100f,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Text Line Reveal",
                    description = "Text lines reveal one at a time",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.TextLineReveal,
                    codeSnippet = """TextLineReveal(
        lines = listOf("Line one", "Line two", "Line three"),
        delayPerLine = 200L,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Typewriter Delete",
                    description = "Characters delete one by one in reverse",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.TypewriterDelete,
                    codeSnippet = """TypewriterDelete(
        text = "Deleting this text...",
        deleteSpeed = 40L,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
            ),
        ),
        // ── Cards & Surfaces ──
        ExampleCategory(
            id = "cards-surfaces",
            title = "Cards & Surfaces",
            subtitle = "Card animations, flips, and surface effects",
            accentLabel = "CARDS",
            tags = listOf("CARDS"),
            examples = listOf(
                ExampleItem(
                    title = "Flip Card",
                    description = "3D card flip between front and back",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.FlipCard,
                    codeSnippet = """FlipCard(
        frontText = "Product Name",
        backText = "Product Details",
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Tilt Card",
                    description = "Card tilts toward pointer on hover",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.TiltCard,
                    codeSnippet = """TiltCard(
        content = { Text("Tilt Me") },
        maxTiltDegrees = 15f,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Card Border Trace",
                    description = "Animated border traces around the card",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CardBorderTrace,
                    codeSnippet = """CardBorderTrace(
        borderColor = Color.Cyan,
        traceDuration = 2000,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Card Lift Hover",
                    description = "Card elevates with shadow on hover",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CardLiftHover,
                    codeSnippet = """CardLiftHover(
        elevation = 12.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
    )""",
                ),
                ExampleItem(
                    title = "Card Gradient Border",
                    description = "Animated gradient border effect",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CardGradientBorder,
                    codeSnippet = """CardGradientBorder(
        colors = listOf(Color.Magenta, Color.Cyan),
        borderWidth = 2.dp,
        modifier = Modifier.size(180.dp),
    )""",
                ),
                ExampleItem(
                    title = "Card Expand Collapse",
                    description = "Card smoothly expands and collapses",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CardExpandCollapse,
                    codeSnippet = """CardExpandCollapse(
        title = "Tap to Expand",
        expandedContent = { Text("Expanded details here") },
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Card Parallax Tilt",
                    description = "Parallax layers tilt with pointer movement",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CardParallaxTilt,
                    codeSnippet = """CardParallaxTilt(
        parallaxIntensity = 20f,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Card Glassmorphism",
                    description = "Frosted glass card with blur effect",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CardGlassmorphism,
                    codeSnippet = """CardGlassmorphism(
        blurRadius = 20.dp,
        tintColor = Color.White.copy(alpha = 0.2f),
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Card Reveal Wipe",
                    description = "Content reveals with a wipe transition",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CardRevealWipe,
                    codeSnippet = """CardRevealWipe(
        revealDirection = RevealDirection.Left,
        duration = 800,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Card Fan Stack",
                    description = "Cards fan out from a stack",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CardFanStack,
                    codeSnippet = """CardFanStack(
        cardCount = 5,
        fanAngle = 30f,
        modifier = Modifier.size(250.dp),
    )""",
                ),
                ExampleItem(
                    title = "Card Magnetic Snap",
                    description = "Card snaps to position with magnetic feel",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CardMagneticSnap,
                    codeSnippet = """CardMagneticSnap(
        snapThreshold = 50.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
    )""",
                ),
                ExampleItem(
                    title = "Card Shuffle",
                    description = "Cards shuffle like a deck",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CardShuffle,
                    codeSnippet = """CardShuffle(
        cardCount = 4,
        shuffleDuration = 600,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Card Stack Swipe",
                    description = "Swipeable card stack like a dating app",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CardStackSwipe,
                    codeSnippet = """CardStackSwipe(
        onSwipe = { direction -> /* handle */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
    )""",
                ),
            ),
        ),
)

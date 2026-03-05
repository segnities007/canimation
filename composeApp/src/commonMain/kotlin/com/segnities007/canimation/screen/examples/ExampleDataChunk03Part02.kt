package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset


internal val exampleCategoriesChunk03Part02: List<ExampleCategory> = listOf(
        // ── Visual Effects ──
        ExampleCategory(
            id = "visual-effects",
            title = "Visual Effects",
            subtitle = "Eye-catching visual animations and effects",
            accentLabel = "VISUAL",
            tags = listOf("VISUAL"),
            examples = listOf(
                ExampleItem(
                    title = "Color Morph",
                    description = "Smooth color morphing transitions",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ColorMorph,
                    codeSnippet = """ColorMorph(
        colors = listOf(Color.Red, Color.Blue, Color.Green),
        duration = 3000,
        modifier = Modifier.size(150.dp),
    )""",
                ),
                ExampleItem(
                    title = "Gradient Shift",
                    description = "Animated gradient color shifting",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.GradientShift,
                    codeSnippet = """GradientShift(
        colors = listOf(Color.Magenta, Color.Cyan),
        shiftSpeed = 2000,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Path Tracer",
                    description = "Animated path drawing on canvas",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PathTracer,
                    codeSnippet = """PathTracer(
        pathColor = Color.Cyan,
        strokeWidth = 3.dp,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Confetti Explosion",
                    description = "Burst of confetti particles on trigger",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ConfettiExplosion,
                    codeSnippet = """ConfettiExplosion(
        particleCount = 100,
        colors = listOf(Color.Red, Color.Yellow, Color.Blue),
        modifier = Modifier.fillMaxSize(),
    )""",
                ),
                ExampleItem(
                    title = "Wave Effect",
                    description = "Audio visualizer-style wave bars",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.WaveEffect,
                    codeSnippet = """WaveEffect(
        waveColor = Color.Blue,
        amplitude = 20f,
        frequency = 2f,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    )""",
                ),
                ExampleItem(
                    title = "Sliding Reveal",
                    description = "Content reveals with a sliding mask",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SlidingReveal,
                    codeSnippet = """SlidingReveal(
        direction = RevealDirection.Left,
        duration = 800,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Focus Blur Effect",
                    description = "Focus shifts with blur transitions",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.FocusBlurEffect,
                    codeSnippet = """FocusBlurEffect(
        blurRadius = 10.dp,
        focusArea = FocusArea.Center,
        modifier = Modifier.fillMaxSize(),
    )""",
                ),
                ExampleItem(
                    title = "Floating Particles",
                    description = "Ambient floating particles background",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.FloatingParticles,
                    codeSnippet = """FloatingParticles(
        particleCount = 30,
        particleColor = Color.White,
        modifier = Modifier.fillMaxSize(),
    )""",
                ),
                ExampleItem(
                    title = "Pulsing Avatar",
                    description = "Avatar with pulsing ring animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PulsingAvatar,
                    codeSnippet = """PulsingAvatar(
        imageSize = 64.dp,
        pulseColor = Color.Green,
        modifier = Modifier.size(80.dp),
    )""",
                ),
                ExampleItem(
                    title = "Pulse Radar",
                    description = "Radar-style pulse scanning animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PulseRadar,
                    codeSnippet = """PulseRadar(
        radarColor = Color.Green,
        pulseCount = 3,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Heartbeat Line",
                    description = "ECG-style heartbeat line animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.HeartbeatLine,
                    codeSnippet = """HeartbeatLine(
        lineColor = Color.Red,
        strokeWidth = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
    )""",
                ),
            ),
        ),
        // ── Physics & Motion ──
        ExampleCategory(
            id = "physics-motion",
            title = "Physics & Motion",
            subtitle = "Physics-based and spring-driven animations",
            accentLabel = "PHYSICS",
            tags = listOf("PHYSICS"),
            examples = listOf(
                ExampleItem(
                    title = "Bouncy Spring List",
                    description = "List items enter with spring bounce",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.BouncySpringList,
                    codeSnippet = """BouncySpringList(
        items = listOf("Item 1", "Item 2", "Item 3"),
        stiffness = Spring.StiffnessLow,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Stagger From Center",
                    description = "Items stagger outward from center",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.StaggerFromCenter,
                    codeSnippet = """StaggerFromCenter(
        itemCount = 7,
        staggerDelay = 100L,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Ticker Marquee",
                    description = "Scrolling ticker text marquee",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.TickerMarquee,
                    codeSnippet = """TickerMarquee(
        text = "Breaking News: Jetpack Compose is awesome!",
        speed = 60.dp,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Spring Chain",
                    description = "Chain of elements connected by springs",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SpringChain,
                    codeSnippet = """SpringChain(
        nodeCount = 5,
        stiffness = Spring.StiffnessMedium,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Parallax Layers",
                    description = "Layered parallax scrolling effect",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ParallaxLayers,
                    codeSnippet = """ParallaxLayers(
        layerCount = 3,
        depthFactor = 0.5f,
        modifier = Modifier.fillMaxSize(),
    )""",
                ),
                ExampleItem(
                    title = "Pendulum Swing",
                    description = "Pendulum swinging back and forth",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PendulumSwing,
                    codeSnippet = """PendulumSwing(
        swingAngle = 30f,
        period = 2000L,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Bouncing Ball",
                    description = "Ball bouncing with gravity physics",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.BouncingBall,
                    codeSnippet = """BouncingBall(
        ballSize = 24.dp,
        bounceHeight = 100.dp,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Slinky Spring",
                    description = "Slinky-style cascading spring animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SlinkySpring,
                    codeSnippet = """SlinkySpring(
        coilCount = 8,
        springColor = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
    )""",
                ),
            ),
        ),
        // ── Charts & Data Viz ──
        ExampleCategory(
            id = "charts-dataviz",
            title = "Charts & Data Viz",
            subtitle = "Animated charts and data visualizations",
            accentLabel = "CHARTS",
            tags = listOf("CHARTS"),
            examples = listOf(
                ExampleItem(
                    title = "Animated Pie Chart",
                    description = "Pie chart with animated segment reveals",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.AnimatedPieChart,
                    codeSnippet = """AnimatedPieChart(
        data = listOf(30f, 25f, 20f, 15f, 10f),
        colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Cyan),
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Animated Bar Chart",
                    description = "Bar chart with animated bar growth",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.AnimatedBarChart,
                    codeSnippet = """AnimatedBarChart(
        data = listOf(80f, 45f, 90f, 60f, 75f),
        barColor = Color.Blue,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
    )""",
                ),
            ),
        ),
        // ── Galleries & Lists ──
        ExampleCategory(
            id = "galleries-lists",
            title = "Galleries & Lists",
            subtitle = "Animated galleries, carousels, and lists",
            accentLabel = "GALLERY",
            tags = listOf("GALLERY"),
            examples = listOf(
                ExampleItem(
                    title = "Infinite Loading List",
                    description = "List that loads more items on scroll",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.InfiniteLoadingList,
                    codeSnippet = """InfiniteLoadingList(
        onLoadMore = { /* fetch next page */ },
        modifier = Modifier.fillMaxSize(),
    )""",
                ),
                ExampleItem(
                    title = "Horizontal Scroll Gallery",
                    description = "Horizontally scrolling image gallery",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.HorizontalScrollGallery,
                    codeSnippet = """HorizontalScrollGallery(
        itemCount = 10,
        itemWidth = 150.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Vertical Carousel",
                    description = "Vertically scrolling carousel with snap",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.VerticalCarousel,
                    codeSnippet = """VerticalCarousel(
        itemCount = 5,
        itemHeight = 200.dp,
        modifier = Modifier.fillMaxSize(),
    )""",
                ),
                ExampleItem(
                    title = "Waterfall Grid",
                    description = "Pinterest-style waterfall grid layout",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.WaterfallGrid,
                    codeSnippet = """WaterfallGrid(
        columns = 2,
        spacing = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    )""",
                ),
                ExampleItem(
                    title = "Zoom Hero Image",
                    description = "Image zooms to hero view on tap",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ZoomHeroImage,
                    codeSnippet = """ZoomHeroImage(
        maxZoom = 3f,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
    )""",
                ),
            ),
        ),
)

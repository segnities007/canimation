package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset


internal val exampleCategoriesChunk03Part01: List<ExampleCategory> = listOf(
        // ── Loading & Progress ──
        ExampleCategory(
            id = "loading-progress",
            title = "Loading & Progress",
            subtitle = "Loading indicators and progress animations",
            accentLabel = "LOADING",
            tags = listOf("LOADING"),
            examples = listOf(
                ExampleItem(
                    title = "Pulse Loading Dots",
                    description = "Dots pulse in sequence to indicate loading",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PulseLoadingDots,
                    codeSnippet = """PulseLoadingDots(
        dotCount = 3,
        dotSize = 12.dp,
        color = Color.Blue,
        modifier = Modifier.padding(16.dp),
    )""",
                ),
                ExampleItem(
                    title = "Shimmer Effect",
                    description = "Shimmering placeholder for loading content",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ShimmerEffect,
                    codeSnippet = """ShimmerEffect(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Skeleton Loader",
                    description = "Skeleton placeholder with shimmer",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SkeletonLoader,
                    codeSnippet = """SkeletonLoader(
        shape = SkeletonShape.RoundedRect,
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp),
    )""",
                ),
                ExampleItem(
                    title = "Progress Ring",
                    description = "Circular progress indicator with animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ProgressRing,
                    codeSnippet = """ProgressRing(
        progress = 0.75f,
        strokeWidth = 8.dp,
        color = Color.Green,
        modifier = Modifier.size(64.dp),
    )""",
                ),
                ExampleItem(
                    title = "Liquid Fill",
                    description = "Liquid-style fill animation in a circle",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.LiquidFill,
                    codeSnippet = """LiquidFill(
        fillPercent = 0.6f,
        liquidColor = Color.Blue,
        modifier = Modifier.size(120.dp),
    )""",
                ),
                ExampleItem(
                    title = "Glow Progress Bar",
                    description = "Progress bar with glowing edge effect",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.GlowProgressBar,
                    codeSnippet = """GlowProgressBar(
        progress = 0.8f,
        glowColor = Color.Cyan,
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp),
    )""",
                ),
                ExampleItem(
                    title = "Morph Progress Indicator",
                    description = "Progress indicator that morphs between shapes",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.MorphProgressIndicator,
                    codeSnippet = """MorphProgressIndicator(
        progress = 0.5f,
        shape = MorphShape.Circle,
        modifier = Modifier.size(60.dp),
    )""",
                ),
                ExampleItem(
                    title = "Progress Scrubber",
                    description = "Draggable progress scrubber control",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ProgressScrubber,
                    codeSnippet = """ProgressScrubber(
        progress = 0.4f,
        onProgressChange = { newVal -> /* update */ },
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
            ),
        ),
        // ── Counters & Data ──
        ExampleCategory(
            id = "counters-data",
            title = "Counters & Data",
            subtitle = "Animated counters and data displays",
            accentLabel = "DATA",
            tags = listOf("DATA"),
            examples = listOf(
                ExampleItem(
                    title = "Animated Counter",
                    description = "Number animates up to target value",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.AnimatedCounter,
                    codeSnippet = """AnimatedCounter(
        targetValue = 1234,
        label = "Total Users",
        modifier = Modifier.padding(16.dp),
    )""",
                ),
                ExampleItem(
                    title = "Engagement Stats",
                    description = "Animated social engagement statistics",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.EngagementStats,
                    codeSnippet = """EngagementStats(
        likes = 1200,
        comments = 340,
        shares = 89,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Price Switcher",
                    description = "Animated price toggle between plans",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.PriceSwitcher,
                    codeSnippet = """PriceSwitcher(
        monthlyPrice = 9.99f,
        yearlyPrice = 99.99f,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
            ),
        ),
        // ── Navigation & Layout ──
        ExampleCategory(
            id = "navigation-layout",
            title = "Navigation & Layout",
            subtitle = "Animated navigation and layout components",
            accentLabel = "NAV",
            tags = listOf("NAV"),
            examples = listOf(
                ExampleItem(
                    title = "Animated Tabs",
                    description = "Tab bar with animated selection indicator",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.AnimatedTabs,
                    codeSnippet = """AnimatedTabs(
        tabs = listOf("Home", "Search", "Profile"),
        selectedIndex = 0,
        onTabSelected = { index -> /* navigate */ },
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Mega Menu Reveal",
                    description = "Large dropdown menu with reveal animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.MegaMenuReveal,
                    codeSnippet = """MegaMenuReveal(
        menuItems = listOf("Products", "Services", "About"),
        revealDuration = 400,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Smooth Tab Indicator",
                    description = "Tab indicator slides smoothly between tabs",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SmoothTabIndicator,
                    codeSnippet = """SmoothTabIndicator(
        tabs = listOf("All", "Active", "Archived"),
        selectedIndex = 0,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Circular Menu",
                    description = "Menu items arranged in a circle with animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CircularMenu,
                    codeSnippet = """CircularMenu(
        items = listOf("Share", "Edit", "Delete"),
        radius = 80.dp,
        modifier = Modifier.size(200.dp),
    )""",
                ),
                ExampleItem(
                    title = "Elastic Drawer",
                    description = "Side drawer with elastic spring animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ElasticDrawer,
                    codeSnippet = """ElasticDrawer(
        drawerWidth = 280.dp,
        elasticity = 0.3f,
        modifier = Modifier.fillMaxHeight(),
    )""",
                ),
                ExampleItem(
                    title = "Scroll Direction Header",
                    description = "Header hides/shows based on scroll direction",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ScrollDirectionHeader,
                    codeSnippet = """ScrollDirectionHeader(
        title = "My Feed",
        collapseOnScroll = true,
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
            ),
        ),
        // ── Interactive Controls ──
        ExampleCategory(
            id = "interactive-controls",
            title = "Interactive Controls",
            subtitle = "Buttons, toggles, and interactive elements",
            accentLabel = "INTERACTIVE",
            tags = listOf("INTERACTIVE"),
            examples = listOf(
                ExampleItem(
                    title = "Hold To Confirm",
                    description = "Long-press button with progress confirmation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.HoldToConfirm,
                    codeSnippet = """HoldToConfirm(
        text = "Hold to Delete",
        holdDuration = 2000L,
        onConfirmed = { /* delete item */ },
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Swipe Actions",
                    description = "Swipe to reveal action buttons",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SwipeActions,
                    codeSnippet = """SwipeActions(
        onSwipeLeft = { /* archive */ },
        onSwipeRight = { /* delete */ },
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Multi State Badge",
                    description = "Badge transitions between multiple states",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.MultiStateBadge,
                    codeSnippet = """MultiStateBadge(
        state = BadgeState.New,
        text = "3 new",
        modifier = Modifier.padding(4.dp),
    )""",
                ),
                ExampleItem(
                    title = "Elastic Pull",
                    description = "Elastic pull-to-refresh interaction",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ElasticPull,
                    codeSnippet = """ElasticPull(
        onRefresh = { /* reload data */ },
        elasticity = 0.5f,
        modifier = Modifier.fillMaxSize(),
    )""",
                ),
                ExampleItem(
                    title = "Expanding Search",
                    description = "Search bar expands from icon on tap",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ExpandingSearch,
                    codeSnippet = """ExpandingSearch(
        placeholder = "Search...",
        onSearch = { query -> /* filter */ },
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "iOS Slider",
                    description = "iOS-style slider with smooth animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.IOSSlider,
                    codeSnippet = """IOSSlider(
        value = 0.5f,
        onValueChange = { newVal -> /* update */ },
        modifier = Modifier.fillMaxWidth(),
    )""",
                ),
                ExampleItem(
                    title = "Checkbox Animation",
                    description = "Animated checkbox with check mark draw",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CheckboxAnimation,
                    codeSnippet = """CheckboxAnimation(
        checked = true,
        onCheckedChange = { isChecked -> /* update */ },
        modifier = Modifier.size(24.dp),
    )""",
                ),
                ExampleItem(
                    title = "Switch Animation",
                    description = "Animated toggle switch with spring physics",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SwitchAnimation,
                    codeSnippet = """SwitchAnimation(
        checked = false,
        onCheckedChange = { isOn -> /* toggle */ },
        modifier = Modifier.padding(8.dp),
    )""",
                ),
                ExampleItem(
                    title = "Magnetic Button",
                    description = "Button attracted to pointer with magnetic effect",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.MagneticButton,
                    codeSnippet = """MagneticButton(
        text = "Hover Me",
        magneticRadius = 40.dp,
        onClick = { /* action */ },
        modifier = Modifier.padding(16.dp),
    )""",
                ),
                ExampleItem(
                    title = "Ripple Button",
                    description = "Button with material ripple on tap",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.RippleButton,
                    codeSnippet = """RippleButton(
        text = "Tap Me",
        rippleColor = Color.White,
        onClick = { /* action */ },
        modifier = Modifier.padding(16.dp),
    )""",
                ),
                ExampleItem(
                    title = "Spring Chip",
                    description = "Chip component with spring selection animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.SpringChip,
                    codeSnippet = """SpringChip(
        label = "Selected",
        selected = true,
        onClick = { /* toggle */ },
        modifier = Modifier.padding(4.dp),
    )""",
                ),
                ExampleItem(
                    title = "Toast Notification",
                    description = "Animated toast notification popup",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.ToastNotification,
                    codeSnippet = """ToastNotification(
        message = "Item saved successfully!",
        duration = 3000L,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    )""",
                ),
                ExampleItem(
                    title = "Coin Flip",
                    description = "3D coin flip animation",
                    demoType = DemoType.Component,
                    componentKey = ComponentDemoKeys.CoinFlip,
                    codeSnippet = """CoinFlip(
        frontContent = { Text("Heads") },
        backContent = { Text("Tails") },
        modifier = Modifier.size(100.dp),
    )""",
                ),
            ),
        ),
)

package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset

internal val exampleCategoriesChunk05: List<ExampleCategory> = listOf(
        ExampleCategory(
            id = "interactive-controls",
            title = "Interactive Controls",
            subtitle = "Animated interactive UI controls",
            accentLabel = "UI",
            tags = listOf("UI", "INTERACTIVE"),
            examples = listOf(
                ExampleItem("Animated Tag Cloud", "Floating tag cloud entry", "AnimatedTagCloud(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.ScaleUp,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedTagCloud),
                ExampleItem("Color Swatch Picker", "Animated color selector", "ColorSwatchPicker(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Elastic.Snap,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.ColorSwatchPicker),
                ExampleItem("Animated Chip Group", "Filter chip group animation", "AnimatedChipGroup(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedChipGroup),
                ExampleItem("Animated Pricing Toggle", "Monthly/Annual price switch", "AnimatedPricingToggle(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Shrink.Subtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedPricingToggle),
            ),
        ),
        ExampleCategory(
            id = "visual-effects",
            title = "Visual Effects",
            subtitle = "Canvas-based visual animations",
            accentLabel = "CANVAS",
            tags = listOf("UI", "CANVAS"),
            examples = listOf(
                ExampleItem("Pulse Ring", "Expanding pulse ring animation", "PulseRing(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Expand,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.PulseRing),
                ExampleItem("Wave Progress Bar", "Sine wave progress indicator", "WaveProgressBar(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.WaveProgressBar),
            ),
        ),
        ExampleCategory(
            id = "layout-patterns",
            title = "Layout Patterns",
            subtitle = "Animated layout components",
            accentLabel = "LAYOUT",
            tags = listOf("UI", "LAYOUT"),
            examples = listOf(
                ExampleItem("Stacked Avatars", "Overlapping avatar group", "StackedAvatars(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Diagonal.Subtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.StackedAvatars),
                ExampleItem("Animated Code Block", "Line-by-line code reveal", "AnimatedCodeBlock(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.LeftSubtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedCodeBlock),
                ExampleItem("Animated List Item", "Staggered list entry", "AnimatedListItem(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Tilt.Left,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedListItem),
                ExampleItem("Animated Feature Grid", "Feature grid with diagonal entry", "AnimatedFeatureGrid(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Diagonal.BottomRight,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedFeatureGrid),
                ExampleItem("Animated Search Result", "Floating search results", "AnimatedSearchResult(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedSearchResult),
            ),
        ),
        // ─── ComponentAnimations7 categories ───
        ExampleCategory(
            id = "text-animations",
            title = "Text & Typography",
            subtitle = "Animated text and typography effects",
            accentLabel = "TEXT",
            tags = listOf("TEXT", "UI"),
            examples = listOf(
                ExampleItem("Text Fade Reveal", "Word-by-word fade reveal", "TextFadeReveal(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Up,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextFadeReveal),
                ExampleItem("Text Highlighter", "Sweeping text highlight", "TextHighlighter(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Reveal.Left,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextHighlighter),
                ExampleItem("Text Shuffle Word", "Cycling word animation", "TextShuffleWord(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextShuffleWord),
                ExampleItem("Text Gradient Reveal", "Gradient sweep text reveal", "TextGradientReveal(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.RightSubtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextGradientReveal),
                ExampleItem("Text Rotate Words", "Rotating word carousel", "TextRotateWords(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rotate.In,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextRotateWords),
            ),
        ),
        ExampleCategory(
            id = "card-components",
            title = "Card Components",
            subtitle = "Rich animated card layouts",
            accentLabel = "CARDS",
            tags = listOf("CARDS", "UI"),
            examples = listOf(
                ExampleItem("Profile Card", "User profile with avatar", "ProfileCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.ProfileCard),
                ExampleItem("Pricing Card", "Animated pricing plan", "PricingCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rise.In,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.PricingCard),
                ExampleItem("Notification Card", "Alert notification card", "NotificationCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.DownSubtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.NotificationCard),
                ExampleItem("Testimonial Card", "Customer review card", "TestimonialCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.TestimonialCard),
                ExampleItem("Metric Card", "Data metric display card", "MetricCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Tilt.Swing,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.MetricCard),
                ExampleItem("Product Card", "E-commerce product card", "ProductCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Up,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.ProductCard),
            ),
        ),
        ExampleCategory(
            id = "nav-components",
            title = "Navigation Components",
            subtitle = "Animated navigation and menu patterns",
            accentLabel = "NAV",
            tags = listOf("NAV", "NAVIGATION"),
            examples = listOf(
                ExampleItem("Side Menu Reveal", "Staggered side menu entry", "SideMenuReveal(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.LeftSubtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.SideMenuReveal),
                ExampleItem("Pagination Dots", "Page indicator dots", "PaginationDots(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.PaginationDots),
                ExampleItem("Command Palette", "Quick command search palette", "CommandPalette(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Down,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.CommandPalette),
            ),
        ),
        ExampleCategory(
            id = "charts-data",
            title = "Charts & Data Viz",
            subtitle = "Animated charts and data displays",
            accentLabel = "CHARTS",
            tags = listOf("CHARTS", "DATA"),
            examples = listOf(
                ExampleItem("Mini Bar Chart", "Animated bar chart", "MiniBarChart(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rise.In,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.MiniBarChart),
                ExampleItem("Donut Chart", "Animated donut/pie chart", "DonutChart(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rotate.In,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.DonutChart),
                ExampleItem("Spark Line", "Mini sparkline chart", "SparkLine(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.RightSubtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.SparkLine),
                ExampleItem("Data Table", "Staggered data table rows", "DataTable(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Up,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.DataTable),
            ),
        ),
        // ─── ComponentAnimations8 categories ───
        ExampleCategory(
            id = "ui-patterns",
            title = "UI Patterns",
            subtitle = "Common UI components with animations",
            accentLabel = "UI",
            tags = listOf("UI", "INTERACTIVE"),
            examples = listOf(
                ExampleItem("Animated Banner", "Slide-in notification banner", "AnimatedBanner(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.DownSubtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedBanner),
                ExampleItem("Animated Tooltip", "Floating tooltip popover", "AnimatedTooltip(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Up,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedTooltip),
                ExampleItem("Animated Alert", "Cycling status alerts", "AnimatedAlert(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.LeftSubtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedAlert),
                ExampleItem("Animated Dropdown", "Expanding dropdown menu", "AnimatedDropdown(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Up,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedDropdown),
                ExampleItem("Animated Progress Card", "Upload progress indicator", "AnimatedProgressCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedProgressCard),
                ExampleItem("Animated Dialog", "Modal dialog with scale entry", "AnimatedDialog(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedDialog),
            ),
        ),
        ExampleCategory(
            id = "visual-canvas",
            title = "Visual & Canvas",
            subtitle = "Canvas-based visual effects",
            accentLabel = "VISUAL",
            tags = listOf("VISUAL", "CANVAS"),
            examples = listOf(
                ExampleItem("Orbit Dots", "Orbital dot animation", "OrbitDots(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rotate.In,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.OrbitDots),
                ExampleItem("Bouncing Loader", "Three-dot bouncing loader", "BouncingLoader(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Bounce.In,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.BouncingLoader),
                ExampleItem("Glow Pulse", "Pulsing glow orb", "GlowPulse(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.GlowPulse),
                ExampleItem("Waveform Bars", "Audio waveform visualization", "WaveformBars(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.WaveformBars),
            ),
        ),
        ExampleCategory(
            id = "interactive-widgets",
            title = "Interactive Widgets",
            subtitle = "User-interactive animated components",
            accentLabel = "INTERACTIVE",
            tags = listOf("INTERACTIVE", "UI"),
            examples = listOf(
                ExampleItem("Like Button", "Heart toggle with spring", "LikeButton(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Spring.PopIn,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.LikeButton),
                ExampleItem("Quantity Selector", "Animated +/- counter", "QuantitySelector(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.QuantitySelector),
                ExampleItem("Radio Selector", "Animated radio group", "RadioSelector(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.RadioSelector),
                ExampleItem("Slider Control", "Animated volume slider", "SliderControl(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.RightSubtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.SliderControl),
                ExampleItem("Animated Password Field", "Password input animation", "AnimatedPasswordField(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Micro.NudgeUp,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedPasswordField),
                ExampleItem("Animated File Upload", "Multi-stage upload animation", "AnimatedFileUpload(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedFileUpload),
                ExampleItem("Animated Vote", "Upvote/downvote counter", "AnimatedVote(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedVote),
                ExampleItem("Animated Search Bar", "Expanding search input", "AnimatedSearchBar(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Stretch.Horizontal,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedSearchBar),
                ExampleItem("Animated Form Validation", "Live form field validation", "AnimatedFormValidation(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Micro.NudgeUp,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedFormValidation),
            ),
        ),
        // ─── ComponentAnimations9 categories ───
        ExampleCategory(
            id = "ui-advanced",
            title = "Advanced UI",
            subtitle = "Complex animated UI components",
            accentLabel = "UI",
            tags = listOf("UI", "INTERACTIVE"),
            examples = listOf(
                ExampleItem("Animated Bottom Sheet", "Slide-up bottom sheet", "AnimatedBottomSheet(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.Up,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedBottomSheet),
                ExampleItem("Animated Snackbar", "Toast notification snackbar", "AnimatedSnackbar(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.Up,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedSnackbar),
                ExampleItem("Animated FAB", "Expandable floating action button", "AnimatedFab(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedFab),
                ExampleItem("Animated Chip Input", "Tag input with animated chips", "AnimatedChipInput(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedChipInput),
                ExampleItem("Animated Accordion", "Collapsible accordion sections", "AnimatedAccordion(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Up,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedAccordion),
                ExampleItem("Animated Stepper", "Multi-step progress stepper", "AnimatedStepper(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.RightSubtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedStepper),
            ),
        ),
        ExampleCategory(
            id = "visual-advanced",
            title = "Visual Patterns",
            subtitle = "Geometric and particle visual effects",
            accentLabel = "VISUAL",
            tags = listOf("VISUAL", "CANVAS"),
            examples = listOf(
                ExampleItem("Morphing Shape", "Square-to-circle morph", "MorphingShape(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rotate.In,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.MorphingShape),
                ExampleItem("Spiral Dots", "Spiraling dot pattern", "SpiralDots(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rotate.In,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.SpiralDots),
                ExampleItem("DNA Helix", "Double helix animation", "DnaHelixCanvas(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Wave.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.DnaHelixCanvas),
                ExampleItem("Hex Grid", "Animated hexagonal grid", "HexGrid(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.HexGrid),
                ExampleItem("Particle Field", "Floating particle field", "ParticleField(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.ParticleField),
                ExampleItem("Rotating Squares", "Counter-rotating squares", "RotatingSquares(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rotate.In,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.RotatingSquares),
            ),
        ),
)

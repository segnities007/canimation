package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset

internal val exampleCategoriesChunk06: List<ExampleCategory> = listOf(
        ExampleCategory(
            id = "interactive-advanced",
            title = "Interactive Advanced",
            subtitle = "Rich interactive animated components",
            accentLabel = "INTERACTIVE",
            tags = listOf("INTERACTIVE", "UI"),
            examples = listOf(
                ExampleItem("Segmented Control", "Animated segment selector", "SegmentedControl(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.SegmentedControl),
                ExampleItem("Animated Switch", "Custom toggle switch", "AnimatedSwitch(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Spring.PopIn,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedSwitch),
                ExampleItem("Animated PIN Input", "PIN code entry with dots", "AnimatedPinInput(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedPinInput),
                ExampleItem("Animated Color Picker", "Color swatch selector", "AnimatedColorPicker(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Spring.PopIn,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedColorPicker),
                ExampleItem("Notification Bell", "Bell with badge animation", "AnimatedNotificationBell(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Attention.Swing,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedNotificationBell),
                ExampleItem("Countdown Timer", "Circular countdown timer", "AnimatedCountdownTimer(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedCountdownTimer),
                ExampleItem("Animated Credit Card", "3D card flip animation", "AnimatedCreditCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Flip.Horizontal,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedCreditCard),
                ExampleItem("Theme Toggle", "Dark/light mode toggle", "AnimatedThemeToggle(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rotate.In,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.AnimatedThemeToggle),
            ),
        ),
        // ─── ComponentAnimations10 categories ───
        ExampleCategory(
            id = "text-advanced",
            title = "Text Effects",
            subtitle = "Advanced text animation patterns",
            accentLabel = "TEXT",
            tags = listOf("TEXT", "UI"),
            examples = listOf(
                ExampleItem("Typewriter Effect", "Character-by-character typing", "TypewriterEffect(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.TypewriterEffect),
                ExampleItem("Text Morph", "Cycling word transition", "TextMorph(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Up,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextMorph),
                ExampleItem("Text Strikethrough", "Price comparison animation", "TextStrikethrough(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextStrikethrough),
                ExampleItem("Text Glitch", "RGB glitch text effect", "TextGlitch(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Glitch.Subtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.TextGlitch),
            ),
        ),
        ExampleCategory(
            id = "cards-advanced",
            title = "Rich Cards",
            subtitle = "Feature-rich animated card layouts",
            accentLabel = "CARDS",
            tags = listOf("CARDS", "UI"),
            examples = listOf(
                ExampleItem("Recipe Card", "Food recipe card", "RecipeCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rise.In,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.RecipeCard),
                ExampleItem("Weather Card", "Cycling weather display", "WeatherCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Fade.Gentle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.WeatherCard),
                ExampleItem("Event Card", "Calendar event card", "EventCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.LeftSubtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.EventCard),
                ExampleItem("Music Player Card", "Audio player interface", "MusicPlayerCard(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Float.Up,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.MusicPlayerCard),
            ),
        ),
        ExampleCategory(
            id = "nav-advanced",
            title = "Navigation Bars",
            subtitle = "Animated navigation bar patterns",
            accentLabel = "NAV",
            tags = listOf("NAV", "NAVIGATION"),
            examples = listOf(
                ExampleItem("Bottom Nav Bar", "Animated bottom navigation", "BottomNavBar(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Spring.PopIn,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.BottomNavBar),
            ),
        ),
        ExampleCategory(
            id = "data-advanced",
            title = "Data Visualizations",
            subtitle = "Advanced animated chart components",
            accentLabel = "CHARTS",
            tags = listOf("CHARTS", "DATA"),
            examples = listOf(
                ExampleItem("Radar Chart", "Animated radar/spider chart", "RadarChart(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Scale.Pop,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.RadarChart),
                ExampleItem("Horizontal Bar Chart", "Animated horizontal bars", "HorizontalBarChart(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Slide.RightSubtle,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.HorizontalBarChart),
                ExampleItem("Gauge Chart", "Semi-circle gauge meter", "GaugeChart(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Rotate.In,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.GaugeChart),
                ExampleItem("Live Counter", "Real-time number counter", "LiveCounter(modifier = Modifier.canimation(\n    visible = isVisible,\n    effect = Canimation.Micro.NudgeUp,\n))", demoType = DemoType.Component, componentKey = ComponentDemoKeys.LiveCounter),
            ),
        ),
)

package com.segnities007.canimation.screen.examples

import io.github.canimation.core.CanimationEffect

internal fun formatDemoType(demoType: DemoType): String = when (demoType) {
    DemoType.Effect -> "Modifier.canimation"
    DemoType.Transition -> "Modifier.canimationTransition"
    DemoType.Composition -> "Effect Composition (+)"
    DemoType.Stagger -> "Stagger Pattern"
    DemoType.Emphasis -> "Modifier.canimationEmphasize"
    DemoType.RealWorld -> "Real World Pattern"
    DemoType.Component -> "Rich Component"
    DemoType.EnterExit -> "Enter / Exit"
    DemoType.Visibility -> "CanimationVisibility"
}

internal fun describeEffect(effect: CanimationEffect): String {
    val parts = mutableListOf<String>()
    effect.alpha?.let { parts.add("alpha: ${it.from}→${it.to}") }
    effect.offsetX?.let { parts.add("offsetX: ${it.from}→${it.to}") }
    effect.offsetY?.let { parts.add("offsetY: ${it.from}→${it.to}") }
    effect.scale?.let { parts.add("scale: ${it.from}→${it.to}") }
    effect.rotation?.let { parts.add("rotation: ${it.from}°→${it.to}°") }
    parts.add("${effect.durationMs}ms")
    return if (parts.size == 1) "Default (${effect.durationMs}ms)" else parts.joinToString(", ")
}

internal fun generateHowItWorks(item: ExampleItem): String = when (item.demoType) {
    DemoType.Effect ->
        """This animation uses Modifier.canimation() to animate element properties between hidden and visible states. When visible becomes true, the element transitions from the initial effect values (offset, alpha, scale, rotation) to their default state using an easing curve. The animation is GPU-accelerated through graphicsLayer."""
    DemoType.Transition ->
        """This uses Modifier.canimationTransition() with separate enter and exit effects. The enter effect plays when visible becomes true, and the exit effect (reversed) plays when visible becomes false. This allows asymmetric transitions — for example, sliding in from the left but fading out."""
    DemoType.Composition ->
        """Effects are combined using the + operator. When two CanimationEffect objects are combined, the right-hand side fills in any null values from the left-hand side. This lets you compose complex multi-property animations from simple building blocks: Canimation.Fade.Up + Canimation.Rotate.In creates a fade-up with rotation."""
    DemoType.Stagger ->
        """Stagger patterns apply the same animation to multiple elements with increasing delays. Each item receives a delay of index × staggerMs, creating a cascading wave effect. Use Canimation.Stagger constants (Fast=60ms, Normal=80ms, Slow=120ms) to control timing."""
    DemoType.Emphasis ->
        """Modifier.canimationEmphasize() creates attention-seeking animations triggered by a boolean state. Unlike enter/exit animations, emphasis effects play while the element is already visible — pulse, shake, bounce, etc. Useful for form validation feedback, notification badges, or interactive highlights."""
    DemoType.Component ->
        """This is a rich animated component that uses Modifier.canimation() for its entry animation, combined with internal Compose animation APIs for its unique behavior. The entry animation ensures smooth appearance using the canimation system, while custom animations handle the component's specific interaction pattern."""
    DemoType.RealWorld,
    DemoType.EnterExit,
    DemoType.Visibility,
    ->
        """This animation demonstrates the canimation library's approach to declarative animations in Compose Multiplatform. Apply effects through Modifier extensions for a clean, composable API."""
}

internal fun generateIntegrationGuide(item: ExampleItem, tag: String): String = when (item.demoType) {
    DemoType.Effect ->
        """To use this animation in your project:
1. Add the canimation-core dependency
2. Create a boolean state for visibility
3. Apply Modifier.canimation() to your composable
4. Toggle the visibility state to trigger the animation

Category: $tag.

The animation respects CanimationProvider policy — wrap your app in CanimationProvider to globally control animation behavior (SystemAware, AlwaysFull, AlwaysReduced, AlwaysOff)."""
    DemoType.Transition ->
        """To use asymmetric transitions:
1. Define separate enter and exit CanimationEffect instances
2. Apply Modifier.canimationTransition(visible, enter, exit)
3. The enter effect plays forward when visible=true
4. The exit effect plays in reverse when visible=false

Combine with CanimationVisibility for composable-level enter/exit."""
    DemoType.Composition ->
        """To compose effects:
1. Combine effects with the + operator: effectA + effectB
2. The right-hand side overrides non-null values of the left
3. Use this to create complex animations from simple primitives
4. Example: Canimation.Fade.Up + Canimation.Scale.Pop"""
    DemoType.Component ->
        """This component uses canimation for entry animation:
1. Import the component from the examples package
2. The component handles its own internal animation logic
3. Entry animation is powered by Modifier.canimation()
4. Wrap in CanimationProvider to control animation policy"""
    DemoType.Stagger,
    DemoType.Emphasis,
    DemoType.RealWorld,
    DemoType.EnterExit,
    DemoType.Visibility,
    ->
        """Category: $tag.

Add canimation-core to your dependencies and apply the appropriate Modifier extension. All animations work cross-platform on Android, iOS, Desktop, and Web (JS/WASM)."""
}

internal fun generateUsageCode(item: ExampleItem): String = when (item.demoType) {
    DemoType.Effect,
    DemoType.Transition,
    DemoType.Composition,
    DemoType.Stagger,
    DemoType.Emphasis,
    DemoType.EnterExit,
    DemoType.Visibility,
    DemoType.RealWorld,
    -> {
        val snippet = item.codeSnippet.trimIndent()
        "var visible by remember { mutableStateOf(false) }\n" +
            "LaunchedEffect(Unit) { visible = true }\n\n" +
            "Box(\n" +
            "    modifier = ${snippet}\n" +
            ") {\n" +
            "    // Your content here\n" +
            "}"
    }

    DemoType.Component -> {
        val snippet = item.codeSnippet.trimIndent()
        "// Component with canimation entry animation\n" +
            snippet +
            "\n\n" +
            "// The component uses Modifier.canimation() internally:\n" +
            "// modifier.canimation(visible = entryVisible, effect = Canimation.XXX.YYY)"
    }
}

internal fun generateFullExample(item: ExampleItem): String = when (item.demoType) {
    DemoType.Effect,
    DemoType.Transition,
    DemoType.Composition,
    DemoType.Stagger,
    DemoType.Emphasis,
    DemoType.EnterExit,
    DemoType.Visibility,
    DemoType.RealWorld,
    -> {
        val snippet = item.codeSnippet.trimIndent()
        "@Composable\n" +
            "fun MyScreen() {\n" +
            "    var visible by remember { mutableStateOf(false) }\n" +
            "    LaunchedEffect(Unit) { visible = true }\n\n" +
            "    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {\n" +
            "        Box(\n" +
            "            modifier = ${snippet.lines().joinToString("\n                ")}\n" +
            "        ) {\n" +
            "            Text(\"Hello, Canimation!\")\n" +
            "        }\n" +
            "    }\n" +
            "}"
    }

    DemoType.Component -> {
        val snippet = item.codeSnippet.trimIndent()
        "@Composable\n" +
            "fun MyScreen() {\n" +
            "    // The component uses Modifier.canimation() internally\n" +
            "    // for its entry animation\n" +
            "    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {\n" +
            "        ${snippet.lines().joinToString("\n        ")}\n" +
            "    }\n" +
            "}"
    }
}

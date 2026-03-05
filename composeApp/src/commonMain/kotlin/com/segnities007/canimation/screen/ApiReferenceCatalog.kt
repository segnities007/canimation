package com.segnities007.canimation.screen

internal val apiEntries: List<ApiEntry> = buildList {

    // ── Modifier extensions ──

    add(ApiEntry(
        name = "Modifier.canimation",
        signature = "fun Modifier.canimation(\n    visible: Boolean,\n    effect: CanimationEffect,\n): Modifier",
        description = "Primary API. Apply enter/exit animations using composable CanimationEffect objects. Effects can be combined with the + operator. Respects CanimationProvider policy (Full / Reduced / Off).",
        category = RefFilter.Modifiers,
        badge = "RECOMMENDED",
        codeExample = """// Simple fade up
Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Fade.Up,
)

// Combine effects
Modifier.canimation(
    visible = isVisible,
    effect = Canimation.Scale.Pop + Canimation.Fade.In,
)""",
    ))

    add(ApiEntry(
        name = "Modifier.canimationTransition",
        signature = "fun Modifier.canimationTransition(\n    visible: Boolean,\n    enter: CanimationEffect,\n    exit: CanimationEffect? = null,\n): Modifier",
        description = "Asymmetric enter/exit transitions using CanimationEffect. If exit is null, the enter effect is automatically reversed.",
        category = RefFilter.Modifiers,
        badge = "ADVANCED",
        codeExample = """Modifier.canimationTransition(
    visible = isVisible,
    enter = Canimation.Fade.Up,
    exit = Canimation.Fade.Down,
)""",
    ))

    add(ApiEntry(
        name = "Modifier.canimationEnter",
        signature = "fun Modifier.canimationEnter(\n    visible: Boolean,\n    preset: CanimationPreset = CanimationPreset.FadeUp,\n): Modifier",
        description = "Enter-only animation using a named CanimationPreset. Ideal for lazy layouts and staggered list items.",
        category = RefFilter.Modifiers,
        badge = "LISTS",
        codeExample = """LazyColumn {
    itemsIndexed(items) { index, item ->
        Card(
            Modifier.canimationEnter(
                visible = true,
                preset = CanimationPreset.FadeUp,
            )
        ) { Text(item.name) }
    }
}""",
    ))

    add(ApiEntry(
        name = "Modifier.canimationEnter (custom spec)",
        signature = "fun Modifier.canimationEnter(\n    visible: Boolean,\n    fullSpec: CanimationSpec,\n    reducedSpec: CanimationSpec = fullSpec.toReduced(),\n): Modifier",
        description = "Enter-only animation with custom CanimationSpec for full control over duration, easing, and animation properties.",
        category = RefFilter.Modifiers,
        codeExample = """val spec = CanimationSpec(
    durationMs = 400,
    easing = FastOutSlowInEasing,
    alpha = CanimationRange(0f, 1f),
    offsetY = CanimationDpRange(24.dp, 0.dp),
)
Modifier.canimationEnter(visible = show, fullSpec = spec)""",
    ))

    add(ApiEntry(
        name = "Modifier.canimationExit",
        signature = "fun Modifier.canimationExit(\n    visible: Boolean,\n    preset: CanimationPreset = CanimationPreset.FadeUp,\n): Modifier",
        description = "Exit-only animation using a named CanimationPreset. Applies the reversed enter spec when the element exits.",
        category = RefFilter.Modifiers,
    ))

    add(ApiEntry(
        name = "Modifier.canimationExit (custom spec)",
        signature = "fun Modifier.canimationExit(\n    visible: Boolean,\n    fullSpec: CanimationSpec,\n    reducedSpec: CanimationSpec = fullSpec.toReduced(),\n): Modifier",
        description = "Exit-only animation with custom CanimationSpec for full control.",
        category = RefFilter.Modifiers,
    ))

    add(ApiEntry(
        name = "Modifier.canimationTransition (preset)",
        signature = "fun Modifier.canimationTransition(\n    visible: Boolean,\n    enterPreset: CanimationPreset = CanimationPreset.FadeUp,\n    exitPreset: CanimationPreset = enterPreset,\n): Modifier",
        description = "Full enter/exit transition using named CanimationPresets. Both enter and exit can be different presets.",
        category = RefFilter.Modifiers,
        codeExample = """Modifier.canimationTransition(
    visible = expanded,
    enterPreset = CanimationPreset.BounceIn,
    exitPreset = CanimationPreset.Fade,
)""",
    ))

    add(ApiEntry(
        name = "Modifier.canimationTransition (full spec)",
        signature = "fun Modifier.canimationTransition(\n    visible: Boolean,\n    enterFullSpec: CanimationSpec,\n    enterReducedSpec: CanimationSpec,\n    exitFullSpec: CanimationSpec,\n    exitReducedSpec: CanimationSpec,\n): Modifier",
        description = "Most flexible transition. Define separate enter/exit specs with full/reduced motion variants for complete accessibility control.",
        category = RefFilter.Modifiers,
    ))

    add(ApiEntry(
        name = "Modifier.canimationEmphasize",
        signature = "fun Modifier.canimationEmphasize(\n    active: Boolean,\n    preset: CanimationPreset = CanimationPreset.ScaleIn,\n): Modifier",
        description = "Emphasis animation that draws attention to interactive elements. Triggers when active becomes true. Use with Pulse, Tada, Wobble, etc.",
        category = RefFilter.Modifiers,
        badge = "ATTENTION",
        codeExample = """Modifier.canimationEmphasize(
    active = hasNotification,
    preset = CanimationPreset.Pulse,
)""",
    ))

    // ── Composable functions ──

    add(ApiEntry(
        name = "CanimationVisibility",
        signature = "@Composable\nfun CanimationVisibility(\n    visible: Boolean,\n    enterPreset: CanimationPreset = CanimationPreset.FadeUp,\n    exitPreset: CanimationPreset = enterPreset,\n    content: @Composable () -> Unit,\n)",
        description = "AnimatedVisibility wrapper with named presets. Wraps content that should appear/disappear with canimation transitions.",
        category = RefFilter.Composables,
        badge = "CLASSIC",
        codeExample = """CanimationVisibility(
    visible = showContent,
    enterPreset = CanimationPreset.BounceIn,
) {
    Text("Hello, Canimation!")
}""",
    ))

    add(ApiEntry(
        name = "CanimationVisibility (custom spec)",
        signature = "@Composable\nfun CanimationVisibility(\n    visible: Boolean,\n    enterFullSpec: CanimationSpec,\n    enterReducedSpec: CanimationSpec = enterFullSpec.toReduced(),\n    exitFullSpec: CanimationSpec = enterFullSpec.reversed(),\n    exitReducedSpec: CanimationSpec = enterReducedSpec.reversed(),\n    content: @Composable () -> Unit,\n)",
        description = "Custom-spec version of CanimationVisibility for full control over all animation directions and accessibility variants.",
        category = RefFilter.Composables,
    ))

    add(ApiEntry(
        name = "CanimationProvider",
        signature = "@Composable\nfun CanimationProvider(\n    tokens: CanimationTokens = CanimationTokens.Default,\n    policy: CanimationPolicy = CanimationPolicy.SystemAware,\n    presetRegistry: PresetRegistry = PresetRegistry.Default,\n    systemReducedMotion: Boolean? = null,\n    content: @Composable () -> Unit,\n)",
        description = "Scoped provider that sets the animation context for all child composables. Controls motion level (Full / Reduced / Off), tokens, and preset registry.",
        category = RefFilter.Composables,
        badge = "PROVIDER",
        codeExample = """// Always play full animations regardless of system setting
CanimationProvider(
    policy = CanimationPolicy.AlwaysFull,
) {
    // All canimation calls inside use Full motion
    Box(Modifier.canimation(visible, Canimation.Fade.Up)) {
        Text("Animated content")
    }
}""",
    ))

    // ── Data classes ──

    add(ApiEntry(
        name = "CanimationEffect",
        signature = "data class CanimationEffect(\n    val alpha: CanimationRange? = null,\n    val offsetX: CanimationDpRange? = null,\n    val offsetY: CanimationDpRange? = null,\n    val scale: CanimationRange? = null,\n    val rotation: CanimationRange? = null,\n    val durationMs: Int = 220,\n    val easing: Easing? = null,\n)",
        description = "Composable animation effect. All properties are optional — set only what you need. Effects can be combined with + operator; the right-hand side overrides null values from the left.",
        category = RefFilter.DataClasses,
        badge = "CORE",
    ))

    add(ApiEntry(
        name = "CanimationSpec",
        signature = "data class CanimationSpec(\n    val durationMs: Int,\n    val easing: Easing,\n    val alpha: CanimationRange? = null,\n    val offsetX: CanimationDpRange? = null,\n    val offsetY: CanimationDpRange? = null,\n    val scale: CanimationRange? = null,\n    val rotation: CanimationRange? = null,\n)",
        description = "Low-level animation specification with required duration and easing. Used by preset-based APIs. Has toReduced() and reversed() helpers.",
        category = RefFilter.DataClasses,
    ))

    add(ApiEntry(
        name = "CanimationPresetSpec",
        signature = "data class CanimationPresetSpec(\n    val fullEnter: CanimationSpec,\n    val fullExit: CanimationSpec,\n    val reducedEnter: CanimationSpec,\n    val reducedExit: CanimationSpec,\n)",
        description = "Complete specification for a named preset — defines all four directions (enter/exit × full/reduced). Used internally by CanimationPreset.",
        category = RefFilter.DataClasses,
    ))

    add(ApiEntry(
        name = "CanimationRange",
        signature = "data class CanimationRange(\n    val from: Float,\n    val to: Float,\n)",
        description = "A range of Float values for animatable properties (alpha, scale, rotation). from is the start value, to is the end value.",
        category = RefFilter.DataClasses,
    ))

    add(ApiEntry(
        name = "CanimationDpRange",
        signature = "data class CanimationDpRange(\n    val from: Dp,\n    val to: Dp,\n)",
        description = "A range of Dp values for offset properties (offsetX, offsetY). from is the start offset, to is the end offset.",
        category = RefFilter.DataClasses,
    ))

    add(ApiEntry(
        name = "CanimationContext",
        signature = "data class CanimationContext(\n    val tokens: CanimationTokens,\n    val level: CanimationLevel,\n    val presetRegistry: PresetRegistry,\n    val timeSourceMillis: () -> Long,\n)",
        description = "Runtime context providing tokens, motion level, and preset registry. Accessed via LocalCanimationContext.current inside the composition.",
        category = RefFilter.DataClasses,
    ))

    add(ApiEntry(
        name = "CanimationTokens",
        signature = "data class CanimationTokens(\n    val duration: DurationTokens,\n    val easing: EasingTokens,\n    val distance: DistanceTokens,\n    val spring: SpringTokens,\n)",
        description = "Design tokens for animation timing, easing curves, distances, and spring physics. Has a Default companion with sensible defaults.",
        category = RefFilter.DataClasses,
    ))

    // ── Enums & policies ──

    add(ApiEntry(
        name = "CanimationPreset",
        signature = "enum class CanimationPreset",
        description = "112 named animation presets. Covers fade, scale, slide, bounce, rotate, zoom, flip, spring, material motion, attention seekers, and more. Each maps to a CanimationPresetSpec.",
        category = RefFilter.Enums,
        badge = "112 ENTRIES",
        codeExample = """// Common presets
CanimationPreset.FadeUp       // Fade + slide up
CanimationPreset.BounceIn     // Bouncy scale entry
CanimationPreset.Pulse        // Scale pulse emphasis
CanimationPreset.SpringIn     // Spring overshoot
CanimationPreset.FadeThrough  // Material design crossfade""",
    ))

    add(ApiEntry(
        name = "CanimationPolicy",
        signature = "sealed interface CanimationPolicy",
        description = "Controls motion behavior in CanimationProvider. SystemAware respects OS reduced-motion setting. AlwaysFull / AlwaysReduced / AlwaysOff override it.",
        category = RefFilter.Enums,
        codeExample = """CanimationPolicy.SystemAware    // Default — respects OS setting
CanimationPolicy.AlwaysFull    // Force full animations
CanimationPolicy.AlwaysReduced // Force reduced motion
CanimationPolicy.AlwaysOff     // Disable all animations""",
    ))

    add(ApiEntry(
        name = "CanimationLevel",
        signature = "enum class CanimationLevel { Full, Reduced, Off }",
        description = "Resolved motion level. Full plays all animations, Reduced uses shortened/simplified versions, Off skips animations entirely.",
        category = RefFilter.Enums,
    ))

    add(ApiEntry(
        name = "AnimationDirection",
        signature = "enum class AnimationDirection { Enter, Exit }",
        description = "Indicates which direction an animation is playing. Used internally for spec resolution.",
        category = RefFilter.Enums,
    ))

    // ── Factory methods ──

    add(ApiEntry(
        name = "CanimationEffect.fade()",
        signature = "fun fade(\n    from: Float = 0f,\n    to: Float = 1f,\n): CanimationEffect",
        description = "Create a fade (opacity) effect. Default transitions from transparent (0) to opaque (1).",
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = "CanimationEffect.slideUp()",
        signature = "fun slideUp(\n    offset: Dp = 16.dp,\n): CanimationEffect",
        description = "Create a slide-up effect. Element enters from below by the given offset distance.",
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = "CanimationEffect.slideDown()",
        signature = "fun slideDown(\n    offset: Dp = 16.dp,\n): CanimationEffect",
        description = "Create a slide-down effect. Element enters from above.",
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = "CanimationEffect.slideLeft()",
        signature = "fun slideLeft(\n    offset: Dp = 24.dp,\n): CanimationEffect",
        description = "Create a slide-left effect. Element enters from the left side.",
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = "CanimationEffect.slideRight()",
        signature = "fun slideRight(\n    offset: Dp = 24.dp,\n): CanimationEffect",
        description = "Create a slide-right effect. Element enters from the right side.",
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = "CanimationEffect.scale()",
        signature = "fun scale(\n    from: Float = 0.92f,\n    to: Float = 1f,\n): CanimationEffect",
        description = "Create a scale effect. Default scales from 92% to 100%.",
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = "CanimationEffect.pop()",
        signature = "fun pop(\n    from: Float = 0.8f,\n    to: Float = 1f,\n): CanimationEffect",
        description = "Create a pop effect with overshoot feel. 300ms duration. From 80% to 100% by default.",
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = "CanimationEffect.rotate()",
        signature = "fun rotate(\n    fromDegrees: Float = -15f,\n    toDegrees: Float = 0f,\n): CanimationEffect",
        description = "Create a rotation effect. Default rotates from -15° to 0° (counter-clockwise entry).",
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = "CanimationEffect.spin()",
        signature = "fun spin(\n    fromDegrees: Float = -360f,\n): CanimationEffect",
        description = "Create a full 360° spin effect. 400ms duration.",
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = "CanimationEffect.zoom()",
        signature = "fun zoom(\n    from: Float = 0.5f,\n): CanimationEffect",
        description = "Create a zoom effect (scale + fade). From 50% scale to 100%. 300ms duration.",
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = "CanimationEffect.bounce()",
        signature = "fun bounce(): CanimationEffect",
        description = "Create a bounce entry (scale 0.3 → 1 + fade). 400ms duration.",
        category = RefFilter.Factories,
    ))

    add(ApiEntry(
        name = "CanimationEffect.duration()",
        signature = "fun duration(ms: Int): CanimationEffect",
        description = "Create an effect wrapper with custom duration. Combine with + to override another effect's duration.",
        category = RefFilter.Factories,
        codeExample = "Canimation.Fade.Up + CanimationEffect.duration(500)",
    ))

    add(ApiEntry(
        name = "CanimationEffect.easing()",
        signature = "fun easing(easing: Easing): CanimationEffect",
        description = "Create an effect wrapper with custom easing curve. Combine with + to override another effect's easing.",
        category = RefFilter.Factories,
        codeExample = "Canimation.Fade.Up + CanimationEffect.easing(LinearOutSlowInEasing)",
    ))

    addNamespaceEntries()
}

package com.segnities007.canimation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
//  Section model
// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

private enum class RefFilter(val label: String) {
    All("All"),
    Modifiers("Modifiers"),
    Composables("Composables"),
    DataClasses("Data Classes"),
    Namespace("Canimation.*"),
    Factories("Factories"),
    Enums("Enums & Policies"),
}

private data class ApiEntry(
    val name: String,
    val signature: String,
    val description: String,
    val category: RefFilter,
    val badge: String = "",
    val codeExample: String = "",
)

// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
//  API catalog
// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

private val apiEntries: List<ApiEntry> = buildList {

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

    // ── Namespace entries ──

    fun ns(obj: String, name: String, desc: String, effect: CanimationEffect) = add(ApiEntry(
        name = "Canimation.$obj.$name",
        signature = "val $name: CanimationEffect",
        description = desc,
        category = RefFilter.Namespace,
    ))

    // Fade
    ns("Fade", "In", "Simple fade in (0 → 1)", Canimation.Fade.In)
    ns("Fade", "Out", "Fade out (1 → 0)", Canimation.Fade.Out)
    ns("Fade", "Gentle", "Gentle long fade (600ms)", Canimation.Fade.Gentle)
    ns("Fade", "Quick", "Quick snap fade (120ms)", Canimation.Fade.Quick)
    ns("Fade", "Up", "Fade + slide up", Canimation.Fade.Up)
    ns("Fade", "Down", "Fade + slide down", Canimation.Fade.Down)
    ns("Fade", "Left", "Fade from left", Canimation.Fade.Left)
    ns("Fade", "Right", "Fade from right", Canimation.Fade.Right)
    ns("Fade", "UpBig", "Big upward slide + fade", Canimation.Fade.UpBig)
    ns("Fade", "DownBig", "Big downward slide + fade", Canimation.Fade.DownBig)
    ns("Fade", "LeftBig", "Big slide from left + fade", Canimation.Fade.LeftBig)
    ns("Fade", "RightBig", "Big slide from right + fade", Canimation.Fade.RightBig)
    ns("Fade", "TopLeft", "Diagonal top-left entry", Canimation.Fade.TopLeft)
    ns("Fade", "TopRight", "Diagonal top-right entry", Canimation.Fade.TopRight)
    ns("Fade", "BottomLeft", "Diagonal bottom-left entry", Canimation.Fade.BottomLeft)
    ns("Fade", "BottomRight", "Diagonal bottom-right entry", Canimation.Fade.BottomRight)
    ns("Fade", "Small", "Fade + slight scale-up", Canimation.Fade.Small)
    ns("Fade", "Big", "Fade + dramatic scale-up", Canimation.Fade.Big)

    // Scale
    ns("Scale", "In", "Scale in from 92%", Canimation.Scale.In)
    ns("Scale", "Up", "Scale down from 108%", Canimation.Scale.Up)
    ns("Scale", "Down", "Scale from 85%", Canimation.Scale.Down)
    ns("Scale", "Expand", "Expand from 0%", Canimation.Scale.Expand)
    ns("Scale", "Shrink", "Shrink from 200%", Canimation.Scale.Shrink)
    ns("Scale", "Subtle", "Tiny subtle scale (97%)", Canimation.Scale.Subtle)
    ns("Scale", "Pop", "Pop with overshoot", Canimation.Scale.Pop)
    ns("Scale", "FadeIn", "Scale in + fade", Canimation.Scale.FadeIn)
    ns("Scale", "UpFade", "Scale from below + fade", Canimation.Scale.UpFade)
    ns("Scale", "DownFade", "Scale from above + fade", Canimation.Scale.DownFade)

    // Slide
    ns("Slide", "Left", "Slide from right", Canimation.Slide.Left)
    ns("Slide", "Right", "Slide from left", Canimation.Slide.Right)
    ns("Slide", "Up", "Slide from below", Canimation.Slide.Up)
    ns("Slide", "Down", "Slide from above", Canimation.Slide.Down)
    ns("Slide", "LeftBig", "Big slide from right", Canimation.Slide.LeftBig)
    ns("Slide", "RightBig", "Big slide from left", Canimation.Slide.RightBig)
    ns("Slide", "UpBig", "Big slide from below", Canimation.Slide.UpBig)
    ns("Slide", "DownBig", "Big slide from above", Canimation.Slide.DownBig)
    ns("Slide", "UpSubtle", "Subtle slide from below (8dp)", Canimation.Slide.UpSubtle)
    ns("Slide", "DownSubtle", "Subtle slide from above (8dp)", Canimation.Slide.DownSubtle)
    ns("Slide", "LeftSubtle", "Subtle slide from right (8dp)", Canimation.Slide.LeftSubtle)
    ns("Slide", "RightSubtle", "Subtle slide from left (8dp)", Canimation.Slide.RightSubtle)

    // Rotate
    ns("Rotate", "In", "Counter-clockwise rotation (-15°)", Canimation.Rotate.In)
    ns("Rotate", "Clockwise", "Clockwise rotation (15°)", Canimation.Rotate.Clockwise)
    ns("Rotate", "Spin", "Full 360° spin", Canimation.Rotate.Spin)
    ns("Rotate", "Half", "Half turn (180°)", Canimation.Rotate.Half)
    ns("Rotate", "Quarter", "Quarter turn (90°)", Canimation.Rotate.Quarter)
    ns("Rotate", "FadeIn", "Rotate + fade", Canimation.Rotate.FadeIn)
    ns("Rotate", "ClockwiseFade", "Clockwise + fade", Canimation.Rotate.ClockwiseFade)
    ns("Rotate", "SpinFade", "Spin + fade", Canimation.Rotate.SpinFade)
    ns("Rotate", "ScaleIn", "Rotate + scale", Canimation.Rotate.ScaleIn)
    ns("Rotate", "DownLeft", "Rotate down-left + fade", Canimation.Rotate.DownLeft)
    ns("Rotate", "DownRight", "Rotate down-right + fade", Canimation.Rotate.DownRight)
    ns("Rotate", "UpLeft", "Rotate up-left + fade", Canimation.Rotate.UpLeft)
    ns("Rotate", "UpRight", "Rotate up-right + fade", Canimation.Rotate.UpRight)

    // Bounce
    ns("Bounce", "In", "Bouncy scale entry", Canimation.Bounce.In)
    ns("Bounce", "Down", "Bounce from top", Canimation.Bounce.Down)
    ns("Bounce", "Up", "Bounce from bottom", Canimation.Bounce.Up)
    ns("Bounce", "Left", "Bounce from left", Canimation.Bounce.Left)
    ns("Bounce", "Right", "Bounce from right", Canimation.Bounce.Right)

    // Spring
    ns("Spring", "In", "Spring overshoot scale", Canimation.Spring.In)
    ns("Spring", "Up", "Spring slide from below", Canimation.Spring.Up)
    ns("Spring", "Down", "Spring slide from above", Canimation.Spring.Down)
    ns("Spring", "Left", "Spring slide from left", Canimation.Spring.Left)
    ns("Spring", "Right", "Spring slide from right", Canimation.Spring.Right)
    ns("Spring", "Pop", "Spring pop (scale + fade)", Canimation.Spring.Pop)
    ns("Spring", "Bounce", "Spring bounce entry", Canimation.Spring.Bounce)

    // Flip
    ns("Flip", "In", "Half-rotation flip + fade", Canimation.Flip.In)
    ns("Flip", "Up", "Flip upward", Canimation.Flip.Up)
    ns("Flip", "Down", "Flip downward", Canimation.Flip.Down)
    ns("Flip", "X", "Horizontal flip (X-axis)", Canimation.Flip.X)
    ns("Flip", "Y", "Vertical flip (Y-axis)", Canimation.Flip.Y)

    // Zoom
    ns("Zoom", "In", "Zoom in from center", Canimation.Zoom.In)
    ns("Zoom", "Out", "Zoom out (large → normal)", Canimation.Zoom.Out)
    ns("Zoom", "InUp", "Zoom in from above", Canimation.Zoom.InUp)
    ns("Zoom", "InDown", "Zoom in from below", Canimation.Zoom.InDown)
    ns("Zoom", "InLeft", "Zoom in from left", Canimation.Zoom.InLeft)
    ns("Zoom", "InRight", "Zoom in from right", Canimation.Zoom.InRight)
    ns("Zoom", "Dramatic", "Dramatic zoom from tiny", Canimation.Zoom.Dramatic)

    // Attention
    ns("Attention", "Pulse", "Scale pulse", Canimation.Attention.Pulse)
    ns("Attention", "HeartBeat", "Heartbeat double-pulse", Canimation.Attention.HeartBeat)
    ns("Attention", "Tada", "Tada celebration + rotation", Canimation.Attention.Tada)
    ns("Attention", "Wobble", "Wobble side-to-side", Canimation.Attention.Wobble)
    ns("Attention", "Swing", "Swing pendulum", Canimation.Attention.Swing)
    ns("Attention", "RubberBand", "Rubber band stretch", Canimation.Attention.RubberBand)
    ns("Attention", "Jello", "Jello wiggle", Canimation.Attention.Jello)
    ns("Attention", "Flash", "Flash blink", Canimation.Attention.Flash)
    ns("Attention", "ShakeX", "Horizontal shake", Canimation.Attention.ShakeX)
    ns("Attention", "ShakeY", "Vertical shake", Canimation.Attention.ShakeY)
    ns("Attention", "HeadShake", "Head shake (subtle)", Canimation.Attention.HeadShake)
    ns("Attention", "Glow", "Gentle glow/throb", Canimation.Attention.Glow)
    ns("Attention", "Ring", "Ring/bell shake", Canimation.Attention.Ring)

    // Entrance
    ns("Entrance", "Elevate", "Elevate from below + scale", Canimation.Entrance.Elevate)
    ns("Entrance", "Drop", "Drop from above", Canimation.Entrance.Drop)
    ns("Entrance", "JackInTheBox", "Jack in the box spring", Canimation.Entrance.JackInTheBox)
    ns("Entrance", "RollIn", "Roll in from side", Canimation.Entrance.RollIn)
    ns("Entrance", "LightSpeedLeft", "Light speed from left", Canimation.Entrance.LightSpeedLeft)
    ns("Entrance", "LightSpeedRight", "Light speed from right", Canimation.Entrance.LightSpeedRight)
    ns("Entrance", "BackInUp", "Back ease from above", Canimation.Entrance.BackInUp)
    ns("Entrance", "BackInDown", "Back ease from below", Canimation.Entrance.BackInDown)
    ns("Entrance", "BackInLeft", "Back ease from left", Canimation.Entrance.BackInLeft)
    ns("Entrance", "BackInRight", "Back ease from right", Canimation.Entrance.BackInRight)
    ns("Entrance", "ShrinkIn", "Shrink from large", Canimation.Entrance.ShrinkIn)
    ns("Entrance", "TiltIn", "Tilt + scale entry", Canimation.Entrance.TiltIn)
    ns("Entrance", "Snap", "Snap appearance", Canimation.Entrance.Snap)
    ns("Entrance", "SwingIn", "Swing from hinge", Canimation.Entrance.SwingIn)
    ns("Entrance", "Unfold", "Unfold/expand", Canimation.Entrance.Unfold)
    ns("Entrance", "Rise", "Rise from below", Canimation.Entrance.Rise)
    ns("Entrance", "Emerge", "Emerge from center", Canimation.Entrance.Emerge)

    // Material
    ns("Material", "FadeThrough", "Material fade-through", Canimation.Material.FadeThrough)
    ns("Material", "SharedAxisX", "Shared axis horizontal", Canimation.Material.SharedAxisX)
    ns("Material", "SharedAxisY", "Shared axis vertical", Canimation.Material.SharedAxisY)
    ns("Material", "SharedAxisZ", "Shared axis depth", Canimation.Material.SharedAxisZ)
    ns("Material", "Emphasized", "Emphasized decelerate", Canimation.Material.Emphasized)
    ns("Material", "ContainerTransform", "Container transform hint", Canimation.Material.ContainerTransform)

    // Morph
    ns("Morph", "ScaleUp", "Scale morph from small", Canimation.Morph.ScaleUp)
    ns("Morph", "Expand", "Expand morph + offset", Canimation.Morph.Expand)
    ns("Morph", "Collapse", "Collapse morph", Canimation.Morph.Collapse)

    // Wave
    ns("Wave", "Gentle", "Gentle floating wave", Canimation.Wave.Gentle)
    ns("Wave", "Strong", "Strong wave with rotation", Canimation.Wave.Strong)
    ns("Wave", "Ripple", "Ripple expansion", Canimation.Wave.Ripple)
    ns("Wave", "Float", "Floating upward", Canimation.Wave.Float)
    ns("Wave", "Drift", "Diagonal drift", Canimation.Wave.Drift)

    // Glitch
    ns("Glitch", "In", "Digital glitch entry", Canimation.Glitch.In)
    ns("Glitch", "Shake", "Glitchy shake", Canimation.Glitch.Shake)
    ns("Glitch", "Flicker", "Flickering", Canimation.Glitch.Flicker)
    ns("Glitch", "Distort", "Distorted entry", Canimation.Glitch.Distort)

    // Elastic
    ns("Elastic", "In", "Elastic stretch entry", Canimation.Elastic.In)
    ns("Elastic", "Stretch", "Full elastic stretch", Canimation.Elastic.Stretch)
    ns("Elastic", "Squash", "Squash compression", Canimation.Elastic.Squash)
    ns("Elastic", "Snap", "Snappy elastic", Canimation.Elastic.Snap)
    ns("Elastic", "Wobble", "Wobbly elastic", Canimation.Elastic.Wobble)

    // Cinematic
    ns("Cinematic", "Curtain", "Curtain reveal", Canimation.Cinematic.Curtain)
    ns("Cinematic", "ZoomPan", "Camera zoom pan", Canimation.Cinematic.ZoomPan)
    ns("Cinematic", "Dolly", "Dolly zoom", Canimation.Cinematic.Dolly)
    ns("Cinematic", "Reveal", "Subtle reveal", Canimation.Cinematic.Reveal)
    ns("Cinematic", "FadeToBlack", "Fade to black", Canimation.Cinematic.FadeToBlack)
    ns("Cinematic", "Dramatic", "Dramatic entrance", Canimation.Cinematic.Dramatic)

    // Playful
    ns("Playful", "Wiggle", "Fun wiggle", Canimation.Playful.Wiggle)
    ns("Playful", "Hop", "Hop bounce", Canimation.Playful.Hop)
    ns("Playful", "Spin", "Playful spin", Canimation.Playful.Spin)
    ns("Playful", "Pop", "Quick playful pop", Canimation.Playful.Pop)
    ns("Playful", "Twirl", "Twirling entry", Canimation.Playful.Twirl)
    ns("Playful", "Boing", "Spring boing", Canimation.Playful.Boing)

    ns("Diagonal", "TopLeft", "Enter from top-left", Canimation.Diagonal.TopLeft)
    ns("Diagonal", "TopRight", "Enter from top-right", Canimation.Diagonal.TopRight)
    ns("Diagonal", "BottomLeft", "Enter from bottom-left", Canimation.Diagonal.BottomLeft)
    ns("Diagonal", "BottomRight", "Enter from bottom-right", Canimation.Diagonal.BottomRight)
    ns("Diagonal", "Subtle", "Subtle diagonal entry", Canimation.Diagonal.Subtle)

    ns("Shrink", "Out", "Shrink from oversized", Canimation.Shrink.Out)
    ns("Shrink", "Subtle", "Subtle shrink entry", Canimation.Shrink.Subtle)
    ns("Shrink", "Rotate", "Shrink with rotation", Canimation.Shrink.Rotate)
    ns("Shrink", "FadeDown", "Shrink and drift down", Canimation.Shrink.FadeDown)

    ns("Tilt", "Left", "Tilt from left", Canimation.Tilt.Left)
    ns("Tilt", "Right", "Tilt from right", Canimation.Tilt.Right)
    ns("Tilt", "Up", "Tilt from above", Canimation.Tilt.Up)
    ns("Tilt", "Down", "Tilt from below", Canimation.Tilt.Down)
    ns("Tilt", "Swing", "Swinging tilt", Canimation.Tilt.Swing)

    ns("Float", "Up", "Gentle float up", Canimation.Float.Up)
    ns("Float", "Down", "Gentle float down", Canimation.Float.Down)
    ns("Float", "Gentle", "Very gentle float", Canimation.Float.Gentle)
    ns("Float", "ScaleUp", "Float with scale", Canimation.Float.ScaleUp)

    ns("Drop", "In", "Standard drop entry", Canimation.Drop.In)
    ns("Drop", "Heavy", "Heavy slam drop", Canimation.Drop.Heavy)
    ns("Drop", "Light", "Light feather drop", Canimation.Drop.Light)
    ns("Drop", "Rotate", "Drop with rotation", Canimation.Drop.Rotate)

    ns("Rise", "In", "Standard rise entry", Canimation.Rise.In)
    ns("Rise", "Slow", "Slow rising entry", Canimation.Rise.Slow)
    ns("Rise", "Scale", "Rise with scale", Canimation.Rise.Scale)
    ns("Rise", "Rotate", "Rise with rotation", Canimation.Rise.Rotate)

    ns("Stretch", "Horizontal", "Horizontal stretch", Canimation.Stretch.Horizontal)
    ns("Stretch", "Vertical", "Vertical stretch", Canimation.Stretch.Vertical)
    ns("Stretch", "Both", "Stretch both axes", Canimation.Stretch.Both)
    ns("Stretch", "Snap", "Stretch with snap", Canimation.Stretch.Snap)

    // Stagger (these are constants, not effects)
    add(ApiEntry(
        name = "Canimation.Stagger",
        signature = "object Stagger {\n    const val Quick = 40     // ms\n    const val Normal = 70    // ms\n    const val Slow = 120     // ms\n    const val Relaxed = 200  // ms\n}",
        description = "Stagger delay constants for building delayed animation sequences. Use as delay multiplier for index-based staggering.",
        category = RefFilter.Namespace,
        badge = "UTILITY",
        codeExample = """items.forEachIndexed { i, item ->
    LaunchedEffect(Unit) {
        delay(i * Canimation.Stagger.Normal.toLong())
        visible = true
    }
    Box(Modifier.canimation(visible, Canimation.Fade.Up)) {
        Text(item)
    }
}""",
    ))
}

// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
//  Screen
// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ApiReferenceScreen(modifier: Modifier = Modifier) {
    var selectedFilter by remember { mutableStateOf(RefFilter.All) }
    var headerStage by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        for (i in 0..3) { headerStage = i; delay(60) }
    }

    val filtered = remember(selectedFilter) {
        if (selectedFilter == RefFilter.All) apiEntries
        else apiEntries.filter { it.category == selectedFilter }
    }

    val groupedByCategory = remember(filtered) {
        filtered.groupBy { it.category }
    }

    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
        LazyColumn(
            modifier = Modifier.widthIn(max = 960.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Header
            item(key = "header") {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "API REFERENCE",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.canimation(visible = headerStage >= 0, effect = Canimation.Fade.Up),
                    )
                    Text(
                        text = "Complete API documentation",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.canimation(visible = headerStage >= 1, effect = Canimation.Fade.Up),
                    )
                    Text(
                        text = "${apiEntries.size} API entries — Modifiers, Composables, Data Classes, Namespace effects, and more",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.canimation(visible = headerStage >= 2, effect = Canimation.Fade.Up),
                    )
                }
            }

            // Filters
            item(key = "filters") {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .canimation(visible = headerStage >= 3, effect = Canimation.Fade.Up),
                ) {
                    RefFilter.entries.forEach { filter ->
                        val count = if (filter == RefFilter.All) apiEntries.size
                        else apiEntries.count { it.category == filter }
                        FilterChip(
                            selected = selectedFilter == filter,
                            onClick = { selectedFilter = filter },
                            label = {
                                Text(
                                    text = "${filter.label} ($count)",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = if (selectedFilter == filter) FontWeight.Bold else FontWeight.Normal,
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            ),
                        )
                    }
                }
            }

            // Grouped entries
            groupedByCategory.forEach { (category, entries) ->
                item(key = "section-${category.name}") {
                    var visible by remember { mutableStateOf(false) }
                    LaunchedEffect(category.name) { visible = true }
                    Box(Modifier.canimation(visible = visible, effect = Canimation.Fade.Left)) {
                        Column {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = MaterialTheme.colorScheme.outlineVariant,
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(MaterialTheme.colorScheme.primary, CircleShape),
                                )
                                Text(
                                    text = category.label.uppercase(),
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.5.sp,
                                )
                                Text(
                                    text = "${entries.size}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                        }
                    }
                }

                if (category == RefFilter.Namespace) {
                    val subGroups = entries.groupBy { entry ->
                        entry.name.removePrefix("Canimation.").substringBefore(".")
                    }
                    subGroups.forEach { (subCategory, subEntries) ->
                        item(key = "sub-${category.name}-$subCategory") {
                            Text(
                                text = subCategory,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                            )
                        }
                        items(subEntries, key = { it.name }) { entry ->
                            var visible by remember { mutableStateOf(false) }
                            LaunchedEffect(entry.name) { visible = true }
                            Box(
                                Modifier.canimation(visible = visible, effect = Canimation.Fade.Up),
                            ) {
                                ApiEntryCard(entry)
                            }
                        }
                    }
                } else {
                items(entries, key = { it.name }) { entry ->
                    var visible by remember { mutableStateOf(false) }
                    LaunchedEffect(entry.name) { visible = true }
                    Box(
                        Modifier.canimation(visible = visible, effect = Canimation.Fade.Up),
                    ) {
                        ApiEntryCard(entry)
                    }
                }
                }
            }

            // Footer
            item(key = "footer") {
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { visible = true }
                Box(Modifier.canimation(visible = visible, effect = Canimation.Fade.In)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = "canimation — Compose Multiplatform Animation Library",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Text(
                            text = "io.github.canimation:canimation-core",
                            style = MaterialTheme.typography.labelSmall,
                            fontFamily = FontFamily.Monospace,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
//  Components
// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
private fun ApiEntryCard(entry: ApiEntry) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
            // Name + badge row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = entry.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f, fill = false),
                )
                if (entry.badge.isNotEmpty()) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                    ) {
                        Text(
                            text = entry.badge,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }

            // Signature
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = entry.signature,
                    modifier = Modifier.padding(12.dp),
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 18.sp,
                )
            }

            // Description
            Text(
                text = entry.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            // Code example
            if (entry.codeExample.isNotEmpty()) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.06f),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "EXAMPLE",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = entry.codeExample,
                            fontFamily = FontFamily.Monospace,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 18.sp,
                        )
                    }
                }
            }

            // Separator
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
    }
}

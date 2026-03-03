package com.segnities007.canimation.screen.examples

import io.github.canimation.core.CanimationPreset

data class ExampleCategory(
    val id: String,
    val title: String,
    val subtitle: String,
    val accentLabel: String,
    val examples: List<ExampleItem>,
)

/**
 * @param demoType "visibility" = CanimationVisibility enter/exit loop,
 *   "emphasis" = canimationEmphasize continuous loop,
 *   "stagger" = multiple items with staggered timing,
 *   "enterExit" = enter → hold → exit → pause cycle,
 *   "grid" = 2x2 grid of staggered items,
 *   "tap" = tap to toggle visibility,
 *   "tapEmphasis" = tap to trigger emphasis,
 *   "hover" = hover/pointer to trigger emphasis,
 *   "longPress" = long press to trigger emphasis,
 *   "toggle" = tap to switch between A/B states,
 *   "drag" = horizontal drag to reveal/hide,
 *   --- Standalone component animations (no CanimationPreset) ---
 *   "counter" / "numberTrend" / "typewriter" / "scramble" /
 *   "wavy" / "pulseDots" / "jumpingDots" / "shimmer" /
 *   "tabs" / "accordion" / "flipCard" / "colorMorph" /
 *   "progressRing" / "holdConfirm" / "splitReveal" /
 *   "staggerCenter" / "ticker" / "bouncyList" /
 *   "spinner" / "ripple" / "swipeActions" / "tiltCard" /
 *   "priceSwitcher" / "engagementStats" / "multiStateBadge"
 */
data class ExampleItem(
    val preset: CanimationPreset,
    val description: String,
    val codeSnippet: String,
    val demoType: String = "visibility",
    val title: String = preset.name,
)

// Helper for concise visibility examples
private fun vis(
    preset: CanimationPreset,
    desc: String,
    code: String = """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.${preset.name},
) { content() }""",
) = ExampleItem(preset, desc, code, "visibility")

private fun mod(
    preset: CanimationPreset,
    desc: String,
    code: String = """Modifier.canimationEnter(
    visible = isVisible,
    preset = CanimationPreset.${preset.name},
)""",
) = ExampleItem(preset, desc, code, "visibility")

private fun emp(
    preset: CanimationPreset,
    desc: String,
    code: String = """Modifier.canimationEmphasize(
    active = isActive,
    preset = CanimationPreset.${preset.name},
)""",
) = ExampleItem(preset, desc, code, "emphasis")

private fun stag(
    preset: CanimationPreset,
    desc: String,
) = ExampleItem(
    preset, desc,
    """// Staggered list with ${preset.name}
items.forEachIndexed { i, item ->
    Box(
        Modifier.canimationEnter(
            visible = isVisible,
            preset = CanimationPreset.${preset.name},
        )
    ) { ItemCard(item) }
    delay(80L * i)
}""",
    "stagger",
)

private fun enterExit(
    preset: CanimationPreset,
    desc: String,
) = ExampleItem(
    preset, desc,
    """CanimationVisibility(
    visible = isVisible,
    enterPreset = CanimationPreset.${preset.name},
    exitPreset = CanimationPreset.${preset.name},
) { content() }""",
    "enterExit",
)

private fun grid(
    preset: CanimationPreset,
    desc: String,
) = ExampleItem(
    preset, desc,
    """// 2×2 grid with staggered ${preset.name}
LazyVerticalGrid(columns = Fixed(2)) {
    items(4) { index ->
        CanimationVisibility(
            visible = visible,
            enterPreset = CanimationPreset.${preset.name},
        ) { GridCell() }
    }
}""",
    "grid",
)

private fun tap(
    preset: CanimationPreset,
    desc: String,
) = ExampleItem(
    preset, desc,
    """// Tap to toggle
var visible by remember { mutableStateOf(false) }
Box(Modifier.clickable { visible = !visible }) {
    CanimationVisibility(
        visible = visible,
        enterPreset = CanimationPreset.${preset.name},
    ) { content() }
}""",
    "tap",
)

private fun tapEmphasis(
    preset: CanimationPreset,
    desc: String,
) = ExampleItem(
    preset, desc,
    """// Tap to emphasize
var active by remember { mutableStateOf(false) }
Box(
    Modifier
        .clickable { active = !active }
        .canimationEmphasize(active, CanimationPreset.${preset.name})
) { content() }""",
    "tapEmphasis",
)

private fun hover(
    preset: CanimationPreset,
    desc: String,
) = ExampleItem(
    preset, desc,
    """// Hover to emphasize
var hovered by remember { mutableStateOf(false) }
Box(
    Modifier
        .onPointerEvent(PointerEventType.Enter) { hovered = true }
        .onPointerEvent(PointerEventType.Exit) { hovered = false }
        .canimationEmphasize(hovered, CanimationPreset.${preset.name})
) { content() }""",
    "hover",
)

private fun longPress(
    preset: CanimationPreset,
    desc: String,
) = ExampleItem(
    preset, desc,
    """// Long press to trigger
var active by remember { mutableStateOf(false) }
Box(
    Modifier.combinedClickable(
        onLongClick = { active = !active }
    ) { }
        .canimationEmphasize(active, CanimationPreset.${preset.name})
) { content() }""",
    "longPress",
)

private fun toggle(
    preset: CanimationPreset,
    desc: String,
) = ExampleItem(
    preset, desc,
    """// Tap to toggle between two states
var state by remember { mutableStateOf(false) }
Box(Modifier.clickable { state = !state }) {
    CanimationVisibility(visible = state,
        enterPreset = CanimationPreset.${preset.name},
        exitPreset = CanimationPreset.${preset.name},
    ) { ContentA() }
    CanimationVisibility(visible = !state,
        enterPreset = CanimationPreset.${preset.name},
        exitPreset = CanimationPreset.${preset.name},
    ) { ContentB() }
}""",
    "toggle",
)

private fun drag(
    preset: CanimationPreset,
    desc: String,
) = ExampleItem(
    preset, desc,
    """// Drag to reveal
var revealed by remember { mutableStateOf(false) }
Box(Modifier.draggable(
    state = rememberDraggableState { delta ->
        if (delta > 10f) revealed = true
        if (delta < -10f) revealed = false
    },
    orientation = Orientation.Horizontal,
)) {
    CanimationVisibility(
        visible = revealed,
        enterPreset = CanimationPreset.${preset.name},
    ) { content() }
}""",
    "drag",
)

// Helper for standalone component animations (no CanimationPreset needed)
private fun component(
    demoType: String,
    desc: String,
    code: String,
) = ExampleItem(
    preset = CanimationPreset.Fade, // unused placeholder
    description = desc,
    codeSnippet = code,
    demoType = demoType,
    title = desc,
)

val exampleCategories: List<ExampleCategory> = listOf(
    // ===== ENTRANCE PRESETS =====
    ExampleCategory(
        id = "fade",
        title = "Fading",
        subtitle = "Smooth opacity transitions with optional movement",
        accentLabel = "ENTRANCE",
        examples = listOf(
            vis(CanimationPreset.Fade, "Simple alpha crossfade"),
            vis(CanimationPreset.FadeUp, "Fade in with upward slide"),
            vis(CanimationPreset.FadeDown, "Fade in with downward slide"),
            vis(CanimationPreset.FadeInLeft, "Fade in from left"),
            vis(CanimationPreset.FadeInRight, "Fade in from right"),
            vis(CanimationPreset.FadeInLeftBig, "Big slide from left with fade"),
            vis(CanimationPreset.FadeInRightBig, "Big slide from right with fade"),
            vis(CanimationPreset.FadeInDownBig, "Big slide from top with fade"),
            vis(CanimationPreset.FadeInUpBig, "Big slide from bottom with fade"),
            vis(CanimationPreset.FadeInTopLeft, "Diagonal entry from top-left"),
            vis(CanimationPreset.FadeInTopRight, "Diagonal entry from top-right"),
            vis(CanimationPreset.FadeInBottomLeft, "Diagonal entry from bottom-left"),
            vis(CanimationPreset.FadeInBottomRight, "Diagonal entry from bottom-right"),
            vis(CanimationPreset.GentleFade, "Long, gentle 600ms fade"),
            vis(CanimationPreset.FadeThrough, "Material fade-through pattern"),
            vis(CanimationPreset.FadeSmall, "Fade + shrink entry"),
            vis(CanimationPreset.FadeBig, "Fade + grow entry"),
            vis(CanimationPreset.FadeUpLeft, "Diagonal fade from top-left"),
            vis(CanimationPreset.FadeDownRight, "Diagonal fade from bottom-right"),
        ),
    ),
    ExampleCategory(
        id = "scale",
        title = "Scaling",
        subtitle = "Grow, shrink, zoom, and pop animations",
        accentLabel = "ENTRANCE",
        examples = listOf(
            mod(CanimationPreset.ScaleIn, "Scale from 92%"),
            mod(CanimationPreset.ScaleUp, "Scale from 108%"),
            mod(CanimationPreset.ZoomIn, "Zoom from 50%"),
            mod(CanimationPreset.ZoomOut, "Zoom from 150%"),
            mod(CanimationPreset.Pop, "Overshoot pop"),
            mod(CanimationPreset.Expand, "Scale from 0% to 100%"),
            mod(CanimationPreset.ShrinkIn, "Shrink from 200%"),
            mod(CanimationPreset.ElevateIn, "Subtle rise + scale"),
            mod(CanimationPreset.ZoomInUp, "Zoom + upward"),
            mod(CanimationPreset.ZoomInDown, "Zoom + downward"),
            mod(CanimationPreset.ZoomInLeft, "Zoom + from left"),
            mod(CanimationPreset.ZoomInRight, "Zoom + from right"),
        ),
    ),
    ExampleCategory(
        id = "slide",
        title = "Sliding",
        subtitle = "Translate animations from any direction",
        accentLabel = "ENTRANCE",
        examples = listOf(
            mod(CanimationPreset.SlideLeft, "Slide from right"),
            mod(CanimationPreset.SlideRight, "Slide from left"),
            mod(CanimationPreset.SlideUp, "Long slide up"),
            mod(CanimationPreset.SlideDown, "Long slide down"),
            mod(CanimationPreset.SharedAxisX, "Shared axis horizontal"),
            mod(CanimationPreset.SharedAxisY, "Shared axis vertical"),
            mod(CanimationPreset.UpBig, "Big upward slide"),
            mod(CanimationPreset.DropIn, "Drop from above"),
        ),
    ),
    ExampleCategory(
        id = "bounce",
        title = "Bouncing",
        subtitle = "Bouncy entry animations from all directions",
        accentLabel = "ENTRANCE",
        examples = listOf(
            vis(CanimationPreset.BounceIn, "Bouncy scale entry"),
            vis(CanimationPreset.BounceInDown, "Bounce from top"),
            vis(CanimationPreset.BounceInUp, "Bounce from bottom"),
            vis(CanimationPreset.BounceInLeft, "Bounce from left"),
            vis(CanimationPreset.BounceInRight, "Bounce from right"),
        ),
    ),
    ExampleCategory(
        id = "rotate",
        title = "Rotating",
        subtitle = "Rotation-based entry animations",
        accentLabel = "ENTRANCE",
        examples = listOf(
            mod(CanimationPreset.RotateIn, "CCW rotation entry"),
            mod(CanimationPreset.RotateClockwise, "CW rotation entry"),
            mod(CanimationPreset.SpinIn, "Full 360° spin + scale"),
            mod(CanimationPreset.RotateInDownLeft, "Rotate from down-left"),
            mod(CanimationPreset.RotateInDownRight, "Rotate from down-right"),
            mod(CanimationPreset.RotateInUpLeft, "Rotate from up-left"),
            mod(CanimationPreset.RotateInUpRight, "Rotate from up-right"),
            mod(CanimationPreset.RotateScale, "Rotation + scale combo"),
        ),
    ),
    ExampleCategory(
        id = "flip",
        title = "Flipping",
        subtitle = "3D-style flip and tilt animations",
        accentLabel = "ENTRANCE",
        examples = listOf(
            mod(CanimationPreset.FlipIn, "Half-rotation flip entry"),
            mod(CanimationPreset.FlipInY, "Vertical flip (Y-axis)"),
            mod(CanimationPreset.FlipUp, "Flip + upward slide"),
            mod(CanimationPreset.FlipDown, "Flip + downward slide"),
            mod(CanimationPreset.TiltIn, "Slight tilt + scale entry"),
        ),
    ),
    ExampleCategory(
        id = "spring",
        title = "Spring Physics",
        subtitle = "Overshoot and spring-based animations",
        accentLabel = "PHYSICS",
        examples = listOf(
            mod(CanimationPreset.SpringIn, "Spring overshoot scale"),
            mod(CanimationPreset.SpringSlideUp, "Spring slide from below"),
            mod(CanimationPreset.SpringFadeIn, "Spring fade + subtle scale"),
            mod(CanimationPreset.BackInUp, "Back easing from bottom"),
            mod(CanimationPreset.BackInDown, "Back easing from top"),
            mod(CanimationPreset.BackInLeft, "Back easing from left"),
            mod(CanimationPreset.BackInRight, "Back easing from right"),
        ),
    ),
    ExampleCategory(
        id = "attention",
        title = "Attention Seekers",
        subtitle = "Emphasis and attention-grabbing animations",
        accentLabel = "EMPHASIS",
        examples = listOf(
            emp(CanimationPreset.Pulse, "Subtle scale pulse"),
            emp(CanimationPreset.HeartBeat, "Heartbeat-like scale pulse"),
            emp(CanimationPreset.Tada, "Rotation + scale emphasis"),
            emp(CanimationPreset.Wobble, "Side-to-side wobble"),
            emp(CanimationPreset.Swing, "Pendulum swing"),
            emp(CanimationPreset.RubberBand, "Rubber band stretch"),
            emp(CanimationPreset.Bounce, "Vertical bounce"),
            emp(CanimationPreset.Flash, "Quick opacity flash"),
            emp(CanimationPreset.ShakeX, "Horizontal shake"),
            emp(CanimationPreset.ShakeY, "Vertical shake"),
            emp(CanimationPreset.HeadShake, "Subtle head shake"),
            emp(CanimationPreset.Jello, "Jello wobble"),
        ),
    ),
    ExampleCategory(
        id = "special",
        title = "Specials",
        subtitle = "Unique and dramatic entry animations",
        accentLabel = "SPECIAL",
        examples = listOf(
            mod(CanimationPreset.JackInTheBox, "Scale + rotation combo"),
            mod(CanimationPreset.RollIn, "Roll + slide from left"),
            mod(CanimationPreset.LightSpeedInRight, "Fast slide + tilt from right"),
            mod(CanimationPreset.LightSpeedInLeft, "Fast slide + tilt from left"),
            mod(CanimationPreset.SwingIn, "Swing + slide entry"),
            mod(CanimationPreset.Snap, "Instant 10ms cut"),
            mod(CanimationPreset.EmphasizedEntry, "Material emphasized decelerate"),
        ),
    ),

    // ===== PATTERN-BASED EXAMPLES =====
    ExampleCategory(
        id = "enter-exit",
        title = "Enter & Exit",
        subtitle = "Paired enter → hold → exit animation cycles",
        accentLabel = "TRANSITION",
        examples = listOf(
            enterExit(CanimationPreset.Fade, "Simple fade in, fade out"),
            enterExit(CanimationPreset.FadeUp, "Slide up in, slide up out"),
            enterExit(CanimationPreset.FadeDown, "Slide down cycle"),
            enterExit(CanimationPreset.FadeInLeft, "Slide left cycle"),
            enterExit(CanimationPreset.FadeInRight, "Slide right cycle"),
            enterExit(CanimationPreset.ScaleIn, "Scale in, scale out"),
            enterExit(CanimationPreset.ZoomIn, "Zoom in/out"),
            enterExit(CanimationPreset.Pop, "Pop in, pop out"),
            enterExit(CanimationPreset.BounceIn, "Bouncy appear, bouncy disappear"),
            enterExit(CanimationPreset.FlipIn, "Flip in, flip out"),
            enterExit(CanimationPreset.RotateIn, "Rotate in/out"),
            enterExit(CanimationPreset.SpringIn, "Spring in, spring out"),
        ),
    ),
    ExampleCategory(
        id = "stagger-list",
        title = "Staggered Lists",
        subtitle = "Multiple items appearing with sequential delay",
        accentLabel = "PATTERN",
        examples = listOf(
            stag(CanimationPreset.FadeUp, "Classic list reveal — fade up"),
            stag(CanimationPreset.FadeInLeft, "Slide in from left, staggered"),
            stag(CanimationPreset.FadeInRight, "Slide in from right, staggered"),
            stag(CanimationPreset.ScaleIn, "Scale items one by one"),
            stag(CanimationPreset.SlideUp, "Slide up items sequentially"),
            stag(CanimationPreset.BounceIn, "Bouncy list reveal"),
            stag(CanimationPreset.SpringFadeIn, "Spring fade stagger"),
            stag(CanimationPreset.Pop, "Pop items one by one"),
            stag(CanimationPreset.FadeSmall, "Shrink-fade list reveal"),
            stag(CanimationPreset.ElevateIn, "Elevate items sequentially"),
        ),
    ),
    ExampleCategory(
        id = "grid-reveal",
        title = "Grid Reveal",
        subtitle = "2×2 grid items appearing with stagger",
        accentLabel = "PATTERN",
        examples = listOf(
            grid(CanimationPreset.FadeUp, "Grid fade up reveal"),
            grid(CanimationPreset.ScaleIn, "Grid scale-in reveal"),
            grid(CanimationPreset.ZoomIn, "Grid zoom reveal"),
            grid(CanimationPreset.Pop, "Grid pop reveal"),
            grid(CanimationPreset.BounceIn, "Grid bounce reveal"),
            grid(CanimationPreset.SpringIn, "Grid spring reveal"),
            grid(CanimationPreset.FlipIn, "Grid flip reveal"),
            grid(CanimationPreset.RotateIn, "Grid rotate reveal"),
            grid(CanimationPreset.FadeInBottomRight, "Grid diagonal reveal"),
            grid(CanimationPreset.Expand, "Grid expand reveal"),
        ),
    ),
    ExampleCategory(
        id = "hover-cards",
        title = "Card Hover Effects",
        subtitle = "Interactive emphasis animations on hover/tap",
        accentLabel = "INTERACTION",
        examples = listOf(
            emp(CanimationPreset.Pulse, "Card pulse on hover"),
            emp(CanimationPreset.HeartBeat, "Card heartbeat on hover"),
            emp(CanimationPreset.Tada, "Card tada on hover"),
            emp(CanimationPreset.Wobble, "Card wobble on hover"),
            emp(CanimationPreset.Swing, "Card swing on hover"),
            emp(CanimationPreset.RubberBand, "Card rubber band on hover"),
            emp(CanimationPreset.Bounce, "Card bounce on hover"),
            emp(CanimationPreset.ShakeX, "Card shake-x on hover"),
            emp(CanimationPreset.Jello, "Card jello on hover"),
            emp(CanimationPreset.HeadShake, "Card head-shake on hover"),
        ),
    ),
    ExampleCategory(
        id = "micro",
        title = "Micro-interactions",
        subtitle = "Quick, subtle animations for UI feedback",
        accentLabel = "UX",
        examples = listOf(
            mod(CanimationPreset.ScaleIn, "Button appear"),
            mod(CanimationPreset.Fade, "Tooltip fade-in"),
            mod(CanimationPreset.ElevateIn, "Card hover lift"),
            mod(CanimationPreset.FadeSmall, "Badge appear"),
            mod(CanimationPreset.Snap, "Instant state change"),
            mod(CanimationPreset.GentleFade, "Subtle content swap"),
            mod(CanimationPreset.FadeThrough, "Material content switch"),
            mod(CanimationPreset.SharedAxisX, "Tab content slide"),
            mod(CanimationPreset.SharedAxisY, "Vertical content swap"),
            mod(CanimationPreset.SpringFadeIn, "Notification badge pop"),
        ),
    ),
    ExampleCategory(
        id = "dramatic",
        title = "Dramatic Entrances",
        subtitle = "Big, flashy entrance animations",
        accentLabel = "SHOWTIME",
        examples = listOf(
            mod(CanimationPreset.JackInTheBox, "Surprise reveal"),
            mod(CanimationPreset.RollIn, "Rolling entrance"),
            mod(CanimationPreset.LightSpeedInRight, "Light-speed from right"),
            mod(CanimationPreset.LightSpeedInLeft, "Light-speed from left"),
            mod(CanimationPreset.BounceIn, "Big bouncy entrance"),
            mod(CanimationPreset.ZoomIn, "Cinematic zoom"),
            mod(CanimationPreset.SpinIn, "Spinning entrance"),
            mod(CanimationPreset.FlipIn, "Dramatic flip"),
            mod(CanimationPreset.Expand, "Explosive expand"),
            mod(CanimationPreset.ShrinkIn, "Dramatic shrink"),
        ),
    ),
    ExampleCategory(
        id = "material",
        title = "Material Motion",
        subtitle = "Google Material Design motion patterns",
        accentLabel = "MATERIAL",
        examples = listOf(
            mod(CanimationPreset.FadeThrough, "Fade through — container transform"),
            mod(CanimationPreset.SharedAxisX, "Shared axis X — horizontal navigation"),
            mod(CanimationPreset.SharedAxisY, "Shared axis Y — vertical navigation"),
            mod(CanimationPreset.EmphasizedEntry, "Emphasized — decelerate entry"),
            mod(CanimationPreset.ElevateIn, "Elevation — lift and appear"),
            mod(CanimationPreset.ScaleIn, "Container transform — scale variant"),
            mod(CanimationPreset.GentleFade, "Gentle fade — cross-dissolve"),
            mod(CanimationPreset.FadeUp, "Fade up — list item enter"),
        ),
    ),
    ExampleCategory(
        id = "directional",
        title = "Directional Suite",
        subtitle = "All 4 directions for each animation family",
        accentLabel = "DIRECTION",
        examples = listOf(
            mod(CanimationPreset.FadeUp, "Fade → Up"),
            mod(CanimationPreset.FadeDown, "Fade → Down"),
            mod(CanimationPreset.FadeInLeft, "Fade → Left"),
            mod(CanimationPreset.FadeInRight, "Fade → Right"),
            mod(CanimationPreset.SlideUp, "Slide → Up"),
            mod(CanimationPreset.SlideDown, "Slide → Down"),
            mod(CanimationPreset.SlideLeft, "Slide → Left"),
            mod(CanimationPreset.SlideRight, "Slide → Right"),
            mod(CanimationPreset.ZoomInUp, "Zoom → Up"),
            mod(CanimationPreset.ZoomInDown, "Zoom → Down"),
            mod(CanimationPreset.ZoomInLeft, "Zoom → Left"),
            mod(CanimationPreset.ZoomInRight, "Zoom → Right"),
            mod(CanimationPreset.BackInUp, "Back → Up"),
            mod(CanimationPreset.BackInDown, "Back → Down"),
            mod(CanimationPreset.BackInLeft, "Back → Left"),
            mod(CanimationPreset.BackInRight, "Back → Right"),
            mod(CanimationPreset.BounceInUp, "Bounce → Up"),
            mod(CanimationPreset.BounceInDown, "Bounce → Down"),
            mod(CanimationPreset.BounceInLeft, "Bounce → Left"),
            mod(CanimationPreset.BounceInRight, "Bounce → Right"),
        ),
    ),
    ExampleCategory(
        id = "diagonal",
        title = "Diagonal & Corner",
        subtitle = "Diagonal and corner-based entry animations",
        accentLabel = "DIRECTION",
        examples = listOf(
            vis(CanimationPreset.FadeInTopLeft, "Fade from top-left"),
            vis(CanimationPreset.FadeInTopRight, "Fade from top-right"),
            vis(CanimationPreset.FadeInBottomLeft, "Fade from bottom-left"),
            vis(CanimationPreset.FadeInBottomRight, "Fade from bottom-right"),
            vis(CanimationPreset.FadeUpLeft, "Fade up-left diagonal"),
            vis(CanimationPreset.FadeDownRight, "Fade down-right diagonal"),
            mod(CanimationPreset.RotateInDownLeft, "Rotate from down-left"),
            mod(CanimationPreset.RotateInDownRight, "Rotate from down-right"),
            mod(CanimationPreset.RotateInUpLeft, "Rotate from up-left"),
            mod(CanimationPreset.RotateInUpRight, "Rotate from up-right"),
        ),
    ),
    ExampleCategory(
        id = "big-moves",
        title = "Big Moves",
        subtitle = "Large distance and exaggerated transitions",
        accentLabel = "ENTRANCE",
        examples = listOf(
            vis(CanimationPreset.FadeInLeftBig, "Big slide from left"),
            vis(CanimationPreset.FadeInRightBig, "Big slide from right"),
            vis(CanimationPreset.FadeInDownBig, "Big slide from top"),
            vis(CanimationPreset.FadeInUpBig, "Big slide from bottom"),
            mod(CanimationPreset.UpBig, "Big upward translation"),
            mod(CanimationPreset.LightSpeedInRight, "Lightspeed from right"),
            mod(CanimationPreset.LightSpeedInLeft, "Lightspeed from left"),
            mod(CanimationPreset.RollIn, "Rolling from far left"),
        ),
    ),

    // ===== INTERACTIVE / GESTURE EXAMPLES =====
    ExampleCategory(
        id = "tap-toggle",
        title = "Tap to Show/Hide",
        subtitle = "Tap the demo area to toggle visibility",
        accentLabel = "TAP",
        examples = listOf(
            tap(CanimationPreset.Fade, "Tap to fade in/out"),
            tap(CanimationPreset.FadeUp, "Tap to fade up/down"),
            tap(CanimationPreset.ScaleIn, "Tap to scale in/out"),
            tap(CanimationPreset.ZoomIn, "Tap to zoom in/out"),
            tap(CanimationPreset.Pop, "Tap to pop in/out"),
            tap(CanimationPreset.SlideLeft, "Tap to slide left"),
            tap(CanimationPreset.SlideRight, "Tap to slide right"),
            tap(CanimationPreset.BounceIn, "Tap to bounce in/out"),
            tap(CanimationPreset.FlipIn, "Tap to flip in/out"),
            tap(CanimationPreset.SpringIn, "Tap to spring in/out"),
            tap(CanimationPreset.RotateIn, "Tap to rotate in/out"),
            tap(CanimationPreset.DropIn, "Tap to drop in/out"),
        ),
    ),
    ExampleCategory(
        id = "tap-emphasis",
        title = "Tap to Emphasize",
        subtitle = "Tap to trigger emphasis animation",
        accentLabel = "TAP",
        examples = listOf(
            tapEmphasis(CanimationPreset.Pulse, "Tap for pulse"),
            tapEmphasis(CanimationPreset.HeartBeat, "Tap for heartbeat"),
            tapEmphasis(CanimationPreset.Tada, "Tap for tada"),
            tapEmphasis(CanimationPreset.Wobble, "Tap for wobble"),
            tapEmphasis(CanimationPreset.Swing, "Tap for swing"),
            tapEmphasis(CanimationPreset.RubberBand, "Tap for rubber band"),
            tapEmphasis(CanimationPreset.Bounce, "Tap for bounce"),
            tapEmphasis(CanimationPreset.Flash, "Tap for flash"),
            tapEmphasis(CanimationPreset.ShakeX, "Tap for shake-x"),
            tapEmphasis(CanimationPreset.ShakeY, "Tap for shake-y"),
            tapEmphasis(CanimationPreset.Jello, "Tap for jello"),
            tapEmphasis(CanimationPreset.HeadShake, "Tap for head shake"),
        ),
    ),
    ExampleCategory(
        id = "hover-interactive",
        title = "Hover Effects",
        subtitle = "Hover/pointer over to trigger emphasis",
        accentLabel = "HOVER",
        examples = listOf(
            hover(CanimationPreset.Pulse, "Hover pulse"),
            hover(CanimationPreset.HeartBeat, "Hover heartbeat"),
            hover(CanimationPreset.Tada, "Hover tada"),
            hover(CanimationPreset.Wobble, "Hover wobble"),
            hover(CanimationPreset.Swing, "Hover swing"),
            hover(CanimationPreset.RubberBand, "Hover rubber band"),
            hover(CanimationPreset.Bounce, "Hover bounce"),
            hover(CanimationPreset.ShakeX, "Hover shake-x"),
            hover(CanimationPreset.Jello, "Hover jello"),
            hover(CanimationPreset.ScaleIn, "Hover scale"),
        ),
    ),
    ExampleCategory(
        id = "long-press",
        title = "Long Press",
        subtitle = "Long press to trigger animation",
        accentLabel = "PRESS",
        examples = listOf(
            longPress(CanimationPreset.Pulse, "Long press pulse"),
            longPress(CanimationPreset.HeartBeat, "Long press heartbeat"),
            longPress(CanimationPreset.Tada, "Long press tada"),
            longPress(CanimationPreset.RubberBand, "Long press rubber band"),
            longPress(CanimationPreset.Bounce, "Long press bounce"),
            longPress(CanimationPreset.ShakeX, "Long press shake-x"),
            longPress(CanimationPreset.ShakeY, "Long press shake-y"),
            longPress(CanimationPreset.Wobble, "Long press wobble"),
        ),
    ),
    ExampleCategory(
        id = "toggle-state",
        title = "State Toggle",
        subtitle = "Tap to switch between two animated states",
        accentLabel = "TOGGLE",
        examples = listOf(
            toggle(CanimationPreset.Fade, "Fade crossfade toggle"),
            toggle(CanimationPreset.FadeUp, "Slide-up toggle"),
            toggle(CanimationPreset.FadeDown, "Slide-down toggle"),
            toggle(CanimationPreset.ScaleIn, "Scale toggle"),
            toggle(CanimationPreset.ZoomIn, "Zoom toggle"),
            toggle(CanimationPreset.FlipIn, "Flip toggle"),
            toggle(CanimationPreset.RotateIn, "Rotate toggle"),
            toggle(CanimationPreset.SpringIn, "Spring toggle"),
            toggle(CanimationPreset.FadeThrough, "Material fade-through toggle"),
            toggle(CanimationPreset.SharedAxisX, "Shared-axis X toggle"),
        ),
    ),
    ExampleCategory(
        id = "drag-reveal",
        title = "Drag & Swipe",
        subtitle = "Drag horizontally to reveal/hide content",
        accentLabel = "DRAG",
        examples = listOf(
            drag(CanimationPreset.Fade, "Swipe to fade"),
            drag(CanimationPreset.FadeInLeft, "Swipe to slide from left"),
            drag(CanimationPreset.FadeInRight, "Swipe to slide from right"),
            drag(CanimationPreset.ScaleIn, "Swipe to scale"),
            drag(CanimationPreset.ZoomIn, "Swipe to zoom"),
            drag(CanimationPreset.Pop, "Swipe to pop"),
            drag(CanimationPreset.BounceIn, "Swipe to bounce"),
            drag(CanimationPreset.SpringIn, "Swipe to spring"),
        ),
    ),

    // ===== STANDALONE COMPONENT ANIMATIONS =====
    // These are unique animation patterns beyond enter/exit/emphasis presets.
    // Inspired by Motion.dev examples, Animate.css, and modern web animation patterns.

    ExampleCategory(
        id = "text-counter",
        title = "Number Counter",
        subtitle = "Animated counting with eased interpolation",
        accentLabel = "TEXT",
        examples = listOf(
            component("counter", "Counting up to 1234 with easing",
                """// Animated counter
var current by remember { mutableIntStateOf(0) }
LaunchedEffect(Unit) {
    for (i in 1..60) {
        val eased = FastOutSlowInEasing.transform(i / 60f)
        current = (1234 * eased).roundToInt()
        delay(33)
    }
}
Text(current.toString())"""),
        ),
    ),
    ExampleCategory(
        id = "text-trend",
        title = "Number Trend",
        subtitle = "Live value with up/down color indicators",
        accentLabel = "TEXT",
        examples = listOf(
            component("numberTrend", "Value trending up and down with color",
                """// Number trend with color
val color by animateColorAsState(
    if (diff > 0) Color.Green else Color.Red,
    tween(400)
)
Text(value.toString(), color = color)"""),
        ),
    ),
    ExampleCategory(
        id = "text-typewriter",
        title = "Typewriter",
        subtitle = "Character-by-character text reveal with cursor",
        accentLabel = "TEXT",
        examples = listOf(
            component("typewriter", "Text appearing one character at a time",
                """// Typewriter effect
var count by remember { mutableIntStateOf(0) }
LaunchedEffect(Unit) {
    for (i in 1..text.length) {
        count = i; delay(80)
    }
}
Text(text.take(count) + "▌")"""),
        ),
    ),
    ExampleCategory(
        id = "text-scramble",
        title = "Scramble Text",
        subtitle = "Random characters resolving to final text",
        accentLabel = "TEXT",
        examples = listOf(
            component("scramble", "Characters scramble then resolve left to right",
                """// Scramble text
repeat(3) {
    display = resolved + randomChars(remaining)
    delay(40)
}
resolvedCount++"""),
        ),
    ),
    ExampleCategory(
        id = "text-wavy",
        title = "Wavy Text",
        subtitle = "Sine-wave motion on each character",
        accentLabel = "TEXT",
        examples = listOf(
            component("wavy", "Characters oscillating in a sine wave",
                """// Wavy text
val phase by infiniteTransition.animateFloat(0f, 2π)
text.forEachIndexed { i, ch ->
    val y = sin(phase + i * 0.5f) * 8f
    Text(ch, Modifier.offset { IntOffset(0, -y.roundToInt()) })
}"""),
        ),
    ),
    ExampleCategory(
        id = "text-split",
        title = "Split Text Reveal",
        subtitle = "Words appearing one by one with spring physics",
        accentLabel = "TEXT",
        examples = listOf(
            component("splitReveal", "Words fade in sequentially from below",
                """// Split text reveal
words.forEachIndexed { i, word ->
    val alpha by animateFloatAsState(if (i < count) 1f else 0f)
    val y by animateFloatAsState(if (i < count) 0f else 20f, spring())
    Text(word, Modifier.graphicsLayer { alpha; translationY = y })
}"""),
        ),
    ),
    ExampleCategory(
        id = "text-price",
        title = "Price Switcher",
        subtitle = "Animated price toggle between monthly/yearly",
        accentLabel = "TEXT",
        examples = listOf(
            component("priceSwitcher", "Price animates between $9.99/mo and $99.99/yr",
                """// Price switcher
val anim = remember { Animatable(9.99f) }
LaunchedEffect(isMonthly) {
    anim.animateTo(if (isMonthly) 9.99f else 99.99f, spring())
}
Text("${'$'}%.2f".format(anim.value))"""),
        ),
    ),
    ExampleCategory(
        id = "text-engagement",
        title = "Engagement Stats",
        subtitle = "Staggered animated counters (views, likes, shares)",
        accentLabel = "TEXT",
        examples = listOf(
            component("engagementStats", "Multiple stats counting up with stagger delay",
                """// Engagement stats
stats.forEachIndexed { i, (label, target) ->
    LaunchedEffect(Unit) {
        delay(i * 200L)
        for (step in 1..40) {
            current = (target * eased(step/40f)).roundToInt()
            delay(30)
        }
    }
    Text("12.8K")
}"""),
        ),
    ),
    ExampleCategory(
        id = "loading-dots",
        title = "Pulse Dots",
        subtitle = "Loading dots with staggered scale pulsing",
        accentLabel = "LOADING",
        examples = listOf(
            component("pulseDots", "Three dots pulsing with staggered timing",
                """// Pulse loading dots
repeat(3) { i ->
    val scale by infiniteTransition.animateFloat(
        0.5f, 1.2f,
        infiniteRepeatable(tween(600, delayMillis = i * 200), Reverse)
    )
    Box(Modifier.size(12.dp).scale(scale).clip(CircleShape).background(primary))
}"""),
        ),
    ),
    ExampleCategory(
        id = "loading-jumping",
        title = "Jumping Dots",
        subtitle = "Dots bouncing up and down in sequence",
        accentLabel = "LOADING",
        examples = listOf(
            component("jumpingDots", "Dots with staggered vertical bounce",
                """// Jumping dots
repeat(3) { i ->
    val y by infiniteTransition.animateFloat(
        0f, -16f,
        infiniteRepeatable(tween(400, delayMillis = i * 150), Reverse)
    )
    Box(Modifier.offset { IntOffset(0, y.roundToInt()) }.clip(CircleShape))
}"""),
        ),
    ),
    ExampleCategory(
        id = "loading-shimmer",
        title = "Shimmer / Skeleton",
        subtitle = "Skeleton loading with animated gradient sweep",
        accentLabel = "LOADING",
        examples = listOf(
            component("shimmer", "Gradient sweeps across skeleton placeholder",
                """// Shimmer effect
val offset by infiniteTransition.animateFloat(-1f, 2f,
    infiniteRepeatable(tween(1500, easing = LinearEasing)))
Box(Modifier.background(Brush.horizontalGradient(
    listOf(base, shimmer, base),
    startX = offset * 300f,
    endX = (offset + 1f) * 300f,
)))"""),
        ),
    ),
    ExampleCategory(
        id = "loading-spinner",
        title = "Loading Spinner",
        subtitle = "Rotating arc spinner with continuous animation",
        accentLabel = "LOADING",
        examples = listOf(
            component("spinner", "Arc spinner with smooth rotation",
                """// Spinner
val rotation by infiniteTransition.animateFloat(0f, 360f,
    infiniteRepeatable(tween(1000, easing = LinearEasing)))
Canvas(Modifier.size(48.dp).graphicsLayer { rotationZ = rotation }) {
    drawArc(color, 0f, 90f, false, style = Stroke(4.dp.toPx()))
}"""),
        ),
    ),
    ExampleCategory(
        id = "loading-ripple",
        title = "Loading Ripple",
        subtitle = "Expanding concentric circles fading out",
        accentLabel = "LOADING",
        examples = listOf(
            component("ripple", "Three circles expanding and fading with stagger",
                """// Ripple loading
repeat(3) { i ->
    val scale by infiniteTransition.animateFloat(0.3f, 1.5f,
        infiniteRepeatable(tween(1500, delayMillis = i * 500)))
    val alpha by infiniteTransition.animateFloat(0.6f, 0f, ...)
    Box(Modifier.scale(scale).alpha(alpha).clip(CircleShape).background(primary))
}"""),
        ),
    ),
    ExampleCategory(
        id = "loading-progress",
        title = "Progress Ring",
        subtitle = "Circular progress indicator filling up",
        accentLabel = "LOADING",
        examples = listOf(
            component("progressRing", "Circular ring with percentage counting",
                """// Progress ring
Canvas(Modifier.size(64.dp)) {
    drawArc(gray, 0f, 360f, false, style = Stroke(6.dp.toPx()))
    drawArc(primary, -90f, 360f * progress, false,
        style = Stroke(6.dp.toPx(), cap = StrokeCap.Round))
}
Text("${'$'}{(progress * 100).roundToInt()}%")"""),
        ),
    ),
    ExampleCategory(
        id = "interactive-tabs",
        title = "Animated Tabs",
        subtitle = "Smooth tab selection with spring indicator",
        accentLabel = "INTERACTION",
        examples = listOf(
            component("tabs", "Tab indicator transitions with spring physics",
                """// Animated tabs
val bgAlpha by animateFloatAsState(
    if (selected) 1f else 0f,
    spring(stiffness = StiffnessLow)
)
Surface(color = primary.copy(alpha = bgAlpha * 0.15f)) {
    Text(label)
}"""),
        ),
    ),
    ExampleCategory(
        id = "interactive-accordion",
        title = "Accordion",
        subtitle = "Expandable/collapsible sections with animation",
        accentLabel = "INTERACTION",
        examples = listOf(
            component("accordion", "FAQ-style expand/collapse with spring",
                """// Accordion
val contentHeight by animateFloatAsState(
    if (expanded) 1f else 0f,
    spring(stiffness = StiffnessMediumLow)
)
Column {
    Text(question)
    if (contentHeight > 0.01f)
        Text(answer, Modifier.graphicsLayer { scaleY = contentHeight })
}"""),
        ),
    ),
    ExampleCategory(
        id = "interactive-flip",
        title = "Flip Card",
        subtitle = "3D card flip with front/back sides",
        accentLabel = "3D",
        examples = listOf(
            component("flipCard", "Card rotates 180° to reveal back side",
                """// 3D flip card
val rotationY by animateFloatAsState(
    if (flipped) 180f else 0f,
    spring(stiffness = StiffnessLow)
)
Box(Modifier.graphicsLayer {
    this.rotationY = rotationY
    cameraDistance = 12f * density
}) {
    Surface { Text(if (rotationY <= 90f) "Front" else "Back") }
}"""),
        ),
    ),
    ExampleCategory(
        id = "interactive-color",
        title = "Color Morph",
        subtitle = "Smooth color transitions between states",
        accentLabel = "INTERACTION",
        examples = listOf(
            component("colorMorph", "Background cycles through colors with smooth transition",
                """// Color morph
val color by animateColorAsState(
    colors[index], tween(800)
)
Box(Modifier.background(color).clip(RoundedCornerShape(16.dp)))"""),
        ),
    ),
    ExampleCategory(
        id = "interactive-hold",
        title = "Hold to Confirm",
        subtitle = "Press-and-hold progress confirmation",
        accentLabel = "INTERACTION",
        examples = listOf(
            component("holdConfirm", "Progress bar fills while button is held",
                """// Hold to confirm
Box(Modifier.pointerInput(Unit) {
    detectTapGestures(onLongPress = { confirmed = true })
}) {
    Box(Modifier.fillMaxWidth(progress).background(green))
    Text(if (confirmed) "✓ Confirmed" else "Hold to confirm")
}"""),
        ),
    ),
    ExampleCategory(
        id = "interactive-swipe",
        title = "Swipe Actions",
        subtitle = "Swipe to reveal action behind list item",
        accentLabel = "INTERACTION",
        examples = listOf(
            component("swipeActions", "Item slides right to reveal action underneath",
                """// Swipe actions
val offsetX by animateFloatAsState(
    if (swiped) 80f else 0f,
    spring(stiffness = StiffnessMediumLow)
)
Box {
    Box { Text("✓ Done") } // action behind
    Surface(Modifier.graphicsLayer { translationX = offsetX }) {
        Text("Swipe me →")
    }
}"""),
        ),
    ),
    ExampleCategory(
        id = "interactive-tilt",
        title = "Tilt Card",
        subtitle = "3D perspective tilt effect",
        accentLabel = "3D",
        examples = listOf(
            component("tiltCard", "Card tilts in 3D with continuous motion",
                """// 3D tilt card
val rotX by infiniteTransition.animateFloat(-8f, 8f,
    infiniteRepeatable(tween(3000), Reverse))
val rotY by infiniteTransition.animateFloat(8f, -8f,
    infiniteRepeatable(tween(4000), Reverse))
Surface(Modifier.graphicsLayer {
    rotationX = rotX; rotationY = rotY
    cameraDistance = 12f * density
})"""),
        ),
    ),
    ExampleCategory(
        id = "stagger-center",
        title = "Stagger From Center",
        subtitle = "Items reveal outward from the center",
        accentLabel = "PATTERN",
        examples = listOf(
            component("staggerCenter", "Bars appear from center outward with spring bounce",
                """// Stagger from center
val center = count / 2
repeat(count) { i ->
    val dist = abs(i - center)
    LaunchedEffect(visible) {
        if (visible) { delay(dist * 100L); itemVisible = true }
    }
    Box(Modifier.scale(animateFloat(if (itemVisible) 1f else 0f, spring())))
}"""),
        ),
    ),
    ExampleCategory(
        id = "ticker-marquee",
        title = "Ticker / Marquee",
        subtitle = "Continuous horizontal scrolling text",
        accentLabel = "PATTERN",
        examples = listOf(
            component("ticker", "Text scrolls infinitely from right to left",
                """// Ticker marquee
val offset by infiniteTransition.animateFloat(0f, -1f,
    infiniteRepeatable(tween(8000, easing = LinearEasing)))
Text(repeatedText, Modifier.offset {
    IntOffset((offset * totalWidth).roundToInt(), 0)
})"""),
        ),
    ),
    ExampleCategory(
        id = "bouncy-list",
        title = "Bouncy Spring List",
        subtitle = "List items slide in with spring physics bounce",
        accentLabel = "PATTERN",
        examples = listOf(
            component("bouncyList", "Items spring in from the left with stagger",
                """// Bouncy spring list
items.forEachIndexed { i, label ->
    val x by animateFloatAsState(
        if (visible) 0f else -200f,
        spring(DampingRatioMediumBouncy, StiffnessLow)
    )
    Surface(Modifier.graphicsLayer { translationX = x }) {
        Text(label)
    }
}"""),
        ),
    ),
    ExampleCategory(
        id = "badge-state",
        title = "Multi-state Badge",
        subtitle = "Badge transitions between states with bounce",
        accentLabel = "PATTERN",
        examples = listOf(
            component("multiStateBadge", "Badge color and label change with spring scale",
                """// Multi-state badge
val color by animateColorAsState(states[i].color, tween(400))
val scale = remember { Animatable(1f) }
LaunchedEffect(stateIndex) {
    scale.animateTo(1.2f, spring(stiffness = StiffnessHigh))
    scale.animateTo(1f, spring(dampingRatio = DampingRatioMediumBouncy))
}
Surface(color = color, Modifier.scale(scale.value)) { Text(label) }"""),
        ),
    ),

    // ─── New standalone animations (batch 2) ───
    ExampleCategory(
        id = "shape-morph",
        title = "Morphing Shapes",
        subtitle = "Shape morphs between circle and rounded square",
        accentLabel = "SHAPE",
        examples = listOf(
            component("morphShapes", "Corner radius animates from circle to square and back",
                """// Morphing shape
val progress by infiniteTransition.animateFloat(0f, 1f)
val radius = lerp(50f, 10f, progress)
Box(Modifier.clip(RoundedCornerShape(radius.percent)))"""),
        ),
    ),
    ExampleCategory(
        id = "gradient-shift",
        title = "Gradient Shift",
        subtitle = "Animated gradient cycling through theme colors",
        accentLabel = "COLOR",
        examples = listOf(
            component("gradientShift", "Horizontal gradient shifts through primary/secondary/tertiary",
                """// Gradient shift
val offset by infiniteTransition.animateFloat(0f, 1f)
Box(Modifier.background(Brush.horizontalGradient(
    colors, startX = -width * offset
)))"""),
        ),
    ),
    ExampleCategory(
        id = "skeleton-loader",
        title = "Skeleton Loader",
        subtitle = "Placeholder loading with shimmer sweep",
        accentLabel = "LOADING",
        examples = listOf(
            component("skeletonLoader", "Skeleton bars with translating gradient shimmer",
                """// Skeleton loader
val offset by infiniteTransition.animateFloat(-1f, 2f)
Box(Modifier.background(Brush.horizontalGradient(
    colors, startX = offset * width
)))"""),
        ),
    ),
    ExampleCategory(
        id = "elastic-pull",
        title = "Elastic Pull",
        subtitle = "Circle stretches and squashes with spring physics",
        accentLabel = "PHYSICS",
        examples = listOf(
            component("elasticPull", "scaleX/scaleY alternate with spring animation",
                """// Elastic pull
val sx by animateFloatAsState(if (phase) 1.3f else 0.8f, spring())
val sy by animateFloatAsState(if (phase) 0.8f else 1.3f, spring())
Box(Modifier.graphicsLayer { scaleX = sx; scaleY = sy })"""),
        ),
    ),
    ExampleCategory(
        id = "parallax-layers",
        title = "Parallax Layers",
        subtitle = "Layered elements moving at different speeds",
        accentLabel = "SCROLL",
        examples = listOf(
            component("parallaxLayers", "Three layers with different parallax speed factors",
                """// Parallax layers
val phase by infiniteTransition.animateFloat(0f, 2π)
layers.forEachIndexed { i, color ->
    Box(Modifier.graphicsLayer {
        translationX = sin(phase) * speed[i]
    })
}"""),
        ),
    ),
    ExampleCategory(
        id = "orbit-animation",
        title = "Orbit Animation",
        subtitle = "Elements orbiting around a center point",
        accentLabel = "PATTERN",
        examples = listOf(
            component("orbitAnim", "Small circles orbit using sin/cos with animated phase",
                """// Orbit animation
val angle by infiniteTransition.animateFloat(0f, 360f)
val x = cos(angle.toRadians()) * radius
val y = sin(angle.toRadians()) * radius
Box(Modifier.offset { IntOffset(x, y) })"""),
        ),
    ),
    ExampleCategory(
        id = "breathing-glow",
        title = "Breathing Glow",
        subtitle = "Pulsating glow with sine-based interpolation",
        accentLabel = "EFFECT",
        examples = listOf(
            component("breathingGlow", "Circle pulsates in scale and alpha like breathing",
                """// Breathing glow
val phase by infiniteTransition.animateFloat(0f, 2π)
val scale = 0.8f + sin(phase) * 0.2f
val alpha = 0.5f + sin(phase) * 0.3f
Box(Modifier.graphicsLayer { scaleX = scale; alpha = alpha })"""),
        ),
    ),
    ExampleCategory(
        id = "path-tracer",
        title = "Path Tracer",
        subtitle = "Arc drawing from 0° to 360° in a loop",
        accentLabel = "CANVAS",
        examples = listOf(
            component("pathTracer", "Canvas drawArc sweeps from 0° to 360° and resets",
                """// Path tracer
val sweep by infiniteTransition.animateFloat(0f, 360f)
Canvas(Modifier) {
    drawArc(color, startAngle = -90f, sweepAngle = sweep)
}"""),
        ),
    ),
    ExampleCategory(
        id = "text-gradient",
        title = "Text Gradient Animation",
        subtitle = "Text with animated horizontal gradient",
        accentLabel = "TEXT",
        examples = listOf(
            component("textGradient", "Gradient brush shifts horizontally across text",
                """// Text gradient
val offset by infiniteTransition.animateFloat(-width, width)
Text(text, style = style.copy(
    brush = Brush.horizontalGradient(colors, startX = offset)
))"""),
        ),
    ),
    ExampleCategory(
        id = "card-shuffle",
        title = "Card Shuffle",
        subtitle = "Overlapping cards dealing/shuffling in a cycle",
        accentLabel = "INTERACTION",
        examples = listOf(
            component("cardShuffle", "Cards animate translationX and rotationZ in sequence",
                """// Card shuffle
val tx by animateFloatAsState(targets[phase].x)
val rz by animateFloatAsState(targets[phase].rotation)
Card(Modifier.graphicsLayer { translationX = tx; rotationZ = rz })"""),
        ),
    ),
    ExampleCategory(
        id = "confetti",
        title = "Confetti Explosion",
        subtitle = "Colored particles burst outward and fade",
        accentLabel = "EFFECT",
        examples = listOf(
            component("confetti", "Particles animate translationX/Y, rotation, and alpha",
                """// Confetti
particles.forEach { p ->
    Box(Modifier.graphicsLayer {
        translationX = p.x.value; translationY = p.y.value
        rotationZ = p.rot.value; alpha = p.alpha.value
    })
}"""),
        ),
    ),
    ExampleCategory(
        id = "wave-effect",
        title = "Wave Effect",
        subtitle = "Bars oscillating with phase offset",
        accentLabel = "PATTERN",
        examples = listOf(
            component("waveEffect", "Vertical bars with sin-based height creating a wave",
                """// Wave effect
bars.forEachIndexed { i, _ ->
    val h = baseHeight + sin(phase + i * 0.5f) * amplitude
    Box(Modifier.height(h.dp).width(barWidth))
}"""),
        ),
    ),
    ExampleCategory(
        id = "progress-steps",
        title = "Progress Steps",
        subtitle = "Sequential step indicator filling in one by one",
        accentLabel = "NAVIGATION",
        examples = listOf(
            component("progressSteps", "Steps fill with color and connecting lines grow",
                """// Progress steps
steps.forEachIndexed { i, _ ->
    val filled = i <= currentStep
    val color by animateColorAsState(if (filled) primary else outline)
    Circle(color); if (i < last) Line(progressWidth)
}"""),
        ),
    ),
    ExampleCategory(
        id = "liquid-fill",
        title = "Liquid Fill",
        subtitle = "Circular container filling from bottom to top",
        accentLabel = "LOADING",
        examples = listOf(
            component("liquidFill", "Clipped box with animated height inside a circle",
                """// Liquid fill
val fillHeight by animateFloatAsState(targetLevel * height)
Box(Modifier.clip(CircleShape)) {
    Box(Modifier.fillMaxWidth().height(fillHeight.dp)
        .align(BottomCenter).background(primary))
}"""),
        ),
    ),
    ExampleCategory(
        id = "sliding-reveal",
        title = "Sliding Reveal",
        subtitle = "Text reveals letter by letter with clipping",
        accentLabel = "TEXT",
        examples = listOf(
            component("slidingReveal", "Text appears as if a curtain is pulled aside via clipToBounds",
                """// Sliding reveal
val revealWidth by animateFloatAsState(targetFraction)
Box(Modifier.fillMaxWidth(revealWidth).clipToBounds()) {
    Text(text)
}"""),
        ),
    ),
    ExampleCategory(
        id = "focus-blur",
        title = "Focus Blur Effect",
        subtitle = "Labels cycle through focused/dimmed states",
        accentLabel = "TEXT",
        examples = listOf(
            component("focusBlur", "One label is alpha=1, others fade to 0.2, cycling",
                """// Focus blur
labels.forEachIndexed { i, label ->
    val alpha by animateFloatAsState(if (i == focus) 1f else 0.2f)
    Text(label, Modifier.alpha(alpha))
}"""),
        ),
    ),
    ExampleCategory(
        id = "rolling-digits",
        title = "Rolling Digits",
        subtitle = "Slot machine digit roller animation",
        accentLabel = "TEXT",
        examples = listOf(
            component("rollingDigits", "Digits roll using translationY to random targets",
                """// Rolling digits
val offset by animateFloatAsState(-target * digitHeight, spring())
Column(Modifier.graphicsLayer { translationY = offset }) {
    (0..9).forEach { Text(it.toString()) }
}"""),
        ),
    ),
    ExampleCategory(
        id = "spring-chain",
        title = "Spring Chain",
        subtitle = "Connected elements with cascading spring animation",
        accentLabel = "PHYSICS",
        examples = listOf(
            component("springChain", "Row of circles with staggered spring offsets",
                """// Spring chain
circles.forEachIndexed { i, _ ->
    val y by animateFloatAsState(
        targetY, spring(dampingRatio = 0.3f), delay = i * 60
    )
    Box(Modifier.offset { IntOffset(0, y.toInt()) })
}"""),
        ),
    ),
    ExampleCategory(
        id = "glitch-text",
        title = "Glitch Text",
        subtitle = "Text with periodic glitch distortion effect",
        accentLabel = "TEXT",
        examples = listOf(
            component("glitchText", "Text briefly offsets horizontally with shifted copies",
                """// Glitch text
if (isGlitching) {
    Text(text, Modifier.offset(x = 3.dp), color = red.copy(0.7f))
    Text(text, Modifier.offset(x = -3.dp), color = cyan.copy(0.7f))
}
Text(text, Modifier.offset(x = if (isGlitching) 2.dp else 0.dp))"""),
        ),
    ),
    ExampleCategory(
        id = "expanding-rings",
        title = "Expanding Rings",
        subtitle = "Concentric circles expanding with fading alpha",
        accentLabel = "CANVAS",
        examples = listOf(
            component("expandingRings", "Canvas drawCircle with expanding radius and fading alpha",
                """// Expanding rings
Canvas(Modifier) {
    rings.forEach { ring ->
        drawCircle(primary, radius = ring.radius, alpha = ring.alpha)
    }
}"""),
        ),
    ),
    ExampleCategory(
        id = "stacked-cards",
        title = "Stacked Cards",
        subtitle = "Card deck where front card animates away",
        accentLabel = "INTERACTION",
        examples = listOf(
            component("stackedCards", "Front card slides out, next becomes front, cycles",
                """// Stacked cards
cards.forEachIndexed { i, card ->
    val tx by animateFloatAsState(if (i == front) dismissX else offsets[i])
    Card(Modifier.graphicsLayer { translationX = tx; rotationZ = angles[i] })
}"""),
        ),
    ),
    ExampleCategory(
        id = "countdown-timer",
        title = "Countdown Timer",
        subtitle = "Circular countdown with decreasing arc",
        accentLabel = "CANVAS",
        examples = listOf(
            component("countdownTimer", "Canvas drawArc showing remaining time as arc progress",
                """// Countdown timer
Canvas(Modifier) {
    drawArc(trackColor, 0f, 360f)
    drawArc(primary, -90f, sweepAngle = progress * 360f)
}
Text(remaining.toString(), Modifier.center())"""),
        ),
    ),
    ExampleCategory(
        id = "vertical-ticker",
        title = "Vertical Ticker",
        subtitle = "Text cycles by sliding vertically",
        accentLabel = "TEXT",
        examples = listOf(
            component("verticalTicker", "Old word slides up out, new word slides up in",
                """// Vertical ticker
val offset by animateFloatAsState(-currentIndex * lineHeight)
Column(Modifier.clipToBounds().height(lineHeight)) {
    words.forEach { Text(it, Modifier.offset { IntOffset(0, offset) }) }
}"""),
        ),
    ),
    ExampleCategory(
        id = "heartbeat-line",
        title = "Heartbeat Line",
        subtitle = "ECG-style heartbeat line drawn on canvas",
        accentLabel = "CANVAS",
        examples = listOf(
            component("heartbeatLine", "Canvas drawLine progressing with heartbeat spike pattern",
                """// Heartbeat line
Canvas(Modifier) {
    val path = buildHeartbeatPath(progress, width, height)
    drawPath(path, primary, style = Stroke(2.dp))
}"""),
        ),
    ),
    ExampleCategory(
        id = "expanding-search",
        title = "Expanding Search",
        subtitle = "Circle icon expands into a search bar shape",
        accentLabel = "INTERACTION",
        examples = listOf(
            component("expandingSearch", "Animated width from circle to wide bar with spring",
                """// Expanding search
val width by animateFloatAsState(
    if (expanded) 240f else 48f, spring()
)
Surface(Modifier.width(width.dp).height(48.dp), shape = CircleShape)"""),
        ),
    ),
)

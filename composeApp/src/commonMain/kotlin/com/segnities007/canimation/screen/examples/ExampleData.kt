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
 *   "grid" = 2x2 grid of staggered items
 */
data class ExampleItem(
    val preset: CanimationPreset,
    val description: String,
    val codeSnippet: String,
    val demoType: String = "visibility",
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
)

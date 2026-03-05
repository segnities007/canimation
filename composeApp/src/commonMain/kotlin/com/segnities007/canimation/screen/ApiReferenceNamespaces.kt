package com.segnities007.canimation.screen

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect

internal fun MutableList<ApiEntry>.addNamespaceEntries() {
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

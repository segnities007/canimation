package com.segnities007.canimation.screen.examples

import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset


internal val exampleCategoriesChunk02Part01: List<ExampleCategory> = listOf(
        // 13. Zoom Effects
        ExampleCategory(
            id = "zoom",
            title = "Zoom Effects",
            subtitle = "Zoom-based entrance and exit",
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = "Zoom In",
                    description = "Standard zoom entrance",
                    effect = Canimation.Zoom.In,
                    preset = CanimationPreset.ZoomIn,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Zoom.In,
    )""",
                ),
                ExampleItem(
                    title = "Zoom Out",
                    description = "Zoom out exit effect",
                    effect = Canimation.Zoom.Out,
                    preset = CanimationPreset.ZoomOut,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Zoom.Out,
    )""",
                ),
                ExampleItem(
                    title = "Zoom In Up",
                    description = "Zoom in from below",
                    effect = Canimation.Zoom.InUp,
                    preset = CanimationPreset.ZoomInUp,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Zoom.InUp,
    )""",
                ),
                ExampleItem(
                    title = "Zoom In Down",
                    description = "Zoom in from above",
                    effect = Canimation.Zoom.InDown,
                    preset = CanimationPreset.ZoomInDown,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Zoom.InDown,
    )""",
                ),
                ExampleItem(
                    title = "Zoom In Left",
                    description = "Zoom in from the right",
                    effect = Canimation.Zoom.InLeft,
                    preset = CanimationPreset.ZoomInLeft,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Zoom.InLeft,
    )""",
                ),
                ExampleItem(
                    title = "Zoom In Right",
                    description = "Zoom in from the left",
                    effect = Canimation.Zoom.InRight,
                    preset = CanimationPreset.ZoomInRight,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Zoom.InRight,
    )""",
                ),
                ExampleItem(
                    title = "Zoom Dramatic",
                    description = "Extreme zoom entrance",
                    effect = Canimation.Zoom.Dramatic,
                    preset = CanimationPreset.ZoomIn,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Zoom.Dramatic,
    )""",
                ),
            ),
        ),
        // 14. Attention Seekers
        ExampleCategory(
            id = "attention",
            title = "Attention Seekers",
            subtitle = "Emphasis and attention effects",
            accentLabel = "EMPHASIS",
            tags = listOf("EMPHASIS"),
            examples = listOf(
                ExampleItem(
                    title = "Pulse",
                    description = "Gentle pulsing scale",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Pulse,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isActive,
        preset = CanimationPreset.Pulse,
    )""",
                ),
                ExampleItem(
                    title = "HeartBeat",
                    description = "Double-pulse heartbeat",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.HeartBeat,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isActive,
        preset = CanimationPreset.HeartBeat,
    )""",
                ),
                ExampleItem(
                    title = "Tada",
                    description = "Celebratory shake and scale",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Tada,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isActive,
        preset = CanimationPreset.Tada,
    )""",
                ),
                ExampleItem(
                    title = "Wobble",
                    description = "Side-to-side wobble",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Wobble,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isActive,
        preset = CanimationPreset.Wobble,
    )""",
                ),
                ExampleItem(
                    title = "Swing",
                    description = "Pendulum swing motion",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Swing,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isActive,
        preset = CanimationPreset.Swing,
    )""",
                ),
                ExampleItem(
                    title = "RubberBand",
                    description = "Elastic rubber band stretch",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.RubberBand,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isActive,
        preset = CanimationPreset.RubberBand,
    )""",
                ),
                ExampleItem(
                    title = "Jello",
                    description = "Jiggly jello effect",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Jello,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isActive,
        preset = CanimationPreset.Jello,
    )""",
                ),
                ExampleItem(
                    title = "Flash",
                    description = "Quick opacity flash",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Flash,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isActive,
        preset = CanimationPreset.Flash,
    )""",
                ),
                ExampleItem(
                    title = "ShakeX",
                    description = "Horizontal shake",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.ShakeX,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isActive,
        preset = CanimationPreset.ShakeX,
    )""",
                ),
                ExampleItem(
                    title = "ShakeY",
                    description = "Vertical shake",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.ShakeY,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isActive,
        preset = CanimationPreset.ShakeY,
    )""",
                ),
                ExampleItem(
                    title = "HeadShake",
                    description = "Head shaking no",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.HeadShake,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isActive,
        preset = CanimationPreset.HeadShake,
    )""",
                ),
                ExampleItem(
                    title = "Bounce",
                    description = "Bouncing attention effect",
                    demoType = DemoType.Emphasis,
                    preset = CanimationPreset.Bounce,
                    codeSnippet = """Modifier.canimationEmphasize(
        active = isActive,
        preset = CanimationPreset.Bounce,
    )""",
                ),
            ),
        ),
        // 15. Dramatic Entrances
        ExampleCategory(
            id = "entrance",
            title = "Dramatic Entrances",
            subtitle = "Unique, expressive entrance effects",
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE"),
            examples = listOf(
                ExampleItem(
                    title = "Elevate",
                    description = "Rise with shadow effect",
                    effect = Canimation.Entrance.Elevate,
                    preset = CanimationPreset.ElevateIn,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.Elevate,
    )""",
                ),
                ExampleItem(
                    title = "Drop",
                    description = "Drop in from above",
                    effect = Canimation.Entrance.Drop,
                    preset = CanimationPreset.DropIn,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.Drop,
    )""",
                ),
                ExampleItem(
                    title = "Jack In The Box",
                    description = "Playful spring-up entrance",
                    effect = Canimation.Entrance.JackInTheBox,
                    preset = CanimationPreset.JackInTheBox,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.JackInTheBox,
    )""",
                ),
                ExampleItem(
                    title = "Roll In",
                    description = "Rolling entrance from left",
                    effect = Canimation.Entrance.RollIn,
                    preset = CanimationPreset.RollIn,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.RollIn,
    )""",
                ),
                ExampleItem(
                    title = "Light Speed Left",
                    description = "Zoom in from left at light speed",
                    effect = Canimation.Entrance.LightSpeedLeft,
                    preset = CanimationPreset.LightSpeedInLeft,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.LightSpeedLeft,
    )""",
                ),
                ExampleItem(
                    title = "Light Speed Right",
                    description = "Zoom in from right at light speed",
                    effect = Canimation.Entrance.LightSpeedRight,
                    preset = CanimationPreset.LightSpeedInRight,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.LightSpeedRight,
    )""",
                ),
                ExampleItem(
                    title = "Snap",
                    description = "Snappy instant entrance",
                    effect = Canimation.Entrance.Snap,
                    preset = CanimationPreset.Snap,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.Snap,
    )""",
                ),
                ExampleItem(
                    title = "Swing In",
                    description = "Swinging door entrance",
                    effect = Canimation.Entrance.SwingIn,
                    preset = CanimationPreset.SwingIn,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.SwingIn,
    )""",
                ),
                ExampleItem(
                    title = "Unfold",
                    description = "Unfolding paper effect",
                    effect = Canimation.Entrance.Unfold,
                    preset = CanimationPreset.ElevateIn,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.Unfold,
    )""",
                ),
                ExampleItem(
                    title = "Rise",
                    description = "Gentle rising entrance",
                    effect = Canimation.Entrance.Rise,
                    preset = CanimationPreset.FadeUp,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.Rise,
    )""",
                ),
                ExampleItem(
                    title = "Emerge",
                    description = "Emerging from nothing",
                    effect = Canimation.Entrance.Emerge,
                    preset = CanimationPreset.ScaleIn,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.Emerge,
    )""",
                ),
            ),
        ),
        // 16. Back Entrances
        ExampleCategory(
            id = "entrance-back",
            title = "Back Entrances",
            subtitle = "Depth-based entrance effects",
            accentLabel = "ENTRANCE",
            tags = listOf("ENTRANCE", "DIRECTION"),
            examples = listOf(
                ExampleItem(
                    title = "Back In Up",
                    description = "Slide in from behind, upward",
                    effect = Canimation.Entrance.BackInUp,
                    preset = CanimationPreset.BackInUp,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.BackInUp,
    )""",
                ),
                ExampleItem(
                    title = "Back In Down",
                    description = "Slide in from behind, downward",
                    effect = Canimation.Entrance.BackInDown,
                    preset = CanimationPreset.BackInDown,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.BackInDown,
    )""",
                ),
                ExampleItem(
                    title = "Back In Left",
                    description = "Slide in from behind, leftward",
                    effect = Canimation.Entrance.BackInLeft,
                    preset = CanimationPreset.BackInLeft,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.BackInLeft,
    )""",
                ),
                ExampleItem(
                    title = "Back In Right",
                    description = "Slide in from behind, rightward",
                    effect = Canimation.Entrance.BackInRight,
                    preset = CanimationPreset.BackInRight,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.BackInRight,
    )""",
                ),
                ExampleItem(
                    title = "Shrink In",
                    description = "Shrinking entrance effect",
                    effect = Canimation.Entrance.ShrinkIn,
                    preset = CanimationPreset.ShrinkIn,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.ShrinkIn,
    )""",
                ),
                ExampleItem(
                    title = "Tilt In",
                    description = "Tilting entrance effect",
                    effect = Canimation.Entrance.TiltIn,
                    preset = CanimationPreset.TiltIn,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Entrance.TiltIn,
    )""",
                ),
            ),
        ),
        // 17. Material Motion
        ExampleCategory(
            id = "material",
            title = "Material Motion",
            subtitle = "Google Material Design transitions",
            accentLabel = "MATERIAL",
            tags = listOf("MATERIAL"),
            examples = listOf(
                ExampleItem(
                    title = "Fade Through",
                    description = "Material fade-through transition",
                    effect = Canimation.Material.FadeThrough,
                    preset = CanimationPreset.FadeThrough,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Material.FadeThrough,
    )""",
                ),
                ExampleItem(
                    title = "Shared Axis X",
                    description = "Horizontal shared-axis transition",
                    effect = Canimation.Material.SharedAxisX,
                    preset = CanimationPreset.SharedAxisX,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Material.SharedAxisX,
    )""",
                ),
                ExampleItem(
                    title = "Shared Axis Y",
                    description = "Vertical shared-axis transition",
                    effect = Canimation.Material.SharedAxisY,
                    preset = CanimationPreset.SharedAxisY,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Material.SharedAxisY,
    )""",
                ),
                ExampleItem(
                    title = "Shared Axis Z",
                    description = "Depth shared-axis transition",
                    effect = Canimation.Material.SharedAxisZ,
                    preset = CanimationPreset.SharedAxisX,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Material.SharedAxisZ,
    )""",
                ),
                ExampleItem(
                    title = "Emphasized",
                    description = "Material emphasized motion",
                    effect = Canimation.Material.Emphasized,
                    preset = CanimationPreset.EmphasizedEntry,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Material.Emphasized,
    )""",
                ),
                ExampleItem(
                    title = "Container Transform",
                    description = "Material container transform",
                    effect = Canimation.Material.ContainerTransform,
                    preset = CanimationPreset.EmphasizedEntry,
                    codeSnippet = """Modifier.canimation(
        visible = isVisible,
        effect = Canimation.Material.ContainerTransform,
    )""",
                ),
            ),
        ),
)

package io.github.canimation.core

internal val ambientHighlightRecipe: CanimationRecipe =
    semanticRecipe(
        id = "semantic.ambient.highlight",
        intent = CanimationIntent.Ambient,
        surfaceRole = CanimationSurfaceRole.Decoration,
        intensity = CanimationIntensity.Subtle,
        family = "Ambient",
        title = "Ambient Highlight",
        summary = "Low-intensity decorative highlight.",
        usageGuidance = "Use only as supportive background motion, not as primary feedback or navigation.",
        effect = CanimationEffect.fade(from = 0.4f, to = 1f) + CanimationEffect.duration(180),
        cost = CanimationCost.Draw,
        tags = setOf("ambient", "decorative"),
        reducedStrategy = CanimationReducedStrategy.FadeOnly,
    )

@Suppress("FunctionName")
object CanimationAmbientRecipes {
    val Highlight: CanimationRecipe = ambientHighlightRecipe
}

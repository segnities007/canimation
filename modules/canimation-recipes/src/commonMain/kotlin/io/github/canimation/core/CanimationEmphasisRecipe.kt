package io.github.canimation.core

internal val emphasisCallToActionRecipe: CanimationRecipe =
    semanticRecipe(
        id = "semantic.emphasis.call-to-action",
        intent = CanimationIntent.Emphasis,
        surfaceRole = CanimationSurfaceRole.Control,
        intensity = CanimationIntensity.Strong,
        family = "Emphasis",
        title = "Emphasis Call To Action",
        summary = "Focused CTA entry with pop and fade.",
        usageGuidance = "Use sparingly to highlight a primary call to action.",
        effect = CanimationEffect.fade() + CanimationEffect.pop(from = 0.84f) + CanimationEffect.duration(280),
        cost = CanimationCost.Transform,
        tags = setOf("cta", "attention"),
    )

@Suppress("FunctionName")
object CanimationEmphasisRecipes {
    val CallToAction: CanimationRecipe = emphasisCallToActionRecipe
}

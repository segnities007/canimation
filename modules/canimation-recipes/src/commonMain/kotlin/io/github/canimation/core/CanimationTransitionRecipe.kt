package io.github.canimation.core

internal val transitionContentSwapRecipe: CanimationRecipe =
    semanticRecipe(
        id = "semantic.transition.content-swap",
        intent = CanimationIntent.Transition,
        surfaceRole = CanimationSurfaceRole.Container,
        intensity = CanimationIntensity.Standard,
        family = "Transition",
        title = "Transition Content Swap",
        summary = "Fade-through style transition for content replacement.",
        usageGuidance = "Use for tabs, filters, or container content swaps where hierarchy should remain stable.",
        effect = CanimationEffect.fade() + CanimationEffect.scale(from = 0.97f) + CanimationEffect.duration(220),
        cost = CanimationCost.Transform,
        tags = setOf("swap", "container"),
    )

@Suppress("FunctionName")
object CanimationTransitionRecipes {
    val ContentSwap: CanimationRecipe = transitionContentSwapRecipe
}

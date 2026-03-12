package io.github.canimation.core

import androidx.compose.ui.unit.dp

internal val navigationForwardRecipe: CanimationRecipe =
    semanticRecipe(
        id = "semantic.navigation.forward",
        intent = CanimationIntent.Navigation,
        surfaceRole = CanimationSurfaceRole.Page,
        intensity = CanimationIntensity.Standard,
        family = "Navigation",
        title = "Navigation Forward",
        summary = "Forward route transition with horizontal continuity.",
        usageGuidance = "Use when navigating deeper into a hierarchy or drilling into detail.",
        effect = CanimationEffect.fade() + semanticHorizontalSlide(from = 24.dp) + CanimationEffect.duration(260),
        cost = CanimationCost.Transform,
        tags = setOf("page", "forward"),
    )

internal val navigationBackwardRecipe: CanimationRecipe =
    semanticRecipe(
        id = "semantic.navigation.backward",
        intent = CanimationIntent.Navigation,
        surfaceRole = CanimationSurfaceRole.Page,
        intensity = CanimationIntensity.Standard,
        family = "Navigation",
        title = "Navigation Backward",
        summary = "Backward route transition with horizontal continuity.",
        usageGuidance = "Use when navigating back or dismissing a detail stack level.",
        effect = CanimationEffect.fade() + semanticHorizontalSlide(from = (-24).dp) + CanimationEffect.duration(240),
        cost = CanimationCost.Transform,
        tags = setOf("page", "back"),
    )

@Suppress("FunctionName")
object CanimationNavigationRecipes {
    val Forward: CanimationRecipe = navigationForwardRecipe
    val Backward: CanimationRecipe = navigationBackwardRecipe
}

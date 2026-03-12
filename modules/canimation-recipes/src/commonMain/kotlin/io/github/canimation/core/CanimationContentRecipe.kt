package io.github.canimation.core

import androidx.compose.ui.unit.dp

internal val contentEnterSubtleRecipe: CanimationRecipe =
    semanticRecipe(
        id = "semantic.content.enter-subtle",
        intent = CanimationIntent.Content,
        surfaceRole = CanimationSurfaceRole.Container,
        intensity = CanimationIntensity.Subtle,
        family = "Content",
        title = "Content Enter Subtle",
        summary = "Fade and rise for standard content entry.",
        usageGuidance = "Use for cards, sections, and content blocks that should enter with low visual noise.",
        effect = CanimationEffect.fade() + CanimationEffect.slideUp(8.dp),
        cost = CanimationCost.Transform,
        tags = setOf("enter", "subtle"),
    )

internal val contentEnterStandardRecipe: CanimationRecipe =
    semanticRecipe(
        id = "semantic.content.enter-standard",
        intent = CanimationIntent.Content,
        surfaceRole = CanimationSurfaceRole.Container,
        intensity = CanimationIntensity.Standard,
        family = "Content",
        title = "Content Enter Standard",
        summary = "Fade and rise for default content entry.",
        usageGuidance = "Use as the general-purpose semantic recipe for normal content appearance.",
        effect = CanimationEffect.fade() + CanimationEffect.slideUp(16.dp),
        cost = CanimationCost.Transform,
        tags = setOf("enter", "default"),
    )

@Suppress("FunctionName")
object CanimationContentRecipes {
    val EnterSubtle: CanimationRecipe = contentEnterSubtleRecipe
    val EnterStandard: CanimationRecipe = contentEnterStandardRecipe
}

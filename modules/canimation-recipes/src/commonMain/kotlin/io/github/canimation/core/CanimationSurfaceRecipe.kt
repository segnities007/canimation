package io.github.canimation.core

import androidx.compose.ui.unit.dp

internal val surfaceDialogRevealRecipe: CanimationRecipe =
    semanticRecipe(
        id = "semantic.surface.dialog-reveal",
        intent = CanimationIntent.Surface,
        surfaceRole = CanimationSurfaceRole.Overlay,
        intensity = CanimationIntensity.Standard,
        family = "Surface",
        title = "Surface Dialog Reveal",
        summary = "Centered dialog reveal with fade and scale.",
        usageGuidance = "Use for dialogs and overlays that should feel distinct from background content.",
        effect = CanimationEffect.fade() + CanimationEffect.scale(from = 0.92f) + CanimationEffect.duration(240),
        cost = CanimationCost.Transform,
        tags = setOf("overlay", "dialog"),
    )

internal val surfaceSheetRevealRecipe: CanimationRecipe =
    semanticRecipe(
        id = "semantic.surface.sheet-reveal",
        intent = CanimationIntent.Surface,
        surfaceRole = CanimationSurfaceRole.Overlay,
        intensity = CanimationIntensity.Standard,
        family = "Surface",
        title = "Surface Sheet Reveal",
        summary = "Bottom sheet reveal with fade and vertical motion.",
        usageGuidance = "Use for bottom sheets and anchored surfaces entering from below.",
        effect = CanimationEffect.fade() + CanimationEffect.slideUp(24.dp) + CanimationEffect.duration(260),
        cost = CanimationCost.Transform,
        tags = setOf("overlay", "sheet"),
    )

@Suppress("FunctionName")
object CanimationSurfaceRecipes {
    val DialogReveal: CanimationRecipe = surfaceDialogRevealRecipe
    val SheetReveal: CanimationRecipe = surfaceSheetRevealRecipe
}

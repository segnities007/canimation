package io.github.canimation.core

private val defaultSemanticRecipes: List<CanimationRecipe> =
    listOf(
        contentEnterSubtleRecipe,
        contentEnterStandardRecipe,
        feedbackPressRecipe,
        feedbackSelectionPulseRecipe,
        navigationForwardRecipe,
        navigationBackwardRecipe,
        surfaceDialogRevealRecipe,
        surfaceSheetRevealRecipe,
        emphasisCallToActionRecipe,
        transitionContentSwapRecipe,
        ambientHighlightRecipe,
        recoveryErrorShakeRecipe,
    )

val DefaultCanimationRecipeRegistry: CanimationRecipeRegistry =
    CanimationRecipeRegistry(defaultSemanticRecipes.associate { it.id to it.descriptor })

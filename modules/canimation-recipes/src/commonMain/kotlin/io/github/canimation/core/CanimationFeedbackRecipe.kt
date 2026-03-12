package io.github.canimation.core

internal val feedbackPressRecipe: CanimationRecipe =
    semanticRecipe(
        id = "semantic.feedback.press",
        intent = CanimationIntent.Feedback,
        surfaceRole = CanimationSurfaceRole.Control,
        intensity = CanimationIntensity.Subtle,
        family = "Feedback",
        title = "Feedback Press",
        summary = "Quick press response with tiny scale compression.",
        usageGuidance = "Use for buttons, chips, and toggles to acknowledge user input without stealing attention.",
        effect = CanimationEffect.scale(from = 0.97f) + CanimationEffect.duration(120),
        cost = CanimationCost.Transform,
        tags = setOf("press", "control"),
        reducedStrategy = CanimationReducedStrategy.FadeOnly,
    )

internal val feedbackSelectionPulseRecipe: CanimationRecipe =
    semanticRecipe(
        id = "semantic.feedback.selection-pulse",
        intent = CanimationIntent.Feedback,
        surfaceRole = CanimationSurfaceRole.Control,
        intensity = CanimationIntensity.Subtle,
        family = "Feedback",
        title = "Feedback Selection Pulse",
        summary = "Gentle pulse for selected state confirmation.",
        usageGuidance = "Use to reinforce selection or confirmation on interactive controls.",
        effect = CanimationEffect.scale(from = 0.96f) + CanimationEffect.fade() + CanimationEffect.duration(160),
        cost = CanimationCost.Transform,
        tags = setOf("selection", "control"),
        reducedStrategy = CanimationReducedStrategy.FadeOnly,
    )

@Suppress("FunctionName")
object CanimationFeedbackRecipes {
    val Press: CanimationRecipe = feedbackPressRecipe
    val SelectionPulse: CanimationRecipe = feedbackSelectionPulseRecipe
}

package io.github.canimation.core

import androidx.compose.ui.unit.dp

internal val recoveryErrorShakeRecipe: CanimationRecipe =
    semanticRecipe(
        id = "semantic.recovery.error-shake",
        intent = CanimationIntent.Recovery,
        surfaceRole = CanimationSurfaceRole.Control,
        intensity = CanimationIntensity.Strong,
        family = "Recovery",
        title = "Recovery Error Shake",
        summary = "Short corrective motion for invalid or failed actions.",
        usageGuidance = "Use for recoverable errors where the user needs immediate correction feedback.",
        effect =
            semanticHorizontalSlide(
                from = 10.dp,
                durationMs = 140,
                easing = CanimationTokens.Default.easing.standard,
            ),
        cost = CanimationCost.Transform,
        tags = setOf("error", "recovery"),
        reducedStrategy = CanimationReducedStrategy.FadeOnly,
    )

@Suppress("FunctionName")
object CanimationRecoveryRecipes {
    val ErrorShake: CanimationRecipe = recoveryErrorShakeRecipe
}

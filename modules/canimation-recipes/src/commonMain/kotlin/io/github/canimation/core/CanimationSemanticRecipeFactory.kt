package io.github.canimation.core

import androidx.compose.animation.core.Easing
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private fun CanimationEffect.toRecipeDescriptor(
    id: String,
    intent: CanimationIntent,
    surfaceRole: CanimationSurfaceRole,
    intensity: CanimationIntensity,
    family: String,
    title: String,
    summary: String,
    usageGuidance: String,
    cost: CanimationCost,
    tags: Set<String> = emptySet(),
    reducedStrategy: CanimationReducedStrategy = CanimationReducedStrategy.Compress,
    supportsOff: Boolean = true,
    benchmarkRequired: Boolean = false,
    listSafe: Boolean = cost != CanimationCost.Layout,
    tokens: CanimationTokens = CanimationTokens.Default,
): CanimationRecipeDescriptor {
    val full = toEnterSpec(tokens)
    val reduced =
        when (reducedStrategy) {
            CanimationReducedStrategy.Snap -> full.copy(durationMs = 0)
            CanimationReducedStrategy.FadeOnly ->
                CanimationEffect.fade().toEnterSpec(tokens).copy(
                    durationMs = tokens.duration.shortMs,
                    easing = tokens.easing.decelerate,
                )

            CanimationReducedStrategy.Alternative,
            CanimationReducedStrategy.Compress,
            -> full.toReduced(tokens)
        }

    return CanimationRecipeDescriptor(
        id = CanimationRecipeId(id),
        semantic =
            CanimationRecipeSemantic(
                intent = intent,
                surfaceRole = surfaceRole,
                intensity = intensity,
                family = family,
                tags = tags + setOf(intent.name, family),
            ),
        accessibility =
            CanimationRecipeAccessibility(
                reducedStrategy = reducedStrategy,
                supportsOff = supportsOff,
                notes = "Built-in semantic recipe.",
            ),
        performance =
            CanimationRecipePerformance(
                cost = cost,
                listSafe = listSafe,
                benchmarkRequired = benchmarkRequired,
                notes = "Derived from semantic recipe effect composition.",
            ),
        docs =
            CanimationRecipeDocs(
                title = title,
                summary = summary,
                usageGuidance = usageGuidance,
            ),
        specs =
            CanimationRecipeSpecSet(
                full = full,
                reduced = reduced,
                off = full.copy(durationMs = 0),
            ),
    )
}

internal fun semanticRecipe(
    id: String,
    intent: CanimationIntent,
    surfaceRole: CanimationSurfaceRole,
    intensity: CanimationIntensity,
    family: String,
    title: String,
    summary: String,
    usageGuidance: String,
    effect: CanimationEffect,
    cost: CanimationCost = CanimationCost.Transform,
    tags: Set<String> = emptySet(),
    reducedStrategy: CanimationReducedStrategy = CanimationReducedStrategy.Compress,
): CanimationRecipe =
    CanimationRecipe(
        effect.toRecipeDescriptor(
            id = id,
            intent = intent,
            surfaceRole = surfaceRole,
            intensity = intensity,
            family = family,
            title = title,
            summary = summary,
            usageGuidance = usageGuidance,
            cost = cost,
            tags = tags,
            reducedStrategy = reducedStrategy,
        ),
    )

internal fun semanticHorizontalSlide(
    from: Dp,
    durationMs: Int = 220,
    easing: Easing = CanimationTokens.Default.easing.standard,
): CanimationEffect =
    CanimationEffect(
        offsetX = CanimationDpRange(from, 0.dp),
        alpha = CanimationRange(0f, 1f),
        durationMs = durationMs,
        easing = easing,
    )

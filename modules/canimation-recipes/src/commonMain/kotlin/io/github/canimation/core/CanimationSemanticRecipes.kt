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

private fun semanticRecipe(
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

private object SemanticRecipeFactory {
    fun contentEnterSubtle(): CanimationRecipe =
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

    fun contentEnterStandard(): CanimationRecipe =
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

    fun feedbackPress(): CanimationRecipe =
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

    fun feedbackSelectionPulse(): CanimationRecipe =
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

    fun navigationForward(): CanimationRecipe =
        semanticRecipe(
            id = "semantic.navigation.forward",
            intent = CanimationIntent.Navigation,
            surfaceRole = CanimationSurfaceRole.Page,
            intensity = CanimationIntensity.Standard,
            family = "Navigation",
            title = "Navigation Forward",
            summary = "Forward route transition with horizontal continuity.",
            usageGuidance = "Use when navigating deeper into a hierarchy or drilling into detail.",
            effect = CanimationEffect.fade() + horizontalSlide(from = 24.dp) + CanimationEffect.duration(260),
            cost = CanimationCost.Transform,
            tags = setOf("page", "forward"),
        )

    fun navigationBackward(): CanimationRecipe =
        semanticRecipe(
            id = "semantic.navigation.backward",
            intent = CanimationIntent.Navigation,
            surfaceRole = CanimationSurfaceRole.Page,
            intensity = CanimationIntensity.Standard,
            family = "Navigation",
            title = "Navigation Backward",
            summary = "Backward route transition with horizontal continuity.",
            usageGuidance = "Use when navigating back or dismissing a detail stack level.",
            effect = CanimationEffect.fade() + horizontalSlide(from = (-24).dp) + CanimationEffect.duration(240),
            cost = CanimationCost.Transform,
            tags = setOf("page", "back"),
        )

    fun surfaceDialogReveal(): CanimationRecipe =
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

    fun surfaceSheetReveal(): CanimationRecipe =
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

    fun emphasisCallToAction(): CanimationRecipe =
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

    fun transitionContentSwap(): CanimationRecipe =
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

    fun ambientHighlight(): CanimationRecipe =
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

    fun recoveryErrorShake(): CanimationRecipe =
        semanticRecipe(
            id = "semantic.recovery.error-shake",
            intent = CanimationIntent.Recovery,
            surfaceRole = CanimationSurfaceRole.Control,
            intensity = CanimationIntensity.Strong,
            family = "Recovery",
            title = "Recovery Error Shake",
            summary = "Short corrective motion for invalid or failed actions.",
            usageGuidance = "Use for recoverable errors where the user needs immediate correction feedback.",
            effect = horizontalSlide(from = 10.dp, durationMs = 140, easing = CanimationTokens.Default.easing.standard),
            cost = CanimationCost.Transform,
            tags = setOf("error", "recovery"),
            reducedStrategy = CanimationReducedStrategy.FadeOnly,
        )

    private fun horizontalSlide(
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
}

private val defaultSemanticRecipes: List<CanimationRecipe> =
    listOf(
        SemanticRecipeFactory.contentEnterSubtle(),
        SemanticRecipeFactory.contentEnterStandard(),
        SemanticRecipeFactory.feedbackPress(),
        SemanticRecipeFactory.feedbackSelectionPulse(),
        SemanticRecipeFactory.navigationForward(),
        SemanticRecipeFactory.navigationBackward(),
        SemanticRecipeFactory.surfaceDialogReveal(),
        SemanticRecipeFactory.surfaceSheetReveal(),
        SemanticRecipeFactory.emphasisCallToAction(),
        SemanticRecipeFactory.transitionContentSwap(),
        SemanticRecipeFactory.ambientHighlight(),
        SemanticRecipeFactory.recoveryErrorShake(),
    )

val DefaultCanimationRecipeRegistry: CanimationRecipeRegistry =
    CanimationRecipeRegistry(defaultSemanticRecipes.associate { it.id to it.descriptor })

@Suppress("FunctionName")
object CanimationContentRecipes {
    val EnterSubtle: CanimationRecipe = defaultSemanticRecipes.first { it.id.value == "semantic.content.enter-subtle" }
    val EnterStandard: CanimationRecipe = defaultSemanticRecipes.first { it.id.value == "semantic.content.enter-standard" }
}

@Suppress("FunctionName")
object CanimationFeedbackRecipes {
    val Press: CanimationRecipe = defaultSemanticRecipes.first { it.id.value == "semantic.feedback.press" }
    val SelectionPulse: CanimationRecipe = defaultSemanticRecipes.first { it.id.value == "semantic.feedback.selection-pulse" }
}

@Suppress("FunctionName")
object CanimationNavigationRecipes {
    val Forward: CanimationRecipe = defaultSemanticRecipes.first { it.id.value == "semantic.navigation.forward" }
    val Backward: CanimationRecipe = defaultSemanticRecipes.first { it.id.value == "semantic.navigation.backward" }
}

@Suppress("FunctionName")
object CanimationSurfaceRecipes {
    val DialogReveal: CanimationRecipe = defaultSemanticRecipes.first { it.id.value == "semantic.surface.dialog-reveal" }
    val SheetReveal: CanimationRecipe = defaultSemanticRecipes.first { it.id.value == "semantic.surface.sheet-reveal" }
}

@Suppress("FunctionName")
object CanimationEmphasisRecipes {
    val CallToAction: CanimationRecipe = defaultSemanticRecipes.first { it.id.value == "semantic.emphasis.call-to-action" }
}

@Suppress("FunctionName")
object CanimationTransitionRecipes {
    val ContentSwap: CanimationRecipe = defaultSemanticRecipes.first { it.id.value == "semantic.transition.content-swap" }
}

@Suppress("FunctionName")
object CanimationAmbientRecipes {
    val Highlight: CanimationRecipe = defaultSemanticRecipes.first { it.id.value == "semantic.ambient.highlight" }
}

@Suppress("FunctionName")
object CanimationRecoveryRecipes {
    val ErrorShake: CanimationRecipe = defaultSemanticRecipes.first { it.id.value == "semantic.recovery.error-shake" }
}

val Canimation.Content: CanimationContentRecipes
    get() = CanimationContentRecipes

val Canimation.Feedback: CanimationFeedbackRecipes
    get() = CanimationFeedbackRecipes

val Canimation.Navigation: CanimationNavigationRecipes
    get() = CanimationNavigationRecipes

val Canimation.Surface: CanimationSurfaceRecipes
    get() = CanimationSurfaceRecipes

val Canimation.Emphasis: CanimationEmphasisRecipes
    get() = CanimationEmphasisRecipes

val Canimation.Transition: CanimationTransitionRecipes
    get() = CanimationTransitionRecipes

val Canimation.Ambient: CanimationAmbientRecipes
    get() = CanimationAmbientRecipes

val Canimation.Recovery: CanimationRecoveryRecipes
    get() = CanimationRecoveryRecipes

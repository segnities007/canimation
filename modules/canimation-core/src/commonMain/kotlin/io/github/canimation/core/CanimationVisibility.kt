package io.github.canimation.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

internal data class VisibilityTransitionDescriptor(
    val durationMs: Int,
    val initialAlpha: Float? = null,
    val targetAlpha: Float? = null,
    val initialOffsetXPx: Int? = null,
    val targetOffsetXPx: Int? = null,
    val initialOffsetYPx: Int? = null,
    val targetOffsetYPx: Int? = null,
    val initialScale: Float? = null,
    val targetScale: Float? = null,
)

/**
 * Animated visibility wrapper that uses canimation presets for enter/exit transitions.
 *
 * @param visible Whether the content should be visible.
 * @param enterPreset The preset for enter animation.
 * @param exitPreset The preset for exit animation.
 * @param content The composable content to animate.
 */
@Composable
fun CanimationVisibility(
    visible: Boolean,
    recipe: CanimationRecipe,
    content: @Composable () -> Unit,
) {
    val context = LocalCanimationContext.current
    val descriptor = context.recipeRegistry.resolve(recipe)
    val spec =
        when (context.level) {
            CanimationLevel.Full -> descriptor.specs.full
            CanimationLevel.Reduced -> descriptor.specs.reduced
            CanimationLevel.Off -> descriptor.specs.off
        }

    AnimatedVisibility(
        visible = visible,
        enter = buildEnterTransition(spec),
        exit = buildExitTransition(spec.reversed(context.tokens)),
    ) {
        content()
    }
}

@Composable
fun CanimationVisibility(
    visible: Boolean,
    enterPreset: CanimationPreset = CanimationPreset.FadeUp,
    exitPreset: CanimationPreset = enterPreset,
    content: @Composable () -> Unit,
) {
    val context = LocalCanimationContext.current
    val enterSpec =
        CanimationSpecResolver.resolve(
            preset = enterPreset,
            level = context.level,
            direction = AnimationDirection.Enter,
            registry = context.presetRegistry,
        )
    val exitSpec =
        CanimationSpecResolver.resolve(
            preset = exitPreset,
            level = context.level,
            direction = AnimationDirection.Exit,
            registry = context.presetRegistry,
        )

    AnimatedVisibility(
        visible = visible,
        enter = buildEnterTransition(enterSpec),
        exit = buildExitTransition(exitSpec),
    ) {
        content()
    }
}

/**
 * Animated visibility wrapper using a custom enter spec and context-derived defaults
 * for reduced and exit motion.
 *
 * @param visible Whether the content should be visible.
 * @param enterFullSpec The full-motion enter spec.
 * @param content The composable content to animate.
 */
@Composable
fun CanimationVisibility(
    visible: Boolean,
    enterFullSpec: CanimationSpec,
    content: @Composable () -> Unit,
) {
    val tokens = LocalCanimationContext.current.tokens
    val enterReducedSpec = enterFullSpec.toReduced(tokens)
    CanimationVisibility(
        visible = visible,
        enterFullSpec = enterFullSpec,
        enterReducedSpec = enterReducedSpec,
        exitFullSpec = enterFullSpec.reversed(tokens),
        exitReducedSpec = enterReducedSpec.reversed(tokens),
        content = content,
    )
}

/**
 * Animated visibility wrapper using custom animation specs.
 *
 * @param visible Whether the content should be visible.
 * @param enterFullSpec The full-motion enter spec.
 * @param enterReducedSpec The reduced-motion enter spec.
 * @param exitFullSpec The full-motion exit spec.
 * @param exitReducedSpec The reduced-motion exit spec.
 * @param content The composable content to animate.
 */
@Composable
fun CanimationVisibility(
    visible: Boolean,
    enterFullSpec: CanimationSpec,
    enterReducedSpec: CanimationSpec = enterFullSpec.toReduced(),
    exitFullSpec: CanimationSpec = enterFullSpec.reversed(),
    exitReducedSpec: CanimationSpec = enterReducedSpec.reversed(),
    content: @Composable () -> Unit,
) {
    val context = LocalCanimationContext.current
    val enterSpec =
        CanimationSpecResolver.resolveCustom(
            level = context.level,
            direction = AnimationDirection.Enter,
            fullSpec = enterFullSpec,
            reducedSpec = enterReducedSpec,
        )
    val exitSpec =
        CanimationSpecResolver.resolveCustom(
            level = context.level,
            direction = AnimationDirection.Exit,
            fullSpec = exitFullSpec,
            reducedSpec = exitReducedSpec,
        )

    AnimatedVisibility(
        visible = visible,
        enter = buildEnterTransition(enterSpec),
        exit = buildExitTransition(exitSpec),
    ) {
        content()
    }
}

@Composable
private fun buildEnterTransition(spec: CanimationSpec): EnterTransition {
    val density = LocalDensity.current
    val descriptor =
        resolveEnterTransitionDescriptor(
            spec = spec,
            density = { value -> with(density) { value.roundToPx() } },
        )
    var transition: EnterTransition =
        fadeIn(
            animationSpec =
                tween(
                    durationMillis = descriptor.durationMs,
                    easing = spec.easing,
                ),
            initialAlpha = descriptor.initialAlpha ?: 0f,
        )

    descriptor.initialOffsetYPx?.let { offset ->
        transition +=
            slideInVertically(
                animationSpec =
                    tween(
                        durationMillis = descriptor.durationMs,
                        easing = spec.easing,
                    ),
                initialOffsetY = { offset },
            )
    }

    descriptor.initialOffsetXPx?.let { offset ->
        transition +=
            slideInHorizontally(
                animationSpec =
                    tween(
                        durationMillis = descriptor.durationMs,
                        easing = spec.easing,
                    ),
                initialOffsetX = { offset },
            )
    }

    descriptor.initialScale?.let { scale ->
        transition +=
            scaleIn(
                animationSpec =
                    tween(
                        durationMillis = descriptor.durationMs,
                        easing = spec.easing,
                    ),
                initialScale = scale,
            )
    }

    return transition
}

@Composable
private fun buildExitTransition(spec: CanimationSpec): ExitTransition {
    val density = LocalDensity.current
    val descriptor =
        resolveExitTransitionDescriptor(
            spec = spec,
            density = { value -> with(density) { value.roundToPx() } },
        )
    var transition: ExitTransition =
        fadeOut(
            animationSpec =
                tween(
                    durationMillis = descriptor.durationMs,
                    easing = spec.easing,
                ),
            targetAlpha = descriptor.targetAlpha ?: 0f,
        )

    descriptor.targetOffsetYPx?.let { offset ->
        transition +=
            slideOutVertically(
                animationSpec =
                    tween(
                        durationMillis = descriptor.durationMs,
                        easing = spec.easing,
                    ),
                targetOffsetY = { offset },
            )
    }

    descriptor.targetOffsetXPx?.let { offset ->
        transition +=
            slideOutHorizontally(
                animationSpec =
                    tween(
                        durationMillis = descriptor.durationMs,
                        easing = spec.easing,
                    ),
                targetOffsetX = { offset },
            )
    }

    descriptor.targetScale?.let { scale ->
        transition +=
            scaleOut(
                animationSpec =
                    tween(
                        durationMillis = descriptor.durationMs,
                        easing = spec.easing,
                    ),
                targetScale = scale,
            )
    }

    return transition
}

internal fun resolveEnterTransitionDescriptor(
    spec: CanimationSpec,
    density: (androidx.compose.ui.unit.Dp) -> Int,
): VisibilityTransitionDescriptor =
    VisibilityTransitionDescriptor(
        durationMs = spec.durationMs,
        initialAlpha = spec.alpha?.from ?: 0f,
        initialOffsetXPx = spec.offsetX?.from?.let(density),
        initialOffsetYPx = spec.offsetY?.from?.let(density),
        initialScale = spec.scale?.from,
    )

internal fun resolveExitTransitionDescriptor(
    spec: CanimationSpec,
    density: (androidx.compose.ui.unit.Dp) -> Int,
): VisibilityTransitionDescriptor =
    VisibilityTransitionDescriptor(
        durationMs = spec.durationMs,
        targetAlpha = spec.alpha?.to ?: 0f,
        targetOffsetXPx = spec.offsetX?.to?.let(density),
        targetOffsetYPx = spec.offsetY?.to?.let(density),
        targetScale = spec.scale?.to,
    )

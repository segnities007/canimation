package io.github.canimation.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

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
    enterPreset: CanimationPreset = CanimationPreset.FadeUp,
    exitPreset: CanimationPreset = enterPreset,
    content: @Composable () -> Unit,
) {
    val context = LocalCanimationContext.current
    val enterSpec = CanimationSpecResolver.resolve(
        preset = enterPreset,
        level = context.level,
        direction = AnimationDirection.Enter,
        registry = context.presetRegistry,
    )
    val exitSpec = CanimationSpecResolver.resolve(
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
    val enterSpec = CanimationSpecResolver.resolveCustom(
        level = context.level,
        direction = AnimationDirection.Enter,
        fullSpec = enterFullSpec,
        reducedSpec = enterReducedSpec,
    )
    val exitSpec = CanimationSpecResolver.resolveCustom(
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
    var transition: EnterTransition = fadeIn(
        animationSpec = tween(
            durationMillis = spec.durationMs,
            easing = spec.easing,
        ),
        initialAlpha = spec.alpha?.from ?: 0f,
    )

    spec.offsetY?.let { range ->
        transition += slideInVertically(
            animationSpec = tween(
                durationMillis = spec.durationMs,
                easing = spec.easing,
            ),
            initialOffsetY = { with(density) { range.from.roundToPx() } },
        )
    }

    spec.offsetX?.let { range ->
        transition += slideInHorizontally(
            animationSpec = tween(
                durationMillis = spec.durationMs,
                easing = spec.easing,
            ),
            initialOffsetX = { with(density) { range.from.roundToPx() } },
        )
    }

    spec.scale?.let { range ->
        transition += scaleIn(
            animationSpec = tween(
                durationMillis = spec.durationMs,
                easing = spec.easing,
            ),
            initialScale = range.from,
        )
    }

    return transition
}

@Composable
private fun buildExitTransition(spec: CanimationSpec): ExitTransition {
    val density = LocalDensity.current
    var transition: ExitTransition = fadeOut(
        animationSpec = tween(
            durationMillis = spec.durationMs,
            easing = spec.easing,
        ),
        targetAlpha = spec.alpha?.to ?: 0f,
    )

    spec.offsetY?.let { range ->
        transition += slideOutVertically(
            animationSpec = tween(
                durationMillis = spec.durationMs,
                easing = spec.easing,
            ),
            targetOffsetY = { with(density) { range.to.roundToPx() } },
        )
    }

    spec.offsetX?.let { range ->
        transition += slideOutHorizontally(
            animationSpec = tween(
                durationMillis = spec.durationMs,
                easing = spec.easing,
            ),
            targetOffsetX = { with(density) { range.to.roundToPx() } },
        )
    }

    spec.scale?.let { range ->
        transition += scaleOut(
            animationSpec = tween(
                durationMillis = spec.durationMs,
                easing = spec.easing,
            ),
            targetScale = range.to,
        )
    }

    return transition
}

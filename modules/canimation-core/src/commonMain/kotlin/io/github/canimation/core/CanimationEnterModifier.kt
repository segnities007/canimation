package io.github.canimation.core

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.debugInspectorInfo

internal data class ResolvedAnimationTargets(
    val alpha: Float,
    val offsetX: Float,
    val offsetY: Float,
    val scale: Float,
    val rotation: Float,
)

/**
 * Applies an enter animation to this composable based on [visible] state.
 *
 * When [visible] transitions from `false` to `true`, the element animates in
 * using the specified [preset].
 *
 * @param visible Whether the composable should be visible.
 * @param preset The animation preset to use (default: [CanimationPreset.FadeUp]).
 */
fun Modifier.canimationEnter(
    visible: Boolean,
    preset: CanimationPreset = CanimationPreset.FadeUp,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "canimationEnter"
        properties["visible"] = visible
        properties["preset"] = preset
    },
) {
    val context = LocalCanimationContext.current
    val spec = CanimationSpecResolver.resolve(
        preset = preset,
        level = context.level,
        direction = AnimationDirection.Enter,
        registry = context.presetRegistry,
    )
    applyAnimationSpec(visible = visible, spec = spec)
}

/**
 * Applies an enter animation using custom specs and context-derived reduced-motion defaults.
 *
 * @param visible Whether the composable should be visible.
 * @param fullSpec The full-motion animation spec.
 */
fun Modifier.canimationEnter(
    visible: Boolean,
    fullSpec: CanimationSpec,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "canimationEnter"
        properties["visible"] = visible
    },
) {
    val context = LocalCanimationContext.current
    val spec = CanimationSpecResolver.resolveCustom(
        level = context.level,
        direction = AnimationDirection.Enter,
        fullSpec = fullSpec,
        reducedSpec = fullSpec.toReduced(context.tokens),
    )
    applyAnimationSpec(visible = visible, spec = spec)
}

/**
 * Applies an enter animation using custom specs.
 *
 * @param visible Whether the composable should be visible.
 * @param fullSpec The full-motion animation spec.
 * @param reducedSpec The reduced-motion animation spec.
 */
fun Modifier.canimationEnter(
    visible: Boolean,
    fullSpec: CanimationSpec,
    reducedSpec: CanimationSpec = fullSpec.toReduced(),
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "canimationEnter"
        properties["visible"] = visible
    },
) {
    val context = LocalCanimationContext.current
    val spec = CanimationSpecResolver.resolveCustom(
        level = context.level,
        direction = AnimationDirection.Enter,
        fullSpec = fullSpec,
        reducedSpec = reducedSpec,
    )
    applyAnimationSpec(visible = visible, spec = spec)
}

/**
 * Animates alpha, translation, and scale based on a resolved [CanimationSpec].
 * Uses `graphicsLayer` for draw-phase-only modifications.
 */
@Composable
internal fun Modifier.applyAnimationSpec(
    visible: Boolean,
    spec: CanimationSpec,
): Modifier {
    val tweenSpec = tween<Float>(
        durationMillis = spec.durationMs,
        easing = spec.easing,
    )
    val targets = resolveAnimationTargets(
        visible = visible,
        spec = spec,
    )

    val animatedAlpha by animateFloatAsState(
        targetValue = targets.alpha,
        animationSpec = tweenSpec,
        label = "canimation_alpha",
    )
    val animatedOffsetX by animateFloatAsState(
        targetValue = targets.offsetX,
        animationSpec = tweenSpec,
        label = "canimation_offsetX",
    )
    val animatedOffsetY by animateFloatAsState(
        targetValue = targets.offsetY,
        animationSpec = tweenSpec,
        label = "canimation_offsetY",
    )
    val animatedScale by animateFloatAsState(
        targetValue = targets.scale,
        animationSpec = tweenSpec,
        label = "canimation_scale",
    )
    val animatedRotation by animateFloatAsState(
        targetValue = targets.rotation,
        animationSpec = tweenSpec,
        label = "canimation_rotation",
    )

    return this.graphicsLayer {
        alpha = animatedAlpha
        translationX = animatedOffsetX * density
        translationY = animatedOffsetY * density
        scaleX = animatedScale
        scaleY = animatedScale
        rotationZ = animatedRotation
    }
}

internal fun resolveAnimationTargets(
    visible: Boolean,
    spec: CanimationSpec,
): ResolvedAnimationTargets = ResolvedAnimationTargets(
    alpha = if (visible) spec.alpha?.to ?: 1f else spec.alpha?.from ?: 0f,
    offsetX = if (visible) spec.offsetX?.to?.value ?: 0f else spec.offsetX?.from?.value ?: 0f,
    offsetY = if (visible) spec.offsetY?.to?.value ?: 0f else spec.offsetY?.from?.value ?: 0f,
    scale = if (visible) spec.scale?.to ?: 1f else spec.scale?.from ?: 1f,
    rotation = if (visible) spec.rotation?.to ?: 0f else spec.rotation?.from ?: 0f,
)

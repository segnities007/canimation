package io.github.canimation.core

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo

/**
 * Unified animation modifier using [CanimationEffect].
 *
 * Recommended API for applying animations with composable effects:
 * ```kotlin
 * // Simple fade up
 * Modifier.canimation(visible, Canimation.Fade.Up)
 *
 * // Combine effects with +
 * Modifier.canimation(visible, CanimationEffect.fade() + CanimationEffect.scale(0.9f))
 *
 * // Use hierarchical namespace
 * Modifier.canimation(visible, Canimation.Scale.Pop + Canimation.Fade.In)
 * ```
 *
 * @param visible Whether the composable should be visible.
 * @param effect The animation effect to apply.
 */
fun Modifier.canimation(
    visible: Boolean,
    effect: CanimationEffect,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "canimation"
        properties["visible"] = visible
        properties["effect"] = effect
    },
) {
    val context = LocalCanimationContext.current
    val defaultEasing = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)

    val spec = when (context.level) {
        CanimationLevel.Full -> effect.toEnterSpec(defaultEasing)
        CanimationLevel.Reduced -> effect.toEnterSpec(defaultEasing).toReduced()
        CanimationLevel.Off -> effect.toEnterSpec(defaultEasing).copy(durationMs = 0)
    }
    applyAnimationSpec(visible = visible, spec = spec)
}

/**
 * Transition modifier using [CanimationEffect] for both enter and exit.
 *
 * ```kotlin
 * Modifier.canimationTransition(
 *     visible = isVisible,
 *     enter = Canimation.Fade.Up,
 *     exit = Canimation.Fade.Down,
 * )
 * ```
 *
 * @param visible Whether the composable should be visible.
 * @param enter Effect for enter animation.
 * @param exit Effect for exit animation (if null, reverses the enter effect).
 */
fun Modifier.canimationTransition(
    visible: Boolean,
    enter: CanimationEffect,
    exit: CanimationEffect? = null,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "canimationTransition"
        properties["visible"] = visible
    },
) {
    val context = LocalCanimationContext.current
    val defaultEasing = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)

    val enterSpec = enter.toEnterSpec(defaultEasing)
    val exitSpec = exit?.toEnterSpec(defaultEasing) ?: enterSpec.reversed()

    val spec = when {
        context.level == CanimationLevel.Off -> enterSpec.copy(durationMs = 0)
        context.level == CanimationLevel.Reduced && visible -> enterSpec.toReduced()
        context.level == CanimationLevel.Reduced && !visible -> exitSpec.toReduced()
        visible -> enterSpec
        else -> exitSpec
    }
    applyAnimationSpec(visible = visible, spec = spec)
}

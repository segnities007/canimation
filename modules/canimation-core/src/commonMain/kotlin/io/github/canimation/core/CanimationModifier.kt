package io.github.canimation.core

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
): Modifier =
    composed(
        inspectorInfo =
            debugInspectorInfo {
                name = "canimation"
                properties["visible"] = visible
                properties["effect"] = effect
            },
    ) {
        val context = LocalCanimationContext.current
        val spec =
            CanimationSpecResolver.resolveEffect(
                effect = effect,
                level = context.level,
                tokens = context.tokens,
            )
        applyAnimationSpec(visible = visible, spec = spec)
    }

/**
 * Semantics-first modifier API backed by a [CanimationRecipe].
 */
fun Modifier.canimation(
    recipe: CanimationRecipe,
    visible: Boolean = true,
): Modifier =
    composed(
        inspectorInfo =
            debugInspectorInfo {
                name = "canimation"
                properties["visible"] = visible
                properties["recipe"] = recipe.id.value
            },
    ) {
        val context = LocalCanimationContext.current
        val spec =
            CanimationSpecResolver.resolveRecipe(
                recipe = recipe,
                level = context.level,
                registry = context.recipeRegistry,
            )
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
): Modifier =
    composed(
        inspectorInfo =
            debugInspectorInfo {
                name = "canimationTransition"
                properties["visible"] = visible
            },
    ) {
        val context = LocalCanimationContext.current
        val spec =
            CanimationSpecResolver.resolveTransitionEffect(
                visible = visible,
                level = context.level,
                enter = enter,
                exit = exit,
                tokens = context.tokens,
            )
        applyAnimationSpec(visible = visible, spec = spec)
    }

/**
 * Semantics-first transition modifier using recipe descriptors.
 */
fun Modifier.canimationTransition(
    visible: Boolean,
    enter: CanimationRecipe,
    exit: CanimationRecipe = enter,
): Modifier =
    composed(
        inspectorInfo =
            debugInspectorInfo {
                name = "canimationTransition"
                properties["visible"] = visible
                properties["enterRecipe"] = enter.id.value
                properties["exitRecipe"] = exit.id.value
            },
    ) {
        val context = LocalCanimationContext.current
        val spec =
            CanimationSpecResolver.resolveRecipe(
                recipe = if (visible) enter else exit,
                level = context.level,
                registry = context.recipeRegistry,
            )
        applyAnimationSpec(visible = visible, spec = spec)
    }

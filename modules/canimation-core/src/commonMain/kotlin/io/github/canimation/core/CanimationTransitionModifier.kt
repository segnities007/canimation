package io.github.canimation.core

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo

/**
 * Applies both enter and exit animations to this composable based on [visible] state.
 *
 * Combines [canimationEnter] and [canimationExit] behavior using separate presets
 * for each direction.
 *
 * @param visible Whether the composable should be visible.
 * @param enterPreset The enter animation preset (default: [CanimationPreset.FadeUp]).
 * @param exitPreset The exit animation preset (default: same as [enterPreset]).
 */
fun Modifier.canimationTransition(
    visible: Boolean,
    enterPreset: CanimationPreset = CanimationPreset.FadeUp,
    exitPreset: CanimationPreset = enterPreset,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "canimationTransition"
        properties["visible"] = visible
        properties["enterPreset"] = enterPreset
        properties["exitPreset"] = exitPreset
    },
) {
    val context = LocalCanimationContext.current
    val spec = if (visible) {
        CanimationSpecResolver.resolve(
            preset = enterPreset,
            level = context.level,
            direction = AnimationDirection.Enter,
            registry = context.presetRegistry,
        )
    } else {
        CanimationSpecResolver.resolve(
            preset = exitPreset,
            level = context.level,
            direction = AnimationDirection.Exit,
            registry = context.presetRegistry,
        )
    }
    // The spec is already selected for the correct direction (enter or exit).
    // Exit specs define to=hidden endpoint, so always animate toward spec.to.
    applyAnimationSpec(visible = true, spec = spec)
}

/**
 * Applies both enter and exit animations using custom specs.
 *
 * @param visible Whether the composable should be visible.
 * @param enterFullSpec The full-motion enter spec.
 * @param enterReducedSpec The reduced-motion enter spec.
 * @param exitFullSpec The full-motion exit spec.
 * @param exitReducedSpec The reduced-motion exit spec.
 */
fun Modifier.canimationTransition(
    visible: Boolean,
    enterFullSpec: CanimationSpec,
    enterReducedSpec: CanimationSpec = enterFullSpec.toReduced(),
    exitFullSpec: CanimationSpec = enterFullSpec.reversed(),
    exitReducedSpec: CanimationSpec = enterReducedSpec.reversed(),
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "canimationTransition"
        properties["visible"] = visible
    },
) {
    val context = LocalCanimationContext.current
    val (fullSpec, reducedSpec) = if (visible) {
        enterFullSpec to enterReducedSpec
    } else {
        exitFullSpec to exitReducedSpec
    }
    val direction = if (visible) AnimationDirection.Enter else AnimationDirection.Exit
    val spec = CanimationSpecResolver.resolveCustom(
        level = context.level,
        direction = direction,
        fullSpec = fullSpec,
        reducedSpec = reducedSpec,
    )
    applyAnimationSpec(visible = true, spec = spec)
}

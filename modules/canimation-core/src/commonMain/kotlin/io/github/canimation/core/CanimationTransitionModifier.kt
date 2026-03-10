package io.github.canimation.core

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo

internal data class ResolvedTransitionSpec(
    val spec: CanimationSpec,
    val applyAsVisible: Boolean,
)

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
    val resolved = resolveTransitionPresetSpec(
        visible = visible,
        level = context.level,
        registry = context.presetRegistry,
        enterPreset = enterPreset,
        exitPreset = exitPreset,
    )
    applyAnimationSpec(visible = resolved.applyAsVisible, spec = resolved.spec)
}

/**
 * Applies both enter and exit animations using context-derived reduced-motion defaults.
 *
 * @param visible Whether the composable should be visible.
 * @param enterFullSpec The full-motion enter spec.
 */
fun Modifier.canimationTransition(
    visible: Boolean,
    enterFullSpec: CanimationSpec,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "canimationTransition"
        properties["visible"] = visible
    },
) {
    val context = LocalCanimationContext.current
    val enterReducedSpec = enterFullSpec.toReduced(context.tokens)
    val resolved = resolveTransitionCustomSpec(
        visible = visible,
        level = context.level,
        enterFullSpec = enterFullSpec,
        enterReducedSpec = enterReducedSpec,
        exitFullSpec = enterFullSpec.reversed(context.tokens),
        exitReducedSpec = enterReducedSpec.reversed(context.tokens),
    )
    applyAnimationSpec(visible = resolved.applyAsVisible, spec = resolved.spec)
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
    val resolved = resolveTransitionCustomSpec(
        visible = visible,
        level = context.level,
        enterFullSpec = enterFullSpec,
        enterReducedSpec = enterReducedSpec,
        exitFullSpec = exitFullSpec,
        exitReducedSpec = exitReducedSpec,
    )
    applyAnimationSpec(visible = resolved.applyAsVisible, spec = resolved.spec)
}

internal fun resolveTransitionPresetSpec(
    visible: Boolean,
    level: CanimationLevel,
    registry: PresetRegistry,
    enterPreset: CanimationPreset,
    exitPreset: CanimationPreset,
): ResolvedTransitionSpec {
    val direction = if (visible) AnimationDirection.Enter else AnimationDirection.Exit
    val preset = if (visible) enterPreset else exitPreset
    val spec = CanimationSpecResolver.resolve(
        preset = preset,
        level = level,
        direction = direction,
        registry = registry,
    )
    return ResolvedTransitionSpec(
        spec = spec,
        applyAsVisible = true,
    )
}

internal fun resolveTransitionCustomSpec(
    visible: Boolean,
    level: CanimationLevel,
    enterFullSpec: CanimationSpec,
    enterReducedSpec: CanimationSpec,
    exitFullSpec: CanimationSpec,
    exitReducedSpec: CanimationSpec,
): ResolvedTransitionSpec {
    val (fullSpec, reducedSpec) = if (visible) {
        enterFullSpec to enterReducedSpec
    } else {
        exitFullSpec to exitReducedSpec
    }
    val direction = if (visible) AnimationDirection.Enter else AnimationDirection.Exit
    return ResolvedTransitionSpec(
        spec = CanimationSpecResolver.resolveCustom(
            level = level,
            direction = direction,
            fullSpec = fullSpec,
            reducedSpec = reducedSpec,
        ),
        applyAsVisible = true,
    )
}

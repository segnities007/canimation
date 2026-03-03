package io.github.canimation.core

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo

/**
 * Applies an exit animation to this composable based on [visible] state.
 *
 * When [visible] transitions from `true` to `false`, the element animates out
 * using the specified [preset].
 *
 * @param visible Whether the composable should be visible.
 * @param preset The animation preset to use (default: [CanimationPreset.FadeUp]).
 */
fun Modifier.canimationExit(
    visible: Boolean,
    preset: CanimationPreset = CanimationPreset.FadeUp,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "canimationExit"
        properties["visible"] = visible
        properties["preset"] = preset
    },
) {
    val context = LocalCanimationContext.current
    val spec = CanimationSpecResolver.resolve(
        preset = preset,
        level = context.level,
        direction = AnimationDirection.Exit,
        registry = context.presetRegistry,
    )
    // Exit specs define from=visible, to=hidden — flip visible so applyAnimationSpec
    // targets the correct direction (visible→from=rest, !visible→to=exit).
    applyAnimationSpec(visible = !visible, spec = spec)
}

/**
 * Applies an exit animation using custom specs.
 *
 * @param visible Whether the composable should be visible.
 * @param fullSpec The full-motion exit spec.
 * @param reducedSpec The reduced-motion exit spec.
 */
fun Modifier.canimationExit(
    visible: Boolean,
    fullSpec: CanimationSpec,
    reducedSpec: CanimationSpec = fullSpec.toReduced(),
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "canimationExit"
        properties["visible"] = visible
    },
) {
    val context = LocalCanimationContext.current
    val spec = CanimationSpecResolver.resolveCustom(
        level = context.level,
        direction = AnimationDirection.Exit,
        fullSpec = fullSpec,
        reducedSpec = reducedSpec,
    )
    applyAnimationSpec(visible = !visible, spec = spec)
}

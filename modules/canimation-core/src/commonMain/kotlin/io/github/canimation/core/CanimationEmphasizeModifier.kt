package io.github.canimation.core

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo

/**
 * Applies an emphasis animation (e.g., pulse, highlight) based on [active] state.
 *
 * Useful for drawing attention to interactive elements like selected cards or buttons.
 *
 * @param active Whether the emphasis effect is active.
 * @param preset The animation preset to use (default: [CanimationPreset.ScaleIn]).
 */
fun Modifier.canimationEmphasize(
    active: Boolean,
    preset: CanimationPreset = CanimationPreset.ScaleIn,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "canimationEmphasize"
        properties["active"] = active
        properties["preset"] = preset
    },
) {
    val context = LocalCanimationContext.current
    val resolved = resolveEmphasizeSpec(
        active = active,
        preset = preset,
        level = context.level,
        registry = context.presetRegistry,
    )
    applyAnimationSpec(visible = resolved.applyAsVisible, spec = resolved.spec)
}

internal fun resolveEmphasizeSpec(
    active: Boolean,
    preset: CanimationPreset,
    level: CanimationLevel,
    registry: PresetRegistry,
): ResolvedTransitionSpec {
    val direction = if (active) AnimationDirection.Enter else AnimationDirection.Exit
    val spec = CanimationSpecResolver.resolve(
        preset = preset,
        level = level,
        direction = direction,
        registry = registry,
    )
    return ResolvedTransitionSpec(
        spec = spec,
        applyAsVisible = direction != AnimationDirection.Exit || !active,
    )
}

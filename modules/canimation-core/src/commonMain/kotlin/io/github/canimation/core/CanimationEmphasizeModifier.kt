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
    val direction = if (active) AnimationDirection.Enter else AnimationDirection.Exit
    val spec = CanimationSpecResolver.resolve(
        preset = preset,
        level = context.level,
        direction = direction,
        registry = context.presetRegistry,
    )
    // Enter: active=true → animate toward spec.to (emphasized)
    // Exit: active=false → animate toward spec.to (de-emphasized), so flip to !active=true
    val effectiveVisible = if (direction == AnimationDirection.Exit) !active else active
    applyAnimationSpec(visible = effectiveVisible, spec = spec)
}

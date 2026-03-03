package io.github.canimation.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * Provides animation configuration to the composable tree.
 *
 * Wrap your application or a subtree to control animation tokens, policy, and presets.
 * When omitted, safe defaults are used (Full motion, default tokens, built-in presets).
 *
 * @param tokens Animation timing, easing, distance, and spring tokens.
 * @param policy Policy for determining animation level from system preferences.
 * @param presetRegistry Registry of preset animation specifications.
 * @param systemReducedMotion Whether the OS has reduce-motion enabled. Provide from platform module.
 * @param content The composable content tree.
 */
@Composable
fun CanimationProvider(
    tokens: CanimationTokens = CanimationTokens.Default,
    policy: CanimationPolicy = CanimationPolicy.SystemAware,
    presetRegistry: PresetRegistry = PresetRegistry.Default,
    systemReducedMotion: Boolean? = null,
    content: @Composable () -> Unit,
) {
    val level = CanimationPolicyResolver.resolve(policy, systemReducedMotion)
    val context = CanimationContext(
        tokens = tokens,
        level = level,
        presetRegistry = presetRegistry,
    )
    CompositionLocalProvider(LocalCanimationContext provides context) {
        content()
    }
}

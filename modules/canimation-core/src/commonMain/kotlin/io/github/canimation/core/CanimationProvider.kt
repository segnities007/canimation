package io.github.canimation.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * Provides animation configuration to the composable tree.
 *
 * Wrap your application or a subtree to control animation tokens, policy, and presets.
 * When omitted, safe defaults are used:
 * `policy = SystemAware` and `systemReducedMotion = null` (resolved to reduced motion).
 *
 * @param tokens Animation timing, easing, distance, and spring tokens.
 * @param policy Policy for determining animation level from system preferences.
 * @param presetRegistry Registry of preset animation specifications.
 * @param systemReducedMotion Whether the OS has reduce-motion enabled. Provide from a platform
 * module when available; `null` is treated as reduced motion to preserve the accessibility-first
 * safe fallback.
 * @param content The composable content tree.
 */
@Composable
fun CanimationProvider(
    tokens: CanimationTokens = CanimationTokens.Default,
    policy: CanimationPolicy = CanimationPolicy.SystemAware,
    presetRegistry: PresetRegistry = PresetRegistry.Default,
    recipeRegistry: CanimationRecipeRegistry = CanimationRecipeRegistry.Empty,
    systemReducedMotion: Boolean? = null,
    content: @Composable () -> Unit,
) {
    val context =
        resolveCanimationContext(
            tokens = tokens,
            policy = policy,
            presetRegistry = presetRegistry,
            recipeRegistry = recipeRegistry,
            systemReducedMotion = systemReducedMotion,
        )
    CompositionLocalProvider(LocalCanimationContext provides context) {
        content()
    }
}

internal fun resolveCanimationContext(
    tokens: CanimationTokens,
    policy: CanimationPolicy,
    presetRegistry: PresetRegistry,
    recipeRegistry: CanimationRecipeRegistry,
    systemReducedMotion: Boolean?,
): CanimationContext =
    CanimationContext(
        tokens = tokens,
        level = CanimationPolicyResolver.resolve(policy, systemReducedMotion),
        presetRegistry = presetRegistry,
        recipeRegistry = recipeRegistry,
    )

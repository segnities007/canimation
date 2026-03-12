package io.github.canimation.compat

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.CanimationSpec
import io.github.canimation.core.CanimationTokens
import io.github.canimation.runtime.DefaultCanimationPresetRegistry
import io.github.canimation.runtime.DefaultCanimationRecipeRegistry
import io.github.canimation.core.CanimationProvider as CoreCanimationProvider
import io.github.canimation.core.CanimationVisibility as CoreCanimationVisibility
import io.github.canimation.core.canimation as coreCanimation
import io.github.canimation.core.canimationEnter as coreCanimationEnter
import io.github.canimation.core.canimationExit as coreCanimationExit
import io.github.canimation.core.canimationTransition as coreCanimationTransition

private const val COMPATIBILITY_MIGRATION_MESSAGE =
    "Compatibility API keeps preset/effect-first entry points available during migration. " +
        "Migrate to io.github.canimation.runtime semantics-first APIs and map preset/effect usage to semantic recipes such as Canimation.Content, Canimation.Feedback, or Canimation.Navigation."

@Deprecated(
    message = COMPATIBILITY_MIGRATION_MESSAGE,
    replaceWith =
        ReplaceWith(
            expression =
                "CanimationProvider(tokens = tokens, policy = policy, presetRegistry = presetRegistry, recipeRegistry = recipeRegistry, systemReducedMotion = systemReducedMotion, content = content)",
            imports = arrayOf("io.github.canimation.runtime.CanimationProvider"),
        ),
)
@Composable
fun CanimationProvider(
    tokens: CanimationTokens = CanimationTokens.Default,
    policy: CanimationPolicy = CanimationPolicy.SystemAware,
    presetRegistry: PresetRegistry = DefaultCanimationPresetRegistry,
    recipeRegistry: CanimationRecipeRegistry = DefaultCanimationRecipeRegistry,
    systemReducedMotion: Boolean? = null,
    content: @Composable () -> Unit,
) {
    CoreCanimationProvider(
        tokens = tokens,
        policy = policy,
        presetRegistry = presetRegistry,
        recipeRegistry = recipeRegistry,
        systemReducedMotion = systemReducedMotion,
        content = content,
    )
}

@Deprecated(
    message = COMPATIBILITY_MIGRATION_MESSAGE,
    replaceWith =
        ReplaceWith(
            expression = "this.canimation(visible = visible, effect = effect)",
            imports = arrayOf("io.github.canimation.runtime.canimation"),
        ),
)
fun Modifier.canimation(
    visible: Boolean,
    effect: CanimationEffect,
): Modifier = this.coreCanimation(visible = visible, effect = effect)

@Deprecated(message = COMPATIBILITY_MIGRATION_MESSAGE)
fun Modifier.canimationEnter(
    visible: Boolean,
    preset: CanimationPreset = CanimationPreset.FadeUp,
): Modifier = this.coreCanimationEnter(visible = visible, preset = preset)

@Deprecated(message = COMPATIBILITY_MIGRATION_MESSAGE)
fun Modifier.canimationExit(
    visible: Boolean,
    preset: CanimationPreset = CanimationPreset.FadeUp,
): Modifier = this.coreCanimationExit(visible = visible, preset = preset)

@Deprecated(message = COMPATIBILITY_MIGRATION_MESSAGE)
fun Modifier.canimationTransition(
    visible: Boolean,
    enterPreset: CanimationPreset = CanimationPreset.FadeUp,
    exitPreset: CanimationPreset = enterPreset,
): Modifier = this.coreCanimationTransition(visible = visible, enterPreset = enterPreset, exitPreset = exitPreset)

@Deprecated(message = COMPATIBILITY_MIGRATION_MESSAGE)
fun Modifier.canimationTransition(
    visible: Boolean,
    enterFullSpec: CanimationSpec,
): Modifier = this.coreCanimationTransition(visible = visible, enterFullSpec = enterFullSpec)

@Deprecated(
    message = COMPATIBILITY_MIGRATION_MESSAGE,
    replaceWith =
        ReplaceWith(
            expression = "CanimationVisibility(visible = visible, recipe = recipe, content = content)",
            imports = arrayOf("io.github.canimation.runtime.CanimationVisibility"),
        ),
)
@Composable
fun CanimationVisibility(
    visible: Boolean,
    enterPreset: CanimationPreset = CanimationPreset.FadeUp,
    exitPreset: CanimationPreset = enterPreset,
    content: @Composable () -> Unit,
) {
    CoreCanimationVisibility(
        visible = visible,
        enterPreset = enterPreset,
        exitPreset = exitPreset,
        content = content,
    )
}

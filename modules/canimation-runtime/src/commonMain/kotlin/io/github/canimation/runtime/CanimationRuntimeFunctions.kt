package io.github.canimation.runtime

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationRecipe
import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.CanimationSpec
import io.github.canimation.core.CanimationTokens
import io.github.canimation.core.PresetRegistry
import io.github.canimation.recipes.DefaultCanimationRecipeRegistry
import io.github.canimation.core.CanimationProvider as CoreCanimationProvider
import io.github.canimation.core.CanimationTransition as CoreCanimationTransition
import io.github.canimation.core.CanimationVisibility as CoreCanimationVisibility
import io.github.canimation.core.canimation as coreCanimation
import io.github.canimation.core.canimationTransition as coreCanimationTransition

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

fun Modifier.canimation(
    visible: Boolean,
    recipe: CanimationRecipe,
): Modifier = this.coreCanimation(recipe = recipe, visible = visible)

fun Modifier.canimation(
    visible: Boolean,
    effect: CanimationEffect,
): Modifier = this.coreCanimation(visible = visible, effect = effect)

fun Modifier.canimationTransition(
    visible: Boolean,
    enter: CanimationRecipe,
    exit: CanimationRecipe = enter,
): Modifier = this.coreCanimationTransition(visible = visible, enter = enter, exit = exit)

fun Modifier.canimationTransition(
    visible: Boolean,
    enterFullSpec: CanimationSpec,
): Modifier = this.coreCanimationTransition(visible = visible, enterFullSpec = enterFullSpec)

@Composable
fun CanimationVisibility(
    visible: Boolean,
    recipe: CanimationRecipe,
    content: @Composable () -> Unit,
) {
    CoreCanimationVisibility(
        visible = visible,
        recipe = recipe,
        content = content,
    )
}

@Composable
fun CanimationTransition(
    visible: Boolean,
    recipe: CanimationRecipe,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit,
) {
    CoreCanimationTransition(
        visible = visible,
        recipe = recipe,
        modifier = modifier,
        content = content,
    )
}

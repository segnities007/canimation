package io.github.canimation.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CanimationTransition(
    visible: Boolean,
    recipe: CanimationRecipe,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit,
) {
    content(modifier.canimation(recipe = recipe, visible = visible))
}

@Composable
fun CanimationTransition(
    visible: Boolean,
    enter: CanimationRecipe,
    exit: CanimationRecipe = enter,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit,
) {
    content(modifier.canimationTransition(visible = visible, enter = enter, exit = exit))
}

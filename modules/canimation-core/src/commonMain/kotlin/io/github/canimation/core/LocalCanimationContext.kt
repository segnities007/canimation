package io.github.canimation.core

import androidx.compose.runtime.compositionLocalOf

/**
 * CompositionLocal providing the current [CanimationContext].
 *
 * Defaults to [CanimationContext.Default] when no [CanimationProvider] is in the tree,
 * ensuring animations always work even without explicit configuration.
 */
val LocalCanimationContext = compositionLocalOf { CanimationContext.Default }

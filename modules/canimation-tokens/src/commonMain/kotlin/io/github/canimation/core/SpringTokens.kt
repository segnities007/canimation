package io.github.canimation.core

import androidx.compose.runtime.Immutable

/**
 * Spring animation tokens.
 *
 * @property gentle A gentle spring with slower settling (dampingRatio=0.85, stiffness=280f).
 * @property snappy A snappy spring with faster response (dampingRatio=0.80, stiffness=420f).
 */
@Immutable
data class SpringTokens(
    val gentle: SpringSpec,
    val snappy: SpringSpec,
) {
    companion object {
        val Default =
            SpringTokens(
                gentle = SpringSpec(dampingRatio = 0.85f, stiffness = 280f),
                snappy = SpringSpec(dampingRatio = 0.80f, stiffness = 420f),
            )
    }
}

/**
 * Simplified spring specification holding damping ratio and stiffness.
 */
@Immutable
data class SpringSpec(
    val dampingRatio: Float,
    val stiffness: Float,
)

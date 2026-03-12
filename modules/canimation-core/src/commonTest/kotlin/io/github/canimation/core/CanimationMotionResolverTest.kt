package io.github.canimation.core

import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

class CanimationMotionResolverTest {
    private val tokens = CanimationTokens.Default

    @Test
    fun resolveEffectUsesReducedDerivedSpec() {
        val effect = CanimationEffect.slideUp(20.dp)
        val expected = effect.toEnterSpec(tokens).toReduced(tokens)

        val resolved =
            CanimationSpecResolver.resolveEffect(
                effect = effect,
                level = CanimationLevel.Reduced,
                tokens = tokens,
            )

        assertEquals(expected, resolved)
    }

    @Test
    fun resolveRecipeUsesRegistryResolvedOffSpec() {
        val overrideDescriptor =
            Canimation.Content.EnterSubtle.descriptor.copy(
                specs =
                    CanimationRecipeSpecSet(
                        full =
                            CanimationSpec(
                                durationMs = 240,
                                easing = EasingTokens.Default.standard,
                                alpha = CanimationRange(from = 0f, to = 1f),
                            ),
                        reduced =
                            CanimationSpec(
                                durationMs = 120,
                                easing = EasingTokens.Default.decelerate,
                                alpha = CanimationRange(from = 0.4f, to = 1f),
                            ),
                        off =
                            CanimationSpec(
                                durationMs = 0,
                                easing = EasingTokens.Default.standard,
                                alpha = CanimationRange(from = 1f, to = 1f),
                            ),
                    ),
            )
        val recipe = Canimation.Content.EnterSubtle
        val registry =
            CanimationRecipeRegistry(
                mapOf(recipe.id to overrideDescriptor),
            )

        val resolved =
            CanimationSpecResolver.resolveRecipe(
                recipe = recipe,
                level = CanimationLevel.Off,
                registry = registry,
            )

        assertEquals(overrideDescriptor.specs.off, resolved)
    }

    @Test
    fun resolveTransitionEffectUsesProvidedExitEffectWhenHidden() {
        val enter = CanimationEffect.slideUp(24.dp)
        val exit = CanimationEffect.slideDown(12.dp)
        val expected = exit.toEnterSpec(tokens)

        val resolved =
            CanimationSpecResolver.resolveTransitionEffect(
                visible = false,
                level = CanimationLevel.Full,
                enter = enter,
                exit = exit,
                tokens = tokens,
            )

        assertEquals(expected, resolved)
    }

    @Test
    fun resolveTransitionEffectFallsBackToReversedEnterEffectWhenExitEffectMissing() {
        val enter = CanimationEffect.slideLeft(18.dp)
        val expected = enter.toEnterSpec(tokens).reversed(tokens)

        val resolved =
            CanimationSpecResolver.resolveTransitionEffect(
                visible = false,
                level = CanimationLevel.Full,
                enter = enter,
                exit = null,
                tokens = tokens,
            )

        assertEquals(expected, resolved)
    }

    @Test
    fun resolveTransitionEffectKeepsEnterOffSpecWhenMotionIsOff() {
        val enter = CanimationEffect.slideUp(16.dp)
        val exit = CanimationEffect.slideDown(8.dp)
        val expected = enter.toEnterSpec(tokens).copy(durationMs = 0)

        val resolved =
            CanimationSpecResolver.resolveTransitionEffect(
                visible = false,
                level = CanimationLevel.Off,
                enter = enter,
                exit = exit,
                tokens = tokens,
            )

        assertEquals(expected, resolved)
    }
}

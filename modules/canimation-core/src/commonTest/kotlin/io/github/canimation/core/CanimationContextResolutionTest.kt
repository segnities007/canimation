package io.github.canimation.core

import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.unit.dp
import io.github.canimation.recipes.DefaultCanimationRecipeRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.time.Duration.Companion.milliseconds

class CanimationContextResolutionTest {
    private val customTokens =
        CanimationTokens(
            duration =
                DurationTokens(
                    short = 90.milliseconds,
                    medium = 180.milliseconds,
                    long = 360.milliseconds,
                ),
            easing =
                EasingTokens(
                    standard = LinearEasing,
                    emphasizedDecelerate = LinearEasing,
                    emphasizedAccelerate = LinearEasing,
                    decelerate = LinearEasing,
                    accelerate = LinearEasing,
                ),
            distance =
                DistanceTokens(
                    small = 4.dp,
                    medium = 8.dp,
                    large = 16.dp,
                ),
            spring = SpringTokens.Default,
        )

    private val customRegistry = PresetRegistry.create(emptyMap())
    private val customRecipeRegistry = DefaultCanimationRecipeRegistry

    @Test
    fun resolveContextUsesPolicyResolvedLevel() {
        val context =
            resolveCanimationContext(
                tokens = CanimationTokens.Default,
                policy = CanimationPolicy.SystemAware,
                presetRegistry = PresetRegistry.Default,
                recipeRegistry = DefaultCanimationRecipeRegistry,
                systemReducedMotion = true,
            )

        assertEquals(CanimationLevel.Reduced, context.level)
    }

    @Test
    fun resolveContextPreservesProvidedTokensAndRegistry() {
        val context =
            resolveCanimationContext(
                tokens = customTokens,
                policy = CanimationPolicy.AlwaysFull,
                presetRegistry = customRegistry,
                recipeRegistry = customRecipeRegistry,
                systemReducedMotion = null,
            )

        assertSame(customTokens, context.tokens)
        assertSame(customRegistry, context.presetRegistry)
        assertSame(customRecipeRegistry, context.recipeRegistry)
        assertEquals(CanimationLevel.Full, context.level)
    }

    @Test
    fun defaultContextRemainsFullWithDefaultDependencies() {
        assertSame(CanimationTokens.Default, CanimationContext.Default.tokens)
        assertSame(PresetRegistry.Default, CanimationContext.Default.presetRegistry)
        assertSame(CanimationRecipeRegistry.Empty, CanimationContext.Default.recipeRegistry)
        assertEquals(CanimationLevel.Full, CanimationContext.Default.level)
    }
}

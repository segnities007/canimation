package com.segnities007.canimation.screen.examples.component.registry

import com.segnities007.canimation.screen.examples.component.demos.*
import com.segnities007.canimation.screen.examples.data.ComponentDemoKey
import com.segnities007.canimation.screen.examples.data.ComponentDemoKeys

import androidx.compose.runtime.Composable

internal val cardAndIndicatorComponentDemos: Map<ComponentDemoKey, @Composable () -> Unit> = mapOf(
    ComponentDemoKeys.CardBorderTrace to { CardBorderTrace() },
    ComponentDemoKeys.CardLiftHover to { CardLiftHover() },
    ComponentDemoKeys.CardGradientBorder to { CardGradientBorder() },
    ComponentDemoKeys.CardExpandCollapse to { CardExpandCollapse() },
    ComponentDemoKeys.CardParallaxTilt to { CardParallaxTilt() },
    ComponentDemoKeys.CardGlassmorphism to { CardGlassmorphism() },
    ComponentDemoKeys.CardRevealWipe to { CardRevealWipe() },
    ComponentDemoKeys.CardFanStack to { CardFanStack() },
    ComponentDemoKeys.CardMagneticSnap to { CardMagneticSnap() },
    ComponentDemoKeys.GlowProgressBar to { GlowProgressBar() },
    ComponentDemoKeys.PulseRadar to { PulseRadar() },
    ComponentDemoKeys.MorphProgressIndicator to { MorphProgressIndicator() },
    ComponentDemoKeys.AnimatedUnderlineText to { AnimatedUnderlineText() },
    ComponentDemoKeys.SpringChip to { SpringChip() },
    ComponentDemoKeys.CoinFlip to { CoinFlip() },
    ComponentDemoKeys.AnimatedPieChart to { AnimatedPieChart() },
    ComponentDemoKeys.PendulumSwing to { PendulumSwing() },
    ComponentDemoKeys.BouncingBall to { BouncingBall() },
    ComponentDemoKeys.CircularMenu to { CircularMenu() },
    ComponentDemoKeys.AnimatedBarChart to { AnimatedBarChart() },
    ComponentDemoKeys.SlinkySpring to { SlinkySpring() },
    ComponentDemoKeys.TypewriterDelete to { TypewriterDelete() },
    ComponentDemoKeys.AnimatedGradientText to { AnimatedGradientText() },
)

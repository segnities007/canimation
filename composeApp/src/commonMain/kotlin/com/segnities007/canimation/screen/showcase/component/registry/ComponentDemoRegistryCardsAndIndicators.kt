package com.segnities007.canimation.screen.showcase.component.registry

import com.segnities007.canimation.screen.showcase.component.demos.*
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKey
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKeys

import androidx.compose.runtime.Composable

internal val cardAndIndicatorComponentDemos: Map<ShowcaseComponentDemoKey, @Composable () -> Unit> = mapOf(
    ShowcaseComponentDemoKeys.CardBorderTrace to { CardBorderTrace() },
    ShowcaseComponentDemoKeys.CardLiftHover to { CardLiftHover() },
    ShowcaseComponentDemoKeys.CardGradientBorder to { CardGradientBorder() },
    ShowcaseComponentDemoKeys.CardExpandCollapse to { CardExpandCollapse() },
    ShowcaseComponentDemoKeys.CardParallaxTilt to { CardParallaxTilt() },
    ShowcaseComponentDemoKeys.CardGlassmorphism to { CardGlassmorphism() },
    ShowcaseComponentDemoKeys.CardRevealWipe to { CardRevealWipe() },
    ShowcaseComponentDemoKeys.CardFanStack to { CardFanStack() },
    ShowcaseComponentDemoKeys.CardMagneticSnap to { CardMagneticSnap() },
    ShowcaseComponentDemoKeys.GlowProgressBar to { GlowProgressBar() },
    ShowcaseComponentDemoKeys.PulseRadar to { PulseRadar() },
    ShowcaseComponentDemoKeys.MorphProgressIndicator to { MorphProgressIndicator() },
    ShowcaseComponentDemoKeys.AnimatedUnderlineText to { AnimatedUnderlineText() },
    ShowcaseComponentDemoKeys.SpringChip to { SpringChip() },
    ShowcaseComponentDemoKeys.CoinFlip to { CoinFlip() },
    ShowcaseComponentDemoKeys.AnimatedPieChart to { AnimatedPieChart() },
    ShowcaseComponentDemoKeys.PendulumSwing to { PendulumSwing() },
    ShowcaseComponentDemoKeys.BouncingBall to { BouncingBall() },
    ShowcaseComponentDemoKeys.CircularMenu to { CircularMenu() },
    ShowcaseComponentDemoKeys.AnimatedBarChart to { AnimatedBarChart() },
    ShowcaseComponentDemoKeys.SlinkySpring to { SlinkySpring() },
    ShowcaseComponentDemoKeys.TypewriterDelete to { TypewriterDelete() },
    ShowcaseComponentDemoKeys.AnimatedGradientText to { AnimatedGradientText() },
)

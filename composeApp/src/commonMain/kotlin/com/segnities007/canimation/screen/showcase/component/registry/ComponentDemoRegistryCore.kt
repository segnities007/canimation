package com.segnities007.canimation.screen.showcase.component.registry

import com.segnities007.canimation.screen.showcase.component.demos.*
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKey
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKeys

import androidx.compose.runtime.Composable

internal val coreComponentDemos: Map<ShowcaseComponentDemoKey, @Composable () -> Unit> = mapOf(
    ShowcaseComponentDemoKeys.AnimatedCounter to { AnimatedCounter() },
    ShowcaseComponentDemoKeys.ScrambleText to { ScrambleText() },
    ShowcaseComponentDemoKeys.WavyText to { WavyText() },
    ShowcaseComponentDemoKeys.PulseLoadingDots to { PulseLoadingDots() },
    ShowcaseComponentDemoKeys.ShimmerEffect to { ShimmerEffect() },
    ShowcaseComponentDemoKeys.AnimatedTabs to { AnimatedTabs() },
    ShowcaseComponentDemoKeys.FlipCard to { FlipCard() },
    ShowcaseComponentDemoKeys.ColorMorph to { ColorMorph() },
    ShowcaseComponentDemoKeys.ProgressRing to { ProgressRing() },
    ShowcaseComponentDemoKeys.HoldToConfirm to { HoldToConfirm() },
    ShowcaseComponentDemoKeys.SplitTextReveal to { SplitTextReveal() },
    ShowcaseComponentDemoKeys.StaggerFromCenter to { StaggerFromCenter() },
    ShowcaseComponentDemoKeys.TickerMarquee to { TickerMarquee() },
    ShowcaseComponentDemoKeys.BouncySpringList to { BouncySpringList() },
    ShowcaseComponentDemoKeys.SwipeActions to { SwipeActions() },
    ShowcaseComponentDemoKeys.TiltCard to { TiltCard() },
    ShowcaseComponentDemoKeys.PriceSwitcher to { PriceSwitcher() },
    ShowcaseComponentDemoKeys.EngagementStats to { EngagementStats() },
    ShowcaseComponentDemoKeys.MultiStateBadge to { MultiStateBadge() },
)

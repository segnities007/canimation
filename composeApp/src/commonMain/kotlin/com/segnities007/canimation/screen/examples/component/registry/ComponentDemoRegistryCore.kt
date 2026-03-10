package com.segnities007.canimation.screen.examples.component.registry

import com.segnities007.canimation.screen.examples.component.demos.*
import com.segnities007.canimation.screen.examples.data.ComponentDemoKey
import com.segnities007.canimation.screen.examples.data.ComponentDemoKeys

import androidx.compose.runtime.Composable

internal val coreComponentDemos: Map<ComponentDemoKey, @Composable () -> Unit> = mapOf(
    ComponentDemoKeys.AnimatedCounter to { AnimatedCounter() },
    ComponentDemoKeys.ScrambleText to { ScrambleText() },
    ComponentDemoKeys.WavyText to { WavyText() },
    ComponentDemoKeys.PulseLoadingDots to { PulseLoadingDots() },
    ComponentDemoKeys.ShimmerEffect to { ShimmerEffect() },
    ComponentDemoKeys.AnimatedTabs to { AnimatedTabs() },
    ComponentDemoKeys.FlipCard to { FlipCard() },
    ComponentDemoKeys.ColorMorph to { ColorMorph() },
    ComponentDemoKeys.ProgressRing to { ProgressRing() },
    ComponentDemoKeys.HoldToConfirm to { HoldToConfirm() },
    ComponentDemoKeys.SplitTextReveal to { SplitTextReveal() },
    ComponentDemoKeys.StaggerFromCenter to { StaggerFromCenter() },
    ComponentDemoKeys.TickerMarquee to { TickerMarquee() },
    ComponentDemoKeys.BouncySpringList to { BouncySpringList() },
    ComponentDemoKeys.SwipeActions to { SwipeActions() },
    ComponentDemoKeys.TiltCard to { TiltCard() },
    ComponentDemoKeys.PriceSwitcher to { PriceSwitcher() },
    ComponentDemoKeys.EngagementStats to { EngagementStats() },
    ComponentDemoKeys.MultiStateBadge to { MultiStateBadge() },
)

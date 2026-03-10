package com.segnities007.canimation.screen.examples.component.registry

import com.segnities007.canimation.screen.examples.component.demos.*
import com.segnities007.canimation.screen.examples.data.ComponentDemoKey
import com.segnities007.canimation.screen.examples.data.ComponentDemoKeys

import androidx.compose.runtime.Composable

internal val experimentalComponentDemos: Map<ComponentDemoKey, @Composable () -> Unit> = mapOf(
    ComponentDemoKeys.GradientShift to { GradientShift() },
    ComponentDemoKeys.SkeletonLoader to { SkeletonLoader() },
    ComponentDemoKeys.ElasticPull to { ElasticPull() },
    ComponentDemoKeys.ParallaxLayers to { ParallaxLayers() },
    ComponentDemoKeys.PathTracer to { PathTracer() },
    ComponentDemoKeys.CardShuffle to { CardShuffle() },
    ComponentDemoKeys.ConfettiExplosion to { ConfettiExplosion() },
    ComponentDemoKeys.WaveEffect to { WaveEffect() },
    ComponentDemoKeys.LiquidFill to { LiquidFill() },
    ComponentDemoKeys.SlidingReveal to { SlidingReveal() },
    ComponentDemoKeys.FocusBlurEffect to { FocusBlurEffect() },
    ComponentDemoKeys.RollingDigits to { RollingDigits() },
    ComponentDemoKeys.SpringChain to { SpringChain() },
    ComponentDemoKeys.GlitchText to { GlitchText() },
    ComponentDemoKeys.VerticalTicker to { VerticalTicker() },
    ComponentDemoKeys.HeartbeatLine to { HeartbeatLine() },
    ComponentDemoKeys.ExpandingSearch to { ExpandingSearch() },
)

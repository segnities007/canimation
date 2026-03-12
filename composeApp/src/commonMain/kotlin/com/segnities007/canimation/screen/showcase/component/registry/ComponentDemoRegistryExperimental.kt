package com.segnities007.canimation.screen.showcase.component.registry

import com.segnities007.canimation.screen.showcase.component.demos.*
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKey
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKeys

import androidx.compose.runtime.Composable

internal val experimentalComponentDemos: Map<ShowcaseComponentDemoKey, @Composable () -> Unit> = mapOf(
    ShowcaseComponentDemoKeys.GradientShift to { GradientShift() },
    ShowcaseComponentDemoKeys.SkeletonLoader to { SkeletonLoader() },
    ShowcaseComponentDemoKeys.ElasticPull to { ElasticPull() },
    ShowcaseComponentDemoKeys.ParallaxLayers to { ParallaxLayers() },
    ShowcaseComponentDemoKeys.PathTracer to { PathTracer() },
    ShowcaseComponentDemoKeys.CardShuffle to { CardShuffle() },
    ShowcaseComponentDemoKeys.ConfettiExplosion to { ConfettiExplosion() },
    ShowcaseComponentDemoKeys.WaveEffect to { WaveEffect() },
    ShowcaseComponentDemoKeys.LiquidFill to { LiquidFill() },
    ShowcaseComponentDemoKeys.SlidingReveal to { SlidingReveal() },
    ShowcaseComponentDemoKeys.FocusBlurEffect to { FocusBlurEffect() },
    ShowcaseComponentDemoKeys.RollingDigits to { RollingDigits() },
    ShowcaseComponentDemoKeys.SpringChain to { SpringChain() },
    ShowcaseComponentDemoKeys.GlitchText to { GlitchText() },
    ShowcaseComponentDemoKeys.VerticalTicker to { VerticalTicker() },
    ShowcaseComponentDemoKeys.HeartbeatLine to { HeartbeatLine() },
    ShowcaseComponentDemoKeys.ExpandingSearch to { ExpandingSearch() },
)

package com.segnities007.canimation.screen.showcase.component.registry

import com.segnities007.canimation.screen.showcase.component.demos.*
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKey
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKeys

import androidx.compose.runtime.Composable

internal val overlayAndFormComponentDemos: Map<ShowcaseComponentDemoKey, @Composable () -> Unit> = mapOf(
    ShowcaseComponentDemoKeys.AnimatedBanner to { AnimatedBanner() },
    ShowcaseComponentDemoKeys.AnimatedTooltip to { AnimatedTooltip() },
    ShowcaseComponentDemoKeys.AnimatedAlert to { AnimatedAlert() },
    ShowcaseComponentDemoKeys.AnimatedDropdown to { AnimatedDropdown() },
    ShowcaseComponentDemoKeys.AnimatedProgressCard to { AnimatedProgressCard() },
    ShowcaseComponentDemoKeys.AnimatedDialog to { AnimatedDialog() },
    ShowcaseComponentDemoKeys.OrbitDots to { OrbitDots() },
    ShowcaseComponentDemoKeys.BouncingLoader to { BouncingLoader() },
    ShowcaseComponentDemoKeys.GlowPulse to { GlowPulse() },
    ShowcaseComponentDemoKeys.WaveformBars to { WaveformBars() },
    ShowcaseComponentDemoKeys.LikeButton to { LikeButton() },
    ShowcaseComponentDemoKeys.QuantitySelector to { QuantitySelector() },
    ShowcaseComponentDemoKeys.RadioSelector to { RadioSelector() },
    ShowcaseComponentDemoKeys.SliderControl to { SliderControl() },
    ShowcaseComponentDemoKeys.AnimatedPasswordField to { AnimatedPasswordField() },
    ShowcaseComponentDemoKeys.AnimatedFileUpload to { AnimatedFileUpload() },
    ShowcaseComponentDemoKeys.AnimatedVote to { AnimatedVote() },
    ShowcaseComponentDemoKeys.AnimatedSearchBar to { AnimatedSearchBar() },
    ShowcaseComponentDemoKeys.AnimatedFormValidation to { AnimatedFormValidation() },
)

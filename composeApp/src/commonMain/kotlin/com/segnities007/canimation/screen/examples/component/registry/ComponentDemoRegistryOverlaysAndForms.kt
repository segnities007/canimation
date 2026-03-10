package com.segnities007.canimation.screen.examples.component.registry

import com.segnities007.canimation.screen.examples.component.demos.*
import com.segnities007.canimation.screen.examples.data.ComponentDemoKey
import com.segnities007.canimation.screen.examples.data.ComponentDemoKeys

import androidx.compose.runtime.Composable

internal val overlayAndFormComponentDemos: Map<ComponentDemoKey, @Composable () -> Unit> = mapOf(
    ComponentDemoKeys.AnimatedBanner to { AnimatedBanner() },
    ComponentDemoKeys.AnimatedTooltip to { AnimatedTooltip() },
    ComponentDemoKeys.AnimatedAlert to { AnimatedAlert() },
    ComponentDemoKeys.AnimatedDropdown to { AnimatedDropdown() },
    ComponentDemoKeys.AnimatedProgressCard to { AnimatedProgressCard() },
    ComponentDemoKeys.AnimatedDialog to { AnimatedDialog() },
    ComponentDemoKeys.OrbitDots to { OrbitDots() },
    ComponentDemoKeys.BouncingLoader to { BouncingLoader() },
    ComponentDemoKeys.GlowPulse to { GlowPulse() },
    ComponentDemoKeys.WaveformBars to { WaveformBars() },
    ComponentDemoKeys.LikeButton to { LikeButton() },
    ComponentDemoKeys.QuantitySelector to { QuantitySelector() },
    ComponentDemoKeys.RadioSelector to { RadioSelector() },
    ComponentDemoKeys.SliderControl to { SliderControl() },
    ComponentDemoKeys.AnimatedPasswordField to { AnimatedPasswordField() },
    ComponentDemoKeys.AnimatedFileUpload to { AnimatedFileUpload() },
    ComponentDemoKeys.AnimatedVote to { AnimatedVote() },
    ComponentDemoKeys.AnimatedSearchBar to { AnimatedSearchBar() },
    ComponentDemoKeys.AnimatedFormValidation to { AnimatedFormValidation() },
)

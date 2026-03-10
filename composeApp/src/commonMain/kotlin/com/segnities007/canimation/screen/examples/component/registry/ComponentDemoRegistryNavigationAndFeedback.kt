package com.segnities007.canimation.screen.examples.component.registry

import androidx.compose.runtime.Composable
import com.segnities007.canimation.screen.examples.component.demos.*
import com.segnities007.canimation.screen.examples.data.ComponentDemoKey
import com.segnities007.canimation.screen.examples.data.ComponentDemoKeys

internal val navigationAndFeedbackComponentDemos: Map<ComponentDemoKey, @Composable () -> Unit> =
    mapOf(
        ComponentDemoKeys.MegaMenuReveal to { MegaMenuReveal() },
        ComponentDemoKeys.SmoothTabIndicator to { SmoothTabIndicator() },
        ComponentDemoKeys.RevealTextEffect to { RevealTextEffect() },
        ComponentDemoKeys.ScatterText to { ScatterText() },
        ComponentDemoKeys.InfiniteLoadingList to { InfiniteLoadingList() },
        ComponentDemoKeys.CardStackSwipe to { CardStackSwipe() },
        ComponentDemoKeys.HorizontalScrollGallery to { HorizontalScrollGallery() },
        ComponentDemoKeys.IOSSlider to { SliderControl() },
        ComponentDemoKeys.CheckboxAnimation to { CheckboxAnimation() },
        ComponentDemoKeys.SwitchAnimation to { SwitchAnimation() },
        ComponentDemoKeys.ToastNotification to { ToastNotification() },
        ComponentDemoKeys.MagneticButton to { MagneticButton() },
        ComponentDemoKeys.RippleButton to { RippleButton() },
        ComponentDemoKeys.FloatingParticles to { FloatingParticles() },
        ComponentDemoKeys.ScrollDirectionHeader to { ScrollDirectionHeader() },
        ComponentDemoKeys.TextLineReveal to { TextLineReveal() },
        ComponentDemoKeys.ZoomHeroImage to { ZoomHeroImage() },
        ComponentDemoKeys.ProgressScrubber to { ProgressScrubber() },
        ComponentDemoKeys.VerticalCarousel to { VerticalCarousel() },
        ComponentDemoKeys.WaterfallGrid to { WaterfallGrid() },
        ComponentDemoKeys.PulsingAvatar to { PulsingAvatar() },
        ComponentDemoKeys.ElasticDrawer to { ElasticDrawer() },
        ComponentDemoKeys.AnimatedBreadcrumb to { AnimatedBreadcrumb() },
        ComponentDemoKeys.AnimatedTagCloud to { AnimatedTagCloud() },
        ComponentDemoKeys.AnimatedTimeline to { AnimatedTimeline() },
        ComponentDemoKeys.PulseRing to { PulseRing() },
        ComponentDemoKeys.AnimatedRating to { AnimatedRating() },
        ComponentDemoKeys.StackedAvatars to { StackedAvatars() },
        ComponentDemoKeys.AnimatedStatCard to { AnimatedStatCard() },
        ComponentDemoKeys.ColorSwatchPicker to { ColorSwatchPicker() },
        ComponentDemoKeys.AnimatedCodeBlock to { AnimatedCodeBlock() },
        ComponentDemoKeys.AnimatedNavItem to { AnimatedNavItem() },
        ComponentDemoKeys.AnimatedKpi to { AnimatedKpi() },
        ComponentDemoKeys.WaveProgressBar to { WaveProgressBar() },
        ComponentDemoKeys.AnimatedListItem to { AnimatedListItem() },
        ComponentDemoKeys.AnimatedPricingToggle to { AnimatedPricingToggle() },
        ComponentDemoKeys.AnimatedFeatureGrid to { AnimatedFeatureGrid() },
        ComponentDemoKeys.AnimatedSearchResult to { AnimatedSearchResult() },
        ComponentDemoKeys.AnimatedChipGroup to { AnimatedChipGroup() },
    )

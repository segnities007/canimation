package com.segnities007.canimation.screen.showcase.component.registry

import androidx.compose.runtime.Composable
import com.segnities007.canimation.screen.showcase.component.demos.*
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKey
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKeys

internal val navigationAndFeedbackComponentDemos: Map<ShowcaseComponentDemoKey, @Composable () -> Unit> =
    mapOf(
        ShowcaseComponentDemoKeys.MegaMenuReveal to { MegaMenuReveal() },
        ShowcaseComponentDemoKeys.SmoothTabIndicator to { SmoothTabIndicator() },
        ShowcaseComponentDemoKeys.RevealTextEffect to { RevealTextEffect() },
        ShowcaseComponentDemoKeys.ScatterText to { ScatterText() },
        ShowcaseComponentDemoKeys.InfiniteLoadingList to { InfiniteLoadingList() },
        ShowcaseComponentDemoKeys.CardStackSwipe to { CardStackSwipe() },
        ShowcaseComponentDemoKeys.HorizontalScrollGallery to { HorizontalScrollGallery() },
        ShowcaseComponentDemoKeys.IOSSlider to { SliderControl() },
        ShowcaseComponentDemoKeys.CheckboxAnimation to { CheckboxAnimation() },
        ShowcaseComponentDemoKeys.SwitchAnimation to { SwitchAnimation() },
        ShowcaseComponentDemoKeys.ToastNotification to { ToastNotification() },
        ShowcaseComponentDemoKeys.MagneticButton to { MagneticButton() },
        ShowcaseComponentDemoKeys.RippleButton to { RippleButton() },
        ShowcaseComponentDemoKeys.FloatingParticles to { FloatingParticles() },
        ShowcaseComponentDemoKeys.ScrollDirectionHeader to { ScrollDirectionHeader() },
        ShowcaseComponentDemoKeys.TextLineReveal to { TextLineReveal() },
        ShowcaseComponentDemoKeys.ZoomHeroImage to { ZoomHeroImage() },
        ShowcaseComponentDemoKeys.ProgressScrubber to { ProgressScrubber() },
        ShowcaseComponentDemoKeys.VerticalCarousel to { VerticalCarousel() },
        ShowcaseComponentDemoKeys.WaterfallGrid to { WaterfallGrid() },
        ShowcaseComponentDemoKeys.PulsingAvatar to { PulsingAvatar() },
        ShowcaseComponentDemoKeys.ElasticDrawer to { ElasticDrawer() },
        ShowcaseComponentDemoKeys.AnimatedBreadcrumb to { AnimatedBreadcrumb() },
        ShowcaseComponentDemoKeys.AnimatedTagCloud to { AnimatedTagCloud() },
        ShowcaseComponentDemoKeys.AnimatedTimeline to { AnimatedTimeline() },
        ShowcaseComponentDemoKeys.PulseRing to { PulseRing() },
        ShowcaseComponentDemoKeys.AnimatedRating to { AnimatedRating() },
        ShowcaseComponentDemoKeys.StackedAvatars to { StackedAvatars() },
        ShowcaseComponentDemoKeys.AnimatedStatCard to { AnimatedStatCard() },
        ShowcaseComponentDemoKeys.ColorSwatchPicker to { ColorSwatchPicker() },
        ShowcaseComponentDemoKeys.AnimatedCodeBlock to { AnimatedCodeBlock() },
        ShowcaseComponentDemoKeys.AnimatedNavItem to { AnimatedNavItem() },
        ShowcaseComponentDemoKeys.AnimatedKpi to { AnimatedKpi() },
        ShowcaseComponentDemoKeys.WaveProgressBar to { WaveProgressBar() },
        ShowcaseComponentDemoKeys.AnimatedListItem to { AnimatedListItem() },
        ShowcaseComponentDemoKeys.AnimatedPricingToggle to { AnimatedPricingToggle() },
        ShowcaseComponentDemoKeys.AnimatedFeatureGrid to { AnimatedFeatureGrid() },
        ShowcaseComponentDemoKeys.AnimatedSearchResult to { AnimatedSearchResult() },
        ShowcaseComponentDemoKeys.AnimatedChipGroup to { AnimatedChipGroup() },
    )

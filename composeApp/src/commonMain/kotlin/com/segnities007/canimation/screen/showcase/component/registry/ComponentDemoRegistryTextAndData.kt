package com.segnities007.canimation.screen.showcase.component.registry

import com.segnities007.canimation.screen.showcase.component.demos.*
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKey
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKeys

import androidx.compose.runtime.Composable

internal val textAndDataComponentDemos: Map<ShowcaseComponentDemoKey, @Composable () -> Unit> = mapOf(
    ShowcaseComponentDemoKeys.TextFadeReveal to { TextFadeReveal() },
    ShowcaseComponentDemoKeys.TextHighlighter to { TextHighlighter() },
    ShowcaseComponentDemoKeys.TextShuffleWord to { TextShuffleWord() },
    ShowcaseComponentDemoKeys.TextGradientReveal to { TextGradientReveal() },
    ShowcaseComponentDemoKeys.TextRotateWords to { TextRotateWords() },
    ShowcaseComponentDemoKeys.ProfileCard to { ProfileCard() },
    ShowcaseComponentDemoKeys.PricingCard to { PricingCard() },
    ShowcaseComponentDemoKeys.NotificationCard to { NotificationCard() },
    ShowcaseComponentDemoKeys.TestimonialCard to { TestimonialCard() },
    ShowcaseComponentDemoKeys.MetricCard to { MetricCard() },
    ShowcaseComponentDemoKeys.ProductCard to { ProductCard() },
    ShowcaseComponentDemoKeys.SideMenuReveal to { SideMenuReveal() },
    ShowcaseComponentDemoKeys.PaginationDots to { PaginationDots() },
    ShowcaseComponentDemoKeys.CommandPalette to { CommandPalette() },
    ShowcaseComponentDemoKeys.MiniBarChart to { MiniBarChart() },
    ShowcaseComponentDemoKeys.DonutChart to { DonutChart() },
    ShowcaseComponentDemoKeys.SparkLine to { SparkLine() },
    ShowcaseComponentDemoKeys.DataTable to { DataTable() },
)

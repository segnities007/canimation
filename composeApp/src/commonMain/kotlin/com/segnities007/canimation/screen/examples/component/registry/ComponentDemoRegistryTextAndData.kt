package com.segnities007.canimation.screen.examples.component.registry

import com.segnities007.canimation.screen.examples.component.demos.*
import com.segnities007.canimation.screen.examples.data.ComponentDemoKey
import com.segnities007.canimation.screen.examples.data.ComponentDemoKeys

import androidx.compose.runtime.Composable

internal val textAndDataComponentDemos: Map<ComponentDemoKey, @Composable () -> Unit> = mapOf(
    ComponentDemoKeys.TextFadeReveal to { TextFadeReveal() },
    ComponentDemoKeys.TextHighlighter to { TextHighlighter() },
    ComponentDemoKeys.TextShuffleWord to { TextShuffleWord() },
    ComponentDemoKeys.TextGradientReveal to { TextGradientReveal() },
    ComponentDemoKeys.TextRotateWords to { TextRotateWords() },
    ComponentDemoKeys.ProfileCard to { ProfileCard() },
    ComponentDemoKeys.PricingCard to { PricingCard() },
    ComponentDemoKeys.NotificationCard to { NotificationCard() },
    ComponentDemoKeys.TestimonialCard to { TestimonialCard() },
    ComponentDemoKeys.MetricCard to { MetricCard() },
    ComponentDemoKeys.ProductCard to { ProductCard() },
    ComponentDemoKeys.SideMenuReveal to { SideMenuReveal() },
    ComponentDemoKeys.PaginationDots to { PaginationDots() },
    ComponentDemoKeys.CommandPalette to { CommandPalette() },
    ComponentDemoKeys.MiniBarChart to { MiniBarChart() },
    ComponentDemoKeys.DonutChart to { DonutChart() },
    ComponentDemoKeys.SparkLine to { SparkLine() },
    ComponentDemoKeys.DataTable to { DataTable() },
)

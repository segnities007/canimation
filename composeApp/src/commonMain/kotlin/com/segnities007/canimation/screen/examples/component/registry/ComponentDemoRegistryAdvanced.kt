package com.segnities007.canimation.screen.examples.component.registry

import com.segnities007.canimation.screen.examples.component.demos.*
import com.segnities007.canimation.screen.examples.data.ComponentDemoKey
import com.segnities007.canimation.screen.examples.data.ComponentDemoKeys

import androidx.compose.runtime.Composable

internal val advancedComponentDemos: Map<ComponentDemoKey, @Composable () -> Unit> = mapOf(
    ComponentDemoKeys.AnimatedBottomSheet to { AnimatedBottomSheet() },
    ComponentDemoKeys.AnimatedSnackbar to { AnimatedSnackbar() },
    ComponentDemoKeys.AnimatedFab to { AnimatedFab() },
    ComponentDemoKeys.AnimatedChipInput to { AnimatedChipInput() },
    ComponentDemoKeys.AnimatedAccordion to { AnimatedAccordion() },
    ComponentDemoKeys.AnimatedStepper to { AnimatedStepper() },
    ComponentDemoKeys.MorphingShape to { MorphingShape() },
    ComponentDemoKeys.SpiralDots to { SpiralDots() },
    ComponentDemoKeys.DnaHelixCanvas to { DnaHelixCanvas() },
    ComponentDemoKeys.HexGrid to { HexGrid() },
    ComponentDemoKeys.ParticleField to { ParticleField() },
    ComponentDemoKeys.RotatingSquares to { RotatingSquares() },
    ComponentDemoKeys.SegmentedControl to { SegmentedControl() },
    ComponentDemoKeys.AnimatedSwitch to { AnimatedSwitch() },
    ComponentDemoKeys.AnimatedPinInput to { AnimatedPinInput() },
    ComponentDemoKeys.AnimatedColorPicker to { AnimatedColorPicker() },
    ComponentDemoKeys.AnimatedNotificationBell to { AnimatedNotificationBell() },
    ComponentDemoKeys.AnimatedCountdownTimer to { AnimatedCountdownTimer() },
    ComponentDemoKeys.AnimatedCreditCard to { AnimatedCreditCard() },
    ComponentDemoKeys.AnimatedThemeToggle to { AnimatedThemeToggle() },
    ComponentDemoKeys.TypewriterEffect to { TypewriterEffect() },
    ComponentDemoKeys.TextMorph to { TextMorph() },
    ComponentDemoKeys.TextStrikethrough to { TextStrikethrough() },
    ComponentDemoKeys.TextGlitch to { TextGlitch() },
    ComponentDemoKeys.RecipeCard to { RecipeCard() },
    ComponentDemoKeys.WeatherCard to { WeatherCard() },
    ComponentDemoKeys.EventCard to { EventCard() },
    ComponentDemoKeys.MusicPlayerCard to { MusicPlayerCard() },
    ComponentDemoKeys.BottomNavBar to { BottomNavBar() },
    ComponentDemoKeys.RadarChart to { RadarChart() },
    ComponentDemoKeys.HorizontalBarChart to { HorizontalBarChart() },
    ComponentDemoKeys.GaugeChart to { GaugeChart() },
    ComponentDemoKeys.LiveCounter to { LiveCounter() },
)

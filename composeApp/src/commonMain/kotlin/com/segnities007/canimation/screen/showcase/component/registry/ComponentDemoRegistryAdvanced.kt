package com.segnities007.canimation.screen.showcase.component.registry

import com.segnities007.canimation.screen.showcase.component.demos.*
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKey
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKeys

import androidx.compose.runtime.Composable

internal val advancedComponentDemos: Map<ShowcaseComponentDemoKey, @Composable () -> Unit> = mapOf(
    ShowcaseComponentDemoKeys.AnimatedBottomSheet to { AnimatedBottomSheet() },
    ShowcaseComponentDemoKeys.AnimatedSnackbar to { AnimatedSnackbar() },
    ShowcaseComponentDemoKeys.AnimatedFab to { AnimatedFab() },
    ShowcaseComponentDemoKeys.AnimatedChipInput to { AnimatedChipInput() },
    ShowcaseComponentDemoKeys.AnimatedAccordion to { AnimatedAccordion() },
    ShowcaseComponentDemoKeys.AnimatedStepper to { AnimatedStepper() },
    ShowcaseComponentDemoKeys.MorphingShape to { MorphingShape() },
    ShowcaseComponentDemoKeys.SpiralDots to { SpiralDots() },
    ShowcaseComponentDemoKeys.DnaHelixCanvas to { DnaHelixCanvas() },
    ShowcaseComponentDemoKeys.HexGrid to { HexGrid() },
    ShowcaseComponentDemoKeys.ParticleField to { ParticleField() },
    ShowcaseComponentDemoKeys.RotatingSquares to { RotatingSquares() },
    ShowcaseComponentDemoKeys.SegmentedControl to { SegmentedControl() },
    ShowcaseComponentDemoKeys.AnimatedSwitch to { AnimatedSwitch() },
    ShowcaseComponentDemoKeys.AnimatedPinInput to { AnimatedPinInput() },
    ShowcaseComponentDemoKeys.AnimatedColorPicker to { AnimatedColorPicker() },
    ShowcaseComponentDemoKeys.AnimatedNotificationBell to { AnimatedNotificationBell() },
    ShowcaseComponentDemoKeys.AnimatedCountdownTimer to { AnimatedCountdownTimer() },
    ShowcaseComponentDemoKeys.AnimatedCreditCard to { AnimatedCreditCard() },
    ShowcaseComponentDemoKeys.AnimatedThemeToggle to { AnimatedThemeToggle() },
    ShowcaseComponentDemoKeys.TypewriterEffect to { TypewriterEffect() },
    ShowcaseComponentDemoKeys.TextMorph to { TextMorph() },
    ShowcaseComponentDemoKeys.TextStrikethrough to { TextStrikethrough() },
    ShowcaseComponentDemoKeys.TextGlitch to { TextGlitch() },
    ShowcaseComponentDemoKeys.RecipeCard to { RecipeCard() },
    ShowcaseComponentDemoKeys.WeatherCard to { WeatherCard() },
    ShowcaseComponentDemoKeys.EventCard to { EventCard() },
    ShowcaseComponentDemoKeys.MusicPlayerCard to { MusicPlayerCard() },
    ShowcaseComponentDemoKeys.BottomNavBar to { BottomNavBar() },
    ShowcaseComponentDemoKeys.RadarChart to { RadarChart() },
    ShowcaseComponentDemoKeys.HorizontalBarChart to { HorizontalBarChart() },
    ShowcaseComponentDemoKeys.GaugeChart to { GaugeChart() },
    ShowcaseComponentDemoKeys.LiveCounter to { LiveCounter() },
)

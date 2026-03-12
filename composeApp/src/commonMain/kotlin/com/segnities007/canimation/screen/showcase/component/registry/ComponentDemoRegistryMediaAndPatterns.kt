package com.segnities007.canimation.screen.showcase.component.registry

import com.segnities007.canimation.screen.showcase.component.demos.*
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKey
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKeys

import androidx.compose.runtime.Composable

internal val mediaAndPatternComponentDemos: Map<ShowcaseComponentDemoKey, @Composable () -> Unit> = mapOf(
    ShowcaseComponentDemoKeys.WaveformVisualizer to { WaveformVisualizer() },
    ShowcaseComponentDemoKeys.RadialProgress to { RadialProgress() },
    ShowcaseComponentDemoKeys.MatrixRain to { MatrixRain() },
    ShowcaseComponentDemoKeys.PulseButton to { PulseButton() },
    ShowcaseComponentDemoKeys.NeumorphismCard to { NeumorphismCard() },
    ShowcaseComponentDemoKeys.GradientBorderCard to { GradientBorderCard() },
    ShowcaseComponentDemoKeys.FlipCounter to { FlipCounter() },
    ShowcaseComponentDemoKeys.ExpandableChip to { ExpandableChip() },
    ShowcaseComponentDemoKeys.StackedNotifications to { StackedNotifications() },
    ShowcaseComponentDemoKeys.CircularRevealCard to { CircularRevealCard() },
    ShowcaseComponentDemoKeys.SwipeCard to { SwipeCard() },
    ShowcaseComponentDemoKeys.AnimatedCheckmark to { AnimatedCheckmark() },
    ShowcaseComponentDemoKeys.RotatingCube to { RotatingCube() },
    ShowcaseComponentDemoKeys.WaterDroplet to { WaterDroplet() },
    ShowcaseComponentDemoKeys.SlotMachine to { SlotMachine() },
    ShowcaseComponentDemoKeys.MusicEqualizer to { MusicEqualizer() },
)

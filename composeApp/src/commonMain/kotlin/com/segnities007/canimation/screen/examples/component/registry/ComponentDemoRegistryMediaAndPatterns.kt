package com.segnities007.canimation.screen.examples.component.registry

import com.segnities007.canimation.screen.examples.component.demos.*
import com.segnities007.canimation.screen.examples.data.ComponentDemoKey
import com.segnities007.canimation.screen.examples.data.ComponentDemoKeys

import androidx.compose.runtime.Composable

internal val mediaAndPatternComponentDemos: Map<ComponentDemoKey, @Composable () -> Unit> = mapOf(
    ComponentDemoKeys.WaveformVisualizer to { WaveformVisualizer() },
    ComponentDemoKeys.RadialProgress to { RadialProgress() },
    ComponentDemoKeys.MatrixRain to { MatrixRain() },
    ComponentDemoKeys.PulseButton to { PulseButton() },
    ComponentDemoKeys.NeumorphismCard to { NeumorphismCard() },
    ComponentDemoKeys.GradientBorderCard to { GradientBorderCard() },
    ComponentDemoKeys.FlipCounter to { FlipCounter() },
    ComponentDemoKeys.ExpandableChip to { ExpandableChip() },
    ComponentDemoKeys.StackedNotifications to { StackedNotifications() },
    ComponentDemoKeys.CircularRevealCard to { CircularRevealCard() },
    ComponentDemoKeys.SwipeCard to { SwipeCard() },
    ComponentDemoKeys.AnimatedCheckmark to { AnimatedCheckmark() },
    ComponentDemoKeys.RotatingCube to { RotatingCube() },
    ComponentDemoKeys.WaterDroplet to { WaterDroplet() },
    ComponentDemoKeys.SlotMachine to { SlotMachine() },
    ComponentDemoKeys.MusicEqualizer to { MusicEqualizer() },
)

package com.segnities007.canimation.screen.examples.component.registry

import com.segnities007.canimation.screen.examples.component.demos.*
import com.segnities007.canimation.screen.examples.data.ComponentDemoKey
import com.segnities007.canimation.screen.examples.data.ComponentDemoKeys

import androidx.compose.runtime.Composable

val componentDemos: Map<ComponentDemoKey, @Composable () -> Unit> = buildMap {
    putAll(coreComponentDemos)
    putAll(experimentalComponentDemos)
    putAll(cardAndIndicatorComponentDemos)
    putAll(navigationAndFeedbackComponentDemos)
    putAll(mediaAndPatternComponentDemos)
    putAll(textAndDataComponentDemos)
    putAll(overlayAndFormComponentDemos)
    putAll(advancedComponentDemos)
}

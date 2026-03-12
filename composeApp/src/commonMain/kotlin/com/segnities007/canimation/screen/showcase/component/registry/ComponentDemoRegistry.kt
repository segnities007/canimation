package com.segnities007.canimation.screen.showcase.component.registry

import com.segnities007.canimation.screen.showcase.component.demos.*
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKey
import com.segnities007.canimation.screen.showcase.data.ShowcaseComponentDemoKeys

import androidx.compose.runtime.Composable

val componentDemos: Map<ShowcaseComponentDemoKey, @Composable () -> Unit> = buildMap {
    putAll(coreComponentDemos)
    putAll(experimentalComponentDemos)
    putAll(cardAndIndicatorComponentDemos)
    putAll(navigationAndFeedbackComponentDemos)
    putAll(mediaAndPatternComponentDemos)
    putAll(textAndDataComponentDemos)
    putAll(overlayAndFormComponentDemos)
    putAll(advancedComponentDemos)
}

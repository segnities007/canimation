package com.segnities007.canimation.screen.examples.data

import io.github.canimation.core.CanimationEffect
import org.jetbrains.compose.resources.StringResource
import io.github.canimation.core.CanimationPreset

data class ExampleCategory(
    val id: String,
    val title: StringResource,
    val subtitle: StringResource,
    val accentLabel: String,
    val examples: List<ExampleItem>,
    val tags: List<String> = emptyList(),
)

data class ExampleItem(
    val title: StringResource,
    val description: StringResource,
    val codeSnippet: StringResource,
    val demoType: DemoType = DemoType.Effect,
    val effect: CanimationEffect? = null,
    val enterEffect: CanimationEffect? = null,
    val exitEffect: CanimationEffect? = null,
    val preset: CanimationPreset = CanimationPreset.Fade,
    val componentKey: ComponentDemoKey? = null,
)

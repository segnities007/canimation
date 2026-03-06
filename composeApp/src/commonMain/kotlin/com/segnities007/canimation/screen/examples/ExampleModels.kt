package com.segnities007.canimation.screen.examples

import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset

data class ExampleCategory(
    val id: String,
    val title: String,
    val subtitle: String,
    val accentLabel: String,
    val examples: List<ExampleItem>,
    val tags: List<String> = emptyList(),
)

data class ExampleItem(
    val title: String,
    val description: String,
    val codeSnippet: String,
    val demoType: DemoType = DemoType.Effect,
    val effect: CanimationEffect? = null,
    val enterEffect: CanimationEffect? = null,
    val exitEffect: CanimationEffect? = null,
    val preset: CanimationPreset = CanimationPreset.Fade,
    val componentKey: ComponentDemoKey? = null,
)

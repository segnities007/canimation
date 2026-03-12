package com.segnities007.canimation.screen.showcase.data

import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.CanimationPreset
import org.jetbrains.compose.resources.StringResource

internal data class ShowcaseCategory(
    val id: String,
    val title: StringResource,
    val subtitle: StringResource,
    val examples: List<ShowcaseItem>,
    val tags: List<ShowcaseTagId>,
) {
    init {
        require(tags.isNotEmpty()) { "ShowcaseCategory.tags must not be empty" }
    }

    val accentTag: ShowcaseTagId
        get() = tags.first()
}

internal data class ShowcaseItem(
    val title: StringResource,
    val description: StringResource,
    val codeSnippet: StringResource,
    val demoType: ShowcaseDemoType = ShowcaseDemoType.Effect,
    val effect: CanimationEffect? = null,
    val enterEffect: CanimationEffect? = null,
    val exitEffect: CanimationEffect? = null,
    val preset: CanimationPreset = CanimationPreset.Fade,
    val componentKey: ShowcaseComponentDemoKey? = null,
)

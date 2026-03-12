package com.segnities007.canimation.screen.docs

import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.doc_section_api_surface
import canimation.composeapp.generated.resources.doc_section_atomic_design
import canimation.composeapp.generated.resources.doc_section_differentiators
import canimation.composeapp.generated.resources.doc_section_inspiration
import canimation.composeapp.generated.resources.doc_section_modules
import canimation.composeapp.generated.resources.doc_section_namespace
import canimation.composeapp.generated.resources.doc_section_overview
import canimation.composeapp.generated.resources.doc_section_philosophy
import canimation.composeapp.generated.resources.doc_section_platforms
import canimation.composeapp.generated.resources.doc_section_playground
import canimation.composeapp.generated.resources.doc_section_primitives
import canimation.composeapp.generated.resources.doc_section_principles
import canimation.composeapp.generated.resources.doc_section_quick_start
import canimation.composeapp.generated.resources.doc_section_roadmap
import org.jetbrains.compose.resources.StringResource

internal enum class DocSection(val labelRes: StringResource) {
    Overview(Res.string.doc_section_overview),
    QuickStart(Res.string.doc_section_quick_start),
    Philosophy(Res.string.doc_section_philosophy),
    ApiSurface(Res.string.doc_section_api_surface),
    Namespace(Res.string.doc_section_namespace),
    Primitives(Res.string.doc_section_primitives),
    AtomicDesign(Res.string.doc_section_atomic_design),
    Playground(Res.string.doc_section_playground),
    Principles(Res.string.doc_section_principles),
    Inspiration(Res.string.doc_section_inspiration),
    Differentiators(Res.string.doc_section_differentiators),
    Modules(Res.string.doc_section_modules),
    Platforms(Res.string.doc_section_platforms),
    Roadmap(Res.string.doc_section_roadmap),
}

internal data class DocsUiState(
    val stage: Int = -1,
    val activeSection: DocSection = DocSection.Overview,
)

internal sealed interface DocsEvent {
    data class StageUpdated(val stage: Int) : DocsEvent
    data class SectionSelected(val section: DocSection) : DocsEvent
}

internal fun reduceDocsState(
    current: DocsUiState,
    event: DocsEvent,
): DocsUiState = when (event) {
    is DocsEvent.StageUpdated -> current.copy(stage = event.stage)
    is DocsEvent.SectionSelected -> current.copy(activeSection = event.section)
}

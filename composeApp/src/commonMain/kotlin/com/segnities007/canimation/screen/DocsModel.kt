package com.segnities007.canimation.screen

internal enum class DocSection(val label: String) {
    Overview("Overview"),
    QuickStart("Quick Start"),
    Philosophy("Philosophy"),
    ApiSurface("API Surface"),
    Namespace("Canimation.*"),
    Primitives("Primitives"),
    AtomicDesign("Atomic Design"),
    Playground("Playground"),
    Principles("Principles"),
    Inspiration("Inspiration"),
    Differentiators("Differentiators"),
    Modules("Modules"),
    Platforms("Platforms"),
    Roadmap("Roadmap"),
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

package com.segnities007.canimation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

@Composable
fun DocsScreen(modifier: Modifier = Modifier) {
    var uiState by remember { mutableStateOf(DocsUiState()) }
    val onEvent: (DocsEvent) -> Unit = { event ->
        uiState = reduceDocsState(uiState, event)
    }

    LaunchedEffect(Unit) {
        for (i in 0..20) {
            delay(40)
            onEvent(DocsEvent.StageUpdated(i))
        }
    }

    Column(modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            DocSection.entries.forEach { section ->
                val selected = uiState.activeSection == section
                Surface(
                    onClick = { onEvent(DocsEvent.SectionSelected(section)) },
                    shape = RoundedCornerShape(20.dp),
                    color = if (selected) MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.surfaceVariant,
                    border = if (selected) BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                    else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                ) {
                    Text(
                        text = stringResource(section.labelRes),
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(
                Modifier
                    .widthIn(max = 900.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 32.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                when (uiState.activeSection) {
                    DocSection.Overview -> OverviewContent(uiState.stage)
                    DocSection.QuickStart -> QuickStartContent(uiState.stage)
                    DocSection.Philosophy -> PhilosophyContent(uiState.stage)
                    DocSection.ApiSurface -> ApiSurfaceContent(uiState.stage)
                    DocSection.Namespace -> NamespaceContent(uiState.stage)
                    DocSection.Primitives -> PrimitivesContent(uiState.stage)
                    DocSection.AtomicDesign -> AtomicDesignContent(uiState.stage)
                    DocSection.Playground -> PlaygroundContent(uiState.stage)
                    DocSection.Principles -> CorePrinciplesContent(uiState.stage)
                    DocSection.Inspiration -> InspirationContent(uiState.stage)
                    DocSection.Differentiators -> DifferentiatorsContent(uiState.stage)
                    DocSection.Modules -> ModulesContent(uiState.stage)
                    DocSection.Platforms -> PlatformContent(uiState.stage)
                    DocSection.Roadmap -> RoadmapContent(uiState.stage)
                }
                Spacer(Modifier.height(40.dp))
            }
        }
    }
}

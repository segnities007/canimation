package com.segnities007.canimation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.segnities007.canimation.navigation.HomeDestination
import io.github.canimation.core.CanimationPreset

@Composable
fun HomeScreen(
    onNavigate: (HomeDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    val presetCount = CanimationPreset.entries.size
    val stateHolder = rememberHomeStateHolder()
    val uiState = stateHolder.uiState

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
    ) {
        HeroSection(stage = uiState.stage, presetCount = presetCount, onNavigate = onNavigate)
        LiveShowcaseSection(stage = uiState.stage)
        FeaturesSection(stage = uiState.stage, presetCount = presetCount)
        CodeExampleSection(stage = uiState.stage)
        ExploreSection(stage = uiState.stage, onNavigate = onNavigate)
        PlatformSection(stage = uiState.stage)
        FooterSection(stage = uiState.stage)
    }
}

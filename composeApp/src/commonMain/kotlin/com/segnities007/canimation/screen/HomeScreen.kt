package com.segnities007.canimation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.segnities007.canimation.navigation.HomeDestination
import io.github.canimation.core.CanimationPreset
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    onNavigate: (HomeDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    val presetCount = CanimationPreset.entries.size
    var stage by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        for (i in 0..11) {
            delay(70)
            stage = i
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
    ) {
        HeroSection(stage = stage, presetCount = presetCount, onNavigate = onNavigate)
        LiveShowcaseSection(stage = stage)
        FeaturesSection(stage = stage, presetCount = presetCount)
        CodeExampleSection(stage = stage)
        ExploreSection(onNavigate = onNavigate)
        PlatformSection(stage = stage)
        FooterSection()
    }
}

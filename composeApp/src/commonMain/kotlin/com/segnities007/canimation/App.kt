package com.segnities007.canimation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.segnities007.canimation.component.PresetPreviewTuning
import com.segnities007.canimation.navigation.ApiReferenceRoute
import com.segnities007.canimation.navigation.CanimationNavHost
import com.segnities007.canimation.navigation.DocsRoute
import com.segnities007.canimation.navigation.ExamplesRoute
import com.segnities007.canimation.navigation.HomeRoute
import com.segnities007.canimation.navigation.ROUTE_KEY_PRESET_GALLERY
import com.segnities007.canimation.navigation.TopBarDestination
import com.segnities007.canimation.navigation.matchesRoute
import com.segnities007.canimation.theme.CanimationTheme
import io.github.canimation.diagnostics.CanimationDiagnosticsOverlay
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.canimation
import io.github.canimation.presets.PresetsExtensionRegistry
import kotlinx.coroutines.delay

@Composable
fun App() {
    var policy by remember { mutableStateOf<CanimationPolicy>(CanimationPolicy.SystemAware) }
    var darkOverride by remember { mutableStateOf<Boolean?>(null) }
    val systemReducedMotion = rememberSystemReducedMotion()
    val isDarkMode = darkOverride ?: isSystemInDarkTheme()
    var showSettings by remember { mutableStateOf(false) }
    var diagnosticsEnabled by remember { mutableStateOf(false) }
    var navStage by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) { for (i in 0..7) { delay(40); navStage = i } }

    // Preset gallery tuning state (hoisted for settings bottom sheet)
    var autoPlayEnabled by remember { mutableStateOf(true) }
    var cycleMs by remember { mutableFloatStateOf(1400f) }
    var durationScale by remember { mutableFloatStateOf(1f) }
    var distanceScale by remember { mutableFloatStateOf(1f) }
    var scaleIntensity by remember { mutableFloatStateOf(1f) }
    var rotationScale by remember { mutableFloatStateOf(1f) }
    var autoPlayTick by remember { mutableIntStateOf(0) }

    val tuning = remember(durationScale, distanceScale, scaleIntensity, rotationScale) {
        PresetPreviewTuning(
            durationScale = durationScale,
            distanceScale = distanceScale,
            scaleIntensity = scaleIntensity,
            rotationScale = rotationScale,
        )
    }
    val presetRegistry = remember {
        PresetsExtensionRegistry.createExtendedRegistry()
    }

    LaunchedEffect(autoPlayEnabled, cycleMs) {
        if (autoPlayEnabled) {
            while (true) {
                autoPlayTick += 1
                delay(cycleMs.toLong().coerceAtLeast(350L))
            }
        }
    }

    CanimationTheme(darkTheme = isDarkMode) {
        CanimationProvider(
            policy = policy,
            presetRegistry = presetRegistry,
            systemReducedMotion = systemReducedMotion,
        ) {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val route = backStackEntry?.destination?.route
            val isOnPresetGallery = route.matchesRoute(ROUTE_KEY_PRESET_GALLERY)

            CanimationDiagnosticsOverlay(enabled = diagnosticsEnabled) {
                Scaffold(
                    topBar = {
                        Surface(
                            color = MaterialTheme.colorScheme.surface,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 24.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "canimation",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .canimation(visible = navStage >= 0, effect = Canimation.Fade.Left)
                                        .clickable {
                                            navController.navigate(HomeRoute) {
                                                popUpTo(HomeRoute) { inclusive = true }
                                            }
                                        },
                                )

                                Spacer(Modifier.weight(1f))

                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                                ) {
                                    TopBarDestination.entries.forEachIndexed { index, destination ->
                                        val selected = route.matchesRoute(destination.routeKey)
                                        TextButton(
                                            onClick = {
                                                navController.navigateTopBarDestination(destination)
                                            },
                                            modifier = Modifier.canimation(
                                                visible = navStage >= index + 1,
                                                effect = Canimation.Fade.Up,
                                            ),
                                        ) {
                                            Text(
                                                text = destination.label,
                                                style = MaterialTheme.typography.labelLarge,
                                                color = if (selected) MaterialTheme.colorScheme.primary
                                                else MaterialTheme.colorScheme.onSurfaceVariant,
                                                fontWeight = if (selected) FontWeight.Bold
                                                else FontWeight.Normal,
                                            )
                                        }
                                    }
                                }

                                IconButton(
                                    onClick = { darkOverride = !isDarkMode },
                                    modifier = Modifier.canimation(
                                        visible = navStage >= 4,
                                        effect = Canimation.Fade.Up,
                                    ),
                                ) {
                                    Icon(
                                        imageVector = if (isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                                        contentDescription = "Toggle dark mode",
                                    )
                                }

                                IconButton(
                                    onClick = { showSettings = true },
                                    modifier = Modifier.canimation(
                                        visible = navStage >= 5,
                                        effect = Canimation.Fade.Up,
                                    ),
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = "Open settings",
                                    )
                                }
                            }
                        }
                    },
                ) { innerPadding ->
                    CanimationNavHost(
                        navController = navController,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        autoPlayEnabled = autoPlayEnabled,
                        autoPlayTick = autoPlayTick,
                        tuning = tuning,
                    )
                }
            }

            if (showSettings) {
                SettingsBottomSheet(
                    state = AppSettingsSheetState(
                        isDarkMode = isDarkMode,
                        policy = policy,
                        showPresetTuning = isOnPresetGallery,
                        autoPlayEnabled = autoPlayEnabled,
                        cycleMs = cycleMs,
                        durationScale = durationScale,
                        distanceScale = distanceScale,
                        scaleIntensity = scaleIntensity,
                        rotationScale = rotationScale,
                        diagnosticsEnabled = diagnosticsEnabled,
                    ),
                    actions = AppSettingsSheetActions(
                        onToggleDark = { darkOverride = !isDarkMode },
                        onPolicyChange = { policy = it },
                        onAutoPlayChange = { autoPlayEnabled = it },
                        onCycleMsChange = { cycleMs = it },
                        onDurationScaleChange = { durationScale = it },
                        onDistanceScaleChange = { distanceScale = it },
                        onScaleIntensityChange = { scaleIntensity = it },
                        onRotationScaleChange = { rotationScale = it },
                        onResetParams = {
                            durationScale = 1f
                            distanceScale = 1f
                            scaleIntensity = 1f
                            rotationScale = 1f
                        },
                        onDiagnosticsEnabledChange = { diagnosticsEnabled = it },
                    ),
                    onDismiss = { showSettings = false },
                )
            }
        }
    }
}

private fun NavHostController.navigateTopBarDestination(destination: TopBarDestination) {
    when (destination) {
        TopBarDestination.Docs -> navigate(DocsRoute) {
            popUpTo(HomeRoute) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
        TopBarDestination.Api -> navigate(ApiReferenceRoute) {
            popUpTo(HomeRoute) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
        TopBarDestination.Gallery -> navigate(ExamplesRoute) {
            popUpTo(HomeRoute) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
expect fun rememberSystemReducedMotion(): Boolean?

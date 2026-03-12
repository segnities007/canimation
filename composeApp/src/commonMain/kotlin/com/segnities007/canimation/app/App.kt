package com.segnities007.canimation.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.segnities007.canimation.app.settings.SettingsBottomSheet
import com.segnities007.canimation.app.settings.onAppSettingsSheetEvent
import com.segnities007.canimation.app.settings.toAppSettingsSheetState
import com.segnities007.canimation.app.shell.AppTopBar
import com.segnities007.canimation.app.shell.navigateTopBarDestination
import com.segnities007.canimation.app.state.AppEvent
import com.segnities007.canimation.app.state.rememberAppStateHolder
import com.segnities007.canimation.app.state.resolveDarkMode
import com.segnities007.canimation.app.state.toPresetPreviewTuning
import com.segnities007.canimation.navigation.CanimationNavHost
import com.segnities007.canimation.navigation.HomeRoute
import com.segnities007.canimation.navigation.ROUTE_KEY_PRESET_GALLERY
import com.segnities007.canimation.navigation.matchesRoute
import com.segnities007.canimation.rememberSystemReducedMotion
import com.segnities007.canimation.theme.CanimationTheme
import io.github.canimation.core.CanimationProvider
import io.github.canimation.diagnostics.CanimationDiagnosticsOverlay
import io.github.canimation.presets.PresetsExtensionRegistry

@Composable
internal fun CanimationApp() {
    val stateHolder = rememberAppStateHolder()
    val uiState = stateHolder.uiState
    val systemReducedMotion = rememberSystemReducedMotion()
    val isDarkMode = uiState.resolveDarkMode()
    val tuning = uiState.toPresetPreviewTuning()
    val presetRegistry =
        remember {
            PresetsExtensionRegistry.createExtendedRegistry()
        }

    CanimationTheme(darkTheme = isDarkMode) {
        CanimationProvider(
            policy = uiState.policy,
            presetRegistry = presetRegistry,
            systemReducedMotion = systemReducedMotion,
        ) {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val route = backStackEntry?.destination?.route
            val isOnPresetGallery = route.matchesRoute(ROUTE_KEY_PRESET_GALLERY)

            CanimationDiagnosticsOverlay(enabled = uiState.diagnosticsEnabled) {
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    topBar = {
                        AppTopBar(
                            uiState = uiState,
                            currentRoute = route,
                            isDarkMode = isDarkMode,
                            onHomeClick = {
                                navController.navigate(HomeRoute) {
                                    popUpTo(HomeRoute) { inclusive = true }
                                }
                            },
                            onNavigate = { destination ->
                                navController.navigateTopBarDestination(destination)
                            },
                            onToggleDark = {
                                stateHolder.onEvent(AppEvent.DarkOverrideChanged(!isDarkMode))
                            },
                            onOpenSettings = {
                                stateHolder.onEvent(AppEvent.SettingsVisibilityChanged(true))
                            },
                        )
                    },
                ) { innerPadding ->
                    CanimationNavHost(
                        navController = navController,
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                        autoPlayEnabled = uiState.autoPlayEnabled,
                        autoPlayTick = uiState.autoPlayTick,
                        tuning = tuning,
                    )
                }
            }

            if (uiState.showSettings) {
                SettingsBottomSheet(
                    state =
                        uiState.toAppSettingsSheetState(
                            isDarkMode = isDarkMode,
                            showPresetTuning = isOnPresetGallery,
                        ),
                    onEvent = { event ->
                        stateHolder.onAppSettingsSheetEvent(event = event, isDarkMode = isDarkMode)
                    },
                    onDismiss = {
                        stateHolder.onEvent(AppEvent.SettingsVisibilityChanged(false))
                    },
                )
            }
        }
    }
}

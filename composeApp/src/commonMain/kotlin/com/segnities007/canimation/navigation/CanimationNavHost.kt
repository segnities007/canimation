package com.segnities007.canimation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.segnities007.canimation.screen.A11yDemoScreen
import com.segnities007.canimation.screen.CustomSpecLabScreen
import com.segnities007.canimation.screen.DiagnosticsScreen
import com.segnities007.canimation.screen.HomeScreen
import com.segnities007.canimation.screen.PresetGalleryScreen
import com.segnities007.canimation.screen.TokenReferenceScreen

@Composable
fun CanimationNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
    ) {
        composable<HomeRoute> {
            HomeScreen(
                onNavigate = { route ->
                    when (route) {
                        "presets" -> navController.navigate(PresetGalleryRoute)
                        "custom" -> navController.navigate(CustomSpecLabRoute)
                        "a11y" -> navController.navigate(A11yDemoRoute)
                        "diagnostics" -> navController.navigate(DiagnosticsRoute)
                        "tokens" -> navController.navigate(TokenReferenceRoute)
                    }
                },
            )
        }
        composable<PresetGalleryRoute> { PresetGalleryScreen() }
        composable<CustomSpecLabRoute> { CustomSpecLabScreen() }
        composable<A11yDemoRoute> { A11yDemoScreen() }
        composable<DiagnosticsRoute> { DiagnosticsScreen() }
        composable<TokenReferenceRoute> { TokenReferenceScreen() }
    }
}

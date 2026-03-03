package com.segnities007.canimation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.segnities007.canimation.screen.A11yDemoScreen
import com.segnities007.canimation.screen.CustomSpecLabScreen
import com.segnities007.canimation.screen.DiagnosticsScreen
import com.segnities007.canimation.screen.DocsScreen
import com.segnities007.canimation.screen.HomeScreen
import com.segnities007.canimation.screen.PresetGalleryScreen
import com.segnities007.canimation.screen.TokenReferenceScreen
import com.segnities007.canimation.screen.examples.ExampleDetailScreen
import com.segnities007.canimation.screen.examples.ExamplesScreen

@Composable
fun CanimationNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
        enterTransition = {
            fadeIn(animationSpec = tween(300)) +
                slideInVertically(animationSpec = tween(300)) { it / 16 }
        },
        exitTransition = {
            fadeOut(animationSpec = tween(200))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(200)) +
                slideOutVertically(animationSpec = tween(200)) { it / 16 }
        },
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
                        "examples" -> navController.navigate(ExamplesRoute)
                        "docs" -> navController.navigate(DocsRoute)
                    }
                },
            )
        }
        composable<DocsRoute> { DocsScreen() }
        composable<PresetGalleryRoute> { PresetGalleryScreen() }
        composable<CustomSpecLabRoute> { CustomSpecLabScreen() }
        composable<A11yDemoRoute> { A11yDemoScreen() }
        composable<DiagnosticsRoute> { DiagnosticsScreen() }
        composable<TokenReferenceRoute> { TokenReferenceScreen() }
        composable<ExamplesRoute> {
            ExamplesScreen(
                onCategoryClick = { categoryId ->
                    navController.navigate(ExampleDetailRoute(categoryId))
                },
            )
        }
        composable<ExampleDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ExampleDetailRoute>()
            ExampleDetailScreen(
                categoryId = route.categoryId,
                onBack = { navController.popBackStack() },
            )
        }
    }
}

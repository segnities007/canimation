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
import com.segnities007.canimation.component.PresetPreviewTuning
import com.segnities007.canimation.screen.ApiReferenceScreen
import com.segnities007.canimation.screen.DocsScreen
import com.segnities007.canimation.screen.HomeScreen
import com.segnities007.canimation.screen.PresetGalleryScreen
import com.segnities007.canimation.screen.examples.ExampleDetailScreen
import com.segnities007.canimation.screen.examples.ExamplesScreen

@Composable
fun CanimationNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    autoPlayEnabled: Boolean = true,
    autoPlayTick: Int = 0,
    tuning: PresetPreviewTuning = PresetPreviewTuning(),
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
                        "examples" -> navController.navigate(ExamplesRoute)
                        "docs" -> navController.navigate(DocsRoute)
                        "api" -> navController.navigate(ApiReferenceRoute)
                    }
                },
            )
        }
        composable<DocsRoute> { DocsScreen() }
        composable<ApiReferenceRoute> { ApiReferenceScreen() }
        composable<PresetGalleryRoute> {
            PresetGalleryScreen(
                autoPlayEnabled = autoPlayEnabled,
                autoPlayTick = autoPlayTick,
                tuning = tuning,
            )
        }
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

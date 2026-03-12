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
import com.segnities007.canimation.screen.apireference.ApiReferenceScreen
import com.segnities007.canimation.screen.docs.DocsScreen
import com.segnities007.canimation.screen.home.HomeScreen
import com.segnities007.canimation.screen.presets.PresetGalleryScreen
import com.segnities007.canimation.screen.showcase.detail.ShowcaseDetailScreen
import com.segnities007.canimation.screen.showcase.gallery.ShowcaseGalleryScreen

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
                onNavigate = { destination ->
                    when (destination) {
                        HomeDestination.PresetGallery -> navController.navigate(PresetGalleryRoute)
                        HomeDestination.ShowcaseGallery -> navController.navigate(ShowcaseGalleryRoute)
                        HomeDestination.Docs -> navController.navigate(DocsRoute)
                        HomeDestination.ApiReference -> navController.navigate(ApiReferenceRoute)
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
        composable<ShowcaseGalleryRoute> {
            ShowcaseGalleryScreen(
                onItemClick = { itemIndex ->
                    navController.navigate(ShowcaseDetailRoute(itemIndex = itemIndex))
                },
            )
        }
        composable<ShowcaseDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ShowcaseDetailRoute>()
            ShowcaseDetailScreen(
                itemIndex = route.itemIndex,
                onBack = { navController.popBackStack() },
            )
        }
    }
}

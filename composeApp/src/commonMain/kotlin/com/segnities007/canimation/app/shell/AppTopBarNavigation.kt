package com.segnities007.canimation.app.shell

import androidx.navigation.NavHostController
import com.segnities007.canimation.navigation.ApiReferenceRoute
import com.segnities007.canimation.navigation.DocsRoute
import com.segnities007.canimation.navigation.ExamplesRoute
import com.segnities007.canimation.navigation.HomeRoute
import com.segnities007.canimation.navigation.TopBarDestination

internal fun NavHostController.navigateTopBarDestination(destination: TopBarDestination) {
    when (destination) {
        TopBarDestination.Docs ->
            navigate(DocsRoute) {
                popUpTo(HomeRoute) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        TopBarDestination.Api ->
            navigate(ApiReferenceRoute) {
                popUpTo(HomeRoute) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        TopBarDestination.Gallery ->
            navigate(ExamplesRoute) {
                popUpTo(HomeRoute) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
    }
}

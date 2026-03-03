package com.segnities007.canimation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.segnities007.canimation.navigation.A11yDemoRoute
import com.segnities007.canimation.navigation.CanimationNavHost
import com.segnities007.canimation.navigation.CustomSpecLabRoute
import com.segnities007.canimation.navigation.DiagnosticsRoute
import com.segnities007.canimation.navigation.HomeRoute
import com.segnities007.canimation.navigation.PresetGalleryRoute
import com.segnities007.canimation.navigation.TokenReferenceRoute
import com.segnities007.canimation.navigation.routeTitle
import com.segnities007.canimation.theme.CanimationTheme
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var policy by remember { mutableStateOf<CanimationPolicy>(CanimationPolicy.SystemAware) }

    CanimationTheme {
        CanimationProvider(policy = policy) {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()

            val route = backStackEntry?.destination?.route
            val currentTitle = routeTitle(route)
            val isHome = route == null || route.contains("HomeRoute")

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(currentTitle) },
                        navigationIcon = {
                            if (!isHome) {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Text("←", style = MaterialTheme.typography.titleLarge)
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                        ),
                    )
                },
                bottomBar = {
                    NavigationBar {
                        val navItems = listOf(
                            Triple("Home", HomeRoute, "🏠"),
                            Triple("Presets", PresetGalleryRoute, "🎨"),
                            Triple("A11y", A11yDemoRoute, "♿"),
                            Triple("Diag", DiagnosticsRoute, "📊"),
                            Triple("Tokens", TokenReferenceRoute, "🔧"),
                        )
                        navItems.forEach { (label, route, icon) ->
                            val selected = backStackEntry?.destination?.route?.contains(route::class.simpleName ?: "") == true
                            NavigationBarItem(
                                selected = selected,
                                onClick = {
                                    navController.navigate(route) {
                                        popUpTo(HomeRoute) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = { Text(icon) },
                                label = { Text(label) },
                            )
                        }
                    }
                },
            ) { innerPadding ->
                CanimationNavHost(
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                )
            }
        }
    }
}
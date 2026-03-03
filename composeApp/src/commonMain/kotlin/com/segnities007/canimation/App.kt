package com.segnities007.canimation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.segnities007.canimation.navigation.A11yDemoRoute
import com.segnities007.canimation.navigation.CanimationNavHost
import com.segnities007.canimation.navigation.CustomSpecLabRoute
import com.segnities007.canimation.navigation.DiagnosticsRoute
import com.segnities007.canimation.navigation.HomeRoute
import com.segnities007.canimation.navigation.PresetGalleryRoute
import com.segnities007.canimation.navigation.TokenReferenceRoute
import com.segnities007.canimation.theme.CanimationTheme
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationProvider

@Composable
fun App() {
    var policy by remember { mutableStateOf<CanimationPolicy>(CanimationPolicy.SystemAware) }
    var darkOverride by remember { mutableStateOf<Boolean?>(null) }
    val isDarkMode = darkOverride ?: isSystemInDarkTheme()

    CanimationTheme(darkTheme = isDarkMode) {
        CanimationProvider(policy = policy) {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val route = backStackEntry?.destination?.route

            Scaffold(
                topBar = {
                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        shadowElevation = 1.dp,
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
                                modifier = Modifier.clickable {
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
                                listOf(
                                    Triple("Presets", PresetGalleryRoute, "PresetGalleryRoute"),
                                    Triple("Lab", CustomSpecLabRoute, "CustomSpecLabRoute"),
                                    Triple("A11y", A11yDemoRoute, "A11yDemoRoute"),
                                    Triple("Diagnostics", DiagnosticsRoute, "DiagnosticsRoute"),
                                    Triple("Tokens", TokenReferenceRoute, "TokenReferenceRoute"),
                                ).forEach { (label, dest, key) ->
                                    val selected = route?.contains(key) == true
                                    TextButton(
                                        onClick = {
                                            navController.navigate(dest) {
                                                popUpTo(HomeRoute) { saveState = true }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                    ) {
                                        Text(
                                            text = label,
                                            style = MaterialTheme.typography.labelLarge,
                                            color = if (selected) MaterialTheme.colorScheme.primary
                                                else MaterialTheme.colorScheme.onSurfaceVariant,
                                            fontWeight = if (selected) FontWeight.Bold
                                                else FontWeight.Normal,
                                        )
                                    }
                                }
                            }
                            TextButton(onClick = { darkOverride = !isDarkMode }) {
                                Text(
                                    text = if (isDarkMode) "☀️" else "🌙",
                                    style = MaterialTheme.typography.titleMedium,
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
                )
            }
        }
    }
}
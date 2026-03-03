package com.segnities007.canimation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.segnities007.canimation.navigation.ExamplesRoute
import com.segnities007.canimation.navigation.PresetGalleryRoute
import com.segnities007.canimation.navigation.TokenReferenceRoute
import com.segnities007.canimation.theme.CanimationTheme
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var policy by remember { mutableStateOf<CanimationPolicy>(CanimationPolicy.SystemAware) }
    var darkOverride by remember { mutableStateOf<Boolean?>(true) }
    val isDarkMode = darkOverride ?: isSystemInDarkTheme()
    var showSettings by remember { mutableStateOf(false) }

    CanimationTheme(darkTheme = isDarkMode) {
        CanimationProvider(policy = policy) {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val route = backStackEntry?.destination?.route

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
                                    Triple("Examples", ExamplesRoute, "ExamplesRoute"),
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
                            IconButton(onClick = { showSettings = true }) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Settings",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
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

            if (showSettings) {
                SettingsBottomSheet(
                    isDarkMode = isDarkMode,
                    onToggleDark = { darkOverride = !isDarkMode },
                    policy = policy,
                    onPolicyChange = { policy = it },
                    onDismiss = { showSettings = false },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsBottomSheet(
    isDarkMode: Boolean,
    onToggleDark: () -> Unit,
    policy: CanimationPolicy,
    onPolicyChange: (CanimationPolicy) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            // Theme
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = if (isDarkMode) Icons.Default.DarkMode
                            else Icons.Default.LightMode,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp),
                    )
                    Column {
                        Text(
                            text = "Dark Mode",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                        )
                        Text(
                            text = if (isDarkMode) "Dark theme active" else "Light theme active",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { onToggleDark() },
                )
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            // Motion policy
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Motion Policy",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = "Controls how animations behave across the app",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.height(4.dp))
                listOf(
                    Triple(CanimationPolicy.SystemAware, "System Aware", "Follows OS reduced motion setting"),
                    Triple(CanimationPolicy.AlwaysFull, "Full Motion", "All animations at full intensity"),
                    Triple(CanimationPolicy.AlwaysReduced, "Reduced Motion", "Simpler, shorter animations"),
                    Triple(CanimationPolicy.AlwaysOff, "Motion Off", "Disable all animations"),
                ).forEach { (p, label, desc) ->
                    Surface(
                        onClick = { onPolicyChange(p) },
                        shape = MaterialTheme.shapes.medium,
                        color = if (policy == p) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surfaceVariant,
                        border = BorderStroke(
                            1.dp,
                            if (policy == p) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.outline,
                        ),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium,
                                    color = if (policy == p) MaterialTheme.colorScheme.onPrimaryContainer
                                        else MaterialTheme.colorScheme.onSurface,
                                )
                                Text(
                                    text = desc,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (policy == p) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                                        else MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                            if (policy == p) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
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
import androidx.compose.material3.Slider
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
import com.segnities007.canimation.component.PresetPreviewTuning
import com.segnities007.canimation.navigation.CanimationNavHost
import com.segnities007.canimation.navigation.HomeRoute
import com.segnities007.canimation.navigation.ApiReferenceRoute
import com.segnities007.canimation.navigation.DocsRoute
import com.segnities007.canimation.navigation.ExamplesRoute
import com.segnities007.canimation.navigation.PresetGalleryRoute
import com.segnities007.canimation.theme.CanimationTheme
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.canimation
import io.github.canimation.core.canimationEnter
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var policy by remember { mutableStateOf<CanimationPolicy>(CanimationPolicy.SystemAware) }
    var darkOverride by remember { mutableStateOf<Boolean?>(true) }
    val isDarkMode = darkOverride ?: isSystemInDarkTheme()
    var showSettings by remember { mutableStateOf(false) }
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

    LaunchedEffect(autoPlayEnabled, cycleMs) {
        if (autoPlayEnabled) {
            while (true) {
                autoPlayTick += 1
                delay(cycleMs.toLong().coerceAtLeast(350L))
            }
        }
    }

    CanimationTheme(darkTheme = isDarkMode) {
        CanimationProvider(policy = policy) {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val route = backStackEntry?.destination?.route
            val isOnPresetGallery = route?.contains("PresetGalleryRoute") == true

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
                                listOf(
                                    Triple("Docs", DocsRoute, "DocsRoute"),
                                    Triple("API", ApiReferenceRoute, "ApiReferenceRoute"),
                                    Triple("Gallery", ExamplesRoute, "ExamplesRoute"),
                                ).forEachIndexed { index, (label, dest, key) ->
                                    val selected = route?.contains(key) == true
                                    TextButton(
                                        onClick = {
                                            navController.navigate(dest) {
                                                popUpTo(HomeRoute) { saveState = true }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        modifier = Modifier.canimation(
                                            visible = navStage >= index + 1,
                                            effect = Canimation.Fade.Up,
                                        ),
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
                    autoPlayEnabled = autoPlayEnabled,
                    autoPlayTick = autoPlayTick,
                    tuning = tuning,
                )
            }

            if (showSettings) {
                SettingsBottomSheet(
                    isDarkMode = isDarkMode,
                    onToggleDark = { darkOverride = !isDarkMode },
                    policy = policy,
                    onPolicyChange = { policy = it },
                    onDismiss = { showSettings = false },
                    showPresetTuning = isOnPresetGallery,
                    autoPlayEnabled = autoPlayEnabled,
                    onAutoPlayChange = { autoPlayEnabled = it },
                    cycleMs = cycleMs,
                    onCycleMsChange = { cycleMs = it },
                    durationScale = durationScale,
                    onDurationScaleChange = { durationScale = it },
                    distanceScale = distanceScale,
                    onDistanceScaleChange = { distanceScale = it },
                    scaleIntensity = scaleIntensity,
                    onScaleIntensityChange = { scaleIntensity = it },
                    rotationScale = rotationScale,
                    onRotationScaleChange = { rotationScale = it },
                    onResetParams = {
                        durationScale = 1f; distanceScale = 1f
                        scaleIntensity = 1f; rotationScale = 1f
                    },
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
    showPresetTuning: Boolean = false,
    autoPlayEnabled: Boolean = true,
    onAutoPlayChange: (Boolean) -> Unit = {},
    cycleMs: Float = 1400f,
    onCycleMsChange: (Float) -> Unit = {},
    durationScale: Float = 1f,
    onDurationScaleChange: (Float) -> Unit = {},
    distanceScale: Float = 1f,
    onDistanceScaleChange: (Float) -> Unit = {},
    scaleIntensity: Float = 1f,
    onScaleIntensityChange: (Float) -> Unit = {},
    rotationScale: Float = 1f,
    onRotationScaleChange: (Float) -> Unit = {},
    onResetParams: () -> Unit = {},
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        var sheetStage by remember { mutableIntStateOf(-1) }
        LaunchedEffect(Unit) { for (i in 0..8) { delay(40); sheetStage = i } }

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
                modifier = Modifier.canimation(visible = sheetStage >= 0, effect = Canimation.Fade.Up),
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            // Theme
            Row(
                modifier = Modifier.fillMaxWidth()
                    .canimation(visible = sheetStage >= 1, effect = Canimation.Fade.Up),
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
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.canimation(visible = sheetStage >= 2, effect = Canimation.Fade.Up),
            ) {
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
                ).forEachIndexed { idx, (p, label, desc) ->
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
                        modifier = Modifier.canimation(
                            visible = sheetStage >= 3 + idx,
                            effect = Canimation.Fade.Up,
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

            // Preset Gallery Tuning (only shown on Presets page)
            if (showPresetTuning) {
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.canimation(visible = sheetStage >= 7, effect = Canimation.Fade.Up),
                ) {
                    Text(
                        text = "Preset Gallery",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text("Auto play all presets", style = MaterialTheme.typography.bodyMedium)
                        Switch(
                            checked = autoPlayEnabled,
                            onCheckedChange = onAutoPlayChange,
                        )
                    }

                    SettingsSlider("Cycle interval", cycleMs, onCycleMsChange, 350f..2500f, "${cycleMs.toInt()}ms")
                    SettingsSlider("Duration scale", durationScale, onDurationScaleChange, 0.5f..2.0f, "${fmtFloat(durationScale)}x")
                    SettingsSlider("Distance scale", distanceScale, onDistanceScaleChange, 0.2f..2.5f, "${fmtFloat(distanceScale)}x")
                    SettingsSlider("Scale intensity", scaleIntensity, onScaleIntensityChange, 0.2f..2.5f, "${fmtFloat(scaleIntensity)}x")
                    SettingsSlider("Rotation scale", rotationScale, onRotationScaleChange, 0.2f..2.5f, "${fmtFloat(rotationScale)}x")

                    TextButton(onClick = onResetParams) {
                        Text("Reset parameters")
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    displayValue: String,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(label, style = MaterialTheme.typography.bodySmall)
            Text(displayValue, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Slider(value = value, onValueChange = onValueChange, valueRange = valueRange)
    }
}

private fun fmtFloat(v: Float): String {
    val rounded = (v * 100).toInt()
    val sign = if (rounded < 0) "-" else ""
    val abs = if (rounded < 0) -rounded else rounded
    return "$sign${abs / 100}.${(abs % 100).toString().padStart(2, '0')}"
}
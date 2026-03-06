package com.segnities007.canimation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light theme — polished to match dark theme accent palette
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6750A4),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFEADDFF),
    onPrimaryContainer = Color(0xFF21005E),
    secondary = Color(0xFF00897B),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFB2DFDB),
    tertiary = Color(0xFF00897B),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFB2DFDB),
    onTertiaryContainer = Color(0xFF002019),
    surface = Color(0xFFFCFCFF),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFF0EDF4),
    onSurfaceVariant = Color(0xFF49454F),
    outline = Color(0xFFCAC4D0),
    outlineVariant = Color(0xFFE0DDE4),
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
)

// Dark theme — Motion.dev-inspired deep dark palette
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF9B8AFF),
    onPrimary = Color(0xFF1A1030),
    primaryContainer = Color(0xFF2A2040),
    onPrimaryContainer = Color(0xFFD0BCFF),
    secondary = Color(0xFF64D8CB),
    onSecondary = Color(0xFF0A3028),
    secondaryContainer = Color(0xFF1A3A35),
    onSecondaryContainer = Color(0xFFA0F0E0),
    tertiary = Color(0xFF64D8CB),
    onTertiary = Color(0xFF0A3028),
    tertiaryContainer = Color(0xFF1A3A35),
    onTertiaryContainer = Color(0xFFA0F0E0),
    surface = Color(0xFF0E0E16),
    onSurface = Color(0xFFE8E6F0),
    surfaceVariant = Color(0xFF1A1A24),
    onSurfaceVariant = Color(0xFFA0A0B0),
    outline = Color(0xFF2A2A3A),
    outlineVariant = Color(0xFF1E1E2A),
    background = Color(0xFF0A0A12),
    onBackground = Color(0xFFE8E6F0),
)

@Composable
fun CanimationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        content = content,
    )
}

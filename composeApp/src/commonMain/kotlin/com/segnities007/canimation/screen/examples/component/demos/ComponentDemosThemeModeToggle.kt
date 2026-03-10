package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.examples_theme_dark_mode
import canimation.composeapp.generated.resources.examples_theme_light_mode
import com.segnities007.canimation.compose.rememberLoopingToggle
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedThemeToggle(modifier: Modifier = Modifier) {
    val autoIsDark = rememberLoopingToggle(
        initialValue = false,
        initialDelayMillis = 2000L,
        toggleDelayMillis = 2000L,
    )
    var isDark by remember { mutableStateOf(autoIsDark) }

    LaunchedEffect(autoIsDark) {
        isDark = autoIsDark
    }

    val background by animateColorAsState(
        if (isDark) Color(0xFF1E293B) else Color(0xFFF1F5F9),
        tween(500),
    )
    val foreground by animateColorAsState(
        if (isDark) Color.White else Color(0xFF0F172A),
        tween(500),
    )
    val rotation by animateFloatAsState(if (isDark) 180f else 0f, spring(dampingRatio = 0.5f))

    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = background,
            modifier = Modifier.fillMaxWidth(0.5f).height(48.dp).clickable { isDark = !isDark },
        ) {
            Row(
                Modifier.fillMaxSize().padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    if (isDark) Icons.Default.DarkMode else Icons.Default.LightMode,
                    contentDescription = null,
                    tint = foreground,
                    modifier = Modifier.graphicsLayer { rotationZ = rotation }.size(20.dp),
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    stringResource(
                        if (isDark) Res.string.examples_theme_dark_mode else Res.string.examples_theme_light_mode,
                    ),
                    color = foreground,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

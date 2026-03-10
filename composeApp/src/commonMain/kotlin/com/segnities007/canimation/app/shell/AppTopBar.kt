package com.segnities007.canimation.app.shell

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.app_title
import canimation.composeapp.generated.resources.cd_open_settings
import canimation.composeapp.generated.resources.cd_toggle_dark_mode
import com.segnities007.canimation.app.state.AppUiState
import com.segnities007.canimation.navigation.HomeRoute
import com.segnities007.canimation.navigation.TopBarDestination
import com.segnities007.canimation.navigation.matchesRoute
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AppTopBar(
    uiState: AppUiState,
    currentRoute: String?,
    isDarkMode: Boolean,
    onHomeClick: () -> Unit,
    onNavigate: (TopBarDestination) -> Unit,
    onToggleDark: () -> Unit,
    onOpenSettings: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(Res.string.app_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier =
                    Modifier
                        .canimation(visible = uiState.navStage >= 0, effect = Canimation.Fade.Left)
                        .clickable(onClick = onHomeClick),
            )

            Spacer(Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.horizontalScroll(rememberScrollState()),
            ) {
                TopBarDestination.entries.forEachIndexed { index, destination ->
                    val selected =
                        when (destination.routeKey) {
                            HomeRoute.toString() -> false
                            else -> currentRoute.matchesRoute(destination.routeKey)
                        }
                    TextButton(
                        onClick = { onNavigate(destination) },
                        modifier =
                            Modifier.canimation(
                                visible = uiState.navStage >= index + 1,
                                effect = Canimation.Fade.Up,
                            ),
                    ) {
                        Text(
                            text = stringResource(destination.labelRes),
                            style = MaterialTheme.typography.labelLarge,
                            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        )
                    }
                }
            }

            IconButton(
                onClick = onToggleDark,
                modifier =
                    Modifier.canimation(
                        visible = uiState.navStage >= 4,
                        effect = Canimation.Fade.Up,
                    ),
            ) {
                Icon(
                    imageVector = if (isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                    contentDescription = stringResource(Res.string.cd_toggle_dark_mode),
                )
            }

            IconButton(
                onClick = onOpenSettings,
                modifier =
                    Modifier.canimation(
                        visible = uiState.navStage >= 5,
                        effect = Canimation.Fade.Up,
                    ),
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(Res.string.cd_open_settings),
                )
            }
        }
    }
}

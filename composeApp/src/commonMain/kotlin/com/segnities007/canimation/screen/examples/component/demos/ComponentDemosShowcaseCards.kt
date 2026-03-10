package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_event_day
import canimation.composeapp.generated.resources.component_event_location
import canimation.composeapp.generated.resources.component_event_month
import canimation.composeapp.generated.resources.component_event_title
import canimation.composeapp.generated.resources.component_recipe_meta
import canimation.composeapp.generated.resources.component_recipe_tag_italian
import canimation.composeapp.generated.resources.component_recipe_tag_vegetarian
import canimation.composeapp.generated.resources.component_recipe_title
import canimation.composeapp.generated.resources.component_weather_location
import canimation.composeapp.generated.resources.examples_location_bella_center
import canimation.composeapp.generated.resources.examples_weather_cloudy
import canimation.composeapp.generated.resources.examples_weather_rainy
import canimation.composeapp.generated.resources.examples_weather_sunny
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingIndex
import com.segnities007.canimation.compose.rememberReplayVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

private data class WeatherItem(
    val icon: ImageVector,
    val summary: StringResource,
)

@Composable
fun RecipeCard(modifier: Modifier = Modifier) {
    val visible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    Box(modifier.fillMaxWidth().padding(16.dp).canimation(visible = visible, effect = Canimation.Rise.In)) {
        Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Restaurant, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(24.dp))
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(stringResource(Res.string.component_recipe_title), style = MaterialTheme.typography.labelLarge)
                        Text(stringResource(Res.string.component_recipe_meta), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf(
                        stringResource(Res.string.component_recipe_tag_italian),
                        stringResource(Res.string.component_recipe_tag_vegetarian),
                    ).forEach { tag ->
                        Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                            Text(tag, Modifier.padding(horizontal = 8.dp, vertical = 2.dp), style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherCard(modifier: Modifier = Modifier) {
    val weathers = listOf(
        WeatherItem(Icons.Default.WbSunny, Res.string.examples_weather_sunny),
        WeatherItem(Icons.Default.WaterDrop, Res.string.examples_weather_rainy),
        WeatherItem(Icons.Default.Cloud, Res.string.examples_weather_cloudy),
    )
    val index = rememberLoopingIndex(itemCount = weathers.size, initialDelayMillis = 2500L, stepDelayMillis = 2500L)
    val visible = rememberReplayVisibility(replayKey = index, resetDurationMillis = 50L)
    Box(modifier.fillMaxWidth().padding(16.dp).canimation(visible = visible, effect = Canimation.Fade.Gentle)) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF3B82F6).copy(alpha = 0.1f),
            border = BorderStroke(1.dp, Color(0xFF3B82F6).copy(alpha = 0.2f)),
        ) {
            Row(Modifier.padding(14.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(weathers[index].icon, contentDescription = null, tint = Color(0xFF3B82F6), modifier = Modifier.size(28.dp))
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(text = stringResource(weathers[index].summary), style = MaterialTheme.typography.titleMedium)
                    Text(stringResource(Res.string.component_weather_location), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
fun EventCard(modifier: Modifier = Modifier) {
    val visible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    Box(modifier.fillMaxWidth().padding(16.dp).canimation(visible = visible, effect = Canimation.Slide.LeftSubtle)) {
        Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
            Row(Modifier.padding(14.dp)) {
                Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primary, modifier = Modifier.size(48.dp)) {
                    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text(stringResource(Res.string.component_event_day), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onPrimary)
                        Text(stringResource(Res.string.component_event_month), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f))
                    }
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(stringResource(Res.string.component_event_title), style = MaterialTheme.typography.labelLarge)
                    Text(stringResource(Res.string.component_event_location), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.size(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(14.dp))
                        Text(stringResource(Res.string.examples_location_bella_center), style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}

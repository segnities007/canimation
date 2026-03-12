package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.LoopingIntStep
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingIntSequence
import com.segnities007.canimation.compose.rememberLoopingVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import canimation.composeapp.generated.resources.*

@Composable
fun ProfileCard(modifier: Modifier = Modifier) {
    val visible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.surfaceVariant, modifier = modifier.fillMaxWidth().padding(16.dp).canimation(visible = visible, effect = Canimation.Tilt.Swing)) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Surface(shape = CircleShape, color = Color(0xFF6366F1), modifier = Modifier.size(48.dp)) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(stringResource(Res.string.demo_jd), color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
            Column {
                Text(stringResource(Res.string.demo_jane_doe), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(stringResource(Res.string.demo_senior_engineer), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun PricingCard(modifier: Modifier = Modifier) {
    val visible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.primaryContainer, border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary), modifier = modifier.fillMaxWidth().padding(16.dp).canimation(visible = visible, effect = Canimation.Scale.Pop)) {
        Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(stringResource(Res.string.component_pro), style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Text(stringResource(Res.string.component_price_29mo), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(stringResource(Res.string.component_unlimited_animations), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
fun NotificationCard(modifier: Modifier = Modifier) {
    val visible = rememberLoopingVisibility(visibleDurationMillis = 3000L, hiddenDurationMillis = 800L)
    Box(modifier.fillMaxWidth().padding(16.dp).canimation(visible = visible, effect = Canimation.Slide.RightSubtle)) {
        Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.tertiaryContainer, modifier = Modifier.fillMaxWidth()) {
            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Surface(shape = CircleShape, color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.size(8.dp)) {}
                Column(Modifier.weight(1f)) {
                    Text(stringResource(Res.string.component_new_animation_added), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Text(stringResource(Res.string.component_wave_gentle_available), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Text(stringResource(Res.string.component_2m), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun TestimonialCard(modifier: Modifier = Modifier) {
    val visible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.surfaceVariant, modifier = modifier.fillMaxWidth().padding(16.dp).canimation(visible = visible, effect = Canimation.Float.Gentle)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(stringResource(Res.string.component_quote_mark), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            Text(stringResource(Res.string.component_testimonial_quote), style = MaterialTheme.typography.bodyMedium)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(shape = CircleShape, color = Color(0xFFEC4899), modifier = Modifier.size(24.dp)) {}
                Text(stringResource(Res.string.component_testimonial_author), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun MetricCard(modifier: Modifier = Modifier) {
    val value = rememberLoopingIntSequence(
        initialValue = 0,
        steps = List(15) { index ->
            val stepValue = 842 * (index + 1) / 15
            val holdDurationMillis = if (index == 14) 2500L else 50L
            LoopingIntStep(value = stepValue, holdDurationMillis = holdDurationMillis)
        },
    )
    Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.surfaceVariant, modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(stringResource(Res.string.component_active_users), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(value.toString(), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
                Text(stringResource(Res.string.component_growth_82), style = MaterialTheme.typography.labelSmall, color = Color(0xFF22C55E), fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 6.dp))
            }
        }
    }
}

@Composable
fun ProductCard(modifier: Modifier = Modifier) {
    val visible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.surface, border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant), modifier = modifier.fillMaxWidth().padding(16.dp).canimation(visible = visible, effect = Canimation.Diagonal.BottomRight)) {
        Column {
            Box(Modifier.fillMaxWidth().height(80.dp).background(MaterialTheme.colorScheme.primaryContainer), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Inbox, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(32.dp))
            }
            Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(stringResource(Res.string.component_product_animation_kit), style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text(stringResource(Res.string.component_product_price_49), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    repeat(5) { Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFF59E0B), modifier = Modifier.size(14.dp)) }
                }
            }
        }
    }
}

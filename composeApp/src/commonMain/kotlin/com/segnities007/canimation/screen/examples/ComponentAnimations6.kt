package com.segnities007.canimation.screen.examples

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.*
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.PI
import kotlin.math.sin

private data class FeatureItem(
    val label: StringResource,
    val icon: ImageVector,
)

// ─── 1. AnimatedBreadcrumb ───
@Composable
fun AnimatedBreadcrumb(modifier: Modifier = Modifier) {
    val items = listOf("Home", "Products", "Electronics", "Phones")
    Row(modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        items.forEachIndexed { i, label ->
            var visible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { delay(i * 120L); visible = true }
            Box(Modifier.canimation(visible = visible, effect = Canimation.Slide.LeftSubtle)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (i == items.lastIndex) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = if (i == items.lastIndex) FontWeight.Bold else FontWeight.Normal,
                )
            }
            if (i < items.lastIndex) {
                Text(" / ", color = MaterialTheme.colorScheme.outline, modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}
// ─── 3. AnimatedTagCloud ───
@Composable
fun AnimatedTagCloud(modifier: Modifier = Modifier) {
    val tags = listOf("Kotlin", "Compose", "Animation", "Multiplatform", "UI", "Design", "Motion", "Effect")
    Column(modifier.fillMaxWidth().padding(16.dp)) {
        @OptIn(ExperimentalLayoutApi::class)
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            tags.forEachIndexed { i, tag ->
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { delay(i * 80L); visible = true }
                Box(Modifier.canimation(visible = visible, effect = Canimation.Float.ScaleUp)) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                    ) {
                        Text(tag, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSecondaryContainer)
                    }
                }
            }
        }
    }
}

// ─── 4. AnimatedTimeline ───
@Composable
fun AnimatedTimeline(modifier: Modifier = Modifier) {
    val events = listOf("Created" to "Jan 1", "In Progress" to "Jan 5", "Review" to "Jan 10", "Shipped" to "Jan 12")
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        events.forEachIndexed { i, (title, date) ->
            var visible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { delay(i * 200L); visible = true }
            Row(
                modifier = Modifier.canimation(visible = visible, effect = Canimation.Rise.In),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primary, modifier = Modifier.size(10.dp)) {}
                Column {
                    Text(title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                    Text(date, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

// ─── 5. PulseRing ───
@Composable
fun PulseRing(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.5f, targetValue = 1.5f,
        animationSpec = infiniteRepeatable(tween(1500, easing = LinearEasing), RepeatMode.Restart)
    )
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.7f, targetValue = 0f,
        animationSpec = infiniteRepeatable(tween(1500, easing = LinearEasing), RepeatMode.Restart)
    )
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(80.dp)) {
            drawCircle(color = primaryColor.copy(alpha = alpha), radius = size.minDimension / 2 * scale, style = Stroke(2.dp.toPx()))
        }
        Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp)) {}
    }
}

// ─── 6. AnimatedRating ───
@Composable
fun AnimatedRating(modifier: Modifier = Modifier) {
    var rating by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(1000); rating = (rating % 5) + 1 } }

    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center) {
        (1..5).forEach { star ->
            var vis by remember { mutableStateOf(false) }
            LaunchedEffect(rating) { vis = star <= rating }
            Box(
                modifier = Modifier.size(32.dp).canimation(visible = vis, effect = Canimation.Scale.Pop),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = if (star <= rating) Icons.Default.Star else Icons.Default.StarOutline,
                    contentDescription = null,
                    tint = if (star <= rating) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.size(22.dp),
                )
            }
        }
    }
}

// ─── 7. StackedAvatars ───
@Composable
fun StackedAvatars(modifier: Modifier = Modifier) {
    val colors = listOf(Color(0xFF6366F1), Color(0xFFEC4899), Color(0xFF14B8A6), Color(0xFFF59E0B), Color(0xFF8B5CF6))
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center) {
        colors.forEachIndexed { i, color ->
            var visible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { delay(i * 100L); visible = true }
            Box(Modifier.offset(x = (-(i * 12)).dp).canimation(visible = visible, effect = Canimation.Diagonal.Subtle)) {
                Surface(
                    shape = CircleShape,
                    color = color,
                    modifier = Modifier.size(40.dp),
                    border = androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.surface),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("${('A'.code + i).toChar()}", color = Color.White, style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
    }
}

// ─── 8. AnimatedStatCard ───
@Composable
fun AnimatedStatCard(modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.fillMaxWidth().padding(16.dp).canimation(visible = visible, effect = Canimation.Tilt.Swing),
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text("Revenue", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("$12,450", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            }
            Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFF22C55E).copy(alpha = 0.15f)) {
                Text("+12.5%", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = Color(0xFF22C55E), style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// ─── 9. ColorSwatchPicker ───
@Composable
fun ColorSwatchPicker(modifier: Modifier = Modifier) {
    val swatches = listOf(Color(0xFFEF4444), Color(0xFFF97316), Color(0xFFEAB308), Color(0xFF22C55E), Color(0xFF3B82F6), Color(0xFF8B5CF6))
    var selected by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(900); selected = (selected + 1) % swatches.size } }

    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
        swatches.forEachIndexed { i, color ->
            val isSelected = i == selected
            var vis by remember { mutableStateOf(false) }
            LaunchedEffect(isSelected) { vis = isSelected }
            Surface(
                shape = CircleShape,
                color = color,
                modifier = Modifier.size(if (isSelected) 40.dp else 32.dp)
                    .canimation(visible = vis, effect = Canimation.Elastic.Snap),
                border = if (isSelected) androidx.compose.foundation.BorderStroke(3.dp, MaterialTheme.colorScheme.onSurface)
                else null,
            ) {}
        }
    }
}

// ─── 10. AnimatedCodeBlock ───
@Composable
fun AnimatedCodeBlock(modifier: Modifier = Modifier) {
    val lines = listOf(
        "val effect = Canimation.Fade.Up",
        "Modifier.canimation(",
        "    visible = isVisible,",
        "    effect = effect,",
        ")",
    )
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFF1E1E2E),
        modifier = modifier.fillMaxWidth().padding(16.dp),
    ) {
        Column(Modifier.padding(12.dp)) {
            lines.forEachIndexed { i, line ->
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { delay(i * 150L); visible = true }
                Box(Modifier.canimation(visible = visible, effect = Canimation.Slide.LeftSubtle)) {
                    Text(line, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFA6E3A1))
                }
            }
        }
    }
}

// ─── 11. AnimatedNavItem ───
@Composable
fun AnimatedNavItem(modifier: Modifier = Modifier) {
    val items = listOf("Home", "Search", "Profile", "Settings")
    var active by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(1500); active = (active + 1) % items.size } }

    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
        items.forEachIndexed { i, label ->
            val isActive = i == active
            var vis by remember { mutableStateOf(false) }
            LaunchedEffect(isActive) { vis = isActive }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    Modifier.size(24.dp)
                        .canimation(visible = vis, effect = Canimation.Drop.Light)
                        .background(
                            if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                            CircleShape,
                        )
                )
                Spacer(Modifier.height(4.dp))
                Text(label, style = MaterialTheme.typography.labelSmall,
                    color = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal)
            }
        }
    }
}
// ─── 13. AnimatedKpi ───
@Composable
fun AnimatedKpi(modifier: Modifier = Modifier) {
    val kpis = listOf("Users" to "1,240", "Orders" to "358", "Revenue" to "$8.2K")
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
        kpis.forEachIndexed { i, (label, value) ->
            var visible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { delay(i * 150L); visible = true }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.canimation(visible = visible, effect = Canimation.Rise.Scale),
            ) {
                Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

// ─── 14. WaveProgressBar ───
@Composable
fun WaveProgressBar(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = (2 * PI).toFloat(),
        animationSpec = infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart)
    )
    val progress by infiniteTransition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f,
        animationSpec = infiniteRepeatable(tween(3000), RepeatMode.Reverse)
    )
    val primaryColor = MaterialTheme.colorScheme.primary
    val trackColor = MaterialTheme.colorScheme.surfaceVariant
    Canvas(modifier.fillMaxWidth().height(32.dp).padding(16.dp)) {
        val w = size.width
        val h = size.height
        drawRoundRect(color = trackColor, size = size, cornerRadius = androidx.compose.ui.geometry.CornerRadius(h / 2))
        val filledW = w * progress
        for (x in 0..filledW.toInt()) {
            val waveY = sin(phase + x * 0.05f) * 2
            drawCircle(color = primaryColor, radius = 1.5f, center = androidx.compose.ui.geometry.Offset(x.toFloat(), h / 2 + waveY))
        }
    }
}

// ─── 15. AnimatedListItem ───
@Composable
fun AnimatedListItem(modifier: Modifier = Modifier) {
    val items = listOf("Design System", "Components", "Animations", "Tokens", "Patterns")
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items.forEachIndexed { i, item ->
            var visible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { delay(i * 100L); visible = true }
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth().canimation(visible = visible, effect = Canimation.Tilt.Left),
            ) {
                Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f), modifier = Modifier.size(8.dp)) {}
                    Spacer(Modifier.width(12.dp))
                    Text(item, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

// ─── 16. AnimatedPricingToggle ───
@Composable
fun AnimatedPricingToggle(modifier: Modifier = Modifier) {
    var annual by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(2000); annual = !annual } }

    Column(modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Monthly", style = MaterialTheme.typography.labelMedium,
                color = if (!annual) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant)
            Switch(checked = annual, onCheckedChange = {})
            Text("Annual", style = MaterialTheme.typography.labelMedium,
                color = if (annual) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Spacer(Modifier.height(8.dp))
        var vis by remember { mutableStateOf(false) }
        LaunchedEffect(annual) { vis = false; delay(50); vis = true }
        Box(Modifier.canimation(visible = vis, effect = Canimation.Shrink.Subtle)) {
            Text(
                text = if (annual) "$99/year" else "$12/month",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

// ─── 17. AnimatedFeatureGrid ───
@Composable
fun AnimatedFeatureGrid(modifier: Modifier = Modifier) {
    val features = listOf(
        FeatureItem(Res.string.examples_feature_fast, Icons.Default.Bolt),
        FeatureItem(Res.string.examples_feature_secure, Icons.Default.Lock),
        FeatureItem(Res.string.examples_feature_simple, Icons.Default.AutoAwesome),
        FeatureItem(Res.string.examples_feature_flexible, Icons.Default.Tune),
    )
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        features.chunked(2).forEachIndexed { rowIdx, row ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEachIndexed { colIdx, feature ->
                    var visible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) { delay((rowIdx * 2 + colIdx) * 150L); visible = true }
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.weight(1f).canimation(visible = visible, effect = Canimation.Diagonal.BottomRight),
                    ) {
                        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = feature.icon,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(26.dp),
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = stringResource(feature.label),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }
            }
        }
    }
}

// ─── 18. AnimatedSearchResult ───
@Composable
fun AnimatedSearchResult(modifier: Modifier = Modifier) {
    val results = listOf(
        "Canimation.Fade.Up" to "Smooth fade-in from below",
        "Canimation.Scale.Pop" to "Pop scale entry effect",
        "Canimation.Bounce.In" to "Bouncy entrance animation",
    )
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        results.forEachIndexed { i, (title, desc) ->
            var visible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { delay(i * 120L); visible = true }
            Column(Modifier.fillMaxWidth().canimation(visible = visible, effect = Canimation.Float.Gentle)) {
                Text(title, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary)
                Text(desc, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                if (i < results.lastIndex) HorizontalDivider(Modifier.padding(top = 6.dp), color = MaterialTheme.colorScheme.outlineVariant)
            }
        }
    }
}
// ─── 20. AnimatedChipGroup ───
@Composable
fun AnimatedChipGroup(modifier: Modifier = Modifier) {
    val chips = listOf("All", "Active", "Completed", "Archived")
    var selected by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(1200); selected = (selected + 1) % chips.size } }

    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        chips.forEachIndexed { i, label ->
            val isSelected = i == selected
            val bgColor by animateColorAsState(
                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
            )
            val textColor by animateColorAsState(
                if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = bgColor,
                modifier = Modifier.clickable {},
            ) {
                Text(label, modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelMedium, color = textColor)
            }
        }
    }
}

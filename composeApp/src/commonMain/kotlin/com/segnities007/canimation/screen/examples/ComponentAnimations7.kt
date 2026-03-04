package com.segnities007.canimation.screen.examples

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.sin

// ═══════════════════════════════════════════════════════════
//  TEXT ANIMATIONS
// ═══════════════════════════════════════════════════════════

// ─── 1. TextFadeReveal ───
@Composable
fun TextFadeReveal(modifier: Modifier = Modifier) {
    val words = listOf("Build", "Animate", "Ship", "Delight")
    var index by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(1500); index = (index + 1) % words.size } }
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(index) { vis = false; delay(50); vis = true }
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Text(
            text = words[index],
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.canimation(visible = vis, effect = Canimation.Fade.Up),
        )
    }
}

// ─── 2. TextCountUp ───
@Composable
fun TextCountUp(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    val target = 1234
    LaunchedEffect(Unit) {
        while (true) {
            count = 0
            repeat(20) { i -> delay(60); count = (target * (i + 1) / 20) }
            delay(2000)
        }
    }
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Text(
            text = "$count",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

// ─── 3. TextHighlighter ───
@Composable
fun TextHighlighter(modifier: Modifier = Modifier) {
    val words = "Compose Multiplatform Animation Library".split(" ")
    var highlighted by remember { mutableIntStateOf(-1) }
    LaunchedEffect(Unit) {
        while (true) {
            for (i in words.indices) { highlighted = i; delay(400) }
            highlighted = -1; delay(600)
        }
    }
    Row(
        modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        words.forEachIndexed { i, word ->
            val bg by animateColorAsState(
                if (i == highlighted) MaterialTheme.colorScheme.primaryContainer
                else Color.Transparent
            )
            Text(
                text = word,
                modifier = Modifier.background(bg, RoundedCornerShape(4.dp)).padding(horizontal = 4.dp, vertical = 2.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (i == highlighted) FontWeight.Bold else FontWeight.Normal,
                color = if (i == highlighted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
            )
            if (i < words.lastIndex) Spacer(Modifier.width(4.dp))
        }
    }
}

// ─── 4. TextShuffleWord ───
@Composable
fun TextShuffleWord(modifier: Modifier = Modifier) {
    val options = listOf("developers", "designers", "creators", "teams")
    var idx by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(2000); idx = (idx + 1) % options.size } }
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(idx) { vis = false; delay(50); vis = true }
    Column(modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Built for", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Box(Modifier.canimation(visible = vis, effect = Canimation.Slide.UpSubtle)) {
            Text(options[idx], style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }
    }
}

// ─── 5. TextGradientReveal ───
@Composable
fun TextGradientReveal(modifier: Modifier = Modifier) {
    val chars = "canimation".toList()
    Column(modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            chars.forEachIndexed { i, c ->
                var vis by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    while (true) { delay(i * 80L); vis = true; delay(2000); vis = false; delay(300) }
                }
                Text(
                    text = c.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.canimation(visible = vis, effect = Canimation.Rise.In),
                )
            }
        }
    }
}

// ─── 6. TextRotateWords ───
@Composable
fun TextRotateWords(modifier: Modifier = Modifier) {
    val words = listOf("Fast", "Simple", "Beautiful", "Powerful")
    var idx by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(1800); idx = (idx + 1) % words.size } }
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(idx) { vis = false; delay(50); vis = true }
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center) {
        Text("Animations are ", style = MaterialTheme.typography.bodyLarge)
        Box(Modifier.canimation(visible = vis, effect = Canimation.Flip.In)) {
            Text(words[idx], style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }
    }
}

// ═══════════════════════════════════════════════════════════
//  CARD ANIMATIONS
// ═══════════════════════════════════════════════════════════

// ─── 7. ProfileCard ───
@Composable
fun ProfileCard(modifier: Modifier = Modifier) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { vis = true }
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.fillMaxWidth().padding(16.dp).canimation(visible = vis, effect = Canimation.Tilt.Swing),
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Surface(shape = CircleShape, color = Color(0xFF6366F1), modifier = Modifier.size(48.dp)) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("JD", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
            Column {
                Text("Jane Doe", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text("Senior Engineer", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

// ─── 8. PricingCard ───
@Composable
fun PricingCard(modifier: Modifier = Modifier) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { vis = true }
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        modifier = modifier.fillMaxWidth().padding(16.dp).canimation(visible = vis, effect = Canimation.Scale.Pop),
    ) {
        Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("PRO", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Text("$29/mo", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Unlimited animations", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

// ─── 9. NotificationCard ───
@Composable
fun NotificationCard(modifier: Modifier = Modifier) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { vis = true; delay(3000); vis = false; delay(800) } }
    Box(modifier.fillMaxWidth().padding(16.dp).canimation(visible = vis, effect = Canimation.Slide.RightSubtle)) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Surface(shape = CircleShape, color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.size(8.dp)) {}
                Column(Modifier.weight(1f)) {
                    Text("New animation added!", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Text("Canimation.Wave.Gentle is now available", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Text("2m", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

// ─── 10. TestimonialCard ───
@Composable
fun TestimonialCard(modifier: Modifier = Modifier) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { vis = true }
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.fillMaxWidth().padding(16.dp).canimation(visible = vis, effect = Canimation.Float.Gentle),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("\"\"\"", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            Text("This library made our app feel alive. The API is incredibly intuitive.", style = MaterialTheme.typography.bodyMedium)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(shape = CircleShape, color = Color(0xFFEC4899), modifier = Modifier.size(24.dp)) {}
                Text("— Alex K.", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

// ─── 11. MetricCard ───
@Composable
fun MetricCard(modifier: Modifier = Modifier) {
    var value by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            value = 0; repeat(15) { i -> delay(50); value = 842 * (i + 1) / 15 }; delay(2500)
        }
    }
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.fillMaxWidth().padding(16.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Active Users", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("$value", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
                Text("+8.2%", style = MaterialTheme.typography.labelSmall, color = Color(0xFF22C55E), fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 6.dp))
            }
        }
    }
}

// ─── 12. ProductCard ───
@Composable
fun ProductCard(modifier: Modifier = Modifier) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { vis = true }
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier.fillMaxWidth().padding(16.dp).canimation(visible = vis, effect = Canimation.Diagonal.BottomRight),
    ) {
        Column {
            Box(Modifier.fillMaxWidth().height(80.dp).background(MaterialTheme.colorScheme.primaryContainer), contentAlignment = Alignment.Center) {
                Text("📦", style = MaterialTheme.typography.headlineLarge)
            }
            Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Animation Kit", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text("$49.99", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    repeat(5) { Text("★", style = MaterialTheme.typography.labelSmall, color = Color(0xFFF59E0B)) }
                }
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════
//  NAVIGATION UI ANIMATIONS
// ═══════════════════════════════════════════════════════════

// ─── 13. TabBarIndicator ───
@Composable
fun TabBarIndicator(modifier: Modifier = Modifier) {
    val tabs = listOf("Home", "Explore", "Library", "Profile")
    var selected by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(1500); selected = (selected + 1) % tabs.size } }

    Column(modifier.fillMaxWidth().padding(16.dp)) {
        Row(Modifier.fillMaxWidth()) {
            tabs.forEachIndexed { i, tab ->
                val isSelected = i == selected
                Column(
                    modifier = Modifier.weight(1f).clickable { selected = i },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        tab, style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Spacer(Modifier.height(6.dp))
                    val color by animateColorAsState(
                        if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                    )
                    Box(Modifier.fillMaxWidth().height(3.dp).clip(RoundedCornerShape(2.dp)).background(color))
                }
            }
        }
    }
}

// ─── 14. SideMenuReveal ───
@Composable
fun SideMenuReveal(modifier: Modifier = Modifier) {
    val items = listOf("Dashboard", "Analytics", "Settings", "Logout")
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        items.forEachIndexed { i, item ->
            var vis by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { delay(i * 120L); vis = true }
            Row(
                modifier = Modifier.fillMaxWidth().canimation(visible = vis, effect = Canimation.Slide.LeftSubtle)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (i == 0) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primary.copy(alpha = if (i == 0) 1f else 0.15f), modifier = Modifier.size(8.dp)) {}
                Text(item, style = MaterialTheme.typography.bodyMedium, fontWeight = if (i == 0) FontWeight.Bold else FontWeight.Normal)
            }
        }
    }
}

// ─── 15. PaginationDots ───
@Composable
fun PaginationDots(modifier: Modifier = Modifier) {
    var active by remember { mutableIntStateOf(0) }
    val total = 5
    LaunchedEffect(Unit) { while (true) { delay(1200); active = (active + 1) % total } }

    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        repeat(total) { i ->
            val isActive = i == active
            val width by animateFloatAsState(if (isActive) 24f else 8f, tween(300))
            val color by animateColorAsState(
                if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
            )
            Box(
                Modifier.padding(horizontal = 3.dp).height(8.dp).width(width.dp)
                    .clip(RoundedCornerShape(4.dp)).background(color)
            )
        }
    }
}

// ─── 16. CommandPalette ───
@Composable
fun CommandPalette(modifier: Modifier = Modifier) {
    val items = listOf("Open File...", "Go to Line", "Toggle Theme", "Run Build")
    var highlighted by remember { mutableIntStateOf(-1) }
    LaunchedEffect(Unit) { while (true) { for (i in items.indices) { highlighted = i; delay(800) }; highlighted = -1; delay(500) } }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier.fillMaxWidth().padding(16.dp),
    ) {
        Column {
            Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("⌘", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.width(8.dp))
                Text("Type a command...", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f))
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            items.forEachIndexed { i, item ->
                val bg by animateColorAsState(
                    if (i == highlighted) MaterialTheme.colorScheme.primaryContainer else Color.Transparent
                )
                Text(
                    item, modifier = Modifier.fillMaxWidth().background(bg).padding(horizontal = 12.dp, vertical = 10.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (i == highlighted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════
//  DATA DISPLAY ANIMATIONS
// ═══════════════════════════════════════════════════════════

// ─── 17. MiniBarChart ───
@Composable
fun MiniBarChart(modifier: Modifier = Modifier) {
    val values = listOf(0.4f, 0.7f, 0.5f, 0.9f, 0.6f, 0.8f, 0.3f)
    val primaryColor = MaterialTheme.colorScheme.primary
    val surfaceColor = MaterialTheme.colorScheme.surfaceVariant
    Column(modifier.fillMaxWidth().padding(16.dp)) {
        Row(Modifier.fillMaxWidth().height(60.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.Bottom) {
            values.forEachIndexed { i, v ->
                var vis by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { delay(i * 100L); vis = true }
                val height by animateFloatAsState(if (vis) v * 60f else 0f, tween(500))
                Box(Modifier.width(16.dp).height(height.dp).clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)).background(primaryColor))
            }
        }
        Spacer(Modifier.height(4.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf("M", "T", "W", "T", "F", "S", "S").forEach {
                Text(it, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.width(16.dp), textAlign = TextAlign.Center)
            }
        }
    }
}

// ─── 18. DonutChart ───
@Composable
fun DonutChart(modifier: Modifier = Modifier) {
    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) { while (true) { progress.animateTo(0.72f, tween(1200)); delay(2000); progress.animateTo(0f, tween(600)); delay(400) } }
    val primaryColor = MaterialTheme.colorScheme.primary
    val trackColor = MaterialTheme.colorScheme.surfaceVariant
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(80.dp)) {
            drawArc(trackColor, 0f, 360f, false, style = Stroke(12f, cap = StrokeCap.Round))
            drawArc(primaryColor, -90f, 360f * progress.value, false, style = Stroke(12f, cap = StrokeCap.Round))
        }
        Text("${(progress.value * 100).toInt()}%", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
    }
}

// ─── 19. SparkLine ───
@Composable
fun SparkLine(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val phase by transition.animateFloat(
        initialValue = 0f, targetValue = (2 * PI).toFloat(),
        animationSpec = infiniteRepeatable(tween(4000, easing = LinearEasing), RepeatMode.Restart)
    )
    val primaryColor = MaterialTheme.colorScheme.primary
    Column(modifier.fillMaxWidth().padding(16.dp)) {
        Text("Performance", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(4.dp))
        Canvas(Modifier.fillMaxWidth().height(40.dp)) {
            val w = size.width; val h = size.height
            val points = 60
            for (i in 0 until points - 1) {
                val x1 = w * i / points; val x2 = w * (i + 1) / points
                val y1 = h / 2 + sin(phase + i * 0.15f).toFloat() * h * 0.35f
                val y2 = h / 2 + sin(phase + (i + 1) * 0.15f).toFloat() * h * 0.35f
                drawLine(primaryColor, Offset(x1, y1), Offset(x2, y2), 2f, StrokeCap.Round)
            }
        }
    }
}

// ─── 20. DataTable ───
@Composable
fun DataTable(modifier: Modifier = Modifier) {
    val rows = listOf("Fade" to "23ms", "Scale" to "18ms", "Bounce" to "45ms", "Spring" to "32ms")
    Column(modifier.fillMaxWidth().padding(16.dp)) {
        Row(Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
            Text("Effect", Modifier.weight(1f), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text("Duration", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        rows.forEachIndexed { i, (name, dur) ->
            var vis by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { delay(i * 100L); vis = true }
            Row(
                Modifier.fillMaxWidth().canimation(visible = vis, effect = Canimation.Fade.Up).padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(name, Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                Surface(shape = RoundedCornerShape(4.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                    Text(dur, Modifier.padding(horizontal = 8.dp, vertical = 2.dp), style = MaterialTheme.typography.labelSmall, fontFamily = FontFamily.Monospace)
                }
            }
        }
    }
}

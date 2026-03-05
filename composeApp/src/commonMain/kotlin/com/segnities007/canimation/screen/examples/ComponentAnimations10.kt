package com.segnities007.canimation.screen.examples

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// ═══════════════════════════════════════════════════════════
//  MORE TEXT ANIMATIONS
// ═══════════════════════════════════════════════════════════

// ─── 1. TypewriterEffect ───
@Composable
fun TypewriterEffect(modifier: Modifier = Modifier) {
    val text = "Hello, World!"
    var displayed by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        while (true) {
            displayed = ""
            for (c in text) { displayed += c; delay(120) }
            delay(2000)
        }
    }
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Text(
            text = displayed + if (displayed.length < text.length) "▌" else "",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
        )
    }
}

// ─── 2. TextMorph ───
@Composable
fun TextMorph(modifier: Modifier = Modifier) {
    val words = listOf("Fast", "Simple", "Beautiful", "Powerful")
    var idx by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(2000); idx = (idx + 1) % words.size } }
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(idx) { vis = false; delay(50); vis = true }
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Animations are ", style = MaterialTheme.typography.bodyLarge)
            Box(Modifier.canimation(visible = vis, effect = Canimation.Fade.Up)) {
                Text(words[idx], style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

// ─── 3. TextStrikethrough ───
@Composable
fun TextStrikethrough(modifier: Modifier = Modifier) {
    var struck by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(2000); struck = !struck } }
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                "Old Price: \$99.99",
                style = MaterialTheme.typography.bodyMedium,
                textDecoration = if (struck) TextDecoration.LineThrough else TextDecoration.None,
                color = if (struck) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface,
            )
            if (struck) {
                var vis by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { vis = true }
                Text(
                    "New Price: \$49.99",
                    modifier = Modifier.canimation(visible = vis, effect = Canimation.Scale.Pop),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF22C55E),
                )
            }
        }
    }
}

// ─── 4. TextGlitch ───
@Composable
fun TextGlitch(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val offsetX by transition.animateFloat(0f, 2f, infiniteRepeatable(tween(150), RepeatMode.Reverse))
    val offsetY by transition.animateFloat(0f, -1f, infiniteRepeatable(tween(200), RepeatMode.Reverse))
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Text("GLITCH", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black, color = Color(0xFF22D3EE).copy(alpha = 0.5f), modifier = Modifier.offset(x = offsetX.dp, y = offsetY.dp))
        Text("GLITCH", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black, color = Color(0xFFEF4444).copy(alpha = 0.5f), modifier = Modifier.offset(x = (-offsetX).dp, y = (-offsetY).dp))
        Text("GLITCH", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black)
    }
}

// ═══════════════════════════════════════════════════════════
//  MORE CARD ANIMATIONS
// ═══════════════════════════════════════════════════════════

// ─── 5. RecipeCard ───
@Composable
fun RecipeCard(modifier: Modifier = Modifier) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { vis = true }
    Box(modifier.fillMaxWidth().padding(16.dp).canimation(visible = vis, effect = Canimation.Rise.In)) {
        Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("🍕", style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text("Margherita Pizza", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                        Text("25 min • Easy", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("Italian", "Vegetarian").forEach { tag ->
                        Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                            Text(tag, Modifier.padding(horizontal = 8.dp, vertical = 2.dp), style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        }
    }
}

// ─── 6. WeatherCard ───
@Composable
fun WeatherCard(modifier: Modifier = Modifier) {
    val weathers = listOf("☀️" to "Sunny 28°", "🌧️" to "Rainy 18°", "⛅" to "Cloudy 22°")
    var idx by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(2500); idx = (idx + 1) % weathers.size } }
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(idx) { vis = false; delay(50); vis = true }
    Box(modifier.fillMaxWidth().padding(16.dp).canimation(visible = vis, effect = Canimation.Fade.Gentle)) {
        Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFF3B82F6).copy(alpha = 0.1f), border = BorderStroke(1.dp, Color(0xFF3B82F6).copy(alpha = 0.2f))) {
            Row(Modifier.padding(14.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(weathers[idx].first, style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(weathers[idx].second, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text("San Francisco", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

// ─── 7. EventCard ───
@Composable
fun EventCard(modifier: Modifier = Modifier) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { vis = true }
    Box(modifier.fillMaxWidth().padding(16.dp).canimation(visible = vis, effect = Canimation.Slide.LeftSubtle)) {
        Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
            Row(Modifier.padding(14.dp)) {
                Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primary, modifier = Modifier.size(48.dp)) {
                    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text("15", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
                        Text("MAR", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f))
                    }
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Kotlin Conf 2026", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                    Text("Copenhagen, Denmark", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(4.dp))
                    Text("📍 Bella Center", style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}

// ─── 8. MusicPlayerCard ───
@Composable
fun MusicPlayerCard(modifier: Modifier = Modifier) {
    var playing by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(2000); playing = !playing } }
    val progress = remember { Animatable(0f) }
    LaunchedEffect(playing) { if (playing) progress.animateTo(1f, tween(4000)) else progress.snapTo(0f) }
    Box(modifier.fillMaxWidth().padding(16.dp)) {
        Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.inverseSurface) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primary, modifier = Modifier.size(40.dp)) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("🎵", fontSize = 18.sp) }
                    }
                    Spacer(Modifier.width(10.dp))
                    Column(Modifier.weight(1f)) {
                        Text("Kotlin Groove", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.inverseOnSurface)
                        Text("Compose Orchestra", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.6f))
                    }
                    Text(if (playing) "⏸" else "▶", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.inverseOnSurface, modifier = Modifier.clickable { playing = !playing })
                }
                Box(Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)).background(MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.1f))) {
                    Box(Modifier.fillMaxWidth(progress.value).fillMaxHeight().clip(RoundedCornerShape(2.dp)).background(MaterialTheme.colorScheme.inversePrimary))
                }
            }
        }
    }
}
// ─── 11. BottomNavBar ───
@Composable
fun BottomNavBar(modifier: Modifier = Modifier) {
    val items = listOf("🏠" to "Home", "🔍" to "Search", "❤️" to "Saved", "👤" to "Profile")
    var selected by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(1500); selected = (selected + 1) % items.size } }
    Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.surfaceVariant, modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            items.forEachIndexed { i, (icon, label) ->
                val isSelected = i == selected
                val scale by animateFloatAsState(if (isSelected) 1.15f else 1f, spring(dampingRatio = 0.5f))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.graphicsLayer { scaleX = scale; scaleY = scale }.clickable { selected = i },
                ) {
                    Text(icon, fontSize = if (isSelected) 20.sp else 16.sp)
                    if (isSelected) {
                        Text(label, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 9.sp)
                    }
                }
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════
//  MORE DATA VISUALIZATIONS
// ═══════════════════════════════════════════════════════════

// ─── 12. RadarChart ───
@Composable
fun RadarChart(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val animProgress by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(3000), RepeatMode.Reverse))
    val values = listOf(0.8f, 0.6f, 0.9f, 0.5f, 0.7f)
    val primaryColor = MaterialTheme.colorScheme.primary
    val outlineColor = MaterialTheme.colorScheme.outlineVariant
    Box(modifier.fillMaxWidth().height(90.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(80.dp)) {
            val cx = size.width / 2; val cy = size.height / 2; val r = size.minDimension / 2.5f
            val n = values.size
            // Grid
            for (ring in 1..3) {
                val rr = r * ring / 3
                val path = Path().apply {
                    for (i in 0 until n) {
                        val a = ((360f / n * i - 90f) * PI / 180f).toFloat()
                        val px = cx + cos(a) * rr; val py = cy + sin(a) * rr
                        if (i == 0) moveTo(px, py) else lineTo(px, py)
                    }
                    close()
                }
                drawPath(path, outlineColor.copy(alpha = 0.3f), style = Stroke(1f))
            }
            // Data
            val dataPath = Path().apply {
                for (i in 0 until n) {
                    val v = values[i] * animProgress
                    val a = ((360f / n * i - 90f) * PI / 180f).toFloat()
                    val px = cx + cos(a) * r * v; val py = cy + sin(a) * r * v
                    if (i == 0) moveTo(px, py) else lineTo(px, py)
                }
                close()
            }
            drawPath(dataPath, primaryColor.copy(alpha = 0.2f))
            drawPath(dataPath, primaryColor, style = Stroke(2f))
            for (i in 0 until n) {
                val v = values[i] * animProgress
                val a = ((360f / n * i - 90f) * PI / 180f).toFloat()
                drawCircle(primaryColor, 3f, Offset(cx + cos(a) * r * v, cy + sin(a) * r * v))
            }
        }
    }
}

// ─── 13. HorizontalBarChart ───
@Composable
fun HorizontalBarChart(modifier: Modifier = Modifier) {
    val items = listOf("Kotlin" to 0.85f, "Swift" to 0.65f, "Dart" to 0.45f, "Java" to 0.55f)
    val anims = items.map { remember { Animatable(0f) } }
    LaunchedEffect(Unit) { while (true) { anims.forEachIndexed { i, a -> a.animateTo(items[i].second, tween(800, delayMillis = i * 200)) }; delay(2000); anims.forEach { it.animateTo(0f, tween(300)) }; delay(400) } }
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        items.forEachIndexed { i, (label, _) ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(label, Modifier.width(56.dp), style = MaterialTheme.typography.labelSmall, fontFamily = FontFamily.Monospace)
                Box(Modifier.weight(1f).height(16.dp).clip(RoundedCornerShape(4.dp)).background(MaterialTheme.colorScheme.surfaceVariant)) {
                    Box(Modifier.fillMaxWidth(anims[i].value).fillMaxHeight().clip(RoundedCornerShape(4.dp)).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)))
                }
                Spacer(Modifier.width(6.dp))
                Text("${(anims[i].value * 100).toInt()}%", style = MaterialTheme.typography.labelSmall, fontFamily = FontFamily.Monospace, modifier = Modifier.width(32.dp))
            }
        }
    }
}

// ─── 14. GaugeChart ───
@Composable
fun GaugeChart(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val value by transition.animateFloat(0f, 0.75f, infiniteRepeatable(tween(3000), RepeatMode.Reverse))
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(80.dp, 50.dp)) {
            val sweep = 180f
            drawArc(primaryColor.copy(alpha = 0.1f), 180f, sweep, false, style = Stroke(8f, cap = StrokeCap.Round), topLeft = Offset(8f, 8f), size = Size(size.width - 16f, (size.height - 8f) * 2))
            drawArc(primaryColor, 180f, sweep * value, false, style = Stroke(8f, cap = StrokeCap.Round), topLeft = Offset(8f, 8f), size = Size(size.width - 16f, (size.height - 8f) * 2))
        }
        Text("${(value * 100).toInt()}%", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace, modifier = Modifier.padding(top = 16.dp))
    }
}

// ─── 15. LiveCounter ───
@Composable
fun LiveCounter(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(1247) }
    LaunchedEffect(Unit) { while (true) { delay((200..800).random().toLong()); count += (1..5).random() } }
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(count) { vis = false; delay(20); vis = true }
    Column(modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Active Users", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(4.dp))
        Box(Modifier.canimation(visible = vis, effect = Canimation.Micro.NudgeUp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF22C55E)))
                Spacer(Modifier.width(6.dp))
                Text(count.toString(), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
            }
        }
    }
}

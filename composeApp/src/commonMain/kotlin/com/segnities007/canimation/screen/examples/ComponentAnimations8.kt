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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// ═══════════════════════════════════════════════════════════
//  UI COMPONENT ANIMATIONS
// ═══════════════════════════════════════════════════════════

// ─── 1. AnimatedBanner ───
@Composable
fun AnimatedBanner(modifier: Modifier = Modifier) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { vis = true; delay(3000); vis = false; delay(800) } }
    Box(modifier.fillMaxWidth().padding(16.dp).canimation(visible = vis, effect = Canimation.Slide.DownSubtle)) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF6366F1),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("🎉", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.width(10.dp))
                Column(Modifier.weight(1f)) {
                    Text("New Release!", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("v2.0 with 190+ effects", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.8f))
                }
                Text("→", style = MaterialTheme.typography.titleMedium, color = Color.White)
            }
        }
    }
}

// ─── 2. AnimatedTooltip ───
@Composable
fun AnimatedTooltip(modifier: Modifier = Modifier) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(1000); vis = true; delay(2500); vis = false; delay(500) } }
    Column(modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.canimation(visible = vis, effect = Canimation.Float.Up)) {
            Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.inverseSurface) {
                Text("Hover me for info!", Modifier.padding(horizontal = 12.dp, vertical = 6.dp), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.inverseOnSurface)
            }
        }
        Spacer(Modifier.height(8.dp))
        Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primaryContainer, modifier = Modifier.size(48.dp, 32.dp)) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("?", fontWeight = FontWeight.Bold) }
        }
    }
}

// ─── 3. AnimatedAlert ───
@Composable
fun AnimatedAlert(modifier: Modifier = Modifier) {
    val alerts = listOf("Success" to Color(0xFF22C55E), "Warning" to Color(0xFFF59E0B), "Error" to Color(0xFFEF4444))
    var idx by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(2000); idx = (idx + 1) % alerts.size } }
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(idx) { vis = false; delay(50); vis = true }
    val (label, color) = alerts[idx]
    Box(modifier.fillMaxWidth().padding(16.dp).canimation(visible = vis, effect = Canimation.Slide.LeftSubtle)) {
        Surface(shape = RoundedCornerShape(8.dp), color = color.copy(alpha = 0.1f), border = BorderStroke(1.dp, color.copy(alpha = 0.3f)), modifier = Modifier.fillMaxWidth()) {
            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Surface(shape = CircleShape, color = color, modifier = Modifier.size(8.dp)) {}
                Spacer(Modifier.width(10.dp))
                Text("$label: Operation completed.", style = MaterialTheme.typography.bodySmall, color = color)
            }
        }
    }
}

// ─── 4. AnimatedDropdown ───
@Composable
fun AnimatedDropdown(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(1500); expanded = !expanded } }
    Column(modifier.fillMaxWidth().padding(16.dp)) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            modifier = Modifier.fillMaxWidth().clickable { expanded = !expanded },
        ) {
            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("Select option", Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                Text(if (expanded) "▲" else "▼", style = MaterialTheme.typography.labelSmall)
            }
        }
        if (expanded) {
            val items = listOf("Fade In", "Scale Pop", "Bounce")
            Surface(
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column {
                    items.forEachIndexed { i, item ->
                        var vis by remember { mutableStateOf(false) }
                        LaunchedEffect(Unit) { delay(i * 80L); vis = true }
                        Text(
                            item,
                            modifier = Modifier.fillMaxWidth().canimation(visible = vis, effect = Canimation.Fade.Up).padding(12.dp),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}

// ─── 5. AnimatedProgressCard ───
@Composable
fun AnimatedProgressCard(modifier: Modifier = Modifier) {
    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) { while (true) { progress.animateTo(0.78f, tween(1500)); delay(2000); progress.animateTo(0f, tween(500)); delay(400) } }
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.fillMaxWidth().padding(16.dp),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Upload Progress", style = MaterialTheme.typography.labelMedium)
                Text("${(progress.value * 100).toInt()}%", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
            Box(Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)).background(MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))) {
                Box(Modifier.fillMaxWidth(progress.value).fillMaxHeight().clip(RoundedCornerShape(4.dp)).background(MaterialTheme.colorScheme.primary))
            }
        }
    }
}

// ─── 6. AnimatedDialog ───
@Composable
fun AnimatedDialog(modifier: Modifier = Modifier) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { vis = true; delay(3000); vis = false; delay(800) } }
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        if (vis) {
            Box(Modifier.canimation(visible = vis, effect = Canimation.Scale.Pop)) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    tonalElevation = 8.dp,
                ) {
                    Column(Modifier.padding(20.dp).widthIn(max = 240.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Confirm Action", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                        Text("Apply this animation?", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
                                Text("Cancel", Modifier.padding(horizontal = 16.dp, vertical = 8.dp), style = MaterialTheme.typography.labelMedium)
                            }
                            Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primary) {
                                Text("Apply", Modifier.padding(horizontal = 16.dp, vertical = 8.dp), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary)
                            }
                        }
                    }
                }
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════
//  VISUAL EFFECT ANIMATIONS
// ═══════════════════════════════════════════════════════════

// ─── 7. OrbitDots ───
@Composable
fun OrbitDots(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val angle by transition.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart)
    )
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(64.dp)) {
            val cx = size.width / 2; val cy = size.height / 2; val r = size.minDimension / 2.5f
            repeat(6) { i ->
                val a = ((angle + i * 60f) * PI / 180f).toFloat()
                val x = cx + cos(a) * r
                val y = cy + sin(a) * r
                drawCircle(primaryColor.copy(alpha = 0.3f + i * 0.1f), radius = 4f, center = Offset(x, y))
            }
            drawCircle(primaryColor, radius = 6f, center = Offset(cx, cy))
        }
    }
}
// ─── 9. BouncingLoader ───
@Composable
fun BouncingLoader(modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        repeat(3) { i ->
            val transition = rememberInfiniteTransition()
            val offsetY by transition.animateFloat(
                initialValue = 0f, targetValue = -12f,
                animationSpec = infiniteRepeatable(tween(400, delayMillis = i * 150, easing = FastOutSlowInEasing), RepeatMode.Reverse)
            )
            Box(Modifier.padding(horizontal = 4.dp).offset(y = offsetY.dp).size(12.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary))
        }
    }
}

// ─── 10. GlowPulse ───
@Composable
fun GlowPulse(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val alpha by transition.animateFloat(0.2f, 0.8f, infiniteRepeatable(tween(1500), RepeatMode.Reverse))
    val scale by transition.animateFloat(0.9f, 1.1f, infiniteRepeatable(tween(1500), RepeatMode.Reverse))
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(60.dp)) {
            drawCircle(primaryColor.copy(alpha = alpha * 0.3f), radius = size.minDimension / 2 * scale)
            drawCircle(primaryColor.copy(alpha = alpha * 0.5f), radius = size.minDimension / 3)
            drawCircle(primaryColor, radius = size.minDimension / 5)
        }
    }
}

// ─── 11. WaveformBars ───
@Composable
fun WaveformBars(modifier: Modifier = Modifier) {
    val barCount = 12
    val primaryColor = MaterialTheme.colorScheme.primary
    Row(modifier.fillMaxWidth().height(50.dp).padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.Bottom) {
        repeat(barCount) { i ->
            val transition = rememberInfiniteTransition()
            val height by transition.animateFloat(
                initialValue = 8f, targetValue = 36f,
                animationSpec = infiniteRepeatable(
                    tween((300 + i * 50), easing = FastOutSlowInEasing),
                    RepeatMode.Reverse, initialStartOffset = StartOffset(i * 80)
                )
            )
            Box(Modifier.width(6.dp).height(height.dp).clip(RoundedCornerShape(3.dp)).background(primaryColor.copy(alpha = 0.5f + (i % 3) * 0.15f)))
        }
    }
}

// ═══════════════════════════════════════════════════════════
//  INTERACTIVE ANIMATIONS
// ═══════════════════════════════════════════════════════════

// ─── 12. LikeButton ───
@Composable
fun LikeButton(modifier: Modifier = Modifier) {
    var liked by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(1800); liked = !liked } }
    val scale by animateFloatAsState(if (liked) 1.3f else 1f, spring(dampingRatio = 0.4f, stiffness = 300f))
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Text(
            text = if (liked) "❤️" else "🤍",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.graphicsLayer { scaleX = scale; scaleY = scale }.clickable { liked = !liked },
        )
    }
}

// ─── 13. QuantitySelector ───
@Composable
fun QuantitySelector(modifier: Modifier = Modifier) {
    var qty by remember { mutableIntStateOf(1) }
    LaunchedEffect(Unit) { while (true) { delay(1200); qty = if (qty >= 5) 1 else qty + 1 } }
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(qty) { vis = false; delay(30); vis = true }
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer, modifier = Modifier.size(36.dp).clickable { if (qty > 1) qty-- }) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("−", fontWeight = FontWeight.Bold) }
        }
        Box(Modifier.width(48.dp).canimation(visible = vis, effect = Canimation.Scale.Pop), contentAlignment = Alignment.Center) {
            Text("$qty", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
        Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer, modifier = Modifier.size(36.dp).clickable { qty++ }) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("+", fontWeight = FontWeight.Bold) }
        }
    }
}

// ─── 14. RadioSelector ───
@Composable
fun RadioSelector(modifier: Modifier = Modifier) {
    val options = listOf("Small", "Medium", "Large")
    var selected by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(1500); selected = (selected + 1) % options.size } }
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        options.forEachIndexed { i, option ->
            val isSelected = i == selected
            val bg by animateColorAsState(if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).background(bg).clickable { selected = i }.padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Box(Modifier.size(20.dp).clip(CircleShape).background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant, CircleShape), contentAlignment = Alignment.Center) {
                    if (isSelected) Box(Modifier.size(8.dp).clip(CircleShape).background(MaterialTheme.colorScheme.onPrimary))
                }
                Text(option, style = MaterialTheme.typography.bodyMedium, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
            }
        }
    }
}

// ─── 15. SliderControl ───
@Composable
fun SliderControl(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(3000), RepeatMode.Reverse))
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Volume", style = MaterialTheme.typography.labelMedium)
            Text("${(progress * 100).toInt()}%", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
        }
        Slider(value = progress, onValueChange = {}, modifier = Modifier.fillMaxWidth())
    }
}

// ─── 16. AnimatedPasswordField ───
@Composable
fun AnimatedPasswordField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    val targetText = "p@ssw0rd"
    LaunchedEffect(Unit) {
        while (true) {
            text = ""
            for (c in targetText) { text += c; delay(200) }
            delay(1500)
        }
    }
    Column(modifier.fillMaxWidth().padding(16.dp)) {
        Text("Password", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(4.dp))
        Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.surfaceVariant, border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline), modifier = Modifier.fillMaxWidth()) {
            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("●".repeat(text.length), style = MaterialTheme.typography.bodyLarge, fontFamily = FontFamily.Monospace, modifier = Modifier.weight(1f))
                Text("👁", style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

// ─── 17. AnimatedFileUpload ───
@Composable
fun AnimatedFileUpload(modifier: Modifier = Modifier) {
    val states = listOf("idle", "uploading", "done")
    var stateIdx by remember { mutableIntStateOf(0) }
    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            stateIdx = 0; delay(1000)
            stateIdx = 1; progress.animateTo(1f, tween(2000)); delay(300)
            stateIdx = 2; delay(1500)
            progress.snapTo(0f)
        }
    }
    Column(modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        val icon = when (stateIdx) { 0 -> "📁"; 1 -> "⬆️"; else -> "✅" }
        Text(icon, style = MaterialTheme.typography.headlineMedium)
        Text(
            when (stateIdx) { 0 -> "Drop file here"; 1 -> "Uploading ${(progress.value * 100).toInt()}%"; else -> "Upload complete!" },
            style = MaterialTheme.typography.labelMedium,
            color = if (stateIdx == 2) Color(0xFF22C55E) else MaterialTheme.colorScheme.onSurfaceVariant,
        )
        if (stateIdx == 1) {
            Box(Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)).background(MaterialTheme.colorScheme.surfaceVariant)) {
                Box(Modifier.fillMaxWidth(progress.value).fillMaxHeight().clip(RoundedCornerShape(3.dp)).background(MaterialTheme.colorScheme.primary))
            }
        }
    }
}

// ─── 18. AnimatedVote ───
@Composable
fun AnimatedVote(modifier: Modifier = Modifier) {
    var upCount by remember { mutableIntStateOf(42) }
    var downCount by remember { mutableIntStateOf(8) }
    var upVis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1800)
            upCount++; upVis = false; delay(30); upVis = true; delay(1800)
            downCount++; upVis = false; delay(30); upVis = true
        }
    }
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primaryContainer, modifier = Modifier.clickable { upCount++ }) {
            Row(Modifier.padding(horizontal = 12.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("▲", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                Box(Modifier.canimation(visible = upVis, effect = Canimation.Scale.Pop)) {
                    Text("$upCount", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(Modifier.width(12.dp))
        Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.errorContainer, modifier = Modifier.clickable { downCount++ }) {
            Row(Modifier.padding(horizontal = 12.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("▼", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.error)
                Text("$downCount", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// ─── 19. AnimatedSearchBar ───
@Composable
fun AnimatedSearchBar(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(1500); expanded = !expanded } }
    val width by animateFloatAsState(if (expanded) 280f else 48f, tween(400))
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(1.dp, if (expanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline),
            modifier = Modifier.width(width.dp).height(48.dp).clickable { expanded = !expanded },
        ) {
            Row(Modifier.padding(horizontal = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("🔍", style = MaterialTheme.typography.titleSmall)
                if (expanded) {
                    Spacer(Modifier.width(8.dp))
                    Text("Search animations...", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f))
                }
            }
        }
    }
}

// ─── 20. AnimatedFormValidation ───
@Composable
fun AnimatedFormValidation(modifier: Modifier = Modifier) {
    val states = listOf("empty", "typing", "valid", "invalid")
    var stateIdx by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { for (i in states.indices) { stateIdx = i; delay(1500) } } }
    val borderColor by animateColorAsState(
        when (stateIdx) { 2 -> Color(0xFF22C55E); 3 -> Color(0xFFEF4444); else -> MaterialTheme.colorScheme.outline }
    )
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Email", style = MaterialTheme.typography.labelMedium)
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(1.5.dp, borderColor),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    when (stateIdx) { 0 -> ""; 1 -> "user@exa"; 2 -> "user@example.com"; else -> "invalid-email" },
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily.Monospace,
                    color = if (stateIdx == 0) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f) else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                )
                when (stateIdx) {
                    2 -> Text("✓", color = Color(0xFF22C55E), fontWeight = FontWeight.Bold)
                    3 -> Text("✗", color = Color(0xFFEF4444), fontWeight = FontWeight.Bold)
                    else -> {}
                }
            }
        }
        if (stateIdx == 3) {
            var vis by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { vis = true }
            Text("Please enter a valid email", style = MaterialTheme.typography.labelSmall, color = Color(0xFFEF4444), modifier = Modifier.canimation(visible = vis, effect = Canimation.Micro.NudgeUp))
        }
    }
}

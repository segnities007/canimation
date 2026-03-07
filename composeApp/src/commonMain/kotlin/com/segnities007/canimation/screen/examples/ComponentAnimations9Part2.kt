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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import canimation.composeapp.generated.resources.*
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// ═══════════════════════════════════════════════════════════
//  MORE UI COMPONENTS
// ═══════════════════════════════════════════════════════════

// ─── 1. AnimatedBottomSheet ───

@Composable
fun HexGrid(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val pulse by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(2000), RepeatMode.Reverse))
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(80.dp, 60.dp)) {
            val hexR = 12f; val cols = 4; val rows = 3
            for (row in 0 until rows) {
                for (col in 0 until cols) {
                    val xOff = if (row % 2 == 1) hexR * 1.1f else 0f
                    val cx = 10f + col * hexR * 2.2f + xOff
                    val cy = 10f + row * hexR * 1.9f
                    val dist = ((col + row).toFloat() / (cols + rows))
                    val alpha = (0.2f + pulse * 0.6f * (1f - dist)).coerceIn(0f, 1f)
                    val path = Path().apply {
                        for (corner in 0..5) {
                            val a = ((60 * corner - 30) * PI / 180).toFloat()
                            val px = cx + cos(a) * hexR * 0.8f
                            val py = cy + sin(a) * hexR * 0.8f
                            if (corner == 0) moveTo(px, py) else lineTo(px, py)
                        }
                        close()
                    }
                    drawPath(path, primaryColor.copy(alpha = alpha))
                }
            }
        }
    }
}

// ─── 11. ParticleField ───
@Composable
fun ParticleField(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val time by transition.animateFloat(0f, 100f, infiniteRepeatable(tween(10000, easing = LinearEasing), RepeatMode.Restart))
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(70.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxWidth(0.8f).height(50.dp)) {
            repeat(20) { i ->
                val speed = 0.3f + (i % 5) * 0.15f
                val x = ((i * 37f + time * speed * 8f) % size.width)
                val y = (i * 19f % size.height)
                val r = 1.5f + (i % 3)
                drawCircle(primaryColor.copy(alpha = 0.15f + (i % 4) * 0.1f), radius = r, center = Offset(x, y))
            }
        }
    }
}

// ─── 12. RotatingSquares ───
@Composable
fun RotatingSquares(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val rot1 by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(4000, easing = LinearEasing), RepeatMode.Restart))
    val rot2 by transition.animateFloat(0f, -360f, infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart))
    val primaryColor = MaterialTheme.colorScheme.primary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(64.dp)) {
            rotate(rot1) { drawRoundRect(primaryColor.copy(alpha = 0.3f), topLeft = Offset(8f, 8f), size = Size(size.width - 16f, size.height - 16f), cornerRadius = CornerRadius(4f), style = Stroke(2f)) }
            rotate(rot2) { drawRoundRect(tertiaryColor.copy(alpha = 0.4f), topLeft = Offset(14f, 14f), size = Size(size.width - 28f, size.height - 28f), cornerRadius = CornerRadius(4f), style = Stroke(2f)) }
        }
    }
}

// ═══════════════════════════════════════════════════════════
//  MORE INTERACTIVE COMPONENTS
// ═══════════════════════════════════════════════════════════

// ─── 13. SegmentedControl ───
@Composable
fun SegmentedControl(modifier: Modifier = Modifier) {
    val options = listOf("Day", "Week", "Month")
    var selected by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(1500); selected = (selected + 1) % options.size } }
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.fillMaxWidth().padding(16.dp),
    ) {
        Row(Modifier.padding(4.dp)) {
            options.forEachIndexed { i, opt ->
                val isSelected = i == selected
                val bg by animateColorAsState(if (isSelected) MaterialTheme.colorScheme.surface else Color.Transparent)
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = bg,
                    modifier = Modifier.weight(1f).clickable { selected = i },
                    tonalElevation = if (isSelected) 2.dp else 0.dp,
                ) {
                    Text(opt, Modifier.padding(vertical = 8.dp), textAlign = TextAlign.Center, style = MaterialTheme.typography.labelMedium, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
                }
            }
        }
    }
}

// ─── 14. AnimatedSwitch ───
@Composable
fun AnimatedSwitch(modifier: Modifier = Modifier) {
    var on by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(1800); on = !on } }
    val thumbOffset by animateFloatAsState(if (on) 28f else 4f, spring(dampingRatio = 0.6f))
    val trackColor by animateColorAsState(if (on) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant)
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Text(stringResource(Res.string.component_off), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.width(12.dp))
        Box(
            Modifier.width(52.dp).height(28.dp).clip(RoundedCornerShape(14.dp)).background(trackColor).clickable { on = !on },
        ) {
            Box(Modifier.offset(x = thumbOffset.dp).size(20.dp).clip(CircleShape).background(Color.White))
        }
        Spacer(Modifier.width(12.dp))
        Text(stringResource(Res.string.component_on), style = MaterialTheme.typography.labelMedium, color = if (on) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = if (on) FontWeight.Bold else FontWeight.Normal)
    }
}

// ─── 15. AnimatedPinInput ───
@Composable
fun AnimatedPinInput(modifier: Modifier = Modifier) {
    var filled by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(600); if (filled < 4) filled++ else { delay(1000); filled = 0 } } }
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        repeat(4) { i ->
            val isFilled = i < filled
            var vis by remember { mutableStateOf(false) }
            LaunchedEffect(isFilled) { vis = isFilled }
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.5.dp, if (isFilled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline),
                modifier = Modifier.padding(horizontal = 4.dp).size(44.dp),
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    if (isFilled) {
                        Box(Modifier.canimation(visible = vis, effect = Canimation.Scale.Pop)) {
                            Box(Modifier.size(12.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary))
                        }
                    }
                }
            }
        }
    }
}

// ─── 16. AnimatedColorPicker ───
@Composable
fun AnimatedColorPicker(modifier: Modifier = Modifier) {
    val colors = listOf(Color(0xFFEF4444), Color(0xFFF59E0B), Color(0xFF22C55E), Color(0xFF3B82F6), Color(0xFF8B5CF6), Color(0xFFEC4899))
    var selected by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(1200); selected = (selected + 1) % colors.size } }
    Column(modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            colors.forEachIndexed { i, color ->
                val isSelected = i == selected
                val scale by animateFloatAsState(if (isSelected) 1.25f else 1f, spring(dampingRatio = 0.5f))
                Box(
                    Modifier.graphicsLayer { scaleX = scale; scaleY = scale }.size(28.dp).clip(CircleShape).background(color).clickable { selected = i },
                    contentAlignment = Alignment.Center,
                ) {
                    if (isSelected) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp),
                        )
                    }
                }
            }
        }
        Box(Modifier.fillMaxWidth(0.6f).height(32.dp).clip(RoundedCornerShape(8.dp)).background(colors[selected]))
    }
}

// ─── 17. AnimatedNotificationBell ───
@Composable
fun AnimatedNotificationBell(modifier: Modifier = Modifier) {
    var hasNotif by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(2000); hasNotif = !hasNotif } }
    val shake by animateFloatAsState(if (hasNotif) 1f else 0f, spring(dampingRatio = 0.3f, stiffness = 600f))
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Box(contentAlignment = Alignment.TopEnd) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.graphicsLayer { rotationZ = shake * 15f }.size(28.dp),
            )
            if (hasNotif) {
                var vis by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { vis = true }
                Box(Modifier.canimation(visible = vis, effect = Canimation.Scale.Pop).size(16.dp).clip(CircleShape).background(Color(0xFFEF4444)), contentAlignment = Alignment.Center) {
                    Text(stringResource(Res.string.component_notification_count), color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// ─── 18. AnimatedCountdownTimer ───
@Composable
fun AnimatedCountdownTimer(modifier: Modifier = Modifier) {
    var seconds by remember { mutableIntStateOf(10) }
    LaunchedEffect(Unit) { while (true) { delay(1000); seconds = if (seconds <= 0) 10 else seconds - 1 } }
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(seconds) { vis = false; delay(30); vis = true }
    val progress = seconds / 10f
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(Modifier.size(64.dp)) {
                drawArc(primaryColor.copy(alpha = 0.1f), 0f, 360f, false, style = Stroke(6f, cap = StrokeCap.Round))
                drawArc(primaryColor, -90f, 360f * progress, false, style = Stroke(6f, cap = StrokeCap.Round))
            }
            Box(Modifier.canimation(visible = vis, effect = Canimation.Scale.Pop)) {
                Text(
                    seconds.toString().padStart(2, '0'),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = if (seconds <= 3) Color(0xFFEF4444) else MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

// ─── 19. AnimatedCreditCard ───
@Composable
fun AnimatedCreditCard(modifier: Modifier = Modifier) {
    var flipped by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(2500); flipped = !flipped } }
    val rotationY by animateFloatAsState(if (flipped) 180f else 0f, tween(600))
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Box(Modifier.fillMaxWidth(0.85f).height(70.dp).graphicsLayer { this.rotationY = rotationY; cameraDistance = 12f * density }) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (rotationY <= 90f) Color(0xFF1E293B) else Color(0xFF334155),
                modifier = Modifier.fillMaxSize(),
            ) {
                if (rotationY <= 90f) {
                    Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.SpaceBetween) {
                        Text(stringResource(Res.string.component_credit_card_brand), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text(stringResource(Res.string.component_credit_card_number), color = Color.White.copy(alpha = 0.8f), fontFamily = FontFamily.Monospace, fontSize = 13.sp)
                    }
                } else {
                    Column(Modifier.padding(12.dp).graphicsLayer { scaleX = -1f }) {
                        Box(Modifier.fillMaxWidth().height(20.dp).background(Color(0xFF0F172A)))
                        Spacer(Modifier.height(6.dp))
                        Text(stringResource(Res.string.component_credit_card_cvv), color = Color.White.copy(alpha = 0.6f), fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                    }
                }
            }
        }
    }
}

// ─── 20. AnimatedThemeToggle ───
@Composable
fun AnimatedThemeToggle(modifier: Modifier = Modifier) {
    var isDark by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(2000); isDark = !isDark } }
    val bgColor by animateColorAsState(if (isDark) Color(0xFF1E293B) else Color(0xFFF1F5F9), tween(500))
    val fgColor by animateColorAsState(if (isDark) Color.White else Color(0xFF0F172A), tween(500))
    val rotation by animateFloatAsState(if (isDark) 180f else 0f, spring(dampingRatio = 0.5f))
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = bgColor,
            modifier = Modifier.fillMaxWidth(0.5f).height(48.dp).clickable { isDark = !isDark },
        ) {
            Row(Modifier.fillMaxSize().padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Icon(
                    imageVector = if (isDark) Icons.Default.DarkMode else Icons.Default.LightMode,
                    contentDescription = null,
                    tint = fgColor,
                    modifier = Modifier.graphicsLayer { rotationZ = rotation }.size(20.dp),
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = stringResource(
                        if (isDark) Res.string.examples_theme_dark_mode
                        else Res.string.examples_theme_light_mode,
                    ),
                    color = fgColor,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

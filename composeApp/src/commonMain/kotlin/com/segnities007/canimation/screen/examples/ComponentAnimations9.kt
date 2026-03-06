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
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.NoteAlt
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import canimation.composeapp.generated.resources.*
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// ═══════════════════════════════════════════════════════════
//  MORE UI COMPONENTS
// ═══════════════════════════════════════════════════════════

private data class FabActionItem(
    val icon: ImageVector,
    val label: StringResource,
)

// ─── 1. AnimatedBottomSheet ───

@Composable
fun AnimatedBottomSheet(modifier: Modifier = Modifier) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { vis = true; delay(3000); vis = false; delay(800) } }
    Box(modifier.fillMaxWidth().height(120.dp), contentAlignment = Alignment.BottomCenter) {
        Box(
            Modifier
                .fillMaxWidth()
                .canimation(visible = vis, effect = Canimation.Slide.Up)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Box(Modifier.width(40.dp).height(4.dp).clip(RoundedCornerShape(2.dp)).background(MaterialTheme.colorScheme.outline))
                Spacer(Modifier.height(12.dp))
                Text("Bottom Sheet Content", style = MaterialTheme.typography.titleSmall)
                Spacer(Modifier.height(4.dp))
                Text("Swipe down to dismiss", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

// ─── 2. AnimatedSnackbar ───
@Composable
fun AnimatedSnackbar(modifier: Modifier = Modifier) {
    var vis by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(500); vis = true; delay(2500); vis = false; delay(800) } }
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.BottomCenter) {
        Box(Modifier.canimation(visible = vis, effect = Canimation.Slide.Up)) {
            Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.inverseSurface) {
                Row(Modifier.padding(horizontal = 16.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Item saved successfully", Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.inverseOnSurface)
                    Text("UNDO", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.inversePrimary, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// ─── 3. AnimatedFab ───
@Composable
fun AnimatedFab(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { while (true) { delay(1500); expanded = !expanded } }
    val rotation by animateFloatAsState(if (expanded) 45f else 0f, spring(stiffness = 300f))
    val actions = listOf(
        FabActionItem(Icons.Default.CameraAlt, Res.string.examples_action_camera),
        FabActionItem(Icons.Default.AttachFile, Res.string.examples_action_attach),
        FabActionItem(Icons.Default.NoteAlt, Res.string.examples_action_note),
    )
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            if (expanded) {
                actions.forEachIndexed { i, action ->
                    var itemVis by remember { mutableStateOf(false) }
                    LaunchedEffect(expanded) { if (expanded) { delay(i * 100L); itemVis = true } else itemVis = false }
                    Row(
                        Modifier.canimation(visible = itemVis, effect = Canimation.Scale.Pop),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = stringResource(action.label),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Surface(shape = CircleShape, color = MaterialTheme.colorScheme.secondaryContainer, modifier = Modifier.size(40.dp)) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = action.icon,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                )
                            }
                        }
                    }
                }
            }
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(56.dp).clickable { expanded = !expanded },
            ) {
                Box(Modifier.fillMaxSize().graphicsLayer { rotationZ = rotation }, contentAlignment = Alignment.Center) {
                    Text("+", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}

// ─── 4. AnimatedChipInput ───
@Composable
fun AnimatedChipInput(modifier: Modifier = Modifier) {
    val chips = listOf("Kotlin", "Compose", "Animation", "Multiplatform")
    var count by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(800); if (count < chips.size) count++ else { delay(1000); count = 0 } } }
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Tags", style = MaterialTheme.typography.labelMedium)
        Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.surfaceVariant, border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline), modifier = Modifier.fillMaxWidth()) {
            Row(Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                chips.take(count).forEachIndexed { i, chip ->
                    var vis by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) { vis = true }
                    Box(Modifier.canimation(visible = vis, effect = Canimation.Scale.Pop)) {
                        Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                            Row(Modifier.padding(horizontal = 10.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text(chip, style = MaterialTheme.typography.labelSmall)
                                Spacer(Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(14.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// ─── 5. AnimatedAccordion ───
@Composable
fun AnimatedAccordion(modifier: Modifier = Modifier) {
    var openIdx by remember { mutableIntStateOf(-1) }
    LaunchedEffect(Unit) { while (true) { for (i in 0..2) { openIdx = i; delay(2000) }; openIdx = -1; delay(1000) } }
    val items = listOf("Getting Started" to "Install with Gradle and add to your project.", "Usage" to "Use Modifier.canimation() on any composable.", "API Reference" to "190+ effects across 31 categories.")
    Column(modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items.forEachIndexed { i, (title, content) ->
            val isOpen = i == openIdx
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (isOpen) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f) else MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, if (isOpen) MaterialTheme.colorScheme.primary.copy(alpha = 0.3f) else Color.Transparent),
                modifier = Modifier.fillMaxWidth().clickable { openIdx = if (openIdx == i) -1 else i },
            ) {
                Column(Modifier.padding(12.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(title, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                        Text(if (isOpen) "−" else "+", fontWeight = FontWeight.Bold)
                    }
                    if (isOpen) {
                        var vis by remember { mutableStateOf(false) }
                        LaunchedEffect(Unit) { vis = true }
                        Text(
                            content,
                            modifier = Modifier.padding(top = 8.dp).canimation(visible = vis, effect = Canimation.Fade.Up),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

// ─── 6. AnimatedStepper ───
@Composable
fun AnimatedStepper(modifier: Modifier = Modifier) {
    var step by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(1500); step = (step + 1) % 4 } }
    val labels = listOf("Cart", "Shipping", "Payment", "Confirm")
    Row(modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        labels.forEachIndexed { i, label ->
            val done = i < step; val active = i == step
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val bg by animateColorAsState(when { done -> Color(0xFF22C55E); active -> MaterialTheme.colorScheme.primary; else -> MaterialTheme.colorScheme.outlineVariant })
                Surface(shape = CircleShape, color = bg, modifier = Modifier.size(28.dp)) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        if (done) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp),
                            )
                        } else {
                            Text(
                                text = "${i + 1}",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
                Spacer(Modifier.height(4.dp))
                Text(label, style = MaterialTheme.typography.labelSmall, fontSize = 9.sp, color = if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant)
            }
            if (i < labels.lastIndex) {
                Box(Modifier.width(24.dp).height(2.dp).background(if (i < step) Color(0xFF22C55E) else MaterialTheme.colorScheme.outlineVariant))
            }
        }
    }
}

// ═══════════════════════════════════════════════════════════
//  MORE VISUAL ANIMATIONS
// ═══════════════════════════════════════════════════════════

// ─── 7. MorphingShape ───
@Composable
fun MorphingShape(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val radius by transition.animateFloat(8f, 32f, infiniteRepeatable(tween(2000), RepeatMode.Reverse))
    val rotation by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(6000, easing = LinearEasing), RepeatMode.Restart))
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(64.dp).graphicsLayer { rotationZ = rotation }) {
            drawRoundRect(primaryColor, cornerRadius = CornerRadius(radius), size = size)
        }
    }
}

// ─── 8. SpiralDots ───
@Composable
fun SpiralDots(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val angle by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(4000, easing = LinearEasing), RepeatMode.Restart))
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(64.dp)) {
            val cx = size.width / 2; val cy = size.height / 2
            repeat(12) { i ->
                val r = 4f + i * 2f
                val a = ((angle + i * 30f) * PI / 180f).toFloat()
                drawCircle(primaryColor.copy(alpha = 0.2f + i * 0.06f), radius = 3f, center = Offset(cx + cos(a) * r, cy + sin(a) * r))
            }
        }
    }
}

// ─── 9. DnaHelixCanvas ───
@Composable
fun DnaHelixCanvas(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()
    val phase by transition.animateFloat(0f, (2 * PI).toFloat(), infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart))
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    Box(modifier.fillMaxWidth().height(60.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxWidth(0.7f).height(40.dp)) {
            val steps = 20
            for (i in 0 until steps) {
                val x = size.width * i / steps
                val y1 = size.height / 2 + sin(phase + i * 0.5f) * size.height * 0.35f
                val y2 = size.height / 2 - sin(phase + i * 0.5f) * size.height * 0.35f
                drawCircle(primaryColor.copy(alpha = 0.7f), radius = 3f, center = Offset(x, y1))
                drawCircle(secondaryColor.copy(alpha = 0.7f), radius = 3f, center = Offset(x, y2))
                if (i % 3 == 0) drawLine(primaryColor.copy(alpha = 0.15f), Offset(x, y1), Offset(x, y2), strokeWidth = 1f)
            }
        }
    }
}

// ─── 10. HexGrid ───

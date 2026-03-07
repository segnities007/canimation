package com.segnities007.canimation.screen.examples

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import canimation.composeapp.generated.resources.*
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

// ─── 1. MegaMenuReveal ──────────────────────────────────────────────

/** Navigation menu that reveals items with staggered slide+fade from left */

@Composable
fun IOSSlider(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val anim = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            anim.animateTo(1f, tween(2000, easing = EaseInOutCubic))
            delay(400)
            anim.animateTo(0f, tween(2000, easing = EaseInOutCubic))
            delay(400)
        }
    }

    val primary = MaterialTheme.colorScheme.primary
    val trackColor = MaterialTheme.colorScheme.surfaceVariant

    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).width(240.dp).height(40.dp), contentAlignment = Alignment.CenterStart) {
        Canvas(modifier = Modifier.fillMaxWidth().height(6.dp)) {
            drawRoundRect(trackColor, cornerRadius = CornerRadius(3f, 3f))
            drawRoundRect(
                primary,
                size = Size(size.width * anim.value, size.height),
                cornerRadius = CornerRadius(3f, 3f),
            )
        }
        Box(
            modifier = Modifier
                .offset { IntOffset((anim.value * 210).dp.roundToPx(), 0) }
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.White)
                .drawBehind {
                    drawCircle(primary.copy(alpha = 0.3f), radius = size.width / 2 + 4.dp.toPx())
                },
        )
    }
}

// ─── 10. CheckboxAnimation ──────────────────────────────────────────

/** Checkbox that draws a checkmark path when toggled */
@Composable
fun CheckboxAnimation(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var checked by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            checked = true; delay(1500)
            checked = false; delay(1000)
        }
    }

    val progress by animateFloatAsState(
        if (checked) 1f else 0f,
        tween(400, easing = FastOutSlowInEasing),
    )
    val bgColor by animateColorAsState(
        if (checked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        tween(300),
    )

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(48.dp)) {
        drawRoundRect(bgColor, cornerRadius = CornerRadius(8.dp.toPx()))
        if (progress > 0f) {
            val path = Path().apply {
                moveTo(size.width * 0.25f, size.height * 0.52f)
                lineTo(size.width * 0.42f, size.height * 0.68f)
                lineTo(size.width * 0.75f, size.height * 0.32f)
            }
            val totalLen = size.width * 0.7f
            drawPath(
                path,
                color = Color.White,
                style = Stroke(
                    width = 3.dp.toPx(),
                    cap = StrokeCap.Round,
                    pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(
                        floatArrayOf(totalLen * progress, totalLen),
                        0f,
                    ),
                ),
            )
        }
    }
}

// ─── 11. SwitchAnimation ────────────────────────────────────────────

/** Material switch with bouncy thumb animation */
@Composable
fun SwitchAnimation(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var on by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            on = !on; delay(1400)
        }
    }

    val thumbOffset by animateFloatAsState(
        if (on) 24f else 0f,
        spring(dampingRatio = 0.5f, stiffness = Spring.StiffnessMedium),
    )
    val trackColor by animateColorAsState(
        if (on) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        tween(300),
    )
    val thumbScale by animateFloatAsState(
        if (on) 1.1f else 1f,
        spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium),
    )

    Box(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In)
            .width(52.dp).height(28.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(trackColor)
            .padding(2.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(thumbOffset.dp.roundToPx(), 0) }
                .size(24.dp)
                .graphicsLayer { scaleX = thumbScale; scaleY = thumbScale }
                .clip(CircleShape)
                .background(Color.White),
        )
    }
}

// ─── 12. ToastNotification ──────────────────────────────────────────

/** Toast that slides in from top, stays, slides out */
@Composable
fun ToastNotification(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var show by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            show = true; delay(2500)
            show = false; delay(1000)
        }
    }

    val offsetY by animateFloatAsState(
        if (show) 0f else -80f,
        spring(dampingRatio = 0.7f, stiffness = Spring.StiffnessMediumLow),
    )
    val alpha by animateFloatAsState(
        if (show) 1f else 0f,
        tween(300),
    )

    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(260.dp).height(60.dp), contentAlignment = Alignment.TopCenter) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = offsetY
                    this.alpha = alpha
                },
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.inverseSurface,
            shadowElevation = 4.dp,
        ) {
            Box(modifier = Modifier.padding(12.dp), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = stringResource(Res.string.demo_action_completed),
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

// ─── 13. AccordionMenu ──────────────────────────────────────────────

/** Expandable menu items that open/close with spring animation */
// ─── 14. MagneticButton ─────────────────────────────────────────────

/** Button that subtly moves toward a simulated touch position */
@Composable
fun MagneticButton(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val magnetX by inf.animateFloat(
        -8f, 8f,
        infiniteRepeatable(tween(2000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
    )
    val magnetY by inf.animateFloat(
        -4f, 4f,
        infiniteRepeatable(tween(1500, easing = FastOutSlowInEasing), RepeatMode.Reverse),
    )

    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Spring.In).size(180.dp, 60.dp), contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier
                .graphicsLayer {
                    translationX = magnetX
                    translationY = magnetY
                },
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.primary,
            shadowElevation = 6.dp,
        ) {
            Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(Res.string.demo_hover_me),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

// ─── 15. RippleButton ───────────────────────────────────────────────

/** Material ripple effect that radiates from center */
@Composable
fun RippleButton(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    val inf = rememberInfiniteTransition()
    val rippleProgress by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(1200, easing = FastOutSlowInEasing), RepeatMode.Restart),
    )

    val primary = MaterialTheme.colorScheme.primary

    Box(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Scale.Pop).size(160.dp, 56.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val maxRadius = size.width / 2
            drawRoundRect(
                primary,
                cornerRadius = CornerRadius(12.dp.toPx()),
            )
            drawCircle(
                Color.White.copy(alpha = (1f - rippleProgress) * 0.4f),
                radius = maxRadius * rippleProgress,
                center = center,
            )
        }
        Text(
            text = stringResource(Res.string.demo_press),
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}

// ─── 16. FloatingParticles ──────────────────────────────────────────

/** Small particles that float upward like embers */
@Composable
fun FloatingParticles(
    particleCount: Int = 12,
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    data class Particle(val x: Float, val speed: Float, val size: Float, val delay: Int)

    val particles = remember {
        List(12) {
            Particle(
                x = (it * 23f) % 260f,
                speed = 1500f + (it * 300f) % 1500f,
                size = 3f + (it * 1.3f) % 4f,
                delay = (it * 200) % 1200,
            )
        }
    }

    val inf = rememberInfiniteTransition()
    val time by inf.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart),
    )

    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.tertiary

    Canvas(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Zoom.In).width(260.dp).height(160.dp)) {
        particles.forEach { p ->
            val prog = ((time + p.delay / 3000f) % 1f)
            val y = size.height * (1f - prog)
            val alpha = if (prog < 0.2f) prog * 5f else if (prog > 0.8f) (1f - prog) * 5f else 1f
            val drift = sin(prog * PI.toFloat() * 2f) * 10f
            val color = if (p.size > 5f) primary else secondary
            drawCircle(
                color.copy(alpha = alpha * 0.8f),
                radius = p.size,
                center = Offset(p.x + drift, y),
            )
        }
    }
}

// ─── 17. ScrollDirectionHeader ──────────────────────────────────────

/** Header that slides up/hides when scrolling down, reveals on up */
@Composable
fun ScrollDirectionHeader(
    modifier: Modifier = Modifier,
) {
    var entryVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { entryVisible = true }

    var hidden by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            hidden = true; delay(1500)
            hidden = false; delay(1500)
        }
    }

    val offsetY by animateFloatAsState(
        if (hidden) -50f else 0f,
        spring(dampingRatio = 0.8f, stiffness = Spring.StiffnessMediumLow),
    )
    val alpha by animateFloatAsState(
        if (hidden) 0f else 1f,
        tween(300),
    )

    Column(modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.Up).width(260.dp).height(80.dp)) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .graphicsLayer {
                    translationY = offsetY
                    this.alpha = alpha
                },
            color = MaterialTheme.colorScheme.primary,
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(Res.string.demo_app_header),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f).background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = if (hidden) "\u2193 Scrolling down" else "\u2191 Scrolling up",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

// ─── 18. TextLineReveal ─────────────────────────────────────────────

/** Multi-line text where each line slides in from right with stagger */

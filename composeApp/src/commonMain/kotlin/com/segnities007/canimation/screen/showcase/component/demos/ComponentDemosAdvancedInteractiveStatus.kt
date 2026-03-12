package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_credit_card_brand
import canimation.composeapp.generated.resources.component_credit_card_cvv
import canimation.composeapp.generated.resources.component_credit_card_number
import canimation.composeapp.generated.resources.component_notification_count
import com.segnities007.canimation.compose.LoopingIntStep
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import com.segnities007.canimation.compose.rememberLoopingIntSequence
import com.segnities007.canimation.compose.rememberLoopingToggle
import com.segnities007.canimation.compose.rememberReplayVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedNotificationBell(modifier: Modifier = Modifier) {
    val autoHasNotification = rememberLoopingToggle(
        initialValue = false,
        initialDelayMillis = 2000L,
        toggleDelayMillis = 2000L,
    )
    var hasNotification by remember { mutableStateOf(autoHasNotification) }
    LaunchedEffect(autoHasNotification) { hasNotification = autoHasNotification }
    val shake by animateFloatAsState(if (hasNotification) 1f else 0f, spring(dampingRatio = 0.3f, stiffness = 600f))
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Box(contentAlignment = Alignment.TopEnd) {
            Icon(Icons.Default.Notifications, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.graphicsLayer { rotationZ = shake * 15f }.size(28.dp))
            if (hasNotification) {
                val visible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
                Box(Modifier.canimation(visible = visible, effect = Canimation.Scale.Pop).size(16.dp).clip(CircleShape).background(Color(0xFFEF4444)), contentAlignment = Alignment.Center) {
                    Text(stringResource(Res.string.component_notification_count), color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun AnimatedCountdownTimer(modifier: Modifier = Modifier) {
    val seconds = rememberLoopingIntSequence(
        initialValue = 10,
        initialDelayMillis = 1000L,
        steps = buildList {
            for (value in 9 downTo 0) {
                add(LoopingIntStep(value = value, holdDurationMillis = 1000L))
            }
            add(LoopingIntStep(value = 10, holdDurationMillis = 1000L))
        },
    )
    val visible = rememberReplayVisibility(replayKey = seconds, resetDurationMillis = 30L)
    val progress = seconds / 10f
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(Modifier.size(64.dp)) {
                drawArc(primaryColor.copy(alpha = 0.1f), 0f, 360f, false, style = Stroke(6f, cap = StrokeCap.Round))
                drawArc(primaryColor, -90f, 360f * progress, false, style = Stroke(6f, cap = StrokeCap.Round))
            }
            Box(Modifier.canimation(visible = visible, effect = Canimation.Scale.Pop)) {
                Text(seconds.toString().padStart(2, '0'), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace, color = if (seconds <= 3) Color(0xFFEF4444) else MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

@Composable
fun AnimatedCreditCard(modifier: Modifier = Modifier) {
    val autoFlipped = rememberLoopingToggle(
        initialValue = false,
        initialDelayMillis = 2500L,
        toggleDelayMillis = 2500L,
    )
    var flipped by remember { mutableStateOf(autoFlipped) }
    LaunchedEffect(autoFlipped) { flipped = autoFlipped }
    val rotationY by animateFloatAsState(if (flipped) 180f else 0f, tween(600))
    Box(modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
        Box(Modifier.fillMaxWidth(0.85f).height(70.dp).graphicsLayer { this.rotationY = rotationY; cameraDistance = 12f * density }) {
            Surface(shape = RoundedCornerShape(12.dp), color = if (rotationY <= 90f) Color(0xFF1E293B) else Color(0xFF334155), modifier = Modifier.fillMaxSize()) {
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

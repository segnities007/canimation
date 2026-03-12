package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.component_step_cart
import canimation.composeapp.generated.resources.component_step_confirm
import canimation.composeapp.generated.resources.component_step_payment
import canimation.composeapp.generated.resources.component_step_shipping
import com.segnities007.canimation.compose.rememberLoopingIndex
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedStepper(modifier: Modifier = Modifier) {
    val step = rememberLoopingIndex(
        itemCount = 4,
        initialDelayMillis = 1500L,
        stepDelayMillis = 1500L,
    )
    val labels = listOf(
        stringResource(Res.string.component_step_cart),
        stringResource(Res.string.component_step_shipping),
        stringResource(Res.string.component_step_payment),
        stringResource(Res.string.component_step_confirm),
    )

    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        labels.forEachIndexed { index, label ->
            val done = index < step
            val active = index == step
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val bg by animateColorAsState(
                    when {
                        done -> Color(0xFF22C55E)
                        active -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.outlineVariant
                    },
                )
                Surface(
                    shape = CircleShape,
                    color = bg,
                    modifier = Modifier.size(28.dp),
                ) {
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
                                text = "${index + 1}",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 9.sp,
                    color = if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            if (index < labels.lastIndex) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(2.dp)
                        .background(if (index < step) Color(0xFF22C55E) else MaterialTheme.colorScheme.outlineVariant),
                )
            }
        }
    }
}

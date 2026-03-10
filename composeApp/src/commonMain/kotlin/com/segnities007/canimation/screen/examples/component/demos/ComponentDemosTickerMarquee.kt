package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.examples_ticker_default
import com.segnities007.canimation.compose.allowsLoopingMotion
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.LocalCanimationContext
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource
import kotlin.math.roundToInt

@Composable
fun TickerMarquee(
    text: String? = null,
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val loopingMotionEnabled = LocalCanimationContext.current.level.allowsLoopingMotion()
    val offset: Float

    if (loopingMotionEnabled) {
        val infiniteTransition = rememberInfiniteTransition()
        val animatedOffset by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = -1f,
            animationSpec =
                infiniteRepeatable(
                    animation = tween(8000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart,
                ),
        )
        offset = animatedOffset
    } else {
        offset = 0f
    }
    val tickerText = text ?: stringResource(Res.string.examples_ticker_default)
    val repeatedText = tickerText.repeat(3)

    Box(
        modifier =
            modifier
                .canimation(visible = entryVisible, effect = Canimation.Fade.In)
                .fillMaxWidth()
                .height(32.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = repeatedText,
            modifier =
                Modifier.offset {
                    val totalWidth = repeatedText.length * 7
                    IntOffset((offset * totalWidth).roundToInt(), 0)
                },
            style =
                MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                ),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
        )
    }
}

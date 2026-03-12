package com.segnities007.canimation.screen.showcase.component.demos

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.demo_tap
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

@Composable
fun ColorMorph(
    label: String = "Tap",
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val colors = listOf(
        Color(0xFF9B8AFF),
        Color(0xFF64D8CB),
        Color(0xFFFF6B9D),
        Color(0xFFFFA726),
        Color(0xFF42A5F5),
    )
    var colorIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1500)
            colorIndex = (colorIndex + 1) % colors.size
        }
    }

    val animatedColor by animateColorAsState(
        targetValue = colors[colorIndex],
        animationSpec = tween(800),
    )

    Box(
        modifier = modifier
            .canimation(visible = entryVisible, effect = Canimation.Zoom.In)
            .size(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(animatedColor)
            .clickable { colorIndex = (colorIndex + 1) % colors.size },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(Res.string.demo_tap),
            style = MaterialTheme.typography.labelMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}

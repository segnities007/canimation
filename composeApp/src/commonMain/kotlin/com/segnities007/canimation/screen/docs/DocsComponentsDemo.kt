package com.segnities007.canimation.screen.docs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedToggleVisibility
import com.segnities007.canimation.compose.rememberLoopingVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation

@Composable
internal fun HeroDemo() {
    val effects = listOf(Canimation.Fade.Up, Canimation.Scale.Pop, Canimation.Bounce.In, Canimation.Rotate.In)
    val labels = listOf("Fade.Up", "Scale.Pop", "Bounce.In", "Rotate.In")
    val visible = rememberLoopingVisibility(
        visibleDurationMillis = 2200L,
        hiddenDurationMillis = 600L,
    )
    Row(
        Modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        effects.forEachIndexed { index, effect ->
            val itemVisible =
                rememberDelayedToggleVisibility(
                    triggerVisible = visible,
                    enterDelayMillis = index * 100L,
                )
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                modifier = Modifier.padding(horizontal = 6.dp).canimation(visible = itemVisible, effect = effect),
            ) {
                Text(
                    labels[index],
                    Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }
    }
}

@Composable
internal fun EffectDemo() {
    val effects =
        listOf(
            "Fade.Up" to Canimation.Fade.Up,
            "Scale.Pop" to Canimation.Scale.Pop,
            "Slide.Left + Fade" to (Canimation.Slide.Left + Canimation.Fade.In),
            "Bounce.In" to Canimation.Bounce.In,
        )
    val visible = rememberLoopingVisibility(
        visibleDurationMillis = 2500L,
        hiddenDurationMillis = 800L,
    )
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        effects.forEachIndexed { index, (label, effect) ->
            val show =
                rememberDelayedToggleVisibility(
                    triggerVisible = visible,
                    enterDelayMillis = index * 120L,
                )
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f)),
                modifier = Modifier.canimation(visible = show, effect = effect),
            ) {
                Text(
                    label,
                    Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
        }
    }
}

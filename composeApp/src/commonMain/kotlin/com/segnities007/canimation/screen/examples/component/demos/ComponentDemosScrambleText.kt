package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay

@Composable
fun ScrambleText(
    targetText: String = "CANIMATION",
    modifier: Modifier = Modifier,
) {
    val entryVisible = rememberDelayedEntryVisibility(entryDelayMillis = 0L)
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%"
    var displayText by remember { mutableStateOf(targetText.map { chars.random() }.joinToString("")) }
    var resolvedCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            resolvedCount = 0
            delay(300)
            for (index in targetText.indices) {
                repeat(3) {
                    displayText = targetText.take(resolvedCount) +
                        (resolvedCount until targetText.length).map { chars.random() }.joinToString("")
                    delay(40)
                }
                resolvedCount = index + 1
                displayText = targetText.take(resolvedCount) +
                    (resolvedCount until targetText.length).map { chars.random() }.joinToString("")
            }
            displayText = targetText
            delay(2500)
        }
    }

    Text(
        modifier = modifier.canimation(visible = entryVisible, effect = Canimation.Fade.In),
        text = displayText,
        style = MaterialTheme.typography.titleLarge.copy(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp,
        ),
        color = MaterialTheme.colorScheme.primary,
    )
}

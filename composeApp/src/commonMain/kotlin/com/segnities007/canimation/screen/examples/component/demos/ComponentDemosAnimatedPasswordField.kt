package com.segnities007.canimation.screen.examples.component.demos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.demo_password
import com.segnities007.canimation.compose.allowsLoopingMotion
import io.github.canimation.core.LocalCanimationContext
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedPasswordField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    val targetText = "p@ssw0rd"
    val passwordLabel = stringResource(Res.string.demo_password)
    val loopingMotionEnabled = LocalCanimationContext.current.level.allowsLoopingMotion()

    LaunchedEffect(loopingMotionEnabled, targetText) {
        if (!loopingMotionEnabled) {
            text = targetText
            return@LaunchedEffect
        }
        while (true) {
            text = ""
            for (char in targetText) {
                text += char
                delay(200)
            }
            delay(1500)
        }
    }

    Column(modifier.fillMaxWidth().padding(16.dp)) {
        Text(passwordLabel, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(4.dp))
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = passwordLabel },
        ) {
            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "*".repeat(text.length),
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.weight(1f),
                )
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(18.dp),
                )
            }
        }
    }
}

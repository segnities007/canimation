package com.segnities007.canimation.screen.docs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedEntryVisibility
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.docs_ref_compose_multiplatform
import canimation.composeapp.generated.resources.docs_ref_one_api_five_targets
import canimation.composeapp.generated.resources.docs_ref_platform_support
import io.github.canimation.core.Canimation
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun PlatformContent(stage: Int) {
    Column(Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        PageTitle(stringResource(Res.string.docs_ref_platform_support), stringResource(Res.string.docs_ref_one_api_five_targets))
        Surface(shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f), border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(stringResource(Res.string.docs_ref_compose_multiplatform), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    val platforms = listOf("Android" to Canimation.Fade.Up, "iOS" to Canimation.Fade.Left, "Desktop (JVM)" to Canimation.Scale.Pop, "Web (JS)" to Canimation.Fade.Right, "Web (WasmJs)" to Canimation.Bounce.In)
                    platforms.forEachIndexed { i, (name, effect) ->
                        val visible = rememberDelayedEntryVisibility(entryDelayMillis = i * 150L + 200L)
                        Surface(shape = RoundedCornerShape(50), color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f), modifier = Modifier.canimation(visible = visible, effect = effect)) {
                            Text(name, Modifier.padding(horizontal = 14.dp, vertical = 6.dp), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}

package com.segnities007.canimation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import io.github.canimation.core.CanimationTokens
import io.github.canimation.core.CanimationVisibility
import io.github.canimation.core.EasingTokens
import io.github.canimation.core.canimationEnter
import kotlinx.coroutines.delay

@Composable
fun TokenReferenceScreen(modifier: Modifier = Modifier) {
    val tokens = CanimationTokens.Default
    var replayTrigger by remember { mutableIntStateOf(0) }
    var entryStage by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        for (i in 0..4) { delay(100); entryStage = i }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
    Column(
        modifier = Modifier
            .widthIn(max = 960.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(Modifier.fillMaxWidth().canimationEnter(visible = entryStage >= 0, preset = CanimationPreset.FadeUp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Tokens in Action",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )
            Button(onClick = { replayTrigger++ }) {
                Text("▶ Replay")
            }
        }
        }

        // Duration demo
        Box(Modifier.fillMaxWidth().canimationEnter(visible = entryStage >= 1, preset = CanimationPreset.FadeUp)) {
        TokenDemoCard(title = "Duration") {
            DurationDemo(replayTrigger = replayTrigger)
        }
        }

        // Easing demo
        Box(Modifier.fillMaxWidth().canimationEnter(visible = entryStage >= 2, preset = CanimationPreset.FadeUp)) {
        TokenDemoCard(title = "Easing") {
            EasingDemo(replayTrigger = replayTrigger)
        }
        }

        // Distance demo
        Box(Modifier.fillMaxWidth().canimationEnter(visible = entryStage >= 3, preset = CanimationPreset.FadeUp)) {
        TokenDemoCard(title = "Distance") {
            DistanceDemo(replayTrigger = replayTrigger)
        }
        }

        // Reference tables
        Box(Modifier.fillMaxWidth().canimationEnter(visible = entryStage >= 4, preset = CanimationPreset.FadeUp)) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Reference",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )

        TokenCard(title = "Duration Tokens") {
            TokenRow("short", tokens.duration.short.toString())
            TokenRow("medium", tokens.duration.medium.toString())
            TokenRow("long", tokens.duration.long.toString())
        }

        TokenCard(title = "Easing Tokens") {
            TokenRow("standard", "CubicBezier(0.2, 0.0, 0.0, 1.0)")
            TokenRow("emphasizedDecelerate", "CubicBezier(0.05, 0.7, 0.1, 1.0)")
            TokenRow("emphasizedAccelerate", "CubicBezier(0.3, 0.0, 0.8, 0.15)")
            TokenRow("decelerate", "CubicBezier(0.0, 0.0, 0.0, 1.0)")
            TokenRow("accelerate", "CubicBezier(0.3, 0.0, 1.0, 1.0)")
        }

        TokenCard(title = "Distance Tokens") {
            TokenRow("small", tokens.distance.small.toString())
            TokenRow("medium", tokens.distance.medium.toString())
            TokenRow("large", tokens.distance.large.toString())
        }

        TokenCard(title = "Spring Tokens") {
            TokenRow("gentle", "damping=0.85, stiffness=280f")
            TokenRow("snappy", "damping=0.80, stiffness=420f")
        }

        TokenCard(title = "Reduced Motion Rules") {
            TokenRow("duration", "→ 120ms (short)")
            TokenRow("offset", "→ × 0.25 (1/4 distance)")
            TokenRow("scale", "→ compress toward 1.0 by 75%")
            TokenRow("alpha", "→ unchanged")
            TokenRow("easing", "→ decelerate")
        }
        }
        }
    }
    }
}

@Composable
private fun DurationDemo(replayTrigger: Int) {
    val durations = listOf("short" to 120, "medium" to 220, "long" to 400)

    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            durations.forEachIndexed { index, (name, ms) ->
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(replayTrigger) {
                    visible = false
                    delay(100L + index * 200L)
                    visible = true
                }
                LaunchedEffect(Unit) {
                    delay(300L + index * 200L)
                    visible = true
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = name,
                        modifier = Modifier.width(72.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Box(
                        modifier = Modifier.weight(1f).height(36.dp),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        CanimationVisibility(
                            visible = visible,
                            enterFullSpec = CanimationSpec(
                                durationMs = ms,
                                easing = EasingTokens.Default.standard,
                                alpha = CanimationRange(0f, 1f),
                                offsetX = CanimationDpRange((-80).dp, 0.dp),
                            ),
                        ) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MaterialTheme.colorScheme.primary,
                            ) {
                                Text(
                                    text = "${ms}ms",
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EasingDemo(replayTrigger: Int) {
    val easings = listOf(
        "standard" to EasingTokens.Default.standard,
        "decelerate" to EasingTokens.Default.decelerate,
        "accelerate" to EasingTokens.Default.accelerate,
    )

    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            easings.forEachIndexed { index, (name, easing) ->
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(replayTrigger) {
                    visible = false
                    delay(100L + index * 150L)
                    visible = true
                }
                LaunchedEffect(Unit) {
                    delay(300L + index * 150L)
                    visible = true
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = name,
                        modifier = Modifier.width(90.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Box(
                        modifier = Modifier.weight(1f).height(36.dp),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        CanimationVisibility(
                            visible = visible,
                            enterFullSpec = CanimationSpec(
                                durationMs = 400,
                                easing = easing,
                                alpha = CanimationRange(0f, 1f),
                                offsetX = CanimationDpRange((-100).dp, 0.dp),
                            ),
                        ) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MaterialTheme.colorScheme.tertiary,
                            ) {
                                Text(
                                    text = name,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onTertiary,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DistanceDemo(replayTrigger: Int) {
    val distances = listOf("small" to 8, "medium" to 16, "large" to 32)

    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            distances.forEachIndexed { index, (name, dp) ->
                var visible by remember { mutableStateOf(false) }
                LaunchedEffect(replayTrigger) {
                    visible = false
                    delay(100L + index * 150L)
                    visible = true
                }
                LaunchedEffect(Unit) {
                    delay(300L + index * 150L)
                    visible = true
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = name,
                        modifier = Modifier.width(72.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Box(
                        modifier = Modifier.weight(1f).height(36.dp),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        CanimationVisibility(
                            visible = visible,
                            enterFullSpec = CanimationSpec(
                                durationMs = 220,
                                easing = EasingTokens.Default.standard,
                                alpha = CanimationRange(0f, 1f),
                                offsetY = CanimationDpRange(dp.dp, 0.dp),
                            ),
                        ) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MaterialTheme.colorScheme.secondary,
                            ) {
                                Text(
                                    text = "${dp}dp",
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSecondary,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TokenDemoCard(
    title: String,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
private fun TokenCard(
    title: String,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
private fun TokenRow(name: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

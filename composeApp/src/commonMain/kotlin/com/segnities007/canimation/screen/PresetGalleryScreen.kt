package com.segnities007.canimation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.segnities007.canimation.component.AnimationShowcase
import com.segnities007.canimation.component.PresetPreviewTuning
import com.segnities007.canimation.component.tunePresetSpec
import io.github.canimation.core.CanimationDpRange
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationRange
import io.github.canimation.core.CanimationSpec
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationProvider
import io.github.canimation.core.canimationTransition
import io.github.canimation.presets.PresetsExtensionRegistry
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PresetGalleryScreen(modifier: Modifier = Modifier) {
    var autoPlayEnabled by remember { mutableStateOf(true) }
    var cycleMs by remember { mutableFloatStateOf(1400f) }
    var durationScale by remember { mutableFloatStateOf(1f) }
    var distanceScale by remember { mutableFloatStateOf(1f) }
    var scaleIntensity by remember { mutableFloatStateOf(1f) }
    var rotationScale by remember { mutableFloatStateOf(1f) }
    var autoPlayTick by remember { mutableIntStateOf(0) }
    var motionFilter by remember { mutableStateOf(MotionFilter.All) }

    var selectedPreset by remember { mutableStateOf(CanimationPreset.FadeUp) }
    var comparePreset by remember { mutableStateOf(CanimationPreset.FadeDown) }
    var codeDialogPreset by remember { mutableStateOf<CanimationPreset?>(null) }

    val tuning = remember(durationScale, distanceScale, scaleIntensity, rotationScale) {
        PresetPreviewTuning(
            durationScale = durationScale,
            distanceScale = distanceScale,
            scaleIntensity = scaleIntensity,
            rotationScale = rotationScale,
        )
    }

    val allPresetSpecs = PresetsExtensionRegistry.allPresetSpecs
    val similarOptions = remember(selectedPreset) { similarPresets(selectedPreset) }

    LaunchedEffect(selectedPreset, similarOptions) {
        if (comparePreset == selectedPreset || comparePreset !in similarOptions) {
            comparePreset = similarOptions.firstOrNull()
                ?: CanimationPreset.entries.first { it != selectedPreset }
        }
    }

    LaunchedEffect(autoPlayEnabled, cycleMs) {
        if (autoPlayEnabled) {
            while (true) {
                autoPlayTick += 1
                delay(cycleMs.toLong().coerceAtLeast(350L))
            }
        }
    }

    val selectedSpec = remember(selectedPreset, tuning) {
        tunePresetSpec(allPresetSpecs.getValue(selectedPreset), tuning)
    }
    val compareSpec = remember(comparePreset, tuning) {
        tunePresetSpec(allPresetSpecs.getValue(comparePreset), tuning)
    }
    val filteredPresets = remember(allPresetSpecs, motionFilter) {
        CanimationPreset.entries.filter { preset ->
            matchesMotionFilter(allPresetSpecs.getValue(preset).fullEnter, motionFilter)
        }
    }

    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 220.dp),
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column(modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)) {
                Text(
                    text = "${CanimationPreset.entries.size} Presets",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 8.dp),
                ) {
                    MotionFilter.entries.forEach { filter ->
                        FilterChip(
                            selected = motionFilter == filter,
                            onClick = { motionFilter = filter },
                            label = { Text(filter.label) },
                        )
                    }
                }
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text("Auto play all presets", style = MaterialTheme.typography.titleSmall)
                    Switch(
                        checked = autoPlayEnabled,
                        onCheckedChange = { autoPlayEnabled = it },
                    )
                }

                LabeledSlider(
                    label = "Cycle interval",
                    value = cycleMs,
                    onValueChange = { cycleMs = it },
                    valueRange = 350f..2500f,
                    displayValue = "${cycleMs.toInt()}ms",
                )
                LabeledSlider(
                    label = "Duration scale",
                    value = durationScale,
                    onValueChange = { durationScale = it },
                    valueRange = 0.5f..2.0f,
                    displayValue = "${durationScale.fmt2()}x",
                )
                LabeledSlider(
                    label = "Distance scale",
                    value = distanceScale,
                    onValueChange = { distanceScale = it },
                    valueRange = 0.2f..2.5f,
                    displayValue = "${distanceScale.fmt2()}x",
                )
                LabeledSlider(
                    label = "Scale intensity",
                    value = scaleIntensity,
                    onValueChange = { scaleIntensity = it },
                    valueRange = 0.2f..2.5f,
                    displayValue = "${scaleIntensity.fmt2()}x",
                )
                LabeledSlider(
                    label = "Rotation scale",
                    value = rotationScale,
                    onValueChange = { rotationScale = it },
                    valueRange = 0.2f..2.5f,
                    displayValue = "${rotationScale.fmt2()}x",
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = { autoPlayTick += 1 },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text("Replay all once")
                    }
                    OutlinedButton(
                        onClick = {
                            durationScale = 1f
                            distanceScale = 1f
                            scaleIntensity = 1f
                            rotationScale = 1f
                        },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text("Reset params")
                    }
                }
            }
        }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Card(
            modifier = Modifier
                .padding(vertical = 2.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text("Similar preset comparison", style = MaterialTheme.typography.titleSmall)

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    similarOptions.forEach { preset ->
                        FilterChip(
                            selected = comparePreset == preset,
                            onClick = { comparePreset = preset },
                            label = { Text(preset.name) },
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ComparisonPreviewTile(
                        label = "Base",
                        preset = selectedPreset,
                        spec = selectedSpec,
                        autoPlayEnabled = autoPlayEnabled,
                        autoPlayTick = autoPlayTick,
                        modifier = Modifier.weight(1f),
                    )
                    ComparisonPreviewTile(
                        label = "Compare",
                        preset = comparePreset,
                        spec = compareSpec,
                        autoPlayEnabled = autoPlayEnabled,
                        autoPlayTick = autoPlayTick,
                        modifier = Modifier.weight(1f),
                    )
                }

                Text(
                    text = buildDiffSummary(selectedPreset, selectedSpec.fullEnter, comparePreset, compareSpec.fullEnter),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        }

        items(filteredPresets, key = { it.name }) { preset ->
            AnimationShowcase(
                title = presetDescription(preset),
                preset = preset,
                baseSpec = allPresetSpecs.getValue(preset),
                    tuning = tuning,
                    autoPlayEnabled = autoPlayEnabled,
                    autoPlayTick = autoPlayTick,
                    selectedForCompare = preset == selectedPreset || preset == comparePreset,
                    onCardClick = { tapped ->
                        selectedPreset = tapped
                        codeDialogPreset = tapped
                    },
                )
            }
        }

        val dialogPreset = codeDialogPreset
        if (dialogPreset != null) {
            val dialogSpec = tunePresetSpec(allPresetSpecs.getValue(dialogPreset), tuning)
            CodeSampleDialog(
                preset = dialogPreset,
                spec = dialogSpec,
                onDismiss = { codeDialogPreset = null },
            )
        }
    }
}

@Composable
private fun CodeSampleDialog(
    preset: CanimationPreset,
    spec: CanimationPresetSpec,
    onDismiss: () -> Unit,
) {
    val clipboard = LocalClipboardManager.current
    val codeSample = remember(preset, spec) { buildCodeSample(preset, spec) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("${preset.name} code sample")
        },
        text = {
            SelectionContainer {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .verticalScroll(rememberScrollState()),
                ) {
                    Text(
                        text = codeSample,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = { clipboard.setText(AnnotatedString(codeSample)) },
            ) {
                Text("Copy")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        },
    )
}

private fun buildCodeSample(
    preset: CanimationPreset,
    spec: CanimationPresetSpec,
): String {
    return buildString {
        appendLine("// Preset API")
        appendLine("Modifier.canimationTransition(")
        appendLine("    visible = visible,")
        appendLine("    enterPreset = CanimationPreset.${preset.name},")
        appendLine("    exitPreset = CanimationPreset.${preset.name},")
        appendLine(")")
        appendLine()
        appendLine("// Tuned custom spec currently shown in gallery")
        appendLine("val enterFullSpec = CanimationSpec(")
        appendSpecFields(spec.fullEnter)
        appendLine(")")
        appendLine()
        appendLine("val enterReducedSpec = CanimationSpec(")
        appendSpecFields(spec.reducedEnter)
        appendLine(")")
        appendLine()
        appendLine("val exitFullSpec = CanimationSpec(")
        appendSpecFields(spec.fullExit)
        appendLine(")")
        appendLine()
        appendLine("val exitReducedSpec = CanimationSpec(")
        appendSpecFields(spec.reducedExit)
        appendLine(")")
        appendLine()
        appendLine("Modifier.canimationTransition(")
        appendLine("    visible = visible,")
        appendLine("    enterFullSpec = enterFullSpec,")
        appendLine("    enterReducedSpec = enterReducedSpec,")
        appendLine("    exitFullSpec = exitFullSpec,")
        appendLine("    exitReducedSpec = exitReducedSpec,")
        appendLine(")")
    }
}

private fun StringBuilder.appendSpecFields(spec: CanimationSpec) {
    appendLine("    durationMs = ${spec.durationMs},")
    appendLine("    easing = EasingTokens.Default.standard, // replace if needed")
    spec.alpha?.let { appendLine("    alpha = CanimationRange(${it.from.fmt2()}f, ${it.to.fmt2()}f),") }
    spec.offsetX?.let { appendLine("    offsetX = CanimationDpRange(${it.from.value.fmt2()}.dp, ${it.to.value.fmt2()}.dp),") }
    spec.offsetY?.let { appendLine("    offsetY = CanimationDpRange(${it.from.value.fmt2()}.dp, ${it.to.value.fmt2()}.dp),") }
    spec.scale?.let { appendLine("    scale = CanimationRange(${it.from.fmt2()}f, ${it.to.fmt2()}f),") }
    spec.rotation?.let { appendLine("    rotation = CanimationRange(${it.from.fmt2()}f, ${it.to.fmt2()}f),") }
}

@Composable
private fun ComparisonPreviewTile(
    label: String,
    preset: CanimationPreset,
    spec: CanimationPresetSpec,
    autoPlayEnabled: Boolean,
    autoPlayTick: Int,
    modifier: Modifier = Modifier,
) {
    var visible by remember(preset) { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    suspend fun replay() {
        visible = false
        delay(220)
        visible = true
    }

    LaunchedEffect(preset) {
        replay()
    }

    LaunchedEffect(autoPlayEnabled, autoPlayTick) {
        if (autoPlayEnabled) {
            replay()
        }
    }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text("$label: ${preset.name}", style = MaterialTheme.typography.labelLarge)
            Text(
                text = "${spec.fullEnter.durationMs}ms / ${spec.reducedEnter.durationMs}ms",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .canimationTransition(
                        visible = visible,
                        enterFullSpec = spec.fullEnter,
                        enterReducedSpec = spec.reducedEnter,
                        exitFullSpec = spec.fullExit,
                        exitReducedSpec = spec.reducedExit,
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("Preview", color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }

            OutlinedButton(
                onClick = { scope.launch { replay() } },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Replay")
            }
        }
    }
}

@Composable
private fun LabeledSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    displayValue: String,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(label, style = MaterialTheme.typography.bodyMedium)
            Text(displayValue, style = MaterialTheme.typography.bodySmall)
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
        )
    }
}

private fun buildDiffSummary(
    leftPreset: CanimationPreset,
    left: CanimationSpec,
    rightPreset: CanimationPreset,
    right: CanimationSpec,
): String {
    return buildString {
        append("Compare ")
        append(leftPreset.name)
        append(" vs ")
        append(rightPreset.name)
        append(": ")
        append("duration ")
        append(left.durationMs)
        append("ms/")
        append(right.durationMs)
        append("ms, ")
        append("offsetX ")
        append(rangeDiff(left.offsetX, right.offsetX))
        append(", offsetY ")
        append(rangeDiff(left.offsetY, right.offsetY))
        append(", scale ")
        append(rangeDiff(left.scale, right.scale))
        append(", rotation ")
        append(rangeDiff(left.rotation, right.rotation))
    }
}

private fun rangeDiff(left: CanimationDpRange?, right: CanimationDpRange?): String {
    if (left == null && right == null) return "none"
    val l = left?.let { "${it.from.value.fmt2()}->${it.to.value.fmt2()}dp" } ?: "none"
    val r = right?.let { "${it.from.value.fmt2()}->${it.to.value.fmt2()}dp" } ?: "none"
    return "$l / $r"
}

private fun rangeDiff(left: CanimationRange?, right: CanimationRange?): String {
    if (left == null && right == null) return "none"
    val l = left?.let { "${it.from.fmt2()}->${it.to.fmt2()}" } ?: "none"
    val r = right?.let { "${it.from.fmt2()}->${it.to.fmt2()}" } ?: "none"
    return "$l / $r"
}

private fun similarPresets(preset: CanimationPreset): List<CanimationPreset> {
    val family = presetFamily(preset)
    val sameFamily = CanimationPreset.entries.filter { it != preset && presetFamily(it) == family }
    if (sameFamily.isNotEmpty()) return sameFamily

    val fallback = CanimationPreset.entries.filter { it != preset }
    return fallback.take(8)
}

private fun presetFamily(preset: CanimationPreset): String {
    val name = preset.name
    return when {
        name.startsWith("FadeIn") -> "fade-in-directional"
        name.startsWith("ZoomIn") -> "zoom-in"
        name.startsWith("BackIn") -> "back-in"
        name.startsWith("BounceIn") -> "bounce-in"
        name.startsWith("RotateIn") -> "rotate-in"
        name.startsWith("LightSpeedIn") -> "light-speed"
        name.startsWith("Slide") -> "slide"
        name.startsWith("Flip") -> "flip"
        name.startsWith("SharedAxis") -> "shared-axis"
        name.startsWith("Spring") -> "spring"
        preset in attentionPresets -> "attention"
        name.startsWith("Fade") -> "fade"
        name.startsWith("Scale") -> "scale"
        else -> name.replace(Regex("(In|Out|Up|Down|Left|Right|Big|Small|Clockwise)$"), "")
    }
}

private val attentionPresets = setOf(
    CanimationPreset.Pulse,
    CanimationPreset.HeartBeat,
    CanimationPreset.Tada,
    CanimationPreset.Wobble,
    CanimationPreset.Swing,
    CanimationPreset.RubberBand,
    CanimationPreset.Bounce,
    CanimationPreset.Flash,
    CanimationPreset.ShakeX,
    CanimationPreset.ShakeY,
    CanimationPreset.HeadShake,
    CanimationPreset.Jello,
)

private fun Float.fmt2(): String {
    val rounded = (this * 100).roundToInt()
    val sign = if (rounded < 0) "-" else ""
    val absValue = abs(rounded)
    return "$sign${absValue / 100}.${(absValue % 100).toString().padStart(2, '0')}"
}

private enum class MotionFilter(val label: String) {
    All("All"),
    Translation("Move"),
    Scale("Scale"),
    Rotation("Rotate"),
    AlphaOnly("Fade Only"),
}

private fun matchesMotionFilter(spec: CanimationSpec, filter: MotionFilter): Boolean {
    return when (filter) {
        MotionFilter.All -> true
        MotionFilter.Translation -> spec.offsetX != null || spec.offsetY != null
        MotionFilter.Scale -> spec.scale != null
        MotionFilter.Rotation -> spec.rotation != null
        MotionFilter.AlphaOnly -> spec.offsetX == null && spec.offsetY == null && spec.scale == null && spec.rotation == null
    }
}

private fun presetDescription(preset: CanimationPreset): String = when (preset) {
    CanimationPreset.FadeUp -> "Fade + slide up"
    CanimationPreset.Fade -> "Alpha crossfade"
    CanimationPreset.ScaleIn -> "Scale from 92%"
    CanimationPreset.SlideLeft -> "Slide from right"
    CanimationPreset.SlideRight -> "Slide from left"
    CanimationPreset.FadeDown -> "Fade + slide down"
    CanimationPreset.ScaleUp -> "Scale from 108%"
    CanimationPreset.ZoomIn -> "Zoom from 50%"
    CanimationPreset.ZoomOut -> "Zoom from 150%"
    CanimationPreset.Pop -> "Overshoot pop"
    CanimationPreset.Expand -> "Scale from 0%"
    CanimationPreset.SlideUp -> "Long slide up"
    CanimationPreset.SlideDown -> "Long slide down"
    CanimationPreset.ElevateIn -> "Subtle rise + scale"
    CanimationPreset.DropIn -> "Drop with bounce"
    CanimationPreset.RotateIn -> "CCW rotate entry"
    CanimationPreset.RotateClockwise -> "CW rotate entry"
    CanimationPreset.SpinIn -> "360° spin + scale"
    CanimationPreset.FlipIn -> "180° flip entry"
    CanimationPreset.SwingIn -> "Swing + slide"
    CanimationPreset.ZoomInUp -> "Zoom + upward"
    CanimationPreset.ZoomInDown -> "Zoom + downward"
    CanimationPreset.ZoomInLeft -> "Zoom + from left"
    CanimationPreset.ZoomInRight -> "Zoom + from right"
    CanimationPreset.BackInUp -> "Back easing up"
    CanimationPreset.BackInDown -> "Back easing down"
    CanimationPreset.ShrinkIn -> "Shrink from 200%"
    CanimationPreset.GentleFade -> "600ms gentle fade"
    CanimationPreset.Snap -> "Instant 10ms cut"
    // Animate.css inspired
    CanimationPreset.BounceIn -> "Bouncy scale entry"
    CanimationPreset.BounceInDown -> "Bounce from top"
    CanimationPreset.BounceInLeft -> "Bounce from left"
    CanimationPreset.BounceInRight -> "Bounce from right"
    CanimationPreset.FadeInLeftBig -> "Big slide from left"
    CanimationPreset.FadeInRightBig -> "Big slide from right"
    CanimationPreset.LightSpeedInRight -> "Fast slide + tilt"
    CanimationPreset.LightSpeedInLeft -> "Fast slide left + tilt"
    CanimationPreset.JackInTheBox -> "Scale + rotate combo"
    CanimationPreset.RollIn -> "Roll + slide left"
    // Framer Motion inspired
    CanimationPreset.SpringIn -> "Spring overshoot scale"
    CanimationPreset.SpringSlideUp -> "Spring slide up"
    CanimationPreset.SpringFadeIn -> "Spring fade + scale"
    // AnimXYZ inspired
    CanimationPreset.FlipUp -> "Flip + upward slide"
    CanimationPreset.FlipDown -> "Flip + downward slide"
    CanimationPreset.TiltIn -> "Tilt + scale entry"
    // Material Motion inspired
    CanimationPreset.FadeThrough -> "Material fade-through"
    CanimationPreset.SharedAxisX -> "Shared axis horizontal"
    CanimationPreset.SharedAxisY -> "Shared axis vertical"
    CanimationPreset.EmphasizedEntry -> "Emphasized decelerate"
    // Animate.css remaining directional
    CanimationPreset.BackInLeft -> "Back ease from left"
    CanimationPreset.BackInRight -> "Back ease from right"
    CanimationPreset.BounceInUp -> "Bounce from bottom"
    CanimationPreset.FadeInDownBig -> "Big fade from top"
    CanimationPreset.FadeInUpBig -> "Big fade from bottom"
    CanimationPreset.FadeInLeft -> "Fade from left"
    CanimationPreset.FadeInRight -> "Fade from right"
    CanimationPreset.FadeInTopLeft -> "Diagonal top-left"
    CanimationPreset.FadeInTopRight -> "Diagonal top-right"
    CanimationPreset.FadeInBottomLeft -> "Diagonal bottom-left"
    CanimationPreset.FadeInBottomRight -> "Diagonal bottom-right"
    CanimationPreset.RotateInDownLeft -> "Rotate down-left"
    CanimationPreset.RotateInDownRight -> "Rotate down-right"
    CanimationPreset.RotateInUpLeft -> "Rotate up-left"
    CanimationPreset.RotateInUpRight -> "Rotate up-right"
    CanimationPreset.FlipInY -> "Vertical flip"
    // Animate.css attention seekers
    CanimationPreset.Pulse -> "Scale pulse"
    CanimationPreset.HeartBeat -> "Heartbeat pulse"
    CanimationPreset.Tada -> "Tada emphasis"
    CanimationPreset.Wobble -> "Side wobble"
    CanimationPreset.Swing -> "Swing rotation"
    CanimationPreset.RubberBand -> "Rubber band stretch"
    CanimationPreset.Bounce -> "Bounce emphasis"
    CanimationPreset.Flash -> "Quick flash"
    CanimationPreset.ShakeX -> "Horizontal shake"
    CanimationPreset.ShakeY -> "Vertical shake"
    CanimationPreset.HeadShake -> "Head shake"
    CanimationPreset.Jello -> "Jello wobble"
    // AnimXYZ compositions
    CanimationPreset.FadeSmall -> "Fade + shrink"
    CanimationPreset.FadeBig -> "Fade + grow"
    CanimationPreset.FadeUpLeft -> "Fade up-left"
    CanimationPreset.FadeDownRight -> "Fade down-right"
    CanimationPreset.RotateScale -> "Rotate + scale"
    CanimationPreset.UpBig -> "Big upward slide"
}

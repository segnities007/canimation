package com.segnities007.canimation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationSpec
import io.github.canimation.core.CanimationPolicy
import io.github.canimation.core.CanimationProvider
import io.github.canimation.presets.PresetsExtensionRegistry
import kotlin.math.abs
import kotlin.math.roundToInt

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PresetGalleryScreen(
    modifier: Modifier = Modifier,
    autoPlayEnabled: Boolean = true,
    autoPlayTick: Int = 0,
    tuning: PresetPreviewTuning = PresetPreviewTuning(),
) {
    var autoPlayTickLocal by remember { mutableIntStateOf(0) }
    var motionFilter by remember { mutableStateOf(MotionFilter.All) }

    var codeDialogPreset by remember { mutableStateOf<CanimationPreset?>(null) }

    // Sync external tick
    LaunchedEffect(autoPlayTick) {
        autoPlayTickLocal = autoPlayTick
    }

    val allPresetSpecs = PresetsExtensionRegistry.allPresetSpecs

    val filteredPresets = remember(allPresetSpecs, motionFilter) {
        CanimationPreset.entries.filter { preset ->
            matchesMotionFilter(allPresetSpecs.getValue(preset).fullEnter, motionFilter)
        }
    }

    CanimationProvider(policy = CanimationPolicy.AlwaysFull) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 220.dp),
            modifier = Modifier.widthIn(max = 1200.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "GALLERY",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "${CanimationPreset.entries.size} Presets",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Browse, compare, and tune every built-in animation",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
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

        items(filteredPresets, key = { it.name }) { preset ->
            AnimationShowcase(
                title = presetDescription(preset),
                preset = preset,
                baseSpec = allPresetSpecs.getValue(preset),
                    tuning = tuning,
                    autoPlayEnabled = autoPlayEnabled,
                    autoPlayTick = autoPlayTickLocal,
                    selectedForCompare = false,
                    onCardClick = { tapped ->
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

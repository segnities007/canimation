package com.segnities007.canimation.screen.presets

import io.github.canimation.core.CanimationPreset
import io.github.canimation.core.CanimationPresetSpec
import io.github.canimation.core.CanimationSpec
import kotlin.math.abs
import kotlin.math.roundToInt

internal fun buildPresetCodeSample(
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

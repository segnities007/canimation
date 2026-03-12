package com.segnities007.canimation.screen.showcase.detail

import com.segnities007.canimation.screen.showcase.data.ShowcaseDemoType
import com.segnities007.canimation.screen.showcase.data.showcaseDemoTypeLabelRes

import androidx.compose.runtime.Composable
import canimation.composeapp.generated.resources.*
import io.github.canimation.core.CanimationEffect
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun formatShowcaseDemoType(demoType: ShowcaseDemoType): String = stringResource(showcaseDemoTypeLabelRes(demoType))

@Composable
internal fun describeEffect(effect: CanimationEffect): String {
    val parts = mutableListOf<String>()
    effect.alpha?.let { parts.add(stringResource(Res.string.examples_effect_alpha, it.from, it.to)) }
    effect.offsetX?.let { parts.add(stringResource(Res.string.examples_effect_offset_x, it.from, it.to)) }
    effect.offsetY?.let { parts.add(stringResource(Res.string.examples_effect_offset_y, it.from, it.to)) }
    effect.scale?.let { parts.add(stringResource(Res.string.examples_effect_scale, it.from, it.to)) }
    effect.rotation?.let { parts.add(stringResource(Res.string.examples_effect_rotation, it.from, it.to)) }
    parts.add(stringResource(Res.string.examples_effect_duration, effect.durationMs))
    return if (parts.size == 1) stringResource(Res.string.examples_effect_default, effect.durationMs) else parts.joinToString(", ")
}

@Composable
internal fun generateHowItWorks(demoType: ShowcaseDemoType): String = stringResource(
    when (demoType) {
        ShowcaseDemoType.Effect -> Res.string.examples_detail_how_effect
        ShowcaseDemoType.Transition -> Res.string.examples_detail_how_transition
        ShowcaseDemoType.Composition -> Res.string.examples_detail_how_composition
        ShowcaseDemoType.Stagger -> Res.string.examples_detail_how_stagger
        ShowcaseDemoType.Emphasis -> Res.string.examples_detail_how_emphasis
        ShowcaseDemoType.Component -> Res.string.examples_detail_how_component
        ShowcaseDemoType.RealWorld,
        ShowcaseDemoType.EnterExit,
        ShowcaseDemoType.Visibility,
        -> Res.string.examples_detail_how_general
    }
)

@Composable
internal fun generateIntegrationGuide(demoType: ShowcaseDemoType, tag: String): String = when (demoType) {
    ShowcaseDemoType.Effect -> stringResource(Res.string.examples_detail_guide_effect, tag)
    ShowcaseDemoType.Transition -> stringResource(Res.string.examples_detail_guide_transition)
    ShowcaseDemoType.Composition -> stringResource(Res.string.examples_detail_guide_composition)
    ShowcaseDemoType.Component -> stringResource(Res.string.examples_detail_guide_component)
    ShowcaseDemoType.Stagger,
    ShowcaseDemoType.Emphasis,
    ShowcaseDemoType.RealWorld,
    ShowcaseDemoType.EnterExit,
    ShowcaseDemoType.Visibility,
    -> stringResource(Res.string.examples_detail_guide_general, tag)
}

@Composable
internal fun generateUsageCode(demoType: ShowcaseDemoType, codeSnippet: String): String = when (demoType) {
    ShowcaseDemoType.Effect,
    ShowcaseDemoType.Transition,
    ShowcaseDemoType.Composition,
    ShowcaseDemoType.Stagger,
    ShowcaseDemoType.Emphasis,
    ShowcaseDemoType.EnterExit,
    ShowcaseDemoType.Visibility,
    ShowcaseDemoType.RealWorld,
    -> stringResource(Res.string.examples_detail_usage_template, codeSnippet.trimIndent())

    ShowcaseDemoType.Component -> stringResource(
        Res.string.examples_detail_usage_component_template,
        codeSnippet.trimIndent(),
    )
}

@Composable
internal fun generateFullExample(demoType: ShowcaseDemoType, codeSnippet: String): String = when (demoType) {
    ShowcaseDemoType.Effect,
    ShowcaseDemoType.Transition,
    ShowcaseDemoType.Composition,
    ShowcaseDemoType.Stagger,
    ShowcaseDemoType.Emphasis,
    ShowcaseDemoType.EnterExit,
    ShowcaseDemoType.Visibility,
    ShowcaseDemoType.RealWorld,
    -> stringResource(
        Res.string.examples_detail_full_template,
        codeSnippet.trimIndent().lines().joinToString("\n                "),
    )

    ShowcaseDemoType.Component -> stringResource(
        Res.string.examples_detail_full_component_template,
        codeSnippet.trimIndent().lines().joinToString("\n        "),
    )
}

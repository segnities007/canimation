package com.segnities007.canimation.screen.examples

import androidx.compose.runtime.Composable
import canimation.composeapp.generated.resources.*
import io.github.canimation.core.CanimationEffect
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun formatDemoType(demoType: DemoType): String = stringResource(
    when (demoType) {
        DemoType.Effect -> Res.string.examples_detail_type_effect
        DemoType.Transition -> Res.string.examples_detail_type_transition
        DemoType.Composition -> Res.string.examples_detail_type_composition
        DemoType.Stagger -> Res.string.examples_detail_type_stagger
        DemoType.Emphasis -> Res.string.examples_detail_type_emphasis
        DemoType.RealWorld -> Res.string.examples_detail_type_real_world
        DemoType.Component -> Res.string.examples_detail_type_component
        DemoType.EnterExit -> Res.string.examples_detail_type_enter_exit
        DemoType.Visibility -> Res.string.examples_detail_type_visibility
    }
)

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
internal fun generateHowItWorks(demoType: DemoType): String = stringResource(
    when (demoType) {
        DemoType.Effect -> Res.string.examples_detail_how_effect
        DemoType.Transition -> Res.string.examples_detail_how_transition
        DemoType.Composition -> Res.string.examples_detail_how_composition
        DemoType.Stagger -> Res.string.examples_detail_how_stagger
        DemoType.Emphasis -> Res.string.examples_detail_how_emphasis
        DemoType.Component -> Res.string.examples_detail_how_component
        DemoType.RealWorld,
        DemoType.EnterExit,
        DemoType.Visibility,
        -> Res.string.examples_detail_how_general
    }
)

@Composable
internal fun generateIntegrationGuide(demoType: DemoType, tag: String): String = when (demoType) {
    DemoType.Effect -> stringResource(Res.string.examples_detail_guide_effect, tag)
    DemoType.Transition -> stringResource(Res.string.examples_detail_guide_transition)
    DemoType.Composition -> stringResource(Res.string.examples_detail_guide_composition)
    DemoType.Component -> stringResource(Res.string.examples_detail_guide_component)
    DemoType.Stagger,
    DemoType.Emphasis,
    DemoType.RealWorld,
    DemoType.EnterExit,
    DemoType.Visibility,
    -> stringResource(Res.string.examples_detail_guide_general, tag)
}

@Composable
internal fun generateUsageCode(demoType: DemoType, codeSnippet: String): String = when (demoType) {
    DemoType.Effect,
    DemoType.Transition,
    DemoType.Composition,
    DemoType.Stagger,
    DemoType.Emphasis,
    DemoType.EnterExit,
    DemoType.Visibility,
    DemoType.RealWorld,
    -> stringResource(Res.string.examples_detail_usage_template, codeSnippet.trimIndent())

    DemoType.Component -> stringResource(
        Res.string.examples_detail_usage_component_template,
        codeSnippet.trimIndent(),
    )
}

@Composable
internal fun generateFullExample(demoType: DemoType, codeSnippet: String): String = when (demoType) {
    DemoType.Effect,
    DemoType.Transition,
    DemoType.Composition,
    DemoType.Stagger,
    DemoType.Emphasis,
    DemoType.EnterExit,
    DemoType.Visibility,
    DemoType.RealWorld,
    -> stringResource(
        Res.string.examples_detail_full_template,
        codeSnippet.trimIndent().lines().joinToString("\n                "),
    )

    DemoType.Component -> stringResource(
        Res.string.examples_detail_full_component_template,
        codeSnippet.trimIndent().lines().joinToString("\n        "),
    )
}

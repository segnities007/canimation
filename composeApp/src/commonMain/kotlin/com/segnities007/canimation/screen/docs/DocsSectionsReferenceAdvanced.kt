package com.segnities007.canimation.screen.docs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.compose.rememberDelayedToggleVisibility
import com.segnities007.canimation.compose.rememberLoopingVisibility
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.docs_atomic_atoms_title
import canimation.composeapp.generated.resources.docs_atomic_molecules_title
import canimation.composeapp.generated.resources.docs_atomic_organisms_title
import canimation.composeapp.generated.resources.docs_ref_animation_hierarchy_insp
import canimation.composeapp.generated.resources.docs_ref_animation_playground
import canimation.composeapp.generated.resources.docs_ref_atomic_design
import canimation.composeapp.generated.resources.docs_ref_complex_multi_property
import canimation.composeapp.generated.resources.docs_ref_core_engine_canimation
import canimation.composeapp.generated.resources.docs_ref_elastic_stretch_effect
import canimation.composeapp.generated.resources.docs_ref_inspired_by_atomic_desig
import canimation.composeapp.generated.resources.docs_ref_material_design_pattern
import canimation.composeapp.generated.resources.docs_ref_modular_architecture_i
import canimation.composeapp.generated.resources.docs_ref_modules
import canimation.composeapp.generated.resources.docs_ref_opacity_only
import canimation.composeapp.generated.resources.docs_ref_scale_overshoot
import canimation.composeapp.generated.resources.docs_ref_see_all_effects_in_act
import canimation.composeapp.generated.resources.docs_ref_single_property_animatio
import canimation.composeapp.generated.resources.docs_ref_translation_only
import canimation.composeapp.generated.resources.docs_ref_two_property_combination
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.canimation
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AtomicDesignContent(stage: Int) {
    Column(Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up), verticalArrangement = Arrangement.spacedBy(24.dp)) {
        PageTitle(stringResource(Res.string.docs_ref_atomic_design), stringResource(Res.string.docs_ref_animation_hierarchy_insp))

        BodyText(
            "Animations in canimation are organized by increasing complexity, " +
                stringResource(Res.string.docs_ref_inspired_by_atomic_desig)
        )

        SectionCard(title = stringResource(Res.string.docs_atomic_atoms_title), description = stringResource(Res.string.docs_ref_single_property_animatio), icon = Icons.Default.Tune) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                AtomicDemoRow("Canimation.Fade.In", stringResource(Res.string.docs_ref_opacity_only), Canimation.Fade.In)
                AtomicDemoRow("Canimation.Slide.Up", stringResource(Res.string.docs_ref_translation_only), Canimation.Slide.Up)
                AtomicDemoRow("Canimation.Scale.In", "Scale only", Canimation.Scale.In)
                AtomicDemoRow("Canimation.Rotate.In", "Rotation only", Canimation.Rotate.In)
            }
        }

        SectionCard(title = stringResource(Res.string.docs_atomic_molecules_title), description = stringResource(Res.string.docs_ref_two_property_combination), icon = Icons.Default.AutoAwesome) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                AtomicDemoRow("Canimation.Fade.Up", "Fade + slideUp", Canimation.Fade.Up)
                AtomicDemoRow("Canimation.Scale.Pop", stringResource(Res.string.docs_ref_scale_overshoot), Canimation.Scale.Pop)
                AtomicDemoRow("Canimation.Zoom.In", "Scale + fade", Canimation.Zoom.In)
                AtomicDemoRow("Canimation.Spring.Up", "Fade + slide + spring timing", Canimation.Spring.Up)
                AtomicDemoRow("Canimation.Bounce.Down", "Bounce + slideDown", Canimation.Bounce.Down)
            }
        }

        SectionCard(title = stringResource(Res.string.docs_atomic_organisms_title), description = stringResource(Res.string.docs_ref_complex_multi_property), icon = Icons.Default.Visibility) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                AtomicDemoRow("Canimation.Attention.Tada", "Scale + rotation emphasis", Canimation.Attention.Tada)
                AtomicDemoRow("Canimation.Entrance.Drop", "Offset + fade dramatic entrance", Canimation.Entrance.Drop)
                AtomicDemoRow("Canimation.Entrance.JackInTheBox", "Scale + rotation + fade", Canimation.Entrance.JackInTheBox)
                AtomicDemoRow("Canimation.Material.FadeThrough", stringResource(Res.string.docs_ref_material_design_pattern), Canimation.Material.FadeThrough)
                AtomicDemoRow("Canimation.Elastic.In", stringResource(Res.string.docs_ref_elastic_stretch_effect), Canimation.Elastic.In)
            }
        }

        CodeBlock("""// Atoms compose into Molecules
val molecule = CanimationEffect.fade() + CanimationEffect.slideUp()
// equivalent to: Canimation.Fade.Up

// Molecules compose into Organisms
val organism = Canimation.Fade.Up + CanimationEffect.scale(0.8f) + CanimationEffect.rotate(-10f)
// Custom complex animation!""")
    }
}

@Composable
private fun AtomicDemoRow(code: String, description: String, effect: CanimationEffect) {
    val visible = rememberLoopingVisibility(
        initialDelayMillis = 300L,
        visibleDurationMillis = 2000L,
        hiddenDurationMillis = 600L,
    )

    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Surface(shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f), modifier = Modifier.size(40.dp).canimation(visible = visible, effect = effect)) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material3.Icon(Icons.Default.PlayArrow, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
            }
        }
        Column(Modifier.weight(1f)) {
            Text(code, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun PlaygroundContent(stage: Int) {
    Column(Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up), verticalArrangement = Arrangement.spacedBy(24.dp)) {
        PageTitle(stringResource(Res.string.docs_ref_animation_playground), stringResource(Res.string.docs_ref_see_all_effects_in_act))

        val allEffects = listOf(
            "Fade.In" to Canimation.Fade.In,
            "Fade.Up" to Canimation.Fade.Up,
            "Scale.In" to Canimation.Scale.In,
            "Scale.Pop" to Canimation.Scale.Pop,
            "Slide.Up" to Canimation.Slide.Up,
            "Rotate.In" to Canimation.Rotate.In,
            "Bounce.In" to Canimation.Bounce.In,
            "Spring.Pop" to Canimation.Spring.Pop,
            "Flip.In" to Canimation.Flip.In,
            "Zoom.In" to Canimation.Zoom.In,
            "Entrance.Drop" to Canimation.Entrance.Drop,
            "Material.FadeThrough" to Canimation.Material.FadeThrough,
            "Elastic.In" to Canimation.Elastic.In,
            "Attention.Tada" to Canimation.Attention.Tada,
        )

        val globalVisibility = rememberLoopingVisibility(
            visibleDurationMillis = 2500L,
            hiddenDurationMillis = 800L,
        )

        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            allEffects.forEachIndexed { i, (label, effect) ->
                val visible = rememberDelayedToggleVisibility(
                    triggerVisible = globalVisibility,
                    enterDelayMillis = i * 50L,
                )
                Surface(shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.surfaceVariant, border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant), modifier = Modifier.canimation(visible = visible, effect = effect)) {
                    Text(label, Modifier.padding(horizontal = 12.dp, vertical = 8.dp), fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}

@Composable
internal fun ModulesContent(stage: Int) {
    Column(Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        PageTitle(stringResource(Res.string.docs_ref_modules), stringResource(Res.string.docs_ref_modular_architecture_i))
        ModuleRow("canimation-core", stringResource(Res.string.docs_ref_core_engine_canimation), Icons.Default.AutoAwesome)
    }
}

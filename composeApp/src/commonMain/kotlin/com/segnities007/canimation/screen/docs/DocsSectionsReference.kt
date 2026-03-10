package com.segnities007.canimation.screen.docs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.docs_api_modifier_canimation_signature
import canimation.composeapp.generated.resources.docs_atomic_atoms_title
import canimation.composeapp.generated.resources.docs_atomic_molecules_title
import canimation.composeapp.generated.resources.docs_atomic_organisms_title
import canimation.composeapp.generated.resources.docs_ref_animation_hierarchy_insp
import canimation.composeapp.generated.resources.docs_ref_animation_playground
import canimation.composeapp.generated.resources.docs_ref_api_surface
import canimation.composeapp.generated.resources.docs_ref_atomic_design
import canimation.composeapp.generated.resources.docs_ref_available_primitives
import canimation.composeapp.generated.resources.docs_ref_canimation_attention_tad
import canimation.composeapp.generated.resources.docs_ref_canimation_bounce_down
import canimation.composeapp.generated.resources.docs_ref_canimation_fade_in
import canimation.composeapp.generated.resources.docs_ref_canimation_fade_up
import canimation.composeapp.generated.resources.docs_ref_canimation_scale_in
import canimation.composeapp.generated.resources.docs_ref_canimation_scale_pop
import canimation.composeapp.generated.resources.docs_ref_canimation_slide_up
import canimation.composeapp.generated.resources.docs_ref_canimation_spring_up
import canimation.composeapp.generated.resources.docs_ref_canimation_stagger
import canimation.composeapp.generated.resources.docs_ref_canimationeffect_primiti
import canimation.composeapp.generated.resources.docs_ref_complex_multi_property
import canimation.composeapp.generated.resources.docs_ref_compose_effects_with_the
import canimation.composeapp.generated.resources.docs_ref_core_engine_canimation
import canimation.composeapp.generated.resources.docs_ref_each_primitive_controls
import canimation.composeapp.generated.resources.docs_ref_elastic_stretch_effect
import canimation.composeapp.generated.resources.docs_ref_every_way_to_animate_p
import canimation.composeapp.generated.resources.docs_ref_fade_slideup
import canimation.composeapp.generated.resources.docs_ref_inspired_by_atomic_desig
import canimation.composeapp.generated.resources.docs_ref_material_design_pattern
import canimation.composeapp.generated.resources.docs_ref_modular_architecture_i
import canimation.composeapp.generated.resources.docs_ref_modules
import canimation.composeapp.generated.resources.docs_ref_normal_70ms
import canimation.composeapp.generated.resources.docs_ref_opacity_only
import canimation.composeapp.generated.resources.docs_ref_quick_40ms
import canimation.composeapp.generated.resources.docs_ref_recommended
import canimation.composeapp.generated.resources.docs_ref_relaxed_200ms
import canimation.composeapp.generated.resources.docs_ref_scale_fade
import canimation.composeapp.generated.resources.docs_ref_scale_only
import canimation.composeapp.generated.resources.docs_ref_scale_overshoot
import canimation.composeapp.generated.resources.docs_ref_see_all_effects_in_act
import canimation.composeapp.generated.resources.docs_ref_single_property_animatio
import canimation.composeapp.generated.resources.docs_ref_slow_120ms
import canimation.composeapp.generated.resources.docs_ref_two_property_combination
import canimation.composeapp.generated.resources.docs_ref_translation_only
import canimation.composeapp.generated.resources.docs_ref_utilities
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import io.github.canimation.core.canimation
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

// ━━━━━━━━━━━━━━━━━━━━━━━━ API Surface ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
internal fun ApiSurfaceContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PageTitle(stringResource(Res.string.docs_ref_api_surface), stringResource(Res.string.docs_ref_every_way_to_animate_p))

        ApiCard(
            signature = stringResource(Res.string.docs_api_modifier_canimation_signature),
            badge = stringResource(Res.string.docs_ref_recommended),
            desc = stringResource(Res.string.docs_ref_compose_effects_with_the),
            code = """Modifier.canimation(visible = true, effect = Canimation.Fade.Up)
Modifier.canimation(visible = true, effect = Canimation.Scale.Pop + Canimation.Fade.In)
Modifier.canimation(visible = true, effect = Canimation.Entrance.Drop)""",
            icon = Icons.Default.AutoAwesome,
        )
        ApiCard(
            "Modifier.canimationTransition(visible, enter, exit)",
            "ADVANCED",
            "Separate enter and exit effects for asymmetric transitions.",
            """Modifier.canimationTransition(
    visible = isVisible,
    enter = Canimation.Fade.Up,
    exit = Canimation.Fade.Down,
)""",
        )
        ApiCard(
            "CanimationVisibility(visible, enterPreset)",
            "CLASSIC",
            "AnimatedVisibility wrapper with named presets.",
            """CanimationVisibility(
    visible = visible,
    enterPreset = CanimationPreset.BounceIn,
) { Text("Content") }""",
        )
        ApiCard(
            "Modifier.canimationEnter(visible, preset)",
            "LISTS",
            "Perfect for lazy layouts and staggered list items.",
            """Card(Modifier.canimationEnter(visible = show, preset = CanimationPreset.FadeUp)) {
    Text(item.name)
}""",
        )
        ApiCard(
            "Modifier.canimationEmphasize(active, preset)",
            "ATTENTION",
            "Pulse, Tada, Wobble — draw attention to interactive elements.",
            """Modifier.canimationEmphasize(active = hasNew, preset = CanimationPreset.Pulse)""",
        )
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Namespace Reference ━━━━━━━━━━━━━━━━━━━━━━━━

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun NamespaceContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        PageTitle("Canimation.* Namespace", "All animation effects organized by category")

        // Atoms
        SectionLabel("ATOMS — Single-property primitives")
        NamespaceRow("Canimation.Fade", listOf("In", "Out", "Gentle", "Quick"))
        NamespaceRow("Canimation.Scale", listOf("In", "Up", "Down", "Expand", "Shrink", "Subtle"))
        NamespaceRow("Canimation.Slide", listOf("Left", "Right", "Up", "Down", "LeftBig", "RightBig", "UpBig", "DownBig", "LeftSubtle", "RightSubtle", "UpSubtle", "DownSubtle"))
        NamespaceRow("Canimation.Rotate", listOf("In", "Clockwise", "Spin", "Half", "Quarter"))

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        // Molecules
        SectionLabel("MOLECULES — Two-property combinations")
        NamespaceRow("Canimation.Fade", listOf("Up", "Down", "Left", "Right", "UpBig", "DownBig", "LeftBig", "RightBig", "TopLeft", "TopRight", "BottomLeft", "BottomRight", "Small", "Big"))
        NamespaceRow("Canimation.Scale", listOf("Pop", "Zoom", "FadeIn", "UpFade", "DownFade"))
        NamespaceRow("Canimation.Rotate", listOf("FadeIn", "ClockwiseFade", "SpinFade", "ScaleIn", "DownLeft", "DownRight", "UpLeft", "UpRight"))
        NamespaceRow("Canimation.Bounce", listOf("In", "Down", "Up", "Left", "Right"))
        NamespaceRow("Canimation.Spring", listOf("In", "Up", "Down", "Left", "Right", "Pop", "Bounce"))
        NamespaceRow("Canimation.Flip", listOf("In", "Up", "Down", "X", "Y"))
        NamespaceRow("Canimation.Zoom", listOf("In", "Out", "InUp", "InDown", "InLeft", "InRight", "Dramatic"))

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        // Organisms
        SectionLabel("ORGANISMS — Complex multi-property effects")
        NamespaceRow("Canimation.Attention", listOf("Pulse", "HeartBeat", "Tada", "Wobble", "Swing", "RubberBand", "Jello", "Flash", "ShakeX", "ShakeY", "HeadShake", "Glow", "Ring"))
        NamespaceRow("Canimation.Entrance", listOf("Elevate", "Drop", "JackInTheBox", "RollIn", "LightSpeedLeft", "LightSpeedRight", "BackInUp", "BackInDown", "BackInLeft", "BackInRight", "ShrinkIn", "TiltIn", "Snap", "SwingIn", "Unfold", "Rise", "Emerge"))
        NamespaceRow("Canimation.Material", listOf("FadeThrough", "SharedAxisX", "SharedAxisY", "SharedAxisZ", "Emphasized", "ContainerTransform"))
        NamespaceRow("Canimation.Morph", listOf("ScaleUp", "Expand", "Collapse", "Elastic"))

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        // Stagger helper
        SectionLabel(stringResource(Res.string.docs_ref_utilities))
        NamespaceRow(stringResource(Res.string.docs_ref_canimation_stagger), listOf(stringResource(Res.string.docs_ref_quick_40ms), stringResource(Res.string.docs_ref_normal_70ms), stringResource(Res.string.docs_ref_slow_120ms), stringResource(Res.string.docs_ref_relaxed_200ms)))

        // Effect primitives
        Text(stringResource(Res.string.docs_ref_canimationeffect_primiti), style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            listOf(
                "fade()", "slideUp()", "slideDown()", "slideLeft()", "slideRight()",
                "scale()", "pop()", "rotate()", "spin()", "zoom()", "bounce()",
                "duration(ms)", "easing(e)",
            ).forEach { name ->
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f),
                ) {
                    Text(
                        name,
                        Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontFamily = FontFamily.Monospace,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

// ━━━━━━━━━━━━━━━━━━━━━━━━ Effect Primitives ━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
internal fun PrimitivesContent(stage: Int) {
    Column(
        Modifier.canimation(visible = stage >= 0, effect = Canimation.Fade.Up),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        PageTitle("Effect Primitives", "Build custom animations from building blocks")

        BodyText(stringResource(Res.string.docs_ref_each_primitive_controls))

        CodeBlock("""// Single primitives
CanimationEffect.fade()              // alpha: 0 → 1
CanimationEffect.slideUp(16.dp)      // offsetY: 16dp → 0dp
CanimationEffect.scale(0.9f)         // scale: 0.9 → 1.0
CanimationEffect.rotate(-15f)        // rotation: -15° → 0°

// Combine freely with +
val custom = CanimationEffect.fade() + 
    CanimationEffect.slideUp(24.dp) + 
    CanimationEffect.scale(0.8f) + 
    CanimationEffect.duration(400)

Modifier.canimation(visible = show, effect = custom)""")

        SectionCard("Available Primitives", "All building blocks at a glance") {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                PrimitiveRow("fade(from, to)", "Opacity transition. Default: 0f → 1f")
                PrimitiveRow("slideUp(offset)", "Vertical slide from below. Default: 16.dp")
                PrimitiveRow("slideDown(offset)", "Vertical slide from above. Default: 16.dp")
                PrimitiveRow("slideLeft(offset)", "Horizontal slide from right. Default: 24.dp")
                PrimitiveRow("slideRight(offset)", "Horizontal slide from left. Default: 24.dp")
                PrimitiveRow("scale(from, to)", "Scale transform. Default: 0.92f → 1f")
                PrimitiveRow("pop(from, to)", "Pop with overshoot. Default: 0.8f → 1f, 300ms")
                PrimitiveRow("rotate(from, to)", "Rotation in degrees. Default: -15° → 0°")
                PrimitiveRow("spin(from)", "Full 360° rotation. Default: -360°, 400ms")
                PrimitiveRow("zoom(from)", "Scale + fade. Default: 0.5f, 300ms")
                PrimitiveRow("bounce()", "Scale bounce. Default: 0.3f → 1f, 400ms")
                PrimitiveRow("duration(ms)", "Override duration in milliseconds")
                PrimitiveRow("easing(e)", "Override easing curve")
            }
        }
    }
}

@Composable
private fun PrimitiveRow(signature: String, description: String) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            signature,
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.widthIn(min = 160.dp),
        )
        Text(
            description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

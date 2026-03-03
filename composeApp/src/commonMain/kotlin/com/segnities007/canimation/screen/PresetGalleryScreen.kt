package com.segnities007.canimation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.segnities007.canimation.component.AnimationShowcase
import io.github.canimation.core.CanimationPreset

@Composable
fun PresetGalleryScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(
                text = "Preset Gallery",
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = "${CanimationPreset.entries.size} presets · Inspired by Motion.dev, Animate.css, Framer Motion, AnimXYZ, Material Motion",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(CanimationPreset.entries) { preset ->
                AnimationShowcase(
                    title = presetDescription(preset),
                    preset = preset,
                )
            }
        }
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

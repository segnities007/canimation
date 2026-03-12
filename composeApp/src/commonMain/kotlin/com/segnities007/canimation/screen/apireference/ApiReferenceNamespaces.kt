package com.segnities007.canimation.screen.apireference

import canimation.composeapp.generated.resources.*
import io.github.canimation.core.Canimation
import io.github.canimation.core.CanimationEffect
import org.jetbrains.compose.resources.StringResource

internal fun MutableList<ApiEntry>.addNamespaceEntries() {
    fun ns(obj: StringResource, name: StringResource, desc: StringResource, effect: CanimationEffect) = add(ApiEntry(
        name = Res.string.api_auto_canimation_obj_name,
        signature = Res.string.api_auto_val_name_canimationeffect,
        description = desc,
        category = RefFilter.Namespace,
    ))

    // Fade
    ns(Res.string.api_auto_fade, Res.string.api_auto_in, Res.string.api_auto_simple_fade_in_0_1, Canimation.Fade.In)
    ns(Res.string.api_auto_fade_2, Res.string.api_auto_out, Res.string.api_auto_fade_out_1_0, Canimation.Fade.Out)
    ns(Res.string.api_auto_fade_3, Res.string.api_auto_gentle, Res.string.api_auto_gentle_long_fade_600ms, Canimation.Fade.Gentle)
    ns(Res.string.api_auto_fade_4, Res.string.api_auto_quick, Res.string.api_auto_quick_snap_fade_120ms, Canimation.Fade.Quick)
    ns(Res.string.api_auto_fade_5, Res.string.api_auto_up, Res.string.api_auto_fade_slide_up, Canimation.Fade.Up)
    ns(Res.string.api_auto_fade_6, Res.string.api_auto_down, Res.string.api_auto_fade_slide_down, Canimation.Fade.Down)
    ns(Res.string.api_auto_fade_7, Res.string.api_auto_left, Res.string.api_auto_fade_from_left, Canimation.Fade.Left)
    ns(Res.string.api_auto_fade_8, Res.string.api_auto_right, Res.string.api_auto_fade_from_right, Canimation.Fade.Right)
    ns(Res.string.api_auto_fade_9, Res.string.api_auto_upbig, Res.string.api_auto_big_upward_slide_fade, Canimation.Fade.UpBig)
    ns(Res.string.api_auto_fade_10, Res.string.api_auto_downbig, Res.string.api_auto_big_downward_slide_fade, Canimation.Fade.DownBig)
    ns(Res.string.api_auto_fade_11, Res.string.api_auto_leftbig, Res.string.api_auto_big_slide_from_left_fade, Canimation.Fade.LeftBig)
    ns(Res.string.api_auto_fade_12, Res.string.api_auto_rightbig, Res.string.api_auto_big_slide_from_right_fade, Canimation.Fade.RightBig)
    ns(Res.string.api_auto_fade_13, Res.string.api_auto_topleft, Res.string.api_auto_diagonal_top_left_entry, Canimation.Fade.TopLeft)
    ns(Res.string.api_auto_fade_14, Res.string.api_auto_topright, Res.string.api_auto_diagonal_top_right_entry, Canimation.Fade.TopRight)
    ns(Res.string.api_auto_fade_15, Res.string.api_auto_bottomleft, Res.string.api_auto_diagonal_bottom_left_entry, Canimation.Fade.BottomLeft)
    ns(Res.string.api_auto_fade_16, Res.string.api_auto_bottomright, Res.string.api_auto_diagonal_bottom_right_entry, Canimation.Fade.BottomRight)
    ns(Res.string.api_auto_fade_17, Res.string.api_auto_small, Res.string.api_auto_fade_slight_scale_up, Canimation.Fade.Small)
    ns(Res.string.api_auto_fade_18, Res.string.api_auto_big, Res.string.api_auto_fade_dramatic_scale_up, Canimation.Fade.Big)

    // Scale
    ns(Res.string.api_auto_scale, Res.string.api_auto_in_2, Res.string.api_auto_scale_in_from_92, Canimation.Scale.In)
    ns(Res.string.api_auto_scale_2, Res.string.api_auto_up_2, Res.string.api_auto_scale_down_from_108, Canimation.Scale.Up)
    ns(Res.string.api_auto_scale_3, Res.string.api_auto_down_2, Res.string.api_auto_scale_from_85, Canimation.Scale.Down)
    ns(Res.string.api_auto_scale_4, Res.string.api_auto_expand, Res.string.api_auto_expand_from_0, Canimation.Scale.Expand)
    ns(Res.string.api_auto_scale_5, Res.string.api_auto_shrink, Res.string.api_auto_shrink_from_200, Canimation.Scale.Shrink)
    ns(Res.string.api_auto_scale_6, Res.string.api_auto_subtle, Res.string.api_auto_tiny_subtle_scale_97, Canimation.Scale.Subtle)
    ns(Res.string.api_auto_scale_7, Res.string.api_auto_pop, Res.string.api_auto_pop_with_overshoot, Canimation.Scale.Pop)
    ns(Res.string.api_auto_scale_8, Res.string.api_auto_fadein, Res.string.api_auto_scale_in_fade, Canimation.Scale.FadeIn)
    ns(Res.string.api_auto_scale_9, Res.string.api_auto_upfade, Res.string.api_auto_scale_from_below_fade, Canimation.Scale.UpFade)
    ns(Res.string.api_auto_scale_10, Res.string.api_auto_downfade, Res.string.api_auto_scale_from_above_fade, Canimation.Scale.DownFade)

    // Slide
    ns(Res.string.api_auto_slide, Res.string.api_auto_left_2, Res.string.api_auto_slide_from_right, Canimation.Slide.Left)
    ns(Res.string.api_auto_slide_2, Res.string.api_auto_right_2, Res.string.api_auto_slide_from_left, Canimation.Slide.Right)
    ns(Res.string.api_auto_slide_3, Res.string.api_auto_up_3, Res.string.api_auto_slide_from_below, Canimation.Slide.Up)
    ns(Res.string.api_auto_slide_4, Res.string.api_auto_down_3, Res.string.api_auto_slide_from_above, Canimation.Slide.Down)
    ns(Res.string.api_auto_slide_5, Res.string.api_auto_leftbig_2, Res.string.api_auto_big_slide_from_right, Canimation.Slide.LeftBig)
    ns(Res.string.api_auto_slide_6, Res.string.api_auto_rightbig_2, Res.string.api_auto_big_slide_from_left, Canimation.Slide.RightBig)
    ns(Res.string.api_auto_slide_7, Res.string.api_auto_upbig_2, Res.string.api_auto_big_slide_from_below, Canimation.Slide.UpBig)
    ns(Res.string.api_auto_slide_8, Res.string.api_auto_downbig_2, Res.string.api_auto_big_slide_from_above, Canimation.Slide.DownBig)
    ns(Res.string.api_auto_slide_9, Res.string.api_auto_upsubtle, Res.string.api_auto_subtle_slide_from_below_8dp, Canimation.Slide.UpSubtle)
    ns(Res.string.api_auto_slide_10, Res.string.api_auto_downsubtle, Res.string.api_auto_subtle_slide_from_above_8dp, Canimation.Slide.DownSubtle)
    ns(Res.string.api_auto_slide_11, Res.string.api_auto_leftsubtle, Res.string.api_auto_subtle_slide_from_right_8dp, Canimation.Slide.LeftSubtle)
    ns(Res.string.api_auto_slide_12, Res.string.api_auto_rightsubtle, Res.string.api_auto_subtle_slide_from_left_8dp, Canimation.Slide.RightSubtle)

    // Rotate
    ns(Res.string.api_auto_rotate, Res.string.api_auto_in_3, Res.string.api_auto_counter_clockwise_rotation_15, Canimation.Rotate.In)
    ns(Res.string.api_auto_rotate_2, Res.string.api_auto_clockwise, Res.string.api_auto_clockwise_rotation_15, Canimation.Rotate.Clockwise)
    ns(Res.string.api_auto_rotate_3, Res.string.api_auto_spin, Res.string.api_auto_full_360_spin, Canimation.Rotate.Spin)
    ns(Res.string.api_auto_rotate_4, Res.string.api_auto_half, Res.string.api_auto_half_turn_180, Canimation.Rotate.Half)
    ns(Res.string.api_auto_rotate_5, Res.string.api_auto_quarter, Res.string.api_auto_quarter_turn_90, Canimation.Rotate.Quarter)
    ns(Res.string.api_auto_rotate_6, Res.string.api_auto_fadein_2, Res.string.api_auto_rotate_fade, Canimation.Rotate.FadeIn)
    ns(Res.string.api_auto_rotate_7, Res.string.api_auto_clockwisefade, Res.string.api_auto_clockwise_fade, Canimation.Rotate.ClockwiseFade)
    ns(Res.string.api_auto_rotate_8, Res.string.api_auto_spinfade, Res.string.api_auto_spin_fade, Canimation.Rotate.SpinFade)
    ns(Res.string.api_auto_rotate_9, Res.string.api_auto_scalein, Res.string.api_auto_rotate_scale, Canimation.Rotate.ScaleIn)
    ns(Res.string.api_auto_rotate_10, Res.string.api_auto_downleft, Res.string.api_auto_rotate_down_left_fade, Canimation.Rotate.DownLeft)
    ns(Res.string.api_auto_rotate_11, Res.string.api_auto_downright, Res.string.api_auto_rotate_down_right_fade, Canimation.Rotate.DownRight)
    ns(Res.string.api_auto_rotate_12, Res.string.api_auto_upleft, Res.string.api_auto_rotate_up_left_fade, Canimation.Rotate.UpLeft)
    ns(Res.string.api_auto_rotate_13, Res.string.api_auto_upright, Res.string.api_auto_rotate_up_right_fade, Canimation.Rotate.UpRight)

    // Bounce
    ns(Res.string.api_auto_bounce, Res.string.api_auto_in_4, Res.string.api_auto_bouncy_scale_entry, Canimation.Bounce.In)
    ns(Res.string.api_auto_bounce_2, Res.string.api_auto_down_4, Res.string.api_auto_bounce_from_top, Canimation.Bounce.Down)
    ns(Res.string.api_auto_bounce_3, Res.string.api_auto_up_4, Res.string.api_auto_bounce_from_bottom, Canimation.Bounce.Up)
    ns(Res.string.api_auto_bounce_4, Res.string.api_auto_left_3, Res.string.api_auto_bounce_from_left, Canimation.Bounce.Left)
    ns(Res.string.api_auto_bounce_5, Res.string.api_auto_right_3, Res.string.api_auto_bounce_from_right, Canimation.Bounce.Right)

    // Spring
    ns(Res.string.api_auto_spring, Res.string.api_auto_in_5, Res.string.api_auto_spring_overshoot_scale, Canimation.Spring.In)
    ns(Res.string.api_auto_spring_2, Res.string.api_auto_up_5, Res.string.api_auto_spring_slide_from_below, Canimation.Spring.Up)
    ns(Res.string.api_auto_spring_3, Res.string.api_auto_down_5, Res.string.api_auto_spring_slide_from_above, Canimation.Spring.Down)
    ns(Res.string.api_auto_spring_4, Res.string.api_auto_left_4, Res.string.api_auto_spring_slide_from_left, Canimation.Spring.Left)
    ns(Res.string.api_auto_spring_5, Res.string.api_auto_right_4, Res.string.api_auto_spring_slide_from_right, Canimation.Spring.Right)
    ns(Res.string.api_auto_spring_6, Res.string.api_auto_pop_2, Res.string.api_auto_spring_pop_scale_fade, Canimation.Spring.Pop)
    ns(Res.string.api_auto_spring_7, Res.string.api_auto_bounce_6, Res.string.api_auto_spring_bounce_entry, Canimation.Spring.Bounce)

    // Flip
    ns(Res.string.api_auto_flip, Res.string.api_auto_in_6, Res.string.api_auto_half_rotation_flip_fade, Canimation.Flip.In)
    ns(Res.string.api_auto_flip_2, Res.string.api_auto_up_6, Res.string.api_auto_flip_upward, Canimation.Flip.Up)
    ns(Res.string.api_auto_flip_3, Res.string.api_auto_down_6, Res.string.api_auto_flip_downward, Canimation.Flip.Down)
    ns(Res.string.api_auto_flip_4, Res.string.api_auto_x, Res.string.api_auto_horizontal_flip_x_axis, Canimation.Flip.X)
    ns(Res.string.api_auto_flip_5, Res.string.api_auto_y, Res.string.api_auto_vertical_flip_y_axis, Canimation.Flip.Y)

    // Zoom
    ns(Res.string.api_auto_zoom, Res.string.api_auto_in_7, Res.string.api_auto_zoom_in_from_center, Canimation.Zoom.In)
    ns(Res.string.api_auto_zoom_2, Res.string.api_auto_out_2, Res.string.api_auto_zoom_out_large_normal, Canimation.Zoom.Out)
    ns(Res.string.api_auto_zoom_3, Res.string.api_auto_inup, Res.string.api_auto_zoom_in_from_above, Canimation.Zoom.InUp)
    ns(Res.string.api_auto_zoom_4, Res.string.api_auto_indown, Res.string.api_auto_zoom_in_from_below, Canimation.Zoom.InDown)
    ns(Res.string.api_auto_zoom_5, Res.string.api_auto_inleft, Res.string.api_auto_zoom_in_from_left, Canimation.Zoom.InLeft)
    ns(Res.string.api_auto_zoom_6, Res.string.api_auto_inright, Res.string.api_auto_zoom_in_from_right, Canimation.Zoom.InRight)
    ns(Res.string.api_auto_zoom_7, Res.string.api_auto_dramatic, Res.string.api_auto_dramatic_zoom_from_tiny, Canimation.Zoom.Dramatic)

    // Attention
    ns(Res.string.api_auto_attention, Res.string.api_auto_pulse, Res.string.api_auto_scale_pulse, Canimation.Attention.Pulse)
    ns(Res.string.api_auto_attention_2, Res.string.api_auto_heartbeat, Res.string.api_auto_heartbeat_double_pulse, Canimation.Attention.HeartBeat)
    ns(Res.string.api_auto_attention_3, Res.string.api_auto_tada, Res.string.api_auto_tada_celebration_rotation, Canimation.Attention.Tada)
    ns(Res.string.api_auto_attention_4, Res.string.api_auto_wobble, Res.string.api_auto_wobble_side_to_side, Canimation.Attention.Wobble)
    ns(Res.string.api_auto_attention_5, Res.string.api_auto_swing, Res.string.api_auto_swing_pendulum, Canimation.Attention.Swing)
    ns(Res.string.api_auto_attention_6, Res.string.api_auto_rubberband, Res.string.api_auto_rubber_band_stretch, Canimation.Attention.RubberBand)
    ns(Res.string.api_auto_attention_7, Res.string.api_auto_jello, Res.string.api_auto_jello_wiggle, Canimation.Attention.Jello)
    ns(Res.string.api_auto_attention_8, Res.string.api_auto_flash, Res.string.api_auto_flash_blink, Canimation.Attention.Flash)
    ns(Res.string.api_auto_attention_9, Res.string.api_auto_shakex, Res.string.api_auto_horizontal_shake, Canimation.Attention.ShakeX)
    ns(Res.string.api_auto_attention_10, Res.string.api_auto_shakey, Res.string.api_auto_vertical_shake, Canimation.Attention.ShakeY)
    ns(Res.string.api_auto_attention_11, Res.string.api_auto_headshake, Res.string.api_auto_head_shake_subtle, Canimation.Attention.HeadShake)
    ns(Res.string.api_auto_attention_12, Res.string.api_auto_glow, Res.string.api_auto_gentle_glow_throb, Canimation.Attention.Glow)
    ns(Res.string.api_auto_attention_13, Res.string.api_auto_ring, Res.string.api_auto_ring_bell_shake, Canimation.Attention.Ring)

    // Entrance
    ns(Res.string.api_auto_entrance, Res.string.api_auto_elevate, Res.string.api_auto_elevate_from_below_scale, Canimation.Entrance.Elevate)
    ns(Res.string.api_auto_entrance_2, Res.string.api_auto_drop, Res.string.api_auto_drop_from_above, Canimation.Entrance.Drop)
    ns(Res.string.api_auto_entrance_3, Res.string.api_auto_jackinthebox, Res.string.api_auto_jack_in_the_box_spring, Canimation.Entrance.JackInTheBox)
    ns(Res.string.api_auto_entrance_4, Res.string.api_auto_rollin, Res.string.api_auto_roll_in_from_side, Canimation.Entrance.RollIn)
    ns(Res.string.api_auto_entrance_5, Res.string.api_auto_lightspeedleft, Res.string.api_auto_light_speed_from_left, Canimation.Entrance.LightSpeedLeft)
    ns(Res.string.api_auto_entrance_6, Res.string.api_auto_lightspeedright, Res.string.api_auto_light_speed_from_right, Canimation.Entrance.LightSpeedRight)
    ns(Res.string.api_auto_entrance_7, Res.string.api_auto_backinup, Res.string.api_auto_back_ease_from_above, Canimation.Entrance.BackInUp)
    ns(Res.string.api_auto_entrance_8, Res.string.api_auto_backindown, Res.string.api_auto_back_ease_from_below, Canimation.Entrance.BackInDown)
    ns(Res.string.api_auto_entrance_9, Res.string.api_auto_backinleft, Res.string.api_auto_back_ease_from_left, Canimation.Entrance.BackInLeft)
    ns(Res.string.api_auto_entrance_10, Res.string.api_auto_backinright, Res.string.api_auto_back_ease_from_right, Canimation.Entrance.BackInRight)
    ns(Res.string.api_auto_entrance_11, Res.string.api_auto_shrinkin, Res.string.api_auto_shrink_from_large, Canimation.Entrance.ShrinkIn)
    ns(Res.string.api_auto_entrance_12, Res.string.api_auto_tiltin, Res.string.api_auto_tilt_scale_entry, Canimation.Entrance.TiltIn)
    ns(Res.string.api_auto_entrance_13, Res.string.api_auto_snap, Res.string.api_auto_snap_appearance, Canimation.Entrance.Snap)
    ns(Res.string.api_auto_entrance_14, Res.string.api_auto_swingin, Res.string.api_auto_swing_from_hinge, Canimation.Entrance.SwingIn)
    ns(Res.string.api_auto_entrance_15, Res.string.api_auto_unfold, Res.string.api_auto_unfold_expand, Canimation.Entrance.Unfold)
    ns(Res.string.api_auto_entrance_16, Res.string.api_auto_rise, Res.string.api_auto_rise_from_below, Canimation.Entrance.Rise)
    ns(Res.string.api_auto_entrance_17, Res.string.api_auto_emerge, Res.string.api_auto_emerge_from_center, Canimation.Entrance.Emerge)

    // Material
    ns(Res.string.api_auto_material, Res.string.api_auto_fadethrough, Res.string.api_auto_material_fade_through, Canimation.Material.FadeThrough)
    ns(Res.string.api_auto_material_2, Res.string.api_auto_sharedaxisx, Res.string.api_auto_shared_axis_horizontal, Canimation.Material.SharedAxisX)
    ns(Res.string.api_auto_material_3, Res.string.api_auto_sharedaxisy, Res.string.api_auto_shared_axis_vertical, Canimation.Material.SharedAxisY)
    ns(Res.string.api_auto_material_4, Res.string.api_auto_sharedaxisz, Res.string.api_auto_shared_axis_depth, Canimation.Material.SharedAxisZ)
    ns(Res.string.api_auto_material_5, Res.string.api_auto_emphasized, Res.string.api_auto_emphasized_decelerate, Canimation.Material.Emphasized)
    ns(Res.string.api_auto_material_6, Res.string.api_auto_containertransform, Res.string.api_auto_container_transform_hint, Canimation.Material.ContainerTransform)

    // Morph
    ns(Res.string.api_auto_morph, Res.string.api_auto_scaleup, Res.string.api_auto_scale_morph_from_small, Canimation.Morph.ScaleUp)
    ns(Res.string.api_auto_morph_2, Res.string.api_auto_expand_2, Res.string.api_auto_expand_morph_offset, Canimation.Morph.Expand)
    ns(Res.string.api_auto_morph_3, Res.string.api_auto_collapse, Res.string.api_auto_collapse_morph, Canimation.Morph.Collapse)

    // Wave
    ns(Res.string.api_auto_wave, Res.string.api_auto_gentle_2, Res.string.api_auto_gentle_floating_wave, Canimation.Wave.Gentle)
    ns(Res.string.api_auto_wave_2, Res.string.api_auto_strong, Res.string.api_auto_strong_wave_with_rotation, Canimation.Wave.Strong)
    ns(Res.string.api_auto_wave_3, Res.string.api_auto_ripple, Res.string.api_auto_ripple_expansion, Canimation.Wave.Ripple)
    ns(Res.string.api_auto_wave_4, Res.string.api_auto_float, Res.string.api_auto_floating_upward, Canimation.Wave.Float)
    ns(Res.string.api_auto_wave_5, Res.string.api_auto_drift, Res.string.api_auto_diagonal_drift, Canimation.Wave.Drift)

    // Glitch
    ns(Res.string.api_auto_glitch, Res.string.api_auto_in_8, Res.string.api_auto_digital_glitch_entry, Canimation.Glitch.In)
    ns(Res.string.api_auto_glitch_2, Res.string.api_auto_shake, Res.string.api_auto_glitchy_shake, Canimation.Glitch.Shake)
    ns(Res.string.api_auto_glitch_3, Res.string.api_auto_flicker, Res.string.api_auto_flickering, Canimation.Glitch.Flicker)
    ns(Res.string.api_auto_glitch_4, Res.string.api_auto_distort, Res.string.api_auto_distorted_entry, Canimation.Glitch.Distort)

    // Elastic
    ns(Res.string.api_auto_elastic, Res.string.api_auto_in_9, Res.string.api_auto_elastic_stretch_entry, Canimation.Elastic.In)
    ns(Res.string.api_auto_elastic_2, Res.string.api_auto_stretch, Res.string.api_auto_full_elastic_stretch, Canimation.Elastic.Stretch)
    ns(Res.string.api_auto_elastic_3, Res.string.api_auto_squash, Res.string.api_auto_squash_compression, Canimation.Elastic.Squash)
    ns(Res.string.api_auto_elastic_4, Res.string.api_auto_snap_2, Res.string.api_auto_snappy_elastic, Canimation.Elastic.Snap)
    ns(Res.string.api_auto_elastic_5, Res.string.api_auto_wobble_2, Res.string.api_auto_wobbly_elastic, Canimation.Elastic.Wobble)

    // Cinematic
    ns(Res.string.api_auto_cinematic, Res.string.api_auto_curtain, Res.string.api_auto_curtain_reveal, Canimation.Cinematic.Curtain)
    ns(Res.string.api_auto_cinematic_2, Res.string.api_auto_zoompan, Res.string.api_auto_camera_zoom_pan, Canimation.Cinematic.ZoomPan)
    ns(Res.string.api_auto_cinematic_3, Res.string.api_auto_dolly, Res.string.api_auto_dolly_zoom, Canimation.Cinematic.Dolly)
    ns(Res.string.api_auto_cinematic_4, Res.string.api_auto_reveal, Res.string.api_auto_subtle_reveal, Canimation.Cinematic.Reveal)
    ns(Res.string.api_auto_cinematic_5, Res.string.api_auto_fadetoblack, Res.string.api_auto_fade_to_black, Canimation.Cinematic.FadeToBlack)
    ns(Res.string.api_auto_cinematic_6, Res.string.api_auto_dramatic_2, Res.string.api_auto_dramatic_entrance, Canimation.Cinematic.Dramatic)

    // Playful
    ns(Res.string.api_auto_playful, Res.string.api_auto_wiggle, Res.string.api_auto_fun_wiggle, Canimation.Playful.Wiggle)
    ns(Res.string.api_auto_playful_2, Res.string.api_auto_hop, Res.string.api_auto_hop_bounce, Canimation.Playful.Hop)
    ns(Res.string.api_auto_playful_3, Res.string.api_auto_spin_2, Res.string.api_auto_playful_spin, Canimation.Playful.Spin)
    ns(Res.string.api_auto_playful_4, Res.string.api_auto_pop_3, Res.string.api_auto_quick_playful_pop, Canimation.Playful.Pop)
    ns(Res.string.api_auto_playful_5, Res.string.api_auto_twirl, Res.string.api_auto_twirling_entry, Canimation.Playful.Twirl)
    ns(Res.string.api_auto_playful_6, Res.string.api_auto_boing, Res.string.api_auto_spring_boing, Canimation.Playful.Boing)

    ns(Res.string.api_auto_diagonal, Res.string.api_auto_topleft_2, Res.string.api_auto_enter_from_top_left, Canimation.Diagonal.TopLeft)
    ns(Res.string.api_auto_diagonal_2, Res.string.api_auto_topright_2, Res.string.api_auto_enter_from_top_right, Canimation.Diagonal.TopRight)
    ns(Res.string.api_auto_diagonal_3, Res.string.api_auto_bottomleft_2, Res.string.api_auto_enter_from_bottom_left, Canimation.Diagonal.BottomLeft)
    ns(Res.string.api_auto_diagonal_4, Res.string.api_auto_bottomright_2, Res.string.api_auto_enter_from_bottom_right, Canimation.Diagonal.BottomRight)
    ns(Res.string.api_auto_diagonal_5, Res.string.api_auto_subtle_2, Res.string.api_auto_subtle_diagonal_entry, Canimation.Diagonal.Subtle)

    ns(Res.string.api_auto_shrink_2, Res.string.api_auto_out_3, Res.string.api_auto_shrink_from_oversized, Canimation.Shrink.Out)
    ns(Res.string.api_auto_shrink_3, Res.string.api_auto_subtle_3, Res.string.api_auto_subtle_shrink_entry, Canimation.Shrink.Subtle)
    ns(Res.string.api_auto_shrink_4, Res.string.api_auto_rotate_14, Res.string.api_auto_shrink_with_rotation, Canimation.Shrink.Rotate)
    ns(Res.string.api_auto_shrink_5, Res.string.api_auto_fadedown, Res.string.api_auto_shrink_and_drift_down, Canimation.Shrink.FadeDown)

    ns(Res.string.api_auto_tilt, Res.string.api_auto_left_5, Res.string.api_auto_tilt_from_left, Canimation.Tilt.Left)
    ns(Res.string.api_auto_tilt_2, Res.string.api_auto_right_5, Res.string.api_auto_tilt_from_right, Canimation.Tilt.Right)
    ns(Res.string.api_auto_tilt_3, Res.string.api_auto_up_7, Res.string.api_auto_tilt_from_above, Canimation.Tilt.Up)
    ns(Res.string.api_auto_tilt_4, Res.string.api_auto_down_7, Res.string.api_auto_tilt_from_below, Canimation.Tilt.Down)
    ns(Res.string.api_auto_tilt_5, Res.string.api_auto_swing_2, Res.string.api_auto_swinging_tilt, Canimation.Tilt.Swing)

    ns(Res.string.api_auto_float_2, Res.string.api_auto_up_8, Res.string.api_auto_gentle_float_up, Canimation.Float.Up)
    ns(Res.string.api_auto_float_3, Res.string.api_auto_down_8, Res.string.api_auto_gentle_float_down, Canimation.Float.Down)
    ns(Res.string.api_auto_float_4, Res.string.api_auto_gentle_3, Res.string.api_auto_very_gentle_float, Canimation.Float.Gentle)
    ns(Res.string.api_auto_float_5, Res.string.api_auto_scaleup_2, Res.string.api_auto_float_with_scale, Canimation.Float.ScaleUp)

    ns(Res.string.api_auto_drop_2, Res.string.api_auto_in_10, Res.string.api_auto_standard_drop_entry, Canimation.Drop.In)
    ns(Res.string.api_auto_drop_3, Res.string.api_auto_heavy, Res.string.api_auto_heavy_slam_drop, Canimation.Drop.Heavy)
    ns(Res.string.api_auto_drop_4, Res.string.api_auto_light, Res.string.api_auto_light_feather_drop, Canimation.Drop.Light)
    ns(Res.string.api_auto_drop_5, Res.string.api_auto_rotate_15, Res.string.api_auto_drop_with_rotation, Canimation.Drop.Rotate)

    ns(Res.string.api_auto_rise_2, Res.string.api_auto_in_11, Res.string.api_auto_standard_rise_entry, Canimation.Rise.In)
    ns(Res.string.api_auto_rise_3, Res.string.api_auto_slow, Res.string.api_auto_slow_rising_entry, Canimation.Rise.Slow)
    ns(Res.string.api_auto_rise_4, Res.string.api_auto_scale_11, Res.string.api_auto_rise_with_scale, Canimation.Rise.Scale)
    ns(Res.string.api_auto_rise_5, Res.string.api_auto_rotate_16, Res.string.api_auto_rise_with_rotation, Canimation.Rise.Rotate)

    ns(Res.string.api_auto_stretch_2, Res.string.api_auto_horizontal, Res.string.api_auto_horizontal_stretch, Canimation.Stretch.Horizontal)
    ns(Res.string.api_auto_stretch_3, Res.string.api_auto_vertical, Res.string.api_auto_vertical_stretch, Canimation.Stretch.Vertical)
    ns(Res.string.api_auto_stretch_4, Res.string.api_auto_both, Res.string.api_auto_stretch_both_axes, Canimation.Stretch.Both)
    ns(Res.string.api_auto_stretch_5, Res.string.api_auto_snap_3, Res.string.api_auto_stretch_with_snap, Canimation.Stretch.Snap)

    // Stagger (these are constants, not effects)
    add(ApiEntry(
        name = Res.string.api_auto_canimation_stagger,
        signature = Res.string.api_auto_object_stagger_n_const_val_quick_40_ms_n_const_v,
        description = Res.string.api_auto_stagger_delay_constants_for_building_delayed_ani,
        category = RefFilter.Namespace,
        badge = Res.string.api_auto_utility,
        codeExample = Res.string.api_auto_items_foreachindexed_i_item_n_launchedeffect_u,
    ))
}

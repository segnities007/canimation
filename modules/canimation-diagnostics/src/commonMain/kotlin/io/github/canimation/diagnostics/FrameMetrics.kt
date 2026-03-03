package io.github.canimation.diagnostics

import androidx.compose.runtime.Immutable

/**
 * Snapshot of frame rendering metrics.
 *
 * @property fps Current frames per second.
 * @property frameTimeMs Most recent frame time in milliseconds.
 * @property jankCount Number of frames exceeding the jank threshold.
 * @property p95FrameTimeMs 95th percentile frame time in milliseconds.
 */
@Immutable
data class FrameMetrics(
    val fps: Float = 0f,
    val frameTimeMs: Float = 0f,
    val jankCount: Int = 0,
    val p95FrameTimeMs: Float = 0f,
)

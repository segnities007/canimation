package com.segnities007.canimation.compose

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.canimation.core.CanimationLevel
import io.github.canimation.core.LocalCanimationContext
import kotlinx.coroutines.delay
import kotlin.random.Random

internal fun CanimationLevel.allowsLoopingMotion(): Boolean = this == CanimationLevel.Full

internal fun CanimationLevel.accessibleDelayMillis(requestedDelayMillis: Long): Long =
    if (this == CanimationLevel.Full) requestedDelayMillis else 0L

internal data class LoopingIntStep(
    val value: Int,
    val holdDurationMillis: Long,
)

internal data class LoopingFloatStep(
    val value: Float,
    val animationDurationMillis: Int = 0,
    val holdDurationMillis: Long,
    val easing: Easing = FastOutSlowInEasing,
)

@Composable
internal fun rememberLoopingVisibility(
    initialDelayMillis: Long = 0L,
    visibleDurationMillis: Long,
    hiddenDurationMillis: Long = 600L,
): Boolean {
    val level = LocalCanimationContext.current.level
    var visible by remember(initialDelayMillis, visibleDurationMillis, hiddenDurationMillis) {
        mutableStateOf(false)
    }

    LaunchedEffect(initialDelayMillis, visibleDurationMillis, hiddenDurationMillis, level) {
        visible = false
        delay(level.accessibleDelayMillis(initialDelayMillis))
        if (!level.allowsLoopingMotion()) {
            visible = true
            return@LaunchedEffect
        }
        while (true) {
            visible = true
            delay(visibleDurationMillis)
            visible = false
            delay(hiddenDurationMillis)
        }
    }

    return visible
}

@Composable
internal fun rememberReplayVisibility(
    replayKey: Any?,
    resetDurationMillis: Long = 220L,
): Boolean {
    val level = LocalCanimationContext.current.level
    var visible by remember(replayKey) { mutableStateOf(false) }

    LaunchedEffect(replayKey, resetDurationMillis, level) {
        visible = false
        delay(level.accessibleDelayMillis(resetDurationMillis))
        visible = true
    }

    return visible
}

@Composable
internal fun rememberLoopingToggle(
    initialValue: Boolean = false,
    initialDelayMillis: Long = 0L,
    toggleDelayMillis: Long,
): Boolean {
    val level = LocalCanimationContext.current.level
    var value by remember(initialValue, initialDelayMillis, toggleDelayMillis) {
        mutableStateOf(initialValue)
    }

    LaunchedEffect(initialValue, initialDelayMillis, toggleDelayMillis, level) {
        value = initialValue
        delay(level.accessibleDelayMillis(initialDelayMillis))
        if (!level.allowsLoopingMotion()) {
            return@LaunchedEffect
        }
        while (true) {
            value = !value
            delay(toggleDelayMillis)
        }
    }

    return value
}

@Composable
internal fun rememberLoopingIndex(
    itemCount: Int,
    initialIndex: Int = 0,
    initialDelayMillis: Long = 0L,
    stepDelayMillis: Long,
): Int {
    val level = LocalCanimationContext.current.level
    require(itemCount > 0) { "itemCount must be greater than 0" }

    var index by remember(itemCount, initialIndex, initialDelayMillis, stepDelayMillis) {
        mutableStateOf(initialIndex.coerceIn(0, itemCount - 1))
    }

    LaunchedEffect(itemCount, initialIndex, initialDelayMillis, stepDelayMillis, level) {
        index = initialIndex.coerceIn(0, itemCount - 1)
        delay(level.accessibleDelayMillis(initialDelayMillis))
        if (!level.allowsLoopingMotion()) {
            return@LaunchedEffect
        }
        while (true) {
            index = (index + 1) % itemCount
            delay(stepDelayMillis)
        }
    }

    return index
}

@Composable
internal fun rememberLoopingIntSequence(
    initialValue: Int,
    steps: List<LoopingIntStep>,
    initialDelayMillis: Long = 0L,
): Int {
    val level = LocalCanimationContext.current.level
    require(steps.isNotEmpty()) { "steps must not be empty" }

    var value by remember(initialValue, steps, initialDelayMillis) {
        mutableIntStateOf(initialValue)
    }

    LaunchedEffect(initialValue, steps, initialDelayMillis, level) {
        value = initialValue
        delay(level.accessibleDelayMillis(initialDelayMillis))
        if (!level.allowsLoopingMotion()) {
            return@LaunchedEffect
        }
        while (true) {
            steps.forEach { step ->
                value = step.value
                delay(step.holdDurationMillis)
            }
        }
    }

    return value
}

@Composable
internal fun rememberLoopingAnimatedFloat(
    initialValue: Float,
    steps: List<LoopingFloatStep>,
    initialDelayMillis: Long = 0L,
): Float {
    val level = LocalCanimationContext.current.level
    require(steps.isNotEmpty()) { "steps must not be empty" }

    val animatable = remember(initialValue) { Animatable(initialValue) }

    LaunchedEffect(initialValue, steps, initialDelayMillis, level) {
        animatable.snapTo(initialValue)
        delay(level.accessibleDelayMillis(initialDelayMillis))
        if (!level.allowsLoopingMotion()) {
            return@LaunchedEffect
        }
        while (true) {
            steps.forEach { step ->
                if (step.animationDurationMillis > 0) {
                    animatable.animateTo(
                        targetValue = step.value,
                        animationSpec =
                            tween(
                                durationMillis = step.animationDurationMillis,
                                easing = step.easing,
                            ),
                    )
                } else {
                    animatable.snapTo(step.value)
                }
                delay(step.holdDurationMillis)
            }
        }
    }

    return animatable.value
}

@Composable
internal fun rememberRandomIncrementingInt(
    initialValue: Int,
    delayRangeMillis: LongRange,
    incrementRange: IntRange,
): Int {
    val level = LocalCanimationContext.current.level
    require(delayRangeMillis.first >= 0L) { "delayRangeMillis must be non-negative" }
    require(delayRangeMillis.first <= delayRangeMillis.last) { "delayRangeMillis must be ascending" }
    require(incrementRange.first <= incrementRange.last) { "incrementRange must be ascending" }

    var value by remember(initialValue, delayRangeMillis, incrementRange) {
        mutableIntStateOf(initialValue)
    }

    LaunchedEffect(initialValue, delayRangeMillis, incrementRange, level) {
        value = initialValue
        if (!level.allowsLoopingMotion()) {
            return@LaunchedEffect
        }
        while (true) {
            val nextDelayMillis = Random.nextLong(delayRangeMillis.first, delayRangeMillis.last + 1)
            val nextIncrement = Random.nextInt(incrementRange.first, incrementRange.last + 1)
            delay(nextDelayMillis)
            value += nextIncrement
        }
    }

    return value
}

@Composable
internal fun rememberDelayedToggleVisibility(
    triggerVisible: Boolean,
    enterDelayMillis: Long = 0L,
): Boolean {
    val level = LocalCanimationContext.current.level
    var visible by remember(triggerVisible, enterDelayMillis) { mutableStateOf(false) }

    LaunchedEffect(triggerVisible, enterDelayMillis, level) {
        if (triggerVisible) {
            delay(level.accessibleDelayMillis(enterDelayMillis))
            visible = true
        } else {
            visible = false
        }
    }

    return visible
}

@Composable
internal fun rememberDelayedEntryVisibility(entryDelayMillis: Long): Boolean {
    val level = LocalCanimationContext.current.level
    var visible by remember(entryDelayMillis) { mutableStateOf(false) }

    LaunchedEffect(entryDelayMillis, level) {
        visible = false
        delay(level.accessibleDelayMillis(entryDelayMillis))
        visible = true
    }

    return visible
}

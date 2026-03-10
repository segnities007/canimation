package io.github.canimation.testkit

import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.runtime.DefaultCanimationRecipeRegistry as DefaultRuntimeRecipeRegistry
import io.github.canimation.test.CanimationTestClock as CoreCanimationTestClock

typealias CanimationTestClock = CoreCanimationTestClock

@Suppress("unused")
val DefaultCanimationRecipeRegistry: CanimationRecipeRegistry = DefaultRuntimeRecipeRegistry

fun canimationTestClock(initialTimeMillis: Long = 0L): CanimationTestClock = CanimationTestClock(initialTimeMillis = initialTimeMillis)

fun CanimationTestClock.advanceByFrame(frameDurationMillis: Long = 16L) {
    advanceBy(frameDurationMillis)
}

fun CanimationTestClock.advanceByFrames(
    frameCount: Int,
    frameDurationMillis: Long = 16L,
) {
    require(frameCount >= 0) { "frameCount must be >= 0, was $frameCount" }
    repeat(frameCount) {
        advanceByFrame(frameDurationMillis)
    }
}

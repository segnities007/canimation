package io.github.canimation.testkit

import io.github.canimation.core.CanimationRecipeRegistry
import io.github.canimation.core.PresetRegistry
import io.github.canimation.runtime.DefaultCanimationRecipeRegistry as DefaultRuntimeRecipeRegistry
import io.github.canimation.test.CanimationTestClock as CoreCanimationTestClock
import io.github.canimation.test.CanimationTestComposition as CoreCanimationTestComposition
import io.github.canimation.test.DefaultCanimationTestComposition as DefaultCoreTestComposition
import io.github.canimation.test.DefaultCanimationTestPresetRegistry as DefaultCoreTestPresetRegistry
import io.github.canimation.test.DefaultCanimationTestRecipeRegistry as DefaultCoreTestRecipeRegistry

typealias CanimationTestClock = CoreCanimationTestClock
typealias CanimationTestComposition = CoreCanimationTestComposition

@Suppress("unused")
val DefaultCanimationRecipeRegistry: CanimationRecipeRegistry = DefaultRuntimeRecipeRegistry

@Suppress("unused")
val DefaultCanimationTestPresetRegistry: PresetRegistry = DefaultCoreTestPresetRegistry

@Suppress("unused")
val DefaultCanimationTestRecipeRegistry: CanimationRecipeRegistry = DefaultCoreTestRecipeRegistry

@Suppress("unused")
val DefaultCanimationTestComposition: CanimationTestComposition = DefaultCoreTestComposition

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

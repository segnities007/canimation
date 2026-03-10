package io.github.canimation.test

/**
 * Virtual clock for deterministic animation testing.
 *
 * Allows tests to control time progression explicitly, enabling reproducible
 * animation state verification.
 */
class CanimationTestClock(
    initialTimeMillis: Long = 0L,
) {
    init {
        require(initialTimeMillis >= 0) { "initialTimeMillis must be >= 0, was $initialTimeMillis" }
    }

    private var currentMillis: Long = initialTimeMillis

    /** Current virtual time in milliseconds. */
    val currentTimeMillis: Long get() = currentMillis

    /**
     * Advances the clock by the specified duration.
     *
     * @param millis Duration to advance in milliseconds. Must be >= 0.
     */
    fun advanceBy(millis: Long) {
        require(millis >= 0) { "millis must be >= 0, was $millis" }
        currentMillis += millis
    }

    /**
     * Sets the clock to an absolute time.
     *
     * @param millis Target time in milliseconds. Must be >= current time.
     */
    fun advanceTo(millis: Long) {
        require(millis >= currentMillis) {
            "Cannot go back in time: current=$currentMillis, target=$millis"
        }
        currentMillis = millis
    }

    /**
     * Resets the clock to 0ms.
     */
    fun reset() {
        currentMillis = 0L
    }
}

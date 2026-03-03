package io.github.canimation.a11y

/**
 * Represents the system's motion preference setting.
 */
enum class SystemMotionPreference {
    /** No motion reduction requested by the OS. */
    NoPreference,

    /** The OS requests reduced motion. */
    ReduceMotion,
}

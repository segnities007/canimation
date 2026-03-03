package io.github.canimation.core

/**
 * Resolved animation level derived from [CanimationPolicy] and system preferences.
 *
 * - [Full]: All animations play with their full specification.
 * - [Reduced]: Animations use reduced motion specifications (shorter duration, less movement).
 * - [Off]: All animations snap instantly (duration = 0ms).
 */
enum class CanimationLevel {
    Full,
    Reduced,
    Off,
}

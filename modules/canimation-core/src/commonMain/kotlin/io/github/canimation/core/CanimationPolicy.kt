package io.github.canimation.core

/**
 * Policy for determining animation behavior.
 *
 * - [SystemAware]: Respects the OS reduce-motion preference.
 * - [AlwaysFull]: Forces full animations regardless of system settings.
 * - [AlwaysReduced]: Forces reduced animations regardless of system settings.
 * - [AlwaysOff]: Disables all animations (instant snap).
 */
sealed interface CanimationPolicy {
    data object SystemAware : CanimationPolicy
    data object AlwaysFull : CanimationPolicy
    data object AlwaysReduced : CanimationPolicy
    data object AlwaysOff : CanimationPolicy
}

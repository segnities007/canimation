package io.github.canimation.diagnostics

/**
 * Describes whether a [DiagnosticsCollector] can provide live frame metrics on the current platform.
 */
data class DiagnosticsCollectorAvailability(
    val isAvailable: Boolean,
    val reason: String? = null,
) {
    init {
        require(!isAvailable || reason == null) {
            "reason must be null when diagnostics are available"
        }
    }

    companion object {
        val Available: DiagnosticsCollectorAvailability = DiagnosticsCollectorAvailability(
            isAvailable = true,
        )
    }
}

/**
 * Optional capability contract for collectors that need to report platform-specific availability.
 */
interface DiagnosticsCollectorAvailabilityProvider {
    val availability: DiagnosticsCollectorAvailability
}

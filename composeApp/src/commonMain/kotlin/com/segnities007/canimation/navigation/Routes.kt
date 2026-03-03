package com.segnities007.canimation.navigation

import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data object PresetGalleryRoute

@Serializable
data object CustomSpecLabRoute

@Serializable
data object A11yDemoRoute

@Serializable
data object DiagnosticsRoute

@Serializable
data object TokenReferenceRoute

@Serializable
data object DocsRoute

@Serializable
data object ExamplesRoute

@Serializable
data class ExampleDetailRoute(val categoryId: String)

fun routeTitle(route: String?): String = when {
    route == null || route.contains("HomeRoute") -> "canimation"
    route.contains("PresetGalleryRoute") -> "Preset Gallery"
    route.contains("CustomSpecLabRoute") -> "Custom Spec Lab"
    route.contains("A11yDemoRoute") -> "Accessibility"
    route.contains("DiagnosticsRoute") -> "Diagnostics"
    route.contains("TokenReferenceRoute") -> "Token Reference"
    else -> "canimation"
}

package com.segnities007.canimation.navigation

import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data object PresetGalleryRoute

@Serializable
data object DocsRoute

@Serializable
data object ExamplesRoute

@Serializable
data class ExampleDetailRoute(val categoryId: String)

fun routeTitle(route: String?): String = when {
    route == null || route.contains("HomeRoute") -> "canimation"
    route.contains("PresetGalleryRoute") -> "Preset Gallery"
    else -> "canimation"
}

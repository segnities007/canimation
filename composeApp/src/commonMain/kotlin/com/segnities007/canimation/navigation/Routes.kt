package com.segnities007.canimation.navigation

import kotlinx.serialization.Serializable

const val ROUTE_KEY_HOME = "HomeRoute"
const val ROUTE_KEY_PRESET_GALLERY = "PresetGalleryRoute"
const val ROUTE_KEY_DOCS = "DocsRoute"
const val ROUTE_KEY_API_REFERENCE = "ApiReferenceRoute"
const val ROUTE_KEY_EXAMPLES = "ExamplesRoute"

enum class HomeDestination {
    PresetGallery,
    Examples,
    Docs,
    ApiReference,
}

enum class TopBarDestination(
    val label: String,
    val routeKey: String,
) {
    Docs(label = "Docs", routeKey = ROUTE_KEY_DOCS),
    Api(label = "API", routeKey = ROUTE_KEY_API_REFERENCE),
    Gallery(label = "Gallery", routeKey = ROUTE_KEY_EXAMPLES),
}

fun String?.matchesRoute(routeKey: String): Boolean = this?.contains(routeKey) == true

@Serializable
data object HomeRoute

@Serializable
data object PresetGalleryRoute

@Serializable
data object DocsRoute

@Serializable
data object ApiReferenceRoute

@Serializable
data object ExamplesRoute

@Serializable
data class ExampleDetailRoute(val itemIndex: Int = 0)

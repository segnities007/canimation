package com.segnities007.canimation.navigation

import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.nav_docs
import canimation.composeapp.generated.resources.nav_api
import canimation.composeapp.generated.resources.nav_gallery

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
    val labelRes: StringResource,
    val routeKey: String,
) {
    Docs(labelRes = Res.string.nav_docs, routeKey = ROUTE_KEY_DOCS),
    Api(labelRes = Res.string.nav_api, routeKey = ROUTE_KEY_API_REFERENCE),
    Gallery(labelRes = Res.string.nav_gallery, routeKey = ROUTE_KEY_EXAMPLES),
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

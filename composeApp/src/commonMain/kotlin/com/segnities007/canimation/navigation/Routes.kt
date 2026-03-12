package com.segnities007.canimation.navigation

import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import canimation.composeapp.generated.resources.Res
import canimation.composeapp.generated.resources.nav_docs
import canimation.composeapp.generated.resources.nav_api
import canimation.composeapp.generated.resources.nav_gallery

@Serializable
data object HomeRoute

@Serializable
data object PresetGalleryRoute

@Serializable
data object DocsRoute

@Serializable
data object ApiReferenceRoute

@Serializable
data object ShowcaseGalleryRoute

@Serializable
data class ShowcaseDetailRoute(val itemIndex: Int = 0)

val ROUTE_KEY_HOME: String = HomeRoute.serializer().descriptor.serialName
val ROUTE_KEY_PRESET_GALLERY: String = PresetGalleryRoute.serializer().descriptor.serialName
val ROUTE_KEY_DOCS: String = DocsRoute.serializer().descriptor.serialName
val ROUTE_KEY_API_REFERENCE: String = ApiReferenceRoute.serializer().descriptor.serialName
val ROUTE_KEY_SHOWCASE_GALLERY: String = ShowcaseGalleryRoute.serializer().descriptor.serialName

enum class HomeDestination {
    PresetGallery,
    ShowcaseGallery,
    Docs,
    ApiReference,
}

enum class TopBarDestination(
    val labelRes: StringResource,
    val routeKey: String,
) {
    Docs(labelRes = Res.string.nav_docs, routeKey = ROUTE_KEY_DOCS),
    Api(labelRes = Res.string.nav_api, routeKey = ROUTE_KEY_API_REFERENCE),
    Gallery(labelRes = Res.string.nav_gallery, routeKey = ROUTE_KEY_SHOWCASE_GALLERY),
}

fun String?.matchesRoute(routeKey: String): Boolean {
    val route = this ?: return false
    return route == routeKey || route.startsWith("$routeKey/") || route.startsWith("$routeKey?")
}

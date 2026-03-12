package com.segnities007.canimation.navigation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RoutesTest {
    @Test
    fun routeKeysFollowSerializableRouteNames() {
        assertEquals(DocsRoute.serializer().descriptor.serialName, ROUTE_KEY_DOCS)
        assertEquals(ApiReferenceRoute.serializer().descriptor.serialName, ROUTE_KEY_API_REFERENCE)
        assertEquals(PresetGalleryRoute.serializer().descriptor.serialName, ROUTE_KEY_PRESET_GALLERY)
        assertEquals(ShowcaseGalleryRoute.serializer().descriptor.serialName, ROUTE_KEY_SHOWCASE_GALLERY)
    }

    @Test
    fun matchesRouteSupportsExactAndBoundedMatches() {
        assertTrue(ROUTE_KEY_DOCS.matchesRoute(ROUTE_KEY_DOCS))
        assertTrue("${ROUTE_KEY_DOCS}?tab=intro".matchesRoute(ROUTE_KEY_DOCS))
        assertTrue("${ROUTE_KEY_SHOWCASE_GALLERY}/featured".matchesRoute(ROUTE_KEY_SHOWCASE_GALLERY))
    }

    @Test
    fun matchesRouteRejectsSubstringCollisions() {
        assertFalse("${ROUTE_KEY_DOCS}Extra".matchesRoute(ROUTE_KEY_DOCS))
        assertFalse("prefix$ROUTE_KEY_DOCS".matchesRoute(ROUTE_KEY_DOCS))
        assertFalse(null.matchesRoute(ROUTE_KEY_DOCS))
    }
}
